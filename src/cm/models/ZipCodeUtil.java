package cm.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

/*
 * STOPPING POINT: see tabs list in 00notes/TODO-urls.txt
 */


/**
 * Ideally, will provide a getZipsByRadius method that returns a list of zip
 * codes within a given distance from a given destination zip code.
 *
 * TODO: Google maps API seems simplest... Still need to explore the different
 * things we can do with it. We'll use Gson to convert GET responses to a java
 * objects.
 *
 * EXAMPLE REQUEST to get distance-by-car between two zip codes...
 * GET https://maps.googleapis.com/maps/api/distancematrix/json?origins=70831&destinations=70803&mode=car&sensor=false
 * Response:
 *   {
 *     "destination_addresses": [
 *       "Baton Rouge, LA 70803, USA"
 *     ],
 *     "origin_addresses": [
 *       "Baton Rouge, LA 70831, USA"
 *     ],
 *     "rows": [
 *       {
 *         "elements": [
 *           {
 *             "distance": {
 *               "text": "7.4 km",
 *               "value": 7381
 *             },
 *             "duration": {
 *               "text": "16 mins",
 *               "value": 960
 *             },
 *             "status": "OK"
 *           }
 *         ]
 *       }
 *     ],
 *     "status": "OK"
 *   }
 */
public class ZipCodeUtil {

    /* -- field(s) ---------------------------------------------------- */
    private String destinationZip;
    private Double radius; // Radius filter, in miles.
    private ArrayList<String> zips;

    /* -- constructor(s) ---------------------------------------------- */

    private ZipCodeUtil() { /* */ }

    public ZipCodeUtil(String destinationZip,
                       Double radius) {
        this.destinationZip = destinationZip;
        this.radius = radius;
    }

    /* -- methods) ---------------------------------------------------- */

    public ArrayList<String> getZipsByRadius() {

        return zips;
    }

    /* -- misc method(s) ---------------------------------------------- */

    /**
     * Main method, used just for testing.
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String destinationZip = "70803";
        Double radius = 10.0; // <-- not sure how to use this yet
        String sourceZip1 = "70831";

        StringBuilder sbRequestUrl = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();
        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?mode=car&sensor=false" +
                          "&origins=" + sourceZip1 +
                          "&destinations=" + destinationZip);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null)
            sbResult.append(line);
        rd.close();
        conn.disconnect();
        System.out.println(sbResult.toString());
    }


}
