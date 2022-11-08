package com.facebook.ads.internal.q.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;

public final class h {
    public static boolean a(Context context) {
        try {
            boolean z = ((RunningTaskInfo) ((ActivityManager) context.getSystemService("activity")).getRunningTasks(2).get(0)).topActivity.getClassName().equals("com.unity3d.player.UnityPlayerActivity") || a("com.unity3d.player.UnityPlayerActivity");
            Boolean valueOf = Boolean.valueOf(z);
            Boolean.toString(valueOf.booleanValue());
            return valueOf.booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
