package org.a.a.b;

import java.util.ArrayList;
import java.util.List;

public abstract class a implements m {
    protected List<Object> a;

    public a(byte b) {
    }

    public final m a(int i) {
        if (this.a == null || i >= this.a.size()) {
            return null;
        }
        return (m) this.a.get(i);
    }

    public final int a() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public final void a(m t) {
        if (t != null) {
            a childTree = (a) t;
            if (!childTree.b()) {
                if (this.a == null) {
                    this.a = new ArrayList();
                }
                this.a.add(t);
                childTree.b((m) this);
                childTree.b(this.a.size() - 1);
            } else if (this.a != null && this.a == childTree.a) {
                throw new RuntimeException("attempt to add child list to itself");
            } else if (childTree.a == null) {
            } else {
                if (this.a != null) {
                    int n = childTree.a.size();
                    for (int i = 0; i < n; i++) {
                        m c = (m) childTree.a.get(i);
                        this.a.add(c);
                        c.b((m) this);
                        c.b(this.a.size() - 1);
                    }
                    return;
                }
                this.a = childTree.a;
                j();
            }
        }
    }

    public boolean b() {
        return false;
    }

    private void j() {
        int n = a();
        for (int c = 0; c < n; c++) {
            m child = a(c);
            child.b(c);
            child.b((m) this);
        }
    }

    public int c() {
        return 0;
    }

    public void b(int index) {
    }

    public m d() {
        return null;
    }

    public void b(m t) {
    }

    public int e() {
        return 0;
    }

    public int f() {
        return 0;
    }
}
