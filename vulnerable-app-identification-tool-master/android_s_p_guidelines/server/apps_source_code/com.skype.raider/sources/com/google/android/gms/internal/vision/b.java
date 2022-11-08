package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class b implements Creator<zzaa> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        while (parcel.dataPosition() < a) {
            a.b(parcel, parcel.readInt());
        }
        a.B(parcel, a);
        return new zzaa();
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaa[i];
    }
}
