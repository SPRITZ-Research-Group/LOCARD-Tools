package com.appboy.ui.inappmessage.listeners;

import com.appboy.e.b;
import com.appboy.e.n;
import com.appboy.ui.inappmessage.InAppMessageCloser;
import com.appboy.ui.inappmessage.InAppMessageOperation;

public interface IInAppMessageManagerListener {
    InAppMessageOperation beforeInAppMessageDisplayed(b bVar);

    boolean onInAppMessageButtonClicked(n nVar, InAppMessageCloser inAppMessageCloser);

    boolean onInAppMessageClicked(b bVar, InAppMessageCloser inAppMessageCloser);

    void onInAppMessageDismissed(b bVar);

    @Deprecated
    boolean onInAppMessageReceived(b bVar);
}
