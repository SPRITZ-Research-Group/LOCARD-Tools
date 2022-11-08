package com.google.android.gms.internal.b;

final class l extends i {
    private final j a = new j();

    l() {
    }

    public final void a(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        } else if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        } else {
            this.a.a(th).add(th2);
        }
    }
}
