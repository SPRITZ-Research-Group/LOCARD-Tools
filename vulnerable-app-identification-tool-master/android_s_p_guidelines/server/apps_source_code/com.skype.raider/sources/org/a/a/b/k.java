package org.a.a.b;

import java.util.List;

public final class k extends j {
    public k(n adaptor, String elementDescription) {
        super(adaptor, elementDescription);
    }

    public k(n adaptor, String elementDescription, Object oneElement) {
        super(adaptor, elementDescription, oneElement);
    }

    public k(n adaptor, String elementDescription, List<Object> elements) {
        super(adaptor, elementDescription, (List) elements);
    }

    protected final Object b(Object el) {
        return this.f.b(el);
    }
}
