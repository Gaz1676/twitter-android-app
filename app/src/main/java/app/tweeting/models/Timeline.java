package app.tweeting.models;

import android.util.Log;

import java.util.ArrayList;

import static app.tweeting.helpers.LogHelpers.info;

// Uses the serializer to load and save the timeline
public class Timeline {

    public ArrayList<Tweet> tweets;
    private TweetSerializer tweetSerializer;


    // loads constructor with creation of timeline
    public Timeline(TweetSerializer tweetSerializer) {
        this.tweetSerializer = tweetSerializer;
        try {
            tweets = tweetSerializer.loadTweets();
        } catch (Exception e) {
            info(this, "Error loading tweets: " + e.getMessage());
            tweets = new ArrayList<>();
        }
    }


    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        this.saveTweets();
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


    public void deleteTweet(Tweet tweet) {
        tweets.remove(tweet);
        saveTweets();
    }


    // save method called when data may have changed
    public boolean saveTweets() {
        try {
            tweetSerializer.saveTweets(tweets);
            info(this, "Tweets saved to file");
            return true;
        } catch (Exception e) {
            info(this, "Error saving tweets: " + e.getMessage());
            return false;
        }
    }
}