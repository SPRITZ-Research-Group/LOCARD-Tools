package com.skype.android.jipc.omx.data.config;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;

public class TemporalLayerCountConfig extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final IntField h = new IntField(this, 3);

    public TemporalLayerCountConfig(ExtendedIndex qpConfigExtendedIndex) {
        super(qpConfigExtendedIndex.a("OMX.microsoft.skype.index.temporallayercount"), 4);
    }
}
