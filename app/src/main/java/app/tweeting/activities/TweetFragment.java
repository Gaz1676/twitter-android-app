package app.tweeting.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import app.tweeting.R;
import app.tweeting.helpers.ContactHelper;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.Timeline;
import app.tweeting.models.Tweet;
import app.tweeting.settings.SettingsActivity;

import static app.tweeting.helpers.ContactHelper.sendEmail;


public class TweetFragment extends Fragment implements TextWatcher,
        OnClickListener,
        DatePickerDialog.OnDateSetListener {
    public static final String EXTRA_TWEET_ID = "mytweet.TWEET_ID";

    // id used for the implicit intent
    private static final int REQUEST_CONTACT = 1;
    private EditText message;
    private Button dateButton;
    private Button tenantButton;
    private Button reportButton;

    private Tweet tweet;
    private Timeline timeline;

    private String emailAddress = "";

    MyTweetApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Long tweetId = (Long) getArguments().getSerializable(EXTRA_TWEET_ID);

        app = MyTweetApp.getApp();
        timeline = app.timeline;
        tweet = timeline.getTweet(tweetId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_tweet, parent, false);

        addListeners(v);
        updateControls(tweet);

        return v;
    }

    private void addListeners(View v) {
        message = v.findViewById(R.id.message);
        dateButton = v.findViewById(R.id.registration_date);
        tenantButton = v.findViewById(R.id.tenant);
        reportButton = v.findViewById(R.id.tweet_reportButton);


        message.addTextChangedListener(this);
        dateButton.setOnClickListener(this);
        tenantButton.setOnClickListener(this);
        reportButton.setOnClickListener(this);

    }

    public void updateControls(Tweet tweet) {
        message.setText(tweet.message);
        dateButton.setText(tweet.getDateString());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tweetpage, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.timeline:
                startActivity(new Intent(getActivity(),TimelineActivity.class));
                return true;

            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;

            case R.id.logout:
                Intent in = new Intent(getActivity(), WelcomeActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // call to saveTweets triggered by ‘onPause’ method
    // occurs when the user leaves the fragment
    @Override
    public void onPause() {
        super.onPause();
        timeline.saveTweets();
        Long tweetId = (Long) getActivity().getIntent().getSerializableExtra(EXTRA_TWEET_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CONTACT:
                String name = ContactHelper.getContact(getActivity(), data);
                emailAddress = ContactHelper.getEmail(getActivity(), data);
                tenantButton.setText(name + " : " + emailAddress);
                tweet.tenant = name;
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable c) {
        Log.i(this.getClass().getSimpleName(), "message " + c.toString());
        tweet.message = c.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registration_date:
                Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dpd.show();
                break;
            case R.id.tenant:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, REQUEST_CONTACT);
                if (tweet.tenant != null) {
                    tenantButton.setText("Tenant: " + tweet.tenant);
                }
                break;
            case R.id.tweet_reportButton:
                sendEmail(getActivity(), "emailAddress", getString(R.string.tweet_report_subject), tweet.getTweetReport(getActivity()));
                break;

        }
    }

   /* //https://developer.android.com/training/permissions/requesting.html
    private void checkContactsReadPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //We can request the permission.
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
        } else {
            //We already have permission, so go head and read the contact
            readContact();
        }
    }

    private void readContact() {
        String name = getContact(getActivity(), data);
        emailAddress = getEmail(getActivity(), data);
        tweet.tenant = name;
        tenantButton.setText("Tenant: "+tweet.tenant);
    }


    //https://developer.android.com/training/permissions/requesting.html
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    readContact();
                }
            }
        }
    }*/

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
        tweet.date = date.getTime();
        dateButton.setText(tweet.getDateString());
    }
}