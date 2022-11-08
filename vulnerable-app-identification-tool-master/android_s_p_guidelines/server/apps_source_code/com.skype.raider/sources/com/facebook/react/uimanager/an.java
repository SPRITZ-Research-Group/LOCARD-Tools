package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.annotation.Nullable;

public final class an {
    private final ViewGroup a;
    private int b = 0;
    @Nullable
    private int[] c;

    public an(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public final void a(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.b++;
        }
        this.c = null;
    }

    public final void b(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.b--;
        }
        this.c = null;
    }

    public final boolean a() {
        return this.b > 0;
    }

    public final int a(int childCount, int index) {
        if (this.c == null) {
            int i;
            ArrayList<View> viewsToSort = new ArrayList();
            for (i = 0; i < childCount; i++) {
                viewsToSort.add(this.a.getChildAt(i));
            }
            Collections.sort(viewsToSort, new Comparator<View>(this) {
                final /* synthetic */ an a;

                {
                    this.a = this$0;
                }

                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    View view = (View) obj2;
                    Integer viewZIndex = ViewGroupManager.getViewZIndex((View) obj);
                    if (viewZIndex == null) {
                        viewZIndex = Integer.valueOf(0);
                    }
                    Integer viewZIndex2 = ViewGroupManager.getViewZIndex(view);
                    if (viewZIndex2 == null) {
                        viewZIndex2 = Integer.valueOf(0);
                    }
                    return viewZIndex.intValue() - viewZIndex2.intValue();
                }
            });
            this.c = new int[childCount];
            for (i = 0; i < childCount; i++) {
                this.c[i] = this.a.indexOfChild((View) viewsToSort.get(i));
            }
        }
        return this.c[index];
    }
}
