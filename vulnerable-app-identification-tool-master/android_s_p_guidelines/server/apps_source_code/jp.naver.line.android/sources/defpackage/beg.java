package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.exoplayer2.y;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Inflater;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* renamed from: beg */
public final class beg {
    public static final int a = VERSION.SDK_INT;
    public static final String b = Build.DEVICE;
    public static final String c = Build.MANUFACTURER;
    public static final String d = Build.MODEL;
    public static final String e;
    public static final byte[] f = new byte[0];
    private static final Pattern g = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
    private static final Pattern h = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
    private static final Pattern i = Pattern.compile("%([A-Fa-f0-9]{2})");
    private static final int[] j = new int[]{0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};

    public static long a(long j, long j2) {
        long j3 = j + j2;
        return ((j ^ j3) & (j2 ^ j3)) < 0 ? Long.MAX_VALUE : j3;
    }

    @EnsuresNonNull({"#1"})
    public static <T> T a(T t) {
        return t;
    }

    public static boolean a(int i) {
        return i == 10 || i == 13;
    }

    public static int b(long j, long j2) {
        return j < j2 ? -1 : j == j2 ? 0 : 1;
    }

    public static boolean b(int i) {
        return i == 3 || i == 2 || i == Integer.MIN_VALUE || i == 1073741824 || i == 4;
    }

    public static boolean c(int i) {
        return i == Integer.MIN_VALUE || i == 1073741824;
    }

    public static int e(int i) {
        switch (i) {
            case 1:
            case 12:
            case 14:
                return 3;
            case 2:
                return 0;
            case 3:
                return 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            case 13:
                return 1;
            default:
                return 3;
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append(", ");
        stringBuilder.append(d);
        stringBuilder.append(", ");
        stringBuilder.append(c);
        stringBuilder.append(", ");
        stringBuilder.append(a);
        e = stringBuilder.toString();
    }

    public static ComponentName a(Context context, Intent intent) {
        if (a >= 26) {
            return context.startForegroundService(intent);
        }
        return context.startService(intent);
    }

    public static boolean a(Uri uri) {
        CharSequence scheme = uri.getScheme();
        return TextUtils.isEmpty(scheme) || "file".equals(scheme);
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public static <T> void a(List<T> list, int i) {
        if (i > list.size() || i < 0) {
            throw new IllegalArgumentException();
        } else if (i != 0) {
            list.subList(0, i).clear();
        }
    }

    public static <T> T[] a(T[] tArr, int i) {
        bcz.a(i <= tArr.length);
        return Arrays.copyOf(tArr, i);
    }

    public static Handler a(Looper looper, Callback callback) {
        return new Handler(looper, callback);
    }

    public static Looper a() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            return myLooper;
        }
        return Looper.getMainLooper();
    }

    public static ExecutorService a(String str) {
        return Executors.newSingleThreadExecutor(new -$$Lambda$beg$cSrwJ54nOW_Ka_M6Po5QF0FWhZY(str));
    }

