package app.tweeting.activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.tweeting.R;
import app.tweeting.main.MyTweetApp;


public class LoginActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginPressed(View view) {
        MyTweetApp app = (MyTweetApp) getApplication();

        TextView email     = (TextView)  findViewById(R.id.loginEmail);
        TextView password  = (TextView)  findViewById(R.id.loginPassword);

        if (app.validUser(email.getText().toString(), password.getText().toString())) {
            startActivity (new Intent(this, TimelineActivity.class));
            mp = MediaPlayer.create(this, R.raw.valid);
            mp.start();
        } else {
            createToastMessage("Please sign up first!").show();

            // https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);

            mp = MediaPlayer.create(this, R.raw.invalid);
            mp.start();
        }
    }

    // created a helper method for Toast response
    private Toast createToastMessage(String string) {
        return Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
    }
}
