package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class q extends a {
    private static final WeakReference<byte[]> b = new WeakReference(null);
    private WeakReference<byte[]> a = b;

    q(byte[] bArr) {
        super(bArr);
    }

    final byte[] a() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.a.get();
            if (bArr == null) {
                bArr = d();
                this.a = new WeakReference(bArr);
            }
        }
        return bArr;
    }

    protected abstract byte[] d();
}
