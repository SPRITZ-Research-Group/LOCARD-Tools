package com.skype.android.jipc.omx.data.config;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.enums.OmxIndex.Video;

public class BitrateConfig extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final IntField h = new IntField(this, 3);

    public BitrateConfig() {
        super(Video.OMX_IndexConfigVideoBitrate, 4);
    }
}
