package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.reflect.Field;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class ListViewCompat extends ListView {
    private static final int[] g = new int[]{0};
    final Rect a;
    int b;
    int c;
    int d;
    int e;
    protected int f;
    private Field h;
    private a i;

    private static class a extends android.support.v7.graphics.drawable.a {
        private boolean a = true;

        public a(Drawable drawable) {
            super(drawable);
        }

        final void a(boolean enabled) {
            this.a = enabled;
        }

        public final boolean setState(int[] stateSet) {
            if (this.a) {
                return super.setState(stateSet);
            }
            return false;
        }

        public final void draw(Canvas canvas) {
            if (this.a) {
                super.draw(canvas);
            }
        }

        public final void setHotspot(float x, float y) {
            if (this.a) {
                super.setHotspot(x, y);
            }
        }

        public final void setHotspotBounds(int left, int top, int right, int bottom) {
            if (this.a) {
                super.setHotspotBounds(left, top, right, bottom);
            }
        }

        public final boolean setVisible(boolean visible, boolean restart) {
            if (this.a) {
                return super.setVisible(visible, restart);
            }
            return false;
        }
    }

    public ListViewCompat(Context context) {
        this(context, null);
    }

    public ListViewCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListViewCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.a = new Rect();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        try {
            this.h = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.h.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void setSelector(Drawable sel) {
        this.i = sel != null ? new a(sel) : null;
        super.setSelector(this.i);
        Rect padding = new Rect();
        if (sel != null) {
            sel.getPadding(padding);
        }
        this.b = padding.left;
        this.c = padding.top;
        this.d = padding.right;
        this.e = padding.bottom;
    }

    protected void drawableStateChanged() {
        boolean z = true;
        super.drawableStateChanged();
        b(true);
        Drawable selector = getSelector();
        if (selector != null) {
            if (!(a() && isPressed())) {
                z = false;
            }
            if (z) {
                selector.setState(getDrawableState());
            }
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.f = pointToPosition((int) ev.getX(), (int) ev.getY());
                break;
        }
        return super.onTouchEvent(ev);
    }

    protected boolean a() {
        return false;
    }

    protected final void a(int position, View sel, float x, float y) {
        boolean z;
        boolean z2 = true;
        Drawable selector = getSelector();
        if (selector == null || position == -1) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            selector.setVisible(false, false);
        }
        Rect rect = this.a;
        rect.set(sel.getLeft(), sel.getTop(), sel.getRight(), sel.getBottom());
        rect.left -= this.b;
        rect.top -= this.c;
        rect.right += this.d;
        rect.bottom += this.e;
        try {
            boolean z3 = this.h.getBoolean(this);
            if (sel.isEnabled() != z3) {
                this.h.set(this, Boolean.valueOf(!z3));
                if (position != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (z) {
            rect = this.a;
            float exactCenterX = rect.exactCenterX();
            float exactCenterY = rect.exactCenterY();
            if (getVisibility() != 0) {
                z2 = false;
            }
            selector.setVisible(z2, false);
            android.support.v4.graphics.drawable.a.a(selector, exactCenterX, exactCenterY);
        }
        Drawable selector2 = getSelector();
        if (selector2 != null && position != -1) {
            android.support.v4.graphics.drawable.a.a(selector2, x, y);
        }
    }

    public final int a(int widthMeasureSpec, int maxHeight) {
        int paddingTop = getListPaddingTop();
        int paddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int reportedDividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return paddingTop + paddingBottom;
        }
        int returnedHeight = paddingTop + paddingBottom;
        int dividerHeight = (reportedDividerHeight <= 0 || divider == null) ? 0 : reportedDividerHeight;
        View child = null;
        int viewType = 0;
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            int heightMeasureSpec;
            int newType = adapter.getItemViewType(i);
            if (newType != viewType) {
                child = null;
                viewType = newType;
            }
            child = adapter.getView(i, child, this);
            LayoutParams childLp = child.getLayoutParams();
            if (childLp == null) {
                childLp = generateDefaultLayoutParams();
                child.setLayoutParams(childLp);
            }
            if (childLp.height > 0) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(childLp.height, ErrorDialogData.SUPPRESSED);
            } else {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            }
            child.measure(widthMeasureSpec, heightMeasureSpec);
            child.forceLayout();
            if (i > 0) {
                returnedHeight += dividerHeight;
            }
            returnedHeight += child.getMeasuredHeight();
            if (returnedHeight >= maxHeight) {
                return maxHeight;
            }
        }
        return returnedHeight;
    }

    protected final void b(boolean enabled) {
        if (this.i != null) {
            this.i.a(enabled);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        if (!this.a.isEmpty()) {
            Drawable selector = getSelector();
            if (selector != null) {
                selector.setBounds(this.a);
                selector.draw(canvas);
            }
        }
        super.dispatchDraw(canvas);
    }
}
