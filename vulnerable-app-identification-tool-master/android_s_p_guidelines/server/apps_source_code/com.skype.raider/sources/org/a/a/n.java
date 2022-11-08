package org.a.a;

public class n extends t {
    public int a = 0;

    public n(int expecting, k input) {
        super(input);
        this.a = expecting;
    }

    public String toString() {
        return "MismatchedTokenException(" + a() + "!=" + this.a + ")";
    }
}
