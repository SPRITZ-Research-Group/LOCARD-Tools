package com.facebook.ads.internal.h;

import android.os.Build.VERSION;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.r.b;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import org.json.JSONObject;

public class d {
    private static final String c = d.class.getSimpleName();
    private static final b d = b.UNKNOWN;
    public int a = -1;
    public int b = -1;
    private final long e = System.currentTimeMillis();
    private b f = d;
    private int g = 1;
    private int h = 0;
    private int i = 0;
    private int j = 20;
    private int k = 0;
    private int l = Constants.ONE_SECOND;
    private int m = 10000;
    private int n = 200;
    private int o = 3600;
    private boolean p = false;
    private List<b> q = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private d(Map<String, String> map) {
        for (Entry entry : map.entrySet()) {
            Object obj;
            String str = (String) entry.getKey();
            switch (str.hashCode()) {
                case -1899431321:
                    if (str.equals("conv_tracking_data")) {
                        obj = 12;
                        break;
                    }
                case -1561601017:
                    if (str.equals("refresh_threshold")) {
                        obj = 4;
                        break;
                    }
                case -856794442:
                    if (str.equals("viewability_check_interval")) {
                        obj = 10;
                        break;
                    }
                case -726276175:
                    if (str.equals("request_timeout")) {
                        obj = 11;
                        break;
                    }
                case -634541425:
                    if (str.equals("invalidation_duration_in_seconds")) {
                        obj = 5;
                        break;
                    }
                case -553208868:
                    if (str.equals("cacheable")) {
                        obj = 6;
                        break;
                    }
                case 3575610:
                    if (str.equals("type")) {
                        obj = null;
                        break;
                    }
                case 700812481:
                    if (str.equals("min_viewability_percentage")) {
                        obj = 1;
                        break;
                    }
                case 858630459:
                    if (str.equals("viewability_check_ticker")) {
                        obj = 2;
                        break;
                    }
                case 986744879:
                    if (str.equals("video_time_polling_interval")) {
                        obj = 13;
                        break;
                    }
                case 1085444827:
                    if (str.equals("refresh")) {
                        obj = 3;
                        break;
                    }
                case 1183549815:
                    if (str.equals("viewability_check_initial_delay")) {
                        obj = 9;
                        break;
                    }
                case 1503616961:
                    if (str.equals("placement_height")) {
                        obj = 8;
                        break;
                    }
                case 2002133996:
                    if (str.equals("placement_width")) {
                        obj = 7;
                        break;
                    }
                default:
                    obj = -1;
                    break;
            }
            switch (obj) {
                case null:
                    this.f = b.a((String) entry.getValue());
                    break;
                case 1:
                    this.g = Integer.parseInt((String) entry.getValue());
                    break;
                case 2:
                    this.h = Integer.parseInt((String) entry.getValue());
                    break;
                case 3:
                    this.i = Integer.parseInt((String) entry.getValue());
                    break;
                case 4:
                    this.j = Integer.parseInt((String) entry.getValue());
                    break;
                case 5:
                    this.o = Integer.parseInt((String) entry.getValue());
                    break;
                case 6:
                    this.p = Boolean.valueOf((String) entry.getValue()).booleanValue();
                    break;
                case 7:
                    this.a = Integer.parseInt((String) entry.getValue());
                    break;
                case 8:
                    this.b = Integer.parseInt((String) entry.getValue());
                    break;
                case 9:
                    this.k = Integer.parseInt((String) entry.getValue());
                    break;
                case 10:
                    this.l = Integer.parseInt((String) entry.getValue());
                    break;
                case 11:
                    this.m = Integer.parseInt((String) entry.getValue());
                    break;
                case 12:
                    this.q = b.a((String) entry.getValue());
                    try {
                        CookieManager instance = CookieManager.getInstance();
                        boolean acceptCookie = instance.acceptCookie();
                        instance.setAcceptCookie(true);
                        for (b bVar : this.q) {
                            obj = (bVar.b == null || bVar.c == null || bVar.a == null) ? null : 1;
                            if (obj != null) {
                                StringBuilder append = new StringBuilder().append(bVar.b).append("=").append(bVar.c).append(";Domain=").append(bVar.a).append(";Expires=");
                                Date date = bVar.d;
                                if (date == null) {
                                    date = new Date();
                                    date.setTime(date.getTime() + 3600000);
                                }
                                DateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                                instance.setCookie(bVar.a, append.append(simpleDateFormat.format(date)).append(";path=/").toString());
                            }
                        }
                        if (VERSION.SDK_INT < 21) {
                            CookieSyncManager.getInstance().startSync();
                        }
                        instance.setAcceptCookie(acceptCookie);
                        break;
                    } catch (Exception e) {
                        break;
                    }
                case 13:
                    try {
                        this.n = Integer.parseInt((String) entry.getValue());
                        break;
                    } catch (NumberFormatException e2) {
                        this.n = 200;
                        break;
                    }
                default:
                    break;
            }
        }
    }

    public static d a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Iterator keys = jSONObject.keys();
        Map hashMap = new HashMap();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, String.valueOf(jSONObject.opt(str)));
        }
        return new d(hashMap);
    }

    public final long a() {
        return this.e;
    }

    public final b b() {
        return this.f;
    }

    public final long c() {
        return (long) (this.i * Constants.ONE_SECOND);
    }

    public final long d() {
        return (long) (this.j * Constants.ONE_SECOND);
    }

    public final boolean e() {
        return this.p;
    }

    public final int f() {
        return this.g;
    }

    public final int g() {
        return this.h;
    }

    public final int h() {
        return this.k;
    }

    public final int i() {
        return this.l;
    }

    public final int j() {
        return this.m;
    }

    public final int k() {
        return this.n;
    }

    public final int l() {
        return this.o * Constants.ONE_SECOND;
    }
}
