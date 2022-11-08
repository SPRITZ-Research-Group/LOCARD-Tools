package com.google.a.d;

import com.google.a.a;
import com.google.a.b.b;
import com.google.a.c;
import com.google.a.g;
import com.google.a.h;
import java.util.Map;

public abstract class n implements g {
    public abstract boolean[] a(String str);

    public b a(String contents, a format, int width, int height, Map<c, ?> hints) throws h {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + width + 'x' + height);
        } else {
            int sidesMargin = a();
            if (hints != null && hints.containsKey(c.MARGIN)) {
                sidesMargin = Integer.parseInt(hints.get(c.MARGIN).toString());
            }
            return a(a(contents), width, height, sidesMargin);
        }
    }

    private static b a(boolean[] code, int width, int height, int sidesMargin) {
        int inputWidth = code.length;
        int fullWidth = inputWidth + sidesMargin;
        int outputWidth = Math.max(width, fullWidth);
        int outputHeight = Math.max(1, height);
        int multiple = outputWidth / fullWidth;
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        b output = new b(outputWidth, outputHeight);
        int inputX = 0;
        int outputX = leftPadding;
        while (inputX < inputWidth) {
            if (code[inputX]) {
                output.a(outputX, 0, multiple, outputHeight);
            }
            inputX++;
            outputX += multiple;
        }
        return output;
    }

    protected static int a(boolean[] target, int pos, int[] pattern, boolean startColor) {
        boolean color = startColor;
        int numAdded = 0;
        int length = pattern.length;
        int i = 0;
        while (i < length) {
            int len = pattern[i];
            int j = 0;
            int pos2 = pos;
            while (j < len) {
                pos = pos2 + 1;
                target[pos2] = color;
                j++;
                pos2 = pos;
            }
            numAdded += len;
            color = !color;
            i++;
            pos = pos2;
        }
        return numAdded;
    }

    public int a() {
        return 10;
    }
}
