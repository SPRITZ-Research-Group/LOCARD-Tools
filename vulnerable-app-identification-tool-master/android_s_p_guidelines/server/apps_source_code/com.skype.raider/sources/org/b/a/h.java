package org.b.a;

import com.adjust.sdk.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.a.a.w;
import org.b.a.a.e;
import org.b.a.c.a;
import org.b.a.c.b;
import org.b.a.c.f;
import org.b.a.c.g;
import org.b.a.c.i;
import org.b.a.c.l;
import org.b.a.c.p;
import org.b.a.c.t;
import org.b.a.f.c;

public final class h {
    public static final String a = ".stg";
    public static final String b = ".st";
    protected static final e l = new e();
    public static final f m = new f();
    public static boolean n = false;
    public static boolean o = false;
    public static h q = new h();
    public String c = Constants.ENCODING;
    protected final List<h> d = Collections.synchronizedList(new ArrayList());
    protected final List<h> e = Collections.synchronizedList(new ArrayList());
    public char f = '<';
    public char g = '>';
    protected Map<String, e> h = Collections.synchronizedMap(new LinkedHashMap());
    protected Map<String, Map<String, Object>> i = Collections.synchronizedMap(new HashMap());
    protected Map<Class<?>, a> j;
    protected final Map<Class<?>, e> k;
    public boolean p;
    public f r;

    public h() {
        t<e> registry = new t();
        registry.a(Object.class, new l());
        registry.a(f.class, new p());
        registry.a(Map.class, new i());
        registry.a(a.class, new b());
        this.k = Collections.synchronizedMap(registry);
        this.p = false;
        this.r = m;
    }

    protected final f a(d interp, c scope, String name) {
        f st;
        String fullyQualifiedName = name;
        if (name.charAt(0) != '/') {
            fullyQualifiedName = scope.b.b.b + name;
        }
        if (n) {
            System.out.println("getEmbeddedInstanceOf(" + fullyQualifiedName + ")");
        }
        if (fullyQualifiedName != null) {
            String str;
            if (n) {
                System.out.println("<no name>;.getInstanceOf(" + fullyQualifiedName + ")");
            }
            if (fullyQualifiedName.charAt(0) != '/') {
                str = "/" + fullyQualifiedName;
            } else {
                str = fullyQualifiedName;
            }
            e a = a(str);
            if (a != null) {
                st = b(a);
                if (st == null) {
                    this.r.a(interp, scope, g.NO_SUCH_TEMPLATE, (Object) fullyQualifiedName);
                    return a(new e());
                } else if (o) {
                    return st;
                } else {
                    st.e.a = null;
                    return st;
                }
            }
        }
        st = null;
        if (st == null) {
            this.r.a(interp, scope, g.NO_SUCH_TEMPLATE, (Object) fullyQualifiedName);
            return a(new e());
        } else if (o) {
            return st;
        } else {
            st.e.a = null;
            return st;
        }
    }

    public final e a(String name) {
        if (name.charAt(0) != '/') {
            name = "/" + name;
        }
        if (n) {
            System.out.println("<no name>;.lookupTemplate(" + name + ")");
        }
        e code = f(name);
        if (code == l) {
            if (n) {
                System.out.println(name + " previously seen as not found");
            }
            return null;
        }
        if (code == null) {
            code = null;
        }
        if (code == null) {
            code = b(name);
        }
        if (code == null) {
            if (n) {
                System.out.println(name + " recorded not found");
            }
            this.h.put(name, l);
        }
        if (n && code != null) {
            System.out.println("<no name>;.lookupTemplate(" + name + ") found");
        }
        return code;
    }

    protected final e b(String name) {
        if (this.d.size() == 0) {
            return null;
        }
        for (h g : this.d) {
            if (n) {
                System.out.println("checking <no name>; for imported " + name);
            }
            e code = g.a(name);
            if (code != null) {
                if (!n) {
                    return code;
                }
                System.out.println("<no name>;.lookupImportedTemplate(" + name + ") found");
                return code;
            }
        }
        if (n) {
            System.out.println(name + " not found in <no name>; imports");
        }
        return null;
    }

    private e f(String name) {
        return (e) this.h.get(name);
    }

    public final Map<String, Object> c(String name) {
        return (Map) this.i.get(name);
    }

    public final boolean d(String name) {
        return this.i.get(name) != null;
    }

    public final void a(String name, e code, w defT) {
        e prev = f(name);
        if (prev != null) {
            if (!prev.l) {
                this.r.a(g.TEMPLATE_REDEFINITION, null, defT);
                return;
            } else if (prev.l) {
                if (code.m != c.IMPLICIT && prev.m == c.EMBEDDED) {
                    this.r.a(g.EMBEDDED_REGION_REDEFINITION, null, defT, e(name));
                    return;
                } else if (code.m == c.IMPLICIT || prev.m == c.EXPLICIT) {
                    this.r.a(g.REGION_REDEFINITION, null, defT, e(name));
                    return;
                }
            }
        }
        code.k = this;
        code.d = defT;
        this.h.put(name, code);
    }

    public static String a(String enclosingTemplateName, String name) {
        if (enclosingTemplateName.charAt(0) != '/') {
            enclosingTemplateName = "/" + enclosingTemplateName;
        }
        return "/region__" + enclosingTemplateName + "__" + name;
    }

    public static String e(String mangledName) {
        String t = mangledName.substring(9, mangledName.lastIndexOf("__"));
        return t + '.' + mangledName.substring(mangledName.lastIndexOf("__") + 2, mangledName.length());
    }

    public final e a(Class<?> attributeType) {
        return (e) this.k.get(attributeType);
    }

    public final a b(Class<?> attributeType) {
        if (this.j == null) {
            return null;
        }
        return (a) this.j.get(attributeType);
    }

    private f b(e impl) {
        f st = new f();
        st.b = impl;
        st.d = this;
        if (impl.g != null) {
            st.c = new Object[impl.g.size()];
            Arrays.fill(st.c, f.a);
        }
        return st;
    }

    public final f a(e impl) {
        f st = b(impl);
        if (o && st.e != null) {
            st.e.a = null;
        }
        return st;
    }

    public static f a(f proto) {
        return new f(proto);
    }

    public static String a() {
        return null;
    }

    public final String toString() {
        return "<no name>;";
    }
}
