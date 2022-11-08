package com.appboy.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import com.appboy.a;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;

public class AppboyBaseFragmentActivity extends FragmentActivity {
    public void onStart() {
        super.onStart();
        a.a((Context) this).a((Activity) this);
    }

    public void onResume() {
        super.onResume();
        AppboyInAppMessageManager.getInstance().registerInAppMessageManager(this);
    }

    public void onPause() {
        super.onPause();
        AppboyInAppMessageManager.getInstance().unregisterInAppMessageManager(this);
    }

    public void onStop() {
        super.onStop();
        a.a((Context) this).b((Activity) this);
    }
}
