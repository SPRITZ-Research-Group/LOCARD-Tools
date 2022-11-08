package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import java.util.HashSet;
import java.util.Set;

public final class zzs implements Creator<zzr> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        zzt zzt = null;
        String str = zzt;
        String str2 = str;
        String str3 = str2;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    zzt = (zzt) SafeParcelReader.createParcelable(parcel, readHeader, zzt.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case 5:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    hashSet.add(Integer.valueOf(5));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzr(hashSet, i, zzt, str, str2, str3);
        }
        StringBuilder stringBuilder = new StringBuilder(37);
        stringBuilder.append("Overread allowed size end=");
        stringBuilder.append(validateObjectHeader);
        throw new ParseException(stringBuilder.toString(), parcel);
    }
}
