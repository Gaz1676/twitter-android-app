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
import android.widget.Toast;

import app.tweeting.R;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.User;

public class SignupActivity extends AppCompatActivity {

    MediaPlayer mp;


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


        // validation check to see if email matches regex pattern
        if (isValidEmail(email.getText().toString().trim())) {
            User user = new User(firstName.getText().toString(), lastName.getText().toString(),
                    email.getText().toString(), password.getText().toString());


            MyTweetApp app = (MyTweetApp) getApplication();
            app.newUser(user);
            startActivity(new Intent(this, TimelineActivity.class));

            createToastMessage("Welcome to MyTweetApp!").show();

            mp = MediaPlayer.create(this, R.raw.valid);
            mp.start();

        } else {
            // https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);

            createToastMessage("Please enter valid email").show();

            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
        }
    }



    // helper method for Toast response
    private Toast createToastMessage(String string) {
        return Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
    }

    // helper method to check email validation pattern
    // https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    public boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]" +
                "+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z" +
                "\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}