package com.appboy.ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.e.b;

public class AppboyDefaultHtmlInAppMessageActionListener implements IHtmlInAppMessageActionListener {
    public void onCloseClicked(b inAppMessage, String url, Bundle queryBundle) {
    }

    public boolean onNewsfeedClicked(b inAppMessage, String url, Bundle queryBundle) {
        return false;
    }

    public boolean onCustomEventFired(b inAppMessage, String url, Bundle queryBundle) {
        return false;
    }

    public boolean onOtherUrlAction(b inAppMessage, String url, Bundle queryBundle) {
        return false;
    }
}
