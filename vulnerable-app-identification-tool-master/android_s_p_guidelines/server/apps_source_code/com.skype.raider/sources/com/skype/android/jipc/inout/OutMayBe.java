package com.skype.android.jipc.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.Out;

public class OutMayBe<T, O extends Out<T>> implements Out<Void> {
    public final OutStatus a;
    public final O b;

    public OutMayBe(OutStatus status, O value) {
        this.a = status;
        this.b = value;
    }

    public String toString() {
        return OutStatus.c(this.a.b()) ? this.b.toString() : this.a.toString();
    }

    public final /* synthetic */ Object c(Parcel parcel) {
        this.a.a(parcel);
        if (OutStatus.c(this.a.b())) {
            this.b.c(parcel);
        }
        return null;
    }
}
