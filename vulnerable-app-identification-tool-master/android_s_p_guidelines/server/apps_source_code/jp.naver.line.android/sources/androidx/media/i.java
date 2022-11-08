package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.session.MediaSessionCompat.Token;
import java.util.List;

interface i {
    IBinder a();

    void a(String str, Token token, Bundle bundle) throws RemoteException;

    void a(String str, List<MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;

    void b() throws RemoteException;
}
