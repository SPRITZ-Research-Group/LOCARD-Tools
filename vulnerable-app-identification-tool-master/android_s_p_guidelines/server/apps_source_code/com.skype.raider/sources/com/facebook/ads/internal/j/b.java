package com.facebook.ads.internal.j;

import java.util.ArrayList;
import java.util.List;

public final class b {
    private static final List<a> a = new ArrayList();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String a() {
        synchronized (a) {
            if (a.isEmpty()) {
                String str = "";
                return str;
            }
            List arrayList = new ArrayList(a);
            a.clear();
        }
    }

    public static void a(a aVar) {
        synchronized (a) {
            a.add(aVar);
        }
    }
}
