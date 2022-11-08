package com.applovin.mediation;

public class AppLovinMediationAdapterStats {
    private final String a;
    private final long b;

    public AppLovinMediationAdapterStats(String str, long j) {
        if (str != null) {
            this.a = str;
            this.b = j;
            return;
        }
        throw new IllegalArgumentException("No adapter name specified");
    }

    public String getAdapterName() {
        return this.a;
    }

    public long getLastAdLoadMillis() {
        return this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[Adapter Stats - <");
        stringBuilder.append(this.a);
        stringBuilder.append(" : loaded in ");
        stringBuilder.append(this.b);
        stringBuilder.append("milliseconds>]");
        return stringBuilder.toString();
    }
}
