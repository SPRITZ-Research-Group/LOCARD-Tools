package com.google.a.c.a;

final class n extends c {
    n() {
    }

    public final int a() {
        return 3;
    }

    public final void a(h context) {
        StringBuilder buffer = new StringBuilder();
        while (context.h()) {
            char c = context.c();
            context.a++;
            a(c, buffer);
            if (buffer.length() % 3 == 0) {
                c.a(context, buffer);
                if (j.a(context.a(), context.a, 3) != 3) {
                    context.a(0);
                    break;
                }
            }
        }
        b(context, buffer);
    }

    final int a(char c, StringBuilder sb) {
        switch (c) {
            case 13:
                sb.append(0);
                break;
            case ' ':
                sb.append(3);
                break;
            case '*':
                sb.append(1);
                break;
            case '>':
                sb.append(2);
                break;
            default:
                if (c < '0' || c > '9') {
                    if (c >= 'A' && c <= 'Z') {
                        sb.append((char) ((c - 65) + 14));
                        break;
                    }
                    j.c(c);
                    break;
                }
                sb.append((char) ((c - 48) + 4));
                break;
                break;
        }
        return 1;
    }

    final void b(h context, StringBuilder buffer) {
        context.k();
        int available = context.j().f() - context.e();
        context.a -= buffer.length();
        if (context.i() > 1 || available > 1 || context.i() != available) {
            context.a(254);
        }
        if (context.f() < 0) {
            context.a(0);
        }
    }
}
