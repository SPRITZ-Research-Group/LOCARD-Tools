package androidx.appcompat.widget;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class bb implements OnTouchListener {
    final /* synthetic */ ListPopupWindow a;

    bb(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (action == 0 && this.a.g != null && this.a.g.isShowing() && x >= 0 && x < this.a.g.getWidth() && y >= 0 && y < this.a.g.getHeight()) {
            this.a.f.postDelayed(this.a.e, 250);
        } else if (action == 1) {
            this.a.f.removeCallbacks(this.a.e);
        }
        return false;
    }
}
