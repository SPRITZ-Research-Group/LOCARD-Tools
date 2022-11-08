package com.appboy.ui.support;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.appboy.f.c;

public class ViewUtils {
    private static final String TAG = c.a(ViewUtils.class);
    private static int sDisplayHeight;

    public static void removeViewFromParent(View view) {
        if (view != null && (view.getParent() instanceof ViewGroup)) {
            ViewGroup parent = (ViewGroup) view.getParent();
            setFocusableInTouchModeAndRequestFocus(parent);
            parent.removeView(view);
        }
    }

    public static void setFocusableInTouchModeAndRequestFocus(View view) {
        try {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } catch (Exception e) {
            c.d(TAG, "Caught exception while setting view to focusable in touch mode and requesting focus.", e);
        }
    }

    public static int getTopVisibleCoordinate(View view) {
        Rect rectangle = new Rect();
        view.getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }

    public static int getDisplayHeight(Activity activity) {
        if (sDisplayHeight > 0) {
            return sDisplayHeight;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int i = point.y;
        sDisplayHeight = i;
        return i;
    }

    public static double convertDpToPixels(Activity activity, double valueInDp) {
        return valueInDp * ((double) activity.getResources().getDisplayMetrics().density);
    }

    public static boolean isRunningOnTablet(Activity activity) {
        return activity.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }

    @TargetApi(16)
    public static void removeOnGlobalLayoutListenerSafe(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (VERSION.SDK_INT < 16) {
            viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void setActivityRequestedOrientation(@NonNull Activity activity, int requestedOrientation) {
        try {
            activity.setRequestedOrientation(requestedOrientation);
        } catch (Exception e) {
            c.d(TAG, "Failed to set requested orientation " + requestedOrientation + " for activity class: " + activity.getLocalClassName(), e);
        }
    }
}
