package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.zzj;
import java.util.Collections;
import java.util.List;

@Class(creator = "DeviceOrientationRequestInternalCreator")
public final class zzm extends AbstractSafeParcelable {
    public static final Creator<zzm> CREATOR = new aj();
    @VisibleForTesting
    static final List<ClientIdentity> a = Collections.emptyList();
    static final zzj b = new zzj();
    @Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", id = 1)
    private zzj c;
    @Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_CLIENTS", id = 2)
    private List<ClientIdentity> d;
    @Nullable
    @Field(defaultValueUnchecked = "null", id = 3)
    private String e;

    @Constructor
    zzm(@Param(id = 1) zzj zzj, @Param(id = 2) List<ClientIdentity> list, @Param(id = 3) String str) {
        this.c = zzj;
        this.d = list;
        this.e = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm zzm = (zzm) obj;
        return z.a(this.c, zzm.c) && z.a(this.d, zzm.d) && z.a(this.e, zzm.e);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.c, i);
        b.b(parcel, 2, this.d);
        b.a(parcel, 3, this.e);
        b.a(parcel, a);
    }
}
