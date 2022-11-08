package com.skype.android.jipc.omx.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.Out;
import com.skype.android.jipc.omx.data.OmxStruct;

public class OutParam implements Out<Void> {
    private OmxStruct a;

    public final void a(OmxStruct struct) {
        this.a = struct;
    }

    public final /* synthetic */ Object c(Parcel parcel) {
        return this.a.b(parcel);
    }
}
