package com.microsoft.tokenshare;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class o {
    private final n a;
    private final AtomicReference<List<ResolveInfo>> b;
    private final AtomicReference<g> c;
    private final AtomicBoolean d;
    private final ConcurrentHashMap<d, c<d>> e;
    private final AtomicReference<f> f;
    private final ExecutorService g;
    private final AtomicReference<String> h;

    private interface a {
        void a(d dVar);

        void a(Throwable th);
    }

    private static class b extends a {
        b(List<ResolveInfo> fullServiceList, List<ResolveInfo> serviceList, int numSuccessConnections) {
            super("GetAccountsEvent");
            a("ProvidersEnabledCount", Integer.valueOf(serviceList.size()));
            a("ProvidersTotalCount", Integer.valueOf(fullServiceList.size() - 1));
            a("ProvidersSuccessCount", Integer.valueOf(numSuccessConnections));
        }
    }

    private static class c {
        static final o a = new o();
    }

    private class d implements ServiceConnection {
        final /* synthetic */ o a;
        private Context b;
        private g c;
        private String d;
        private boolean e;

        public d(o oVar, Context context) {
            this.a = oVar;
            this.b = context.getApplicationContext();
        }

        final g a() {
            return this.c;
        }

        final String b() {
            return this.d;
        }

        public final void onServiceConnected(ComponentName className, IBinder service) {
            this.c = com.microsoft.tokenshare.g.a.a(service);
            this.d = className.getPackageName();
            this.e = true;
            new StringBuilder("Connected to ").append(this.d);
            h.a("TokenSharingManager");
            c<d> callbackExecutor = (c) this.a.e.remove(this);
            if (callbackExecutor != null) {
                callbackExecutor.a((Object) this);
                return;
            }
            new StringBuilder().append(this.d).append(" doesn't have any callback to invoke");
            h.c("TokenSharingManager");
            this.b.unbindService(this);
        }

        public final void onServiceDisconnected(ComponentName className) {
            this.e = false;
            new StringBuilder("Service ").append(className.getPackageName()).append(" was disconnected");
            h.a("TokenSharingManager");
        }

        final void a(String packageName, String className) {
            Intent intent = new Intent(TokenSharingService.class.getName());
            intent.setClassName(packageName, className);
            intent.putExtra(AccountInfo.VERSION_KEY, AccountInfo.SERIALIZABLE_VALUE_CODE_NAME);
            new StringBuilder("Connecting to ").append(packageName).append(" ver:").append(i.a(this.b, packageName));
            h.a("TokenSharingManager");
            if (!this.b.bindService(intent, this, 1)) {
                c<d> callbackExecutor = (c) this.a.e.remove(this);
                if (callbackExecutor != null) {
                    callbackExecutor.a(new IOException("Connection to " + packageName + " failed"));
                    return;
                }
                new StringBuilder("Connection to ").append(packageName).append(" failed, but callback was already invoked");
                h.c("TokenSharingManager");
            }
        }

        final void c() {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    if (this.a.e) {
                        new StringBuilder("Disconnecting from ").append(this.a.d);
                        h.a("TokenSharingManager");
                        this.a.b.unbindService(this.a);
                        this.a.e = false;
                    }
                }
            });
        }
    }

    /* synthetic */ o(byte b) {
        this();
    }

    public static o a() {
        return c.a;
    }

    private o() {
        this.a = new k();
        this.b = new AtomicReference(null);
        this.c = new AtomicReference(null);
        this.d = new AtomicBoolean(false);
        this.e = new ConcurrentHashMap();
        this.f = new AtomicReference(null);
        this.g = Executors.newCachedThreadPool();
        this.h = new AtomicReference(null);
    }

    public final void a(@NonNull final Context context, @NonNull b<String> callback) {
        Object sharedDeviceId = (String) this.h.get();
        if (sharedDeviceId == null) {
            sharedDeviceId = e.a(context);
            if (sharedDeviceId != null) {
                this.h.set(sharedDeviceId);
            }
        }
        if (sharedDeviceId != null) {
            callback.a(sharedDeviceId);
            return;
        }
        final c<String> callbackExecutor = new c<String>(this, callback) {
            final /* synthetic */ o a;

            protected final void a() {
                a(new TimeoutException("getSharedDeviceId time exceeded"));
            }
        };
        a(context, b(context, null), new a(this) {
            final /* synthetic */ o c;

            public final void a(d connection) {
                try {
                    String sharedDeviceId = connection.a().b();
                    if (sharedDeviceId != null) {
                        this.c.h.set(sharedDeviceId);
                        new StringBuilder("Fetched shared device id from ").append(connection.b());
                        h.a("TokenSharingManager");
                    }
                } catch (RemoteException ignore) {
                    h.a("TokenSharingManager", "Can't fetch shared device id from remote", ignore);
                } catch (RuntimeException e) {
                    h.a("TokenSharingManager", connection.b() + " provider throws RuntimeException", e);
                    throw e;
                }
            }

            public final void a(Throwable throwable) {
                Object sharedDeviceId;
                if (throwable != null) {
                    h.a("TokenSharingManager", "There were issues connecting to services ", throwable);
                    sharedDeviceId = (String) this.c.h.get();
                    if (sharedDeviceId != null) {
                        e.a(context, sharedDeviceId);
                        callbackExecutor.a(sharedDeviceId);
                        return;
                    }
                    callbackExecutor.a(throwable);
                    return;
                }
                this.c.h.compareAndSet(null, UUID.randomUUID().toString());
                sharedDeviceId = (String) this.c.h.get();
                e.a(context, sharedDeviceId);
                callbackExecutor.a(sharedDeviceId);
            }
        });
    }

    public final void b(@NonNull Context context, @NonNull b<List<AccountInfo>> callback) {
        final Queue<AccountInfo> accounts = new ConcurrentLinkedQueue();
        final List serviceList = b(context, null);
        final c<List<AccountInfo>> callbackExecutor = new c<List<AccountInfo>>(this, callback) {
            final /* synthetic */ o b;

            protected final void a() {
                h.b("TokenSharingManager");
                a((Object) new ArrayList(accounts));
            }
        };
        final AtomicInteger successfulConnections = new AtomicInteger();
        final Context context2 = context;
        a(context, serviceList, new a(this) {
            final /* synthetic */ o f;

            public final void a(d connection) {
                successfulConnections.incrementAndGet();
                try {
                    List<AccountInfo> accountInfoList = connection.a().a();
                    for (AccountInfo providerPackageId : accountInfoList) {
                        providerPackageId.setProviderPackageId(connection.b());
                    }
                    new StringBuilder("Fetched accounts from ").append(connection.b());
                    h.a("TokenSharingManager");
                    accounts.addAll(accountInfoList);
                } catch (RemoteException ignore) {
                    h.a("TokenSharingManager", "Can't fetch accounts from remote", ignore);
                } catch (RuntimeException e) {
                    h.a("TokenSharingManager", connection.b() + " provider throws RuntimeException", e);
                    throw e;
                }
            }

            public final void a(Throwable throwable) {
                List<ResolveInfo> fullServiceList = (List) this.f.b.get();
                if (fullServiceList == null) {
                    fullServiceList = context2.getPackageManager().queryIntentServices(new Intent(TokenSharingService.class.getName()), 512);
                }
                b.a;
                b bVar = new b(fullServiceList, serviceList, successfulConnections.get());
                b.a;
                if (throwable instanceof TimeoutException) {
                    h.a("TokenSharingManager", "bind() got TimeoutConnection", throwable);
                    throwable = null;
                }
                if (throwable != null) {
                    callbackExecutor.a(throwable);
                    return;
                }
                Object accountInfoList = new ArrayList(accounts);
                Collections.sort(accountInfoList, new Comparator<AccountInfo>(this) {
                    final /* synthetic */ AnonymousClass9 a;

                    {
                        this.a = this$1;
                    }

                    public final /* synthetic */ int compare(Object obj, Object obj2) {
                        AccountInfo accountInfo = (AccountInfo) obj;
                        AccountInfo accountInfo2 = (AccountInfo) obj2;
                        if (accountInfo.getRefreshTokenAcquireTime() == null && accountInfo2.getRefreshTokenAcquireTime() == null) {
                            return 0;
                        }
                        if (accountInfo.getRefreshTokenAcquireTime() == null) {
                            return 1;
                        }
                        if (accountInfo2.getRefreshTokenAcquireTime() == null) {
                            return -1;
                        }
                        return accountInfo2.getRefreshTokenAcquireTime().compareTo(accountInfo.getRefreshTokenAcquireTime());
                    }
                });
                callbackExecutor.a(accountInfoList);
            }
        });
    }

    public final void a(@NonNull Context context, @NonNull final AccountInfo accountInfo, @NonNull b<RefreshToken> callback) {
        List serviceList = b(context, accountInfo.getProviderPackageId());
        final c<RefreshToken> callbackExecutor = new c<RefreshToken>(this, callback) {
            final /* synthetic */ o b;

            protected final void a() {
                a(new TimeoutException("getRefreshToken time exceeded for " + accountInfo.getProviderPackageId()));
            }
        };
        a(context, serviceList, new a(this) {
            final /* synthetic */ o c;
            private RefreshToken d = null;

            public final void a(d connection) {
                try {
                    this.d = connection.a().a(accountInfo);
                    new StringBuilder("Fetched token from ").append(connection.b());
                    h.a("TokenSharingManager");
                } catch (RemoteException ignore) {
                    h.a("TokenSharingManager", "Can't fetch token from remote ", ignore);
                } catch (RuntimeException e) {
                    h.a("TokenSharingManager", connection.b() + " provider throws RuntimeException", e);
                    throw e;
                }
            }

            public final void a(Throwable throwable) {
                if (throwable != null) {
                    callbackExecutor.a(throwable);
                } else if (this.d == null) {
                    callbackExecutor.a(new a(accountInfo.getProviderPackageId()));
                } else {
                    callbackExecutor.a(this.d);
                }
            }
        });
    }

    public final void b() {
        h.a("TokenSharingManager");
        this.d.set(false);
    }

    final g c() {
        return (g) this.c.get();
    }

    final n d() {
        return this.a;
    }

    final void a(BroadcastReceiver receiver, Context context, String packageName) {
        if (c(context, packageName)) {
            context.getApplicationContext().unregisterReceiver(receiver);
            this.b.set(null);
        }
    }

    final void a(Context context, String packageName) {
        f accountChangeListener = (f) this.f.get();
        if (accountChangeListener != null && c(context, packageName)) {
            accountChangeListener.onAccountAdded(packageName);
        }
    }

    final boolean e() {
        return this.d.get();
    }

    private List<ResolveInfo> b(Context context, String filter) {
        String packageName;
        Intent intent = new Intent(TokenSharingService.class.getName());
        List<ResolveInfo> fullServiceList = (List) this.b.get();
        if (fullServiceList == null) {
            fullServiceList = context.getPackageManager().queryIntentServices(intent, 512);
            List<ResolveInfo> enabledServiceList = context.getPackageManager().queryIntentServices(intent, 128);
            for (ResolveInfo service : fullServiceList) {
                packageName = service.serviceInfo.packageName;
                service.serviceInfo.enabled = false;
                for (ResolveInfo resolveInfo : enabledServiceList) {
                    if (resolveInfo.serviceInfo.packageName.equalsIgnoreCase(packageName)) {
                        service.serviceInfo.enabled = true;
                        break;
                    }
                }
            }
            if (this.b.getAndSet(fullServiceList) == null) {
                boolean z;
                if (c() != null) {
                    z = true;
                } else {
                    z = false;
                }
                b(context, z);
                context.getApplicationContext().registerReceiver(new PackageChangeReceiver(), PackageChangeReceiver.a(context));
            }
        }
        List<ResolveInfo> filteredServiceList = new ArrayList();
        for (ResolveInfo info : fullServiceList) {
            packageName = info.serviceInfo.packageName;
            if (info.serviceInfo.enabled && !context.getPackageName().equalsIgnoreCase(packageName)) {
                if (TextUtils.isEmpty(filter) || packageName.equalsIgnoreCase(filter)) {
                    if (!i.b(context, packageName)) {
                        new StringBuilder("Skipping package ").append(info.serviceInfo.packageName).append(" because SDK version isn't compatible");
                        h.a("TokenSharingManager");
                    } else if (c(context, packageName)) {
                        filteredServiceList.add(info);
                    } else {
                        new StringBuilder("Skipping package ").append(info.serviceInfo.packageName).append(" because it's not MS application");
                        h.a("TokenSharingManager");
                    }
                }
            }
        }
        return filteredServiceList;
    }

    private static void b(@NonNull Context context, boolean isEnable) {
        ComponentName tokenShareService = new ComponentName(context, TokenSharingService.class);
        int oldState = context.getPackageManager().getComponentEnabledSetting(tokenShareService);
        int newState = isEnable ? 0 : 2;
        if (oldState != newState) {
            context.getPackageManager().setComponentEnabledSetting(tokenShareService, newState, 1);
            if (newState == 0) {
                context.getApplicationContext().sendBroadcast(new Intent("com.microsoft.tokenshare.SERVICE_ENABLED", Uri.parse("package:" + context.getPackageName())));
            }
        }
    }

    private boolean c(Context context, String packageName) {
        try {
            return i.c(context, packageName) || this.d.get();
        } catch (NameNotFoundException e) {
            h.a("TokenSharingManager", "getPackageSignature failed for " + packageName, e);
            return false;
        }
    }

    private void a(Context context, List<ResolveInfo> providers, final a callback) {
        final AtomicInteger bindConnectionCounter = new AtomicInteger(providers.size());
        if (providers.isEmpty()) {
            callback.a(null);
            return;
        }
        for (ResolveInfo provider : providers) {
            final b<d> bindingCallback = new b<d>(this) {
                final /* synthetic */ o c;
                private final AtomicReference<Throwable> d = new AtomicReference(null);

                public final void a(Throwable ex) {
                    this.d.set(ex);
                    a();
                }

                private void a() {
                    if (bindConnectionCounter.decrementAndGet() == 0) {
                        callback.a((Throwable) this.d.get());
                    }
                }
            };
            final String str = provider.serviceInfo.packageName;
            String str2 = provider.serviceInfo.name;
            b anonymousClass4 = new b<d>(this) {
                final /* synthetic */ o b;

                public final /* synthetic */ void a(Object obj) {
                    obj = (d) obj;
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        bindingCallback.a(obj);
                        obj.c();
                        return;
                    }
                    this.b.g.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public final void run() {
                            bindingCallback.a(obj);
                            obj.c();
                        }
                    });
                }

                public final void a(Throwable ex) {
                    bindingCallback.a(ex);
                }
            };
            final d dVar = new d(this, context);
            this.e.put(dVar, new c<d>(this, anonymousClass4) {
                final /* synthetic */ o c;

                protected final void a() {
                    if (this.c.e.remove(dVar) != null) {
                        a(new TimeoutException("Binding time exceeded for " + str));
                    }
                }
            });
            dVar.a(str, str2);
        }
    }

    public final void a(@NonNull final Context context, @Nullable final g provider, @Nullable f accountChangeListener) {
        this.c.set(provider);
        if (accountChangeListener != null) {
            this.f.set(accountChangeListener);
            context.getApplicationContext().registerReceiver(new AccountChangeReceiver(), AccountChangeReceiver.a());
        }
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable(this) {
            final /* synthetic */ o c;

            public final void run() {
                this.c.d().a(context);
                try {
                    Context context = context;
                    boolean z = (provider == null || provider.a().isEmpty()) ? false : true;
                    o.b(context, z);
                } catch (RemoteException e) {
                }
            }
        });
    }
}
