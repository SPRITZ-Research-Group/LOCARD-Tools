package com.google.a.a;

import com.google.a.b.b;
import com.google.a.c;
import com.google.a.g;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class a implements g {
    public final b a(String contents, com.google.a.a format, int width, int height, Map<c, ?> hints) {
        Charset charset = StandardCharsets.ISO_8859_1;
        int eccPercent = 33;
        int layers = 0;
        if (hints != null) {
            if (hints.containsKey(c.CHARACTER_SET)) {
                charset = Charset.forName(hints.get(c.CHARACTER_SET).toString());
            }
            if (hints.containsKey(c.ERROR_CORRECTION)) {
                eccPercent = Integer.parseInt(hints.get(c.ERROR_CORRECTION).toString());
            }
            if (hints.containsKey(c.AZTEC_LAYERS)) {
                layers = Integer.parseInt(hints.get(c.AZTEC_LAYERS).toString());
            }
        }
        if (format == com.google.a.a.AZTEC) {
            return a(com.google.a.a.a.c.a(contents.getBytes(charset), eccPercent, layers), width, height);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got " + format);
    }

    private static b a(com.google.a.a.a.a code, int width, int height) {
        b input = code.a();
        if (input == null) {
            throw new IllegalStateException();
        }
        int inputWidth = input.b();
        int inputHeight = input.c();
        int outputWidth = Math.max(width, inputWidth);
        int outputHeight = Math.max(height, inputHeight);
        int multiple = Math.min(outputWidth / inputWidth, outputHeight / inputHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        int topPadding = (outputHeight - (inputHeight * multiple)) / 2;
        b output = new b(outputWidth, outputHeight);
        int inputY = 0;
        int outputY = topPadding;
        while (inputY < inputHeight) {
            int inputX = 0;
            int outputX = leftPadding;
            while (inputX < inputWidth) {
                if (input.a(inputX, inputY)) {
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
