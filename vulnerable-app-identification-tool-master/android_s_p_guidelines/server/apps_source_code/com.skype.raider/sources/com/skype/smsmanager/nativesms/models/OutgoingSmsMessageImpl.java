package com.skype.smsmanager.nativesms.models;

public final class OutgoingSmsMessageImpl {
    private final String a;
    private final String b;
    private final String c;

    public OutgoingSmsMessageImpl(String recipient, String body, String cuid) {
        this.b = recipient;
        this.c = body;
        this.a = cuid;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.a;
    }

    public final String c() {
        return this.c;
    }
}
