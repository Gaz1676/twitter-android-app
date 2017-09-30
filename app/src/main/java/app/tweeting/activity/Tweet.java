package app.tweeting.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import app.tweeting.R;
import app.tweeting.main.TweetApp;

public class Tweet extends AppCompatActivity {

    private TweetApp app;
    MediaPlayer mp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        app = (TweetApp) getApplication();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // intent starts new activity
            case R.id.account:
                Toast.makeText(this, "Account not completed", Toast.LENGTH_SHORT).show();
                mp = MediaPlayer.create(this, R.raw.invalid);
                mp.start();
                break;
            case R.id.settings:
                Toast.makeText(this, "Settings not completed", Toast.LENGTH_SHORT).show();
                mp = MediaPlayer.create(this, R.raw.invalid);
                mp.start();
                break;
            case R.id.logout:
                startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflating the sub_menu menu will add its menu items to the empty SubMenu created
        inflater.inflate(R.menu.menu_tweet, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void tweetButtonPressed(View view) {
        TweetApp app = (TweetApp) getApplication();

        Toast toast = Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT);
        toast.show();
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }

    public void clearedButtonPressed(View view) {
        TweetApp app = (TweetApp) getApplication();

        Toast toast = Toast.makeText(this, "Message Cleared", Toast.LENGTH_SHORT);
        toast.show();
        mp = MediaPlayer.create(this, R.raw.valid);
        mp.start();
    }
}
