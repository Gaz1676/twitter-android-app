package app.tweeting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.tweeting.R;

public class SplashActivity extends Activity {

    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 1500;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();

                if (!mIsBackButtonPressed) {
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class); // start the home screen if the back button wasn't pressed already

                    SplashActivity.this.startActivity(intent);
                }
            }
        }, SPLASH_DURATION);

    }

    @Override
    public void onBackPressed() {
        mIsBackButtonPressed = true;  // set the flag to true so the next activity won't start up
        super.onBackPressed();
    }
}