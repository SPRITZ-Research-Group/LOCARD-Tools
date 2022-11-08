package androidx.work.impl.background.systemalarm;

import android.content.Intent;

final class f implements Runnable {
    private final e a;
    private final Intent b;
    private final int c;

    f(e eVar, Intent intent, int i) {
        this.a = eVar;
        this.b = intent;
        this.c = i;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
