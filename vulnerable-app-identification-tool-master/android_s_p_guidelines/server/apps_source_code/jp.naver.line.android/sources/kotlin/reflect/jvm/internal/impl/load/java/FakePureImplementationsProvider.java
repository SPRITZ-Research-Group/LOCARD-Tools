package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.u;

public final class FakePureImplementationsProvider {
    public static final FakePureImplementationsProvider INSTANCE;
    private static final HashMap<FqName, FqName> pureImplementations = new HashMap();

    static {
        FakePureImplementationsProvider fakePureImplementationsProvider = new FakePureImplementationsProvider();
        INSTANCE = fakePureImplementationsProvider;
        fakePureImplementationsProvider.implementedWith(KotlinBuiltIns.FQ_NAMES.mutableList, fakePureImplementationsProvider.fqNameListOf("java.util.ArrayList", "java.util.LinkedList"));
        fakePureImplementationsProvider.implementedWith(KotlinBuiltIns.FQ_NAMES.mutableSet, fakePureImplementationsProvider.fqNameListOf("java.util.HashSet", "java.util.TreeSet", "java.util.LinkedHashSet"));
        fakePureImplementationsProvider.implementedWith(KotlinBuiltIns.FQ_NAMES.mutableMap, fakePureImplementationsProvider.fqNameListOf("java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.concurrent.ConcurrentSkipListMap"));
        fakePureImplementationsProvider.implementedWith(new FqName("java.util.function.Function"), fakePureImplementationsProvider.fqNameListOf("java.util.function.UnaryOperator"));
        fakePureImplementationsProvider.implementedWith(new FqName("java.util.function.BiFunction"), fakePureImplementationsProvider.fqNameListOf("java.util.function.BinaryOperator"));
    }

    private FakePureImplementationsProvider() {
    }

    public final FqName getPurelyImplementedInterface(FqName fqName) {
        return (FqName) pureImplementations.get(fqName);
    }

    private final void implementedWith(FqName fqName, List<FqName> list) {
        Map map = pureImplementations;
        for (FqName a : list) {
            m a2 = u.a(a, fqName);
            map.put(a2.a(), a2.b());
        }
    }

    private final List<FqName> fqNameListOf(String... strArr) {
        Collection arrayList = new ArrayList(strArr.length);
        for (String fqName : strArr) {
            arrayList.add(new FqName(fqName));
        }
        return (List) arrayList;
    }
}
