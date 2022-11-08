package com.applovin.mediation;

import java.util.Map;

public class AppLovinMediatedAdInfo {
    private final Map<String, Object> a;

    public AppLovinMediatedAdInfo(Map<String, Object> map) {
        this.a = map;
    }

    public boolean containsKey(String str) {
        return this.a != null ? this.a.containsKey(str) : false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppLovinMediatedAdInfo)) {
            return false;
        }
        AppLovinMediatedAdInfo appLovinMediatedAdInfo = (AppLovinMediatedAdInfo) obj;
        return this.a != null ? this.a.equals(appLovinMediatedAdInfo.a) : appLovinMediatedAdInfo.a == null;
    }

    public Object get(String str) {
        return this.a != null ? this.a.get(str) : null;
    }

    public int hashCode() {
        return this.a != null ? this.a.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AppLovinMediatedAdInfo{adInfo=");
        stringBuilder.append(this.a);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
