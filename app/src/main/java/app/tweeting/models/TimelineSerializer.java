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
// collection of tweet objects to / from disk

public class TimelineSerializer {

    private Context Context;
    private String Filename;


    public TimelineSerializer(Context context, String filename) {
        Context = context;
        Filename = filename;
    }


    // creates a JSON Array object
    public void saveTweets(ArrayList<Tweet> tweets) throws JSONException, IOException {

        // places each tweet in turn into the object
        JSONArray array = new JSONArray();
        for (Tweet c : tweets)
            array.put(c.toJSON());

        // write the file to file / disk
        Writer writer = null;
        try {
            OutputStream out = Context.openFileOutput(Filename, android.content.Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }


    // Creates Tweet Array
    public ArrayList<Tweet> loadTweets() throws IOException, JSONException {

        // Attach a reader to the file (via a buffered reader)
        ArrayList<Tweet> tweets = new ArrayList<>();
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

            // build the array of tweets from JSONObjects
            // Add to our tweet list
            for (int i = 0; i < array.length(); i++) {
                tweets.add(new Tweet(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return tweets;
    }
}

