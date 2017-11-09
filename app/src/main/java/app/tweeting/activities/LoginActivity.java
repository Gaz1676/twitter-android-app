/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.tweeting.R;
import app.tweeting.helpers.MediaPlayerHelper;
import app.tweeting.helpers.ToastHelper;
import app.tweeting.helpers.ValidateHelper;
import app.tweeting.main.MyTweetApp;


public class LoginActivity extends AppCompatActivity {

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
    public void login(View view) {
        MyTweetApp app = (MyTweetApp) getApplication();
        TextView email = (TextView) findViewById(R.id.loginEmail);
        TextView password = (TextView) findViewById(R.id.loginPassword);


        if (!ValidateHelper.isValidInput(email.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            email.setError("Please enter a valid email");
            notValidated = true;


        } else if (!ValidateHelper.isValidEmail(email.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            email.setError("Please enter a valid email");
            notValidated = true;


        } else if (!ValidateHelper.isValidInput(password.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            password.setError("Please enter password");
            notValidated = true;


        } else if (app.validUser(email.getText().toString(), password.getText().toString())) {
            MediaPlayerHelper.validInput(this);
            ToastHelper.createToastMessage(this, "Welcome to MyTweetApp!");
            startActivity(new Intent(this, TimelineActivity.class));
        }
    }
}
