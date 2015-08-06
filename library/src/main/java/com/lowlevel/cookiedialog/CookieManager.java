package com.lowlevel.cookiedialog;

import android.app.Activity;
import android.app.AlertDialog;
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
    private void setPreference(boolean shown) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mActivity);

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

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        /* Setup dialog */
        builder.setCancelable    (false);
        builder.setMessage       (R.string.cd_message);
        builder.setPositiveButton("OK", this);

        /* Show dialog */
        builder.show();
    }

    private void startTask() {
        /* Create task */
        mTask = new LocationTask();

        /* Start task */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            mTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            mTask.execute();
    }


    /*
     * Public methods
     */
    public void cancel() {
        /* Cancel task */
        if (mTask != null)
            mTask.cancel(true);
    }

    public void show() {
        /* Show dialog */
        show(true);
    }

    public void show(boolean onlyForEu) {
        /* Show dialog */
        if (!onlyForEu) {
            showDialog();
            return;
        }

        /* Start task */
        mTask = new LocationTask();
        mTask.start();
    }

    public void showOnce() {
        /* Show once */
        showOnce(true);
    }

    public void showOnce(boolean onlyForEu) {
        /* Check state */
        if (wasShown())
            return;

        /* Show dialog */
        show(onlyForEu);
    }

    public boolean wasShown() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mActivity);

        /* Return preference */
        return sp.getBoolean(KEY, false);
    }


    /*
     * Private classes
     */
    private class LocationTask extends AsyncTask<Void, Void, Boolean> {
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

            /* Show dialog */
            showDialog();
        }
    }
}
