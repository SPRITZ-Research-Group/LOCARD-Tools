package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.vision.barcode.Barcode.CalendarDateTime;
import com.google.android.gms.vision.barcode.Barcode.CalendarEvent;

public final class e implements Creator<CalendarEvent> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        CalendarDateTime calendarDateTime = null;
        int a = a.a(parcel);
        CalendarDateTime calendarDateTime2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str5 = a.p(parcel, readInt);
                    break;
                case 3:
                    str4 = a.p(parcel, readInt);
                    break;
                case 4:
                    str3 = a.p(parcel, readInt);
                    break;
                case 5:
                    str2 = a.p(parcel, readInt);
                    break;
                case 6:
                    str = a.p(parcel, readInt);
                    break;
                case 7:
                    calendarDateTime2 = (CalendarDateTime) a.a(parcel, readInt, CalendarDateTime.CREATOR);
                    break;
                case 8:
                    calendarDateTime = (CalendarDateTime) a.a(parcel, readInt, CalendarDateTime.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new CalendarEvent(str5, str4, str3, str2, str, calendarDateTime2, calendarDateTime);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CalendarEvent[i];
    }
}
