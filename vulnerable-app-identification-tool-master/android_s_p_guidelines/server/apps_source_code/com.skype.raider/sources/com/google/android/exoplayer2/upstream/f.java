package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.IOException;

public interface f {

    public interface a {
        f a();
    }

    int a(byte[] bArr, int i, int i2) throws IOException;

    long a(DataSpec dataSpec) throws IOException;

    Uri a();

    void b() throws IOException;
}
