package com.google.a.c.a;

final class b implements g {
    b() {
    }

    public final void a(h context) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(0);
        while (context.h()) {
            buffer.append(context.c());
            context.a++;
            if (j.a(context.a(), context.a, 5) != 5) {
                context.a(0);
                break;
            }
        }
        int dataCount = buffer.length() - 1;
        int currentSize = (context.e() + dataCount) + 1;
        context.b(currentSize);
        boolean mustPad;
        if (context.j().f() - currentSize > 0) {
            mustPad = true;
        } else {
            mustPad = false;
        }
        if (context.h() || mustPad) {
            if (dataCount <= 249) {
                buffer.setCharAt(0, (char) dataCount);
            } else if (dataCount <= 1555) {
                buffer.setCharAt(0, (char) ((dataCount / 250) + 249));
                buffer.insert(1, (char) (dataCount % 250));
            } else {
                throw new IllegalStateException("Message length not in valid ranges: " + dataCount);
            }
        }
        int c = buffer.length();
        for (int i = 0; i < c; i++) {
            int charAt = buffer.charAt(i) + ((((context.e() + 1) * 149) % 255) + 1);
            if (charAt > 255) {
                charAt -= 256;
            }
            context.a((char) charAt);
        }
    }
}
