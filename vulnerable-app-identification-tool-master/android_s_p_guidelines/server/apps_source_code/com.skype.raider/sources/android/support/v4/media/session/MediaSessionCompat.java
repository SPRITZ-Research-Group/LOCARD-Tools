package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.media.RemoteControlClient.OnMetadataUpdateListener;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import android.media.session.MediaSession;
import android.media.session.MediaSession.Callback;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import com.skype.Defines;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
    static int a;
    private final b b;
    private final MediaControllerCompat c;
    private final ArrayList<Object> d = new ArrayList();

    public static abstract class a {
        private WeakReference<b> a;
        final Object b;
        private a c = null;
        private boolean d;

        private class a extends Handler {
            final /* synthetic */ a a;

            a(a aVar, Looper looper) {
                this.a = aVar;
                super(looper);
            }

            public final void handleMessage(Message msg) {
                if (msg.what == 1) {
                    this.a.a();
                }
            }
        }

        @RequiresApi(21)
        private class b implements a {
            final /* synthetic */ a a;

            b(a aVar) {
                this.a = aVar;
            }

            public final void a(String command, Bundle extras, ResultReceiver cb) {
                QueueItem item = null;
                try {
                    e impl;
                    if (command.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                        impl = (e) this.a.a.get();
                        if (impl != null) {
                            IBinder item2;
                            Bundle result = new Bundle();
                            b extraBinder = impl.c().b();
                            String str = "android.support.v4.media.session.EXTRA_BINDER";
                            if (extraBinder != null) {
                                item2 = extraBinder.asBinder();
                            }
                            android.support.v4.app.e.a(result, str, item2);
                            cb.send(0, result);
                        }
                    } else if (command.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                        extras.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        extras.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
                    } else if (command.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                        extras.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        extras.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
                        extras.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX");
                    } else if (command.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                        extras.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        extras.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
                    } else if (command.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                        impl = (e) this.a.a.get();
                        if (impl != null && impl.k != null) {
                            int index = extras.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                            if (index >= 0 && index < impl.k.size()) {
                                item = (QueueItem) impl.k.get(index);
                            }
                            if (item != null) {
                                item.a();
                            }
                        }
                    }
                } catch (BadParcelableException e) {
                }
            }

            public final boolean a(Intent mediaButtonIntent) {
                return this.a.a(mediaButtonIntent);
            }

            public final void a(Object ratingObj) {
                RatingCompat.a(ratingObj);
            }

            public final void a(String action, Bundle extras) {
                if (action.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                    extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                    extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                } else if (!action.equals("android.support.v4.media.session.action.PREPARE")) {
                    if (action.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                        extras.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                        extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    } else if (action.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                        extras.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                        extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    } else if (action.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                        extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                        extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    } else if (action.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                        extras.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
                    } else if (action.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                        extras.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
                    } else if (action.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED")) {
                        extras.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED");
                    } else if (action.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                        extras.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");
                    } else if (action.equals("android.support.v4.media.session.action.SET_RATING")) {
                        extras.setClassLoader(RatingCompat.class.getClassLoader());
                        extras.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
                        extras.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    }
                }
            }
        }

        @RequiresApi(23)
        private class c extends b implements android.support.v4.media.session.e.a {
            final /* synthetic */ a b;

            c(a aVar) {
                this.b = aVar;
                super(aVar);
            }
        }

        @RequiresApi(24)
        private class d extends c implements android.support.v4.media.session.f.a {
            final /* synthetic */ a c;

            d(a aVar) {
                this.c = aVar;
                super(aVar);
            }
        }

        public a() {
            if (VERSION.SDK_INT >= 24) {
                this.b = new b(new d(this));
            } else if (VERSION.SDK_INT >= 23) {
                this.b = new b(new c(this));
            } else if (VERSION.SDK_INT >= 21) {
                this.b = new b(new b(this));
            } else {
                this.b = null;
            }
        }

        public boolean a(Intent mediaButtonEvent) {
            b impl = (b) this.a.get();
            if (impl == null || this.c == null) {
                return false;
            }
            KeyEvent keyEvent = (KeyEvent) mediaButtonEvent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent == null || keyEvent.getAction() != 0) {
                return false;
            }
            switch (keyEvent.getKeyCode()) {
                case 79:
                case 85:
                    if (keyEvent.getRepeatCount() > 0) {
                        a();
                        return true;
                    } else if (this.d) {
                        this.c.removeMessages(1);
                        this.d = false;
                        PlaybackStateCompat state = impl.d();
                        if (state == null) {
                            return true;
                        }
                        long j = state.e;
                        return true;
                    } else {
                        this.d = true;
                        this.c.sendEmptyMessageDelayed(1, (long) ViewConfiguration.getDoubleTapTimeout());
                        return true;
                    }
                default:
                    a();
                    return false;
            }
        }

        private void a() {
            if (this.d) {
                this.d = false;
                this.c.removeMessages(1);
                b impl = (b) this.a.get();
                if (impl != null) {
                    PlaybackStateCompat state = impl.d();
                    if (state != null) {
                        long j = state.e;
                    }
                    if (state != null) {
                        int i = state.a;
                    }
                }
            }
        }

        static /* synthetic */ void a(a x0, b x1, Handler x2) {
            x0.a = new WeakReference(x1);
            if (x0.c != null) {
                x0.c.removeCallbacksAndMessages(null);
            }
            x0.c = new a(x0, x2.getLooper());
        }
    }

    public static final class QueueItem implements Parcelable {
        public static final Creator<QueueItem> CREATOR = new Creator<QueueItem>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new QueueItem[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }
        };
        private final MediaDescriptionCompat a;
        private final long b;
        private Object c;

        private QueueItem(Object queueItem, MediaDescriptionCompat description, long id) {
            if (description == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            } else if (id == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            } else {
                this.a = description;
                this.b = id;
                this.c = queueItem;
            }
        }

        QueueItem(Parcel in) {
            this.a = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(in);
            this.b = in.readLong();
        }

        public final MediaDescriptionCompat a() {
            return this.a;
        }

        public final void writeToParcel(Parcel dest, int flags) {
            this.a.writeToParcel(dest, flags);
            dest.writeLong(this.b);
        }

        public final int describeContents() {
            return 0;
        }

        public static List<QueueItem> a(List<?> itemList) {
            if (itemList == null || VERSION.SDK_INT < 21) {
                return null;
            }
            List<QueueItem> items = new ArrayList();
            for (Object itemObj : itemList) {
                Object obj;
                if (itemObj == null || VERSION.SDK_INT < 21) {
                    obj = null;
                } else {
                    obj = new QueueItem(itemObj, MediaDescriptionCompat.a(((android.media.session.MediaSession.QueueItem) itemObj).getDescription()), ((android.media.session.MediaSession.QueueItem) itemObj).getQueueId());
                }
                items.add(obj);
            }
            return items;
        }

        public final String toString() {
            return "MediaSession.QueueItem {Description=" + this.a + ", Id=" + this.b + " }";
        }
    }

    static final class ResultReceiverWrapper implements Parcelable {
        public static final Creator<ResultReceiverWrapper> CREATOR = new Creator<ResultReceiverWrapper>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }
        };
        private ResultReceiver a;

        ResultReceiverWrapper(Parcel in) {
            this.a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(in);
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel dest, int flags) {
            this.a.writeToParcel(dest, flags);
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlags {
    }

    public static final class Token implements Parcelable {
        public static final Creator<Token> CREATOR = new Creator<Token>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Token[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                Object readParcelable;
                if (VERSION.SDK_INT >= 21) {
                    readParcelable = parcel.readParcelable(null);
                } else {
                    readParcelable = parcel.readStrongBinder();
                }
                return new Token(readParcelable);
            }
        };
        private final Object a;
        private final b b;

        Token(Object inner) {
            this(inner, null);
        }

        Token(Object inner, b extraBinder) {
            this.a = inner;
            this.b = extraBinder;
        }

        public static Token a(Object token) {
            return a(token, null);
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public static Token a(Object token, b extraBinder) {
            if (token == null || VERSION.SDK_INT < 21) {
                return null;
            }
            if (token instanceof android.media.session.MediaSession.Token) {
                return new Token(token, extraBinder);
            }
            throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel dest, int flags) {
            if (VERSION.SDK_INT >= 21) {
                dest.writeParcelable((Parcelable) this.a, flags);
            } else {
                dest.writeStrongBinder((IBinder) this.a);
            }
        }

        public final int hashCode() {
            if (this.a == null) {
                return 0;
            }
            return this.a.hashCode();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token other = (Token) obj;
            if (this.a == null) {
                if (other.a != null) {
                    return false;
                }
                return true;
            } else if (other.a == null) {
                return false;
            } else {
                return this.a.equals(other.a);
            }
        }

        public final Object a() {
            return this.a;
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public final b b() {
            return this.b;
        }
    }

    interface b {
        void a();

        void a(PendingIntent pendingIntent);

        void a(a aVar, Handler handler);

        void a(PlaybackStateCompat playbackStateCompat);

        void b();

        Token c();

        PlaybackStateCompat d();
    }

    static class f implements b {
        private final PendingIntent A;
        private final b B;
        private final Token C;
        private c D;
        private boolean E = false;
        private boolean F = false;
        private android.support.v4.media.VolumeProviderCompat.a G = new android.support.v4.media.VolumeProviderCompat.a(this) {
            final /* synthetic */ f a;

            {
                this.a = this$0;
            }
        };
        final String a;
        final String b;
        final AudioManager c;
        final RemoteControlClient d;
        final Object e = new Object();
        final RemoteCallbackList<a> f = new RemoteCallbackList();
        boolean g = false;
        boolean h = false;
        volatile a i;
        int j;
        MediaMetadataCompat k;
        PlaybackStateCompat l;
        PendingIntent m;
        List<QueueItem> n;
        CharSequence o;
        int p;
        boolean q;
        int r;
        int s;
        boolean t;
        Bundle u;
        int v;
        int w;
        VolumeProviderCompat x;
        private final Context y;
        private final ComponentName z;

        private static final class a {
            public final String a;
            public final Bundle b;
            public final ResultReceiver c;

            public a(String command, Bundle extras, ResultReceiver stub) {
                this.a = command;
                this.b = extras;
                this.c = stub;
            }
        }

        class b extends android.support.v4.media.session.b.a {
            final /* synthetic */ f a;

            b(f this$0) {
                this.a = this$0;
            }

            public final void a(String command, Bundle args, ResultReceiverWrapper cb) {
                this.a.a(1, new a(command, args, cb.a));
            }

            public final boolean a(KeyEvent mediaButton) {
                boolean handlesMediaButtons = (this.a.j & 1) != 0;
                if (handlesMediaButtons) {
                    this.a.a(21, (Object) mediaButton);
                }
                return handlesMediaButtons;
            }

            public final void a(a cb) {
                if (this.a.g) {
                    try {
                        cb.a();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
                this.a.f.register(cb);
            }

            public final void b(a cb) {
                this.a.f.unregister(cb);
            }

            public final String b() {
                return this.a.a;
            }

            public final String c() {
                return this.a.b;
            }

            public final PendingIntent d() {
                PendingIntent pendingIntent;
                synchronized (this.a.e) {
                    pendingIntent = this.a.m;
                }
                return pendingIntent;
            }

            public final long e() {
                long j;
                synchronized (this.a.e) {
                    j = (long) this.a.j;
                }
                return j;
            }

            public final ParcelableVolumeInfo f() {
                int volumeType;
                int stream;
                int controlType;
                int max;
                int current;
                synchronized (this.a.e) {
                    volumeType = this.a.v;
                    stream = this.a.w;
                    VolumeProviderCompat vp = this.a.x;
                    if (volumeType == 2) {
                        controlType = vp.b();
                        max = vp.c();
                        current = vp.a();
                    } else {
                        controlType = 2;
                        max = this.a.c.getStreamMaxVolume(stream);
                        current = this.a.c.getStreamVolume(stream);
                    }
                }
                return new ParcelableVolumeInfo(volumeType, stream, controlType, max, current);
            }

            public final void a(int direction, int flags, String packageName) {
                this.a.b(direction, flags);
            }

            public final void b(int value, int flags, String packageName) {
                this.a.c(value, flags);
            }

            public final void q() throws RemoteException {
                this.a.a(3);
            }

            public final void a(String mediaId, Bundle extras) throws RemoteException {
                this.a.a(4, (Object) mediaId, extras);
            }

            public final void b(String query, Bundle extras) throws RemoteException {
                this.a.a(5, (Object) query, extras);
            }

            public final void a(Uri uri, Bundle extras) throws RemoteException {
                this.a.a(6, (Object) uri, extras);
            }

            public final void r() throws RemoteException {
                this.a.a(7);
            }

            public final void c(String mediaId, Bundle extras) throws RemoteException {
                this.a.a(8, (Object) mediaId, extras);
            }

            public final void d(String query, Bundle extras) throws RemoteException {
                this.a.a(9, (Object) query, extras);
            }

            public final void b(Uri uri, Bundle extras) throws RemoteException {
                this.a.a(10, (Object) uri, extras);
            }

            public final void a(long id) {
                this.a.a(11, Long.valueOf(id));
            }

            public final void s() throws RemoteException {
                this.a.a(12);
            }

            public final void t() throws RemoteException {
                this.a.a(13);
            }

            public final void u() throws RemoteException {
                this.a.a(14);
            }

            public final void v() throws RemoteException {
                this.a.a(15);
            }

            public final void w() throws RemoteException {
                this.a.a(16);
            }

            public final void x() throws RemoteException {
                this.a.a(17);
            }

            public final void b(long pos) throws RemoteException {
                this.a.a(18, Long.valueOf(pos));
            }

            public final void a(RatingCompat rating) throws RemoteException {
                this.a.a(19, (Object) rating);
            }

            public final void a(RatingCompat rating, Bundle extras) throws RemoteException {
                this.a.a(31, (Object) rating, extras);
            }

            public final void a(boolean enabled) throws RemoteException {
                this.a.a(29, Boolean.valueOf(enabled));
            }

            public final void b(int repeatMode) throws RemoteException {
                this.a.a(23, repeatMode);
            }

            public final void b(boolean enabled) throws RemoteException {
                this.a.a(24, Boolean.valueOf(enabled));
            }

            public final void c(int shuffleMode) throws RemoteException {
                this.a.a(30, shuffleMode);
            }

            public final void e(String action, Bundle args) throws RemoteException {
                this.a.a(20, (Object) action, args);
            }

            public final MediaMetadataCompat g() {
                return this.a.k;
            }

            public final PlaybackStateCompat h() {
                PlaybackStateCompat state;
                MediaMetadataCompat metadata;
                synchronized (this.a.e) {
                    state = this.a.l;
                    metadata = this.a.k;
                }
                return MediaSessionCompat.a(state, metadata);
            }

            public final List<QueueItem> i() {
                List<QueueItem> list;
                synchronized (this.a.e) {
                    list = this.a.n;
                }
                return list;
            }

            public final void a(MediaDescriptionCompat description) {
                this.a.a(25, (Object) description);
            }

            public final void a(MediaDescriptionCompat description, int index) {
                this.a.a(26, (Object) description, index);
            }

            public final void b(MediaDescriptionCompat description) {
                this.a.a(27, (Object) description);
            }

            public final void a(int index) {
                this.a.a(28, index);
            }

            public final CharSequence j() {
                return this.a.o;
            }

            public final Bundle k() {
                Bundle bundle;
                synchronized (this.a.e) {
                    bundle = this.a.u;
                }
                return bundle;
            }

            public final int l() {
                return this.a.p;
            }

            public final boolean m() {
                return this.a.q;
            }

            public final int n() {
                return this.a.r;
            }

            public final boolean o() {
                return this.a.t;
            }

            public final int p() {
                return this.a.s;
            }

            public final boolean a() {
                return (this.a.j & 2) != 0;
            }
        }

        class c extends Handler {
            final /* synthetic */ f a;

            public c(f this$0, Looper looper) {
                this.a = this$0;
                super(looper);
            }

            public final void handleMessage(Message msg) {
                a cb = this.a.i;
                if (cb != null) {
                    Object obj;
                    int i;
                    switch (msg.what) {
                        case 1:
                            obj = msg.obj;
                            return;
                        case 2:
                            this.a.b(msg.arg1, 0);
                            return;
                        case 4:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 5:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 6:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 8:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 9:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 10:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 11:
                            ((Long) msg.obj).longValue();
                            return;
                        case 18:
                            ((Long) msg.obj).longValue();
                            return;
                        case 19:
                            obj = msg.obj;
                            return;
                        case 20:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        case 21:
                            KeyEvent keyEvent = msg.obj;
                            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                            if (!cb.a(intent) && keyEvent != null && keyEvent.getAction() == 0) {
                                if (this.a.l != null) {
                                    long j = this.a.l.e;
                                }
                                keyEvent.getKeyCode();
                                return;
                            }
                            return;
                        case 22:
                            this.a.c(msg.arg1, 0);
                            return;
                        case 23:
                            i = msg.arg1;
                            return;
                        case 24:
                            ((Boolean) msg.obj).booleanValue();
                            return;
                        case 25:
                            obj = msg.obj;
                            return;
                        case 26:
                            obj = msg.obj;
                            i = msg.arg1;
                            return;
                        case 27:
                            obj = msg.obj;
                            return;
                        case 28:
                            if (this.a.n != null) {
                                QueueItem item = (msg.arg1 < 0 || msg.arg1 >= this.a.n.size()) ? null : (QueueItem) this.a.n.get(msg.arg1);
                                if (item != null) {
                                    item.a();
                                    return;
                                }
                                return;
                            }
                            return;
                        case 29:
                            ((Boolean) msg.obj).booleanValue();
                            return;
                        case 30:
                            i = msg.arg1;
                            return;
                        case 31:
                            obj = msg.obj;
                            msg.getData();
                            return;
                        default:
                            return;
                    }
                }
            }
        }

        public f(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
            if (mbrComponent == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            }
            this.y = context;
            this.a = context.getPackageName();
            this.c = (AudioManager) context.getSystemService("audio");
            this.b = tag;
            this.z = mbrComponent;
            this.A = mbrIntent;
            this.B = new b(this);
            this.C = new Token(this.B);
            this.p = 0;
            this.v = 1;
            this.w = 3;
            this.d = new RemoteControlClient(mbrIntent);
        }

        public void a(a callback, Handler handler) {
            this.i = callback;
            if (callback != null) {
                if (handler == null) {
                    handler = new Handler();
                }
                synchronized (this.e) {
                    if (this.D != null) {
                        this.D.removeCallbacksAndMessages(null);
                    }
                    this.D = new c(this, handler.getLooper());
                    a.a(this.i, this, handler);
                }
            }
        }

        final void a(int what) {
            a(what, null, null);
        }

        final void a(int what, int arg1) {
            a(what, null, arg1);
        }

        final void a(int what, Object obj) {
            a(what, obj, null);
        }

        final void a(int what, Object obj, int arg1) {
            synchronized (this.e) {
                if (this.D != null) {
                    this.D.obtainMessage(what, arg1, 0, obj).sendToTarget();
                }
            }
        }

        final void a(int what, Object obj, Bundle extras) {
            synchronized (this.e) {
                if (this.D != null) {
                    Message obtainMessage = this.D.obtainMessage(what, obj);
                    obtainMessage.setData(extras);
                    obtainMessage.sendToTarget();
                }
            }
        }

        public final void a() {
            synchronized (this.e) {
                this.j = 1;
            }
            e();
        }

        public final void b() {
            if (true != this.h) {
                this.h = true;
                if (e()) {
                    MediaMetadataCompat a;
                    MediaMetadataCompat mediaMetadataCompat = this.k;
                    if (mediaMetadataCompat != null) {
                        a = new android.support.v4.media.MediaMetadataCompat.a(mediaMetadataCompat, MediaSessionCompat.a).a();
                    } else {
                        a = mediaMetadataCompat;
                    }
                    synchronized (this.e) {
                        this.k = a;
                    }
                    for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                        try {
                            ((a) this.f.getBroadcastItem(beginBroadcast)).a(a);
                        } catch (RemoteException e) {
                        }
                    }
                    this.f.finishBroadcast();
                    if (this.h) {
                        Bundle bundle;
                        if (a == null) {
                            bundle = null;
                        } else {
                            bundle = a.a();
                        }
                        a(bundle).apply();
                    }
                    a(this.l);
                }
            }
        }

        public final Token c() {
            return this.C;
        }

        public final void a(PlaybackStateCompat state) {
            synchronized (this.e) {
                this.l = state;
            }
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((a) this.f.getBroadcastItem(beginBroadcast)).a(state);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
            if (!this.h) {
                return;
            }
            if (state == null) {
                this.d.setPlaybackState(0);
                this.d.setTransportControlFlags(0);
                return;
            }
            b(state);
            this.d.setTransportControlFlags(a(state.e));
        }

        public final PlaybackStateCompat d() {
            PlaybackStateCompat playbackStateCompat;
            synchronized (this.e) {
                playbackStateCompat = this.l;
            }
            return playbackStateCompat;
        }

        void b(PlaybackStateCompat state) {
            this.d.setPlaybackState(b(state.a));
        }

        static int b(int state) {
            switch (state) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        int a(long actions) {
            int transportControlFlags = 0;
            if ((1 & actions) != 0) {
                transportControlFlags = 32;
            }
            if ((2 & actions) != 0) {
                transportControlFlags |= 16;
            }
            if ((4 & actions) != 0) {
                transportControlFlags |= 4;
            }
            if ((8 & actions) != 0) {
                transportControlFlags |= 2;
            }
            if ((16 & actions) != 0) {
                transportControlFlags |= 1;
            }
            if ((32 & actions) != 0) {
                transportControlFlags |= 128;
            }
            if ((64 & actions) != 0) {
                transportControlFlags |= 64;
            }
            if ((512 & actions) != 0) {
                return transportControlFlags | 8;
            }
            return transportControlFlags;
        }

        MetadataEditor a(Bundle metadata) {
            MetadataEditor editor = this.d.editMetadata(true);
            if (metadata != null) {
                Bitmap art;
                if (metadata.containsKey("android.media.metadata.ART")) {
                    art = (Bitmap) metadata.getParcelable("android.media.metadata.ART");
                    if (art != null) {
                        art = art.copy(art.getConfig(), false);
                    }
                    editor.putBitmap(100, art);
                } else if (metadata.containsKey("android.media.metadata.ALBUM_ART")) {
                    art = (Bitmap) metadata.getParcelable("android.media.metadata.ALBUM_ART");
                    if (art != null) {
                        art = art.copy(art.getConfig(), false);
                    }
                    editor.putBitmap(100, art);
                }
                if (metadata.containsKey("android.media.metadata.ALBUM")) {
                    editor.putString(1, metadata.getString("android.media.metadata.ALBUM"));
                }
                if (metadata.containsKey("android.media.metadata.ALBUM_ARTIST")) {
                    editor.putString(13, metadata.getString("android.media.metadata.ALBUM_ARTIST"));
                }
                if (metadata.containsKey("android.media.metadata.ARTIST")) {
                    editor.putString(2, metadata.getString("android.media.metadata.ARTIST"));
                }
                if (metadata.containsKey("android.media.metadata.AUTHOR")) {
                    editor.putString(3, metadata.getString("android.media.metadata.AUTHOR"));
                }
                if (metadata.containsKey("android.media.metadata.COMPILATION")) {
                    editor.putString(15, metadata.getString("android.media.metadata.COMPILATION"));
                }
                if (metadata.containsKey("android.media.metadata.COMPOSER")) {
                    editor.putString(4, metadata.getString("android.media.metadata.COMPOSER"));
                }
                if (metadata.containsKey("android.media.metadata.DATE")) {
                    editor.putString(5, metadata.getString("android.media.metadata.DATE"));
                }
                if (metadata.containsKey("android.media.metadata.DISC_NUMBER")) {
                    editor.putLong(14, metadata.getLong("android.media.metadata.DISC_NUMBER"));
                }
                if (metadata.containsKey("android.media.metadata.DURATION")) {
                    editor.putLong(9, metadata.getLong("android.media.metadata.DURATION"));
                }
                if (metadata.containsKey("android.media.metadata.GENRE")) {
                    editor.putString(6, metadata.getString("android.media.metadata.GENRE"));
                }
                if (metadata.containsKey("android.media.metadata.TITLE")) {
                    editor.putString(7, metadata.getString("android.media.metadata.TITLE"));
                }
                if (metadata.containsKey("android.media.metadata.TRACK_NUMBER")) {
                    editor.putLong(0, metadata.getLong("android.media.metadata.TRACK_NUMBER"));
                }
                if (metadata.containsKey("android.media.metadata.WRITER")) {
                    editor.putString(11, metadata.getString("android.media.metadata.WRITER"));
                }
            }
            return editor;
        }

        public final void a(PendingIntent mbr) {
        }

        private boolean e() {
            if (this.h) {
                if (!this.E && (this.j & 1) != 0) {
                    a(this.A, this.z);
                    this.E = true;
                } else if (this.E && (this.j & 1) == 0) {
                    b(this.A, this.z);
                    this.E = false;
                }
                if (!this.F && (this.j & 2) != 0) {
                    this.c.registerRemoteControlClient(this.d);
                    this.F = true;
                    return true;
                } else if (!this.F || (this.j & 2) != 0) {
                    return false;
                } else {
                    this.d.setPlaybackState(0);
                    this.c.unregisterRemoteControlClient(this.d);
                    this.F = false;
                    return false;
                }
            }
            if (this.E) {
                b(this.A, this.z);
                this.E = false;
            }
            if (!this.F) {
                return false;
            }
            this.d.setPlaybackState(0);
            this.c.unregisterRemoteControlClient(this.d);
            this.F = false;
            return false;
        }

        void a(PendingIntent mbrIntent, ComponentName mbrComponent) {
            this.c.registerMediaButtonEventReceiver(mbrComponent);
        }

        void b(PendingIntent mbrIntent, ComponentName mbrComponent) {
            this.c.unregisterMediaButtonEventReceiver(mbrComponent);
        }

        final void b(int r3, int r4) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.utils.BlockUtils.getNextBlock(BlockUtils.java:289)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:143)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:654)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:654)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r2 = this;
            r0 = r2.v;
            r1 = 2;
            if (r0 != r1) goto L_0x000a;
        L_0x0005:
            r0 = r2.x;
            if (r0 == 0) goto L_0x0009;
        L_0x0009:
            return;
        L_0x000a:
            r0 = r2.c;
            r1 = r2.w;
            r0.adjustStreamVolume(r1, r3, r4);
            goto L_0x0009;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.f.b(int, int):void");
        }

        final void c(int r3, int r4) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.utils.BlockUtils.getNextBlock(BlockUtils.java:289)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:143)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:654)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:654)
	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:90)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r2 = this;
            r0 = r2.v;
            r1 = 2;
            if (r0 != r1) goto L_0x000a;
        L_0x0005:
            r0 = r2.x;
            if (r0 == 0) goto L_0x0009;
        L_0x0009:
            return;
        L_0x000a:
            r0 = r2.c;
            r1 = r2.w;
            r0.setStreamVolume(r1, r3, r4);
            goto L_0x0009;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompat.f.c(int, int):void");
        }
    }

    @RequiresApi(18)
    static class c extends f {
        private static boolean y = true;

        c(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
            super(context, tag, mbrComponent, mbrIntent);
        }

        public void a(a callback, Handler handler) {
            super.a(callback, handler);
            if (callback == null) {
                this.d.setPlaybackPositionUpdateListener(null);
                return;
            }
            this.d.setPlaybackPositionUpdateListener(new OnPlaybackPositionUpdateListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = this$0;
                }

                public final void onPlaybackPositionUpdate(long newPositionMs) {
                    this.a.a(18, (Object) Long.valueOf(newPositionMs));
                }
            });
        }

        final void b(PlaybackStateCompat state) {
            long position = state.b;
            float speed = state.d;
            long updateTime = state.h;
            long currTime = SystemClock.elapsedRealtime();
            if (state.a == 3 && position > 0) {
                long diff = 0;
                if (updateTime > 0) {
                    diff = currTime - updateTime;
                    if (speed > 0.0f && speed != 1.0f) {
                        diff = (long) (((float) diff) * speed);
                    }
                }
                position += diff;
            }
            this.d.setPlaybackState(f.b(state.a), position, speed);
        }

        int a(long actions) {
            int transportControlFlags = super.a(actions);
            if ((256 & actions) != 0) {
                return transportControlFlags | Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
            }
            return transportControlFlags;
        }

        final void a(PendingIntent mbrIntent, ComponentName mbrComponent) {
            if (y) {
                try {
                    this.c.registerMediaButtonEventReceiver(mbrIntent);
                } catch (NullPointerException e) {
                    y = false;
                }
            }
            if (!y) {
                super.a(mbrIntent, mbrComponent);
            }
        }

        final void b(PendingIntent mbrIntent, ComponentName mbrComponent) {
            if (y) {
                this.c.unregisterMediaButtonEventReceiver(mbrIntent);
            } else {
                super.b(mbrIntent, mbrComponent);
            }
        }
    }

    @RequiresApi(19)
    static class d extends c {
        d(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
            super(context, tag, mbrComponent, mbrIntent);
        }

        public final void a(a callback, Handler handler) {
            super.a(callback, handler);
            if (callback == null) {
                this.d.setMetadataUpdateListener(null);
                return;
            }
            this.d.setMetadataUpdateListener(new OnMetadataUpdateListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = this$0;
                }

                public final void onMetadataUpdate(int key, Object newValue) {
                    if (key == 268435457 && (newValue instanceof Rating)) {
                        this.a.a(19, (Object) RatingCompat.a(newValue));
                    }
                }
            });
        }

        final int a(long actions) {
            int transportControlFlags = super.a(actions);
            if ((128 & actions) != 0) {
                return transportControlFlags | 512;
            }
            return transportControlFlags;
        }

        final MetadataEditor a(Bundle metadata) {
            long j;
            MetadataEditor editor = super.a(metadata);
            if (this.l == null) {
                j = 0;
            } else {
                j = this.l.e;
            }
            if ((j & 128) != 0) {
                editor.addEditableKey(268435457);
            }
            if (metadata != null) {
                if (metadata.containsKey("android.media.metadata.YEAR")) {
                    editor.putLong(8, metadata.getLong("android.media.metadata.YEAR"));
                }
                if (metadata.containsKey("android.media.metadata.RATING")) {
                    editor.putObject(101, metadata.getParcelable("android.media.metadata.RATING"));
                }
                if (metadata.containsKey("android.media.metadata.USER_RATING")) {
                    editor.putObject(268435457, metadata.getParcelable("android.media.metadata.USER_RATING"));
                }
            }
            return editor;
        }
    }

    @RequiresApi(21)
    static class e implements b {
        int a;
        boolean b;
        int c;
        boolean d;
        int e;
        private final Object f;
        private final Token g;
        private boolean h = false;
        private final RemoteCallbackList<a> i = new RemoteCallbackList();
        private PlaybackStateCompat j;
        private List<QueueItem> k;
        private MediaMetadataCompat l;

        class a extends android.support.v4.media.session.b.a {
            final /* synthetic */ e a;

            a(e this$0) {
                this.a = this$0;
            }

            public final void a(String command, Bundle args, ResultReceiverWrapper cb) {
                throw new AssertionError();
            }

            public final boolean a(KeyEvent mediaButton) {
                throw new AssertionError();
            }

            public final void a(a cb) {
                if (!this.a.h) {
                    this.a.i.register(cb);
                }
            }

            public final void b(a cb) {
                this.a.i.unregister(cb);
            }

            public final String b() {
                throw new AssertionError();
            }

            public final String c() {
                throw new AssertionError();
            }

            public final PendingIntent d() {
                throw new AssertionError();
            }

            public final long e() {
                throw new AssertionError();
            }

            public final ParcelableVolumeInfo f() {
                throw new AssertionError();
            }

            public final void a(int direction, int flags, String packageName) {
                throw new AssertionError();
            }

            public final void b(int value, int flags, String packageName) {
                throw new AssertionError();
            }

            public final void q() throws RemoteException {
                throw new AssertionError();
            }

            public final void a(String mediaId, Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void b(String query, Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(Uri uri, Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void r() throws RemoteException {
                throw new AssertionError();
            }

            public final void c(String mediaId, Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void d(String query, Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void b(Uri uri, Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(long id) {
                throw new AssertionError();
            }

            public final void s() throws RemoteException {
                throw new AssertionError();
            }

            public final void t() throws RemoteException {
                throw new AssertionError();
            }

            public final void u() throws RemoteException {
                throw new AssertionError();
            }

            public final void v() throws RemoteException {
                throw new AssertionError();
            }

            public final void w() throws RemoteException {
                throw new AssertionError();
            }

            public final void x() throws RemoteException {
                throw new AssertionError();
            }

            public final void b(long pos) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(RatingCompat rating) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(RatingCompat rating, Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public final void a(boolean enabled) throws RemoteException {
                throw new AssertionError();
            }

            public final void b(int repeatMode) throws RemoteException {
                throw new AssertionError();
            }

            public final void b(boolean enabled) throws RemoteException {
                throw new AssertionError();
            }

            public final void c(int shuffleMode) throws RemoteException {
                throw new AssertionError();
            }

            public final void e(String action, Bundle args) throws RemoteException {
                throw new AssertionError();
            }

            public final MediaMetadataCompat g() {
                throw new AssertionError();
            }

            public final PlaybackStateCompat h() {
                return MediaSessionCompat.a(this.a.j, this.a.l);
            }

            public final List<QueueItem> i() {
                return null;
            }

            public final void a(MediaDescriptionCompat descriptionCompat) {
                throw new AssertionError();
            }

            public final void a(MediaDescriptionCompat descriptionCompat, int index) {
                throw new AssertionError();
            }

            public final void b(MediaDescriptionCompat description) {
                throw new AssertionError();
            }

            public final void a(int index) {
                throw new AssertionError();
            }

            public final CharSequence j() {
                throw new AssertionError();
            }

            public final Bundle k() {
                throw new AssertionError();
            }

            public final int l() {
                return this.a.a;
            }

            public final boolean m() {
                return this.a.b;
            }

            public final int n() {
                return this.a.c;
            }

            public final boolean o() {
                return this.a.d;
            }

            public final int p() {
                return this.a.e;
            }

            public final boolean a() {
                throw new AssertionError();
            }
        }

        public e(Context context, String tag) {
            this.f = new MediaSession(context, tag);
            this.g = new Token(((MediaSession) this.f).getSessionToken(), new a(this));
        }

        public final void a(a callback, Handler handler) {
            Object obj;
            Object obj2 = this.f;
            if (callback == null) {
                obj = null;
            } else {
                obj = callback.b;
            }
            ((MediaSession) obj2).setCallback((Callback) obj, handler);
            if (callback != null) {
                a.a(callback, this, handler);
            }
        }

        public final void a() {
            ((MediaSession) this.f).setFlags(1);
        }

        public final void b() {
            ((MediaSession) this.f).setActive(true);
        }

        public final Token c() {
            return this.g;
        }

        public final void a(PlaybackStateCompat state) {
            Object obj;
            this.j = state;
            for (int i = this.i.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    ((a) this.i.getBroadcastItem(i)).a(state);
                } catch (RemoteException e) {
                }
            }
            this.i.finishBroadcast();
            Object obj2 = this.f;
            if (state == null) {
                obj = null;
            } else {
                obj = state.a();
            }
            ((MediaSession) obj2).setPlaybackState((PlaybackState) obj);
        }

        public final PlaybackStateCompat d() {
            return this.j;
        }

        public final void a(PendingIntent mbr) {
            ((MediaSession) this.f).setMediaButtonReceiver(mbr);
        }
    }

    static /* synthetic */ PlaybackStateCompat a(PlaybackStateCompat x0, MediaMetadataCompat x1) {
        long j = -1;
        if (x0 == null || x0.b == -1) {
            return x0;
        }
        if (x0.a != 3 && x0.a != 4 && x0.a != 5) {
            return x0;
        }
        long j2 = x0.h;
        if (j2 <= 0) {
            return x0;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        j2 = ((long) (x0.d * ((float) (elapsedRealtime - j2)))) + x0.b;
        if (x1 != null && x1.a("android.media.metadata.DURATION")) {
            j = x1.b("android.media.metadata.DURATION");
        }
        if (j < 0 || j2 <= j) {
            if (j2 < 0) {
                j = 0;
            } else {
                j = j2;
            }
        }
        return new android.support.v4.media.session.PlaybackStateCompat.a(x0).a(x0.a, j, x0.d, elapsedRealtime).b();
    }

    public MediaSessionCompat(Context context, String tag, ComponentName mbrComponent) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        } else if (TextUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        } else {
            Intent mediaButtonIntent = new Intent("android.intent.action.MEDIA_BUTTON");
            mediaButtonIntent.setComponent(mbrComponent);
            PendingIntent mbrIntent = PendingIntent.getBroadcast(context, 0, mediaButtonIntent, 0);
            if (VERSION.SDK_INT >= 21) {
                this.b = new e(context, tag);
                a(new a(this) {
                    final /* synthetic */ MediaSessionCompat a;

                    {
                        this.a = this$0;
                    }
                });
                this.b.a(mbrIntent);
            } else if (VERSION.SDK_INT >= 19) {
                this.b = new d(context, tag, mbrComponent, mbrIntent);
            } else if (VERSION.SDK_INT >= 18) {
                this.b = new c(context, tag, mbrComponent, mbrIntent);
            } else {
                this.b = new f(context, tag, mbrComponent, mbrIntent);
            }
            this.c = new MediaControllerCompat(context, this);
            if (a == 0) {
                a = (int) TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics());
            }
        }
    }

    public final void a(PendingIntent mbr) {
        this.b.a(mbr);
    }

    public final void a() {
        this.b.a();
    }

    public final void b() {
        this.b.b();
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public final Token c() {
        return this.b.c();
    }

    public final void a(PlaybackStateCompat state) {
        this.b.a(state);
    }

    public final void a(a callback) {
        this.b.a(callback, new Handler());
    }
}
