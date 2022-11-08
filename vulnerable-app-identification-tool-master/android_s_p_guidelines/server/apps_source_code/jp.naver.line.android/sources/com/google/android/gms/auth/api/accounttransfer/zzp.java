package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzp implements Creator<zzo> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzo[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        List list = null;
        List list2 = list;
        List list3 = list2;
        List list4 = list3;
        List list5 = list4;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    list = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 3:
                    list2 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 4:
                    list3 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 5:
                    list4 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 6:
                    list5 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzo(i, list, list2, list3, list4, list5);
    }
}
