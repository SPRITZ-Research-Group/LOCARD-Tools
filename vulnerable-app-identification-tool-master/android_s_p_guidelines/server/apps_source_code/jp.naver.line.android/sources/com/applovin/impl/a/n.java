package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.tagmanager.DataLayer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class n {
    private static DateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private static Random b = new Random(System.currentTimeMillis());

    public static Uri a(String str, long j, Uri uri, h hVar, AppLovinSdk appLovinSdk) {
        if (URLUtil.isValidUrl(str)) {
            try {
                String replace = str.replace("[ERRORCODE]", Integer.toString(hVar.a()));
                if (j >= 0) {
                    replace = replace.replace("[CONTENTPLAYHEAD]", a(j));
                }
                if (uri != null) {
                    replace = replace.replace("[ASSETURI]", uri.toString());
                }
                return Uri.parse(replace.replace("[CACHEBUSTING]", a()).replace("[TIMESTAMP]", b()));
            } catch (Throwable th) {
                appLovinSdk.getLogger().e("VastUtils", "Unable to replace macros in URL string ".concat(String.valueOf(str)), th);
                return null;
            }
        }
        appLovinSdk.getLogger().e("VastUtils", "Unable to replace macros in invalid URL string.");
        return null;
    }

    public static h a(a aVar) {
        return (b(aVar) || c(aVar)) ? null : h.GENERAL_WRAPPER_ERROR;
    }

    private static String a() {
        return Integer.toString(b.nextInt(89999999) + 10000000);
    }

    private static String a(long j) {
        if (j <= 0) {
            return "00:00:00.000";
        }
        long toHours = TimeUnit.SECONDS.toHours(j);
        long toMinutes = TimeUnit.SECONDS.toMinutes(j) % TimeUnit.MINUTES.toSeconds(1);
        j %= TimeUnit.MINUTES.toSeconds(1);
        return String.format("%02d:%02d:%02d.000", new Object[]{Long.valueOf(toHours), Long.valueOf(toMinutes), Long.valueOf(j)});
    }

    public static String a(g gVar) {
        if (gVar != null) {
            List b = gVar.b();
            int size = gVar.b().size();
            if (size > 0) {
                gf c = ((gf) b.get(size - 1)).c("VASTAdTagURI");
                if (c != null) {
                    return c.c();
                }
            }
            return null;
        }
        throw new IllegalArgumentException("Unable to get resolution uri string for fetching the next wrapper or inline response in the chain");
    }

    public static String a(gf gfVar, String str, String str2) {
        gfVar = gfVar.b(str);
        if (gfVar != null) {
            String c = gfVar.c();
            if (AppLovinSdkUtils.isValidString(c)) {
                return c;
            }
        }
        return str2;
    }

    private static Set<l> a(g gVar, AppLovinSdk appLovinSdk) {
        if (gVar == null) {
            return null;
        }
        List<gf> b = gVar.b();
        Set hashSet = new HashSet(b.size());
        for (gf gfVar : b) {
            gf c = gfVar.c("Wrapper");
            if (c == null) {
                c = gfVar.c("InLine");
            }
            hashSet = a(hashSet, c != null ? c.a("Error") : gfVar.a("Error"), gVar, appLovinSdk);
        }
        StringBuilder stringBuilder = new StringBuilder("Retrieved ");
        stringBuilder.append(hashSet.size());
        stringBuilder.append(" top level error trackers: ");
        stringBuilder.append(hashSet);
        appLovinSdk.getLogger().d("VastUtils", stringBuilder.toString());
        return hashSet;
    }

    private static Set<l> a(Set<l> set, List<gf> list, g gVar, AppLovinSdk appLovinSdk) {
        if (list != null) {
            for (gf a : list) {
                l a2 = l.a(a, gVar, appLovinSdk);
                if (a2 != null) {
                    set.add(a2);
                }
            }
        }
        return set;
    }

    public static void a(g gVar, AppLovinAdLoadListener appLovinAdLoadListener, h hVar, int i, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            gd.a(appLovinAdLoadListener, gVar.f(), i, appLovinSdkImpl);
            a(a(gVar, (AppLovinSdk) appLovinSdkImpl), hVar, appLovinSdkImpl);
            return;
        }
        throw new IllegalArgumentException("Unable to handle failure. No sdk specified.");
    }

    public static void a(gf gfVar, Map<String, Set<l>> map, g gVar, AppLovinSdk appLovinSdk) {
        if (appLovinSdk != null) {
            AppLovinLogger logger;
            String str;
            String str2;
            if (gfVar == null) {
                logger = appLovinSdk.getLogger();
                str = "VastUtils";
                str2 = "Unable to render event trackers; null node provided";
            } else if (map == null) {
                logger = appLovinSdk.getLogger();
                str = "VastUtils";
                str2 = "Unable to render event trackers; null event trackers provided";
            } else {
                gfVar = gfVar.b("TrackingEvents");
                if (gfVar != null) {
                    List<gf> a = gfVar.a("Tracking");
                    if (a != null) {
                        for (gf gfVar2 : a) {
                            String str3 = (String) gfVar2.b().get(DataLayer.EVENT_KEY);
                            if (AppLovinSdkUtils.isValidString(str3)) {
                                l a2 = l.a(gfVar2, gVar, appLovinSdk);
                                if (a2 != null) {
                                    Set set = (Set) map.get(str3);
                                    if (set != null) {
                                        set.add(a2);
                                    } else {
                                        set = new HashSet();
                                        set.add(a2);
                                        map.put(str3, set);
                                    }
                                }
                            } else {
                                appLovinSdk.getLogger().e("VastUtils", "Could not find event for tracking node = ".concat(String.valueOf(gfVar2)));
                            }
                        }
                    }
                }
                return;
            }
            logger.e(str, str2);
            return;
        }
        throw new IllegalArgumentException("Unable to render event trackers. No sdk specified.");
    }

    public static void a(List<gf> list, Set<l> set, g gVar, AppLovinSdk appLovinSdk) {
        if (appLovinSdk != null) {
            AppLovinLogger logger;
            String str;
            String str2;
            if (list == null) {
                logger = appLovinSdk.getLogger();
                str = "VastUtils";
                str2 = "Unable to render trackers; null nodes provided";
            } else if (set == null) {
                logger = appLovinSdk.getLogger();
                str = "VastUtils";
                str2 = "Unable to render trackers; null trackers provided";
            } else {
                for (gf a : list) {
                    l a2 = l.a(a, gVar, appLovinSdk);
                    if (a2 != null) {
                        set.add(a2);
                    }
                }
                return;
            }
            logger.e(str, str2);
            return;
        }
        throw new IllegalArgumentException("Unable to render trackers. No sdk specified.");
    }

    public static void a(Set<l> set, long j, Uri uri, h hVar, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("Unable to fire trackers. No sdk specified.");
        } else if (set != null && !set.isEmpty()) {
            for (l b : set) {
                Uri a = a(b.b(), j, uri, hVar, (AppLovinSdk) appLovinSdkImpl);
                if (a != null) {
                    appLovinSdkImpl.getPersistentPostbackManager().a(a.toString(), null, false);
                }
            }
        }
    }

    public static void a(Set<l> set, h hVar, AppLovinSdkImpl appLovinSdkImpl) {
        a((Set) set, -1, null, hVar, appLovinSdkImpl);
    }

    public static void a(Set<l> set, AppLovinSdkImpl appLovinSdkImpl) {
        a((Set) set, -1, null, h.UNSPECIFIED, appLovinSdkImpl);
    }

    public static boolean a(gf gfVar) {
        if (gfVar != null) {
            return gfVar.c("Wrapper") != null;
        } else {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains a wrapper response");
        }
    }

    private static String b() {
        a.setTimeZone(TimeZone.getDefault());
        return a.format(new Date());
    }

    public static boolean b(a aVar) {
        if (aVar == null) {
            return false;
        }
        o a = aVar.a();
        if (a != null) {
            List a2 = a.a();
            if (!(a2 == null || a2.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(gf gfVar) {
        if (gfVar != null) {
            return gfVar.c("InLine") != null;
        } else {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains an inline response");
        }
    }

    public static boolean c(a aVar) {
        if (aVar == null) {
            return false;
        }
        f e = aVar.e();
        if (e != null) {
            i b = e.b();
            if (b != null) {
                return b.b() != null || AppLovinSdkUtils.isValidString(b.c());
            }
        }
        return false;
    }
}
