package com.google.android.exoplayer2.text.d;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.c;
import java.util.Collections;
import java.util.List;

final class b implements c {
    public static final b a = new b();
    private final List<Cue> b;

    public b(Cue cue) {
        this.b = Collections.singletonList(cue);
    }

    private b() {
        this.b = Collections.emptyList();
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
        return timeUs >= 0 ? this.b : Collections.emptyList();
    }
}
