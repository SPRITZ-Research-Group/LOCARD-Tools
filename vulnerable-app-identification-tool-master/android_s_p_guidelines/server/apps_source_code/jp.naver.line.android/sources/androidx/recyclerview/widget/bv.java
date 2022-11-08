package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public abstract class bv {
    private int a = -1;
    private RecyclerView b;
    private bj c;
    private boolean d;
    private boolean e;
    private View f;
    private final bw g = new bw();
    private boolean h;

    protected abstract void a();

    protected abstract void a(int i, int i2, bw bwVar);

    protected abstract void a(View view, bw bwVar);

    final void a(RecyclerView recyclerView, bj bjVar) {
        if (this.h) {
            StringBuilder stringBuilder = new StringBuilder("An instance of ");
            stringBuilder.append(getClass().getSimpleName());
            stringBuilder.append(" was started more than once. Each instance of");
            stringBuilder.append(getClass().getSimpleName());
            stringBuilder.append(" is intended to only be used once. You should create a new instance for each use.");
            Log.w("RecyclerView", stringBuilder.toString());
        }
        this.b = recyclerView;
        this.c = bjVar;
        if (this.a != -1) {
            this.b.mState.a = this.a;
            this.e = true;
            this.d = true;
            this.f = this.b.mLayout.c(this.a);
            this.b.mViewFlinger.a();
            this.h = true;
            return;
        }
        throw new IllegalArgumentException("Invalid target position");
    }

    public final void c(int i) {
        this.a = i;
    }

    public final bj c() {
        return this.c;
    }

    protected final void d() {
        if (this.e) {
            this.e = false;
            a();
            this.b.mState.a = -1;
            this.f = null;
            this.a = -1;
            this.d = false;
            bj bjVar = this.c;
            if (bjVar.t == this) {
                bjVar.t = null;
            }
            this.c = null;
            this.b = null;
        }
    }

    public final boolean e() {
        return this.d;
    }

    public final boolean f() {
        return this.e;
    }

    public final int g() {
        return this.a;
    }

    final void a(int i, int i2) {
        RecyclerView recyclerView = this.b;
        if (!this.e || this.a == -1 || recyclerView == null) {
            d();
        }
        if (this.d && this.f == null && this.c != null) {
            PointF d = d(this.a);
            if (!(d == null || (d.x == BitmapDescriptorFactory.HUE_RED && d.y == BitmapDescriptorFactory.HUE_RED))) {
                recyclerView.scrollStep((int) Math.signum(d.x), (int) Math.signum(d.y), null);
            }
        }
        this.d = false;
        if (this.f != null) {
            if (b(this.f) == this.a) {
                a(this.f, this.g);
                this.g.a(recyclerView);
                d();
            } else {
                Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                this.f = null;
            }
        }
        if (this.e) {
            a(i, i2, this.g);
            boolean a = this.g.a();
            this.g.a(recyclerView);
            if (a) {
                if (this.e) {
                    this.d = true;
                    recyclerView.mViewFlinger.a();
                    return;
                }
                d();
            }
        }
    }

    private int b(View view) {
        return this.b.getChildLayoutPosition(view);
    }

    public final int h() {
        return this.b.mLayout.z();
    }

    protected void a(View view) {
        if (b(view) == this.a) {
            this.f = view;
        }
    }

    public PointF d(int i) {
        bj bjVar = this.c;
        if (bjVar instanceof bx) {
            return ((bx) bjVar).d(i);
        }
        StringBuilder stringBuilder = new StringBuilder("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
        stringBuilder.append(bx.class.getCanonicalName());
        Log.w("RecyclerView", stringBuilder.toString());
        return null;
    }
}
