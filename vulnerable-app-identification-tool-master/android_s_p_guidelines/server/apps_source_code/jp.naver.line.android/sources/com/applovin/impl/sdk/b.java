package com.applovin.impl.sdk;

import android.text.TextUtils;
import java.util.Collection;
import java.util.HashSet;

class b {
    static final b a = a("srt");
    static final b b = a("sft");
    static final b c = a("sfs");
    static final b d = a("sadb");
    static final b e = a("sacb");
    static final b f = a("stdl");
    static final b g = a("stdi");
    static final b h = a("snas");
    static final b i = a("snat");
    static final b j = a("stah");
    static final b k = a("stas");
    static final b l = a("stac");
    static final b m = a("stbe");
    static final b n = a("stbc");
    static final b o = a("saan");
    static final b p = a("suvs");
    static final b q = a("svpv");
    static final b r = a("stpd");
    private static final Collection<String> t = new HashSet(18);
    private final String s;

    private b(String str) {
        this.s = str;
    }

    private static b a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key name specified");
        } else if (t.contains(str)) {
            throw new IllegalArgumentException("Key has already been used: ".concat(String.valueOf(str)));
        } else {
            t.add(str);
            return new b(str);
        }
    }

    public String a() {
        return this.s;
    }
}
