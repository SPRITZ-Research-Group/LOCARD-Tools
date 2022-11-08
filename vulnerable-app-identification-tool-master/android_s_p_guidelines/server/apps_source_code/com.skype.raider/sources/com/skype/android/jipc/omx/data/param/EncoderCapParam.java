package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.data.embedded.EncoderCap;

public class EncoderCapParam extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final EncoderCap h = new EncoderCap(this);

    public EncoderCapParam(ExtendedIndex index) {
        super(true, index.a("OMX.microsoft.skype.index.encodercapability"), 15);
    }
}
