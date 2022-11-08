package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.af;
import com.google.android.gms.location.ag;
import com.google.android.gms.location.ai;
import com.google.android.gms.location.aj;

@Class(creator = "LocationRequestUpdateDataCreator")
@Reserved({1000})
public final class zzbf extends AbstractSafeParcelable {
    public static final Creator<zzbf> CREATOR = new ab();
    @Field(defaultValueUnchecked = "LocationRequestUpdateData.OPERATION_ADD", id = 1)
    private int a;
    @Field(defaultValueUnchecked = "null", id = 2)
    private zzbd b;
    @Field(defaultValueUnchecked = "null", getter = "getLocationListenerBinder", id = 3, type = "android.os.IBinder")
    private ai c;
    @Field(defaultValueUnchecked = "null", id = 4)
    private PendingIntent d;
    @Field(defaultValueUnchecked = "null", getter = "getLocationCallbackBinder", id = 5, type = "android.os.IBinder")
    private af e;
    @Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 6, type = "android.os.IBinder")
    private i f;

    @Constructor
    zzbf(@Param(id = 1) int i, @Param(id = 2) zzbd zzbd, @Param(id = 3) IBinder iBinder, @Param(id = 4) PendingIntent pendingIntent, @Param(id = 5) IBinder iBinder2, @Param(id = 6) IBinder iBinder3) {
        i iVar = null;
        this.a = i;
        this.b = zzbd;
        this.c = iBinder == null ? null : aj.a(iBinder);
        this.d = pendingIntent;
        this.e = iBinder2 == null ? null : ag.a(iBinder2);
        if (!(iBinder3 == null || iBinder3 == null)) {
            IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            iVar = queryLocalInterface instanceof i ? (i) queryLocalInterface : new k(iBinder3);
        }
        this.f = iVar;
    }

    public static zzbf a(af afVar) {
        return new zzbf(2, null, null, null, afVar.asBinder(), null);
    }

    public static zzbf a(ai aiVar) {
        return new zzbf(2, null, aiVar.asBinder(), null, null, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder = null;
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b, i);
        b.a(parcel, 3, this.c == null ? null : this.c.asBinder());
        b.a(parcel, 4, this.d, i);
        b.a(parcel, 5, this.e == null ? null : this.e.asBinder());
        if (this.f != null) {
            iBinder = this.f.asBinder();
        }
        b.a(parcel, 6, iBinder);
        b.a(parcel, a);
    }
}
