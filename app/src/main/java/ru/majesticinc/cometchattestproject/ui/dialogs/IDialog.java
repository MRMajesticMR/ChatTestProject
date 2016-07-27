package ru.majesticinc.cometchattestproject.ui.dialogs;

import ru.majesticinc.cometchattestproject.ui.dialogs.listeners.DialogActionListener;

/**
 * Created by Majestic on 27.07.2016.
 */

public interface IDialog {

    void show();
    void hide();
    boolean isVisible();

    void setDialogActionListener(DialogActionListener dialogActionListener);

}
