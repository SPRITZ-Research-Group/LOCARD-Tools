package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;
import com.google.android.exoplayer2.text.a.g;
import java.util.List;

final class r {
    private final List<Format> a;
    private final n[] b;

    public r(List<Format> closedCaptionFormats) {
        this.a = closedCaptionFormats;
        this.b = new n[closedCaptionFormats.size()];
    }

    public final void a(h extractorOutput, d idGenerator) {
        for (int i = 0; i < this.b.length; i++) {
            idGenerator.a();
            n output = extractorOutput.a(idGenerator.b());
            Format channelFormat = (Format) this.a.get(i);
            String channelMimeType = channelFormat.f;
            boolean z = "application/cea-608".equals(channelMimeType) || "application/cea-708".equals(channelMimeType);
            a.a(z, "Invalid closed caption mime type provided: " + channelMimeType);
            output.a(Format.a(idGenerator.c(), channelMimeType, channelFormat.x, channelFormat.y, channelFormat.z));
            this.b[i] = output;
        }
    }

    public final void a(long pesTimeUs, k seiBuffer) {
        g.a(pesTimeUs, seiBuffer, this.b);
    }
}
