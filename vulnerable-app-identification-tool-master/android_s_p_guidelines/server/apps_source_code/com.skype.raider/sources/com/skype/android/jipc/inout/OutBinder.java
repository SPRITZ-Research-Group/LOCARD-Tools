package com.skype.android.jipc.inout;

import android.os.IBinder;
import android.os.Parcel;
import com.skype.android.jipc.Transactor.Out;

public class OutBinder implements Out<IBinder> {
    public final /* synthetic */ Object c(Parcel parcel) {
        return parcel.readStrongBinder();
    }
}
