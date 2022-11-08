package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.Out;
import com.skype.android.jipc.inout.OutInt32;
import com.skype.android.jipc.inout.OutStatus;

public class OutState<T, O extends Out<T>> implements Out<Void> {
    public final OutInt32 a = new OutInt32();
    public final OutStatus b;

    public OutState(OutStatus status) {
        this.b = status;
    }

    public String toString() {
        return OutStatus.c(this.b.b()) ? this.a.toString() : this.b.toString();
    }

    public final /* synthetic */ Object c(Parcel parcel) {
        this.a.a(parcel);
        this.b.a(parcel);
        return null;
    }
}
