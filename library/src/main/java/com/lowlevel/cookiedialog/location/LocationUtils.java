package com.lowlevel.cookiedialog.location;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lowlevel.cookiedialog.location.locators.ApiLocator;
import com.lowlevel.cookiedialog.location.locators.ILocator;
import com.lowlevel.cookiedialog.location.locators.LocationLocator;
import com.lowlevel.cookiedialog.location.locators.TelephonyLocator;

import java.util.Arrays;
import java.util.List;

public class LocationUtils {
    /*
     * Private constants
     */
    private static final List<String> COUNTRIES = Arrays.asList("at", "be", "bg", "cy", "cz", "de", "dk", "ee", "es", "fi", "fr", "gb", "gr", "hr", "hu", "ie", "it", "lt", "lu", "lv", "mt", "nl", "pl", "pt", "ro", "se", "si", "sk");

    private static final List<ILocator> LOCATORS = Arrays.asList(
        new TelephonyLocator(),
        new LocationLocator(),
        new ApiLocator()
    );

    private static final String TAG = "LocationUtils";


    /*
     * Public methods
     */
    public static String getCountry(Context context) {
        /* Loop locators */
        for (ILocator l : LOCATORS) {
            String country;

            /* Get country */
            country = l.getCountry(context);

            /* Check value */
            if (TextUtils.isEmpty(country))
                continue;

            /* To lower-case */
            country = country.toLowerCase();

            /* Print debug */
            Log.d(TAG, "Country detected using " + l.getClass().getSimpleName() + ": " + country);

            /* Return value */
            return country;
        }

        /* Print debug */
        Log.d(TAG, "Country not detected");

        return null;
    }

    public static boolean isEuCountry(Context context) {
        String country = getCountry(context);

        /* Check value */
        if (TextUtils.isEmpty(country))
            return true;

        /* Check country */
        return COUNTRIES.contains(country);
    }
}
