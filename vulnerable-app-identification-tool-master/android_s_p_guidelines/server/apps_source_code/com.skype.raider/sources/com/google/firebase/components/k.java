package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class k implements l {
    private k() {
    }

    /* synthetic */ k(byte b) {
        this();
    }

    private static Bundle b(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, ComponentDiscoveryService.class), 128);
            return serviceInfo != null ? serviceInfo.metaData : null;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public final List<String> a(Context context) {
        Bundle b = b(context);
        if (b == null) {
            return Collections.emptyList();
        }
        List<String> arrayList = new ArrayList();
        for (String str : b.keySet()) {
            if ("com.google.firebase.components.ComponentRegistrar".equals(b.get(str)) && str.startsWith("com.google.firebase.components:")) {
                arrayList.add(str.substring(31));
            }
        }
        return arrayList;
    }
}
