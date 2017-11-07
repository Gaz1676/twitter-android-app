/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.tweeting.R;
import app.tweeting.helpers.ToastHelper;
import app.tweeting.helpers.ValidateHelper;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.User;

public class SignupActivity extends AppCompatActivity {

    MediaPlayer mp;

    /**
     * Signup Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic02-b/book-a-donation-03/index.html#/Donation-03
     */

    // called to do initial creation of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup_button = (Button) findViewById(R.id.signupButton);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(view);
            }
        });
    }


    // details of new user created on sign up and saved in MyTweetApp Object
    // https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
    // https://stackoverflow.com/questions/12266502/android-mediaplayer-stop-and-play
    public void signup(View view) {
        TextView firstName = (TextView) findViewById(R.id.signupFirstName);
        TextView lastName = (TextView) findViewById(R.id.signupLastName);
        TextView email = (TextView) findViewById(R.id.signupEmail);
        TextView password = (TextView) findViewById(R.id.signupPassword);


        if (!ValidateHelper.isValidInput(firstName.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            firstName.setError("Please input first name");


        } else if (!ValidateHelper.isValidName(firstName.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            firstName.setError("Only use letters");


        } else if (!ValidateHelper.isValidInput(lastName.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            lastName.setError("Please input last name");


        } else if (!ValidateHelper.isValidName(lastName.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            lastName.setError("Only use letters");


        } else if (!ValidateHelper.isValidEmail(email.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            email.setError("Please enter a valid email");


        } else if (!ValidateHelper.isValidInput(email.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            email.setError("Please enter a valid email");


        } else if (!ValidateHelper.isValidInput(password.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            password.setError("Please enter password");


        } else {
            User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

            MyTweetApp app = (MyTweetApp) getApplication();
            app.newUser(user);
            startActivity(new Intent(this, TimelineActivity.class));

            mp = MediaPlayer.create(this, R.raw.valid);
            mp.start();

            ToastHelper.createToastMessage(this, "Welcome to MyTweetApp!");
        }
    }
}