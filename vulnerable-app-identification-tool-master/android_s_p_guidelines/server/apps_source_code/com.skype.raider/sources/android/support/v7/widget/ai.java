package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.g;
import android.view.View;

final class ai {
    static int a(State state, ad orientation, View startChild, View endChild, g lm, boolean smoothScrollbarEnabled, boolean reverseLayout) {
        if (lm.s() == 0 || state.c() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        int itemsBefore;
        int minPosition = Math.min(g.e(startChild), g.e(endChild));
        int maxPosition = Math.max(g.e(startChild), g.e(endChild));
        if (reverseLayout) {
            itemsBefore = Math.max(0, (state.c() - maxPosition) - 1);
        } else {
            itemsBefore = Math.max(0, minPosition);
        }
        if (!smoothScrollbarEnabled) {
            return itemsBefore;
        }
        return Math.round((((float) itemsBefore) * (((float) Math.abs(orientation.b(endChild) - orientation.a(startChild))) / ((float) (Math.abs(g.e(startChild) - g.e(endChild)) + 1)))) + ((float) (orientation.c() - orientation.a(startChild))));
    }

    static int a(State state, ad orientation, View startChild, View endChild, g lm, boolean smoothScrollbarEnabled) {
        if (lm.s() == 0 || state.c() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return Math.abs(g.e(startChild) - g.e(endChild)) + 1;
        }
        return Math.min(orientation.f(), orientation.b(endChild) - orientation.a(startChild));
    }

    static int b(State state, ad orientation, View startChild, View endChild, g lm, boolean smoothScrollbarEnabled) {
        if (lm.s() == 0 || state.c() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return state.c();
        }
        return (int) ((((float) (orientation.b(endChild) - orientation.a(startChild))) / ((float) (Math.abs(g.e(startChild) - g.e(endChild)) + 1))) * ((float) state.c()));
    }
}
