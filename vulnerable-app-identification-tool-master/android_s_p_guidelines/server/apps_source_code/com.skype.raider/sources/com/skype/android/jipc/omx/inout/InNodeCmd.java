package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.In;
import com.skype.android.jipc.omx.enums.OmxCore.Command;

public class InNodeCmd implements In {
    private final InNode a;
    private Command b;
    private int c;

    public InNodeCmd(InNode inNode) {
        this.a = inNode;
    }

    public final void a(Parcel callArgs) {
        this.a.a(callArgs);
        callArgs.writeInt(this.b.a());
        callArgs.writeInt(this.c);
    }
}
