package com.applovin.impl.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class gf {
    public static final gf a = new gf();
    protected String b;
    protected final List<gf> c;
    private final gf d;
    private final String e;
    private final Map<String, String> f;

    private gf() {
        this.d = null;
        this.e = "";
        this.f = Collections.emptyMap();
        this.b = "";
        this.c = Collections.emptyList();
    }

    public gf(String str, Map<String, String> map, gf gfVar) {
        this.d = gfVar;
        this.e = str;
        this.f = Collections.unmodifiableMap(map);
        this.c = new ArrayList();
    }

    public String a() {
        return this.e;
    }

    public List<gf> a(String str) {
        if (str != null) {
            List<gf> arrayList = new ArrayList(this.c.size());
            for (gf gfVar : this.c) {
                if (str.equalsIgnoreCase(gfVar.a())) {
                    arrayList.add(gfVar);
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("No name specified.");
    }

    public gf b(String str) {
        if (str != null) {
            for (gf gfVar : this.c) {
                if (str.equalsIgnoreCase(gfVar.a())) {
                    return gfVar;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("No name specified.");
    }

    public Map<String, String> b() {
        return this.f;
    }

    public gf c(String str) {
        if (str != null) {
            if (this.c.size() > 0) {
                List arrayList = new ArrayList();
                arrayList.add(this);
                while (!arrayList.isEmpty()) {
                    gf gfVar = (gf) arrayList.get(0);
                    arrayList.remove(0);
                    if (str.equalsIgnoreCase(gfVar.a())) {
                        return gfVar;
                    }
                    arrayList.addAll(gfVar.d());
                }
            }
            return null;
        }
        throw new IllegalArgumentException("No name specified.");
    }

    public String c() {
        return this.b;
    }

    public List<gf> d() {
        return Collections.unmodifiableList(this.c);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmlNode{, elementName='");
        stringBuilder.append(this.e);
        stringBuilder.append('\'');
        stringBuilder.append(", text='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", attributes=");
        stringBuilder.append(this.f);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
