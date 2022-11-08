package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.internal.z;
import com.google.android.gms.common.internal.z.a;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;

@KeepForSdk
@Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements i, ReflectedParcelable {
    public static final Creator<Status> CREATOR = new p();
    @KeepForSdk
    @VisibleForTesting
    public static final Status a = new Status(0);
    @KeepForSdk
    public static final Status b = new Status(14);
    @KeepForSdk
    public static final Status c = new Status(8);
    @KeepForSdk
    public static final Status d = new Status(15);
    @KeepForSdk
    public static final Status e = new Status(16);
    @KeepForSdk
    public static final Status f = new Status(18);
    private static final Status g = new Status(17);
    @VersionField(id = 1000)
    private final int h;
    @Field(getter = "getStatusCode", id = 1)
    private final int i;
    @Nullable
    @Field(getter = "getStatusMessage", id = 2)
    private final String j;
    @Nullable
    @Field(getter = "getPendingIntent", id = 3)
    private final PendingIntent k;

    @KeepForSdk
    public Status(int i) {
        this(i, null);
    }

    @Constructor
    @KeepForSdk
    Status(@Param(id = 1000) int i, @Param(id = 1) int i2, @Nullable @Param(id = 2) String str, @Nullable @Param(id = 3) PendingIntent pendingIntent) {
        this.h = i;
        this.i = i2;
        this.j = str;
        this.k = pendingIntent;
    }

    @KeepForSdk
    public Status(int i, @Nullable String str) {
        this(1, i, str, null);
    }

    @KeepForSdk
    public Status(@Nullable String str) {
        this(1, 8, str, null);
    }

    @KeepForSdk
    public final Status a() {
        return this;
    }

    @Nullable
    public final String b() {
        return this.j;
    }

    @VisibleForTesting
    public final boolean c() {
        return this.k != null;
    }

    public final boolean d() {
        return this.i <= 0;
    }

    public final int e() {
        return this.i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.h == status.h && this.i == status.i && z.a(this.j, status.j) && z.a(this.k, status.k);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.h), Integer.valueOf(this.i), this.j, this.k});
    }

    public final String toString() {
        Object obj;
        a a = z.a(this);
        String str = "statusCode";
        if (this.j == null) {
            int i = this.i;
            switch (i) {
                case -1:
                    obj = "SUCCESS_CACHE";
                    break;
                case 0:
                    obj = "SUCCESS";
                    break;
                case 2:
                    obj = "SERVICE_VERSION_UPDATE_REQUIRED";
                    break;
                case 3:
                    obj = "SERVICE_DISABLED";
                    break;
                case 4:
                    obj = "SIGN_IN_REQUIRED";
                    break;
                case 5:
                    obj = "INVALID_ACCOUNT";
                    break;
                case 6:
                    obj = "RESOLUTION_REQUIRED";
                    break;
                case 7:
                    obj = "NETWORK_ERROR";
                    break;
                case 8:
                    obj = "INTERNAL_ERROR";
                    break;
                case 10:
                    obj = "DEVELOPER_ERROR";
                    break;
                case 13:
                    obj = "ERROR";
                    break;
                case 14:
                    obj = "INTERRUPTED";
                    break;
                case 15:
                    obj = "TIMEOUT";
                    break;
                case 16:
                    obj = "CANCELED";
                    break;
                case 17:
                    obj = "API_NOT_CONNECTED";
                    break;
                case 18:
                    obj = "DEAD_CLIENT";
                    break;
                default:
                    obj = "unknown status code: " + i;
                    break;
            }
        }
        obj = this.j;
        return a.a(str, obj).a("resolution", this.k).toString();
    }

    @KeepForSdk
    public final void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.i);
        b.a(parcel, 2, this.j);
        b.a(parcel, 3, this.k, i);
        b.a(parcel, (int) Constants.ONE_SECOND, this.h);
        b.a(parcel, a);
    }
}
