package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.b;

@Class(creator = "TextRecognizerOptionsCreator")
@Reserved({1})
public final class zzaa extends AbstractSafeParcelable {
    public static final Creator<zzaa> CREATOR = new b();

    public final void writeToParcel(Parcel parcel, int i) {
        b.a(parcel, b.a(parcel));
    }
}
