package com.lowlevel.cookiedialog;

import android.app.Dialog;
import android.content.Context;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.lowlevel.cookiedialog.dialog.ICookieDialog;

public class MaterialCookieDialog extends ICookieDialog {
    public MaterialCookieDialog(Context context) {
        super(context);
    }

    @Override
    protected Dialog onCreateDialog(Context context) {
        AlertDialogWrapper.Builder builder = new AlertDialogWrapper.Builder(context);

        /* Setup dialog */
        builder.setCancelable    (false);
        builder.setMessage       (getMessageRes());
        builder.setPositiveButton(getButtonRes(), this);

        /* Create dialog */
        return builder.create();
    }
}
