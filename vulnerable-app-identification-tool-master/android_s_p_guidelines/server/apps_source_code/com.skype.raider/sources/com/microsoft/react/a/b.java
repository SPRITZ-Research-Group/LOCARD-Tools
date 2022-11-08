package com.microsoft.react.a;

import android.net.Uri;
import android.support.annotation.NonNull;

public final class b {
    @NonNull
    public final Uri a;
    public final long b;
    public final boolean c;
    public final long d;
    public final int e;
    public final long f;
    public long g;
    public int h;
    public int i;

    public b(Uri fileUri, long id, long size, int width, int height, boolean isVideo, long duration, int orientation, long timestamp) {
        this.a = fileUri;
        this.b = id;
        this.g = size;
        this.h = width;
        this.i = height;
        this.c = isVideo;
        this.d = duration;
        this.e = orientation;
        this.f = timestamp;
    }
}
