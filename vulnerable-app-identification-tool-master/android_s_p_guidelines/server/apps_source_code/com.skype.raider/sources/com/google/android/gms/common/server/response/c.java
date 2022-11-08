package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.server.response.FieldMappingDictionary.Entry;
import java.util.ArrayList;

public final class c implements Creator<FieldMappingDictionary> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = a.a(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = a.f(parcel, readInt);
                    break;
                case 2:
                    arrayList = a.c(parcel, readInt, Entry.CREATOR);
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
        return new FieldMappingDictionary(i, arrayList, str);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new FieldMappingDictionary[i];
    }
}
