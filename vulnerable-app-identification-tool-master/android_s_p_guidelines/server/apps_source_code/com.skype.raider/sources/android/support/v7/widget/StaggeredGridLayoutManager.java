package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.b.l;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.p;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends g implements android.support.v7.widget.RecyclerView.p.b {
    private SavedState A;
    private int B;
    private final Rect C = new Rect();
    private final a D = new a(this);
    private boolean E = false;
    private boolean F = true;
    private int[] G;
    private final Runnable H = new Runnable(this) {
        final /* synthetic */ StaggeredGridLayoutManager a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.g();
        }
    };
    c[] a;
    @NonNull
    ad b;
    @NonNull
    ad c;
    boolean d = false;
    boolean e = false;
    int f = -1;
    int g = Integer.MIN_VALUE;
    LazySpanLookup h = new LazySpanLookup();
    private int i = -1;
    private int j;
    private int k;
    @NonNull
    private final y l;
    private BitSet m;
    private int n = 2;
    private boolean o;
    private boolean z;

    static class LazySpanLookup {
        int[] a;
        List<FullSpanItem> b;

        static class FullSpanItem implements Parcelable {
            public static final Creator<FullSpanItem> CREATOR = new Creator<FullSpanItem>() {
                public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                    return new FullSpanItem[i];
                }

                public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }
            };
            int a;
            int b;
            int[] c;
            boolean d;

            FullSpanItem(Parcel in) {
                boolean z = true;
                this.a = in.readInt();
                this.b = in.readInt();
                if (in.readInt() != 1) {
                    z = false;
                }
                this.d = z;
                int spanCount = in.readInt();
                if (spanCount > 0) {
                    this.c = new int[spanCount];
                    in.readIntArray(this.c);
                }
            }

            FullSpanItem() {
            }

            final int a(int spanIndex) {
                return this.c == null ? 0 : this.c[spanIndex];
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.a);
                dest.writeInt(this.b);
                dest.writeInt(this.d ? 1 : 0);
                if (this.c == null || this.c.length <= 0) {
                    dest.writeInt(0);
                    return;
                }
                dest.writeInt(this.c.length);
                dest.writeIntArray(this.c);
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + '}';
            }
        }

        LazySpanLookup() {
        }

        final int a(int position) {
            if (this.b != null) {
                for (int i = this.b.size() - 1; i >= 0; i--) {
                    if (((FullSpanItem) this.b.get(i)).a >= position) {
                        this.b.remove(i);
                    }
                }
            }
            return b(position);
        }

        final int b(int position) {
            if (this.a == null) {
                return -1;
            }
            if (position >= this.a.length) {
                return -1;
            }
            int endPosition;
            if (this.b != null) {
                FullSpanItem d = d(position);
                if (d != null) {
                    this.b.remove(d);
                }
                int size = this.b.size();
                int i = 0;
                while (i < size) {
                    if (((FullSpanItem) this.b.get(i)).a >= position) {
                        break;
                    }
                    i++;
                }
                i = -1;
                if (i != -1) {
                    d = (FullSpanItem) this.b.get(i);
                    this.b.remove(i);
                    endPosition = d.a;
                    if (endPosition != -1) {
                        Arrays.fill(this.a, position, this.a.length, -1);
                        return this.a.length;
                    }
                    Arrays.fill(this.a, position, endPosition + 1, -1);
                    return endPosition + 1;
                }
            }
            endPosition = -1;
            if (endPosition != -1) {
                Arrays.fill(this.a, position, endPosition + 1, -1);
                return endPosition + 1;
            }
            Arrays.fill(this.a, position, this.a.length, -1);
            return this.a.length;
        }

        final void c(int position) {
            if (this.a == null) {
                this.a = new int[(Math.max(position, 10) + 1)];
                Arrays.fill(this.a, -1);
            } else if (position >= this.a.length) {
                int[] old = this.a;
                int length = this.a.length;
                while (length <= position) {
                    length *= 2;
                }
                this.a = new int[length];
                System.arraycopy(old, 0, this.a, 0, old.length);
                Arrays.fill(this.a, old.length, this.a.length, -1);
            }
        }

        final void a() {
            if (this.a != null) {
                Arrays.fill(this.a, -1);
            }
            this.b = null;
        }

        final void a(int positionStart, int itemCount) {
            if (this.a != null && positionStart < this.a.length) {
                c(positionStart + itemCount);
                System.arraycopy(this.a, positionStart + itemCount, this.a, positionStart, (this.a.length - positionStart) - itemCount);
                Arrays.fill(this.a, this.a.length - itemCount, this.a.length, -1);
                if (this.b != null) {
                    int i = positionStart + itemCount;
                    for (int size = this.b.size() - 1; size >= 0; size--) {
                        FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                        if (fullSpanItem.a >= positionStart) {
                            if (fullSpanItem.a < i) {
                                this.b.remove(size);
                            } else {
                                fullSpanItem.a -= itemCount;
                            }
                        }
                    }
                }
            }
        }

        final void b(int positionStart, int itemCount) {
            if (this.a != null && positionStart < this.a.length) {
                c(positionStart + itemCount);
                System.arraycopy(this.a, positionStart, this.a, positionStart + itemCount, (this.a.length - positionStart) - itemCount);
                Arrays.fill(this.a, positionStart, positionStart + itemCount, -1);
                if (this.b != null) {
                    for (int size = this.b.size() - 1; size >= 0; size--) {
                        FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                        if (fullSpanItem.a >= positionStart) {
                            fullSpanItem.a += itemCount;
                        }
                    }
                }
            }
        }

        public final void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem other = (FullSpanItem) this.b.get(i);
                if (other.a == fullSpanItem.a) {
                    this.b.remove(i);
                }
                if (other.a >= fullSpanItem.a) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        public final FullSpanItem d(int position) {
            if (this.b == null) {
                return null;
            }
            for (int i = this.b.size() - 1; i >= 0; i--) {
                FullSpanItem fsi = (FullSpanItem) this.b.get(i);
                if (fsi.a == position) {
                    return fsi;
                }
            }
            return null;
        }

        public final FullSpanItem a(int minPos, int maxPos, int gapDir) {
            if (this.b == null) {
                return null;
            }
            int limit = this.b.size();
            for (int i = 0; i < limit; i++) {
                FullSpanItem fsi = (FullSpanItem) this.b.get(i);
                if (fsi.a >= maxPos) {
                    return null;
                }
                if (fsi.a >= minPos && (gapDir == 0 || fsi.b == gapDir || fsi.d)) {
                    return fsi;
                }
            }
            return null;
        }
    }

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
        int c;
        int[] d;
        int e;
        int[] f;
        List<FullSpanItem> g;
        boolean h;
        boolean i;
        boolean j;

        SavedState(Parcel in) {
            boolean z;
            boolean z2 = true;
            this.a = in.readInt();
            this.b = in.readInt();
            this.c = in.readInt();
            if (this.c > 0) {
                this.d = new int[this.c];
                in.readIntArray(this.d);
            }
            this.e = in.readInt();
            if (this.e > 0) {
                this.f = new int[this.e];
                in.readIntArray(this.f);
            }
            this.h = in.readInt() == 1;
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.i = z;
            if (in.readInt() != 1) {
                z2 = false;
            }
            this.j = z2;
            this.g = in.readArrayList(FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState other) {
            this.c = other.c;
            this.a = other.a;
            this.b = other.b;
            this.d = other.d;
            this.e = other.e;
            this.f = other.f;
            this.h = other.h;
            this.i = other.i;
            this.j = other.j;
            this.g = other.g;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            int i;
            int i2 = 1;
            dest.writeInt(this.a);
            dest.writeInt(this.b);
            dest.writeInt(this.c);
            if (this.c > 0) {
                dest.writeIntArray(this.d);
            }
            dest.writeInt(this.e);
            if (this.e > 0) {
                dest.writeIntArray(this.f);
            }
            if (this.h) {
                i = 1;
            } else {
                i = 0;
            }
            dest.writeInt(i);
            if (this.i) {
                i = 1;
            } else {
                i = 0;
            }
            dest.writeInt(i);
            if (!this.j) {
                i2 = 0;
            }
            dest.writeInt(i2);
            dest.writeList(this.g);
        }
    }

    class a {
        int a;
        int b;
        boolean c;
        boolean d;
        boolean e;
        int[] f;
        final /* synthetic */ StaggeredGridLayoutManager g;

        a(StaggeredGridLayoutManager this$0) {
            this.g = this$0;
            a();
        }

        final void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
            this.e = false;
            if (this.f != null) {
                Arrays.fill(this.f, -1);
            }
        }
    }

    public static class b extends h {
        c a;
        boolean b;

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

        public final int a() {
            if (this.a == null) {
                return -1;
            }
            return this.a.e;
        }
    }

    class c {
        ArrayList<View> a = new ArrayList();
        int b = Integer.MIN_VALUE;
        int c = Integer.MIN_VALUE;
        int d = 0;
        final int e;
        final /* synthetic */ StaggeredGridLayoutManager f;

        c(StaggeredGridLayoutManager this$0, int index) {
            this.f = this$0;
            this.e = index;
        }

        final int a(int def) {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            if (this.a.size() == 0) {
                return def;
            }
            h();
            return this.b;
        }

        private void h() {
            View startView = (View) this.a.get(0);
            b lp = (b) startView.getLayoutParams();
            this.b = this.f.b.a(startView);
            if (lp.b) {
                FullSpanItem fsi = this.f.h.d(lp.c.c());
                if (fsi != null && fsi.b == -1) {
                    this.b -= fsi.a(this.e);
                }
            }
        }

        final int a() {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            h();
            return this.b;
        }

        final int b(int def) {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            if (this.a.size() == 0) {
                return def;
            }
            i();
            return this.c;
        }

        private void i() {
            View endView = (View) this.a.get(this.a.size() - 1);
            b lp = (b) endView.getLayoutParams();
            this.c = this.f.b.b(endView);
            if (lp.b) {
                FullSpanItem fsi = this.f.h.d(lp.c.c());
                if (fsi != null && fsi.b == 1) {
                    this.c += fsi.a(this.e);
                }
            }
        }

        final int b() {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            i();
            return this.c;
        }

        final void a(View view) {
            b lp = (b) view.getLayoutParams();
            lp.a = this;
            this.a.add(0, view);
            this.b = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.c = Integer.MIN_VALUE;
            }
            if (lp.c.m() || lp.c.s()) {
                this.d += this.f.b.e(view);
            }
        }

        final void b(View view) {
            b lp = (b) view.getLayoutParams();
            lp.a = this;
            this.a.add(view);
            this.c = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (lp.c.m() || lp.c.s()) {
                this.d += this.f.b.e(view);
            }
        }

        final void c() {
            this.a.clear();
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
            this.d = 0;
        }

        final void c(int line) {
            this.b = line;
            this.c = line;
        }

        final void d() {
            int size = this.a.size();
            View end = (View) this.a.remove(size - 1);
            b lp = (b) end.getLayoutParams();
            lp.a = null;
            if (lp.c.m() || lp.c.s()) {
                this.d -= this.f.b.e(end);
            }
            if (size == 1) {
                this.b = Integer.MIN_VALUE;
            }
            this.c = Integer.MIN_VALUE;
        }

        final void e() {
            View start = (View) this.a.remove(0);
            b lp = (b) start.getLayoutParams();
            lp.a = null;
            if (this.a.size() == 0) {
                this.c = Integer.MIN_VALUE;
            }
            if (lp.c.m() || lp.c.s()) {
                this.d -= this.f.b.e(start);
            }
            this.b = Integer.MIN_VALUE;
        }

        final void d(int dt) {
            if (this.b != Integer.MIN_VALUE) {
                this.b += dt;
            }
            if (this.c != Integer.MIN_VALUE) {
                this.c += dt;
            }
        }

        public final int f() {
            if (this.f.d) {
                return b(this.a.size() - 1, -1);
            }
            return b(0, this.a.size());
        }

        public final int g() {
            if (this.f.d) {
                return b(0, this.a.size());
            }
            return b(this.a.size() - 1, -1);
        }

        private int b(int fromIndex, int toIndex) {
            int i;
            int c = this.f.b.c();
            int d = this.f.b.d();
            if (toIndex > fromIndex) {
                i = 1;
            } else {
                i = -1;
            }
            while (fromIndex != toIndex) {
                Object obj;
                View view = (View) this.a.get(fromIndex);
                int a = this.f.b.a(view);
                int b = this.f.b.b(view);
                if (a <= d) {
                    obj = 1;
                } else {
                    obj = null;
                }
                Object obj2;
                if (b >= c) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (obj != null && obj2 != null && (a < c || b > d)) {
                    return g.e(view);
                }
                fromIndex += i;
            }
            return -1;
        }

        public final View a(int referenceChildPosition, int layoutDir) {
            View candidate = null;
            int i;
            View view;
            if (layoutDir != -1) {
                for (i = this.a.size() - 1; i >= 0; i--) {
                    view = (View) this.a.get(i);
                    if ((this.f.d && g.e(view) >= referenceChildPosition) || ((!this.f.d && g.e(view) <= referenceChildPosition) || !view.hasFocusable())) {
                        break;
                    }
                    candidate = view;
                }
            } else {
                int limit = this.a.size();
                for (i = 0; i < limit; i++) {
                    view = (View) this.a.get(i);
                    if ((this.f.d && g.e(view) <= referenceChildPosition) || ((!this.f.d && g.e(view) >= referenceChildPosition) || !view.hasFocusable())) {
                        break;
                    }
                    candidate = view;
                }
            }
            return candidate;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        boolean z = true;
        android.support.v7.widget.RecyclerView.g.b properties = g.a(context, attrs, defStyleAttr, defStyleRes);
        int i = properties.a;
        if (i == 0 || i == 1) {
            a(null);
            if (i != this.j) {
                this.j = i;
                ad adVar = this.b;
                this.b = this.c;
                this.c = adVar;
                n();
            }
            a(properties.b);
            a(properties.c);
            if (this.n == 0) {
                z = false;
            }
            this.w = z;
            this.l = new y();
            h();
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public StaggeredGridLayoutManager(int spanCount, int orientation) {
        boolean z = true;
        this.j = orientation;
        a(spanCount);
        if (this.n == 0) {
            z = false;
        }
        this.w = z;
        this.l = new y();
        h();
    }

    private void h() {
        this.b = ad.a(this, this.j);
        this.c = ad.a(this, 1 - this.j);
    }

    final boolean g() {
        if (s() == 0 || this.n == 0 || !this.v) {
            return false;
        }
        int minPos;
        int maxPos;
        if (this.e) {
            minPos = m();
            maxPos = D();
        } else {
            minPos = D();
            maxPos = m();
        }
        if (minPos == 0 && i() != null) {
            this.h.a();
            this.u = true;
            n();
            return true;
        } else if (!this.E) {
            return false;
        } else {
            int invalidGapDir;
            if (this.e) {
                invalidGapDir = -1;
            } else {
                invalidGapDir = 1;
            }
            FullSpanItem invalidFsi = this.h.a(minPos, maxPos + 1, invalidGapDir);
            if (invalidFsi == null) {
                this.E = false;
                this.h.a(maxPos + 1);
                return false;
            }
            FullSpanItem validFsi = this.h.a(minPos, invalidFsi.a, invalidGapDir * -1);
            if (validFsi == null) {
                this.h.a(invalidFsi.a);
            } else {
                this.h.a(validFsi.a + 1);
            }
            this.u = true;
            n();
            return true;
        }
    }

    public final void j(int state) {
        if (state == 0) {
            g();
        }
    }

    public final void a(RecyclerView view, m recycler) {
        a(this.H);
        for (int i = 0; i < this.i; i++) {
            this.a[i].c();
        }
        view.requestLayout();
    }

    private View i() {
        int firstChildIndex;
        int childLimit;
        int endChildIndex = s() - 1;
        BitSet mSpansToCheck = new BitSet(this.i);
        mSpansToCheck.set(0, this.i, true);
        int preferredSpanDir = (this.j == 1 && l()) ? 1 : -1;
        if (this.e) {
            firstChildIndex = endChildIndex;
            childLimit = -1;
        } else {
            firstChildIndex = 0;
            childLimit = endChildIndex + 1;
        }
        int nextChildDiff = firstChildIndex < childLimit ? 1 : -1;
        int i = firstChildIndex;
        while (i != childLimit) {
            Object obj;
            View child = g(i);
            b lp = (b) child.getLayoutParams();
            if (mSpansToCheck.get(lp.a.e)) {
                c cVar = lp.a;
                if (this.e) {
                    if (cVar.b() < this.b.d()) {
                        obj = !((b) ((View) cVar.a.get(cVar.a.size() + -1)).getLayoutParams()).b ? 1 : null;
                    }
                    obj = null;
                } else {
                    if (cVar.a() > this.b.c()) {
                        obj = !((b) ((View) cVar.a.get(0)).getLayoutParams()).b ? 1 : null;
                    }
                    obj = null;
                }
                if (obj != null) {
                    return child;
                }
                mSpansToCheck.clear(lp.a.e);
            }
            if (!(lp.b || i + nextChildDiff == childLimit)) {
                View nextChild = g(i + nextChildDiff);
                boolean compareSpans = false;
                if (this.e) {
                    int myEnd = this.b.b(child);
                    int nextEnd = this.b.b(nextChild);
                    if (myEnd < nextEnd) {
                        return child;
                    }
                    if (myEnd == nextEnd) {
                        compareSpans = true;
                    }
                } else {
                    int myStart = this.b.a(child);
                    int nextStart = this.b.a(nextChild);
                    if (myStart > nextStart) {
                        return child;
                    }
                    if (myStart == nextStart) {
                        compareSpans = true;
                    }
                }
                if (compareSpans) {
                    Object obj2;
                    if (lp.a.e - ((b) nextChild.getLayoutParams()).a.e < 0) {
                        obj2 = 1;
                    } else {
                        obj2 = null;
                    }
                    if (preferredSpanDir < 0) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj2 != obj) {
                        return child;
                    }
                } else {
                    continue;
                }
            }
            i += nextChildDiff;
        }
        return null;
    }

    private void a(int spanCount) {
        a(null);
        if (spanCount != this.i) {
            this.h.a();
            n();
            this.i = spanCount;
            this.m = new BitSet(this.i);
            this.a = new c[this.i];
            for (int i = 0; i < this.i; i++) {
                this.a[i] = new c(this, i);
            }
            n();
        }
    }

    private void a(boolean reverseLayout) {
        a(null);
        if (!(this.A == null || this.A.h == reverseLayout)) {
            this.A.h = reverseLayout;
        }
        this.d = reverseLayout;
        n();
    }

    public final void a(String message) {
        if (this.A == null) {
            super.a(message);
        }
    }

    private void k() {
        boolean z = true;
        if (this.j == 1 || !l()) {
            z = this.d;
        } else if (this.d) {
            z = false;
        }
        this.e = z;
    }

    private boolean l() {
        return ViewCompat.f(this.q) == 1;
    }

    public final void a(Rect childrenBounds, int wSpec, int hSpec) {
        int height;
        int width;
        int horizontalPadding = x() + z();
        int verticalPadding = y() + A();
        if (this.j == 1) {
            height = g.a(hSpec, childrenBounds.height() + verticalPadding, ViewCompat.o(this.q));
            width = g.a(wSpec, (this.k * this.i) + horizontalPadding, ViewCompat.n(this.q));
        } else {
            width = g.a(wSpec, childrenBounds.width() + horizontalPadding, ViewCompat.n(this.q));
            height = g.a(hSpec, (this.k * this.i) + verticalPadding, ViewCompat.o(this.q));
        }
        h(width, height);
    }

    public final void c(m recycler, State state) {
        Object obj = 1;
        while (true) {
            Object obj2 = obj;
            a aVar = this.D;
            if (!(this.A == null && this.f == -1) && state.c() == 0) {
                c(recycler);
                aVar.a();
                return;
            }
            Object obj3;
            int i;
            int i2;
            int c;
            int s;
            if (aVar.e && this.f == -1 && this.A == null) {
                obj3 = null;
            } else {
                obj3 = 1;
            }
            if (obj3 != null) {
                aVar.a();
                if (this.A != null) {
                    if (this.A.c > 0) {
                        if (this.A.c == this.i) {
                            for (i = 0; i < this.i; i++) {
                                this.a[i].c();
                                i2 = this.A.d[i];
                                if (i2 != Integer.MIN_VALUE) {
                                    if (this.A.i) {
                                        i2 += this.b.d();
                                    } else {
                                        i2 += this.b.c();
                                    }
                                }
                                this.a[i].c(i2);
                            }
                        } else {
                            SavedState savedState = this.A;
                            savedState.d = null;
                            savedState.c = 0;
                            savedState.e = 0;
                            savedState.f = null;
                            savedState.g = null;
                            this.A.a = this.A.b;
                        }
                    }
                    this.z = this.A.j;
                    a(this.A.h);
                    k();
                    if (this.A.a != -1) {
                        this.f = this.A.a;
                        aVar.c = this.A.i;
                    } else {
                        aVar.c = this.e;
                    }
                    if (this.A.e > 1) {
                        this.h.a = this.A.f;
                        this.h.b = this.A.g;
                    }
                } else {
                    k();
                    aVar.c = this.e;
                }
                if (state.f || this.f == -1) {
                    obj = null;
                } else if (this.f < 0 || this.f >= state.c()) {
                    this.f = -1;
                    this.g = Integer.MIN_VALUE;
                    obj = null;
                } else {
                    if (this.A == null || this.A.a == -1 || this.A.c <= 0) {
                        View c2 = c(this.f);
                        if (c2 != null) {
                            if (this.e) {
                                i = m();
                            } else {
                                i = D();
                            }
                            aVar.a = i;
                            if (this.g != Integer.MIN_VALUE) {
                                if (aVar.c) {
                                    aVar.b = (this.b.d() - this.g) - this.b.b(c2);
                                } else {
                                    aVar.b = (this.b.c() + this.g) - this.b.a(c2);
                                }
                                obj = 1;
                            } else if (this.b.e(c2) > this.b.f()) {
                                if (aVar.c) {
                                    i = this.b.d();
                                } else {
                                    i = this.b.c();
                                }
                                aVar.b = i;
                            } else {
                                i = this.b.a(c2) - this.b.c();
                                if (i < 0) {
                                    aVar.b = -i;
                                } else {
                                    i = this.b.d() - this.b.b(c2);
                                    if (i < 0) {
                                        aVar.b = i;
                                    } else {
                                        aVar.b = Integer.MIN_VALUE;
                                    }
                                }
                            }
                        } else {
                            aVar.a = this.f;
                            if (this.g == Integer.MIN_VALUE) {
                                boolean z;
                                if (o(aVar.a) == 1) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                aVar.c = z;
                                if (aVar.c) {
                                    i = aVar.g.b.d();
                                } else {
                                    i = aVar.g.b.c();
                                }
                                aVar.b = i;
                            } else {
                                i = this.g;
                                if (aVar.c) {
                                    aVar.b = aVar.g.b.d() - i;
                                } else {
                                    aVar.b = i + aVar.g.b.c();
                                }
                            }
                            aVar.d = true;
                        }
                    } else {
                        aVar.b = Integer.MIN_VALUE;
                        aVar.a = this.f;
                    }
                    obj = 1;
                }
                if (obj == null) {
                    if (this.o) {
                        c = state.c();
                        for (i2 = s() - 1; i2 >= 0; i2--) {
                            i = g.e(g(i2));
                            if (i >= 0 && i < c) {
                                break;
                            }
                        }
                        i = 0;
                    } else {
                        c = state.c();
                        s = s();
                        for (i2 = 0; i2 < s; i2++) {
                            i = g.e(g(i2));
                            if (i >= 0 && i < c) {
                                break;
                            }
                        }
                        i = 0;
                    }
                    aVar.a = i;
                    aVar.b = Integer.MIN_VALUE;
                }
                aVar.e = true;
            }
            if (this.A == null && this.f == -1 && !(aVar.c == this.o && l() == this.z)) {
                this.h.a();
                aVar.d = true;
            }
            if (s() > 0 && (this.A == null || this.A.c <= 0)) {
                if (aVar.d) {
                    for (i = 0; i < this.i; i++) {
                        this.a[i].c();
                        if (aVar.b != Integer.MIN_VALUE) {
                            this.a[i].c(aVar.b);
                        }
                    }
                } else if (obj3 != null || this.D.f == null) {
                    for (i = 0; i < this.i; i++) {
                        c cVar = this.a[i];
                        boolean z2 = this.e;
                        s = aVar.b;
                        if (z2) {
                            i2 = cVar.b(Integer.MIN_VALUE);
                        } else {
                            i2 = cVar.a(Integer.MIN_VALUE);
                        }
                        cVar.c();
                        if (i2 != Integer.MIN_VALUE && ((!z2 || i2 >= cVar.f.b.d()) && (z2 || i2 <= cVar.f.b.c()))) {
                            if (s != Integer.MIN_VALUE) {
                                i2 += s;
                            }
                            cVar.c = i2;
                            cVar.b = i2;
                        }
                    }
                    a aVar2 = this.D;
                    c[] cVarArr = this.a;
                    c = cVarArr.length;
                    if (aVar2.f == null || aVar2.f.length < c) {
                        aVar2.f = new int[aVar2.g.a.length];
                    }
                    for (i = 0; i < c; i++) {
                        aVar2.f[i] = cVarArr[i].a(Integer.MIN_VALUE);
                    }
                } else {
                    for (i = 0; i < this.i; i++) {
                        c cVar2 = this.a[i];
                        cVar2.c();
                        cVar2.c(this.D.f[i]);
                    }
                }
            }
            a(recycler);
            this.l.a = false;
            this.E = false;
            b(this.c.f());
            a(aVar.a, state);
            if (aVar.c) {
                f(-1);
                a(recycler, this.l, state);
                f(1);
                this.l.c = aVar.a + this.l.d;
                a(recycler, this.l, state);
            } else {
                f(1);
                a(recycler, this.l, state);
                f(-1);
                this.l.c = aVar.a + this.l.d;
                a(recycler, this.l, state);
            }
            if (this.c.h() != ErrorDialogData.SUPPRESSED) {
                float f = 0.0f;
                s = s();
                c = 0;
                while (c < s) {
                    float f2;
                    View g = g(c);
                    float e = (float) this.c.e(g);
                    if (e >= f) {
                        if (((b) g.getLayoutParams()).b) {
                            f2 = (1.0f * e) / ((float) this.i);
                        } else {
                            f2 = e;
                        }
                        f2 = Math.max(f, f2);
                    } else {
                        f2 = f;
                    }
                    c++;
                    f = f2;
                }
                c = this.k;
                i = Math.round(((float) this.i) * f);
                if (this.c.h() == Integer.MIN_VALUE) {
                    i = Math.min(i, this.c.f());
                }
                b(i);
                if (this.k != c) {
                    for (i2 = 0; i2 < s; i2++) {
                        View g2 = g(i2);
                        b bVar = (b) g2.getLayoutParams();
                        if (!bVar.b) {
                            if (l() && this.j == 1) {
                                g2.offsetLeftAndRight(((-((this.i - 1) - bVar.a.e)) * this.k) - ((-((this.i - 1) - bVar.a.e)) * c));
                            } else {
                                int i3 = bVar.a.e * this.k;
                                i = bVar.a.e * c;
                                if (this.j == 1) {
                                    g2.offsetLeftAndRight(i3 - i);
                                } else {
                                    g2.offsetTopAndBottom(i3 - i);
                                }
                            }
                        }
                    }
                }
            }
            if (s() > 0) {
                if (this.e) {
                    a(recycler, state, true);
                    b(recycler, state, false);
                } else {
                    b(recycler, state, true);
                    a(recycler, state, false);
                }
            }
            obj = null;
            if (!(obj2 == null || state.f)) {
                obj2 = (this.n == 0 || s() <= 0 || (!this.E && i() == null)) ? null : 1;
                if (obj2 != null) {
                    a(this.H);
                    if (g()) {
                        obj = 1;
                    }
                }
            }
            if (state.f) {
                this.D.a();
            }
            this.o = aVar.c;
            this.z = l();
            if (obj != null) {
                this.D.a();
                obj = null;
            } else {
                return;
            }
        }
    }

    public final void a(State state) {
        super.a(state);
        this.f = -1;
        this.g = Integer.MIN_VALUE;
        this.A = null;
        this.D.a();
    }

    private void b(int totalSpace) {
        this.k = totalSpace / this.i;
        this.B = MeasureSpec.makeMeasureSpec(totalSpace, this.c.h());
    }

    public final boolean c() {
        return this.A == null;
    }

    public final int b(State state) {
        return h(state);
    }

    private int h(State state) {
        boolean z = true;
        if (s() == 0) {
            return 0;
        }
        ad adVar = this.b;
        View b = b(!this.F);
        if (this.F) {
            z = false;
        }
        return ai.a(state, adVar, b, c(z), this, this.F, this.e);
    }

    public final int c(State state) {
        return h(state);
    }

    public final int d(State state) {
        return i(state);
    }

    private int i(State state) {
        boolean z = true;
        if (s() == 0) {
            return 0;
        }
        ad adVar = this.b;
        View b = b(!this.F);
        if (this.F) {
            z = false;
        }
        return ai.a(state, adVar, b, c(z), this, this.F);
    }

    public final int e(State state) {
        return i(state);
    }

    public final int f(State state) {
        return j(state);
    }

    private int j(State state) {
        boolean z = true;
        if (s() == 0) {
            return 0;
        }
        ad adVar = this.b;
        View b = b(!this.F);
        if (this.F) {
            z = false;
        }
        return ai.b(state, adVar, b, c(z), this, this.F);
    }

    public final int g(State state) {
        return j(state);
    }

    private void a(View child, int widthSpec, int heightSpec) {
        b(child, this.C);
        b lp = (b) child.getLayoutParams();
        widthSpec = b(widthSpec, lp.leftMargin + this.C.left, lp.rightMargin + this.C.right);
        heightSpec = b(heightSpec, lp.topMargin + this.C.top, lp.bottomMargin + this.C.bottom);
        if (b(child, widthSpec, heightSpec, lp)) {
            child.measure(widthSpec, heightSpec);
        }
    }

    private static int b(int spec, int startInset, int endInset) {
        if (startInset == 0 && endInset == 0) {
            return spec;
        }
        int mode = MeasureSpec.getMode(spec);
        if (mode == Integer.MIN_VALUE || mode == ErrorDialogData.SUPPRESSED) {
            return MeasureSpec.makeMeasureSpec(Math.max(0, (MeasureSpec.getSize(spec) - startInset) - endInset), mode);
        }
        return spec;
    }

    public final void a(Parcelable state) {
        if (state instanceof SavedState) {
            this.A = (SavedState) state;
            n();
        }
    }

    public final Parcelable d() {
        if (this.A != null) {
            return new SavedState(this.A);
        }
        Parcelable state = new SavedState();
        state.h = this.d;
        state.i = this.o;
        state.j = this.z;
        if (this.h == null || this.h.a == null) {
            state.e = 0;
        } else {
            state.f = this.h.a;
            state.e = state.f.length;
            state.g = this.h.b;
        }
        if (s() > 0) {
            int m;
            View c;
            if (this.o) {
                m = m();
            } else {
                m = D();
            }
            state.a = m;
            if (this.e) {
                c = c(true);
            } else {
                c = b(true);
            }
            if (c == null) {
                m = -1;
            } else {
                m = g.e(c);
            }
            state.b = m;
            state.c = this.i;
            state.d = new int[this.i];
            for (int i = 0; i < this.i; i++) {
                int line;
                if (this.o) {
                    line = this.a[i].b(Integer.MIN_VALUE);
                    if (line != Integer.MIN_VALUE) {
                        line -= this.b.d();
                    }
                } else {
                    line = this.a[i].a(Integer.MIN_VALUE);
                    if (line != Integer.MIN_VALUE) {
                        line -= this.b.c();
                    }
                }
                state.d[i] = line;
            }
            return state;
        }
        state.a = -1;
        state.b = -1;
        state.c = 0;
        return state;
    }

    public final void a(m recycler, State state, View host, android.support.v4.view.accessibility.b info) {
        int i = 1;
        int i2 = -1;
        LayoutParams lp = host.getLayoutParams();
        if (lp instanceof b) {
            int a;
            int i3;
            b sglp = (b) lp;
            if (this.j == 0) {
                a = sglp.a();
                if (sglp.b) {
                    i = this.i;
                }
                i3 = a;
                a = i;
                i = -1;
            } else {
                a = sglp.a();
                if (sglp.b) {
                    i = this.i;
                    i3 = -1;
                    i2 = a;
                    a = -1;
                } else {
                    i3 = -1;
                    i2 = a;
                    a = -1;
                }
            }
            info.b(l.a(i3, a, i2, i, sglp.b));
            return;
        }
        super.a(host, info);
    }

    public final void a(AccessibilityEvent event) {
        super.a(event);
        if (s() > 0) {
            View start = b(false);
            View end = c(false);
            if (start != null && end != null) {
                int startPos = g.e(start);
                int endPos = g.e(end);
                if (startPos < endPos) {
                    event.setFromIndex(startPos);
                    event.setToIndex(endPos);
                    return;
                }
                event.setFromIndex(endPos);
                event.setToIndex(startPos);
            }
        }
    }

    public final int a(m recycler, State state) {
        if (this.j == 0) {
            return this.i;
        }
        return super.a(recycler, state);
    }

    public final int b(m recycler, State state) {
        if (this.j == 1) {
            return this.i;
        }
        return super.b(recycler, state);
    }

    private View b(boolean fullyVisible) {
        int boundsStart = this.b.c();
        int boundsEnd = this.b.d();
        int limit = s();
        View partiallyVisible = null;
        for (int i = 0; i < limit; i++) {
            View child = g(i);
            int childStart = this.b.a(child);
            if (this.b.b(child) > boundsStart && childStart < boundsEnd) {
                if (childStart >= boundsStart || !fullyVisible) {
                    return child;
                }
                if (partiallyVisible == null) {
                    partiallyVisible = child;
                }
            }
        }
        return partiallyVisible;
    }

    private View c(boolean fullyVisible) {
        int boundsStart = this.b.c();
        int boundsEnd = this.b.d();
        View partiallyVisible = null;
        for (int i = s() - 1; i >= 0; i--) {
            View child = g(i);
            int childStart = this.b.a(child);
            int childEnd = this.b.b(child);
            if (childEnd > boundsStart && childStart < boundsEnd) {
                if (childEnd <= boundsEnd || !fullyVisible) {
                    return child;
                }
                if (partiallyVisible == null) {
                    partiallyVisible = child;
                }
            }
        }
        return partiallyVisible;
    }

    private void a(m recycler, State state, boolean canOffsetChildren) {
        int maxEndLine = m(Integer.MIN_VALUE);
        if (maxEndLine != Integer.MIN_VALUE) {
            int gap = this.b.d() - maxEndLine;
            if (gap > 0) {
                gap -= -c(-gap, recycler, state);
                if (canOffsetChildren && gap > 0) {
                    this.b.a(gap);
                }
            }
        }
    }

    private void b(m recycler, State state, boolean canOffsetChildren) {
        int minStartLine = l(Integer.MAX_VALUE);
        if (minStartLine != Integer.MAX_VALUE) {
            int gap = minStartLine - this.b.c();
            if (gap > 0) {
                gap -= c(gap, recycler, state);
                if (canOffsetChildren && gap > 0) {
                    this.b.a(-gap);
                }
            }
        }
    }

    private void a(int anchorPosition, State state) {
        boolean z = true;
        this.l.b = 0;
        this.l.c = anchorPosition;
        int startExtra = 0;
        int endExtra = 0;
        if (r()) {
            int targetPos = state.a();
            if (targetPos != -1) {
                boolean z2;
                boolean z3 = this.e;
                if (targetPos < anchorPosition) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z3 == z2) {
                    endExtra = this.b.f();
                } else {
                    startExtra = this.b.f();
                }
            }
        }
        if (q()) {
            this.l.f = this.b.c() - startExtra;
            this.l.g = this.b.d() + endExtra;
        } else {
            this.l.g = this.b.e() + endExtra;
            this.l.f = -startExtra;
        }
        this.l.h = false;
        this.l.a = true;
        y yVar = this.l;
        if (!(this.b.h() == 0 && this.b.e() == 0)) {
            z = false;
        }
        yVar.i = z;
    }

    private void f(int direction) {
        int i = 1;
        this.l.e = direction;
        y yVar = this.l;
        if (this.e != (direction == -1)) {
            i = -1;
        }
        yVar.d = i;
    }

    public final void h(int dx) {
        super.h(dx);
        for (int i = 0; i < this.i; i++) {
            this.a[i].d(dx);
        }
    }

    public final void i(int dy) {
        super.i(dy);
        for (int i = 0; i < this.i; i++) {
            this.a[i].d(dy);
        }
    }

    public final void b(int positionStart, int itemCount) {
        c(positionStart, itemCount, 2);
    }

    public final void a(int positionStart, int itemCount) {
        c(positionStart, itemCount, 1);
    }

    public final void a() {
        this.h.a();
        n();
    }

    public final void d(int from, int to) {
        c(from, to, 8);
    }

    public final void c(int positionStart, int itemCount) {
        c(positionStart, itemCount, 4);
    }

    private void c(int positionStart, int itemCountOrToPosition, int cmd) {
        int affectedRangeStart;
        int affectedRangeEnd;
        int minPosition = this.e ? m() : D();
        if (cmd != 8) {
            affectedRangeStart = positionStart;
            affectedRangeEnd = positionStart + itemCountOrToPosition;
        } else if (positionStart < itemCountOrToPosition) {
            affectedRangeEnd = itemCountOrToPosition + 1;
            affectedRangeStart = positionStart;
        } else {
            affectedRangeEnd = positionStart + 1;
            affectedRangeStart = itemCountOrToPosition;
        }
        this.h.b(affectedRangeStart);
        switch (cmd) {
            case 1:
                this.h.b(positionStart, itemCountOrToPosition);
                break;
            case 2:
                this.h.a(positionStart, itemCountOrToPosition);
                break;
            case 8:
                this.h.a(positionStart, 1);
                this.h.b(itemCountOrToPosition, 1);
                break;
        }
        if (affectedRangeEnd > minPosition) {
            if (affectedRangeStart <= (this.e ? D() : m())) {
                n();
            }
        }
    }

    private int a(m recycler, y layoutState, State state) {
        int targetLine;
        int defaultNewViewLine;
        int diff;
        this.m.set(0, this.i, true);
        if (this.l.i) {
            if (layoutState.e == 1) {
                targetLine = Integer.MAX_VALUE;
            } else {
                targetLine = Integer.MIN_VALUE;
            }
        } else if (layoutState.e == 1) {
            targetLine = layoutState.g + layoutState.b;
        } else {
            targetLine = layoutState.f - layoutState.b;
        }
        e(layoutState.e, targetLine);
        if (this.e) {
            defaultNewViewLine = this.b.d();
        } else {
            defaultNewViewLine = this.b.c();
        }
        boolean added = false;
        while (layoutState.a(state) && (this.l.i || !this.m.isEmpty())) {
            int spanIndex;
            c currentSpan;
            int i;
            int i2;
            int start;
            int end;
            FullSpanItem fullSpanItem;
            int otherEnd;
            int otherStart;
            View view = recycler.c(layoutState.c);
            layoutState.c += layoutState.d;
            b lp = (b) view.getLayoutParams();
            int position = lp.c.c();
            LazySpanLookup lazySpanLookup = this.h;
            if (lazySpanLookup.a == null || position >= lazySpanLookup.a.length) {
                spanIndex = -1;
            } else {
                spanIndex = lazySpanLookup.a[position];
            }
            boolean assignSpan = spanIndex == -1;
            if (assignSpan) {
                if (lp.b) {
                    currentSpan = this.a[0];
                } else {
                    int i3;
                    if (n(layoutState.e)) {
                        i3 = this.i - 1;
                        i = -1;
                        i2 = -1;
                    } else {
                        i3 = 0;
                        i = this.i;
                        i2 = 1;
                    }
                    c cVar;
                    int i4;
                    int c;
                    int i5;
                    c cVar2;
                    if (layoutState.e == 1) {
                        cVar = null;
                        i4 = Integer.MAX_VALUE;
                        c = this.b.c();
                        while (true) {
                            i5 = i3;
                            if (i5 == i) {
                                break;
                            }
                            cVar2 = this.a[i5];
                            i3 = cVar2.b(c);
                            if (i3 < i4) {
                                i4 = cVar2;
                            } else {
                                i3 = i4;
                                i4 = cVar;
                            }
                            i5 += i2;
                            cVar = i4;
                        }
                        currentSpan = cVar;
                    } else {
                        cVar = null;
                        i4 = Integer.MIN_VALUE;
                        c = this.b.d();
                        while (true) {
                            i5 = i3;
                            if (i5 == i) {
                                break;
                            }
                            cVar2 = this.a[i5];
                            i3 = cVar2.a(c);
                            if (i3 > i4) {
                                i4 = cVar2;
                            } else {
                                i3 = i4;
                                i4 = cVar;
                            }
                            i5 += i2;
                            cVar = i4;
                        }
                        currentSpan = cVar;
                    }
                }
                lazySpanLookup = this.h;
                lazySpanLookup.c(position);
                lazySpanLookup.a[position] = currentSpan.e;
            } else {
                currentSpan = this.a[spanIndex];
            }
            lp.a = currentSpan;
            if (layoutState.e == 1) {
                c(view);
            } else {
                d(view);
            }
            if (lp.b) {
                if (this.j == 1) {
                    a(view, this.B, g.a(w(), u(), 0, lp.height, true));
                } else {
                    a(view, g.a(v(), t(), 0, lp.width, true), this.B);
                }
            } else if (this.j == 1) {
                a(view, g.a(this.k, t(), 0, lp.width, false), g.a(w(), u(), 0, lp.height, true));
            } else {
                a(view, g.a(v(), t(), 0, lp.width, true), g.a(this.k, u(), 0, lp.height, false));
            }
            if (layoutState.e == 1) {
                if (lp.b) {
                    start = m(defaultNewViewLine);
                } else {
                    start = currentSpan.b(defaultNewViewLine);
                }
                end = start + this.b.e(view);
                if (assignSpan && lp.b) {
                    fullSpanItem = new FullSpanItem();
                    fullSpanItem.c = new int[this.i];
                    for (i2 = 0; i2 < this.i; i2++) {
                        fullSpanItem.c[i2] = start - this.a[i2].b(start);
                    }
                    fullSpanItem.b = -1;
                    fullSpanItem.a = position;
                    this.h.a(fullSpanItem);
                }
            } else {
                if (lp.b) {
                    end = l(defaultNewViewLine);
                } else {
                    end = currentSpan.a(defaultNewViewLine);
                }
                start = end - this.b.e(view);
                if (assignSpan && lp.b) {
                    fullSpanItem = new FullSpanItem();
                    fullSpanItem.c = new int[this.i];
                    for (i2 = 0; i2 < this.i; i2++) {
                        fullSpanItem.c[i2] = this.a[i2].a(end) - end;
                    }
                    fullSpanItem.b = 1;
                    fullSpanItem.a = position;
                    this.h.a(fullSpanItem);
                }
            }
            if (lp.b && layoutState.d == -1) {
                if (!assignSpan) {
                    boolean hasInvalidGap;
                    Object obj;
                    if (layoutState.e == 1) {
                        i = this.a[0].b(Integer.MIN_VALUE);
                        for (i2 = 1; i2 < this.i; i2++) {
                            if (this.a[i2].b(Integer.MIN_VALUE) != i) {
                                obj = null;
                                break;
                            }
                        }
                        obj = 1;
                        hasInvalidGap = obj == null;
                    } else {
                        i = this.a[0].a(Integer.MIN_VALUE);
                        for (i2 = 1; i2 < this.i; i2++) {
                            if (this.a[i2].a(Integer.MIN_VALUE) != i) {
                                obj = null;
                                break;
                            }
                        }
                        obj = 1;
                        hasInvalidGap = obj == null;
                    }
                    if (hasInvalidGap) {
                        fullSpanItem = this.h.d(position);
                        if (fullSpanItem != null) {
                            fullSpanItem.d = true;
                        }
                    }
                }
                this.E = true;
            }
            if (layoutState.e == 1) {
                if (lp.b) {
                    for (i2 = this.i - 1; i2 >= 0; i2--) {
                        this.a[i2].b(view);
                    }
                } else {
                    lp.a.b(view);
                }
            } else if (lp.b) {
                for (i2 = this.i - 1; i2 >= 0; i2--) {
                    this.a[i2].a(view);
                }
            } else {
                lp.a.a(view);
            }
            if (l() && this.j == 1) {
                if (lp.b) {
                    otherEnd = this.c.d();
                } else {
                    otherEnd = this.c.d() - (((this.i - 1) - currentSpan.e) * this.k);
                }
                otherStart = otherEnd - this.c.e(view);
            } else {
                if (lp.b) {
                    otherStart = this.c.c();
                } else {
                    otherStart = (currentSpan.e * this.k) + this.c.c();
                }
                otherEnd = otherStart + this.c.e(view);
            }
            if (this.j == 1) {
                g.a(view, otherStart, start, otherEnd, end);
            } else {
                g.a(view, start, otherStart, end, otherEnd);
            }
            if (lp.b) {
                e(this.l.e, targetLine);
            } else {
                a(currentSpan, this.l.e, targetLine);
            }
            a(recycler, this.l);
            if (this.l.h && view.hasFocusable()) {
                if (lp.b) {
                    this.m.clear();
                } else {
                    this.m.set(currentSpan.e, false);
                }
            }
            added = true;
        }
        if (!added) {
            a(recycler, this.l);
        }
        if (this.l.e == -1) {
            diff = this.b.c() - l(this.b.c());
        } else {
            diff = m(this.b.d()) - this.b.d();
        }
        return diff > 0 ? Math.min(layoutState.b, diff) : 0;
    }

    private void a(m recycler, y layoutState) {
        int i = 1;
        if (layoutState.a && !layoutState.i) {
            int i2;
            int a;
            int a2;
            int scrolled;
            int line;
            if (layoutState.b == 0) {
                if (layoutState.e == -1) {
                    b(recycler, layoutState.g);
                } else {
                    a(recycler, layoutState.f);
                }
            } else if (layoutState.e == -1) {
                i2 = layoutState.f;
                int i3 = layoutState.f;
                a = this.a[0].a(i3);
                while (i < this.i) {
                    a2 = this.a[i].a(i3);
                    if (a2 > a) {
                        a = a2;
                    }
                    i++;
                }
                scrolled = i2 - a;
                if (scrolled < 0) {
                    line = layoutState.g;
                } else {
                    line = layoutState.g - Math.min(scrolled, layoutState.b);
                }
                b(recycler, line);
            } else {
                i2 = layoutState.g;
                a = this.a[0].b(i2);
                while (i < this.i) {
                    a2 = this.a[i].b(i2);
                    if (a2 < a) {
                        a = a2;
                    }
                    i++;
                }
                scrolled = a - layoutState.g;
                if (scrolled < 0) {
                    line = layoutState.f;
                } else {
                    line = layoutState.f + Math.min(scrolled, layoutState.b);
                }
                a(recycler, line);
            }
        }
    }

    private void e(int layoutDir, int targetLine) {
        for (int i = 0; i < this.i; i++) {
            if (!this.a[i].a.isEmpty()) {
                a(this.a[i], layoutDir, targetLine);
            }
        }
    }

    private void a(c span, int layoutDir, int targetLine) {
        int deletedSize = span.d;
        if (layoutDir == -1) {
            if (span.a() + deletedSize <= targetLine) {
                this.m.set(span.e, false);
            }
        } else if (span.b() - deletedSize >= targetLine) {
            this.m.set(span.e, false);
        }
    }

    private int l(int def) {
        int minStart = this.a[0].a(def);
        for (int i = 1; i < this.i; i++) {
            int spanStart = this.a[i].a(def);
            if (spanStart < minStart) {
                minStart = spanStart;
            }
        }
        return minStart;
    }

    private int m(int def) {
        int maxEnd = this.a[0].b(def);
        for (int i = 1; i < this.i; i++) {
            int spanEnd = this.a[i].b(def);
            if (spanEnd > maxEnd) {
                maxEnd = spanEnd;
            }
        }
        return maxEnd;
    }

    private void a(m recycler, int line) {
        while (s() > 0) {
            View child = g(0);
            if (this.b.b(child) <= line && this.b.c(child) <= line) {
                b lp = (b) child.getLayoutParams();
                if (lp.b) {
                    int j = 0;
                    while (j < this.i) {
                        if (this.a[j].a.size() != 1) {
                            j++;
                        } else {
                            return;
                        }
                    }
                    for (j = 0; j < this.i; j++) {
                        this.a[j].e();
                    }
                } else if (lp.a.a.size() != 1) {
                    lp.a.e();
                } else {
                    return;
                }
                a(child, recycler);
            } else {
                return;
            }
        }
    }

    private void b(m recycler, int line) {
        int i = s() - 1;
        while (i >= 0) {
            View child = g(i);
            if (this.b.a(child) >= line && this.b.d(child) >= line) {
                b lp = (b) child.getLayoutParams();
                if (lp.b) {
                    int j = 0;
                    while (j < this.i) {
                        if (this.a[j].a.size() != 1) {
                            j++;
                        } else {
                            return;
                        }
                    }
                    for (j = 0; j < this.i; j++) {
                        this.a[j].d();
                    }
                } else if (lp.a.a.size() != 1) {
                    lp.a.d();
                } else {
                    return;
                }
                a(child, recycler);
                i--;
            } else {
                return;
            }
        }
    }

    private boolean n(int layoutDir) {
        if (this.j == 0) {
            boolean z;
            if (layoutDir == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z != this.e) {
                return true;
            }
            return false;
        }
        if (((layoutDir == -1) == this.e) != l()) {
            return false;
        }
        return true;
    }

    public final boolean f() {
        return this.j == 1;
    }

    public final boolean e() {
        return this.j == 0;
    }

    public final int a(int dx, m recycler, State state) {
        return c(dx, recycler, state);
    }

    public final int b(int dy, m recycler, State state) {
        return c(dy, recycler, state);
    }

    private int o(int position) {
        if (s() != 0) {
            if ((position < D()) != this.e) {
                return -1;
            }
            return 1;
        } else if (this.e) {
            return 1;
        } else {
            return -1;
        }
    }

    public final PointF d(int targetPosition) {
        int direction = o(targetPosition);
        PointF outVector = new PointF();
        if (direction == 0) {
            return null;
        }
        if (this.j == 0) {
            outVector.x = (float) direction;
            outVector.y = 0.0f;
            return outVector;
        }
        outVector.x = 0.0f;
        outVector.y = (float) direction;
        return outVector;
    }

    public final void a(RecyclerView recyclerView, int position) {
        z scroller = new z(recyclerView.getContext());
        scroller.d(position);
        a((p) scroller);
    }

    public final void e(int position) {
        if (!(this.A == null || this.A.a == position)) {
            SavedState savedState = this.A;
            savedState.d = null;
            savedState.c = 0;
            savedState.a = -1;
            savedState.b = -1;
        }
        this.f = position;
        this.g = Integer.MIN_VALUE;
        n();
    }

    public final void a(int dx, int dy, State state, android.support.v7.widget.RecyclerView.g.a layoutPrefetchRegistry) {
        int delta;
        if (this.j == 0) {
            delta = dx;
        } else {
            delta = dy;
        }
        if (s() != 0 && delta != 0) {
            int i;
            b(delta, state);
            if (this.G == null || this.G.length < this.i) {
                this.G = new int[this.i];
            }
            int itemPrefetchCount = 0;
            for (i = 0; i < this.i; i++) {
                int distance;
                if (this.l.d == -1) {
                    distance = this.l.f - this.a[i].a(this.l.f);
                } else {
                    distance = this.a[i].b(this.l.g) - this.l.g;
                }
                if (distance >= 0) {
                    this.G[itemPrefetchCount] = distance;
                    itemPrefetchCount++;
                }
            }
            Arrays.sort(this.G, 0, itemPrefetchCount);
            for (i = 0; i < itemPrefetchCount && this.l.a(state); i++) {
                layoutPrefetchRegistry.a(this.l.c, this.G[i]);
                y yVar = this.l;
                yVar.c += this.l.d;
            }
        }
    }

    private void b(int delta, State state) {
        int layoutDir;
        int referenceChildPosition;
        if (delta > 0) {
            layoutDir = 1;
            referenceChildPosition = m();
        } else {
            layoutDir = -1;
            referenceChildPosition = D();
        }
        this.l.a = true;
        a(referenceChildPosition, state);
        f(layoutDir);
        this.l.c = this.l.d + referenceChildPosition;
        this.l.b = Math.abs(delta);
    }

    private int c(int dt, m recycler, State state) {
        if (s() == 0 || dt == 0) {
            return 0;
        }
        int totalScroll;
        b(dt, state);
        int consumed = a(recycler, this.l, state);
        if (this.l.b < consumed) {
            totalScroll = dt;
        } else if (dt < 0) {
            totalScroll = -consumed;
        } else {
            totalScroll = consumed;
        }
        this.b.a(-totalScroll);
        this.o = this.e;
        this.l.b = 0;
        a(recycler, this.l);
        return totalScroll;
    }

    private int m() {
        int childCount = s();
        return childCount == 0 ? 0 : g.e(g(childCount - 1));
    }

    private int D() {
        if (s() == 0) {
            return 0;
        }
        return g.e(g(0));
    }

    public final h b() {
        if (this.j == 0) {
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

    @Nullable
    public final View a(View focused, int direction, m recycler, State state) {
        if (s() == 0) {
            return null;
        }
        View directChild = f(focused);
        if (directChild == null) {
            return null;
        }
        int layoutDir;
        k();
        switch (direction) {
            case 1:
                if (this.j != 1) {
                    if (!l()) {
                        layoutDir = -1;
                        break;
                    }
                    layoutDir = 1;
                    break;
                }
                layoutDir = -1;
                break;
            case 2:
                if (this.j != 1) {
                    if (!l()) {
                        layoutDir = 1;
                        break;
                    }
                    layoutDir = -1;
                    break;
                }
                layoutDir = 1;
                break;
            case 17:
                if (this.j != 0) {
                    layoutDir = Integer.MIN_VALUE;
                    break;
                }
                layoutDir = -1;
                break;
            case 33:
                if (this.j != 1) {
                    layoutDir = Integer.MIN_VALUE;
                    break;
                }
                layoutDir = -1;
                break;
            case 66:
                if (this.j != 0) {
                    layoutDir = Integer.MIN_VALUE;
                    break;
                }
                layoutDir = 1;
                break;
            case 130:
                if (this.j != 1) {
                    layoutDir = Integer.MIN_VALUE;
                    break;
                }
                layoutDir = 1;
                break;
            default:
                layoutDir = Integer.MIN_VALUE;
                break;
        }
        if (layoutDir == Integer.MIN_VALUE) {
            return null;
        }
        int referenceChildPosition;
        View view;
        int i;
        int f;
        View unfocusableCandidate;
        b prevFocusLayoutParams = (b) directChild.getLayoutParams();
        boolean prevFocusFullSpan = prevFocusLayoutParams.b;
        c prevFocusSpan = prevFocusLayoutParams.a;
        if (layoutDir == 1) {
            referenceChildPosition = m();
        } else {
            referenceChildPosition = D();
        }
        a(referenceChildPosition, state);
        f(layoutDir);
        this.l.c = this.l.d + referenceChildPosition;
        this.l.b = (int) (0.33333334f * ((float) this.b.f()));
        this.l.h = true;
        this.l.a = false;
        a(recycler, this.l, state);
        this.o = this.e;
        if (!prevFocusFullSpan) {
            view = prevFocusSpan.a(referenceChildPosition, layoutDir);
            if (!(view == null || view == directChild)) {
                return view;
            }
        }
        if (n(layoutDir)) {
            for (i = this.i - 1; i >= 0; i--) {
                view = this.a[i].a(referenceChildPosition, layoutDir);
                if (view != null && view != directChild) {
                    return view;
                }
            }
        } else {
            for (i = 0; i < this.i; i++) {
                view = this.a[i].a(referenceChildPosition, layoutDir);
                if (view != null && view != directChild) {
                    return view;
                }
            }
        }
        boolean shouldSearchFromStart = (!this.d ? 1 : null) == (layoutDir == -1 ? 1 : null);
        if (!prevFocusFullSpan) {
            if (shouldSearchFromStart) {
                f = prevFocusSpan.f();
            } else {
                f = prevFocusSpan.g();
            }
            unfocusableCandidate = c(f);
            if (!(unfocusableCandidate == null || unfocusableCandidate == directChild)) {
                return unfocusableCandidate;
            }
        }
        if (n(layoutDir)) {
            for (i = this.i - 1; i >= 0; i--) {
                if (i != prevFocusSpan.e) {
                    if (shouldSearchFromStart) {
                        f = this.a[i].f();
                    } else {
                        f = this.a[i].g();
                    }
                    unfocusableCandidate = c(f);
                    if (!(unfocusableCandidate == null || unfocusableCandidate == directChild)) {
                        return unfocusableCandidate;
                    }
                }
            }
        } else {
            for (i = 0; i < this.i; i++) {
                if (shouldSearchFromStart) {
                    f = this.a[i].f();
                } else {
                    f = this.a[i].g();
                }
                unfocusableCandidate = c(f);
                if (unfocusableCandidate != null && unfocusableCandidate != directChild) {
                    return unfocusableCandidate;
                }
            }
        }
        return null;
    }
}
