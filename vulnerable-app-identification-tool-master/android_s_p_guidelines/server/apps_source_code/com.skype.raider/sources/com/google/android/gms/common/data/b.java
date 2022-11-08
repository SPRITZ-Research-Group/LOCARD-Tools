package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.safeparcel.a;

public final class b implements Creator<DataHolder> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int a = a.a(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    strArr = a.x(parcel, readInt);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) a.b(parcel, readInt, CursorWindow.CREATOR);
                    break;
                case 3:
                    i = a.f(parcel, readInt);
                    break;
                case 4:
                    bundle = a.r(parcel, readInt);
                    break;
                case Constants.ONE_SECOND /*1000*/:
                    i2 = a.f(parcel, readInt);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.a();
        return dataHolder;
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DataHolder[i];
    }
}
