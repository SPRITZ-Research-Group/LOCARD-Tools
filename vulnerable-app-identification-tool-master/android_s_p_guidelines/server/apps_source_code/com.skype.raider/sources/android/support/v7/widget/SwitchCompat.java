package android.support.v7.widget;

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
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.a;
import android.support.v7.appcompat.a.j;
import android.support.v7.content.res.b;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import com.adjust.sdk.Constants;

@RequiresApi(14)
public class SwitchCompat extends CompoundButton {
    private static final int[] N = new int[]{16842912};
    private static final Property<SwitchCompat, Float> b = new Property<SwitchCompat, Float>(Float.class, "thumbPos") {
        public final /* synthetic */ Object get(Object obj) {
            return Float.valueOf(((SwitchCompat) obj).z);
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
    ObjectAnimator a;
    private Drawable c;
    private ColorStateList d;
    private Mode e;
    private boolean f;
    private boolean g;
    private Drawable h;
    private ColorStateList i;
    private Mode j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private CharSequence q;
    private CharSequence r;
    private boolean s;
    private int t;
    private int u;
    private float v;
    private float w;
    private VelocityTracker x;
    private int y;
    private float z;

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attrs) {
        this(context, attrs, a.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = false;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = false;
        this.x = VelocityTracker.obtain();
        this.M = new Rect();
        this.H = new TextPaint(1);
        Resources res = getResources();
        this.H.density = res.getDisplayMetrics().density;
        aq a = aq.a(context, attrs, j.SwitchCompat, defStyleAttr, 0);
        this.c = a.a(j.SwitchCompat_android_thumb);
        if (this.c != null) {
            this.c.setCallback(this);
        }
        this.h = a.a(j.SwitchCompat_track);
        if (this.h != null) {
            this.h.setCallback(this);
        }
        this.q = a.c(j.SwitchCompat_android_textOn);
        this.r = a.c(j.SwitchCompat_android_textOff);
        this.s = a.a(j.SwitchCompat_showText, true);
        this.m = a.e(j.SwitchCompat_thumbTextPadding, 0);
        this.n = a.e(j.SwitchCompat_switchMinWidth, 0);
        this.o = a.e(j.SwitchCompat_switchPadding, 0);
        this.p = a.a(j.SwitchCompat_splitTrack, false);
        ColorStateList thumbTintList = a.f(j.SwitchCompat_thumbTint);
        if (thumbTintList != null) {
            this.d = thumbTintList;
            this.f = true;
        }
        Mode thumbTintMode = t.a(a.a(j.SwitchCompat_thumbTintMode, -1), null);
        if (this.e != thumbTintMode) {
            this.e = thumbTintMode;
            this.g = true;
        }
        if (this.f || this.g) {
            d();
        }
        ColorStateList trackTintList = a.f(j.SwitchCompat_trackTint);
        if (trackTintList != null) {
            this.i = trackTintList;
            this.k = true;
        }
        Mode trackTintMode = t.a(a.a(j.SwitchCompat_trackTintMode, -1), null);
        if (this.j != trackTintMode) {
            this.j = trackTintMode;
            this.l = true;
        }
        if (this.k || this.l) {
            c();
        }
        int appearance = a.g(j.SwitchCompat_switchTextAppearance, 0);
        if (appearance != 0) {
            setSwitchTextAppearance(context, appearance);
        }
        a.a();
        ViewConfiguration config = ViewConfiguration.get(context);
        this.u = config.getScaledTouchSlop();
        this.y = config.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTextAppearance(Context context, int resid) {
        Typeface typeface;
        aq appearance = aq.a(context, resid, j.TextAppearance);
        ColorStateList colors = appearance.f(j.TextAppearance_android_textColor);
        if (colors != null) {
            this.I = colors;
        } else {
            this.I = getTextColors();
        }
        int ts = appearance.e(j.TextAppearance_android_textSize, 0);
        if (!(ts == 0 || ((float) ts) == this.H.getTextSize())) {
            this.H.setTextSize((float) ts);
            requestLayout();
        }
        int typefaceIndex = appearance.a(j.TextAppearance_android_typeface, -1);
        int styleIndex = appearance.a(j.TextAppearance_android_textStyle, -1);
        switch (typefaceIndex) {
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
        setSwitchTypeface(typeface, styleIndex);
        if (appearance.a(j.TextAppearance_textAllCaps, false)) {
            this.L = new android.support.v7.text.a(getContext());
        } else {
            this.L = null;
        }
        appearance.a();
    }

    public void setSwitchTypeface(Typeface tf, int style) {
        boolean z = false;
        if (style > 0) {
            int typefaceStyle;
            float f;
            if (tf == null) {
                tf = Typeface.defaultFromStyle(style);
            } else {
                tf = Typeface.create(tf, style);
            }
            setSwitchTypeface(tf);
            if (tf != null) {
                typefaceStyle = tf.getStyle();
            } else {
                typefaceStyle = 0;
            }
            int need = style & (typefaceStyle ^ -1);
            TextPaint textPaint = this.H;
            if ((need & 1) != 0) {
                z = true;
            }
            textPaint.setFakeBoldText(z);
            textPaint = this.H;
            if ((need & 2) != 0) {
                f = -0.25f;
            } else {
                f = 0.0f;
            }
            textPaint.setTextSkewX(f);
            return;
        }
        this.H.setFakeBoldText(false);
        this.H.setTextSkewX(0.0f);
        setSwitchTypeface(tf);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.H.getTypeface() != null && !this.H.getTypeface().equals(typeface)) || (this.H.getTypeface() == null && typeface != null)) {
            this.H.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchPadding(int pixels) {
        this.o = pixels;
        requestLayout();
    }

    public void setSwitchMinWidth(int pixels) {
        this.n = pixels;
        requestLayout();
    }

    public void setThumbTextPadding(int pixels) {
        this.m = pixels;
        requestLayout();
    }

    public void setTrackDrawable(Drawable track) {
        if (this.h != null) {
            this.h.setCallback(null);
        }
        this.h = track;
        if (track != null) {
            track.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int resId) {
        setTrackDrawable(b.b(getContext(), resId));
    }

    public final Drawable a() {
        return this.h;
    }

    public void setTrackTintList(@Nullable ColorStateList tint) {
        this.i = tint;
        this.k = true;
        c();
    }

    public void setTrackTintMode(@Nullable Mode tintMode) {
        this.j = tintMode;
        this.l = true;
        c();
    }

    private void c() {
        if (this.h == null) {
            return;
        }
        if (this.k || this.l) {
            this.h = this.h.mutate();
            if (this.k) {
                android.support.v4.graphics.drawable.a.a(this.h, this.i);
            }
            if (this.l) {
                android.support.v4.graphics.drawable.a.a(this.h, this.j);
            }
            if (this.h.isStateful()) {
                this.h.setState(getDrawableState());
            }
        }
    }

    public void setThumbDrawable(Drawable thumb) {
        if (this.c != null) {
            this.c.setCallback(null);
        }
        this.c = thumb;
        if (thumb != null) {
            thumb.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbResource(int resId) {
        setThumbDrawable(b.b(getContext(), resId));
    }

    public final Drawable b() {
        return this.c;
    }

    public void setThumbTintList(@Nullable ColorStateList tint) {
        this.d = tint;
        this.f = true;
        d();
    }

    public void setThumbTintMode(@Nullable Mode tintMode) {
        this.e = tintMode;
        this.g = true;
        d();
    }

    private void d() {
        if (this.c == null) {
            return;
        }
        if (this.f || this.g) {
            this.c = this.c.mutate();
            if (this.f) {
                android.support.v4.graphics.drawable.a.a(this.c, this.d);
            }
            if (this.g) {
                android.support.v4.graphics.drawable.a.a(this.c, this.e);
            }
            if (this.c.isStateful()) {
                this.c.setState(getDrawableState());
            }
        }
    }

    public void setSplitTrack(boolean splitTrack) {
        this.p = splitTrack;
        invalidate();
    }

    public void setTextOn(CharSequence textOn) {
        this.q = textOn;
        requestLayout();
    }

    public void setTextOff(CharSequence textOff) {
        this.r = textOff;
        requestLayout();
    }

    public void setShowText(boolean showText) {
        if (this.s != showText) {
            this.s = showText;
            requestLayout();
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int thumbWidth;
        int thumbHeight;
        int maxTextWidth;
        int trackHeight;
        if (this.s) {
            if (this.J == null) {
                this.J = a(this.q);
            }
            if (this.K == null) {
                this.K = a(this.r);
            }
        }
        Rect padding = this.M;
        if (this.c != null) {
            this.c.getPadding(padding);
            thumbWidth = (this.c.getIntrinsicWidth() - padding.left) - padding.right;
            thumbHeight = this.c.getIntrinsicHeight();
        } else {
            thumbWidth = 0;
            thumbHeight = 0;
        }
        if (this.s) {
            maxTextWidth = Math.max(this.J.getWidth(), this.K.getWidth()) + (this.m * 2);
        } else {
            maxTextWidth = 0;
        }
        this.C = Math.max(maxTextWidth, thumbWidth);
        if (this.h != null) {
            this.h.getPadding(padding);
            trackHeight = this.h.getIntrinsicHeight();
        } else {
            padding.setEmpty();
            trackHeight = 0;
        }
        int paddingLeft = padding.left;
        int paddingRight = padding.right;
        if (this.c != null) {
            Rect inset = t.a(this.c);
            paddingLeft = Math.max(paddingLeft, inset.left);
            paddingRight = Math.max(paddingRight, inset.right);
        }
        int switchWidth = Math.max(this.n, ((this.C * 2) + paddingLeft) + paddingRight);
        int switchHeight = Math.max(trackHeight, thumbHeight);
        this.A = switchWidth;
        this.B = switchHeight;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredHeight() < switchHeight) {
            setMeasuredDimension(getMeasuredWidthAndState(), switchHeight);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);
        CharSequence text = isChecked() ? this.q : this.r;
        if (text != null) {
            event.getText().add(text);
        }
    }

    private Layout a(CharSequence text) {
        CharSequence transformed;
        if (this.L != null) {
            transformed = this.L.getTransformation(text, this);
        } else {
            transformed = text;
        }
        return new StaticLayout(transformed, this.H, transformed != null ? (int) Math.ceil((double) Layout.getDesiredWidth(transformed, this.H)) : 0, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        float newPos = 0.0f;
        int i = 0;
        this.x.addMovement(ev);
        float x;
        float y;
        switch (ev.getActionMasked()) {
            case 0:
                x = ev.getX();
                y = ev.getY();
                if (isEnabled()) {
                    if (this.c != null) {
                        int f = f();
                        this.c.getPadding(this.M);
                        int i2 = this.E - this.u;
                        f = (f + this.D) - this.u;
                        int i3 = (((this.C + f) + this.M.left) + this.M.right) + this.u;
                        int i4 = this.G + this.u;
                        if (x > ((float) f) && x < ((float) i3) && y > ((float) i2) && y < ((float) i4)) {
                            i = 1;
                        }
                    }
                    if (i != 0) {
                        this.t = 1;
                        this.v = x;
                        this.w = y;
                        break;
                    }
                }
                break;
            case 1:
            case 3:
                if (this.t != 2) {
                    this.t = 0;
                    this.x.clear();
                    break;
                }
                this.t = 0;
                boolean z = ev.getAction() == 1 && isEnabled();
                boolean isChecked = isChecked();
                if (z) {
                    this.x.computeCurrentVelocity(Constants.ONE_SECOND);
                    float xVelocity = this.x.getXVelocity();
                    z = Math.abs(xVelocity) > ((float) this.y) ? ax.a(this) ? xVelocity < 0.0f : xVelocity > 0.0f : e();
                } else {
                    z = isChecked;
                }
                if (z != isChecked) {
                    playSoundEffect(0);
                }
                setChecked(z);
                MotionEvent obtain = MotionEvent.obtain(ev);
                obtain.setAction(3);
                super.onTouchEvent(obtain);
                obtain.recycle();
                super.onTouchEvent(ev);
                return true;
            case 2:
                switch (this.t) {
                    case 1:
                        x = ev.getX();
                        y = ev.getY();
                        if (Math.abs(x - this.v) > ((float) this.u) || Math.abs(y - this.w) > ((float) this.u)) {
                            this.t = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.v = x;
                            this.w = y;
                            return true;
                        }
                    case 2:
                        x = ev.getX();
                        int thumbScrollRange = g();
                        float thumbScrollOffset = x - this.v;
                        float dPos = thumbScrollRange != 0 ? thumbScrollOffset / ((float) thumbScrollRange) : thumbScrollOffset > 0.0f ? 1.0f : -1.0f;
                        if (ax.a(this)) {
                            dPos = -dPos;
                        }
                        float f2 = this.z + dPos;
                        if (f2 >= 0.0f) {
                            newPos = f2 > 1.0f ? 1.0f : f2;
                        }
                        if (newPos == this.z) {
                            return true;
                        }
                        this.v = x;
                        a(newPos);
                        return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private boolean e() {
        return this.z > 0.5f;
    }

    final void a(float position) {
        this.z = position;
        invalidate();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public void setChecked(boolean checked) {
        float f = 1.0f;
        super.setChecked(checked);
        checked = isChecked();
        if (getWindowToken() == null || !ViewCompat.B(this)) {
            if (this.a != null) {
                this.a.cancel();
            }
            if (!checked) {
                f = 0.0f;
            }
            a(f);
            return;
        }
        if (!checked) {
            f = 0.0f;
        }
        this.a = ObjectAnimator.ofFloat(this, b, new float[]{f});
        this.a.setDuration(250);
        if (VERSION.SDK_INT >= 18) {
            this.a.setAutoCancel(true);
        }
        this.a.start();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int switchLeft;
        int switchRight;
        int switchTop;
        int switchBottom;
        super.onLayout(changed, left, top, right, bottom);
        int opticalInsetLeft = 0;
        int opticalInsetRight = 0;
        if (this.c != null) {
            Rect trackPadding = this.M;
            if (this.h != null) {
                this.h.getPadding(trackPadding);
            } else {
                trackPadding.setEmpty();
            }
            Rect insets = t.a(this.c);
            opticalInsetLeft = Math.max(0, insets.left - trackPadding.left);
            opticalInsetRight = Math.max(0, insets.right - trackPadding.right);
        }
        if (ax.a(this)) {
            switchLeft = getPaddingLeft() + opticalInsetLeft;
            switchRight = ((this.A + switchLeft) - opticalInsetLeft) - opticalInsetRight;
        } else {
            switchRight = (getWidth() - getPaddingRight()) - opticalInsetRight;
            switchLeft = ((switchRight - this.A) + opticalInsetLeft) + opticalInsetRight;
        }
        switch (getGravity() & 112) {
            case 16:
                switchTop = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (this.B / 2);
                switchBottom = switchTop + this.B;
                break;
            case 80:
                switchBottom = getHeight() - getPaddingBottom();
                switchTop = switchBottom - this.B;
                break;
            default:
                switchTop = getPaddingTop();
                switchBottom = switchTop + this.B;
                break;
        }
        this.D = switchLeft;
        this.E = switchTop;
        this.G = switchBottom;
        this.F = switchRight;
    }

    public void draw(Canvas c) {
        Rect thumbInsets;
        Rect padding = this.M;
        int switchLeft = this.D;
        int switchTop = this.E;
        int switchRight = this.F;
        int switchBottom = this.G;
        int thumbInitialLeft = switchLeft + f();
        if (this.c != null) {
            thumbInsets = t.a(this.c);
        } else {
            thumbInsets = t.a;
        }
        if (this.h != null) {
            this.h.getPadding(padding);
            thumbInitialLeft += padding.left;
            int trackLeft = switchLeft;
            int trackTop = switchTop;
            int trackRight = switchRight;
            int trackBottom = switchBottom;
            if (thumbInsets != null) {
                if (thumbInsets.left > padding.left) {
                    trackLeft = switchLeft + (thumbInsets.left - padding.left);
                }
                if (thumbInsets.top > padding.top) {
                    trackTop = switchTop + (thumbInsets.top - padding.top);
                }
                if (thumbInsets.right > padding.right) {
                    trackRight = switchRight - (thumbInsets.right - padding.right);
                }
                if (thumbInsets.bottom > padding.bottom) {
                    trackBottom = switchBottom - (thumbInsets.bottom - padding.bottom);
                }
            }
            this.h.setBounds(trackLeft, trackTop, trackRight, trackBottom);
        }
        if (this.c != null) {
            this.c.getPadding(padding);
            int thumbLeft = thumbInitialLeft - padding.left;
            int thumbRight = (this.C + thumbInitialLeft) + padding.right;
            this.c.setBounds(thumbLeft, switchTop, thumbRight, switchBottom);
            Drawable background = getBackground();
            if (background != null) {
                android.support.v4.graphics.drawable.a.a(background, thumbLeft, switchTop, thumbRight, switchBottom);
            }
        }
        super.draw(c);
    }

    protected void onDraw(Canvas canvas) {
        int saveCount;
        Layout switchText;
        super.onDraw(canvas);
        Rect padding = this.M;
        Drawable trackDrawable = this.h;
        if (trackDrawable != null) {
            trackDrawable.getPadding(padding);
        } else {
            padding.setEmpty();
        }
        int switchTop = this.E;
        int switchInnerTop = switchTop + padding.top;
        int switchInnerBottom = this.G - padding.bottom;
        Drawable thumbDrawable = this.c;
        if (trackDrawable != null) {
            if (!this.p || thumbDrawable == null) {
                trackDrawable.draw(canvas);
            } else {
                Rect insets = t.a(thumbDrawable);
                thumbDrawable.copyBounds(padding);
                padding.left += insets.left;
                padding.right -= insets.right;
                saveCount = canvas.save();
                canvas.clipRect(padding, Op.DIFFERENCE);
                trackDrawable.draw(canvas);
                canvas.restoreToCount(saveCount);
            }
        }
        saveCount = canvas.save();
        if (thumbDrawable != null) {
            thumbDrawable.draw(canvas);
        }
        if (e()) {
            switchText = this.J;
        } else {
            switchText = this.K;
        }
        if (switchText != null) {
            int cX;
            int[] drawableState = getDrawableState();
            if (this.I != null) {
                this.H.setColor(this.I.getColorForState(drawableState, 0));
            }
            this.H.drawableState = drawableState;
            if (thumbDrawable != null) {
                Rect bounds = thumbDrawable.getBounds();
                cX = bounds.left + bounds.right;
            } else {
                cX = getWidth();
            }
            canvas.translate((float) ((cX / 2) - (switchText.getWidth() / 2)), (float) (((switchInnerTop + switchInnerBottom) / 2) - (switchText.getHeight() / 2)));
            switchText.draw(canvas);
        }
        canvas.restoreToCount(saveCount);
    }

    public int getCompoundPaddingLeft() {
        if (!ax.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int padding = super.getCompoundPaddingLeft() + this.A;
        if (TextUtils.isEmpty(getText())) {
            return padding;
        }
        return padding + this.o;
    }

    public int getCompoundPaddingRight() {
        if (ax.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int padding = super.getCompoundPaddingRight() + this.A;
        if (TextUtils.isEmpty(getText())) {
            return padding;
        }
        return padding + this.o;
    }

    private int f() {
        float thumbPosition;
        if (ax.a(this)) {
            thumbPosition = 1.0f - this.z;
        } else {
            thumbPosition = this.z;
        }
        return (int) ((((float) g()) * thumbPosition) + 0.5f);
    }

    private int g() {
        if (this.h == null) {
            return 0;
        }
        Rect insets;
        Rect padding = this.M;
        this.h.getPadding(padding);
        if (this.c != null) {
            insets = t.a(this.c);
        } else {
            insets = t.a;
        }
        return ((((this.A - this.C) - padding.left) - padding.right) - insets.left) - insets.right;
    }

    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, N);
        }
        return drawableState;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state = getDrawableState();
        boolean changed = false;
        Drawable thumbDrawable = this.c;
        if (thumbDrawable != null && thumbDrawable.isStateful()) {
            changed = thumbDrawable.setState(state) | 0;
        }
        Drawable trackDrawable = this.h;
        if (trackDrawable != null && trackDrawable.isStateful()) {
            changed |= trackDrawable.setState(state);
        }
        if (changed) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float x, float y) {
        if (VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(x, y);
        }
        if (this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, x, y);
        }
        if (this.h != null) {
            android.support.v4.graphics.drawable.a.a(this.h, x, y);
        }
    }

    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.c || who == this.h;
    }

    public void jumpDrawablesToCurrentState() {
        if (VERSION.SDK_INT >= 14) {
            super.jumpDrawablesToCurrentState();
            if (this.c != null) {
                this.c.jumpToCurrentState();
            }
            if (this.h != null) {
                this.h.jumpToCurrentState();
            }
            if (this.a != null && this.a.isStarted()) {
                this.a.end();
                this.a = null;
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(info);
            info.setClassName("android.widget.Switch");
            CharSequence switchText = isChecked() ? this.q : this.r;
            if (!TextUtils.isEmpty(switchText)) {
                CharSequence oldText = info.getText();
                if (TextUtils.isEmpty(oldText)) {
                    info.setText(switchText);
                    return;
                }
                StringBuilder newText = new StringBuilder();
                newText.append(oldText).append(' ').append(switchText);
                info.setText(newText);
            }
        }
    }
}
