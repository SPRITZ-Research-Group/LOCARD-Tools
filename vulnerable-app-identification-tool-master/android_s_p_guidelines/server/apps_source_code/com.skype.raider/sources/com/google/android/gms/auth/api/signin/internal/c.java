package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

public final class c {
    private static final Lock a = new ReentrantLock();
    @GuardedBy("sLk")
    private static c b;
    private final Lock c = new ReentrantLock();
    @GuardedBy("mLk")
    private final SharedPreferences d;

    @VisibleForTesting
    private c(Context context) {
        this.d = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    @Nullable
    @VisibleForTesting
    private final GoogleSignInAccount a(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (TextUtils.isEmpty(str)) {
            return googleSignInAccount;
        }
        String str2 = "googleSignInAccount";
        str2 = b(new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(str).length()).append(str2).append(":").append(str).toString());
        if (str2 == null) {
            return googleSignInAccount;
        }
        try {
            return GoogleSignInAccount.a(str2);
        } catch (JSONException e) {
            return googleSignInAccount;
        }
    }

    public static c a(Context context) {
        ab.a((Object) context);
        a.lock();
        try {
            if (b == null) {
                b = new c(context.getApplicationContext());
            }
            c cVar = b;
            return cVar;
        } finally {
            a.unlock();
        }
    }

    @Nullable
    private String b(String str) {
        this.c.lock();
        try {
            String string = this.d.getString(str, null);
            return string;
        } finally {
            this.c.unlock();
        }
    }

    @Nullable
    public final GoogleSignInAccount a() {
        return a(b("defaultGoogleSignInAccount"));
    }
}
