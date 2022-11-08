package com.applovin.impl.adview;

class ap {
    private final String a;
    private final ao b;
    private final long c;

    private ap(String str, long j, ao aoVar) {
        this.a = str;
        this.c = j;
        this.b = aoVar;
    }

    /* synthetic */ ap(String str, long j, ao aoVar, an anVar) {
        this(str, j, aoVar);
    }

    private String a() {
        return this.a;
    }

    private long b() {
        return this.c;
    }

    private ao c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ap)) {
            return false;
        }
        ap apVar = (ap) obj;
        return this.a != null ? this.a.equalsIgnoreCase(apVar.a) : apVar.a == null;
    }

    public int hashCode() {
        return this.a != null ? this.a.hashCode() : 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("CountdownProxy{identifier='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", countdownStepMillis=");
        stringBuilder.append(this.c);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
