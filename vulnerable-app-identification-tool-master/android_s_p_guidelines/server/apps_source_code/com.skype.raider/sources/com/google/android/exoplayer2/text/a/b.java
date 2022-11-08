package com.google.android.exoplayer2.text.a;

import android.support.annotation.NonNull;
import android.text.Layout.Alignment;
import com.google.android.exoplayer2.text.Cue;

final class b extends Cue implements Comparable<b> {
    public final int m;

    public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
        b bVar = (b) obj;
        if (bVar.m < this.m) {
            return -1;
        }
        if (bVar.m > this.m) {
            return 1;
        }
        return 0;
    }

    public b(CharSequence text, Alignment textAlignment, float line, int lineAnchor, float position, int positionAnchor, boolean windowColorSet, int windowColor, int priority) {
        super(text, textAlignment, line, 0, lineAnchor, position, positionAnchor, Float.MIN_VALUE, windowColorSet, windowColor);
        this.m = priority;
    }
}
