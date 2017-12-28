/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.helpers;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;

import java.io.Serializable;

/**
 * IntentHelper is used across multiple classes
 * Intent feature helps to launch types of Activities
 * Useful location for methods that involve intents
 * The Helper is used to reduce code (DRY) where necessary
 */

public class IntentHelper {

    // starts the activity described by the intent
    public static void startActivity(Activity parent, Class classname) {
        Intent intent = new Intent(parent, classname);
        parent.startActivity(intent);
    }

    // starts the new activity with its data
    public static void startActivityWithData(Activity parent, Class classname, String extraID, Serializable extraData) {
        Intent intent = new Intent(parent, classname);
        intent.putExtra(extraID, extraData);
        parent.startActivity(intent);
    }

    public static void startActivityWithDataForResult (Activity parent, Class classname, String extraID, Serializable extraData, int idForResult)
    {
        Intent intent = new Intent(parent, classname);
        intent.putExtra(extraID, extraData);
        parent.startActivityForResult(intent, idForResult);
    }


    // to support enhanced navigation, we define an additional Helper method in the IntentHelper class
    public static void navigateUp(Activity parent) {
        Intent upIntent = NavUtils.getParentActivityIntent(parent);
        NavUtils.navigateUpTo(parent, upIntent);
    }

    public static void selectContact(Activity parent, int id)
    {
        Intent selectContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        parent.startActivityForResult(selectContactIntent, id);
    }
}
