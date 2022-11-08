package com.google.android.exoplayer2.text.b;

import com.google.android.exoplayer2.text.Cue;
import java.util.List;

final class c implements com.google.android.exoplayer2.text.c {
    private final List<Cue> a;

    public c(List<Cue> cues) {
        this.a = cues;
    }

    public final int a(long timeUs) {
        return -1;
    }

    public final int b() {
        return 1;
    }

    public final long a(int index) {
        return 0;
    }

    public final List<Cue> b(long timeUs) {
        return this.a;
    }
}
