package org.a.a.b;

import org.a.a.w;

public abstract class b implements n {
    protected int a = 1;

    public abstract w a(w wVar);

    public abstract w b(int i, String str);

    public final Object a() {
        return b(null);
    }

    public final boolean a(Object tree) {
        return ((m) tree).b();
    }

    public final Object b(Object tree) {
        return d(tree, null);
    }

    private Object d(Object t, Object parent) {
        if (t == null) {
            return null;
        }
        Object newTree = g(t);
        b(newTree, j(t));
        c(newTree, parent);
        int n = f(t);
        for (int i = 0; i < n; i++) {
            a(newTree, d(a(t, i), t));
        }
        return newTree;
    }

    public final void a(Object t, Object child) {
        if (t != null && child != null) {
            ((m) t).a((m) child);
        }
    }

    public final Object b(Object newRoot, Object oldRoot) {
        m newRootTree = (m) newRoot;
        m oldRootTree = (m) oldRoot;
        if (oldRoot == null) {
            return newRoot;
        }
        if (newRootTree.b()) {
            int nc = newRootTree.a();
            if (nc == 1) {
                newRootTree = newRootTree.a(0);
            } else if (nc > 1) {
                throw new RuntimeException("more than one node as root (TODO: make exception hierarchy)");
            }
        }
        newRootTree.a(oldRootTree);
        return newRootTree;
    }

    public final Object c(Object root) {
        m r = (m) root;
        if (r == null || !r.b()) {
            return r;
        }
        if (r.a() == 0) {
            return null;
        }
        if (r.a() != 1) {
            return r;
        }
        r = r.a(0);
        r.b(null);
        r.b(-1);
        return r;
    }

    public final Object a(int tokenType, w fromToken) {
        fromToken = a(fromToken);
        fromToken.c(tokenType);
        return (m) b(fromToken);
    }

    public final Object a(int tokenType, w fromToken, String text) {
        if (fromToken == null) {
            return a(tokenType, text);
        }
        fromToken = a(fromToken);
        fromToken.c(tokenType);
        fromToken.a(text);
        return (m) b(fromToken);
    }

    public final Object a(int tokenType, String text) {
        return (m) b(b(tokenType, text));
    }

    public int d(Object t) {
        return ((m) t).h();
    }

    public String e(Object t) {
        return ((m) t).i();
    }

    public Object a(Object t, int i) {
        return ((m) t).a(i);
    }

    public int f(Object t) {
        return ((m) t).a();
    }
}
