package com.facebook.ads;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.q.a.j;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.c.g;

public class AdChoicesView extends RelativeLayout {
    private final h a;
    private final float b;
    private boolean c = false;
    private TextView d;
    private String e;

    public AdChoicesView(Context context, final h hVar) {
        super(context);
        this.a = hVar;
        this.b = u.b;
        if (!this.a.f() || this.a.d().h()) {
            this.e = this.a.o();
            if (TextUtils.isEmpty(this.e)) {
                this.e = "AdChoices";
            }
            f k = this.a.c().k();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ AdChoicesView b;

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!this.b.c) {
                        AdChoicesView.c(this.b);
                    } else if (!TextUtils.isEmpty(this.b.a.n())) {
                        g gVar = new g();
                        g.a(this.b.getContext(), Uri.parse(this.b.a.n()), hVar.p());
                    }
                    return true;
                }
            });
            this.d = new TextView(getContext());
            addView(this.d);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            if (k != null) {
                ImageView imageView = new ImageView(getContext());
                addView(imageView);
                LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(Math.round(((float) k.b()) * this.b), Math.round(((float) k.c()) * this.b));
                layoutParams3.addRule(9);
                layoutParams3.addRule(15, -1);
                layoutParams3.setMargins(Math.round(4.0f * this.b), Math.round(this.b * 2.0f), Math.round(this.b * 2.0f), Math.round(this.b * 2.0f));
                imageView.setLayoutParams(layoutParams3);
                e.a(k, imageView);
                layoutParams2.addRule(11, imageView.getId());
                layoutParams2.width = 0;
                layoutParams.width = Math.round(((float) (k.b() + 4)) * this.b);
                layoutParams.height = Math.round(((float) (k.c() + 2)) * this.b);
                this.c = false;
            } else {
                this.c = true;
            }
            setLayoutParams(layoutParams);
            layoutParams2.addRule(15, -1);
            this.d.setLayoutParams(layoutParams2);
            this.d.setSingleLine();
            this.d.setText(this.e);
            this.d.setTextSize(10.0f);
            this.d.setTextColor(-4341303);
            j.a(this, j.INTERNAL_AD_CHOICES_ICON);
            j.a(this.d, j.INTERNAL_AD_CHOICES_ICON);
            return;
        }
        setVisibility(8);
    }

    static /* synthetic */ void c(AdChoicesView adChoicesView) {
        Paint paint = new Paint();
        paint.setTextSize(adChoicesView.d.getTextSize());
        int round = Math.round(paint.measureText(adChoicesView.e) + (4.0f * adChoicesView.b));
        final int width = adChoicesView.getWidth();
        round += width;
        adChoicesView.c = true;
        Animation anonymousClass2 = new Animation(adChoicesView) {
            final /* synthetic */ AdChoicesView c;

            protected final void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (round - width)) * f));
                this.c.getLayoutParams().width = i;
                this.c.requestLayout();
                this.c.d.getLayoutParams().width = i - width;
                this.c.d.requestLayout();
            }

            public final boolean willChangeBounds() {
                return true;
            }
        };
        anonymousClass2.setAnimationListener(new AnimationListener(adChoicesView) {
            final /* synthetic */ AdChoicesView a;

            {
                this.a = r1;
            }

            public final void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        if (this.a.a.c) {
                            AdChoicesView.e(this.a.a);
                        }
                    }
                }, 3000);
            }

            public final void onAnimationRepeat(Animation animation) {
            }

            public final void onAnimationStart(Animation animation) {
            }
        });
        anonymousClass2.setDuration(300);
        anonymousClass2.setFillAfter(true);
        adChoicesView.startAnimation(anonymousClass2);
    }

    static /* synthetic */ void e(AdChoicesView adChoicesView) {
        Paint paint = new Paint();
        paint.setTextSize(adChoicesView.d.getTextSize());
        int round = Math.round(paint.measureText(adChoicesView.e) + (4.0f * adChoicesView.b));
        final int width = adChoicesView.getWidth();
        round = width - round;
        Animation anonymousClass4 = new Animation(adChoicesView) {
            final /* synthetic */ AdChoicesView c;

            protected final void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (round - width)) * f));
                this.c.getLayoutParams().width = i;
                this.c.requestLayout();
                this.c.d.getLayoutParams().width = i - round;
                this.c.d.requestLayout();
            }

            public final boolean willChangeBounds() {
                return true;
            }
        };
        anonymousClass4.setAnimationListener(new AnimationListener(adChoicesView) {
            final /* synthetic */ AdChoicesView a;

            {
                this.a = r1;
            }

            public final void onAnimationEnd(Animation animation) {
                this.a.c = false;
            }

            public final void onAnimationRepeat(Animation animation) {
            }

            public final void onAnimationStart(Animation animation) {
            }
        });
        anonymousClass4.setDuration(300);
        anonymousClass4.setFillAfter(true);
        adChoicesView.startAnimation(anonymousClass4);
    }
}
