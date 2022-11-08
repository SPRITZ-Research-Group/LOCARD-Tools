package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Collections;
import java.util.List;

@Class(creator = "RemoveGeofencingRequestCreator")
@Reserved({1000})
public final class zzal extends AbstractSafeParcelable {
    public static final Creator<zzal> CREATOR = new r();
    @Field(getter = "getGeofenceIds", id = 1)
    private final List<String> a;
    @Field(getter = "getPendingIntent", id = 2)
    private final PendingIntent b;
    @Field(defaultValue = "", getter = "getTag", id = 3)
    private final String c;

    @Constructor
    zzal(@Nullable @Param(id = 1) List<String> list, @Nullable @Param(id = 2) PendingIntent pendingIntent, @Param(id = 3) String str) {
        this.a = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.b = pendingIntent;
        this.c = str;
    }

    public static zzal a(PendingIntent pendingIntent) {
        ab.a((Object) pendingIntent, (Object) "PendingIntent can not be null.");
        return new zzal(null, pendingIntent, "");
    }

    public static zzal a(List<String> list) {
        ab.a((Object) list, (Object) "geofence can't be null.");
        ab.b(!list.isEmpty(), "Geofences must contains at least one id.");
        return new zzal(list, null, "");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b, i);
        b.a(parcel, 3, this.c);
        b.a(parcel, a);
    }
}
