package ru.majesticinc.cometchattestproject.ui.dialogs.impl;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Majestic on 27.07.2016.
 */

public class ConnectionProgressDialog {

    private ProgressDialog dialog;

    public ConnectionProgressDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Подключение...");
        dialog.setMessage("Пожалуйста, подождите...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
    }

    public void show() {
        dialog.show();
    }

    public void hide() {
        dialog.hide();
    }

}
