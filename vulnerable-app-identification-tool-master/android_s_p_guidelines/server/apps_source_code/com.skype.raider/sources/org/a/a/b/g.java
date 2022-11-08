package org.a.a.b;

public class g extends RuntimeException {
    public String a;

    public g(String elementDescription) {
        this.a = elementDescription;
    }

    public String getMessage() {
        if (this.a != null) {
            return this.a;
        }
        return null;
    }
}
