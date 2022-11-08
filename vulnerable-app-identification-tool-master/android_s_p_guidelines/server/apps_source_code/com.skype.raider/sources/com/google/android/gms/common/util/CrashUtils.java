package com.google.android.gms.common.util;

import android.os.DropBoxManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.concurrent.GuardedBy;

public final class CrashUtils {
    private static final String[] a = new String[]{"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager b = null;
    private static boolean c = false;
    private static int d = -1;
    @GuardedBy("CrashUtils.class")
    private static int e = 0;
    @GuardedBy("CrashUtils.class")
    private static int f = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorDialogData {
        public static final int AVG_CRASH_FREQ = 2;
        public static final int BINDER_CRASH = 268435456;
        public static final int DYNAMITE_CRASH = 536870912;
        public static final int FORCED_SHUSHED_BY_WRAPPER = 4;
        public static final int NONE = 0;
        public static final int POPUP_FREQ = 1;
        public static final int SUPPRESSED = 1073741824;
    }
}
