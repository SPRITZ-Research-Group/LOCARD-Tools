package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;

public final class r {
    public static Intent a(Activity sourceActivity) {
        if (VERSION.SDK_INT >= 16) {
            Intent result = sourceActivity.getParentActivityIntent();
            if (result != null) {
                return result;
            }
        }
        String parentName = b(sourceActivity);
        if (parentName == null) {
            return null;
        }
        ComponentName target = new ComponentName(sourceActivity, parentName);
        try {
            if (b(sourceActivity, target) == null) {
                return Intent.makeMainActivity(target);
            }
            return new Intent().setComponent(target);
        } catch (NameNotFoundException e) {
            new StringBuilder("getParentActivityIntent: bad parentActivityName '").append(parentName).append("' in manifest");
            return null;
        }
    }

    public static Intent a(Context context, ComponentName componentName) throws NameNotFoundException {
        String parentActivity = b(context, componentName);
        if (parentActivity == null) {
            return null;
        }
        ComponentName target = new ComponentName(componentName.getPackageName(), parentActivity);
        if (b(context, target) == null) {
            return Intent.makeMainActivity(target);
        }
        return new Intent().setComponent(target);
    }

    @Nullable
    public static String b(Activity sourceActivity) {
        try {
            return b(sourceActivity, sourceActivity.getComponentName());
        } catch (NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Nullable
    private static String b(Context context, ComponentName componentName) throws NameNotFoundException {
        ActivityInfo info = context.getPackageManager().getActivityInfo(componentName, 128);
        if (VERSION.SDK_INT >= 16) {
            String result = info.parentActivityName;
            if (result != null) {
                return result;
            }
        }
        if (info.metaData == null) {
            return null;
        }
        String parentActivity = info.metaData.getString("android.support.PARENT_ACTIVITY");
        if (parentActivity == null) {
            return null;
        }
        if (parentActivity.charAt(0) == '.') {
            parentActivity = context.getPackageName() + parentActivity;
        }
        return parentActivity;
    }
}
