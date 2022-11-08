package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.ag;

@VisibleForTesting(otherwise = 3)
@KeepForSdk
public final class c {

    @KeepForSdk
    public interface b<R> {
        @KeepForSdk
        void a(R r);
    }

    @KeepForSdk
    public static abstract class a<R extends i, A extends com.google.android.gms.common.api.a.b> extends BasePendingResult<R> implements b<R> {
        @KeepForSdk
        private final com.google.android.gms.common.api.a.c<A> b;
        @KeepForSdk
        private final com.google.android.gms.common.api.a<?> c;

        @KeepForSdk
        protected a(@NonNull com.google.android.gms.common.api.a<?> aVar, @NonNull e eVar) {
            super((e) ab.a((Object) eVar, (Object) "GoogleApiClient must not be null"));
            ab.a((Object) aVar, (Object) "Api must not be null");
            this.b = aVar.b();
            this.c = aVar;
        }

        @KeepForSdk
        private void a(@NonNull RemoteException remoteException) {
            a(new Status(remoteException.getLocalizedMessage()));
        }

        @KeepForSdk
        public final void a(@NonNull Status status) {
            ab.b(!status.d(), "Failed result must not be success");
            a(c(status));
        }

        @KeepForSdk
        public final void a(@NonNull A a) throws DeadObjectException {
            com.google.android.gms.common.api.a.b a2;
            if (a2 instanceof ag) {
                a2 = ((ag) a2).s();
            }
            try {
                b(a2);
            } catch (RemoteException e) {
                a(e);
                throw e;
            } catch (RemoteException e2) {
                a(e2);
            }
        }

        @KeepForSdk
        protected abstract void b(@NonNull A a) throws RemoteException;
    }
}
