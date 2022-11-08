package com.skype4life.syncadapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class h {
    private final String a;
    private final String b;
    private final String c;
    private Map<String, i> d = new HashMap();
    private Map<String, i> e = new HashMap();

    public h(String id, String sourceId, String contactId) {
        this.a = id;
        this.b = sourceId;
        this.c = contactId;
    }

    public final Map<String, i> a() {
        return this.d;
    }

    public final boolean a(String mimeType) {
        return this.d.containsKey(mimeType);
    }

    public final i b(String mimeType) {
        return (i) this.d.get(mimeType);
    }

    public final Map<String, i> b() {
        return this.e;
    }

    public final boolean c(String phoneNumber) {
        return this.e.containsKey(phoneNumber);
    }

    public final i d(String phoneNumber) {
        return (i) this.e.get(phoneNumber);
    }

    public final String c() {
        return this.a;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((h) o).a);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.a});
    }
}
