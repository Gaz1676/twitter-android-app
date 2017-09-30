package app.tweeting.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.tweeting.R;

public class Welcome extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mp = MediaPlayer.create(this, R.raw.welcome_chirp);
        mp.start();
    }

    public void loginPressed(View view) {
        startActivity (new Intent(this, Login.class));
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }

    public void signupPressed(View view) {
        startActivity (new Intent(this, Signup.class));
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }
}
