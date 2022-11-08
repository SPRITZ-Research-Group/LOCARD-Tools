package com.skype.badges.implementations;

import android.content.Intent;
import com.skype.badges.interfaces.BadgeNotification;

public class LGBadgeNotification extends DefaultBadgeNotification implements BadgeNotification {
    public final void a(int badgeCount) {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", badgeCount);
        intent.putExtra("badge_count_package_name", this.a.getPackageName());
        intent.putExtra("badge_count_class_name", this.b);
        this.a.sendBroadcast(intent);
    }
}
