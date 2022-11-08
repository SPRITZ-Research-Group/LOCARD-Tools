package com.appboy.ui.inappmessage;

public class InAppMessageCloser {
    private InAppMessageViewWrapper mInAppMessageViewWrapper;

    public InAppMessageCloser(InAppMessageViewWrapper inAppMessageViewWrapper) {
        this.mInAppMessageViewWrapper = inAppMessageViewWrapper;
    }

    public void close(boolean animate) {
        if (animate) {
            this.mInAppMessageViewWrapper.getInAppMessage().a(true);
        } else {
            this.mInAppMessageViewWrapper.getInAppMessage().a(false);
        }
        this.mInAppMessageViewWrapper.close();
    }
}
