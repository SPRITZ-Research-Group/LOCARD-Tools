package androidx.transition;

import android.view.ViewGroup;
import defpackage.bu;
import defpackage.hs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public final class ag {
    static ArrayList<ViewGroup> a = new ArrayList();
    private static Transition b = new AutoTransition();
    private static ThreadLocal<WeakReference<bu<ViewGroup, ArrayList<Transition>>>> c = new ThreadLocal();

    static bu<ViewGroup, ArrayList<Transition>> a() {
        bu<ViewGroup, ArrayList<Transition>> buVar;
        WeakReference weakReference = (WeakReference) c.get();
        if (weakReference != null) {
            buVar = (bu) weakReference.get();
            if (buVar != null) {
                return buVar;
            }
        }
        buVar = new bu();
        c.set(new WeakReference(buVar));
        return buVar;
    }

    public static void a(ViewGroup viewGroup, Transition transition) {
        if (!a.contains(viewGroup) && hs.C(viewGroup)) {
            a.add(viewGroup);
            if (transition == null) {
                transition = b;
            }
            transition = transition.clone();
            ArrayList arrayList = (ArrayList) a().get(viewGroup);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).pause(viewGroup);
                }
            }
            if (transition != null) {
                transition.captureValues(viewGroup, true);
            }
            v a = v.a(viewGroup);
            if (a != null) {
                a.a();
            }
            viewGroup.setTag(t.transition_current_scene, null);
            if (transition != null && viewGroup != null) {
                Object ahVar = new ah(transition, viewGroup);
                viewGroup.addOnAttachStateChangeListener(ahVar);
                viewGroup.getViewTreeObserver().addOnPreDrawListener(ahVar);
            }
        }
    }
}
