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

@Class(creator = "ActivityTransitionEventCreator")
@Reserved({1000})
public class ActivityTransitionEvent extends AbstractSafeParcelable {
    public static final Creator<ActivityTransitionEvent> CREATOR = new u();
    @Field(getter = "getActivityType", id = 1)
    private final int a;
    @Field(getter = "getTransitionType", id = 2)
    private final int b;
    @Field(getter = "getElapsedRealTimeNanos", id = 3)
    private final long c;

    @Constructor
    public ActivityTransitionEvent(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) long j) {
        DetectedActivity.a(i);
        ActivityTransition.a(i2);
        this.a = i;
        this.b = i2;
        this.c = j;
    }

    public final long a() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityTransitionEvent)) {
            return false;
        }
        ActivityTransitionEvent activityTransitionEvent = (ActivityTransitionEvent) obj;
        return this.a == activityTransitionEvent.a && this.b == activityTransitionEvent.b && this.c == activityTransitionEvent.c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Long.valueOf(this.c)});
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ActivityType " + this.a);
        stringBuilder.append(" ");
        stringBuilder.append("TransitionType " + this.b);
        stringBuilder.append(" ");
        stringBuilder.append("ElapsedRealTimeNanos " + this.c);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.a(parcel, 3, this.c);
        b.a(parcel, a);
    }
}
