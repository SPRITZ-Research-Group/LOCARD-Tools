package org.b.a.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.a.a.b.c;
import org.a.a.w;
import org.a.a.y;
import org.b.a.f;
import org.b.a.h;

public final class e implements Cloneable {
    public String a;
    public String b = "/";
    public String c = "";
    public w d;
    public y e;
    public c f;
    public Map<String, g> g;
    public boolean h;
    public int i;
    public List<e> j;
    public h k = h.q;
    public boolean l;
    public f.c m;
    public boolean n;
    public String[] o;
    public byte[] p = new byte[15];
    public int q;
    public org.b.a.c.h[] r = new org.b.a.c.h[15];

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public final e a() throws CloneNotSupportedException {
        e clone = (e) super.clone();
        if (this.g != null) {
            this.g = Collections.synchronizedMap(new LinkedHashMap(this.g));
        }
        return clone;
    }

    public final void a(e sub) {
        sub.b = this.b;
        if (sub.a.charAt(0) != '/') {
            sub.a = sub.b + sub.a;
        }
        if (this.j == null) {
            this.j = new ArrayList();
        }
        this.j.add(sub);
    }

    public final void a(List<g> args) {
        this.h = true;
        if (args == null) {
            this.g = null;
            return;
        }
        for (g a : args) {
            a(a);
        }
    }

    public final void a(g a) {
        if (this.g == null) {
            this.g = Collections.synchronizedMap(new LinkedHashMap());
        }
        a.b = this.g.size();
        this.g.put(a.a, a);
    }

    public final void a(h group) {
        if (this.j != null) {
            for (e sub : this.j) {
                group.a(sub.a, sub, sub.d);
                sub.a(group);
            }
        }
    }
}
