package com.google.android.exoplayer2.text;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

public final class g extends DecoderInputBuffer implements Comparable<g> {
    public long d;

    public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
        long j = this.c - ((g) obj).c;
        if (j == 0) {
            return 0;
        }
        return j > 0 ? 1 : -1;
    }

    public g() {
        super(1);
    }
}
