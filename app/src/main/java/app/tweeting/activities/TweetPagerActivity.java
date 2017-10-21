package app.tweeting.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import app.tweeting.R;
import app.tweeting.main.MyTweetApp;
import app.tweeting.models.Timeline;
import app.tweeting.models.Tweet;

import static app.tweeting.helpers.LogHelpers.info;

public class TweetPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private ArrayList<Tweet> tweets;
    private Timeline timeline;
    private PagerAdapter pagerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        setTimeline();
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tweets);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
        setCurrentItem();


    }


    // Ensure selected residence is shown in details view

    private void setCurrentItem() {
        Long resId = (Long) getIntent().getSerializableExtra(TweetFragment.EXTRA_TWEET_ID);
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).id.equals(resId)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    private void setTimeline() {
        MyTweetApp app = (MyTweetApp) getApplication();
        timeline = app.timeline;
        tweets = timeline.tweets;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        info(this, "onPageScrolled: position " + position + " arg1 " + positionOffset + " positionOffsetPixels " + positionOffsetPixels);
        Tweet tweet = tweets.get(position);
        if (tweet.message != null) {
            setTitle(tweet.message);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Tweet> tweets;

        public PagerAdapter(FragmentManager fm, ArrayList<Tweet> tweets) {
            super(fm);
            this.tweets = tweets;
        }

        @Override
        public int getCount() {
            return tweets.size();
        }

        @Override
        public Fragment getItem(int pos) {
            Tweet tweet = tweets.get(pos);
            Bundle args = new Bundle();
            args.putSerializable(TweetFragment.EXTRA_TWEET_ID, tweet.id);
            TweetFragment fragment = new TweetFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }
}