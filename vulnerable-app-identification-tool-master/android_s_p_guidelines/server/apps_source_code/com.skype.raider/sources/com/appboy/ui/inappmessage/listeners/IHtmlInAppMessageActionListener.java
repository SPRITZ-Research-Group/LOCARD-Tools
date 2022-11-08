package com.appboy.ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.e.b;

public interface IHtmlInAppMessageActionListener {
    void onCloseClicked(b bVar, String str, Bundle bundle);

    boolean onCustomEventFired(b bVar, String str, Bundle bundle);

    boolean onNewsfeedClicked(b bVar, String str, Bundle bundle);

    boolean onOtherUrlAction(b bVar, String str, Bundle bundle);
}
