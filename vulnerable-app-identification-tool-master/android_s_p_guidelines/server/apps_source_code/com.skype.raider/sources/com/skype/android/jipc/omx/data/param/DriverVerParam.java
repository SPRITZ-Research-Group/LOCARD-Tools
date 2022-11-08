package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.Struct.LongField;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;

public class DriverVerParam extends OmxStruct {
    public final IntField g = new IntField(this, 2);
    public final LongField h = new LongField(this, 3);

    public DriverVerParam(ExtendedIndex extendedIndex) {
        super(extendedIndex.a("OMX.microsoft.skype.index.driverversion"), 5);
    }
}
