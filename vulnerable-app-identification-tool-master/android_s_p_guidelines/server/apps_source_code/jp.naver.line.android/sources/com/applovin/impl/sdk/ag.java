package com.applovin.impl.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.cordova.networkinformation.NetworkManager;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ag {
    private static final int[] a = new int[]{7, 4, 2, 1, 11};
    private static final int[] b = new int[]{5, 6, 10, 3, 9, 8, 14};
    private static final int[] c = new int[]{15, 12, 13};
    private static final String d = ag.class.getSimpleName();

    private static NetworkInfo a(Context context) {
        if (ah.a("android.permission.ACCESS_NETWORK_STATE", context)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
        }
        return null;
    }

    static String a(AppLovinSdkImpl appLovinSdkImpl) {
        NetworkInfo a = a(appLovinSdkImpl.getApplicationContext());
        if (a == null) {
            return NetworkManager.TYPE_UNKNOWN;
        }
        int type = a.getType();
        int subtype = a.getSubtype();
        String str = type == 1 ? "wifi" : type == 0 ? a(subtype, a) ? NetworkManager.TYPE_2G : a(subtype, b) ? NetworkManager.TYPE_3G : a(subtype, c) ? NetworkManager.TYPE_4G : NetworkManager.MOBILE : NetworkManager.TYPE_UNKNOWN;
        AppLovinLogger logger = appLovinSdkImpl.getLogger();
        String str2 = d;
        StringBuilder stringBuilder = new StringBuilder("Network ");
        stringBuilder.append(type);
        stringBuilder.append("/");
        stringBuilder.append(subtype);
        stringBuilder.append(" resolved to ");
        stringBuilder.append(str);
        logger.d(str2, stringBuilder.toString());
        return str;
    }

    static String a(InputStream inputStream, AppLovinSdkImpl appLovinSdkImpl) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[((Integer) appLovinSdkImpl.get(ea.df)).intValue()];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return byteArrayOutputStream.toString("UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th) {
            appLovinSdkImpl.getLogger().e(d, "Encountered error while reading stream", th);
            return null;
        }
    }

    static String a(String str, String str2, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null || str.length() < 4) {
            throw new IllegalArgumentException("Invalid domain specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (appLovinSdkImpl != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            StringBuilder stringBuilder2 = new StringBuilder(stringBuilder.toString());
            if (map != null && map.size() > 0) {
                StringBuilder stringBuilder3 = new StringBuilder("?");
                stringBuilder3.append(gd.a((Map) map));
                stringBuilder2.append(stringBuilder3.toString());
            }
            return stringBuilder2.toString();
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    static String a(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        Map map2;
        String str2 = (String) appLovinSdkImpl.get(ea.m);
        appLovinSdkImpl.get(ea.h);
        if (map2 == null) {
            map2 = b(appLovinSdkImpl);
        } else {
            map2.putAll(b(appLovinSdkImpl));
        }
        return a(str2, str, map2, appLovinSdkImpl);
    }

    static JSONObject a(JSONObject jSONObject) throws JSONException {
        return (JSONObject) jSONObject.getJSONArray("results").get(0);
    }

    static void a(int i, AppLovinSdkImpl appLovinSdkImpl) {
        ec ecVar;
        Object obj;
        ed settingsManager = appLovinSdkImpl.getSettingsManager();
        if (i == HttpStatus.SC_UNAUTHORIZED) {
            settingsManager.a(ea.f, (Object) "");
            ecVar = ea.h;
            obj = "";
        } else if (i == 418) {
            ecVar = ea.a;
            obj = Boolean.TRUE;
        } else if (i < HttpStatus.SC_BAD_REQUEST || i >= 500) {
            if (i == -1) {
                appLovinSdkImpl.h();
            }
            return;
        } else {
            appLovinSdkImpl.h();
            return;
        }
        settingsManager.a(ecVar, obj);
        settingsManager.a();
    }

    static void a(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (appLovinSdkImpl != null) {
            try {
                if (jSONObject.has("settings")) {
                    ed settingsManager = appLovinSdkImpl.getSettingsManager();
                    if (!jSONObject.isNull("settings")) {
                        settingsManager.a(jSONObject.getJSONObject("settings"));
                        settingsManager.a();
                        appLovinSdkImpl.getLogger().d(d, "New settings processed");
                    }
                }
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().e(d, "Unable to parse settings out of API response", e);
            }
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    private static boolean a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, AppLovinSdkImpl appLovinSdkImpl) {
        Object obj = (!ah.a("android.permission.ACCESS_NETWORK_STATE", context) || context.getSystemService("connectivity") == null) ? null : 1;
        if (obj == null) {
            return true;
        }
        NetworkInfo a = a(context);
        return a != null ? a.isConnected() : ((Boolean) appLovinSdkImpl.get(ea.cH)).booleanValue();
    }

    static String b(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        return a((String) appLovinSdkImpl.get(ea.n), str, map, appLovinSdkImpl);
    }

    private static Map<String, String> b(AppLovinSdkImpl appLovinSdkImpl) {
        Map<String, String> hashMap = new HashMap();
        String str = (String) appLovinSdkImpl.get(ea.h);
        if (AppLovinSdkUtils.isValidString(str)) {
            hashMap.put("device_token", str);
        } else {
            hashMap.put("api_key", appLovinSdkImpl.getSdkKey());
        }
        return hashMap;
    }

    static void b(int i, AppLovinSdkImpl appLovinSdkImpl) {
        if (i == 418) {
            ed settingsManager = appLovinSdkImpl.getSettingsManager();
            settingsManager.a(ea.a, Boolean.TRUE);
            settingsManager.a();
        }
    }

    static void b(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        JSONArray a = bu.a(jSONObject, "zones", null, (AppLovinSdk) appLovinSdkImpl);
        if (a != null) {
            Iterator it = appLovinSdkImpl.getZoneManager().a(a).iterator();
            while (it.hasNext()) {
                n nVar = (n) it.next();
                if (nVar.e()) {
                    appLovinSdkImpl.getNativeAdService().a(nVar);
                } else {
                    appLovinSdkImpl.getAdService().preloadAds(nVar);
                }
            }
            appLovinSdkImpl.c().a(appLovinSdkImpl.getZoneManager().b());
            appLovinSdkImpl.d().a(appLovinSdkImpl.getZoneManager().b());
        }
    }

    static String c(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        Map map2;
        String str2 = (String) appLovinSdkImpl.get(ea.q);
        if (map2 == null) {
            map2 = b(appLovinSdkImpl);
        } else {
            map2.putAll(b(appLovinSdkImpl));
        }
        return a(str2, str, map2, appLovinSdkImpl);
    }

    static String d(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        return a((String) appLovinSdkImpl.get(ea.r), str, map, appLovinSdkImpl);
    }
}
