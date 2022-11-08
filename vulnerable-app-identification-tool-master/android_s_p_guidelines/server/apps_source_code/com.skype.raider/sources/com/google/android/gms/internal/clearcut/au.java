package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class au extends e<String> implements av, RandomAccess {
    private static final au a;
    private static final av b = a;
    private final List<Object> c;

    static {
        e auVar = new au();
        a = auVar;
        auVar.b();
    }

    public au() {
        this(10);
    }

    public au(int i) {
        this(new ArrayList(i));
    }

    private au(ArrayList<Object> arrayList) {
        this.c = arrayList;
    }

    private static String a(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof h ? ((h) obj).b() : aj.b((byte[]) obj);
    }

    public final Object a(int i) {
        return this.c.get(i);
    }

    public final /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        c();
        this.c.add(i, str);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        Collection collection2;
        c();
        if (collection2 instanceof av) {
            collection2 = ((av) collection2).d();
        }
        boolean addAll = this.c.addAll(i, collection2);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final /* synthetic */ am b(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.c);
        return new au(arrayList);
    }

    public final void clear() {
        c();
        this.c.clear();
        this.modCount++;
    }

    public final List<?> d() {
        return Collections.unmodifiableList(this.c);
    }

    public final av e() {
        return a() ? new cu(this) : this;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.c.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        String b;
        if (obj instanceof h) {
            h hVar = (h) obj;
            b = hVar.b();
            if (hVar.c()) {
                this.c.set(i, b);
            }
            return b;
        }
        byte[] bArr = (byte[]) obj;
        b = aj.b(bArr);
        if (aj.a(bArr)) {
            this.c.set(i, b);
        }
        return b;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        c();
        Object remove = this.c.remove(i);
        this.modCount++;
        return a(remove);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        c();
        return a(this.c.set(i, str));
    }

    public final int size() {
        return this.c.size();
    }
}
