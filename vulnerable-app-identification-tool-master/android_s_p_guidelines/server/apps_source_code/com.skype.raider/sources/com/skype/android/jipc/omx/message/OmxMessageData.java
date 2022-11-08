package com.skype.android.jipc.omx.message;

import com.skype.android.jipc.Struct;
import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.Struct.LongField;

public class OmxMessageData extends Struct {
    public final IntField d = new IntField(this, 0);
    public final IntField e = new IntField(this, 1);
    public final IntField f = new IntField(this, 2);
    public final IntField g = new IntField(this, 0);
    public final IntField h = new IntField(this, 1);
    public final IntField i = new IntField(this, 2);
    public final IntField j = new IntField(this, 3);
    public final LongField k = new LongField(this, 4);
    public final IntField l = new IntField(this, 6);
    public final IntField m = new IntField(this, 7);
    public final LongField n = new LongField(this, 0);
    public final LongField o = new LongField(this, 2);

    public OmxMessageData() {
        super(OmxMessageType.a() >> 2);
    }
}
