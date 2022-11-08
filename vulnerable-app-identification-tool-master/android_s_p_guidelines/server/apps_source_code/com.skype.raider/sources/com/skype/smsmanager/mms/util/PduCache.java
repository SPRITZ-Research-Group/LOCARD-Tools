package com.skype.smsmanager.mms.util;

import android.content.UriMatcher;
import android.net.Uri;
import java.util.HashMap;
import java.util.HashSet;

public final class PduCache extends AbstractCache<Uri, PduCacheEntry> {
    private static final UriMatcher a;
    private static final HashMap<Integer, Integer> b;
    private static PduCache c;
    private final HashMap<Integer, HashSet<Uri>> d = new HashMap();
    private final HashMap<Long, HashSet<Uri>> e = new HashMap();
    private final HashSet<Uri> f = new HashSet();

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        a = uriMatcher;
        uriMatcher.addURI("mms", null, 0);
        a.addURI("mms", "#", 1);
        a.addURI("mms", "inbox", 2);
        a.addURI("mms", "inbox/#", 3);
        a.addURI("mms", "sent", 4);
        a.addURI("mms", "sent/#", 5);
        a.addURI("mms", "drafts", 6);
        a.addURI("mms", "drafts/#", 7);
        a.addURI("mms", "outbox", 8);
        a.addURI("mms", "outbox/#", 9);
        a.addURI("mms-sms", "conversations", 10);
        a.addURI("mms-sms", "conversations/#", 11);
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(Integer.valueOf(2), Integer.valueOf(1));
        b.put(Integer.valueOf(4), Integer.valueOf(2));
        b.put(Integer.valueOf(6), Integer.valueOf(3));
        b.put(Integer.valueOf(8), Integer.valueOf(4));
    }

    private PduCache() {
    }

    public static final synchronized PduCache a() {
        PduCache pduCache;
        synchronized (PduCache.class) {
            if (c == null) {
                c = new PduCache();
            }
            pduCache = c;
        }
        return pduCache;
    }
}
