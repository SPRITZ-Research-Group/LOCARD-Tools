package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import java.io.IOException;

public interface n {
    int a(g gVar, int i, boolean z) throws IOException, InterruptedException;

    void a(long j, int i, int i2, int i3, byte[] bArr);

    void a(Format format);

    void a(k kVar, int i);
}
