package com.microsoft.tokenshare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import java.util.List;

public class PackageChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getData() != null ? intent.getData().getSchemeSpecificPart() : null;
        if (packageName != null && context.getResources() != null) {
            c.a.a((BroadcastReceiver) this, context, packageName);
        }
    }

    static IntentFilter a(@NonNull Context context) {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("com.microsoft.tokenshare.SERVICE_ENABLED");
        intentfilter.addDataScheme("package");
        if (VERSION.SDK_INT >= 19) {
            List<String> packageNames = c.a.d().b(context);
            String runningPackage = context.getPackageName();
            for (String packageName : packageNames) {
                if (!runningPackage.equalsIgnoreCase(packageName)) {
                    intentfilter.addDataSchemeSpecificPart(packageName, 0);
                }
            }
        }
        return intentfilter;
    }
}
