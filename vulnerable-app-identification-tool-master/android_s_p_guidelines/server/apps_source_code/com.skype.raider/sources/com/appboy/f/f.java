package com.appboy.f;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;
import java.util.Random;

public final class f {
    private static final String a = String.format("%s.%s", new Object[]{"Appboy v2.5.1 .", f.class.getName()});
    private static final Random b = new Random();

    public static int a() {
        return b.nextInt();
    }

    public static void a(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null) {
            new StringBuilder("No components found for the following intent: ").append(intent.getAction());
            return;
        }
        for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            context.sendBroadcast(intent2);
        }
    }
}
