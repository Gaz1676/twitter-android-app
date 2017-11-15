/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.tweeting.R;

public class SplashActivity extends Activity {

    /**
     * Splash Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic08-a/book-coffeemate-lab-04/index.html#/02
     */

    // setting back button pressed to a boolean
    // duration of splash set to 1500ms
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 1500;


    // called to do initial creation of the fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Handler handler = new Handler();

        // run a thread after 1.5 seconds to start the home screen
        handler.postDelayed(new Runnable() {

            // make sure splash screen closes so the user
            // won't come back when it presses back key
            @Override
            public void run() {
                finish();

                // start the home screen if the back button wasn't pressed already
                if (!mIsBackButtonPressed) {
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    SplashActivity.this.startActivity(intent);
                }
            }
        }, SPLASH_DURATION);
    }


    // set the flag to true so the next activity won't start up
    @Override
    public void onBackPressed() {
        mIsBackButtonPressed = true;
        super.onBackPressed();
    }
}