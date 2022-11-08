package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.d.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class DataSpec {
    public final Uri a;
    public final byte[] b;
    public final long c;
    public final long d;
    public final long e;
    public final String f;
    public final int g;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DataSpec(Uri uri, long absoluteStreamPosition, String key) {
        this(uri, absoluteStreamPosition, absoluteStreamPosition, -1, key, 0);
    }

    public DataSpec(Uri uri, long absoluteStreamPosition, long length, String key, int flags) {
        this(uri, absoluteStreamPosition, absoluteStreamPosition, length, key, flags);
    }

    public DataSpec(Uri uri, long absoluteStreamPosition, long position, long length, String key, int flags) {
        this(uri, absoluteStreamPosition, position, length, key, flags, (byte) 0);
    }

    private DataSpec(Uri uri, long absoluteStreamPosition, long position, long length, String key, int flags, byte b) {
        a.a(absoluteStreamPosition >= 0);
        a.a(position >= 0);
        boolean z = length > 0 || length == -1;
        a.a(z);
        this.a = uri;
        this.b = null;
        this.c = absoluteStreamPosition;
        this.d = position;
        this.e = length;
        this.f = key;
        this.g = flags;
    }

    public final boolean a() {
        return (this.g & 1) == 1;
    }

    public final String toString() {
        return "DataSpec[" + this.a + ", " + Arrays.toString(this.b) + ", " + this.c + ", " + this.d + ", " + this.e + ", " + this.f + ", " + this.g + "]";
    }
}
