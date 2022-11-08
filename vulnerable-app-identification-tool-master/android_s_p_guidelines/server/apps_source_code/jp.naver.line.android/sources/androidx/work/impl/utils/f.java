package androidx.work.impl.utils;

import androidx.work.ai;
import androidx.work.impl.j;

public final class f implements Runnable {
    private j a;
    private String b;
    private ai c;

    public f(j jVar, String str, ai aiVar) {
        this.a = jVar;
        this.b = str;
        this.c = aiVar;
    }

    public final void run() {
        this.a.g().a(this.b, this.c);
    }
}
