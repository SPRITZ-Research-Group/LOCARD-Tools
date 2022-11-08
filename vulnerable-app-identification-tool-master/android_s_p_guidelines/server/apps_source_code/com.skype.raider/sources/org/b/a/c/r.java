package org.b.a.c;

import org.b.a.a.h;

public final class r extends h {
    public Object a = null;
    public String b;

    public r(Exception e, String propertyName) {
        super(e);
        this.b = propertyName;
    }

    public final String getMessage() {
        if (this.a != null) {
            return "object " + this.a.getClass() + " has no " + this.b + " property";
        }
        return "no such property: " + this.b;
    }
}
