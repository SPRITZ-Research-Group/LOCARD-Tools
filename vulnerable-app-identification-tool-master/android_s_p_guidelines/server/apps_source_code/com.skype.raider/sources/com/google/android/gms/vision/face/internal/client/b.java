package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class b implements Creator<zzc> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        int a = a.a(parcel);
        float f = -1.0f;
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i3 = a.f(parcel, readInt);
                    break;
                case 3:
                    i2 = a.f(parcel, readInt);
                    break;
                case 4:
                    i = a.f(parcel, readInt);
                    break;
                case 5:
                    z2 = a.c(parcel, readInt);
                    break;
                case 6:
                    z = a.c(parcel, readInt);
                    break;
                case 7:
                    f = a.k(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzc(i3, i2, i, z2, z, f);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzc[i];
    }
}
