package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.Arrays;
import java.util.Comparator;

@Class(creator = "DetectedActivityCreator")
@Reserved({1000})
public class DetectedActivity extends AbstractSafeParcelable {
    public static final Creator<DetectedActivity> CREATOR = new z();
    private static final Comparator<DetectedActivity> a = new y();
    private static final int[] b = new int[]{9, 10};
    private static final int[] c = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17, 18, 19};
    private static final int[] d = new int[]{0, 1, 2, 3, 7, 8, 16, 17};
    @Field(id = 1)
    private int e;
    @Field(id = 2)
    private int f;

    @Constructor
    public DetectedActivity(@Param(id = 1) int i, @Param(id = 2) int i2) {
        this.e = i;
        this.f = i2;
    }

    public static void a(int i) {
        Object obj = null;
        for (int i2 : d) {
            if (i2 == i) {
                obj = 1;
            }
        }
        if (obj == null) {
            new StringBuilder(81).append(i).append(" is not a valid DetectedActivity supported by Activity Transition API.");
        }
    }

    public final int a() {
        int i = this.e;
        return (i > 19 || i < 0) ? 4 : i;
    }

    public final int b() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.e == detectedActivity.e && this.f == detectedActivity.f;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.f)});
    }

    public String toString() {
        String str;
        int a = a();
        switch (a) {
            case 0:
                str = "IN_VEHICLE";
                break;
            case 1:
                str = "ON_BICYCLE";
                break;
            case 2:
                str = "ON_FOOT";
                break;
            case 3:
                str = "STILL";
                break;
            case 4:
                str = "UNKNOWN";
                break;
            case 5:
                str = "TILTING";
                break;
            case 7:
                str = "WALKING";
                break;
            case 8:
                str = "RUNNING";
                break;
            case 16:
                str = "IN_ROAD_VEHICLE";
                break;
            case 17:
                str = "IN_RAIL_VEHICLE";
                break;
            case 18:
                str = "IN_TWO_WHEELER_VEHICLE";
                break;
            case 19:
                str = "IN_FOUR_WHEELER_VEHICLE";
                break;
            default:
                str = Integer.toString(a);
                break;
        }
        return new StringBuilder(String.valueOf(str).length() + 48).append("DetectedActivity [type=").append(str).append(", confidence=").append(this.f).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.e);
        b.a(parcel, 2, this.f);
        b.a(parcel, a);
    }
}
