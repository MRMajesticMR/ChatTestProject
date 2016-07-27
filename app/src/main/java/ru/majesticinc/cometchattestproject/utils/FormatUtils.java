package ru.majesticinc.cometchattestproject.utils;

/**
 * Created by Majestic on 27.07.2016.
 */

public class FormatUtils {

    public static String extractUsernameFromLogin(String login) {
        if(login == null) {
            return null;

        } else if(!login.contains("@")) {
            return login;

        } else {
            return login.substring(0, login.indexOf("@"));
        }
    }

}
