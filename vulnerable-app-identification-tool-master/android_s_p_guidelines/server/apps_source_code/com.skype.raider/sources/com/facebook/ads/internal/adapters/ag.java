package com.facebook.ads.internal.adapters;

public final class ag {
    private static final String[] a = new String[]{"com.flurry.android.FlurryAgent", "com.flurry.android.ads.FlurryAdErrorType", "com.flurry.android.ads.FlurryAdNative", "com.flurry.android.ads.FlurryAdNativeAsset", "com.flurry.android.ads.FlurryAdNativeListener"};
    private static final String[] b = new String[]{"com.inmobi.ads.InMobiNative", "com.inmobi.sdk.InMobiSdk"};
    private static final String[] c = new String[]{"com.google.android.gms.ads.formats.NativeAdView"};

    public static boolean a(n nVar) {
        switch (nVar) {
            case AN:
                return true;
            case YAHOO:
                return a(a);
            case INMOBI:
                return a(b);
            case ADMOB:
                return a(c);
            default:
                return false;
        }
    }

    private static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean a(String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String a : strArr) {
            if (!a(a)) {
                return false;
            }
        }
        return true;
    }
}
