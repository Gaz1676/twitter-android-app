package app.tweeting.main;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import app.tweeting.models.Timeline;
import app.tweeting.models.TweetSerializer;
import app.tweeting.models.User;
import app.tweeting.models.UserSerializer;
import app.tweeting.models.UserStore;

import static app.tweeting.helpers.LogHelpers.info;

public class MyTweetApp extends Application {

    public Timeline timeline;
    private static final String FILENAME_TWEETS = "tweets.json"; // Creates the serializer giving in a file name to use
    private static final String FILENAME_USERS = "users.json"; // Creates the serializer giving in a file name to use
    public UserStore userStore;
    public List<User> users = new ArrayList<>(); // creates new users arraylist
    protected static MyTweetApp app;


    // Passes the serializer to the Timeline object which will be responsible
    // for loading / saving to the file using the serializer

    // called to do initial creation of the fragment
    @Override
    public void onCreate() {
        super.onCreate();
        TweetSerializer tweetSerializer = new TweetSerializer(this, FILENAME_TWEETS);
        UserSerializer userSerializer = new UserSerializer(this, FILENAME_USERS);
        timeline = new Timeline(tweetSerializer);
        userStore = new UserStore(userSerializer);
        users = userStore.users;
        app = this;

        info(this, "MyTweetApp is launched");
    }


    // adds new user to userStore collection
    public void newUser(User user) {
        users.add(user);
        userStore.users = (ArrayList<User>) users;
        userStore.saveUsers();
    }


    // validation of the users by its credentials
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