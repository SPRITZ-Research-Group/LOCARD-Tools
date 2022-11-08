package com.google.android.exoplayer.dash.mpd;

public final class UtcTimingElement {
    public final String schemeIdUri;
    public final String value;

    public UtcTimingElement(String str, String str2) {
        this.schemeIdUri = str;
        this.value = str2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.schemeIdUri);
        stringBuilder.append(", ");
        stringBuilder.append(this.value);
        return stringBuilder.toString();
    }
}
