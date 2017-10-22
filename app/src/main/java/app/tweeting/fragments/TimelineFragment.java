package app.tweeting.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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

import app.tweeting.R;
import app.tweeting.activities.TweetPagerActivity;
import app.tweeting.activities.WelcomeActivity;
import app.tweeting.helpers.IntentHelper;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.Timeline;
import app.tweeting.models.Tweet;
import app.tweeting.settings.SettingsActivity;

public class TimelineFragment extends ListFragment implements OnItemClickListener, AbsListView.MultiChoiceModeListener {
    private ArrayList<Tweet> tweets;
    private Timeline timeline;
    private TweetAdapter adapter;
    private ListView listView;

    MyTweetApp app;


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        listView = v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        return v;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Tweet tweet = ((TweetAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), TweetPagerActivity.class);
        i.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
        startActivityForResult(i, 0);
    }


    @Override
    public void onResume() {
        super.onResume();
        ((TweetAdapter) getListAdapter()).notifyDataSetChanged();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.timeline, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_tweet:
                Tweet tweet = new Tweet();
                timeline.addTweet(tweet);

                Intent i = new Intent(getActivity(), TweetPagerActivity.class);
                i.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
                startActivityForResult(i, 0);
                return true;

            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet tweet = adapter.getItem(position);
        IntentHelper.startActivityWithData(getActivity(), TweetPagerActivity.class, "TWEET_ID", tweet.id);
    }


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
        switch (menuItem.getItemId()) {
            case R.id.menu_item_delete_tweet:
                deleteTweet(actionMode);
                return true;
            default:
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


    class TweetAdapter extends ArrayAdapter<Tweet> {
        private Context context;

        public TweetAdapter(Context context, ArrayList<Tweet> tweets) {
            super(context, 0, tweets);
            this.context = context;
        }


        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_tweet, null);
            }
            Tweet tweet = getItem(position);

            TextView messageView = convertView.findViewById(R.id.timeline_item_tweet);
            assert tweet != null;
            messageView.setText(tweet.message);

            TextView dateView = convertView.findViewById(R.id.timeline_item_dateTextView);
            dateView.setText(tweet.getDateString());
            return convertView;
        }
    }
}