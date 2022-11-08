package addon.greenrobot.eventbus;

import defpackage.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class t {
    final List<r> a = new ArrayList();
    final Map<Class, Object> b = new HashMap();
    final Map<String, Class> c = new HashMap();
    final StringBuilder d = new StringBuilder(128);
    Class<?> e;
    Class<?> f;
    boolean g;
    b h;

    t() {
    }

    final void a(Class<?> cls) {
        this.f = cls;
        this.e = cls;
        this.g = false;
        this.h = null;
    }

    final boolean a(Method method, Class<?> cls) {
        Object put = this.b.put(cls, method);
        if (put == null) {
            return true;
        }
        if (put instanceof Method) {
            if (b((Method) put, cls)) {
                this.b.put(cls, this);
            } else {
                throw new IllegalStateException();
            }
        }
        return b(method, cls);
    }

    private boolean b(Method method, Class<?> cls) {
        this.d.setLength(0);
        this.d.append(method.getName());
        StringBuilder stringBuilder = this.d;
        stringBuilder.append('>');
        stringBuilder.append(cls.getName());
        String stringBuilder2 = this.d.toString();
        Class declaringClass = method.getDeclaringClass();
        Class cls2 = (Class) this.c.put(stringBuilder2, declaringClass);
        if (cls2 == null || cls2.isAssignableFrom(declaringClass)) {
            return true;
        }
        this.c.put(stringBuilder2, cls2);
        return false;
    }

    final void a() {
        if (!this.g) {
            this.f = this.f.getSuperclass();
            String name = this.f.getName();
            if (!(name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android."))) {
                return;
            }
        }
        this.f = null;
    }
}
