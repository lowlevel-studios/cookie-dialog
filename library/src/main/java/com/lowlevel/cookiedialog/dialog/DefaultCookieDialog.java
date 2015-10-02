package com.lowlevel.cookiedialog.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

public class DefaultCookieDialog extends ICookieDialog {
    public DefaultCookieDialog(Context context) {
        super(context);
    }

    @Override
    protected Dialog onCreateDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        /* Setup dialog */
        builder.setCancelable    (false);
        builder.setMessage       (getMessageRes());
        builder.setPositiveButton(getButtonRes(), this);

        /* Create dialog */
        return builder.create();
    }
}
