package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
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
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.h;
import androidx.media.d;
import defpackage.bu;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;

    class CallbackHandler extends Handler {
        private final WeakReference<MediaBrowserServiceCallbackImpl> mCallbackImplRef;
        private WeakReference<Messenger> mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl) {
            this.mCallbackImplRef = new WeakReference(mediaBrowserServiceCallbackImpl);
        }

        public void handleMessage(android.os.Message r10) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.CallbackHandler.handleMessage(android.os.Message):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r9 = this;
            r0 = r9.mCallbacksMessengerRef;
            if (r0 == 0) goto L_0x00a6;
        L_0x0004:
            r0 = r9.mCallbacksMessengerRef;
            r0 = r0.get();
            if (r0 == 0) goto L_0x00a6;
        L_0x000c:
            r0 = r9.mCallbackImplRef;
            r0 = r0.get();
            if (r0 != 0) goto L_0x0016;
        L_0x0014:
            goto L_0x00a6;
        L_0x0016:
            r0 = r10.getData();
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r0);
            r1 = r9.mCallbackImplRef;
            r1 = r1.get();
            r1 = (android.support.v4.media.MediaBrowserCompat.MediaBrowserServiceCallbackImpl) r1;
            r2 = r9.mCallbacksMessengerRef;
            r2 = r2.get();
            r8 = r2;
            r8 = (android.os.Messenger) r8;
            r2 = r10.what;	 Catch:{ BadParcelableException -> 0x0095 }
            switch(r2) {
                case 1: goto L_0x005e;
                case 2: goto L_0x005a;
                case 3: goto L_0x0036;
                default: goto L_0x0033;
            };	 Catch:{ BadParcelableException -> 0x0095 }
        L_0x0033:
            r0 = "MediaBrowserCompat";	 Catch:{ BadParcelableException -> 0x0095 }
            goto L_0x0079;	 Catch:{ BadParcelableException -> 0x0095 }
        L_0x0036:
            r2 = "data_options";	 Catch:{ BadParcelableException -> 0x0095 }
            r6 = r0.getBundle(r2);	 Catch:{ BadParcelableException -> 0x0095 }
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r6);	 Catch:{ BadParcelableException -> 0x0095 }
            r2 = "data_notify_children_changed_options";	 Catch:{ BadParcelableException -> 0x0095 }
            r7 = r0.getBundle(r2);	 Catch:{ BadParcelableException -> 0x0095 }
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r7);	 Catch:{ BadParcelableException -> 0x0095 }
            r2 = "data_media_item_id";	 Catch:{ BadParcelableException -> 0x0095 }
            r4 = r0.getString(r2);	 Catch:{ BadParcelableException -> 0x0095 }
            r2 = "data_media_item_list";	 Catch:{ BadParcelableException -> 0x0095 }
            r5 = r0.getParcelableArrayList(r2);	 Catch:{ BadParcelableException -> 0x0095 }
            r2 = r1;	 Catch:{ BadParcelableException -> 0x0095 }
            r3 = r8;	 Catch:{ BadParcelableException -> 0x0095 }
            r2.onLoadChildren(r3, r4, r5, r6, r7);	 Catch:{ BadParcelableException -> 0x0095 }
            return;	 Catch:{ BadParcelableException -> 0x0095 }
        L_0x005a:
            r1.onConnectionFailed(r8);	 Catch:{ BadParcelableException -> 0x0095 }
            return;	 Catch:{ BadParcelableException -> 0x0095 }
        L_0x005e:
            r2 = "data_root_hints";	 Catch:{ BadParcelableException -> 0x0095 }
            r2 = r0.getBundle(r2);	 Catch:{ BadParcelableException -> 0x0095 }
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r2);	 Catch:{ BadParcelableException -> 0x0095 }
            r3 = "data_media_item_id";	 Catch:{ BadParcelableException -> 0x0095 }
            r3 = r0.getString(r3);	 Catch:{ BadParcelableException -> 0x0095 }
            r4 = "data_media_session_token";	 Catch:{ BadParcelableException -> 0x0095 }
            r0 = r0.getParcelable(r4);	 Catch:{ BadParcelableException -> 0x0095 }
            r0 = (android.support.v4.media.session.MediaSessionCompat.Token) r0;	 Catch:{ BadParcelableException -> 0x0095 }
            r1.onServiceConnected(r8, r3, r0, r2);	 Catch:{ BadParcelableException -> 0x0095 }
            return;	 Catch:{ BadParcelableException -> 0x0095 }
        L_0x0079:
            r2 = new java.lang.StringBuilder;	 Catch:{ BadParcelableException -> 0x0095 }
            r3 = "Unhandled message: ";	 Catch:{ BadParcelableException -> 0x0095 }
            r2.<init>(r3);	 Catch:{ BadParcelableException -> 0x0095 }
            r2.append(r10);	 Catch:{ BadParcelableException -> 0x0095 }
            r3 = "\n  Client version: 1\n  Service version: ";	 Catch:{ BadParcelableException -> 0x0095 }
            r2.append(r3);	 Catch:{ BadParcelableException -> 0x0095 }
            r3 = r10.arg1;	 Catch:{ BadParcelableException -> 0x0095 }
            r2.append(r3);	 Catch:{ BadParcelableException -> 0x0095 }
            r2 = r2.toString();	 Catch:{ BadParcelableException -> 0x0095 }
            android.util.Log.w(r0, r2);	 Catch:{ BadParcelableException -> 0x0095 }
            return;
            r0 = "MediaBrowserCompat";
            r2 = "Could not unparcel the data.";
            android.util.Log.e(r0, r2);
            r10 = r10.what;
            r0 = 1;
            if (r10 != r0) goto L_0x00a5;
        L_0x00a2:
            r1.onConnectionFailed(r8);
        L_0x00a5:
            return;
        L_0x00a6:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.CallbackHandler.handleMessage(android.os.Message):void");
        }

        void setCallbacksMessenger(Messenger messenger) {
            this.mCallbacksMessengerRef = new WeakReference(messenger);
        }
    }

    public class ConnectionCallback {
        ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;

        interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        class StubApi21 implements ConnectionCallback {
            StubApi21() {
            }

            public void onConnected() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }

            public void onConnectionSuspended() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }

            public void onConnectionFailed() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
                }
                ConnectionCallback.this.onConnectionFailed();
            }
        }

        public void onConnected() {
        }

        public void onConnectionFailed() {
        }

        public void onConnectionSuspended() {
        }

        public ConnectionCallback() {
            if (VERSION.SDK_INT >= 21) {
                this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
            } else {
                this.mConnectionCallbackObj = null;
            }
        }

        void setInternalConnectionCallback(ConnectionCallbackInternal connectionCallbackInternal) {
            this.mConnectionCallbackInternal = connectionCallbackInternal;
        }
    }

    public abstract class CustomActionCallback {
        public void onError(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onProgressUpdate(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onResult(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    public abstract class ItemCallback {
        final Object mItemCallbackObj;

        class StubApi23 implements ItemCallback {
            StubApi23() {
            }

            public void onItemLoaded(Parcel parcel) {
                if (parcel == null) {
                    ItemCallback.this.onItemLoaded(null);
                    return;
                }
                parcel.setDataPosition(0);
                MediaItem mediaItem = (MediaItem) MediaItem.CREATOR.createFromParcel(parcel);
                parcel.recycle();
                ItemCallback.this.onItemLoaded(mediaItem);
            }

            public void onError(String str) {
                ItemCallback.this.onError(str);
            }
        }

        public void onError(String str) {
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }

        public ItemCallback() {
            if (VERSION.SDK_INT >= 23) {
                this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
            } else {
                this.mItemCallbackObj = null;
            }
        }
    }

    interface MediaBrowserImpl {
        void connect();

        void disconnect();

        Bundle getExtras();

        void getItem(String str, ItemCallback itemCallback);

        Bundle getNotifyChildrenChangedOptions();

        String getRoot();

        ComponentName getServiceComponent();

        Token getSessionToken();

        boolean isConnected();

        void search(String str, Bundle bundle, SearchCallback searchCallback);

        void sendCustomAction(String str, Bundle bundle, CustomActionCallback customActionCallback);

        void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback);

        void unsubscribe(String str, SubscriptionCallback subscriptionCallback);
    }

    interface MediaBrowserServiceCallbackImpl {
        void onConnectionFailed(Messenger messenger);

        void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);

        void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle);
    }

    public class MediaItem implements Parcelable {
        public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
            public final MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            public final MediaItem[] newArray(int i) {
                return new MediaItem[i];
            }
        };
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }

        public int describeContents() {
            return 0;
        }

        public static MediaItem fromMediaItem(Object obj) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaItem.getDescription(obj)), MediaItem.getFlags(obj));
        }

        public static List<MediaItem> fromMediaItemList(List<?> list) {
            if (list == null || VERSION.SDK_INT < 21) {
                return null;
            }
            List<MediaItem> arrayList = new ArrayList(list.size());
            for (Object fromMediaItem : list) {
                arrayList.add(fromMediaItem(fromMediaItem));
            }
            return arrayList;
        }

        public MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            } else if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else {
                this.mFlags = i;
                this.mDescription = mediaDescriptionCompat;
            }
        }

        MediaItem(Parcel parcel) {
            this.mFlags = parcel.readInt();
            this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mFlags);
            this.mDescription.writeToParcel(parcel, i);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("MediaItem{");
            stringBuilder.append("mFlags=");
            stringBuilder.append(this.mFlags);
            stringBuilder.append(", mDescription=");
            stringBuilder.append(this.mDescription);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }

        public int getFlags() {
            return this.mFlags;
        }

        public boolean isBrowsable() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.mFlags & 2) != 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public String getMediaId() {
            return this.mDescription.getMediaId();
        }
    }

    public abstract class SearchCallback {
        public void onError(String str, Bundle bundle) {
        }

        public void onSearchResult(String str, Bundle bundle, List<MediaItem> list) {
        }
    }

    class ServiceBinderWrapper {
        private Messenger mMessenger;
        private Bundle mRootHints;

        public ServiceBinderWrapper(IBinder iBinder, Bundle bundle) {
            this.mMessenger = new Messenger(iBinder);
            this.mRootHints = bundle;
        }

        void connect(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.mRootHints);
            sendRequest(1, bundle, messenger);
        }

        void disconnect(Messenger messenger) throws RemoteException {
            sendRequest(2, null, messenger);
        }

        void addSubscription(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            h.a(bundle2, "data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            sendRequest(3, bundle2, messenger);
        }

        void removeSubscription(String str, IBinder iBinder, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", str);
            h.a(bundle, "data_callback_token", iBinder);
            sendRequest(4, bundle, messenger);
        }

        void getMediaItem(String str, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", str);
            bundle.putParcelable("data_result_receiver", resultReceiver);
            sendRequest(5, bundle, messenger);
        }

        void registerCallbackMessenger(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.mRootHints);
            sendRequest(6, bundle, messenger);
        }

        void unregisterCallbackMessenger(Messenger messenger) throws RemoteException {
            sendRequest(7, null, messenger);
        }

        void search(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_search_query", str);
            bundle2.putBundle("data_search_extras", bundle);
            bundle2.putParcelable("data_result_receiver", resultReceiver);
            sendRequest(8, bundle2, messenger);
        }

        void sendCustomAction(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_custom_action", str);
            bundle2.putBundle("data_custom_action_extras", bundle);
            bundle2.putParcelable("data_result_receiver", resultReceiver);
            sendRequest(9, bundle2, messenger);
        }

        private void sendRequest(int i, Bundle bundle, Messenger messenger) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            obtain.replyTo = messenger;
            this.mMessenger.send(obtain);
        }
    }

    class Subscription {
        private final List<SubscriptionCallback> mCallbacks = new ArrayList();
        private final List<Bundle> mOptionsList = new ArrayList();

        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }

        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }

        public SubscriptionCallback getCallback(Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (d.a((Bundle) this.mOptionsList.get(i), bundle)) {
                    return (SubscriptionCallback) this.mCallbacks.get(i);
                }
            }
            return null;
        }

        public void putCallback(Bundle bundle, SubscriptionCallback subscriptionCallback) {
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (d.a((Bundle) this.mOptionsList.get(i), bundle)) {
                    this.mCallbacks.set(i, subscriptionCallback);
                    return;
                }
            }
            this.mCallbacks.add(subscriptionCallback);
            this.mOptionsList.add(bundle);
        }
    }

    public abstract class SubscriptionCallback {
        final Object mSubscriptionCallbackObj;
        WeakReference<Subscription> mSubscriptionRef;
        final IBinder mToken = new Binder();

        class StubApi21 implements SubscriptionCallback {
            StubApi21() {
            }

            public void onChildrenLoaded(String str, List<?> list) {
                Subscription subscription = SubscriptionCallback.this.mSubscriptionRef == null ? null : (Subscription) SubscriptionCallback.this.mSubscriptionRef.get();
                if (subscription == null) {
                    SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list));
                    return;
                }
                List fromMediaItemList = MediaItem.fromMediaItemList(list);
                List callbacks = subscription.getCallbacks();
                List optionsList = subscription.getOptionsList();
                for (int i = 0; i < callbacks.size(); i++) {
                    Bundle bundle = (Bundle) optionsList.get(i);
                    if (bundle == null) {
                        SubscriptionCallback.this.onChildrenLoaded(str, fromMediaItemList);
                    } else {
                        SubscriptionCallback.this.onChildrenLoaded(str, applyOptions(fromMediaItemList, bundle), bundle);
                    }
                }
            }

            public void onError(String str) {
                SubscriptionCallback.this.onError(str);
            }

            List<MediaItem> applyOptions(List<MediaItem> list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                if (i == -1 && i2 == -1) {
                    return list;
                }
                int i3 = i2 * i;
                int i4 = i3 + i2;
                if (i < 0 || i2 <= 0 || i3 >= list.size()) {
                    return Collections.emptyList();
                }
                if (i4 > list.size()) {
                    i4 = list.size();
                }
                return list.subList(i3, i4);
            }
        }

        class StubApi26 extends StubApi21 implements SubscriptionCallback {
            StubApi26() {
                super();
            }

            public void onChildrenLoaded(String str, List<?> list, Bundle bundle) {
                SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list), bundle);
            }

            public void onError(String str, Bundle bundle) {
                SubscriptionCallback.this.onError(str, bundle);
            }
        }

        public void onChildrenLoaded(String str, List<MediaItem> list) {
        }

        public void onChildrenLoaded(String str, List<MediaItem> list, Bundle bundle) {
        }

        public void onError(String str) {
        }

        public void onError(String str, Bundle bundle) {
        }

        public SubscriptionCallback() {
            if (VERSION.SDK_INT >= 26) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi26.createSubscriptionCallback(new StubApi26());
            } else if (VERSION.SDK_INT >= 21) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
            } else {
                this.mSubscriptionCallbackObj = null;
            }
        }

        void setSubscription(Subscription subscription) {
            this.mSubscriptionRef = new WeakReference(subscription);
        }
    }

    class CustomActionResultReceiver extends ResultReceiver {
        private final String mAction;
        private final CustomActionCallback mCallback;
        private final Bundle mExtras;

        CustomActionResultReceiver(String str, Bundle bundle, CustomActionCallback customActionCallback, Handler handler) {
            super(handler);
            this.mAction = str;
            this.mExtras = bundle;
            this.mCallback = customActionCallback;
        }

        protected void onReceiveResult(int i, Bundle bundle) {
            if (this.mCallback != null) {
                MediaSessionCompat.ensureClassLoader(bundle);
                switch (i) {
                    case -1:
                        this.mCallback.onError(this.mAction, this.mExtras, bundle);
                        return;
                    case 0:
                        this.mCallback.onResult(this.mAction, this.mExtras, bundle);
                        return;
                    case 1:
                        this.mCallback.onProgressUpdate(this.mAction, this.mExtras, bundle);
                        return;
                    default:
                        String str = MediaBrowserCompat.TAG;
                        StringBuilder stringBuilder = new StringBuilder("Unknown result code: ");
                        stringBuilder.append(i);
                        stringBuilder.append(" (extras=");
                        stringBuilder.append(this.mExtras);
                        stringBuilder.append(", resultData=");
                        stringBuilder.append(bundle);
                        stringBuilder.append(")");
                        Log.w(str, stringBuilder.toString());
                        return;
                }
            }
        }
    }

    class ItemReceiver extends ResultReceiver {
        private final ItemCallback mCallback;
        private final String mMediaId;

        ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.mMediaId = str;
            this.mCallback = itemCallback;
        }

        protected void onReceiveResult(int i, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i == 0 && bundle != null && bundle.containsKey("media_item")) {
                Parcelable parcelable = bundle.getParcelable("media_item");
                if (parcelable == null || (parcelable instanceof MediaItem)) {
                    this.mCallback.onItemLoaded((MediaItem) parcelable);
                    return;
                } else {
                    this.mCallback.onError(this.mMediaId);
                    return;
                }
            }
            this.mCallback.onError(this.mMediaId);
        }
    }

    class MediaBrowserImplApi21 implements ConnectionCallbackInternal, MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        protected final Object mBrowserObj;
        protected Messenger mCallbacksMessenger;
        final Context mContext;
        protected final CallbackHandler mHandler = new CallbackHandler(this);
        private Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;
        protected final Bundle mRootHints;
        protected ServiceBinderWrapper mServiceBinderWrapper;
        protected int mServiceVersion;
        private final bu<String, Subscription> mSubscriptions = new bu();

        public void onConnectionFailed() {
        }

        public void onConnectionFailed(Messenger messenger) {
        }

        public void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle) {
        }

        MediaBrowserImplApi21(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.mContext = context;
            this.mRootHints = bundle != null ? new Bundle(bundle) : new Bundle();
            this.mRootHints.putInt("extra_client_version", 1);
            connectionCallback.setInternalConnectionCallback(this);
            this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, componentName, connectionCallback.mConnectionCallbackObj, this.mRootHints);
        }

        public void connect() {
            MediaBrowserCompatApi21.connect(this.mBrowserObj);
        }

        public void disconnect() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.disconnect():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r2 = this;
            r0 = r2.mServiceBinderWrapper;
            if (r0 == 0) goto L_0x0017;
        L_0x0004:
            r0 = r2.mCallbacksMessenger;
            if (r0 == 0) goto L_0x0017;
        L_0x0008:
            r0 = r2.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x0010 }
            r1 = r2.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x0010 }
            r0.unregisterCallbackMessenger(r1);	 Catch:{ RemoteException -> 0x0010 }
            goto L_0x0017;
        L_0x0010:
            r0 = "MediaBrowserCompat";
            r1 = "Remote error unregistering client messenger.";
            android.util.Log.i(r0, r1);
        L_0x0017:
            r0 = r2.mBrowserObj;
            android.support.v4.media.MediaBrowserCompatApi21.disconnect(r0);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.disconnect():void");
        }

        public boolean isConnected() {
            return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
        }

        public ComponentName getServiceComponent() {
            return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
        }

        public String getRoot() {
            return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
        }

        public Bundle getExtras() {
            return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
        }

        public Token getSessionToken() {
            if (this.mMediaSessionToken == null) {
                this.mMediaSessionToken = Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
            }
            return this.mMediaSessionToken;
        }

        public void subscribe(java.lang.String r3, android.os.Bundle r4, android.support.v4.media.MediaBrowserCompat.SubscriptionCallback r5) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.subscribe(java.lang.String, android.os.Bundle, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r2 = this;
            r0 = r2.mSubscriptions;
            r0 = r0.get(r3);
            r0 = (android.support.v4.media.MediaBrowserCompat.Subscription) r0;
            if (r0 != 0) goto L_0x0014;
        L_0x000a:
            r0 = new android.support.v4.media.MediaBrowserCompat$Subscription;
            r0.<init>();
            r1 = r2.mSubscriptions;
            r1.put(r3, r0);
        L_0x0014:
            r5.setSubscription(r0);
            if (r4 != 0) goto L_0x001b;
        L_0x0019:
            r4 = 0;
            goto L_0x0021;
        L_0x001b:
            r1 = new android.os.Bundle;
            r1.<init>(r4);
            r4 = r1;
        L_0x0021:
            r0.putCallback(r4, r5);
            r0 = r2.mServiceBinderWrapper;
            if (r0 != 0) goto L_0x0030;
        L_0x0028:
            r4 = r2.mBrowserObj;
            r5 = r5.mSubscriptionCallbackObj;
            android.support.v4.media.MediaBrowserCompatApi21.subscribe(r4, r3, r5);
            return;
        L_0x0030:
            r0 = r2.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x003a }
            r5 = r5.mToken;	 Catch:{ RemoteException -> 0x003a }
            r1 = r2.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x003a }
            r0.addSubscription(r3, r5, r4, r1);	 Catch:{ RemoteException -> 0x003a }
            return;
        L_0x003a:
            r4 = "MediaBrowserCompat";
            r5 = "Remote error subscribing media item: ";
            r3 = java.lang.String.valueOf(r3);
            r3 = r5.concat(r3);
            android.util.Log.i(r4, r3);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.subscribe(java.lang.String, android.os.Bundle, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void");
        }

        public void unsubscribe(java.lang.String r8, android.support.v4.media.MediaBrowserCompat.SubscriptionCallback r9) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.unsubscribe(java.lang.String, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r7 = this;
            r0 = r7.mSubscriptions;
            r0 = r0.get(r8);
            r0 = (android.support.v4.media.MediaBrowserCompat.Subscription) r0;
            if (r0 != 0) goto L_0x000b;
        L_0x000a:
            return;
        L_0x000b:
            r1 = r7.mServiceBinderWrapper;
            if (r1 != 0) goto L_0x0042;
        L_0x000f:
            if (r9 != 0) goto L_0x0017;
        L_0x0011:
            r1 = r7.mBrowserObj;
            android.support.v4.media.MediaBrowserCompatApi21.unsubscribe(r1, r8);
            goto L_0x0077;
        L_0x0017:
            r1 = r0.getCallbacks();
            r2 = r0.getOptionsList();
            r3 = r1.size();
            r3 = r3 + -1;
        L_0x0025:
            if (r3 < 0) goto L_0x0036;
        L_0x0027:
            r4 = r1.get(r3);
            if (r4 != r9) goto L_0x0033;
        L_0x002d:
            r1.remove(r3);
            r2.remove(r3);
        L_0x0033:
            r3 = r3 + -1;
            goto L_0x0025;
        L_0x0036:
            r1 = r1.size();
            if (r1 != 0) goto L_0x0077;
        L_0x003c:
            r1 = r7.mBrowserObj;
            android.support.v4.media.MediaBrowserCompatApi21.unsubscribe(r1, r8);
            goto L_0x0077;
        L_0x0042:
            if (r9 != 0) goto L_0x004f;
        L_0x0044:
            r1 = r7.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x004d }
            r2 = 0;	 Catch:{ RemoteException -> 0x004d }
            r3 = r7.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x004d }
            r1.removeSubscription(r8, r2, r3);	 Catch:{ RemoteException -> 0x004d }
            goto L_0x0077;	 Catch:{ RemoteException -> 0x004d }
            goto L_0x0077;	 Catch:{ RemoteException -> 0x004d }
        L_0x004f:
            r1 = r0.getCallbacks();	 Catch:{ RemoteException -> 0x004d }
            r2 = r0.getOptionsList();	 Catch:{ RemoteException -> 0x004d }
            r3 = r1.size();	 Catch:{ RemoteException -> 0x004d }
            r3 = r3 + -1;	 Catch:{ RemoteException -> 0x004d }
        L_0x005d:
            if (r3 < 0) goto L_0x0077;	 Catch:{ RemoteException -> 0x004d }
        L_0x005f:
            r4 = r1.get(r3);	 Catch:{ RemoteException -> 0x004d }
            if (r4 != r9) goto L_0x0074;	 Catch:{ RemoteException -> 0x004d }
        L_0x0065:
            r4 = r7.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x004d }
            r5 = r9.mToken;	 Catch:{ RemoteException -> 0x004d }
            r6 = r7.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x004d }
            r4.removeSubscription(r8, r5, r6);	 Catch:{ RemoteException -> 0x004d }
            r1.remove(r3);	 Catch:{ RemoteException -> 0x004d }
            r2.remove(r3);	 Catch:{ RemoteException -> 0x004d }
        L_0x0074:
            r3 = r3 + -1;
            goto L_0x005d;
        L_0x0077:
            r0 = r0.isEmpty();
            if (r0 != 0) goto L_0x007f;
        L_0x007d:
            if (r9 != 0) goto L_0x0084;
        L_0x007f:
            r9 = r7.mSubscriptions;
            r9.remove(r8);
        L_0x0084:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.unsubscribe(java.lang.String, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void");
        }

        public void getItem(final java.lang.String r4, final android.support.v4.media.MediaBrowserCompat.ItemCallback r5) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.getItem(java.lang.String, android.support.v4.media.MediaBrowserCompat$ItemCallback):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r3 = this;
            r0 = android.text.TextUtils.isEmpty(r4);
            if (r0 != 0) goto L_0x0062;
        L_0x0006:
            if (r5 == 0) goto L_0x005a;
        L_0x0008:
            r0 = r3.mBrowserObj;
            r0 = android.support.v4.media.MediaBrowserCompatApi21.isConnected(r0);
            if (r0 != 0) goto L_0x0022;
        L_0x0010:
            r0 = "MediaBrowserCompat";
            r1 = "Not connected, unable to retrieve the MediaItem.";
            android.util.Log.i(r0, r1);
            r0 = r3.mHandler;
            r1 = new android.support.v4.media.MediaBrowserCompat$MediaBrowserImplApi21$1;
            r1.<init>(r5, r4);
            r0.post(r1);
            return;
        L_0x0022:
            r0 = r3.mServiceBinderWrapper;
            if (r0 != 0) goto L_0x0031;
        L_0x0026:
            r0 = r3.mHandler;
            r1 = new android.support.v4.media.MediaBrowserCompat$MediaBrowserImplApi21$2;
            r1.<init>(r5, r4);
            r0.post(r1);
            return;
        L_0x0031:
            r0 = new android.support.v4.media.MediaBrowserCompat$ItemReceiver;
            r1 = r3.mHandler;
            r0.<init>(r4, r5, r1);
            r1 = r3.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x0040 }
            r2 = r3.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x0040 }
            r1.getMediaItem(r4, r0, r2);	 Catch:{ RemoteException -> 0x0040 }
            return;
        L_0x0040:
            r0 = "MediaBrowserCompat";
            r1 = "Remote error getting media item: ";
            r2 = java.lang.String.valueOf(r4);
            r1 = r1.concat(r2);
            android.util.Log.i(r0, r1);
            r0 = r3.mHandler;
            r1 = new android.support.v4.media.MediaBrowserCompat$MediaBrowserImplApi21$3;
            r1.<init>(r5, r4);
            r0.post(r1);
            return;
        L_0x005a:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "cb is null";
            r4.<init>(r5);
            throw r4;
        L_0x0062:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "mediaId is empty";
            r4.<init>(r5);
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.getItem(java.lang.String, android.support.v4.media.MediaBrowserCompat$ItemCallback):void");
        }

        public void search(final String str, final Bundle bundle, final SearchCallback searchCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("search() called while not connected");
            } else if (this.mServiceBinderWrapper == null) {
                Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support search.");
                this.mHandler.post(new Runnable() {
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
            } else {
                try {
                    this.mServiceBinderWrapper.search(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.mHandler), this.mCallbacksMessenger);
                } catch (Throwable e) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error searching items with query: ".concat(String.valueOf(str)), e);
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            searchCallback.onError(str, bundle);
                        }
                    });
                }
            }
        }

        public void sendCustomAction(final String str, final Bundle bundle, final CustomActionCallback customActionCallback) {
            if (isConnected()) {
                if (this.mServiceBinderWrapper == null) {
                    Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support sendCustomAction.");
                    if (customActionCallback != null) {
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                customActionCallback.onError(str, bundle, null);
                            }
                        });
                    }
                }
                try {
                    this.mServiceBinderWrapper.sendCustomAction(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.mHandler), this.mCallbacksMessenger);
                    return;
                } catch (Throwable e) {
                    String str2 = MediaBrowserCompat.TAG;
                    StringBuilder stringBuilder = new StringBuilder("Remote error sending a custom action: action=");
                    stringBuilder.append(str);
                    stringBuilder.append(", extras=");
                    stringBuilder.append(bundle);
                    Log.i(str2, stringBuilder.toString(), e);
                    if (customActionCallback != null) {
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                customActionCallback.onError(str, bundle, null);
                            }
                        });
                    }
                    return;
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder("Cannot send a custom action (");
            stringBuilder2.append(str);
            stringBuilder2.append(") with extras ");
            stringBuilder2.append(bundle);
            stringBuilder2.append(" because the browser is not connected to the service.");
            throw new IllegalStateException(stringBuilder2.toString());
        }

        public void onConnected() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.onConnected():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r4 = this;
            r0 = r4.mBrowserObj;
            r0 = android.support.v4.media.MediaBrowserCompatApi21.getExtras(r0);
            if (r0 != 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r1 = "extra_service_version";
            r2 = 0;
            r1 = r0.getInt(r1, r2);
            r4.mServiceVersion = r1;
            r1 = "extra_messenger";
            r1 = androidx.core.app.h.a(r0, r1);
            if (r1 == 0) goto L_0x0044;
        L_0x001a:
            r2 = new android.support.v4.media.MediaBrowserCompat$ServiceBinderWrapper;
            r3 = r4.mRootHints;
            r2.<init>(r1, r3);
            r4.mServiceBinderWrapper = r2;
            r1 = new android.os.Messenger;
            r2 = r4.mHandler;
            r1.<init>(r2);
            r4.mCallbacksMessenger = r1;
            r1 = r4.mHandler;
            r2 = r4.mCallbacksMessenger;
            r1.setCallbacksMessenger(r2);
            r1 = r4.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x003d }
            r2 = r4.mContext;	 Catch:{ RemoteException -> 0x003d }
            r3 = r4.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x003d }
            r1.registerCallbackMessenger(r2, r3);	 Catch:{ RemoteException -> 0x003d }
            goto L_0x0044;
        L_0x003d:
            r1 = "MediaBrowserCompat";
            r2 = "Remote error registering client messenger.";
            android.util.Log.i(r1, r2);
        L_0x0044:
            r1 = "extra_session_binder";
            r0 = androidx.core.app.h.a(r0, r1);
            r0 = android.support.v4.media.session.IMediaSession.Stub.asInterface(r0);
            if (r0 == 0) goto L_0x005c;
        L_0x0050:
            r1 = r4.mBrowserObj;
            r1 = android.support.v4.media.MediaBrowserCompatApi21.getSessionToken(r1);
            r0 = android.support.v4.media.session.MediaSessionCompat.Token.fromToken(r1, r0);
            r4.mMediaSessionToken = r0;
        L_0x005c:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.onConnected():void");
        }

        public void onConnectionSuspended() {
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mMediaSessionToken = null;
            this.mHandler.setCallbacksMessenger(null);
        }

        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.mCallbacksMessenger == messenger) {
                Subscription subscription = (Subscription) this.mSubscriptions.get(str);
                if (subscription == null) {
                    boolean z = MediaBrowserCompat.DEBUG;
                    return;
                }
                SubscriptionCallback callback = subscription.getCallback(bundle);
                if (callback != null) {
                    if (bundle == null) {
                        if (list == null) {
                            callback.onError(str);
                            return;
                        }
                        this.mNotifyChildrenChangedOptions = bundle2;
                        callback.onChildrenLoaded(str, list);
                        this.mNotifyChildrenChangedOptions = null;
                    } else if (list == null) {
                        callback.onError(str, bundle);
                    } else {
                        this.mNotifyChildrenChangedOptions = bundle2;
                        callback.onChildrenLoaded(str, list, bundle);
                        this.mNotifyChildrenChangedOptions = null;
                    }
                }
            }
        }

        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }
    }

    class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        static final int CONNECT_STATE_CONNECTED = 3;
        static final int CONNECT_STATE_CONNECTING = 2;
        static final int CONNECT_STATE_DISCONNECTED = 1;
        static final int CONNECT_STATE_DISCONNECTING = 0;
        static final int CONNECT_STATE_SUSPENDED = 4;
        final ConnectionCallback mCallback;
        Messenger mCallbacksMessenger;
        final Context mContext;
        private Bundle mExtras;
        final CallbackHandler mHandler = new CallbackHandler(this);
        private Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;
        final Bundle mRootHints;
        private String mRootId;
        ServiceBinderWrapper mServiceBinderWrapper;
        final ComponentName mServiceComponent;
        MediaServiceConnection mServiceConnection;
        int mState = 1;
        private final bu<String, Subscription> mSubscriptions = new bu();

        class MediaServiceConnection implements ServiceConnection {
            MediaServiceConnection() {
            }

            public void onServiceConnected(final ComponentName componentName, final IBinder iBinder) {
                postOrRun(new Runnable() {
                    public void run() {
                        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.1.run():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                        /*
                        r4 = this;
                        r0 = android.support.v4.media.MediaBrowserCompat.DEBUG;
                        if (r0 == 0) goto L_0x0021;
                    L_0x0004:
                        r0 = new java.lang.StringBuilder;
                        r1 = "MediaServiceConnection.onServiceConnected name=";
                        r0.<init>(r1);
                        r1 = r2;
                        r0.append(r1);
                        r1 = " binder=";
                        r0.append(r1);
                        r1 = r3;
                        r0.append(r1);
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0.dump();
                    L_0x0021:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r1 = "onServiceConnected";
                        r0 = r0.isCurrent(r1);
                        if (r0 != 0) goto L_0x002c;
                    L_0x002b:
                        return;
                    L_0x002c:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r1 = new android.support.v4.media.MediaBrowserCompat$ServiceBinderWrapper;
                        r2 = r3;
                        r3 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r3 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r3 = r3.mRootHints;
                        r1.<init>(r2, r3);
                        r0.mServiceBinderWrapper = r1;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r1 = new android.os.Messenger;
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r2 = r2.mHandler;
                        r1.<init>(r2);
                        r0.mCallbacksMessenger = r1;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0 = r0.mHandler;
                        r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r1 = r1.mCallbacksMessenger;
                        r0.setCallbacksMessenger(r1);
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r1 = 2;
                        r0.mState = r1;
                        r0 = android.support.v4.media.MediaBrowserCompat.DEBUG;	 Catch:{ RemoteException -> 0x0087 }
                        if (r0 == 0) goto L_0x0071;	 Catch:{ RemoteException -> 0x0087 }
                    L_0x006a:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;	 Catch:{ RemoteException -> 0x0087 }
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ RemoteException -> 0x0087 }
                        r0.dump();	 Catch:{ RemoteException -> 0x0087 }
                    L_0x0071:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;	 Catch:{ RemoteException -> 0x0087 }
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ RemoteException -> 0x0087 }
                        r0 = r0.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x0087 }
                        r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;	 Catch:{ RemoteException -> 0x0087 }
                        r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ RemoteException -> 0x0087 }
                        r1 = r1.mContext;	 Catch:{ RemoteException -> 0x0087 }
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;	 Catch:{ RemoteException -> 0x0087 }
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ RemoteException -> 0x0087 }
                        r2 = r2.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x0087 }
                        r0.connect(r1, r2);	 Catch:{ RemoteException -> 0x0087 }
                        return;
                        r0 = "MediaBrowserCompat";
                        r1 = new java.lang.StringBuilder;
                        r2 = "RemoteException during connect for ";
                        r1.<init>(r2);
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r2 = r2.mServiceComponent;
                        r1.append(r2);
                        r1 = r1.toString();
                        android.util.Log.w(r0, r1);
                        r0 = android.support.v4.media.MediaBrowserCompat.DEBUG;
                        if (r0 == 0) goto L_0x00ac;
                    L_0x00a5:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0.dump();
                    L_0x00ac:
                        return;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.1.run():void");
                    }
                });
            }

            public void onServiceDisconnected(final ComponentName componentName) {
                postOrRun(new Runnable() {
                    public void run() {
                        if (MediaBrowserCompat.DEBUG) {
                            StringBuilder stringBuilder = new StringBuilder("MediaServiceConnection.onServiceDisconnected name=");
                            stringBuilder.append(componentName);
                            stringBuilder.append(" this=");
                            stringBuilder.append(this);
                            stringBuilder.append(" mServiceConnection=");
                            stringBuilder.append(MediaBrowserImplBase.this.mServiceConnection);
                            MediaBrowserImplBase.this.dump();
                        }
                        if (MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
                            MediaBrowserImplBase.this.mServiceBinderWrapper = null;
                            MediaBrowserImplBase.this.mCallbacksMessenger = null;
                            MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(null);
                            MediaBrowserImplBase.this.mState = 4;
                            MediaBrowserImplBase.this.mCallback.onConnectionSuspended();
                        }
                    }
                });
            }

            private void postOrRun(Runnable runnable) {
                if (Thread.currentThread() == MediaBrowserImplBase.this.mHandler.getLooper().getThread()) {
                    runnable.run();
                } else {
                    MediaBrowserImplBase.this.mHandler.post(runnable);
                }
            }

            boolean isCurrent(String str) {
                if (MediaBrowserImplBase.this.mServiceConnection == this && MediaBrowserImplBase.this.mState != 0 && MediaBrowserImplBase.this.mState != 1) {
                    return true;
                }
                if (!(MediaBrowserImplBase.this.mState == 0 || MediaBrowserImplBase.this.mState == 1)) {
                    String str2 = MediaBrowserCompat.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(" for ");
                    stringBuilder.append(MediaBrowserImplBase.this.mServiceComponent);
                    stringBuilder.append(" with mServiceConnection=");
                    stringBuilder.append(MediaBrowserImplBase.this.mServiceConnection);
                    stringBuilder.append(" this=");
                    stringBuilder.append(this);
                    Log.i(str2, stringBuilder.toString());
                }
                return false;
            }
        }

        public MediaBrowserImplBase(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            } else if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            } else if (connectionCallback != null) {
                Bundle bundle2;
                this.mContext = context;
                this.mServiceComponent = componentName;
                this.mCallback = connectionCallback;
                if (bundle == null) {
                    bundle2 = null;
                } else {
                    bundle2 = new Bundle(bundle);
                }
                this.mRootHints = bundle2;
            } else {
                throw new IllegalArgumentException("connection callback must not be null");
            }
        }

        public void connect() {
            if (this.mState == 0 || this.mState == 1) {
                this.mState = 2;
                this.mHandler.post(new Runnable() {
                    public void run() {
                        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.1.run():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                        /*
                        r5 = this;
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0 = r0.mState;
                        if (r0 != 0) goto L_0x0007;
                    L_0x0006:
                        return;
                    L_0x0007:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r1 = 2;
                        r0.mState = r1;
                        r0 = android.support.v4.media.MediaBrowserCompat.DEBUG;
                        if (r0 == 0) goto L_0x002f;
                    L_0x0010:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0 = r0.mServiceConnection;
                        if (r0 != 0) goto L_0x0017;
                    L_0x0016:
                        goto L_0x002f;
                    L_0x0017:
                        r0 = new java.lang.RuntimeException;
                        r1 = new java.lang.StringBuilder;
                        r2 = "mServiceConnection should be null. Instead it is ";
                        r1.<init>(r2);
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r2 = r2.mServiceConnection;
                        r1.append(r2);
                        r1 = r1.toString();
                        r0.<init>(r1);
                        throw r0;
                    L_0x002f:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0 = r0.mServiceBinderWrapper;
                        if (r0 != 0) goto L_0x00ab;
                    L_0x0035:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0 = r0.mCallbacksMessenger;
                        if (r0 != 0) goto L_0x0093;
                    L_0x003b:
                        r0 = new android.content.Intent;
                        r1 = "android.media.browse.MediaBrowserService";
                        r0.<init>(r1);
                        r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r1 = r1.mServiceComponent;
                        r0.setComponent(r1);
                        r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r2 = new android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection;
                        r3 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r2.<init>();
                        r1.mServiceConnection = r2;
                        r1 = 0;
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ Exception -> 0x0063 }
                        r2 = r2.mContext;	 Catch:{ Exception -> 0x0063 }
                        r3 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ Exception -> 0x0063 }
                        r3 = r3.mServiceConnection;	 Catch:{ Exception -> 0x0063 }
                        r4 = 1;	 Catch:{ Exception -> 0x0063 }
                        r0 = r2.bindService(r0, r3, r4);	 Catch:{ Exception -> 0x0063 }
                        goto L_0x007b;
                    L_0x0063:
                        r0 = "MediaBrowserCompat";
                        r2 = new java.lang.StringBuilder;
                        r3 = "Failed binding to service ";
                        r2.<init>(r3);
                        r3 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r3 = r3.mServiceComponent;
                        r2.append(r3);
                        r2 = r2.toString();
                        android.util.Log.e(r0, r2);
                        r0 = 0;
                    L_0x007b:
                        if (r0 != 0) goto L_0x0089;
                    L_0x007d:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0.forceCloseConnection();
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0 = r0.mCallback;
                        r0.onConnectionFailed();
                    L_0x0089:
                        r0 = android.support.v4.media.MediaBrowserCompat.DEBUG;
                        if (r0 == 0) goto L_0x0092;
                    L_0x008d:
                        r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r0.dump();
                    L_0x0092:
                        return;
                    L_0x0093:
                        r0 = new java.lang.RuntimeException;
                        r1 = new java.lang.StringBuilder;
                        r2 = "mCallbacksMessenger should be null. Instead it is ";
                        r1.<init>(r2);
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r2 = r2.mCallbacksMessenger;
                        r1.append(r2);
                        r1 = r1.toString();
                        r0.<init>(r1);
                        throw r0;
                    L_0x00ab:
                        r0 = new java.lang.RuntimeException;
                        r1 = new java.lang.StringBuilder;
                        r2 = "mServiceBinderWrapper should be null. Instead it is ";
                        r1.<init>(r2);
                        r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                        r2 = r2.mServiceBinderWrapper;
                        r1.append(r2);
                        r1 = r1.toString();
                        r0.<init>(r1);
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.1.run():void");
                    }
                });
                return;
            }
            StringBuilder stringBuilder = new StringBuilder("connect() called while neigther disconnecting nor disconnected (state=");
            stringBuilder.append(getStateLabel(this.mState));
            stringBuilder.append(")");
            throw new IllegalStateException(stringBuilder.toString());
        }

        public void disconnect() {
            this.mState = 0;
            this.mHandler.post(new Runnable() {
                public void run() {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.2.run():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                    /*
                    r3 = this;
                    r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                    r0 = r0.mCallbacksMessenger;
                    if (r0 == 0) goto L_0x0029;
                L_0x0006:
                    r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ RemoteException -> 0x0012 }
                    r0 = r0.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x0012 }
                    r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;	 Catch:{ RemoteException -> 0x0012 }
                    r1 = r1.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x0012 }
                    r0.disconnect(r1);	 Catch:{ RemoteException -> 0x0012 }
                    goto L_0x0029;
                L_0x0012:
                    r0 = "MediaBrowserCompat";
                    r1 = new java.lang.StringBuilder;
                    r2 = "RemoteException during connect for ";
                    r1.<init>(r2);
                    r2 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                    r2 = r2.mServiceComponent;
                    r1.append(r2);
                    r1 = r1.toString();
                    android.util.Log.w(r0, r1);
                L_0x0029:
                    r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                    r0 = r0.mState;
                    r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                    r1.forceCloseConnection();
                    if (r0 == 0) goto L_0x0038;
                L_0x0034:
                    r1 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                    r1.mState = r0;
                L_0x0038:
                    r0 = android.support.v4.media.MediaBrowserCompat.DEBUG;
                    if (r0 == 0) goto L_0x0041;
                L_0x003c:
                    r0 = android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.this;
                    r0.dump();
                L_0x0041:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.2.run():void");
                }
            });
        }

        void forceCloseConnection() {
            if (this.mServiceConnection != null) {
                this.mContext.unbindService(this.mServiceConnection);
            }
            this.mState = 1;
            this.mServiceConnection = null;
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger(null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }

        public boolean isConnected() {
            return this.mState == 3;
        }

        public ComponentName getServiceComponent() {
            if (isConnected()) {
                return this.mServiceComponent;
            }
            StringBuilder stringBuilder = new StringBuilder("getServiceComponent() called while not connected (state=");
            stringBuilder.append(this.mState);
            stringBuilder.append(")");
            throw new IllegalStateException(stringBuilder.toString());
        }

        public String getRoot() {
            if (isConnected()) {
                return this.mRootId;
            }
            StringBuilder stringBuilder = new StringBuilder("getRoot() called while not connected(state=");
            stringBuilder.append(getStateLabel(this.mState));
            stringBuilder.append(")");
            throw new IllegalStateException(stringBuilder.toString());
        }

        public Bundle getExtras() {
            if (isConnected()) {
                return this.mExtras;
            }
            StringBuilder stringBuilder = new StringBuilder("getExtras() called while not connected (state=");
            stringBuilder.append(getStateLabel(this.mState));
            stringBuilder.append(")");
            throw new IllegalStateException(stringBuilder.toString());
        }

        public Token getSessionToken() {
            if (isConnected()) {
                return this.mMediaSessionToken;
            }
            StringBuilder stringBuilder = new StringBuilder("getSessionToken() called while not connected(state=");
            stringBuilder.append(this.mState);
            stringBuilder.append(")");
            throw new IllegalStateException(stringBuilder.toString());
        }

        public void subscribe(java.lang.String r3, android.os.Bundle r4, android.support.v4.media.MediaBrowserCompat.SubscriptionCallback r5) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.subscribe(java.lang.String, android.os.Bundle, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r2 = this;
            r0 = r2.mSubscriptions;
            r0 = r0.get(r3);
            r0 = (android.support.v4.media.MediaBrowserCompat.Subscription) r0;
            if (r0 != 0) goto L_0x0014;
        L_0x000a:
            r0 = new android.support.v4.media.MediaBrowserCompat$Subscription;
            r0.<init>();
            r1 = r2.mSubscriptions;
            r1.put(r3, r0);
        L_0x0014:
            if (r4 != 0) goto L_0x0018;
        L_0x0016:
            r4 = 0;
            goto L_0x001e;
        L_0x0018:
            r1 = new android.os.Bundle;
            r1.<init>(r4);
            r4 = r1;
        L_0x001e:
            r0.putCallback(r4, r5);
            r0 = r2.isConnected();
            if (r0 == 0) goto L_0x0031;
        L_0x0027:
            r0 = r2.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x0031 }
            r5 = r5.mToken;	 Catch:{ RemoteException -> 0x0031 }
            r1 = r2.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x0031 }
            r0.addSubscription(r3, r5, r4, r1);	 Catch:{ RemoteException -> 0x0031 }
            return;
        L_0x0031:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.subscribe(java.lang.String, android.os.Bundle, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void");
        }

        public void unsubscribe(java.lang.String r8, android.support.v4.media.MediaBrowserCompat.SubscriptionCallback r9) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.unsubscribe(java.lang.String, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r7 = this;
            r0 = r7.mSubscriptions;
            r0 = r0.get(r8);
            r0 = (android.support.v4.media.MediaBrowserCompat.Subscription) r0;
            if (r0 != 0) goto L_0x000b;
        L_0x000a:
            return;
        L_0x000b:
            if (r9 != 0) goto L_0x001e;
        L_0x000d:
            r1 = r7.isConnected();	 Catch:{ RemoteException -> 0x001c }
            if (r1 == 0) goto L_0x004c;	 Catch:{ RemoteException -> 0x001c }
        L_0x0013:
            r1 = r7.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x001c }
            r2 = 0;	 Catch:{ RemoteException -> 0x001c }
            r3 = r7.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x001c }
            r1.removeSubscription(r8, r2, r3);	 Catch:{ RemoteException -> 0x001c }
            goto L_0x004c;	 Catch:{ RemoteException -> 0x001c }
            goto L_0x004c;	 Catch:{ RemoteException -> 0x001c }
        L_0x001e:
            r1 = r0.getCallbacks();	 Catch:{ RemoteException -> 0x001c }
            r2 = r0.getOptionsList();	 Catch:{ RemoteException -> 0x001c }
            r3 = r1.size();	 Catch:{ RemoteException -> 0x001c }
            r3 = r3 + -1;	 Catch:{ RemoteException -> 0x001c }
        L_0x002c:
            if (r3 < 0) goto L_0x004c;	 Catch:{ RemoteException -> 0x001c }
        L_0x002e:
            r4 = r1.get(r3);	 Catch:{ RemoteException -> 0x001c }
            if (r4 != r9) goto L_0x0049;	 Catch:{ RemoteException -> 0x001c }
        L_0x0034:
            r4 = r7.isConnected();	 Catch:{ RemoteException -> 0x001c }
            if (r4 == 0) goto L_0x0043;	 Catch:{ RemoteException -> 0x001c }
        L_0x003a:
            r4 = r7.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x001c }
            r5 = r9.mToken;	 Catch:{ RemoteException -> 0x001c }
            r6 = r7.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x001c }
            r4.removeSubscription(r8, r5, r6);	 Catch:{ RemoteException -> 0x001c }
        L_0x0043:
            r1.remove(r3);	 Catch:{ RemoteException -> 0x001c }
            r2.remove(r3);	 Catch:{ RemoteException -> 0x001c }
        L_0x0049:
            r3 = r3 + -1;
            goto L_0x002c;
        L_0x004c:
            r0 = r0.isEmpty();
            if (r0 != 0) goto L_0x0054;
        L_0x0052:
            if (r9 != 0) goto L_0x0059;
        L_0x0054:
            r9 = r7.mSubscriptions;
            r9.remove(r8);
        L_0x0059:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.unsubscribe(java.lang.String, android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void");
        }

        public void getItem(final java.lang.String r4, final android.support.v4.media.MediaBrowserCompat.ItemCallback r5) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.getItem(java.lang.String, android.support.v4.media.MediaBrowserCompat$ItemCallback):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r3 = this;
            r0 = android.text.TextUtils.isEmpty(r4);
            if (r0 != 0) goto L_0x0051;
        L_0x0006:
            if (r5 == 0) goto L_0x0049;
        L_0x0008:
            r0 = r3.isConnected();
            if (r0 != 0) goto L_0x0020;
        L_0x000e:
            r0 = "MediaBrowserCompat";
            r1 = "Not connected, unable to retrieve the MediaItem.";
            android.util.Log.i(r0, r1);
            r0 = r3.mHandler;
            r1 = new android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$3;
            r1.<init>(r5, r4);
            r0.post(r1);
            return;
        L_0x0020:
            r0 = new android.support.v4.media.MediaBrowserCompat$ItemReceiver;
            r1 = r3.mHandler;
            r0.<init>(r4, r5, r1);
            r1 = r3.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x002f }
            r2 = r3.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x002f }
            r1.getMediaItem(r4, r0, r2);	 Catch:{ RemoteException -> 0x002f }
            return;
        L_0x002f:
            r0 = "MediaBrowserCompat";
            r1 = "Remote error getting media item: ";
            r2 = java.lang.String.valueOf(r4);
            r1 = r1.concat(r2);
            android.util.Log.i(r0, r1);
            r0 = r3.mHandler;
            r1 = new android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$4;
            r1.<init>(r5, r4);
            r0.post(r1);
            return;
        L_0x0049:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "cb is null";
            r4.<init>(r5);
            throw r4;
        L_0x0051:
            r4 = new java.lang.IllegalArgumentException;
            r5 = "mediaId is empty";
            r4.<init>(r5);
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.getItem(java.lang.String, android.support.v4.media.MediaBrowserCompat$ItemCallback):void");
        }

        public void search(final String str, final Bundle bundle, final SearchCallback searchCallback) {
            if (isConnected()) {
                try {
                    this.mServiceBinderWrapper.search(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.mHandler), this.mCallbacksMessenger);
                    return;
                } catch (Throwable e) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error searching items with query: ".concat(String.valueOf(str)), e);
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            searchCallback.onError(str, bundle);
                        }
                    });
                    return;
                }
            }
            StringBuilder stringBuilder = new StringBuilder("search() called while not connected (state=");
            stringBuilder.append(getStateLabel(this.mState));
            stringBuilder.append(")");
            throw new IllegalStateException(stringBuilder.toString());
        }

        public void sendCustomAction(final String str, final Bundle bundle, final CustomActionCallback customActionCallback) {
            if (isConnected()) {
                try {
                    this.mServiceBinderWrapper.sendCustomAction(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.mHandler), this.mCallbacksMessenger);
                    return;
                } catch (Throwable e) {
                    String str2 = MediaBrowserCompat.TAG;
                    StringBuilder stringBuilder = new StringBuilder("Remote error sending a custom action: action=");
                    stringBuilder.append(str);
                    stringBuilder.append(", extras=");
                    stringBuilder.append(bundle);
                    Log.i(str2, stringBuilder.toString(), e);
                    if (customActionCallback != null) {
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                customActionCallback.onError(str, bundle, null);
                            }
                        });
                    }
                    return;
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder("Cannot send a custom action (");
            stringBuilder2.append(str);
            stringBuilder2.append(") with extras ");
            stringBuilder2.append(bundle);
            stringBuilder2.append(" because the browser is not connected to the service.");
            throw new IllegalStateException(stringBuilder2.toString());
        }

        public void onServiceConnected(android.os.Messenger r6, java.lang.String r7, android.support.v4.media.session.MediaSessionCompat.Token r8, android.os.Bundle r9) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.onServiceConnected(android.os.Messenger, java.lang.String, android.support.v4.media.session.MediaSessionCompat$Token, android.os.Bundle):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r5 = this;
            r0 = "onConnect";
            r6 = r5.isCurrent(r6, r0);
            if (r6 != 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r6 = r5.mState;
            r0 = 2;
            if (r6 == r0) goto L_0x002d;
        L_0x000e:
            r6 = "MediaBrowserCompat";
            r7 = new java.lang.StringBuilder;
            r8 = "onConnect from service while mState=";
            r7.<init>(r8);
            r8 = r5.mState;
            r8 = getStateLabel(r8);
            r7.append(r8);
            r8 = "... ignoring";
            r7.append(r8);
            r7 = r7.toString();
            android.util.Log.w(r6, r7);
            return;
        L_0x002d:
            r5.mRootId = r7;
            r5.mMediaSessionToken = r8;
            r5.mExtras = r9;
            r6 = 3;
            r5.mState = r6;
            r6 = android.support.v4.media.MediaBrowserCompat.DEBUG;
            if (r6 == 0) goto L_0x003d;
        L_0x003a:
            r5.dump();
        L_0x003d:
            r6 = r5.mCallback;
            r6.onConnected();
            r6 = r5.mSubscriptions;	 Catch:{ RemoteException -> 0x008c }
            r6 = r6.entrySet();	 Catch:{ RemoteException -> 0x008c }
            r6 = r6.iterator();	 Catch:{ RemoteException -> 0x008c }
        L_0x004c:
            r7 = r6.hasNext();	 Catch:{ RemoteException -> 0x008c }
            if (r7 == 0) goto L_0x008b;	 Catch:{ RemoteException -> 0x008c }
        L_0x0052:
            r7 = r6.next();	 Catch:{ RemoteException -> 0x008c }
            r7 = (java.util.Map.Entry) r7;	 Catch:{ RemoteException -> 0x008c }
            r8 = r7.getKey();	 Catch:{ RemoteException -> 0x008c }
            r8 = (java.lang.String) r8;	 Catch:{ RemoteException -> 0x008c }
            r7 = r7.getValue();	 Catch:{ RemoteException -> 0x008c }
            r7 = (android.support.v4.media.MediaBrowserCompat.Subscription) r7;	 Catch:{ RemoteException -> 0x008c }
            r9 = r7.getCallbacks();	 Catch:{ RemoteException -> 0x008c }
            r7 = r7.getOptionsList();	 Catch:{ RemoteException -> 0x008c }
            r0 = 0;	 Catch:{ RemoteException -> 0x008c }
        L_0x006d:
            r1 = r9.size();	 Catch:{ RemoteException -> 0x008c }
            if (r0 >= r1) goto L_0x004c;	 Catch:{ RemoteException -> 0x008c }
        L_0x0073:
            r1 = r5.mServiceBinderWrapper;	 Catch:{ RemoteException -> 0x008c }
            r2 = r9.get(r0);	 Catch:{ RemoteException -> 0x008c }
            r2 = (android.support.v4.media.MediaBrowserCompat.SubscriptionCallback) r2;	 Catch:{ RemoteException -> 0x008c }
            r2 = r2.mToken;	 Catch:{ RemoteException -> 0x008c }
            r3 = r7.get(r0);	 Catch:{ RemoteException -> 0x008c }
            r3 = (android.os.Bundle) r3;	 Catch:{ RemoteException -> 0x008c }
            r4 = r5.mCallbacksMessenger;	 Catch:{ RemoteException -> 0x008c }
            r1.addSubscription(r8, r2, r3, r4);	 Catch:{ RemoteException -> 0x008c }
            r0 = r0 + 1;
            goto L_0x006d;
        L_0x008b:
            return;
        L_0x008c:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.onServiceConnected(android.os.Messenger, java.lang.String, android.support.v4.media.session.MediaSessionCompat$Token, android.os.Bundle):void");
        }

        public void onConnectionFailed(Messenger messenger) {
            String str = MediaBrowserCompat.TAG;
            StringBuilder stringBuilder = new StringBuilder("onConnectFailed for ");
            stringBuilder.append(this.mServiceComponent);
            Log.e(str, stringBuilder.toString());
            if (!isCurrent(messenger, "onConnectFailed")) {
                return;
            }
            if (this.mState != 2) {
                String str2 = MediaBrowserCompat.TAG;
                StringBuilder stringBuilder2 = new StringBuilder("onConnect from service while mState=");
                stringBuilder2.append(getStateLabel(this.mState));
                stringBuilder2.append("... ignoring");
                Log.w(str2, stringBuilder2.toString());
                return;
            }
            forceCloseConnection();
            this.mCallback.onConnectionFailed();
        }

        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (isCurrent(messenger, "onLoadChildren")) {
                if (MediaBrowserCompat.DEBUG) {
                    StringBuilder stringBuilder = new StringBuilder("onLoadChildren for ");
                    stringBuilder.append(this.mServiceComponent);
                    stringBuilder.append(" id=");
                    stringBuilder.append(str);
                }
                Subscription subscription = (Subscription) this.mSubscriptions.get(str);
                if (subscription == null) {
                    boolean z = MediaBrowserCompat.DEBUG;
                    return;
                }
                SubscriptionCallback callback = subscription.getCallback(bundle);
                if (callback != null) {
                    if (bundle == null) {
                        if (list == null) {
                            callback.onError(str);
                            return;
                        }
                        this.mNotifyChildrenChangedOptions = bundle2;
                        callback.onChildrenLoaded(str, list);
                        this.mNotifyChildrenChangedOptions = null;
                    } else if (list == null) {
                        callback.onError(str, bundle);
                    } else {
                        this.mNotifyChildrenChangedOptions = bundle2;
                        callback.onChildrenLoaded(str, list, bundle);
                        this.mNotifyChildrenChangedOptions = null;
                    }
                }
            }
        }

        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }

        private static String getStateLabel(int i) {
            switch (i) {
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
                    return "UNKNOWN/".concat(String.valueOf(i));
            }
        }

        private boolean isCurrent(Messenger messenger, String str) {
            if (this.mCallbacksMessenger == messenger && this.mState != 0 && this.mState != 1) {
                return true;
            }
            if (!(this.mState == 0 || this.mState == 1)) {
                String str2 = MediaBrowserCompat.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" for ");
                stringBuilder.append(this.mServiceComponent);
                stringBuilder.append(" with mCallbacksMessenger=");
                stringBuilder.append(this.mCallbacksMessenger);
                stringBuilder.append(" this=");
                stringBuilder.append(this);
                Log.i(str2, stringBuilder.toString());
            }
            return false;
        }

        void dump() {
            new StringBuilder("  mServiceComponent=").append(this.mServiceComponent);
            new StringBuilder("  mCallback=").append(this.mCallback);
            new StringBuilder("  mRootHints=").append(this.mRootHints);
            new StringBuilder("  mState=").append(getStateLabel(this.mState));
            new StringBuilder("  mServiceConnection=").append(this.mServiceConnection);
            new StringBuilder("  mServiceBinderWrapper=").append(this.mServiceBinderWrapper);
            new StringBuilder("  mCallbacksMessenger=").append(this.mCallbacksMessenger);
            new StringBuilder("  mRootId=").append(this.mRootId);
            new StringBuilder("  mMediaSessionToken=").append(this.mMediaSessionToken);
        }
    }

    class SearchResultReceiver extends ResultReceiver {
        private final SearchCallback mCallback;
        private final Bundle mExtras;
        private final String mQuery;

        SearchResultReceiver(String str, Bundle bundle, SearchCallback searchCallback, Handler handler) {
            super(handler);
            this.mQuery = str;
            this.mExtras = bundle;
            this.mCallback = searchCallback;
        }

        protected void onReceiveResult(int i, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i == 0 && bundle != null && bundle.containsKey("search_results")) {
                Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
                List list = null;
                if (parcelableArray != null) {
                    list = new ArrayList();
                    for (Parcelable parcelable : parcelableArray) {
                        list.add((MediaItem) parcelable);
                    }
                }
                this.mCallback.onSearchResult(this.mQuery, this.mExtras, list);
                return;
            }
            this.mCallback.onError(this.mQuery, this.mExtras);
        }
    }

    class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
        MediaBrowserImplApi23(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        public void getItem(String str, ItemCallback itemCallback) {
            if (this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi23.getItem(this.mBrowserObj, str, itemCallback.mItemCallbackObj);
            } else {
                super.getItem(str, itemCallback);
            }
        }
    }

    class MediaBrowserImplApi26 extends MediaBrowserImplApi23 {
        MediaBrowserImplApi26(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        public void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
            if (this.mServiceBinderWrapper != null && this.mServiceVersion >= 2) {
                super.subscribe(str, bundle, subscriptionCallback);
            } else if (bundle == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, str, subscriptionCallback.mSubscriptionCallbackObj);
            } else {
                MediaBrowserCompatApi26.subscribe(this.mBrowserObj, str, bundle, subscriptionCallback.mSubscriptionCallbackObj);
            }
        }

        public void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
            if (this.mServiceBinderWrapper != null && this.mServiceVersion >= 2) {
                super.unsubscribe(str, subscriptionCallback);
            } else if (subscriptionCallback == null) {
                MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, str);
            } else {
                MediaBrowserCompatApi26.unsubscribe(this.mBrowserObj, str, subscriptionCallback.mSubscriptionCallbackObj);
            }
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        if (VERSION.SDK_INT >= 26) {
            this.mImpl = new MediaBrowserImplApi26(context, componentName, connectionCallback, bundle);
        } else if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle);
        } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaBrowserImplApi21(context, componentName, connectionCallback, bundle);
        } else {
            this.mImpl = new MediaBrowserImplBase(context, componentName, connectionCallback, bundle);
        }
    }

    public final void connect() {
        this.mImpl.connect();
    }

    public final void disconnect() {
        this.mImpl.disconnect();
    }

    public final boolean isConnected() {
        return this.mImpl.isConnected();
    }

    public final ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }

    public final String getRoot() {
        return this.mImpl.getRoot();
    }

    public final Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public final Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public final void subscribe(String str, SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback != null) {
            this.mImpl.subscribe(str, null, subscriptionCallback);
        } else {
            throw new IllegalArgumentException("callback is null");
        }
    }

    public final void subscribe(String str, Bundle bundle, SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        } else if (bundle != null) {
            this.mImpl.subscribe(str, bundle, subscriptionCallback);
        } else {
            throw new IllegalArgumentException("options are null");
        }
    }

    public final void unsubscribe(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.mImpl.unsubscribe(str, null);
    }

    public final void unsubscribe(String str, SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback != null) {
            this.mImpl.unsubscribe(str, subscriptionCallback);
        } else {
            throw new IllegalArgumentException("callback is null");
        }
    }

    public final void getItem(String str, ItemCallback itemCallback) {
        this.mImpl.getItem(str, itemCallback);
    }

    public final void search(String str, Bundle bundle, SearchCallback searchCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        } else if (searchCallback != null) {
            this.mImpl.search(str, bundle, searchCallback);
        } else {
            throw new IllegalArgumentException("callback cannot be null");
        }
    }

    public final void sendCustomAction(String str, Bundle bundle, CustomActionCallback customActionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("action cannot be empty");
        }
        this.mImpl.sendCustomAction(str, bundle, customActionCallback);
    }

    public final Bundle getNotifyChildrenChangedOptions() {
        return this.mImpl.getNotifyChildrenChangedOptions();
    }
}