    public static void a(defpackage.bbo r0) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:beg.a(bbo):void. bs: []
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
        if (r0 == 0) goto L_0x0007;
    L_0x0002:
        r0.c();	 Catch:{ IOException -> 0x0006 }
        goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: beg.a(bbo):void");
    }

    public static void a(java.io.Closeable r0) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:beg.a(java.io.Closeable):void. bs: []
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
        throw new UnsupportedOperationException("Method not decompiled: beg.a(java.io.Closeable):void");
    }

    public static boolean a(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void a(Parcel parcel, boolean z) {
        parcel.writeInt(z);
    }

    public static java.lang.String b(java.lang.String r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:beg.b(java.lang.String):java.lang.String. bs: []
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
        if (r1 != 0) goto L_0x0004;
    L_0x0002:
        r1 = 0;
        return r1;
    L_0x0004:
        r0 = new java.util.Locale;	 Catch:{ MissingResourceException -> 0x000e }
        r0.<init>(r1);	 Catch:{ MissingResourceException -> 0x000e }
        r0 = r0.getISO3Language();	 Catch:{ MissingResourceException -> 0x000e }
        return r0;
    L_0x000e:
        r1 = defpackage.beg.d(r1);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: beg.b(java.lang.String):java.lang.String");
    }

    public static String a(byte[] bArr) {
        return new String(bArr, Charset.forName("UTF-8"));
    }

    public static String a(byte[] bArr, int i, int i2) {
        return new String(bArr, i, i2, Charset.forName("UTF-8"));
    }

    public static byte[] c(String str) {
        return str.getBytes(Charset.forName("UTF-8"));
    }

    public static String[] a(String str, String str2) {
        return str.split(str2, -1);
    }

    public static String[] b(String str, String str2) {
        return str.split(str2, 2);
    }

    public static String d(String str) {
        return str == null ? str : str.toLowerCase(Locale.US);
    }

    public static String e(String str) {
        return str == null ? str : str.toUpperCase(Locale.US);
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static int a(int i, int i2) {
        return ((i + i2) - 1) / i2;
    }

    public static int a(int i, int i2, int i3) {
        return Math.max(i2, Math.min(i, i3));
    }

    public static long a(long j, long j2, long j3) {
        return Math.max(j2, Math.min(j, j3));
    }

    public static float a(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f, f3));
    }

    public static int a(long[] jArr, long j, boolean z) {
        int i;
        int binarySearch = Arrays.binarySearch(jArr, j);
        if (binarySearch < 0) {
            i = -(binarySearch + 2);
        } else {
            do {
                binarySearch--;
                if (binarySearch < 0) {
                    break;
                }
            } while (jArr[binarySearch] == j);
            i = binarySearch + 1;
        }
        return z ? Math.max(0, i) : i;
    }

    public static <T extends Comparable<? super T>> int a(List<? extends Comparable<? super T>> list, T t, boolean z) {
        int i;
        int binarySearch = Collections.binarySearch(list, t);
        if (binarySearch < 0) {
            i = -(binarySearch + 2);
        } else {
            do {
                binarySearch--;
                if (binarySearch < 0) {
                    break;
                }
            } while (((Comparable) list.get(binarySearch)).compareTo(t) == 0);
            i = binarySearch + 1;
        }
        return z ? Math.max(0, i) : i;
    }

    public static int a(long[] jArr, long j) {
        int binarySearch = Arrays.binarySearch(jArr, j);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        do {
            binarySearch++;
            if (binarySearch >= jArr.length) {
                break;
            }
        } while (jArr[binarySearch] == j);
        return binarySearch;
    }

    public static long f(String str) throws y {
        Matcher matcher = g.matcher(str);
        if (matcher.matches()) {
            int i = 0;
            if (!(matcher.group(9) == null || matcher.group(9).equalsIgnoreCase("Z"))) {
                i = (Integer.parseInt(matcher.group(12)) * 60) + Integer.parseInt(matcher.group(13));
                if ("-".equals(matcher.group(11))) {
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
            throw new y("Invalid date/time format: ".concat(String.valueOf(str)));
        }
    }

    public static long b(long j, long j2, long j3) {
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

    public static long a(long j, float f) {
        if (f == 1.0f) {
            return j;
        }
        double d = (double) j;
        double d2 = (double) f;
        Double.isNaN(d);
        Double.isNaN(d2);
        return Math.round(d * d2);
    }

    public static long b(long j, float f) {
        if (f == 1.0f) {
            return j;
        }
        double d = (double) j;
        double d2 = (double) f;
        Double.isNaN(d);
        Double.isNaN(d2);
        return Math.round(d / d2);
    }

    public static int[] a(List<Integer> list) {
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

    public static int g(String str) {
        int length = str.length();
        bcz.a(length <= 4);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i << 8) | str.charAt(i2);
        }
        return i;
    }

    public static byte[] h(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public static java.lang.String a(android.content.Context r2, java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:beg.a(android.content.Context, java.lang.String):java.lang.String. bs: []
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
        r2 = ") ExoPlayerLib/2.9.3";
        r0.append(r2);
        r2 = r0.toString();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: beg.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static String a(String str, int i) {
        String[] i2 = beg.i(str);
        if (i2.length == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : i2) {
            if (i == bdn.i(str2)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str2);
            }
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.toString();
        }
        return null;
    }

    public static String[] i(String str) {
        if (TextUtils.isEmpty(str)) {
            return new String[0];
        }
        return str.trim().split("(\\s*,\\s*)", -1);
    }

    public static int d(int i) {
        switch (i) {
            case 1:
                return 4;
            case 2:
                return 12;
            case 3:
                return 28;
            case 4:
                return 204;
            case 5:
                return 220;
            case 6:
                return 252;
            case 7:
                return 1276;
            case 8:
                return (a < 23 && a < 21) ? 0 : 6396;
            default:
                return 0;
        }
    }

    public static int b(int i, int i2) {
        if (i == Integer.MIN_VALUE) {
            return i2 * 3;
        }
        if (i != 1073741824) {
            switch (i) {
                case 2:
                    return i2 * 2;
                case 3:
                    return i2;
                case 4:
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return i2 * 4;
    }

    public static int f(int i) {
        switch (i) {
            case 0:
                return 16777216;
            case 1:
                return 3538944;
            case 2:
                return 13107200;
            case 3:
                return 131072;
            case 4:
                return 131072;
            case 5:
                return 131072;
            case 6:
                return 0;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int b(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            i2 = j[((i2 >>> 24) ^ (bArr[i3] & 255)) & 255] ^ (i2 << 8);
        }
        return i2;
    }

    public static int a(android.content.Context r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:beg.a(android.content.Context):int. bs: []
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
        r0 = 0;
        if (r3 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = "connectivity";	 Catch:{ SecurityException -> 0x003d }
        r3 = r3.getSystemService(r1);	 Catch:{ SecurityException -> 0x003d }
        r3 = (android.net.ConnectivityManager) r3;	 Catch:{ SecurityException -> 0x003d }
        if (r3 != 0) goto L_0x000f;	 Catch:{ SecurityException -> 0x003d }
    L_0x000e:
        return r0;	 Catch:{ SecurityException -> 0x003d }
    L_0x000f:
        r3 = r3.getActiveNetworkInfo();	 Catch:{ SecurityException -> 0x003d }
        if (r3 == 0) goto L_0x003b;
    L_0x0015:
        r0 = r3.isConnected();
        if (r0 != 0) goto L_0x001c;
    L_0x001b:
        goto L_0x003b;
    L_0x001c:
        r0 = r3.getType();
        r1 = 5;
        r2 = 2;
        switch(r0) {
            case 0: goto L_0x002c;
            case 1: goto L_0x002b;
            case 2: goto L_0x0025;
            case 3: goto L_0x0025;
            case 4: goto L_0x002c;
            case 5: goto L_0x002c;
            case 6: goto L_0x002a;
            case 7: goto L_0x0025;
            case 8: goto L_0x0025;
            case 9: goto L_0x0028;
            default: goto L_0x0025;
        };
    L_0x0025:
        r3 = 8;
        return r3;
    L_0x0028:
        r3 = 7;
        return r3;
    L_0x002a:
        return r1;
    L_0x002b:
        return r2;
    L_0x002c:
        r3 = r3.getSubtype();
        switch(r3) {
            case 1: goto L_0x0039;
            case 2: goto L_0x0039;
            case 3: goto L_0x0037;
            case 4: goto L_0x0037;
            case 5: goto L_0x0037;
            case 6: goto L_0x0037;
            case 7: goto L_0x0037;
            case 8: goto L_0x0037;
            case 9: goto L_0x0037;
            case 10: goto L_0x0037;
            case 11: goto L_0x0037;
            case 12: goto L_0x0037;
            case 13: goto L_0x0036;
            case 14: goto L_0x0037;
            case 15: goto L_0x0037;
            case 16: goto L_0x0033;
            case 17: goto L_0x0037;
            case 18: goto L_0x0035;
            default: goto L_0x0033;
        };
    L_0x0033:
        r3 = 6;
        return r3;
    L_0x0035:
        return r2;
    L_0x0036:
        return r1;
    L_0x0037:
        r3 = 4;
        return r3;
    L_0x0039:
        r3 = 3;
        return r3;
    L_0x003b:
        r3 = 1;
        return r3;
    L_0x003d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: beg.a(android.content.Context):int");
    }

    public static String b() {
        return beg.e(Locale.getDefault().getCountry());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(bdt bdt, bdt bdt2, Inflater inflater) {
        if (bdt.b() <= 0) {
            return false;
        }
        byte[] bArr = bdt2.a;
        if (bArr.length < bdt.b()) {
            bArr = new byte[(bdt.b() * 2)];
        }
        if (inflater == null) {
            inflater = new Inflater();
        }
        inflater.setInput(bdt.a, bdt.d(), bdt.b());
        int i = 0;
        while (true) {
            i += inflater.inflate(bArr, i, bArr.length - i);
            if (inflater.finished()) {
                bdt2.a(bArr, i);
                inflater.reset();
                return true;
            }
            try {
                if (inflater.needsDictionary() || inflater.needsInput()) {
                    inflater.reset();
                } else if (i == bArr.length) {
                    bArr = Arrays.copyOf(bArr, bArr.length * 2);
                }
            } catch (Throwable th) {
                inflater.reset();
            }
        }
        inflater.reset();
        return false;
    }
}
