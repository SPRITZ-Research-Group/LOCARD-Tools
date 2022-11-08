package com.appboy.ui.inappmessage;

import android.app.Activity;
import android.view.View;
import com.appboy.e.b;

public interface IInAppMessageViewWrapper {
    void close();

    b getInAppMessage();

    View getInAppMessageView();

    boolean getIsAnimatingClose();

    void open(Activity activity);
}
