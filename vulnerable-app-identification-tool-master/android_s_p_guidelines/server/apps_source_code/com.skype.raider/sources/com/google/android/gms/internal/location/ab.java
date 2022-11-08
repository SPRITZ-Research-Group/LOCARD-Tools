package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class ab implements Creator<zzbf> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int a = a.a(parcel);
        int i = 1;
        IBinder iBinder2 = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder3 = null;
        zzbd zzbd = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    zzbd = (zzbd) a.a(parcel, readInt, zzbd.CREATOR);
                    break;
                case 3:
                    iBinder3 = a.q(parcel, readInt);
                    break;
                case 4:
                    pendingIntent = (PendingIntent) a.a(parcel, readInt, PendingIntent.CREATOR);
                    break;
                case 5:
                    iBinder2 = a.q(parcel, readInt);
                    break;
                case 6:
                    iBinder = a.q(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzbf(i, zzbd, iBinder3, pendingIntent, iBinder2, iBinder);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbf[i];
    }
}
