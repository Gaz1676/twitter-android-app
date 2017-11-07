/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import app.tweeting.fragments.SettingsFragment;

/**
 * Settings Activity Referenced from:
 * https://wit-ictskills-2017.github.io/mobile-app-dev/topic06-b/book-a-settings/index.html#/MyRent-10 (Settings)
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}