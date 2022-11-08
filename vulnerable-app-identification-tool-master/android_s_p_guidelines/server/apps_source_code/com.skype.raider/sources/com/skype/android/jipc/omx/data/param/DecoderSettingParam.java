package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;

public class DecoderSettingParam extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final IntField h = new IntField(this, 3);

    public DecoderSettingParam(ExtendedIndex extendedIndex) {
        super(extendedIndex.a("OMX.microsoft.skype.index.decodersetting"), 4);
    }
}
