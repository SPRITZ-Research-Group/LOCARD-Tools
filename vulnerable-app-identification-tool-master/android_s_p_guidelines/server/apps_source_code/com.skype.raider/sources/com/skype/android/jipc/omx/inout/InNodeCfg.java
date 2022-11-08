package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.In;
import com.skype.android.jipc.omx.data.OmxStruct;

public class InNodeCfg implements In {
    private final InNode a;
    private OmxStruct b;

    public InNodeCfg(InNode header) {
        this.a = header;
    }

    public final void a(int nodeId, OmxStruct param) {
        this.a.a(nodeId);
        this.b = param;
    }

    public final void a(Parcel callArgs) {
        this.a.a(callArgs);
        this.b.a(callArgs);
    }
}
