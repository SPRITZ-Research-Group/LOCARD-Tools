package com.skype.calendar;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri.Builder;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class CalendarModule extends ReactContextBaseJavaModule {
    public CalendarModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "Calendar";
    }

    @ReactMethod
    public void showCalendar(int date) {
        Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, Long.valueOf((long) date).longValue() * 1000);
        Intent intent = new Intent("android.intent.action.VIEW").setData(builder.build());
        intent.setFlags(ErrorDialogData.BINDER_CRASH);
        getReactApplicationContext().startActivity(intent);
    }

    @ReactMethod
    public void createEvent(int startTime, int endTime, String title, String location) {
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setData(Events.CONTENT_URI);
        intent.putExtra("title", title);
        intent.putExtra("eventLocation", location);
        intent.putExtra("beginTime", Long.valueOf((long) startTime).longValue() * 1000);
        intent.putExtra("endTime", Long.valueOf((long) endTime).longValue() * 1000);
        intent.setFlags(ErrorDialogData.BINDER_CRASH);
        getReactApplicationContext().startActivity(intent);
    }
}
