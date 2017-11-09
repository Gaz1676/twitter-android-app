/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.helpers;

import android.util.Log;

/**
 * LogHelper is used across multiple classes
 * Log feature logs info to the console
 * The Helper is used to reduce code (DRY) where necessary
 */

public class LogHelpers {

    public static void info(Object parent, String message) {
        Log.i(parent.getClass().getSimpleName(), message);
    }
}