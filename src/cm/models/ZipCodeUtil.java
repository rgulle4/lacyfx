package cm.models;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 */
public class ZipCodeUtil {

    /* -- field(s) ---------------------------------------------------- */

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?&mode=car&sensor=false&units=imperial";
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
     * @param destinationZip Zip code of the destination.
     * @return The distance in meters (0 if zips are bad).
     */
    public Double getDistance(String originZip, String destinationZip) {
        if (!isValidZipCode(originZip) || !isValidZipCode(destinationZip))
            return ZERO;
        Gson gson = new Gson();
        Response response1 = getResponse(originZip, destinationZip);
        System.out.println(gson.toJson(response1, Response.class));
        int distance = response1.rows[0].elements[0].distance.value;
        return new Double(distance);
    }

//    public ArrayList<String> getZipsByRadius() {
//
//        return zips;
//    }

    /* -- misc method(s) ---------------------------------------------- */

    /**
     * Returns true if a zipCode string is of the form "12345" or "12345-6789"
     */
    public boolean isValidZipCode(String zipCode) {
        if (zipCode == null)
            return false;
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(zipCode).matches();
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
            while ((line = rd.readLine()) != null) {
                sbResponse.append(line);
            }
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
     * main, used for testing
     */
    public static void main(String[] args) {
        ZipCodeUtil zcu = new ZipCodeUtil();
        Gson gson = new Gson();
        String destinationZip1 = "70803"; // Baton Rouge
        Model.DESTINATION_ZIP_CODE_MUTABLE = destinationZip1;

        String originZip1 = "70115"; // New Orleans
        String originZip2 = "70601"; // Lake Charles
        String originZip3 = "77001"; // Houston

        Response response1 = zcu.getResponse(originZip1, destinationZip1);
        System.out.println(gson.toJson(response1, Response.class));

        int distance = response1.rows[0].elements[0].distance.value;
    }


    /**
     * main1 method, used just for testing.
     */
    public static void main1(String[] args) throws IOException {
        ZipCodeUtil zcu = new ZipCodeUtil();
        String destinationZip1 = "70803"; // Baton Rouge
        Model.DESTINATION_ZIP_CODE_MUTABLE = destinationZip1;

        String originZip1 = "70115"; // New Orleans
        String originZip2 = "70601"; // Lake Charles
        String originZip3 = "77001"; // Houston
        String expectedUrl1 = "https://maps.googleapis.com/maps/api/distancematrix/json?&mode=car&sensor=false&units=imperial&destinations=70803&origins=70115";


        List<String> originsList = Arrays.asList(originZip1, originZip2, originZip3);
        String builtUrl = zcu.buildUrl(originsList);
//        String builtUrl = zcu.buildUrl(originZip1);

        System.out.println(builtUrl);
        System.out.println("---");
        System.out.println(expectedUrl1);
        System.out.println("OK? = " + expectedUrl1.equals(builtUrl));
        System.out.println("--------");

        StringBuilder sbResponse = new StringBuilder();
        URL url = new URL(builtUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
            sbResponse.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println("----");

        String responseString = sbResponse.toString();
        Gson gson = new Gson();
        Response response = gson.fromJson(responseString, Response.class);
//        System.out.println(gson.toJson(responseString));
//        println(response.destination_addresses[0]);

        println();
        println("Destination addresses....");
        printList(response.destination_addresses);

        println();
        println("Origin addresses....");
        printList(response.origin_addresses);

        println();
        println("Distances.... (as (text, value))");
        println("text = " + response.rows[0].elements[0].distance.text);
        println("value = " + response.rows[0].elements[0].distance.value);
    }

    private static void printList(String[] stringArray) {
        printList(Arrays.asList(stringArray));
    }
    private static void printList(List<String> list) {
        for (String s : list) println((new StringBuilder()).append("  - ").append(s));
    }
    private static void println()         { System.out.println(); }
    private static void println(Object o) { System.out.println(o); }

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
