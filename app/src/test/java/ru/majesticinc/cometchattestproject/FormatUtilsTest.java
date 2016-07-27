package ru.majesticinc.cometchattestproject;

import static org.junit.Assert.*;
import org.junit.Test;

import ru.majesticinc.cometchattestproject.utils.FormatUtils;

/**
 * Created by Majestic on 27.07.2016.
 */

public class FormatUtilsTest {

    @Test
    public void extractUsernameFromLogin() {
        String[] input = {
                "mrmajesticmr90@gmail.com",
                "mrmajesticmr90@gmail",
                "mrmajesticmr90",
                "",
                null
        };

        String[] results = {
                "mrmajesticmr90",
                "mrmajesticmr90",
                "mrmajesticmr90",
                "",
                null
        };

        for(int i = 0; i < input.length; i++) {
            assertEquals(results[i], FormatUtils.extractUsernameFromLogin(input[i]));
        }

    }

}
