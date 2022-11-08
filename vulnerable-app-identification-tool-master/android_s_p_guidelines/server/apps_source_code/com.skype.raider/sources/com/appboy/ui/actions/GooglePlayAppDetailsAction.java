package com.appboy.ui.actions;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.appboy.b.a;
import com.appboy.b.d;
import com.appboy.f.c;

public final class GooglePlayAppDetailsAction implements IAction {
    private static final String TAG = c.a(GooglePlayAppDetailsAction.class);
    private final a mAppStore;
    private final d mChannel;
    private final String mKindleId;
    private final String mPackageName;
    private boolean mUseWebView;

    public GooglePlayAppDetailsAction(String packageName, boolean useAppboyWebView, a appStore, String kindleId, d channel) {
        this.mPackageName = packageName;
        this.mUseWebView = useAppboyWebView;
        this.mAppStore = appStore;
        this.mKindleId = kindleId;
        this.mChannel = channel;
    }

    public final void execute(Context context) {
        if (this.mAppStore != a.KINDLE_STORE) {
            try {
                context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
            } catch (NameNotFoundException e) {
                c.d(TAG, "Google Play Store not found, launching Play Store with WebView");
                this.mUseWebView = true;
            } catch (Exception e2) {
                c.g(TAG, "Unexpected exception while checking for com.google.android.gsf.");
                this.mUseWebView = true;
            }
        }
        String uriString;
        if (this.mUseWebView) {
            if (this.mAppStore == a.KINDLE_STORE) {
                uriString = "http://www.amazon.com/gp/mas/dl/android?asin=" + this.mKindleId;
            } else {
                uriString = "https://play.google.com/store/apps/details?id=" + this.mPackageName;
            }
            UriAction.openUriWithWebViewActivity(context, Uri.parse(uriString), null);
            return;
        }
        if (this.mAppStore == a.KINDLE_STORE) {
            uriString = "amzn://apps/android?asin=" + this.mKindleId;
        } else {
            uriString = "market://details?id=" + this.mPackageName;
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uriString)));
    }
}
