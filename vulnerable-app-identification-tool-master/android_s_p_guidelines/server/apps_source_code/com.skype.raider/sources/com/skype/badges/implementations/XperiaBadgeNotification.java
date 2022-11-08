package com.skype.badges.implementations;

import android.app.Application;
import android.content.Intent;
import com.skype.badges.interfaces.BadgeNotification;

public class XperiaBadgeNotification extends DefaultBadgeNotification implements BadgeNotification {
    public XperiaBadgeNotification(Application context) {
        super(context);
    }

    public final void a(int badgeCount) {
        Intent i1 = new Intent();
        i1.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        i1.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", this.b);
        i1.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", true);
        i1.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.format("%s", new Object[]{Integer.valueOf(badgeCount)}));
        i1.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", this.a.getPackageName());
        this.a.sendBroadcast(i1);
    }
}
