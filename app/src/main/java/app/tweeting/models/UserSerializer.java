/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

// a class to orchestrate the serialization of a
// collection of a user object to / from disk

public class UserSerializer {

    private Context Context;
    private String Filename;


    public UserSerializer(Context context, String filename) {
        Context = context;
        Filename = filename;
    }


    // creates a JSON Array object
    public void saveUsers(ArrayList<User> users) throws JSONException, IOException {

        // places each user in turn into the object
        JSONArray array = new JSONArray();
        for (User user : users)
            array.put(user.toJSON());

        // write the file to file / disk
        Writer writer = null;
        try {
            OutputStream out = Context.openFileOutput(Filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }


    // creates User Array
    public ArrayList<User> loadUsers() throws IOException, JSONException {

        // attach a reader to the file (via a buffered reader)
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = null;

        try {
            // open and read the file into a StringBuilder
            InputStream in = Context.openFileInput(Filename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {

                // reads the file into a string
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }

            // tokenizes the string into individual json objects
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();


            // build the array of users from JSONObjects
            // add to our user list
            for (int i = 0; i < array.length(); i++) {
                users.add(new User(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return users;
    }
}

