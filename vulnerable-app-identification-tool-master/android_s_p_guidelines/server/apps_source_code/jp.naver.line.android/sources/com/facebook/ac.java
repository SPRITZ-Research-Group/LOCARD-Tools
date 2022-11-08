package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class ac extends AbstractList<GraphRequest> {
    private static AtomicInteger a = new AtomicInteger();
    private Handler b;
    private List<GraphRequest> c;
    private int d;
    private final String e;
    private List<ad> f;
    private String g;

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        this.c.add(i, (GraphRequest) obj);
    }

    public final /* synthetic */ boolean add(Object obj) {
        return a((GraphRequest) obj);
    }

    public final /* synthetic */ Object get(int i) {
        return a(i);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        return (GraphRequest) this.c.set(i, (GraphRequest) obj);
    }

    public ac() {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = new ArrayList();
    }

    public ac(Collection<GraphRequest> collection) {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = new ArrayList(collection);
    }

    public ac(GraphRequest... graphRequestArr) {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = Arrays.asList(graphRequestArr);
    }

    public final int a() {
        return this.d;
    }

    public final void a(ad adVar) {
        if (!this.f.contains(adVar)) {
            this.f.add(adVar);
        }
    }

    public final boolean a(GraphRequest graphRequest) {
        return this.c.add(graphRequest);
    }

    public final void clear() {
        this.c.clear();
    }

    public final GraphRequest a(int i) {
        return (GraphRequest) this.c.get(i);
    }

    public final int size() {
        return this.c.size();
    }

    final String b() {
        return this.e;
    }

    final Handler c() {
        return this.b;
    }

    final void a(Handler handler) {
        this.b = handler;
    }

    final List<GraphRequest> d() {
        return this.c;
    }

    final List<ad> e() {
        return this.f;
    }

    public final String f() {
        return this.g;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        return (GraphRequest) this.c.remove(i);
    }
}
