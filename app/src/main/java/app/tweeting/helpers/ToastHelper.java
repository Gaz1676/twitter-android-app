/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Created: Nov 7th 2017
 */

package app.tweeting.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import app.tweeting.R;

/**
 * ToastHelper is used across multiple classes
 * Toast feature shows a pop up when something is activated
 * The Helper is used to reduce code (DRY) where necessary
 */

// https://stackoverflow.com/questions/43142062/how-to-change-icon-colors-in-android-studio

public class ToastHelper {

    // pop up message info box
    public static void createToastMessage(Activity parent, String message) {
        Toast toast = Toast.makeText(parent, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    // pop up dialogue box when deleting tweets
    // gives user the option to delete tweet or cancel request
    public static void dialogBox(Context context, DialogInterface.OnClickListener noListener,
                                 DialogInterface.OnClickListener yesListener) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Selection")
                .setMessage("Are you sure you want to do this?")
                .setNegativeButton(android.R.string.no, noListener)
                .setPositiveButton(android.R.string.yes, yesListener)
                .setIcon(R.drawable.warning_icon)
                .create().show();
    }
}
