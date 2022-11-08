package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class ViewStubCompat extends View {
    private int a;
    private int b;
    private WeakReference<View> c;
    private LayoutInflater d;
    private a e;

    public interface a {
    }

    public ViewStubCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.a = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, j.ViewStubCompat, defStyle, 0);
        this.b = a.getResourceId(j.ViewStubCompat_android_inflatedId, -1);
        this.a = a.getResourceId(j.ViewStubCompat_android_layout, 0);
        setId(a.getResourceId(j.ViewStubCompat_android_id, -1));
        a.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    public final void setInflatedId(int inflatedId) {
        this.b = inflatedId;
    }

    public final void setLayoutResource(int layoutResource) {
        this.a = layoutResource;
    }

    public final void setLayoutInflater(LayoutInflater inflater) {
        this.d = inflater;
    }

    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(0, 0);
    }

    public final void draw(Canvas canvas) {
    }

    protected final void dispatchDraw(Canvas canvas) {
    }

    public final void setVisibility(int visibility) {
        if (this.c != null) {
            View view = (View) this.c.get();
            if (view != null) {
                view.setVisibility(visibility);
                return;
            }
            throw new IllegalStateException("setVisibility called on un-referenced view");
        }
        super.setVisibility(visibility);
        if (visibility == 0 || visibility == 4) {
            a();
        }
    }

    public final View a() {
        ViewParent viewParent = getParent();
        if (viewParent == null || !(viewParent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        } else if (this.a != 0) {
            LayoutInflater factory;
            ViewGroup parent = (ViewGroup) viewParent;
            if (this.d != null) {
                factory = this.d;
            } else {
                factory = LayoutInflater.from(getContext());
            }
            View view = factory.inflate(this.a, parent, false);
            if (this.b != -1) {
                view.setId(this.b);
            }
            int index = parent.indexOfChild(this);
            parent.removeViewInLayout(this);
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                parent.addView(view, index, layoutParams);
            } else {
                parent.addView(view, index);
            }
            this.c = new WeakReference(view);
            return view;
        } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
    }

    public final void setOnInflateListener(a inflateListener) {
        this.e = inflateListener;
    }
}
