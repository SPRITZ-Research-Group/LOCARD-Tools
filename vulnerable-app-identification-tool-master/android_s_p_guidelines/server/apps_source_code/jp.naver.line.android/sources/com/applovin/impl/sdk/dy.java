package com.applovin.impl.sdk;

import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class dy {
    private static final String[] a = new String[]{"paused", "saved_instance_state"};
    private static final String[] b = new String[]{"paused", "saved_instance_state", "stopped", "started"};
    private static final String[] c = new String[]{"paused", "stopped", "saved_instance_state", "started"};
    private static final String[] d = new String[]{"saved_instance_state", "paused", "stopped", "started"};
    private final AppLovinSdkImpl e;
    private final List<String> f = new ArrayList();
    private final AtomicBoolean g = new AtomicBoolean();
    private final AtomicBoolean h = new AtomicBoolean();
    private final AtomicBoolean i = new AtomicBoolean();
    private Date j;
    private Date k;

    dy(AppLovinSdkImpl appLovinSdkImpl) {
        this.e = appLovinSdkImpl;
    }

    private static boolean a(List<String> list, String[] strArr) {
        int size = list.size();
        int length = strArr.length;
        if (size == 0 || length == 0 || size < strArr.length) {
            return false;
        }
        size -= length;
        for (int i = size; i < length; i++) {
            if (!((String) list.get(i)).equals(strArr[i - size])) {
                return false;
            }
        }
        return true;
    }

    private void e() {
        this.f.add("paused");
    }

    private void f() {
        this.f.add("saved_instance_state");
    }

    private void g() {
        if (!this.i.get()) {
            if (((Boolean) this.e.get(ea.dp)).booleanValue() && a(this.f, a)) {
                boolean booleanValue = ((Boolean) this.e.get(ea.dm)).booleanValue();
                long toMillis = TimeUnit.MINUTES.toMillis(((Long) this.e.get(ea.do)).longValue());
                if (this.j == null || System.currentTimeMillis() - this.j.getTime() >= toMillis) {
                    ((EventServiceImpl) this.e.getEventService()).a("paused", false);
                    if (booleanValue) {
                        this.j = new Date();
                    }
                }
                if (!booleanValue) {
                    this.j = new Date();
                }
            }
            this.f.add("stopped");
        }
    }

    private void h() {
        if (!this.f.isEmpty()) {
            String str = (String) this.f.get(this.f.size() - 1);
            if ("stopped".equals(str) || "saved_instance_state".equals(str)) {
                this.f.add("started");
            } else {
                this.f.clear();
            }
        }
    }

    private void i() {
        if (!this.i.getAndSet(false)) {
            if (a(this.f, b) || a(this.f, c) || a(this.f, d)) {
                boolean booleanValue = ((Boolean) this.e.get(ea.dm)).booleanValue();
                long toMillis = TimeUnit.MINUTES.toMillis(((Long) this.e.get(ea.dn)).longValue());
                if (this.k == null || System.currentTimeMillis() - this.k.getTime() >= toMillis) {
                    ((EventServiceImpl) this.e.getEventService()).a("resumed", false);
                    if (booleanValue) {
                        this.k = new Date();
                    }
                }
                if (!booleanValue) {
                    this.k = new Date();
                }
                this.e.a().a("app_paused_and_resumed");
                this.h.set(true);
            }
            this.f.clear();
        }
    }

    private void j() {
        this.f.clear();
    }

    public void a() {
        this.i.set(true);
    }

    void a(Context context) {
        if (context != null && ab.c() && ((Boolean) this.e.get(ea.dl)).booleanValue() && !this.g.getAndSet(true)) {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            ((Application) context).registerActivityLifecycleCallbacks(new dz(this));
        }
    }

    public void b() {
        this.i.set(false);
    }

    boolean c() {
        return this.g.get();
    }

    boolean d() {
        return this.h.getAndSet(false);
    }
}
