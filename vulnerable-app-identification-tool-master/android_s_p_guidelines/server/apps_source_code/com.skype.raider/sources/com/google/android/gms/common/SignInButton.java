package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.a.a.d;
import com.google.android.gms.b.f.a;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.common.internal.af;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements OnClickListener {
    private int a;
    private int b;
    private View c;
    private OnClickListener d;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, null);
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = null;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, d.SignInButton, 0, 0);
        try {
            this.a = obtainStyledAttributes.getInt(d.SignInButton_buttonSize, 0);
            this.b = obtainStyledAttributes.getInt(d.SignInButton_colorScheme, 2);
            setStyle(this.a, this.b);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public final void onClick(View view) {
        if (this.d != null && view == this.c) {
            this.d.onClick(this);
        }
    }

    public final void setColorScheme(int i) {
        setStyle(this.a, i);
    }

    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.c.setEnabled(z);
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.d = onClickListener;
        if (this.c != null) {
            this.c.setOnClickListener(this);
        }
    }

    @Deprecated
    public final void setScopes(Scope[] scopeArr) {
        setStyle(this.a, this.b);
    }

    public final void setSize(int i) {
        setStyle(i, this.b);
    }

    public final void setStyle(int i, int i2) {
        this.a = i;
        this.b = i2;
        Context context = getContext();
        if (this.c != null) {
            removeView(this.c);
        }
        try {
            this.c = af.a(context, this.a, this.b);
        } catch (a e) {
            int i3 = this.a;
            int i4 = this.b;
            View signInButtonImpl = new SignInButtonImpl(context);
            signInButtonImpl.a(context.getResources(), i3, i4);
            this.c = signInButtonImpl;
        }
        addView(this.c);
        this.c.setEnabled(isEnabled());
        this.c.setOnClickListener(this);
    }

    @Deprecated
    public final void setStyle(int i, int i2, Scope[] scopeArr) {
        setStyle(i, i2);
    }
}
