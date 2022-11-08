package com.facebook.ads.internal.view.hscroll;

import android.util.SparseArray;

public final class a {
    private final SparseArray<int[]> a = new SparseArray();

    public final void a(int i, int[] iArr) {
        this.a.put(i, iArr);
    }

    public final int[] a(int i) {
        return (int[]) this.a.get(i);
    }

    public final boolean b(int i) {
        return this.a.indexOfKey(i) >= 0;
    }
}
