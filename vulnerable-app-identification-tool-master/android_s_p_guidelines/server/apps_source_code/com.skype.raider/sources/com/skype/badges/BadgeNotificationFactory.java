package com.skype.badges;

import android.app.Application;
import android.os.Build;
import com.skype.badges.implementations.DefaultBadgeNotification;
import com.skype.badges.implementations.HTCBadgeNotification;
import com.skype.badges.implementations.SamsungBadgeNotification;
import com.skype.badges.implementations.XperiaBadgeNotification;
import com.skype.badges.interfaces.BadgeNotification;

public class BadgeNotificationFactory {
    private BadgeNotificationFactory() {
    }

    public static BadgeNotification a(Application context) {
        if (Build.MANUFACTURER.equalsIgnoreCase("Samsung")) {
            return new SamsungBadgeNotification(context);
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("Htc")) {
            return new HTCBadgeNotification(context);
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("Sony")) {
            return new XperiaBadgeNotification(context);
        }
        return new DefaultBadgeNotification(context);
    }
}
