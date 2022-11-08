package com.appboy.ui;

import android.content.Context;
import com.appboy.f.c;
import com.appboy.h;
import com.appboy.ui.actions.NewsfeedAction;
import com.appboy.ui.actions.UriAction;

public class AppboyNavigator implements h {
    private static final String TAG = c.a(AppboyNavigator.class);
    private static volatile h sCustomAppboyNavigator;
    private static volatile h sDefaultAppboyNavigator = new AppboyNavigator();

    public void gotoNewsFeed(Context context, NewsfeedAction newsfeedAction) {
        executeNewsFeedAction(context, newsfeedAction);
    }

    public void gotoUri(Context context, UriAction uriAction) {
        executeUriAction(context, uriAction);
    }

    public static void executeNewsFeedAction(Context context, NewsfeedAction newsfeedAction) {
        if (newsfeedAction == null) {
            c.g(TAG, "IAppboyNavigator cannot open News feed because the news feed action object was null.");
        } else {
            newsfeedAction.execute(context);
        }
    }

    public static void executeUriAction(Context context, UriAction uriAction) {
        if (uriAction == null) {
            c.g(TAG, "IAppboyNavigator cannot open Uri because the Uri action object was null.");
        } else {
            uriAction.execute(context);
        }
    }

    public static h getAppboyNavigator() {
        if (sCustomAppboyNavigator != null) {
            return sCustomAppboyNavigator;
        }
        return sDefaultAppboyNavigator;
    }
}
