package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;

public class a implements IInterface {
    private final IBinder a;
    private final String b;

    protected a(IBinder iBinder, String str) {
        this.a = iBinder;
        this.b = str;
    }

    public IBinder asBinder() {
        return this.a;
    }
}
