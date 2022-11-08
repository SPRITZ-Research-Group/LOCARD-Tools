package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.internal.measurement.bx;

@Keep
public final class FirebaseAnalytics {
    private final bx a;

    public static class a {
    }

    public static class b {
    }

    public static class c {
    }

    public FirebaseAnalytics(bx bxVar) {
        ab.a((Object) bxVar);
        this.a = bxVar;
    }

    @Keep
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @NonNull
    public static FirebaseAnalytics getInstance(@NonNull Context context) {
        return bx.a(context).m();
    }

    @Keep
    @MainThread
    public final void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        this.a.s().a(activity, str, str2);
    }
}
