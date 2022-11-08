package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public final class ap extends fp implements an {
    ap(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final List<zzjx> a(zzdz zzdz, boolean z) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzdz);
        fr.a(a, z);
        a = a(7, a);
        List createTypedArrayList = a.createTypedArrayList(zzjx.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final List<zzed> a(String str, String str2, zzdz zzdz) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        fr.a(a, (Parcelable) zzdz);
        a = a(16, a);
        List createTypedArrayList = a.createTypedArrayList(zzed.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final List<zzed> a(String str, String str2, String str3) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        a.writeString(str3);
        a = a(17, a);
        List createTypedArrayList = a.createTypedArrayList(zzed.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final List<zzjx> a(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        a.writeString(str3);
        fr.a(a, z);
        a = a(15, a);
        List createTypedArrayList = a.createTypedArrayList(zzjx.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final List<zzjx> a(String str, String str2, boolean z, zzdz zzdz) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        fr.a(a, z);
        fr.a(a, (Parcelable) zzdz);
        a = a(14, a);
        List createTypedArrayList = a.createTypedArrayList(zzjx.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final void a(long j, String str, String str2, String str3) throws RemoteException {
        Parcel a = a();
        a.writeLong(j);
        a.writeString(str);
        a.writeString(str2);
        a.writeString(str3);
        b(10, a);
    }

    public final void a(zzdz zzdz) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzdz);
        b(4, a);
    }

    public final void a(zzed zzed) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzed);
        b(13, a);
    }

    public final void a(zzed zzed, zzdz zzdz) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzed);
        fr.a(a, (Parcelable) zzdz);
        b(12, a);
    }

    public final void a(zzeu zzeu, zzdz zzdz) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzeu);
        fr.a(a, (Parcelable) zzdz);
        b(1, a);
    }

    public final void a(zzeu zzeu, String str, String str2) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzeu);
        a.writeString(str);
        a.writeString(str2);
        b(5, a);
    }

    public final void a(zzjx zzjx, zzdz zzdz) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzjx);
        fr.a(a, (Parcelable) zzdz);
        b(2, a);
    }

    public final byte[] a(zzeu zzeu, String str) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzeu);
        a.writeString(str);
        a = a(9, a);
        byte[] createByteArray = a.createByteArray();
        a.recycle();
        return createByteArray;
    }

    public final void b(zzdz zzdz) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzdz);
        b(6, a);
    }

    public final String c(zzdz zzdz) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzdz);
        a = a(11, a);
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final void d(zzdz zzdz) throws RemoteException {
        Parcel a = a();
        fr.a(a, (Parcelable) zzdz);
        b(18, a);
    }
}
