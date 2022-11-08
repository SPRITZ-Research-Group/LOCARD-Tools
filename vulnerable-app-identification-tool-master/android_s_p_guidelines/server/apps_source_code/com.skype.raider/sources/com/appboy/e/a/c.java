package com.appboy.e.a;

import a.a.am;
import a.a.be;
import a.a.cf;
import a.a.co;
import a.a.cv;
import android.support.annotation.VisibleForTesting;
import com.appboy.e.e;
import com.appboy.f.i;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import org.json.JSONArray;
import org.json.JSONObject;

public class c extends Observable implements e<JSONObject> {
    private static final String k = com.appboy.f.c.a(c.class);
    protected final JSONObject a;
    protected final Map<String, String> b;
    protected final String c;
    protected boolean d;
    protected boolean e;
    protected final long f;
    protected final long g;
    protected final long h;
    protected boolean i = false;
    protected final EnumSet<com.appboy.b.c> j;
    private final am l;
    private final cf m;

    public c(JSONObject object, am manager, cf storageProvider) {
        int i = 0;
        this.a = object;
        this.b = cv.a(object.optJSONObject("extras"), new HashMap());
        this.l = manager;
        this.m = storageProvider;
        this.c = object.getString("id");
        this.d = object.getBoolean("viewed");
        this.e = this.d;
        this.f = object.getLong("created");
        this.g = object.getLong("updated");
        this.h = object.optLong("expires_at", -1);
        this.i = object.optBoolean("use_webview", false);
        JSONArray optJSONArray = object.optJSONArray("categories");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            this.j = EnumSet.of(com.appboy.b.c.NO_CATEGORY);
            return;
        }
        this.j = EnumSet.noneOf(com.appboy.b.c.class);
        while (i < optJSONArray.length()) {
            com.appboy.b.c a = com.appboy.b.c.a(optJSONArray.getString(i));
            if (a != null) {
                this.j.add(a);
            }
            i++;
        }
    }

    public final boolean g() {
        try {
            if (!(this.l == null || this.m == null || !a())) {
                this.l.a(be.c(this.c));
                this.m.a(this.c);
                return true;
            }
        } catch (Throwable e) {
            com.appboy.f.c.c(k, "Failed to log feed card impression.", e);
        }
        return false;
    }

    public final boolean i() {
        try {
            if (this.l != null && a()) {
                this.l.a(be.d(this.c));
                return true;
            }
        } catch (Throwable e) {
            com.appboy.f.c.c(k, "Failed to log feed card clicked.", e);
        }
        return false;
    }

    public final boolean a(c card) {
        return this.c.equals(card.c) && this.g == card.g && this.l == card.l;
    }

    public final String j() {
        return this.c;
    }

    public final Map<String, String> k() {
        return this.b;
    }

    public String b() {
        return null;
    }

    public final boolean l() {
        return this.d;
    }

    public final boolean m() {
        return this.i;
    }

    public final void n() {
        this.d = true;
    }

    public final boolean o() {
        return this.e;
    }

    public final void p() {
        this.e = true;
        setChanged();
        notifyObservers();
        try {
            this.m.b(this.c);
        } catch (Throwable e) {
            com.appboy.f.c.a(k, "Failed to mark card as read.", e);
        }
    }

    public final boolean a(EnumSet<com.appboy.b.c> categories) {
        Iterator it = categories.iterator();
        while (it.hasNext()) {
            if (this.j.contains((com.appboy.b.c) it.next())) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    private boolean a() {
        if (!i.c(this.c)) {
            return true;
        }
        com.appboy.f.c.g(k, "Card ID cannot be null");
        return false;
    }

    public final boolean q() {
        return this.h != -1 && this.h <= co.a();
    }

    public final /* bridge */ /* synthetic */ Object h() {
        return this.a;
    }
}
