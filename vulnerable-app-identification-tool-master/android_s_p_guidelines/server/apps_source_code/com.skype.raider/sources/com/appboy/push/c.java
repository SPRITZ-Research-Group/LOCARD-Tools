package com.appboy.push;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.RemoteViews;
import com.appboy.f.g;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class c {
    private static final String a = com.appboy.f.c.a(c.class);

    public static RemoteViews a(Context context, Bundle notificationExtras, int smallIconResourceId, boolean showSmallIcon) {
        if (notificationExtras != null) {
            int layoutResourceId;
            String title = notificationExtras.getString("t");
            String contentText = notificationExtras.getString("a");
            Resources resources = context.getResources();
            String resourcePackageName = g.a(context);
            if (showSmallIcon) {
                layoutResourceId = resources.getIdentifier("com_appboy_notification", "layout", resourcePackageName);
            } else {
                layoutResourceId = resources.getIdentifier("com_appboy_notification_no_icon", "layout", resourcePackageName);
            }
            int titleResourceId = resources.getIdentifier("com_appboy_notification_title", "id", resourcePackageName);
            int contentResourceId = resources.getIdentifier("com_appboy_notification_content", "id", resourcePackageName);
            int iconResourceId = resources.getIdentifier("com_appboy_notification_icon", "id", resourcePackageName);
            int timeViewResourceId = resources.getIdentifier("com_appboy_notification_time", "id", resourcePackageName);
            int twentyFourHourFormatResourceId = resources.getIdentifier("com_appboy_notification_time_twenty_four_hour_format", "string", resourcePackageName);
            int twelveHourFormatResourceId = resources.getIdentifier("com_appboy_notification_time_twelve_hour_format", "string", resourcePackageName);
            String twentyFourHourTimeFormat = e.a(resources, twentyFourHourFormatResourceId, "HH:mm");
            String twelveHourTimeFormat = e.a(resources, twelveHourFormatResourceId, "h:mm a");
            if (layoutResourceId == 0 || titleResourceId == 0 || contentResourceId == 0 || iconResourceId == 0 || timeViewResourceId == 0) {
                com.appboy.f.c.f(a, "Couldn't find all resource IDs for custom notification view, extended view will not be used for push notifications. Received " + layoutResourceId + " for layout, " + titleResourceId + " for title, " + contentResourceId + " for content, " + iconResourceId + " for icon, and " + timeViewResourceId + " for time.");
            } else {
                RemoteViews remoteViews;
                com.appboy.f.c.b(a, "Using RemoteViews for rendering of push notification.");
                try {
                    remoteViews = new RemoteViews(g.a(context), layoutResourceId);
                } catch (Exception e) {
                    com.appboy.f.c.d(a, "Failed to initialize remote views with package " + g.a(context), e);
                    try {
                        remoteViews = new RemoteViews(context.getPackageName(), layoutResourceId);
                    } catch (Exception e2) {
                        com.appboy.f.c.d(a, "Failed to initialize remote views with package " + context.getPackageName(), e2);
                        return null;
                    }
                }
                remoteViews.setTextViewText(titleResourceId, title);
                remoteViews.setTextViewText(contentResourceId, contentText);
                if (showSmallIcon) {
                    remoteViews.setImageViewResource(iconResourceId, smallIconResourceId);
                }
                if (!DateFormat.is24HourFormat(context)) {
                    twentyFourHourTimeFormat = twelveHourTimeFormat;
                }
                remoteViews.setTextViewText(timeViewResourceId, new SimpleDateFormat(twentyFourHourTimeFormat, Locale.getDefault()).format(new Date()));
                return remoteViews;
            }
        }
        return null;
    }
}
