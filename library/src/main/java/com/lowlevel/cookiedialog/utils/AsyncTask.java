package com.lowlevel.cookiedialog.utils;

import android.os.Build;

public abstract class AsyncTask<Params, Progress, Result> extends android.os.AsyncTask<Params, Progress, Result> {
    /*
     * Public methods
     */
    public void start(Params... params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            executeOnExecutor(THREAD_POOL_EXECUTOR, params);
        else
            execute(params);
    }
}
