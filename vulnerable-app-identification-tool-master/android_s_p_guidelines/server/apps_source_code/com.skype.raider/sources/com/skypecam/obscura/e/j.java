package com.skypecam.obscura.e;

public final class j {
    private final int a;
    private final int b;

    public j(int width, int height) {
        this.a = width;
        this.b = height;
    }

    public final int a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final boolean equals(Object o) {
        return (o instanceof j) && ((j) o).a == this.a && ((j) o).b == this.b;
    }

    public final String toString() {
        return super.toString() + "-" + this.a + "x" + this.b;
    }
}
