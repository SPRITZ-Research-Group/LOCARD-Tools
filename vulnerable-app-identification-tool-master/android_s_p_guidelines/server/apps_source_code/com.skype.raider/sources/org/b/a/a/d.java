package org.b.a.a;

import org.a.a.b.c;
import org.a.a.g;
import org.a.a.y;
import org.b.a.c.f;
import org.b.a.c.h;
import org.b.a.c.j;

public final class d {
    e a = new e();
    k b = new k();
    int c = 0;
    y d;
    f e;

    public d(f errMgr, String name, y tokens) {
        this.e = errMgr;
        this.d = tokens;
        this.a.a = name;
        this.a.b = j.c(name);
    }

    public final int a(String s) {
        return this.b.a(s);
    }

    public final void a() {
        a(null, (short) 40);
    }

    public final void a(c opAST, short opcode) {
        a(1);
        if (opAST != null) {
            int i = opAST.j();
            int j = opAST.k();
            int p = ((g) this.d.e(i)).f();
            int q = ((g) this.d.e(j)).g();
            if (p >= 0 && q >= 0) {
                this.a.r[this.c] = new h(p, q);
            }
        }
        byte[] bArr = this.a.p;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = (byte) opcode;
    }

    public final void a(c opAST, short opcode, int arg) {
        a(opAST, opcode);
        a(2);
        a(this.a.p, this.c, (short) arg);
        this.c += 2;
    }

    public final void a(c opAST, short opcode, int arg, int arg2) {
        a(opAST, opcode);
        a(4);
        a(this.a.p, this.c, (short) arg);
        this.c += 2;
        a(this.a.p, this.c, (short) arg2);
        this.c += 2;
    }

    public final void a(c opAST, short opcode, String s) {
        a(opAST, opcode, a(s));
    }

    private void a(int n) {
        if (this.c + n >= this.a.p.length) {
            byte[] c = new byte[(this.a.p.length * 2)];
            System.arraycopy(this.a.p, 0, c, 0, this.a.p.length);
            this.a.p = c;
            h[] sm = new h[(this.a.r.length * 2)];
            System.arraycopy(this.a.r, 0, sm, 0, this.a.r.length);
            this.a.r = sm;
        }
    }

    public final void a(c indent) {
        a(indent, (short) 39, indent.i());
    }

    public static void a(byte[] memory, int index, short value) {
        memory[index + 0] = (byte) ((value >> 8) & 255);
        memory[index + 1] = (byte) (value & 255);
    }
}
