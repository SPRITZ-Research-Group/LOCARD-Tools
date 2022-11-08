package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class s {
    Runnable a = null;
    Runnable b = null;
    int c = -1;
    private WeakReference<View> d;

    static class a implements t {
        s a;
        boolean b;

        a(s vpa) {
            this.a = vpa;
        }

        public final void a(View view) {
            this.b = false;
            if (this.a.c >= 0) {
                view.setLayerType(2, null);
            }
            if (this.a.a != null) {
                Runnable startAction = this.a.a;
                this.a.a = null;
                startAction.run();
            }
            t listenerTag = view.getTag(2113929216);
            t listener = null;
            if (listenerTag instanceof t) {
                listener = listenerTag;
            }
            if (listener != null) {
                listener.a(view);
            }
        }

        public final void b(View view) {
            if (this.a.c >= 0) {
                view.setLayerType(this.a.c, null);
                this.a.c = -1;
            }
            if (VERSION.SDK_INT >= 16 || !this.b) {
                if (this.a.b != null) {
                    Runnable endAction = this.a.b;
                    this.a.b = null;
                    endAction.run();
                }
                t listenerTag = view.getTag(2113929216);
                t listener = null;
                if (listenerTag instanceof t) {
                    listener = listenerTag;
                }
                if (listener != null) {
                    listener.b(view);
                }
                this.b = true;
            }
        }

        public final void c(View view) {
            t listenerTag = view.getTag(2113929216);
            t listener = null;
            if (listenerTag instanceof t) {
                listener = listenerTag;
            }
            if (listener != null) {
                listener.c(view);
            }
        }
    }

    s(View view) {
        this.d = new WeakReference(view);
    }

    public final s a(long value) {
        View view = (View) this.d.get();
        if (view != null) {
            view.animate().setDuration(value);
        }
        return this;
    }

    public final s a(float value) {
        View view = (View) this.d.get();
        if (view != null) {
            view.animate().alpha(value);
        }
        return this;
    }

    public final s b(float value) {
        View view = (View) this.d.get();
        if (view != null) {
            view.animate().translationY(value);
        }
        return this;
    }

    public final long a() {
        View view = (View) this.d.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0;
    }

    public final s a(Interpolator value) {
        View view = (View) this.d.get();
        if (view != null) {
            view.animate().setInterpolator(value);
        }
        return this;
    }

    public final s b(long value) {
        View view = (View) this.d.get();
        if (view != null) {
            view.animate().setStartDelay(value);
        }
        return this;
    }

    public final void b() {
        View view = (View) this.d.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public final void c() {
        View view = (View) this.d.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public final s a(t listener) {
        View view = (View) this.d.get();
        if (view != null) {
            if (VERSION.SDK_INT >= 16) {
                a(view, listener);
            } else {
                view.setTag(2113929216, listener);
                a(view, new a(this));
            }
        }
        return this;
    }

    private void a(final View view, final t listener) {
        if (listener != null) {
            view.animate().setListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ s c;

                public final void onAnimationCancel(Animator animation) {
                    listener.c(view);
                }

                public final void onAnimationEnd(Animator animation) {
                    listener.b(view);
                }

                public final void onAnimationStart(Animator animation) {
                    listener.a(view);
                }
            });
        } else {
            view.animate().setListener(null);
        }
    }

    public final s a(final v listener) {
        final View view = (View) this.d.get();
        if (view != null && VERSION.SDK_INT >= 19) {
            AnimatorUpdateListener wrapped = null;
            if (listener != null) {
                wrapped = new AnimatorUpdateListener(this) {
                    final /* synthetic */ s c;

                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        listener.a();
                    }
                };
            }
            view.animate().setUpdateListener(wrapped);
        }
        return this;
    }
}
