package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

public class Period {
    public final List<AdaptationSet> adaptationSets;
    public final long durationMs;
    public final String id;
    public final long startMs;

    public Period(String str, long j, long j2, List<AdaptationSet> list) {
        this.id = str;
        this.startMs = j;
        this.durationMs = j2;
        this.adaptationSets = Collections.unmodifiableList(list);
    }

    public int getAdaptationSetIndex(int i) {
        int size = this.adaptationSets.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((AdaptationSet) this.adaptationSets.get(i2)).type == i) {
                return i2;
            }
        }
        return -1;
    }
}
