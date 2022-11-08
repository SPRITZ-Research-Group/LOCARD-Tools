package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.d;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class a implements com.google.android.exoplayer2.metadata.a {
    public final Metadata a(d inputBuffer) {
        ByteBuffer buffer = inputBuffer.b;
        k emsgData = new k(buffer.array(), buffer.limit());
        String schemeIdUri = emsgData.w();
        String value = emsgData.w();
        long timescale = emsgData.l();
        emsgData.d(4);
        return new Metadata(new EventMessage(schemeIdUri, value, (emsgData.l() * 1000) / timescale, emsgData.l(), Arrays.copyOfRange(data, emsgData.d(), size)));
    }
}
