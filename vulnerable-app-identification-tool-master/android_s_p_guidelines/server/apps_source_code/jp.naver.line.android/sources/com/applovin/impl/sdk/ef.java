package com.applovin.impl.sdk;

import java.util.HashSet;

public class ef<T> {
    static final ef<String> a = new ef("com.applovin.sdk.impl.isFirstRun", String.class);
    static final ef<HashSet> b = new ef("com.applovin.sdk.impl.postbackQueue.key", HashSet.class);
    static final ef<Integer> c = new ef("com.applovin.sdk.last_version_code", Integer.class);
    static final ef<String> d = new ef("com.applovin.sdk.device_data", String.class);
    static final ef<String> e = new ef("com.applovin.sdk.zones", String.class);
    static final ef<String> f = new ef("com.applovin.sdk.loaded_mediation_adapters", String.class);
    static final ef<Boolean> g = new ef("com.applovin.sdk.compliance.has_user_consent", Boolean.class);
    static final ef<Boolean> h = new ef("com.applovin.sdk.compliance.is_age_restricted_user", Boolean.class);
    static final ef<String> i = new ef("com.applovin.sdk.stats", String.class);
    static final ef<HashSet> j = new ef("com.applovin.sdk.ad.stats", HashSet.class);
    public static final ef<Integer> k = new ef("com.applovin.sdk.last_video_position", Integer.class);
    public static final ef<Boolean> l = new ef("com.applovin.sdk.should_resume_video", Boolean.class);
    private final String m;
    private final Class<T> n;

    ef(String str, Class<T> cls) {
        this.m = str;
        this.n = cls;
    }

    String a() {
        return this.m;
    }

    Class<T> b() {
        return this.n;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Key{name='");
        stringBuilder.append(this.m);
        stringBuilder.append('\'');
        stringBuilder.append("type='");
        stringBuilder.append(this.n);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
