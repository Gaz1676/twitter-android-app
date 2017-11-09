/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Created: Nov 9th 2017
 */

package app.tweeting.helpers;

import android.content.Context;
import android.media.MediaPlayer;

import app.tweeting.R;

/**
 * MediaPlayerHelper is used across multiple classes
 * MediaPlayer feature plays audio when something is activated
 * There is audio for welcome startup, valid and invalid selections
 * The Helper is used to reduce code (DRY) where necessary
 */

// https://stackoverflow.com/questions/12266502/android-mediaplayer-stop-and-play

public class MediaPlayerHelper {
    private static MediaPlayer mp;


    // sound for welcome page start up
    public static void welcome(Context context) {
        mp = MediaPlayer.create(context, R.raw.welcome_chirp);
        mp.start();
    }


    // sound for valid selection or input
    public static void validInput(Context context) {
        mp = MediaPlayer.create(context, R.raw.valid);
        mp.start();
    }


    // sound for invalid selection or input
    public static void invalidInput(Context context) {
        mp = MediaPlayer.create(context, R.raw.invalid);
        mp.start();
    }


    // sound for delete tweet method
    public static void deleteTweet(Context context) {
        mp = MediaPlayer.create(context, R.raw.swish);
        mp.start();
    }
}


