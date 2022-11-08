package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import java.util.Comparator;

final class d implements Comparator<Format> {
    private d() {
    }

    /* synthetic */ d(byte b) {
        this();
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return ((Format) obj2).c - ((Format) obj).c;
    }
}
