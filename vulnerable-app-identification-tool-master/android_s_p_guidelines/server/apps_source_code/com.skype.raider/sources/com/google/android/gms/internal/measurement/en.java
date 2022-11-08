package com.google.android.gms.internal.measurement;

abstract class en extends em {
    private boolean b;

    en(eo eoVar) {
        super(eoVar);
        this.a.L();
    }

    final boolean M() {
        return this.b;
    }

    protected final void N() {
        if (!M()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void O() {
        if (this.b) {
            throw new IllegalStateException("Can't initialize twice");
        }
        t();
        this.a.M();
        this.b = true;
    }

    protected abstract boolean t();
}
