package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Intent;

public interface a {
    void onActivityResult(Activity activity, int i, int i2, Intent intent);

    void onNewIntent(Intent intent);
}
