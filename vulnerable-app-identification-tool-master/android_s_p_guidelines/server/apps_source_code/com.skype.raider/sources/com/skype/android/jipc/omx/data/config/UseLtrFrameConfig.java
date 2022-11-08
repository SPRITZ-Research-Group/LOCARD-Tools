package com.skype.android.jipc.omx.data.config;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.Struct.ShortField;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;

public class UseLtrFrameConfig extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final ShortField h = new ShortField(this);

    public UseLtrFrameConfig(ExtendedIndex qpConfigExtendedIndex) {
        super(qpConfigExtendedIndex.a("OMX.microsoft.skype.index.useltrframe"), 4);
    }

    public final int a() {
        return super.a() - 2;
    }
}
