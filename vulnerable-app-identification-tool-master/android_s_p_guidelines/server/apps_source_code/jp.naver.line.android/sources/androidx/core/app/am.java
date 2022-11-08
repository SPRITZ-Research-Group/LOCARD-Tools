package androidx.core.app;

import android.os.RemoteException;
import android.support.v4.app.INotificationSideChannel;

interface am {
    void a(INotificationSideChannel iNotificationSideChannel) throws RemoteException;
}
