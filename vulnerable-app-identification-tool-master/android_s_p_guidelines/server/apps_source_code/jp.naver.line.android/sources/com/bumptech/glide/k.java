package com.bumptech.glide;

import defpackage.alu;
import java.util.Queue;

final class k {
    private final Queue<j> a;

    k(int i) {
        this.a = alu.a(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.a.offer(new j());
        }
    }

    public final j a(int i, int i2) {
        j jVar = (j) this.a.poll();
        this.a.offer(jVar);
        jVar.b = i;
        jVar.a = i2;
        return jVar;
    }
}
