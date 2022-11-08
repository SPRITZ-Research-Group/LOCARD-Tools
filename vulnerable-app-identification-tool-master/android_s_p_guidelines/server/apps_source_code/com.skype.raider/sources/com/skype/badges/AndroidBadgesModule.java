package com.skype.badges;

import android.app.Application;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ap;

public class AndroidBadgesModule extends ReactContextBaseJavaModule {
    private static final String BADGE_COUNT_KEY = (AndroidBadgesModule.class.getPackage().getName() + ".badgeCount");
    private static final String MODULE_NAME = "AndroidBadges";
    public static final String TAG = "AndroidBadgesModule";
    private int mCurrentBadgeCount = -1;
    private final ag reactContext;

    public AndroidBadgesModule(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getCount(ae promise) {
        if (this.mCurrentBadgeCount == -1) {
            this.mCurrentBadgeCount = PreferenceManager.getDefaultSharedPreferences(this.reactContext.getApplicationContext()).getInt(BADGE_COUNT_KEY, -1);
        }
        FLog.i(TAG, "getCount: " + this.mCurrentBadgeCount);
        promise.a(Integer.valueOf(this.mCurrentBadgeCount));
    }

    @ReactMethod
    public void setCount(final int newCount) {
        ap.a(new Runnable(this) {
            final /* synthetic */ AndroidBadgesModule b;

            public final void run() {
                FLog.i(AndroidBadgesModule.TAG, "setCount: " + newCount);
                Application application = (Application) this.b.getReactApplicationContext().getApplicationContext();
                BadgeNotificationFactory.a(application).a(newCount);
                Editor editor = PreferenceManager.getDefaultSharedPreferences(application).edit();
                editor.putInt(AndroidBadgesModule.BADGE_COUNT_KEY, newCount);
                editor.apply();
            }
        });
        this.mCurrentBadgeCount = newCount;
    }
}
