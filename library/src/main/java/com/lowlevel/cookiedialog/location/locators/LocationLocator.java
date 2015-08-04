package com.lowlevel.cookiedialog.location.locators;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.util.List;

public class LocationLocator implements ILocator {
    @Override
    public String getCountry(Context context) {
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        try {
            /* Get country */
            return getCountry(context, lm);
        }
        catch (Exception e) {
            return null;
        }
    }


    /*
     * Private methods
     */
    private String getCountry(Context context, LocationManager lm) throws Exception {
        Criteria criteria = new Criteria();

        /* Setup criteria */
        criteria.setAccuracy        (Criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired (false);
        criteria.setSpeedRequired   (false);
        criteria.setCostAllowed     (false);

        /* Get best provider */
        String provider = lm.getBestProvider(criteria, true);

        /* Get last location */
        Location location = lm.getLastKnownLocation(provider);

        /* Get coordinates */
        double latitude  = location.getLatitude();
        double longitude = location.getLongitude();

        /* Create geocoder */
        Geocoder geo = new Geocoder(context);

        /* Get from location */
        List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);

        /* Check list */
        if (addresses.isEmpty())
            return null;

        /* Return country */
        return addresses.get(0).getCountryCode();
    }
}
