package org.a.a;

public abstract class b {
    protected u a;

    public abstract String d();

    public b(u state) {
        if (state == null) {
            state = new u();
        }
        this.a = state;
    }

    public final Object a(k input, int ttype, c follow) throws t {
        Object matchedSymbol = b(input);
        if (input.a(1) == ttype) {
            input.a();
            this.a.c = false;
            this.a.e = false;
            return matchedSymbol;
        } else if (this.a.g <= 0) {
            return b(input, ttype, follow);
        } else {
            this.a.e = true;
            return matchedSymbol;
        }
    }

    public final void a(t e) {
        if (!this.a.c) {
            u uVar = this.a;
            uVar.f++;
            this.a.c = true;
            String[] b = b();
            String b2 = b(e);
            System.err.println(b2 + " " + a(e, b));
        }
    }

    public String a(t e, String[] tokenNames) {
        String msg = e.getMessage();
        String tokenName;
        if (e instanceof z) {
            z ute = (z) e;
            if (ute.a == -1) {
                tokenName = "EOF";
            } else {
                tokenName = tokenNames[ute.a];
            }
            return "extraneous input " + a(ute.f) + " expecting " + tokenName;
        } else if (e instanceof p) {
            p mte = (p) e;
            if (mte.a == -1) {
                tokenName = "EOF";
            } else {
                tokenName = tokenNames[mte.a];
            }
            return "missing " + tokenName + " at " + a(e.f);
        } else if (e instanceof n) {
            n mte2 = (n) e;
            if (mte2.a == -1) {
                tokenName = "EOF";
            } else {
                tokenName = tokenNames[mte2.a];
            }
            return "mismatched input " + a(e.f) + " expecting " + tokenName;
        } else if (e instanceof o) {
            o mtne = (o) e;
            if (mtne.a == -1) {
                tokenName = "EOF";
            } else {
                tokenName = tokenNames[mtne.a];
            }
            return "mismatched tree node: " + mtne.g + " expecting " + tokenName;
        } else if (e instanceof q) {
            return "no viable alternative at input " + a(e.f);
        } else {
            if (e instanceof i) {
                return "required (...)+ loop did not match anything at input " + a(e.f);
            }
            if (e instanceof m) {
                return "mismatched input " + a(e.f) + " expecting set " + ((m) e).a;
            } else if (e instanceof l) {
                return "mismatched input " + a(e.f) + " expecting set " + ((l) e).a;
            } else if (!(e instanceof j)) {
                return msg;
            } else {
                j fpe = (j) e;
                return "rule " + fpe.a + " failed predicate: {" + fpe.b + "}?";
            }
        }
    }

    public final int a() {
        return this.a.f;
    }

    public String b(t e) {
        if (d() != null) {
            return d() + " line " + e.i + ":" + e.j;
        }
        return "line " + e.i + ":" + e.j;
    }

    private static String a(w t) {
        String s = t.b();
        if (s == null) {
            if (t.a() == -1) {
                s = "<EOF>";
            } else {
                s = "<" + t.a() + ">";
            }
        }
        return "'" + s.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\t", "\\\\t") + "'";
    }

    public final void a(k input) {
        if (this.a.d == input.b()) {
            input.a();
        }
        this.a.d = input.b();
        c followSet = a(false);
        int a = input.a(1);
        while (a != -1 && !followSet.a(a)) {
            input.a();
            a = input.a(1);
        }
    }

    private c a(boolean exact) {
        int top = this.a.b;
        c followSet = new c();
        for (int i = top; i >= 0; i--) {
            c localFollowSet = this.a.a[i];
            followSet.a(localFollowSet);
            if (exact) {
                if (!localFollowSet.a(1)) {
                    break;
                } else if (i > 0) {
                    followSet.a();
                }
            }
        }
        return followSet;
    }

    protected Object b(k input, int ttype, c follow) throws t {
        boolean z;
        if (input.a(2) == ttype) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            t e = new z(ttype, input);
            input.a();
            a(e);
            Object matchedSymbol = b(input);
            input.a();
            return matchedSymbol;
        }
        Object inserted;
        if (follow != null) {
            c cVar;
            if (follow.a(1)) {
                cVar = (c) follow.clone();
                cVar.a(a(true));
                if (this.a.b >= 0) {
                    cVar.a();
                }
            } else {
                cVar = follow;
            }
            if (cVar.a(input.a(1)) || cVar.a(1)) {
                z = true;
                if (z) {
                    throw new n(ttype, input);
                }
                inserted = a(input, ttype);
                a(new p(ttype, input, inserted));
                return inserted;
            }
        }
        z = false;
        if (z) {
            throw new n(ttype, input);
        }
        inserted = a(input, ttype);
        a(new p(ttype, input, inserted));
        return inserted;
    }

    protected Object b(k input) {
        return null;
    }

    protected Object a(k input, int expectedTokenType) {
        return null;
    }

    protected final void a(c fset) {
        if (this.a.b + 1 >= this.a.a.length) {
            c[] f = new c[(this.a.a.length * 2)];
            System.arraycopy(this.a.a, 0, f, 0, this.a.a.length);
            this.a.a = f;
        }
        c[] cVarArr = this.a.a;
        u uVar = this.a;
        int i = uVar.b + 1;
        uVar.b = i;
        cVarArr[i] = fset;
    }

    public String[] b() {
        return null;
    }

    public String c() {
        return null;
    }
}
