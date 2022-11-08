package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.List;

final class cb {
    private static final Class<?> a = d();
    private static final cr<?, ?> b = a(false);
    private static final cr<?, ?> c = a(true);
    private static final cr<?, ?> d = new ct();

    static int a(int i, Object obj, bz bzVar) {
        return obj instanceof at ? s.a(i, (at) obj) : s.b(i, (bk) obj, bzVar);
    }

    static int a(int i, List<Long> list) {
        return list.size() == 0 ? 0 : a((List) list) + (list.size() * s.l(i));
    }

    static int a(int i, List<?> list, bz bzVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int l = s.l(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            i2++;
            l = (obj instanceof at ? s.a((at) obj) : s.a((bk) obj, bzVar)) + l;
        }
        return l;
    }

    static int a(List<Long> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        int d;
        if (list instanceof az) {
            az azVar = (az) list;
            i2 = 0;
            while (i2 < size) {
                d = s.d(azVar.a(i2)) + i;
                i2++;
                i = d;
            }
            return i;
        }
        i2 = 0;
        for (d = 0; d < size; d++) {
            i2 += s.d(((Long) list.get(d)).longValue());
        }
        return i2;
    }

    public static cr<?, ?> a() {
        return b;
    }

    private static cr<?, ?> a(boolean z) {
        try {
            Class e = e();
            if (e == null) {
                return null;
            }
            return (cr) e.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    public static void a(int i, List<String> list, dk dkVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.a(i, (List) list);
        }
    }

    public static void a(int i, List<?> list, dk dkVar, bz bzVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.a(i, (List) list, bzVar);
        }
    }

