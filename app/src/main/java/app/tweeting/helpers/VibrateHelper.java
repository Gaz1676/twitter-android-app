/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Created: Nov 9th 2017
 */

package app.tweeting.helpers;

import android.content.Context;
import android.os.Vibrator;

/**
 * VibrateHelper is used across multiple classes
 * Vibrate feature vibrates when invalid input is selected
 * The Helper is used to reduce code (DRY) where necessary
 */

// https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate

public class VibrateHelper {
    public static void vibrator(Context context) {

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        assert v != null;
        v.vibrate(500);
    }
}
