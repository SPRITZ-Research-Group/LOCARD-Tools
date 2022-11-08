package com.skype.android.jipc.omx.data.embedded;

import com.skype.android.jipc.Struct;
import com.skype.android.jipc.Struct.IntField;
import java.util.IllegalFormatWidthException;

public class EncoderSetting {
    public final IntField a;
    public final IntField b;
    public final IntField c;
    public final IntField d;
    public final IntField e;
    public final IntField f;
    public final IntField g;
    public final IntField h;
    public final IntField i;
    public final IntField j;
    public final IntField k;
    public final int l = 3;
    public final int m;

    public EncoderSetting(Struct owner) {
        this.a = new IntField(owner, 3);
        this.b = new IntField(owner, 4);
        this.c = new IntField(owner, 5);
        this.d = new IntField(owner, 6);
        this.e = new IntField(owner, 7);
        this.f = new IntField(owner, 8);
        this.g = new IntField(owner, 9);
        this.h = new IntField(owner, 10);
        this.i = new IntField(owner, 11);
        this.j = new IntField(owner, 12);
        this.k = new IntField(owner, 13);
        this.m = 14;
        if (11 != this.m - this.l) {
            throw new IllegalFormatWidthException(this.m - this.l);
        }
    }
}
