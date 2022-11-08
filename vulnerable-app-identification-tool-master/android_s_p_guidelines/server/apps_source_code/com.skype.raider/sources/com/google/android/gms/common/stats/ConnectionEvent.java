package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "ConnectionEventCreator")
public final class ConnectionEvent extends StatsEvent {
    public static final Creator<ConnectionEvent> CREATOR = new a();
    @VersionField(id = 1)
    private final int a;
    @Field(getter = "getTimeMillis", id = 2)
    private final long b;
    @Field(getter = "getEventType", id = 12)
    private int c;
    @Field(getter = "getCallingProcess", id = 4)
    private final String d;
    @Field(getter = "getCallingService", id = 5)
    private final String e;
    @Field(getter = "getTargetProcess", id = 6)
    private final String f;
    @Field(getter = "getTargetService", id = 7)
    private final String g;
    @Field(getter = "getStackTrace", id = 8)
    private final String h;
    @Field(getter = "getEventKey", id = 13)
    private final String i;
    @Field(getter = "getElapsedRealtime", id = 10)
    private final long j;
    @Field(getter = "getHeapAlloc", id = 11)
    private final long k;
    private long l = -1;

    @Constructor
    ConnectionEvent(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 12) int i2, @Param(id = 4) String str, @Param(id = 5) String str2, @Param(id = 6) String str3, @Param(id = 7) String str4, @Param(id = 8) String str5, @Param(id = 13) String str6, @Param(id = 10) long j2, @Param(id = 11) long j3) {
        this.a = i;
        this.b = j;
        this.c = i2;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = str5;
        this.i = str6;
        this.j = j2;
        this.k = j3;
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
        return this.l;
    }

    public final long e() {
        return this.j;
    }

    public final long f() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, a());
        b.a(parcel, 4, this.d);
        b.a(parcel, 5, this.e);
        b.a(parcel, 6, this.f);
        b.a(parcel, 7, this.g);
        b.a(parcel, 8, this.h);
        b.a(parcel, 10, e());
        b.a(parcel, 11, this.k);
        b.a(parcel, 12, b());
        b.a(parcel, 13, c());
        b.a(parcel, a);
    }

    public final String g() {
        String str = this.d;
        String str2 = this.e;
        String str3 = this.f;
        String str4 = this.g;
        String str5 = this.h == null ? "" : this.h;
        return new StringBuilder(((((String.valueOf(str).length() + 26) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length()) + String.valueOf(str5).length()).append("\t").append(str).append("/").append(str2).append("\t").append(str3).append("/").append(str4).append("\t").append(str5).append("\t").append(this.k).toString();
    }
}
