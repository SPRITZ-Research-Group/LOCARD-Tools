package com.google.a.c.a;

final class f implements g {
    f() {
    }

    public final void a(h context) {
        int i = 1;
        CharSequence buffer = new StringBuilder();
        while (context.h()) {
            char c = context.c();
            if (c >= ' ' && c <= '?') {
                buffer.append(c);
            } else if (c < '@' || c > '^') {
                j.c(c);
            } else {
                buffer.append((char) (c - 64));
            }
            context.a++;
            if (buffer.length() >= 4) {
                context.a(a(buffer));
                buffer.delete(0, 4);
                if (j.a(context.a(), context.a, 4) != 4) {
                    context.a(0);
                    break;
                }
            }
        }
        buffer.append(31);
        try {
            int length = buffer.length();
            if (length != 0) {
                int f;
                if (length == 1) {
                    context.k();
                    f = context.j().f() - context.e();
                    if (context.i() <= f && f <= 2) {
                        context.a(0);
                        return;
                    }
                }
                if (length > 4) {
                    throw new IllegalStateException("Count must not exceed 4");
                }
                f = length - 1;
                String a = a(buffer);
                if ((!context.h() ? 1 : 0) == 0 || f > 2) {
                    i = 0;
                }
                if (f <= 2) {
                    context.b(context.e() + f);
                    if (context.j().f() - context.e() >= 3) {
                        context.b(context.e() + a.length());
                        i = 0;
                    }
                }
                if (i != 0) {
                    context.l();
                    context.a -= f;
                } else {
                    context.a(a);
                }
                context.a(0);
            }
        } finally {
            context.a(0);
        }
    }

    private static String a(CharSequence sb) {
        char c4 = 0;
        int len = sb.length() + 0;
        if (len == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        char c2;
        char c3;
        char c1 = sb.charAt(0);
        if (len >= 2) {
            c2 = sb.charAt(1);
        } else {
            c2 = 0;
        }
        if (len >= 3) {
            c3 = sb.charAt(2);
        } else {
            c3 = 0;
        }
        if (len >= 4) {
            c4 = sb.charAt(3);
        }
        int v = (((c1 << 18) + (c2 << 12)) + (c3 << 6)) + c4;
        char cw1 = (char) ((v >> 16) & 255);
        char cw2 = (char) ((v >> 8) & 255);
        char cw3 = (char) (v & 255);
        StringBuilder res = new StringBuilder(3);
        res.append(cw1);
        if (len >= 2) {
            res.append(cw2);
        }
        if (len >= 3) {
            res.append(cw3);
        }
        return res.toString();
    }
}
