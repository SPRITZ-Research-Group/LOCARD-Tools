package com.applovin.mediation;

public class AppLovinMediationAdapterInfo {
    private final String a;
    private final String b;
    private final String c;
    private final AppLovinMediationAdapterStatus d;
    private final AppLovinMediationAdapter e;
    private final AppLovinMediationAdapterConfig f;

    public AppLovinMediationAdapterInfo(String str, String str2, String str3, AppLovinMediationAdapterStatus appLovinMediationAdapterStatus) {
        this(str, str2, str3, appLovinMediationAdapterStatus, null, null);
    }

    public AppLovinMediationAdapterInfo(String str, String str2, String str3, AppLovinMediationAdapterStatus appLovinMediationAdapterStatus, AppLovinMediationAdapter appLovinMediationAdapter, AppLovinMediationAdapterConfig appLovinMediationAdapterConfig) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No class name specified");
        } else if (appLovinMediationAdapterStatus != null) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = appLovinMediationAdapterStatus;
            this.e = appLovinMediationAdapter;
            this.f = appLovinMediationAdapterConfig;
        } else {
            throw new IllegalArgumentException("No status specified");
        }
    }

    public AppLovinMediationAdapter getAdapter() {
        return this.e;
    }

    public AppLovinMediationAdapterConfig getAdapterConfiguration() {
        return this.f;
    }

    public String getClassName() {
        return this.b;
    }

    public String getName() {
        return this.a;
    }

    public AppLovinMediationAdapterStatus getStatus() {
        return this.d;
    }

    public String getVersion() {
        return this.c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[Adapter Info - <");
        stringBuilder.append(this.a);
        stringBuilder.append(" : ");
        stringBuilder.append(this.b);
        stringBuilder.append("> v");
        stringBuilder.append(this.c);
        stringBuilder.append(" with configuration: ");
        stringBuilder.append(this.f);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
