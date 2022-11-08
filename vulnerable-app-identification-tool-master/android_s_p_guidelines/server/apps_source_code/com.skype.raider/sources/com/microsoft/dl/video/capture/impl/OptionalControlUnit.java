package com.microsoft.dl.video.capture.impl;

import android.os.Handler;
import android.os.Looper;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.PackageInfo;
import java.lang.reflect.Method;
import java.util.Arrays;

public class OptionalControlUnit {
    private static final Method a;
    private static final String b;
    private static final Handler c = new Handler(Looper.getMainLooper());

    private static class OnStateChangedCmd implements Runnable {
        private final int a;
        private final int b;
        private final int c;

        protected OnStateChangedCmd(int what, int arg1, int arg2) {
            this.a = what;
            this.b = arg1;
            this.c = arg2;
        }

        public void run() {
            try {
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "Calling " + OptionalControlUnit.b + '(' + this.a + ", " + this.b + ", " + this.c + ')');
                }
                OptionalControlUnit.a.invoke(null, new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
            } catch (Exception e) {
                if (Log.isLoggable(PackageInfo.TAG, 5)) {
                    Log.w(PackageInfo.TAG, "Exception caught", e);
                }
            }
        }
    }

    static {
        Method a = a("com.skype.android.video.ControlUnit", "onStateChanged", Integer.TYPE, Integer.TYPE, Integer.TYPE);
        a = a;
        b = a != null ? a.getDeclaringClass().getCanonicalName() + "." + a.getName() : null;
    }

    private static Class<?> a(String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            if (Log.isLoggable(PackageInfo.TAG, 5)) {
                Log.w(PackageInfo.TAG, "Could not find class " + name);
            }
            return null;
        }
    }

    private static Method a(String className, String methodName, Class<?>... params) {
        Method method = null;
        Class<?> clazz = a(className);
        if (clazz == null) {
            return method;
        }
        try {
            return clazz.getDeclaredMethod(methodName, params);
        } catch (Exception e) {
            if (!Log.isLoggable(PackageInfo.TAG, 5)) {
                return method;
            }
            Log.w(PackageInfo.TAG, "Could not find method " + className + '.' + methodName + '(' + Arrays.deepToString(params) + ')', e);
            return method;
        }
    }

    public static void onStateChanged(int what, int arg1, int arg2) {
        if (a != null) {
            c.post(new OnStateChangedCmd(what, arg1, arg2));
        } else if (Log.isLoggable(PackageInfo.TAG, 5)) {
            Log.w(PackageInfo.TAG, "Could not call " + b + '(' + what + ", " + arg1 + ", " + arg2 + ')');
        }
    }
}
