package com.appboy.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.o;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.b;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appboy.c.a;
import com.appboy.f.c;
import com.appboy.ui.adapters.AppboyListAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class AppboyFeedFragment extends o implements b {
    private static final String TAG = c.a(AppboyFeedFragment.class);
    private AppboyListAdapter mAdapter;
    private EnumSet<com.appboy.b.c> mCategories;
    @VisibleForTesting
    int mCurrentCardIndexAtBottomOfScreen = 0;
    private LinearLayout mEmptyFeedLayout;
    private RelativeLayout mFeedRootLayout;
    private SwipeRefreshLayout mFeedSwipeLayout;
    private com.appboy.c.b<a> mFeedUpdatedSubscriber;
    private android.support.v4.view.c mGestureDetector;
    private ProgressBar mLoadingSpinner;
    private final Handler mMainThreadLooper = new Handler(Looper.getMainLooper());
    private LinearLayout mNetworkErrorLayout;
    @VisibleForTesting
    int mPreviousVisibleHeadCardIndex = 0;
    private final Runnable mShowNetworkError = new Runnable() {
        public void run() {
            if (AppboyFeedFragment.this.mLoadingSpinner != null) {
                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
            }
            if (AppboyFeedFragment.this.mNetworkErrorLayout != null) {
                AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(0);
            }
        }
    };
    @VisibleForTesting
    boolean mSkipCardImpressionsReset = false;
    private boolean mSortEnabled = false;
    private View mTransparentFullBoundsContainerView;

    public class FeedGestureListener extends SimpleOnGestureListener {
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float dx, float dy) {
            AppboyFeedFragment.this.getListView().smoothScrollBy((int) dy, 0);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float velocityX, float velocityY) {
            long deltaTimeMillis = (motionEvent2.getEventTime() - motionEvent.getEventTime()) * 2;
            AppboyFeedFragment.this.getListView().smoothScrollBy(-((int) ((((float) deltaTimeMillis) * velocityY) / 1000.0f)), (int) (deltaTimeMillis * 2));
            return true;
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.mAdapter == null) {
            this.mAdapter = new AppboyListAdapter(context, R.id.tag, new ArrayList());
            this.mCategories = com.appboy.b.c.a();
        }
        this.mGestureDetector = new android.support.v4.view.c(context, new FeedGestureListener());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.com_appboy_feed, container, false);
        this.mNetworkErrorLayout = (LinearLayout) view.findViewById(R.id.com_appboy_feed_network_error);
        this.mLoadingSpinner = (ProgressBar) view.findViewById(R.id.com_appboy_feed_loading_spinner);
        this.mEmptyFeedLayout = (LinearLayout) view.findViewById(R.id.com_appboy_feed_empty_feed);
        this.mFeedRootLayout = (RelativeLayout) view.findViewById(R.id.com_appboy_feed_root);
        this.mFeedSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.appboy_feed_swipe_container);
        this.mFeedSwipeLayout.setOnRefreshListener(this);
        this.mFeedSwipeLayout.setEnabled(false);
        this.mFeedSwipeLayout.setColorSchemeResources(R.color.com_appboy_newsfeed_swipe_refresh_color_1, R.color.com_appboy_newsfeed_swipe_refresh_color_2, R.color.com_appboy_newsfeed_swipe_refresh_color_3, R.color.com_appboy_newsfeed_swipe_refresh_color_4);
        this.mTransparentFullBoundsContainerView = view.findViewById(R.id.com_appboy_feed_transparent_full_bounds_container_view);
        return view;
    }

    @SuppressLint({"InflateParams"})
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadFragmentStateFromSavedInstanceState(savedInstanceState);
        if (this.mSkipCardImpressionsReset) {
            this.mSkipCardImpressionsReset = false;
        } else {
            this.mAdapter.resetCardImpressionTracker();
            c.b(TAG, "Resetting card impressions.");
        }
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final ListView listView = getListView();
        listView.addHeaderView(inflater.inflate(R.layout.com_appboy_feed_header, null));
        listView.addFooterView(inflater.inflate(R.layout.com_appboy_feed_footer, null));
        this.mFeedRootLayout.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return AppboyFeedFragment.this.mGestureDetector.a(motionEvent);
            }
        });
        listView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            }

            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                AppboyFeedFragment.this.mFeedSwipeLayout.setEnabled(firstVisibleItem == 0);
                if (visibleItemCount != 0) {
                    int currentVisibleHeadCardIndex = firstVisibleItem - 1;
                    if (currentVisibleHeadCardIndex > AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex) {
                        AppboyFeedFragment.this.mAdapter.batchSetCardsToRead(AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex, currentVisibleHeadCardIndex);
                    }
                    AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex = currentVisibleHeadCardIndex;
                    AppboyFeedFragment.this.mCurrentCardIndexAtBottomOfScreen = firstVisibleItem + visibleItemCount;
                }
            }
        });
        this.mTransparentFullBoundsContainerView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return view.getVisibility() == 0;
            }
        });
        com.appboy.a.a(getContext()).a(this.mFeedUpdatedSubscriber, a.class);
        this.mFeedUpdatedSubscriber = new com.appboy.c.b<a>() {
            public void trigger(final a event) {
                Activity activity = AppboyFeedFragment.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            c.a(AppboyFeedFragment.TAG, "Updating feed views in response to FeedUpdatedEvent: " + event);
                            AppboyFeedFragment.this.mMainThreadLooper.removeCallbacks(AppboyFeedFragment.this.mShowNetworkError);
                            AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(8);
                            if (event.b(AppboyFeedFragment.this.mCategories) == 0) {
                                listView.setVisibility(8);
                                AppboyFeedFragment.this.mAdapter.clear();
                            } else {
                                AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(8);
                            }
                            if (event.a() && (event.b() + 60) * 1000 < System.currentTimeMillis()) {
                                c.d(AppboyFeedFragment.TAG, "Feed received was older than the max time to live of 60 seconds, displaying it for now, but requesting an updated view from the server.");
                                com.appboy.a.a(AppboyFeedFragment.this.getContext()).d();
                                if (event.b(AppboyFeedFragment.this.mCategories) == 0) {
                                    c.b(AppboyFeedFragment.TAG, "Old feed was empty, putting up a network spinner and registering the network error message on a delay of 5000ms.");
                                    AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                    AppboyFeedFragment.this.mLoadingSpinner.setVisibility(0);
                                    AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                                    AppboyFeedFragment.this.mMainThreadLooper.postDelayed(AppboyFeedFragment.this.mShowNetworkError, 5000);
                                    return;
                                }
                            }
                            if (event.b(AppboyFeedFragment.this.mCategories) == 0) {
                                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(0);
                                AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                            } else {
                                if (!AppboyFeedFragment.this.mSortEnabled || event.b(AppboyFeedFragment.this.mCategories) == event.c(AppboyFeedFragment.this.mCategories)) {
                                    AppboyFeedFragment.this.mAdapter.replaceFeed(event.a(AppboyFeedFragment.this.mCategories));
                                } else {
                                    AppboyFeedFragment.this.mAdapter.replaceFeed(AppboyFeedFragment.this.sortFeedCards(event.a(AppboyFeedFragment.this.mCategories)));
                                }
                                listView.setVisibility(0);
                            }
                            AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
                        }
                    });
                }
            }
        };
        com.appboy.a.a(getContext()).b(this.mFeedUpdatedSubscriber);
        listView.setAdapter(this.mAdapter);
        com.appboy.a.a(getContext()).c();
    }

    public List<com.appboy.e.a.c> sortFeedCards(List<com.appboy.e.a.c> cards) {
        Collections.sort(cards, new Comparator<com.appboy.e.a.c>() {
            public int compare(com.appboy.e.a.c cardOne, com.appboy.e.a.c cardTwo) {
                if (cardOne.o() == cardTwo.o()) {
                    return 0;
                }
                return cardOne.o() ? 1 : -1;
            }
        });
        return cards;
    }

    public void onResume() {
        super.onResume();
        com.appboy.a.a(getActivity()).a();
    }

    public void onDestroyView() {
        super.onDestroyView();
        com.appboy.a.a(getContext()).a(this.mFeedUpdatedSubscriber, a.class);
        setOnScreenCardsToRead();
    }

    public void onPause() {
        super.onPause();
        setOnScreenCardsToRead();
    }

    private void setOnScreenCardsToRead() {
        this.mAdapter.batchSetCardsToRead(this.mPreviousVisibleHeadCardIndex, this.mCurrentCardIndexAtBottomOfScreen);
    }

    public void onDetach() {
        super.onDetach();
        setListAdapter(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("PREVIOUS_VISIBLE_HEAD_CARD_INDEX", this.mPreviousVisibleHeadCardIndex);
        outState.putInt("CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN", this.mCurrentCardIndexAtBottomOfScreen);
        outState.putBoolean("SKIP_CARD_IMPRESSIONS_RESET", this.mSkipCardImpressionsReset);
        if (this.mCategories == null) {
            this.mCategories = com.appboy.b.c.a();
        }
        ArrayList<String> cardCategoryArrayList = new ArrayList(this.mCategories.size());
        Iterator it = this.mCategories.iterator();
        while (it.hasNext()) {
            cardCategoryArrayList.add(((com.appboy.b.c) it.next()).name());
        }
        outState.putStringArrayList("CARD_CATEGORY", cardCategoryArrayList);
        super.onSaveInstanceState(outState);
        if (isVisible()) {
            this.mSkipCardImpressionsReset = true;
        }
    }

    @VisibleForTesting
    void loadFragmentStateFromSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (this.mCategories == null) {
                this.mCategories = com.appboy.b.c.a();
            }
            this.mPreviousVisibleHeadCardIndex = savedInstanceState.getInt("PREVIOUS_VISIBLE_HEAD_CARD_INDEX", 0);
            this.mCurrentCardIndexAtBottomOfScreen = savedInstanceState.getInt("CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN", 0);
            this.mSkipCardImpressionsReset = savedInstanceState.getBoolean("SKIP_CARD_IMPRESSIONS_RESET", false);
            ArrayList<String> cardCategoryArrayList = savedInstanceState.getStringArrayList("CARD_CATEGORY");
            if (cardCategoryArrayList != null) {
                this.mCategories.clear();
                Iterator it = cardCategoryArrayList.iterator();
                while (it.hasNext()) {
                    this.mCategories.add(com.appboy.b.c.valueOf((String) it.next()));
                }
            }
        }
    }

    public void onRefresh() {
        com.appboy.a.a(getContext()).d();
        this.mMainThreadLooper.postDelayed(new Runnable() {
            public void run() {
                AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
            }
        }, 2500);
    }
}
