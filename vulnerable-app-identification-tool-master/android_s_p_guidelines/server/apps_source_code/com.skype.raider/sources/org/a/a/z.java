package org.a.a;

public final class z extends n {
    public z(int expecting, k input) {
        super(expecting, input);
    }

    public final String toString() {
        String exp = ", expected " + this.a;
        if (this.a == 0) {
            exp = "";
        }
        if (this.f == null) {
            return "UnwantedTokenException(found=" + null + exp + ")";
        }
        return "UnwantedTokenException(found=" + this.f.b() + exp + ")";
    }
}
