package com.skype.android.jipc.omx;

import android.os.Binder;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.skype.android.jipc.omx.message.OmxMessage;
import com.skype.android.video.hw.utils.Blossom;
import java.util.concurrent.atomic.AtomicInteger;

public class OmxObserver extends Binder {
    private final OmxMessage a = new OmxMessage();
    private final AtomicInteger b = new AtomicInteger();
    private final Blossom c = new Blossom(3);
    private final CallRouter d;
    private OnMessageListener e;

    public interface OnMessageListener {
    }

    OmxObserver(CallRouter router) {
        this.d = router;
        attachInterface(new IInterface(this) {
            final /* synthetic */ OmxObserver a;

            {
                this.a = this$0;
            }

            public final IBinder asBinder() {
                return this.a;
            }
        }, "android.hardware.IOMXObserver");
    }

    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        boolean z = false;
        if (code != this.d.h().a()) {
            return super.onTransact(code, data, reply, flags);
        }
        if (this.e == null) {
            return true;
        }
        int nodeId = data.readInt();
        if (VERSION.SDK_INT >= 23) {
            while (true) {
                int fence = data.readInt();
                if (fence < 0) {
                    return true;
                }
                this.a.a(nodeId, fence != 0, data);
            }
        } else {
            OmxMessage omxMessage = this.a;
            if (VERSION.SDK_INT >= 21) {
                z = true;
            }
            omxMessage.a(nodeId, z, data);
            return true;
        }
    }

    public final void a() {
        this.b.incrementAndGet();
    }

    public final void a(int nodeId) {
        this.c.put(nodeId);
    }

    public final void b() {
        if (this.b.decrementAndGet() == 0) {
            this.c.clear();
        }
    }

    public final void b(int nodeId) {
        if (!this.c.mayHave(nodeId)) {
            throw new NotMyNodeException(nodeId);
        }
    }
}
