package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acnz;
import defpackage.acob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public final class CollectionsKt {
    public static final <K> Map<K, Integer> mapToIndex(Iterable<? extends K> iterable) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (Object put : iterable) {
            linkedHashMap.put(put, Integer.valueOf(i));
            i++;
        }
        return linkedHashMap;
    }

    public static final <T> void addIfNotNull(Collection<T> collection, T t) {
        if (t != null) {
            collection.add(t);
        }
    }

    public static final <K, V> HashMap<K, V> newHashMapWithExpectedSize(int i) {
        return new HashMap(capacity(i));
    }

    public static final <E> HashSet<E> newHashSetWithExpectedSize(int i) {
        return new HashSet(capacity(i));
    }

    public static final <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int i) {
        return new LinkedHashSet(capacity(i));
    }

    private static final int capacity(int i) {
        return i < 3 ? 3 : (i + (i / 3)) + 1;
    }

    public static final <T> List<T> compact(ArrayList<T> arrayList) {
        switch (arrayList.size()) {
            case 0:
                return acob.a;
            case 1:
                return Collections.singletonList(acnz.e((List) arrayList));
            default:
                arrayList.trimToSize();
                return arrayList;
        }
    }
}
