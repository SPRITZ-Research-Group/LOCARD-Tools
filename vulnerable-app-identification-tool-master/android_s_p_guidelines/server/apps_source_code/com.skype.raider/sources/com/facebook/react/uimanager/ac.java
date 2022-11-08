package com.facebook.react.uimanager;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.facebook.react.common.g;

final class ac {
    private final SparseArray<w> a = new SparseArray();
    private final SparseBooleanArray b = new SparseBooleanArray();
    private final g c = new g();

    public final void a(w node) {
        int tag = node.A();
        this.a.put(tag, node);
        this.b.put(tag, true);
    }

    public final void a(int tag) {
        this.c.a();
        if (this.b.get(tag)) {
            this.a.remove(tag);
            this.b.delete(tag);
            return;
        }
        throw new f("View with tag " + tag + " is not registered as a root view");
    }

    public final void b(w node) {
        this.c.a();
        this.a.put(node.A(), node);
    }

    public final void b(int tag) {
        this.c.a();
        if (this.b.get(tag)) {
            throw new f("Trying to remove root node " + tag + " without using removeRootNode!");
        }
        this.a.remove(tag);
    }

    public final w c(int tag) {
        this.c.a();
        return (w) this.a.get(tag);
    }

    public final boolean d(int tag) {
        this.c.a();
        return this.b.get(tag);
    }

    public final int a() {
        this.c.a();
        return this.b.size();
    }

    public final int e(int index) {
        this.c.a();
        return this.b.keyAt(index);
    }
}
