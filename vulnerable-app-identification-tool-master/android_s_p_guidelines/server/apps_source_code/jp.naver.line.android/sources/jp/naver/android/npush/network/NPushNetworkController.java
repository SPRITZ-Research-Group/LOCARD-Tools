package jp.naver.android.npush.network;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import com.google.android.exoplayer.C;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.obf.ly;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import jp.naver.android.npush.common.Logger;
import jp.naver.android.npush.common.NPushConstant;
import jp.naver.android.npush.common.NPushConstant.ClientType;
import jp.naver.android.npush.common.NPushIntent;
import jp.naver.android.npush.library.PermissionManager.WakeLockWrapper;
import jp.naver.android.npush.network.NPushConnectedData.SubscribeInfo;
import jp.naver.android.npush.network.NPushSocketChannel.NetworkStatusChanged;
import jp.naver.android.npush.network.NPushSocketChannel.ReceivedPacket;
import jp.naver.android.npush.register.NPushPreferences;
import org.json.JSONObject;

public class NPushNetworkController {
    static final long CHECK_SERVICE_INTERVAL = 1620000;
    static final int EXCEPTION_RETRY_CONNECT_INTERVAL = 45000;
    static final int EXCEPTION_RETRY_CONNECT_MIN = 15000;
    static final long INTERVAL_HEALTH_CHECK_RESET = 180000;
    static final int INTERVAL_RECONNECT_AFTER_ERROR = 240000;
    static final int INTERVAL_RECONNECT_AFTER_MARGIN = 180000;
    static final int LIMIT_BACKOFF_MS = ((int) TimeUnit.SECONDS.toMillis(480));
    static int LOOKUP_RECONNECT_COUNT = 7;
    static final int MAX_BACKOFF_MS = ((int) TimeUnit.SECONDS.toMillis(720));
    static int RECONNECT_COUNT = 5;
    static final int REQUEST_CODE_TO_CHECK_LIVE_SERVICE = 1;
    static final int REQUEST_CODE_TO_CHECK_SERVICE = 3;
    static final int REQUEST_CODE_TO_LOG = 5;
    static final int REQUEST_CODE_TO_RESTART = 4;
    static final int REQUEST_CODE_TO_RETRY_CONNECT = 2;
    static final int REQUEST_CODE_TO_RETRY_LOOKUP_CONNECT = 0;
    static NPushNetworkController instance;
    static final Random sRandom = new Random();
    private final Runnable ConnectNPushServerHandler = new Runnable() {
        public synchronized void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.android.npush.network.NPushNetworkController.7.run():void. bs: []
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
            r3 = this;
            monitor-enter(r3);
            r0 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = r0.getCurrentNetworkStatus();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = 5;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            if (r0 != r1) goto L_0x0023;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
        L_0x000a:
            r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = "Runnable ConnectNPushHandler before return = ";	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0.<init>(r1);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = r1.getCurrentNetworkStatus();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0.append(r1);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = r0.toString();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            jp.naver.android.npush.common.Logger.d(r0);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            monitor-exit(r3);
            return;
        L_0x0023:
            r0 = "Runnable ConnectNPushHandler";	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            jp.naver.android.npush.common.Logger.d(r0);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = jp.naver.android.npush.network.NPushSocketChannel.getInstance();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0.npushSocketChannel = r1;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = r0.npushSocketChannel;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = new jp.naver.android.npush.network.NPushNetworkController$7$1;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1.<init>();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0.setReceivedPacket(r1);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = r0.npushSocketChannel;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = new jp.naver.android.npush.network.NPushNetworkController$7$2;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1.<init>();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0.setNetworkStatusChanged(r1);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = r0.npushSocketChannel;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = 1;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r2 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r2 = r2.requestConnectJson;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = r0.connectNPushServer(r1, r2);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            if (r0 != 0) goto L_0x0076;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
        L_0x0060:
            r0 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0 = r0.isShutdown();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            if (r0 != 0) goto L_0x0076;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
        L_0x0068:
            r0 = new java.lang.Thread;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = jp.naver.android.npush.network.NPushNetworkController.this;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r1 = r1.npushSocketChannel;	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0.<init>(r1);	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
            r0.start();	 Catch:{ IOException -> 0x007b, all -> 0x0078 }
        L_0x0076:
            monitor-exit(r3);
            return;
        L_0x0078:
            r0 = move-exception;
            monitor-exit(r3);
            throw r0;
        L_0x007b:
            monitor-exit(r3);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: jp.naver.android.npush.network.NPushNetworkController.7.run():void");
        }
    };
    final int INTERVAL_CHECK_AFTER_CONNECT = DefaultLoadControl.DEFAULT_HIGH_WATERMARK_MS;
    final int INTERVAL_WAIT_FOR_RESPONSE = DefaultLoadControl.DEFAULT_HIGH_WATERMARK_MS;
    private final Runnable connectLookupServerTask = new Runnable() {
        public void run() {
            try {
                if (NPushNetworkController.this.getCurrentNetworkStatus() == 5) {
                    StringBuilder stringBuilder = new StringBuilder("Runnable ConnectNniHandler before return = ");
                    stringBuilder.append(NPushNetworkController.this.getCurrentNetworkStatus());
                    Logger.d(stringBuilder.toString());
                    return;
                }
                Logger.d("Runnable ConnectNniHandler, connectLookupServerTask");
                NPushNetworkController.this.npushSocketChannel = NPushSocketChannel.getInstance();
                NPushNetworkController.this.npushSocketChannel.setReceivedPacket(new ReceivedPacket() {
                    public void onReceivedPacket(JSONObject jSONObject) {
                        NPushNetworkController.this.receivedPacket(jSONObject);
                    }
                });
                NPushNetworkController.this.npushSocketChannel.setNetworkStatusChanged(new NetworkStatusChanged() {
                    public void onNetworkStatusChanged(int i) {
                        NPushNetworkController.this.networkStatusChanged(i);
                    }
                });
                if (NPushNetworkController.this.npushSocketChannel.connectNPushServer(0, NPushNetworkController.this.requestConnectLookupJson) == 0 && !NPushNetworkController.this.isShutdown()) {
                    new Thread(NPushNetworkController.this.npushSocketChannel).start();
                }
            } catch (Throwable e) {
                Logger.e(e);
            } catch (Throwable e2) {
                Logger.e(e2);
            }
        }
    };
    private NetworkConnectivityReceiver connectivityReceiver;
    long crsTime = 0;
    long currentTime = 0;
    private final Runnable disconnectLookupServerTask = new Runnable() {
        public void run() {
            try {
                NPushSocketChannel instance = NPushSocketChannel.getInstance();
                if (instance != null) {
                    instance.disconnectNPushServer();
                }
            } catch (Throwable e) {
                Logger.e(e);
            }
        }
    };
    private final Runnable disconnectNPushServerTask = new Runnable() {
        public void run() {
            try {
                NPushSocketChannel instance = NPushSocketChannel.getInstance();
                if (instance != null) {
                    instance.disconnectNPushServer();
                }
            } catch (Throwable e) {
                Logger.e(e);
            }
        }
    };
    private boolean isShutdown = false;
    AtomicInteger mLookupRetryCount = new AtomicInteger(0);
    AtomicInteger mRetryCount = new AtomicInteger(0);
    private Service mServiceContext;
    final Handler networkExecuteHandler;
    final HandlerThread networkWorker = new HandlerThread("NPushNetwork");
    private NPushSocketChannel npushSocketChannel;
    long oldTime = 0;
    private long pingInterval = 0;
    Random reconnRandom = new Random(Calendar.getInstance().getTimeInMillis());
    private int reconnectInterval = 0;
    final List<JSONObject> recvJsonPackets = Collections.synchronizedList(new ArrayList());
    long recvTime = 0;
    private final Runnable requestApiCallHandler = new Runnable() {
        public synchronized void run() {
            try {
                Logger.d("NPushNetworkController.requestApiCallHandler is called");
                NPushSocketChannel instance = NPushSocketChannel.getInstance();
                if (NPushNetworkController.this.sendJsonPackets.size() != 0) {
                    for (int i = 0; i < NPushNetworkController.this.sendJsonPackets.size(); i++) {
                        instance.sendRequestPacket((JSONObject) NPushNetworkController.this.sendJsonPackets.get(i));
                    }
                    NPushNetworkController.this.sendJsonPackets.clear();
                }
            } catch (IOException e) {
                Logger.d(e.toString());
            }
        }
    };
    private JSONObject requestConnectJson;
    private JSONObject requestConnectLookupJson;
    private final Runnable resetConnectLookupServerTask = new Runnable() {
        public void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.android.npush.network.NPushNetworkController.2.run():void. bs: []
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
            r1 = this;
            r0 = jp.naver.android.npush.network.NPushSocketChannel.getInstance();	 Catch:{ Exception -> 0x000a }
            if (r0 == 0) goto L_0x0009;	 Catch:{ Exception -> 0x000a }
        L_0x0006:
            r0.resetConnectLookupServer();	 Catch:{ Exception -> 0x000a }
        L_0x0009:
            return;
        L_0x000a:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: jp.naver.android.npush.network.NPushNetworkController.2.run():void");
        }
    };
    private final Runnable resetConnectNPushServerTask = new Runnable() {
        public void run() {
            try {
                NPushSocketChannel instance = NPushSocketChannel.getInstance();
                if (instance != null) {
                    instance.resetConnectNPushServer();
                }
            } catch (Throwable e) {
                Logger.e(e);
            }
        }
    };
    private final Map<Object, Routing> routingHandlers = new ConcurrentHashMap();
    final List<JSONObject> sendJsonPackets = Collections.synchronizedList(new ArrayList());
    long sendTime = 0;

    public interface Routing {
        void onRoutePacket(int i, int i2, JSONObject jSONObject);
    }

    private int resolveReconnectInterval(int i) {
        return i == 0 ? 0 : i * 1000;
    }

    public static NPushNetworkController getInstance() {
        if (instance == null) {
            instance = new NPushNetworkController();
        }
        return instance;
    }

    public static void initInstance() {
        instance = null;
    }

    private NPushNetworkController() {
        this.networkWorker.start();
        this.networkWorker.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                Logger.e(th);
                NPushNetworkController.this.restartService();
            }
        });
        this.networkExecuteHandler = new Handler(this.networkWorker.getLooper());
        setRoutePacket(this, NPushBaseRoutePacket.getInstance());
    }

    public boolean setRoutePacket(Object obj, Routing routing) {
        if (this.routingHandlers.containsKey(obj)) {
            return false;
        }
        this.routingHandlers.put(obj, routing);
        return true;
    }

    public void releaseRoutePacket(Object obj) {
        if (this.routingHandlers.containsKey(obj)) {
            this.routingHandlers.remove(obj);
        }
    }

    public int getRouteCount() {
        return this.routingHandlers.size();
    }

    public boolean isShutdown() {
        return this.isShutdown;
    }

    public void setShutdown(boolean z) {
        this.isShutdown = z;
    }

    void restartService() {
        Logger.w("Service Process is going to DIE !!!");
        if (this.mServiceContext == null || isShutdown()) {
            Logger.w("Service Process.KillProcess !!!");
            Process.killProcess(Process.myPid());
            return;
        }
        if (!NPushConnectedData.getInstance().getSubscribeMap().isEmpty()) {
            long restartTimeAfterDestory = NPushPreferences.getRestartTimeAfterDestory(this.mServiceContext);
            Intent intent = new Intent(NPushIntent.REQUEST_SUBSCRIBE_INTENT);
            intent.putExtra(NPushIntent.EXTRA_APPLICATION_SERVICE_ID, NPushIntent.Self);
            intent.putExtra(NPushIntent.EXTRA_APPLICATION_REQUESTCODE, 4);
            ((AlarmManager) this.mServiceContext.getSystemService("alarm")).set(0, System.currentTimeMillis() + restartTimeAfterDestory, PendingIntent.getService(this.mServiceContext, 4, intent, 268435456));
            StringBuilder stringBuilder = new StringBuilder("restartService set alarmmanger ");
            stringBuilder.append(restartTimeAfterDestory);
            stringBuilder.append(" ms");
            Logger.d(stringBuilder.toString());
        }
        this.mServiceContext.stopSelf();
    }

    private void requestClientHealthCheck() {
        if (NPushSocketChannel.getInstance().getCurrentNetworkStatus() == 7) {
            try {
                NPushRequestApi.requestClientHealthCheck();
            } catch (Throwable e) {
                Logger.e(e);
            }
        } else if (NPushConnectedData.getInstance().getSubscribeMap().isEmpty()) {
            Logger.w("requestClientHealthCheck : Subscribe ServiceId is not exist, Stop NNI Service");
            restartService();
        } else {
            Logger.w("requestClientHealthCheck : need Subscribe");
        }
    }

    public void setServiceContext(Context context) {
        this.mServiceContext = (Service) context;
        cancelRegisterAlarmManager(0);
        cancelRegisterAlarmManager(1);
        cancelRegisterAlarmManager(2);
        cancelRegisterAlarmManager(3);
        cancelRegisterAlarmManager(4);
    }

    public Service getServiceContext() {
        return this.mServiceContext;
    }

    public void SERVICE_procOnCreate() {
        WakeLockWrapper instance = WakeLockWrapper.getInstance(this.mServiceContext);
        NPushConstant.wakeLockRefCount.incrementAndGet();
        if (!instance.acquire(500)) {
            NPushConstant.wakeLockRefCount.decrementAndGet();
        }
        registerConnectivityReceiver();
        NPushPreferences.resetBackoff(this.mServiceContext);
        NPushConnectedData.getInstance().setAlternativeConnect(false);
        NPushConnectedData.getInstance().setIsLookup(false);
        NPushVirtualConnectionLayer.getInstance().resetLookupRetryCount();
        resetConnectLookupServer();
        this.networkExecuteHandler.post(new Runnable() {
            public void run() {
                StringBuilder stringBuilder = new StringBuilder("NNI Version: 7.2.5, Server Information: ");
                stringBuilder.append(NPushConstant.getClientType(NPushNetworkController.this.mServiceContext));
                String stringBuilder2 = stringBuilder.toString();
                Logger.m(NPushConstant.TAG, stringBuilder2);
                Logger.i(stringBuilder2);
            }
        });
        scheduleBackoff();
    }

    public void SERVICE_onDestroy() {
        cancelRegisterAlarmManager(0);
        cancelRegisterAlarmManager(1);
        cancelRegisterAlarmManager(2);
        cancelRegisterAlarmManager(3);
        disconnectNPushServer();
        unregisterConnectivityReceiver();
        NPushBaseRoutePacket.getInstance().close();
        this.networkExecuteHandler.removeMessages(0, null);
        this.networkWorker.quit();
        WakeLockWrapper.getInstance(this.mServiceContext).release();
    }

    public void wakeLockRelease(WakeLockWrapper wakeLockWrapper) {
        if (NPushConstant.wakeLockRefCount.decrementAndGet() == 0) {
            wakeLockWrapper.release();
        } else {
            NPushConstant.wakeLockRefCount.incrementAndGet();
        }
    }

    public void SERVICE_handleRequestIntent(Intent intent) {
        this.sendTime = System.currentTimeMillis();
        WakeLockWrapper instance = WakeLockWrapper.getInstance(this.mServiceContext);
        NPushConstant.wakeLockRefCount.incrementAndGet();
        if (this.crsTime > 1500) {
            if (!instance.acquire(200)) {
                NPushConstant.wakeLockRefCount.decrementAndGet();
            }
        } else if (!instance.acquire(1500)) {
            NPushConstant.wakeLockRefCount.decrementAndGet();
        }
        StringBuilder stringBuilder = new StringBuilder("NPushNetworkController.SERVICE_handleRequestIntent(): ");
        stringBuilder.append(intent.getAction());
        Logger.i(stringBuilder.toString());
        ClientType fromString = ClientType.fromString(intent.getStringExtra(NPushIntent.EXTRA_APPLICATION_CLIENT_TYPE));
        if (fromString != null) {
            ClientType clientType = NPushConstant.setClientType(fromString);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getClass().getSimpleName());
            stringBuilder2.append(": change Clinet type from ");
            stringBuilder2.append(clientType);
            stringBuilder2.append(", to ");
            stringBuilder2.append(fromString);
            Logger.i(stringBuilder2.toString());
            return;
        }
        int i = 0;
        PendingIntent pendingIntent;
        String stringExtra;
        int intExtra;
        String stringExtra2;
        StringBuilder stringBuilder3;
        if (NPushIntent.REQUEST_SUBSCRIBE_INTENT.equals(intent.getAction())) {
            pendingIntent = (PendingIntent) intent.getParcelableExtra(NPushIntent.EXTRA_APPLICATION_PENDING_INTENT);
            stringExtra = intent.getStringExtra(NPushIntent.EXTRA_APPLICATION_SERVICE_ID);
            if (pendingIntent != null && stringExtra == null) {
                stringExtra = pendingIntent.getCreatorPackage();
            }
            int currentNetworkStatus;
            StringBuilder stringBuilder4;
            if (stringExtra.equals(NPushIntent.Self)) {
                currentNetworkStatus = NPushSocketChannel.getInstance().getCurrentNetworkStatus();
                intExtra = intent.getIntExtra(NPushIntent.EXTRA_APPLICATION_REQUESTCODE, 3);
                Logger.i("--------AlarmManager Running----------".concat(String.valueOf(intExtra)));
                switch (intExtra) {
                    case 0:
                        stringBuilder4 = new StringBuilder("REQUEST_CODE_TO_RETRY_LOOKUP_CONNECT : retry count : ");
                        stringBuilder4.append(this.mLookupRetryCount.get() + 1);
                        Logger.i(stringBuilder4.toString());
                        cancelRegisterAlarmManager(1);
                        if (this.mLookupRetryCount.incrementAndGet() < LOOKUP_RECONNECT_COUNT) {
                            scheduleBackoff();
                            resetConnectLookupServer();
                            return;
                        }
                        restartService();
                        return;
                    case 1:
                        if (currentNetworkStatus != 7) {
                            Logger.d("currentNetworkStatus : ".concat(String.valueOf(currentNetworkStatus)));
                            return;
                        }
                        setRetryConnectAlarmManager(DefaultLoadControl.DEFAULT_HIGH_WATERMARK_MS);
                        requestClientHealthCheck();
                        return;
                    case 2:
                        cancelRegisterAlarmManager(1);
                        stringBuilder4 = new StringBuilder("REQUEST_CODE_TO_RETRY_CONNECT, retry count: ");
                        stringBuilder4.append(this.mRetryCount.get() + 1);
                        Logger.i(stringBuilder4.toString());
                        if (NPushConnectedData.getInstance().isAlternativeConnect()) {
                            Logger.i("isAlternativeConnect() = true");
                            restartService();
                            return;
                        } else if (this.mRetryCount.incrementAndGet() > RECONNECT_COUNT) {
                            restartService();
                            return;
                        } else if (NPushConnectedData.getInstance().getSubscribeMap().isEmpty()) {
                            Logger.w("REQUEST_CODE_TO_RETRY_CONNECT : Subscribe ServiceId is not exist, Stop NNI Service");
                            restartService();
                            return;
                        } else {
                            setRetryConnectAlarmManager();
                            resetConnectNPushServer();
                            return;
                        }
                    case 3:
                        wakeLockRelease(instance);
                        return;
                    case 4:
                        wakeLockRelease(instance);
                        return;
                    case 5:
                        wakeLockRelease(instance);
                        break;
                }
                return;
            }
            if (pendingIntent != null) {
                currentNetworkStatus = intent.getIntExtra(NPushIntent.EXTRA_APPLICATION_CATEGORY_ID, 0);
                boolean booleanExtra = intent.getBooleanExtra(NPushIntent.EXTRA_APPLICATION_KEEPALIVE, false) ^ true;
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append(stringExtra);
                stringBuilder5.append("_");
                stringBuilder5.append(currentNetworkStatus);
                String stringBuilder6 = stringBuilder5.toString();
                NPushConnectedData instance2 = NPushConnectedData.getInstance();
                stringExtra2 = intent.getStringExtra(NPushIntent.EXTRA_TARGET_ID);
                if (stringExtra2 != null && stringExtra2.contains(ly.a)) {
                    while (i < 2) {
                        stringExtra2 = stringExtra2.substring(stringExtra2.indexOf(46) + 1);
                        i++;
                    }
                    String refreshTargetId = instance2.refreshTargetId(this.mServiceContext, stringExtra2);
                    StringBuilder stringBuilder7 = new StringBuilder();
                    stringBuilder7.append(getClass().getSimpleName());
                    stringBuilder7.append(": Target ID is Received from a Service Application. The ID is ");
                    stringBuilder7.append(stringExtra2);
                    stringBuilder7.append(", and refreshed ID is ");
                    stringBuilder7.append(refreshTargetId);
                    Logger.d(stringBuilder7.toString());
                    if (!stringExtra2.equals(refreshTargetId)) {
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(getClass().getSimpleName());
                        stringBuilder4.append(": Target ID is different! Registration process will proceed.");
                        Logger.i(stringBuilder4.toString());
                        booleanExtra = true;
                    }
                }
                SubscribeInfo addSubscribeInfo = instance2.addSubscribeInfo(stringBuilder6, new SubscribeInfo(stringExtra, currentNetworkStatus), true);
                currentNetworkStatus = getCurrentNetworkStatus();
                if (currentNetworkStatus == 7 || currentNetworkStatus == 9) {
                    subscribeAll();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(getClass().getSimpleName());
                stringBuilder.append("Registration process...serviceId: ");
                stringBuilder.append(stringExtra);
                stringBuilder.append(", needRegistration: ");
                stringBuilder.append(booleanExtra);
                Logger.v(stringBuilder.toString());
                if (booleanExtra) {
                    if (addSubscribeInfo == null) {
                        addSubscribeInfo = NPushConnectedData.getInstance().getSubscribeInfo(stringBuilder6);
                    }
                    if (addSubscribeInfo != null) {
                        Intent action = new Intent().setAction(NPushIntent.RESPONSE_SUBSCRIBE_INTENT);
                        action.addCategory(stringExtra);
                        action.putExtra(NPushIntent.EXTRA_TARGET_ID, addSubscribeInfo.getTargetId());
                        action.setFlags(32);
                        stringBuilder3 = new StringBuilder("Send Subscribe Info TargetId : ");
                        stringBuilder3.append(addSubscribeInfo.getTargetId());
                        stringBuilder3.append(" To ");
                        stringBuilder3.append(stringExtra);
                        Logger.d(stringBuilder3.toString());
                        this.mServiceContext.sendBroadcast(action);
                    }
                }
                wakeLockRelease(instance);
            }
        } else if (NPushIntent.REQUEST_UNSUBSCRIBE_INTENT.equals(intent.getAction())) {
            pendingIntent = (PendingIntent) intent.getParcelableExtra(NPushIntent.EXTRA_APPLICATION_PENDING_INTENT);
            stringExtra = intent.getStringExtra(NPushIntent.EXTRA_APPLICATION_SERVICE_ID);
            if (stringExtra == null) {
                stringExtra = pendingIntent.getCreatorPackage();
            }
            intExtra = intent.getIntExtra(NPushIntent.EXTRA_APPLICATION_CATEGORY_ID, 0);
            if (pendingIntent != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(stringExtra);
                stringBuilder.append("_");
                stringBuilder.append(intExtra);
                stringExtra2 = stringBuilder.toString();
                Logger.w("subscribeKey : ".concat(String.valueOf(stringExtra2)));
                SubscribeInfo subscribeInfo = NPushConnectedData.getInstance().getSubscribeInfo(stringExtra2);
                if (subscribeInfo != null) {
                    if (subscribeInfo.isSuccess() && subscribeInfo.getType() == 0) {
                        Logger.w("subscribeInfo is Success true");
                        subscribeInfo.setType(1);
                        intExtra = getCurrentNetworkStatus();
                        if (intExtra == 7 || intExtra == 9) {
                            unsubscribeAll();
                        }
                    } else {
                        Logger.w("subscribeInfo is Success false");
                        NPushConnectedData.getInstance().removeSubscribeInfo(stringExtra2);
                    }
                    intent = new Intent().setAction(NPushIntent.RESPONSE_UNSUBSCRIBE_INTENT);
                    intent.addCategory(stringExtra);
                    intent.setFlags(32);
                    this.mServiceContext.sendBroadcast(intent);
                }
            }
            wakeLockRelease(instance);
        } else {
            if (NPushIntent.REQUEST_GETSTATE_INTENT.equals(intent.getAction())) {
                pendingIntent = (PendingIntent) intent.getParcelableExtra(NPushIntent.EXTRA_APPLICATION_PENDING_INTENT);
                stringExtra2 = intent.getStringExtra(NPushIntent.EXTRA_APPLICATION_SERVICE_ID);
                if (stringExtra2 == null) {
                    stringExtra2 = pendingIntent.getCreatorPackage();
                }
                if (pendingIntent != null) {
                    intent = new Intent().setAction(NPushIntent.RESPONSE_GETSTATE_INTENT).addCategory(stringExtra2);
                    String str = "NNI ";
                    switch (getCurrentNetworkStatus()) {
                        case -1:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_SOCKET_EXCEPTION");
                            str = stringBuilder3.toString();
                            break;
                        case 0:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_READY");
                            str = stringBuilder3.toString();
                            break;
                        case 2:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_CONNECTED_NPUSH");
                            str = stringBuilder3.toString();
                            break;
                        case 3:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_GRACEFULLY_CLOSED");
                            str = stringBuilder3.toString();
                            break;
                        case 4:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_DISCONNECTED");
                            str = stringBuilder3.toString();
                            break;
                        case 5:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_CONNECTIONPENDING");
                            str = stringBuilder3.toString();
                            break;
                        case 6:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_REFUSE");
                            str = stringBuilder3.toString();
                            break;
                        case 7:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_CONNECTED_SUBSCRIBE");
                            str = stringBuilder3.toString();
                            break;
                        default:
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(str);
                            stringBuilder3.append("NETWORK_STATUS_UNKNOWN");
                            str = stringBuilder3.toString();
                            break;
                    }
                    intent.putExtra(NPushIntent.EXTRA_STATE, str);
                    this.mServiceContext.sendBroadcast(intent);
                }
                wakeLockRelease(instance);
            }
        }
    }

    public void scheduleBackoff() {
        int backoff = NPushPreferences.getBackoff(this.mServiceContext);
        int nextInt = (backoff / 2) + sRandom.nextInt(backoff);
        StringBuilder stringBuilder = new StringBuilder("Scheduling Lookup Server retry, backoff = ");
        stringBuilder.append(nextInt);
        stringBuilder.append(" (");
        stringBuilder.append(backoff);
        stringBuilder.append(")");
        Logger.d(stringBuilder.toString());
        setRegisterAlarmManager(0, (long) nextInt);
        if (backoff < LIMIT_BACKOFF_MS) {
            NPushPreferences.setBackoff(this.mServiceContext, backoff * 2);
        } else {
            NPushPreferences.setBackoff(this.mServiceContext, MAX_BACKOFF_MS);
        }
    }

    private void scheduleCheckClientTimer(long j) {
        if (j > CHECK_SERVICE_INTERVAL) {
            Logger.d("scheduleCheckClientPingTimer > 1620000 thus REQUEST_CODE_TO_CHECK_SERVICE Set! : 1620000 ms");
            setRegisterAlarmManager(3, CHECK_SERVICE_INTERVAL);
            return;
        }
        Logger.d("scheduleCheckClientPingTimer <= 1620000 thus REQUEST_CODE_TO_CHECK_SERVICE Cancel!");
        cancelRegisterAlarmManager(3);
    }

    public void resetScheduleCheckClientPingTimer() {
        this.currentTime = System.currentTimeMillis();
        if (this.currentTime - this.oldTime > 180000) {
            Logger.d("\tPush receive, Client ping timer reset");
            scheduleCheckClientPingTimer((long) this.reconnectInterval);
            this.oldTime = this.currentTime;
            return;
        }
        Logger.d("\tPush receive");
    }

    private void scheduleCheckClientPingTimer(long j) {
        Logger.d("+NPushNetworkController : scheduleCheckClientPingTimer : ".concat(String.valueOf(j)));
        this.pingInterval = j + ((long) new Random(Calendar.getInstance().getTimeInMillis()).nextInt(5000));
        StringBuilder stringBuilder = new StringBuilder("\t\t=NPushNetworkController : scheduleCheckClientPingTimer : ");
        stringBuilder.append(this.pingInterval);
        Logger.d(stringBuilder.toString());
        setRegisterAlarmManager(1, this.pingInterval);
    }

    private synchronized void receivedPacket(JSONObject jSONObject) {
        if (jSONObject == null) {
            Logger.d("receivedPacket: JSONObject is NULL");
            return;
        }
        this.recvJsonPackets.add(jSONObject);
        handleReceivedPackets();
    }

    public void handleReceivedPackets() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.android.npush.network.NPushNetworkController.handleReceivedPackets():void. bs: []
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
        r9 = this;
        r0 = r9.recvJsonPackets;
        r0 = r0.size();
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r0 = 0;
        r1 = 0;
    L_0x000b:
        r2 = r9.recvJsonPackets;	 Catch:{ Exception -> 0x0112 }
        r2 = r2.size();	 Catch:{ Exception -> 0x0112 }
        if (r1 >= r2) goto L_0x0112;	 Catch:{ Exception -> 0x0112 }
    L_0x0013:
        r2 = r9.recvJsonPackets;	 Catch:{ Exception -> 0x0112 }
        r2 = r2.get(r1);	 Catch:{ Exception -> 0x0112 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ Exception -> 0x0112 }
        r3 = "command";	 Catch:{ JSONException -> 0x0108 }
        r3 = r2.getInt(r3);	 Catch:{ JSONException -> 0x0108 }
        r4 = "transid";	 Catch:{ JSONException -> 0x0108 }
        r4 = r2.getInt(r4);	 Catch:{ JSONException -> 0x0108 }
        r5 = 1;
        r6 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        switch(r3) {
            case 3401: goto L_0x00a5;
            case 34897: goto L_0x0091;
            case 35357: goto L_0x0088;
            case 36353: goto L_0x005b;
            case 36355: goto L_0x003b;
            case 36359: goto L_0x0031;
            default: goto L_0x002d;
        };
    L_0x002d:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0112 }
        goto L_0x00cb;	 Catch:{ Exception -> 0x0112 }
    L_0x0031:
        r5 = "NPushNetworkController.handleReceivedParkcet(): RES_SRC_USE_NEW_KEY";	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.d(r5);	 Catch:{ Exception -> 0x0112 }
        r9.setRetryConnectAlarmManager(r6);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00e8;	 Catch:{ Exception -> 0x0112 }
    L_0x003b:
        r5 = "NPushNetworkController.handleReceivedPackets(): RES_SRC_SUBSCRIBE";	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.d(r5);	 Catch:{ Exception -> 0x0112 }
        r9.cancelRetryConnectAlarmManager();	 Catch:{ Exception -> 0x0112 }
        r5 = r9.reconnectInterval;	 Catch:{ Exception -> 0x0112 }
        r5 = (long) r5;	 Catch:{ Exception -> 0x0112 }
        r9.scheduleCheckClientTimer(r5);	 Catch:{ Exception -> 0x0112 }
        r5 = r9.reconnectInterval;	 Catch:{ Exception -> 0x0112 }
        r5 = (long) r5;	 Catch:{ Exception -> 0x0112 }
        r9.scheduleCheckClientPingTimer(r5);	 Catch:{ Exception -> 0x0112 }
        r5 = r9.mLookupRetryCount;	 Catch:{ Exception -> 0x0112 }
        r5.set(r0);	 Catch:{ Exception -> 0x0112 }
        r5 = r9.mRetryCount;	 Catch:{ Exception -> 0x0112 }
        r5.set(r0);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00e8;	 Catch:{ Exception -> 0x0112 }
    L_0x005b:
        r5 = "NPushNetworkController.handleReceivedPackets(): response to server(RES_SRC_CONNECT)";	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.d(r5);	 Catch:{ Exception -> 0x0112 }
        r5 = jp.naver.android.npush.network.NPushConnectedData.getInstance();	 Catch:{ Exception -> 0x0112 }
        r5 = r5.parseConnectedInfo(r2);	 Catch:{ Exception -> 0x0112 }
        if (r5 == 0) goto L_0x0076;	 Catch:{ Exception -> 0x0112 }
    L_0x006a:
        r0 = r9.recvJsonPackets;	 Catch:{ Exception -> 0x0112 }
        r0.remove(r1);	 Catch:{ Exception -> 0x0112 }
        r9.disconnectNPushServer();	 Catch:{ Exception -> 0x0112 }
        r9.setRetryConnectAlarmManager(r6);	 Catch:{ Exception -> 0x0112 }
        return;	 Catch:{ Exception -> 0x0112 }
    L_0x0076:
        r5 = jp.naver.android.npush.network.NPushConnectedData.getInstance();	 Catch:{ Exception -> 0x0112 }
        r5 = r5.getPingInterval();	 Catch:{ Exception -> 0x0112 }
        r5 = r9.resolveReconnectInterval(r5);	 Catch:{ Exception -> 0x0112 }
        r9.reconnectInterval = r5;	 Catch:{ Exception -> 0x0112 }
        r9.setRetryConnectAlarmManager(r6);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00e8;	 Catch:{ Exception -> 0x0112 }
    L_0x0088:
        r5 = "NPushNetworkController.handleReceivedPackets(): RES_SRC_KEY_EXCHANGE";	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.d(r5);	 Catch:{ Exception -> 0x0112 }
        r9.setRetryConnectAlarmManager(r6);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00e8;	 Catch:{ Exception -> 0x0112 }
    L_0x0091:
        r6 = "NPushNetworkController.handleReceivedPackets(): RES_SRC_CLIENT_HEALTHCHECK";	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.d(r6);	 Catch:{ Exception -> 0x0112 }
        r9.cancelRetryConnectAlarmManager();	 Catch:{ Exception -> 0x0112 }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0112 }
        r7 = 19;	 Catch:{ Exception -> 0x0112 }
        if (r6 < r7) goto L_0x00e8;	 Catch:{ Exception -> 0x0112 }
    L_0x009f:
        r6 = r9.pingInterval;	 Catch:{ Exception -> 0x0112 }
        r9.setRegisterAlarmManager(r5, r6);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00e8;	 Catch:{ Exception -> 0x0112 }
    L_0x00a5:
        r5 = "NNINetworkController.handleReceivedPackets(): response to server(RES_SRC_LOOKUP)";	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.d(r5);	 Catch:{ Exception -> 0x0112 }
        r5 = jp.naver.android.npush.network.NPushConnectedData.getInstance();	 Catch:{ Exception -> 0x0112 }
        r5 = r5.parseLookupInfo(r2);	 Catch:{ Exception -> 0x0112 }
        if (r5 == 0) goto L_0x00bd;	 Catch:{ Exception -> 0x0112 }
    L_0x00b4:
        r0 = r9.recvJsonPackets;	 Catch:{ Exception -> 0x0112 }
        r0.remove(r1);	 Catch:{ Exception -> 0x0112 }
        r9.disconnectLookupServer();	 Catch:{ Exception -> 0x0112 }
        return;	 Catch:{ Exception -> 0x0112 }
    L_0x00bd:
        r5 = jp.naver.android.npush.network.NPushVirtualConnectionLayer.getInstance();	 Catch:{ Exception -> 0x0112 }
        r5.resetLookupRetryCount();	 Catch:{ Exception -> 0x0112 }
        r9.cancelRegisterAlarmManager(r0);	 Catch:{ Exception -> 0x0112 }
        r9.setRetryConnectAlarmManager(r6);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00e8;	 Catch:{ Exception -> 0x0112 }
    L_0x00cb:
        r7 = "NPushNetworkController.handleReceivedPackets(): ";	 Catch:{ Exception -> 0x0112 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x0112 }
        r7 = "response to server(%x)";	 Catch:{ Exception -> 0x0112 }
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0112 }
        r8 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x0112 }
        r5[r0] = r8;	 Catch:{ Exception -> 0x0112 }
        r5 = java.lang.String.format(r7, r5);	 Catch:{ Exception -> 0x0112 }
        r6.append(r5);	 Catch:{ Exception -> 0x0112 }
        r5 = r6.toString();	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.d(r5);	 Catch:{ Exception -> 0x0112 }
    L_0x00e8:
        r5 = r9.routingHandlers;	 Catch:{ Exception -> 0x0112 }
        r5 = r5.values();	 Catch:{ Exception -> 0x0112 }
        r5 = r5.iterator();	 Catch:{ Exception -> 0x0112 }
    L_0x00f2:
        r6 = r5.hasNext();	 Catch:{ Exception -> 0x0112 }
        if (r6 == 0) goto L_0x0104;	 Catch:{ Exception -> 0x0112 }
    L_0x00f8:
        r6 = r5.next();	 Catch:{ Exception -> 0x0112 }
        r6 = (jp.naver.android.npush.network.NPushNetworkController.Routing) r6;	 Catch:{ Exception -> 0x0112 }
        if (r6 == 0) goto L_0x00f2;	 Catch:{ Exception -> 0x0112 }
    L_0x0100:
        r6.onRoutePacket(r3, r4, r2);	 Catch:{ Exception -> 0x0112 }
        goto L_0x00f2;	 Catch:{ Exception -> 0x0112 }
    L_0x0104:
        r1 = r1 + 1;	 Catch:{ Exception -> 0x0112 }
        goto L_0x000b;	 Catch:{ Exception -> 0x0112 }
    L_0x0108:
        r0 = move-exception;	 Catch:{ Exception -> 0x0112 }
        jp.naver.android.npush.common.Logger.e(r0);	 Catch:{ Exception -> 0x0112 }
        r0 = r9.recvJsonPackets;	 Catch:{ Exception -> 0x0112 }
        r0.remove(r1);	 Catch:{ Exception -> 0x0112 }
        return;
    L_0x0112:
        r0 = r9.recvJsonPackets;
        r0.clear();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.android.npush.network.NPushNetworkController.handleReceivedPackets():void");
    }

    private void networkStatusChanged(int i) {
        Logger.d("networkStatusChanged = ".concat(String.valueOf(i)));
        NPushVirtualConnectionLayer.getInstance().process(i);
        clearControllerDatas();
    }

    private void clearControllerDatas() {
        this.recvJsonPackets.clear();
        this.sendJsonPackets.clear();
    }

    public void connectNPushServer(JSONObject jSONObject) {
        this.requestConnectJson = jSONObject;
        this.networkExecuteHandler.post(this.ConnectNPushServerHandler);
    }

    public int requestApi(JSONObject jSONObject) {
        int currentNetworkStatus = getCurrentNetworkStatus();
        if (currentNetworkStatus == 2 || currentNetworkStatus == 7 || currentNetworkStatus == 9) {
            this.sendJsonPackets.add(jSONObject);
            this.networkExecuteHandler.post(this.requestApiCallHandler);
        } else {
            Logger.d(String.format("NPushNetworkController.requestApi currentNetworkStatus %d", new Object[]{Integer.valueOf(currentNetworkStatus)}));
        }
        return currentNetworkStatus;
    }

    public void disconnectNPushServer() {
        this.networkExecuteHandler.post(this.disconnectNPushServerTask);
    }

    public void resetConnectNPushServer() {
        this.networkExecuteHandler.post(this.resetConnectNPushServerTask);
    }

    public void onLookupRetryConnectOver() {
        restartService();
    }

    public void disconnectLookupServer() {
        this.networkExecuteHandler.post(this.disconnectLookupServerTask);
    }

    public void resetConnectLookupServer() {
        this.networkExecuteHandler.post(this.resetConnectLookupServerTask);
    }

    public void connectLookupServer(JSONObject jSONObject) {
        this.requestConnectLookupJson = jSONObject;
        this.networkExecuteHandler.post(this.connectLookupServerTask);
    }

    public int getCurrentNetworkInfo() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mServiceContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() : -1;
    }

    public int getCurrentNetworkStatus() {
        int currentNetworkStatus = NPushSocketChannel.getInstance().getCurrentNetworkStatus();
        StringBuilder stringBuilder = new StringBuilder("NPushNetworkController.getCurrentNetworkStatus() Status: ");
        stringBuilder.append(currentNetworkStatus);
        stringBuilder.append("(See NPushNetworkConfig)");
        Logger.d(stringBuilder.toString());
        return currentNetworkStatus;
    }

    public int getRandomAccessTimeInSec() {
        return this.reconnRandom.nextInt(NPushConnectedData.getInstance().getMaxStandbyConnTime()) + 1;
    }

    private void subscribeAll() {
        Logger.d("NPushNetworkController.subscribeAll()");
        try {
            NPushRequestApi.subscribeAllService();
        } catch (Throwable e) {
            Logger.e(e);
        }
    }

    private void unsubscribeAll() {
        Logger.d("NPushNetworkController.unsubscribeAll()");
        try {
            NPushRequestApi.unsubscribeAllService();
        } catch (Throwable e) {
            Logger.e(e);
        }
    }

    private void registerConnectivityReceiver() {
        if (this.connectivityReceiver != null) {
            this.mServiceContext.unregisterReceiver(this.connectivityReceiver);
            this.connectivityReceiver = null;
        }
        this.connectivityReceiver = new NetworkConnectivityReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.mServiceContext.registerReceiver(this.connectivityReceiver, intentFilter);
    }

    private void unregisterConnectivityReceiver() {
        if (this.connectivityReceiver != null) {
            this.mServiceContext.unregisterReceiver(this.connectivityReceiver);
            this.connectivityReceiver = null;
        }
    }

    public void setRetryConnectAlarmManager() {
        setRegisterAlarmManager(2, (long) (sRandom.nextInt(EXCEPTION_RETRY_CONNECT_INTERVAL) + 15000));
    }

    public void setRetryConnectAlarmManager(int i) {
        setRegisterAlarmManager(2, (long) i);
    }

    public void cancelRetryConnectAlarmManager() {
        cancelRegisterAlarmManager(2);
    }

    private void setRegisterAlarmManager(int i, long j) {
        cancelRegisterAlarmManager(i);
        AlarmManager alarmManager = (AlarmManager) this.mServiceContext.getSystemService("alarm");
        Intent intent = new Intent(NPushIntent.REQUEST_SUBSCRIBE_INTENT);
        intent.putExtra(NPushIntent.EXTRA_APPLICATION_PENDING_INTENT, PendingIntent.getBroadcast(this.mServiceContext, 1, new Intent(), 0));
        intent.putExtra(NPushIntent.EXTRA_APPLICATION_SERVICE_ID, NPushIntent.Self);
        intent.putExtra(NPushIntent.EXTRA_APPLICATION_CATEGORY_ID, 0);
        intent.putExtra(NPushIntent.EXTRA_APPLICATION_KEEPALIVE, false);
        intent.putExtra(NPushIntent.EXTRA_APPLICATION_REQUESTCODE, i);
        String servicePackageName = NPushConstant.getServicePackageName();
        if (!servicePackageName.isEmpty()) {
            intent.setPackage(servicePackageName);
        }
        PendingIntent service = PendingIntent.getService(this.mServiceContext, i, intent, C.SAMPLE_FLAG_DECODE_ONLY);
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, System.currentTimeMillis() + j, service);
        } else {
            alarmManager.setRepeating(0, System.currentTimeMillis() + j, j, service);
        }
        StringBuilder stringBuilder = new StringBuilder("NPushNetworkController.setRegisterAlarmManager() : ");
        stringBuilder.append(i);
        stringBuilder.append(" / interval : ");
        stringBuilder.append(j);
        Logger.d(stringBuilder.toString());
    }

    private void cancelRegisterAlarmManager(int i) {
        AlarmManager alarmManager = (AlarmManager) this.mServiceContext.getSystemService("alarm");
        Intent intent = new Intent(NPushIntent.REQUEST_SUBSCRIBE_INTENT);
        String servicePackageName = NPushConstant.getServicePackageName();
        if (!servicePackageName.isEmpty()) {
            intent.setPackage(servicePackageName);
        }
        alarmManager.cancel(PendingIntent.getService(this.mServiceContext, i, intent, C.SAMPLE_FLAG_DECODE_ONLY));
        Logger.d("NPushNetworkController.cancelRegisterAlarmManager : ".concat(String.valueOf(i)));
    }
}
