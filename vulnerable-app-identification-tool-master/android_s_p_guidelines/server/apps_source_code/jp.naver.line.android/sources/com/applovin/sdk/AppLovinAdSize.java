package com.applovin.sdk;

import com.applovin.impl.sdk.aa;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.http.HttpStatus;

public class AppLovinAdSize {
    public static final AppLovinAdSize BANNER = new AppLovinAdSize(-1, 50, "BANNER");
    public static final AppLovinAdSize INTERSTITIAL = new AppLovinAdSize(-1, -1, "INTER");
    public static final AppLovinAdSize LEADER = new AppLovinAdSize(-1, 75, "LEADER");
    public static final AppLovinAdSize MREC = new AppLovinAdSize(HttpStatus.SC_MULTIPLE_CHOICES, 250, "MREC");
    public static final AppLovinAdSize NATIVE = new AppLovinAdSize("NATIVE");
    public static final int SPAN = -1;
    private final int a;
    private final int b;
    private final String c;

    protected AppLovinAdSize(int i, int i2, String str) {
        if (i < 0 && i != -1) {
            throw new IllegalArgumentException("Ad width must be a positive number. Number provided: ".concat(String.valueOf(i)));
        } else if (i > 9999) {
            throw new IllegalArgumentException("Ad width must be less then 9999. Number provided: ".concat(String.valueOf(i)));
        } else if (i2 < 0 && i2 != -1) {
            throw new IllegalArgumentException("Ad height must be a positive number. Number provided: ".concat(String.valueOf(i2)));
        } else if (i2 > 9999) {
            throw new IllegalArgumentException("Ad height must be less then 9999. Number provided: ".concat(String.valueOf(i2)));
        } else if (str == null) {
            throw new IllegalArgumentException("No label specified");
        } else if (str.length() <= 9) {
            this.a = i;
            this.b = i2;
            this.c = str;
        } else {
            throw new IllegalArgumentException("Provided label is too long. Label provided: ".concat(String.valueOf(str)));
        }
    }

    public AppLovinAdSize(String str) {
        this(0, 0, str);
    }

    private static int a(java.lang.String r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.sdk.AppLovinAdSize.a(java.lang.String):int. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = "span";
        r0 = r0.equalsIgnoreCase(r1);
        if (r0 == 0) goto L_0x000a;
    L_0x0008:
        r1 = -1;
        return r1;
    L_0x000a:
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ NumberFormatException -> 0x000f }
        return r1;
    L_0x000f:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.sdk.AppLovinAdSize.a(java.lang.String):int");
    }

    public static Set<AppLovinAdSize> allSizes() {
        Set<AppLovinAdSize> hashSet = new HashSet(4);
        hashSet.add(BANNER);
        hashSet.add(MREC);
        hashSet.add(INTERSTITIAL);
        hashSet.add(LEADER);
        hashSet.add(NATIVE);
        return hashSet;
    }

    public static AppLovinAdSize fromString(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        String toLowerCase = str.toLowerCase(Locale.ENGLISH);
        if (toLowerCase.equals("banner")) {
            return BANNER;
        }
        if (toLowerCase.equals("interstitial") || toLowerCase.equals("inter")) {
            return INTERSTITIAL;
        }
        if (toLowerCase.equals("mrec")) {
            return MREC;
        }
        if (toLowerCase.equals("leader")) {
            return LEADER;
        }
        List a = aa.a(str, "x");
        return a.size() == 2 ? new AppLovinAdSize(a((String) a.get(0)), a((String) a.get(1)), str) : new AppLovinAdSize(0, 0, str);
    }

    public int getHeight() {
        return this.b;
    }

    public String getLabel() {
        return this.c.toUpperCase(Locale.ENGLISH);
    }

    public int getWidth() {
        return this.a;
    }

    public String toString() {
        return getLabel();
    }
}
