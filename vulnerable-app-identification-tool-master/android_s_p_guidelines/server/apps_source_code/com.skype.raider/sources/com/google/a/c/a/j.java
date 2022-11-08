package com.google.a.c.a;

import com.google.a.b;
import java.util.Arrays;

public final class j {
    public static String a(String msg, l shape, b minSize, b maxSize) {
        g[] encoders = new g[]{new a(), new c(), new m(), new n(), new f(), new b()};
        h context = new h(msg);
        context.a(shape);
        context.a(minSize, maxSize);
        if (msg.startsWith("[)>\u001e05\u001d") && msg.endsWith("\u001e\u0004")) {
            context.a(236);
            context.b();
            context.a += 7;
        } else if (msg.startsWith("[)>\u001e06\u001d") && msg.endsWith("\u001e\u0004")) {
            context.a(237);
            context.b();
            context.a += 7;
        }
        int encodingMode = 0;
        while (context.h()) {
            encoders[encodingMode].a(context);
            if (context.f() >= 0) {
                encodingMode = context.f();
                context.g();
            }
        }
        int len = context.e();
        context.k();
        int capacity = context.j().f();
        if (!(len >= capacity || encodingMode == 0 || encodingMode == 5 || encodingMode == 4)) {
            context.a(254);
        }
        StringBuilder codewords = context.d();
        if (codewords.length() < capacity) {
            codewords.append(129);
        }
        while (codewords.length() < capacity) {
            int length = ((((codewords.length() + 1) * 149) % 253) + 1) + 129;
            if (length > 254) {
                length -= 254;
            }
            codewords.append((char) length);
        }
        return context.d().toString();
    }

    static int a(CharSequence msg, int startpos, int currentMode) {
        if (startpos >= msg.length()) {
            return currentMode;
        }
        float[] charCounts;
        int[] intCharCounts;
        byte[] mins;
        int minCount;
        if (currentMode == 0) {
            charCounts = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            charCounts = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            charCounts[currentMode] = 0.0f;
        }
        int charsProcessed = 0;
        while (startpos + charsProcessed != msg.length()) {
            Object obj;
            char c = msg.charAt(startpos + charsProcessed);
            charsProcessed++;
            if (a(c)) {
                charCounts[0] = charCounts[0] + 0.5f;
            } else if (b(c)) {
                charCounts[0] = (float) Math.ceil((double) charCounts[0]);
                charCounts[0] = charCounts[0] + 2.0f;
            } else {
                charCounts[0] = (float) Math.ceil((double) charCounts[0]);
                charCounts[0] = charCounts[0] + 1.0f;
            }
            if (c == ' ' || ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z'))) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                charCounts[1] = charCounts[1] + 0.6666667f;
            } else if (b(c)) {
                charCounts[1] = charCounts[1] + 2.6666667f;
            } else {
                charCounts[1] = charCounts[1] + 1.3333334f;
            }
            if (c == ' ' || ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z'))) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                charCounts[2] = charCounts[2] + 0.6666667f;
            } else if (b(c)) {
                charCounts[2] = charCounts[2] + 2.6666667f;
            } else {
                charCounts[2] = charCounts[2] + 1.3333334f;
            }
            if (d(c)) {
                charCounts[3] = charCounts[3] + 0.6666667f;
            } else if (b(c)) {
                charCounts[3] = charCounts[3] + 4.3333335f;
            } else {
                charCounts[3] = charCounts[3] + 3.3333333f;
            }
            if (c < ' ' || c > '^') {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj != null) {
                charCounts[4] = charCounts[4] + 0.75f;
            } else if (b(c)) {
                charCounts[4] = charCounts[4] + 4.25f;
            } else {
                charCounts[4] = charCounts[4] + 3.25f;
            }
            charCounts[5] = charCounts[5] + 1.0f;
            if (charsProcessed >= 4) {
                intCharCounts = new int[6];
                mins = new byte[6];
                a(charCounts, intCharCounts, Integer.MAX_VALUE, mins);
                minCount = a(mins);
                if (intCharCounts[0] < intCharCounts[5] && intCharCounts[0] < intCharCounts[1] && intCharCounts[0] < intCharCounts[2] && intCharCounts[0] < intCharCounts[3] && intCharCounts[0] < intCharCounts[4]) {
                    return 0;
                }
                if (intCharCounts[5] < intCharCounts[0] || ((mins[1] + mins[2]) + mins[3]) + mins[4] == 0) {
                    return 5;
                }
                if (minCount == 1 && mins[4] > (byte) 0) {
                    return 4;
                }
                if (minCount == 1 && mins[2] > (byte) 0) {
                    return 2;
                }
                if (minCount == 1 && mins[3] > (byte) 0) {
                    return 3;
                }
                if (intCharCounts[1] + 1 < intCharCounts[0] && intCharCounts[1] + 1 < intCharCounts[5] && intCharCounts[1] + 1 < intCharCounts[4] && intCharCounts[1] + 1 < intCharCounts[2]) {
                    if (intCharCounts[1] < intCharCounts[3]) {
                        return 1;
                    }
                    if (intCharCounts[1] == intCharCounts[3]) {
                        int p = (startpos + charsProcessed) + 1;
                        while (p < msg.length()) {
                            char tc = msg.charAt(p);
                            if (!e(tc)) {
                                if (!d(tc)) {
                                    break;
                                }
                                p++;
                            } else {
                                return 3;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
        mins = new byte[6];
        intCharCounts = new int[6];
        int min = a(charCounts, intCharCounts, Integer.MAX_VALUE, mins);
        minCount = a(mins);
        if (intCharCounts[0] == min) {
            return 0;
        }
        if (minCount == 1 && mins[5] > (byte) 0) {
            return 5;
        }
        if (minCount == 1 && mins[4] > (byte) 0) {
            return 4;
        }
        if (minCount == 1 && mins[2] > (byte) 0) {
            return 2;
        }
        if (minCount != 1 || mins[3] <= (byte) 0) {
            return 1;
        }
        return 3;
    }

    private static int a(float[] charCounts, int[] intCharCounts, int min, byte[] mins) {
        Arrays.fill(mins, (byte) 0);
        for (int i = 0; i < 6; i++) {
            intCharCounts[i] = (int) Math.ceil((double) charCounts[i]);
            int current = intCharCounts[i];
            if (min > current) {
                min = current;
                Arrays.fill(mins, (byte) 0);
            }
            if (min == current) {
                mins[i] = (byte) (mins[i] + 1);
            }
        }
        return min;
    }

    private static int a(byte[] mins) {
        int minCount = 0;
        for (int i = 0; i < 6; i++) {
            minCount += mins[i];
        }
        return minCount;
    }

    static boolean a(char ch) {
        return ch >= '0' && ch <= '9';
    }

    static boolean b(char ch) {
        return ch >= 128 && ch <= 255;
    }

    private static boolean d(char ch) {
        return e(ch) || ch == ' ' || ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z'));
    }

    private static boolean e(char ch) {
        return ch == 13 || ch == '*' || ch == '>';
    }

    static void c(char c) {
        String hex = Integer.toHexString(c);
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hex.length()) + hex) + ')');
    }
}
