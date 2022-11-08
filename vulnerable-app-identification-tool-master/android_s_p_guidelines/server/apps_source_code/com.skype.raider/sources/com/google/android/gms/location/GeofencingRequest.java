package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "GeofencingRequestCreator")
@Reserved({1000})
public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Creator<GeofencingRequest> CREATOR = new ab();
    @Field(getter = "getParcelableGeofences", id = 1)
    private final List<zzbh> a;
    @Field(getter = "getInitialTrigger", id = 2)
    private final int b;
    @Field(defaultValue = "", getter = "getTag", id = 3)
    private final String c;

    public static final class a {
        private final List<zzbh> a = new ArrayList();
        private int b = 5;
        private String c = "";

        public final a a() {
            this.b = 0;
            return this;
        }

        public final a a(List<c> list) {
            if (!list.isEmpty()) {
                for (Object obj : list) {
                    if (obj != null) {
                        ab.a(obj, (Object) "geofence can't be null.");
                        ab.b(obj instanceof zzbh, "Geofence must be created using Geofence.Builder.");
                        this.a.add((zzbh) obj);
                    }
                }
            }
            return this;
        }

        public final GeofencingRequest b() {
            ab.b(!this.a.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(this.a, this.b, this.c);
        }
    }

    @Constructor
    GeofencingRequest(@Param(id = 1) List<zzbh> list, @Param(id = 2) int i, @Param(id = 3) String str) {
        this.a = list;
        this.b = i;
        this.c = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GeofencingRequest[");
        stringBuilder.append("geofences=");
        stringBuilder.append(this.a);
        stringBuilder.append(", initialTrigger=" + this.b + ", ");
        String str = "tag=";
        String valueOf = String.valueOf(this.c);
        stringBuilder.append(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.b(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, a);
    }
}
