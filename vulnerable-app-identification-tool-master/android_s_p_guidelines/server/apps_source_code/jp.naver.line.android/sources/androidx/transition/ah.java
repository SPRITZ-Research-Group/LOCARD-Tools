package androidx.transition;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import defpackage.bu;
import java.util.ArrayList;
import java.util.Iterator;

final class ah implements OnAttachStateChangeListener, OnPreDrawListener {
    Transition a;
    ViewGroup b;

    public final void onViewAttachedToWindow(View view) {
    }

    ah(Transition transition, ViewGroup viewGroup) {
        this.a = transition;
        this.b = viewGroup;
    }

    private void a() {
        this.b.getViewTreeObserver().removeOnPreDrawListener(this);
        this.b.removeOnAttachStateChangeListener(this);
    }

    public final void onViewDetachedFromWindow(View view) {
        a();
        ag.a.remove(this.b);
        ArrayList arrayList = (ArrayList) ag.a().get(this.b);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).resume(this.b);
            }
        }
        this.a.clearValues(true);
    }

    public final boolean onPreDraw() {
        a();
        if (!ag.a.remove(this.b)) {
            return true;
        }
        final bu a = ag.a();
        ArrayList arrayList = (ArrayList) a.get(this.b);
        ArrayList arrayList2 = null;
        if (arrayList == null) {
            arrayList = new ArrayList();
            a.put(this.b, arrayList);
        } else if (arrayList.size() > 0) {
            arrayList2 = new ArrayList(arrayList);
        }
        arrayList.add(this.a);
        this.a.addListener(new af(this) {
            final /* synthetic */ ah b;

            public final void a(Transition transition) {
                ((ArrayList) a.get(this.b.b)).remove(transition);
            }
        });
        this.a.captureValues(this.b, false);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).resume(this.b);
            }
        }
        this.a.playTransition(this.b);
        return true;
    }
}
