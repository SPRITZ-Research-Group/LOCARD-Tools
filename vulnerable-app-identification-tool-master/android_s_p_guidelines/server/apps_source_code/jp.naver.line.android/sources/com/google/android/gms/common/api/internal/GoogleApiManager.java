package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.C;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zal;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.linecorp.linepay.legacy.activity.a;
import defpackage.bu;
import defpackage.bv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import jp.naver.android.npush.network.NPushProtocol;

@KeepForSdk
public class GoogleApiManager implements Callback {
    private static final Object lock = new Object();
    public static final Status zahw = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zahx = new Status(4, "The user must be signed in to make this API call.");
    private static GoogleApiManager zaib;
    private final Handler handler;
    private long zahy = HlsChunkSource.DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS;
    private long zahz = 120000;
    private long zaia = 10000;
    private final Context zaic;
    private final GoogleApiAvailability zaid;
    private final GoogleApiAvailabilityCache zaie;
    private final AtomicInteger zaif = new AtomicInteger(1);
    private final AtomicInteger zaig = new AtomicInteger(0);
    private final Map<zai<?>, zaa<?>> zaih = new ConcurrentHashMap(5, 0.75f, 1);
    private zaae zaii = null;
    private final Set<zai<?>> zaij = new bv();
    private final Set<zai<?>> zaik = new bv();

    final class zab {
        private final zai<?> zaja;
        private final Feature zajb;

        private zab(zai<?> zai, Feature feature) {
            this.zaja = zai;
            this.zajb = feature;
        }

        public final boolean equals(Object obj) {
            if (obj == null || !(obj instanceof zab)) {
                return false;
            }
            zab zab = (zab) obj;
            if (Objects.equal(this.zaja, zab.zaja) && Objects.equal(this.zajb, zab.zajb)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zaja, this.zajb);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add(NPushProtocol.PROTOCOL_KEY, this.zaja).add(a.QUERY_KEY_FEATURE, this.zajb).toString();
        }

        /* synthetic */ zab(zai zai, Feature feature, zabi zabi) {
            this(zai, feature);
        }
    }

    final class zac implements zach, ConnectionProgressReportCallbacks {
        private final zai<?> zafp;
        final /* synthetic */ GoogleApiManager zail;
        private final Client zain;
        private IAccountAccessor zajc = null;
        private Set<Scope> zajd = null;
        private boolean zaje = false;

        public zac(GoogleApiManager googleApiManager, Client client, zai<?> zai) {
            this.zail = googleApiManager;
            this.zain = client;
            this.zafp = zai;
        }

        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            this.zail.handler.post(new zabo(this, connectionResult));
        }

