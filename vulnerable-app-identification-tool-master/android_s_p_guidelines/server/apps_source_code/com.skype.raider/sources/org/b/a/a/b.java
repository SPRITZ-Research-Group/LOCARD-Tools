package org.b.a.a;

import java.util.ArrayList;
import java.util.List;
import org.b.a.a.a.a;
import org.b.a.c.h;
import org.b.a.c.j;

public final class b {
    e a;

    public b(e code) {
        this.a = code;
    }

    public final String a() {
        StringBuilder buf = new StringBuilder();
        int i = 0;
        while (i < this.a.q) {
            i = a(buf, i);
            buf.append(10);
        }
        return buf.toString();
    }

    public final int a(StringBuilder buf, int ip) {
        int opcode = this.a.p[ip];
        if (ip >= this.a.q) {
            throw new IllegalArgumentException("ip out of range: " + ip);
        }
        a I = a.a[opcode];
        if (I == null) {
            throw new IllegalArgumentException("no such instruction " + opcode + " at address " + ip);
        }
        String instrName = I.a;
        buf.append(String.format("%04d:\t%-14s", new Object[]{Integer.valueOf(ip), instrName}));
        ip++;
        if (I.c == 0) {
            buf.append("  ");
            return ip;
        }
        int i;
        List<String> operands = new ArrayList();
        for (i = 0; i < I.c; i++) {
            byte[] bArr = this.a.p;
            int opnd = ((bArr[ip] & 255) << 8) | (bArr[ip + 1] & 255);
            ip += 2;
            switch (I.b[i]) {
                case STRING:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("#");
                    stringBuilder.append(opnd);
                    String str = "<bad string index>";
                    if (opnd < this.a.o.length) {
                        if (this.a.o[opnd] == null) {
                            str = "null";
                        } else {
                            str = this.a.o[opnd].toString();
                            if (this.a.o[opnd] instanceof String) {
                                str = "\"" + j.d(str) + '\"';
                            }
                        }
                    }
                    stringBuilder.append(":");
                    stringBuilder.append(str);
                    operands.add(stringBuilder.toString());
                    break;
                case ADDR:
                case INT:
                    operands.add(String.valueOf(opnd));
                    break;
                default:
                    operands.add(String.valueOf(opnd));
                    break;
            }
        }
        for (i = 0; i < operands.size(); i++) {
            String s = (String) operands.get(i);
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(s);
        }
        return ip;
    }

    public final String b() {
        StringBuilder buf = new StringBuilder();
        int addr = 0;
        if (this.a.o != null) {
            for (String o : this.a.o) {
                if (o instanceof String) {
                    String s = j.d(o);
                    buf.append(String.format("%04d: \"%s\"\n", new Object[]{Integer.valueOf(addr), s}));
                } else {
                    buf.append(String.format("%04d: %s\n", new Object[]{Integer.valueOf(addr), o}));
                }
                addr++;
            }
        }
        return buf.toString();
    }

    public final String c() {
        StringBuilder buf = new StringBuilder();
        int addr = 0;
        for (h I : this.a.r) {
            if (I != null) {
                String chunk = this.a.c.substring(I.a, I.b + 1);
                buf.append(String.format("%04d: %s\t\"%s\"\n", new Object[]{Integer.valueOf(addr), I, chunk}));
            }
            addr++;
        }
        return buf.toString();
    }
}
