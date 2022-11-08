package org.b.a.b;

public class c extends f {
    public final int a;
    public final int b;
    public final String c;

    public c(org.b.a.c scope, int start, int stop, int exprStartChar, int exprStopChar) {
        super(scope, start, stop);
        this.a = exprStartChar;
        this.b = exprStopChar;
        if (exprStartChar < 0 || exprStopChar < 0) {
            this.c = "";
        } else {
            this.c = scope.b.b.c.substring(exprStartChar, exprStopChar + 1);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "{self=" + this.d.b + ", expr='" + this.c + '\'' + ", exprStartChar=" + this.a + ", exprStopChar=" + this.b + ", start=" + this.e + ", stop=" + this.f + '}';
    }
}
