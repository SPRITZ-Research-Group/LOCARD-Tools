package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.text.Cue;
import java.util.Collections;
import java.util.List;

final class c implements com.google.android.exoplayer2.text.c {
    private final List<Cue> a;

    public c(List<Cue> cueList) {
        this.a = Collections.unmodifiableList(cueList);
    }

    public final int a(long timeUs) {
        return timeUs < 0 ? 0 : -1;
    }

    public final int b() {
        return 1;
    }

    public final long a(int index) {
        a.a(index == 0);
        return 0;
    }

    public final List<Cue> b(long timeUs) {
        return timeUs >= 0 ? this.a : Collections.emptyList();
    }
}
