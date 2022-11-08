package org.b.a;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.b.a.a.e;
import org.b.a.c.f;
import org.b.a.c.g;
import org.b.a.c.h;
import org.b.a.c.j;
import org.b.a.c.o;
import org.b.a.c.q;
import org.b.a.c.r;
import org.b.a.c.s;

public final class d {
    public static final Set<String> a = new HashSet<String>() {
        {
            add("i");
            add("i0");
        }
    };
    public static boolean h = false;
    Object[] b = new Object[100];
    int c = -1;
    int d = 0;
    h e;
    Locale f;
    f g;
    protected List<String> i;
    public boolean j = false;
    protected List<org.b.a.b.f> k;

    protected static class a extends HashMap<String, Object> {
        protected a() {
        }
    }

    protected static class b extends ArrayList<Object> {
        protected b() {
        }
    }

    public enum c {
        ANCHOR,
        FORMAT,
        NULL,
        SEPARATOR,
        WRAP
    }

    public d(h group, Locale locale, f errMgr) {
        this.e = group;
        this.f = locale;
        this.g = errMgr;
        this.j = false;
    }

    public final int a(i out, c scope) {
        f self = scope.b;
        if (h) {
            System.out.println("exec(" + self.b.a + ")");
        }
        try {
            c(out, scope);
            return b(out, scope);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            this.g.a(this, scope, g.INTERNAL_ERROR, "internal error: " + sw.toString());
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(i out, c scope) {
        f self = scope.b;
        int start = out.e();
        int prevOpcode = 0;
        int n = 0;
        byte[] code = self.b.p;
        int ip = 0;
        while (ip < self.b.q) {
            int i;
            if (h || this.j) {
                f fVar = scope.b;
                StringBuilder stringBuilder = new StringBuilder();
                new org.b.a.a.b(fVar.b).a(new StringBuilder(), ip);
                String str = fVar.b.a + ":";
                if (j.a(fVar.b.a, (Object) "anonymous")) {
                    str = "";
                }
                stringBuilder.append(String.format("%-40s", new Object[]{str + r8}));
                stringBuilder.append("\tstack=[");
                for (i = 0; i <= this.c; i++) {
                    a(stringBuilder, scope, this.b[i]);
                }
                stringBuilder.append(" ], calls=");
                stringBuilder.append(a(scope));
                stringBuilder.append(", sp=" + this.c + ", nw=" + this.d);
                str = stringBuilder.toString();
                if (this.j) {
                    this.i.add(str);
                }
                if (h) {
                    System.out.println(str);
                }
            }
            short opcode = (short) code[ip];
            scope.c = ip;
            ip++;
            Object[] objArr;
            int i2;
            int nameIndex;
            String name;
            Object o;
            Object[] objArr2;
            int i3;
            int nargs;
            f st;
            Map attrs;
            f fVar2;
            e b;
            int n1;
            int nmaps;
            int i4;
            Object right;
            Object left;
            boolean z;
            int strIndex;
            switch (opcode) {
                case (short) 1:
                    i = a(self.b.p, ip);
                    objArr = this.b;
                    i2 = this.c + 1;
                    this.c = i2;
                    objArr[i2] = self.b.o[i];
                    ip += 2;
                    break;
                case (short) 2:
                    nameIndex = a(code, ip);
                    ip += 2;
                    name = self.b.o[nameIndex];
                    try {
                        o = a(scope, name);
                        if (o == f.a) {
                            o = null;
                        }
                    } catch (q e) {
                        this.g.a(this, scope, g.NO_SUCH_ATTRIBUTE, (Object) name);
                        o = null;
                    }
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = o;
                    break;
                case (short) 3:
                    int valueIndex = a(code, ip);
                    ip += 2;
                    o = self.c[valueIndex];
                    if (o == f.a) {
                        o = null;
                    }
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = o;
                    break;
                case (short) 4:
                    nameIndex = a(code, ip);
                    ip += 2;
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    o = objArr2[i3];
                    name = self.b.o[nameIndex];
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = a(out, scope, o, (Object) name);
                    break;
                case (short) 5:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    this.b[this.c] = a(out, scope, this.b[this.c], objArr2[i3]);
                    break;
                case (short) 6:
                    int optionIndex = a(code, ip);
                    ip += 2;
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    ((Object[]) this.b[this.c])[optionIndex] = objArr2[i3];
                    break;
                case (short) 7:
                    name = self.b.o[a(code, ip)];
                    ip += 2;
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    ((a) this.b[this.c]).put(name, objArr2[i3]);
                    break;
                case (short) 8:
                    nameIndex = a(code, ip);
                    ip += 2;
                    name = self.b.o[nameIndex];
                    nargs = a(code, ip);
                    ip += 2;
                    st = self.d.a(this, scope, name);
                    a(scope, nargs, st);
                    this.c -= nargs;
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = st;
                    break;
                case (short) 9:
                    nargs = a(code, ip);
                    ip += 2;
                    st = self.d.a(this, scope, (String) this.b[this.c - nargs]);
                    a(scope, nargs, st);
                    this.c -= nargs;
                    this.c--;
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = st;
                    break;
                case (short) 10:
                    nameIndex = a(code, ip);
                    ip += 2;
                    name = self.b.o[nameIndex];
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    attrs = (a) objArr2[i3];
                    st = self.d.a(this, scope, name);
                    a(scope, attrs, st);
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = st;
                    break;
                case (short) 11:
                    nameIndex = a(code, ip);
                    ip += 2;
                    name = self.b.o[nameIndex];
                    nargs = a(code, ip);
                    ip += 2;
                    fVar2 = scope.b;
                    b = fVar2.b.k.b(name);
                    if (b == null) {
                        this.g.a(this, scope, g.NO_IMPORTED_TEMPLATE, (Object) name);
                        fVar2 = fVar2.d.a(new e());
                    } else {
                        fVar2 = b.k.a(this, scope, name);
                        fVar2.d = this.e;
                    }
                    a(scope, nargs, fVar2);
                    this.c -= nargs;
                    objArr = this.b;
                    i2 = this.c + 1;
                    this.c = i2;
                    objArr[i2] = fVar2;
                    break;
                case (short) 12:
                    nameIndex = a(code, ip);
                    ip += 2;
                    name = self.b.o[nameIndex];
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    attrs = (a) objArr2[i3];
                    fVar2 = scope.b;
                    b = fVar2.b.k.b(name);
                    if (b == null) {
                        this.g.a(this, scope, g.NO_IMPORTED_TEMPLATE, (Object) name);
                        fVar2 = fVar2.d.a(new e());
                    } else {
                        fVar2 = b.k.a(b);
                        fVar2.d = this.e;
                    }
                    a(scope, attrs, fVar2);
                    objArr = this.b;
                    i2 = this.c + 1;
                    this.c = i2;
                    objArr[i2] = fVar2;
                    break;
                case (short) 13:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    n1 = a(out, scope, objArr2[i3]);
                    n += n1;
                    this.d += n1;
                    break;
                case (short) 14:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    Object[] options = objArr2[i3];
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    int n2 = a(out, scope, objArr2[i3], options);
                    n += n2;
                    this.d += n2;
                    break;
                case (short) 15:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    st = (f) objArr2[i3];
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    final f fVar3 = st;
                    a(scope, objArr2[i3], new ArrayList<f>(this) {
                        final /* synthetic */ d b;
                    });
                    break;
                case (short) 16:
                    nmaps = a(code, ip);
                    ip += 2;
                    List<f> templates = new ArrayList();
                    for (i4 = nmaps - 1; i4 >= 0; i4--) {
                        templates.add((f) this.b[this.c - i4]);
                    }
                    this.c -= nmaps;
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    o = objArr2[i3];
                    if (o == null) {
                        break;
                    }
                    a(scope, o, (List) templates);
                    break;
                case (short) 17:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    st = (f) objArr2[i3];
                    nmaps = a(code, ip);
                    ip += 2;
                    List exprs = new b();
                    for (i4 = nmaps - 1; i4 >= 0; i4--) {
                        exprs.add(this.b[this.c - i4]);
                    }
                    this.c -= nmaps;
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = a(scope, exprs, st);
                    break;
                case (short) 18:
                    ip = a(code, ip);
                    break;
                case (short) 19:
                    int addr = a(code, ip);
                    ip += 2;
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    if (!b(objArr2[i3])) {
                        ip = addr;
                        break;
                    }
                    break;
                case (short) 20:
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = new Object[org.b.a.a.f.b];
                    break;
                case (short) 21:
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = new a();
                    break;
                case (short) 22:
                    nameIndex = a(code, ip);
                    ip += 2;
                    a(scope, self.b.o[nameIndex], (a) this.b[this.c]);
                    break;
                case (short) 24:
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = new b();
                    break;
                case (short) 25:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    a(scope, (List) (b) this.b[this.c], objArr2[i3]);
                    break;
                case (short) 26:
                    this.b[this.c] = b(out, scope, this.b[this.c]);
                    break;
                case (short) 27:
                    Object obj;
                    Object[] objArr3 = this.b;
                    int i5 = this.c;
                    Object obj2 = this.b[this.c];
                    if (obj2 == null) {
                        obj = null;
                    } else {
                        obj = f(scope, obj2);
                        if (obj instanceof Iterator) {
                            Iterator it = (Iterator) obj;
                            if (it.hasNext()) {
                                obj = it.next();
                            }
                        }
                        obj = obj2;
                    }
                    objArr3[i5] = obj;
                    break;
                case (short) 28:
                    this.b[this.c] = a(scope, this.b[this.c]);
                    break;
                case (short) 29:
                    this.b[this.c] = b(scope, this.b[this.c]);
                    break;
                case (short) 30:
                    this.b[this.c] = c(scope, this.b[this.c]);
                    break;
                case (short) 31:
                    this.b[this.c] = d(scope, this.b[this.c]);
                    break;
                case (short) 32:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    o = objArr2[i3];
                    if (o.getClass() != String.class) {
                        this.g.a(this, scope, g.EXPECTING_STRING, "trim", o.getClass().getName());
                        objArr2 = this.b;
                        i3 = this.c + 1;
                        this.c = i3;
                        objArr2[i3] = o;
                        break;
                    }
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = ((String) o).trim();
                    break;
                case (short) 33:
                    this.b[this.c] = a(this.b[this.c]);
                    break;
                case (short) 34:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    o = objArr2[i3];
                    if (o.getClass() != String.class) {
                        this.g.a(this, scope, g.EXPECTING_STRING, "strlen", o.getClass().getName());
                        objArr2 = this.b;
                        i3 = this.c + 1;
                        this.c = i3;
                        objArr2[i3] = Integer.valueOf(0);
                        break;
                    }
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = Integer.valueOf(((String) o).length());
                    break;
                case (short) 35:
                    this.b[this.c] = e(scope, this.b[this.c]);
                    break;
                case (short) 36:
                    this.b[this.c] = Boolean.valueOf(!b(this.b[this.c]));
                    break;
                case (short) 37:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    right = objArr2[i3];
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    left = objArr2[i3];
                    objArr = this.b;
                    i2 = this.c + 1;
                    this.c = i2;
                    z = b(left) || b(right);
                    objArr[i2] = Boolean.valueOf(z);
                    break;
                case (short) 38:
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    right = objArr2[i3];
                    objArr2 = this.b;
                    i3 = this.c;
                    this.c = i3 - 1;
                    left = objArr2[i3];
                    objArr = this.b;
                    i2 = this.c + 1;
                    this.c = i2;
                    z = b(left) && b(right);
                    objArr[i2] = Boolean.valueOf(z);
                    break;
                case (short) 39:
                    strIndex = a(code, ip);
                    ip += 2;
                    String str2 = scope.b.b.o[strIndex];
                    if (this.j) {
                        i2 = out.e();
                        a(scope, new org.b.a.b.e(scope, i2, (str2.length() + i2) - 1, b(scope), c(scope)));
                    }
                    out.a(str2);
                    break;
                case (short) 40:
                    out.b();
                    break;
                case (short) 41:
                    if (!(prevOpcode == 41 || prevOpcode == 39)) {
                        try {
                            break;
                        } catch (Throwable ioe) {
                            this.g.a(self, g.WRITE_IO_ERROR, ioe);
                            break;
                        }
                    }
                    out.b(j.a);
                    this.d = 0;
                    break;
                case (short) 42:
                    break;
                case (short) 43:
                    this.c--;
                    break;
                case (short) 44:
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = null;
                    break;
                case (short) 45:
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = Boolean.valueOf(true);
                    break;
                case (short) 46:
                    objArr2 = this.b;
                    i3 = this.c + 1;
                    this.c = i3;
                    objArr2[i3] = Boolean.valueOf(false);
                    break;
                case (short) 47:
                    strIndex = a(code, ip);
                    ip += 2;
                    n1 = a(out, scope, (Object) self.b.o[strIndex]);
                    n += n1;
                    this.d += n1;
                    break;
                default:
                    this.g.a(self, "invalid bytecode @ " + (ip - 1) + ": " + opcode, null);
                    e eVar = self.b;
                    org.b.a.a.b bVar = new org.b.a.a.b(eVar);
                    System.out.println(eVar.a + ":");
                    System.out.println(bVar.a());
                    System.out.println("Strings:");
                    System.out.println(bVar.b());
                    System.out.println("Bytecode to template map:");
                    System.out.println(bVar.c());
                    break;
            }
            short prevOpcode2 = opcode;
        }
        if (this.j) {
            a(scope, new org.b.a.b.d(scope, start, out.e() - 1));
        }
        return n;
    }

    private void a(c scope, String templateName, Map<String, Object> attrs) {
        e c = this.e.a(templateName);
        if (c != null && c.g != null) {
            for (org.b.a.a.g arg : c.g.values()) {
                if (!attrs.containsKey(arg.a)) {
                    try {
                        Object o = a(scope, arg.a);
                        if (o == f.a && arg.c == null) {
                            attrs.put(arg.a, null);
                        } else if (o != f.a) {
                            attrs.put(arg.a, o);
                        }
                    } catch (q e) {
                        if (arg.c == null) {
                            this.g.a(this, scope, g.NO_SUCH_ATTRIBUTE_PASS_THROUGH, arg.a);
                            attrs.put(arg.a, null);
                        }
                    }
                }
            }
        }
    }

    private void a(c scope, Map<String, Object> attrs, f st) {
        if (st.b.h) {
            boolean argumentCountMismatch = false;
            Map<String, org.b.a.a.g> formalArguments = st.b.g;
            if (formalArguments == null) {
                formalArguments = Collections.emptyMap();
            }
            for (Entry<String, org.b.a.a.g> formalArgument : formalArguments.entrySet()) {
                if (((org.b.a.a.g) formalArgument.getValue()).c == null && ((org.b.a.a.g) formalArgument.getValue()).d == null) {
                    if (attrs != null) {
                        if (attrs.containsKey(formalArgument.getKey())) {
                        }
                    }
                    argumentCountMismatch = true;
                    break;
                }
            }
            if (attrs != null && attrs.size() > formalArguments.size()) {
                argumentCountMismatch = true;
            }
            if (argumentCountMismatch) {
                this.g.a(this, scope, g.ARGUMENT_COUNT_MISMATCH, Integer.valueOf(attrs != null ? attrs.size() : 0), st.b.a, Integer.valueOf(formalArguments.size()));
            }
        }
        if (attrs != null) {
            for (Entry<String, Object> argument : attrs.entrySet()) {
                if (st.b.h) {
                    if (st.b.g == null || !st.b.g.containsKey(argument.getKey())) {
                        this.g.a(this, scope, g.NO_SUCH_ATTRIBUTE, argument.getKey());
                    } else {
                        st.b((String) argument.getKey(), argument.getValue());
                    }
                } else if (st.b.g == null || !st.b.g.containsKey(argument.getKey())) {
                    try {
                        st.b = st.b.a();
                        st.a((String) argument.getKey(), argument.getValue());
                    } catch (CloneNotSupportedException e) {
                        this.g.a(this, scope, g.NO_SUCH_ATTRIBUTE, argument.getKey());
                    }
                } else {
                    st.b((String) argument.getKey(), argument.getValue());
                }
            }
        }
    }

    private void a(c scope, int nargs, f st) {
        if (nargs > 0 && !st.b.h && st.b.g == null) {
            st.a("it", null);
        }
        int nformalArgs = 0;
        if (st.b.g != null) {
            nformalArgs = st.b.g.size();
        }
        int firstArg = this.c - (nargs - 1);
        int numToStore = Math.min(nargs, nformalArgs);
        if (st.b.n) {
            nformalArgs -= a.size();
        }
        if (nargs < nformalArgs - st.b.i || nargs > nformalArgs) {
            this.g.a(this, scope, g.ARGUMENT_COUNT_MISMATCH, Integer.valueOf(nargs), st.b.a, Integer.valueOf(nformalArgs));
        }
        if (st.b.g != null) {
            Iterator<String> argNames = st.b.g.keySet().iterator();
            for (int i = 0; i < numToStore; i++) {
                f fVar = st;
                fVar.b((String) argNames.next(), this.b[firstArg + i]);
            }
        }
    }

    private int a(i out, c scope, Object o) {
        int start = out.e();
        int n = a(out, scope, o, null);
        if (this.j) {
            a(scope, new org.b.a.b.c(scope, start, out.e() - 1, b(scope), c(scope)));
        }
        return n;
    }

    private int a(i out, c scope, Object o, Object[] options) {
        int start = out.e();
        String[] optionStrings = null;
        if (options != null) {
            optionStrings = new String[options.length];
            for (int i = 0; i < org.b.a.a.f.b; i++) {
                optionStrings[i] = b(out, scope, options[i]);
            }
        }
        if (!(options == null || options[c.ANCHOR.ordinal()] == null)) {
            out.c();
        }
        int n = a(out, scope, o, optionStrings);
        if (!(options == null || options[c.ANCHOR.ordinal()] == null)) {
            out.d();
        }
        if (this.j) {
            a(scope, new org.b.a.b.c(scope, start, out.e() - 1, b(scope), c(scope)));
        }
        return n;
    }

    private int a(i out, c scope, Object o, String[] options) {
        int n = 0;
        if (o == null) {
            if (options == null || options[c.NULL.ordinal()] == null) {
                return 0;
            }
            o = options[c.NULL.ordinal()];
        }
        if (o instanceof f) {
            c scope2 = new c(scope, (f) o);
            if (!(options == null || options[c.WRAP.ordinal()] == null)) {
                try {
                    out.d(options[c.WRAP.ordinal()]);
                } catch (Throwable ioe) {
                    this.g.a(scope2.b, g.WRITE_IO_ERROR, ioe);
                }
            }
            n = a(out, scope2);
            scope = scope2;
        } else {
            o = f(scope, o);
            try {
                if (!(o instanceof Iterator)) {
                    String a;
                    if (options != null) {
                        c.FORMAT.ordinal();
                    }
                    a b = scope.b.b.k.b(o.getClass());
                    if (b != null) {
                        a = b.a();
                    } else {
                        a = o.toString();
                    }
                    if (options == null || options[c.WRAP.ordinal()] == null) {
                        n = out.b(a);
                    } else {
                        n = out.a(a, options[c.WRAP.ordinal()]);
                    }
                } else if (o == null) {
                    n = 0;
                } else {
                    Iterator it = (Iterator) o;
                    String str = null;
                    if (options != null) {
                        str = options[c.SEPARATOR.ordinal()];
                    }
                    int i = 0;
                    int i2 = 0;
                    while (it.hasNext()) {
                        Object next = it.next();
                        int i3 = (i == 0 || str == null || (next == null && options[c.NULL.ordinal()] == null)) ? 0 : 1;
                        if (i3 != 0) {
                            i2 += out.c(str);
                        }
                        i3 = a(out, scope, next, options);
                        if (i3 > 0) {
                            i = 1;
                        }
                        i2 += i3;
                    }
                    n = i2;
                }
            } catch (IOException ioe2) {
                this.g.b.c(new o(g.WRITE_IO_ERROR, scope.b, ioe2, o));
            }
        }
        return n;
    }

    private static int b(c scope) {
        h templateLocation = scope.b.b.r[scope.c];
        if (templateLocation != null) {
            return templateLocation.a;
        }
        return -1;
    }

    private static int c(c scope) {
        h templateLocation = scope.b.b.r[scope.c];
        if (templateLocation != null) {
            return templateLocation.b;
        }
        return -1;
    }

    private void a(c scope, Object attr, List<f> prototypes) {
        int i = 0;
        Object[] objArr;
        if (attr == null) {
            objArr = this.b;
            i = this.c + 1;
            this.c = i;
            objArr[i] = null;
            return;
        }
        attr = f(scope, attr);
        if (attr instanceof Iterator) {
            Iterator it = (Iterator) attr;
            List<f> mapped = new ArrayList();
            int i2 = 1;
            int i3 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                if (next == null) {
                    mapped.add(null);
                } else {
                    int i4 = i + 1;
                    f a = h.a((f) prototypes.get(i % prototypes.size()));
                    a(scope, a, next);
                    if (a.b.n) {
                        a.b("i0", Integer.valueOf(i3));
                        a.b("i", Integer.valueOf(i2));
                    }
                    mapped.add(a);
                    i3++;
                    i2++;
                    i = i4;
                }
            }
            objArr = this.b;
            i = this.c + 1;
            this.c = i;
            objArr[i] = mapped;
            return;
        }
        f st = h.a((f) prototypes.get(0));
        a(scope, st, attr);
        if (st.b.n) {
            st.b("i0", Integer.valueOf(0));
            st.b("i", Integer.valueOf(1));
        }
        objArr = this.b;
        i = this.c + 1;
        this.c = i;
        objArr[i] = st;
    }

    private org.b.a.f.a a(c scope, List<Object> exprs, f prototype) {
        if (prototype == null || exprs.size() == 0) {
            return null;
        }
        int i;
        for (i = 0; i < exprs.size(); i++) {
            Object attr = exprs.get(i);
            if (attr != null) {
                Object f = f(scope, attr);
                if (f instanceof Iterator) {
                    f = (Iterator) f;
                } else {
                    List aVar = new org.b.a.f.a(1);
                    aVar.add(f);
                    f = aVar.iterator();
                }
                exprs.set(i, f);
            }
        }
        int numExprs = exprs.size();
        e code = prototype.b;
        Map<String, org.b.a.a.g> formalArguments = code.g;
        if (!code.h || formalArguments == null) {
            int i2;
            f fVar = this.g;
            g gVar = g.MISSING_FORMAL_ARGUMENTS;
            g gVar2 = fVar.b;
            if (scope != null) {
                i2 = scope.c;
            } else {
                i2 = 0;
            }
            gVar2.b(new s(this, gVar, i2, scope));
            return null;
        }
        String[] formalArgumentNames = (String[]) formalArguments.keySet().toArray(new String[formalArguments.size()]);
        int nformalArgs = formalArgumentNames.length;
        if (prototype.b.n) {
            nformalArgs -= a.size();
        }
        if (nformalArgs != numExprs) {
            this.g.a(this, scope, g.MAP_ARGUMENT_COUNT_MISMATCH, Integer.valueOf(numExprs), Integer.valueOf(nformalArgs));
            int shorterSize = Math.min(formalArgumentNames.length, numExprs);
            numExprs = shorterSize;
            Object newFormalArgumentNames = new String[shorterSize];
            System.arraycopy(formalArgumentNames, 0, newFormalArgumentNames, 0, shorterSize);
            formalArgumentNames = newFormalArgumentNames;
        }
        org.b.a.f.a results = new org.b.a.f.a();
        i = 0;
        while (true) {
            int numEmpty = 0;
            f embedded = h.a(prototype);
            embedded.b("i0", Integer.valueOf(i));
            embedded.b("i", Integer.valueOf(i + 1));
            for (int a = 0; a < numExprs; a++) {
                Iterator<?> it = (Iterator) exprs.get(a);
                if (it == null || !it.hasNext()) {
                    numEmpty++;
                } else {
                    embedded.b(formalArgumentNames[a], it.next());
                }
            }
            if (numEmpty == numExprs) {
                return results;
            }
            results.add(embedded);
            i++;
        }
    }

    private void a(c scope, f st, Object attr) {
        if (!st.b.h && st.b.g == null) {
            st.a("it", attr);
        } else if (st.b.g == null) {
            this.g.a(this, scope, g.ARGUMENT_COUNT_MISMATCH, Integer.valueOf(1), st.b.a, Integer.valueOf(0));
        } else {
            st.c[0] = attr;
        }
    }

    private static void a(c scope, List<Object> list, Object o) {
        Iterator<?> o2 = f(scope, o);
        if (o2 instanceof Iterator) {
            Iterator<?> it = o2;
            while (it.hasNext()) {
                list.add(it.next());
            }
            return;
        }
        list.add(o2);
    }

    private static Object a(c scope, Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof List) {
            return ((List) v).get(((List) v).size() - 1);
        }
        if (v.getClass().isArray()) {
            return Array.get(v, Array.getLength(v) - 1);
        }
        Object last = v;
        Iterator<?> v2 = f(scope, v);
        if (!(v2 instanceof Iterator)) {
            return last;
        }
        Iterator<?> it = v2;
        while (it.hasNext()) {
            last = it.next();
        }
        return last;
    }

