package com.skype.android.jipc.omx.message;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;

public class OmxMessage {
    private int a;
    private ParcelFileDescriptor b;
    private OmxMessageType c;
    private OmxMessageData d = new OmxMessageData();

    public final void a(int nodeId, boolean hasFence, Parcel data) {
        this.a = nodeId;
        this.b = hasFence ? data.readFileDescriptor() : null;
        this.c = OmxMessageType.values()[data.readInt()];
        this.d.b(data);
    }
}
