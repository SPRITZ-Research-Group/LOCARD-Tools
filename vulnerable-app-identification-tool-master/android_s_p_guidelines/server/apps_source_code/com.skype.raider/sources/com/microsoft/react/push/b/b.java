package com.microsoft.react.push.b;

import com.microsoft.react.push.c.a;

public final class b {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private a d;

    public b(a completionExecutor) {
        this.d = completionExecutor;
    }

    public final void a() {
        this.a = true;
        c();
    }

    public final void b() {
        this.b = true;
        c();
    }

    private void c() {
        if (!this.c && this.a && this.b) {
            this.c = true;
            this.d.a();
        }
    }
}
