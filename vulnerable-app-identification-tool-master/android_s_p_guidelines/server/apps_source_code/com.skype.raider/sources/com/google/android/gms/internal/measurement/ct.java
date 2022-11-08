package com.google.android.gms.internal.measurement;

abstract class ct extends cs {
    private boolean a;

    ct(bx bxVar) {
        super(bxVar);
        this.q.B();
    }

    final boolean F() {
        return this.a;
    }

    protected final void G() {
        if (!F()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void H() {
        if (this.a) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!t()) {
            this.q.C();
            this.a = true;
        }
    }

    public final void I() {
        if (this.a) {
            throw new IllegalStateException("Can't initialize twice");
        }
        t_();
        this.q.C();
        this.a = true;
    }

    protected abstract boolean t();

    protected void t_() {
    }
}
