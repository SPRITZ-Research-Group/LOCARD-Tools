package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import defpackage.dx;
import defpackage.hs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ah {

    /* renamed from: androidx.fragment.app.ah$2 */
    final class AnonymousClass2 implements Runnable {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ Map b;
        final /* synthetic */ ah c;

        AnonymousClass2(ah ahVar, ArrayList arrayList, Map map) {
            this.c = ahVar;
            this.a = arrayList;
            this.b = map;
        }

        public final void run() {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.a.get(i);
                String r = hs.r(view);
                if (r != null) {
                    for (Entry entry : this.b.entrySet()) {
                        if (r.equals(entry.getValue())) {
                            r = (String) entry.getKey();
                            break;
                        }
                    }
                    r = null;
                    hs.a(view, r);
                }
            }
        }
    }

    /* renamed from: androidx.fragment.app.ah$3 */
    final class AnonymousClass3 implements Runnable {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ Map b;
        final /* synthetic */ ah c;

        AnonymousClass3(ah ahVar, ArrayList arrayList, Map map) {
            this.c = ahVar;
            this.a = arrayList;
            this.b = map;
        }

        public final void run() {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.a.get(i);
                hs.a(view, (String) this.b.get(hs.r(view)));
            }
        }
    }

    public abstract Object a(Object obj, Object obj2, Object obj3);

    public abstract void a(ViewGroup viewGroup, Object obj);

    public abstract void a(Object obj, Rect rect);

    public abstract void a(Object obj, View view);

    public abstract void a(Object obj, View view, ArrayList<View> arrayList);

    public abstract void a(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void a(Object obj, ArrayList<View> arrayList);

    public abstract void a(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract boolean a(Object obj);

    public abstract Object b(Object obj);

    public abstract Object b(Object obj, Object obj2, Object obj3);

    public abstract void b(Object obj, View view);

    public abstract void b(Object obj, View view, ArrayList<View> arrayList);

    public abstract void b(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object c(Object obj);

    public abstract void c(Object obj, View view);

    protected static void a(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    static ArrayList<String> a(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayList.get(i);
            arrayList2.add(hs.r(view));
            hs.a(view, null);
        }
        return arrayList2;
    }

    final void a(View view, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayList<String> arrayList3, Map<String, String> map) {
        final int size = arrayList2.size();
        final ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < size; i++) {
            View view2 = (View) arrayList.get(i);
            String r = hs.r(view2);
            arrayList4.add(r);
            if (r != null) {
                hs.a(view2, null);
                String str = (String) map.get(r);
                for (int i2 = 0; i2 < size; i2++) {
                    if (str.equals(arrayList3.get(i2))) {
                        hs.a((View) arrayList2.get(i2), r);
                        break;
                    }
                }
            }
        }
        final ArrayList<View> arrayList5 = arrayList2;
        final ArrayList<String> arrayList6 = arrayList3;
        final ArrayList<View> arrayList7 = arrayList;
        ai.a(view, new Runnable(this) {
            final /* synthetic */ ah f;

            public final void run() {
                for (int i = 0; i < size; i++) {
                    hs.a((View) arrayList5.get(i), (String) arrayList6.get(i));
                    hs.a((View) arrayList7.get(i), (String) arrayList4.get(i));
                }
            }
        });
    }

    final void a(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() == 0) {
            if (view instanceof ViewGroup) {
                boolean isTransitionGroup;
                ViewGroup viewGroup = (ViewGroup) view;
                if (VERSION.SDK_INT >= 21) {
                    isTransitionGroup = viewGroup.isTransitionGroup();
                } else {
                    Boolean bool = (Boolean) viewGroup.getTag(dx.tag_transition_group);
                    isTransitionGroup = ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && hs.r(viewGroup) == null) ? false : true;
                }
                if (isTransitionGroup) {
                    arrayList.add(viewGroup);
                    return;
                }
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a((ArrayList) arrayList, viewGroup.getChildAt(i));
                }
                return;
            }
            arrayList.add(view);
        }
    }

    final void a(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String r = hs.r(view);
            if (r != null) {
                map.put(r, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a((Map) map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    protected static void a(List<View> list, View view) {
        int size = list.size();
        if (!a((List) list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); i++) {
                View view2 = (View) list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!a((List) list, childAt, size)) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    private static boolean a(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    protected static boolean a(List list) {
        return list == null || list.isEmpty();
    }
}
