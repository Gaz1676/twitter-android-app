package app.tweeting;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
            case R.id.menuSettings:
                Toast.makeText(this, "Settings not completed", Toast.LENGTH_SHORT).show();
                mp = MediaPlayer.create(this, R.raw.invalid_login);
                mp.start();
                break;
            case R.id.menuLogout:
                startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        // Inflating the sub_menu menu will add its menu items to the empty SubMenu created
        inflater.inflate(R.menu.menu_tweet, menu);
        return super.onCreateOptionsMenu(menu);
    }

   /* public void tweetButtonPressed(View view) {
        mp.start();
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.payBuddy ? "PayBuddy" : "Direct";

        int donatedAmount = amountPicker.getValue();
        if (donatedAmount == 0) {
            String text = amountText.getText().toString();
            if (!text.equals(""))
                donatedAmount = Integer.parseInt(text);
        }

        if (donatedAmount > 0) {
            app.newDonation(new Donation(donatedAmount, method));
            progressBar.setProgress(app.totalDonated);
            String totalDonatedStr = "Donuts: " + app.totalDonated;
            amountTotal.setText(totalDonatedStr);

            // resets values to 0 and original text box state
            amountPicker.setValue(0);
            amountText.setText("");
        }
    }*/
}
