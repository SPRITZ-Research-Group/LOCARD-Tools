package com.appboy.ui.inappmessage.listeners;

import android.view.View;
import com.appboy.e.b;
import com.appboy.e.d;
import com.appboy.e.n;
import com.appboy.ui.inappmessage.InAppMessageCloser;

public interface IInAppMessageViewLifecycleListener {
    void afterClosed(b bVar);

    void afterOpened(View view, b bVar);

    void beforeClosed(View view, b bVar);

    void beforeOpened(View view, b bVar);

    void onButtonClicked(InAppMessageCloser inAppMessageCloser, n nVar, d dVar);

    void onClicked(InAppMessageCloser inAppMessageCloser, View view, b bVar);

    void onDismissed(View view, b bVar);
}
