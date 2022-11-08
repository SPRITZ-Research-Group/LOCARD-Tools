package defpackage;

import java.util.ArrayList;
import java.util.Collections;

/* renamed from: acsq */
public final class acsq {
    private final ArrayList<Object> a;

    public acsq(int i) {
        this.a = new ArrayList(i);
    }

    public final void a(Object obj) {
        if (obj != null) {
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 0) {
                    this.a.ensureCapacity(this.a.size() + objArr.length);
                    Collections.addAll(this.a, objArr);
                }
                return;
            }
            StringBuilder stringBuilder = new StringBuilder("Don't know how to spread ");
            stringBuilder.append(obj.getClass());
            throw new UnsupportedOperationException(stringBuilder.toString());
        }
    }

    public final int a() {
        return this.a.size();
    }

    public final void b(Object obj) {
        this.a.add(obj);
    }

    public final Object[] a(Object[] objArr) {
        return this.a.toArray(objArr);
    }
}
