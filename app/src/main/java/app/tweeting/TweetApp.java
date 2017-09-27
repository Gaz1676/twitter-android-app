package app.tweeting;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.util.Log;

// any CRUD done through this class
public class TweetApp extends Application {
    public final int target = 10000;
    public int totalDonated = 0;
    //public List<Tweet> tweets = new ArrayList<>(); // creates new donations arraylist
    public List<User> users = new ArrayList<>(); // creates new users arraylist

   /* public boolean newTweets(Tweets tweets) {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved) {
            tweets.add(tweets);
            totalDonated += tweets.amount;
        } else {
            Toast toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT);
            toast.show();
        }
        return targetAchieved;
    }*/

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
        Log.v("Donate", "Donation App Started");
    }
}