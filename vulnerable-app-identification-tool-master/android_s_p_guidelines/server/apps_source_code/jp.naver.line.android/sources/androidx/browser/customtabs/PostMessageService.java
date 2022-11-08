package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import defpackage.d;
import defpackage.k;

public class PostMessageService extends Service {
    private k a = new k(this) {
        final /* synthetic */ PostMessageService a;

        {
            this.a = r1;
        }

        public final void a(d dVar, Bundle bundle) throws RemoteException {
            dVar.a(bundle);
        }

        public final void a(d dVar, String str, Bundle bundle) throws RemoteException {
            dVar.b(str, bundle);
        }
    };

    public IBinder onBind(Intent intent) {
        return this.a;
    }
}
