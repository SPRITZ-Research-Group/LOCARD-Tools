package com.airbnb.lottie.c;

import com.airbnb.lottie.c.b.n;
import java.util.List;

public final class d {
    private final List<n> a;
    private final char b;
    private final int c;
    private final double d;
    private final String e;
    private final String f;

    public static int a(char character, String fontFamily, String style) {
        return ((((character + 0) * 31) + fontFamily.hashCode()) * 31) + style.hashCode();
    }

    public d(List<n> shapes, char character, int size, double width, String style, String fontFamily) {
        this.a = shapes;
        this.b = character;
        this.c = size;
        this.d = width;
        this.e = style;
        this.f = fontFamily;
    }

    public final List<n> a() {
        return this.a;
    }

    public final double b() {
        return this.d;
    }

    public final int hashCode() {
        return a(this.b, this.f, this.e);
    }
}
