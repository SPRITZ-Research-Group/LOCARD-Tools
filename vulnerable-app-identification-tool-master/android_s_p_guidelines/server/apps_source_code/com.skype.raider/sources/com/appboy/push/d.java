package com.appboy.push;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.f;
import android.support.v4.app.NotificationCompat.q;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import com.appboy.a;
import com.appboy.b.b;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.ui.R;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.HashMap;
import java.util.Map;

public class d {
    private static final String a = c.a(d.class);
    private static final Integer[] b = new Integer[]{Integer.valueOf(R.id.com_appboy_story_text_view), Integer.valueOf(R.id.com_appboy_story_text_view_container), Integer.valueOf(R.id.com_appboy_story_text_view_small), Integer.valueOf(R.id.com_appboy_story_text_view_small_container), Integer.valueOf(R.id.com_appboy_story_image_view), Integer.valueOf(R.id.com_appboy_story_relative_layout)};
    private static final Map<String, Integer> c;

    static {
        Map<String, Integer> stringToGravityInt = new HashMap();
        stringToGravityInt.put("start", Integer.valueOf(8388611));
        stringToGravityInt.put("center", Integer.valueOf(17));
        stringToGravityInt.put("end", Integer.valueOf(8388613));
        c = stringToGravityInt;
    }

