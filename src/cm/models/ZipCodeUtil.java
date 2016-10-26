package cm.models;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cm.models.Model.*;

/*
 * STOPPING POINT: see tabs list in 00notes/TODO-urls.txt
 */


/**
 * Provides methods for dealing with distances (meters) between zip codes.
 * Usage examples:
 *     ZipCodeUtil zcu = new ZipCodeUtil();
 *     Double result1 = zcu.getDistance("70115", "70803"); // returns 133917
 *
 */
public class ZipCodeUtil {

    /* -- example usage ----------------------------------------------- */

    public static void main(String[] args) {
        ZipCodeUtil zcu = new ZipCodeUtil();
        String destination = "70803";
        Double radius = 100000.0; // 250 kilometers
        List<String> origins = Arrays.asList(
              "77001", "70821", "70822", "70823",
              "70115", "70825", "70826", "70601");
        Map<String, Double> filteredZips
              = zcu.zipsWithinRadius(radius,origins,destination);

        // original list has 8 zips.
        System.out.println("origins.size() = " + origins.size());

        // new list has 4 zips.
        System.out.println("filteredZips.size() = " + filteredZips.size());

        System.out.println("newZips within " + radius / 1000 + " kilometers...");
        for (String zip : filteredZips.keySet()) {
            System.out.println(
                  "Zip = " + zip + "... "
                  + filteredZips.get(zip) / 1000 + " kilometers");
        }

    }

    /* -- field(s) ---------------------------------------------------- */

    private static final String BASE_URL = "https://maps.googleapis.com/" +
          "maps/api/distancematrix/json?" +
          "&mode=car" +
          "&sensor=false" +
          "&units=imperial";
    private static final String BASE_DESTINATION_PARAM = "&destinations=" ;
    private static final String BASE_ORIGINS_PARAM = "&origins="; // multiple origins separated by "|"
    private static final String ZIPS_SEPARATOR = "|"; // multiple zips separated by "|"

    private static final Double ZERO = new Double(0);

    /* -- constructor(s) ---------------------------------------------- */

    public ZipCodeUtil() { /* */ }

    /* -- methods) ---------------------------------------------------- */

    /**
     * Distance (meters) from an origin zip code to project DESTINATION_ZIP_CODE;
     * (0 if zips are bad).
     * @param originZip The zip code of the origin.
     * @return The distance in meters (0 if zips are bad).
     */
    public Double getDistance(String originZip) {
        if (Model.DESTINATION_ZIP_CODE_MUTABLE == null)
            return ZERO;
        if (!isValidZipCode(Model.DESTINATION_ZIP_CODE_MUTABLE))
            return ZERO;
        if (!isValidZipCode(originZip))
            return ZERO;
        return getDistance(originZip, DESTINATION_ZIP_CODE_MUTABLE);
    }

    /**
     * Distance (meters) between two zip codes.
     * @param originZip Zip code of the origin.
     * @param destinationZip Optional, zip code of the destination.
     * @return The distance in meters (0 if zips are bad).
     */
    public Double getDistance(String originZip, String destinationZip) {
        if (!isValidZipCode(originZip) || !isValidZipCode(destinationZip))
            return ZERO;
        Gson gson = new Gson();
        Response response1 = getResponse(originZip, destinationZip);
        long distance = response1.rows[0].elements[0].distance.value;
        return new Double(distance);
    }

    /**
     * Maps a list of originZips to their respective distances (meters) to
     * one destinationZip.
     * @param originZips A list of origin zips.
     * @param destinationZip A destination zip.
     * @return A Map String zip -> Double distance (in meters).
     */
    public Map<String, Double> getDistances(List<String> originZips,
                                            String destinationZip)
    {
        if (!isValidZipCode(destinationZip))
            return null;
        Response response = getResponse(originZips, destinationZip);

        int sizeOfOriginZips = originZips.size();
        int sizeOfResponse = response.origin_addresses.length;
        Map<String, Double> result = new LinkedHashMap<>();
        for (int i = 0; i < sizeOfResponse; i++) {
            String address = response.origin_addresses[i];
            String extractedZip = extractZipFromAddress(address);
            Double distance = ZERO;
            if (response.rows[i].elements[0].status.equals("OK")) {
                distance = new Double(
                      response.rows[i].elements[0].distance.value);
            }
            result.put(extractedZip, distance);
        }

        return result;
    }


