package androidx.appcompat.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode.Callback;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.core.graphics.drawable.a;
import androidx.core.widget.l;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.am;
import defpackage.hs;
import defpackage.m;
import defpackage.v;
import defpackage.w;

public class SwitchCompat extends CompoundButton {
    private static final int[] N = new int[]{16842912};
    private static final Property<SwitchCompat, Float> c = new Property<SwitchCompat, Float>(Float.class, "thumbPos") {
        public final /* synthetic */ Object get(Object obj) {
            return Float.valueOf(((SwitchCompat) obj).a);
        }

        public final /* synthetic */ void set(Object obj, Object obj2) {
            ((SwitchCompat) obj).a(((Float) obj2).floatValue());
        }
    };
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private final TextPaint H;
    private ColorStateList I;
    private Layout J;
    private Layout K;
    private TransformationMethod L;
    private final Rect M;
    float a;
    ObjectAnimator b;
    private Drawable d;
    private ColorStateList e;
    private Mode f;
    private boolean g;
    private boolean h;
    private Drawable i;
    private ColorStateList j;
    private Mode k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private CharSequence r;
    private CharSequence s;
    private boolean t;
    private int u;
    private int v;
    private float w;
    private float x;
    private VelocityTracker y;
    private int z;

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, m.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = false;
        this.y = VelocityTracker.obtain();
        this.M = new Rect();
        this.H = new TextPaint(1);
        Resources resources = getResources();
        this.H.density = resources.getDisplayMetrics().density;
        bu a = bu.a(context, attributeSet, v.SwitchCompat, i, 0);
        this.d = a.a(v.SwitchCompat_android_thumb);
        if (this.d != null) {
            this.d.setCallback(this);
        }
        this.i = a.a(v.SwitchCompat_track);
        if (this.i != null) {
            this.i.setCallback(this);
        }
        this.r = a.c(v.SwitchCompat_android_textOn);
        this.s = a.c(v.SwitchCompat_android_textOff);
        this.t = a.a(v.SwitchCompat_showText, true);
        this.n = a.e(v.SwitchCompat_thumbTextPadding, 0);
        this.o = a.e(v.SwitchCompat_switchMinWidth, 0);
        this.p = a.e(v.SwitchCompat_switchPadding, 0);
        this.q = a.a(v.SwitchCompat_splitTrack, false);
        ColorStateList e = a.e(v.SwitchCompat_thumbTint);
        if (e != null) {
            this.e = e;
            this.g = true;
        }
        Mode a2 = ap.a(a.a(v.SwitchCompat_thumbTintMode, -1), null);
        if (this.f != a2) {
            this.f = a2;
            this.h = true;
        }
        if (this.g || this.h) {
            b();
        }
        e = a.e(v.SwitchCompat_trackTint);
        if (e != null) {
            this.j = e;
            this.l = true;
        }
        a2 = ap.a(a.a(v.SwitchCompat_trackTintMode, -1), null);
        if (this.k != a2) {
            this.k = a2;
            this.m = true;
        }
        if (this.l || this.m) {
            a();
        }
        i = a.g(v.SwitchCompat_switchTextAppearance, 0);
        if (i != 0) {
            setSwitchTextAppearance(context, i);
        }
        a.a();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.v = viewConfiguration.getScaledTouchSlop();
        this.z = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTextAppearance(Context context, int i) {
        Typeface typeface;
        bu a = bu.a(context, i, v.TextAppearance);
        ColorStateList e = a.e(v.TextAppearance_android_textColor);
        if (e != null) {
            this.I = e;
        } else {
            this.I = getTextColors();
        }
        i = a.e(v.TextAppearance_android_textSize, 0);
        if (i != 0) {
            float f = (float) i;
            if (f != this.H.getTextSize()) {
                this.H.setTextSize(f);
                requestLayout();
            }
        }
        i = a.a(v.TextAppearance_android_typeface, -1);
        int a2 = a.a(v.TextAppearance_android_textStyle, -1);
        switch (i) {
            case 1:
                typeface = Typeface.SANS_SERIF;
                break;
            case 2:
                typeface = Typeface.SERIF;
                break;
            case 3:
                typeface = Typeface.MONOSPACE;
                break;
            default:
                typeface = null;
                break;
        }
        setSwitchTypeface(typeface, a2);
        if (a.a(v.TextAppearance_textAllCaps, false)) {
            this.L = new am(getContext());
        } else {
            this.L = null;
        }
        a.a();
    }

