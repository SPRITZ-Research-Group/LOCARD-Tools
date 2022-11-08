package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.content.res.b;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] a = new int[]{16843016};
    private final m b;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 16843720);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(an.a(context), attrs, defStyleAttr);
        this.b = m.a((TextView) this);
        this.b.a(attrs, defStyleAttr);
        this.b.a();
        aq a = aq.a(getContext(), attrs, a, defStyleAttr, 0);
        setCheckMarkDrawable(a.a(0));
        a.a();
    }

    public void setCheckMarkDrawable(@DrawableRes int resId) {
        setCheckMarkDrawable(b.b(getContext(), resId));
    }

    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (this.b != null) {
            this.b.a(context, resId);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.b != null) {
            this.b.a();
        }
    }
}
