package com.applovin.impl.sdk;

import java.util.LinkedList;
import java.util.Queue;

class dt {
    private int a;
    private final Queue<cj> b = new LinkedList();
    private final Object c = new Object();

    dt(int i) {
        a(i);
    }

    int a() {
        int size;
        synchronized (this.c) {
            size = this.b.size();
        }
        return size;
    }

    void a(int i) {
        if (i > 25) {
            i = 25;
        }
        this.a = i;
    }

    void a(cj cjVar) {
        synchronized (this.c) {
            if (a() <= 25) {
                this.b.offer(cjVar);
            }
        }
    }

    int b() {
        return this.a;
    }

    boolean c() {
        boolean z;
        synchronized (this.c) {
            z = a() >= this.a;
        }
        return z;
    }

    boolean d() {
        boolean z;
        synchronized (this.c) {
            z = a() == 0;
        }
        return z;
    }

    cj e() {
        cj cjVar;
        synchronized (this.c) {
            try {
                cjVar = !d() ? (cj) this.b.poll() : null;
            } catch (Throwable th) {
                return null;
            }
        }
        return cjVar;
    }

    cj f() {
        cj cjVar;
        synchronized (this.c) {
            cjVar = (cj) this.b.peek();
        }
        return cjVar;
    }
}
