package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.b.d;
import com.google.android.gms.common.internal.s.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.Nullable;

@Class(creator = "GoogleCertificatesQueryCreator")
public class GoogleCertificatesQuery extends AbstractSafeParcelable {
    public static final Creator<GoogleCertificatesQuery> CREATOR = new g();
    @Field(getter = "getCallingPackage", id = 1)
    private final String a;
    @Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
    @Nullable
    private final a b;
    @Field(getter = "getAllowTestKeys", id = 3)
    private final boolean c;

    @Constructor
    GoogleCertificatesQuery(@Param(id = 1) String str, @Param(id = 2) @Nullable IBinder iBinder, @Param(id = 3) boolean z) {
        this.a = str;
        this.b = a(iBinder);
        this.c = z;
    }

    GoogleCertificatesQuery(String str, @Nullable a aVar, boolean z) {
        this.a = str;
        this.b = aVar;
        this.c = z;
    }

    @Nullable
    private static a a(@Nullable IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        try {
            b b = a.a(iBinder).b();
            byte[] bArr = b == null ? null : (byte[]) d.a(b);
            return bArr != null ? new p(bArr) : null;
        } catch (RemoteException e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.b.a(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, this.a);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, this.b == null ? null : this.b.asBinder());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, this.c);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, a);
    }
}
