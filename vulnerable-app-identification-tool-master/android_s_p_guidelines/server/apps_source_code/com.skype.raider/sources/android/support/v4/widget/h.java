package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.ListView;

public final class h extends a {
    private final ListView f;

    public h(ListView target) {
        super(target);
        this.f = target;
    }

    public final void a(int deltaY) {
        ListView listView = this.f;
        if (VERSION.SDK_INT >= 19) {
            listView.scrollListBy(deltaY);
            return;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition != -1) {
            View childAt = listView.getChildAt(0);
            if (childAt != null) {
                listView.setSelectionFromTop(firstVisiblePosition, childAt.getTop() - deltaY);
            }
        }
    }

    public final boolean b(int direction) {
        ListView target = this.f;
        int itemCount = target.getCount();
        if (itemCount == 0) {
            return false;
        }
        int childCount = target.getChildCount();
        int firstPosition = target.getFirstVisiblePosition();
        int lastPosition = firstPosition + childCount;
        if (direction > 0) {
            if (lastPosition >= itemCount && target.getChildAt(childCount - 1).getBottom() <= target.getHeight()) {
                return false;
            }
        } else if (direction >= 0) {
            return false;
        } else {
            if (firstPosition <= 0 && target.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }
}
