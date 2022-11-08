package com.google.android.exoplayer2;

import defpackage.bcz;

final class p {
    private z a;
    private int b;
    private boolean c;
    private int d;

    private p() {
    }

    /* synthetic */ p(byte b) {
        this();
    }

    public final boolean a(z zVar) {
        return zVar != this.a || this.b > 0 || this.c;
    }

    public final void b(z zVar) {
        this.a = zVar;
        this.b = 0;
        this.c = false;
    }

    public final void a(int i) {
        this.b += i;
    }

    public final void b(int i) {
        boolean z = true;
        if (!this.c || this.d == 4) {
            this.c = true;
            this.d = i;
            return;
        }
        if (i != 4) {
            z = false;
        }
        bcz.a(z);
    }
}
