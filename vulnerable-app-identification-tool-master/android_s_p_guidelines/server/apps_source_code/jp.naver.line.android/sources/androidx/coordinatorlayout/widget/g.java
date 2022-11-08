package androidx.coordinatorlayout.widget;

import defpackage.cg;
import defpackage.gp;
import defpackage.gq;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class g<T> {
    private final gp<ArrayList<T>> a = new gq(10);
    private final cg<T, ArrayList<T>> b = new cg();
    private final ArrayList<T> c = new ArrayList();
    private final HashSet<T> d = new HashSet();

    public final void a(T t) {
        if (!this.b.containsKey(t)) {
            this.b.put(t, null);
        }
    }

    public final boolean b(T t) {
        return this.b.containsKey(t);
    }

    public final void a(T t, T t2) {
        if (this.b.containsKey(t) && this.b.containsKey(t2)) {
            ArrayList arrayList = (ArrayList) this.b.get(t);
            if (arrayList == null) {
                arrayList = (ArrayList) this.a.a();
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                this.b.put(t, arrayList);
            }
            arrayList.add(t2);
            return;
        }
        throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
    }

    public final List c(T t) {
        return (List) this.b.get(t);
    }

    public final List<T> d(T t) {
        int size = this.b.size();
        List<T> list = null;
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.b.c(i);
            if (arrayList != null && arrayList.contains(t)) {
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(this.b.b(i));
            }
        }
        return list;
    }

    public final boolean e(T t) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.b.c(i);
            if (arrayList != null && arrayList.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public final void a() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.b.c(i);
            if (arrayList != null) {
                arrayList.clear();
                this.a.a(arrayList);
            }
        }
        this.b.clear();
    }

    public final ArrayList<T> b() {
        this.c.clear();
        this.d.clear();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            a(this.b.b(i), this.c, this.d);
        }
        return this.c;
    }

    private void a(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (!arrayList.contains(t)) {
            if (hashSet.contains(t)) {
                throw new RuntimeException("This graph contains cyclic dependencies");
            }
            hashSet.add(t);
            ArrayList arrayList2 = (ArrayList) this.b.get(t);
            if (arrayList2 != null) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    a(arrayList2.get(i), arrayList, hashSet);
                }
            }
            hashSet.remove(t);
            arrayList.add(t);
        }
    }
}
