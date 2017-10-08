package app.tweeting.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import static app.tweeting.helpers.LogHelpers.info;

// this class will be equipped to use the serializer to save | restore the Tweets it is managing.
// it will use the TweetListSerializer class to do this

public class TweetList {
    public TweetList tweetList;
    public ArrayList<Tweet> tweets;
    // introduced the serializer as a member of the class
    private TweetListSerializer serializer;

    // revised the constructor to take a serializer when it is being initialised
    public TweetList(TweetListSerializer serializer) {
        this.serializer = serializer;
        try {
            tweets = serializer.loadTweets();
        } catch (Exception e) {
            info(this, "Error loading tweets: " + e.getMessage());
            tweets = new ArrayList<>();
        }
    }

    // method to add a tweets to the list
    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    // introduced a new method to save all the tweets to disk
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
    public Tweet getTweet(UUID id) {
        Log.i(this.getClass().getSimpleName(), "Long parameter id: " + id);

        for (Tweet tweet : tweets) {
            if (id.equals(tweet.id)) {
                return tweet;
            }
        }
        return null;
    }

    public void deleteTweet(Tweet tweet) {
        tweets.remove(tweet);
        saveTweets();
    }

    public void clearTweets() {
        tweets.clear();
    }
}