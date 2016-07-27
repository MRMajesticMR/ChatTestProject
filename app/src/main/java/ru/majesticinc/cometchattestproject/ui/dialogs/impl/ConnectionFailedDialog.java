package ru.majesticinc.cometchattestproject.ui.dialogs.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import ru.majesticinc.cometchattestproject.ui.dialogs.ADialog;

/**
 * Created by Majestic on 27.07.2016.
 */

public class ConnectionFailedDialog extends ADialog implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {

    private AlertDialog alertDialog;

    public ConnectionFailedDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Ошибка подключения");
        dialogBuilder.setMessage("Проверьте подключение к Интернету и повторите попытку.");
        dialogBuilder.setPositiveButton("Повторить", this);
        dialogBuilder.setNegativeButton("Выйти", this);
        dialogBuilder.setOnCancelListener(this);

        alertDialog = dialogBuilder.create();
    }

    @Override
    public void show() {
        alertDialog.show();
    }

    @Override
    public void hide() {
        alertDialog.hide();
    }

    @Override
    public boolean isVisible() {
        return alertDialog.isShowing();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                if(dialogActionListener != null)
                    dialogActionListener.onDialogConfirmed(this);
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                if(dialogActionListener != null) {
                    dialogActionListener.onDialogCanceled(this);
                }
                break;
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if(dialogActionListener != null) {
            dialogActionListener.onDialogCanceled(this);
        }
    }
}
