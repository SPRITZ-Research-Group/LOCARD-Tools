package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class ao extends fq implements an {
    public ao() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    protected final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
        List a;
        switch (i) {
            case 1:
                a((zzeu) fr.a(parcel, zzeu.CREATOR), (zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                a((zzjx) fr.a(parcel, zzjx.CREATOR), (zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                a((zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                a((zzeu) fr.a(parcel, zzeu.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                b((zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                a = a((zzdz) fr.a(parcel, zzdz.CREATOR), fr.a(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(a);
                break;
            case 9:
                byte[] a2 = a((zzeu) fr.a(parcel, zzeu.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(a2);
                break;
            case 10:
                a(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String c = c((zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(c);
                break;
            case 12:
                a((zzed) fr.a(parcel, zzed.CREATOR), (zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                a((zzed) fr.a(parcel, zzed.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                a = a(parcel.readString(), parcel.readString(), fr.a(parcel), (zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(a);
                break;
            case 15:
                a = a(parcel.readString(), parcel.readString(), parcel.readString(), fr.a(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(a);
                break;
            case 16:
                a = a(parcel.readString(), parcel.readString(), (zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(a);
                break;
            case 17:
                a = a(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(a);
                break;
            case 18:
                d((zzdz) fr.a(parcel, zzdz.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
