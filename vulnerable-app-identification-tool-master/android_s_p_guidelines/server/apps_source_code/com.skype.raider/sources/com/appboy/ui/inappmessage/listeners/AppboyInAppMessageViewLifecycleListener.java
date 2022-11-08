package com.appboy.ui.inappmessage.listeners;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import com.appboy.b.a.a;
import com.appboy.e.b;
import com.appboy.e.d;
import com.appboy.e.n;
import com.appboy.f.c;
import com.appboy.f.k;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.NewsfeedAction;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.ui.inappmessage.InAppMessageCloser;

public class AppboyInAppMessageViewLifecycleListener implements IInAppMessageViewLifecycleListener {
    private static final String TAG = c.a(AppboyInAppMessageViewLifecycleListener.class);

    public void beforeOpened(View inAppMessageView, b inAppMessage) {
        c.b(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeOpened called.");
        inAppMessage.A();
    }

    public void afterOpened(View inAppMessageView, b inAppMessage) {
        c.b(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterOpened called.");
    }

    public void beforeClosed(View inAppMessageView, b inAppMessage) {
        c.b(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeClosed called.");
    }

    public void afterClosed(b inAppMessage) {
        c.b(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterClosed called.");
        getInAppMessageManager().resetAfterInAppMessageClose();
        if (inAppMessage instanceof com.appboy.e.c) {
            startClearHtmlInAppMessageAssetsThread();
        }
        inAppMessage.C();
    }

    public void onClicked(InAppMessageCloser inAppMessageCloser, View inAppMessageView, b inAppMessage) {
        c.b(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onClicked called.");
        inAppMessage.B();
        if (!getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageClicked(inAppMessage, inAppMessageCloser)) {
            performInAppMessageClicked(inAppMessage, inAppMessageCloser);
        }
    }

    public void onButtonClicked(InAppMessageCloser inAppMessageCloser, n messageButton, d inAppMessageImmersive) {
        c.b(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onButtonClicked called.");
        inAppMessageImmersive.a(messageButton);
        if (!getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageButtonClicked(messageButton, inAppMessageCloser)) {
            performInAppMessageButtonClicked(messageButton, inAppMessageImmersive, inAppMessageCloser);
        }
    }

    public void onDismissed(View inAppMessageView, b inAppMessage) {
        c.b(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onDismissed called.");
        getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageDismissed(inAppMessage);
    }

    private void performInAppMessageButtonClicked(n messageButton, b inAppMessage, InAppMessageCloser inAppMessageCloser) {
        performClickAction(messageButton.b(), inAppMessage, inAppMessageCloser, messageButton.c(), messageButton.g());
    }

    private void performInAppMessageClicked(b inAppMessage, InAppMessageCloser inAppMessageCloser) {
        performClickAction(inAppMessage.n(), inAppMessage, inAppMessageCloser, inAppMessage.o(), inAppMessage.w());
    }

    private void performClickAction(a clickAction, b inAppMessage, InAppMessageCloser inAppMessageCloser, Uri clickUri, boolean openUriInWebview) {
        if (getInAppMessageManager().getActivity() == null) {
            c.f(TAG, "Can't perform click action because the cached activity is null.");
            return;
        }
        switch (clickAction) {
            case NEWS_FEED:
                inAppMessageCloser.close(false);
                AppboyNavigator.getAppboyNavigator().gotoNewsFeed(getInAppMessageManager().getActivity(), new NewsfeedAction(com.appboy.f.d.a(inAppMessage.b()), com.appboy.b.d.INAPP_MESSAGE));
                return;
            case URI:
                inAppMessageCloser.close(false);
                AppboyNavigator.getAppboyNavigator().gotoUri(getInAppMessageManager().getActivity(), ActionFactory.createUriActionFromUri(clickUri, com.appboy.f.d.a(inAppMessage.b()), openUriInWebview, com.appboy.b.d.INAPP_MESSAGE));
                return;
            case NONE:
                inAppMessageCloser.close(inAppMessage.m());
                return;
            default:
                inAppMessageCloser.close(false);
                return;
        }
    }

    private AppboyInAppMessageManager getInAppMessageManager() {
        return AppboyInAppMessageManager.getInstance();
    }

    private void startClearHtmlInAppMessageAssetsThread() {
        new Thread(new Runnable() {
            public void run() {
                Activity inAppMessageActivity = AppboyInAppMessageManager.getInstance().getActivity();
                if (inAppMessageActivity != null) {
                    com.appboy.f.a.a(k.a(inAppMessageActivity));
                }
            }
        }).start();
    }
}
