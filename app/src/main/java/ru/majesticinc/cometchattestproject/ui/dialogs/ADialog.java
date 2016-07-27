package ru.majesticinc.cometchattestproject.ui.dialogs;

import ru.majesticinc.cometchattestproject.ui.dialogs.listeners.DialogActionListener;

/**
 * Created by Majestic on 27.07.2016.
 */

public abstract class ADialog implements IDialog {

    protected DialogActionListener dialogActionListener;

    @Override
    public void setDialogActionListener(DialogActionListener dialogActionListener) {
        this.dialogActionListener = dialogActionListener;
    }
}
