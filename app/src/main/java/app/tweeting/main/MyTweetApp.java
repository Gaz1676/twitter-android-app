/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.main;

import android.app.Application;

import app.tweeting.models.Timeline;
import app.tweeting.models.TimelineSerializer;
import app.tweeting.models.User;
import app.tweeting.models.UserSerializer;
import app.tweeting.models.UserStore;

import static app.tweeting.helpers.LogHelpers.info;

public class MyTweetApp extends Application {

    public Timeline timeline;
    private static final String FILENAME_TWEETS = "tweets.json"; // Creates the serializer giving in a file name to use
    private static final String FILENAME_USERS = "users.json"; // Creates the serializer giving in a file name to use
    public UserStore userStore;
    public User currentUser;

    protected static MyTweetApp app;


    // Passes the serializer to the Timeline object which will be responsible
    // for loading / saving to the file using the serializer

    // called to do initial creation of the fragment
    @Override
    public void onCreate() {
        super.onCreate();
        TimelineSerializer timelineSerializer = new TimelineSerializer(this, FILENAME_TWEETS);
        UserSerializer serializer = new UserSerializer(this, FILENAME_USERS);
        timeline = new Timeline(timelineSerializer);
        userStore = new UserStore(serializer);
        app = this;

        info(this, "MyTweetApp is launched");
    }


    public boolean validUser(String email, String password) {
        User user = userStore.getUserByEmail(email);
        if (user != null) {
            if (user.email.equals(email) && user.password.equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public static MyTweetApp getApp() {
        return app;
    }
}