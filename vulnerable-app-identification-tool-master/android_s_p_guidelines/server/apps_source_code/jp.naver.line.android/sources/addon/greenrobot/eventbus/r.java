package addon.greenrobot.eventbus;

import java.lang.reflect.Method;

public final class r {
    final Method a;
    final ThreadMode b;
    final Class<?> c;
    final int d;
    final boolean e;
    String f;

    public r(Method method, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.a = method;
        this.b = threadMode;
        this.c = cls;
        this.d = i;
        this.e = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        a();
        r rVar = (r) obj;
        rVar.a();
        return this.f.equals(rVar.f);
    }

    private synchronized void a() {
        if (this.f == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.a.getDeclaringClass().getName());
            stringBuilder.append('#');
            stringBuilder.append(this.a.getName());
            stringBuilder.append('(');
            stringBuilder.append(this.c.getName());
            this.f = stringBuilder.toString();
        }
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
