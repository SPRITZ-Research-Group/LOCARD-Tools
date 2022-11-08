package com.google.android.exoplayer.hls;

public abstract class HlsPlaylist {
    public static final int TYPE_MASTER = 0;
    public static final int TYPE_MEDIA = 1;
    public final String baseUri;
    public final int type;

    protected HlsPlaylist(String str, int i) {
        this.baseUri = str;
        this.type = i;
    }
}
