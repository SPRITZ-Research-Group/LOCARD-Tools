package com.skype.android.jipc.omx.inout;

import android.os.IBinder;
import android.os.Parcel;
import com.skype.android.jipc.Transactor;
import com.skype.android.jipc.inout.InHeader;

public class InNewNode extends InHeader {
    private String a;
    private IBinder b;

    public InNewNode() {
        super("android.hardware.IOMX");
    }

    public final void a(String codecName, IBinder observer) {
        this.a = codecName;
        this.b = observer;
    }

    public final void a(Parcel in) {
        super.a(in);
        Transactor.a(in, this.a);
        in.writeStrongBinder(this.b);
    }

    public final void a() {
        this.b = null;
    }
}
