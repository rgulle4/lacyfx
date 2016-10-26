package cm.models;

import org.junit.Test;

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
        assertFalse(zcu.isValidZipCode(null));
    }
}