    public static void a(int i, List<Double> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.g(i, list, z);
        }
    }

    static <T> void a(bf bfVar, T t, T t2, long j) {
        cx.a((Object) t, j, bfVar.a(cx.f(t, j), cx.f(t2, j)));
    }

    static <T, UT, UB> void a(cr<UT, UB> crVar, T t, T t2) {
        crVar.a((Object) t, crVar.b(crVar.a(t), crVar.a(t2)));
    }

    static <T, FT extends ac<FT>> void a(w<FT> wVar, T t, T t2) {
        aa a = wVar.a((Object) t2);
        if (!a.b()) {
            wVar.b(t).a(a);
        }
    }

    public static void a(Class<?> cls) {
        if (!ah.class.isAssignableFrom(cls) && a != null && !a.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static boolean a(int i, int i2, int i3) {
        return i2 < 40 || ((((long) i2) - ((long) i)) + 1) + 9 <= ((2 * ((long) i3)) + 3) + ((((long) i3) + 3) * 3);
    }

    static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static int b(int i, List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * s.l(i)) + b(list);
    }

    static int b(int i, List<bk> list, bz bzVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += s.c(i, (bk) list.get(i3), bzVar);
        }
        return i2;
    }

    static int b(List<Long> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        int e;
        if (list instanceof az) {
            az azVar = (az) list;
            i2 = 0;
            while (i2 < size) {
                e = s.e(azVar.a(i2)) + i;
                i2++;
                i = e;
            }
            return i;
        }
        i2 = 0;
        for (e = 0; e < size; e++) {
            i2 += s.e(((Long) list.get(e)).longValue());
        }
        return i2;
    }

    public static cr<?, ?> b() {
        return c;
    }

    public static void b(int i, List<h> list, dk dkVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.b(i, (List) list);
        }
    }

    public static void b(int i, List<?> list, dk dkVar, bz bzVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.b(i, (List) list, bzVar);
        }
    }

    public static void b(int i, List<Float> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.f(i, list, z);
        }
    }

    static int c(int i, List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * s.l(i)) + c(list);
    }

    static int c(List<Long> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        int f;
        if (list instanceof az) {
            az azVar = (az) list;
            i2 = 0;
            while (i2 < size) {
                f = s.f(azVar.a(i2)) + i;
                i2++;
                i = f;
            }
            return i;
        }
        i2 = 0;
        for (f = 0; f < size; f++) {
            i2 += s.f(((Long) list.get(f)).longValue());
        }
        return i2;
    }

    public static cr<?, ?> c() {
        return d;
    }

    public static void c(int i, List<Long> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.c(i, list, z);
        }
    }

    static int d(int i, List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * s.l(i)) + d(list);
    }

    static int d(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        int p;
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i2 = 0;
            while (i2 < size) {
                p = s.p(aiVar.a(i2)) + i;
                i2++;
                i = p;
            }
            return i;
        }
        i2 = 0;
        for (p = 0; p < size; p++) {
            i2 += s.p(((Integer) list.get(p)).intValue());
        }
        return i2;
    }

    private static Class<?> d() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    public static void d(int i, List<Long> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.d(i, list, z);
        }
    }

    static int e(int i, List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * s.l(i)) + e(list);
    }

    static int e(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        int m;
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i2 = 0;
            while (i2 < size) {
                m = s.m(aiVar.a(i2)) + i;
                i2++;
                i = m;
            }
            return i;
        }
        i2 = 0;
        for (m = 0; m < size; m++) {
            i2 += s.m(((Integer) list.get(m)).intValue());
        }
        return i2;
    }

    private static Class<?> e() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    public static void e(int i, List<Long> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.n(i, list, z);
        }
    }

    static int f(int i, List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * s.l(i)) + f(list);
    }

    static int f(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        int n;
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i2 = 0;
            while (i2 < size) {
                n = s.n(aiVar.a(i2)) + i;
                i2++;
                i = n;
            }
            return i;
        }
        i2 = 0;
        for (n = 0; n < size; n++) {
            i2 += s.n(((Integer) list.get(n)).intValue());
        }
        return i2;
    }

    public static void f(int i, List<Long> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.e(i, list, z);
        }
    }

    static int g(int i, List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * s.l(i)) + g(list);
    }

    static int g(List<Integer> list) {
        int i = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2;
        int o;
        if (list instanceof ai) {
            ai aiVar = (ai) list;
            i2 = 0;
            while (i2 < size) {
                o = s.o(aiVar.a(i2)) + i;
                i2++;
                i = o;
            }
            return i;
        }
        i2 = 0;
        for (o = 0; o < size; o++) {
            i2 += s.o(((Integer) list.get(o)).intValue());
        }
        return i2;
    }

    public static void g(int i, List<Long> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.l(i, list, z);
        }
    }

    static int h(int i, List<?> list) {
        int size = list.size();
        return size == 0 ? 0 : size * s.e(i);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    public static void h(int i, List<Integer> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.a(i, (List) list, z);
        }
    }

    static int i(int i, List<?> list) {
        int size = list.size();
        return size == 0 ? 0 : size * s.g(i);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    public static void i(int i, List<Integer> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.j(i, list, z);
        }
    }

    static int j(int i, List<?> list) {
        int size = list.size();
        return size == 0 ? 0 : size * s.k(i);
    }

    static int j(List<?> list) {
        return list.size();
    }

    public static void j(int i, List<Integer> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.m(i, list, z);
        }
    }

    static int k(int i, List<?> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int l = s.l(i) * size;
        int i2;
        Object a;
        if (list instanceof av) {
            av avVar = (av) list;
            i2 = 0;
            while (i2 < size) {
                a = avVar.a(i2);
                i2++;
                l = (a instanceof h ? s.b((h) a) : s.b((String) a)) + l;
            }
            return l;
        }
        i2 = 0;
        while (i2 < size) {
            a = list.get(i2);
            i2++;
            l = (a instanceof h ? s.b((h) a) : s.b((String) a)) + l;
        }
        return l;
    }

    public static void k(int i, List<Integer> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.b(i, (List) list, z);
        }
    }

    static int l(int i, List<h> list) {
        int i2 = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int l = size * s.l(i);
        while (true) {
            size = i2;
            if (size >= list.size()) {
                return l;
            }
            l += s.b((h) list.get(size));
            i2 = size + 1;
        }
    }

    public static void l(int i, List<Integer> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.k(i, list, z);
        }
    }

    public static void m(int i, List<Integer> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.h(i, list, z);
        }
    }

    public static void n(int i, List<Boolean> list, dk dkVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            dkVar.i(i, list, z);
        }
    }
}
