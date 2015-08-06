package com.lowlevel.cookiedialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.lowlevel.cookiedialog.location.LocationUtils;
import com.lowlevel.cookiedialog.utils.AsyncTask;

public class CookieManager implements DialogInterface.OnClickListener {
    /*
     * Private constants
     */
    private static final String KEY = "cd_wasShown";

    /*
     * Public enumerator
     */
    public enum Type {
        DIALOG,
        OVERLAY
    }

    /*
     * Private variables
     */
    private Activity     mActivity;
    private LocationTask mTask;


    public CookieManager(Activity activity) {
        /* Set activity */
        mActivity = activity;
    }


    /*
     * Listener methods
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {
        /* Set preference */
        setPreference(true);
    }


    /*
     * Private methods
     */
    private void internalShow(Type type) {
        /* Check type */
        switch (type) {
        case DIALOG:
            showDialog();
            break;

        case OVERLAY:
            showOverlay();
            break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        /* Setup dialog */
        builder.setCancelable    (false);
        builder.setMessage       (R.string.cd_message);
        builder.setPositiveButton(R.string.cd_ok, this);

        /* Show dialog */
        builder.show();
    }

    private void showOverlay() {
        /* Show overlay */
        CookieOverlay.show(mActivity);
    }


    /*
     * Public methods
     */
    public void cancel() {
        /* Cancel task */
        if (mTask != null)
            mTask.cancel(true);
    }

    public void setPreference(boolean shown) {
        /* Set preference */
        setPreference(mActivity, shown);
    }

    public void show() {
        /* Show dialog */
        show(true, Type.DIALOG);
    }

    public void show(boolean onlyForEu) {
        /* Show dialog */
        show(onlyForEu, Type.DIALOG);
    }

    public void show(boolean onlyForEu, Type type) {
        /* Show message */
        if (!onlyForEu) {
            internalShow(type);
            return;
        }

        /* Start task */
        mTask = new LocationTask(type);
        mTask.start();
    }

    public void showOnce() {
        /* Show once */
        showOnce(true, Type.DIALOG);
    }

    public void showOnce(boolean onlyForEu) {
        /* Show once */
        showOnce(onlyForEu, Type.DIALOG);
    }

    public void showOnce(boolean onlyForEu, Type type) {
        /* Check state */
        if (wasShown())
            return;

        /* Show message */
        show(onlyForEu, type);
    }

    public boolean wasShown() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mActivity);

        /* Return preference */
        return sp.getBoolean(KEY, false);
    }


    /*
     * Static methods
     */
    public static void setPreference(Context context, boolean shown) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        /* Get editor */
        SharedPreferences.Editor editor = sp.edit();

        /* Put preference */
        editor.putBoolean(KEY, shown);

        /* Commit change */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
            editor.apply();
        else
            editor.commit();
    }


    /*
     * Private classes
     */
    private class LocationTask extends AsyncTask<Void, Void, Boolean> {
        /*
         * Private variables
         */
        private Type mType;


        public LocationTask(Type type) {
            /* Set type */
            mType = type;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            /* Check country */
            return LocationUtils.isEuCountry(mActivity);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            /* Check result */
            if (!result) {
                setPreference(true);
                return;
            }

            /* Show message */
            internalShow(mType);
        }
    }
}
