package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class d {
    final int a;
    final Method b;

    d(int i, Method method) {
        this.a = i;
        this.b = method;
        this.b.setAccessible(true);
    }

    final void a(o oVar, j jVar, Object obj) {
        try {
            switch (this.a) {
                case 0:
                    this.b.invoke(obj, new Object[0]);
                    return;
                case 1:
                    this.b.invoke(obj, new Object[]{oVar});
                    return;
                case 2:
                    this.b.invoke(obj, new Object[]{oVar, jVar});
                    break;
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to call observer method", e.getCause());
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        return this.a == dVar.a && this.b.getName().equals(dVar.b.getName());
    }

    public final int hashCode() {
        return (this.a * 31) + this.b.getName().hashCode();
    }
}
