package com.google.android.exoplayer2.text.webvtt;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class h implements c {
    private final List<d> a;
    private final int b;
    private final long[] c = new long[(this.b * 2)];
    private final long[] d;

    public h(List<d> cues) {
        this.a = cues;
        this.b = cues.size();
        for (int cueIndex = 0; cueIndex < this.b; cueIndex++) {
            d cue = (d) cues.get(cueIndex);
            int arrayIndex = cueIndex * 2;
            this.c[arrayIndex] = cue.m;
            this.c[arrayIndex + 1] = cue.n;
        }
        this.d = Arrays.copyOf(this.c, this.c.length);
        Arrays.sort(this.d);
    }

    public final int a(long timeUs) {
        int index = s.a(this.d, timeUs, false, false);
        return index < this.d.length ? index : -1;
    }

    public final int b() {
        return this.d.length;
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
        if (index >= this.d.length) {
            z2 = false;
        }
        a.a(z2);
        return this.d[index];
    }

    public final List<Cue> b(long timeUs) {
        ArrayList<Cue> list = null;
        d firstNormalCue = null;
        SpannableStringBuilder normalCueTextBuilder = null;
        int i = 0;
        while (i < this.b) {
            if (this.c[i * 2] <= timeUs && timeUs < this.c[(i * 2) + 1]) {
                Object obj;
                if (list == null) {
                    list = new ArrayList();
                }
                d cue = (d) this.a.get(i);
                if (cue.d == Float.MIN_VALUE && cue.g == Float.MIN_VALUE) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    list.add(cue);
                } else if (firstNormalCue == null) {
                    firstNormalCue = cue;
                } else if (normalCueTextBuilder == null) {
                    normalCueTextBuilder = new SpannableStringBuilder();
                    normalCueTextBuilder.append(firstNormalCue.a).append("\n").append(cue.a);
                } else {
                    normalCueTextBuilder.append("\n").append(cue.a);
                }
            }
            i++;
        }
        if (normalCueTextBuilder != null) {
            list.add(new d(normalCueTextBuilder));
        } else if (firstNormalCue != null) {
            list.add(firstNormalCue);
        }
        return list != null ? list : Collections.emptyList();
    }
}
