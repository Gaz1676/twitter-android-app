package app.tweeting.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;

/* This Tweet model has the ability to save and restore itself
    to an external JSON format.
 */

public class Tweet {

    public Long id;
    public Long date;
    public String contact;
    public String message;

    // string IDs of each field that are to be serialized
    private static final String JSON_ID = "id";
    private static final String JSON_DATE = "date";
    private static final String JSON_CONTACT = "contact";
    private static final String JSON_MESSAGE = "message";


    public Tweet() {
        id = unsignedLong();
        date = new Date().getTime();
        contact = ":none presently";
    }


    private Long unsignedLong() {
        long rndVal = 0;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }


    // a constructor that loads tweet objects from JSON
    public Tweet(JSONObject json) throws JSONException {
        id = json.getLong(JSON_ID);
        date = json.getLong(JSON_DATE);
        contact = json.getString(JSON_CONTACT);
        message = json.getString(JSON_MESSAGE);
    }


    //  method that saves tweet objects to JSON
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, Long.toString(id));
        json.put(JSON_DATE, date);
        json.put(JSON_CONTACT, contact);
        json.put(JSON_MESSAGE, message);

        return json;
    }


    public String getMessage() {
        return message;
    }


    private String dateString() {
        String dateFormat = "EEE d MMM yyyy H:mm";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }


    public String getDateString() {
        return dateString();
    }

}