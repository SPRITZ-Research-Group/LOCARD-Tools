package com.google.android.exoplayer2.text.b;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import java.util.List;

public final class a extends com.google.android.exoplayer2.text.a {
    private final b a;

    public a(List<byte[]> initializationData) {
        super("DvbDecoder");
        k data = new k((byte[]) initializationData.get(0));
        this.a = new b(data.h(), data.h());
    }

    protected final /* synthetic */ c a(byte[] bArr, int i, boolean z) throws e {
        if (z) {
            this.a.a();
        }
        return new c(this.a.a(bArr, i));
    }
}
