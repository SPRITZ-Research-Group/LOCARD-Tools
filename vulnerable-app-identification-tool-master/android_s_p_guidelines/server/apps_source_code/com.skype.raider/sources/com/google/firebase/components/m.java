package com.google.firebase.components;

import com.google.firebase.a.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class m implements b {
    private final List<a<?>> a;
    private final Map<Class<?>, o<?>> b = new HashMap();

    public m(Iterable<d> iterable, a<?>... aVarArr) {
        n nVar;
        n nVar2;
        List<a> arrayList = new ArrayList();
        for (d components : iterable) {
            arrayList.addAll(components.getComponents());
        }
        Collections.addAll(arrayList, aVarArr);
        Map hashMap = new HashMap(arrayList.size());
        for (a aVar : arrayList) {
            n nVar3 = new n(aVar);
            for (Class put : aVar.a()) {
                if (hashMap.put(put, nVar3) != null) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", new Object[]{(Class) r5.next()}));
                }
            }
        }
        for (n nVar22 : hashMap.values()) {
            for (e eVar : nVar22.b().b()) {
                if (eVar.c()) {
                    nVar = (n) hashMap.get(eVar.a());
                    if (nVar != null) {
                        nVar22.a(nVar);
                        nVar.b(nVar22);
                    }
                }
            }
        }
        Set<n> hashSet = new HashSet(hashMap.values());
        Set hashSet2 = new HashSet();
        for (n nVar222 : hashSet) {
            if (nVar222.c()) {
                hashSet2.add(nVar222);
            }
        }
        List arrayList2 = new ArrayList();
        while (!hashSet2.isEmpty()) {
            nVar222 = (n) hashSet2.iterator().next();
            hashSet2.remove(nVar222);
            arrayList2.add(nVar222.b());
            for (n nVar4 : nVar222.a()) {
                nVar4.c(nVar222);
                if (nVar4.c()) {
                    hashSet2.add(nVar4);
                }
            }
        }
        if (arrayList2.size() == arrayList.size()) {
            Collections.reverse(arrayList2);
            this.a = Collections.unmodifiableList(arrayList2);
            for (a aVar2 : this.a) {
                o oVar = new o(aVar2.c(), new r(aVar2.b(), this));
                for (Class put2 : aVar2.a()) {
                    this.b.put(put2, oVar);
                }
            }
            for (a aVar22 : this.a) {
                for (e eVar2 : aVar22.b()) {
                    if (eVar2.b() && !this.b.containsKey(eVar2.a())) {
                        throw new h(String.format("Unsatisfied dependency for component %s: %s", new Object[]{aVar22, eVar2.a()}));
                    }
                }
            }
            return;
        }
        List arrayList3 = new ArrayList();
        for (n nVar2222 : hashSet) {
            if (!(nVar2222.c() || nVar2222.d())) {
                arrayList3.add(nVar2222.b());
            }
        }
        throw new f(arrayList3);
    }

    public final void a(boolean z) {
        for (a aVar : this.a) {
            if (aVar.d() || (aVar.e() && z)) {
                a((Class) aVar.a().iterator().next());
            }
        }
    }

    public final <T> a<T> b(Class<T> cls) {
        q.a((Object) cls, "Null interface requested.");
        return (a) this.b.get(cls);
    }

    public final Object a(Class cls) {
        a b = b(cls);
        return b == null ? null : b.a();
    }
}
