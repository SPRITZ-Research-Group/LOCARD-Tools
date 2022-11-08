package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class Constraints extends ViewGroup {
    b a;

    public class LayoutParams extends androidx.constraintlayout.widget.ConstraintLayout.LayoutParams {
        public float an;
        public boolean ao;
        public float ap;
        public float aq;
        public float ar;
        public float as;
        public float at;
        public float au;
        public float av;
        public float aw;
        public float ax;
        public float ay;
        public float az;

        public LayoutParams() {
            super(-2, -2);
            this.an = 1.0f;
            this.ao = false;
            this.ap = BitmapDescriptorFactory.HUE_RED;
            this.aq = BitmapDescriptorFactory.HUE_RED;
            this.ar = BitmapDescriptorFactory.HUE_RED;
            this.as = BitmapDescriptorFactory.HUE_RED;
            this.at = 1.0f;
            this.au = 1.0f;
            this.av = BitmapDescriptorFactory.HUE_RED;
            this.aw = BitmapDescriptorFactory.HUE_RED;
            this.ax = BitmapDescriptorFactory.HUE_RED;
            this.ay = BitmapDescriptorFactory.HUE_RED;
            this.az = BitmapDescriptorFactory.HUE_RED;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.an = 1.0f;
            int i = 0;
            this.ao = false;
            this.ap = BitmapDescriptorFactory.HUE_RED;
            this.aq = BitmapDescriptorFactory.HUE_RED;
            this.ar = BitmapDescriptorFactory.HUE_RED;
            this.as = BitmapDescriptorFactory.HUE_RED;
            this.at = 1.0f;
            this.au = 1.0f;
            this.av = BitmapDescriptorFactory.HUE_RED;
            this.aw = BitmapDescriptorFactory.HUE_RED;
            this.ax = BitmapDescriptorFactory.HUE_RED;
            this.ay = BitmapDescriptorFactory.HUE_RED;
            this.az = BitmapDescriptorFactory.HUE_RED;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.ConstraintSet);
            int indexCount = obtainStyledAttributes.getIndexCount();
            while (i < indexCount) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == f.ConstraintSet_android_alpha) {
                    this.an = obtainStyledAttributes.getFloat(index, this.an);
                } else if (index == f.ConstraintSet_android_elevation) {
                    this.ap = obtainStyledAttributes.getFloat(index, this.ap);
                    this.ao = true;
                } else if (index == f.ConstraintSet_android_rotationX) {
                    this.ar = obtainStyledAttributes.getFloat(index, this.ar);
                } else if (index == f.ConstraintSet_android_rotationY) {
                    this.as = obtainStyledAttributes.getFloat(index, this.as);
                } else if (index == f.ConstraintSet_android_rotation) {
                    this.aq = obtainStyledAttributes.getFloat(index, this.aq);
                } else if (index == f.ConstraintSet_android_scaleX) {
                    this.at = obtainStyledAttributes.getFloat(index, this.at);
                } else if (index == f.ConstraintSet_android_scaleY) {
                    this.au = obtainStyledAttributes.getFloat(index, this.au);
                } else if (index == f.ConstraintSet_android_transformPivotX) {
                    this.av = obtainStyledAttributes.getFloat(index, this.av);
                } else if (index == f.ConstraintSet_android_transformPivotY) {
                    this.aw = obtainStyledAttributes.getFloat(index, this.aw);
                } else if (index == f.ConstraintSet_android_translationX) {
                    this.ax = obtainStyledAttributes.getFloat(index, this.ax);
                } else if (index == f.ConstraintSet_android_translationY) {
                    this.ay = obtainStyledAttributes.getFloat(index, this.ay);
                } else if (index == f.ConstraintSet_android_translationZ) {
                    this.ax = obtainStyledAttributes.getFloat(index, this.az);
                }
                i++;
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public Constraints(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new androidx.constraintlayout.widget.ConstraintLayout.LayoutParams(layoutParams);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
