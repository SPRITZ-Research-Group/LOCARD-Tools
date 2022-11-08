package org.a.a;

public final class p extends n {
    public Object b;

    public p(int expecting, k input, Object inserted) {
        super(expecting, input);
        this.b = inserted;
    }

    public final String toString() {
        if (this.b != null && this.f != null) {
            return "MissingTokenException(inserted " + this.b + " at " + this.f.b() + ")";
        }
        if (this.f != null) {
            return "MissingTokenException(at " + this.f.b() + ")";
        }
        return "MissingTokenException";
    }
}
