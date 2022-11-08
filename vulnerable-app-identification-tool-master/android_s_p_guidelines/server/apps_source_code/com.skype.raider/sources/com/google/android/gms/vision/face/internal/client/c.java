package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class c implements Creator<LandmarkParcel> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        float f = 0.0f;
        int a = a.a(parcel);
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = a.f(parcel, readInt);
                    break;
                case 2:
                    f2 = a.k(parcel, readInt);
                    break;
                case 3:
                    f = a.k(parcel, readInt);
                    break;
                case 4:
                    i = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new LandmarkParcel(i2, f2, f, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LandmarkParcel[i];
    }
}
