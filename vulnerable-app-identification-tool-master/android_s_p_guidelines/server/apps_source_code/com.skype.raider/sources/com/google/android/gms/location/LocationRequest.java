package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;

@Class(creator = "LocationRequestCreator")
@Reserved({1000})
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<LocationRequest> CREATOR = new j();
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_PRIORITY", id = 1)
    private int a;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_INTERVAL", id = 2)
    private long b;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_FASTEST_INTERVAL", id = 3)
    private long c;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPLICIT_FASTEST_INTERVAL", id = 4)
    private boolean d;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPIRE_AT", id = 5)
    private long e;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_NUM_UPDATES", id = 6)
    private int f;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_SMALLEST_DISPLACEMENT", id = 7)
    private float g;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_MAX_WAIT_TIME", id = 8)
    private long h;

    public LocationRequest() {
        this.a = 102;
        this.b = 3600000;
        this.c = 600000;
        this.d = false;
        this.e = Long.MAX_VALUE;
        this.f = Integer.MAX_VALUE;
        this.g = 0.0f;
        this.h = 0;
    }

    @Constructor
    LocationRequest(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 3) long j2, @Param(id = 4) boolean z, @Param(id = 5) long j3, @Param(id = 6) int i2, @Param(id = 7) float f, @Param(id = 8) long j4) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = z;
        this.e = j3;
        this.f = i2;
        this.g = f;
        this.h = j4;
    }

    @VisibleForTesting
    public static LocationRequest a() {
        return new LocationRequest();
    }

    private long d() {
        long j = this.h;
        return j < this.b ? this.b : j;
    }

    @VisibleForTesting
    public final LocationRequest b() {
        this.a = 100;
        return this;
    }

    @VisibleForTesting
    public final LocationRequest c() {
        this.f = 1;
        return this;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        return this.a == locationRequest.a && this.b == locationRequest.b && this.c == locationRequest.c && this.d == locationRequest.d && this.e == locationRequest.e && this.f == locationRequest.f && this.g == locationRequest.g && d() == locationRequest.d();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.a), Long.valueOf(this.b), Float.valueOf(this.g), Long.valueOf(this.h)});
    }

    public final String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append("Request[");
        switch (this.a) {
            case 100:
                str = "PRIORITY_HIGH_ACCURACY";
                break;
            case 102:
                str = "PRIORITY_BALANCED_POWER_ACCURACY";
                break;
            case 104:
                str = "PRIORITY_LOW_POWER";
                break;
            case 105:
                str = "PRIORITY_NO_POWER";
                break;
            default:
                str = "???";
                break;
        }
        append.append(str);
        if (this.a != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.b).append("ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.c).append("ms");
        if (this.h > this.b) {
            stringBuilder.append(" maxWait=");
            stringBuilder.append(this.h).append("ms");
        }
        if (this.g > 0.0f) {
            stringBuilder.append(" smallestDisplacement=");
            stringBuilder.append(this.g).append("m");
        }
        if (this.e != Long.MAX_VALUE) {
            long elapsedRealtime = this.e - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime).append("ms");
        }
        if (this.f != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.f);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.e);
        b.a(parcel, 6, this.f);
        b.a(parcel, 7, this.g);
        b.a(parcel, 8, this.h);
        b.a(parcel, a);
    }
}
