package com.google.a.c.a;

final class a implements g {
    a() {
    }

    public final void a(h context) {
        char charAt;
        int i;
        CharSequence a = context.a();
        int i2 = context.a;
        int length = a.length();
        if (i2 < length) {
            charAt = a.charAt(i2);
            i = 0;
            while (j.a(charAt) && i2 < length) {
                i++;
                i2++;
                if (i2 < length) {
                    charAt = a.charAt(i2);
                }
            }
        } else {
            i = 0;
        }
        if (i >= 2) {
            charAt = context.a().charAt(context.a);
            char charAt2 = context.a().charAt(context.a + 1);
            if (j.a(charAt) && j.a(charAt2)) {
                context.a((char) ((((charAt - 48) * 10) + (charAt2 - 48)) + 130));
                context.a += 2;
                return;
            }
            throw new IllegalArgumentException("not digits: " + charAt + charAt2);
        }
        char c = context.c();
        int newMode = j.a(context.a(), context.a, 0);
        if (newMode != 0) {
            switch (newMode) {
                case 1:
                    context.a(230);
                    context.a(1);
                    return;
                case 2:
                    context.a(239);
                    context.a(2);
                    return;
                case 3:
                    context.a(238);
                    context.a(3);
                    return;
                case 4:
                    context.a(240);
                    context.a(4);
                    return;
                case 5:
                    context.a(231);
                    context.a(5);
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: " + newMode);
            }
        } else if (j.b(c)) {
            context.a(235);
            context.a((char) ((c - 128) + 1));
            context.a++;
        } else {
            context.a((char) (c + 1));
            context.a++;
        }
    }
}
