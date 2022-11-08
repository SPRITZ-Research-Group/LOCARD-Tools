package com.microsoft.tokenshare;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.microsoft.tokenshare.AccountInfo.AccountType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TokenSharingService extends Service {
    private final com.microsoft.tokenshare.g.a a = new h();
    private a b;

    static class a {
        private final Map<String, Integer> a = new HashMap();
        private final Context b;

        public a(Context context) {
            this.b = context;
        }

        protected final int a(int callingUserId) {
            String[] packagesNames = this.b.getPackageManager().getPackagesForUid(callingUserId);
            if (packagesNames == null || packagesNames.length <= 0) {
                String.format(Locale.ROOT, "There is no packages for uid: %s", new Object[]{Integer.valueOf(callingUserId)});
                h.a("TokenSharingService");
                return 1;
            }
            int minVersion = a(packagesNames[0]);
            if (packagesNames.length <= 1) {
                return minVersion;
            }
            for (String packageName : packagesNames) {
                int version = a(packageName);
                if (minVersion > version) {
                    minVersion = version;
                }
            }
            return minVersion;
        }

        private synchronized int a(@NonNull String packageName) {
            Integer manifestVariable;
            manifestVariable = (Integer) this.a.get(packageName);
            if (manifestVariable == null) {
                Integer defaultValue = Integer.valueOf(1);
                Integer latestVersionNoManifestData = Integer.valueOf(2);
                try {
                    ApplicationInfo applicationInfo = this.b.getPackageManager().getApplicationInfo(packageName, 128);
                    manifestVariable = Integer.valueOf(applicationInfo.metaData != null ? applicationInfo.metaData.getInt("token_share_parcelable_version") : 0);
                    if (manifestVariable.intValue() == 0) {
                        String buildVersion = applicationInfo.metaData != null ? applicationInfo.metaData.getString("token_share_build_version") : null;
                        if (TextUtils.isEmpty(buildVersion) || buildVersion.startsWith("1.1")) {
                            manifestVariable = defaultValue;
                        } else {
                            manifestVariable = latestVersionNoManifestData;
                        }
                    }
                } catch (NameNotFoundException e) {
                    h.c("TokenSharingService");
                    manifestVariable = defaultValue;
                    this.a.put(packageName, manifestVariable);
                    return manifestVariable.intValue();
                } catch (NotFoundException e2) {
                    h.c("TokenSharingService");
                    manifestVariable = defaultValue;
                    this.a.put(packageName, manifestVariable);
                    return manifestVariable.intValue();
                }
                this.a.put(packageName, manifestVariable);
            }
            return manifestVariable.intValue();
        }
    }

    private interface b {
        void a(@NonNull List<AccountInfo> list);
    }

    private static class c {
        static /* synthetic */ b a(a x0) {
            switch (x0.a(Binder.getCallingUid())) {
                case 1:
                    return new d();
                case 2:
                    return new e();
                default:
                    return new f();
            }
        }
    }

    private static class f implements b {
        private f() {
        }

        /* synthetic */ f(byte b) {
            this();
        }

        public void a(@NonNull List<AccountInfo> list) {
        }
    }

    private static class e extends f {
        private e() {
            super();
        }

        /* synthetic */ e(byte b) {
            this();
        }

        public void a(@NonNull List<AccountInfo> accountInfoList) {
            super.a(accountInfoList);
            List<AccountInfo> accountsToRemove = new ArrayList();
            for (AccountInfo accountInfo : accountInfoList) {
                if (accountInfo.getAccountType() == AccountType.OTHER) {
                    accountsToRemove.add(accountInfo);
                }
            }
            accountInfoList.removeAll(accountsToRemove);
        }
    }

    private static class d extends e {
        private d() {
            super();
        }

        /* synthetic */ d(byte b) {
            this();
        }

        public final void a(@NonNull List<AccountInfo> accountInfoList) {
            super.a(accountInfoList);
            for (AccountInfo parcelableVersion : accountInfoList) {
                parcelableVersion.setParcelableVersion(null);
            }
        }
    }

    private class g {
        final /* synthetic */ TokenSharingService a;
        private final Timer b = new Timer();
        private final String c;

        g(TokenSharingService tokenSharingService, String errorMsg) {
            this.a = tokenSharingService;
            this.c = errorMsg;
        }

        public final void a() {
            this.b.schedule(new TimerTask(this) {
                final /* synthetic */ g a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    h.c("TokenSharingService");
                }
            }, 5000);
        }

        public final void b() {
            this.b.cancel();
        }
    }

    private class h extends com.microsoft.tokenshare.g.a {
        final /* synthetic */ TokenSharingService a;

        private h(TokenSharingService tokenSharingService) {
            this.a = tokenSharingService;
        }

        /* synthetic */ h(TokenSharingService x0, byte b) {
            this(x0);
        }

        public final List<AccountInfo> a() {
            List<AccountInfo> accountInfoList = new ArrayList();
            try {
                return c();
            } catch (RuntimeException e) {
                new Thread(new Runnable(e) {
                    public final void run() {
                        throw x0;
                    }
                }).start();
                return accountInfoList;
            }
        }

        public final RefreshToken a(AccountInfo accountInfo) {
            RefreshToken refreshToken = null;
            try {
                return b(accountInfo);
            } catch (RuntimeException e) {
                new Thread(/* anonymous class already generated */).start();
                return refreshToken;
            }
        }

        public final String b() {
            try {
                return this.a.a() ? e.a(this.a) : null;
            } catch (RuntimeException e) {
                new Thread(/* anonymous class already generated */).start();
                return null;
            }
        }

        private List<AccountInfo> c() {
            g provider = c.a.c();
            List<AccountInfo> accountInfoList = new ArrayList();
            if (provider != null && this.a.a()) {
                g scheduleTimer = new g(this.a, "Timed out waiting for accounts to be fetched from remote");
                scheduleTimer.a();
                try {
                    accountInfoList = provider.a();
                } catch (RemoteException e) {
                    h.a("TokenSharingService", "Can't fetch accounts from remote", e);
                } finally {
                    scheduleTimer.b();
                }
            }
            if (!accountInfoList.isEmpty()) {
                c.a(this.a.b()).a(accountInfoList);
            }
            return accountInfoList;
        }

        private RefreshToken b(AccountInfo accountInfo) {
            g provider = c.a.c();
            RefreshToken refreshToken = null;
            if (provider != null && this.a.a()) {
                g scheduleTimer = new g(this.a, "Timed out waiting for refresh token to be fetched from remote");
                scheduleTimer.a();
                try {
                    refreshToken = provider.a(accountInfo);
                } catch (RemoteException e) {
                    h.a("TokenSharingService", "Can't fetch token from remote", e);
                } finally {
                    scheduleTimer.b();
                }
            }
            return refreshToken;
        }
    }

    public void onCreate() {
        super.onCreate();
        this.b = new a(this);
    }

    public IBinder onBind(Intent intent) {
        return this.a;
    }

    protected final boolean a() {
        String[] packagesNames = getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesNames == null || packagesNames.length <= 0) {
            String.format(Locale.ROOT, "There is no packages for uid: %s", new Object[]{Integer.valueOf(callingUserId)});
            h.a("TokenSharingService");
            return false;
        }
        String packageName;
        if (packagesNames.length > 1) {
            StringBuilder builder = new StringBuilder(String.format(Locale.ROOT, "There is more than 1 package associated with caller uid: %s ", new Object[]{Integer.valueOf(callingUserId)}));
            for (String packageName2 : packagesNames) {
                builder.append(10);
                builder.append(packageName2);
            }
            h.a("TokenSharingService");
        }
        boolean isMicrosoftApplication = false;
        String verifiedPackageName = packagesNames[0];
        int length = packagesNames.length;
        int i = 0;
        while (i < length) {
            packageName2 = packagesNames[i];
            if (getPackageName().equalsIgnoreCase(packageName2)) {
                i++;
            } else {
                try {
                    isMicrosoftApplication = i.c(this, packageName2);
                    verifiedPackageName = packageName2;
                    break;
                } catch (NameNotFoundException e) {
                    h.a("TokenSharingService", "getPackageSignature failed for " + packageName2, e);
                }
            }
        }
        boolean isDebugMode = c.a.e();
        Locale locale = Locale.ROOT;
        String str = "Binding request %s from %s, MS app = %s, debug mode = %s";
        Object[] objArr = new Object[4];
        String str2 = (isMicrosoftApplication || isDebugMode) ? "is approved" : "is denied";
        objArr[0] = str2;
        objArr[1] = verifiedPackageName;
        objArr[2] = String.valueOf(isMicrosoftApplication);
        objArr[3] = String.valueOf(isDebugMode);
        String.format(locale, str, objArr);
        h.a("TokenSharingService");
        if (isMicrosoftApplication || isDebugMode) {
            return true;
        }
        return false;
    }

    protected final a b() {
        return this.b;
    }
}
