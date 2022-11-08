package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.k;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.p.a;
import android.support.v7.widget.RecyclerView.p.b;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

public abstract class ak extends i {
    RecyclerView a;
    private Scroller b;
    private final k c = new k(this) {
        boolean a = false;
        final /* synthetic */ ak b;

        {
            this.b = this$0;
        }

        public final void a(RecyclerView recyclerView, int newState) {
            super.a(recyclerView, newState);
            if (newState == 0 && this.a) {
                this.a = false;
                this.b.a();
            }
        }

        public final void a(RecyclerView recyclerView, int dx, int dy) {
            if (dx != 0 || dy != 0) {
                this.a = true;
            }
        }
    };

    public abstract int a(g gVar, int i, int i2);

    @Nullable
    public abstract View a(g gVar);

    @Nullable
    public abstract int[] a(@NonNull g gVar, @NonNull View view);

    public final boolean a(int velocityX, int velocityY) {
        g layoutManager = this.a.e();
        if (layoutManager == null || this.a.l == null) {
            return false;
        }
        int minFlingVelocity = this.a.h();
        if (Math.abs(velocityY) <= minFlingVelocity && Math.abs(velocityX) <= minFlingVelocity) {
            return false;
        }
        boolean z;
        if (layoutManager instanceof b) {
            p b = b(layoutManager);
            if (b == null) {
                z = false;
            } else {
                int a = a(layoutManager, velocityX, velocityY);
                if (a == -1) {
                    z = false;
                } else {
                    b.d(a);
                    layoutManager.a(b);
                    z = true;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public final void a(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        if (this.a != recyclerView) {
            if (this.a != null) {
                this.a.b(this.c);
                this.a.setOnFlingListener(null);
            }
            this.a = recyclerView;
            if (this.a == null) {
                return;
            }
            if (this.a.d() != null) {
                throw new IllegalStateException("An instance of OnFlingListener already set.");
            }
            this.a.a(this.c);
            this.a.setOnFlingListener(this);
            this.b = new Scroller(this.a.getContext(), new DecelerateInterpolator());
            a();
        }
    }

    final void a() {
        if (this.a != null) {
            g layoutManager = this.a.e();
            if (layoutManager != null) {
                View snapView = a(layoutManager);
                if (snapView != null) {
                    int[] snapDistance = a(layoutManager, snapView);
                    if (snapDistance[0] != 0 || snapDistance[1] != 0) {
                        this.a.a(snapDistance[0], snapDistance[1]);
                    }
                }
            }
        }
    }

    @Nullable
    @Deprecated
    protected z b(g layoutManager) {
        if (layoutManager instanceof b) {
            return new z(this, this.a.getContext()) {
                final /* synthetic */ ak f;

                protected final void a(View targetView, a action) {
                    int[] snapDistances = this.f.a(this.f.a.e(), targetView);
                    int dx = snapDistances[0];
                    int dy = snapDistances[1];
                    int time = a(Math.max(Math.abs(dx), Math.abs(dy)));
                    if (time > 0) {
                        action.a(dx, dy, time, this.b);
                    }
                }

                protected final float a(DisplayMetrics displayMetrics) {
                    return 100.0f / ((float) displayMetrics.densityDpi);
                }
            };
        }
        return null;
    }
}
