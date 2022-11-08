package com.applovin.impl.sdk;

import java.util.Map;

public class du {
    private final String a;
    private final Map<String, String> b;
    private final long c;
    private final String d;

    public du(String str, Map<String, String> map, long j, String str2) {
        this.a = str;
        this.b = map;
        this.c = j;
        this.d = str2;
    }

    public String a() {
        return this.a;
    }

    public Map<String, String> b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            du duVar = (du) obj;
            return this.c != duVar.c ? false : (this.a == null ? duVar.a == null : this.a.equals(duVar.a)) ? (this.b == null ? duVar.b == null : this.b.equals(duVar.b)) ? this.d == null ? duVar.d != null : !this.d.equals(duVar.d) : false : false;
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SdkEvent{eventType='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", parameters=");
        stringBuilder.append(this.b);
        stringBuilder.append(", creationTsMillis=");
        stringBuilder.append(this.c);
        stringBuilder.append(", uniqueIdentifier='");
        stringBuilder.append(this.d);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
