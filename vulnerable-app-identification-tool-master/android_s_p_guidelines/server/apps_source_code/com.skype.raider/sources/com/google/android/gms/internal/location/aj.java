package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.location.zzj;
import java.util.List;

public final class aj implements Creator<zzm> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        zzj zzj = zzm.b;
        List list = zzm.a;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    zzj = (zzj) a.a(parcel, readInt, zzj.CREATOR);
                    break;
                case 2:
                    list = a.c(parcel, readInt, ClientIdentity.CREATOR);
                    break;
                case 3:
                    str = a.p(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new zzm(zzj, list, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzm[i];
    }
}
