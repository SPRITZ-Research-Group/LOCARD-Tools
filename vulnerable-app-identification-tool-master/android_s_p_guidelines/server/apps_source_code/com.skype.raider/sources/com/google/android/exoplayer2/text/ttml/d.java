package com.google.android.exoplayer2.text.ttml;

import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.c;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class d implements c {
    private final b a;
    private final long[] b;
    private final Map<String, TtmlStyle> c;
    private final Map<String, c> d;

    public d(b root, Map<String, TtmlStyle> globalStyles, Map<String, c> regionMap) {
        this.a = root;
        this.d = regionMap;
        this.c = Collections.unmodifiableMap(globalStyles);
        this.b = root.a();
    }

    public final int a(long timeUs) {
        int index = s.a(this.b, timeUs, false, false);
        return index < this.b.length ? index : -1;
    }

    public final int b() {
        return this.b.length;
    }

    public final long a(int index) {
        return this.b[index];
    }

    public final List<Cue> b(long timeUs) {
        return this.a.a(timeUs, this.c, this.d);
    }
}
