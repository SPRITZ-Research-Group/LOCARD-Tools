package com.skype.smsmanager.nativesms.models;

import java.util.ArrayList;

public final class MmsGetRecipientsInfo {
    private final ArrayList<String> a;
    private final int b;

    public MmsGetRecipientsInfo(ArrayList<String> recipients, int iterationsToGetMmsRecipients) {
        this.a = recipients;
        this.b = iterationsToGetMmsRecipients;
    }

    public final ArrayList<String> a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }
}
