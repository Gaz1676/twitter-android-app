package app.tweeting.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import app.tweeting.R;

import static android.R.attr.key;
import static app.tweeting.helpers.IntentHelper.navigateUp;
import static app.tweeting.helpers.LogHelpers.info;

// implemented the SharedPreferences.OnSharedPreferenceChangeListener interface
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    // added a SharedPreference field
    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        // enabled up button in onCreate()
        setHasOptionsMenu(true);
    }

    // override onStart and initialize the SharePreference field
    // registered the listener in onStart()
    @Override
    public void onStart() {
        super.onStart();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    // unregistered the listeners in onStop()
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

    // added the menu handler for the up button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateUp(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
