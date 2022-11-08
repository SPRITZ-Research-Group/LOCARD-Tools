package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class u implements Creator<ActivityTransitionEvent> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = a.a(parcel);
        long j = 0;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = a.f(parcel, readInt);
                    break;
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    j = a.h(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ActivityTransitionEvent(i2, i, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ActivityTransitionEvent[i];
    }
}
