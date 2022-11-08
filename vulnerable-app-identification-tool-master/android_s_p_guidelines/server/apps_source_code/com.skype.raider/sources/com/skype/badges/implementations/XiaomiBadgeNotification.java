package com.skype.badges.implementations;

import android.content.Intent;
import com.facebook.common.logging.FLog;
import com.skype.badges.AndroidBadgesModule;
import com.skype.badges.interfaces.BadgeNotification;
import java.lang.reflect.Field;

public class XiaomiBadgeNotification extends DefaultBadgeNotification implements BadgeNotification {
    private Field c;
    private Object d;

    public final void a(int badgeCount) {
        if (!(this.c != null ? b(badgeCount) : false)) {
            Object obj;
            Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
            intent.putExtra("android.intent.extra.update_application_component_name", this.a.getPackageName() + "/" + this.a.getPackageManager().getLaunchIntentForPackage(this.a.getPackageName()).getComponent().getClassName());
            String str = "android.intent.extra.update_application_message_text";
            if (badgeCount == 0) {
                obj = "";
            } else {
                obj = Integer.valueOf(badgeCount);
            }
            intent.putExtra(str, String.valueOf(obj));
            this.a.sendBroadcast(intent);
        }
    }

    private boolean b(int badgeCount) {
        try {
            this.c.set(this.d, Integer.valueOf(badgeCount));
            return true;
        } catch (Exception ex) {
            FLog.i(AndroidBadgesModule.TAG, "XiaomiBadgeNotification setBadgeCountByField: Exception" + ex);
            return false;
        }
    }
}
