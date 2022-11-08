package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import android.os.Process;
import com.skype.android.jipc.Transactor.In;

public class InNodePid implements In {
    private final InNode a;

    public InNodePid(InNode inNode) {
        this.a = inNode;
    }

    public final void a(Parcel in) {
        this.a.a(in);
        in.writeInt(Process.myPid());
    }
}
