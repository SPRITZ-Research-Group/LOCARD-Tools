package com.google.android.gms.b;

import android.os.IBinder;
import android.os.IInterface;

public interface b extends IInterface {

    public static abstract class a extends com.google.android.gms.internal.d.b implements b {

        public static class a extends com.google.android.gms.internal.d.a implements b {
            a(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.dynamic.IObjectWrapper");
            }
        }

        public a() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            return queryLocalInterface instanceof b ? (b) queryLocalInterface : new a(iBinder);
        }
    }
}
