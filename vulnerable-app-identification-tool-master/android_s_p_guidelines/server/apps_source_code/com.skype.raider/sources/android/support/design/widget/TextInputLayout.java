package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.a.e;
import android.support.design.a.h;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.b;
import android.support.v4.view.u;
import android.support.v4.widget.Space;
import android.support.v7.widget.TintManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextInputLayout extends LinearLayout {
    private EditText a;
    private CharSequence b;
    private Paint c;
    private LinearLayout d;
    private boolean e;
    private TextView f;
    private int g;
    private boolean h;
    private boolean i;
    private TextView j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private ColorStateList o;
    private ColorStateList p;
    private final d q;
    private boolean r;
    private s s;

    private class a extends android.support.v4.view.a {
        final /* synthetic */ TextInputLayout a;

        private a(TextInputLayout textInputLayout) {
            this.a = textInputLayout;
        }

        /* synthetic */ a(TextInputLayout x0, byte b) {
            this(x0);
        }

        public final void a(View host, AccessibilityEvent event) {
            super.a(host, event);
            event.setClassName(TextInputLayout.class.getSimpleName());
        }

        public final void b(View host, AccessibilityEvent event) {
            super.b(host, event);
            CharSequence text = this.a.q.e();
            if (!TextUtils.isEmpty(text)) {
                event.getText().add(text);
            }
        }

        public final void a(View host, b info) {
            super.a(host, info);
            info.b(TextInputLayout.class.getSimpleName());
            CharSequence text = this.a.q.e();
            if (!TextUtils.isEmpty(text)) {
                info.c(text);
            }
            if (this.a.a != null) {
                info.d(this.a.a);
            }
            CharSequence error = this.a.f != null ? this.a.f.getText() : null;
            if (!TextUtils.isEmpty(error)) {
                info.p();
                info.e(error);
            }
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        this.q = new d(this);
        r.a(context);
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.q.a(a.b);
        this.q.b(new AccelerateInterpolator());
        this.q.d(8388659);
        TypedArray a = context.obtainStyledAttributes(attrs, j.TextInputLayout, defStyleAttr, i.Widget_Design_TextInputLayout);
        setHint(a.getText(j.TextInputLayout_android_hint));
        this.r = a.getBoolean(j.TextInputLayout_hintAnimationEnabled, true);
        if (a.hasValue(j.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateList = a.getColorStateList(j.TextInputLayout_android_textColorHint);
            this.p = colorStateList;
            this.o = colorStateList;
        }
        if (a.getResourceId(j.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(a.getResourceId(j.TextInputLayout_hintTextAppearance, 0));
        }
        this.g = a.getResourceId(j.TextInputLayout_errorTextAppearance, 0);
        boolean errorEnabled = a.getBoolean(j.TextInputLayout_errorEnabled, false);
        boolean counterEnabled = a.getBoolean(j.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(a.getInt(j.TextInputLayout_counterMaxLength, -1));
        this.l = a.getResourceId(j.TextInputLayout_counterTextAppearance, 0);
        this.m = a.getResourceId(j.TextInputLayout_counterOverflowTextAppearance, 0);
        a.recycle();
        setErrorEnabled(errorEnabled);
        setCounterEnabled(counterEnabled);
        if (ViewCompat.e(this) == 0) {
            ViewCompat.a((View) this, 1);
        }
        ViewCompat.a((View) this, new a());
    }

    public void addView(View child, int index, LayoutParams params) {
        if (child instanceof EditText) {
            EditText editText = (EditText) child;
            if (this.a != null) {
                throw new IllegalArgumentException("We already have an EditText, can only have one");
            }
            this.a = editText;
            this.q.c(this.a.getTypeface());
            this.q.a(this.a.getTextSize());
            this.q.c(this.a.getGravity());
            this.a.addTextChangedListener(new TextWatcher(this) {
                final /* synthetic */ TextInputLayout a;

                {
                    this.a = r1;
                }

                public final void afterTextChanged(Editable s) {
                    this.a.a(true);
                    if (this.a.i) {
                        this.a.a(s.length());
                    }
                }

                public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public final void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
            if (this.o == null) {
                this.o = this.a.getHintTextColors();
            }
            if (TextUtils.isEmpty(this.b)) {
                setHint(this.a.getHint());
                this.a.setHint(null);
            }
            if (this.j != null) {
                a(this.a.getText().length());
            }
            if (this.d != null) {
                a();
            }
            a(false);
            super.addView(child, 0, a(params));
            return;
        }
        super.addView(child, index, params);
    }

    public void setTypeface(@Nullable Typeface typeface) {
        this.q.c(typeface);
    }

    private LinearLayout.LayoutParams a(LayoutParams lp) {
        LinearLayout.LayoutParams llp = lp instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) lp : new LinearLayout.LayoutParams(lp);
        if (this.c == null) {
            this.c = new Paint();
        }
        this.c.setTypeface(this.q.a());
        this.c.setTextSize(this.q.c());
        llp.topMargin = (int) (-this.c.ascent());
        return llp;
    }

    private void a(boolean animate) {
        boolean hasText;
        boolean isFocused;
        CharSequence text;
        if (this.a == null || TextUtils.isEmpty(this.a.getText())) {
            hasText = false;
        } else {
            hasText = true;
        }
        for (int i : getDrawableState()) {
            if (i == 16842908) {
                isFocused = true;
                break;
            }
        }
        isFocused = false;
        if (this.e && this.f != null && this.f.getVisibility() == 0) {
            text = this.f.getText();
        } else {
            text = null;
        }
        boolean isErrorShowing = !TextUtils.isEmpty(text);
        if (this.o != null) {
            this.q.b(this.o.getDefaultColor());
        }
        if (this.n && this.j != null) {
            this.q.a(this.j.getCurrentTextColor());
        } else if (isErrorShowing && this.f != null) {
            this.q.a(this.f.getCurrentTextColor());
        } else if (isFocused && this.p != null) {
            this.q.a(this.p.getDefaultColor());
        } else if (this.o != null) {
            this.q.a(this.o.getDefaultColor());
        }
        if (hasText || isFocused || isErrorShowing) {
            if (this.s != null && this.s.b()) {
                this.s.e();
            }
            if (animate && this.r) {
                a(1.0f);
                return;
            } else {
                this.q.b(1.0f);
                return;
            }
        }
        if (this.s != null && this.s.b()) {
            this.s.e();
        }
        if (animate && this.r) {
            a(0.0f);
        } else {
            this.q.b(0.0f);
        }
    }

    public void setHint(@Nullable CharSequence hint) {
        this.b = hint;
        this.q.a(hint);
        sendAccessibilityEvent(2048);
    }

    public void setHintTextAppearance(@StyleRes int resId) {
        this.q.e(resId);
        this.p = ColorStateList.valueOf(this.q.f());
        if (this.a != null) {
            a(false);
            this.a.setLayoutParams(a(this.a.getLayoutParams()));
            this.a.requestLayout();
        }
    }

    private void a(TextView indicator, int index) {
        if (this.d == null) {
            this.d = new LinearLayout(getContext());
            this.d.setOrientation(0);
            addView(this.d, -1, -2);
            this.d.addView(new Space(getContext()), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.a != null) {
                a();
            }
        }
        this.d.setVisibility(0);
        this.d.addView(indicator, index);
    }

    private void a() {
        ViewCompat.b(this.d, ViewCompat.j(this.a), 0, ViewCompat.k(this.a), this.a.getPaddingBottom());
    }

    private void a(TextView indicator) {
        if (this.d != null) {
            this.d.removeView(indicator);
            if (this.d.getChildCount() == 0) {
                this.d.setVisibility(8);
            }
        }
    }

    public void setErrorEnabled(boolean enabled) {
        if (this.e != enabled) {
            if (this.f != null) {
                ViewCompat.p(this.f).b();
            }
            if (enabled) {
                this.f = new TextView(getContext());
                this.f.setTextAppearance(getContext(), this.g);
                this.f.setVisibility(4);
                ViewCompat.i(this.f);
                a(this.f, 0);
            } else {
                this.h = false;
                b();
                a(this.f);
                this.f = null;
            }
            this.e = enabled;
        }
    }

    public void setError(@Nullable CharSequence error) {
        if (!this.e) {
            if (!TextUtils.isEmpty(error)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(error)) {
            ViewCompat.b(this.f, 0.0f);
            this.f.setText(error);
            ViewCompat.p(this.f).a(1.0f).a(200).a(a.b).a(new u(this) {
                final /* synthetic */ TextInputLayout a;

                {
                    this.a = r1;
                }

                public final void a(View view) {
                    view.setVisibility(0);
                }
            }).c();
            this.h = true;
            b();
            a(true);
        } else if (this.f.getVisibility() == 0) {
            ViewCompat.p(this.f).a(0.0f).a(200).a(a.b).a(new u(this) {
                final /* synthetic */ TextInputLayout a;

                {
                    this.a = r1;
                }

                public final void b(View view) {
                    view.setVisibility(4);
                    this.a.a(true);
                }
            }).c();
            this.h = false;
            b();
        }
    }

    public void setCounterEnabled(boolean enabled) {
        if (this.i != enabled) {
            if (enabled) {
                this.j = new TextView(getContext());
                this.j.setMaxLines(1);
                this.j.setTextAppearance(getContext(), this.l);
                ViewCompat.i(this.j);
                a(this.j, -1);
                if (this.a == null) {
                    a(0);
                } else {
                    a(this.a.getText().length());
                }
            } else {
                a(this.j);
                this.j = null;
            }
            this.i = enabled;
        }
    }

    public void setCounterMaxLength(int maxLength) {
        if (this.k != maxLength) {
            if (maxLength > 0) {
                this.k = maxLength;
            } else {
                this.k = -1;
            }
            if (this.i) {
                a(this.a == null ? 0 : this.a.getText().length());
            }
        }
    }

    private void a(int length) {
        boolean wasCounterOverflowed = this.n;
        if (this.k == -1) {
            this.j.setText(String.valueOf(length));
            this.n = false;
        } else {
            this.n = length > this.k;
            if (wasCounterOverflowed != this.n) {
                this.j.setTextAppearance(getContext(), this.n ? this.m : this.l);
            }
            this.j.setText(getContext().getString(h.character_counter_pattern, new Object[]{Integer.valueOf(length), Integer.valueOf(this.k)}));
        }
        if (this.a != null && wasCounterOverflowed != this.n) {
            a(false);
            b();
        }
    }

    private void b() {
        if (this.h && this.f != null) {
            ViewCompat.a(this.a, ColorStateList.valueOf(this.f.getCurrentTextColor()));
        } else if (!this.n || this.j == null) {
            ViewCompat.a(this.a, TintManager.get(getContext()).getTintList(e.abc_edit_text_material));
        } else {
            ViewCompat.a(this.a, ColorStateList.valueOf(this.j.getCurrentTextColor()));
        }
    }

    public void setHintAnimationEnabled(boolean enabled) {
        this.r = enabled;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.q.a(canvas);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.a != null) {
            int l = this.a.getLeft() + this.a.getCompoundPaddingLeft();
            int r = this.a.getRight() - this.a.getCompoundPaddingRight();
            this.q.a(l, this.a.getTop() + this.a.getCompoundPaddingTop(), r, this.a.getBottom() - this.a.getCompoundPaddingBottom());
            this.q.b(l, getPaddingTop(), r, (bottom - top) - getPaddingBottom());
            this.q.d();
        }
    }

    public void refreshDrawableState() {
        super.refreshDrawableState();
        a(ViewCompat.B(this));
    }

    private void a(float target) {
        if (this.q.b() != target) {
            if (this.s == null) {
                this.s = z.a();
                this.s.a(a.a);
                this.s.a(200);
                this.s.a(new c(this) {
                    final /* synthetic */ TextInputLayout a;

                    {
                        this.a = r1;
                    }

                    public final void a(s animator) {
                        this.a.q.b(animator.d());
                    }
                });
            }
            this.s.a(this.q.b(), target);
            this.s.a();
        }
    }
}
