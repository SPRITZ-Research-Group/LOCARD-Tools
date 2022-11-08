package org.b.a.b;

public final class a extends b {
    String a;
    Object b;

    public a(String name, Object value) {
        this.a = name;
        this.b = value;
    }

    public final String toString() {
        return "addEvent{, name='" + this.a + '\'' + ", value=" + this.b + ", location=" + a().getFileName() + ":" + a().getLineNumber() + '}';
    }
}
