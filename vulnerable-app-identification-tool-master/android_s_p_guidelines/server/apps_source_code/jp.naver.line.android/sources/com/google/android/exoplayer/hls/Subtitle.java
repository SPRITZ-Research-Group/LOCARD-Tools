package com.google.android.exoplayer.hls;

public final class Subtitle {
    public final boolean autoSelect;
    public final boolean isDefault;
    public final String language;
    public final String name;
    public final String uri;

    public Subtitle(String str, String str2, String str3, boolean z, boolean z2) {
        this.name = str;
        this.uri = str2;
        this.language = str3;
        this.autoSelect = z2;
        this.isDefault = z;
    }
}
