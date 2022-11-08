package org.b.a;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.b.a.a.e;
import org.b.a.a.g;
import org.b.a.c.k;

public class f {
    public static final Object a = new Object();
    public e b;
    protected Object[] c;
    public h d;
    public b e;

    public static final class a extends ArrayList<Object> {
        public a(int size) {
            super(size);
        }
    }

    public static class b {
        public org.b.a.b.b a;
        public k<String, org.b.a.b.a> b = new k();
    }

    public enum c {
        IMPLICIT,
        EMBEDDED,
        EXPLICIT
    }

    protected f() {
        if (h.o) {
            if (this.e == null) {
                this.e = new b();
            }
            this.e.a = new org.b.a.b.b();
        }
    }

    public f(String template) {
        this(h.q, template);
    }

    private f(h group, String template) {
        this();
        this.d = group;
        h hVar = this.d;
        h.a();
        this.b = new org.b.a.a.f(hVar).a(template);
        this.b.h = false;
        this.b.a = "anonymous";
        this.b.a(this.d);
    }

    public f(f proto) {
        this.b = proto.b;
        if (proto.c != null) {
            this.c = new Object[proto.c.length];
            System.arraycopy(proto.c, 0, this.c, 0, proto.c.length);
        } else if (!(this.b.g == null || this.b.g.isEmpty())) {
            this.c = new Object[this.b.g.size()];
        }
        this.d = proto.d;
    }

    public final synchronized f a(String name, Object value) {
        f this;
        if (name == null) {
            throw new NullPointerException("null attribute name");
        } else if (name.indexOf(46) >= 0) {
            throw new IllegalArgumentException("cannot have '.' in attribute names");
        } else {
            if (h.o) {
                if (this.e == null) {
                    this.e = new b();
                }
                this.e.b.a(name, new org.b.a.b.a(name, value));
            }
            g arg = null;
            if (this.b.h) {
                if (this.b.g != null) {
                    arg = (g) this.b.g.get(name);
                }
                if (arg == null) {
                    throw new IllegalArgumentException("no such attribute: " + name);
                }
            }
            if (this.b.g != null) {
                arg = (g) this.b.g.get(name);
            }
            if (arg == null) {
                arg = new g(name);
                this.b.a(arg);
                if (this.c == null) {
                    this.c = new Object[1];
                } else {
                    Object[] copy = new Object[this.b.g.size()];
                    System.arraycopy(this.c, 0, copy, 0, Math.min(this.c.length, this.b.g.size()));
                    this.c = copy;
                }
                this.c[arg.b] = a;
            }
            Object curvalue = this.c[arg.b];
            if (curvalue == a) {
                this.c[arg.b] = value;
                this = this;
            } else {
                a multi = a(curvalue);
                this.c[arg.b] = multi;
                if (value instanceof List) {
                    multi.addAll((List) value);
                } else if (value == null || !value.getClass().isArray()) {
                    multi.add(value);
                } else if (value instanceof Object[]) {
                    multi.addAll(Arrays.asList((Object[]) value));
                } else {
                    multi.addAll(a(value));
                }
                this = this;
            }
        }
        return this;
    }

    protected final void b(String name, Object value) {
        if (this.b.g == null) {
            throw new IllegalArgumentException("no such attribute: " + name);
        }
        g arg = (g) this.b.g.get(name);
        if (arg == null) {
            throw new IllegalArgumentException("no such attribute: " + name);
        }
        this.c[arg.b] = value;
    }

    public final Object a(String name) {
        g localArg = null;
        if (this.b.g != null) {
            localArg = (g) this.b.g.get(name);
        }
        if (localArg == null) {
            return null;
        }
        Object o = this.c[localArg.b];
        if (o == a) {
            return null;
        }
        return o;
    }

    private static a a(Object curvalue) {
        a multi;
        if (curvalue == null) {
            multi = new a();
            multi.add(curvalue);
            return multi;
        } else if (curvalue instanceof a) {
            return (a) curvalue;
        } else {
            if (curvalue instanceof List) {
                List<?> listAttr = (List) curvalue;
                multi = new a(listAttr.size());
                multi.addAll(listAttr);
                return multi;
            } else if (curvalue instanceof Object[]) {
                Object[] a = (Object[]) curvalue;
                multi = new a(a.length);
                multi.addAll(Arrays.asList(a));
                return multi;
            } else if (curvalue.getClass().isArray()) {
                int length = Array.getLength(curvalue);
                multi = new a(length);
                for (int i = 0; i < length; i++) {
                    multi.add(Array.get(curvalue, i));
                }
                return multi;
            } else {
                multi = new a();
                multi.add(curvalue);
                return multi;
            }
        }
    }

    public String toString() {
        if (this.b == null) {
            return "bad-template()";
        }
        String name = this.b.a + "()";
        if (this.b.l) {
            return "@" + h.e(name);
        }
        return name;
    }
}
