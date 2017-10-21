package app.tweeting.models;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import app.tweeting.R;

import java.util.Date;
import java.util.Random;

// JSON Support libraries

public class Tweet {
    public Long id;
    public Long date;
    public String tenant;
    public String message;

    // string IDs of each field
    private static final String JSON_ID = "id";
    private static final String JSON_DATE = "date";
    private static final String JSON_TENANT = "tenant";
    private static final String JSON_MESSAGE = "message";


    public Tweet() {
        id = unsignedLong();
        id = new Random().nextLong();
        message = "";
        date = new Date().getTime();
        tenant = ":none presently";
    }

    private Long unsignedLong() {
        long rndVal = 0;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }

    // read a json tweet object
    public Tweet(JSONObject json) throws JSONException {
        id = json.getLong(JSON_ID);
        date = json.getLong(JSON_DATE);
        tenant = json.getString(JSON_TENANT);
        message = json.getString(JSON_MESSAGE);
    }

    //  write json tweet object
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, Long.toString(id));
        json.put(JSON_DATE, date);
        json.put(JSON_TENANT, tenant);
        json.put(JSON_MESSAGE, message);

        return json;
    }

    public String getDateString() {
        return dateString();
    }

    private String dateString() {
        String dateFormat = "EEE d MMM yyyy H:mm";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }

    public String getTweetReport(Context context) {

        String prospectiveTenant = tenant;
        if (tenant == null) {
            prospectiveTenant = context.getString(R.string.tweet_report_nobody_interested);
        } else {
            prospectiveTenant = context.getString(R.string.tweet_report_prospective_tenant, tenant);
        }
        return "Message " + message + " Date: " + dateString() + " " + prospectiveTenant;

    }

}