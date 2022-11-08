package android.support.v4.media;

import android.app.Service;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.i;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MediaBrowserServiceCompat extends Service {
    static final boolean a = Log.isLoggable("MBServiceCompat", 3);
    final android.support.v4.util.a<IBinder, b> b = new android.support.v4.util.a();
    b c;
    final g d = new g(this);
    Token e;

    public static class c<T> {
        private final Object a;
        private boolean b;
        private boolean c;
        private boolean d;
        private int e;

        c(Object debug) {
            this.a = debug;
        }

        public final void c() {
            if (this.c || this.d) {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
            this.c = true;
            a();
        }

        public final void d() {
            if (this.c || this.d) {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
            this.d = true;
            b();
        }

        final boolean e() {
            return this.b || this.c || this.d;
        }

        final void a(int flags) {
            this.e = flags;
        }

        final int f() {
            return this.e;
        }

        void a() {
        }

        void b() {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.a);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$2 */
    class AnonymousClass2 extends c<MediaItem> {
        final /* synthetic */ ResultReceiver a;
        final /* synthetic */ MediaBrowserServiceCompat b;

        AnonymousClass2(MediaBrowserServiceCompat this$0, Object debug, ResultReceiver resultReceiver) {
            this.b = this$0;
            this.a = resultReceiver;
            super(debug);
        }

        final /* synthetic */ void a() {
            if ((f() & 2) != 0) {
                this.a.b(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", null);
            this.a.b(0, bundle);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$3 */
    class AnonymousClass3 extends c<List<MediaItem>> {
        final /* synthetic */ ResultReceiver a;
        final /* synthetic */ MediaBrowserServiceCompat b;

        AnonymousClass3(MediaBrowserServiceCompat this$0, Object debug, ResultReceiver resultReceiver) {
            this.b = this$0;
            this.a = resultReceiver;
            super(debug);
        }

        final /* synthetic */ void a() {
            this.a.b(-1, null);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$4 */
    class AnonymousClass4 extends c<Bundle> {
        final /* synthetic */ ResultReceiver a;
        final /* synthetic */ MediaBrowserServiceCompat b;

        AnonymousClass4(MediaBrowserServiceCompat this$0, Object debug, ResultReceiver resultReceiver) {
            this.b = this$0;
            this.a = resultReceiver;
            super(debug);
        }

        final void b() {
            this.a.b(-1, null);
        }

        final /* synthetic */ void a() {
            this.a.b(0, null);
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    private @interface ResultFlags {
    }

    public static final class a {
        private final String a;
        private final Bundle b;

        public final String a() {
            return this.a;
        }

        public final Bundle b() {
            return this.b;
        }
    }

    private static class b {
        String a;
        Bundle b;
        e c;
        a d;
        HashMap<String, List<i<IBinder, Bundle>>> e = new HashMap();

        b() {
        }
    }

    private class d {
        final /* synthetic */ MediaBrowserServiceCompat a;

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$1 */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ String b;
            final /* synthetic */ Bundle c;
            final /* synthetic */ int d;
            final /* synthetic */ d e;

            AnonymousClass1(d this$1, e eVar, String str, Bundle bundle, int i) {
                this.e = this$1;
                this.a = eVar;
                this.b = str;
                this.c = bundle;
                this.d = i;
            }

            public final void run() {
                IBinder b = this.a.a();
                this.e.a.b.remove(b);
                b connection = new b();
                connection.a = this.b;
                connection.b = this.c;
                connection.c = this.a;
                connection.d = this.e.a.a();
                if (connection.d == null) {
                    new StringBuilder("No root for client ").append(this.b).append(" from service ").append(getClass().getName());
                    try {
                        this.a.b();
                        return;
                    } catch (RemoteException e) {
                        new StringBuilder("Calling onConnectFailed() failed. Ignoring. pkg=").append(this.b);
                        return;
                    }
                }
                try {
                    this.e.a.b.put(b, connection);
                    if (this.e.a.e != null) {
                        this.a.a(connection.d.a(), this.e.a.e, connection.d.b());
                    }
                } catch (RemoteException e2) {
                    new StringBuilder("Calling onConnect() failed. Dropping client. pkg=").append(this.b);
                    this.e.a.b.remove(b);
                }
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$2 */
        class AnonymousClass2 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ d b;

            AnonymousClass2(d this$1, e eVar) {
                this.b = this$1;
                this.a = eVar;
            }

            public final void run() {
                this.b.a.b.remove(this.a.a());
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$3 */
        class AnonymousClass3 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ String b;
            final /* synthetic */ IBinder c;
            final /* synthetic */ Bundle d;
            final /* synthetic */ d e;

            AnonymousClass3(d this$1, e eVar, String str, IBinder iBinder, Bundle bundle) {
                this.e = this$1;
                this.a = eVar;
                this.b = str;
                this.c = iBinder;
                this.d = bundle;
            }

            public final void run() {
                b connection = (b) this.e.a.b.get(this.a.a());
                if (connection == null) {
                    new StringBuilder("addSubscription for callback that isn't registered id=").append(this.b);
                } else {
                    this.e.a.a(this.b, connection, this.c, this.d);
                }
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$4 */
        class AnonymousClass4 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ String b;
            final /* synthetic */ IBinder c;
            final /* synthetic */ d d;

            AnonymousClass4(d this$1, e eVar, String str, IBinder iBinder) {
                this.d = this$1;
                this.a = eVar;
                this.b = str;
                this.c = iBinder;
            }

            public final void run() {
                b connection = (b) this.d.a.b.get(this.a.a());
                if (connection == null) {
                    new StringBuilder("removeSubscription for callback that isn't registered id=").append(this.b);
                } else if (!MediaBrowserServiceCompat.a(this.b, connection, this.c)) {
                    new StringBuilder("removeSubscription called for ").append(this.b).append(" which is not subscribed");
                }
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$5 */
        class AnonymousClass5 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ String b;
            final /* synthetic */ ResultReceiver c;
            final /* synthetic */ d d;

            AnonymousClass5(d this$1, e eVar, String str, ResultReceiver resultReceiver) {
                this.d = this$1;
                this.a = eVar;
                this.b = str;
                this.c = resultReceiver;
            }

            public final void run() {
                b connection = (b) this.d.a.b.get(this.a.a());
                if (connection == null) {
                    new StringBuilder("getMediaItem for callback that isn't registered id=").append(this.b);
                    return;
                }
                MediaBrowserServiceCompat mediaBrowserServiceCompat = this.d.a;
                String str = this.b;
                c anonymousClass2 = new AnonymousClass2(mediaBrowserServiceCompat, str, this.c);
                mediaBrowserServiceCompat.c = connection;
                anonymousClass2.a(2);
                anonymousClass2.c();
                mediaBrowserServiceCompat.c = null;
                if (!anonymousClass2.e()) {
                    throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
                }
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$6 */
        class AnonymousClass6 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ Bundle b;
            final /* synthetic */ d c;

            AnonymousClass6(d this$1, e eVar, Bundle bundle) {
                this.c = this$1;
                this.a = eVar;
                this.b = bundle;
            }

            public final void run() {
                IBinder b = this.a.a();
                this.c.a.b.remove(b);
                b connection = new b();
                connection.c = this.a;
                connection.b = this.b;
                this.c.a.b.put(b, connection);
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$7 */
        class AnonymousClass7 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ d b;

            AnonymousClass7(d this$1, e eVar) {
                this.b = this$1;
                this.a = eVar;
            }

            public final void run() {
                this.b.a.b.remove(this.a.a());
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$8 */
        class AnonymousClass8 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ String b;
            final /* synthetic */ Bundle c;
            final /* synthetic */ ResultReceiver d;
            final /* synthetic */ d e;

            AnonymousClass8(d this$1, e eVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.e = this$1;
                this.a = eVar;
                this.b = str;
                this.c = bundle;
                this.d = resultReceiver;
            }

            public final void run() {
                b connection = (b) this.e.a.b.get(this.a.a());
                if (connection == null) {
                    new StringBuilder("search for callback that isn't registered query=").append(this.b);
                    return;
                }
                MediaBrowserServiceCompat mediaBrowserServiceCompat = this.e.a;
                String str = this.b;
                c anonymousClass3 = new AnonymousClass3(mediaBrowserServiceCompat, str, this.d);
                mediaBrowserServiceCompat.c = connection;
                anonymousClass3.a(4);
                anonymousClass3.c();
                mediaBrowserServiceCompat.c = null;
                if (!anonymousClass3.e()) {
                    throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
                }
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$d$9 */
        class AnonymousClass9 implements Runnable {
            final /* synthetic */ e a;
            final /* synthetic */ String b;
            final /* synthetic */ Bundle c;
            final /* synthetic */ ResultReceiver d;
            final /* synthetic */ d e;

            AnonymousClass9(d this$1, e eVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.e = this$1;
                this.a = eVar;
                this.b = str;
                this.c = bundle;
                this.d = resultReceiver;
            }

            public final void run() {
                b connection = (b) this.e.a.b.get(this.a.a());
                if (connection == null) {
                    new StringBuilder("sendCustomAction for callback that isn't registered action=").append(this.b).append(", extras=").append(this.c);
                    return;
                }
                MediaBrowserServiceCompat mediaBrowserServiceCompat = this.e.a;
                String str = this.b;
                Bundle bundle = this.c;
                c anonymousClass4 = new AnonymousClass4(mediaBrowserServiceCompat, str, this.d);
                mediaBrowserServiceCompat.c = connection;
                anonymousClass4.d();
                mediaBrowserServiceCompat.c = null;
                if (!anonymousClass4.e()) {
                    throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
                }
            }
        }

        d(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.a = mediaBrowserServiceCompat;
        }
    }

    private interface e {
        IBinder a();

        void a(String str, Token token, Bundle bundle) throws RemoteException;

        void a(String str, List<MediaItem> list, Bundle bundle) throws RemoteException;

        void b() throws RemoteException;
    }

    private static class f implements e {
        final Messenger a;

        f(Messenger callbacks) {
            this.a = callbacks;
        }

        public final IBinder a() {
            return this.a.getBinder();
        }

        public final void a(String root, Token session, Bundle extras) throws RemoteException {
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putInt("extra_service_version", 1);
            Bundle data = new Bundle();
            data.putString("data_media_item_id", root);
            data.putParcelable("data_media_session_token", session);
            data.putBundle("data_root_hints", extras);
            a(1, data);
        }

        public final void b() throws RemoteException {
            a(2, null);
        }

        public final void a(String mediaId, List<MediaItem> list, Bundle options) throws RemoteException {
            Bundle data = new Bundle();
            data.putString("data_media_item_id", mediaId);
            data.putBundle("data_options", options);
            a(3, data);
        }

        private void a(int what, Bundle data) throws RemoteException {
            Message msg = Message.obtain();
            msg.what = what;
            msg.arg1 = 1;
            msg.setData(data);
            this.a.send(msg);
        }
    }

    private final class g extends Handler {
        final /* synthetic */ MediaBrowserServiceCompat a;
        private final d b = new d(this.a);

        g(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.a = mediaBrowserServiceCompat;
        }

        public final void handleMessage(Message msg) {
            Object obj = null;
            Bundle data = msg.getData();
            d dVar;
            String string;
            Bundle bundle;
            e fVar;
            d dVar2;
            Object string2;
            ResultReceiver resultReceiver;
            switch (msg.what) {
                case 1:
                    dVar = this.b;
                    string = data.getString("data_package_name");
                    int i = data.getInt("data_calling_uid");
                    bundle = data.getBundle("data_root_hints");
                    fVar = new f(msg.replyTo);
                    MediaBrowserServiceCompat mediaBrowserServiceCompat = dVar.a;
                    if (string != null) {
                        String[] packagesForUid = mediaBrowserServiceCompat.getPackageManager().getPackagesForUid(i);
                        int length = packagesForUid.length;
                        int i2 = 0;
                        while (i2 < length) {
                            if (packagesForUid[i2].equals(string)) {
                                obj = 1;
                            } else {
                                i2++;
                            }
                        }
                    }
                    if (obj == null) {
                        throw new IllegalArgumentException("Package/uid mismatch: uid=" + i + " package=" + string);
                    }
                    dVar.a.d.a(new AnonymousClass1(dVar, fVar, string, bundle, i));
                    return;
                case 2:
                    dVar2 = this.b;
                    dVar2.a.d.a(new AnonymousClass2(dVar2, new f(msg.replyTo)));
                    return;
                case 3:
                    dVar = this.b;
                    string = data.getString("data_media_item_id");
                    IBinder a = android.support.v4.app.e.a(data, "data_callback_token");
                    Bundle bundle2 = data.getBundle("data_options");
                    dVar.a.d.a(new AnonymousClass3(dVar, new f(msg.replyTo), string, a, bundle2));
                    return;
                case 4:
                    dVar2 = this.b;
                    String string3 = data.getString("data_media_item_id");
                    IBinder a2 = android.support.v4.app.e.a(data, "data_callback_token");
                    dVar2.a.d.a(new AnonymousClass4(dVar2, new f(msg.replyTo), string3, a2));
                    return;
                case 5:
                    dVar = this.b;
                    Object string4 = data.getString("data_media_item_id");
                    ResultReceiver resultReceiver2 = (ResultReceiver) data.getParcelable("data_result_receiver");
                    e fVar2 = new f(msg.replyTo);
                    if (!TextUtils.isEmpty(string4) && resultReceiver2 != null) {
                        dVar.a.d.a(new AnonymousClass5(dVar, fVar2, string4, resultReceiver2));
                        return;
                    }
                    return;
                case 6:
                    dVar2 = this.b;
                    dVar2.a.d.a(new AnonymousClass6(dVar2, new f(msg.replyTo), data.getBundle("data_root_hints")));
                    return;
                case 7:
                    dVar2 = this.b;
                    dVar2.a.d.a(new AnonymousClass7(dVar2, new f(msg.replyTo)));
                    return;
                case 8:
                    dVar = this.b;
                    string2 = data.getString("data_search_query");
                    bundle = data.getBundle("data_search_extras");
                    resultReceiver = (ResultReceiver) data.getParcelable("data_result_receiver");
                    fVar = new f(msg.replyTo);
                    if (!TextUtils.isEmpty(string2) && resultReceiver != null) {
                        dVar.a.d.a(new AnonymousClass8(dVar, fVar, string2, bundle, resultReceiver));
                        return;
                    }
                    return;
                case 9:
                    dVar = this.b;
                    string2 = data.getString("data_custom_action");
                    bundle = data.getBundle("data_custom_action_extras");
                    resultReceiver = (ResultReceiver) data.getParcelable("data_result_receiver");
                    fVar = new f(msg.replyTo);
                    if (!TextUtils.isEmpty(string2) && resultReceiver != null) {
                        dVar.a.d.a(new AnonymousClass9(dVar, fVar, string2, bundle, resultReceiver));
                        return;
                    }
                    return;
                default:
                    new StringBuilder("Unhandled message: ").append(msg).append("\n  Service version: 1\n  Client version: ").append(msg.arg1);
                    return;
            }
        }

        public final boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            Bundle data = msg.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            return super.sendMessageAtTime(msg, uptimeMillis);
        }

        private void a(Runnable r) {
            if (Thread.currentThread() == getLooper().getThread()) {
                r.run();
            } else {
                post(r);
            }
        }
    }

    @Nullable
    public abstract a a();

    final void a(String id, b connection, IBinder token, Bundle options) {
        List<i<IBinder, Bundle>> callbackList = (List) connection.e.get(id);
        if (callbackList == null) {
            callbackList = new ArrayList();
        }
        for (i<IBinder, Bundle> callback : callbackList) {
            if (token == callback.a && d.a(options, (Bundle) callback.b)) {
                return;
            }
        }
        callbackList.add(new i(token, options));
        connection.e.put(id, callbackList);
        final b bVar = connection;
        final String str = id;
        final Bundle bundle = options;
        c anonymousClass1 = new c<List<MediaItem>>(this, id) {
            final /* synthetic */ MediaBrowserServiceCompat d;

            final /* synthetic */ void a() {
                if (this.d.b.get(bVar.c.a()) == bVar) {
                    try {
                        bVar.c.a(str, null, bundle);
                    } catch (RemoteException e) {
                        new StringBuilder("Calling onLoadChildren() failed for id=").append(str).append(" package=").append(bVar.a);
                    }
                } else if (MediaBrowserServiceCompat.a) {
                    new StringBuilder("Not sending onLoadChildren result for connection that has been disconnected. pkg=").append(bVar.a).append(" id=").append(str);
                }
            }
        };
        this.c = connection;
        if (options != null) {
            anonymousClass1.a(1);
        }
        this.c = null;
        if (!anonymousClass1.e()) {
            throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + connection.a + " id=" + id);
        }
    }

    static boolean a(String id, b connection, IBinder token) {
        if (token != null) {
            boolean removed = false;
            List<i<IBinder, Bundle>> callbackList = (List) connection.e.get(id);
            if (callbackList == null) {
                return false;
            }
            Iterator<i<IBinder, Bundle>> iter = callbackList.iterator();
            while (iter.hasNext()) {
                if (token == ((i) iter.next()).a) {
                    removed = true;
                    iter.remove();
                }
            }
            if (callbackList.size() != 0) {
                return removed;
            }
            connection.e.remove(id);
            return removed;
        } else if (connection.e.remove(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
