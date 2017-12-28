/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import app.tweeting.R;
import app.tweeting.main.MyTweetApp;

import static android.R.attr.key;
import static app.tweeting.helpers.IntentHelper.navigateUp;
import static app.tweeting.helpers.LogHelpers.info;
import static app.tweeting.helpers.MediaPlayerHelper.validInput;

/**
 * Settings Fragment Referenced from:
 * https://wit-ictskills-2017.github.io/mobile-app-dev/topic06-b/book-a-settings/index.html#/MyRent-10 (Settings)
 */

// implemented the SharedPreferences.OnSharedPreferenceChangeListener interface
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    // added a SharedPreference field
    private SharedPreferences prefs;
    MyTweetApp app = MyTweetApp.getApp();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // enabled up button in onCreate()
        setHasOptionsMenu(true);
    }


    /**
     * Changes to Settings Fragment BG Color Referenced from:
     * https://stackoverflow.com/questions/16970209/preferencefragment-background-color
     */

    // changes the background color for the preference screen settings page
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        return view;
    }


    // override onStart and initialize the SharePreference field
    // register the listener in onStart()
    @Override
    public void onStart() {
        super.onStart();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }


    // unregister the listener in onStop()
    @Override
    public void onStop() {
        super.onStop();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }


    // implement the interface's method
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        info(getActivity(), "Setting change - key : value = " + key + " : " + sharedPreferences.getString(String.valueOf(key), ""));
    }


    // added the menu handler code for the up button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateUp(getActivity());
                validInput(getActivity());

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
