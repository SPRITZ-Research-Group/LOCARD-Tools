package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.s;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.List;

public class LinearLayoutManager extends g implements android.support.v7.widget.RecyclerView.p.b {
    private c a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private final b g;
    private int h;
    int i;
    ad j;
    boolean k;
    int l;
    int m;
    SavedState n;
    final a o;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        int a;
        int b;
        boolean c;

        SavedState(Parcel in) {
            boolean z = true;
            this.a = in.readInt();
            this.b = in.readInt();
            if (in.readInt() != 1) {
                z = false;
            }
            this.c = z;
        }

        public SavedState(SavedState other) {
            this.a = other.a;
            this.b = other.b;
            this.c = other.c;
        }

        final boolean a() {
            return this.a >= 0;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.a);
            dest.writeInt(this.b);
            dest.writeInt(this.c ? 1 : 0);
        }
    }

    class a {
        int a;
        int b;
        boolean c;
        boolean d;
        final /* synthetic */ LinearLayoutManager e;

        a(LinearLayoutManager this$0) {
            this.e = this$0;
            a();
        }

        final void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
        }

        final void b() {
            int d;
            if (this.c) {
                d = this.e.j.d();
            } else {
                d = this.e.j.c();
            }
            this.b = d;
        }

        public final String toString() {
            return "AnchorInfo{mPosition=" + this.a + ", mCoordinate=" + this.b + ", mLayoutFromEnd=" + this.c + ", mValid=" + this.d + '}';
        }

        public final void a(View child) {
            int spaceChange = this.e.j.b();
            if (spaceChange >= 0) {
                b(child);
                return;
            }
            this.a = g.e(child);
            int startMargin;
            if (this.c) {
                int previousEndMargin = (this.e.j.d() - spaceChange) - this.e.j.b(child);
                this.b = this.e.j.d() - previousEndMargin;
                if (previousEndMargin > 0) {
                    int estimatedChildStart = this.b - this.e.j.e(child);
                    int layoutStart = this.e.j.c();
                    startMargin = estimatedChildStart - (layoutStart + Math.min(this.e.j.a(child) - layoutStart, 0));
                    if (startMargin < 0) {
                        this.b += Math.min(previousEndMargin, -startMargin);
                        return;
                    }
                    return;
                }
                return;
            }
            int childStart = this.e.j.a(child);
            startMargin = childStart - this.e.j.c();
            this.b = childStart;
            if (startMargin > 0) {
                int endMargin = (this.e.j.d() - Math.min(0, (this.e.j.d() - spaceChange) - this.e.j.b(child))) - (childStart + this.e.j.e(child));
                if (endMargin < 0) {
                    this.b -= Math.min(startMargin, -endMargin);
                }
            }
        }

        public final void b(View child) {
            if (this.c) {
                this.b = this.e.j.b(child) + this.e.j.b();
            } else {
                this.b = this.e.j.a(child);
            }
            this.a = g.e(child);
        }
    }

    protected static class b {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;

        protected b() {
        }
    }

    static class c {
        boolean a = true;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h = 0;
        boolean i = false;
        int j;
        List<s> k = null;
        boolean l;

        c() {
        }

        final boolean a(State state) {
            return this.d >= 0 && this.d < state.c();
        }

        final View a(m recycler) {
            View view;
            if (this.k != null) {
                int size = this.k.size();
                int i = 0;
                while (i < size) {
                    view = ((s) this.k.get(i)).a;
                    h hVar = (h) view.getLayoutParams();
                    if (hVar.c.m() || this.d != hVar.c.c()) {
                        i++;
                    } else {
                        a(view);
                        return view;
                    }
                }
                return null;
            }
            view = recycler.c(this.d);
            this.d += this.e;
            return view;
        }

        public final void a(View ignore) {
            View closest;
            int size = this.k.size();
            View view = null;
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < size) {
                int c;
                View view2;
                View view3 = ((s) this.k.get(i2)).a;
                h hVar = (h) view3.getLayoutParams();
                if (!(view3 == ignore || hVar.c.m())) {
                    c = (hVar.c.c() - this.d) * this.e;
                    if (c >= 0 && c < i) {
                        if (c == 0) {
                            closest = view3;
                            break;
                        }
                        view2 = view3;
                        i2++;
                        view = view2;
                        i = c;
                    }
                }
                c = i;
                view2 = view;
                i2++;
                view = view2;
                i = c;
            }
            closest = view;
            if (closest == null) {
                this.d = -1;
            } else {
                this.d = ((h) closest.getLayoutParams()).c.c();
            }
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.n = null;
        this.o = new a(this);
        this.g = new b();
        this.h = 2;
        b(orientation);
        b(reverseLayout);
        this.w = true;
    }

    public LinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.c = false;
        this.k = false;
        this.d = false;
        this.e = true;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.n = null;
        this.o = new a(this);
        this.g = new b();
        this.h = 2;
        android.support.v7.widget.RecyclerView.g.b properties = g.a(context, attrs, defStyleAttr, defStyleRes);
        b(properties.a);
        b(properties.c);
        a(properties.d);
        this.w = true;
    }

    public h b() {
        return new h(-2, -2);
    }

    public final void a(RecyclerView view, m recycler) {
        super.a(view, recycler);
        if (this.f) {
            c(recycler);
            recycler.a();
        }
    }

    public final void a(AccessibilityEvent event) {
        super.a(event);
        if (s() > 0) {
            event.setFromIndex(k());
            event.setToIndex(m());
        }
    }

    public final Parcelable d() {
        if (this.n != null) {
            return new SavedState(this.n);
        }
        Parcelable state = new SavedState();
        if (s() > 0) {
            i();
            boolean didLayoutFromEnd = this.b ^ this.k;
            state.c = didLayoutFromEnd;
            View refChild;
            if (didLayoutFromEnd) {
                refChild = G();
                state.b = this.j.d() - this.j.b(refChild);
                state.a = g.e(refChild);
                return state;
            }
            refChild = F();
            state.a = g.e(refChild);
            state.b = this.j.a(refChild) - this.j.c();
            return state;
        }
        state.a = -1;
        return state;
    }

    public final void a(Parcelable state) {
        if (state instanceof SavedState) {
            this.n = (SavedState) state;
            n();
        }
    }

    public final boolean e() {
        return this.i == 0;
    }

    public final boolean f() {
        return this.i == 1;
    }

    public void a(boolean stackFromEnd) {
        a(null);
        if (this.d != stackFromEnd) {
            this.d = stackFromEnd;
            n();
        }
    }

    public final int g() {
        return this.i;
    }

    public final void b(int orientation) {
        if (orientation == 0 || orientation == 1) {
            a(null);
            if (orientation != this.i) {
                this.i = orientation;
                this.j = null;
                n();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + orientation);
    }

    private void D() {
        boolean z = true;
        if (this.i == 1 || !h()) {
            z = this.c;
        } else if (this.c) {
            z = false;
        }
        this.k = z;
    }

    private void b(boolean reverseLayout) {
        a(null);
        if (reverseLayout != this.c) {
            this.c = reverseLayout;
            n();
        }
    }

    public final View c(int position) {
        int childCount = s();
        if (childCount == 0) {
            return null;
        }
        int viewPosition = position - g.e(g(0));
        if (viewPosition >= 0 && viewPosition < childCount) {
            View child = g(viewPosition);
            if (g.e(child) == position) {
                return child;
            }
        }
        return super.c(position);
    }

    private int h(State state) {
        if (state.b()) {
            return this.j.f();
        }
        return 0;
    }

    public void a(RecyclerView recyclerView, int position) {
        z linearSmoothScroller = new z(recyclerView.getContext());
        linearSmoothScroller.d(position);
        a((p) linearSmoothScroller);
    }

    public final PointF d(int targetPosition) {
        boolean z = false;
        if (s() == 0) {
            return null;
        }
        int direction;
        if (targetPosition < g.e(g(0))) {
            z = true;
        }
        if (z != this.k) {
            direction = -1;
        } else {
            direction = 1;
        }
        if (this.i == 0) {
            return new PointF((float) direction, 0.0f);
        }
        return new PointF(0.0f, (float) direction);
    }

    public void c(m recycler, State state) {
        if (!(this.n == null && this.l == -1) && state.c() == 0) {
            c(recycler);
            return;
        }
        int b;
        int extraForEnd;
        int extraForStart;
        int startOffset;
        int endOffset;
        if (this.n != null && this.n.a()) {
            this.l = this.n.a;
        }
        i();
        this.a.a = false;
        D();
        View focused = B();
        if (!this.o.d || this.l != -1 || this.n != null) {
            Object obj;
            View c;
            this.o.a();
            this.o.c = this.k ^ this.d;
            a aVar = this.o;
            if (state.f || this.l == -1) {
                obj = null;
            } else if (this.l < 0 || this.l >= state.c()) {
                this.l = -1;
                this.m = Integer.MIN_VALUE;
                obj = null;
            } else {
                aVar.a = this.l;
                if (this.n == null || !this.n.a()) {
                    if (this.m == Integer.MIN_VALUE) {
                        c = c(this.l);
                        if (c == null) {
                            if (s() > 0) {
                                aVar.c = (this.l < g.e(g(0))) == this.k;
                            }
                            aVar.b();
                        } else if (this.j.e(c) > this.j.f()) {
                            aVar.b();
                        } else if (this.j.a(c) - this.j.c() < 0) {
                            aVar.b = this.j.c();
                            aVar.c = false;
                        } else if (this.j.d() - this.j.b(c) < 0) {
                            aVar.b = this.j.d();
                            aVar.c = true;
                        } else {
                            if (aVar.c) {
                                b = this.j.b(c) + this.j.b();
                            } else {
                                b = this.j.a(c);
                            }
                            aVar.b = b;
                        }
                        obj = 1;
                    } else {
                        aVar.c = this.k;
                        if (this.k) {
                            aVar.b = this.j.d() - this.m;
                        } else {
                            aVar.b = this.j.c() + this.m;
                        }
                    }
                    obj = 1;
                } else {
                    aVar.c = this.n.c;
                    if (aVar.c) {
                        aVar.b = this.j.d() - this.n.b;
                    } else {
                        aVar.b = this.j.c() + this.n.b;
                    }
                    obj = 1;
                }
            }
            if (obj == null) {
                if (s() != 0) {
                    View B = B();
                    if (B != null) {
                        h hVar = (h) B.getLayoutParams();
                        if (hVar.c.m() || hVar.c.c() < 0 || hVar.c.c() >= state.c()) {
                            obj = null;
                        } else {
                            obj = 1;
                        }
                        if (obj != null) {
                            aVar.a(B);
                            obj = 1;
                            if (obj == null) {
                                aVar.b();
                                if (this.d) {
                                    b = state.c() - 1;
                                } else {
                                    b = 0;
                                }
                                aVar.a = b;
                            }
                        }
                    }
                    if (this.b == this.d) {
                        State state2;
                        if (aVar.c) {
                            if (!this.k) {
                                c = d(recycler, state);
                            }
                            state2 = state;
                            c = a(recycler, state2, 0, s(), state2.c());
                        } else {
                            if (this.k) {
                                c = d(recycler, state);
                            }
                            state2 = state;
                            c = a(recycler, state2, 0, s(), state2.c());
                        }
                        if (c != null) {
                            aVar.b(c);
                            if (!state.f && c()) {
                                obj = (this.j.a(c) >= this.j.d() || this.j.b(c) < this.j.c()) ? 1 : null;
                                if (obj != null) {
                                    if (aVar.c) {
                                        b = this.j.d();
                                    } else {
                                        b = this.j.c();
                                    }
                                    aVar.b = b;
                                }
                            }
                            obj = 1;
                            if (obj == null) {
                                aVar.b();
                                if (this.d) {
                                    b = 0;
                                } else {
                                    b = state.c() - 1;
                                }
                                aVar.a = b;
                            }
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    aVar.b();
                    if (this.d) {
                        b = state.c() - 1;
                    } else {
                        b = 0;
                    }
                    aVar.a = b;
                }
            }
            this.o.d = true;
        } else if (focused != null && (this.j.a(focused) >= this.j.d() || this.j.b(focused) <= this.j.c())) {
            this.o.a(focused);
        }
        int extra = h(state);
        if (this.a.j >= 0) {
            extraForEnd = extra;
            extraForStart = 0;
        } else {
            extraForStart = extra;
            extraForEnd = 0;
        }
        extraForStart += this.j.c();
        extraForEnd += this.j.g();
        if (!(!state.f || this.l == -1 || this.m == Integer.MIN_VALUE)) {
            View existing = c(this.l);
            if (existing != null) {
                int upcomingOffset;
                if (this.k) {
                    upcomingOffset = (this.j.d() - this.j.b(existing)) - this.m;
                } else {
                    upcomingOffset = this.m - (this.j.a(existing) - this.j.c());
                }
                if (upcomingOffset > 0) {
                    extraForStart += upcomingOffset;
                } else {
                    extraForEnd -= upcomingOffset;
                }
            }
        }
        int firstLayoutDirection = this.o.c ? this.k ? 1 : -1 : this.k ? -1 : 1;
        a(recycler, state, this.o, firstLayoutDirection);
        a(recycler);
        this.a.l = E();
        this.a.i = state.f;
        c cVar;
        if (this.o.c) {
            b(this.o);
            this.a.h = extraForStart;
            a(recycler, this.a, state, false);
            startOffset = this.a.b;
            int firstElement = this.a.d;
            if (this.a.c > 0) {
                extraForEnd += this.a.c;
            }
            a(this.o);
            this.a.h = extraForEnd;
            cVar = this.a;
            cVar.d += this.a.e;
            a(recycler, this.a, state, false);
            endOffset = this.a.b;
            if (this.a.c > 0) {
                extraForStart = this.a.c;
                j(firstElement, startOffset);
                this.a.h = extraForStart;
                a(recycler, this.a, state, false);
                startOffset = this.a.b;
            }
        } else {
            a(this.o);
            this.a.h = extraForEnd;
            a(recycler, this.a, state, false);
            endOffset = this.a.b;
            int lastElement = this.a.d;
            if (this.a.c > 0) {
                extraForStart += this.a.c;
            }
            b(this.o);
            this.a.h = extraForStart;
            cVar = this.a;
            cVar.d += this.a.e;
            a(recycler, this.a, state, false);
            startOffset = this.a.b;
            if (this.a.c > 0) {
                extraForEnd = this.a.c;
                i(lastElement, endOffset);
                this.a.h = extraForEnd;
                a(recycler, this.a, state, false);
                endOffset = this.a.b;
            }
        }
        if (s() > 0) {
            int fixOffset;
            if ((this.k ^ this.d) != 0) {
                fixOffset = a(endOffset, recycler, state, true);
                startOffset += fixOffset;
                endOffset += fixOffset;
                fixOffset = b(startOffset, recycler, state, false);
                startOffset += fixOffset;
                endOffset += fixOffset;
            } else {
                fixOffset = b(startOffset, recycler, state, true);
                startOffset += fixOffset;
                endOffset += fixOffset;
                fixOffset = a(endOffset, recycler, state, false);
                startOffset += fixOffset;
                endOffset += fixOffset;
            }
        }
        if (state.j && s() != 0 && !state.f && c()) {
            int i = 0;
            int i2 = 0;
            List c2 = recycler.c();
            int size = c2.size();
            int e = g.e(g(0));
            b = 0;
            while (true) {
                int i3 = b;
                if (i3 >= size) {
                    break;
                }
                int i4;
                s sVar = (s) c2.get(i3);
                if (sVar.m()) {
                    b = i2;
                    i4 = i;
                } else {
                    if (((sVar.c() < e) != this.k ? -1 : 1) == -1) {
                        i4 = this.j.e(sVar.a) + i;
                        b = i2;
                    } else {
                        b = this.j.e(sVar.a) + i2;
                        i4 = i;
                    }
                }
                i2 = i3 + 1;
                i3 = i2;
                i = i4;
            }
            this.a.k = c2;
            if (i > 0) {
                j(g.e(F()), startOffset);
                this.a.h = i;
                this.a.c = 0;
                this.a.a(null);
                a(recycler, this.a, state, false);
            }
            if (i2 > 0) {
                i(g.e(G()), endOffset);
                this.a.h = i2;
                this.a.c = 0;
                this.a.a(null);
                a(recycler, this.a, state, false);
            }
            this.a.k = null;
        }
        if (state.f) {
            this.o.a();
        } else {
            this.j.a();
        }
        this.b = this.d;
    }

    public void a(State state) {
        super.a(state);
        this.n = null;
        this.l = -1;
        this.m = Integer.MIN_VALUE;
        this.o.a();
    }

    void a(m recycler, State state, a anchorInfo, int firstLayoutItemDirection) {
    }

    private int a(int endOffset, m recycler, State state, boolean canOffsetChildren) {
        int gap = this.j.d() - endOffset;
        if (gap <= 0) {
            return 0;
        }
        int fixOffset = -c(-gap, recycler, state);
        endOffset += fixOffset;
        if (!canOffsetChildren) {
            return fixOffset;
        }
        gap = this.j.d() - endOffset;
        if (gap <= 0) {
            return fixOffset;
        }
        this.j.a(gap);
        return fixOffset + gap;
    }

    private int b(int startOffset, m recycler, State state, boolean canOffsetChildren) {
        int gap = startOffset - this.j.c();
        if (gap <= 0) {
            return 0;
        }
        int fixOffset = -c(gap, recycler, state);
        startOffset += fixOffset;
        if (!canOffsetChildren) {
            return fixOffset;
        }
        gap = startOffset - this.j.c();
        if (gap <= 0) {
            return fixOffset;
        }
        this.j.a(-gap);
        return fixOffset - gap;
    }

    private void a(a anchorInfo) {
        i(anchorInfo.a, anchorInfo.b);
    }

    private void i(int itemPosition, int offset) {
        this.a.c = this.j.d() - offset;
        this.a.e = this.k ? -1 : 1;
        this.a.d = itemPosition;
        this.a.f = 1;
        this.a.b = offset;
        this.a.g = Integer.MIN_VALUE;
    }

    private void b(a anchorInfo) {
        j(anchorInfo.a, anchorInfo.b);
    }

    private void j(int itemPosition, int offset) {
        this.a.c = offset - this.j.c();
        this.a.d = itemPosition;
        this.a.e = this.k ? 1 : -1;
        this.a.f = -1;
        this.a.b = offset;
        this.a.g = Integer.MIN_VALUE;
    }

    protected final boolean h() {
        return ViewCompat.f(this.q) == 1;
    }

    final void i() {
        if (this.a == null) {
            this.a = new c();
        }
        if (this.j == null) {
            this.j = ad.a(this, this.i);
        }
    }

    public void e(int position) {
        this.l = position;
        this.m = Integer.MIN_VALUE;
        if (this.n != null) {
            this.n.a = -1;
        }
        n();
    }

    public final void e(int position, int offset) {
        this.l = position;
        this.m = offset;
        if (this.n != null) {
            this.n.a = -1;
        }
        n();
    }

    public int a(int dx, m recycler, State state) {
        if (this.i == 1) {
            return 0;
        }
        return c(dx, recycler, state);
    }

    public int b(int dy, m recycler, State state) {
        if (this.i == 0) {
            return 0;
        }
        return c(dy, recycler, state);
    }

    public final int b(State state) {
        return i(state);
    }

    public final int c(State state) {
        return i(state);
    }

    public final int d(State state) {
        return j(state);
    }

    public final int e(State state) {
        return j(state);
    }

    public final int f(State state) {
        return k(state);
    }

    public final int g(State state) {
        return k(state);
    }

    private int i(State state) {
        boolean z = true;
        if (s() == 0) {
            return 0;
        }
        i();
        ad adVar = this.j;
        View c = c(!this.e);
        if (this.e) {
            z = false;
        }
        return ai.a(state, adVar, c, d(z), this, this.e, this.k);
    }

    private int j(State state) {
        boolean z = true;
        if (s() == 0) {
            return 0;
        }
        i();
        ad adVar = this.j;
        View c = c(!this.e);
        if (this.e) {
            z = false;
        }
        return ai.a(state, adVar, c, d(z), this, this.e);
    }

    private int k(State state) {
        boolean z = true;
        if (s() == 0) {
            return 0;
        }
        i();
        ad adVar = this.j;
        View c = c(!this.e);
        if (this.e) {
            z = false;
        }
        return ai.b(state, adVar, c, d(z), this, this.e);
    }

    private void a(int layoutDirection, int requiredSpace, boolean canUseExistingSpace, State state) {
        int scrollingOffset;
        int i = -1;
        int i2 = 1;
        this.a.l = E();
        this.a.h = h(state);
        this.a.f = layoutDirection;
        c cVar;
        View child;
        if (layoutDirection == 1) {
            cVar = this.a;
            cVar.h += this.j.g();
            child = G();
            cVar = this.a;
            if (!this.k) {
                i = 1;
            }
            cVar.e = i;
            this.a.d = g.e(child) + this.a.e;
            this.a.b = this.j.b(child);
            scrollingOffset = this.j.b(child) - this.j.d();
        } else {
            child = F();
            cVar = this.a;
            cVar.h += this.j.c();
            cVar = this.a;
            if (!this.k) {
                i2 = -1;
            }
            cVar.e = i2;
            this.a.d = g.e(child) + this.a.e;
            this.a.b = this.j.a(child);
            scrollingOffset = (-this.j.a(child)) + this.j.c();
        }
        this.a.c = requiredSpace;
        if (canUseExistingSpace) {
            c cVar2 = this.a;
            cVar2.c -= scrollingOffset;
        }
        this.a.g = scrollingOffset;
    }

    private boolean E() {
        return this.j.h() == 0 && this.j.e() == 0;
    }

    void a(State state, c layoutState, android.support.v7.widget.RecyclerView.g.a layoutPrefetchRegistry) {
        int pos = layoutState.d;
        if (pos >= 0 && pos < state.c()) {
            layoutPrefetchRegistry.a(pos, Math.max(0, layoutState.g));
        }
    }

    public final void a(int adapterItemCount, android.support.v7.widget.RecyclerView.g.a layoutPrefetchRegistry) {
        boolean fromEnd;
        int anchorPos;
        int direction = -1;
        if (this.n == null || !this.n.a()) {
            D();
            fromEnd = this.k;
            anchorPos = this.l == -1 ? fromEnd ? adapterItemCount - 1 : 0 : this.l;
        } else {
            fromEnd = this.n.c;
            anchorPos = this.n.a;
        }
        if (!fromEnd) {
            direction = 1;
        }
        int targetPos = anchorPos;
        for (int i = 0; i < this.h && targetPos >= 0 && targetPos < adapterItemCount; i++) {
            layoutPrefetchRegistry.a(targetPos, 0);
            targetPos += direction;
        }
    }

    public final void a(int dx, int dy, State state, android.support.v7.widget.RecyclerView.g.a layoutPrefetchRegistry) {
        int delta;
        if (this.i == 0) {
            delta = dx;
        } else {
            delta = dy;
        }
        if (s() != 0 && delta != 0) {
            i();
            a(delta > 0 ? 1 : -1, Math.abs(delta), true, state);
            a(state, this.a, layoutPrefetchRegistry);
        }
    }

    private int c(int dy, m recycler, State state) {
        int scrolled = 0;
        if (!(s() == 0 || dy == 0)) {
            this.a.a = true;
            i();
            int layoutDirection = dy > 0 ? 1 : -1;
            int absDy = Math.abs(dy);
            a(layoutDirection, absDy, true, state);
            int consumed = this.a.g + a(recycler, this.a, state, false);
            if (consumed >= 0) {
                if (absDy > consumed) {
                    scrolled = layoutDirection * consumed;
                } else {
                    scrolled = dy;
                }
                this.j.a(-scrolled);
                this.a.j = scrolled;
            }
        }
        return scrolled;
    }

    public final void a(String message) {
        if (this.n == null) {
            super.a(message);
        }
    }

    private void a(m recycler, int startIndex, int endIndex) {
        if (startIndex != endIndex) {
            int i;
            if (endIndex > startIndex) {
                for (i = endIndex - 1; i >= startIndex; i--) {
                    a(i, recycler);
                }
                return;
            }
            for (i = startIndex; i > endIndex; i--) {
                a(i, recycler);
            }
        }
    }

    private void a(m recycler, c layoutState) {
        if (layoutState.a && !layoutState.l) {
            int i;
            int s;
            int e;
            View g;
            View g2;
            if (layoutState.f == -1) {
                i = layoutState.g;
                s = s();
                if (i >= 0) {
                    e = this.j.e() - i;
                    if (this.k) {
                        for (i = 0; i < s; i++) {
                            g = g(i);
                            if (this.j.a(g) < e || this.j.d(g) < e) {
                                a(recycler, 0, i);
                                return;
                            }
                        }
                        return;
                    }
                    for (i = s - 1; i >= 0; i--) {
                        g2 = g(i);
                        if (this.j.a(g2) < e || this.j.d(g2) < e) {
                            a(recycler, s - 1, i);
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            s = layoutState.g;
            if (s >= 0) {
                e = s();
                if (this.k) {
                    for (i = e - 1; i >= 0; i--) {
                        g2 = g(i);
                        if (this.j.b(g2) > s || this.j.c(g2) > s) {
                            a(recycler, e - 1, i);
                            return;
                        }
                    }
                    return;
                }
                for (i = 0; i < e; i++) {
                    g = g(i);
                    if (this.j.b(g) > s || this.j.c(g) > s) {
                        a(recycler, 0, i);
                        return;
                    }
                }
            }
        }
    }

    private int a(m recycler, c layoutState, State state, boolean stopOnFocusable) {
        int start = layoutState.c;
        if (layoutState.g != Integer.MIN_VALUE) {
            if (layoutState.c < 0) {
                layoutState.g += layoutState.c;
            }
            a(recycler, layoutState);
        }
        int remainingSpace = layoutState.c + layoutState.h;
        b layoutChunkResult = this.g;
        while (true) {
            if ((!layoutState.l && remainingSpace <= 0) || !layoutState.a(state)) {
                break;
            }
            layoutChunkResult.a = 0;
            layoutChunkResult.b = false;
            layoutChunkResult.c = false;
            layoutChunkResult.d = false;
            a(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.b) {
                layoutState.b += layoutChunkResult.a * layoutState.f;
                if (!(layoutChunkResult.c && this.a.k == null && state.f)) {
                    layoutState.c -= layoutChunkResult.a;
                    remainingSpace -= layoutChunkResult.a;
                }
                if (layoutState.g != Integer.MIN_VALUE) {
                    layoutState.g += layoutChunkResult.a;
                    if (layoutState.c < 0) {
                        layoutState.g += layoutState.c;
                    }
                    a(recycler, layoutState);
                }
                if (stopOnFocusable && layoutChunkResult.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return start - layoutState.c;
    }

    void a(m recycler, State state, c layoutState, b result) {
        View view = layoutState.a(recycler);
        if (view == null) {
            result.b = true;
            return;
        }
        int right;
        int left;
        int bottom;
        int top;
        h params = (h) view.getLayoutParams();
        if (layoutState.k == null) {
            if (this.k == (layoutState.f == -1)) {
                c(view);
            } else {
                d(view);
            }
        } else {
            if (this.k == (layoutState.f == -1)) {
                a(view);
            } else {
                b(view);
            }
        }
        h hVar = (h) view.getLayoutParams();
        Rect f = this.q.f(view);
        int i = (f.left + f.right) + 0;
        int i2 = (f.bottom + f.top) + 0;
        i = g.a(v(), t(), i + (((x() + z()) + hVar.leftMargin) + hVar.rightMargin), hVar.width, e());
        i2 = g.a(w(), u(), i2 + (((y() + A()) + hVar.topMargin) + hVar.bottomMargin), hVar.height, f());
        if (b(view, i, i2, hVar)) {
            view.measure(i, i2);
        }
        result.a = this.j.e(view);
        if (this.i == 1) {
            if (h()) {
                right = v() - z();
                left = right - this.j.f(view);
            } else {
                left = x();
                right = left + this.j.f(view);
            }
            if (layoutState.f == -1) {
                bottom = layoutState.b;
                top = layoutState.b - result.a;
            } else {
                top = layoutState.b;
                bottom = layoutState.b + result.a;
            }
        } else {
            top = y();
            bottom = top + this.j.f(view);
            if (layoutState.f == -1) {
                right = layoutState.b;
                left = layoutState.b - result.a;
            } else {
                left = layoutState.b;
                right = layoutState.b + result.a;
            }
        }
        g.a(view, left, top, right, bottom);
        if (params.c.m() || params.c.s()) {
            result.c = true;
        }
        result.d = view.hasFocusable();
    }

    final boolean j() {
        if (!(u() == ErrorDialogData.SUPPRESSED || t() == ErrorDialogData.SUPPRESSED)) {
            boolean z;
            int s = s();
            for (int i = 0; i < s; i++) {
                LayoutParams layoutParams = g(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (z) {
                return true;
            }
        }
        return false;
    }

    final int f(int focusDirection) {
        switch (focusDirection) {
            case 1:
                if (this.i == 1 || !h()) {
                    return -1;
                }
                return 1;
            case 2:
                if (this.i == 1) {
                    return 1;
                }
                if (h()) {
                    return -1;
                }
                return 1;
            case 17:
                if (this.i != 0) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 33:
                if (this.i != 1) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 66:
                return this.i == 0 ? 1 : Integer.MIN_VALUE;
            case 130:
                return this.i == 1 ? 1 : Integer.MIN_VALUE;
            default:
                return Integer.MIN_VALUE;
        }
    }

    private View F() {
        return g(this.k ? s() - 1 : 0);
    }

    private View G() {
        return g(this.k ? 0 : s() - 1);
    }

    private View c(boolean completelyVisible) {
        if (this.k) {
            return a(s() - 1, -1, completelyVisible, true);
        }
        return a(0, s(), completelyVisible, true);
    }

    private View d(boolean completelyVisible) {
        if (this.k) {
            return a(0, s(), completelyVisible, true);
        }
        return a(s() - 1, -1, completelyVisible, true);
    }

    private View d(m recycler, State state) {
        return a(recycler, state, s() - 1, -1, state.c());
    }

    View a(m recycler, State state, int start, int end, int itemCount) {
        i();
        View invalidMatch = null;
        View outOfBoundsMatch = null;
        int boundsStart = this.j.c();
        int boundsEnd = this.j.d();
        int diff = end > start ? 1 : -1;
        for (int i = start; i != end; i += diff) {
            View g = g(i);
            int position = g.e(g);
            if (position >= 0 && position < itemCount) {
                if (((h) g.getLayoutParams()).c.m()) {
                    if (invalidMatch == null) {
                        invalidMatch = g;
                    }
                } else if (this.j.a(g) < boundsEnd && this.j.b(g) >= boundsStart) {
                    return g;
                } else {
                    if (outOfBoundsMatch == null) {
                        outOfBoundsMatch = g;
                    }
                }
            }
        }
        return outOfBoundsMatch != null ? outOfBoundsMatch : invalidMatch;
    }

    private View H() {
        return k(0, s());
    }

    private View I() {
        return k(s() - 1, -1);
    }

    public final int k() {
        View child = a(0, s(), false, true);
        return child == null ? -1 : g.e(child);
    }

    public final int l() {
        View child = a(0, s(), true, false);
        return child == null ? -1 : g.e(child);
    }

    public final int m() {
        View child = a(s() - 1, -1, false, true);
        if (child == null) {
            return -1;
        }
        return g.e(child);
    }

    private View a(int fromIndex, int toIndex, boolean completelyVisible, boolean acceptPartiallyVisible) {
        int preferredBoundsFlag;
        i();
        int acceptableBoundsFlag = 0;
        if (completelyVisible) {
            preferredBoundsFlag = 24579;
        } else {
            preferredBoundsFlag = 320;
        }
        if (acceptPartiallyVisible) {
            acceptableBoundsFlag = 320;
        }
        if (this.i == 0) {
            return this.r.a(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
        }
        return this.s.a(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
    }

    private View k(int fromIndex, int toIndex) {
        i();
        Object obj = toIndex > fromIndex ? 1 : toIndex < fromIndex ? -1 : null;
        if (obj == null) {
            return g(fromIndex);
        }
        int preferredBoundsFlag;
        int acceptableBoundsFlag;
        if (this.j.a(g(fromIndex)) < this.j.c()) {
            preferredBoundsFlag = 16644;
            acceptableBoundsFlag = 16388;
        } else {
            preferredBoundsFlag = 4161;
            acceptableBoundsFlag = 4097;
        }
        if (this.i == 0) {
            return this.r.a(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
        }
        return this.s.a(fromIndex, toIndex, preferredBoundsFlag, acceptableBoundsFlag);
    }

    public View a(View focused, int focusDirection, m recycler, State state) {
        D();
        if (s() == 0) {
            return null;
        }
        int layoutDir = f(focusDirection);
        if (layoutDir == Integer.MIN_VALUE) {
            return null;
        }
        View nextCandidate;
        View nextFocus;
        i();
        i();
        a(layoutDir, (int) (0.33333334f * ((float) this.j.f())), false, state);
        this.a.g = Integer.MIN_VALUE;
        this.a.a = false;
        a(recycler, this.a, state, true);
        if (layoutDir == -1) {
            if (this.k) {
                nextCandidate = I();
            } else {
                nextCandidate = H();
            }
        } else if (this.k) {
            nextCandidate = H();
        } else {
            nextCandidate = I();
        }
        if (layoutDir == -1) {
            nextFocus = F();
        } else {
            nextFocus = G();
        }
        if (!nextFocus.hasFocusable()) {
            return nextCandidate;
        }
        if (nextCandidate == null) {
            return null;
        }
        return nextFocus;
    }

    public boolean c() {
        return this.n == null && this.b == this.d;
    }
}
