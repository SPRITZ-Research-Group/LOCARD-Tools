package com.google.android.exoplayer2.text.c;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.c;
import java.util.Collections;
import java.util.List;

final class b implements c {
    private final Cue[] a;
    private final long[] b;

    public b(Cue[] cues, long[] cueTimesUs) {
        this.a = cues;
        this.b = cueTimesUs;
    }

    public final int a(long timeUs) {
        int index = s.a(this.b, timeUs, false, false);
        return index < this.b.length ? index : -1;
    }

    public final int b() {
        return this.b.length;
    }

    public final long a(int index) {
        boolean z;
        boolean z2 = true;
        if (index >= 0) {
            z = true;
        } else {
            z = false;
        }
        a.a(z);
        if (index >= this.b.length) {
            z2 = false;
        }
        a.a(z2);
        return this.b[index];
    }

    public final List<Cue> b(long timeUs) {
        int index = s.a(this.b, timeUs, false);
        if (index == -1 || this.a[index] == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(this.a[index]);
    }
}
