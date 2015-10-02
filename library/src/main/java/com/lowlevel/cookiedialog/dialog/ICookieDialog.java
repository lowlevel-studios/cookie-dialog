package com.lowlevel.cookiedialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.lowlevel.cookiedialog.CookieManager;
import com.lowlevel.cookiedialog.R;

public abstract class ICookieDialog implements DialogInterface.OnClickListener {
    /*
     * Private variables
     */
    private Context mContext;


    public ICookieDialog(Context context) {
        /* Set context */
        mContext = context;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        /* Set preference */
        CookieManager.setPreference(mContext, true);
    }


    /*
     * Protected methods
     */
    protected int getButtonRes() {
        return R.string.cd_ok;
    }

    protected int getMessageRes() {
        return R.string.cd_message;
    }

    protected abstract Dialog onCreateDialog(Context context);


    /*
     * Public methods
     */
    public final Dialog create() {
        /* Create dialog */
        return onCreateDialog(mContext);
    }

    public final void show() {
        /* Show dialog */
        create().show();
    }
}
