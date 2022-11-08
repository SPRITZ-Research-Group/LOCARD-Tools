package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class bg implements bf {
    bg() {
    }

    public final bd<?, ?> a() {
        throw new NoSuchMethodError();
    }

    public final Object a(Object obj, Object obj2) {
        obj = (be) obj;
        be beVar = (be) obj2;
        if (!beVar.isEmpty()) {
            if (!obj.c()) {
                obj = obj.a();
            }
            obj.a(beVar);
        }
        return obj;
    }

    public final Map<?, ?> a(Object obj) {
        return (be) obj;
    }

    public final Object b(Object obj) {
        ((be) obj).b();
        return obj;
    }

    public final int c(Object obj) {
        be beVar = (be) obj;
        if (!beVar.isEmpty()) {
            Iterator it = beVar.entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                entry.getValue();
                throw new NoSuchMethodError();
            }
        }
        return 0;
    }
}
