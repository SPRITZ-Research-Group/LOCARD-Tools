package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.browse.MediaBrowser;
import android.media.browse.MediaBrowser.ConnectionCallback;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public final class MediaBrowserCompat {
    static final boolean a = Log.isLoggable("MediaBrowserCompat", 3);
    private final d b;

    private static class CustomActionResultReceiver extends ResultReceiver {
        private final Bundle d;
        private final c e;

        protected final void a(int resultCode, Bundle resultData) {
            if (this.e != null) {
                switch (resultCode) {
                    case -1:
                    case 0:
                    case 1:
                        return;
                    default:
                        new StringBuilder("Unknown result code: ").append(resultCode).append(" (extras=").append(this.d).append(", resultData=").append(resultData).append(")");
                        return;
                }
            }
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        protected final void a(int resultCode, Bundle resultData) {
            if (resultData != null) {
                resultData.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (resultCode == 0 && resultData != null && resultData.containsKey("media_item")) {
                resultData.getParcelable("media_item");
            }
        }
    }

    public static class MediaItem implements Parcelable {
        public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new MediaItem[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }
        };
        private final int a;
        private final MediaDescriptionCompat b;

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }

        public static List<MediaItem> a(List<?> itemList) {
            if (itemList == null || VERSION.SDK_INT < 21) {
                return null;
            }
            List<MediaItem> items = new ArrayList(itemList.size());
            for (Object itemObj : itemList) {
                Object obj;
                if (itemObj == null || VERSION.SDK_INT < 21) {
                    obj = null;
                } else {
                    obj = new MediaItem(MediaDescriptionCompat.a(((android.media.browse.MediaBrowser.MediaItem) itemObj).getDescription()), ((android.media.browse.MediaBrowser.MediaItem) itemObj).getFlags());
                }
                items.add(obj);
            }
            return items;
        }

        private MediaItem(@NonNull MediaDescriptionCompat description, int flags) {
            if (description == null) {
                throw new IllegalArgumentException("description cannot be null");
            } else if (TextUtils.isEmpty(description.a())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else {
                this.a = flags;
                this.b = description;
            }
        }

        MediaItem(Parcel in) {
            this.a = in.readInt();
            this.b = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(in);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.a);
            this.b.writeToParcel(out, flags);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("MediaItem{");
            sb.append("mFlags=").append(this.a);
            sb.append(", mDescription=").append(this.b);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class SearchResultReceiver extends ResultReceiver {
        protected final void a(int resultCode, Bundle resultData) {
            if (resultData != null) {
                resultData.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (resultCode == 0 && resultData != null && resultData.containsKey("search_results")) {
                Parcelable[] items = resultData.getParcelableArray("search_results");
                if (items != null) {
                    List<MediaItem> results = new ArrayList();
                    for (Parcelable item : items) {
                        results.add((MediaItem) item);
                    }
                }
            }
        }
    }

    private static class a extends Handler {
        private final WeakReference<i> a;
        private WeakReference<Messenger> b;

        a(i callbackImpl) {
            this.a = new WeakReference(callbackImpl);
        }

        public final void handleMessage(Message msg) {
            if (this.b != null && this.b.get() != null && this.a.get() != null) {
                Bundle data = msg.getData();
                data.setClassLoader(MediaSessionCompat.class.getClassLoader());
                i serviceCallback = (i) this.a.get();
                Messenger callbacksMessenger = (Messenger) this.b.get();
                try {
                    switch (msg.what) {
                        case 1:
                            serviceCallback.a(callbacksMessenger, data.getString("data_media_item_id"), (Token) data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
                            return;
                        case 2:
                            serviceCallback.a(callbacksMessenger);
                            return;
                        case 3:
                            String string = data.getString("data_media_item_id");
                            data.getParcelableArrayList("data_media_item_list");
                            serviceCallback.a(callbacksMessenger, string, data.getBundle("data_options"));
                            return;
                        default:
                            new StringBuilder("Unhandled message: ").append(msg).append("\n  Client version: 1\n  Service version: ").append(msg.arg1);
                            return;
                    }
                } catch (BadParcelableException e) {
                    if (msg.what == 1) {
                        serviceCallback.a(callbacksMessenger);
                    }
                }
                if (msg.what == 1) {
                    serviceCallback.a(callbacksMessenger);
                }
            }
        }

        final void a(Messenger callbacksMessenger) {
            this.b = new WeakReference(callbacksMessenger);
        }
    }

    public static class b {
        final Object a;
        a b;

        interface a {
            void a();

            void b();
        }

        private class b implements a {
            final /* synthetic */ b a;

            b(b bVar) {
                this.a = bVar;
            }

            public final void a() {
                if (this.a.b != null) {
                    this.a.b.a();
                }
                this.a.a();
            }

            public final void b() {
                if (this.a.b != null) {
                    this.a.b.b();
                }
                this.a.b();
            }

            public final void c() {
                this.a.c();
            }
        }

        public b() {
            if (VERSION.SDK_INT >= 21) {
                this.a = new b(new b(this));
            } else {
                this.a = null;
            }
        }

        public void a() {
        }

        public void b() {
        }

        public void c() {
        }
    }

    public static abstract class c {
    }

    interface d {
        void c();

        void d();

        @NonNull
        Token e();
    }

    interface i {
        void a(Messenger messenger);

        void a(Messenger messenger, String str, Bundle bundle);

        void a(Messenger messenger, String str, Token token, Bundle bundle);
    }

    @RequiresApi(21)
    static class e implements a, d, i {
        final Context a;
        protected final Object b;
        protected final Bundle c;
        protected final a d = new a(this);
        protected j e;
        protected Messenger f;
        private final android.support.v4.util.a<String, k> g = new android.support.v4.util.a();
        private Token h;

        public e(Context context, ComponentName serviceComponent, b callback) {
            this.a = context;
            Bundle rootHints = new Bundle();
            rootHints.putInt("extra_client_version", 1);
            this.c = new Bundle(rootHints);
            callback.b = this;
            this.b = new MediaBrowser(context, serviceComponent, (ConnectionCallback) callback.a, this.c);
        }

        public final void c() {
            ((MediaBrowser) this.b).connect();
        }

        public final void d() {
            if (!(this.e == null || this.f == null)) {
                try {
                    this.e.c(this.f);
                } catch (RemoteException e) {
                }
            }
            ((MediaBrowser) this.b).disconnect();
        }

        @NonNull
        public final Token e() {
            if (this.h == null) {
                this.h = Token.a(((MediaBrowser) this.b).getSessionToken());
            }
            return this.h;
        }

        public final void a() {
            Bundle extras = ((MediaBrowser) this.b).getExtras();
            if (extras != null) {
                IBinder serviceBinder = android.support.v4.app.e.a(extras, "extra_messenger");
                if (serviceBinder != null) {
                    this.e = new j(serviceBinder, this.c);
                    this.f = new Messenger(this.d);
                    this.d.a(this.f);
                    try {
                        this.e.b(this.f);
                    } catch (RemoteException e) {
                    }
                }
                android.support.v4.media.session.b sessionToken = android.support.v4.media.session.b.a.a(android.support.v4.app.e.a(extras, "extra_session_binder"));
                if (sessionToken != null) {
                    this.h = Token.a(((MediaBrowser) this.b).getSessionToken(), sessionToken);
                }
            }
        }

        public final void b() {
            this.e = null;
            this.f = null;
            this.h = null;
            this.d.a(null);
        }

        public final void a(Messenger callback, String root, Token session, Bundle extra) {
        }

        public final void a(Messenger callback) {
        }

        public final void a(Messenger callback, String parentId, Bundle options) {
            if (this.f == callback) {
                k subscription = (k) this.g.get(parentId);
                if (subscription != null) {
                    subscription.a(this.a, options);
                }
            }
        }
    }

    @RequiresApi(23)
    static class f extends e {
        public f(Context context, ComponentName serviceComponent, b callback) {
            super(context, serviceComponent, callback);
        }
    }

    @RequiresApi(26)
    static class g extends f {
        public g(Context context, ComponentName serviceComponent, b callback) {
            super(context, serviceComponent, callback);
        }
    }

    static class h implements d, i {
        final Context a;
        final ComponentName b;
        final b c;
        final Bundle d;
        final a e = new a(this);
        int f = 1;
        a g;
        j h;
        Messenger i;
        private final android.support.v4.util.a<String, k> j = new android.support.v4.util.a();
        private String k;
        private Token l;
        private Bundle m;

        private class a implements ServiceConnection {
            final /* synthetic */ h a;

            a(h hVar) {
                this.a = hVar;
            }

            public final void onServiceConnected(final ComponentName name, final IBinder binder) {
                a(new Runnable(this) {
                    final /* synthetic */ a c;

                    public final void run() {
                        if (MediaBrowserCompat.a) {
                            new StringBuilder("MediaServiceConnection.onServiceConnected name=").append(name).append(" binder=").append(binder);
                            this.c.a.b();
                        }
                        if (this.c.a("onServiceConnected")) {
                            this.c.a.h = new j(binder, this.c.a.d);
                            this.c.a.i = new Messenger(this.c.a.e);
                            this.c.a.e.a(this.c.a.i);
                            this.c.a.f = 2;
                            try {
                                if (MediaBrowserCompat.a) {
                                    this.c.a.b();
                                }
                                this.c.a.h.a(this.c.a.a, this.c.a.i);
                            } catch (RemoteException e) {
                                new StringBuilder("RemoteException during connect for ").append(this.c.a.b);
                                if (MediaBrowserCompat.a) {
                                    this.c.a.b();
                                }
                            }
                        }
                    }
                });
            }

            public final void onServiceDisconnected(final ComponentName name) {
                a(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        if (MediaBrowserCompat.a) {
                            new StringBuilder("MediaServiceConnection.onServiceDisconnected name=").append(name).append(" this=").append(this).append(" mServiceConnection=").append(this.b.a.g);
                            this.b.a.b();
                        }
                        if (this.b.a("onServiceDisconnected")) {
                            this.b.a.h = null;
                            this.b.a.i = null;
                            this.b.a.e.a(null);
                            this.b.a.f = 4;
                            this.b.a.c.b();
                        }
                    }
                });
            }

            private void a(Runnable r) {
                if (Thread.currentThread() == this.a.e.getLooper().getThread()) {
                    r.run();
                } else {
                    this.a.e.post(r);
                }
            }

            final boolean a(String funcName) {
                if (this.a.g == this && this.a.f != 0 && this.a.f != 1) {
                    return true;
                }
                if (!(this.a.f == 0 || this.a.f == 1)) {
                    new StringBuilder().append(funcName).append(" for ").append(this.a.b).append(" with mServiceConnection=").append(this.a.g).append(" this=").append(this);
                }
                return false;
            }
        }

        public h(Context context, ComponentName serviceComponent, b callback) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            } else if (serviceComponent == null) {
                throw new IllegalArgumentException("service component must not be null");
            } else if (callback == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            } else {
                this.a = context;
                this.b = serviceComponent;
                this.c = callback;
                this.d = null;
            }
        }

        public final void c() {
            if (this.f == 0 || this.f == 1) {
                this.f = 2;
                this.e.post(new Runnable(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = this$0;
                    }

                    public final void run() {
                        if (this.a.f != 0) {
                            this.a.f = 2;
                            if (MediaBrowserCompat.a && this.a.g != null) {
                                throw new RuntimeException("mServiceConnection should be null. Instead it is " + this.a.g);
                            } else if (this.a.h != null) {
                                throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.a.h);
                            } else if (this.a.i != null) {
                                throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.a.i);
                            } else {
                                Intent intent = new Intent("android.media.browse.MediaBrowserService");
                                intent.setComponent(this.a.b);
                                this.a.g = new a(this.a);
                                boolean bound = false;
                                try {
                                    bound = this.a.a.bindService(intent, this.a.g, 1);
                                } catch (Exception e) {
                                    new StringBuilder("Failed binding to service ").append(this.a.b);
                                }
                                if (!bound) {
                                    this.a.a();
                                    this.a.c.c();
                                }
                                if (MediaBrowserCompat.a) {
                                    this.a.b();
                                }
                            }
                        }
                    }
                });
                return;
            }
            throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + a(this.f) + ")");
        }

        public final void d() {
            this.f = 0;
            this.e.post(new Runnable(this) {
                final /* synthetic */ h a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    if (this.a.i != null) {
                        try {
                            this.a.h.a(this.a.i);
                        } catch (RemoteException e) {
                            new StringBuilder("RemoteException during connect for ").append(this.a.b);
                        }
                    }
                    int state = this.a.f;
                    this.a.a();
                    if (state != 0) {
                        this.a.f = state;
                    }
                    if (MediaBrowserCompat.a) {
                        this.a.b();
                    }
                }
            });
        }

        final void a() {
            if (this.g != null) {
                this.a.unbindService(this.g);
            }
            this.f = 1;
            this.g = null;
            this.h = null;
            this.i = null;
            this.e.a(null);
            this.k = null;
            this.l = null;
        }

        public final void a(Messenger callback, String root, Token session, Bundle extra) {
            if (!a(callback, "onConnect")) {
                return;
            }
            if (this.f != 2) {
                new StringBuilder("onConnect from service while mState=").append(a(this.f)).append("... ignoring");
                return;
            }
            this.k = root;
            this.l = session;
            this.m = extra;
            this.f = 3;
            if (MediaBrowserCompat.a) {
                b();
            }
            this.c.a();
            try {
                for (Entry<String, k> subscriptionEntry : this.j.entrySet()) {
                    String id = (String) subscriptionEntry.getKey();
                    k sub = (k) subscriptionEntry.getValue();
                    List<l> callbackList = sub.b();
                    List<Bundle> optionsList = sub.a();
                    for (int i = 0; i < callbackList.size(); i++) {
                        this.h.a(id, ((l) callbackList.get(i)).c, (Bundle) optionsList.get(i), this.i);
                    }
                }
            } catch (RemoteException e) {
            }
        }

        public final void a(Messenger callback) {
            new StringBuilder("onConnectFailed for ").append(this.b);
            if (!a(callback, "onConnectFailed")) {
                return;
            }
            if (this.f != 2) {
                new StringBuilder("onConnect from service while mState=").append(a(this.f)).append("... ignoring");
                return;
            }
            a();
            this.c.c();
        }

        public final void a(Messenger callback, String parentId, Bundle options) {
            if (a(callback, "onLoadChildren")) {
                if (MediaBrowserCompat.a) {
                    new StringBuilder("onLoadChildren for ").append(this.b).append(" id=").append(parentId);
                }
                k subscription = (k) this.j.get(parentId);
                if (subscription != null) {
                    subscription.a(this.a, options);
                }
            }
        }

        private static String a(int state) {
            switch (state) {
                case 0:
                    return "CONNECT_STATE_DISCONNECTING";
                case 1:
                    return "CONNECT_STATE_DISCONNECTED";
                case 2:
                    return "CONNECT_STATE_CONNECTING";
                case 3:
                    return "CONNECT_STATE_CONNECTED";
                case 4:
                    return "CONNECT_STATE_SUSPENDED";
                default:
                    return "UNKNOWN/" + state;
            }
        }

        private boolean a(Messenger callback, String funcName) {
            if (this.i == callback && this.f != 0 && this.f != 1) {
                return true;
            }
            if (!(this.f == 0 || this.f == 1)) {
                new StringBuilder().append(funcName).append(" for ").append(this.b).append(" with mCallbacksMessenger=").append(this.i).append(" this=").append(this);
            }
            return false;
        }

        final void b() {
            new StringBuilder("  mServiceComponent=").append(this.b);
            new StringBuilder("  mCallback=").append(this.c);
            new StringBuilder("  mRootHints=").append(this.d);
            new StringBuilder("  mState=").append(a(this.f));
            new StringBuilder("  mServiceConnection=").append(this.g);
            new StringBuilder("  mServiceBinderWrapper=").append(this.h);
            new StringBuilder("  mCallbacksMessenger=").append(this.i);
            new StringBuilder("  mRootId=").append(this.k);
            new StringBuilder("  mMediaSessionToken=").append(this.l);
        }

        @NonNull
        public final Token e() {
            Object obj;
            if (this.f == 3) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                return this.l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.f + ")");
        }
    }

    private static class j {
        private Messenger a;
        private Bundle b;

        public j(IBinder target, Bundle rootHints) {
            this.a = new Messenger(target);
            this.b = rootHints;
        }

        final void a(Context context, Messenger callbacksMessenger) throws RemoteException {
            Bundle data = new Bundle();
            data.putString("data_package_name", context.getPackageName());
            data.putBundle("data_root_hints", this.b);
            a(1, data, callbacksMessenger);
        }

        final void a(Messenger callbacksMessenger) throws RemoteException {
            a(2, null, callbacksMessenger);
        }

        final void a(String parentId, IBinder callbackToken, Bundle options, Messenger callbacksMessenger) throws RemoteException {
            Bundle data = new Bundle();
            data.putString("data_media_item_id", parentId);
            android.support.v4.app.e.a(data, "data_callback_token", callbackToken);
            data.putBundle("data_options", options);
            a(3, data, callbacksMessenger);
        }

        final void b(Messenger callbackMessenger) throws RemoteException {
            Bundle data = new Bundle();
            data.putBundle("data_root_hints", this.b);
            a(6, data, callbackMessenger);
        }

        final void c(Messenger callbackMessenger) throws RemoteException {
            a(7, null, callbackMessenger);
        }

        private void a(int what, Bundle data, Messenger cbMessenger) throws RemoteException {
            Message msg = Message.obtain();
            msg.what = what;
            msg.arg1 = 1;
            msg.setData(data);
            msg.replyTo = cbMessenger;
            this.a.send(msg);
        }
    }

    private static class k {
        private final List<l> a = new ArrayList();
        private final List<Bundle> b = new ArrayList();

        public final List<Bundle> a() {
            return this.b;
        }

        public final List<l> b() {
            return this.a;
        }

        public final l a(Context context, Bundle options) {
            if (options != null) {
                options.setClassLoader(context.getClassLoader());
            }
            for (int i = 0; i < this.b.size(); i++) {
                if (d.a((Bundle) this.b.get(i), options)) {
                    return (l) this.a.get(i);
                }
            }
            return null;
        }
    }

    public static abstract class l {
        WeakReference<k> a;
        private final Object b;
        private final IBinder c;

        private class a implements c {
            final /* synthetic */ l a;

            a(l lVar) {
                this.a = lVar;
            }

            public final void a(List<?> children) {
                k sub = this.a.a == null ? null : (k) this.a.a.get();
                if (sub == null) {
                    MediaItem.a(children);
                    return;
                }
                List<MediaItem> itemList = MediaItem.a(children);
                List<l> callbacks = sub.b();
                List<Bundle> optionsList = sub.a();
                for (int i = 0; i < callbacks.size(); i++) {
                    Bundle options = (Bundle) optionsList.get(i);
                    if (!(options == null || itemList == null)) {
                        int i2 = options.getInt("android.media.browse.extra.PAGE", -1);
                        int i3 = options.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                        if (i2 != -1 || i3 != -1) {
                            int i4 = i3 * i2;
                            int i5 = i4 + i3;
                            if (i2 < 0 || i3 <= 0 || i4 >= itemList.size()) {
                                List list = Collections.EMPTY_LIST;
                            } else {
                                if (i5 > itemList.size()) {
                                    i5 = itemList.size();
                                }
                                itemList.subList(i4, i5);
                            }
                        }
                    }
                }
            }
        }

        private class b extends a implements a {
            final /* synthetic */ l b;

            b(l lVar) {
                this.b = lVar;
                super(lVar);
            }

            public final void b(List<?> children) {
                MediaItem.a(children);
            }
        }

        public l() {
            if (VERSION.SDK_INT >= 26) {
                this.b = new b(new b(this));
                this.c = null;
            } else if (VERSION.SDK_INT >= 21) {
                this.b = new d(new a(this));
                this.c = new Binder();
            } else {
                this.b = null;
                this.c = new Binder();
            }
        }
    }

    public MediaBrowserCompat(Context context, ComponentName serviceComponent, b callback) {
        if (VERSION.SDK_INT >= 26) {
            this.b = new g(context, serviceComponent, callback);
        } else if (VERSION.SDK_INT >= 23) {
            this.b = new f(context, serviceComponent, callback);
        } else if (VERSION.SDK_INT >= 21) {
            this.b = new e(context, serviceComponent, callback);
        } else {
            this.b = new h(context, serviceComponent, callback);
        }
    }

    public final void a() {
        this.b.c();
    }

    public final void b() {
        this.b.d();
    }

    @NonNull
    public final Token c() {
        return this.b.e();
    }
}
