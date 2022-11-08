package android.support.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.a;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.design.widget.CoordinatorLayout.DefaultBehavior;
import android.support.design.widget.CoordinatorLayout.b;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageButton;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.List;

@DefaultBehavior(Behavior.class)
public class FloatingActionButton extends ImageButton {
    private ColorStateList a;
    private Mode b;
    private int c;
    private int d;
    private int e;
    private int f;
    private final Rect g;
    private final i h;

    public static class Behavior extends b<FloatingActionButton> {
        private static final boolean a = (VERSION.SDK_INT >= 11);
        private s b;
        private float c;
        private Rect d;

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i) {
            int i2;
            int i3 = 0;
            view = (FloatingActionButton) view;
            List c = coordinatorLayout.c(view);
            int size = c.size();
            for (i2 = 0; i2 < size; i2++) {
                View view2 = (View) c.get(i2);
                if ((view2 instanceof AppBarLayout) && a(coordinatorLayout, (AppBarLayout) view2, (FloatingActionButton) view)) {
                    break;
                }
            }
            coordinatorLayout.a(view, i);
            Rect a = view.g;
            if (a != null && a.centerX() > 0 && a.centerY() > 0) {
                d dVar = (d) view.getLayoutParams();
                if (view.getRight() >= coordinatorLayout.getWidth() - dVar.rightMargin) {
                    i2 = a.right;
                } else if (view.getLeft() <= dVar.leftMargin) {
                    i2 = -a.left;
                } else {
                    i2 = 0;
                }
                if (view.getBottom() >= coordinatorLayout.getBottom() - dVar.bottomMargin) {
                    i3 = a.bottom;
                } else if (view.getTop() <= dVar.topMargin) {
                    i3 = -a.top;
                }
                view.offsetTopAndBottom(i3);
                view.offsetLeftAndRight(i2);
            }
            return true;
        }

