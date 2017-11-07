/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Created: Nov 7th 2017
 */

package app.tweeting.helpers;

import android.app.Activity;
import android.widget.Toast;


public class ToastHelper {
    public static void createToastMessage(Activity parent, String message)
    {
        Toast toast = Toast.makeText(parent, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
