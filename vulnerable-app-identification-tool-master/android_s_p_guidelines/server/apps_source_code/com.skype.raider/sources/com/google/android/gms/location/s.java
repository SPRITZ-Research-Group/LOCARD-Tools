package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;

public final class s implements Creator<ActivityRecognitionResult> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        long j = 0;
        Bundle bundle = null;
        int a = a.a(parcel);
        int i = 0;
        long j2 = 0;
        List list = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    list = a.c(parcel, readInt, DetectedActivity.CREATOR);
                    break;
                case 2:
                    j2 = a.h(parcel, readInt);
                    break;
                case 3:
                    j = a.h(parcel, readInt);
                    break;
                case 4:
                    i = a.f(parcel, readInt);
                    break;
                case 5:
                    bundle = a.r(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ActivityRecognitionResult(list, j2, j, i, bundle);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ActivityRecognitionResult[i];
    }
}
