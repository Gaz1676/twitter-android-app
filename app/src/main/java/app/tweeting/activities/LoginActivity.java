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


public class LoginActivity extends AppCompatActivity {

    MediaPlayer mp;
    boolean notValidated;

    /**
     * Login Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic02-b/book-a-donation-03/index.html#/Donation-03
     */

    // called to do initial creation of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login_button = (Button) findViewById(R.id.loginButton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });
    }


    // checks to see if the inputted data matches the user
    // https://stackoverflow.com/questions/12266502/android-mediaplayer-stop-and-play
    public void login(View view) {
        MyTweetApp app = (MyTweetApp) getApplication();
        TextView email = (TextView) findViewById(R.id.loginEmail);
        TextView password = (TextView) findViewById(R.id.loginPassword);


        if (!ValidateHelper.isValidInput(email.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            email.setError("Please enter a valid email");
            notValidated = true;


        } else if (!ValidateHelper.isValidEmail(email.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            email.setError("Please enter a valid email");
            notValidated = true;


        } else if (!ValidateHelper.isValidInput(password.getText().toString())) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
            password.setError("Please enter password");
            notValidated = true;


        } else if (app.validUser(email.getText().toString(), password.getText().toString())) {
            mp = MediaPlayer.create(this, R.raw.valid);
            mp.start();

            ToastHelper.createToastMessage(this, "Welcome to MyTweetApp!");

            startActivity(new Intent(this, TimelineActivity.class));
        }
    }
}
