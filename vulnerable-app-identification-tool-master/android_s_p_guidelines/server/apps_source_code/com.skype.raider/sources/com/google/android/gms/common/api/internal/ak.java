package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.f;

final class ak extends Handler {
    private final /* synthetic */ ai a;

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                f fVar = (f) message.obj;
                synchronized (this.a.e) {
                    if (fVar == null) {
                        this.a.b.a(new Status(13, "Transform returned null"));
                    } else if (fVar instanceof ac) {
                        this.a.b.a(((ac) fVar).b());
                    } else {
                        this.a.b.a(fVar);
                    }
                }
                return;
            case 1:
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String str = "Runtime exception on the transformation worker thread: ";
                String valueOf = String.valueOf(runtimeException.getMessage());
                if (valueOf.length() != 0) {
                    str.concat(valueOf);
                } else {
                    valueOf = new String(str);
                }
                throw runtimeException;
            default:
                new StringBuilder(70).append("TransformationResultHandler received unknown message type: ").append(message.what);
                return;
        }
    }
}
