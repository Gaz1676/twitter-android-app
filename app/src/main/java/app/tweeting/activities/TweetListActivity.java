package app.tweeting.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.UUID;

import app.tweeting.R;
import app.tweeting.main.TweetApp;
import app.tweeting.settings.SettingsActivity;

public class TweetListActivity extends AppCompatActivity {

    private TweetApp app;
    MediaPlayer mp;
    public UUID id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tweet);
        app = (TweetApp) getApplication();
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    // Inflating the sub_menu menu will add its menu items to the empty SubMenu created
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tweet, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // intent starts new activity
            case R.id.timeline:
                Toast.makeText(this, "Timeline in production", Toast.LENGTH_SHORT).show();
                mp = MediaPlayer.create(this, R.raw.invalid);
                mp.start();
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                mp = MediaPlayer.create(this, R.raw.valid);
                mp.start();
                return true;
            case R.id.logout:
                startActivity(new Intent(this, WelcomeActivity.class));
                break;
        }
        return true;
    }

    public void tweetButtonPressed(View view) {
        TweetApp app = (TweetApp) getApplication();

        Toast toast = Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT);
        toast.show();
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }
}
