package androidx.percentlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

@Deprecated
public class PercentRelativeLayout extends RelativeLayout {
    private final a a = new a(this);

    @Deprecated
    public class LayoutParams extends android.widget.RelativeLayout.LayoutParams implements c {
        private b a;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = a.a(context, attributeSet);
        }

        public LayoutParams(int i) {
            super(-1, i);
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

    public /* synthetic */ android.widget.RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PercentRelativeLayout(Context context, AttributeSet attributeSet, int i) {
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

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1);
    }
}
