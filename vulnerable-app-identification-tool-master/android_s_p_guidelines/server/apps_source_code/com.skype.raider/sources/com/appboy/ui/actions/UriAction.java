package com.appboy.ui.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ai;
import com.appboy.b.d;
import com.appboy.f.a;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.ui.AppboyWebViewActivity;
import com.appboy.ui.support.UriUtils;
import java.util.List;
import net.hockeyapp.android.j;

public class UriAction implements IAction {
    private static final String TAG = c.a(UriAction.class);
    private final d mChannel;
    private final Bundle mExtras;
    private Uri mUri;
    private boolean mUseWebView;

    UriAction(@NonNull Uri uri, Bundle extras, boolean useWebView, @NonNull d channel) {
        this.mUri = uri;
        this.mExtras = extras;
        this.mUseWebView = useWebView;
        this.mChannel = channel;
    }

    public void execute(Context context) {
        if (a.b(this.mUri)) {
            c.b(TAG, "Not executing local Uri: " + this.mUri);
            return;
        }
        c.b(TAG, "Executing Uri action from channel " + this.mChannel + ": " + this.mUri + ". UseWebView: " + this.mUseWebView + ". Extras: " + this.mExtras);
        if (this.mUseWebView && a.a.contains(this.mUri.getScheme())) {
            if (this.mChannel.equals(d.PUSH)) {
                openUriWithWebViewActivityFromPush(context, this.mUri, this.mExtras);
            } else {
                openUriWithWebViewActivity(context, this.mUri, this.mExtras);
            }
        } else if (this.mChannel.equals(d.PUSH)) {
            openUriWithActionViewFromPush(context, this.mUri, this.mExtras);
        } else {
            openUriWithActionView(context, this.mUri, this.mExtras);
        }
    }

    @NonNull
    public Uri getUri() {
        return this.mUri;
    }

    static void openUriWithWebViewActivity(Context context, Uri uri, Bundle extras) {
        Intent intent = getWebViewActivityIntent(context, uri, extras);
        intent.setFlags(872415232);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            c.d(TAG, "Appboy AppboyWebViewActivity not opened successfully.", e);
        }
    }

    private static void openUriWithWebViewActivityFromPush(Context context, Uri uri, Bundle extras) {
        ai stackBuilder = getConfiguredTaskBackStackBuilder(context, extras);
        stackBuilder.a(getWebViewActivityIntent(context, uri, extras));
        try {
            stackBuilder.a(extras);
        } catch (Exception e) {
            c.d(TAG, "Appboy AppboyWebViewActivity not opened successfully.", e);
        }
    }

    private static void openUriWithActionView(Context context, Uri uri, Bundle extras) {
        Intent intent = getActionViewIntent(context, uri, extras);
        intent.setFlags(872415232);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            new StringBuilder("Could not find appropriate activity to open for deep link ").append(uri).append(".");
        }
    }

    private static void openUriWithActionViewFromPush(Context context, Uri uri, Bundle extras) {
        ai stackBuilder = getConfiguredTaskBackStackBuilder(context, extras);
        stackBuilder.a(getActionViewIntent(context, uri, extras));
        try {
            stackBuilder.a(extras);
        } catch (ActivityNotFoundException e) {
            new StringBuilder("Could not find appropriate activity to open for deep link ").append(uri);
        }
    }

    private static Intent getWebViewActivityIntent(Context context, Uri uri, Bundle extras) {
        Intent intent = new Intent(context, AppboyWebViewActivity.class);
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(j.FRAGMENT_URL, uri.toString());
        return intent;
    }

    private static Intent getActionViewIntent(Context context, Uri uri, Bundle extras) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        if (extras != null) {
            intent.putExtras(extras);
        }
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, 0);
        if (resolveInfos.size() > 1) {
            for (ResolveInfo resolveInfo : resolveInfos) {
                if (resolveInfo.activityInfo.packageName.equals(context.getPackageName())) {
                    new StringBuilder("Setting deep link activity to ").append(resolveInfo.activityInfo.packageName).append(".");
                    intent.setPackage(resolveInfo.activityInfo.packageName);
                    break;
                }
            }
        }
        return intent;
    }

    private static ai getConfiguredTaskBackStackBuilder(Context context, Bundle extras) {
        com.appboy.a.a configurationProvider = new com.appboy.a.a(context);
        ai stackBuilder = ai.a(context);
        if (configurationProvider.x()) {
            String pushDeepLinkBackStackActivityClassName = configurationProvider.y();
            if (i.c(pushDeepLinkBackStackActivityClassName)) {
                c.d(TAG, "Adding main activity intent to back stack while opening uri from push");
                stackBuilder.a(UriUtils.getMainActivityIntent(context, extras));
            } else if (UriUtils.isActivityRegisteredInManifest(context, pushDeepLinkBackStackActivityClassName)) {
                c.d(TAG, "Adding custom back stack activity while opening uri from push: " + pushDeepLinkBackStackActivityClassName);
                stackBuilder.a(new Intent().setClassName(context, pushDeepLinkBackStackActivityClassName));
            } else {
                c.d(TAG, "Not adding unregistered activity to the back stack while opening uri from push: " + pushDeepLinkBackStackActivityClassName);
            }
        } else {
            c.d(TAG, "Not adding back stack activity while opening uri from push due to disabled configuration setting.");
        }
        return stackBuilder;
    }
}
