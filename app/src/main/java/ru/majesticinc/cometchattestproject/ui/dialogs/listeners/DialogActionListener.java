package ru.majesticinc.cometchattestproject.ui.dialogs.listeners;

import ru.majesticinc.cometchattestproject.ui.dialogs.IDialog;

/**
 * Created by Majestic on 27.07.2016.
 */

public interface DialogActionListener {

    void onDialogCanceled(IDialog dialog);
    void onDialogConfirmed(IDialog dialog);

}
