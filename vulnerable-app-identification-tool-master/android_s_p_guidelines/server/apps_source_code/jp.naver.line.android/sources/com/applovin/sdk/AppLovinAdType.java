package com.applovin.sdk;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AppLovinAdType {
    public static final AppLovinAdType INCENTIVIZED = new AppLovinAdType("VIDEOA");
    public static final AppLovinAdType NATIVE = new AppLovinAdType("NATIVE");
    public static final AppLovinAdType REGULAR = new AppLovinAdType("REGULAR");
    private final String a;

    public AppLovinAdType(String str) {
        this.a = str;
    }

    public static Set<AppLovinAdType> allTypes() {
        Set<AppLovinAdType> hashSet = new HashSet(2);
        hashSet.add(REGULAR);
        hashSet.add(INCENTIVIZED);
        return hashSet;
    }

    public static AppLovinAdType fromString(String str) {
        return str.toUpperCase(Locale.ENGLISH).equals(INCENTIVIZED.getLabel()) ? INCENTIVIZED : REGULAR;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AppLovinAdType appLovinAdType = (AppLovinAdType) obj;
            return this.a == null ? appLovinAdType.a != null : !this.a.equals(appLovinAdType.a);
        }
    }

    public String getLabel() {
        return this.a.toUpperCase(Locale.ENGLISH);
    }

    public int hashCode() {
        return this.a != null ? this.a.hashCode() : 0;
    }

    public String toString() {
        return getLabel();
    }
}
