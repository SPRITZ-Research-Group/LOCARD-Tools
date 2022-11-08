package com.skype.smsmanager.nativesms.models;

public final class OutgoingMessageStatusImpl {
    private final String a;
    private final String b;
    private final boolean c;
    private final boolean d;

    public OutgoingMessageStatusImpl(String cuid, String cellularMessageId, boolean successful, boolean isMms) {
        this.a = cuid;
        this.b = cellularMessageId;
        this.c = successful;
        this.d = isMms;
    }

    public final boolean a() {
        return this.d;
    }

    public final boolean b() {
        return this.c;
    }

    public final String c() {
        return this.a;
    }

    public final String d() {
        return this.b;
    }
}
