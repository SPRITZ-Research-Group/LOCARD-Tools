package com.google.android.exoplayer2.d;

import java.util.PriorityQueue;

public final class n {
    private final Object a;
    private final PriorityQueue<Integer> b;
    private int c;

    public final void a() {
        synchronized (this.a) {
            this.b.add(Integer.valueOf(0));
            this.c = Math.max(this.c, 0);
        }
    }

    public final void b() {
        synchronized (this.a) {
            this.b.remove(Integer.valueOf(0));
            this.c = this.b.isEmpty() ? Integer.MIN_VALUE : ((Integer) this.b.peek()).intValue();
            this.a.notifyAll();
        }
    }
}
