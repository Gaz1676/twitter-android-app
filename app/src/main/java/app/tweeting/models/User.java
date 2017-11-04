package app.tweeting.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class User {

    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;


    // string IDs of each field that are to be serialized
    private static final String JSON_ID    = "id";
    private static final String JSON_FIRSTNAME = "firstName";
    private static final String JSON_LASTNAME  = "lastName";
    private static final String JSON_EMAIL     = "email";
    private static final String JSON_PASSWORD  = "password";


    // constructor
    public User(String firstName, String lastName, String email, String password) {
        id = unsignedLong();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    // generate a long greater than zero
    private Long unsignedLong() {
        long rndVal;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }


    // a constructor that loads user objects from JSON
    public User(JSONObject json) throws JSONException {
        id = json.getLong(JSON_ID);
        firstName = json.getString(JSON_FIRSTNAME);
        lastName = json.getString(JSON_LASTNAME);
        email = json.getString(JSON_EMAIL);
        password = json.getString(JSON_PASSWORD);
    }


    //  method that saves user objects to JSON
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, Long.toString(id));
        json.put(JSON_FIRSTNAME, firstName);
        json.put(JSON_LASTNAME, lastName);
        json.put(JSON_EMAIL, email);
        json.put(JSON_PASSWORD, password);
        return json;
    }
}
