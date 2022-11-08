package com.skype.android.jipc.omx.data;

import android.os.IBinder;
import com.skype.android.jipc.inout.OutInt32;
import com.skype.android.jipc.inout.OutMayBe;
import com.skype.android.jipc.inout.OutStatus;
import com.skype.android.jipc.omx.OmxFacade;
import com.skype.android.jipc.omx.enums.OmxIndex;
import com.skype.android.jipc.omx.enums.OmxIndex.Component;

public class ExtendedIndex implements OmxIndex {
    public static final int a = Component.OMX_IndexComponentStartUnused.a();
    private final String b;
    private final int c = 2;
    private final int d = 0;
    private int e = a;
    private int f = -1;

    public ExtendedIndex(String name) {
        this.b = name;
    }

    public final void a(OmxFacade facade, IBinder omx, int nodeId) {
        if (this.f != nodeId) {
            int b;
            OutMayBe a = facade.a(omx, nodeId, this.b);
            if (OutStatus.c(a.a.b())) {
                b = ((OutInt32) a.b).b();
            } else {
                b = a;
            }
            this.e = b;
            this.f = nodeId;
        }
    }

    public final int b() {
        return this.f;
    }

    public final boolean e() {
        return this.e != a;
    }

    public final int a() {
        return this.e;
    }

    public final OmxIndex a(String extName) {
        if (this.b.equals(extName)) {
            return this;
        }
        throw new IllegalArgumentException("Can't use index of " + this.b + " to access " + extName);
    }

    public final int x_() {
        return this.c;
    }

    public final int d() {
        return this.d;
    }
}
