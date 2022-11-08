package com.skype.android.jipc.omx.data;

import android.os.Build.VERSION;
import android.os.Parcel;
import com.skype.android.jipc.Struct;
import com.skype.android.jipc.Struct.IntField;
import com.skype.android.jipc.omx.OmxVersion;
import com.skype.android.jipc.omx.enums.OmxIndex;

public abstract class OmxStruct extends Struct implements OmxVersion {
    static final boolean d = (VERSION.SDK_INT >= 21);
    public final IntField e;
    public final IntField f;
    private final OmxIndex g;

    protected OmxStruct(OmxIndex index, int wordCount) {
        this(false, index, wordCount);
    }

    protected OmxStruct(boolean direct, OmxIndex index, int wordCount) {
        super(direct, wordCount);
        this.e = new IntField(this, 0);
        this.f = new IntField(this, 1);
        this.g = index;
        this.c.put(0, a());
        f();
    }

    public final void b() {
        super.b();
        this.c.put(0, a());
        f();
    }

    public final void a(Parcel in) {
        in.writeInt(this.g.a());
        if (d) {
            in.writeLong((long) a());
        } else {
            in.writeInt(a());
        }
        super.a(in);
    }

    public final OmxIndex e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof OmxStruct) && ((OmxStruct) obj).g.a() == this.g.a() && super.equals(obj));
    }

    public final void f() {
        a(this.g.x_(), this.g.d());
    }

    public final void a(int major, int minor) {
        this.c.put(1, 0);
        this.a.put(4, (byte) major);
        this.a.put(5, (byte) minor);
    }

    public final int x_() {
        return this.a.get(4);
    }

    public final int d() {
        return this.a.get(5);
    }
}
