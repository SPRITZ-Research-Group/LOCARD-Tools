package com.google.android.gms.internal.c;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.b.b.a;

public final class o extends a implements m {
    o(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    public final b a(float f) throws RemoteException {
        Parcel u_ = u_();
        u_.writeFloat(f);
        u_ = a(5, u_);
        b a = a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final b a(int i) throws RemoteException {
        Parcel u_ = u_();
        u_.writeInt(i);
        u_ = a(1, u_);
        b a = a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }

    public final b a(Bitmap bitmap) throws RemoteException {
        Parcel u_ = u_();
        k.a(u_, (Parcelable) bitmap);
        u_ = a(6, u_);
        b a = a.a(u_.readStrongBinder());
        u_.recycle();
        return a;
    }
}
