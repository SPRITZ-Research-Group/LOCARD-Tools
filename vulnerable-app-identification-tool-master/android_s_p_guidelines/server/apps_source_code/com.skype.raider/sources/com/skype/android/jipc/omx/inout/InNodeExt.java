package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor;
import com.skype.android.jipc.Transactor.In;

public class InNodeExt implements In {
    private final InNode a;
    private String b;

    public InNodeExt(InNode header) {
        this.a = header;
    }

    public final void a(int nodeId, String parameterName) {
        this.a.a(nodeId);
        this.b = parameterName;
    }

    public final void a(Parcel in) {
        this.a.a(in);
        Transactor.a(in, this.b);
    }
}
