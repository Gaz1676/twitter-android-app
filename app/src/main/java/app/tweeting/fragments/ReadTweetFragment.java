package app.tweeting.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.tweeting.R;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.Timeline;
import app.tweeting.models.Tweet;
import app.tweeting.models.User;

import static app.tweeting.helpers.ContactHelper.getContact;
import static app.tweeting.helpers.ContactHelper.getEmail;
import static app.tweeting.helpers.ContactHelper.sendEmail;
import static app.tweeting.helpers.IntentHelper.navigateUp;
import static app.tweeting.helpers.MediaPlayerHelper.validInput;

/**
 * Read Tweet Fragment Referenced from:
 * https://wit-ictskills-2017.github.io/mobile-app-dev/topic05-b/book-a-myrent-07%20(Fragments)/index.html
 *
 * this class is used for when a user clicks on any given tweet and is shown:
 * .. if it is users tweet they can edit it otherwise
 * .... the tweet is a Read Only tweet and can not be edited only viewed
 */

public class ReadTweetFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_CONTACT = 1;
    public static final String EXTRA_TWEET_ID = "mytweet.TWEET_ID";

    private TextView message;
    private TextView date;
    private TextView tweeter;

    private Button contactButton;
    private Button emailButton;

    private Intent data;

    private Tweet tweet;
    private Timeline timeline;

    private String emailAddress = "";
    MyTweetApp app;


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
        View v = inflater.inflate(R.layout.fragment_read_tweet, parent, false);
        addListeners(v);
        updateControls(tweet);
        return v;
    }

    // binds and sets listener to the widgets
    private void addListeners(View v) {
        message = v.findViewById(R.id.message);
        date = v.findViewById(R.id.tweetDate);
        tweeter = v.findViewById(R.id.tweeter);
        contactButton = v.findViewById(R.id.contactButton);
        emailButton = v.findViewById(R.id.emailButton);

        emailButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);

        updateControls(tweet);
    }

    @SuppressLint("SetTextI18n")
    public void updateControls(Tweet tweet) {
        User user = app.userStore.getUser(tweet.getUserId());
        message.setText(tweet.getMessage());
        date.setText(tweet.getDateString());
        tweeter.setText("Tweeted by: " + user.firstName + " " + user.lastName);
    }

    // connects new menu to timeline Activity
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.readtweet, menu);
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


    // onClick method activated when a button from menu is clicked
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contactButton:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, REQUEST_CONTACT);
                validInput(getActivity());
                if (tweet.contact != null) {
                    contactButton.setText("Contact: " + tweet.contact);
                }
                break;


            case R.id.emailButton:
                sendEmail(getActivity(), emailAddress, getString(R.string.email_subject), tweet.getMessage());
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

    // reads the contact from the phone
    @SuppressLint("SetTextI18n")
    public void readContact() {
        String name = getContact(getActivity(), data);
        emailAddress = getEmail(getActivity(), data);
        contactButton.setText("Contact: " + emailAddress);
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
}

