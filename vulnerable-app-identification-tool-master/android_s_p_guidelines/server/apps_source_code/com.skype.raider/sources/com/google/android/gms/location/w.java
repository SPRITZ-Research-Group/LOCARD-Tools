package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;

public final class w implements Creator<ActivityTransitionRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list = null;
        int a = a.a(parcel);
        String str = null;
        List list2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    list2 = a.c(parcel, readInt, ActivityTransition.CREATOR);
                    break;
                case 2:
                    str = a.p(parcel, readInt);
                    break;
                case 3:
                    list = a.c(parcel, readInt, ClientIdentity.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ActivityTransitionRequest(list2, str, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ActivityTransitionRequest[i];
    }
}
