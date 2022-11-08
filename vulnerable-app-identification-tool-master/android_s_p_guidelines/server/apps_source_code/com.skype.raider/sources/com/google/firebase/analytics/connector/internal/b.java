package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.measurement.AppMeasurement.d;
import java.util.Arrays;
import java.util.List;

public final class b {
    private static final List<String> a = Arrays.asList(new String[]{"_e", "_f", "_iap", "_s", "_au", "_ui", "_cd", "app_open"});
    private static final List<String> b = Arrays.asList(new String[]{"auto", "app", "am"});
    private static final List<String> c = Arrays.asList(new String[]{"_r", "_dbg"});
    private static final List<String> d;
    private static final List<String> e = Arrays.asList(new String[]{"^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$"});

    static {
        String[][] strArr = new String[][]{d.a, d.b};
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            i += strArr[i2].length;
        }
        Object copyOf = Arrays.copyOf(strArr[0], i);
        int length = strArr[0].length;
        for (i = 1; i < 2; i++) {
            Object obj = strArr[1];
            System.arraycopy(obj, 0, copyOf, length, obj.length);
        }
        d = Arrays.asList((String[]) copyOf);
    }

    public static boolean a(@NonNull String str) {
        return !b.contains(str);
    }

    public static boolean a(@NonNull String str, @Nullable Bundle bundle) {
        if (a.contains(str)) {
            return false;
        }
        if (bundle != null) {
            for (String containsKey : c) {
                if (bundle.containsKey(containsKey)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean a(@NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        if (!"_cmp".equals(str2)) {
            return true;
        }
        if (!a(str)) {
            return false;
        }
        if (bundle == null) {
            return false;
        }
        for (String containsKey : c) {
            if (bundle.containsKey(containsKey)) {
                return false;
            }
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case 101200:
                if (str.equals("fcm")) {
                    obj = null;
                    break;
                }
                break;
            case 101230:
                if (str.equals("fdl")) {
                    int obj2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                bundle.putString("_cis", "fcm_integration");
                return true;
            case 1:
                bundle.putString("_cis", "fdl_integration");
                return true;
            default:
                return false;
        }
    }

    public static boolean b(@NonNull String str) {
        if (d.contains(str)) {
            return false;
        }
        for (String matches : e) {
            if (str.matches(matches)) {
                return false;
            }
        }
        return true;
    }
}
