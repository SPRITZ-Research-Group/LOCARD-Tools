package com.skype.android.jipc.inout;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.In;

public class InHeader implements In {
    private String a;

    public InHeader(String interfaceName) {
        this.a = interfaceName;
    }

    public void a(Parcel callArgs) {
        callArgs.writeInterfaceToken(this.a);
    }
}
