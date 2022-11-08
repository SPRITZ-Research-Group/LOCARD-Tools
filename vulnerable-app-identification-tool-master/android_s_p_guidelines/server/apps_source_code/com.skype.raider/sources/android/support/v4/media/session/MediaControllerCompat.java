package android.support.v4.media.session;

import android.content.Context;
import android.media.session.MediaController;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class MediaControllerCompat {
    private final b a;
    private final Token b;
    private final HashSet<a> c = new HashSet();

    interface b {
        boolean a(KeyEvent keyEvent);
    }

    @RequiresApi(21)
    static class MediaControllerImplApi21 implements b {
        protected final Object a;
        private final List<a> b = new ArrayList();
        private b c;
        private HashMap<a, a> d = new HashMap();

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> a;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImpl, Handler handler) {
                super(handler);
                this.a = new WeakReference(mediaControllerImpl);
            }

            protected void onReceiveResult(int resultCode, Bundle resultData) {
                MediaControllerImplApi21 mediaControllerImpl = (MediaControllerImplApi21) this.a.get();
                if (mediaControllerImpl != null && resultData != null) {
                    mediaControllerImpl.c = android.support.v4.media.session.b.a.a(android.support.v4.app.e.a(resultData, "android.support.v4.media.session.EXTRA_BINDER"));
                    MediaControllerImplApi21.a(mediaControllerImpl);
                }
            }
        }

        private static class a extends c {
            a(a callback) {
                super(callback);
            }

            public final void a() throws RemoteException {
                throw new AssertionError();
            }

            public final void a(MediaMetadataCompat metadata) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(List<QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(CharSequence title) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(ParcelableVolumeInfo info) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat session) {
            this.a = c.a(context, session.c().a());
            this.c = session.c().b();
            if (this.c == null) {
                a();
            }
        }

        public MediaControllerImplApi21(Context context, Token sessionToken) throws RemoteException {
            this.a = c.a(context, sessionToken.a());
            if (this.a == null) {
                throw new RemoteException();
            }
            this.c = sessionToken.b();
            if (this.c == null) {
                a();
            }
        }

        public final boolean a(KeyEvent event) {
            return ((MediaController) this.a).dispatchMediaButtonEvent(event);
        }

        private void a() {
            ResultReceiver extraBinderRequestResultReceiver = new ExtraBinderRequestResultReceiver(this, new Handler());
            ((MediaController) this.a).sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, extraBinderRequestResultReceiver);
        }

        static /* synthetic */ void a(MediaControllerImplApi21 x0) {
            if (x0.c != null) {
                synchronized (x0.b) {
                    for (a aVar : x0.b) {
                        a aVar2 = new a(aVar);
                        x0.d.put(aVar, aVar2);
                        aVar.b = true;
                        try {
                            x0.c.a(aVar2);
                        } catch (RemoteException e) {
                        }
                    }
                    x0.b.clear();
                }
            }
        }
    }

    public static abstract class a implements DeathRecipient {
        a a;
        boolean b;
        private final Object c;

        private static class c extends android.support.v4.media.session.a.a {
            private final WeakReference<a> a;

            c(a callback) {
                this.a = new WeakReference(callback);
            }

            public final void a(String event, Bundle extras) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(1, event, extras);
                }
            }

            public void a() throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(8, null, null);
                }
            }

            public final void a(PlaybackStateCompat state) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(2, state, null);
                }
            }

            public void a(MediaMetadataCompat metadata) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(3, metadata, null);
                }
            }

            public void a(List<QueueItem> queue) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(5, queue, null);
                }
            }

            public void a(CharSequence title) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(6, title, null);
                }
            }

            public final void b(boolean enabled) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(11, Boolean.valueOf(enabled), null);
                }
            }

            public final void a(int repeatMode) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(9, Integer.valueOf(repeatMode), null);
                }
            }

            public final void a(boolean enabled) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(10, Boolean.valueOf(enabled), null);
                }
            }

            public final void b(int shuffleMode) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(12, Integer.valueOf(shuffleMode), null);
                }
            }

            public void a(Bundle extras) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    callback.a(7, extras, null);
                }
            }

            public void a(ParcelableVolumeInfo info) throws RemoteException {
                a callback = (a) this.a.get();
                if (callback != null) {
                    f pi = null;
                    if (info != null) {
                        pi = new f(info.a, info.b, info.c, info.d, info.e);
                    }
                    callback.a(4, pi, null);
                }
            }
        }

        private class a extends Handler {
            boolean a;

            public final void handleMessage(Message msg) {
                if (this.a) {
                    Object obj;
                    switch (msg.what) {
                        case 1:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 2:
                            obj = msg.obj;
                            return;
                        case 3:
                            obj = msg.obj;
                            return;
                        case 4:
                            obj = msg.obj;
                            return;
                        case 5:
                            obj = msg.obj;
                            return;
                        case 6:
                            obj = msg.obj;
                            return;
                        case 7:
                            obj = msg.obj;
                            return;
                        case 9:
                            ((Integer) msg.obj).intValue();
                            return;
                        case 10:
                            ((Boolean) msg.obj).booleanValue();
                            return;
                        case 11:
                            ((Boolean) msg.obj).booleanValue();
                            return;
                        case 12:
                            ((Integer) msg.obj).intValue();
                            return;
                        default:
                            return;
                    }
                }
            }
        }

        private static class b implements android.support.v4.media.session.c.a {
            private final WeakReference<a> a;

            b(a callback) {
                this.a = new WeakReference(callback);
            }

            public final void a() {
                this.a.get();
            }

            public final void b() {
                a callback = (a) this.a.get();
                if (callback != null && callback.b) {
                    int i = VERSION.SDK_INT;
                }
            }

            public final void a(Object stateObj) {
                a callback = (a) this.a.get();
                if (callback != null && !callback.b) {
                    PlaybackStateCompat.a(stateObj);
                }
            }

            public final void b(Object metadataObj) {
                if (((a) this.a.get()) != null) {
                    MediaMetadataCompat.a(metadataObj);
                }
            }

            public final void a(List<?> queue) {
                if (((a) this.a.get()) != null) {
                    QueueItem.a(queue);
                }
            }

            public final void c() {
                this.a.get();
            }

            public final void d() {
                this.a.get();
            }

            public final void a(int type, int stream, int control, int max, int current) {
                if (((a) this.a.get()) != null) {
                    f fVar = new f(type, stream, control, max, current);
                }
            }
        }

        public a() {
            if (VERSION.SDK_INT >= 21) {
                this.c = new b(new b(this));
            } else {
                this.c = new c(this);
            }
        }

        final void a(int what, Object obj, Bundle data) {
            if (this.a != null) {
                Message msg = this.a.obtainMessage(what, obj);
                msg.setData(data);
                msg.sendToTarget();
            }
        }
    }

    @RequiresApi(23)
    static class c extends MediaControllerImplApi21 {
        public c(Context context, MediaSessionCompat session) {
            super(context, session);
        }

        public c(Context context, Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }
    }

    @RequiresApi(24)
    static class d extends c {
        public d(Context context, MediaSessionCompat session) {
            super(context, session);
        }

        public d(Context context, Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }
    }

    static class e implements b {
        private b a;

        public e(Token token) {
            this.a = android.support.v4.media.session.b.a.a((IBinder) token.a());
        }

        public final boolean a(KeyEvent event) {
            if (event == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.a.a(event);
            } catch (RemoteException e) {
            }
            return false;
        }
    }

    public static final class f {
        private final int a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;

        f(int type, int stream, int control, int max, int current) {
            this.a = type;
            this.b = stream;
            this.c = control;
            this.d = max;
            this.e = current;
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat session) {
        if (session == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        this.b = session.c();
        if (VERSION.SDK_INT >= 24) {
            this.a = new d(context, session);
        } else if (VERSION.SDK_INT >= 23) {
            this.a = new c(context, session);
        } else if (VERSION.SDK_INT >= 21) {
            this.a = new MediaControllerImplApi21(context, session);
        } else {
            this.a = new e(this.b);
        }
    }

    public MediaControllerCompat(Context context, @NonNull Token sessionToken) throws RemoteException {
        if (sessionToken == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.b = sessionToken;
        if (VERSION.SDK_INT >= 24) {
            this.a = new d(context, sessionToken);
        } else if (VERSION.SDK_INT >= 23) {
            this.a = new c(context, sessionToken);
        } else if (VERSION.SDK_INT >= 21) {
            this.a = new MediaControllerImplApi21(context, sessionToken);
        } else {
            this.a = new e(this.b);
        }
    }

    public final boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.a.a(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }
}
