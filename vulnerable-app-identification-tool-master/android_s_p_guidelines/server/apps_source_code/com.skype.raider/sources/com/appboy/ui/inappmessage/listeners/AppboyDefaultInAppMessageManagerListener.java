package com.appboy.ui.inappmessage.listeners;

import com.appboy.e.b;
import com.appboy.e.n;
import com.appboy.ui.inappmessage.InAppMessageCloser;
import com.appboy.ui.inappmessage.InAppMessageOperation;

public class AppboyDefaultInAppMessageManagerListener implements IInAppMessageManagerListener {
    @Deprecated
    public boolean onInAppMessageReceived(b inAppMessage) {
        return false;
    }

    public InAppMessageOperation beforeInAppMessageDisplayed(b inAppMessage) {
        return InAppMessageOperation.DISPLAY_NOW;
    }

    public boolean onInAppMessageClicked(b inAppMessage, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public boolean onInAppMessageButtonClicked(n button, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public void onInAppMessageDismissed(b inAppMessage) {
    }
}
