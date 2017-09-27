package app.tweeting;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signupPressed(View view) {
        TextView firstName = (TextView)  findViewById(R.id.signupFirstName);
        TextView lastName  = (TextView)  findViewById(R.id.signupLastName);
        TextView email     = (TextView)  findViewById(R.id.signupEmail);
        TextView password  = (TextView)  findViewById(R.id.signupPassword);

        User user = new User (firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

        TweetApp app = (TweetApp) getApplication();
        app.newUser(user);
        startActivity(new Intent(this, Login.class));
        mp = MediaPlayer.create(this, R.raw.valid_login);
        mp.start();
    }

}