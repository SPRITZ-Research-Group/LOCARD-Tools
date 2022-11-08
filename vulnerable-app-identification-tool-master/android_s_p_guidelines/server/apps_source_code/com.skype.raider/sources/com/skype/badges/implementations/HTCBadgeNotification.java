package com.skype.badges.implementations;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import com.skype.badges.interfaces.BadgeNotification;

public class HTCBadgeNotification extends DefaultBadgeNotification implements BadgeNotification {
    private String c;

    public HTCBadgeNotification(Application context) {
        super(context);
        this.c = new ComponentName(context.getPackageName(), this.b).flattenToShortString();
    }

    public final void a(int badgeCount) {
        Intent i1 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        i1.putExtra("packagename", this.a.getPackageName());
        i1.putExtra("count", badgeCount);
        this.a.sendBroadcast(i1);
        Intent i2 = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        i2.putExtra("com.htc.launcher.extra.COMPONENT", this.c);
        i2.putExtra("com.htc.launcher.extra.COUNT", badgeCount);
        this.a.sendBroadcast(i2);
    }
}
