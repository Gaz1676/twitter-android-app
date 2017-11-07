/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.tweeting.R;

import static app.tweeting.helpers.LogHelpers.info;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mp;


    /**
     * Welcome Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic02-b/book-a-donation-03/index.html#/04
     */

    // called to do initial creation of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button login_button = (Button) findViewById(R.id.loginButton);
        final Button signup_button = (Button) findViewById(R.id.signupButton);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);

        mp = MediaPlayer.create(this, R.raw.welcome_chirp);
        mp.start();
    }


    // onClick method activated when a button from page is clicked
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                info(this, "Login Pressed");
                startActivity(new Intent(this, LoginActivity.class));
                mp = MediaPlayer.create(this, R.raw.valid);
                mp.start();
                break;


            case R.id.signupButton:
                info(this, "Signup Pressed");
                startActivity(new Intent(this, SignupActivity.class));
                mp = MediaPlayer.create(this, R.raw.valid);
                mp.start();
                break;
        }
    }
}
