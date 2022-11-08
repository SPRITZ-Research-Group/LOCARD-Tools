package com.skype.badges.implementations;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.facebook.common.logging.FLog;
import com.skype.badges.AndroidBadgesModule;
import com.skype.badges.interfaces.BadgeNotification;

public class DefaultBadgeNotification implements BadgeNotification {
    protected Application a;
    protected String b = a();

    public DefaultBadgeNotification(Application context) {
        this.a = context;
        FLog.i(AndroidBadgesModule.TAG, "Launcher Class Name: " + this.b);
    }

    private String a() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo info : this.a.getPackageManager().queryIntentActivities(intent, 0)) {
            if (info.activityInfo.applicationInfo.packageName.equalsIgnoreCase(this.a.getPackageName())) {
                return info.activityInfo.name;
            }
        }
        return "";
    }

    public void a(int badgeCount) {
    }
}
