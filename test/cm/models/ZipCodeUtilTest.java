package cm.models;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by royg59 on 10/26/16.
 */
public class ZipCodeUtilTest {

    ZipCodeUtil zcu = new ZipCodeUtil();

    String invalidZip1 = "99"; // invalid zip
    String invalidZip2 = "6454"; // invalid zip
    String destinationZip1 = "70803"; // Baton Rouge

    String originZip1 = "70115"; // New Orleans
    String originZip2 = "70601"; // Lake Charles
    String originZip3 = "77001"; // Houston

    List<String> originsList = Arrays.asList(originZip1, originZip2, originZip3);

    final Double ZERO = new Double(0);
    final Double NEGATIVE_ONE = new Double(-1);

    @Test
    public void testGetDistance() throws Exception {
        // should return zero if DESTINATION_ZIP_CODE_MUTABLE is null
        Model.DESTINATION_ZIP_CODE_MUTABLE = null;
        assertEquals(ZERO, zcu.getDistance(originZip1));

        // should return zero if DESTINATION_ZIP_CODE_MUTABLE is invalid
        Model.DESTINATION_ZIP_CODE_MUTABLE = invalidZip1;
        assertEquals(ZERO, zcu.getDistance(originZip1));

        // should return zero if originZip arg is invalid
        Model.DESTINATION_ZIP_CODE_MUTABLE = destinationZip1;
        assertEquals(ZERO, zcu.getDistance(invalidZip1));
    }

    @Test
    public void testGetDistance1() throws Exception {
        // setup
        Model.DESTINATION_ZIP_CODE_MUTABLE = destinationZip1;

        // should return zero if given at least one invalid zip
        assertEquals(ZERO, zcu.getDistance(originZip1, invalidZip1));
        assertEquals(ZERO, zcu.getDistance(invalidZip1, destinationZip1));
        assertEquals(ZERO, zcu.getDistance(invalidZip1, invalidZip2));

        // 70115 to 70803 should be 133917 meters (83.2 miles)
        assertEquals(new Double(133917), zcu.getDistance("70115", "70803"));
    }

    @Test
    public void testIsValidZipCode() throws Exception {
        assertTrue(zcu.isValidZipCode("12345"));
        assertTrue(zcu.isValidZipCode("12345-6789"));
        assertFalse(zcu.isValidZipCode("1234"));
        assertFalse(zcu.isValidZipCode("123456"));
        assertFalse(zcu.isValidZipCode(null));
    }

    @Test
    public void testGetDistances() throws Exception {
        // setup
        Model.DESTINATION_ZIP_CODE_MUTABLE = destinationZip1;

        Map<String, Double> distances
              = zcu.getDistances(originsList, destinationZip1);

        assertEquals(originsList.size(), distances.size());
        assertEquals(new Double(133917), distances.get("70115"), 133917.0 * 0.05);
        assertEquals(new Double(434705), distances.get("77001"), 434705 * 0.05);
    }

    @Test
    public void testExtractZipFromAddress() throws Exception {
        String address1 = "New Orleans, LA 70115, USA";
        String address2  = "Lake Charles, LA 70601, USA";
        String address3 = "Houston, TX 77001-1234, USA";

        String result1 = zcu.extractZipFromAddress(address1);
        assertEquals("70115", result1);

        String result2 = zcu.extractZipFromAddress(address2);
        assertEquals("70601", result2);

        String result3 = zcu.extractZipFromAddress(address3);
        assertEquals("77001-1234", result3);
    }

    @Test
    public void testZipsWithinRadius() throws Exception {
        Double radius = 250000.0;

        // https://maps.googleapis.com/maps/api/distancematrix/json?&mode=car&sensor=false&units=imperial&destinations=70803&origins=70115|70601|77001|12|00000|33

        List<String> origins = Arrays.asList(
              "70115",  // New Orleans
              "70601",  // Lake Charles
              "77001",  // Houston
              "12",     // invalid zipcode
              "00000",  // "valid" zip code, but doesn't exist
              "33"      // invalid zip code, but actually in France
        );

        Map<String, Double> results =
              zcu.zipsWithinRadius(
                    radius,
                    origins,
                    destinationZip1);

        // sanity check... our original list has 3 zips
        assertEquals(6, origins.size());

        // only 3 zips should be within 250km
        assertEquals(2, results.size());

        // The Houston zip code should not pass the filter.
        assertEquals(false, results.keySet().contains("77001"));

        // The New Orleans zip code should pass the filter, and
        // it should be mapped to its distance, 133917 meters.
        assertEquals(true, results.keySet().contains("70115"));
        assertEquals(new Double(133917), results.get("70115"));
    }
}
