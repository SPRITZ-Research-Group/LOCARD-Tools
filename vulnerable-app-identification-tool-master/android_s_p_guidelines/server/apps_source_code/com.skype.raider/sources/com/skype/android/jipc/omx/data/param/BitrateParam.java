package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.enums.OmxIndex.Video;

public class BitrateParam extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final IntField h = new IntField(this, 3);
    public final IntField i = new IntField(this, 4);

    public BitrateParam() {
        super(Video.OMX_IndexParamVideoBitrate, 5);
    }
}
