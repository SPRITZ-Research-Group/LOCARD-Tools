package com.skype.android.jipc.omx;

public enum ServicePath {
    MONOLITHIC("media.player", "android.media.IMediaPlayerService"),
    FRIGHTENED("media.codec", "android.media.IMediaCodecService");
    
    public final String c;
    public final String d;

    private ServicePath(String serviceName, String serviceInterfaceToken) {
        this.c = serviceName;
        this.d = serviceInterfaceToken;
    }
}
