package com.appboy.ui.inappmessage.listeners;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import com.appboy.a;
import com.appboy.e.b;
import com.appboy.f.c;
import com.appboy.f.d;
import com.appboy.f.i;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.NewsfeedAction;
import com.appboy.ui.actions.UriAction;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;

public class AppboyInAppMessageWebViewClientListener implements IInAppMessageWebViewClientListener {
    private static final String TAG = c.a(AppboyInAppMessageWebViewClientListener.class);

    public void onCloseAction(b inAppMessage, String url, Bundle queryBundle) {
        c.b(TAG, "IInAppMessageWebViewClientListener.onCloseAction called.");
        logHtmlInAppMessageClick(inAppMessage, queryBundle);
        getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(true);
        getInAppMessageManager().getHtmlInAppMessageActionListener().onCloseClicked(inAppMessage, url, queryBundle);
    }

    public void onNewsfeedAction(b inAppMessage, String url, Bundle queryBundle) {
        c.b(TAG, "IInAppMessageWebViewClientListener.onNewsfeedAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            c.f(TAG, "Can't perform news feed action because the cached activity is null.");
            return;
        }
        logHtmlInAppMessageClick(inAppMessage, queryBundle);
        if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onNewsfeedClicked(inAppMessage, url, queryBundle)) {
            inAppMessage.a(false);
            getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(false);
            AppboyNavigator.getAppboyNavigator().gotoNewsFeed(getInAppMessageManager().getActivity(), new NewsfeedAction(d.a(inAppMessage.b()), com.appboy.b.d.INAPP_MESSAGE));
        }
    }

    public void onCustomEventAction(b inAppMessage, String url, Bundle queryBundle) {
        c.b(TAG, "IInAppMessageWebViewClientListener.onCustomEventAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            c.f(TAG, "Can't perform custom event action because the activity is null.");
        } else if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onCustomEventFired(inAppMessage, url, queryBundle)) {
            String customEventName = parseCustomEventNameFromQueryBundle(queryBundle);
            if (!i.c(customEventName)) {
                a.a(getInAppMessageManager().getActivity()).a(customEventName, parsePropertiesFromQueryBundle(queryBundle));
            }
        }
    }

    public void onOtherUrlAction(b inAppMessage, String url, Bundle queryBundle) {
        c.b(TAG, "IInAppMessageWebViewClientListener.onOtherUrlAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            c.f(TAG, "Can't perform other url action because the cached activity is null.");
            return;
        }
        logHtmlInAppMessageClick(inAppMessage, queryBundle);
        if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onOtherUrlAction(inAppMessage, url, queryBundle)) {
            boolean useWebViewForWebLinks = parseUseWebViewFromQueryBundle(inAppMessage, queryBundle);
            Bundle inAppMessageBundle = d.a(inAppMessage.b());
            inAppMessageBundle.putAll(queryBundle);
            UriAction uriAction = ActionFactory.createUriActionFromUrlString(url, inAppMessageBundle, useWebViewForWebLinks, com.appboy.b.d.INAPP_MESSAGE);
            Uri uri = uriAction.getUri();
            if (uri == null || !com.appboy.f.a.b(uri)) {
                inAppMessage.a(false);
                getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(false);
                if (uriAction != null) {
                    AppboyNavigator.getAppboyNavigator().gotoUri(getInAppMessageManager().getApplicationContext(), uriAction);
                    return;
                }
                return;
            }
            c.f(TAG, "Not passing local URI to AppboyNavigator. Got local uri: " + uri);
        }
    }

    @VisibleForTesting
    static boolean parseUseWebViewFromQueryBundle(b inAppMessage, Bundle queryBundle) {
        boolean anyQueryFlagSet = false;
        boolean deepLinkFlag = false;
        if (queryBundle.containsKey("abDeepLink")) {
            deepLinkFlag = Boolean.parseBoolean(queryBundle.getString("abDeepLink"));
            anyQueryFlagSet = true;
        }
        boolean externalOpenFlag = false;
        if (queryBundle.containsKey("abExternalOpen")) {
            externalOpenFlag = Boolean.parseBoolean(queryBundle.getString("abExternalOpen"));
            anyQueryFlagSet = true;
        }
        boolean useWebViewForWebLinks = inAppMessage.w();
        if (anyQueryFlagSet) {
            return (deepLinkFlag || externalOpenFlag) ? false : true;
        } else {
            return useWebViewForWebLinks;
        }
    }

    private AppboyInAppMessageManager getInAppMessageManager() {
        return AppboyInAppMessageManager.getInstance();
    }

    private void logHtmlInAppMessageClick(b inAppMessage, Bundle queryBundle) {
        if (queryBundle == null || !queryBundle.containsKey("abButtonId")) {
            inAppMessage.B();
        } else {
            ((com.appboy.e.c) inAppMessage).b(queryBundle.getString("abButtonId"));
        }
    }

    static String parseCustomEventNameFromQueryBundle(Bundle queryBundle) {
        return queryBundle.getString("name");
    }

    static com.appboy.e.b.a parsePropertiesFromQueryBundle(Bundle queryBundle) {
        com.appboy.e.b.a customEventProperties = new com.appboy.e.b.a();
        for (String key : queryBundle.keySet()) {
            if (!key.equals("name")) {
                String propertyValue = queryBundle.getString(key, null);
                if (!i.c(propertyValue)) {
                    customEventProperties.a(key, propertyValue);
                }
            }
        }
        return customEventProperties;
    }
}
