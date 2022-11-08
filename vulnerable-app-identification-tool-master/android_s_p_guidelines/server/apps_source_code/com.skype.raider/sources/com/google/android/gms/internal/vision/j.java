package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;

public final class j extends a implements i {
    j(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
    }

    public final g a(b bVar, zzc zzc) throws RemoteException {
        g gVar;
        Parcel a = a();
        d.a(a, (IInterface) bVar);
        d.a(a, (Parcelable) zzc);
        Parcel a2 = a(1, a);
        IBinder readStrongBinder = a2.readStrongBinder();
        if (readStrongBinder == null) {
            gVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            gVar = queryLocalInterface instanceof g ? (g) queryLocalInterface : new h(readStrongBinder);
        }
        a2.recycle();
        return gVar;
    }
}
