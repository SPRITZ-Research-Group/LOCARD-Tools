package com.skype.slimcore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RNObjectHandleHelper {
    private static RNObjectHandleHelper a = null;
    private Map<String, Object> b = new HashMap();

    private RNObjectHandleHelper() {
    }

    public static synchronized RNObjectHandleHelper a() {
        RNObjectHandleHelper rNObjectHandleHelper;
        synchronized (RNObjectHandleHelper.class) {
            if (a == null) {
                a = new RNObjectHandleHelper();
            }
            rNObjectHandleHelper = a;
        }
        return rNObjectHandleHelper;
    }

    public final synchronized Object a(String key) {
        return this.b.get(key);
    }

    public final synchronized void b(String key) {
        this.b.remove(key);
    }

    public final synchronized List<String> b() {
        List<String> list;
        list = new ArrayList(this.b.size());
        for (String key : this.b.keySet()) {
            list.add(key);
        }
        return list;
    }

    public final synchronized String a(Object obj) {
        String key;
        key = UUID.randomUUID().toString();
        this.b.put(key, obj);
        return key;
    }
}
