package com.lowlevel.cookiedialog.location.locators;

import android.content.Context;

import com.lowlevel.cookiedialog.utils.IOUtils;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

public class ApiLocator implements ILocator {
    @Override
    public String getCountry(Context context) {
        try {
            /* Get country */
            return getCountry();
        }
        catch (Exception e) {
            return null;
        }
    }


    /*
     * Private methods
     */
    private String getCountry() throws Exception {
        HttpURLConnection conn = null;

        /* Create URL */
        URL url = new URL("http://ip-api.com/json");

        try {
            /* Open connection */
            conn = (HttpURLConnection)url.openConnection();

            /* Get string */
            String s = IOUtils.toString(conn.getInputStream(), conn.getContentEncoding());

            /* Parse JSON */
            JSONObject jo = new JSONObject(s);

            /* Return country */
            return jo.getString("countryCode");
        }
        finally {
            /* Disconnect */
            if (conn != null)
                conn.disconnect();
        }
    }
}
