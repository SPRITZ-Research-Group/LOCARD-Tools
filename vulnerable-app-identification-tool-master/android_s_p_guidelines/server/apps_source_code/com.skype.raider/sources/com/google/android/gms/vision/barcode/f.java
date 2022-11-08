package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.vision.barcode.Barcode.Address;
import com.google.android.gms.vision.barcode.Barcode.ContactInfo;
import com.google.android.gms.vision.barcode.Barcode.Email;
import com.google.android.gms.vision.barcode.Barcode.PersonName;
import com.google.android.gms.vision.barcode.Barcode.Phone;

public final class f implements Creator<ContactInfo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Address[] addressArr = null;
        int a = a.a(parcel);
        String[] strArr = null;
        Email[] emailArr = null;
        Phone[] phoneArr = null;
        String str = null;
        String str2 = null;
        PersonName personName = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    personName = (PersonName) a.a(parcel, readInt, PersonName.CREATOR);
                    break;
                case 3:
                    str2 = a.p(parcel, readInt);
                    break;
                case 4:
                    str = a.p(parcel, readInt);
                    break;
                case 5:
                    phoneArr = (Phone[]) a.b(parcel, readInt, Phone.CREATOR);
                    break;
                case 6:
                    emailArr = (Email[]) a.b(parcel, readInt, Email.CREATOR);
                    break;
                case 7:
                    strArr = a.x(parcel, readInt);
                    break;
                case 8:
                    addressArr = (Address[]) a.b(parcel, readInt, Address.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new ContactInfo(personName, str2, str, phoneArr, emailArr, strArr, addressArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ContactInfo[i];
    }
}
