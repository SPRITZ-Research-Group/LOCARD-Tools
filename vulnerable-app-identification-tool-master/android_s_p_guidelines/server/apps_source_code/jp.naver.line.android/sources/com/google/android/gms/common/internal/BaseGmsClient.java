package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface> {
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = new String[]{"service_esmobile", "service_googleme"};
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzbt = new Feature[0];
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzbu;
    private long zzbv;
    private long zzbw;
    private int zzbx;
    private long zzby;
    @VisibleForTesting
    private zzh zzbz;
    private final Looper zzca;
    private final GmsClientSupervisor zzcb;
    private final GoogleApiAvailabilityLight zzcc;
    private final Object zzcd;
    private IGmsServiceBroker zzce;
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzcf;
    private T zzcg;
    private final ArrayList<zzc<?>> zzch;
    private zze zzci;
    private int zzcj;
    private final BaseConnectionCallbacks zzck;
    private final BaseOnConnectionFailedListener zzcl;
    private final int zzcm;
    private final String zzcn;
    private ConnectionResult zzco;
    private boolean zzcp;
    private volatile zzb zzcq;
    @VisibleForTesting
    protected AtomicInteger zzcr;

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    public abstract class zzc<TListener> {
        private final /* synthetic */ BaseGmsClient zzct;
        private TListener zzcu;
        private boolean zzcv = false;

        public zzc(BaseGmsClient baseGmsClient, TListener tListener) {
            this.zzct = baseGmsClient;
            this.zzcu = tListener;
        }

        protected abstract void zza(TListener tListener);

        protected abstract void zzn();

        public final void zzo() {
            Object obj;
            synchronized (this) {
                obj = this.zzcu;
                if (this.zzcv) {
                    String valueOf = String.valueOf(this);
                    StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    stringBuilder.append("Callback proxy ");
                    stringBuilder.append(valueOf);
                    stringBuilder.append(" being reused. This is not safe.");
                    Log.w("GmsClient", stringBuilder.toString());
                }
            }
            if (obj != null) {
                try {
                    zza(obj);
                } catch (RuntimeException e) {
                    zzn();
                    throw e;
                }
            }
            zzn();
            synchronized (this) {
                this.zzcv = true;
            }
            unregister();
        }

        public final void unregister() {
            removeListener();
            synchronized (this.zzct.zzch) {
                this.zzct.zzch.remove(this);
            }
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzcu = null;
            }
        }
    }

    @VisibleForTesting
    public final class zze implements ServiceConnection {
        private final /* synthetic */ BaseGmsClient zzct;
        private final int zzcx;

        public zze(BaseGmsClient baseGmsClient, int i) {
            this.zzct = baseGmsClient;
            this.zzcx = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                this.zzct.zzb(16);
                return;
            }
            synchronized (this.zzct.zzcd) {
                IGmsServiceBroker iGmsServiceBroker;
                BaseGmsClient baseGmsClient = this.zzct;
                if (iBinder == null) {
                    iGmsServiceBroker = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        iGmsServiceBroker = new zza(iBinder);
                    } else {
                        iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                baseGmsClient.zzce = iGmsServiceBroker;
            }
            this.zzct.zza(0, null, this.zzcx);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (this.zzct.zzcd) {
                this.zzct.zzce = null;
            }
            this.zzct.mHandler.sendMessage(this.zzct.mHandler.obtainMessage(6, this.zzcx, 1));
        }
    }

    public class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        private final /* synthetic */ BaseGmsClient zzct;

        @KeepForSdk
        public LegacyClientCallbackAdapter(BaseGmsClient baseGmsClient) {
            this.zzct = baseGmsClient;
        }

        public void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                this.zzct.getRemoteService(null, this.zzct.getScopes());
                return;
            }
            if (this.zzct.zzcl != null) {
                this.zzct.zzcl.onConnectionFailed(connectionResult);
            }
        }
    }

    abstract class zza extends zzc<Boolean> {
        private final int statusCode;
        private final Bundle zzcs;
        private final /* synthetic */ BaseGmsClient zzct;

        protected zza(BaseGmsClient baseGmsClient, int i, Bundle bundle) {
            this.zzct = baseGmsClient;
            super(baseGmsClient, Boolean.TRUE);
            this.statusCode = i;
            this.zzcs = bundle;
        }

        protected abstract void zza(ConnectionResult connectionResult);

        protected abstract boolean zzm();

        protected final void zzn() {
        }

        protected final /* synthetic */ void zza(Object obj) {
            PendingIntent pendingIntent = null;
            if (((Boolean) obj) == null) {
                this.zzct.zza(1, null);
                return;
            }
            int i = this.statusCode;
            if (i != 0) {
                if (i != 10) {
                    this.zzct.zza(1, null);
                    if (this.zzcs != null) {
                        pendingIntent = (PendingIntent) this.zzcs.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
                    }
                    zza(new ConnectionResult(this.statusCode, pendingIntent));
                } else {
                    this.zzct.zza(1, null);
                    throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", new Object[]{getClass().getSimpleName(), this.zzct.getStartServiceAction(), this.zzct.getServiceDescriptor()}));
                }
            } else if (!zzm()) {
                this.zzct.zza(1, null);
                zza(new ConnectionResult(8, null));
            }
        }
    }

    final class zzb extends com.google.android.gms.internal.common.zze {
        private final /* synthetic */ BaseGmsClient zzct;

        public zzb(BaseGmsClient baseGmsClient, Looper looper) {
            this.zzct = baseGmsClient;
            super(looper);
        }

        public final void handleMessage(Message message) {
            if (this.zzct.zzcr.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 7 || message.what == 4 || message.what == 5) && !this.zzct.isConnecting()) {
                zza(message);
            } else {
                PendingIntent pendingIntent = null;
                ConnectionResult zzd;
                if (message.what == 4) {
                    this.zzct.zzco = new ConnectionResult(message.arg2);
                    if (!this.zzct.zzl() || this.zzct.zzcp) {
                        if (this.zzct.zzco != null) {
                            zzd = this.zzct.zzco;
                        } else {
                            zzd = new ConnectionResult(8);
                        }
                        this.zzct.zzcf.onReportServiceBinding(zzd);
                        this.zzct.onConnectionFailed(zzd);
                        return;
                    }
                    this.zzct.zza(3, null);
                } else if (message.what == 5) {
                    if (this.zzct.zzco != null) {
                        zzd = this.zzct.zzco;
                    } else {
                        zzd = new ConnectionResult(8);
                    }
                    this.zzct.zzcf.onReportServiceBinding(zzd);
                    this.zzct.onConnectionFailed(zzd);
                } else if (message.what == 3) {
                    if (message.obj instanceof PendingIntent) {
                        pendingIntent = (PendingIntent) message.obj;
                    }
                    ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                    this.zzct.zzcf.onReportServiceBinding(connectionResult);
                    this.zzct.onConnectionFailed(connectionResult);
                } else if (message.what == 6) {
                    this.zzct.zza(5, null);
                    if (this.zzct.zzck != null) {
                        this.zzct.zzck.onConnectionSuspended(message.arg2);
                    }
                    this.zzct.onConnectionSuspended(message.arg2);
                    this.zzct.zza(5, 1, null);
                } else if (message.what == 2 && !this.zzct.isConnected()) {
                    zza(message);
                } else if (zzb(message)) {
                    ((zzc) message.obj).zzo();
                } else {
                    int i = message.what;
                    StringBuilder stringBuilder = new StringBuilder(45);
                    stringBuilder.append("Don't know how to handle message: ");
                    stringBuilder.append(i);
                    Log.wtf("GmsClient", stringBuilder.toString(), new Exception());
                }
            }
        }

        private static void zza(Message message) {
            zzc zzc = (zzc) message.obj;
            zzc.zzn();
            zzc.unregister();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    @VisibleForTesting
    public final class zzd extends com.google.android.gms.common.internal.IGmsCallbacks.zza {
        private BaseGmsClient zzcw;
        private final int zzcx;

        public zzd(BaseGmsClient baseGmsClient, int i) {
            this.zzcw = baseGmsClient;
            this.zzcx = i;
        }

        public final void zza(int i, Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcw.onPostInitHandler(i, iBinder, bundle, this.zzcx);
            this.zzcw = null;
        }

        public final void zza(int i, IBinder iBinder, zzb zzb) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzb);
            this.zzcw.zza(zzb);
            onPostInitComplete(i, iBinder, zzb.zzda);
        }
    }

    public final class zzf extends zza {
        private final /* synthetic */ BaseGmsClient zzct;
        private final IBinder zzcy;

        public zzf(BaseGmsClient baseGmsClient, int i, IBinder iBinder, Bundle bundle) {
            this.zzct = baseGmsClient;
            super(baseGmsClient, i, bundle);
            this.zzcy = iBinder;
        }

        protected final void zza(ConnectionResult connectionResult) {
            if (this.zzct.zzcl != null) {
                this.zzct.zzcl.onConnectionFailed(connectionResult);
            }
            this.zzct.onConnectionFailed(connectionResult);
        }

        protected final boolean zzm() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.internal.BaseGmsClient.zzf.zzm():boolean. bs: []
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
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r6 = this;
            r0 = 0;
            r1 = r6.zzcy;	 Catch:{ RemoteException -> 0x0088 }
            r1 = r1.getInterfaceDescriptor();	 Catch:{ RemoteException -> 0x0088 }
            r2 = r6.zzct;
            r2 = r2.getServiceDescriptor();
            r2 = r2.equals(r1);
            if (r2 != 0) goto L_0x004b;
        L_0x0013:
            r2 = "GmsClient";
            r3 = r6.zzct;
            r3 = r3.getServiceDescriptor();
            r4 = java.lang.String.valueOf(r3);
            r4 = r4.length();
            r4 = r4 + 34;
            r5 = java.lang.String.valueOf(r1);
            r5 = r5.length();
            r4 = r4 + r5;
            r5 = new java.lang.StringBuilder;
            r5.<init>(r4);
            r4 = "service descriptor mismatch: ";
            r5.append(r4);
            r5.append(r3);
            r3 = " vs. ";
            r5.append(r3);
            r5.append(r1);
            r1 = r5.toString();
            android.util.Log.e(r2, r1);
            return r0;
        L_0x004b:
            r1 = r6.zzct;
            r2 = r6.zzcy;
            r1 = r1.createServiceInterface(r2);
            if (r1 == 0) goto L_0x0087;
        L_0x0055:
            r2 = r6.zzct;
            r3 = 2;
            r4 = 4;
            r2 = r2.zza(r3, r4, r1);
            if (r2 != 0) goto L_0x0068;
        L_0x005f:
            r2 = r6.zzct;
            r3 = 3;
            r1 = r2.zza(r3, r4, r1);
            if (r1 == 0) goto L_0x0087;
        L_0x0068:
            r0 = r6.zzct;
            r1 = 0;
            r0.zzco = r1;
            r0 = r6.zzct;
            r0 = r0.getConnectionHint();
            r1 = r6.zzct;
            r1 = r1.zzck;
            if (r1 == 0) goto L_0x0085;
        L_0x007c:
            r1 = r6.zzct;
            r1 = r1.zzck;
            r1.onConnected(r0);
        L_0x0085:
            r0 = 1;
            return r0;
        L_0x0087:
            return r0;
        L_0x0088:
            r1 = "GmsClient";
            r2 = "service probably died";
            android.util.Log.w(r1, r2);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zzf.zzm():boolean");
        }
    }

    public final class zzg extends zza {
        private final /* synthetic */ BaseGmsClient zzct;

        public zzg(BaseGmsClient baseGmsClient, int i, Bundle bundle) {
            this.zzct = baseGmsClient;
            super(baseGmsClient, i, null);
        }

        protected final void zza(ConnectionResult connectionResult) {
            this.zzct.zzcf.onReportServiceBinding(connectionResult);
            this.zzct.onConnectionFailed(connectionResult);
        }

        protected final boolean zzm() {
            this.zzct.zzcf.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    @KeepForSdk
    protected abstract T createServiceInterface(IBinder iBinder);

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    @KeepForSdk
    protected String getLocalStartServiceAction() {
        return null;
    }

    @KeepForSdk
    protected abstract String getServiceDescriptor();

    @KeepForSdk
    protected abstract String getStartServiceAction();

    @KeepForSdk
    protected String getStartServicePackage() {
        return "com.google.android.gms";
    }

    @KeepForSdk
    void onSetConnectState(int i, T t) {
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzca = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzb(this, looper);
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = str;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzca = handler.getLooper();
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = null;
    }

    private final String zzj() {
        return this.zzcn == null ? this.mContext.getClass().getName() : this.zzcn;
    }

    private final void zza(zzb zzb) {
        this.zzcq = zzb;
    }

    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        zzb zzb = this.zzcq;
        if (zzb == null) {
            return null;
        }
        return zzb.zzdb;
    }

    @KeepForSdk
    protected void onConnectedLocked(T t) {
        this.zzbw = System.currentTimeMillis();
    }

    @KeepForSdk
    protected void onConnectionSuspended(int i) {
        this.zzbu = i;
        this.zzbv = System.currentTimeMillis();
    }

    @KeepForSdk
    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbx = connectionResult.getErrorCode();
        this.zzby = System.currentTimeMillis();
    }

    private final void zza(int i, T t) {
        Preconditions.checkArgument((i == 4 ? 1 : null) == (t != null ? 1 : null));
        synchronized (this.mLock) {
            this.zzcj = i;
            this.zzcg = t;
            onSetConnectState(i, t);
            switch (i) {
                case 1:
                    if (this.zzci != null) {
                        this.zzcb.zza(this.zzbz.zzu(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                        this.zzci = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    String zzu;
                    zzh zzh;
                    if (!(this.zzci == null || this.zzbz == null)) {
                        zzu = this.zzbz.zzu();
                        String packageName = this.zzbz.getPackageName();
                        StringBuilder stringBuilder = new StringBuilder((String.valueOf(zzu).length() + 70) + String.valueOf(packageName).length());
                        stringBuilder.append("Calling connect() while still connected, missing disconnect() for ");
                        stringBuilder.append(zzu);
                        stringBuilder.append(" on ");
                        stringBuilder.append(packageName);
                        Log.e("GmsClient", stringBuilder.toString());
                        this.zzcb.zza(this.zzbz.zzu(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                        this.zzcr.incrementAndGet();
                    }
                    this.zzci = new zze(this, this.zzcr.get());
                    if (this.zzcj != 3 || getLocalStartServiceAction() == null) {
                        zzh = new zzh(getStartServicePackage(), getStartServiceAction(), false, 129);
                    } else {
                        zzh = new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129);
                    }
                    this.zzbz = zzh;
                    if (!this.zzcb.zza(new com.google.android.gms.common.internal.GmsClientSupervisor.zza(this.zzbz.zzu(), this.zzbz.getPackageName(), this.zzbz.zzq()), this.zzci, zzj())) {
                        zzu = this.zzbz.zzu();
                        String packageName2 = this.zzbz.getPackageName();
                        StringBuilder stringBuilder2 = new StringBuilder((String.valueOf(zzu).length() + 34) + String.valueOf(packageName2).length());
                        stringBuilder2.append("unable to connect to service: ");
                        stringBuilder2.append(zzu);
                        stringBuilder2.append(" on ");
                        stringBuilder2.append(packageName2);
                        Log.e("GmsClient", stringBuilder2.toString());
                        zza(16, null, this.zzcr.get());
                        break;
                    }
                    break;
                case 4:
                    onConnectedLocked(t);
                    break;
            }
        }
    }

    private final boolean zza(int i, int i2, T t) {
        synchronized (this.mLock) {
            if (this.zzcj != i) {
                return false;
            }
            zza(i2, (IInterface) t);
            return true;
        }
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzcc.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(this), isGooglePlayServicesAvailable, null);
            return;
        }
        connect(new LegacyClientCallbackAdapter(this));
    }

    @KeepForSdk
    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, null);
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 4;
        }
        return z;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 2 || this.zzcj == 3;
        }
        return z;
    }

    private final boolean zzk() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 3;
        }
        return z;
    }

    @KeepForSdk
    public void disconnect() {
        this.zzcr.incrementAndGet();
        synchronized (this.zzch) {
            int size = this.zzch.size();
            for (int i = 0; i < size; i++) {
                ((zzc) this.zzch.get(i)).removeListener();
            }
            this.zzch.clear();
        }
        synchronized (this.zzcd) {
            this.zzce = null;
        }
        zza(1, null);
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzcr.get(), i));
    }

    private final void zzb(int i) {
        if (zzk()) {
            i = 5;
            this.zzcp = true;
        } else {
            i = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i, this.zzcr.get(), 16));
    }

    @KeepForSdk
    @VisibleForTesting
    protected void triggerNotAvailable(ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, PendingIntent pendingIntent) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzcr.get(), i, pendingIntent));
    }

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzca;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zzbt;
    }

    @KeepForSdk
    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @KeepForSdk
    protected void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzf(this, i, iBinder, bundle)));
    }

    protected final void zza(int i, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new zzg(this, i, null)));
    }

    @KeepForSdk
    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzcj != 5) {
                checkConnected();
                Preconditions.checkState(this.zzcg != null, "Client is connected but service is null");
                t = this.zzcg;
            } else {
                throw new DeadObjectException();
            }
        }
        return t;
    }

    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzcm);
        getServiceRequest.zzy = this.mContext.getPackageName();
        getServiceRequest.zzdk = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzdj = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdl = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (iAccountAccessor != null) {
                getServiceRequest.zzdi = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdl = getAccount();
        }
        getServiceRequest.zzdm = zzbt;
        getServiceRequest.zzdn = getApiFeatures();
        try {
            synchronized (this.zzcd) {
                if (this.zzce != null) {
                    this.zzce.getService(new zzd(this, this.zzcr.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (Throwable e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (Throwable e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, null, null, this.zzcr.get());
        }
    }

    @KeepForSdk
    public void onUserSignOut(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    @KeepForSdk
    protected Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        IInterface iInterface;
        IGmsServiceBroker iGmsServiceBroker;
        PrintWriter append;
        long j;
        String format;
        StringBuilder stringBuilder;
        synchronized (this.mLock) {
            i = this.zzcj;
            iInterface = this.zzcg;
        }
        synchronized (this.zzcd) {
            iGmsServiceBroker = this.zzce;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzbw > 0) {
            append = printWriter.append(str).append("lastConnectedTime=");
            j = this.zzbw;
            format = simpleDateFormat.format(new Date(this.zzbw));
            stringBuilder = new StringBuilder(String.valueOf(format).length() + 21);
            stringBuilder.append(j);
            stringBuilder.append(" ");
            stringBuilder.append(format);
            append.println(stringBuilder.toString());
        }
        if (this.zzbv > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzbu) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzbu));
                    break;
            }
            append = printWriter.append(" lastSuspendedTime=");
            j = this.zzbv;
            format = simpleDateFormat.format(new Date(this.zzbv));
            stringBuilder = new StringBuilder(String.valueOf(format).length() + 21);
            stringBuilder.append(j);
            stringBuilder.append(" ");
            stringBuilder.append(format);
            append.println(stringBuilder.toString());
        }
        if (this.zzby > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzbx));
            PrintWriter append2 = printWriter.append(" lastFailedTime=");
            long j2 = this.zzby;
            String format2 = simpleDateFormat.format(new Date(this.zzby));
            StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(format2).length() + 21);
            stringBuilder2.append(j2);
            stringBuilder2.append(" ");
            stringBuilder2.append(format2);
            append2.println(stringBuilder2.toString());
        }
    }

    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzcd) {
            if (this.zzce == null) {
                return null;
            }
            IBinder asBinder = this.zzce.asBinder();
            return asBinder;
        }
    }

    private final boolean zzl() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.internal.BaseGmsClient.zzl():boolean. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
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
        r0 = r2.zzcp;
        r1 = 0;
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r2.getServiceDescriptor();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        return r1;
    L_0x0011:
        r0 = r2.getLocalStartServiceAction();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x001c;
    L_0x001b:
        return r1;
    L_0x001c:
        r0 = r2.getServiceDescriptor();	 Catch:{ ClassNotFoundException -> 0x0025 }
        java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0025 }
        r0 = 1;
        return r0;
    L_0x0025:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zzl():boolean");
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        if (isConnected() && this.zzbz != null) {
            return this.zzbz.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}
