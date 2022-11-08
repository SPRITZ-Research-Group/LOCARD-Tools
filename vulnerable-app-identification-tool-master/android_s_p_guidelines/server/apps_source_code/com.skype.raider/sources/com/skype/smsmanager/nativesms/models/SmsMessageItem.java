package com.skype.smsmanager.nativesms.models;

import android.os.Bundle;
import com.skype.smsmanager.nativesms.SmsManagerConstants;

public final class SmsMessageItem implements SmsManagerConstants {
    private final double a;
    private final long b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;

    public SmsMessageItem(Bundle bundle) {
        this.c = bundle.getString("Sender", "");
        this.d = bundle.getString("Recipient", "");
        this.a = bundle.getDouble("SmsIntentTime", 0.0d);
        this.b = bundle.getLong("TimestampMs", 0);
        this.e = bundle.getString("Body", "");
        this.f = bundle.getString("dbId", "");
    }

    public final String a() {
        return this.d;
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.e;
    }

    public final long d() {
        return this.b;
    }

    public final double e() {
        return this.a;
    }

    public final String f() {
        return this.f;
    }
}
