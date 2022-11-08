package androidx.recyclerview.widget;

import android.view.View;

final class ce {
    static int a(by byVar, ax axVar, View view, View view2, bj bjVar, boolean z, boolean z2) {
        if (bjVar.z() == 0 || byVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max;
        int min = Math.min(bj.d(view), bj.d(view2));
        int max2 = Math.max(bj.d(view), bj.d(view2));
        if (z2) {
            max = Math.max(0, (byVar.b() - max2) - 1);
        } else {
            max = Math.max(0, min);
        }
        if (!z) {
            return max;
        }
        return Math.round((((float) max) * (((float) Math.abs(axVar.b(view2) - axVar.a(view))) / ((float) (Math.abs(bj.d(view) - bj.d(view2)) + 1)))) + ((float) (axVar.c() - axVar.a(view))));
    }

    static int a(by byVar, ax axVar, View view, View view2, bj bjVar, boolean z) {
        if (bjVar.z() == 0 || byVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(bj.d(view) - bj.d(view2)) + 1;
        }
        return Math.min(axVar.f(), axVar.b(view2) - axVar.a(view));
    }

    static int b(by byVar, ax axVar, View view, View view2, bj bjVar, boolean z) {
        if (bjVar.z() == 0 || byVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return byVar.b();
        }
        return (int) ((((float) (axVar.b(view2) - axVar.a(view))) / ((float) (Math.abs(bj.d(view) - bj.d(view2)) + 1))) * ((float) byVar.b()));
    }
}
