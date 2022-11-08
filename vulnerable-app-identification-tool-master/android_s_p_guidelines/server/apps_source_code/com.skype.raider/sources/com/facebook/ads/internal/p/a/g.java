package com.facebook.ads.internal.p.a;

import com.facebook.ads.internal.t.a;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public final class g implements r {
    private static void a(Map<String, List<String>> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                for (String str2 : (List) map.get(str)) {
                    System.out.println(str + ":" + str2);
                }
            }
        }
    }

    public final void a(n nVar) {
        if (nVar != null) {
            System.out.println("=== HTTP Response ===");
            System.out.println("Receive url: " + nVar.b());
            System.out.println("Status: " + nVar.a());
            a(nVar.c());
            System.out.println("Content:\n" + nVar.e());
        }
    }

    public final void a(String str) {
        System.out.println(str);
    }

    public final void a(HttpURLConnection httpURLConnection) {
        System.out.println("=== HTTP Request ===");
        System.out.println(httpURLConnection.getRequestMethod() + " " + httpURLConnection.getURL().toString());
        a(httpURLConnection.getRequestProperties());
    }

    public final boolean a() {
        return a.f();
    }
}
