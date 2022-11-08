package com.skype.android.jipc.omx.data.embedded;

import com.skype.android.jipc.Struct;
import com.skype.android.jipc.Struct.IntField;
import java.util.IllegalFormatWidthException;

public class EncoderCap {
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
    public final IntField l;
    public final int m = 3;
    public final int n;

    public EncoderCap(Struct owner) {
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
        this.l = new IntField(owner, 14);
        this.n = 15;
        if (12 != this.n - this.m) {
            throw new IllegalFormatWidthException(this.n - this.m);
        }
    }
}
