package com.microsoft.tokenshare;

import java.util.HashMap;
import java.util.Map;

public final class d {

    static class a {
        private final String a;
        private final Map<String, String> b = new HashMap();

        a(String name) {
            this.a = name;
        }

        protected final a a(String name, Object value) {
            this.b.put(name, value.toString());
            return this;
        }
    }

    private static final class b {
        private static d a = new d();
    }

    /* synthetic */ d(byte b) {
        this();
    }

    private d() {
    }
}
