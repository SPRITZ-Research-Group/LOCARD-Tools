package com.google.a.c.a;

import com.skype.Defines;

class c implements g {
    c() {
    }

    public int a() {
        return 1;
    }

    public void a(h context) {
        StringBuilder buffer = new StringBuilder();
        while (context.h()) {
            char c = context.c();
            context.a++;
            int lastCharSize = a(c, buffer);
            int curCodewordCount = context.e() + ((buffer.length() / 3) << 1);
            context.b(curCodewordCount);
            int available = context.j().f() - curCodewordCount;
            if (context.h()) {
                if (buffer.length() % 3 == 0 && j.a(context.a(), context.a, a()) != a()) {
                    context.a(0);
                    break;
                }
            } else {
                StringBuilder removed = new StringBuilder();
                if (buffer.length() % 3 == 2 && (available < 2 || available > 2)) {
                    lastCharSize = a(context, buffer, removed, lastCharSize);
                }
                while (buffer.length() % 3 == 1 && ((lastCharSize <= 3 && available != 1) || lastCharSize > 3)) {
                    lastCharSize = a(context, buffer, removed, lastCharSize);
                }
            }
        }
        b(context, buffer);
    }

    private int a(h context, StringBuilder buffer, StringBuilder removed, int lastCharSize) {
        int count = buffer.length();
        buffer.delete(count - lastCharSize, count);
        context.a--;
        lastCharSize = a(context.c(), removed);
        context.l();
        return lastCharSize;
    }

    static void a(h context, StringBuilder buffer) {
        char charAt = (char) (((((buffer.charAt(0) * 1600) + (buffer.charAt(1) * 40)) + buffer.charAt(2)) + 1) % Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE);
        context.a(new String(new char[]{(char) (r0 / Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE), charAt}));
        buffer.delete(0, 3);
    }

    void b(h context, StringBuilder buffer) {
        int rest = buffer.length() % 3;
        int curCodewordCount = context.e() + ((buffer.length() / 3) << 1);
        context.b(curCodewordCount);
        int available = context.j().f() - curCodewordCount;
        if (rest == 2) {
            buffer.append(0);
            while (buffer.length() >= 3) {
                a(context, buffer);
            }
            if (context.h()) {
                context.a(254);
            }
        } else if (available == 1 && rest == 1) {
            while (buffer.length() >= 3) {
                a(context, buffer);
            }
            if (context.h()) {
                context.a(254);
            }
            context.a--;
        } else if (rest == 0) {
            while (buffer.length() >= 3) {
                a(context, buffer);
            }
            if (available > 0 || context.h()) {
                context.a(254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        context.a(0);
    }

    int a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append(3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - 48) + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 65) + 14));
            return 1;
        } else if (c >= 0 && c <= 31) {
            sb.append(0);
            sb.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            sb.append(1);
            sb.append((char) (c - 33));
            return 2;
        } else if (c >= ':' && c <= '@') {
            sb.append(1);
            sb.append((char) ((c - 58) + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            sb.append(1);
            sb.append((char) ((c - 91) + 22));
            return 2;
        } else if (c >= '`' && c <= 127) {
            sb.append(2);
            sb.append((char) (c - 96));
            return 2;
        } else if (c >= 128) {
            sb.append("\u0001\u001e");
            return a((char) (c - 128), sb) + 2;
        } else {
            throw new IllegalArgumentException("Illegal character: " + c);
        }
    }
}