    /**
     * Given a list of origin zips,returns a map with a keyset that's the
     * subset of the origin zips that are within radius (meters) to the
     * destination; each zip is mapped to its distance (meters).
     * @param radius Radius in meters.
     * @param originZips A list of origin zips.
     * @param destinationZip A destination zip.
     * @return A map from zip -> distance (meters).
     */
    public Map<String, Double> zipsWithinRadius(Double radius,
                                                List<String> originZips,
                                                String destinationZip)
    {
        if (!isValidZipCode(destinationZip))
            return null;
        Response response = getResponse(originZips, destinationZip);


        int sizeOfResponse = response.origin_addresses.length;
        Map<String, Double> result = new LinkedHashMap<>();
        for (int i = 0; i < sizeOfResponse; i++) {
            Double distance = ZERO;
            if (response.rows[i].elements[0].status.equals("OK")) {
                distance = new Double(
                      response.rows[i].elements[0].distance.value);
            }
            if (distance <= radius) {
                String address = response.origin_addresses[i];
                String extractedZip = extractZipFromAddress(address);
                result.put(extractedZip, distance);
            }
        }
        return result;
    }
    public Map<String, Double> zipsWithinRadius(Double radius,
                                                List<String> originZips)
    {
        return zipsWithinRadius(radius, originZips, Model.DESTINATION_ZIP_CODE_MUTABLE);
    }

    /* -- misc method(s) ---------------------------------------------- */

    /**
     * Returns true if a zipCode string is of the form "12345" or "12345-6789"
     */
    public boolean isValidZipCode(String zipCode) {
        if (zipCode == null)
            return false;
        final String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
        final Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(zipCode).matches();
    }

    /**
     * Extracts a 5- or 9-digit zip code string from an address string.
     * @param addressString like "New Orleans, LA 70115, USA"
     * @return A 5- or 9-digit zip code string.
     */
    public String extractZipFromAddress(String addressString) {
        if (addressString == null)
            return null;
        final String regex = "[0-9]{5}(?:-[0-9]{4})?";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(addressString);
        String result = null;
        if (matcher.find())
            result = matcher.group();
        return result;
    }

    /* -- methods for building url ------------------------------------ */

    private String buildUrl(String originZip) {
        return buildUrl(originZip, Model.DESTINATION_ZIP_CODE_MUTABLE);
    }

    private String buildUrl(String originZip, String destinationZip) {
        return buildUrl(Arrays.asList(originZip), destinationZip);
    }

    private String buildUrl(List<String> originZipsList) {
        return buildUrl(originZipsList, Model.DESTINATION_ZIP_CODE_MUTABLE);
    }

    private String buildUrl(List<String> originZipsList, String destinationZip) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(BASE_DESTINATION_PARAM).append(destinationZip);
        sb.append(BASE_ORIGINS_PARAM);
        boolean isFirst = true;
        for (String originZip : originZipsList) {
            if (!isFirst)
                sb.append(ZIPS_SEPARATOR);
            sb.append(originZip);
            isFirst = false;
        }
        return sb.toString();
    }

    /* -- methods for getting raw response ---------------------------- */

    private Response getResponse(String originZip) {
        return getResponse(originZip, Model.DESTINATION_ZIP_CODE_MUTABLE);
    }
    private Response getResponse(String originZip, String destinationZip) {
        return getResponse(Arrays.asList(originZip), destinationZip);
    }
    private Response getResponse(List<String> originZips) {
        return getResponse(originZips, Model.DESTINATION_ZIP_CODE_MUTABLE);
    }
    private Response getResponse(List<String> originZips, String destinationZip) {
        String urlString = buildUrl(originZips, destinationZip);
        URL url;
        HttpURLConnection conn;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(
                  new InputStreamReader(conn.getInputStream()));
            StringBuilder sbResponse = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null)
                sbResponse.append(line);
            rd.close();
            conn.disconnect();
            String responseString = sbResponse.toString();
            Gson gson = new Gson();
            return gson.fromJson(responseString, Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * https://developers.google.com/maps/documentation/distance-matrix/intro
     */
    class Response
    {
        String status;  // "OK"
        String[] destination_addresses; // [ "Baton Rouge, LA 70803, USA" ]
        String[] origin_addresses; // [ "New Orleans, LA 70115, USA", "Lake Charles, LA 70601, USA", "Houston, TX 77001, USA" ]
        Row[] rows;

        class Row {
            Element[] elements;
        }
        class Element {
            Distance distance;
            Duration duration;
            String status; // "Ok"
        }
        class Distance {
            String text; // "83.2 mi"
            int value;   // 133917 (meters)
        }
        class Duration {
            String text; // "1 hour 27 mins"
            int value;   // 5200 (seconds)
        }
    }
}
