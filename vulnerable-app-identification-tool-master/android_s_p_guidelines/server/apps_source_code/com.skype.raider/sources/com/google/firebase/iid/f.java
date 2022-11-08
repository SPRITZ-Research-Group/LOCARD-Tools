package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import com.google.android.gms.common.util.m;
import com.google.firebase.a;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public final class f {
    private final Context a;
    @GuardedBy("this")
    private String b;
    @GuardedBy("this")
    private String c;
    @GuardedBy("this")
    private int d;
    @GuardedBy("this")
    private int e = 0;

    public f(Context context) {
        this.a = context;
    }

    private final PackageInfo a(String str) {
        try {
            return this.a.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to find package ").append(valueOf);
            return null;
        }
    }

    public static String a(a aVar) {
        String b = aVar.b().b();
        if (b != null) {
            return b;
        }
        b = aVar.b().a();
        if (!b.startsWith("1:")) {
            return b;
        }
        String[] split = b.split(":");
        if (split.length < 2) {
            return null;
        }
        b = split[1];
        return b.isEmpty() ? null : b;
    }

    public static String a(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private final synchronized void e() {
        PackageInfo a = a(this.a.getPackageName());
        if (a != null) {
            this.b = Integer.toString(a.versionCode);
            this.c = a.versionName;
        }
    }

    public final synchronized int a() {
        int i = 0;
        synchronized (this) {
            if (this.e != 0) {
                i = this.e;
            } else {
                PackageManager packageManager = this.a.getPackageManager();
                if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") != -1) {
                    Intent intent;
                    List queryIntentServices;
                    if (!m.d()) {
                        intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                        intent.setPackage("com.google.android.gms");
                        queryIntentServices = packageManager.queryIntentServices(intent, 0);
                        if (queryIntentServices != null && queryIntentServices.size() > 0) {
                            this.e = 1;
                            i = this.e;
                        }
                    }
                    intent = new Intent("com.google.iid.TOKEN_REQUEST");
                    intent.setPackage("com.google.android.gms");
                    queryIntentServices = packageManager.queryBroadcastReceivers(intent, 0);
                    if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                        if (m.d()) {
                            this.e = 2;
                        } else {
                            this.e = 1;
                        }
                        i = this.e;
                    } else {
                        this.e = 2;
                        i = this.e;
                    }
                }
            }
        }
        return i;
    }

    public final synchronized String b() {
        if (this.b == null) {
            e();
        }
        return this.b;
    }

    public final synchronized String c() {
        if (this.c == null) {
            e();
        }
        return this.c;
    }

    public final synchronized int d() {
        if (this.d == 0) {
            PackageInfo a = a("com.google.android.gms");
            if (a != null) {
                this.d = a.versionCode;
            }
        }
        return this.d;
    }
}
