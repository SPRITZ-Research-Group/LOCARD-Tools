package com.google.android.gms.internal.a;

import android.os.Parcel;

public class c {
    private static final ClassLoader a = c.class.getClassLoader();

    private c() {
    }

    public static boolean a(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void b(Parcel parcel) {
        parcel.writeInt(1);
    }
}
