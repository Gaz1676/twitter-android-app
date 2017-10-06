package app.tweeting.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.tweeting.activity.Tweet;
import app.tweeting.model.Tweets;
import app.tweeting.model.User;

// any CRUD done through this class
public class TweetApp extends Application {

    public final int target = 140;
    public int totalTweet = 0;
    public List<Tweet> tweets = new ArrayList<>(); // creates new tweet arraylist
    public List<User> users = new ArrayList<>(); // creates new users arraylist

    public boolean newTweet(Tweets tweets) {
        boolean targetAchieved = totalTweet > target;

            Toast toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT);
            toast.show();
        return targetAchieved;
    }


    public void newUser(User user) {
        users.add(user);
    }

    public boolean validUser (String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Tweet", "Tweeting App Started");
    }
}