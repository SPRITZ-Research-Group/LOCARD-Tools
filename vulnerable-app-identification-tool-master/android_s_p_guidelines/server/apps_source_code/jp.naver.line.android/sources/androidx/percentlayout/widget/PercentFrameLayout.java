package androidx.percentlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

@Deprecated
public class PercentFrameLayout extends FrameLayout {
    private final a a = new a(this);

    @Deprecated
    public class LayoutParams extends android.widget.FrameLayout.LayoutParams implements c {
        private b a;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = a.a(context, attributeSet);
        }

        public LayoutParams() {
            super(-1, -1);
        }

        public final b a() {
            if (this.a == null) {
                this.a = new b();
            }
            return this.a;
        }

        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            a.a(this, typedArray, i, i2);
        }
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    public /* synthetic */ android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    public PercentFrameLayout(Context context) {
        super(context);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private LayoutParams a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        this.a.a(i, i2);
        super.onMeasure(i, i2);
        if (this.a.b()) {
            super.onMeasure(i, i2);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.a.a();
    }

    protected /* synthetic */ android.widget.FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }
}
