package com.google.android.exoplayer;

public final class DecoderInfo {
    public final boolean adaptive;
    public final String name;

    DecoderInfo(String str, boolean z) {
        this.name = str;
        this.adaptive = z;
    }
}
