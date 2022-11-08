package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class a {
    private static final Object a = new Object();
    private static final Map<String, Map<String, String>> b = new HashMap();

    static Map<String, String> a(AppLovinSdkImpl appLovinSdkImpl) {
        Map<String, String> c;
        synchronized (a) {
            appLovinSdkImpl.getLogger().d("AdDataCache", "Reading cached device data...");
            c = c(appLovinSdkImpl);
        }
        return c;
    }

    private static void a(String str, Map<String, String> map) {
        List a = aa.a(str, "=");
        if (a.size() == 2) {
            map.put(a.get(0), a.get(1));
        }
    }

    static void a(Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        b(map, appLovinSdkImpl);
    }

    static void b(AppLovinSdkImpl appLovinSdkImpl) {
        synchronized (a) {
            appLovinSdkImpl.getLogger().d("AdDataCache", "Clearing old device data cache...");
            a(new HashMap(0), appLovinSdkImpl);
        }
    }

    private static void b(Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        if (map == null) {
            throw new IllegalArgumentException("No ad aata specified");
        } else if (appLovinSdkImpl != null) {
            try {
                synchronized (b) {
                    Map map2 = (Map) b.get("ad_data_cache");
                    if (map2 == null) {
                        map2 = new HashMap();
                    }
                    map2.clear();
                    map2.putAll(map);
                    b.put("ad_data_cache", map2);
                }
                appLovinSdkImpl.put(ef.d, gd.a((Map) map));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(map.size());
                stringBuilder.append(" ad_data_cache entries saved in cache");
                appLovinSdkImpl.getLogger().d("AdDataCache", stringBuilder.toString());
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().e("AdDataCache", "Unable to save ad data entries", e);
            }
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    private static Map<String, String> c(AppLovinSdkImpl appLovinSdkImpl) {
        Map map;
        Throwable e;
        synchronized (b) {
            map = (Map) b.get("ad_data_cache");
        }
        if (map == null) {
            List<String> a = aa.a((String) appLovinSdkImpl.get(ef.d), "&");
            if (!a.isEmpty()) {
                try {
                    Map hashMap = new HashMap();
                    try {
                        for (String a2 : a) {
                            a(a2, hashMap);
                        }
                        synchronized (b) {
                            b.put("ad_data_cache", hashMap);
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(hashMap.size());
                        stringBuilder.append(" ad_data_cache entries loaded from cache");
                        appLovinSdkImpl.getLogger().d("AdDataCache", stringBuilder.toString());
                        map = hashMap;
                    } catch (Exception e2) {
                        e = e2;
                        map = hashMap;
                    }
                } catch (Exception e3) {
                    e = e3;
                    appLovinSdkImpl.getLogger().e("AdDataCache", "Unable to load ad data", e);
                    appLovinSdkImpl.put(ef.d, "");
                    return map == null ? new HashMap() : new HashMap(map);
                }
            }
        }
        if (map == null) {
        }
    }
}
