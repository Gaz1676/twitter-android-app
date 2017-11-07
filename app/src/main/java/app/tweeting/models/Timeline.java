/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.models;

import android.util.Log;

import java.util.ArrayList;

import static app.tweeting.helpers.LogHelpers.info;

// Uses the serializer to load and save the timeline
public class Timeline {

    public ArrayList<Tweet> tweets;
    private TimelineSerializer serializer;


    // loads constructor with creation of timeline
    public Timeline(TimelineSerializer serializer) {
        this.serializer = serializer;
        try {
            tweets = serializer.loadTweets();
        } catch (Exception e) {
            info(this, "Error loading tweets: " + e.getMessage());
            tweets = new ArrayList<>();
        }
    }


    // method to add a tweet to the list
    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }


    // method to get a tweet by its id
    public Tweet getTweet(Long id) {
        Log.i(this.getClass().getSimpleName(), "Long parameter id: " + id);

        for (Tweet tweet : tweets) {
            if (id.equals(tweet.id)) {
                return tweet;
            }
        }
        return null;
    }


    // method to save all the tweets to disk
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


    // method to delete tweet from list
    public void deleteTweet(Tweet tweet) {
        tweets.remove(tweet);
        saveTweets();
    }
}