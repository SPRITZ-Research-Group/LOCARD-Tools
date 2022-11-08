package com.google.android.gms.internal.vision;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.vision.barcode.Barcode;

public interface g extends IInterface {
    Barcode[] a(b bVar, zzk zzk) throws RemoteException;

    Barcode[] b(b bVar, zzk zzk) throws RemoteException;
}
