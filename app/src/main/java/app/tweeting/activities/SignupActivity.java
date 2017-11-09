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
import app.tweeting.models.User;

public class SignupActivity extends AppCompatActivity {
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
    public void signup(View view) {
        TextView firstName = (TextView) findViewById(R.id.signupFirstName);
        TextView lastName = (TextView) findViewById(R.id.signupLastName);
        TextView email = (TextView) findViewById(R.id.signupEmail);
        TextView password = (TextView) findViewById(R.id.signupPassword);


        if (!ValidateHelper.isValidInput(firstName.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            firstName.setError("Please input first name");


        } else if (!ValidateHelper.isValidName(firstName.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            firstName.setError("Only use letters");


        } else if (!ValidateHelper.isValidInput(lastName.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            lastName.setError("Please input last name");


        } else if (!ValidateHelper.isValidName(lastName.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            lastName.setError("Only use letters");


        } else if (!ValidateHelper.isValidEmail(email.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            email.setError("Please enter a valid email");


        } else if (!ValidateHelper.isValidInput(password.getText().toString())) {
            MediaPlayerHelper.invalidInput(this);
            password.setError("Please enter password");


        } else {
            User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

            MyTweetApp app = (MyTweetApp) getApplication();
            app.newUser(user);
            startActivity(new Intent(this, TimelineActivity.class));

            MediaPlayerHelper.validInput(this);
            ToastHelper.createToastMessage(this, "Welcome to MyTweetApp!");
        }
    }
}