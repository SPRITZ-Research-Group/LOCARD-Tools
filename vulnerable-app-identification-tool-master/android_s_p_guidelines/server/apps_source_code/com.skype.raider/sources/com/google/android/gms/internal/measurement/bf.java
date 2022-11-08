package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.util.Locale;

final class bf extends ct {
    @VisibleForTesting
    static final Pair<String, Long> a = new Pair("", Long.valueOf(0));
    public bi b;
    public final bh c = new bh(this, "last_upload", 0);
    public final bh d = new bh(this, "last_upload_attempt", 0);
    public final bh e = new bh(this, "backoff", 0);
    public final bh f = new bh(this, "last_delete_stale", 0);
    public final bh g = new bh(this, "midnight_offset", 0);
    public final bh h = new bh(this, "first_open_time", 0);
    public final bh i = new bh(this, "app_install_time", 0);
    public final bj j = new bj(this, "app_instance_id");
    public final bh k = new bh(this, "time_before_start", 10000);
    public final bh l = new bh(this, "session_timeout", 1800000);
    public final bg m = new bg(this, "start_new_session");
    public final bh n = new bh(this, "last_pause_time", 0);
    public final bh o = new bh(this, "time_active", 0);
    public boolean p;
    private SharedPreferences r;
    private String s;
    private boolean t;
    private long u;
    private final Object v = new Object();

    bf(bx bxVar) {
        super(bxVar);
    }

    @WorkerThread
    private final SharedPreferences B() {
        c();
        G();
        return this.r;
    }

    @WorkerThread
    final boolean A() {
        return this.r.contains("deferred_analytics_collection");
    }

    @WorkerThread
    @NonNull
    final Pair<String, Boolean> a(String str) {
        c();
        long b = j().b();
        if (this.s != null && b < this.u) {
            return new Pair(this.s, Boolean.valueOf(this.t));
        }
        this.u = b + s().a(str, al.c);
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(k());
            if (advertisingIdInfo != null) {
                this.s = advertisingIdInfo.getId();
                this.t = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.s == null) {
                this.s = "";
            }
        } catch (Exception e) {
            q().B().a("Unable to get advertising id", e);
            this.s = "";
        }
        return new Pair(this.s, Boolean.valueOf(this.t));
    }

    @WorkerThread
    final void a(boolean z) {
        c();
        q().C().a("Setting useService", Boolean.valueOf(z));
        Editor edit = B().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    @WorkerThread
    final String b(String str) {
        c();
        String str2 = (String) a(str).first;
        if (ew.e(Constants.MD5) == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, ew.e(Constants.MD5).digest(str2.getBytes()))});
    }

    @WorkerThread
    final boolean b(boolean z) {
        c();
        return B().getBoolean("measurement_enabled", z);
    }

    @WorkerThread
    final void c(String str) {
        c();
        Editor edit = B().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    @WorkerThread
    final void c(boolean z) {
        c();
        q().C().a("Updating deferred analytics collection", Boolean.valueOf(z));
        Editor edit = B().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    protected final boolean t() {
        return true;
    }

    @WorkerThread
    protected final void t_() {
        this.r = k().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.p = this.r.getBoolean("has_been_opened", false);
        if (!this.p) {
            Editor edit = this.r.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.b = new bi(this, "health_monitor", Math.max(0, ((Long) al.d.b()).longValue()), (byte) 0);
    }

    @WorkerThread
    final String v() {
        c();
        return B().getString("gmp_app_id", null);
    }

    @WorkerThread
    final Boolean w() {
        c();
        return !B().contains("use_service") ? null : Boolean.valueOf(B().getBoolean("use_service", false));
    }

    @WorkerThread
    final void x() {
        boolean z = true;
        c();
        q().C().a("Clearing collection preferences.");
        boolean contains = B().contains("measurement_enabled");
        if (contains) {
            z = b(true);
        }
        Editor edit = B().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            c();
            q().C().a("Setting measurementEnabled", Boolean.valueOf(z));
            Editor edit2 = B().edit();
            edit2.putBoolean("measurement_enabled", z);
            edit2.apply();
        }
    }

    @WorkerThread
    protected final String y() {
        c();
        String string = B().getString("previous_os_version", null);
        g().G();
        String str = VERSION.RELEASE;
        if (!(TextUtils.isEmpty(str) || str.equals(string))) {
            Editor edit = B().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    @WorkerThread
    final boolean z() {
        c();
        return B().getBoolean("deferred_analytics_collection", false);
    }
}
