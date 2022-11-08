package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.b.l;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
    boolean a = false;
    int b = -1;
    int[] c;
    View[] d;
    final SparseIntArray e = new SparseIntArray();
    final SparseIntArray f = new SparseIntArray();
    c g = new a();
    final Rect h = new Rect();

    public static abstract class c {
        final SparseIntArray a = new SparseIntArray();
        private boolean b = false;

        final int b(int position, int spanCount) {
            if (!this.b) {
                return a(position, spanCount);
            }
            int existing = this.a.get(position, -1);
            if (existing != -1) {
                return existing;
            }
            int value = a(position, spanCount);
            this.a.put(position, value);
            return value;
        }

        public int a(int position, int spanCount) {
            if (1 == spanCount) {
                return 0;
            }
            int span = 0;
            int startPos = 0;
            if (this.b && this.a.size() > 0) {
                int prevKey;
                int size = this.a.size() - 1;
                int i = 0;
                while (i <= size) {
                    int i2 = (i + size) >>> 1;
                    if (this.a.keyAt(i2) < position) {
                        i = i2 + 1;
                    } else {
                        size = i2 - 1;
                    }
                }
                size = i - 1;
                if (size < 0 || size >= this.a.size()) {
                    prevKey = -1;
                } else {
                    prevKey = this.a.keyAt(size);
                }
                if (prevKey >= 0) {
                    span = this.a.get(prevKey) + 1;
                    startPos = prevKey + 1;
                }
            }
            for (int i3 = startPos; i3 < position; i3++) {
                span++;
                if (span == spanCount) {
                    span = 0;
                } else if (span > spanCount) {
                    span = 1;
                }
            }
            if (span + 1 > spanCount) {
                return 0;
            }
            return span;
        }

        public static int c(int adapterPosition, int spanCount) {
            int span = 0;
            int group = 0;
            for (int i = 0; i < adapterPosition; i++) {
                span++;
                if (span == spanCount) {
                    span = 0;
                    group++;
                } else if (span > spanCount) {
                    span = 1;
                    group++;
                }
            }
            if (span + 1 > spanCount) {
                return group + 1;
            }
            return group;
        }
    }

    public static final class a extends c {
        public final int a(int position, int spanCount) {
            return position % spanCount;
        }
    }

    public static class b extends h {
        int a = -1;
        int b = 0;

        public b(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public b(int width, int height) {
            super(width, height);
        }

        public b(MarginLayoutParams source) {
            super(source);
        }

        public b(LayoutParams source) {
            super(source);
        }
    }

    public GridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        a(g.a(context, attrs, defStyleAttr, defStyleRes).b);
    }

    public GridLayoutManager(Context context, int spanCount) {
        super(context);
        a(spanCount);
    }

    public GridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        a(spanCount);
    }

    public final void a(boolean stackFromEnd) {
        if (stackFromEnd) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.a(false);
    }

    public final int a(m recycler, State state) {
        if (this.i == 0) {
            return this.b;
        }
        if (state.c() <= 0) {
            return 0;
        }
        return a(recycler, state, state.c() - 1) + 1;
    }

    public final int b(m recycler, State state) {
        if (this.i == 1) {
            return this.b;
        }
        if (state.c() <= 0) {
            return 0;
        }
        return a(recycler, state, state.c() - 1) + 1;
    }

    public final void a(m recycler, State state, View host, android.support.v4.view.accessibility.b info) {
        boolean z = false;
        LayoutParams lp = host.getLayoutParams();
        if (lp instanceof b) {
            b glp = (b) lp;
            int spanGroupIndex = a(recycler, state, glp.c.c());
            int i;
            int i2;
            if (this.i == 0) {
                i = glp.a;
                i2 = glp.b;
                if (this.b > 1 && glp.b == this.b) {
                    z = true;
                }
                info.b(l.a(i, i2, spanGroupIndex, 1, z));
                return;
            }
            i = glp.a;
            i2 = glp.b;
            if (this.b > 1 && glp.b == this.b) {
                z = true;
            }
            info.b(l.a(spanGroupIndex, 1, i, i2, z));
            return;
        }
        super.a(host, info);
    }

    public final void a(State state) {
        super.a(state);
        this.a = false;
    }

    public final void a(int positionStart, int itemCount) {
        this.g.a.clear();
    }

    public final void a() {
        this.g.a.clear();
    }

    public final void b(int positionStart, int itemCount) {
        this.g.a.clear();
    }

    public final void c(int positionStart, int itemCount) {
        this.g.a.clear();
    }

    public final void d(int from, int to) {
        this.g.a.clear();
    }

    public final h b() {
        if (this.i == 0) {
            return new b(-2, -1);
        }
        return new b(-1, -2);
    }

    public final h a(Context c, AttributeSet attrs) {
        return new b(c, attrs);
    }

    public final h a(LayoutParams lp) {
        if (lp instanceof MarginLayoutParams) {
            return new b((MarginLayoutParams) lp);
        }
        return new b(lp);
    }

    public final boolean a(h lp) {
        return lp instanceof b;
    }

    public final void a(Rect childrenBounds, int wSpec, int hSpec) {
        int height;
        int width;
        if (this.c == null) {
            super.a(childrenBounds, wSpec, hSpec);
        }
        int horizontalPadding = x() + z();
        int verticalPadding = y() + A();
        if (this.i == 1) {
            height = g.a(hSpec, childrenBounds.height() + verticalPadding, ViewCompat.o(this.q));
            width = g.a(wSpec, this.c[this.c.length - 1] + horizontalPadding, ViewCompat.n(this.q));
        } else {
            width = g.a(wSpec, childrenBounds.width() + horizontalPadding, ViewCompat.n(this.q));
            height = g.a(hSpec, this.c[this.c.length - 1] + verticalPadding, ViewCompat.o(this.q));
        }
        h(width, height);
    }

    private void l(int totalSpace) {
        int i = 0;
        int[] iArr = this.c;
        int i2 = this.b;
        if (!(iArr != null && iArr.length == i2 + 1 && iArr[iArr.length - 1] == totalSpace)) {
            iArr = new int[(i2 + 1)];
        }
        iArr[0] = 0;
        int i3 = totalSpace / i2;
        int i4 = totalSpace % i2;
        int i5 = 0;
        for (int i6 = 1; i6 <= i2; i6++) {
            int i7;
            i += i4;
            if (i <= 0 || i2 - i >= i4) {
                i7 = i3;
            } else {
                i7 = i3 + 1;
                i -= i2;
            }
            i5 += i7;
            iArr[i6] = i5;
        }
        this.c = iArr;
    }

    private int i(int startSpan, int spanSize) {
        if (this.i == 1 && h()) {
            return this.c[this.b - startSpan] - this.c[(this.b - startSpan) - spanSize];
        }
        return this.c[startSpan + spanSize] - this.c[startSpan];
    }

    final void a(m recycler, State state, a anchorInfo, int itemDirection) {
        Object obj = 1;
        super.a(recycler, state, anchorInfo, itemDirection);
        D();
        if (state.c() > 0 && !state.f) {
            if (itemDirection != 1) {
                obj = null;
            }
            int b = b(recycler, state, anchorInfo.a);
            if (obj != null) {
                while (b > 0 && anchorInfo.a > 0) {
                    anchorInfo.a--;
                    b = b(recycler, state, anchorInfo.a);
                }
            } else {
                int c = state.c() - 1;
                int i = anchorInfo.a;
                int i2 = b;
                while (i < c) {
                    b = b(recycler, state, i + 1);
                    if (b <= i2) {
                        break;
                    }
                    i++;
                    i2 = b;
                }
                anchorInfo.a = i;
            }
        }
        E();
    }

    private void E() {
        if (this.d == null || this.d.length != this.b) {
            this.d = new View[this.b];
        }
    }

    public final int a(int dx, m recycler, State state) {
        D();
        E();
        return super.a(dx, recycler, state);
    }

    public final int b(int dy, m recycler, State state) {
        D();
        E();
        return super.b(dy, recycler, state);
    }

    final View a(m recycler, State state, int start, int end, int itemCount) {
        i();
        View invalidMatch = null;
        View outOfBoundsMatch = null;
        int boundsStart = this.j.c();
        int boundsEnd = this.j.d();
        int diff = end > start ? 1 : -1;
        for (int i = start; i != end; i += diff) {
            View g = g(i);
            int position = g.e(g);
            if (position >= 0 && position < itemCount && b(recycler, state, position) == 0) {
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

    private int b(m recycler, State state, int pos) {
        if (!state.f) {
            return this.g.b(pos, this.b);
        }
        int cached = this.f.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        int adapterPosition = recycler.b(pos);
        if (adapterPosition == -1) {
            return 0;
        }
        return this.g.b(adapterPosition, this.b);
    }

    private int c(m recycler, State state, int pos) {
        if (!state.f) {
            return 1;
        }
        int cached = this.e.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        if (recycler.b(pos) == -1) {
            return 1;
        }
        return 1;
    }

    final void a(State state, c layoutState, android.support.v7.widget.RecyclerView.g.a layoutPrefetchRegistry) {
        int remainingSpan = this.b;
        for (int count = 0; count < this.b && layoutState.a(state) && remainingSpan > 0; count++) {
            layoutPrefetchRegistry.a(layoutState.d, Math.max(0, layoutState.g));
            remainingSpan--;
            layoutState.d += layoutState.e;
        }
    }

    final void a(m recycler, State state, c layoutState, b result) {
        View view;
        int otherDirSpecMode = this.j.i();
        boolean flexibleInOtherDir = otherDirSpecMode != 1073741824;
        int currentOtherDirSize = s() > 0 ? this.c[this.b] : 0;
        if (flexibleInOtherDir) {
            D();
        }
        boolean layingOutInPrimaryDirection = layoutState.e == 1;
        int count = 0;
        int remainingSpan = this.b;
        if (!layingOutInPrimaryDirection) {
            remainingSpan = b(recycler, state, layoutState.d) + c(recycler, state, layoutState.d);
        }
        while (count < this.b && layoutState.a(state) && remainingSpan > 0) {
            int pos = layoutState.d;
            int spanSize = c(recycler, state, pos);
            if (spanSize <= this.b) {
                remainingSpan -= spanSize;
                if (remainingSpan < 0) {
                    break;
                }
                view = layoutState.a(recycler);
                if (view == null) {
                    break;
                }
                this.d[count] = view;
                count++;
            } else {
                throw new IllegalArgumentException("Item at position " + pos + " requires " + spanSize + " spans but GridLayoutManager has only " + this.b + " spans.");
            }
        }
        if (count == 0) {
            result.b = true;
            return;
        }
        int i;
        int size;
        int maxSize = 0;
        float maxSizeInOther = 0.0f;
        a(recycler, state, count, layingOutInPrimaryDirection);
        for (i = 0; i < count; i++) {
            view = this.d[i];
            if (layoutState.k == null) {
                if (layingOutInPrimaryDirection) {
                    c(view);
                } else {
                    d(view);
                }
            } else if (layingOutInPrimaryDirection) {
                a(view);
            } else {
                b(view);
            }
            b(view, this.h);
            a(view, otherDirSpecMode, false);
            size = this.j.e(view);
            if (size > maxSize) {
                maxSize = size;
            }
            float otherSize = (1.0f * ((float) this.j.f(view))) / ((float) ((b) view.getLayoutParams()).b);
            if (otherSize > maxSizeInOther) {
                maxSizeInOther = otherSize;
            }
        }
        if (flexibleInOtherDir) {
            l(Math.max(Math.round(((float) this.b) * maxSizeInOther), currentOtherDirSize));
            maxSize = 0;
            for (i = 0; i < count; i++) {
                view = this.d[i];
                a(view, ErrorDialogData.SUPPRESSED, true);
                size = this.j.e(view);
                if (size > maxSize) {
                    maxSize = size;
                }
            }
        }
        for (i = 0; i < count; i++) {
            view = this.d[i];
            if (this.j.e(view) != maxSize) {
                int wSpec;
                int hSpec;
                b lp = (b) view.getLayoutParams();
                Rect decorInsets = lp.d;
                int verticalInsets = ((decorInsets.top + decorInsets.bottom) + lp.topMargin) + lp.bottomMargin;
                int horizontalInsets = ((decorInsets.left + decorInsets.right) + lp.leftMargin) + lp.rightMargin;
                int totalSpaceInOther = i(lp.a, lp.b);
                if (this.i == 1) {
                    wSpec = g.a(totalSpaceInOther, ErrorDialogData.SUPPRESSED, horizontalInsets, lp.width, false);
                    hSpec = MeasureSpec.makeMeasureSpec(maxSize - verticalInsets, ErrorDialogData.SUPPRESSED);
                } else {
                    wSpec = MeasureSpec.makeMeasureSpec(maxSize - horizontalInsets, ErrorDialogData.SUPPRESSED);
                    hSpec = g.a(totalSpaceInOther, ErrorDialogData.SUPPRESSED, verticalInsets, lp.height, false);
                }
                a(view, wSpec, hSpec, true);
            }
        }
        result.a = maxSize;
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        if (this.i == 1) {
            if (layoutState.f == -1) {
                bottom = layoutState.b;
                top = bottom - maxSize;
            } else {
                top = layoutState.b;
                bottom = top + maxSize;
            }
        } else if (layoutState.f == -1) {
            right = layoutState.b;
            left = right - maxSize;
        } else {
            left = layoutState.b;
            right = left + maxSize;
        }
        for (i = 0; i < count; i++) {
            view = this.d[i];
            h params = (b) view.getLayoutParams();
            if (this.i != 1) {
                top = y() + this.c[params.a];
                bottom = top + this.j.f(view);
            } else if (h()) {
                right = x() + this.c[this.b - params.a];
                left = right - this.j.f(view);
            } else {
                left = x() + this.c[params.a];
                right = left + this.j.f(view);
            }
            g.a(view, left, top, right, bottom);
            if (params.c.m() || params.c.s()) {
                result.c = true;
            }
            result.d |= view.hasFocusable();
        }
        Arrays.fill(this.d, null);
    }

    private void a(View view, int otherDirParentSpecMode, boolean alreadyMeasured) {
        int wSpec;
        int hSpec;
        b lp = (b) view.getLayoutParams();
        Rect decorInsets = lp.d;
        int verticalInsets = ((decorInsets.top + decorInsets.bottom) + lp.topMargin) + lp.bottomMargin;
        int horizontalInsets = ((decorInsets.left + decorInsets.right) + lp.leftMargin) + lp.rightMargin;
        int availableSpaceInOther = i(lp.a, lp.b);
        if (this.i == 1) {
            wSpec = g.a(availableSpaceInOther, otherDirParentSpecMode, horizontalInsets, lp.width, false);
            hSpec = g.a(this.j.f(), u(), verticalInsets, lp.height, true);
        } else {
            hSpec = g.a(availableSpaceInOther, otherDirParentSpecMode, verticalInsets, lp.height, false);
            wSpec = g.a(this.j.f(), t(), horizontalInsets, lp.width, true);
        }
        a(view, wSpec, hSpec, alreadyMeasured);
    }

    private void a(View child, int widthSpec, int heightSpec, boolean alreadyMeasured) {
        boolean measure;
        h lp = (h) child.getLayoutParams();
        if (alreadyMeasured) {
            measure = a(child, widthSpec, heightSpec, lp);
        } else {
            measure = b(child, widthSpec, heightSpec, lp);
        }
        if (measure) {
            child.measure(widthSpec, heightSpec);
        }
    }

    private void a(m recycler, State state, int count, boolean layingOutInPrimaryDirection) {
        int start;
        int end;
        int diff;
        if (layingOutInPrimaryDirection) {
            start = 0;
            end = count;
            diff = 1;
        } else {
            start = count - 1;
            end = -1;
            diff = -1;
        }
        int span = 0;
        for (int i = start; i != end; i += diff) {
            View view = this.d[i];
            b params = (b) view.getLayoutParams();
            params.b = c(recycler, state, g.e(view));
            params.a = span;
            span += params.b;
        }
    }

    public final void a(int spanCount) {
        if (spanCount != this.b) {
            this.a = true;
            if (spanCount <= 0) {
                throw new IllegalArgumentException("Span count should be at least 1. Provided " + spanCount);
            }
            this.b = spanCount;
            this.g.a.clear();
            n();
        }
    }

    public final View a(View focused, int focusDirection, m recycler, State state) {
        View prevFocusedChild = f(focused);
        if (prevFocusedChild == null) {
            return null;
        }
        b lp = (b) prevFocusedChild.getLayoutParams();
        int prevSpanStart = lp.a;
        int prevSpanEnd = lp.a + lp.b;
        if (super.a(focused, focusDirection, recycler, state) == null) {
            return null;
        }
        int start;
        int inc;
        int limit;
        if (((f(focusDirection) == 1) != this.k ? 1 : null) != null) {
            start = s() - 1;
            inc = -1;
            limit = -1;
        } else {
            start = 0;
            inc = 1;
            limit = s();
        }
        boolean preferLastSpan = this.i == 1 && h();
        View focusableWeakCandidate = null;
        int focusableWeakCandidateSpanIndex = -1;
        int focusableWeakCandidateOverlap = 0;
        View unfocusableWeakCandidate = null;
        int unfocusableWeakCandidateSpanIndex = -1;
        int unfocusableWeakCandidateOverlap = 0;
        int focusableSpanGroupIndex = a(recycler, state, start);
        for (int i = start; i != limit; i += inc) {
            int spanGroupIndex = a(recycler, state, i);
            View candidate = g(i);
            if (candidate == prevFocusedChild) {
                break;
            }
            if (!candidate.hasFocusable() || spanGroupIndex == focusableSpanGroupIndex) {
                b candidateLp = (b) candidate.getLayoutParams();
                int candidateStart = candidateLp.a;
                int candidateEnd = candidateLp.a + candidateLp.b;
                if (candidate.hasFocusable() && candidateStart == prevSpanStart && candidateEnd == prevSpanEnd) {
                    return candidate;
                }
                boolean assignAsWeek = false;
                if (!(candidate.hasFocusable() && focusableWeakCandidate == null) && (candidate.hasFocusable() || unfocusableWeakCandidate != null)) {
                    int overlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                    if (candidate.hasFocusable()) {
                        if (overlap > focusableWeakCandidateOverlap) {
                            assignAsWeek = true;
                        } else if (overlap == focusableWeakCandidateOverlap) {
                            if (preferLastSpan == (candidateStart > focusableWeakCandidateSpanIndex)) {
                                assignAsWeek = true;
                            }
                        }
                    } else if (focusableWeakCandidate == null) {
                        Object obj = (this.r.a(candidate) && this.s.a(candidate)) ? 1 : null;
                        if (obj == null) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            if (overlap > unfocusableWeakCandidateOverlap) {
                                assignAsWeek = true;
                            } else if (overlap == unfocusableWeakCandidateOverlap) {
                                if (preferLastSpan == (candidateStart > unfocusableWeakCandidateSpanIndex)) {
                                    assignAsWeek = true;
                                }
                            }
                        }
                    }
                } else {
                    assignAsWeek = true;
                }
                if (assignAsWeek) {
                    if (candidate.hasFocusable()) {
                        focusableWeakCandidate = candidate;
                        focusableWeakCandidateSpanIndex = candidateLp.a;
                        focusableWeakCandidateOverlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                    } else {
                        unfocusableWeakCandidate = candidate;
                        unfocusableWeakCandidateSpanIndex = candidateLp.a;
                        unfocusableWeakCandidateOverlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                    }
                }
            } else if (focusableWeakCandidate != null) {
                break;
            }
        }
        if (focusableWeakCandidate != null) {
            return focusableWeakCandidate;
        }
        return unfocusableWeakCandidate;
    }

    public final boolean c() {
        return this.n == null && !this.a;
    }

    public final void c(m recycler, State state) {
        if (state.f) {
            int s = s();
            for (int i = 0; i < s; i++) {
                b bVar = (b) g(i).getLayoutParams();
                int c = bVar.c.c();
                this.e.put(c, bVar.b);
                this.f.put(c, bVar.a);
            }
        }
        super.c(recycler, state);
        this.e.clear();
        this.f.clear();
    }

    private void D() {
        int totalSpace;
        if (this.i == 1) {
            totalSpace = (v() - z()) - x();
        } else {
            totalSpace = (w() - A()) - y();
        }
        l(totalSpace);
    }

    private int a(m recycler, State state, int viewPosition) {
        if (!state.f) {
            return c.c(viewPosition, this.b);
        }
        int adapterPosition = recycler.b(viewPosition);
        if (adapterPosition == -1) {
            return 0;
        }
        return c.c(adapterPosition, this.b);
    }
}
