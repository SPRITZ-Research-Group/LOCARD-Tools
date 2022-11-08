package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import java.io.File;

public class a {
    private static final Object a = new Object();
    private static TypedValue b;

    public static boolean a(Context context, Intent[] intents, Bundle options) {
        if (VERSION.SDK_INT >= 16) {
            context.startActivities(intents, options);
        } else {
            context.startActivities(intents);
        }
        return true;
    }

    public static void a(Context context, Intent intent, @Nullable Bundle options) {
        if (VERSION.SDK_INT >= 16) {
            context.startActivity(intent, options);
        } else {
            context.startActivity(intent);
        }
    }

    public static File[] a(Context context) {
        if (VERSION.SDK_INT >= 19) {
            return context.getExternalFilesDirs(null);
        }
        return new File[]{context.getExternalFilesDir(null)};
    }

    public static File[] b(Context context) {
        if (VERSION.SDK_INT >= 19) {
            return context.getExternalCacheDirs();
        }
        return new File[]{context.getExternalCacheDir()};
    }

    public static final Drawable a(Context context, @DrawableRes int id) {
        if (VERSION.SDK_INT >= 21) {
            return context.getDrawable(id);
        }
        if (VERSION.SDK_INT >= 16) {
            return context.getResources().getDrawable(id);
        }
        int resolvedId;
        synchronized (a) {
            if (b == null) {
                b = new TypedValue();
            }
            context.getResources().getValue(id, b, true);
            resolvedId = b.resourceId;
        }
        return context.getResources().getDrawable(resolvedId);
    }

    public static final ColorStateList b(Context context, @ColorRes int id) {
        if (VERSION.SDK_INT >= 23) {
            return context.getColorStateList(id);
        }
        return context.getResources().getColorStateList(id);
    }

    @ColorInt
    public static final int c(Context context, @ColorRes int id) {
        if (VERSION.SDK_INT >= 23) {
            return context.getColor(id);
        }
        return context.getResources().getColor(id);
    }

    public static int a(@NonNull Context context, @NonNull String permission) {
        if (permission != null) {
            return context.checkPermission(permission, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static final File c(Context context) {
        if (VERSION.SDK_INT >= 21) {
            return context.getNoBackupFilesDir();
        }
        return a(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }

    private static synchronized File a(File file) {
        synchronized (a.class) {
            if (!(file.exists() || file.mkdirs() || file.exists())) {
                new StringBuilder("Unable to create files subdir ").append(file.getPath());
                file = null;
            }
        }
        return file;
    }

    public static boolean d(Context context) {
        if (VERSION.SDK_INT >= 24) {
            return context.isDeviceProtectedStorage();
        }
        return false;
    }
}
