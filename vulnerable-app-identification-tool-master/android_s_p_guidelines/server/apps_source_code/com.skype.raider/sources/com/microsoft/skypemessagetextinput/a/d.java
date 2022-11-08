package com.microsoft.skypemessagetextinput.a;

public final class d {
    private a a;
    private Integer b;
    private Integer c;

    public enum a {
        ;

        private a(String str) {
        }
    }

    public d(a action, Integer deletionStart, Integer deletionEnd) {
        this.a = action;
        this.b = deletionStart;
        this.c = deletionEnd;
    }

    public final a a() {
        return this.a;
    }

    public final Integer b() {
        return this.b;
    }

    public final Integer c() {
        return this.c;
    }
}
