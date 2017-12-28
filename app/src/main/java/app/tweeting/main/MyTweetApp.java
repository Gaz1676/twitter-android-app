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


    // validates the user by their email & password
    // this lets the app know that it is a current user
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

/*
package app.tweeting.main;


import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import app.tweeting.models.Timeline;
import app.tweeting.models.User;
import app.tweeting.retrofit.MyTweetServiceProxy;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MyTweetApp extends Application {
    static final String TAG = "MyTweetApp";
    public Timeline timeline;
    protected static MyTweetApp app;

    public List<User> users = new ArrayList<>();
    public List<User> currentUsers = new ArrayList<>();

    public String service_url = "https://my-tweet20073381.herokuapp.com/"; //server on now
    public MyTweetServiceProxy tweetService;


    @Override
    public void onCreate() {
        super.onCreate();
        */
/*timeline = new Timeline(getApplicationContext());
        Log.d(TAG, "MyTweet app launched");
        app = this;*//*


        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(service_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        tweetService = retrofit.create(MyTweetServiceProxy.class);
    }

    public static MyTweetApp getApp() {
        return app;
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
}*/
