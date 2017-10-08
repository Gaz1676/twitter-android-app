package app.tweeting.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.tweeting.R;

public class WelcomeActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mp = MediaPlayer.create(this, R.raw.welcome_chirp);
        mp.start();
    }

    public void loginPressed(View view) {
        startActivity (new Intent(this, LoginActivity.class));
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }

    public void signupPressed(View view) {
        startActivity (new Intent(this, SignupActivity.class));
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }
}
