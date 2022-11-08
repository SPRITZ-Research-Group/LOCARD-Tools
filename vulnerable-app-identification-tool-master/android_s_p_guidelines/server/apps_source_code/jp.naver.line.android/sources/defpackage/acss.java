package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: acss */
public class acss {
    private static <T extends Throwable> T a(T t) {
        return acry.a((Throwable) t, acss.class.getName());
    }

    private static void a(Object obj, String str) {
        String name = obj == null ? "null" : obj.getClass().getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(" cannot be cast to ");
        stringBuilder.append(str);
        throw ((ClassCastException) acss.a(new ClassCastException(stringBuilder.toString())));
    }

    public static Iterator a(Object obj) {
        if ((obj instanceof acst) && !(obj instanceof acsw)) {
            acss.a(obj, "kotlin.collections.MutableIterator");
        }
        return acss.f(obj);
    }

    private static Iterator f(Object obj) {
        try {
            return (Iterator) obj;
        } catch (Throwable e) {
            throw ((ClassCastException) acss.a(e));
        }
    }

    public static Iterable b(Object obj) {
        if ((obj instanceof acst) && !(obj instanceof acsv)) {
            acss.a(obj, "kotlin.collections.MutableIterable");
        }
        return acss.g(obj);
    }

    private static Iterable g(Object obj) {
        try {
            return (Iterable) obj;
        } catch (Throwable e) {
            throw ((ClassCastException) acss.a(e));
        }
    }

    public static Collection c(Object obj) {
        if ((obj instanceof acst) && !(obj instanceof acsu)) {
            acss.a(obj, "kotlin.collections.MutableCollection");
        }
        return acss.h(obj);
    }

    private static Collection h(Object obj) {
        try {
            return (Collection) obj;
        } catch (Throwable e) {
            throw ((ClassCastException) acss.a(e));
        }
    }

    public static List d(Object obj) {
        if ((obj instanceof acst) && !(obj instanceof acsx)) {
            acss.a(obj, "kotlin.collections.MutableList");
        }
        return acss.i(obj);
    }

    private static List i(Object obj) {
        try {
            return (List) obj;
        } catch (Throwable e) {
            throw ((ClassCastException) acss.a(e));
        }
    }

    public static Set e(Object obj) {
        if ((obj instanceof acst) && !(obj instanceof acsy)) {
            acss.a(obj, "kotlin.collections.MutableSet");
        }
        return acss.j(obj);
    }

    private static Set j(Object obj) {
        try {
            return (Set) obj;
        } catch (Throwable e) {
            throw ((ClassCastException) acss.a(e));
        }
    }
}
