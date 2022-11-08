package com.applovin.impl.sdk;

import java.util.Map;

final class dq {
    private int a;
    private String b;
    private String c;
    private Map<String, String> d;

    dq(String str, Map<String, String> map, int i, String str2) {
        this.a = i;
        this.d = map;
        this.b = str;
        this.c = str2;
    }

    public final int a() {
        return this.a;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final Map<String, String> d() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        dq dqVar = (dq) obj;
        return this.a != dqVar.a ? false : (this.b == null ? dqVar.b == null : this.b.equals(dqVar.b)) ? (this.c == null ? dqVar.c == null : this.c.equals(dqVar.c)) ? this.d == null ? dqVar.d == null : this.d.equals(dqVar.d) : false : false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((this.a * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("PostbackRequest{attemptNumber=");
        stringBuilder.append(this.a);
        stringBuilder.append(", targetUrl='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", backupUrl='");
        stringBuilder.append(this.c);
        stringBuilder.append('\'');
        stringBuilder.append(", requestBody=");
        stringBuilder.append(this.d);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
