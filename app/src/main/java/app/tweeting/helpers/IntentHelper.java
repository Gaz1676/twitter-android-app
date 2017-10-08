package app.tweeting.helpers;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;

// useful location for methods that involved intents, either explicit or implicit.
public class IntentHelper {

    // to support enhanced navigation, we define an additional Helper method in the IntentHelper class
    public static void navigateUp(Activity parent) {
        Intent upIntent = NavUtils.getParentActivityIntent(parent);
        NavUtils.navigateUpTo(parent, upIntent);
    }

    // here we introduce a new method to trigger contact list and email access
    public static void selectContact(Activity parent, int id)
    {
        Intent selectContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        parent.startActivityForResult(selectContactIntent, id);
    }
}
