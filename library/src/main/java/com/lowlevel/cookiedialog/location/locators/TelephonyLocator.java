package com.lowlevel.cookiedialog.location.locators;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyLocator implements ILocator {
    @Override
    public String getCountry(Context context) {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        try {
            /* Get country */
            return getCountry(tm);
        }
        catch (Exception e) {
            return null;
        }
    }


    /*
     * Private methods
     */
    private String getCountry(TelephonyManager tm) throws Exception {
        /* Get sim country */
        String country = getCountryFromSim(tm);

        /* Check value */
        if (isValid(country))
            return country;

        /* Get network country */
        country = getCountryFromNetwork(tm);

        /* Check value */
        if (isValid(country))
            return country;

        /* Throw exception */
        throw new Exception("No country code found");
    }

    private String getCountryFromNetwork(TelephonyManager tm) {
        /* Check phone type */
        boolean isCdma = (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA);

        /* Return value */
        return isCdma ? null : tm.getNetworkCountryIso();
    }

    private String getCountryFromSim(TelephonyManager tm) {
        /* Return value */
        return tm.getSimCountryIso();
    }

    private boolean isValid(String code) {
        /* Check code */
        return (code != null && code.length() == 2);
    }
}
