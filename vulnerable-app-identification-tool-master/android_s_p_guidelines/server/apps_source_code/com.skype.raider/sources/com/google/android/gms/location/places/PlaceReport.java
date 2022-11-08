package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
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
import com.skype.camera.imagefilter.ImageFilterManager;
import java.util.Arrays;

@Class(creator = "PlaceReportCreator")
public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new a();
    @VersionField(id = 1)
    private final int a;
    @Field(getter = "getPlaceId", id = 2)
    private final String b;
    @Field(getter = "getTag", id = 3)
    private final String c;
    @Field(getter = "getSource", id = 4)
    private final String d;

    @Constructor
    PlaceReport(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return z.a(this.b, placeReport.b) && z.a(this.c, placeReport.c) && z.a(this.d, placeReport.d);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.c, this.d});
    }

    public String toString() {
        a a = z.a(this);
        a.a("placeId", this.b);
        a.a("tag", this.c);
        if (!"unknown".equals(this.d)) {
            a.a(ImageFilterManager.PROP_SOURCE, this.d);
        }
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, 4, this.d);
        b.a(parcel, a);
    }
}
