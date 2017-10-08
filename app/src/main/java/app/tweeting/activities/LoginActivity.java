package app.tweeting.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.tweeting.R;
import app.tweeting.main.TweetApp;

public class LoginActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginPressed(View view) {
        TweetApp app = (TweetApp) getApplication();

        TextView email     = (TextView)  findViewById(R.id.loginEmail);
        TextView password  = (TextView)  findViewById(R.id.loginPassword);

        if (app.validUser(email.getText().toString(), password.getText().toString())) {
            startActivity (new Intent(this, TweetListActivity.class));
            mp = MediaPlayer.create(this, R.raw.valid);
            mp.start();
        } else {
            Toast toast = Toast.makeText(this, "Please sign up first!", Toast.LENGTH_SHORT);
            toast.show();
            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
        }
    }

}
