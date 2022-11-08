package com.skype.badges.implementations;

import android.net.Uri;
import android.os.Bundle;
import com.skype.badges.interfaces.BadgeNotification;

public class HuaweiBadgeNotification extends DefaultBadgeNotification implements BadgeNotification {
    private final String c;
    private final boolean d;

    public final void a(int badgeCount) {
        if (!this.d) {
            Bundle extra = new Bundle();
            extra.putString("package", this.c);
            extra.putString("class", this.b);
            extra.putInt("badgenumber", new Integer(badgeCount).intValue());
            this.a.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, extra);
        }
    }
}
