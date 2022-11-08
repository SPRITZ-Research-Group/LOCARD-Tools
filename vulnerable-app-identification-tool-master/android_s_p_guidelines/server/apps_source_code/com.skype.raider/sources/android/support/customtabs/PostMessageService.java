package android.support.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.f.a;

public class PostMessageService extends Service {
    private a a = new a(this) {
        final /* synthetic */ PostMessageService a;

        {
            this.a = this$0;
        }

        public final void a(d callback, Bundle extras) throws RemoteException {
            callback.a(extras);
        }

        public final void a(d callback, String message, Bundle extras) throws RemoteException {
            callback.b(message, extras);
        }
    };

    public IBinder onBind(Intent intent) {
        return this.a;
    }
}
