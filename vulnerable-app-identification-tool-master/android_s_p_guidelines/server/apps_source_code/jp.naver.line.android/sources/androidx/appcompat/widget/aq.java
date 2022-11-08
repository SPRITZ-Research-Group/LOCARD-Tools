package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.core.graphics.drawable.a;
import androidx.core.widget.h;
import defpackage.hz;
import defpackage.m;
import java.lang.reflect.Field;

class aq extends ListView {
    as a;
    private final Rect b = new Rect();
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g;
    private Field h;
    private ar i;
    private boolean j;
    private boolean k;
    private boolean l;
    private hz m;
    private h n;

    aq(Context context, boolean z) {
        super(context, null, m.dropDownListViewStyle);
        this.k = z;
        setCacheColorHint(0);
        try {
            this.h = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.h.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public boolean isInTouchMode() {
        return (this.k && this.j) || super.isInTouchMode();
    }

    public boolean hasWindowFocus() {
        return this.k || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.k || super.isFocused();
    }

    public boolean hasFocus() {
        return this.k || super.hasFocus();
    }

    public void setSelector(Drawable drawable) {
        this.i = drawable != null ? new ar(drawable) : null;
        super.setSelector(this.i);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.c = rect.left;
        this.d = rect.top;
        this.e = rect.right;
        this.f = rect.bottom;
    }

    protected void drawableStateChanged() {
        if (this.a == null) {
            super.drawableStateChanged();
            b(true);
            a();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.g = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (this.a != null) {
            Runnable runnable = this.a;
            runnable.a.a = null;
            runnable.a.removeCallbacks(runnable);
        }
        return super.onTouchEvent(motionEvent);
    }

    public int a(int i, int i2, int i3, int i4, int i5) {
        i2 = getListPaddingTop();
        i3 = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return i2 + i3;
        }
        i2 += i3;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i6 = i2;
        View view = null;
        i2 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i2 < count) {
            int itemViewType = adapter.getItemViewType(i2);
            if (itemViewType != i7) {
                view = null;
                i7 = itemViewType;
            }
            view = adapter.getView(i2, view, this);
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (layoutParams.height > 0) {
                itemViewType = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            } else {
                itemViewType = MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, itemViewType);
            view.forceLayout();
            if (i2 > 0) {
                i6 += dividerHeight;
            }
            i6 += view.getMeasuredHeight();
            if (i6 >= i4) {
                return (i5 < 0 || i2 <= i5 || i8 <= 0 || i6 == i4) ? i4 : i8;
            } else {
                if (i5 >= 0 && i2 >= i5) {
                    i8 = i6;
                }
                i2++;
            }
        }
        return i6;
    }

    private void b(boolean z) {
        if (this.i != null) {
            this.i.a(z);
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.a == null) {
            this.a = new as(this);
            Runnable runnable = this.a;
            runnable.a.post(runnable);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                a();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    protected void onDetachedFromWindow() {
        this.a = null;
        super.onDetachedFromWindow();
    }

    public boolean a(MotionEvent motionEvent, int i) {
        boolean z;
        Object obj;
        View childAt;
        View view = this;
        MotionEvent motionEvent2 = motionEvent;
        int actionMasked = motionEvent.getActionMasked();
        boolean z2 = false;
        switch (actionMasked) {
            case 1:
                z = false;
                break;
            case 2:
                z = true;
                break;
            case 3:
                obj = null;
                break;
            default:
                obj = null;
                z2 = true;
                break;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i);
        if (findPointerIndex >= 0) {
            int x = (int) motionEvent2.getX(findPointerIndex);
            findPointerIndex = (int) motionEvent2.getY(findPointerIndex);
            int pointToPosition = pointToPosition(x, findPointerIndex);
            if (pointToPosition == -1) {
                z2 = z;
                obj = 1;
                if (!(z2 && obj == null)) {
                    view.l = false;
                    setPressed(false);
                    drawableStateChanged();
                    childAt = getChildAt(view.g - getFirstVisiblePosition());
                    if (childAt != null) {
                        childAt.setPressed(false);
                    }
                    if (view.m != null) {
                        view.m.e();
                        view.m = null;
                    }
                }
                if (!z2) {
                    if (view.n == null) {
                        view.n = new h(view);
                    }
                    view.n.a(true);
                    view.n.onTouch(view, motionEvent2);
                } else if (view.n != null) {
                    view.n.a(false);
                }
                return z2;
            }
            boolean z3;
            View childAt2 = getChildAt(pointToPosition - getFirstVisiblePosition());
            float f = (float) x;
            float f2 = (float) findPointerIndex;
            view.l = true;
            if (VERSION.SDK_INT >= 21) {
                drawableHotspotChanged(f, f2);
            }
            if (!isPressed()) {
                setPressed(true);
            }
            layoutChildren();
            if (view.g != -1) {
                childAt = getChildAt(view.g - getFirstVisiblePosition());
                if (!(childAt == null || childAt == childAt2 || !childAt.isPressed())) {
                    childAt.setPressed(false);
                }
            }
            view.g = pointToPosition;
            float left = f - ((float) childAt2.getLeft());
            float top = f2 - ((float) childAt2.getTop());
            if (VERSION.SDK_INT >= 21) {
                childAt2.drawableHotspotChanged(left, top);
            }
            if (!childAt2.isPressed()) {
                childAt2.setPressed(true);
            }
            Drawable selector = getSelector();
            Object obj2 = (selector == null || pointToPosition == -1) ? null : 1;
            if (obj2 != null) {
                selector.setVisible(false, false);
            }
            Rect rect = view.b;
            rect.set(childAt2.getLeft(), childAt2.getTop(), childAt2.getRight(), childAt2.getBottom());
            rect.left -= view.c;
            rect.top -= view.d;
            rect.right += view.e;
            rect.bottom += view.f;
            try {
                z = view.h.getBoolean(view);
                if (childAt2.isEnabled() != z) {
                    view.h.set(view, Boolean.valueOf(z ^ true));
                    if (pointToPosition != -1) {
                        refreshDrawableState();
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj2 != null) {
                rect = view.b;
                float exactCenterX = rect.exactCenterX();
                left = rect.exactCenterY();
                z3 = false;
                selector.setVisible(getVisibility() == 0, false);
                a.a(selector, exactCenterX, left);
            } else {
                z3 = false;
            }
            Drawable selector2 = getSelector();
            if (!(selector2 == null || pointToPosition == -1)) {
                a.a(selector2, f, f2);
            }
            b(z3);
            refreshDrawableState();
            if (actionMasked == 1) {
                performItemClick(childAt2, pointToPosition, getItemIdAtPosition(pointToPosition));
            }
            obj = null;
            z2 = true;
            view.l = false;
            setPressed(false);
            drawableStateChanged();
            childAt = getChildAt(view.g - getFirstVisiblePosition());
            if (childAt != null) {
                childAt.setPressed(false);
            }
            if (view.m != null) {
                view.m.e();
                view.m = null;
            }
            if (!z2) {
                if (view.n == null) {
                    view.n = new h(view);
                }
                view.n.a(true);
                view.n.onTouch(view, motionEvent2);
            } else if (view.n != null) {
                view.n.a(false);
            }
            return z2;
        }
        obj = null;
        view.l = false;
        setPressed(false);
        drawableStateChanged();
        childAt = getChildAt(view.g - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        if (view.m != null) {
            view.m.e();
            view.m = null;
        }
        if (!z2) {
            if (view.n == null) {
                view.n = new h(view);
            }
            view.n.a(true);
            view.n.onTouch(view, motionEvent2);
        } else if (view.n != null) {
            view.n.a(false);
        }
        return z2;
    }

    final void a(boolean z) {
        this.j = z;
    }

    private void a() {
        Drawable selector = getSelector();
        if (selector != null && this.l && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        if (!this.b.isEmpty()) {
            Drawable selector = getSelector();
            if (selector != null) {
                selector.setBounds(this.b);
                selector.draw(canvas);
            }
        }
        super.dispatchDraw(canvas);
    }
}
