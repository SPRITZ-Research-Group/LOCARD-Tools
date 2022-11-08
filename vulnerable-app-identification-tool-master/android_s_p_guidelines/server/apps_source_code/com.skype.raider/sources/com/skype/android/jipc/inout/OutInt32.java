package com.skype.android.jipc.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.Out;

public class OutInt32 implements Out<Void> {
    private int a;

    public final /* synthetic */ Object c(Parcel parcel) {
        return a(parcel);
    }

    public final int b() {
        return this.a;
    }

    public final void a(int defaultValue) {
        this.a = defaultValue;
    }

    public final Void a(Parcel reply) {
        this.a = reply.readInt();
        return null;
    }

    public String toString() {
        return b(this.a);
    }

    public static String b(int value) {
        return String.format("%d 0x%08x", new Object[]{Integer.valueOf(value), Integer.valueOf(value)});
    }
}
