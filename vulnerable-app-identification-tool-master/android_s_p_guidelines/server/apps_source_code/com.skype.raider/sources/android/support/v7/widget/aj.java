package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ItemAnimator.b;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;

public abstract class aj extends ItemAnimator {
    boolean h = true;

    public abstract boolean a(s sVar);

    public abstract boolean a(s sVar, int i, int i2, int i3, int i4);

    public abstract boolean a(s sVar, s sVar2, int i, int i2, int i3, int i4);

    public abstract boolean b(s sVar);

    public final boolean f(@NonNull s viewHolder) {
        return !this.h || viewHolder.j();
    }

    public final boolean a(@NonNull s viewHolder, @NonNull b preLayoutInfo, @Nullable b postLayoutInfo) {
        int oldLeft = preLayoutInfo.a;
        int oldTop = preLayoutInfo.b;
        View disappearingItemView = viewHolder.a;
        int newLeft = postLayoutInfo == null ? disappearingItemView.getLeft() : postLayoutInfo.a;
        int newTop = postLayoutInfo == null ? disappearingItemView.getTop() : postLayoutInfo.b;
        if (viewHolder.m() || (oldLeft == newLeft && oldTop == newTop)) {
            return a(viewHolder);
        }
        disappearingItemView.layout(newLeft, newTop, disappearingItemView.getWidth() + newLeft, disappearingItemView.getHeight() + newTop);
        return a(viewHolder, oldLeft, oldTop, newLeft, newTop);
    }

    public final boolean b(@NonNull s viewHolder, @Nullable b preLayoutInfo, @NonNull b postLayoutInfo) {
        if (preLayoutInfo == null || (preLayoutInfo.a == postLayoutInfo.a && preLayoutInfo.b == postLayoutInfo.b)) {
            return b(viewHolder);
        }
        return a(viewHolder, preLayoutInfo.a, preLayoutInfo.b, postLayoutInfo.a, postLayoutInfo.b);
    }

    public final boolean c(@NonNull s viewHolder, @NonNull b preInfo, @NonNull b postInfo) {
        if (preInfo.a == postInfo.a && preInfo.b == postInfo.b) {
            e(viewHolder);
            return false;
        }
        return a(viewHolder, preInfo.a, preInfo.b, postInfo.a, postInfo.b);
    }

    public final boolean a(@NonNull s oldHolder, @NonNull s newHolder, @NonNull b preInfo, @NonNull b postInfo) {
        int toLeft;
        int toTop;
        int fromLeft = preInfo.a;
        int fromTop = preInfo.b;
        if (newHolder.b()) {
            toLeft = preInfo.a;
            toTop = preInfo.b;
        } else {
            toLeft = postInfo.a;
            toTop = postInfo.b;
        }
        return a(oldHolder, newHolder, fromLeft, fromTop, toLeft, toTop);
    }
}