    @TargetApi(16)
    public static q a(Context context, Bundle notificationExtras, Bundle appboyExtras, android.support.v4.app.NotificationCompat.d notificationBuilder) {
        CharSequence a;
        q style = null;
        if (notificationExtras.containsKey("ab_c")) {
            int i;
            Object obj;
            int i2;
            c.b(a, "Rendering push notification with DecoratedCustomViewStyle (Story)");
            style = new f();
            int i3 = 0;
            while (true) {
                i = i3;
                if (a.a(i, notificationExtras, "ab_c*_i", null) != null) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    break;
                }
                i3 = i + 1;
            }
            if (notificationExtras.containsKey("appboy_story_index")) {
                i2 = notificationExtras.getInt("appboy_story_index");
            } else {
                i2 = 0;
            }
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.com_appboy_notification_story_one_image);
            String string = notificationExtras.getString("cid");
            a = a.a(i2, notificationExtras, "ab_c*_t");
            if (i.c(a)) {
                remoteViews.setInt(b[1].intValue(), "setVisibility", 8);
            } else {
                remoteViews.setTextViewText(b[0].intValue(), a);
                remoteViews.setInt(b[1].intValue(), "setGravity", ((Integer) c.get(a.a(i2, notificationExtras, "ab_c*_t_j", "center"))).intValue());
            }
            a = a.a(i2, notificationExtras, "ab_c*_st");
            if (i.c(a)) {
                remoteViews.setInt(b[3].intValue(), "setVisibility", 8);
            } else {
                remoteViews.setTextViewText(b[2].intValue(), a);
                remoteViews.setInt(b[3].intValue(), "setGravity", ((Integer) c.get(a.a(i2, notificationExtras, "ab_c*_st_j", "center"))).intValue());
            }
            Bitmap a2 = a.a(context).g().a(context, a.a(i2, notificationExtras, "ab_c*_i"), b.NOTIFICATION_ONE_IMAGE_STORY);
            if (a2 == null) {
                obj = null;
            } else {
                remoteViews.setImageViewBitmap(b[4].intValue(), a2);
                String a3 = a.a(i2, notificationExtras, "ab_c*_id", "");
                String a4 = a.a(i2, notificationExtras, "ab_c*_uri");
                String a5 = a.a(i2, notificationExtras, "ab_c*_use_webview");
                Intent intent = new Intent("com.appboy.action.APPBOY_STORY_CLICKED").setClass(context, AppboyNotificationRoutingActivity.class);
                intent.putExtra("appboy_action_uri", a4);
                intent.putExtra("appboy_action_use_webview", a5);
                intent.putExtra("appboy_story_page_id", a3);
                intent.putExtra("appboy_campaign_id", string);
                remoteViews.setOnClickPendingIntent(b[5].intValue(), PendingIntent.getActivity(context, com.appboy.f.f.a(), intent, ErrorDialogData.SUPPRESSED));
                obj = 1;
            }
            if (obj == null) {
                c.f(a, "Push story page was not populated correctly. Not using DecoratedCustomViewStyle.");
                style = null;
            } else {
                remoteViews.setOnClickPendingIntent(R.id.com_appboy_story_button_previous, a(context, notificationExtras, ((i2 - 1) + i) % i));
                remoteViews.setOnClickPendingIntent(R.id.com_appboy_story_button_next, a(context, notificationExtras, (i2 + 1) % i));
                notificationBuilder.a(remoteViews);
                notificationBuilder.a(true);
            }
        } else if (appboyExtras != null && appboyExtras.containsKey("appboy_image_url")) {
            c.b(a, "Rendering push notification with BigPictureStyle");
            style = a(context, notificationExtras, appboyExtras);
        }
        if (style != null) {
            return style;
        }
        c.b(a, "Rendering push notification with BigTextStyle");
        if (notificationExtras == null) {
            return null;
        }
        style = new NotificationCompat.c();
        style.c(notificationExtras.getString("a"));
        a = null;
        CharSequence charSequence = null;
        if (notificationExtras.containsKey("ab_bs")) {
            a = notificationExtras.getString("ab_bs");
        }
        if (notificationExtras.containsKey("ab_bt")) {
            charSequence = notificationExtras.getString("ab_bt");
        }
        if (a != null) {
            style.b(a);
        }
        if (charSequence == null) {
            return style;
        }
        style.a(charSequence);
        return style;
    }

    private static PendingIntent a(Context context, Bundle notificationExtras, int pageIndex) {
        Intent storyNextClickedIntent = new Intent("com.appboy.action.STORY_TRAVERSE").setClass(context, e.b());
        if (notificationExtras != null) {
            notificationExtras.putInt("appboy_story_index", pageIndex);
            storyNextClickedIntent.putExtras(notificationExtras);
        }
        return PendingIntent.getBroadcast(context, com.appboy.f.f.a(), storyNextClickedIntent, ErrorDialogData.SUPPRESSED);
    }

    @TargetApi(16)
    private static NotificationCompat.b a(Context context, Bundle notificationExtras, Bundle appboyExtras) {
        if (appboyExtras == null || !appboyExtras.containsKey("appboy_image_url")) {
            return null;
        }
        String imageUrl = appboyExtras.getString("appboy_image_url");
        if (i.c(imageUrl)) {
            return null;
        }
        Bitmap imageBitmap = com.appboy.f.b.a(context, Uri.parse(imageUrl), b.NOTIFICATION_EXPANDED_IMAGE);
        if (imageBitmap == null) {
            return null;
        }
        try {
            if (imageBitmap.getWidth() > imageBitmap.getHeight()) {
                DisplayMetrics displayMetrics = com.appboy.f.b.a(context);
                int bigPictureHeightPixels = com.appboy.f.b.a(displayMetrics.densityDpi, 192);
                int bigPictureWidthPixels = bigPictureHeightPixels * 2;
                if (bigPictureWidthPixels > displayMetrics.widthPixels) {
                    bigPictureWidthPixels = displayMetrics.widthPixels;
                }
                try {
                    imageBitmap = Bitmap.createScaledBitmap(imageBitmap, bigPictureWidthPixels, bigPictureHeightPixels, true);
                } catch (Exception e) {
                    c.d(a, "Failed to scale image bitmap, using original.", e);
                }
            }
            if (imageBitmap == null) {
                c.d(a, "Bitmap download failed for push notification. No image will be included with the notification.");
                return null;
            }
            CharSequence string;
            CharSequence string2;
            NotificationCompat.b bigPictureNotificationStyle = new NotificationCompat.b();
            bigPictureNotificationStyle.a(imageBitmap);
            if (notificationExtras.containsKey("ab_bs")) {
                string = notificationExtras.getString("ab_bs");
            } else {
                string = null;
            }
            if (notificationExtras.containsKey("ab_bt")) {
                string2 = notificationExtras.getString("ab_bt");
            } else {
                string2 = null;
            }
            if (string != null) {
                bigPictureNotificationStyle.b(string);
            }
            if (string2 != null) {
                bigPictureNotificationStyle.a(string2);
            }
            if (notificationExtras.getString("s") != null || string != null) {
                return bigPictureNotificationStyle;
            }
            bigPictureNotificationStyle.b(notificationExtras.getString("a"));
            return bigPictureNotificationStyle;
        } catch (Exception e2) {
            c.d(a, "Failed to create Big Picture Style.", e2);
            return null;
        }
    }
}
