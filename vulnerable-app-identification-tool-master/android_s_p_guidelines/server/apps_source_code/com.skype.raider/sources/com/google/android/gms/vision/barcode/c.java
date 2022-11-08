package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.vision.barcode.Barcode.CalendarEvent;
import com.google.android.gms.vision.barcode.Barcode.ContactInfo;
import com.google.android.gms.vision.barcode.Barcode.DriverLicense;
import com.google.android.gms.vision.barcode.Barcode.Email;
import com.google.android.gms.vision.barcode.Barcode.GeoPoint;
import com.google.android.gms.vision.barcode.Barcode.Phone;
import com.google.android.gms.vision.barcode.Barcode.Sms;
import com.google.android.gms.vision.barcode.Barcode.UrlBookmark;
import com.google.android.gms.vision.barcode.Barcode.WiFi;

public final class c implements Creator<Barcode> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = a.a(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        int i2 = 0;
        Point[] pointArr = null;
        Email email = null;
        Phone phone = null;
        Sms sms = null;
        WiFi wiFi = null;
        UrlBookmark urlBookmark = null;
        GeoPoint geoPoint = null;
        CalendarEvent calendarEvent = null;
        ContactInfo contactInfo = null;
        DriverLicense driverLicense = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = a.f(parcel, readInt);
                    break;
                case 3:
                    str = a.p(parcel, readInt);
                    break;
                case 4:
                    str2 = a.p(parcel, readInt);
                    break;
                case 5:
                    i2 = a.f(parcel, readInt);
                    break;
                case 6:
                    pointArr = (Point[]) a.b(parcel, readInt, Point.CREATOR);
                    break;
                case 7:
                    email = (Email) a.a(parcel, readInt, Email.CREATOR);
                    break;
                case 8:
                    phone = (Phone) a.a(parcel, readInt, Phone.CREATOR);
                    break;
                case 9:
                    sms = (Sms) a.a(parcel, readInt, Sms.CREATOR);
                    break;
                case 10:
                    wiFi = (WiFi) a.a(parcel, readInt, WiFi.CREATOR);
                    break;
                case 11:
                    urlBookmark = (UrlBookmark) a.a(parcel, readInt, UrlBookmark.CREATOR);
                    break;
                case 12:
                    geoPoint = (GeoPoint) a.a(parcel, readInt, GeoPoint.CREATOR);
                    break;
                case 13:
                    calendarEvent = (CalendarEvent) a.a(parcel, readInt, CalendarEvent.CREATOR);
                    break;
                case 14:
                    contactInfo = (ContactInfo) a.a(parcel, readInt, ContactInfo.CREATOR);
                    break;
                case 15:
                    driverLicense = (DriverLicense) a.a(parcel, readInt, DriverLicense.CREATOR);
                    break;
                default:
                    a.b(parcel, readInt);
                    break;
            }
        }
        a.B(parcel, a);
        return new Barcode(i, str, str2, i2, pointArr, email, phone, sms, wiFi, urlBookmark, geoPoint, calendarEvent, contactInfo, driverLicense);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode[i];
    }
}
