package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.vision.barcode.Barcode;

public final class h extends a implements g {
    h(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final Barcode[] a(b bVar, zzk zzk) throws RemoteException {
        Parcel a = a();
        d.a(a, (IInterface) bVar);
        d.a(a, (Parcelable) zzk);
        Parcel a2 = a(1, a);
        Barcode[] barcodeArr = (Barcode[]) a2.createTypedArray(Barcode.CREATOR);
        a2.recycle();
        return barcodeArr;
    }

    public final Barcode[] b(b bVar, zzk zzk) throws RemoteException {
        Parcel a = a();
        d.a(a, (IInterface) bVar);
        d.a(a, (Parcelable) zzk);
        Parcel a2 = a(2, a);
        Barcode[] barcodeArr = (Barcode[]) a2.createTypedArray(Barcode.CREATOR);
        a2.recycle();
        return barcodeArr;
    }
}
