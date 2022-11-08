package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Message;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ab;

@KeepForSdk
public final class h<L> {
    private final c a;
    private volatile L b;

    @KeepForSdk
    public static final class a<L> {
        private final L a;
        private final String b;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b.equals(aVar.b);
        }

        public final int hashCode() {
            return (System.identityHashCode(this.a) * 31) + this.b.hashCode();
        }
    }

    @KeepForSdk
    public interface b<L> {
    }

    private final class c extends Handler {
        private final /* synthetic */ h a;

        public final void handleMessage(Message message) {
            boolean z = true;
            if (message.what != 1) {
                z = false;
            }
            ab.b(z);
            h hVar = this.a;
            Object obj = message.obj;
            hVar.b();
        }
    }

    @KeepForSdk
    public final void a() {
        this.b = null;
    }

    @KeepForSdk
    public final void a(b<? super L> bVar) {
        ab.a((Object) bVar, (Object) "Notifier must not be null");
        this.a.sendMessage(this.a.obtainMessage(1, bVar));
    }

    @KeepForSdk
    final void b() {
        if (this.b != null) {
        }
    }
}
