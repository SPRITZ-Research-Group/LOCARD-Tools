package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.b.b;
import com.facebook.ads.internal.q.c.g;
import com.facebook.ads.internal.view.f.a.c;

public class a extends c {
    private final a a;

    public static class a extends RelativeLayout {
        private final String a;
        private final String b;
        private final String c;
        private final DisplayMetrics d;
        private ImageView e;
        private TextView f;
        private boolean g = false;

        public a(Context context, String str, String str2, float[] fArr, String str3) {
            super(context);
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = context.getResources().getDisplayMetrics();
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-16777216);
            gradientDrawable.setAlpha(178);
            gradientDrawable.setCornerRadii(new float[]{fArr[0] * this.d.density, fArr[0] * this.d.density, fArr[1] * this.d.density, fArr[1] * this.d.density, fArr[2] * this.d.density, fArr[2] * this.d.density, fArr[3] * this.d.density, fArr[3] * this.d.density});
            u.a((View) this, gradientDrawable);
            setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!this.a.g) {
                        a.d(this.a);
                    } else if (!TextUtils.isEmpty(this.a.b)) {
                        g gVar = new g();
                        g.a(this.a.getContext(), Uri.parse(this.a.b), this.a.c);
                    }
                    return true;
                }
            });
            this.e = new ImageView(getContext());
            this.e.setImageBitmap(com.facebook.ads.internal.q.b.c.a(b.IC_AD_CHOICES));
            addView(this.e);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(16.0f * this.d.density), Math.round(16.0f * this.d.density));
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(Math.round(4.0f * this.d.density), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f));
            this.e.setLayoutParams(layoutParams);
            this.f = new TextView(getContext());
            addView(this.f);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.width = 0;
            layoutParams.leftMargin = (int) (20.0f * this.d.density);
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            this.f.setLayoutParams(layoutParams);
            this.f.setSingleLine();
            this.f.setText(this.a);
            this.f.setTextSize(10.0f);
            this.f.setTextColor(-4341303);
            setMinimumWidth(Math.round(20.0f * this.d.density));
            setMinimumHeight(Math.round(18.0f * this.d.density));
        }

        static /* synthetic */ void d(a aVar) {
            Paint paint = new Paint();
            paint.setTextSize(aVar.f.getTextSize());
            int round = Math.round(paint.measureText(aVar.a) + (4.0f * aVar.d.density));
            final int width = aVar.getWidth();
            round += width;
            aVar.g = true;
            Animation anonymousClass2 = new Animation(aVar) {
                final /* synthetic */ a c;

                protected final void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (round - width)) * f));
                    this.c.getLayoutParams().width = i;
                    this.c.requestLayout();
                    this.c.f.getLayoutParams().width = i - width;
                    this.c.f.requestLayout();
                }

                public final boolean willChangeBounds() {
                    return true;
                }
            };
            anonymousClass2.setAnimationListener(new AnimationListener(aVar) {
                final /* synthetic */ a a;

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
                            if (this.a.a.g) {
                                a.f(this.a.a);
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
            aVar.startAnimation(anonymousClass2);
        }

        static /* synthetic */ void f(a aVar) {
            Paint paint = new Paint();
            paint.setTextSize(aVar.f.getTextSize());
            int round = Math.round(paint.measureText(aVar.a) + (4.0f * aVar.d.density));
            final int width = aVar.getWidth();
            round = width - round;
            Animation anonymousClass4 = new Animation(aVar) {
                final /* synthetic */ a c;

                protected final void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (round - width)) * f));
                    this.c.getLayoutParams().width = i;
                    this.c.requestLayout();
                    this.c.f.getLayoutParams().width = i - round;
                    this.c.f.requestLayout();
                }

                public final boolean willChangeBounds() {
                    return true;
                }
            };
            anonymousClass4.setAnimationListener(new AnimationListener(aVar) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void onAnimationEnd(Animation animation) {
                    this.a.g = false;
                }

                public final void onAnimationRepeat(Animation animation) {
                }

                public final void onAnimationStart(Animation animation) {
                }
            });
            anonymousClass4.setDuration(300);
            anonymousClass4.setFillAfter(true);
            aVar.startAnimation(anonymousClass4);
        }
    }

    public a(Context context, String str, String str2, float[] fArr) {
        super(context);
        this.a = new a(context, "AdChoices", str, fArr, str2);
        addView(this.a);
    }
}
