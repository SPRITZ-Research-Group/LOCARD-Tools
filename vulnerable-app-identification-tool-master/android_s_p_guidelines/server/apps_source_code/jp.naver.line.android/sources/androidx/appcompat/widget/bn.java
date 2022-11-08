package androidx.appcompat.widget;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;

final class bn extends TouchDelegate {
    private final View a;
    private final Rect b = new Rect();
    private final Rect c = new Rect();
    private final Rect d = new Rect();
    private final int e;
    private boolean f;

    public bn(Rect rect, Rect rect2, View view) {
        super(rect, view);
        this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        a(rect, rect2);
        this.a = view;
    }

    public final void a(Rect rect, Rect rect2) {
        this.b.set(rect);
        this.d.set(rect);
        this.d.inset(-this.e, -this.e);
        this.c.set(rect2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        boolean z2 = true;
        switch (motionEvent.getAction()) {
            case 0:
                if (this.b.contains(x, y)) {
                    this.f = true;
                    z = true;
                    break;
                }
            case 1:
            case 2:
                z = this.f;
                if (z && !this.d.contains(x, y)) {
                    z2 = false;
                    break;
                }
            case 3:
                z = this.f;
                this.f = false;
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            return false;
        }
        if (!z2 || this.c.contains(x, y)) {
            motionEvent.setLocation((float) (x - this.c.left), (float) (y - this.c.top));
        } else {
            motionEvent.setLocation((float) (this.a.getWidth() / 2), (float) (this.a.getHeight() / 2));
        }
        return this.a.dispatchTouchEvent(motionEvent);
    }
}
