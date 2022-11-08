package com.facebook.internal;

import java.util.TreeSet;

abstract class ba {
    private TreeSet<Integer> a;

    protected abstract String a();

    protected abstract String b();

    private ba() {
    }

    /* synthetic */ ba(byte b) {
        this();
    }

    public final TreeSet<Integer> c() {
        if (this.a == null) {
            a(false);
        }
        return this.a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(boolean z) {
        if (!z) {
        }
        this.a = av.b(this);
    }
}
