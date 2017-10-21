package app.tweeting.models;

import android.util.Log;

import java.util.ArrayList;

import static app.tweeting.helpers.LogHelpers.info;

// Uses the serializer to read and write the tweet list
public class Timeline {
    public ArrayList<Tweet> tweets;
    private TimelineSerializer serializer;

    /*public Timeline() {
        tweets = new ArrayList<>();
        //this.generateTestData();
    }*/

    // Read - in constructor with creation of timeline
    public Timeline(TimelineSerializer serializer) {
        this.serializer = serializer;
        try {
            tweets = serializer.loadTweets();
        } catch (Exception e) {
            info(this, "Error loading tweets: " + e.getMessage());
            tweets = new ArrayList<>();
        }
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public Tweet getTweet(Long id) {
        Log.i(this.getClass().getSimpleName(), "Long parameter id: " + id);

        for (Tweet tweet : tweets) {
            if (id.equals(tweet.id)) {
                return tweet;
            }
        }
        return null;
    }

    // Write - in method called when data may have changed
    public boolean saveTweets() {
        try {
            serializer.saveTweets(tweets);
            info(this, "Tweets saved to file");
            return true;
        } catch (Exception e) {
            info(this, "Error saving tweets: " + e.getMessage());
            return false;
        }
    }

    public void deleteTweet(Tweet tweet) {
        tweets.remove(tweet);
        saveTweets();
    }
}