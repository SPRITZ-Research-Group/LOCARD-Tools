package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.List;

@Class(creator = "WakeLockEventCreator")
public final class WakeLockEvent extends StatsEvent {
    public static final Creator<WakeLockEvent> CREATOR = new c();
    @VersionField(id = 1)
    private final int a;
    @Field(getter = "getTimeMillis", id = 2)
    private final long b;
    @Field(getter = "getEventType", id = 11)
    private int c;
    @Field(getter = "getWakeLockName", id = 4)
    private final String d;
    @Field(getter = "getSecondaryWakeLockName", id = 10)
    private final String e;
    @Field(getter = "getCodePackage", id = 17)
    private final String f;
    @Field(getter = "getWakeLockType", id = 5)
    private final int g;
    @Field(getter = "getCallingPackages", id = 6)
    private final List<String> h;
    @Field(getter = "getEventKey", id = 12)
    private final String i;
    @Field(getter = "getElapsedRealtime", id = 8)
    private final long j;
    @Field(getter = "getDeviceState", id = 14)
    private int k;
    @Field(getter = "getHostPackage", id = 13)
    private final String l;
    @Field(getter = "getBeginPowerPercentage", id = 15)
    private final float m;
    @Field(getter = "getTimeout", id = 16)
    private final long n;
    private long o = -1;

    @Constructor
    WakeLockEvent(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 11) int i2, @Param(id = 4) String str, @Param(id = 5) int i3, @Param(id = 6) List<String> list, @Param(id = 12) String str2, @Param(id = 8) long j2, @Param(id = 14) int i4, @Param(id = 10) String str3, @Param(id = 13) String str4, @Param(id = 15) float f, @Param(id = 16) long j3, @Param(id = 17) String str5) {
        this.a = i;
        this.b = j;
        this.c = i2;
        this.d = str;
        this.e = str3;
        this.f = str5;
        this.g = i3;
        this.h = list;
        this.i = str2;
        this.j = j2;
        this.k = i4;
        this.l = str4;
        this.m = f;
        this.n = j3;
    }

    public final long a() {
        return this.b;
    }

    public final int b() {
        return this.c;
    }

    public final String c() {
        return this.i;
    }

    public final long d() {
        return this.o;
    }

    public final long e() {
        return this.j;
    }

    public final long f() {
        return this.n;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, a());
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.g);
        b.a(parcel, 6, this.h);
        b.a(parcel, 8, e());
        b.a(parcel, 10, this.e);
        b.a(parcel, 11, b());
        b.a(parcel, 12, c());
        b.a(parcel, 13, this.l);
        b.a(parcel, 14, this.k);
        b.a(parcel, 15, this.m);
        b.a(parcel, 16, f());
        b.a(parcel, 17, this.f);
        b.a(parcel, a);
    }

    public final String g() {
        String str;
        String str2;
        String str3;
        String str4 = this.d;
        int i = this.g;
        String join = this.h == null ? "" : TextUtils.join(",", this.h);
        int i2 = this.k;
        if (this.e == null) {
            str = "";
        } else {
            str = this.e;
        }
        if (this.l == null) {
            str2 = "";
        } else {
            str2 = this.l;
        }
        float f = this.m;
        if (this.f == null) {
            str3 = "";
        } else {
            str3 = this.f;
        }
        return new StringBuilder(((((String.valueOf(str4).length() + 45) + String.valueOf(join).length()) + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append("\t").append(str4).append("\t").append(i).append("\t").append(join).append("\t").append(i2).append("\t").append(str).append("\t").append(str2).append("\t").append(f).append("\t").append(str3).toString();
    }
}
