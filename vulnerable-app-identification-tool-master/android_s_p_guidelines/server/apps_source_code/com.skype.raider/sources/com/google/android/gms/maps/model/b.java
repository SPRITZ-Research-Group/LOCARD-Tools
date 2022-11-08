package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.c.m;

public final class b {
    private static m a;

    private static m a() {
        return (m) ab.a(a, (Object) "IBitmapDescriptorFactory is not initialized");
    }

    public static a a(float f) {
        try {
            return new a(a().a(f));
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public static a a(int i) {
        try {
            return new a(a().a(i));
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public static a a(Bitmap bitmap) {
        try {
            return new a(a().a(bitmap));
        } catch (RemoteException e) {
            throw new g(e);
        }
    }

    public static void a(m mVar) {
        if (a == null) {
            a = (m) ab.a((Object) mVar);
        }
    }
}
