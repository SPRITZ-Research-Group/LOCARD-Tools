package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class b extends a {
    private static final int a = s.e("payl");
    private static final int b = s.e("sttg");
    private static final int c = s.e("vttc");
    private final k d = new k();
    private final d.a e = new d.a();

    public b() {
        super("Mp4WebvttDecoder");
    }

    private static Cue a(k sampleData, d.a builder, int remainingCueBoxBytes) throws e {
        builder.a();
        while (remainingCueBoxBytes > 0) {
            if (remainingCueBoxBytes < 8) {
                throw new e("Incomplete vtt cue box header found.");
            }
            int boxSize = sampleData.n();
            int boxType = sampleData.n();
            remainingCueBoxBytes -= 8;
            int payloadLength = boxSize - 8;
            String boxPayload = new String(sampleData.a, sampleData.d(), payloadLength);
            sampleData.d(payloadLength);
            remainingCueBoxBytes -= payloadLength;
            if (boxType == b) {
                e.a(boxPayload, builder);
            } else if (boxType == a) {
                e.a(null, boxPayload.trim(), builder, Collections.emptyList());
            }
        }
        return builder.b();
    }

    protected final /* synthetic */ c a(byte[] bArr, int i, boolean z) throws e {
        this.d.a(bArr, i);
        List arrayList = new ArrayList();
        while (this.d.b() > 0) {
            if (this.d.b() < 8) {
                throw new e("Incomplete Mp4Webvtt Top Level box header found.");
            }
            int n = this.d.n();
            if (this.d.n() == c) {
                arrayList.add(a(this.d, this.e, n - 8));
            } else {
                this.d.d(n - 8);
            }
        }
        return new c(arrayList);
    }
}
