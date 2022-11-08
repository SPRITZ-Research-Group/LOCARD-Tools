package com.skype.android.jipc.omx.data.param;

import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.data.embedded.PortDefinition;
import com.skype.android.jipc.omx.enums.OmxIndex.Port;

public class PortDefinitionParam extends OmxStruct {
    public final IntField g;
    public final IntField h;
    public final IntField i;
    public final IntField j;
    public final IntField k;
    public final IntField l;
    public final IntField m;
    public final IntField n;
    public final PortDefinition o;
    public final IntField p;
    public final IntField q;
    private int r = 2;

    public PortDefinitionParam() {
        super(Port.OMX_IndexParamPortDefinition, 24);
        int i = this.r;
        this.r = i + 1;
        this.g = new IntField(this, i);
        i = this.r;
        this.r = i + 1;
        this.h = new IntField(this, i);
        i = this.r;
        this.r = i + 1;
        this.i = new IntField(this, i);
        i = this.r;
        this.r = i + 1;
        this.j = new IntField(this, i);
        i = this.r;
        this.r = i + 1;
        this.k = new IntField(this, i);
        i = this.r;
        this.r = i + 1;
        this.l = new IntField(this, i);
        i = this.r;
        this.r = i + 1;
        this.m = new IntField(this, i);
        i = this.r;
        this.r = i + 1;
        this.n = new IntField(this, i);
        this.o = new PortDefinition(this, this.r);
        this.p = new IntField(this, this.o.k);
        this.q = new IntField(this, this.o.k + 1);
    }
}
