package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class fw {
    public static final Pattern a = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern b = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final Uri c = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri d = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    private static final AtomicBoolean e = new AtomicBoolean();
    private static HashMap<String, String> f;
    private static final HashMap<String, Boolean> g = new HashMap();
    private static final HashMap<String, Integer> h = new HashMap();
    private static final HashMap<String, Long> i = new HashMap();
    private static final HashMap<String, Float> j = new HashMap();
    private static Object k;
    private static boolean l;
    private static String[] m = new String[0];

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T a(HashMap<String, T> hashMap, String str, T t) {
        synchronized (fw.class) {
            if (hashMap.containsKey(str)) {
                T t2 = hashMap.get(str);
                if (t2 == null) {
                    t2 = t;
                }
            } else {
                return null;
            }
        }
    }

    public static String a(ContentResolver contentResolver, String str) {
        String str2 = null;
        synchronized (fw.class) {
            a(contentResolver);
            Object obj = k;
            String str3;
            if (f.containsKey(str)) {
                str3 = (String) f.get(str);
                if (str3 != null) {
                    str2 = str3;
                }
            } else {
                String[] strArr = m;
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    if (str.startsWith(strArr[i])) {
                        if (!l || f.isEmpty()) {
                            f.putAll(a(contentResolver, m));
                            l = true;
                            if (f.containsKey(str)) {
                                str3 = (String) f.get(str);
                                if (str3 != null) {
                                    str2 = str3;
                                }
                            }
                        }
                    } else {
                        i++;
                    }
                }
                Cursor query = contentResolver.query(c, null, null, new String[]{str}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            str3 = query.getString(1);
                            if (str3 != null && str3.equals(null)) {
                                str3 = null;
                            }
                            a(obj, str, str3);
                            if (str3 != null) {
                                str2 = str3;
                            }
                            if (query != null) {
                                query.close();
                            }
                        }
                    } catch (Throwable th) {
                        if (query != null) {
                            query.close();
                        }
                    }
                }
                a(obj, str, null);
                if (query != null) {
                    query.close();
                }
            }
        }
        return str2;
    }

    private static Map<String, String> a(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(d, null, null, strArr, null);
        Map<String, String> treeMap = new TreeMap();
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    treeMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return treeMap;
    }

    private static void a(ContentResolver contentResolver) {
        if (f == null) {
            e.set(false);
            f = new HashMap();
            k = new Object();
            l = false;
            contentResolver.registerContentObserver(c, true, new fx());
        } else if (e.getAndSet(false)) {
            f.clear();
            g.clear();
            h.clear();
            i.clear();
            j.clear();
            k = new Object();
            l = false;
        }
    }

    private static void a(Object obj, String str, String str2) {
        synchronized (fw.class) {
            if (obj == k) {
                f.put(str, str2);
            }
        }
    }

    public static boolean a(ContentResolver contentResolver, String str, boolean z) {
        Object b = b(contentResolver);
        Object obj = (Boolean) a(g, str, Boolean.valueOf(z));
        if (obj != null) {
            return obj.booleanValue();
        }
        Object a = a(contentResolver, str);
        if (!(a == null || a.equals(""))) {
            if (a.matcher(a).matches()) {
                obj = Boolean.valueOf(true);
                z = true;
            } else if (b.matcher(a).matches()) {
                obj = Boolean.valueOf(false);
                z = false;
            } else {
                new StringBuilder("attempt to read gservices key ").append(str).append(" (value \"").append(a).append("\") as boolean");
            }
        }
        HashMap hashMap = g;
        synchronized (fw.class) {
            if (b == k) {
                hashMap.put(str, obj);
                f.remove(str);
            }
        }
        return z;
    }

    private static Object b(ContentResolver contentResolver) {
        Object obj;
        synchronized (fw.class) {
            a(contentResolver);
            obj = k;
        }
        return obj;
    }
}