        public final void zag(ConnectionResult connectionResult) {
            ((zaa) this.zail.zaih.get(this.zafp)).zag(connectionResult);
        }

        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zag(new ConnectionResult(4));
                return;
            }
            this.zajc = iAccountAccessor;
            this.zajd = set;
            zabr();
        }

        private final void zabr() {
            if (this.zaje && this.zajc != null) {
                this.zain.getRemoteService(this.zajc, this.zajd);
            }
        }
    }

    public final class zaa<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zar {
        private final zai<O> zafp;
        final /* synthetic */ GoogleApiManager zail;
        private final Queue<zab> zaim = new LinkedList();
        private final Client zain;
        private final AnyClient zaio;
        private final zaab zaip;
        private final Set<zak> zaiq = new HashSet();
        private final Map<ListenerKey<?>, zabw> zair = new HashMap();
        private final int zais;
        private final zace zait;
        private boolean zaiu;
        private final List<zab> zaiv = new ArrayList();
        private ConnectionResult zaiw = null;

        public zaa(GoogleApiManager googleApiManager, GoogleApi<O> googleApi) {
            this.zail = googleApiManager;
            this.zain = googleApi.zaa(googleApiManager.handler.getLooper(), this);
            if (this.zain instanceof SimpleClientAdapter) {
                this.zaio = ((SimpleClientAdapter) this.zain).getClient();
            } else {
                this.zaio = this.zain;
            }
            this.zafp = googleApi.zak();
            this.zaip = new zaab();
            this.zais = googleApi.getInstanceId();
            if (this.zain.requiresSignIn()) {
                this.zait = googleApi.zaa(googleApiManager.zaic, googleApiManager.handler);
            } else {
                this.zait = null;
            }
        }

        public final void onConnected(Bundle bundle) {
            if (Looper.myLooper() == this.zail.handler.getLooper()) {
                zabg();
            } else {
                this.zail.handler.post(new zabj(this));
            }
        }

        private final void zabg() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.api.internal.GoogleApiManager.zaa.zabg():void. bs: []
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
            r4 = this;
            r4.zabl();
            r0 = com.google.android.gms.common.ConnectionResult.RESULT_SUCCESS;
            r4.zai(r0);
            r4.zabn();
            r0 = r4.zair;
            r0 = r0.values();
            r0 = r0.iterator();
        L_0x0015:
            r1 = r0.hasNext();
            if (r1 == 0) goto L_0x004b;
        L_0x001b:
            r1 = r0.next();
            r1 = (com.google.android.gms.common.api.internal.zabw) r1;
            r2 = r1.zajw;
            r2 = r2.getRequiredFeatures();
            r2 = r4.zaa(r2);
            if (r2 == 0) goto L_0x0031;
        L_0x002d:
            r0.remove();
            goto L_0x0015;
        L_0x0031:
            r1 = r1.zajw;	 Catch:{ DeadObjectException -> 0x0042, RemoteException -> 0x003e }
            r2 = r4.zaio;	 Catch:{ DeadObjectException -> 0x0042, RemoteException -> 0x003e }
            r3 = new com.google.android.gms.tasks.TaskCompletionSource;	 Catch:{ DeadObjectException -> 0x0042, RemoteException -> 0x003e }
            r3.<init>();	 Catch:{ DeadObjectException -> 0x0042, RemoteException -> 0x003e }
            r1.registerListener(r2, r3);	 Catch:{ DeadObjectException -> 0x0042, RemoteException -> 0x003e }
            goto L_0x0015;
        L_0x003e:
            r0.remove();
            goto L_0x0015;
        L_0x0042:
            r0 = 1;
            r4.onConnectionSuspended(r0);
            r0 = r4.zain;
            r0.disconnect();
        L_0x004b:
            r4.zabi();
            r4.zabo();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.GoogleApiManager.zaa.zabg():void");
        }

        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == this.zail.handler.getLooper()) {
                zabh();
            } else {
                this.zail.handler.post(new zabk(this));
            }
        }

        private final void zabh() {
            zabl();
            this.zaiu = true;
            this.zaip.zaai();
            this.zail.handler.sendMessageDelayed(Message.obtain(this.zail.handler, 9, this.zafp), this.zail.zahy);
            this.zail.handler.sendMessageDelayed(Message.obtain(this.zail.handler, 11, this.zafp), this.zail.zahz);
            this.zail.zaie.flush();
        }

        public final void zag(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(this.zail.handler);
            this.zain.disconnect();
            onConnectionFailed(connectionResult);
        }

        private final boolean zah(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                if (this.zail.zaii == null || !this.zail.zaij.contains(this.zafp)) {
                    return false;
                }
                this.zail.zaii.zab(connectionResult, this.zais);
                return true;
            }
        }

        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == this.zail.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                this.zail.handler.post(new zabl(this, connectionResult));
            }
        }

        public final void onConnectionFailed(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(this.zail.handler);
            if (this.zait != null) {
                this.zait.zabs();
            }
            zabl();
            this.zail.zaie.flush();
            zai(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zac(GoogleApiManager.zahx);
            } else if (this.zaim.isEmpty()) {
                this.zaiw = connectionResult;
            } else {
                if (!(zah(connectionResult) || this.zail.zac(connectionResult, this.zais))) {
                    if (connectionResult.getErrorCode() == 18) {
                        this.zaiu = true;
                    }
                    if (this.zaiu) {
                        this.zail.handler.sendMessageDelayed(Message.obtain(this.zail.handler, 9, this.zafp), this.zail.zahy);
                        return;
                    }
                    String zan = this.zafp.zan();
                    StringBuilder stringBuilder = new StringBuilder(String.valueOf(zan).length() + 38);
                    stringBuilder.append("API: ");
                    stringBuilder.append(zan);
                    stringBuilder.append(" is not available on this device.");
                    zac(new Status(17, stringBuilder.toString()));
                }
            }
        }

        private final void zabi() {
            ArrayList arrayList = new ArrayList(this.zaim);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                zab zab = (zab) obj;
                if (!this.zain.isConnected()) {
                    return;
                }
                if (zab(zab)) {
                    this.zaim.remove(zab);
                }
            }
        }

        public final void zaa(zab zab) {
            Preconditions.checkHandlerThread(this.zail.handler);
            if (!this.zain.isConnected()) {
                this.zaim.add(zab);
                if (this.zaiw == null || !this.zaiw.hasResolution()) {
                    connect();
                } else {
                    onConnectionFailed(this.zaiw);
                }
            } else if (zab(zab)) {
                zabo();
            } else {
                this.zaim.add(zab);
            }
        }

        public final void zabj() {
            Preconditions.checkHandlerThread(this.zail.handler);
            zac(GoogleApiManager.zahw);
            this.zaip.zaah();
            for (ListenerKey zah : (ListenerKey[]) this.zair.keySet().toArray(new ListenerKey[this.zair.size()])) {
                zaa(new zah(zah, new TaskCompletionSource()));
            }
            zai(new ConnectionResult(4));
            if (this.zain.isConnected()) {
                this.zain.onUserSignOut(new zabm(this));
            }
        }

        public final Client zaab() {
            return this.zain;
        }

        public final Map<ListenerKey<?>, zabw> zabk() {
            return this.zair;
        }

        public final void zabl() {
            Preconditions.checkHandlerThread(this.zail.handler);
            this.zaiw = null;
        }

        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(this.zail.handler);
            return this.zaiw;
        }

        private final boolean zab(zab zab) {
            if (zab instanceof zac) {
                zac zac = (zac) zab;
                Feature zaa = zaa(zac.zab(this));
                if (zaa == null) {
                    zac(zab);
                    return true;
                }
                if (zac.zac(this)) {
                    zab zab2 = new zab(this.zafp, zaa, null);
                    int indexOf = this.zaiv.indexOf(zab2);
                    if (indexOf >= 0) {
                        zab2 = (zab) this.zaiv.get(indexOf);
                        this.zail.handler.removeMessages(15, zab2);
                        this.zail.handler.sendMessageDelayed(Message.obtain(this.zail.handler, 15, zab2), this.zail.zahy);
                    } else {
                        this.zaiv.add(zab2);
                        this.zail.handler.sendMessageDelayed(Message.obtain(this.zail.handler, 15, zab2), this.zail.zahy);
                        this.zail.handler.sendMessageDelayed(Message.obtain(this.zail.handler, 16, zab2), this.zail.zahz);
                        ConnectionResult connectionResult = new ConnectionResult(2, null);
                        if (!zah(connectionResult)) {
                            this.zail.zac(connectionResult, this.zais);
                        }
                    }
                } else {
                    zac.zaa(new UnsupportedApiCallException(zaa));
                }
                return false;
            }
            zac(zab);
            return true;
        }

        private final void zac(com.google.android.gms.common.api.internal.zab r3) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.api.internal.GoogleApiManager.zaa.zac(com.google.android.gms.common.api.internal.zab):void. bs: []
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
            r2 = this;
            r0 = r2.zaip;
            r1 = r2.requiresSignIn();
            r3.zaa(r0, r1);
            r3.zaa(r2);	 Catch:{ DeadObjectException -> 0x000d }
            return;
        L_0x000d:
            r3 = 1;
            r2.onConnectionSuspended(r3);
            r3 = r2.zain;
            r3.disconnect();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.GoogleApiManager.zaa.zac(com.google.android.gms.common.api.internal.zab):void");
        }

        public final void zac(Status status) {
            Preconditions.checkHandlerThread(this.zail.handler);
            for (zab zaa : this.zaim) {
                zaa.zaa(status);
            }
            this.zaim.clear();
        }

        public final void resume() {
            Preconditions.checkHandlerThread(this.zail.handler);
            if (this.zaiu) {
                connect();
            }
        }

        private final void zabn() {
            if (this.zaiu) {
                this.zail.handler.removeMessages(11, this.zafp);
                this.zail.handler.removeMessages(9, this.zafp);
                this.zaiu = false;
            }
        }

        public final void zaav() {
            Preconditions.checkHandlerThread(this.zail.handler);
            if (this.zaiu) {
                Status status;
                zabn();
                if (this.zail.zaid.isGooglePlayServicesAvailable(this.zail.zaic) == 18) {
                    status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                } else {
                    status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                zac(status);
                this.zain.disconnect();
            }
        }

        private final void zabo() {
            this.zail.handler.removeMessages(12, this.zafp);
            this.zail.handler.sendMessageDelayed(this.zail.handler.obtainMessage(12, this.zafp), this.zail.zaia);
        }

        public final boolean zabp() {
            return zac(true);
        }

        private final boolean zac(boolean z) {
            Preconditions.checkHandlerThread(this.zail.handler);
            if (!this.zain.isConnected() || this.zair.size() != 0) {
                return false;
            }
            if (this.zaip.zaag()) {
                if (z) {
                    zabo();
                }
                return false;
            }
            this.zain.disconnect();
            return true;
        }

        public final void connect() {
            Preconditions.checkHandlerThread(this.zail.handler);
            if (!this.zain.isConnected() && !this.zain.isConnecting()) {
                int clientAvailability = this.zail.zaie.getClientAvailability(this.zail.zaic, this.zain);
                if (clientAvailability != 0) {
                    onConnectionFailed(new ConnectionResult(clientAvailability, null));
                    return;
                }
                zach zac = new zac(this.zail, this.zain, this.zafp);
                if (this.zain.requiresSignIn()) {
                    this.zait.zaa(zac);
                }
                this.zain.connect(zac);
            }
        }

        public final void zaa(zak zak) {
            Preconditions.checkHandlerThread(this.zail.handler);
            this.zaiq.add(zak);
        }

        private final void zai(ConnectionResult connectionResult) {
            for (zak zak : this.zaiq) {
                String str = null;
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.zain.getEndpointPackageName();
                }
                zak.zaa(this.zafp, connectionResult, str);
            }
            this.zaiq.clear();
        }

        final boolean isConnected() {
            return this.zain.isConnected();
        }

        public final boolean requiresSignIn() {
            return this.zain.requiresSignIn();
        }

        public final int getInstanceId() {
            return this.zais;
        }

        final zad zabq() {
            return this.zait == null ? null : this.zait.zabq();
        }

        private final Feature zaa(Feature[] featureArr) {
            if (featureArr == null || featureArr.length == 0) {
                return null;
            }
            Feature[] availableFeatures = this.zain.getAvailableFeatures();
            int i = 0;
            if (availableFeatures == null) {
                availableFeatures = new Feature[0];
            }
            Map buVar = new bu(availableFeatures.length);
            for (Feature feature : availableFeatures) {
                buVar.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            int length = featureArr.length;
            while (i < length) {
                Feature feature2 = featureArr[i];
                if (!buVar.containsKey(feature2.getName()) || ((Long) buVar.get(feature2.getName())).longValue() < feature2.getVersion()) {
                    return feature2;
                }
                i++;
            }
            return null;
        }

        private final void zaa(zab zab) {
            if (this.zaiv.contains(zab) && !this.zaiu) {
                if (this.zain.isConnected()) {
                    zabi();
                } else {
                    connect();
                }
            }
        }

        private final void zab(zab zab) {
            if (this.zaiv.remove(zab)) {
                this.zail.handler.removeMessages(15, zab);
                this.zail.handler.removeMessages(16, zab);
                Object zad = zab.zajb;
                ArrayList arrayList = new ArrayList(this.zaim.size());
                for (zab zab2 : this.zaim) {
                    if (zab2 instanceof zac) {
                        Object[] zab3 = ((zac) zab2).zab(this);
                        if (zab3 != null && ArrayUtils.contains(zab3, zad)) {
                            arrayList.add(zab2);
                        }
                    }
                }
                arrayList = arrayList;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zab zab4 = (zab) obj;
                    this.zaim.remove(zab4);
                    zab4.zaa(new UnsupportedApiCallException(zad));
                }
            }
        }
    }

    public static GoogleApiManager zab(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zaib == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zaib = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zaib;
        }
        return googleApiManager;
    }

    public static GoogleApiManager zabc() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zaib, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zaib;
        }
        return googleApiManager;
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (lock) {
            if (zaib != null) {
                GoogleApiManager googleApiManager = zaib;
                googleApiManager.zaig.incrementAndGet();
                googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
            }
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zaic = context;
        this.handler = new zal(looper, this);
        this.zaid = googleApiAvailability;
        this.zaie = new GoogleApiAvailabilityCache(googleApiAvailability);
        this.handler.sendMessage(this.handler.obtainMessage(6));
    }

    public final int zabd() {
        return this.zaif.getAndIncrement();
    }

    public final void zaa(GoogleApi<?> googleApi) {
        this.handler.sendMessage(this.handler.obtainMessage(7, googleApi));
    }

    private final void zab(GoogleApi<?> googleApi) {
        zai zak = googleApi.zak();
        zaa zaa = (zaa) this.zaih.get(zak);
        if (zaa == null) {
            zaa = new zaa(this, googleApi);
            this.zaih.put(zak, zaa);
        }
        if (zaa.requiresSignIn()) {
            this.zaik.add(zak);
        }
        zaa.connect();
    }

    public final void zaa(zaae zaae) {
        synchronized (lock) {
            if (this.zaii != zaae) {
                this.zaii = zaae;
                this.zaij.clear();
            }
            this.zaij.addAll(zaae.zaaj());
        }
    }

    final void zab(zaae zaae) {
        synchronized (lock) {
            if (this.zaii == zaae) {
                this.zaii = null;
                this.zaij.clear();
            }
        }
    }

    public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> iterable) {
        zak zak = new zak(iterable);
        this.handler.sendMessage(this.handler.obtainMessage(2, zak));
        return zak.getTask();
    }

    public final void zao() {
        this.handler.sendMessage(this.handler.obtainMessage(3));
    }

    final void maybeSignOut() {
        this.zaig.incrementAndGet();
        this.handler.sendMessage(this.handler.obtainMessage(10));
    }

    public final Task<Boolean> zac(GoogleApi<?> googleApi) {
        zaaf zaaf = new zaaf(googleApi.zak());
        this.handler.sendMessage(this.handler.obtainMessage(14, zaaf));
        return zaaf.zaal().getTask();
    }

    public final <O extends ApiOptions> void zaa(GoogleApi<O> googleApi, int i, ApiMethodImpl<? extends Result, AnyClient> apiMethodImpl) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zabv(new zae(i, apiMethodImpl), this.zaig.get(), googleApi)));
    }

    public final <O extends ApiOptions, ResultT> void zaa(GoogleApi<O> googleApi, int i, TaskApiCall<AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zabv(new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zaig.get(), googleApi)));
    }

    public final <O extends ApiOptions> Task<Void> zaa(GoogleApi<O> googleApi, RegisterListenerMethod<AnyClient, ?> registerListenerMethod, UnregisterListenerMethod<AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(8, new zabv(new zaf(new zabw(registerListenerMethod, unregisterListenerMethod), taskCompletionSource), this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends ApiOptions> Task<Boolean> zaa(GoogleApi<O> googleApi, ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(13, new zabv(new zah(listenerKey, taskCompletionSource), this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public boolean handleMessage(Message message) {
        long j = 300000;
        zaa zaa;
        zaa zaa2;
        zai zak;
        zab zab;
        switch (message.what) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.zaia = j;
                this.handler.removeMessages(12);
                for (zai obtainMessage : this.zaih.keySet()) {
                    this.handler.sendMessageDelayed(this.handler.obtainMessage(12, obtainMessage), this.zaia);
                }
                break;
            case 2:
                zak zak2 = (zak) message.obj;
                for (zai obtainMessage2 : zak2.zap()) {
                    zaa = (zaa) this.zaih.get(obtainMessage2);
                    if (zaa == null) {
                        zak2.zaa(obtainMessage2, new ConnectionResult(13), null);
                        break;
                    } else if (zaa.isConnected()) {
                        zak2.zaa(obtainMessage2, ConnectionResult.RESULT_SUCCESS, zaa.zaab().getEndpointPackageName());
                    } else if (zaa.zabm() != null) {
                        zak2.zaa(obtainMessage2, zaa.zabm(), null);
                    } else {
                        zaa.zaa(zak2);
                        zaa.connect();
                    }
                }
                break;
            case 3:
                for (zaa zaa22 : this.zaih.values()) {
                    zaa22.zabl();
                    zaa22.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zabv zabv = (zabv) message.obj;
                zaa22 = (zaa) this.zaih.get(zabv.zajs.zak());
                if (zaa22 == null) {
                    zab(zabv.zajs);
                    zaa22 = (zaa) this.zaih.get(zabv.zajs.zak());
                }
                if (zaa22.requiresSignIn() && this.zaig.get() != zabv.zajr) {
                    zabv.zajq.zaa(zahw);
                    zaa22.zabj();
                    break;
                }
                zaa22.zaa(zabv.zajq);
                break;
                break;
            case 5:
                StringBuilder stringBuilder;
                String errorString;
                String errorMessage;
                StringBuilder stringBuilder2;
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                for (zaa zaa3 : this.zaih.values()) {
                    if (zaa3.getInstanceId() == i) {
                        if (zaa3 != null) {
                            stringBuilder = new StringBuilder(76);
                            stringBuilder.append("Could not find API instance ");
                            stringBuilder.append(i);
                            stringBuilder.append(" while trying to fail enqueued calls.");
                            Log.wtf("GoogleApiManager", stringBuilder.toString(), new Exception());
                            break;
                        }
                        errorString = this.zaid.getErrorString(connectionResult.getErrorCode());
                        errorMessage = connectionResult.getErrorMessage();
                        stringBuilder2 = new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length());
                        stringBuilder2.append("Error resolution was canceled by the user, original error message: ");
                        stringBuilder2.append(errorString);
                        stringBuilder2.append(": ");
                        stringBuilder2.append(errorMessage);
                        zaa3.zac(new Status(17, stringBuilder2.toString()));
                        break;
                    }
                }
                zaa3 = null;
                if (zaa3 != null) {
                    stringBuilder = new StringBuilder(76);
                    stringBuilder.append("Could not find API instance ");
                    stringBuilder.append(i);
                    stringBuilder.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", stringBuilder.toString(), new Exception());
                } else {
                    errorString = this.zaid.getErrorString(connectionResult.getErrorCode());
                    errorMessage = connectionResult.getErrorMessage();
                    stringBuilder2 = new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length());
                    stringBuilder2.append("Error resolution was canceled by the user, original error message: ");
                    stringBuilder2.append(errorString);
                    stringBuilder2.append(": ");
                    stringBuilder2.append(errorMessage);
                    zaa3.zac(new Status(17, stringBuilder2.toString()));
                }
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zaic.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zaic.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabi(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaia = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zab((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zaih.containsKey(message.obj)) {
                    ((zaa) this.zaih.get(message.obj)).resume();
                    break;
                }
                break;
            case 10:
                for (zai zak3 : this.zaik) {
                    ((zaa) this.zaih.remove(zak3)).zabj();
                }
                this.zaik.clear();
                break;
            case 11:
                if (this.zaih.containsKey(message.obj)) {
                    ((zaa) this.zaih.get(message.obj)).zaav();
                    break;
                }
                break;
            case 12:
                if (this.zaih.containsKey(message.obj)) {
                    ((zaa) this.zaih.get(message.obj)).zabp();
                    break;
                }
                break;
            case 14:
                zaaf zaaf = (zaaf) message.obj;
                zak3 = zaaf.zak();
                if (!this.zaih.containsKey(zak3)) {
                    zaaf.zaal().setResult(Boolean.FALSE);
                    break;
                }
                zaaf.zaal().setResult(Boolean.valueOf(((zaa) this.zaih.get(zak3)).zac(false)));
                break;
            case 15:
                zab = (zab) message.obj;
                if (this.zaih.containsKey(zab.zaja)) {
                    ((zaa) this.zaih.get(zab.zaja)).zaa(zab);
                    break;
                }
                break;
            case 16:
                zab = (zab) message.obj;
                if (this.zaih.containsKey(zab.zaja)) {
                    ((zaa) this.zaih.get(zab.zaja)).zab(zab);
                    break;
                }
                break;
            default:
                int i2 = message.what;
                StringBuilder stringBuilder3 = new StringBuilder(31);
                stringBuilder3.append("Unknown message id: ");
                stringBuilder3.append(i2);
                Log.w("GoogleApiManager", stringBuilder3.toString());
                return false;
        }
        return true;
    }

    final PendingIntent zaa(zai<?> zai, int i) {
        zaa zaa = (zaa) this.zaih.get(zai);
        if (zaa == null) {
            return null;
        }
        zad zabq = zaa.zabq();
        if (zabq == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaic, i, zabq.getSignInIntent(), C.SAMPLE_FLAG_DECODE_ONLY);
    }

    final boolean zac(ConnectionResult connectionResult, int i) {
        return this.zaid.zaa(this.zaic, connectionResult, i);
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        if (!zac(connectionResult, i)) {
            this.handler.sendMessage(this.handler.obtainMessage(5, i, 0, connectionResult));
        }
    }
}
