package com.skype.callmonitor.symbolview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import com.facebook.react.views.text.d;
import com.skype.callmonitor.R;
import com.skype.callmonitor.symbolview.SymbolElement.SymbolCode;

public class SymbolView extends TextView implements SymbolElement {
    private SymbolCode a;
    private Drawable b;
    private boolean c;
    private boolean d;

    public SymbolView(Context context) {
        super(context);
        a(null);
    }

    public SymbolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        a(attrs);
    }

    public SymbolView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        a(attrs);
    }

    private void a(AttributeSet attrs) {
        if (isInEditMode()) {
            setText("?");
            setGravity(17);
            return;
        }
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SymbolView);
            int code = a.getInt(R.styleable.SymbolView_symbol_code, -1);
            if (code != -1) {
                setText(String.valueOf((char) code));
                this.a = SymbolCode.a((char) code);
            } else {
                setSymbolCode(SymbolCode.None);
                this.a = SymbolCode.None;
            }
            int backgroundColor = a.getColor(R.styleable.SymbolView_symbolBackground, 0);
            this.d = a.getBoolean(R.styleable.SymbolView_symbolCircleBackground, false);
            if (backgroundColor != 0) {
                setSymbolBackground(backgroundColor);
            }
            this.c = a.getBoolean(R.styleable.SymbolView_ignorePaddingForBackground, false);
            a.recycle();
        }
        setIncludeFontPadding(false);
        setTypeface(a());
        setGravity(17);
        this.b = getBackground();
        setBackgroundResource(0);
    }

    public Drawable getBackground() {
        if (this.b == null) {
            return super.getBackground();
        }
        return this.b;
    }

    protected void onDraw(Canvas canvas) {
        if (getTextSize() - ((float) getHeight()) > 0.0f) {
            setScrollY(((int) (getTextSize() - ((float) getHeight()))) / 2);
        }
        if (this.b != null) {
            this.b.setState(getDrawableState());
            if (this.c) {
                this.b.setBounds(0, 0, getWidth(), getHeight());
            } else {
                this.b.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            }
            this.b.draw(canvas);
        }
        if (LayoutDirection.a() && this.a.l) {
            canvas.translate((float) getWidth(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != 2) {
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.b == null || this.b.getMinimumWidth() == 0 || this.b.getMinimumHeight() == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(this.b.getMinimumWidth(), this.b.getMinimumHeight());
        }
    }

    public void setSymbolBackground(int color) {
        if (this.d) {
            ShapeDrawable drawable = new ShapeDrawable();
            drawable.setShape(new OvalShape());
            drawable.getPaint().setColor(color);
            setBackgroundDrawable(drawable);
            return;
        }
        setBackgroundColor(color);
    }

    public void setSymbolCode(SymbolCode code) {
        if (code == null) {
            code = SymbolCode.None;
        }
        this.a = code;
        if (code == SymbolCode.None) {
            setText("");
        } else {
            setText(code.m);
        }
        setTypeface(a());
    }

    private Typeface a() {
        return d.a().a("SkypeAssets-Medium", 0, getContext().getAssets());
    }

    public void setSymbolColor(int color) {
        setTextColor(color);
    }

    public void setVisible(boolean isVisible) {
        if (isVisible) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
