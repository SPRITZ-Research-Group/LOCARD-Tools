package com.skype.android.jipc.omx;

import android.os.IBinder;
import com.skype.android.jipc.Locator;
import com.skype.android.jipc.Transactor;
import com.skype.android.jipc.Transactor.Out;
import com.skype.android.jipc.Transactor.What;
import com.skype.android.jipc.inout.InHeader;
import com.skype.android.jipc.inout.OutBinder;
import com.skype.android.jipc.inout.OutBoolean;
import com.skype.android.jipc.inout.OutInt32;
import com.skype.android.jipc.inout.OutMayBe;
import com.skype.android.jipc.inout.OutStatus;
import com.skype.android.jipc.omx.data.ExtendedIndex;
import com.skype.android.jipc.omx.data.OmxStruct;
import com.skype.android.jipc.omx.enums.OmxCore.Error;
import com.skype.android.jipc.omx.enums.OmxIndex;
import com.skype.android.jipc.omx.enums.OmxIndex.Component;
import com.skype.android.jipc.omx.inout.InNewNode;
import com.skype.android.jipc.omx.inout.InNode;
import com.skype.android.jipc.omx.inout.InNodeCfg;
import com.skype.android.jipc.omx.inout.InNodeCmd;
import com.skype.android.jipc.omx.inout.InNodeExt;
import com.skype.android.jipc.omx.inout.InNodePid;
import com.skype.android.jipc.omx.inout.OutCodecs;
import com.skype.android.jipc.omx.inout.OutParam;
import com.skype.android.jipc.omx.inout.OutState;

public class OmxFacade {
    public static final int a = Component.OMX_IndexComponentStartUnused.a();
    private final ServicePath b;
    private final CallRouter c;
    private final InHeader d;
    private final InHeader e;
    private final InNewNode f = new InNewNode();
    private final InNode g = new InNode();
    private final InNodeCfg h;
    private final InNodeCmd i;
    private final InNodeExt j;
    private final InNodePid k;
    private final OutBinder l = new OutBinder();
    private final OutCodecs m = new OutCodecs();
    private final OutStatus n = new OutStatus();
    private final OutInt32 o = new OutInt32();
    private final OutBoolean p = new OutBoolean();
    private final OutMayBe<Void, OutInt32> q;
    private final OutState r = new OutState(this.n);
    private final OutParam s = new OutParam();
    private final OutMayBe<Void, OutParam> t;

    OmxFacade(ServicePath servicePath, CallRouter callRouter) {
        this.b = servicePath;
        this.c = callRouter;
        this.d = new InHeader(servicePath.d);
        this.e = new InHeader("android.hardware.IOMX");
        this.h = new InNodeCfg(this.g);
        this.i = new InNodeCmd(this.g);
        this.k = new InNodePid(this.g);
        this.j = new InNodeExt(this.g);
        this.q = new OutMayBe(this.n, this.o);
        this.t = new OutMayBe(this.n, this.s);
    }

    public final OutStatus b() {
        return this.n;
    }

    public final OutMayBe<Void, OutInt32> a(IBinder omx, OmxObserver observer, String codecName) {
        observer.a();
        this.f.a(codecName, observer);
        Transactor.a(omx, this.c.b(), this.f, this.q);
        this.f.a();
        if (OutStatus.c(this.q.a.b())) {
            observer.a(((OutInt32) this.q.b).b());
        } else {
            observer.b();
        }
        return this.q;
    }

    public final OutStatus a(IBinder omx, int nodeId, OmxObserver observer) {
        observer.b(nodeId);
        this.g.a(nodeId);
        Transactor.a(omx, this.c.c(), this.g, this.n);
        observer.b();
        return this.n;
    }

    private <T, O extends Out<T>> OutStatus a(IBinder omx, What code, int nodeId, OmxStruct param, O out) {
        Object obj;
        OmxIndex index = param.e();
        if (index instanceof ExtendedIndex) {
            ((ExtendedIndex) index).a(this, omx, nodeId);
            if (index.a() == a) {
                this.n.a(Error.OMX_ErrorComponentNotFound.N);
            }
            if (!OutStatus.c(this.n.b())) {
                return this.n;
            }
        }
        this.h.a(nodeId, param);
        if (out == this.t) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.s.a(param);
        }
        Transactor.a(omx, code, this.h, out);
        return this.n;
    }

    public final OutStatus a(IBinder omx, int nodeId, OmxStruct param) {
        return a(omx, this.c.d(), nodeId, param, this.t);
    }

    public final OutStatus b(IBinder omx, int nodeId, OmxStruct param) {
        return a(omx, this.c.e(), nodeId, param, this.n);
    }

    public final OutStatus c(IBinder omx, int nodeId, OmxStruct param) {
        return a(omx, this.c.f(), nodeId, param, this.n);
    }

    public final OutMayBe<Void, OutInt32> a(IBinder omx, int nodeId, String extName) {
        this.j.a(nodeId, extName);
        this.o.a(a);
        Transactor.a(omx, this.c.g(), this.j, this.q);
        return this.q;
    }

    public final IBinder a() {
        return (IBinder) Transactor.a(Locator.a().a(this.b.c), this.c.a(), this.d, this.l);
    }
}
