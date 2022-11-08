package androidx.databinding;

import android.view.View;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;

public final class af {
    private ViewStub a;
    private ViewDataBinding b;
    private View c;
    private OnInflateListener d;
    private ViewDataBinding e;
    private OnInflateListener f = new OnInflateListener(this) {
        final /* synthetic */ af a;

        {
            this.a = r1;
        }

        public final void onInflate(ViewStub viewStub, View view) {
            this.a.c = view;
            this.a.b = h.a(this.a.e.mBindingComponent, view, viewStub.getLayoutResource());
            this.a.a = null;
            if (this.a.d != null) {
                this.a.d.onInflate(viewStub, view);
                this.a.d = null;
            }
            this.a.e.invalidateAll();
            this.a.e.forceExecuteBindings();
        }
    };

    public af(ViewStub viewStub) {
        this.a = viewStub;
        this.a.setOnInflateListener(this.f);
    }

    public final void a(ViewDataBinding viewDataBinding) {
        this.e = viewDataBinding;
    }

    public final boolean a() {
        return this.c != null;
    }

    public final View b() {
        return this.c;
    }

    public final ViewDataBinding c() {
        return this.b;
    }

    public final ViewStub d() {
        return this.a;
    }
}
