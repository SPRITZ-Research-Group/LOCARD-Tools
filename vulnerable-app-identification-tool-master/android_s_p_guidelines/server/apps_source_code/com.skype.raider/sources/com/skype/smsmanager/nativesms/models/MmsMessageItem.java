package com.skype.smsmanager.nativesms.models;

import android.os.Bundle;
import com.skype.smsmanager.nativesms.SmsManagerConstants;
import java.util.ArrayList;
import java.util.List;

public final class MmsMessageItem implements SmsManagerConstants {
    final double a;
    private final long b;
    private final String c;
    private final String d;
    private final ArrayList<String> e;
    private final ArrayList<MmsMediaItem> f;
    private final int g;
    private final String h;

    public MmsMessageItem(Bundle bundle) {
        this.b = bundle.getLong("TimestampMs", 0);
        this.c = bundle.getString("Sender", "");
        this.d = bundle.getString("Body", "");
        this.e = bundle.getStringArrayList("Recipients");
        this.f = bundle.getParcelableArrayList("Media");
        this.a = bundle.getDouble("SmsIntentTime", 0.0d);
        this.g = bundle.getInt("GetMmsRecipientsIterations", 0);
        this.h = bundle.getString("dbId", "");
    }

    public final long a() {
        return this.b;
    }

    public final String b() {
        return this.d;
    }

    public final String c() {
        return this.c;
    }

    public final ArrayList<String> d() {
        return this.e;
    }

    public final double e() {
        return this.a;
    }

    public final int f() {
        return this.g;
    }

    public final List<MmsMediaItem> g() {
        return this.f;
    }

    public final String h() {
        return this.h;
    }
}
