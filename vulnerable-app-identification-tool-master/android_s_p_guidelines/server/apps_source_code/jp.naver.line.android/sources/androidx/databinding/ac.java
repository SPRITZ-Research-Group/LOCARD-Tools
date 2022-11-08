package androidx.databinding;

import androidx.lifecycle.o;
import java.lang.ref.WeakReference;

final class ac<T> extends WeakReference<ViewDataBinding> {
    protected final int a;
    private final z<T> b;
    private T c;

    public ac(ViewDataBinding viewDataBinding, int i, z<T> zVar) {
        super(viewDataBinding, ViewDataBinding.sReferenceQueue);
        this.a = i;
        this.b = zVar;
    }

    public final void a(o oVar) {
        this.b.a(oVar);
    }

    public final void a(T t) {
        a();
        this.c = t;
        if (this.c != null) {
            this.b.b(this.c);
        }
    }

    public final boolean a() {
        boolean z;
        if (this.c != null) {
            this.b.a(this.c);
            z = true;
        } else {
            z = false;
        }
        this.c = null;
        return z;
    }

    public final T b() {
        return this.c;
    }

    protected final ViewDataBinding c() {
        ViewDataBinding viewDataBinding = (ViewDataBinding) get();
        if (viewDataBinding == null) {
            a();
        }
        return viewDataBinding;
    }
}
