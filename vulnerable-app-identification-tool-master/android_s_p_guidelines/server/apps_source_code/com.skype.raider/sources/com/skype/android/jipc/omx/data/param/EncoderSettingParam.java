package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.data.embedded.EncoderSetting;

public class EncoderSettingParam extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final EncoderSetting h = new EncoderSetting(this);

    public EncoderSettingParam(ExtendedIndex extendedIndex) {
        super(extendedIndex.a("OMX.microsoft.skype.index.encodersetting"), 14);
    }
}
