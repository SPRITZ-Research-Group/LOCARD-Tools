package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.a;

public final class i implements Creator<ConnectionInfo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Feature[] featureArr = null;
        int a = a.a(parcel);
        Bundle bundle = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    bundle = a.r(parcel, readInt);
                    break;
                case 2:
                    featureArr = (Feature[]) a.b(parcel, readInt, Feature.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ConnectionInfo(bundle, featureArr);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ConnectionInfo[i];
    }
}
