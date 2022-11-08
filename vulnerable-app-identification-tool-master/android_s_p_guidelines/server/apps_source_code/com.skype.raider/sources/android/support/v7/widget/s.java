package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class s extends aj {
    private static TimeInterpolator i;
    ArrayList<ArrayList<android.support.v7.widget.RecyclerView.s>> a = new ArrayList();
    ArrayList<ArrayList<b>> b = new ArrayList();
    ArrayList<ArrayList<a>> c = new ArrayList();
    ArrayList<android.support.v7.widget.RecyclerView.s> d = new ArrayList();
    ArrayList<android.support.v7.widget.RecyclerView.s> e = new ArrayList();
    ArrayList<android.support.v7.widget.RecyclerView.s> f = new ArrayList();
    ArrayList<android.support.v7.widget.RecyclerView.s> g = new ArrayList();
    private ArrayList<android.support.v7.widget.RecyclerView.s> j = new ArrayList();
    private ArrayList<android.support.v7.widget.RecyclerView.s> k = new ArrayList();
    private ArrayList<b> l = new ArrayList();
    private ArrayList<a> m = new ArrayList();

    /* renamed from: android.support.v7.widget.s$5 */
    class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ android.support.v7.widget.RecyclerView.s a;
        final /* synthetic */ View b;
        final /* synthetic */ ViewPropertyAnimator c;
        final /* synthetic */ s d;

        AnonymousClass5(s this$0, android.support.v7.widget.RecyclerView.s sVar, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.d = this$0;
            this.a = sVar;
            this.b = view;
            this.c = viewPropertyAnimator;
        }

        public final void onAnimationStart(Animator animator) {
        }

        public final void onAnimationCancel(Animator animator) {
            this.b.setAlpha(1.0f);
        }

        public final void onAnimationEnd(Animator animator) {
            this.c.setListener(null);
            this.d.e(this.a);
            this.d.d.remove(this.a);
            this.d.c();
        }
    }

    /* renamed from: android.support.v7.widget.s$6 */
    class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ android.support.v7.widget.RecyclerView.s a;
        final /* synthetic */ int b;
        final /* synthetic */ View c;
        final /* synthetic */ int d;
        final /* synthetic */ ViewPropertyAnimator e;
        final /* synthetic */ s f;

        AnonymousClass6(s this$0, android.support.v7.widget.RecyclerView.s sVar, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
            this.f = this$0;
            this.a = sVar;
            this.b = i;
            this.c = view;
            this.d = i2;
            this.e = viewPropertyAnimator;
        }

        public final void onAnimationStart(Animator animator) {
        }

        public final void onAnimationCancel(Animator animator) {
            if (this.b != 0) {
                this.c.setTranslationX(0.0f);
            }
            if (this.d != 0) {
                this.c.setTranslationY(0.0f);
            }
        }

        public final void onAnimationEnd(Animator animator) {
            this.e.setListener(null);
            this.f.e(this.a);
            this.f.e.remove(this.a);
            this.f.c();
        }
    }

    /* renamed from: android.support.v7.widget.s$7 */
    class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ a a;
        final /* synthetic */ ViewPropertyAnimator b;
        final /* synthetic */ View c;
        final /* synthetic */ s d;

        AnonymousClass7(s this$0, a aVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.d = this$0;
            this.a = aVar;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        public final void onAnimationStart(Animator animator) {
        }

        public final void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            this.c.setTranslationX(0.0f);
            this.c.setTranslationY(0.0f);
            this.d.e(this.a.a);
            this.d.g.remove(this.a.a);
            this.d.c();
        }
    }

    /* renamed from: android.support.v7.widget.s$8 */
    class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ a a;
        final /* synthetic */ ViewPropertyAnimator b;
        final /* synthetic */ View c;
        final /* synthetic */ s d;

        AnonymousClass8(s this$0, a aVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.d = this$0;
            this.a = aVar;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        public final void onAnimationStart(Animator animator) {
        }

        public final void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            this.c.setTranslationX(0.0f);
            this.c.setTranslationY(0.0f);
            this.d.e(this.a.b);
            this.d.g.remove(this.a.b);
            this.d.c();
        }
    }

    private static class a {
        public android.support.v7.widget.RecyclerView.s a;
        public android.support.v7.widget.RecyclerView.s b;
        public int c;
        public int d;
        public int e;
        public int f;

        private a(android.support.v7.widget.RecyclerView.s oldHolder, android.support.v7.widget.RecyclerView.s newHolder) {
            this.a = oldHolder;
            this.b = newHolder;
        }

        a(android.support.v7.widget.RecyclerView.s oldHolder, android.support.v7.widget.RecyclerView.s newHolder, int fromX, int fromY, int toX, int toY) {
            this(oldHolder, newHolder);
            this.c = fromX;
            this.d = fromY;
            this.e = toX;
            this.f = toY;
        }

        public final String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    private static class b {
        public android.support.v7.widget.RecyclerView.s a;
        public int b;
        public int c;
        public int d;
        public int e;

        b(android.support.v7.widget.RecyclerView.s holder, int fromX, int fromY, int toX, int toY) {
            this.a = holder;
            this.b = fromX;
            this.c = fromY;
            this.d = toX;
            this.e = toY;
        }
    }

    public final void a() {
        boolean removalsPending = !this.j.isEmpty();
        boolean movesPending = !this.l.isEmpty();
        boolean changesPending = !this.m.isEmpty();
        boolean additionsPending = !this.k.isEmpty();
        if (removalsPending || movesPending || additionsPending || changesPending) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                final android.support.v7.widget.RecyclerView.s holder = (android.support.v7.widget.RecyclerView.s) it.next();
                View view = holder.a;
                ViewPropertyAnimator animate = view.animate();
                this.f.add(holder);
                final ViewPropertyAnimator viewPropertyAnimator = animate;
                final View view2 = view;
                animate.setDuration(g()).alpha(0.0f).setListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ s d;

                    public final void onAnimationStart(Animator animator) {
                    }

                    public final void onAnimationEnd(Animator animator) {
                        viewPropertyAnimator.setListener(null);
                        view2.setAlpha(1.0f);
                        this.d.e(holder);
                        this.d.f.remove(holder);
                        this.d.c();
                    }
                }).start();
            }
            this.j.clear();
            if (movesPending) {
                ArrayList<b> moves = new ArrayList();
                moves.addAll(this.l);
                this.b.add(moves);
                this.l.clear();
                final ArrayList<b> arrayList = moves;
                Runnable mover = new Runnable(this) {
                    final /* synthetic */ s b;

                    public final void run() {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            b moveInfo = (b) it.next();
                            s sVar = this.b;
                            android.support.v7.widget.RecyclerView.s sVar2 = moveInfo.a;
                            int i = moveInfo.b;
                            int i2 = moveInfo.c;
                            int i3 = moveInfo.d;
                            int i4 = moveInfo.e;
                            View view = sVar2.a;
                            i3 -= i;
                            i2 = i4 - i2;
                            if (i3 != 0) {
                                view.animate().translationX(0.0f);
                            }
                            if (i2 != 0) {
                                view.animate().translationY(0.0f);
                            }
                            ViewPropertyAnimator animate = view.animate();
                            sVar.e.add(sVar2);
                            animate.setDuration(sVar.e()).setListener(new AnonymousClass6(sVar, sVar2, i3, view, i2, animate)).start();
                        }
                        arrayList.clear();
                        this.b.b.remove(arrayList);
                    }
                };
                if (removalsPending) {
                    ViewCompat.a(((b) moves.get(0)).a.a, mover, g());
                } else {
                    mover.run();
                }
            }
            if (changesPending) {
                final ArrayList<a> changes = new ArrayList();
                changes.addAll(this.m);
                this.c.add(changes);
                this.m.clear();
                Runnable changer = new Runnable(this) {
                    final /* synthetic */ s b;

                    public final void run() {
                        Iterator it = changes.iterator();
                        while (it.hasNext()) {
                            View view;
                            a change = (a) it.next();
                            s sVar = this.b;
                            android.support.v7.widget.RecyclerView.s sVar2 = change.a;
                            View view2 = sVar2 == null ? null : sVar2.a;
                            android.support.v7.widget.RecyclerView.s sVar3 = change.b;
                            if (sVar3 != null) {
                                view = sVar3.a;
                            } else {
                                view = null;
                            }
                            if (view2 != null) {
                                ViewPropertyAnimator duration = view2.animate().setDuration(sVar.h());
                                sVar.g.add(change.a);
                                duration.translationX((float) (change.e - change.c));
                                duration.translationY((float) (change.f - change.d));
                                duration.alpha(0.0f).setListener(new AnonymousClass7(sVar, change, duration, view2)).start();
                            }
                            if (view != null) {
                                ViewPropertyAnimator animate = view.animate();
                                sVar.g.add(change.b);
                                animate.translationX(0.0f).translationY(0.0f).setDuration(sVar.h()).alpha(1.0f).setListener(new AnonymousClass8(sVar, change, animate, view)).start();
                            }
                        }
                        changes.clear();
                        this.b.c.remove(changes);
                    }
                };
                if (removalsPending) {
                    ViewCompat.a(((a) changes.get(0)).a.a, changer, g());
                } else {
                    changer.run();
                }
            }
            if (additionsPending) {
                final ArrayList<android.support.v7.widget.RecyclerView.s> additions = new ArrayList();
                additions.addAll(this.k);
                this.a.add(additions);
                this.k.clear();
                Runnable adder = new Runnable(this) {
                    final /* synthetic */ s b;

                    public final void run() {
                        Iterator it = additions.iterator();
                        while (it.hasNext()) {
                            android.support.v7.widget.RecyclerView.s holder = (android.support.v7.widget.RecyclerView.s) it.next();
                            s sVar = this.b;
                            View view = holder.a;
                            ViewPropertyAnimator animate = view.animate();
                            sVar.d.add(holder);
                            animate.alpha(1.0f).setDuration(sVar.f()).setListener(new AnonymousClass5(sVar, holder, view, animate)).start();
                        }
                        additions.clear();
                        this.b.a.remove(additions);
                    }
                };
                if (removalsPending || movesPending || changesPending) {
                    ViewCompat.a(((android.support.v7.widget.RecyclerView.s) additions.get(0)).a, adder, (removalsPending ? g() : 0) + Math.max(movesPending ? e() : 0, changesPending ? h() : 0));
                } else {
                    adder.run();
                }
            }
        }
    }

    public final boolean a(android.support.v7.widget.RecyclerView.s holder) {
        g(holder);
        this.j.add(holder);
        return true;
    }

    public final boolean b(android.support.v7.widget.RecyclerView.s holder) {
        g(holder);
        holder.a.setAlpha(0.0f);
        this.k.add(holder);
        return true;
    }

    public final boolean a(android.support.v7.widget.RecyclerView.s holder, int fromX, int fromY, int toX, int toY) {
        View view = holder.a;
        fromX += (int) holder.a.getTranslationX();
        fromY += (int) holder.a.getTranslationY();
        g(holder);
        int deltaX = toX - fromX;
        int deltaY = toY - fromY;
        if (deltaX == 0 && deltaY == 0) {
            e(holder);
            return false;
        }
        if (deltaX != 0) {
            view.setTranslationX((float) (-deltaX));
        }
        if (deltaY != 0) {
            view.setTranslationY((float) (-deltaY));
        }
        this.l.add(new b(holder, fromX, fromY, toX, toY));
        return true;
    }

    public final boolean a(android.support.v7.widget.RecyclerView.s oldHolder, android.support.v7.widget.RecyclerView.s newHolder, int fromX, int fromY, int toX, int toY) {
        if (oldHolder == newHolder) {
            return a(oldHolder, fromX, fromY, toX, toY);
        }
        float prevTranslationX = oldHolder.a.getTranslationX();
        float prevTranslationY = oldHolder.a.getTranslationY();
        float prevAlpha = oldHolder.a.getAlpha();
        g(oldHolder);
        int deltaX = (int) (((float) (toX - fromX)) - prevTranslationX);
        int deltaY = (int) (((float) (toY - fromY)) - prevTranslationY);
        oldHolder.a.setTranslationX(prevTranslationX);
        oldHolder.a.setTranslationY(prevTranslationY);
        oldHolder.a.setAlpha(prevAlpha);
        if (newHolder != null) {
            g(newHolder);
            newHolder.a.setTranslationX((float) (-deltaX));
            newHolder.a.setTranslationY((float) (-deltaY));
            newHolder.a.setAlpha(0.0f);
        }
        this.m.add(new a(oldHolder, newHolder, fromX, fromY, toX, toY));
        return true;
    }

    private void a(List<a> infoList, android.support.v7.widget.RecyclerView.s item) {
        for (int i = infoList.size() - 1; i >= 0; i--) {
            a changeInfo = (a) infoList.get(i);
            if (a(changeInfo, item) && changeInfo.a == null && changeInfo.b == null) {
                infoList.remove(changeInfo);
            }
        }
    }

    private void a(a changeInfo) {
        if (changeInfo.a != null) {
            a(changeInfo, changeInfo.a);
        }
        if (changeInfo.b != null) {
            a(changeInfo, changeInfo.b);
        }
    }

    private boolean a(a changeInfo, android.support.v7.widget.RecyclerView.s item) {
        if (changeInfo.b == item) {
            changeInfo.b = null;
        } else if (changeInfo.a != item) {
            return false;
        } else {
            changeInfo.a = null;
        }
        item.a.setAlpha(1.0f);
        item.a.setTranslationX(0.0f);
        item.a.setTranslationY(0.0f);
        e(item);
        return true;
    }

    public final void c(android.support.v7.widget.RecyclerView.s item) {
        int i;
        View view = item.a;
        view.animate().cancel();
        for (i = this.l.size() - 1; i >= 0; i--) {
            if (((b) this.l.get(i)).a == item) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                e(item);
                this.l.remove(i);
            }
        }
        a(this.m, item);
        if (this.j.remove(item)) {
            view.setAlpha(1.0f);
            e(item);
        }
        if (this.k.remove(item)) {
            view.setAlpha(1.0f);
            e(item);
        }
        for (i = this.c.size() - 1; i >= 0; i--) {
            List changes = (ArrayList) this.c.get(i);
            a(changes, item);
            if (changes.isEmpty()) {
                this.c.remove(i);
            }
        }
        for (i = this.b.size() - 1; i >= 0; i--) {
            ArrayList<b> moves = (ArrayList) this.b.get(i);
            int j = moves.size() - 1;
            while (j >= 0) {
                if (((b) moves.get(j)).a == item) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    e(item);
                    moves.remove(j);
                    if (moves.isEmpty()) {
                        this.b.remove(i);
                    }
                } else {
                    j--;
                }
            }
        }
        for (i = this.a.size() - 1; i >= 0; i--) {
            ArrayList<android.support.v7.widget.RecyclerView.s> additions = (ArrayList) this.a.get(i);
            if (additions.remove(item)) {
                view.setAlpha(1.0f);
                e(item);
                if (additions.isEmpty()) {
                    this.a.remove(i);
                }
            }
        }
        this.f.remove(item);
        this.d.remove(item);
        this.g.remove(item);
        this.e.remove(item);
        c();
    }

    private void g(android.support.v7.widget.RecyclerView.s holder) {
        if (i == null) {
            i = new ValueAnimator().getInterpolator();
        }
        holder.a.animate().setInterpolator(i);
        c(holder);
    }

    public final boolean b() {
        return (this.k.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.j.isEmpty() && this.e.isEmpty() && this.f.isEmpty() && this.d.isEmpty() && this.g.isEmpty() && this.b.isEmpty() && this.a.isEmpty() && this.c.isEmpty()) ? false : true;
    }

    final void c() {
        if (!b()) {
            i();
        }
    }

    public final void d() {
        int i;
        View view;
        android.support.v7.widget.RecyclerView.s item;
        for (i = this.l.size() - 1; i >= 0; i--) {
            b item2 = (b) this.l.get(i);
            view = item2.a.a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            e(item2.a);
            this.l.remove(i);
        }
        for (i = this.j.size() - 1; i >= 0; i--) {
            e((android.support.v7.widget.RecyclerView.s) this.j.get(i));
            this.j.remove(i);
        }
        for (i = this.k.size() - 1; i >= 0; i--) {
            item = (android.support.v7.widget.RecyclerView.s) this.k.get(i);
            item.a.setAlpha(1.0f);
            e(item);
            this.k.remove(i);
        }
        for (i = this.m.size() - 1; i >= 0; i--) {
            a((a) this.m.get(i));
        }
        this.m.clear();
        if (b()) {
            int j;
            for (i = this.b.size() - 1; i >= 0; i--) {
                ArrayList<b> moves = (ArrayList) this.b.get(i);
                for (j = moves.size() - 1; j >= 0; j--) {
                    b moveInfo = (b) moves.get(j);
                    view = moveInfo.a.a;
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    e(moveInfo.a);
                    moves.remove(j);
                    if (moves.isEmpty()) {
                        this.b.remove(moves);
                    }
                }
            }
            for (i = this.a.size() - 1; i >= 0; i--) {
                ArrayList<android.support.v7.widget.RecyclerView.s> additions = (ArrayList) this.a.get(i);
                for (j = additions.size() - 1; j >= 0; j--) {
                    item = (android.support.v7.widget.RecyclerView.s) additions.get(j);
                    item.a.setAlpha(1.0f);
                    e(item);
                    additions.remove(j);
                    if (additions.isEmpty()) {
                        this.a.remove(additions);
                    }
                }
            }
            for (i = this.c.size() - 1; i >= 0; i--) {
                ArrayList<a> changes = (ArrayList) this.c.get(i);
                for (j = changes.size() - 1; j >= 0; j--) {
                    a((a) changes.get(j));
                    if (changes.isEmpty()) {
                        this.c.remove(changes);
                    }
                }
            }
            a(this.f);
            a(this.e);
            a(this.d);
            a(this.g);
            i();
        }
    }

    private static void a(List<android.support.v7.widget.RecyclerView.s> viewHolders) {
        for (int i = viewHolders.size() - 1; i >= 0; i--) {
            ((android.support.v7.widget.RecyclerView.s) viewHolders.get(i)).a.animate().cancel();
        }
    }

    public final boolean a(@NonNull android.support.v7.widget.RecyclerView.s viewHolder, @NonNull List<Object> payloads) {
        return !payloads.isEmpty() || super.a(viewHolder, payloads);
    }
}
