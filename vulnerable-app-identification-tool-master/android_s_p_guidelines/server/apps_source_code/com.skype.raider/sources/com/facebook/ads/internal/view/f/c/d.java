package com.facebook.ads.internal.view.f.c;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.t;
import com.facebook.ads.internal.view.f.b.u;

@TargetApi(12)
public final class d implements b {
    private final i a;
    private final k b;
    private final c c;
    private final u d;
    private final Handler e;
    private final boolean f;
    private final boolean g;
    private View h;
    @Nullable
    private a i;
    @Nullable
    private com.facebook.ads.internal.view.f.a j;
    private boolean k;

    public enum a {
        VISIBLE,
        INVSIBLE,
        FADE_OUT_ON_PLAY
    }

    public d(View view, a aVar) {
        this(view, aVar, false);
    }

    public d(View view, a aVar, boolean z) {
        this(view, aVar, z, false);
    }

    public d(View view, @Nullable a aVar, boolean z, boolean z2) {
        this.a = new i(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public final /* bridge */ /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
                this.a.a(1, 0);
            }
        };
        this.b = new k(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
                if (!this.a.k) {
                    return;
                }
                if (this.a.i == a.FADE_OUT_ON_PLAY || this.a.f) {
                    this.a.i = null;
                    this.a.h.animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter(this.a) {
                        final /* synthetic */ d a;

                        {
                            this.a = r1;
                        }

                        public final void onAnimationEnd(Animator animator) {
                            this.a.h.setVisibility(8);
                        }
                    });
                    return;
                }
                this.a.a(0, 8);
            }
        };
        this.c = new c(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
                if (this.a.i != a.INVSIBLE) {
                    this.a.h.setAlpha(1.0f);
                    this.a.h.setVisibility(0);
                }
            }
        };
        this.d = new u(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
                t tVar = (t) dVar;
                if (this.a.j != null && tVar.a().getAction() == 0) {
                    this.a.e.removeCallbacksAndMessages(null);
                    this.a.a(new AnimatorListenerAdapter(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public final void onAnimationEnd(Animator animator) {
                            this.a.a.e.postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public final void run() {
                                    if (!this.a.a.a.g && this.a.a.a.k) {
                                        this.a.a.a.h.animate().alpha(0.0f).setDuration(500).setListener(/* anonymous class already generated */);
                                    }
                                }
                            }, 2000);
                        }
                    });
                }
            }
        };
        this.k = true;
        this.e = new Handler();
        this.f = z;
        this.g = z2;
        a(view, aVar);
    }

    private void a(int i, int i2) {
        this.e.removeCallbacksAndMessages(null);
        this.h.clearAnimation();
        this.h.setAlpha((float) i);
        this.h.setVisibility(i2);
    }

    private void a(AnimatorListenerAdapter animatorListenerAdapter) {
        this.h.setVisibility(0);
        this.h.animate().alpha(1.0f).setDuration(500).setListener(animatorListenerAdapter);
    }

    public final void a(View view, a aVar) {
        this.i = aVar;
        this.h = view;
        this.h.clearAnimation();
        if (aVar == a.INVSIBLE) {
            this.h.setAlpha(0.0f);
            this.h.setVisibility(8);
            return;
        }
        this.h.setAlpha(1.0f);
        this.h.setVisibility(0);
    }

    public final void a(com.facebook.ads.internal.view.f.a aVar) {
        this.j = aVar;
        aVar.a().a(this.a, this.b, this.d, this.c);
    }

    public final boolean a() {
        return this.k;
    }

    public final void b() {
        this.k = false;
        a(null);
    }

    public final void b(com.facebook.ads.internal.view.f.a aVar) {
        a(1, 0);
        aVar.a().b(this.c, this.d, this.b, this.a);
        this.j = null;
    }
}
