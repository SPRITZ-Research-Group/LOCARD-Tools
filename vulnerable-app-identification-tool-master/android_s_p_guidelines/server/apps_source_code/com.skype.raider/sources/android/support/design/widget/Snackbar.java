package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.a.d;
import android.support.design.a.f;
import android.support.design.a.g;
import android.support.design.a.j;
import android.support.design.widget.CoordinatorLayout.b;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.u;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Snackbar {
    private static final Handler a = new Handler(Looper.getMainLooper(), new android.os.Handler.Callback() {
        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    ((Snackbar) message.obj).b();
                    return true;
                case 1:
                    ((Snackbar) message.obj).a(message.arg1);
                    return true;
                default:
                    return false;
            }
        }
    });
    private final ViewGroup b;
    private final SnackbarLayout c;
    private Callback d;
    private final a e;

    public static abstract class Callback {

        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static class SnackbarLayout extends LinearLayout {
        private TextView a;
        private Button b;
        private int c;
        private int d;
        private b e;
        private a f;

        interface a {
            void a();
        }

        interface b {
            void a();
        }

        public SnackbarLayout(Context context) {
            this(context, null);
        }

        public SnackbarLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, j.SnackbarLayout);
            this.c = a.getDimensionPixelSize(j.SnackbarLayout_android_maxWidth, -1);
            this.d = a.getDimensionPixelSize(j.SnackbarLayout_maxActionInlineWidth, -1);
            if (a.hasValue(j.SnackbarLayout_elevation)) {
                ViewCompat.c((View) this, (float) a.getDimensionPixelSize(j.SnackbarLayout_elevation, 0));
            }
            a.recycle();
            setClickable(true);
            LayoutInflater.from(context).inflate(g.design_layout_snackbar_include, this);
            ViewCompat.i(this);
        }

        protected void onFinishInflate() {
            super.onFinishInflate();
            this.a = (TextView) findViewById(f.snackbar_text);
            this.b = (Button) findViewById(f.snackbar_action);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            boolean isMultiLine;
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (this.c > 0 && getMeasuredWidth() > this.c) {
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.c, ErrorDialogData.SUPPRESSED);
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
            int multiLineVPadding = getResources().getDimensionPixelSize(d.design_snackbar_padding_vertical_2lines);
            int singleLineVPadding = getResources().getDimensionPixelSize(d.design_snackbar_padding_vertical);
            if (this.a.getLayout().getLineCount() > 1) {
                isMultiLine = true;
            } else {
                isMultiLine = false;
            }
            boolean remeasure = false;
            if (!isMultiLine || this.d <= 0 || this.b.getMeasuredWidth() <= this.d) {
                int messagePadding;
                if (isMultiLine) {
                    messagePadding = multiLineVPadding;
                } else {
                    messagePadding = singleLineVPadding;
                }
                if (a(0, messagePadding, messagePadding)) {
                    remeasure = true;
                }
            } else if (a(1, multiLineVPadding, multiLineVPadding - singleLineVPadding)) {
                remeasure = true;
            }
            if (remeasure) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }

        final void a() {
            ViewCompat.b(this.a, 0.0f);
            ViewCompat.p(this.a).a(1.0f).a(180).b(70).c();
            if (this.b.getVisibility() == 0) {
                ViewCompat.b(this.b, 0.0f);
                ViewCompat.p(this.b).a(1.0f).a(180).b(70).c();
            }
        }

        final void b() {
            ViewCompat.b(this.a, 1.0f);
            ViewCompat.p(this.a).a(0.0f).a(180).b(0).c();
            if (this.b.getVisibility() == 0) {
                ViewCompat.b(this.b, 1.0f);
                ViewCompat.p(this.b).a(0.0f).a(180).b(0).c();
            }
        }

        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            if (changed && this.e != null) {
                this.e.a();
            }
        }

        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
        }

        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.f != null) {
                this.f.a();
            }
        }

        final void a(b onLayoutChangeListener) {
            this.e = onLayoutChangeListener;
        }

        final void a(a listener) {
            this.f = listener;
        }

        private boolean a(int orientation, int messagePadTop, int messagePadBottom) {
            boolean changed = false;
            if (orientation != getOrientation()) {
                setOrientation(orientation);
                changed = true;
            }
            if (this.a.getPaddingTop() == messagePadTop && this.a.getPaddingBottom() == messagePadBottom) {
                return changed;
            }
            View view = this.a;
            if (ViewCompat.w(view)) {
                ViewCompat.b(view, ViewCompat.j(view), messagePadTop, ViewCompat.k(view), messagePadBottom);
            } else {
                view.setPadding(view.getPaddingLeft(), messagePadTop, view.getPaddingRight(), messagePadBottom);
            }
            return true;
        }
    }

    final class a extends SwipeDismissBehavior<SnackbarLayout> {
        final /* synthetic */ Snackbar a;

        a(Snackbar snackbar) {
            this.a = snackbar;
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            view = (SnackbarLayout) view;
            if (coordinatorLayout.a(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                switch (motionEvent.getActionMasked()) {
                    case 0:
                        p.a().d(this.a.e);
                        break;
                    case 1:
                    case 3:
                        p.a().e(this.a.e);
                        break;
                }
            }
            return super.a(coordinatorLayout, view, motionEvent);
        }

        public final boolean b(View child) {
            return child instanceof SnackbarLayout;
        }
    }

    public final boolean a() {
        return p.a().f(this.e);
    }

    final void b() {
        if (this.c.getParent() == null) {
            LayoutParams lp = this.c.getLayoutParams();
            if (lp instanceof CoordinatorLayout.d) {
                b behavior = new a(this);
                behavior.b();
                behavior.c();
                behavior.a();
                behavior.a(new android.support.design.widget.SwipeDismissBehavior.a(this) {
                    final /* synthetic */ Snackbar a;

                    {
                        this.a = r1;
                    }

                    public final void a() {
                        p.a().a(this.a.e);
                    }

                    public final void a(int state) {
                        switch (state) {
                            case 0:
                                p.a().e(this.a.e);
                                return;
                            case 1:
                            case 2:
                                p.a().d(this.a.e);
                                return;
                            default:
                                return;
                        }
                    }
                });
                ((CoordinatorLayout.d) lp).a(behavior);
            }
            this.b.addView(this.c);
        }
        this.c.a(new a(this) {
            final /* synthetic */ Snackbar a;

            {
                this.a = r1;
            }

            public final void a() {
                if (this.a.a()) {
                    Snackbar.a.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            this.a.a.e();
                        }
                    });
                }
            }
        });
        if (ViewCompat.B(this.c)) {
            d();
        } else {
            this.c.a(new b(this) {
                final /* synthetic */ Snackbar a;

                {
                    this.a = r1;
                }

                public final void a() {
                    this.a.d();
                    this.a.c.a(null);
                }
            });
        }
    }

    private void d() {
        if (VERSION.SDK_INT >= 14) {
            ViewCompat.a(this.c, (float) this.c.getHeight());
            ViewCompat.p(this.c).b(0.0f).a(a.b).a(250).a(new u(this) {
                final /* synthetic */ Snackbar a;

                {
                    this.a = r1;
                }

                public final void a(View view) {
                    this.a.c.a();
                }

                public final void b(View view) {
                    if (this.a.d != null) {
                        this.a.d;
                    }
                    p.a().c(this.a.e);
                }
            }).c();
            return;
        }
        Animation anim = AnimationUtils.loadAnimation(this.c.getContext(), android.support.design.a.a.design_snackbar_in);
        anim.setInterpolator(a.b);
        anim.setDuration(250);
        anim.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ Snackbar a;

            {
                this.a = r1;
            }

            public final void onAnimationEnd(Animation animation) {
                if (this.a.d != null) {
                    this.a.d;
                }
                p.a().c(this.a.e);
            }

            public final void onAnimationStart(Animation animation) {
            }

            public final void onAnimationRepeat(Animation animation) {
            }
        });
        this.c.startAnimation(anim);
    }

    final void a(final int event) {
        if (this.c.getVisibility() == 0) {
            Object obj;
            Animation loadAnimation;
            LayoutParams layoutParams = this.c.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.d) {
                b bVar = ((CoordinatorLayout.d) layoutParams).a;
                if (bVar instanceof SwipeDismissBehavior) {
                    obj = ((SwipeDismissBehavior) bVar).d() != 0 ? 1 : null;
                    if (obj == null) {
                        if (VERSION.SDK_INT < 14) {
                            ViewCompat.p(this.c).b((float) this.c.getHeight()).a(a.b).a(250).a(new u(this) {
                                final /* synthetic */ Snackbar b;

                                public final void a(View view) {
                                    this.b.c.b();
                                }

                                public final void b(View view) {
                                    this.b.e();
                                }
                            }).c();
                            return;
                        }
                        loadAnimation = AnimationUtils.loadAnimation(this.c.getContext(), android.support.design.a.a.design_snackbar_out);
                        loadAnimation.setInterpolator(a.b);
                        loadAnimation.setDuration(250);
                        loadAnimation.setAnimationListener(new AnimationListener(this) {
                            final /* synthetic */ Snackbar b;

                            public final void onAnimationEnd(Animation animation) {
                                this.b.e();
                            }

                            public final void onAnimationStart(Animation animation) {
                            }

                            public final void onAnimationRepeat(Animation animation) {
                            }
                        });
                        this.c.startAnimation(loadAnimation);
                        return;
                    }
                }
            }
            obj = null;
            if (obj == null) {
                if (VERSION.SDK_INT < 14) {
                    loadAnimation = AnimationUtils.loadAnimation(this.c.getContext(), android.support.design.a.a.design_snackbar_out);
                    loadAnimation.setInterpolator(a.b);
                    loadAnimation.setDuration(250);
                    loadAnimation.setAnimationListener(/* anonymous class already generated */);
                    this.c.startAnimation(loadAnimation);
                    return;
                }
                ViewCompat.p(this.c).b((float) this.c.getHeight()).a(a.b).a(250).a(/* anonymous class already generated */).c();
                return;
            }
        }
        e();
    }

    private void e() {
        p.a().b(this.e);
        ViewParent parent = this.c.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.c);
        }
    }
}
