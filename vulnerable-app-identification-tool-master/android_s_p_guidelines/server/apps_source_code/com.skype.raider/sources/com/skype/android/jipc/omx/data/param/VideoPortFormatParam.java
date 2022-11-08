package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.enums.OmxIndex.Video;

public class VideoPortFormatParam extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final IntField h = new IntField(this, 3);
    public final IntField i = new IntField(this, 4);
    public final IntField j = new IntField(this, 5);
    public final IntField k = new IntField(this, 6);

    public VideoPortFormatParam() {
        super(Video.OMX_IndexParamVideoPortFormat, 7);
    }
}
