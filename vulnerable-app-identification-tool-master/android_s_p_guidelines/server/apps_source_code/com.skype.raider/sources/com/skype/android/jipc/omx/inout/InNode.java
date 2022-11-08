package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import com.skype.android.jipc.inout.InHeader;

public class InNode extends InHeader {
    private int a;

    public InNode() {
        super("android.hardware.IOMX");
    }

    public final void a(int nodeId) {
        this.a = nodeId;
    }

    public final void a(Parcel callArgs) {
        super.a(callArgs);
        callArgs.writeInt(this.a);
    }
}
