/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import app.tweeting.R;
import app.tweeting.fragments.TimelineFragment;

public class TimelineActivity extends AppCompatActivity {


    /**
     * Timeline Activity Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic05-b/book-a-myrent-07%20(Fragments)/index.html#/04
     */

    // called to do initial creation of the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);


        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new TimelineFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}