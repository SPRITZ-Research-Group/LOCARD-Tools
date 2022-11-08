package androidx.transition;

import java.util.ArrayList;

final class ac {
    static <T> ArrayList<T> a(ArrayList<T> arrayList, T t) {
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (!arrayList.contains(t)) {
            arrayList.add(t);
        }
        return arrayList;
    }

    static <T> ArrayList<T> b(ArrayList<T> arrayList, T t) {
        if (arrayList == null) {
            return arrayList;
        }
        arrayList.remove(t);
        return arrayList.isEmpty() ? null : arrayList;
    }
}
