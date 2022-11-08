package com.skype.android.jipc.omx.data.embedded;

import com.skype.android.jipc.Struct;
import com.skype.android.jipc.Struct.IntField;
import java.util.IllegalFormatWidthException;

public class PortDefinition {
    public final IntField a;
    public final IntField b;
    public final IntField c;
    public final IntField d;
    public final IntField e;
    public final IntField f;
    public final IntField g;
    public final IntField h;
    public final IntField i;
    public final int j;
    public final int k;

    public PortDefinition(Struct owner, int base) {
        this.j = base;
        base = (base + 1) + 1;
        int base2 = base + 1;
        this.a = new IntField(owner, base);
        base = base2 + 1;
        this.b = new IntField(owner, base2);
        base2 = base + 1;
        this.c = new IntField(owner, base);
        base = base2 + 1;
        this.d = new IntField(owner, base2);
        base2 = base + 1;
        this.e = new IntField(owner, base);
        base = base2 + 1;
        this.f = new IntField(owner, base2);
        base2 = base + 1;
        this.g = new IntField(owner, base);
        base = base2 + 1;
        this.h = new IntField(owner, base2);
        base2 = base + 1;
        this.i = new IntField(owner, base);
        this.k = base2 + 1;
        if (12 != this.k - this.j) {
            throw new IllegalFormatWidthException(this.k - this.j);
        }
    }
}
