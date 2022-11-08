package com.google.gson.a.a;

import com.google.gson.a.c;
import com.google.gson.a.h;
import com.google.gson.a.j;
import com.google.gson.c.b;
import com.google.gson.e;
import com.google.gson.i;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.n;
import com.google.gson.p;
import com.google.gson.r;
import com.google.gson.s;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class g implements s {
    final boolean a = false;
    private final c b;

    private final class a<K, V> extends r<Map<K, V>> {
        final /* synthetic */ g a;
        private final r<K> b;
        private final r<V> c;
        private final h<? extends Map<K, V>> d;

        public final /* synthetic */ void a(com.google.gson.c.c cVar, Object obj) throws IOException {
            int i = 0;
            Map map = (Map) obj;
            if (map == null) {
                cVar.f();
            } else if (this.a.a) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    int i3;
                    i a = this.b.a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    if ((a instanceof com.google.gson.g) || (a instanceof l)) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    cVar.b();
                    while (i < arrayList.size()) {
                        cVar.b();
                        j.a((i) arrayList.get(i), cVar);
                        this.c.a(cVar, arrayList2.get(i));
                        cVar.c();
                        i++;
                    }
                    cVar.c();
                    return;
                }
                cVar.d();
                while (i < arrayList.size()) {
                    String valueOf;
                    i iVar = (i) arrayList.get(i);
                    if (iVar instanceof n) {
                        n g = iVar.g();
                        if (g.i()) {
                            valueOf = String.valueOf(g.a());
                        } else if (g.h()) {
                            valueOf = Boolean.toString(g.f());
                        } else if (g.j()) {
                            valueOf = g.b();
                        } else {
                            throw new AssertionError();
                        }
                    } else if (iVar instanceof k) {
                        valueOf = "null";
                    } else {
                        throw new AssertionError();
                    }
                    cVar.a(valueOf);
                    this.c.a(cVar, arrayList2.get(i));
                    i++;
                }
                cVar.e();
            } else {
                cVar.d();
                for (Entry entry2 : map.entrySet()) {
                    cVar.a(String.valueOf(entry2.getKey()));
                    this.c.a(cVar, entry2.getValue());
                }
                cVar.e();
            }
        }

        public a(g gVar, e context, Type keyType, r<K> keyTypeAdapter, Type valueType, r<V> valueTypeAdapter, h<? extends Map<K, V>> constructor) {
            this.a = gVar;
            this.b = new m(context, keyTypeAdapter, keyType);
            this.c = new m(context, valueTypeAdapter, valueType);
            this.d = constructor;
        }

        public final /* synthetic */ Object a(com.google.gson.c.a aVar) throws IOException {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            }
            Map map = (Map) this.d.a();
            Object a;
            if (f == b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.e()) {
                    aVar.a();
                    a = this.b.a(aVar);
                    if (map.put(a, this.c.a(aVar)) != null) {
                        throw new p("duplicate key: " + a);
                    }
                    aVar.b();
                }
                aVar.b();
                return map;
            }
            aVar.c();
            while (aVar.e()) {
                com.google.gson.a.e.a.a(aVar);
                a = this.b.a(aVar);
                if (map.put(a, this.c.a(aVar)) != null) {
                    throw new p("duplicate key: " + a);
                }
            }
            aVar.d();
            return map;
        }
    }

    public g(c constructorConstructor) {
        this.b = constructorConstructor;
    }

    public final <T> r<T> a(e gson, com.google.gson.b.a<T> typeToken) {
        Type type = typeToken.b();
        if (!Map.class.isAssignableFrom(typeToken.a())) {
            return null;
        }
        r<?> keyAdapter;
        Type[] keyAndValueTypes = com.google.gson.a.b.b(type, com.google.gson.a.b.b(type));
        Type type2 = keyAndValueTypes[0];
        if (type2 == Boolean.TYPE || type2 == Boolean.class) {
            keyAdapter = n.f;
        } else {
            keyAdapter = gson.a(com.google.gson.b.a.a(type2));
        }
        return new a(this, gson, keyAndValueTypes[0], keyAdapter, keyAndValueTypes[1], gson.a(com.google.gson.b.a.a(keyAndValueTypes[1])), this.b.a((com.google.gson.b.a) typeToken));
    }
}
