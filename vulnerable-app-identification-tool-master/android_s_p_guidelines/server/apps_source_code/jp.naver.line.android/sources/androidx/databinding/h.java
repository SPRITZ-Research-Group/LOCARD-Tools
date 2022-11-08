package androidx.databinding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class h {
    private static e a = new f();
    private static g b = null;

    public static g a() {
        return b;
    }

    public static <T extends ViewDataBinding> T a(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, boolean z) {
        return a(layoutInflater, i, viewGroup, z, b);
    }

    public static <T extends ViewDataBinding> T a(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, boolean z, g gVar) {
        int i2 = 0;
        Object obj = (viewGroup == null || !z) ? null : 1;
        if (obj != null) {
            i2 = viewGroup.getChildCount();
        }
        View inflate = layoutInflater.inflate(i, viewGroup, z);
        if (obj != null) {
            return a(gVar, viewGroup, i2, i);
        }
        return a(gVar, inflate, i);
    }

    public static <T extends ViewDataBinding> T a(View view) {
        g gVar = b;
        T binding = ViewDataBinding.getBinding(view);
        if (binding != null) {
            return binding;
        }
        Object tag = view.getTag();
        if (tag instanceof String) {
            int layoutId = a.getLayoutId((String) tag);
            if (layoutId != 0) {
                return a.getDataBinder(gVar, view, layoutId);
            }
            throw new IllegalArgumentException("View is not a binding layout. Tag: ".concat(String.valueOf(tag)));
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }

    static <T extends ViewDataBinding> T a(g gVar, View[] viewArr, int i) {
        return a.getDataBinder(gVar, viewArr, i);
    }

    static <T extends ViewDataBinding> T a(g gVar, View view, int i) {
        return a.getDataBinder(gVar, view, i);
    }

    public static <T extends ViewDataBinding> T a(Activity activity, int i) {
        g gVar = b;
        activity.setContentView(i);
        return a(gVar, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 0, i);
    }

    private static <T extends ViewDataBinding> T a(g gVar, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return a(gVar, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            viewArr[i4] = viewGroup.getChildAt(i4 + i);
        }
        return a(gVar, viewArr, i2);
    }
}
