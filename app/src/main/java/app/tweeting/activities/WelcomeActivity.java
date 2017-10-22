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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final Button signupButton = (Button) findViewById(R.id.signupButton);

        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

        mp = MediaPlayer.create(this, R.raw.welcome_chirp);
        mp.start();
    }


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
