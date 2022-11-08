package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.ac;
import com.google.android.gms.location.ad;

@Class(creator = "DeviceOrientationRequestUpdateDataCreator")
public final class zzo extends AbstractSafeParcelable {
    public static final Creator<zzo> CREATOR = new ak();
    @Field(defaultValueUnchecked = "DeviceOrientationRequestUpdateData.OPERATION_ADD", id = 1)
    private int a;
    @Field(defaultValueUnchecked = "null", id = 2)
    private zzm b;
    @Field(defaultValueUnchecked = "null", getter = "getDeviceOrientationListenerBinder", id = 3, type = "android.os.IBinder")
    private ac c;
    @Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 4, type = "android.os.IBinder")
    private i d;

    @Constructor
    zzo(@Param(id = 1) int i, @Param(id = 2) zzm zzm, @Param(id = 3) IBinder iBinder, @Param(id = 4) IBinder iBinder2) {
        i iVar = null;
        this.a = i;
        this.b = zzm;
        this.c = iBinder == null ? null : ad.a(iBinder);
        if (!(iBinder2 == null || iBinder2 == null)) {
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            iVar = queryLocalInterface instanceof i ? (i) queryLocalInterface : new k(iBinder2);
        }
        this.d = iVar;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder = null;
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b, i);
        b.a(parcel, 3, this.c == null ? null : this.c.asBinder());
        if (this.d != null) {
            iBinder = this.d.asBinder();
        }
        b.a(parcel, 4, iBinder);
        b.a(parcel, a);
    }
}
