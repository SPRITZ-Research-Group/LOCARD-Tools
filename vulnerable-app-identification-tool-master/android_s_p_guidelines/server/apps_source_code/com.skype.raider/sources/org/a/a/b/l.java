package org.a.a.b;

import java.util.List;
import org.a.a.w;

public final class l extends j {
    public l(n adaptor, String elementDescription) {
        super(adaptor, elementDescription);
    }

    public l(n adaptor, String elementDescription, Object oneElement) {
        super(adaptor, elementDescription, oneElement);
    }

    public l(n adaptor, String elementDescription, List<Object> elements) {
        super(adaptor, elementDescription, (List) elements);
    }

    public final Object e() {
        return this.f.b((w) c());
    }

    protected final Object c(Object el) {
        return el;
    }

    protected final Object b(Object el) {
        throw new UnsupportedOperationException("dup can't be called for a token stream.");
    }
}
