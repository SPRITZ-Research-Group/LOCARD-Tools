package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.d;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import com.skype.Defines;
import com.skype.camera.imagefilter.ImageFilterManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

public final class ew extends ct {
    private static final String[] a = new String[]{"firebase_", "google_", "ga_"};
    private SecureRandom b;
    private final AtomicLong c = new AtomicLong(0);
    private int d;
    private Integer e = null;

    ew(bx bxVar) {
        super(bxVar);
    }

    public static fk a(fj fjVar, String str) {
        for (fk fkVar : fjVar.c) {
            if (fkVar.c.equals(str)) {
                return fkVar;
            }
        }
        return null;
    }

    private static Object a(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Float ? Double.valueOf(((Float) obj).doubleValue()) : ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) ? a(String.valueOf(obj), i, z) : null;
        } else {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
    }

    public static Object a(String str, Object obj) {
        int i = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
        if ("_ev".equals(str)) {
            return a((int) Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, obj, true);
        }
        if (!g(str)) {
            i = 100;
        }
        return a(i, obj, false);
    }

    public static String a(String str, int i, boolean z) {
        return str.codePointCount(0, str.length()) > i ? z ? String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...") : null : str;
    }

    @Nullable
    public static String a(String str, String[] strArr, String[] strArr2) {
        ab.a((Object) strArr);
        ab.a((Object) strArr2);
        int min = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < min; i++) {
            if (b(str, strArr[i])) {
                return strArr2[i];
            }
        }
        return null;
    }

    private static void a(Bundle bundle, Object obj) {
        ab.a((Object) bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    public static boolean a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0);
            return serviceInfo != null && serviceInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean a(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    private static boolean a(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    @WorkerThread
    static boolean a(zzeu zzeu, zzdz zzdz) {
        ab.a((Object) zzeu);
        ab.a((Object) zzdz);
        return !TextUtils.isEmpty(zzdz.b);
    }

    static boolean a(String str) {
        ab.a(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    private final boolean a(String str, String str2, int i, Object obj, boolean z) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            String valueOf = String.valueOf(obj);
            if (valueOf.codePointCount(0, valueOf.length()) <= i) {
                return true;
            }
            q().y().a("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
            return false;
        } else if ((obj instanceof Bundle) && z) {
            return true;
        } else {
            int length;
            int i2;
            Object obj2;
            if ((obj instanceof Parcelable[]) && z) {
                Parcelable[] parcelableArr = (Parcelable[]) obj;
                length = parcelableArr.length;
                i2 = 0;
                while (i2 < length) {
                    obj2 = parcelableArr[i2];
                    if (obj2 instanceof Bundle) {
                        i2++;
                    } else {
                        q().y().a("All Parcelable[] elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof ArrayList) || !z) {
                return false;
            } else {
                ArrayList arrayList = (ArrayList) obj;
                length = arrayList.size();
                i2 = 0;
                while (i2 < length) {
                    obj2 = arrayList.get(i2);
                    i2++;
                    if (!(obj2 instanceof Bundle)) {
                        q().y().a("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            }
        }
    }

    public static boolean a(long[] jArr, int i) {
        return i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    static byte[] a(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            return marshall;
        } finally {
            obtain.recycle();
        }
    }

    public static long[] a(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i << 6) + i2 < bitSet.length()) {
                if (bitSet.get((i << 6) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    public static Bundle[] a(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        } else if (obj instanceof Parcelable[]) {
            return (Bundle[]) Arrays.copyOf((Parcelable[]) obj, ((Parcelable[]) obj).length, Bundle[].class);
        } else {
            if (!(obj instanceof ArrayList)) {
                return null;
            }
            ArrayList arrayList = (ArrayList) obj;
            return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    static fk[] a(fk[] fkVarArr, String str, Object obj) {
        for (fk fkVar : fkVarArr) {
            if (str.equals(fkVar.c)) {
                fkVar.e = null;
                fkVar.d = null;
                fkVar.f = null;
                if (obj instanceof Long) {
                    fkVar.e = (Long) obj;
                    return fkVarArr;
                } else if (obj instanceof String) {
                    fkVar.d = (String) obj;
                    return fkVarArr;
                } else if (!(obj instanceof Double)) {
                    return fkVarArr;
                } else {
                    fkVar.f = (Double) obj;
                    return fkVarArr;
                }
            }
        }
        Object obj2 = new fk[(fkVarArr.length + 1)];
        System.arraycopy(fkVarArr, 0, obj2, 0, fkVarArr.length);
        fk fkVar2 = new fk();
        fkVar2.c = str;
        if (obj instanceof Long) {
            fkVar2.e = (Long) obj;
        } else if (obj instanceof String) {
            fkVar2.d = (String) obj;
        } else if (obj instanceof Double) {
            fkVar2.f = (Double) obj;
        }
        obj2[fkVarArr.length] = fkVar2;
        return obj2;
    }

    public static Object b(fj fjVar, String str) {
        fk a = a(fjVar, str);
        if (a != null) {
            if (a.d != null) {
                return a.d;
            }
            if (a.e != null) {
                return a.e;
            }
            if (a.f != null) {
                return a.f;
            }
        }
        return null;
    }

    public static Object b(Object obj) {
        Throwable th;
        if (obj == null) {
            return null;
        }
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                try {
                    Object readObject = objectInputStream.readObject();
                    try {
                        objectOutputStream.close();
                        objectInputStream.close();
                        return readObject;
                    } catch (IOException e) {
                        return null;
                    } catch (ClassNotFoundException e2) {
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                objectInputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            objectInputStream = null;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
    }

    public static boolean b(String str, String str2) {
        return (str == null && str2 == null) ? true : str == null ? false : str.equals(str2);
    }

    @VisibleForTesting
    static long c(byte[] bArr) {
        long j = null;
        ab.a((Object) bArr);
        ab.a(bArr.length > 0);
        long j2 = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j2 += (((long) bArr[length]) & 255) << j;
            j += 8;
            length--;
        }
        return j2;
    }

    public static Object c(String str, Object obj) {
        return "_ldl".equals(str) ? a(k(str), obj, true) : a(k(str), obj, false);
    }

    @VisibleForTesting
    private final boolean c(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo b = c.a(context).b(str, 64);
            if (!(b == null || b.signatures == null || b.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(b.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            q().v().a("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            q().v().a("Package name not found", e2);
        }
        return true;
    }

    private final boolean c(String str, String str2) {
        if (str2 == null) {
            q().v().a("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            q().v().a("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        q().v().a("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            q().v().a("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    static MessageDigest e(String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 2) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i = i2 + 1;
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    public static boolean g(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean i(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    @WorkerThread
    static boolean j(String str) {
        ab.a(str);
        boolean z = true;
        switch (str.hashCode()) {
            case 94660:
                if (str.equals("_in")) {
                    z = false;
                    break;
                }
                break;
            case 95025:
                if (str.equals("_ug")) {
                    z = true;
                    break;
                }
                break;
            case 95027:
                if (str.equals("_ui")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
                return true;
            default:
                return false;
        }
    }

    private static int k(String str) {
        return "_ldl".equals(str) ? 2048 : "_id".equals(str) ? Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE : 36;
    }

    public final Bundle a(@NonNull Uri uri) {
        Bundle bundle = null;
        if (uri != null) {
            try {
                Object queryParameter;
                Object queryParameter2;
                Object queryParameter3;
                Object queryParameter4;
                if (uri.isHierarchical()) {
                    queryParameter = uri.getQueryParameter("utm_campaign");
                    queryParameter2 = uri.getQueryParameter("utm_source");
                    queryParameter3 = uri.getQueryParameter("utm_medium");
                    queryParameter4 = uri.getQueryParameter("gclid");
                } else {
                    queryParameter4 = null;
                    queryParameter3 = null;
                    queryParameter2 = null;
                    queryParameter = null;
                }
                if (!(TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3) && TextUtils.isEmpty(queryParameter4))) {
                    bundle = new Bundle();
                    if (!TextUtils.isEmpty(queryParameter)) {
                        bundle.putString("campaign", queryParameter);
                    }
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        bundle.putString(ImageFilterManager.PROP_SOURCE, queryParameter2);
                    }
                    if (!TextUtils.isEmpty(queryParameter3)) {
                        bundle.putString(Constants.MEDIUM, queryParameter3);
                    }
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("gclid", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("utm_term");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("term", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("utm_content");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("content", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("aclid");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("aclid", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("cp1");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("cp1", queryParameter4);
                    }
                    queryParameter4 = uri.getQueryParameter("anid");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("anid", queryParameter4);
                    }
                }
            } catch (UnsupportedOperationException e) {
                q().y().a("Install referrer url isn't a hierarchical URI", e);
            }
        }
        return bundle;
    }

    final Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object a = a(str, bundle.get(str));
                if (a == null) {
                    q().y().a("Param value can't be null", m().b(str));
                } else {
                    a(bundle2, str, a);
                }
            }
        }
        return bundle2;
    }

    public final Bundle a(String str, Bundle bundle, @Nullable List<String> list, boolean z, boolean z2) {
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        int i = 0;
        for (String str2 : bundle.keySet()) {
            int i2 = 0;
            if (list == null || !list.contains(str2)) {
                if (z) {
                    i2 = !a("event param", str2) ? 3 : !a("event param", null, str2) ? 14 : !a("event param", 40, str2) ? 3 : 0;
                }
                if (i2 == 0) {
                    i2 = !c("event param", str2) ? 3 : !a("event param", null, str2) ? 14 : !a("event param", 40, str2) ? 3 : 0;
                }
            }
            if (i2 != 0) {
                if (a(bundle2, i2)) {
                    bundle2.putString("_ev", a(str2, 40, true));
                    if (i2 == 3) {
                        a(bundle2, (Object) str2);
                    }
                }
                bundle2.remove(str2);
            } else {
                Object obj = bundle.get(str2);
                c();
                if (z2) {
                    Object obj2;
                    String str3 = "param";
                    if (obj instanceof Parcelable[]) {
                        i2 = ((Parcelable[]) obj).length;
                    } else if (obj instanceof ArrayList) {
                        i2 = ((ArrayList) obj).size();
                    } else {
                        obj2 = 1;
                        if (obj2 == null) {
                            i2 = 17;
                            if (i2 != 0 || "_ev".equals(str2)) {
                                if (a(str2)) {
                                    i2 = i + 1;
                                    if (i2 > 25) {
                                        q().v().a("Event can't contain more than 25 params", m().a(str), m().a(bundle));
                                        a(bundle2, 5);
                                        bundle2.remove(str2);
                                        i = i2;
                                    }
                                } else {
                                    i2 = i;
                                }
                                i = i2;
                            } else {
                                if (a(bundle2, i2)) {
                                    bundle2.putString("_ev", a(str2, 40, true));
                                    a(bundle2, bundle.get(str2));
                                }
                                bundle2.remove(str2);
                            }
                        }
                    }
                    if (i2 > Constants.ONE_SECOND) {
                        q().y().a("Parameter array is too long; discarded. Value kind, name, array length", str3, str2, Integer.valueOf(i2));
                        obj2 = null;
                    } else {
                        obj2 = 1;
                    }
                    if (obj2 == null) {
                        i2 = 17;
                        if (i2 != 0) {
                        }
                        if (a(str2)) {
                            i2 = i;
                        } else {
                            i2 = i + 1;
                            if (i2 > 25) {
                                q().v().a("Event can't contain more than 25 params", m().a(str), m().a(bundle));
                                a(bundle2, 5);
                                bundle2.remove(str2);
                                i = i2;
                            }
                        }
                        i = i2;
                    }
                }
                boolean a = ((s().f(f().w()) && g(str)) || g(str2)) ? a("param", str2, (int) Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, obj, z2) : a("param", str2, 100, obj, z2);
                i2 = a ? 0 : 4;
                if (i2 != 0) {
                }
                if (a(str2)) {
                    i2 = i + 1;
                    if (i2 > 25) {
                        q().v().a("Event can't contain more than 25 params", m().a(str), m().a(bundle));
                        a(bundle2, 5);
                        bundle2.remove(str2);
                        i = i2;
                    }
                } else {
                    i2 = i;
                }
                i = i2;
            }
        }
        return bundle2;
    }

    final <T extends Parcelable> T a(byte[] bArr, Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        T t;
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            t = (Parcelable) creator.createFromParcel(obtain);
            return t;
        } catch (a e) {
            t = q().v();
            t.a("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    final zzeu a(String str, Bundle bundle, String str2, long j) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (b(str) != 0) {
            q().v().a("Invalid conditional property event name", m().c(str));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str2);
        return new zzeu(str, new zzer(a(a(str, bundle2, Collections.singletonList("_o"), false, false))), str2, j);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(int i, String str, String str2, int i2) {
        b(i, str, str2, i2);
    }

    public final void a(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                q().z().a("Not putting event parameter. Invalid value type. name, type", m().b(str), obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public final void a(fk fkVar, Object obj) {
        ab.a(obj);
        fkVar.d = null;
        fkVar.e = null;
        fkVar.f = null;
        if (obj instanceof String) {
            fkVar.d = (String) obj;
        } else if (obj instanceof Long) {
            fkVar.e = (Long) obj;
        } else if (obj instanceof Double) {
            fkVar.f = (Double) obj;
        } else {
            q().v().a("Ignoring invalid (type) event param value", obj);
        }
    }

    public final void a(fo foVar, Object obj) {
        ab.a(obj);
        foVar.e = null;
        foVar.f = null;
        foVar.g = null;
        if (obj instanceof String) {
            foVar.e = (String) obj;
        } else if (obj instanceof Long) {
            foVar.f = (Long) obj;
        } else if (obj instanceof Double) {
            foVar.g = (Double) obj;
        } else {
            q().v().a("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final boolean a(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(j().a() - j) > j2;
    }

    final boolean a(String str, int i, String str2) {
        if (str2 == null) {
            q().v().a("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            q().v().a("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    final boolean a(String str, String str2) {
        if (str2 == null) {
            q().v().a("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            q().v().a("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt)) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        q().v().a("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            q().v().a("Name must start with a letter. Type, name", str, str2);
            return false;
        }
    }

    final boolean a(String str, String[] strArr, String str2) {
        if (str2 == null) {
            q().v().a("Name is required and can't be null. Type", str);
            return false;
        }
        boolean z;
        ab.a((Object) str2);
        for (String startsWith : a) {
            if (str2.startsWith(startsWith)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            q().v().a("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            ab.a((Object) strArr);
            for (String startsWith2 : strArr) {
                if (b(str2, startsWith2)) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (z) {
                q().v().a("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    public final byte[] a(fl flVar) {
        try {
            byte[] bArr = new byte[flVar.d()];
            b a = b.a(bArr, bArr.length);
            flVar.a(a);
            a.a();
            return bArr;
        } catch (IOException e) {
            q().v().a("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    public final byte[] a(byte[] bArr) throws IOException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            q().v().a("Failed to gzip content", e);
            throw e;
        }
    }

    public final int b(String str) {
        return !c(EventsEntry.COLUMN_NAME_EVENT, str) ? 2 : !a(EventsEntry.COLUMN_NAME_EVENT, AppMeasurement.a.a, str) ? 13 : a(EventsEntry.COLUMN_NAME_EVENT, 40, str) ? 0 : 2;
    }

    public final int b(String str, Object obj) {
        return "_ldl".equals(str) ? a("user property referrer", str, k(str), obj, false) : a("user property", str, k(str), obj, false) ? 0 : 7;
    }

    @WorkerThread
    final long b(Context context, String str) {
        c();
        ab.a((Object) context);
        ab.a(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest e = e(Constants.MD5);
        if (e == null) {
            q().v().a("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!c(context, str)) {
                    PackageInfo b = c.a(context).b(k().getPackageName(), 64);
                    if (b.signatures != null && b.signatures.length > 0) {
                        return c(e.digest(b.signatures[0].toByteArray()));
                    }
                    q().y().a("Could not get signatures");
                    return -1;
                }
            } catch (NameNotFoundException e2) {
                q().v().a("Package name not found", e2);
            }
        }
        return 0;
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final void b(int i, String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        a(bundle, i);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(str, str2);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.q.i().a("auto", "_err", bundle);
    }

    public final byte[] b(byte[] bArr) throws IOException {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            q().v().a("Failed to ungzip content", e);
            throw e;
        }
    }

    public final int c(String str) {
        return !c("user property", str) ? 6 : !a("user property", d.a, str) ? 15 : a("user property", 24, str) ? 0 : 6;
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            q().v().a("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        }
        ab.a((Object) str);
        if (str.matches("^1:\\d+:android:[a-f0-9]+$")) {
            return true;
        }
        q().v().a("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
        return false;
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    @WorkerThread
    public final boolean f(String str) {
        c();
        if (c.a(k()).a(str) == 0) {
            return true;
        }
        q().B().a("Permission not granted", str);
        return false;
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    public final boolean h(String str) {
        return TextUtils.isEmpty(str) ? false : s().x().equals(str);
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ Context k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ar l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ at m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ ew n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ee o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ bs p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ av q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ bf r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    protected final boolean t() {
        return true;
    }

    @WorkerThread
    protected final void t_() {
        c();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                q().y().a("Utils falling back to Random for random id");
            }
        }
        this.c.set(nextLong);
    }

    public final long v() {
        long nextLong;
        if (this.c.get() == 0) {
            synchronized (this.c) {
                nextLong = new Random(System.nanoTime() ^ j().a()).nextLong();
                int i = this.d + 1;
                this.d = i;
                nextLong += (long) i;
            }
        } else {
            synchronized (this.c) {
                this.c.compareAndSet(-1, 1);
                nextLong = this.c.getAndIncrement();
            }
        }
        return nextLong;
    }

    @WorkerThread
    final SecureRandom w() {
        c();
        if (this.b == null) {
            this.b = new SecureRandom();
        }
        return this.b;
    }

    public final int x() {
        if (this.e == null) {
            this.e = Integer.valueOf(com.google.android.gms.common.e.b().b(k()) / Constants.ONE_SECOND);
        }
        return this.e.intValue();
    }
}
