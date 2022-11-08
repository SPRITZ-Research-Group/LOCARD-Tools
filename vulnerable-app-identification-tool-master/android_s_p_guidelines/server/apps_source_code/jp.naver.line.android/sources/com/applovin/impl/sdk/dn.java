package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class dn {
    private static dn d;
    private final Map<an, String> a = new HashMap(1);
    private final Map<an, Map<String, String>> b = new HashMap(1);
    private final Object c = new Object();

    private dn() {
    }

    public static synchronized dn a() {
        dn dnVar;
        synchronized (dn.class) {
            if (d == null) {
                d = new dn();
            }
            dnVar = d;
        }
        return dnVar;
    }

    public Map<String, String> a(an anVar) {
        Map<String, String> map;
        synchronized (this.c) {
            map = (Map) this.b.remove(anVar);
        }
        return map;
    }

    public void a(an anVar, String str) {
        synchronized (this.c) {
            this.a.put(anVar, str);
        }
    }

    public void a(an anVar, Map<String, String> map) {
        synchronized (this.c) {
            this.b.put(anVar, map);
        }
    }

    public String b(an anVar) {
        String str;
        synchronized (this.c) {
            str = (String) this.a.remove(anVar);
        }
        return str;
    }
}
