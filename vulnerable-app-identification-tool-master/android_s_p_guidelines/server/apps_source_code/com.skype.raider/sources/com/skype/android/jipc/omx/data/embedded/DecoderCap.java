package com.skype.android.jipc.omx.data.embedded;

import com.skype.android.jipc.Struct;
import com.skype.android.jipc.Struct.IntField;
import java.util.IllegalFormatWidthException;

public class DecoderCap {
    final IntField a;
    final IntField b;
    final IntField c;
    final IntField d;
    final IntField e;
    final IntField f;
    public final int g = 3;
    public final int h;

    public DecoderCap(Struct owner) {
        this.a = new IntField(owner, 3);
        this.b = new IntField(owner, 4);
        this.c = new IntField(owner, 5);
        this.d = new IntField(owner, 6);
        this.e = new IntField(owner, 7);
        this.f = new IntField(owner, 8);
        this.h = 9;
        if (6 != this.h - this.g) {
            throw new IllegalFormatWidthException(this.h - this.g);
        }
    }
}
