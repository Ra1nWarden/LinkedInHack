package com.teamsnapblock.snapblock;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

/**
 * Created by louyang on 7/18/15.
 */
public class RestClient {
    private static final String DEBUG_TAG = "RestClient";
    private static final String BASE_URL = "http://10.16.21.173/hackthon/";

    public static String GET(String myURL) throws IOException {
        InputStream is = null;
        String absoluteURL = getAbsoluteUrl(myURL);

        try {
            URL url = new URL(absoluteURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            return readIt(is);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static String POST(String myURL, JSONObject jsonObject) throws IOException{
        InputStream is = null;
        OutputStream os = null;
        String absoluteURL = getAbsoluteUrl(myURL);

        try {
            URL url = new URL(absoluteURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestMethod("POST");
            if(jsonObject!=null){
                conn.connect();
                os = conn.getOutputStream();
                os = new BufferedOutputStream(conn.getOutputStream());
                os.write(jsonObject.toString().getBytes());
                os.flush();
            }
            else {
                conn.connect();
            }
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            return readIt(is);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
                os.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public static String readIt(InputStream stream) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(stream, writer, "UTF-8");
        return writer.toString();
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}