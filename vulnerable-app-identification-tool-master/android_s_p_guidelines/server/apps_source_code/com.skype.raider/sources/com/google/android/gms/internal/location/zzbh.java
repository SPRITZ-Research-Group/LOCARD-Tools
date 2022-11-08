package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.c;
import java.util.Locale;

@Class(creator = "ParcelableGeofenceCreator")
@Reserved({1000})
@VisibleForTesting
public final class zzbh extends AbstractSafeParcelable implements c {
    public static final Creator<zzbh> CREATOR = new ac();
    @Field(getter = "getRequestId", id = 1)
    private final String a;
    @Field(getter = "getExpirationTime", id = 2)
    private final long b;
    @Field(getter = "getType", id = 3)
    private final short c;
    @Field(getter = "getLatitude", id = 4)
    private final double d;
    @Field(getter = "getLongitude", id = 5)
    private final double e;
    @Field(getter = "getRadius", id = 6)
    private final float f;
    @Field(getter = "getTransitionTypes", id = 7)
    private final int g;
    @Field(defaultValue = "0", getter = "getNotificationResponsiveness", id = 8)
    private final int h;
    @Field(defaultValue = "-1", getter = "getLoiteringDelay", id = 9)
    private final int i;

    @Constructor
    public zzbh(@Param(id = 1) String str, @Param(id = 7) int i, @Param(id = 3) short s, @Param(id = 4) double d, @Param(id = 5) double d2, @Param(id = 6) float f, @Param(id = 2) long j, @Param(id = 8) int i2, @Param(id = 9) int i3) {
        if (str == null || str.length() > 100) {
            String str2 = "requestId is null or too long: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        } else if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        } else {
            int i4 = i & 7;
            if (i4 == 0) {
                throw new IllegalArgumentException("No supported transition specified: " + i);
            }
            this.c = s;
            this.a = str;
            this.d = d;
            this.e = d2;
            this.f = f;
            this.b = j;
            this.g = i4;
            this.h = i2;
            this.i = i3;
        }
    }

    public static zzbh a(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzbh zzbh = (zzbh) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return zzbh;
    }

    public final String a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzbh)) {
            return false;
        }
        zzbh zzbh = (zzbh) obj;
        return this.f != zzbh.f ? false : this.d != zzbh.d ? false : this.e != zzbh.e ? false : this.c == zzbh.c;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.d);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.e);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.f)) * 31) + this.c) * 31) + this.g;
    }

    public final String toString() {
        String str;
        Locale locale = Locale.US;
        String str2 = "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]";
        Object[] objArr = new Object[9];
        switch (this.c) {
            case (short) 1:
                str = "CIRCLE";
                break;
            default:
                str = null;
                break;
        }
        objArr[0] = str;
        objArr[1] = this.a.replaceAll("\\p{C}", "?");
        objArr[2] = Integer.valueOf(this.g);
        objArr[3] = Double.valueOf(this.d);
        objArr[4] = Double.valueOf(this.e);
        objArr[5] = Float.valueOf(this.f);
        objArr[6] = Integer.valueOf(this.h / Constants.ONE_SECOND);
        objArr[7] = Integer.valueOf(this.i);
        objArr[8] = Long.valueOf(this.b);
        return String.format(locale, str2, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, this.c);
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.e);
        b.a(parcel, 6, this.f);
        b.a(parcel, 7, this.g);
        b.a(parcel, 8, this.h);
        b.a(parcel, 9, this.i);
        b.a(parcel, a);
    }
}
