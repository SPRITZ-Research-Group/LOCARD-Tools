package com.appboy.ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.e.b;

public interface IInAppMessageWebViewClientListener {
    void onCloseAction(b bVar, String str, Bundle bundle);

    void onCustomEventAction(b bVar, String str, Bundle bundle);

    void onNewsfeedAction(b bVar, String str, Bundle bundle);

    void onOtherUrlAction(b bVar, String str, Bundle bundle);
}
