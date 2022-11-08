package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "BarcodeCreator")
@Reserved({1})
public class Barcode extends AbstractSafeParcelable {
    public static final Creator<Barcode> CREATOR = new c();
    @Field(id = 2)
    public int a;
    @Field(id = 3)
    public String b;
    @Field(id = 4)
    public String c;
    @Field(id = 5)
    public int d;
    @Field(id = 6)
    public Point[] e;
    @Field(id = 7)
    public Email f;
    @Field(id = 8)
    public Phone g;
    @Field(id = 9)
    public Sms h;
    @Field(id = 10)
    public WiFi i;
    @Field(id = 11)
    public UrlBookmark j;
    @Field(id = 12)
    public GeoPoint k;
    @Field(id = 13)
    public CalendarEvent l;
    @Field(id = 14)
    public ContactInfo m;
    @Field(id = 15)
    public DriverLicense n;

    @Class(creator = "AddressCreator")
    @Reserved({1})
    public static class Address extends AbstractSafeParcelable {
        public static final Creator<Address> CREATOR = new b();
        @Field(id = 2)
        public int a;
        @Field(id = 3)
        public String[] b;

        @Constructor
        public Address(@Param(id = 2) int i, @Param(id = 3) String[] strArr) {
            this.a = i;
            this.b = strArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, a);
        }
    }

    @Class(creator = "CalendarDateTimeCreator")
    @Reserved({1})
    public static class CalendarDateTime extends AbstractSafeParcelable {
        public static final Creator<CalendarDateTime> CREATOR = new d();
        @Field(id = 2)
        public int a;
        @Field(id = 3)
        public int b;
        @Field(id = 4)
        public int c;
        @Field(id = 5)
        public int d;
        @Field(id = 6)
        public int e;
        @Field(id = 7)
        public int f;
        @Field(id = 8)
        public boolean g;
        @Field(id = 9)
        public String h;

        @Constructor
        public CalendarDateTime(@Param(id = 2) int i, @Param(id = 3) int i2, @Param(id = 4) int i3, @Param(id = 5) int i4, @Param(id = 6) int i5, @Param(id = 7) int i6, @Param(id = 8) boolean z, @Param(id = 9) String str) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = z;
            this.h = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, 5, this.d);
            b.a(parcel, 6, this.e);
            b.a(parcel, 7, this.f);
            b.a(parcel, 8, this.g);
            b.a(parcel, 9, this.h);
            b.a(parcel, a);
        }
    }

    @Class(creator = "CalendarEventCreator")
    @Reserved({1})
    public static class CalendarEvent extends AbstractSafeParcelable {
        public static final Creator<CalendarEvent> CREATOR = new e();
        @Field(id = 2)
        public String a;
        @Field(id = 3)
        public String b;
        @Field(id = 4)
        public String c;
        @Field(id = 5)
        public String d;
        @Field(id = 6)
        public String e;
        @Field(id = 7)
        public CalendarDateTime f;
        @Field(id = 8)
        public CalendarDateTime g;

        @Constructor
        public CalendarEvent(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) CalendarDateTime calendarDateTime, @Param(id = 8) CalendarDateTime calendarDateTime2) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = calendarDateTime;
            this.g = calendarDateTime2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, 5, this.d);
            b.a(parcel, 6, this.e);
            b.a(parcel, 7, this.f, i);
            b.a(parcel, 8, this.g, i);
            b.a(parcel, a);
        }
    }

    @Class(creator = "ContactInfoCreator")
    @Reserved({1})
    public static class ContactInfo extends AbstractSafeParcelable {
        public static final Creator<ContactInfo> CREATOR = new f();
        @Field(id = 2)
        public PersonName a;
        @Field(id = 3)
        public String b;
        @Field(id = 4)
        public String c;
        @Field(id = 5)
        public Phone[] d;
        @Field(id = 6)
        public Email[] e;
        @Field(id = 7)
        public String[] f;
        @Field(id = 8)
        public Address[] g;

        @Constructor
        public ContactInfo(@Param(id = 2) PersonName personName, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) Phone[] phoneArr, @Param(id = 6) Email[] emailArr, @Param(id = 7) String[] strArr, @Param(id = 8) Address[] addressArr) {
            this.a = personName;
            this.b = str;
            this.c = str2;
            this.d = phoneArr;
            this.e = emailArr;
            this.f = strArr;
            this.g = addressArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a, i);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, 5, this.d, i);
            b.a(parcel, 6, this.e, i);
            b.a(parcel, 7, this.f);
            b.a(parcel, 8, this.g, i);
            b.a(parcel, a);
        }
    }

    @Class(creator = "DriverLicenseCreator")
    @Reserved({1})
    public static class DriverLicense extends AbstractSafeParcelable {
        public static final Creator<DriverLicense> CREATOR = new g();
        @Field(id = 2)
        public String a;
        @Field(id = 3)
        public String b;
        @Field(id = 4)
        public String c;
        @Field(id = 5)
        public String d;
        @Field(id = 6)
        public String e;
        @Field(id = 7)
        public String f;
        @Field(id = 8)
        public String g;
        @Field(id = 9)
        public String h;
        @Field(id = 10)
        public String i;
        @Field(id = 11)
        public String j;
        @Field(id = 12)
        public String k;
        @Field(id = 13)
        public String l;
        @Field(id = 14)
        public String m;
        @Field(id = 15)
        public String n;

        @Constructor
        public DriverLicense(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6, @Param(id = 8) String str7, @Param(id = 9) String str8, @Param(id = 10) String str9, @Param(id = 11) String str10, @Param(id = 12) String str11, @Param(id = 13) String str12, @Param(id = 14) String str13, @Param(id = 15) String str14) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
            this.g = str7;
            this.h = str8;
            this.i = str9;
            this.j = str10;
            this.k = str11;
            this.l = str12;
            this.m = str13;
            this.n = str14;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, 5, this.d);
            b.a(parcel, 6, this.e);
            b.a(parcel, 7, this.f);
            b.a(parcel, 8, this.g);
            b.a(parcel, 9, this.h);
            b.a(parcel, 10, this.i);
            b.a(parcel, 11, this.j);
            b.a(parcel, 12, this.k);
            b.a(parcel, 13, this.l);
            b.a(parcel, 14, this.m);
            b.a(parcel, 15, this.n);
            b.a(parcel, a);
        }
    }

    @Class(creator = "EmailCreator")
    @Reserved({1})
    public static class Email extends AbstractSafeParcelable {
        public static final Creator<Email> CREATOR = new h();
        @Field(id = 2)
        public int a;
        @Field(id = 3)
        public String b;
        @Field(id = 4)
        public String c;
        @Field(id = 5)
        public String d;

        @Constructor
        public Email(@Param(id = 2) int i, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) String str3) {
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, 5, this.d);
            b.a(parcel, a);
        }
    }

    @Class(creator = "GeoPointCreator")
    @Reserved({1})
    public static class GeoPoint extends AbstractSafeParcelable {
        public static final Creator<GeoPoint> CREATOR = new i();
        @Field(id = 2)
        public double a;
        @Field(id = 3)
        public double b;

        @Constructor
        public GeoPoint(@Param(id = 2) double d, @Param(id = 3) double d2) {
            this.a = d;
            this.b = d2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, a);
        }
    }

    @Class(creator = "PersonNameCreator")
    @Reserved({1})
    public static class PersonName extends AbstractSafeParcelable {
        public static final Creator<PersonName> CREATOR = new j();
        @Field(id = 2)
        public String a;
        @Field(id = 3)
        public String b;
        @Field(id = 4)
        public String c;
        @Field(id = 5)
        public String d;
        @Field(id = 6)
        public String e;
        @Field(id = 7)
        public String f;
        @Field(id = 8)
        public String g;

        @Constructor
        public PersonName(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6, @Param(id = 8) String str7) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
            this.g = str7;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, 5, this.d);
            b.a(parcel, 6, this.e);
            b.a(parcel, 7, this.f);
            b.a(parcel, 8, this.g);
            b.a(parcel, a);
        }
    }

    @Class(creator = "PhoneCreator")
    @Reserved({1})
    public static class Phone extends AbstractSafeParcelable {
        public static final Creator<Phone> CREATOR = new k();
        @Field(id = 2)
        public int a;
        @Field(id = 3)
        public String b;

        @Constructor
        public Phone(@Param(id = 2) int i, @Param(id = 3) String str) {
            this.a = i;
            this.b = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, a);
        }
    }

    @Class(creator = "SmsCreator")
    @Reserved({1})
    public static class Sms extends AbstractSafeParcelable {
        public static final Creator<Sms> CREATOR = new l();
        @Field(id = 2)
        public String a;
        @Field(id = 3)
        public String b;

        @Constructor
        public Sms(@Param(id = 2) String str, @Param(id = 3) String str2) {
            this.a = str;
            this.b = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, a);
        }
    }

    @Class(creator = "UrlBookmarkCreator")
    @Reserved({1})
    public static class UrlBookmark extends AbstractSafeParcelable {
        public static final Creator<UrlBookmark> CREATOR = new m();
        @Field(id = 2)
        public String a;
        @Field(id = 3)
        public String b;

        @Constructor
        public UrlBookmark(@Param(id = 2) String str, @Param(id = 3) String str2) {
            this.a = str;
            this.b = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, a);
        }
    }

    @Class(creator = "WiFiCreator")
    @Reserved({1})
    public static class WiFi extends AbstractSafeParcelable {
        public static final Creator<WiFi> CREATOR = new n();
        @Field(id = 2)
        public String a;
        @Field(id = 3)
        public String b;
        @Field(id = 4)
        public int c;

        @Constructor
        public WiFi(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int a = b.a(parcel);
            b.a(parcel, 2, this.a);
            b.a(parcel, 3, this.b);
            b.a(parcel, 4, this.c);
            b.a(parcel, a);
        }
    }

    @Constructor
    public Barcode(@Param(id = 2) int i, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) int i2, @Param(id = 6) Point[] pointArr, @Param(id = 7) Email email, @Param(id = 8) Phone phone, @Param(id = 9) Sms sms, @Param(id = 10) WiFi wiFi, @Param(id = 11) UrlBookmark urlBookmark, @Param(id = 12) GeoPoint geoPoint, @Param(id = 13) CalendarEvent calendarEvent, @Param(id = 14) ContactInfo contactInfo, @Param(id = 15) DriverLicense driverLicense) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = i2;
        this.e = pointArr;
        this.f = email;
        this.g = phone;
        this.h = sms;
        this.i = wiFi;
        this.j = urlBookmark;
        this.k = geoPoint;
        this.l = calendarEvent;
        this.m = contactInfo;
        this.n = driverLicense;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 2, this.a);
        b.a(parcel, 3, this.b);
        b.a(parcel, 4, this.c);
        b.a(parcel, 5, this.d);
        b.a(parcel, 6, this.e, i);
        b.a(parcel, 7, this.f, i);
        b.a(parcel, 8, this.g, i);
        b.a(parcel, 9, this.h, i);
        b.a(parcel, 10, this.i, i);
        b.a(parcel, 11, this.j, i);
        b.a(parcel, 12, this.k, i);
        b.a(parcel, 13, this.l, i);
        b.a(parcel, 14, this.m, i);
        b.a(parcel, 15, this.n, i);
        b.a(parcel, a);
    }
}
