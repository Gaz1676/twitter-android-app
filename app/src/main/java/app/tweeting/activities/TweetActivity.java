/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import app.tweeting.R;
import app.tweeting.fragments.ReadTweetFragment;
import app.tweeting.fragments.TweetFragment;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.Tweet;

import static app.tweeting.fragments.ReadTweetFragment.EXTRA_TWEET_ID;


public class TweetActivity extends AppCompatActivity {
    MyTweetApp app = MyTweetApp.getApp();
    ActionBar actionBar;

    /**
     * Tweet Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic05-b/book-a-myrent-07%20(Fragments)/index.html#/04
     */

    // called to do initial creation of the fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        actionBar = getSupportActionBar();

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        Long tweetId = (Long) getIntent().getSerializableExtra(EXTRA_TWEET_ID);
        Tweet tweet = app.timeline.getTweet(tweetId);

        // if tweet is tweeted by current user it can be edited
        // if not users tweet it is Read Only viewing
        if (fragment == null) {
            if (tweet.getUserId().equals(app.currentUser.id)) {
                fragment = new TweetFragment();
                manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
            } else {
                fragment = new ReadTweetFragment();
                manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
            }
        }


    }
}