        public final /* synthetic */ boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            final FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof SnackbarLayout) {
                if (floatingActionButton.getVisibility() == 0) {
                    float min;
                    float f = 0.0f;
                    List c = coordinatorLayout.c((View) floatingActionButton);
                    int size = c.size();
                    int i = 0;
                    while (i < size) {
                        View view3 = (View) c.get(i);
                        if ((view3 instanceof SnackbarLayout) && coordinatorLayout.a((View) floatingActionButton, view3)) {
                            min = Math.min(f, ViewCompat.m(view3) - ((float) view3.getHeight()));
                        } else {
                            min = f;
                        }
                        i++;
                        f = min;
                    }
                    if (this.c != f) {
                        min = ViewCompat.m(floatingActionButton);
                        if (this.b != null && this.b.b()) {
                            this.b.e();
                        }
                        if (Math.abs(min - f) > ((float) floatingActionButton.getHeight()) * 0.667f) {
                            if (this.b == null) {
                                this.b = z.a();
                                this.b.a(a.b);
                                this.b.a(new c(this) {
                                    final /* synthetic */ Behavior b;

                                    public final void a(s animator) {
                                        ViewCompat.a(floatingActionButton, animator.d());
                                    }
                                });
                            }
                            this.b.a(min, f);
                            this.b.a();
                        } else {
                            ViewCompat.a((View) floatingActionButton, f);
                        }
                        this.c = f;
                    }
                }
            } else if (view2 instanceof AppBarLayout) {
                a(coordinatorLayout, (AppBarLayout) view2, floatingActionButton);
            }
            return false;
        }

        private boolean a(CoordinatorLayout parent, AppBarLayout appBarLayout, FloatingActionButton child) {
            if (((d) child.getLayoutParams()).f != appBarLayout.getId()) {
                return false;
            }
            if (this.d == null) {
                this.d = new Rect();
            }
            Rect rect = this.d;
            v.a(parent, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.b()) {
                child.b();
            } else {
                child.a();
            }
            return true;
        }

        public final /* bridge */ /* synthetic */ boolean a_(View view) {
            return a && (view instanceof SnackbarLayout);
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        Mode mode;
        super(context, attrs, defStyleAttr);
        r.a(context);
        this.g = new Rect();
        TypedArray a = context.obtainStyledAttributes(attrs, j.FloatingActionButton, defStyleAttr, i.Widget_Design_FloatingActionButton);
        this.a = a.getColorStateList(j.FloatingActionButton_backgroundTint);
        switch (a.getInt(j.FloatingActionButton_backgroundTintMode, -1)) {
            case 3:
                mode = Mode.SRC_OVER;
                break;
            case 5:
                mode = Mode.SRC_IN;
                break;
            case 9:
                mode = Mode.SRC_ATOP;
                break;
            case 14:
                mode = Mode.MULTIPLY;
                break;
            case 15:
                mode = Mode.SCREEN;
                break;
            default:
                mode = null;
                break;
        }
        this.b = mode;
        this.d = a.getColor(j.FloatingActionButton_rippleColor, 0);
        this.e = a.getInt(j.FloatingActionButton_fabSize, 0);
        this.c = a.getDimensionPixelSize(j.FloatingActionButton_borderWidth, 0);
        float elevation = a.getDimension(j.FloatingActionButton_elevation, 0.0f);
        float pressedTranslationZ = a.getDimension(j.FloatingActionButton_pressedTranslationZ, 0.0f);
        a.recycle();
        o delegate = new o(this) {
            final /* synthetic */ FloatingActionButton a;

            {
                this.a = r1;
            }

            public final float a() {
                return ((float) this.a.c()) / 2.0f;
            }

            public final void a(int left, int top, int right, int bottom) {
                this.a.g.set(left, top, right, bottom);
                this.a.setPadding(this.a.f + left, this.a.f + top, this.a.f + right, this.a.f + bottom);
            }

            public final void a(Drawable background) {
                super.setBackgroundDrawable(background);
            }
        };
        int sdk = VERSION.SDK_INT;
        if (sdk >= 21) {
            this.h = new j(this, delegate);
        } else if (sdk >= 12) {
            this.h = new h(this, delegate);
        } else {
            this.h = new g(this, delegate);
        }
        this.f = (c() - ((int) getResources().getDimension(a.d.design_fab_content_size))) / 2;
        this.h.a(this.a, this.b, this.d, this.c);
        this.h.a(elevation);
        this.h.b(pressedTranslationZ);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int preferredSize = c();
        int d = Math.min(a(preferredSize, widthMeasureSpec), a(preferredSize, heightMeasureSpec));
        setMeasuredDimension((this.g.left + d) + this.g.right, (this.g.top + d) + this.g.bottom);
    }

    public void setRippleColor(@ColorInt int color) {
        if (this.d != color) {
            this.d = color;
            this.h.a(color);
        }
    }

    @Nullable
    public ColorStateList getBackgroundTintList() {
        return this.a;
    }

    public void setBackgroundTintList(@Nullable ColorStateList tint) {
        if (this.a != tint) {
            this.a = tint;
            this.h.a(tint);
        }
    }

    @Nullable
    public Mode getBackgroundTintMode() {
        return this.b;
    }

    public void setBackgroundTintMode(@Nullable Mode tintMode) {
        if (this.b != tintMode) {
            this.b = tintMode;
            this.h.a(tintMode);
        }
    }

    public void setBackgroundDrawable(Drawable background) {
    }

    public void setBackgroundResource(int resid) {
    }

    public void setBackgroundColor(int color) {
    }

    public final void a() {
        this.h.c();
    }

    public final void b() {
        this.h.b();
    }

    final int c() {
        switch (this.e) {
            case 1:
                return getResources().getDimensionPixelSize(a.d.design_fab_size_mini);
            default:
                return getResources().getDimensionPixelSize(a.d.design_fab_size_normal);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h.f();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h.g();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.h.a(getDrawableState());
    }

    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.h.a();
    }

    private static int a(int desiredSize, int measureSpec) {
        int result = desiredSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case Integer.MIN_VALUE:
                return Math.min(desiredSize, specSize);
            case 0:
                return desiredSize;
            case ErrorDialogData.SUPPRESSED /*1073741824*/:
                return specSize;
            default:
                return result;
        }
    }
}
