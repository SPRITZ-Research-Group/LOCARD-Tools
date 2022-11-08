package com.applovin.impl.sdk;

import com.google.android.exoplayer.hls.HlsMediaPlaylist;

public enum o {
    NONE(HlsMediaPlaylist.ENCRYPTION_METHOD_NONE),
    DIRECT("DIRECT"),
    INDIRECT("INDIRECT");
    
    private final String d;

    private o(String str) {
        this.d = str;
    }

    public static o a(String str) {
        return DIRECT.toString().equalsIgnoreCase(str) ? DIRECT : INDIRECT.toString().equalsIgnoreCase(str) ? INDIRECT : NONE;
    }

    public final String toString() {
        return this.d;
    }
}
