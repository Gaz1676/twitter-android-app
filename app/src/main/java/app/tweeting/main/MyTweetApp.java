package app.tweeting.main;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import app.tweeting.models.Timeline;
import app.tweeting.models.TimelineSerializer;
import app.tweeting.models.User;

import static app.tweeting.helpers.LogHelpers.info;

public class MyTweetApp extends Application {

    public Timeline timeline;
    private static final String FILENAME = "timeline.json"; // Creates the serializer giving in a file name to use
    public List<User> users = new ArrayList<>(); // creates new users arraylist
    protected static MyTweetApp app;


    // Passes the serializer to the Timeline object
    // which will be responsible for loading / saving
    // to the file using the serializer
    @Override
    public void onCreate() {
        super.onCreate();
        TimelineSerializer serializer = new TimelineSerializer(this, FILENAME);
        timeline = new Timeline(serializer);
        app = this;

        info(this, "MyTweetApp is launched");
    }


    public void newUser(User user) {
        users.add(user);
    }


    public boolean validUser(String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }


    public static MyTweetApp getApp() {
        return app;
    }
}