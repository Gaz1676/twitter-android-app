package app.tweeting.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    }

    public void signupPressed(View view) {
        TextView firstName = (TextView)  findViewById(R.id.signupFirstName);
        TextView lastName  = (TextView)  findViewById(R.id.signupLastName);
        TextView email     = (TextView)  findViewById(R.id.signupEmail);
        TextView password  = (TextView)  findViewById(R.id.signupPassword);

        User user = new User (firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

        MyTweetApp app = (MyTweetApp) getApplication();
        app.newUser(user);
        startActivity(new Intent(this, TimelineActivity.class));
        createToastMessage("Welcome to MyTweetApp!").show();
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }

    // created a helper method for Toast response
    private Toast createToastMessage(String string) {
        return Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
    }
}