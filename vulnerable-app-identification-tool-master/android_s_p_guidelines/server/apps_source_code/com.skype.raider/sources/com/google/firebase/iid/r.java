package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

final class r implements Runnable {
    private final long a;
    private final WakeLock b = ((PowerManager) a().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId c;
    private final f d;

    @VisibleForTesting
    r(FirebaseInstanceId firebaseInstanceId, f fVar, long j) {
        this.c = firebaseInstanceId;
        this.d = fVar;
        this.a = j;
        this.b.setReferenceCounted(false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean a(String str) {
        String[] split = str.split("!");
        if (split.length == 2) {
            String str2 = split[0];
            String str3 = split[1];
            boolean z = true;
            try {
                switch (str2.hashCode()) {
                    case 83:
                        if (str2.equals("S")) {
                            z = false;
                            break;
                        }
                        break;
                    case 85:
                        if (str2.equals("U")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        this.c.zzb(str3);
                        break;
                    case true:
                        this.c.zzc(str3);
                        FirebaseInstanceId.zzj();
                        break;
                }
            } catch (IOException e) {
                String str4 = "Topic sync failed: ";
                String valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() != 0) {
                    str4.concat(valueOf);
                    return false;
                }
                valueOf = new String(str4);
                return false;
            }
        }
        return true;
    }

    @VisibleForTesting
    private final boolean c() {
        Exception e;
        String str;
        String valueOf;
        q zzg = this.c.zzg();
        if (zzg != null && !zzg.b(this.d.b())) {
            return true;
        }
        try {
            String zzh = this.c.zzh();
            if (zzh == null) {
                return false;
            }
            if (zzg != null && (zzg == null || zzh.equals(zzg.a))) {
                return true;
            }
            Context a = a();
            Parcelable intent = new Intent("com.google.firebase.iid.TOKEN_REFRESH");
            Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT");
            intent2.setClass(a, FirebaseInstanceIdReceiver.class);
            intent2.putExtra("wrapped_intent", intent);
            a.sendBroadcast(intent2);
            return true;
        } catch (IOException e2) {
            e = e2;
            str = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
                str.concat(valueOf);
            } else {
                valueOf = new String(str);
            }
            return false;
        } catch (SecurityException e3) {
            e = e3;
            str = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
                valueOf = new String(str);
            } else {
                str.concat(valueOf);
            }
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @VisibleForTesting
    private final boolean d() {
        while (true) {
            String a;
            synchronized (this.c) {
                a = FirebaseInstanceId.zzi().a();
                if (a == null) {
                    return true;
                }
            }
            FirebaseInstanceId.zzi().b(a);
        }
    }

    final Context a() {
        return this.c.zze().a();
    }

    final boolean b() {
        ConnectivityManager connectivityManager = (ConnectivityManager) a().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final void run() {
        Object obj = 1;
        this.b.acquire();
        try {
            this.c.zza(true);
            if (this.d.a() == 0) {
                obj = null;
            }
            if (obj == null) {
                this.c.zza(false);
            } else if (b()) {
                if (c() && d()) {
                    this.c.zza(false);
                } else {
                    this.c.zza(this.a);
                }
                this.b.release();
            } else {
                new s(this).a();
                this.b.release();
            }
        } finally {
            this.b.release();
        }
    }
}
