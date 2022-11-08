package com.appboy.ui.support;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.f.c;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UriUtils {
    private static final String TAG = c.a(UriUtils.class);

    public static Map<String, String> getQueryParameters(Uri uri) {
        if (uri.isOpaque()) {
            c.b(TAG, "URI is not hierarchical. There are no query parameters to parse.");
            return Collections.emptyMap();
        }
        String query = uri.getEncodedQuery();
        if (query == null) {
            return Collections.emptyMap();
        }
        Map<String, String> parameters = new HashMap();
        int start = 0;
        do {
            int end;
            int next = query.indexOf(38, start);
            if (next == -1) {
                end = query.length();
            } else {
                end = next;
            }
            int separator = query.indexOf(61, start);
            if (separator > end || separator == -1) {
                separator = end;
            }
            if (end > start) {
                parameters.put(Uri.decode(query.substring(start, separator)), Uri.decode(query.substring(separator + 1, end)));
            }
            start = end + 1;
        } while (start < query.length());
        return Collections.unmodifiableMap(parameters);
    }

    public static Intent getMainActivityIntent(Context context, Bundle extras) {
        Intent startActivityIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        startActivityIntent.setFlags(872415232);
        if (extras != null) {
            startActivityIntent.putExtras(extras);
        }
        return startActivityIntent;
    }

    public static boolean isActivityRegisteredInManifest(Context context, String className) {
        try {
            if (context.getPackageManager().getActivityInfo(new ComponentName(context, className), 0) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            c.c(TAG, "Could not find activity info for class with name: " + className, e);
            return false;
        }
    }
}
