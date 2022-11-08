package androidx.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class c {
    final Map<j, List<d>> a = new HashMap();
    final Map<d, j> b;

    c(Map<d, j> map) {
        this.b = map;
        for (Entry entry : map.entrySet()) {
            j jVar = (j) entry.getValue();
            List list = (List) this.a.get(jVar);
            if (list == null) {
                list = new ArrayList();
                this.a.put(jVar, list);
            }
            list.add(entry.getKey());
        }
    }

    final void a(o oVar, j jVar, Object obj) {
        a((List) this.a.get(jVar), oVar, jVar, obj);
        a((List) this.a.get(j.ON_ANY), oVar, jVar, obj);
    }

    private static void a(List<d> list, o oVar, j jVar, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((d) list.get(size)).a(oVar, jVar, obj);
            }
        }
    }
}
