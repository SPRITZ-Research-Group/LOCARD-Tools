package com.google.android.exoplayer.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.exoplayer.upstream.DataSpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Util {
    public static final String DEVICE = Build.DEVICE;
    public static final String MANUFACTURER = Build.MANUFACTURER;
    private static final long MAX_BYTES_TO_DRAIN = 2048;
    public static final int SDK_INT = VERSION.SDK_INT;
    private static final Pattern XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)(\\.(\\d+))?([Zz]|((\\+|\\-)(\\d\\d):(\\d\\d)))?");
    private static final Pattern XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");

    private Util() {
    }

    @SuppressLint({"InlinedApi"})
    public static boolean isAndroidTv(Context context) {
        return context.getPackageManager().hasSystemFeature("android.software.leanback");
    }

    public static boolean isUrlLocalFile(URL url) {
        return url.getProtocol().equals("file");
    }

    public static boolean areEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public static boolean contains(Object[] objArr, Object obj) {
        for (Object areEqual : objArr) {
            if (areEqual(areEqual, obj)) {
                return true;
            }
        }
        return false;
    }

    public static ExecutorService newSingleThreadExecutor(final String str) {
        return Executors.newSingleThreadExecutor(new ThreadFactory() {
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, str);
            }
        });
    }

    public static ScheduledExecutorService newSingleThreadScheduledExecutor(final String str) {
        return Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, str);
            }
        });
    }

    public static void closeQuietly(com.google.android.exoplayer.upstream.DataSource r0) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.util.Util.closeQuietly(com.google.android.exoplayer.upstream.DataSource):void. bs: []
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
        r0.close();	 Catch:{ IOException -> 0x0004 }
        return;
    L_0x0004:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.util.Util.closeQuietly(com.google.android.exoplayer.upstream.DataSource):void");
    }

    public static void closeQuietly(java.io.OutputStream r0) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.util.Util.closeQuietly(java.io.OutputStream):void. bs: []
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
        r0.close();	 Catch:{ IOException -> 0x0004 }
        return;
    L_0x0004:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.util.Util.closeQuietly(java.io.OutputStream):void");
    }

    public static String toLowerInvariant(String str) {
        return str == null ? null : str.toLowerCase(Locale.US);
    }

    public static int ceilDivide(int i, int i2) {
        return ((i + i2) - 1) / i2;
    }

    public static long ceilDivide(long j, long j2) {
        return ((j + j2) - 1) / j2;
    }

    public static int binarySearchFloor(long[] jArr, long j, boolean z, boolean z2) {
        int binarySearch = Arrays.binarySearch(jArr, j);
        if (binarySearch < 0) {
            binarySearch = -(binarySearch + 2);
        } else if (!z) {
            binarySearch--;
        }
        return z2 ? Math.max(0, binarySearch) : binarySearch;
    }

    public static int binarySearchCeil(long[] jArr, long j, boolean z, boolean z2) {
        int binarySearch = Arrays.binarySearch(jArr, j);
        if (binarySearch < 0) {
            binarySearch ^= -1;
        } else if (!z) {
            binarySearch++;
        }
        return z2 ? Math.min(jArr.length - 1, binarySearch) : binarySearch;
    }

    public static <T> int binarySearchFloor(List<? extends Comparable<? super T>> list, T t, boolean z, boolean z2) {
        int binarySearch = Collections.binarySearch(list, t);
        if (binarySearch < 0) {
            binarySearch = -(binarySearch + 2);
        } else if (!z) {
            binarySearch--;
        }
        return z2 ? Math.max(0, binarySearch) : binarySearch;
    }

    public static <T> int binarySearchCeil(List<? extends Comparable<? super T>> list, T t, boolean z, boolean z2) {
        int binarySearch = Collections.binarySearch(list, t);
        if (binarySearch < 0) {
            binarySearch ^= -1;
        } else if (!z) {
            binarySearch++;
        }
        return z2 ? Math.min(list.size() - 1, binarySearch) : binarySearch;
    }

    public static long parseXsDuration(String str) {
        Matcher matcher = XS_DURATION_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return (long) ((Double.parseDouble(str) * 3600.0d) * 1000.0d);
        }
        int isEmpty = 1 ^ TextUtils.isEmpty(matcher.group(1));
        String group = matcher.group(3);
        double d = 0.0d;
        double parseDouble = group != null ? Double.parseDouble(group) * 3.1556908E7d : 0.0d;
        group = matcher.group(5);
        parseDouble += group != null ? Double.parseDouble(group) * 2629739.0d : 0.0d;
        group = matcher.group(7);
        parseDouble += group != null ? Double.parseDouble(group) * 86400.0d : 0.0d;
        group = matcher.group(10);
        parseDouble += group != null ? 3600.0d * Double.parseDouble(group) : 0.0d;
        group = matcher.group(12);
        parseDouble += group != null ? Double.parseDouble(group) * 60.0d : 0.0d;
        String group2 = matcher.group(14);
        if (group2 != null) {
            d = Double.parseDouble(group2);
        }
        long j = (long) ((parseDouble + d) * 1000.0d);
        if (isEmpty != 0) {
            j = -j;
        }
        return j;
    }

    public static long parseXsDateTime(String str) throws ParseException {
        Matcher matcher = XS_DATE_TIME_PATTERN.matcher(str);
        int i = 0;
        if (matcher.matches()) {
            if (!(matcher.group(9) == null || matcher.group(9).equalsIgnoreCase("Z"))) {
                i = (Integer.parseInt(matcher.group(12)) * 60) + Integer.parseInt(matcher.group(13));
                if (matcher.group(11).equals("-")) {
                    i *= -1;
                }
            }
            Calendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            gregorianCalendar.clear();
            gregorianCalendar.set(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
            if (!TextUtils.isEmpty(matcher.group(8))) {
                StringBuilder stringBuilder = new StringBuilder("0.");
                stringBuilder.append(matcher.group(8));
                gregorianCalendar.set(14, new BigDecimal(stringBuilder.toString()).movePointRight(3).intValue());
            }
            long timeInMillis = gregorianCalendar.getTimeInMillis();
            return i != 0 ? timeInMillis - ((long) (i * 60000)) : timeInMillis;
        } else {
            throw new ParseException("Invalid date/time format: ".concat(String.valueOf(str)), 0);
        }
    }

    public static long scaleLargeTimestamp(long j, long j2, long j3) {
        if (j3 >= j2 && j3 % j2 == 0) {
            return j / (j3 / j2);
        }
        if (j3 < j2 && j2 % j3 == 0) {
            return j * (j2 / j3);
        }
        double d = (double) j2;
        double d2 = (double) j3;
        Double.isNaN(d);
        Double.isNaN(d2);
        d /= d2;
        double d3 = (double) j;
        Double.isNaN(d3);
        return (long) (d3 * d);
    }

    public static long[] scaleLargeTimestamps(List<Long> list, long j, long j2) {
        long[] jArr = new long[list.size()];
        int i = 0;
        if (j2 >= j && j2 % j == 0) {
            j2 /= j;
            while (i < jArr.length) {
                jArr[i] = ((Long) list.get(i)).longValue() / j2;
                i++;
            }
        } else if (j2 >= j || j % j2 != 0) {
            double d = (double) j;
            double d2 = (double) j2;
            Double.isNaN(d);
            Double.isNaN(d2);
            d /= d2;
            while (i < jArr.length) {
                d2 = (double) ((Long) list.get(i)).longValue();
                Double.isNaN(d2);
                jArr[i] = (long) (d2 * d);
                i++;
            }
        } else {
            j /= j2;
            while (i < jArr.length) {
                jArr[i] = ((Long) list.get(i)).longValue() * j;
                i++;
            }
        }
        return jArr;
    }

    public static void scaleLargeTimestampsInPlace(long[] jArr, long j, long j2) {
        int i = 0;
        if (j2 >= j && j2 % j == 0) {
            j2 /= j;
            while (i < jArr.length) {
                jArr[i] = jArr[i] / j2;
                i++;
            }
        } else if (j2 >= j || j % j2 != 0) {
            double d = (double) j;
            double d2 = (double) j2;
            Double.isNaN(d);
            Double.isNaN(d2);
            d /= d2;
            while (i < jArr.length) {
                d2 = (double) jArr[i];
                Double.isNaN(d2);
                jArr[i] = (long) (d2 * d);
                i++;
            }
        } else {
            j /= j2;
            while (i < jArr.length) {
                jArr[i] = jArr[i] * j;
                i++;
            }
        }
    }

    public static int[] toArray(List<Integer> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
        return iArr;
    }

    public static void maybeTerminateInputStream(java.net.HttpURLConnection r3, long r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.util.Util.maybeTerminateInputStream(java.net.HttpURLConnection, long):void. bs: []
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
        r0 = SDK_INT;
        r1 = 19;
        if (r0 == r1) goto L_0x000d;
    L_0x0006:
        r0 = SDK_INT;
        r1 = 20;
        if (r0 == r1) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r3 = r3.getInputStream();	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r0 = -1;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        if (r2 != 0) goto L_0x001f;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x0017:
        r4 = r3.read();	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r5 = -1;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        if (r4 != r5) goto L_0x0026;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x001e:
        return;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x001f:
        r0 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        if (r2 > 0) goto L_0x0026;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x0025:
        return;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x0026:
        r4 = r3.getClass();	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r4 = r4.getName();	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r5 = "com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream";	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r5 = r4.equals(r5);	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        if (r5 != 0) goto L_0x003e;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x0036:
        r5 = "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream";	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r4 = r4.equals(r5);	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        if (r4 == 0) goto L_0x0058;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x003e:
        r4 = r3.getClass();	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r4 = r4.getSuperclass();	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r5 = "unexpectedEndOfInput";	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r0 = 0;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r1 = new java.lang.Class[r0];	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r4 = r4.getDeclaredMethod(r5, r1);	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r5 = 1;	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r4.setAccessible(r5);	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r5 = new java.lang.Object[r0];	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
        r4.invoke(r3, r5);	 Catch:{ IOException -> 0x005a, Exception -> 0x0059 }
    L_0x0058:
        return;
    L_0x0059:
        return;
    L_0x005a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.util.Util.maybeTerminateInputStream(java.net.HttpURLConnection, long):void");
    }

    public static DataSpec getRemainderDataSpec(DataSpec dataSpec, int i) {
        if (i == 0) {
            return dataSpec;
        }
        long j = -1;
        if (dataSpec.length != -1) {
            j = dataSpec.length - ((long) i);
        }
        return new DataSpec(dataSpec.uri, dataSpec.position + ((long) i), j, dataSpec.key, dataSpec.flags);
    }

    public static int getIntegerCodeForString(String str) {
        int length = str.length();
        Assertions.checkArgument(length <= 4);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i << 8) | str.charAt(i2);
        }
        return i;
    }

    public static String getHexStringFromBytes(byte[] bArr, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(i2 - i);
        while (i < i2) {
            stringBuilder.append(String.format(Locale.US, "%02X", new Object[]{Byte.valueOf(bArr[i])}));
            i++;
        }
        return stringBuilder.toString();
    }

    public static <T> String getCommaDelimitedSimpleClassNames(T[] tArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tArr.length; i++) {
            stringBuilder.append(tArr[i].getClass().getSimpleName());
            if (i < tArr.length - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    public static java.lang.String getUserAgent(android.content.Context r2, java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer.util.Util.getUserAgent(android.content.Context, java.lang.String):java.lang.String. bs: []
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
        r0 = r2.getPackageName();	 Catch:{ NameNotFoundException -> 0x0010 }
        r2 = r2.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0010 }
        r1 = 0;	 Catch:{ NameNotFoundException -> 0x0010 }
        r2 = r2.getPackageInfo(r0, r1);	 Catch:{ NameNotFoundException -> 0x0010 }
        r2 = r2.versionName;	 Catch:{ NameNotFoundException -> 0x0010 }
        goto L_0x0012;
    L_0x0010:
        r2 = "?";
    L_0x0012:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r3);
        r3 = "/";
        r0.append(r3);
        r0.append(r2);
        r2 = " (Linux;Android ";
        r0.append(r2);
        r2 = android.os.Build.VERSION.RELEASE;
        r0.append(r2);
        r2 = ") ExoPlayerLib/1.4.2";
        r0.append(r2);
        r2 = r0.toString();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.util.Util.getUserAgent(android.content.Context, java.lang.String):java.lang.String");
    }

    public static byte[] executePost(String str, byte[] bArr, Map<String, String> map) throws IOException {
        Throwable th;
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            OutputStream outputStream;
            InputStream inputStream;
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(bArr != null);
                httpURLConnection.setDoInput(true);
                if (map != null) {
                    for (Entry entry : map.entrySet()) {
                        httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (bArr != null) {
                    outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(bArr);
                    outputStream.close();
                }
                inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                inputStream.close();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return toByteArray;
            } catch (Throwable th2) {
                th = th2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
