package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Creator<BinderWrapper> CREATOR = new aj();
    private IBinder a;

    public BinderWrapper() {
        this.a = null;
    }

    private BinderWrapper(Parcel parcel) {
        this.a = null;
        this.a = parcel.readStrongBinder();
    }

    /* synthetic */ BinderWrapper(Parcel parcel, byte b) {
        this(parcel);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.a);
    }
}
