package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair;

public final class b implements Creator<FieldMapPair> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Field field = null;
        int a = a.a(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    str = a.p(parcel, readInt);
                    break;
                case 3:
                    field = (Field) a.a(parcel, readInt, Field.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new FieldMapPair(i, str, field);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new FieldMapPair[i];
    }
}
