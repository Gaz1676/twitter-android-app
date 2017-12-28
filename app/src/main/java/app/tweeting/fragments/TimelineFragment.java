/**
 * Author: Gary Fleming
 * Student No: 20019497
 * Start Date: Sept 24th 2017
 */

package app.tweeting.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.tweeting.R;
import app.tweeting.activities.SettingsActivity;
import app.tweeting.activities.TweetActivity;
import app.tweeting.activities.WelcomeActivity;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.Timeline;
import app.tweeting.models.Tweet;

import static app.tweeting.helpers.IntentHelper.startActivityWithData;
import static app.tweeting.helpers.MediaPlayerHelper.removeTweetSound;
import static app.tweeting.helpers.MediaPlayerHelper.validInput;
import static app.tweeting.helpers.ToastHelper.createToastMessage;
import static app.tweeting.helpers.ToastHelper.dialogBox;


/**
 * Timeline Fragment Referenced from:
 * https://wit-ictskills-2017.github.io/mobile-app-dev/topic05-b/book-a-myrent-07%20(Fragments)/index.html
 */

public class TimelineFragment extends ListFragment implements OnItemClickListener, AbsListView.MultiChoiceModeListener {

    private ArrayList<Tweet> tweets;
    private Timeline timeline;
    private TweetAdapter adapter;
    private ListView listView; // multi choice mode field
    MyTweetApp app;


    // called to do initial creation of the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

        app = MyTweetApp.getApp();
        timeline = app.timeline;
        tweets = timeline.tweets;

        adapter = new TweetAdapter(getActivity(), tweets);
        setListAdapter(adapter);
    }


    // creates and returns the view hierarchy associated with the fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        listView = v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        return v;
    }


    // called whenever a item gets clicked in the list fragment
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Tweet tweet = ((TweetAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), TweetActivity.class);
        i.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
        startActivityForResult(i, 0);
    }


    // method for the interface
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet tweet = adapter.getItem(position);
        startActivityWithData(getActivity(), TweetActivity.class,"TWEET_ID", tweet.id);
    }


    // connects new menu to timeline Activity
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.timeline, menu);
    }


    // new instance created after selection from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_tweet:
                Tweet tweet = new Tweet(app.currentUser.id);

                timeline.addTweet(tweet);
                validInput(getActivity());

                Intent i = new Intent(getActivity(), TweetActivity.class);
                i.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
                startActivityForResult(i, 0);
                return true;


            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                validInput(getActivity());
                return true;


            case R.id.clear:
                for (int x = tweets.size() - 1; x >= 0; x--) {
                    timeline.deleteTweet(tweets.get(x));
                    adapter.notifyDataSetChanged();
                }
                validInput(getActivity());

                return true;


            case R.id.logout:
                Intent in = new Intent(getActivity(), WelcomeActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // makes sure changes made in timeline Activity are shown in the list
    @Override
    public void onResume() {
        super.onResume();
        ((TweetAdapter) getListAdapter()).notifyDataSetChanged();
    }

    /**
     * MultiChoiceListener method Referenced from:
     * https://wit-ictskills-2017.github.io/mobile-app-dev/topic06-a/book-a-myrent-08%20(Deletion)/index.html#/03
     */

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.timeline_context, menu);
        return true;
    }


    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }


    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        // Action bar needs to be declared final to be accessed in inner dialog box class
        final ActionMode action = actionMode;
        if (menuItem.getItemId() == R.id.menu_item_delete_tweet) {

            // Dialog box to confirm delete tweets
            dialogBox(getActivity(),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Close action bar
                            action.finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            deleteTweet(action);
                            removeTweetSound(getActivity());
                            createToastMessage(getActivity(), "Tweet(s) deleted!");
                        }
                    });
            return true;
        } else {
            return false;
        }
    }


    private void deleteTweet(ActionMode actionMode) {
        for (int i = adapter.getCount() - 1; i >= 0; i--) {
            if (listView.isItemChecked(i)) {
                timeline.deleteTweet(adapter.getItem(i));
            }
        }
        actionMode.finish();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
    }


    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
    }
  /* ************ MultiChoiceModeListener methods (end) *********** */


    // the timeline adapter updates the list with the tweet objects contained in the timeline
    class TweetAdapter extends ArrayAdapter<Tweet> {
        private Context context;
        List<Tweet> tweets;

        public TweetAdapter(Context context, ArrayList<Tweet> tweets) {
            super(context, 0, tweets);
            this.context = context;
            this.tweets = tweets;
        }


        // gets a View that displays the data at the specified position in the data set
        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_tweet, null);
            }
            Tweet tweet = getItem(position);

            TextView message = convertView.findViewById(R.id.timeline_item_tweet);
            assert tweet != null;
            message.setText(tweet.getMessage());

            TextView dateView = convertView.findViewById(R.id.timeline_item_dateTextView);
            dateView.setText(tweet.getDateString());
            return convertView;
        }
    }
}
