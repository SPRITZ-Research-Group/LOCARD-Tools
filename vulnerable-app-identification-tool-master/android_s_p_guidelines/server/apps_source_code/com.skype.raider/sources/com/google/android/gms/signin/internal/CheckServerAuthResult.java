package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.List;

@Class(creator = "CheckServerAuthResultCreator")
public class CheckServerAuthResult extends AbstractSafeParcelable {
    public static final Creator<CheckServerAuthResult> CREATOR = new c();
    @VersionField(id = 1)
    private final int a;
    @Field(id = 2)
    private final boolean b;
    @Field(id = 3)
    private final List<Scope> c;

    @Constructor
    CheckServerAuthResult(@Param(id = 1) int i, @Param(id = 2) boolean z, @Param(id = 3) List<Scope> list) {
        this.a = i;
        this.b = z;
        this.c = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, this.a);
        b.a(parcel, 2, this.b);
        b.b(parcel, 3, this.c);
        b.a(parcel, a);
    }
}
