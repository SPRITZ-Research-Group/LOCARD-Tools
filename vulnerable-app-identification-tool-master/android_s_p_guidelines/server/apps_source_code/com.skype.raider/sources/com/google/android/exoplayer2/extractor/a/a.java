package com.google.android.exoplayer2.extractor.a;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.b;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.n;
import com.skype.Defines;
import java.util.Collections;

final class a extends d {
    private static final int[] b = new int[]{5512, 11025, 22050, 44100};
    private boolean c;
    private boolean d;
    private int e;

    public a(n output) {
        super(output);
    }

    protected final boolean a(k data) throws com.google.android.exoplayer2.extractor.a.d.a {
        if (this.c) {
            data.d(1);
        } else {
            int header = data.g();
            this.e = (header >> 4) & 15;
            if (this.e == 2) {
                this.a.a(Format.a(null, "audio/mpeg", -1, -1, 1, b[(header >> 2) & 3], null, null, null));
                this.d = true;
            } else if (this.e == 7 || this.e == 8) {
                this.a.a(Format.a(null, this.e == 7 ? "audio/g711-alaw" : "audio/g711-mlaw", -1, -1, 1, Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE, (header & 1) == 1 ? 2 : 3, null, null, 0, null));
                this.d = true;
            } else if (this.e != 10) {
                throw new com.google.android.exoplayer2.extractor.a.d.a("Audio format not supported: " + this.e);
            }
            this.c = true;
        }
        return true;
    }

    protected final void a(k data, long timeUs) {
        int sampleSize;
        if (this.e == 2) {
            sampleSize = data.b();
            this.a.a(data, sampleSize);
            this.a.a(timeUs, 1, sampleSize, 0, null);
            return;
        }
        int packetType = data.g();
        if (packetType == 0 && !this.d) {
            byte[] audioSpecificConfig = new byte[data.b()];
            data.a(audioSpecificConfig, 0, audioSpecificConfig.length);
            Pair<Integer, Integer> audioParams = b.a(audioSpecificConfig);
            this.a.a(Format.a(null, "audio/mp4a-latm", -1, -1, ((Integer) audioParams.second).intValue(), ((Integer) audioParams.first).intValue(), Collections.singletonList(audioSpecificConfig), null, null));
            this.d = true;
        } else if (this.e != 10 || packetType == 1) {
            sampleSize = data.b();
            this.a.a(data, sampleSize);
            this.a.a(timeUs, 1, sampleSize, 0, null);
        }
    }
}
