package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.data.embedded.DecoderCap;

public class DecoderCapParam extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final DecoderCap h = new DecoderCap(this);

    public DecoderCapParam(ExtendedIndex index) {
        super(true, index.a("OMX.microsoft.skype.index.decodercapability"), 9);
    }
}
