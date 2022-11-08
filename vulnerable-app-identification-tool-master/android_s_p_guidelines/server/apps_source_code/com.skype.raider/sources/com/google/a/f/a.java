package com.google.a.f;

import com.google.a.b.b;
import com.google.a.c;
import com.google.a.f.b.f;
import com.google.a.g;
import com.google.a.h;
import java.util.Map;

public final class a implements g {
    public final b a(String contents, com.google.a.a format, int width, int height, Map<c, ?> hints) throws h {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (format != com.google.a.a.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + format);
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' + height);
        } else {
            com.google.a.f.a.a errorCorrectionLevel = com.google.a.f.a.a.L;
            int quietZone = 4;
            if (hints != null) {
                if (hints.containsKey(c.ERROR_CORRECTION)) {
                    errorCorrectionLevel = com.google.a.f.a.a.valueOf(hints.get(c.ERROR_CORRECTION).toString());
                }
                if (hints.containsKey(c.MARGIN)) {
                    quietZone = Integer.parseInt(hints.get(c.MARGIN).toString());
                }
            }
            return a(com.google.a.f.b.c.a(contents, errorCorrectionLevel, (Map) hints), width, height, quietZone);
        }
    }

    private static b a(f code, int width, int height, int quietZone) {
        com.google.a.f.b.b input = code.a();
        if (input == null) {
            throw new IllegalStateException();
        }
        int inputWidth = input.b();
        int inputHeight = input.a();
        int qrWidth = inputWidth + (quietZone << 1);
        int qrHeight = inputHeight + (quietZone << 1);
        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);
        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        int topPadding = (outputHeight - (inputHeight * multiple)) / 2;
        b output = new b(outputWidth, outputHeight);
        int inputY = 0;
        int outputY = topPadding;
        while (inputY < inputHeight) {
            int inputX = 0;
            int outputX = leftPadding;
            while (inputX < inputWidth) {
                if (input.a(inputX, inputY) == (byte) 1) {
                    output.a(outputX, outputY, multiple, multiple);
                }
                inputX++;
                outputX += multiple;
            }
            inputY++;
            outputY += multiple;
        }
        return output;
    }
}
