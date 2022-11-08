package com.facebook.ads;

import android.content.Context;

public final class c {
    private static boolean a;
    private static a b = a.DEFAULT;

    public enum a {
        DEFAULT("DEFAULT", "Default"),
        IMG_16_9_APP_INSTALL("IMG_16_9_APP_INSTALL", "Image App install"),
        IMG_16_9_LINK("IMG_16_9_LINK", "Image link"),
        VIDEO_HD_16_9_46S_APP_INSTALL("VID_HD_16_9_46S_APP_INSTALL", "Video 46 sec App install"),
        VIDEO_HD_16_9_46S_LINK("VID_HD_16_9_46S_LINK", "Video 46 sec link"),
        VIDEO_HD_16_9_15S_APP_INSTALL("VID_HD_16_9_15S_APP_INSTALL", "Video 15 sec App install"),
        VIDEO_HD_16_9_15S_LINK("VID_HD_16_9_15S_LINK", "Video 15 sec link"),
        VIDEO_HD_9_16_39S_APP_INSTALL("VID_HD_9_16_39S_APP_INSTALL", "Video 39 sec App install"),
        VIDEO_HD_9_16_39S_LINK("VID_HD_9_16_39S_LINK", "Video 39 sec link"),
        CAROUSEL_IMG_SQUARE_APP_INSTALL("CAROUSEL_IMG_SQUARE_APP_INSTALL", "Carousel App install"),
        CAROUSEL_IMG_SQUARE_LINK("CAROUSEL_IMG_SQUARE_LINK", "Carousel link");
        
        private final String l;
        private final String m;

        private a(String str, String str2) {
            this.l = str;
            this.m = str2;
        }

        public final String a() {
            return this.l;
        }
    }

    public static void a() {
        com.facebook.ads.internal.t.a.c();
    }

    public static void a(String str) {
        com.facebook.ads.internal.t.a.c(str);
    }

    public static void a(boolean z) {
        com.facebook.ads.internal.t.a.a(z);
    }

    public static boolean a(Context context) {
        return com.facebook.ads.internal.t.a.a(context);
    }

    public static String b() {
        return com.facebook.ads.internal.t.a.a();
    }

    public static void b(String str) {
        com.facebook.ads.internal.t.a.a(str);
    }

    public static void b(boolean z) {
        com.facebook.ads.internal.t.a.b(z);
    }

    public static void c(String str) {
        com.facebook.ads.internal.t.a.b(str);
    }

    public static void c(boolean z) {
        com.facebook.ads.internal.t.a.c(z);
    }

    public static boolean c() {
        return a;
    }

    public static a d() {
        return b;
    }

    public static void d(boolean z) {
        a = z;
    }
}
