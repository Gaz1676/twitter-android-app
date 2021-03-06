/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.GregorianCalendar;

import app.tweeting.R;
import app.tweeting.activities.TimelineActivity;
import app.tweeting.helpers.IntentHelper;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.Timeline;
import app.tweeting.models.Tweet;

import static app.tweeting.R.id;
import static app.tweeting.R.layout;
import static app.tweeting.R.string;
import static app.tweeting.helpers.ContactHelper.getContact;
import static app.tweeting.helpers.ContactHelper.getEmail;
import static app.tweeting.helpers.ContactHelper.sendEmail;
import static app.tweeting.helpers.MediaPlayerHelper.invalidInput;
import static app.tweeting.helpers.MediaPlayerHelper.validInput;
import static app.tweeting.helpers.ToastHelper.createToastMessage;

/**
 * Tweet Fragment Referenced from:
 * https://wit-ictskills-2017.github.io/mobile-app-dev/topic05-b/book-a-myrent-07%20(Fragments)/index.html
 */

public class TweetFragment extends Fragment implements TextWatcher, OnClickListener,
        DatePickerDialog.OnDateSetListener {


    public static final String EXTRA_TWEET_ID = "mytweet.TWEET_ID";
    private static final int REQUEST_CONTACT = 1;

    private EditText message;
    private TextView date;

    private Button tweetButton;
    private Button contactButton;
    private Button emailButton;

    // used to provide us with access to data intent outside onActivityResult
    private Intent data;

    private Tweet tweet;
    private Timeline timeline;

    private String emailAddress = "";
    MyTweetApp app;


    public TweetFragment() {

    }

    // called to do initial creation of the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //Recover the ID passed to us via the intent in TimelineActivity
        Long tweetId = (Long) getActivity().getIntent().getSerializableExtra(EXTRA_TWEET_ID);

        app = MyTweetApp.getApp();
        timeline = app.timeline;
        tweet = timeline.getTweet(tweetId);
    }


    // creates and returns the view hierarchy associated with the fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(layout.fragment_tweet, parent, false);
        addListeners(v);
        updateControls(tweet);
        return v;
    }


    // binds and sets listener to the widgets
    private void addListeners(View v) {
        message = v.findViewById(id.message);
        tweetButton = v.findViewById(id.tweetButton);
        contactButton = v.findViewById(id.contactButton);
        emailButton = v.findViewById(id.emailButton);
        date = v.findViewById(id.tweetDate);


        message.addTextChangedListener(this);
        tweetButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
        date.setOnClickListener(this);

        updateControls(tweet);
    }


    public void updateControls(Tweet tweet) {
        message.setText(tweet.getMessage());
        date.setText(tweet.getDateString());
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


    // new instance created after selection from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // if the message is empty it is deleted before resuming
    // otherwise it is saved to timeline
    @Override
    public void onPause() {
        super.onPause();
        if (message.getText().length() > 0) {
            timeline.saveTweets();
            validInput(getActivity());
        } else {
            timeline.deleteTweet(tweet);
        }
    }


    // onClick method activated when a button from menu is clicked
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tweetButton:
                if (message.getText().length() > 0) {
                    tweet.message = message.getText().toString();
                    IntentHelper.startActivity(getActivity(), TimelineActivity.class);
                    timeline.saveTweets();
                    break;

                } else {
                    createToastMessage(getActivity(), "You forgot to enter your message!!");
                    invalidInput(getActivity());
                    break;
                }


            case R.id.contactButton:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, REQUEST_CONTACT);
                validInput(getActivity());
                if (tweet.contact != null) {
                    contactButton.setText("Contact: " + tweet.contact);
                }
                break;


            case R.id.emailButton:
                sendEmail(getActivity(), emailAddress, getString(string.email_subject), tweet.getMessage());
                validInput(getActivity());
                break;
        }
    }


    // deals with selectContent method activating 'startActivityForResult'
    // reading of contact details sent out to readContact()
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CONTACT:
                this.data = data;
                checkContactsReadPermission();
                break;
        }
    }


    // reads the contact from the phone
    private void readContact() {
        String name = getContact(getActivity(), data);
        emailAddress = getEmail(getActivity(), data);
        tweet.contact = emailAddress;
        contactButton.setText("Contact : " + emailAddress);
    }


    // checks if app has permission to read phones contact details.
    // if permission true - the readContact() method will be called
    // if false - a pop-up dialog box with a toast will ask for permission
    // there are two options - allow or deny access
    // https://developer.android.com/training/permissions/requesting.html
    private void checkContactsReadPermission() {

        // current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // permission can be requested here
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
        } else {

            // else if already granted permission
            readContact();
        }
    }


    /**
     * https://developer.android.com/training/permissions/requesting.html
     */
    // when there is a respond to the dialog box (allow or deny)
    // the onRequestPermissionsResult() method is called passing in the response
    // the readContact method is called when granted otherwise it is ignored.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
    }


    // sets the date to a certain style for the tweets after they are created
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
        tweet.date = date.getTime();
        tweetButton.setText(tweet.getDateString());
    }
}