    private static Object b(c scope, Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof List) {
            List<?> elems = (List) v;
            if (elems.size() <= 1) {
                return null;
            }
            return elems.subList(1, elems.size());
        }
        Iterator<?> v2 = f(scope, v);
        if (!(v2 instanceof Iterator)) {
            return null;
        }
        Object a = new ArrayList();
        Iterator<?> it = v2;
        if (!it.hasNext()) {
            return null;
        }
        it.next();
        while (it.hasNext()) {
            a.add(it.next());
        }
        return a;
    }

    private static Object c(c scope, Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof List) {
            List<?> elems = (List) v;
            if (elems.size() > 1) {
                return elems.subList(0, elems.size() - 1);
            }
            return null;
        }
        Iterator<?> v2 = f(scope, v);
        if (!(v2 instanceof Iterator)) {
            return null;
        }
        Object a = new ArrayList();
        Iterator<?> it = v2;
        while (it.hasNext()) {
            Object o = it.next();
            if (it.hasNext()) {
                a.add(o);
            }
        }
        return a;
    }

    private static Object d(c scope, Object v) {
        if (v == null) {
            return null;
        }
        Iterator<?> v2 = f(scope, v);
        if (!(v2 instanceof Iterator)) {
            return v2;
        }
        Object a = new ArrayList();
        Iterator<?> it = v2;
        while (it.hasNext()) {
            Object o = it.next();
            if (o != null) {
                a.add(o);
            }
        }
        return a;
    }

    private static Object e(c scope, Object v) {
        if (v == null) {
            return null;
        }
        Iterator<?> v2 = f(scope, v);
        if (!(v2 instanceof Iterator)) {
            return v2;
        }
        Object a = new LinkedList();
        Iterator<?> it = v2;
        while (it.hasNext()) {
            a.add(0, it.next());
        }
        return a;
    }

    private static Object a(Object v) {
        if (v == null) {
            return Integer.valueOf(0);
        }
        int i = 1;
        if (v instanceof Map) {
            i = ((Map) v).size();
        } else if (v instanceof Collection) {
            i = ((Collection) v).size();
        } else if (v instanceof Object[]) {
            i = ((Object[]) v).length;
        } else if (v.getClass().isArray()) {
            i = Array.getLength(v);
        } else if (v instanceof Iterator) {
            Iterator<?> it = (Iterator) v;
            i = 0;
            while (it.hasNext()) {
                it.next();
                i++;
            }
        }
        return Integer.valueOf(i);
    }

    private String b(i out, c scope, Object value) {
        if (value == null) {
            return null;
        }
        if (value.getClass() == String.class) {
            return (String) value;
        }
        i stw;
        StringWriter sw = new StringWriter();
        try {
            stw = (i) out.getClass().getConstructor(new Class[]{Writer.class}).newInstance(new Object[]{sw});
        } catch (Exception e) {
            stw = new b(sw);
            this.g.a(this, scope, g.WRITER_CTOR_ISSUE, out.getClass().getSimpleName());
        }
        if (this.j && !scope.f) {
            c scope2 = new c(scope, scope.b);
            scope2.f = true;
            scope = scope2;
        }
        a(stw, scope, value);
        return sw.toString();
    }

    private static Object f(c scope, Object o) {
        Iterator<?> iter = null;
        if (o == null) {
            return null;
        }
        if (o instanceof Collection) {
            iter = ((Collection) o).iterator();
        } else if (o instanceof Object[]) {
            iter = Arrays.asList((Object[]) o).iterator();
        } else if (o.getClass().isArray()) {
            iter = new org.b.a.c.d(o);
        } else if (o instanceof Map) {
            if (scope.b.d.p) {
                iter = ((Map) o).values().iterator();
            } else {
                iter = ((Map) o).keySet().iterator();
            }
        }
        if (iter != null) {
            return iter;
        }
        return o;
    }

    private static boolean b(Object a) {
        if (a == null) {
            return false;
        }
        if (a instanceof Boolean) {
            return ((Boolean) a).booleanValue();
        }
        if (a instanceof Collection) {
            if (((Collection) a).size() > 0) {
                return true;
            }
            return false;
        } else if (!(a instanceof Map)) {
            return a instanceof Iterator ? ((Iterator) a).hasNext() : true;
        } else {
            if (((Map) a).size() > 0) {
                return true;
            }
            return false;
        }
    }

    private Object a(i out, c scope, Object o, Object property) {
        if (o == null) {
            this.g.a(this, scope, g.NO_SUCH_PROPERTY, "null." + property);
            return null;
        }
        try {
            f self = scope.b;
            return self.d.a(o.getClass()).a(this, self, o, property, b(out, scope, property));
        } catch (r e) {
            int i;
            f fVar = this.g;
            g gVar = g.NO_SUCH_PROPERTY;
            String str = o.getClass().getName() + "." + property;
            g gVar2 = fVar.b;
            if (scope != null) {
                i = scope.c;
            } else {
                i = 0;
            }
            gVar2.b(new s(this, gVar, i, scope, e, str));
            return null;
        }
    }

    private Object a(c scope, String name) {
        for (c current = scope; current != null; current = current.a) {
            f p = current.b;
            org.b.a.a.g localArg = null;
            if (p.b.g != null) {
                localArg = (org.b.a.a.g) p.b.g.get(name);
            }
            if (localArg != null) {
                return p.c[localArg.b];
            }
        }
        Object o = a(scope.b.b.k, name);
        if (o != null) {
            return o;
        }
        throw new q(name, scope);
    }

    private Object a(h g, String name) {
        if (g.d(name)) {
            return g.c(name);
        }
        if (g.d != null) {
            for (h sup : g.d) {
                Object o = a(sup, name);
                if (o != null) {
                    return o;
                }
            }
        }
        return null;
    }

    private void c(i out, c scope) {
        f invokedST = scope.b;
        if (invokedST.b.g != null && invokedST.b.i != 0) {
            for (org.b.a.a.g arg : invokedST.b.g.values()) {
                if (invokedST.c[arg.b] == f.a && arg.c != null) {
                    if (arg.c.a() == 4) {
                        e code = arg.e;
                        if (code == null) {
                            code = new e();
                        }
                        f defaultArgST = this.e.a(code);
                        defaultArgST.d = this.e;
                        String defArgTemplate = arg.c.b();
                        if (defArgTemplate.startsWith("{" + this.e.f + "(") && defArgTemplate.endsWith(")" + this.e.g + "}")) {
                            invokedST.b(arg.a, b(out, new c(scope, invokedST), defaultArgST));
                        } else {
                            invokedST.b(arg.a, defaultArgST);
                        }
                    } else {
                        invokedST.b(arg.a, arg.d);
                    }
                }
            }
        }
    }

    private void a(StringBuilder tr, c scope, Object o) {
        if (!(o instanceof f)) {
            Iterator<?> o2 = f(scope, o);
            if (o2 instanceof Iterator) {
                Iterator<?> it = o2;
                tr.append(" [");
                while (it.hasNext()) {
                    a(tr, scope, it.next());
                }
                tr.append(" ]");
                return;
            }
            tr.append(" " + o2);
        } else if (((f) o).b == null) {
            tr.append("bad-template()");
        } else {
            tr.append(" " + ((f) o).b.a + "()");
        }
    }

    private void a(c scope, org.b.a.b.f e) {
        this.k.add(e);
        scope.d.add(e);
        if ((e instanceof org.b.a.b.d) && scope.a != null) {
            scope.a.e.add((org.b.a.b.d) e);
        }
    }

    private static int a(byte[] memory, int index) {
        return ((memory[index] & 255) << 8) | (memory[index + 1] & 255);
    }

    public static String a(c scope) {
        List<f> templates = new LinkedList();
        while (scope != null) {
            templates.add(0, scope.b);
            scope = scope.a;
        }
        StringBuilder buf = new StringBuilder();
        int i = 0;
        for (f st : templates) {
            if (i > 0) {
                buf.append(" ");
            }
            buf.append(st.b.a);
            i++;
        }
        return buf.toString();
    }
}
