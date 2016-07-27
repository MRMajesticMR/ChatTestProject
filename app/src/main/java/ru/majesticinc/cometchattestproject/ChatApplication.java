package ru.majesticinc.cometchattestproject;

import android.app.Application;

import com.sendbird.android.SendBird;

import ru.majesticinc.cometchattestproject.utils.Settings;

/**
 * Created by Majestic on 26.07.2016.
 */

public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SendBird.init(this, Settings.SEND_BIRD_APP_ID);
    }
}