    public void setSwitchTypeface(Typeface typeface, int i) {
        float f = BitmapDescriptorFactory.HUE_RED;
        boolean z = false;
        if (i > 0) {
            if (typeface == null) {
                typeface = Typeface.defaultFromStyle(i);
            } else {
                typeface = Typeface.create(typeface, i);
            }
            setSwitchTypeface(typeface);
            int style = ((typeface != null ? typeface.getStyle() : 0) ^ -1) & i;
            TextPaint textPaint = this.H;
            if ((style & 1) != 0) {
                z = true;
            }
            textPaint.setFakeBoldText(z);
            textPaint = this.H;
            if ((style & 2) != 0) {
                f = -0.25f;
            }
            textPaint.setTextSkewX(f);
            return;
        }
        this.H.setFakeBoldText(false);
        this.H.setTextSkewX(BitmapDescriptorFactory.HUE_RED);
        setSwitchTypeface(typeface);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.H.getTypeface() != null && !this.H.getTypeface().equals(typeface)) || (this.H.getTypeface() == null && typeface != null)) {
            this.H.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchPadding(int i) {
        this.p = i;
        requestLayout();
    }

    public void setSwitchMinWidth(int i) {
        this.o = i;
        requestLayout();
    }

    public void setThumbTextPadding(int i) {
        this.n = i;
        requestLayout();
    }

    public void setTrackDrawable(Drawable drawable) {
        if (this.i != null) {
            this.i.setCallback(null);
        }
        this.i = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(w.b(getContext(), i));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.l = true;
        a();
    }

    public void setTrackTintMode(Mode mode) {
        this.k = mode;
        this.m = true;
        a();
    }

    private void a() {
        if (this.i == null) {
            return;
        }
        if (this.l || this.m) {
            this.i = this.i.mutate();
            if (this.l) {
                a.a(this.i, this.j);
            }
            if (this.m) {
                a.a(this.i, this.k);
            }
            if (this.i.isStateful()) {
                this.i.setState(getDrawableState());
            }
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        if (this.d != null) {
            this.d.setCallback(null);
        }
        this.d = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(w.b(getContext(), i));
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.e = colorStateList;
        this.g = true;
        b();
    }

    public void setThumbTintMode(Mode mode) {
        this.f = mode;
        this.h = true;
        b();
    }

    private void b() {
        if (this.d == null) {
            return;
        }
        if (this.g || this.h) {
            this.d = this.d.mutate();
            if (this.g) {
                a.a(this.d, this.e);
            }
            if (this.h) {
                a.a(this.d, this.f);
            }
            if (this.d.isStateful()) {
                this.d.setState(getDrawableState());
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.q = z;
        invalidate();
    }

    public void setTextOn(CharSequence charSequence) {
        this.r = charSequence;
        requestLayout();
    }

    public void setTextOff(CharSequence charSequence) {
        this.s = charSequence;
        requestLayout();
    }

    public void setShowText(boolean z) {
        if (this.t != z) {
            this.t = z;
            requestLayout();
        }
    }

    public void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.t) {
            if (this.J == null) {
                this.J = a(this.r);
            }
            if (this.K == null) {
                this.K = a(this.s);
            }
        }
        Rect rect = this.M;
        int i3 = 0;
        if (this.d != null) {
            this.d.getPadding(rect);
            intrinsicWidth = (this.d.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.d.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.C = Math.max(this.t ? Math.max(this.J.getWidth(), this.K.getWidth()) + (this.n * 2) : 0, intrinsicWidth);
        if (this.i != null) {
            this.i.getPadding(rect);
            i3 = this.i.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        intrinsicWidth = rect.left;
        int i4 = rect.right;
        if (this.d != null) {
            Rect a = ap.a(this.d);
            intrinsicWidth = Math.max(intrinsicWidth, a.left);
            i4 = Math.max(i4, a.right);
        }
        i4 = Math.max(this.o, ((this.C * 2) + intrinsicWidth) + i4);
        intrinsicWidth = Math.max(i3, intrinsicHeight);
        this.A = i4;
        this.B = intrinsicWidth;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < intrinsicWidth) {
            setMeasuredDimension(getMeasuredWidthAndState(), intrinsicWidth);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        Object obj = isChecked() ? this.r : this.s;
        if (obj != null) {
            accessibilityEvent.getText().add(obj);
        }
    }

    private Layout a(CharSequence charSequence) {
        if (this.L != null) {
            charSequence = this.L.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        return new StaticLayout(charSequence2, this.H, charSequence2 != null ? (int) Math.ceil((double) Layout.getDesiredWidth(charSequence2, this.H)) : 0, Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.y.addMovement(motionEvent);
        int i = 0;
        float x;
        float y;
        switch (motionEvent.getActionMasked()) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                if (isEnabled()) {
                    if (this.d != null) {
                        int d = d();
                        this.d.getPadding(this.M);
                        int i2 = this.E - this.v;
                        int i3 = (this.D + d) - this.v;
                        d = (((this.C + i3) + this.M.left) + this.M.right) + this.v;
                        int i4 = this.G + this.v;
                        if (x > ((float) i3) && x < ((float) d) && y > ((float) i2) && y < ((float) i4)) {
                            i = 1;
                        }
                    }
                    if (i != 0) {
                        this.u = 1;
                        this.w = x;
                        this.x = y;
                        break;
                    }
                }
                break;
            case 1:
            case 3:
                if (this.u != 2) {
                    this.u = 0;
                    this.y.clear();
                    break;
                }
                boolean c;
                this.u = 0;
                Object obj = (motionEvent.getAction() == 1 && isEnabled()) ? 1 : null;
                boolean isChecked = isChecked();
                if (obj != null) {
                    this.y.computeCurrentVelocity(1000);
                    x = this.y.getXVelocity();
                    c = Math.abs(x) > ((float) this.z) ? cd.a(this) ? x >= BitmapDescriptorFactory.HUE_RED : x <= BitmapDescriptorFactory.HUE_RED : c();
                } else {
                    c = isChecked;
                }
                if (c != isChecked) {
                    playSoundEffect(0);
                }
                setChecked(c);
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(3);
                super.onTouchEvent(obtain);
                obtain.recycle();
                super.onTouchEvent(motionEvent);
                return true;
                break;
            case 2:
                switch (this.u) {
                    case 1:
                        x = motionEvent.getX();
                        float y2 = motionEvent.getY();
                        if (Math.abs(x - this.w) > ((float) this.v) || Math.abs(y2 - this.x) > ((float) this.v)) {
                            this.u = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.w = x;
                            this.x = y2;
                            return true;
                        }
                    case 2:
                        float x2 = motionEvent.getX();
                        int e = e();
                        y = x2 - this.w;
                        x = e != 0 ? y / ((float) e) : y > BitmapDescriptorFactory.HUE_RED ? 1.0f : -1.0f;
                        if (cd.a(this)) {
                            x = -x;
                        }
                        x += this.a;
                        if (x < BitmapDescriptorFactory.HUE_RED) {
                            x = BitmapDescriptorFactory.HUE_RED;
                        } else if (x > 1.0f) {
                            x = 1.0f;
                        }
                        if (x != this.a) {
                            this.w = x2;
                            a(x);
                        }
                        return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean c() {
        return this.a > 0.5f;
    }

    final void a(float f) {
        this.a = f;
        invalidate();
    }

    public void toggle() {
        setChecked(isChecked() ^ 1);
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
        z = isChecked();
        IBinder windowToken = getWindowToken();
        float f = BitmapDescriptorFactory.HUE_RED;
        if (windowToken == null || !hs.C(this)) {
            if (this.b != null) {
                this.b.cancel();
            }
            if (z) {
                f = 1.0f;
            }
            a(f);
            return;
        }
        if (z) {
            f = 1.0f;
        }
        this.b = ObjectAnimator.ofFloat(this, c, new float[]{f});
        this.b.setDuration(250);
        if (VERSION.SDK_INT >= 18) {
            this.b.setAutoCancel(true);
        }
        this.b.start();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int max;
        super.onLayout(z, i, i2, i3, i4);
        i = 0;
        if (this.d != null) {
            Rect rect = this.M;
            if (this.i != null) {
                this.i.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect a = ap.a(this.d);
            i3 = Math.max(0, a.left - rect.left);
            max = Math.max(0, a.right - rect.right);
            i = i3;
        } else {
            max = 0;
        }
        if (cd.a(this)) {
            i2 = getPaddingLeft() + i;
            i3 = ((this.A + i2) - i) - max;
        } else {
            i3 = (getWidth() - getPaddingRight()) - max;
            i2 = ((i3 - this.A) + i) + max;
        }
        max = getGravity() & 112;
        if (max == 16) {
            max = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (this.B / 2);
            i = this.B + max;
        } else if (max != 80) {
            max = getPaddingTop();
            i = this.B + max;
        } else {
            i = getHeight() - getPaddingBottom();
            max = i - this.B;
        }
        this.D = i2;
        this.E = max;
        this.G = i;
        this.F = i3;
    }

    public void draw(Canvas canvas) {
        Rect a;
        Rect rect = this.M;
        int i = this.D;
        int i2 = this.E;
        int i3 = this.F;
        int i4 = this.G;
        int d = d() + i;
        if (this.d != null) {
            a = ap.a(this.d);
        } else {
            a = ap.a;
        }
        if (this.i != null) {
            int i5;
            int i6;
            this.i.getPadding(rect);
            d += rect.left;
            if (a != null) {
                if (a.left > rect.left) {
                    i += a.left - rect.left;
                }
                i5 = a.top > rect.top ? (a.top - rect.top) + i2 : i2;
                if (a.right > rect.right) {
                    i3 -= a.right - rect.right;
                }
                if (a.bottom > rect.bottom) {
                    i6 = i4 - (a.bottom - rect.bottom);
                    this.i.setBounds(i, i5, i3, i6);
                }
            } else {
                i5 = i2;
            }
            i6 = i4;
            this.i.setBounds(i, i5, i3, i6);
        }
        if (this.d != null) {
            this.d.getPadding(rect);
            i = d - rect.left;
            d = (d + this.C) + rect.right;
            this.d.setBounds(i, i2, d, i4);
            Drawable background = getBackground();
            if (background != null) {
                a.a(background, i, i2, d, i4);
            }
        }
        super.draw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        int save;
        super.onDraw(canvas);
        Rect rect = this.M;
        Drawable drawable = this.i;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i = this.E + rect.top;
        int i2 = this.G - rect.bottom;
        Drawable drawable2 = this.d;
        if (drawable != null) {
            if (!this.q || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect a = ap.a(drawable2);
                drawable2.copyBounds(rect);
                rect.left += a.left;
                rect.right -= a.right;
                save = canvas.save();
                canvas.clipRect(rect, Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = c() ? this.J : this.K;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            if (this.I != null) {
                this.H.setColor(this.I.getColorForState(drawableState, 0));
            }
            this.H.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                save = bounds.left + bounds.right;
            } else {
                save = getWidth();
            }
            canvas.translate((float) ((save / 2) - (layout.getWidth() / 2)), (float) (((i + i2) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public int getCompoundPaddingLeft() {
        if (!cd.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.A;
        if (!TextUtils.isEmpty(getText())) {
            compoundPaddingLeft += this.p;
        }
        return compoundPaddingLeft;
    }

    public int getCompoundPaddingRight() {
        if (cd.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.A;
        if (!TextUtils.isEmpty(getText())) {
            compoundPaddingRight += this.p;
        }
        return compoundPaddingRight;
    }

    private int d() {
        float f;
        if (cd.a(this)) {
            f = 1.0f - this.a;
        } else {
            f = this.a;
        }
        return (int) ((f * ((float) e())) + 0.5f);
    }

    private int e() {
        if (this.i == null) {
            return 0;
        }
        Rect a;
        Rect rect = this.M;
        this.i.getPadding(rect);
        if (this.d != null) {
            a = ap.a(this.d);
        } else {
            a = ap.a;
        }
        return ((((this.A - this.C) - rect.left) - rect.right) - a.left) - a.right;
    }

    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, N);
        }
        return onCreateDrawableState;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.d;
        int i = 0;
        if (drawable != null && drawable.isStateful()) {
            i = 0 | drawable.setState(drawableState);
        }
        drawable = this.i;
        if (drawable != null && drawable.isStateful()) {
            i |= drawable.setState(drawableState);
        }
        if (i != 0) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float f, float f2) {
        if (VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f, f2);
        }
        if (this.d != null) {
            a.a(this.d, f, f2);
        }
        if (this.i != null) {
            a.a(this.i, f, f2);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.d || drawable == this.i;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.d != null) {
            this.d.jumpToCurrentState();
        }
        if (this.i != null) {
            this.i.jumpToCurrentState();
        }
        if (this.b != null && this.b.isStarted()) {
            this.b.end();
            this.b = null;
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.r : this.s;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            CharSequence stringBuilder = new StringBuilder();
            stringBuilder.append(text);
            stringBuilder.append(' ');
            stringBuilder.append(charSequence);
            accessibilityNodeInfo.setText(stringBuilder);
        }
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        super.setCustomSelectionActionModeCallback(l.a((TextView) this, callback));
    }
}
