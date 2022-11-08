package android.support.v4.content;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker {

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionResult {
    }

    public static int a(@NonNull Context context, @NonNull String permission, int pid, int uid, String packageName) {
        if (context.checkPermission(permission, pid, uid) == -1) {
            return -1;
        }
        String op;
        if (VERSION.SDK_INT >= 23) {
            op = AppOpsManager.permissionToOp(permission);
        } else {
            op = null;
        }
        if (op == null) {
            return 0;
        }
        int noteProxyOp;
        if (packageName == null) {
            String[] packageNames = context.getPackageManager().getPackagesForUid(uid);
            if (packageNames == null || packageNames.length <= 0) {
                return -1;
            }
            packageName = packageNames[0];
        }
        if (VERSION.SDK_INT >= 23) {
            noteProxyOp = ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(op, packageName);
        } else {
            noteProxyOp = 1;
        }
        if (noteProxyOp != 0) {
            return -2;
        }
        return 0;
    }

    public static int a(@NonNull Context context, @NonNull String permission) {
        return a(context, permission, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
