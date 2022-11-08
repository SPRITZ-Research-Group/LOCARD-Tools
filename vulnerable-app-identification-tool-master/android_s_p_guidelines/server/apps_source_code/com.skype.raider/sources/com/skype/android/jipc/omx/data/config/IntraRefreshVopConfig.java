package com.skype.android.jipc.omx.data.config;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.enums.OmxIndex.Video;

public class IntraRefreshVopConfig extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final IntField h = new IntField(this, 3);

    public IntraRefreshVopConfig() {
        super(Video.OMX_IndexConfigVideoIntraVOPRefresh, 4);
    }
}
