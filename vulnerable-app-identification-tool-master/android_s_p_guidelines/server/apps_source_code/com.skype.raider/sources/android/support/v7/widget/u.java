package android.support.v7.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.s;
import android.support.v4.widget.h;
import android.support.v7.appcompat.a.a;
import android.view.MotionEvent;
import android.view.View;

class u extends ListViewCompat {
    private boolean g;
    private boolean h;
    private boolean i;
    private s j;
    private h k;

    public u(Context context, boolean hijackFocus) {
        super(context, null, a.dropDownListViewStyle);
        this.h = hijackFocus;
        setCacheColorHint(0);
    }

    public boolean a(MotionEvent event, int activePointerId) {
        boolean handledEvent = true;
        boolean clearPressedItem = false;
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case 1:
                handledEvent = false;
                break;
            case 2:
                break;
            case 3:
                handledEvent = false;
                break;
        }
        int activeIndex = event.findPointerIndex(activePointerId);
        if (activeIndex < 0) {
            handledEvent = false;
        } else {
            int x = (int) event.getX(activeIndex);
            int y = (int) event.getY(activeIndex);
            int position = pointToPosition(x, y);
            if (position == -1) {
                clearPressedItem = true;
            } else {
                View child = getChildAt(position - getFirstVisiblePosition());
                float f = (float) x;
                float f2 = (float) y;
                this.i = true;
                if (VERSION.SDK_INT >= 21) {
                    drawableHotspotChanged(f, f2);
                }
                if (!isPressed()) {
                    setPressed(true);
                }
                layoutChildren();
                if (this.f != -1) {
                    View childAt = getChildAt(this.f - getFirstVisiblePosition());
                    if (!(childAt == null || childAt == child || !childAt.isPressed())) {
                        childAt.setPressed(false);
                    }
                }
                this.f = position;
                float left = f - ((float) child.getLeft());
                float top = f2 - ((float) child.getTop());
                if (VERSION.SDK_INT >= 21) {
                    child.drawableHotspotChanged(left, top);
                }
                if (!child.isPressed()) {
                    child.setPressed(true);
                }
                a(position, child, f, f2);
                b(false);
                refreshDrawableState();
                handledEvent = true;
                if (actionMasked == 1) {
                    performItemClick(child, position, getItemIdAtPosition(position));
                }
            }
        }
        if (!handledEvent || clearPressedItem) {
            this.i = false;
            setPressed(false);
            drawableStateChanged();
            View childAt2 = getChildAt(this.f - getFirstVisiblePosition());
            if (childAt2 != null) {
                childAt2.setPressed(false);
            }
            if (this.j != null) {
                this.j.b();
                this.j = null;
            }
        }
        if (handledEvent) {
            if (this.k == null) {
                this.k = new h(this);
            }
            this.k.a(true);
            this.k.onTouch(this, event);
        } else if (this.k != null) {
            this.k.a(false);
        }
        return handledEvent;
    }

    final void a(boolean hideListSelection) {
        this.g = hideListSelection;
    }

    protected final boolean a() {
        return this.i || super.a();
    }

    public boolean isInTouchMode() {
        return (this.h && this.g) || super.isInTouchMode();
    }

    public boolean hasWindowFocus() {
        return this.h || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.h || super.isFocused();
    }

    public boolean hasFocus() {
        return this.h || super.hasFocus();
    }
}
