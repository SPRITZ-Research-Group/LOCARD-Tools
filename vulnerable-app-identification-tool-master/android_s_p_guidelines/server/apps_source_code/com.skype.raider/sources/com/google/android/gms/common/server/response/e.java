package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;

public final class e implements Creator<SafeParcelResponse> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        FieldMappingDictionary fieldMappingDictionary = null;
        int a = a.a(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    parcel2 = a.z(parcel, readInt);
                    break;
                case 3:
                    fieldMappingDictionary = (FieldMappingDictionary) a.a(parcel, readInt, FieldMappingDictionary.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new SafeParcelResponse(i, parcel2, fieldMappingDictionary);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SafeParcelResponse[i];
    }
}
