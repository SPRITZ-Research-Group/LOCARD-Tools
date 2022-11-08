package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
    static final int[] a = new int[]{16842931};
    private static final h ai = new h();
    private static final Comparator<a> e = new Comparator<a>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return ((a) obj).b - ((a) obj2).b;
        }
    };
    private static final Interpolator f = new Interpolator() {
        public final float getInterpolation(float t) {
            t -= 1.0f;
            return ((((t * t) * t) * t) * t) + 1.0f;
        }
    };
    private int A = 1;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private int F;
    private float G;
    private float H;
    private float I;
    private float J;
    private int K = -1;
    private VelocityTracker L;
    private int M;
    private int N;
    private int O;
    private int P;
    private boolean Q;
    private EdgeEffect R;
    private EdgeEffect S;
    private boolean T = true;
    private boolean U = false;
    private boolean V;
    private int W;
    private List<e> aa;
    private e ab;
    private e ac;
    private List<d> ad;
    private f ae;
    private int af;
    private int ag;
    private ArrayList<View> ah;
    private final Runnable aj = new Runnable(this) {
        final /* synthetic */ ViewPager a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.a(0);
            this.a.d();
        }
    };
    private int ak = 0;
    n b;
    int c;
    private int d;
    private final ArrayList<a> g = new ArrayList();
    private final a h = new a();
    private final Rect i = new Rect();
    private int j = -1;
    private Parcelable k = null;
    private ClassLoader l = null;
    private Scroller m;
    private boolean n;
    private g o;
    private int p;
    private Drawable q;
    private int r;
    private int s;
    private float t = -3.4028235E38f;
    private float u = Float.MAX_VALUE;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    public interface e {
        void a(int i);

        void a(int i, float f);

        void b(int i);
    }

    public interface d {
        void a(@Nullable n nVar, @Nullable n nVar2);
    }

    @Inherited
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DecorView {
    }

    public static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        int b;
        Parcelable c;
        ClassLoader d;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.b);
            out.writeParcelable(this.c, flags);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.b + "}";
        }

        SavedState(Parcel in, ClassLoader loader) {
            super(in, loader);
            if (loader == null) {
                loader = getClass().getClassLoader();
            }
            this.b = in.readInt();
            this.c = in.readParcelable(loader);
            this.d = loader;
        }
    }

    static class a {
        Object a;
        int b;
        boolean c;
        float d;
        float e;

        a() {
        }
    }

    public static class b extends LayoutParams {
        public boolean a;
        public int b;
        float c = 0.0f;
        boolean d;
        int e;
        int f;

        public b() {
            super(-1, -1);
        }

        public b(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, ViewPager.a);
            this.b = a.getInteger(0, 48);
            a.recycle();
        }
    }

    class c extends a {
        final /* synthetic */ ViewPager a;

        c(ViewPager this$0) {
            this.a = this$0;
        }

        public final void a(View host, AccessibilityEvent event) {
            super.a(host, event);
            event.setClassName(ViewPager.class.getName());
            event.setScrollable(a());
            if (event.getEventType() == 4096 && this.a.b != null) {
                event.setItemCount(this.a.b.a());
                event.setFromIndex(this.a.c);
                event.setToIndex(this.a.c);
            }
        }

        public final void a(View host, android.support.v4.view.accessibility.b info) {
            super.a(host, info);
            info.b(ViewPager.class.getName());
            info.i(a());
            if (this.a.canScrollHorizontally(1)) {
                info.a(4096);
            }
            if (this.a.canScrollHorizontally(-1)) {
                info.a(8192);
            }
        }

        public final boolean a(View host, int action, Bundle args) {
            if (super.a(host, action, args)) {
                return true;
            }
            switch (action) {
                case 4096:
                    if (!this.a.canScrollHorizontally(1)) {
                        return false;
                    }
                    this.a.setCurrentItem(this.a.c + 1);
                    return true;
                case 8192:
                    if (!this.a.canScrollHorizontally(-1)) {
                        return false;
                    }
                    this.a.setCurrentItem(this.a.c - 1);
                    return true;
                default:
                    return false;
            }
        }

        private boolean a() {
            return this.a.b != null && this.a.b.a() > 1;
        }
    }

    public interface f {
    }

    private class g extends DataSetObserver {
        final /* synthetic */ ViewPager a;

        g(ViewPager viewPager) {
            this.a = viewPager;
        }

        public final void onChanged() {
            this.a.c();
        }

        public final void onInvalidated() {
            this.a.c();
        }
    }

    static class h implements Comparator<View> {
        h() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            b bVar = (b) ((View) obj).getLayoutParams();
            b bVar2 = (b) ((View) obj2).getLayoutParams();
            if (bVar.a != bVar2.a) {
                return bVar.a ? 1 : -1;
            } else {
                return bVar.e - bVar2.e;
            }
        }
    }

    public ViewPager(Context context) {
        super(context);
        e();
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        e();
    }

    private void e() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.m = new Scroller(context, f);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        float density = context.getResources().getDisplayMetrics().density;
        this.F = configuration.getScaledPagingTouchSlop();
        this.M = (int) (400.0f * density);
        this.N = configuration.getScaledMaximumFlingVelocity();
        this.R = new EdgeEffect(context);
        this.S = new EdgeEffect(context);
        this.O = (int) (25.0f * density);
        this.P = (int) (2.0f * density);
        this.D = (int) (16.0f * density);
        ViewCompat.a((View) this, new c(this));
        if (ViewCompat.e(this) == 0) {
            ViewCompat.a((View) this, 1);
        }
        ViewCompat.a((View) this, new m(this) {
            final /* synthetic */ ViewPager a;
            private final Rect b = new Rect();

            {
                this.a = this$0;
            }

            public final w a(View v, w originalInsets) {
                w applied = ViewCompat.a(v, originalInsets);
                if (applied.e()) {
                    return applied;
                }
                Rect res = this.b;
                res.left = applied.a();
                res.top = applied.b();
                res.right = applied.c();
                res.bottom = applied.d();
                int count = this.a.getChildCount();
                for (int i = 0; i < count; i++) {
                    w childInsets = ViewCompat.b(this.a.getChildAt(i), applied);
                    res.left = Math.min(childInsets.a(), res.left);
                    res.top = Math.min(childInsets.b(), res.top);
                    res.right = Math.min(childInsets.c(), res.right);
                    res.bottom = Math.min(childInsets.d(), res.bottom);
                }
                return applied.a(res.left, res.top, res.right, res.bottom);
            }
        });
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.aj);
        if (!(this.m == null || this.m.isFinished())) {
            this.m.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    final void a(int newState) {
        int i = 0;
        if (this.ak != newState) {
            this.ak = newState;
            if (this.ae != null) {
                int i2;
                if (newState != 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                int childCount = getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    getChildAt(i3).setLayerType(i2 != 0 ? this.af : 0, null);
                }
            }
            if (this.ab != null) {
                this.ab.a(newState);
            }
            if (this.aa != null) {
                int size = this.aa.size();
                while (i < size) {
                    e eVar = (e) this.aa.get(i);
                    if (eVar != null) {
                        eVar.a(newState);
                    }
                    i++;
                }
            }
            if (this.ac != null) {
                this.ac.a(newState);
            }
        }
    }

    public void setAdapter(n adapter) {
        int i;
        if (this.b != null) {
            this.b.c(null);
            for (i = 0; i < this.g.size(); i++) {
                this.b.a((ViewGroup) this, ((a) this.g.get(i)).a);
            }
            this.g.clear();
            int i2 = 0;
            while (i2 < getChildCount()) {
                if (!((b) getChildAt(i2).getLayoutParams()).a) {
                    removeViewAt(i2);
                    i2--;
                }
                i2++;
            }
            this.c = 0;
            scrollTo(0, 0);
        }
        n oldAdapter = this.b;
        this.b = adapter;
        this.d = 0;
        if (this.b != null) {
            if (this.o == null) {
                this.o = new g(this);
            }
            this.b.c(this.o);
            this.z = false;
            boolean wasFirstLayout = this.T;
            this.T = true;
            this.d = this.b.a();
            if (this.j >= 0) {
                a(this.j, false, true);
                this.j = -1;
                this.k = null;
                this.l = null;
            } else if (wasFirstLayout) {
                requestLayout();
            } else {
                d();
            }
        }
        if (this.ad != null && !this.ad.isEmpty()) {
            int count = this.ad.size();
            for (i = 0; i < count; i++) {
                ((d) this.ad.get(i)).a(oldAdapter, adapter);
            }
        }
    }

    public n a() {
        return this.b;
    }

    public final void a(@NonNull d listener) {
        if (this.ad == null) {
            this.ad = new ArrayList();
        }
        this.ad.add(listener);
    }

    public final void b(@NonNull d listener) {
        if (this.ad != null) {
            this.ad.remove(listener);
        }
    }

    private int f() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int item) {
        boolean z;
        this.z = false;
        if (this.T) {
            z = false;
        } else {
            z = true;
        }
        a(item, z, false);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        this.z = false;
        a(item, smoothScroll, false);
    }

    public final int b() {
        return this.c;
    }

    private void a(int item, boolean smoothScroll, boolean always) {
        a(item, smoothScroll, always, 0);
    }

    private void a(int item, boolean smoothScroll, boolean always, int velocity) {
        boolean dispatchSelected = true;
        if (this.b == null || this.b.a() <= 0) {
            b(false);
        } else if (always || this.c != item || this.g.size() == 0) {
            if (item < 0) {
                item = 0;
            } else if (item >= this.b.a()) {
                item = this.b.a() - 1;
            }
            int pageLimit = this.A;
            if (item > this.c + pageLimit || item < this.c - pageLimit) {
                for (int i = 0; i < this.g.size(); i++) {
                    ((a) this.g.get(i)).c = true;
                }
            }
            if (this.c == item) {
                dispatchSelected = false;
            }
            if (this.T) {
                this.c = item;
                if (dispatchSelected) {
                    e(item);
                }
                requestLayout();
                return;
            }
            b(item);
            a(item, smoothScroll, velocity, dispatchSelected);
        } else {
            b(false);
        }
    }

    private void a(int item, boolean smoothScroll, int velocity, boolean dispatchSelected) {
        a curInfo = c(item);
        int destX = 0;
        if (curInfo != null) {
            destX = (int) (((float) f()) * Math.max(this.t, Math.min(curInfo.e, this.u)));
        }
        if (smoothScroll) {
            if (getChildCount() == 0) {
                b(false);
            } else {
                int currX;
                int i;
                Object obj = (this.m == null || this.m.isFinished()) ? null : 1;
                if (obj != null) {
                    currX = this.n ? this.m.getCurrX() : this.m.getStartX();
                    this.m.abortAnimation();
                    b(false);
                    i = currX;
                } else {
                    i = getScrollX();
                }
                int scrollY = getScrollY();
                int i2 = destX - i;
                int i3 = 0 - scrollY;
                if (i2 == 0 && i3 == 0) {
                    a(false);
                    d();
                    a(0);
                } else {
                    b(true);
                    a(2);
                    currX = f();
                    int i4 = currX / 2;
                    float sin = (((float) i4) * ((float) Math.sin((double) ((Math.min(1.0f, (1.0f * ((float) Math.abs(i2))) / ((float) currX)) - 0.5f) * 0.47123894f)))) + ((float) i4);
                    int abs = Math.abs(velocity);
                    if (abs > 0) {
                        currX = Math.round(1000.0f * Math.abs(sin / ((float) abs))) * 4;
                    } else {
                        currX = (int) (((((float) Math.abs(i2)) / ((((float) currX) * 1.0f) + ((float) this.p))) + 1.0f) * 100.0f);
                    }
                    i4 = Math.min(currX, 600);
                    this.n = false;
                    this.m.startScroll(i, scrollY, i2, i3, i4);
                    ViewCompat.d(this);
                }
            }
            if (dispatchSelected) {
                e(item);
                return;
            }
            return;
        }
        if (dispatchSelected) {
            e(item);
        }
        a(false);
        scrollTo(destX, 0);
        d(destX);
    }

    @Deprecated
    public void setOnPageChangeListener(e listener) {
        this.ab = listener;
    }

    public final void a(e listener) {
        if (this.aa == null) {
            this.aa = new ArrayList();
        }
        this.aa.add(listener);
    }

    public void setPageTransformer(boolean reverseDrawingOrder, f transformer) {
        setPageTransformer(reverseDrawingOrder, transformer, 2);
    }

    public void setPageTransformer(boolean reverseDrawingOrder, f transformer, int pageLayerType) {
        boolean hasTransformer;
        boolean z;
        int i = 1;
        if (transformer != null) {
            hasTransformer = true;
        } else {
            hasTransformer = false;
        }
        if (this.ae != null) {
            z = true;
        } else {
            z = false;
        }
        boolean needsPopulate = hasTransformer != z;
        this.ae = transformer;
        setChildrenDrawingOrderEnabled(hasTransformer);
        if (hasTransformer) {
            if (reverseDrawingOrder) {
                i = 2;
            }
            this.ag = i;
            this.af = pageLayerType;
        } else {
            this.ag = 0;
        }
        if (needsPopulate) {
            d();
        }
    }

    protected int getChildDrawingOrder(int childCount, int i) {
        int index;
        if (this.ag == 2) {
            index = (childCount - 1) - i;
        } else {
            index = i;
        }
        return ((b) ((View) this.ah.get(index)).getLayoutParams()).f;
    }

    final e b(e listener) {
        e oldListener = this.ac;
        this.ac = listener;
        return oldListener;
    }

    public void setOffscreenPageLimit(int limit) {
        if (limit <= 0) {
            new StringBuilder("Requested offscreen page limit ").append(limit).append(" too small; defaulting to 1");
            limit = 1;
        }
        if (limit != this.A) {
            this.A = limit;
            d();
        }
    }

    public void setPageMargin(int marginPixels) {
        int oldMargin = this.p;
        this.p = marginPixels;
        int width = getWidth();
        a(width, width, marginPixels, oldMargin);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable d) {
        this.q = d;
        if (d != null) {
            refreshDrawableState();
        }
        setWillNotDraw(d == null);
        invalidate();
    }

    public void setPageMarginDrawable(@DrawableRes int resId) {
        setPageMarginDrawable(android.support.v4.content.a.a(getContext(), resId));
    }

    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.q;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable d = this.q;
        if (d != null && d.isStateful()) {
            d.setState(getDrawableState());
        }
    }

    private a a(int position, int index) {
        a ii = new a();
        ii.b = position;
        ii.a = this.b.a((ViewGroup) this, position);
        ii.d = 1.0f;
        if (index < 0 || index >= this.g.size()) {
            this.g.add(ii);
        } else {
            this.g.add(index, ii);
        }
        return ii;
    }

    final void c() {
        boolean needPopulate;
        int adapterCount = this.b.a();
        this.d = adapterCount;
        if (this.g.size() >= (this.A * 2) + 1 || this.g.size() >= adapterCount) {
            needPopulate = false;
        } else {
            needPopulate = true;
        }
        int newCurrItem = this.c;
        int i = 0;
        while (i < this.g.size()) {
            a ii = (a) this.g.get(i);
            int newPos = this.b.a(ii.a);
            if (newPos != -1) {
                if (newPos == -2) {
                    this.g.remove(i);
                    i--;
                    this.b.a((ViewGroup) this, ii.a);
                    needPopulate = true;
                    if (this.c == ii.b) {
                        newCurrItem = Math.max(0, Math.min(this.c, adapterCount - 1));
                        needPopulate = true;
                    }
                } else if (ii.b != newPos) {
                    if (ii.b == this.c) {
                        newCurrItem = newPos;
                    }
                    ii.b = newPos;
                    needPopulate = true;
                }
            }
            i++;
        }
        Collections.sort(this.g, e);
        if (needPopulate) {
            int childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                b lp = (b) getChildAt(i).getLayoutParams();
                if (!lp.a) {
                    lp.c = 0.0f;
                }
            }
            a(newCurrItem, false, true);
            requestLayout();
        }
    }

    final void d() {
        b(this.c);
    }

    private void b(int newCurrentItem) {
        a oldCurInfo = null;
        if (this.c != newCurrentItem) {
            oldCurInfo = c(this.c);
            this.c = newCurrentItem;
        }
        if (this.b == null) {
            g();
        } else if (this.z) {
            g();
        } else if (getWindowToken() != null) {
            int pageLimit = this.A;
            int startPos = Math.max(0, this.c - pageLimit);
            int N = this.b.a();
            int endPos = Math.min(N - 1, this.c + pageLimit);
            if (N != this.d) {
                String resName;
                try {
                    resName = getResources().getResourceName(getId());
                } catch (NotFoundException e) {
                    resName = Integer.toHexString(getId());
                }
                throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.d + ", found: " + N + " Pager id: " + resName + " Pager class: " + getClass() + " Problematic adapter: " + this.b.getClass());
            }
            a ii;
            float extraWidthLeft;
            int itemIndex;
            int clientWidth;
            float leftWidthNeeded;
            int pos;
            float extraWidthRight;
            float rightWidthNeeded;
            int childCount;
            int i;
            View child;
            b lp;
            View currentFocused;
            a curItem = null;
            int curIndex = 0;
            while (curIndex < this.g.size()) {
                ii = (a) this.g.get(curIndex);
                if (ii.b >= this.c) {
                    if (ii.b == this.c) {
                        curItem = ii;
                    }
                    if (curItem == null && N > 0) {
                        curItem = a(this.c, curIndex);
                    }
                    if (curItem != null) {
                        extraWidthLeft = 0.0f;
                        itemIndex = curIndex - 1;
                        ii = itemIndex < 0 ? (a) this.g.get(itemIndex) : null;
                        clientWidth = f();
                        if (clientWidth > 0) {
                            leftWidthNeeded = 0.0f;
                        } else {
                            leftWidthNeeded = (2.0f - curItem.d) + (((float) getPaddingLeft()) / ((float) clientWidth));
                        }
                        pos = this.c - 1;
                        while (pos >= 0) {
                            if (extraWidthLeft < leftWidthNeeded && pos < startPos) {
                                if (ii == null) {
                                    break;
                                }
                                if (pos == ii.b && !ii.c) {
                                    this.g.remove(itemIndex);
                                    this.b.a((ViewGroup) this, ii.a);
                                    itemIndex--;
                                    curIndex--;
                                    ii = itemIndex >= 0 ? (a) this.g.get(itemIndex) : null;
                                }
                            } else if (ii == null && pos == ii.b) {
                                extraWidthLeft += ii.d;
                                itemIndex--;
                                ii = itemIndex >= 0 ? (a) this.g.get(itemIndex) : null;
                            } else {
                                extraWidthLeft += a(pos, itemIndex + 1).d;
                                curIndex++;
                                ii = itemIndex < 0 ? (a) this.g.get(itemIndex) : null;
                            }
                            pos--;
                        }
                        extraWidthRight = curItem.d;
                        itemIndex = curIndex + 1;
                        if (extraWidthRight < 2.0f) {
                            ii = itemIndex >= this.g.size() ? (a) this.g.get(itemIndex) : null;
                            if (clientWidth > 0) {
                                rightWidthNeeded = 0.0f;
                            } else {
                                rightWidthNeeded = (((float) getPaddingRight()) / ((float) clientWidth)) + 2.0f;
                            }
                            pos = this.c + 1;
                            while (pos < N) {
                                if (extraWidthRight < rightWidthNeeded && pos > endPos) {
                                    if (ii == null) {
                                        break;
                                    }
                                    if (pos == ii.b && !ii.c) {
                                        this.g.remove(itemIndex);
                                        this.b.a((ViewGroup) this, ii.a);
                                        ii = itemIndex < this.g.size() ? (a) this.g.get(itemIndex) : null;
                                    }
                                } else if (ii == null && pos == ii.b) {
                                    extraWidthRight += ii.d;
                                    itemIndex++;
                                    ii = itemIndex < this.g.size() ? (a) this.g.get(itemIndex) : null;
                                } else {
                                    ii = a(pos, itemIndex);
                                    itemIndex++;
                                    extraWidthRight += ii.d;
                                    ii = itemIndex >= this.g.size() ? (a) this.g.get(itemIndex) : null;
                                }
                                pos++;
                            }
                        }
                        a(curItem, curIndex, oldCurInfo);
                    }
                    childCount = getChildCount();
                    for (i = 0; i < childCount; i++) {
                        child = getChildAt(i);
                        lp = (b) child.getLayoutParams();
                        lp.f = i;
                        if (!lp.a && lp.c == 0.0f) {
                            ii = a(child);
                            if (ii != null) {
                                lp.c = ii.d;
                                lp.e = ii.b;
                            }
                        }
                    }
                    g();
                    if (hasFocus()) {
                        currentFocused = findFocus();
                        ii = currentFocused == null ? b(currentFocused) : null;
                        if (ii != null || ii.b != this.c) {
                            while (i < getChildCount()) {
                                child = getChildAt(i);
                                ii = a(child);
                                if (ii != null || ii.b != this.c || !child.requestFocus(2)) {
                                } else {
                                    return;
                                }
                            }
                        }
                        return;
                    }
                }
                curIndex++;
            }
            curItem = a(this.c, curIndex);
            if (curItem != null) {
                extraWidthLeft = 0.0f;
                itemIndex = curIndex - 1;
                if (itemIndex < 0) {
                }
                clientWidth = f();
                if (clientWidth > 0) {
                    leftWidthNeeded = (2.0f - curItem.d) + (((float) getPaddingLeft()) / ((float) clientWidth));
                } else {
                    leftWidthNeeded = 0.0f;
                }
                pos = this.c - 1;
                while (pos >= 0) {
                    if (extraWidthLeft < leftWidthNeeded) {
                    }
                    if (ii == null) {
                    }
                    extraWidthLeft += a(pos, itemIndex + 1).d;
                    curIndex++;
                    if (itemIndex < 0) {
                    }
                    pos--;
                }
                extraWidthRight = curItem.d;
                itemIndex = curIndex + 1;
                if (extraWidthRight < 2.0f) {
                    if (itemIndex >= this.g.size()) {
                    }
                    if (clientWidth > 0) {
                        rightWidthNeeded = (((float) getPaddingRight()) / ((float) clientWidth)) + 2.0f;
                    } else {
                        rightWidthNeeded = 0.0f;
                    }
                    pos = this.c + 1;
                    while (pos < N) {
                        if (extraWidthRight < rightWidthNeeded) {
                        }
                        if (ii == null) {
                        }
                        ii = a(pos, itemIndex);
                        itemIndex++;
                        extraWidthRight += ii.d;
                        if (itemIndex >= this.g.size()) {
                        }
                        pos++;
                    }
                }
                a(curItem, curIndex, oldCurInfo);
            }
            childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                lp = (b) child.getLayoutParams();
                lp.f = i;
                ii = a(child);
                if (ii != null) {
                    lp.c = ii.d;
                    lp.e = ii.b;
                }
            }
            g();
            if (hasFocus()) {
                currentFocused = findFocus();
                if (currentFocused == null) {
                }
                if (ii != null) {
                }
                for (i = 0; i < getChildCount(); i++) {
                    child = getChildAt(i);
                    ii = a(child);
                    if (ii != null) {
                    }
                }
            }
        }
    }

    private void g() {
        if (this.ag != 0) {
            if (this.ah == null) {
                this.ah = new ArrayList();
            } else {
                this.ah.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.ah.add(getChildAt(i));
            }
            Collections.sort(this.ah, ai);
        }
    }

    private void a(a curItem, int curIndex, a oldCurInfo) {
        float offset;
        int pos;
        a ii;
        int N = this.b.a();
        int width = f();
        float marginOffset = width > 0 ? ((float) this.p) / ((float) width) : 0.0f;
        if (oldCurInfo != null) {
            int oldCurPosition = oldCurInfo.b;
            int itemIndex;
            if (oldCurPosition < curItem.b) {
                itemIndex = 0;
                offset = (oldCurInfo.e + oldCurInfo.d) + marginOffset;
                pos = oldCurPosition + 1;
                while (pos <= curItem.b && itemIndex < this.g.size()) {
                    ii = this.g.get(itemIndex);
                    while (true) {
                        ii = ii;
                        if (pos <= ii.b || itemIndex >= this.g.size() - 1) {
                            while (pos < ii.b) {
                                offset += 1.0f + marginOffset;
                                pos++;
                            }
                        } else {
                            itemIndex++;
                            ii = this.g.get(itemIndex);
                        }
                    }
                    while (pos < ii.b) {
                        offset += 1.0f + marginOffset;
                        pos++;
                    }
                    ii.e = offset;
                    offset += ii.d + marginOffset;
                    pos++;
                }
            } else if (oldCurPosition > curItem.b) {
                itemIndex = this.g.size() - 1;
                offset = oldCurInfo.e;
                pos = oldCurPosition - 1;
                while (pos >= curItem.b && itemIndex >= 0) {
                    Object ii2 = this.g.get(itemIndex);
                    while (true) {
                        ii = (a) ii2;
                        if (pos >= ii.b || itemIndex <= 0) {
                            while (pos > ii.b) {
                                offset -= 1.0f + marginOffset;
                                pos--;
                            }
                        } else {
                            itemIndex--;
                            ii2 = this.g.get(itemIndex);
                        }
                    }
                    while (pos > ii.b) {
                        offset -= 1.0f + marginOffset;
                        pos--;
                    }
                    offset -= ii.d + marginOffset;
                    ii.e = offset;
                    pos--;
                }
            }
        }
        int itemCount = this.g.size();
        offset = curItem.e;
        pos = curItem.b - 1;
        this.t = curItem.b == 0 ? curItem.e : -3.4028235E38f;
        this.u = curItem.b == N + -1 ? (curItem.e + curItem.d) - 1.0f : Float.MAX_VALUE;
        int i = curIndex - 1;
        while (i >= 0) {
            ii = (a) this.g.get(i);
            while (pos > ii.b) {
                pos--;
                offset -= 1.0f + marginOffset;
            }
            offset -= ii.d + marginOffset;
            ii.e = offset;
            if (ii.b == 0) {
                this.t = offset;
            }
            i--;
            pos--;
        }
        offset = (curItem.e + curItem.d) + marginOffset;
        pos = curItem.b + 1;
        i = curIndex + 1;
        while (i < itemCount) {
            ii = (a) this.g.get(i);
            while (pos < ii.b) {
                pos++;
                offset += 1.0f + marginOffset;
            }
            if (ii.b == N - 1) {
                this.u = (ii.d + offset) - 1.0f;
            }
            ii.e = offset;
            offset += ii.d + marginOffset;
            i++;
            pos++;
        }
        this.U = false;
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.b = this.c;
        if (this.b != null) {
            ss.c = null;
        }
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.a());
            if (this.b != null) {
                Parcelable parcelable = ss.c;
                ClassLoader classLoader = ss.d;
                a(ss.b, false, true);
                return;
            }
            this.j = ss.b;
            this.k = ss.c;
            this.l = ss.d;
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void addView(View child, int index, LayoutParams params) {
        int i;
        if (!checkLayoutParams(params)) {
            params = generateLayoutParams(params);
        }
        b lp = (b) params;
        boolean z = lp.a;
        if (child.getClass().getAnnotation(DecorView.class) != null) {
            i = 1;
        } else {
            i = 0;
        }
        lp.a = i | z;
        if (!this.x) {
            super.addView(child, index, params);
        } else if (lp == null || !lp.a) {
            lp.d = true;
            addViewInLayout(child, index, params);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.x) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    private a a(View child) {
        for (int i = 0; i < this.g.size(); i++) {
            a ii = (a) this.g.get(i);
            if (this.b.a(child, ii.a)) {
                return ii;
            }
        }
        return null;
    }

    private a b(View child) {
        while (true) {
            View parent = child.getParent();
            if (parent == this) {
                return a(child);
            }
            if (parent != null && (parent instanceof View)) {
                child = parent;
            }
        }
        return null;
    }

    private a c(int position) {
        for (int i = 0; i < this.g.size(); i++) {
            a ii = (a) this.g.get(i);
            if (ii.b == position) {
                return ii;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.T = true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        View child;
        b lp;
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int measuredWidth = getMeasuredWidth();
        this.E = Math.min(measuredWidth / 10, this.D);
        int childWidthSize = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int childHeightSize = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int size = getChildCount();
        for (i = 0; i < size; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (b) child.getLayoutParams();
                if (lp != null && lp.a) {
                    int hgrav = lp.b & 7;
                    int vgrav = lp.b & 112;
                    int widthMode = Integer.MIN_VALUE;
                    int heightMode = Integer.MIN_VALUE;
                    boolean consumeVertical = vgrav == 48 || vgrav == 80;
                    boolean consumeHorizontal = hgrav == 3 || hgrav == 5;
                    if (consumeVertical) {
                        widthMode = ErrorDialogData.SUPPRESSED;
                    } else if (consumeHorizontal) {
                        heightMode = ErrorDialogData.SUPPRESSED;
                    }
                    int widthSize = childWidthSize;
                    int heightSize = childHeightSize;
                    if (lp.width != -2) {
                        widthMode = ErrorDialogData.SUPPRESSED;
                        if (lp.width != -1) {
                            widthSize = lp.width;
                        }
                    }
                    if (lp.height != -2) {
                        heightMode = ErrorDialogData.SUPPRESSED;
                        if (lp.height != -1) {
                            heightSize = lp.height;
                        }
                    }
                    child.measure(MeasureSpec.makeMeasureSpec(widthSize, widthMode), MeasureSpec.makeMeasureSpec(heightSize, heightMode));
                    if (consumeVertical) {
                        childHeightSize -= child.getMeasuredHeight();
                    } else if (consumeHorizontal) {
                        childWidthSize -= child.getMeasuredWidth();
                    }
                }
            }
        }
        this.v = MeasureSpec.makeMeasureSpec(childWidthSize, ErrorDialogData.SUPPRESSED);
        this.w = MeasureSpec.makeMeasureSpec(childHeightSize, ErrorDialogData.SUPPRESSED);
        this.x = true;
        d();
        this.x = false;
        size = getChildCount();
        for (i = 0; i < size; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (b) child.getLayoutParams();
                if (lp == null || !lp.a) {
                    child.measure(MeasureSpec.makeMeasureSpec((int) (((float) childWidthSize) * lp.c), ErrorDialogData.SUPPRESSED), this.w);
                }
            }
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            a(w, oldw, this.p, this.p);
        }
    }

    private void a(int width, int oldWidth, int margin, int oldMargin) {
        if (oldWidth <= 0 || this.g.isEmpty()) {
            a ii = c(this.c);
            int scrollPos = (int) ((ii != null ? Math.min(ii.e, this.u) : 0.0f) * ((float) ((width - getPaddingLeft()) - getPaddingRight())));
            if (scrollPos != getScrollX()) {
                a(false);
                scrollTo(scrollPos, getScrollY());
            }
        } else if (this.m.isFinished()) {
            scrollTo((int) ((((float) getScrollX()) / ((float) (((oldWidth - getPaddingLeft()) - getPaddingRight()) + oldMargin))) * ((float) (((width - getPaddingLeft()) - getPaddingRight()) + margin))), getScrollY());
        } else {
            this.m.setFinalX(this.c * f());
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        View child;
        b lp;
        int childLeft;
        int childTop;
        int count = getChildCount();
        int width = r - l;
        int height = b - t;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int decorCount = 0;
        for (i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (b) child.getLayoutParams();
                if (lp.a) {
                    int vgrav = lp.b & 112;
                    switch (lp.b & 7) {
                        case 1:
                            childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 3:
                            childLeft = paddingLeft;
                            paddingLeft += child.getMeasuredWidth();
                            break;
                        case 5:
                            childLeft = (width - paddingRight) - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            break;
                        default:
                            childLeft = paddingLeft;
                            break;
                    }
                    switch (vgrav) {
                        case 16:
                            childTop = Math.max((height - child.getMeasuredHeight()) / 2, paddingTop);
                            break;
                        case 48:
                            childTop = paddingTop;
                            paddingTop += child.getMeasuredHeight();
                            break;
                        case 80:
                            childTop = (height - paddingBottom) - child.getMeasuredHeight();
                            paddingBottom += child.getMeasuredHeight();
                            break;
                        default:
                            childTop = paddingTop;
                            break;
                    }
                    childLeft += scrollX;
                    child.layout(childLeft, childTop, child.getMeasuredWidth() + childLeft, child.getMeasuredHeight() + childTop);
                    decorCount++;
                }
            }
        }
        int childWidth = (width - paddingLeft) - paddingRight;
        for (i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (b) child.getLayoutParams();
                if (!lp.a) {
                    a ii = a(child);
                    if (ii != null) {
                        childLeft = paddingLeft + ((int) (((float) childWidth) * ii.e));
                        childTop = paddingTop;
                        if (lp.d) {
                            lp.d = false;
                            child.measure(MeasureSpec.makeMeasureSpec((int) (((float) childWidth) * lp.c), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec((height - paddingTop) - paddingBottom, ErrorDialogData.SUPPRESSED));
                        }
                        child.layout(childLeft, childTop, child.getMeasuredWidth() + childLeft, child.getMeasuredHeight() + childTop);
                    }
                }
            }
        }
        this.r = paddingTop;
        this.s = height - paddingBottom;
        this.W = decorCount;
        if (this.T) {
            a(this.c, false, 0, false);
        }
        this.T = false;
    }

    public void computeScroll() {
        this.n = true;
        if (this.m.isFinished() || !this.m.computeScrollOffset()) {
            a(true);
            return;
        }
        int oldX = getScrollX();
        int oldY = getScrollY();
        int x = this.m.getCurrX();
        int y = this.m.getCurrY();
        if (!(oldX == x && oldY == y)) {
            scrollTo(x, y);
            if (!d(x)) {
                this.m.abortAnimation();
                scrollTo(0, y);
            }
        }
        ViewCompat.d(this);
    }

    private boolean d(int xpos) {
        if (this.g.size() != 0) {
            a ii = j();
            int width = f();
            float marginOffset = ((float) this.p) / ((float) width);
            int currentPage = ii.b;
            float pageOffset = ((((float) xpos) / ((float) width)) - ii.e) / (ii.d + marginOffset);
            this.V = false;
            a(currentPage, pageOffset);
            if (this.V) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.T) {
            return false;
        } else {
            this.V = false;
            a(0, 0.0f);
            if (this.V) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    @CallSuper
    private void a(int position, float offset) {
        int childCount;
        int i;
        View child;
        if (this.W > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                b lp = (b) child.getLayoutParams();
                if (lp.a) {
                    int childLeft;
                    switch (lp.b & 7) {
                        case 1:
                            childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 3:
                            childLeft = paddingLeft;
                            paddingLeft += child.getWidth();
                            break;
                        case 5:
                            childLeft = (width - paddingRight) - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            break;
                        default:
                            childLeft = paddingLeft;
                            break;
                    }
                    int childOffset = (childLeft + scrollX) - child.getLeft();
                    if (childOffset != 0) {
                        child.offsetLeftAndRight(childOffset);
                    }
                }
            }
        }
        if (this.ab != null) {
            this.ab.a(position, offset);
        }
        if (this.aa != null) {
            int size = this.aa.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = (e) this.aa.get(i2);
                if (eVar != null) {
                    eVar.a(position, offset);
                }
            }
        }
        if (this.ac != null) {
            this.ac.a(position, offset);
        }
        if (this.ae != null) {
            getScrollX();
            childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                if (!((b) child.getLayoutParams()).a) {
                    child.getLeft();
                    f();
                }
            }
        }
        this.V = true;
    }

    private void e(int position) {
        if (this.ab != null) {
            this.ab.b(position);
        }
        if (this.aa != null) {
            int z = this.aa.size();
            for (int i = 0; i < z; i++) {
                e listener = (e) this.aa.get(i);
                if (listener != null) {
                    listener.b(position);
                }
            }
        }
        if (this.ac != null) {
            this.ac.b(position);
        }
    }

    private void a(boolean postEvents) {
        boolean needPopulate;
        boolean z = true;
        if (this.ak == 2) {
            needPopulate = true;
        } else {
            needPopulate = false;
        }
        if (needPopulate) {
            b(false);
            if (this.m.isFinished()) {
                z = false;
            }
            if (z) {
                this.m.abortAnimation();
                int oldX = getScrollX();
                int oldY = getScrollY();
                int x = this.m.getCurrX();
                int y = this.m.getCurrY();
                if (!(oldX == x && oldY == y)) {
                    scrollTo(x, y);
                    if (x != oldX) {
                        d(x);
                    }
                }
            }
        }
        this.z = false;
        for (int i = 0; i < this.g.size(); i++) {
            a ii = (a) this.g.get(i);
            if (ii.c) {
                needPopulate = true;
                ii.c = false;
            }
        }
        if (!needPopulate) {
            return;
        }
        if (postEvents) {
            ViewCompat.a((View) this, this.aj);
        } else {
            this.aj.run();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction() & 255;
        if (action == 3 || action == 1) {
            h();
            return false;
        }
        if (action != 0) {
            if (this.B) {
                return true;
            }
            if (this.C) {
                return false;
            }
        }
        float x;
        switch (action) {
            case 0:
                x = ev.getX();
                this.I = x;
                this.G = x;
                x = ev.getY();
                this.J = x;
                this.H = x;
                this.K = ev.getPointerId(0);
                this.C = false;
                this.n = true;
                this.m.computeScrollOffset();
                if (this.ak == 2 && Math.abs(this.m.getFinalX() - this.m.getCurrX()) > this.P) {
                    this.m.abortAnimation();
                    this.z = false;
                    d();
                    this.B = true;
                    i();
                    a(1);
                    break;
                }
                a(false);
                this.B = false;
                break;
                break;
            case 2:
                int activePointerId = this.K;
                if (activePointerId != -1) {
                    int pointerIndex = ev.findPointerIndex(activePointerId);
                    float x2 = ev.getX(pointerIndex);
                    float dx = x2 - this.G;
                    float xDiff = Math.abs(dx);
                    float y = ev.getY(pointerIndex);
                    float yDiff = Math.abs(y - this.J);
                    if (dx != 0.0f) {
                        Object obj;
                        x = this.G;
                        if ((x >= ((float) this.E) || dx <= 0.0f) && (x <= ((float) (getWidth() - this.E)) || dx >= 0.0f)) {
                            obj = null;
                        } else {
                            obj = 1;
                        }
                        if (obj == null && a(this, false, (int) dx, (int) x2, (int) y)) {
                            this.G = x2;
                            this.H = y;
                            this.C = true;
                            return false;
                        }
                    }
                    if (xDiff > ((float) this.F) && 0.5f * xDiff > yDiff) {
                        this.B = true;
                        i();
                        a(1);
                        this.G = dx > 0.0f ? this.I + ((float) this.F) : this.I - ((float) this.F);
                        this.H = y;
                        b(true);
                    } else if (yDiff > ((float) this.F)) {
                        this.C = true;
                    }
                    if (this.B && a(x2)) {
                        ViewCompat.d(this);
                        break;
                    }
                }
                break;
            case 6:
                a(ev);
                break;
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(ev);
        return this.B;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.Q) {
            return true;
        }
        if (ev.getAction() == 0 && ev.getEdgeFlags() != 0) {
            return false;
        }
        if (this.b == null || this.b.a() == 0) {
            return false;
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(ev);
        boolean needsInvalidate = false;
        float x;
        switch (ev.getAction() & 255) {
            case 0:
                this.m.abortAnimation();
                this.z = false;
                d();
                x = ev.getX();
                this.I = x;
                this.G = x;
                x = ev.getY();
                this.J = x;
                this.H = x;
                this.K = ev.getPointerId(0);
                break;
            case 1:
                if (this.B) {
                    int nextPage;
                    VelocityTracker velocityTracker = this.L;
                    velocityTracker.computeCurrentVelocity(Constants.ONE_SECOND, (float) this.N);
                    int initialVelocity = (int) velocityTracker.getXVelocity(this.K);
                    this.z = true;
                    int width = f();
                    int scrollX = getScrollX();
                    a ii = j();
                    float marginOffset = ((float) this.p) / ((float) width);
                    int currentPage = ii.b;
                    float pageOffset = ((((float) scrollX) / ((float) width)) - ii.e) / (ii.d + marginOffset);
                    if (Math.abs((int) (ev.getX(ev.findPointerIndex(this.K)) - this.I)) <= this.O || Math.abs(initialVelocity) <= this.M) {
                        currentPage += (int) ((currentPage >= this.c ? 0.4f : 0.6f) + pageOffset);
                    } else if (initialVelocity <= 0) {
                        currentPage++;
                    }
                    if (this.g.size() > 0) {
                        nextPage = Math.max(((a) this.g.get(0)).b, Math.min(currentPage, ((a) this.g.get(this.g.size() - 1)).b));
                    } else {
                        nextPage = currentPage;
                    }
                    a(nextPage, true, true, initialVelocity);
                    needsInvalidate = h();
                    break;
                }
                break;
            case 2:
                if (!this.B) {
                    int pointerIndex = ev.findPointerIndex(this.K);
                    if (pointerIndex == -1) {
                        needsInvalidate = h();
                        break;
                    }
                    float x2 = ev.getX(pointerIndex);
                    float xDiff = Math.abs(x2 - this.G);
                    float y = ev.getY(pointerIndex);
                    float yDiff = Math.abs(y - this.H);
                    if (xDiff > ((float) this.F) && xDiff > yDiff) {
                        this.B = true;
                        i();
                        if (x2 - this.I > 0.0f) {
                            x = this.I + ((float) this.F);
                        } else {
                            x = this.I - ((float) this.F);
                        }
                        this.G = x;
                        this.H = y;
                        a(1);
                        b(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.B) {
                    needsInvalidate = a(ev.getX(ev.findPointerIndex(this.K))) | 0;
                    break;
                }
                break;
            case 3:
                if (this.B) {
                    a(this.c, true, 0, false);
                    needsInvalidate = h();
                    break;
                }
                break;
            case 5:
                int index = ev.getActionIndex();
                this.G = ev.getX(index);
                this.K = ev.getPointerId(index);
                break;
            case 6:
                a(ev);
                this.G = ev.getX(ev.findPointerIndex(this.K));
                break;
        }
        if (needsInvalidate) {
            ViewCompat.d(this);
        }
        return true;
    }

    private boolean h() {
        this.K = -1;
        this.B = false;
        this.C = false;
        if (this.L != null) {
            this.L.recycle();
            this.L = null;
        }
        this.R.onRelease();
        this.S.onRelease();
        if (this.R.isFinished() || this.S.isFinished()) {
            return true;
        }
        return false;
    }

    private void i() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    private boolean a(float x) {
        boolean needsInvalidate = false;
        float deltaX = this.G - x;
        this.G = x;
        float scrollX = ((float) getScrollX()) + deltaX;
        int width = f();
        float leftBound = ((float) width) * this.t;
        float rightBound = ((float) width) * this.u;
        boolean leftAbsolute = true;
        boolean rightAbsolute = true;
        a firstItem = (a) this.g.get(0);
        a lastItem = (a) this.g.get(this.g.size() - 1);
        if (firstItem.b != 0) {
            leftAbsolute = false;
            leftBound = firstItem.e * ((float) width);
        }
        if (lastItem.b != this.b.a() - 1) {
            rightAbsolute = false;
            rightBound = lastItem.e * ((float) width);
        }
        if (scrollX < leftBound) {
            if (leftAbsolute) {
                this.R.onPull(Math.abs(leftBound - scrollX) / ((float) width));
                needsInvalidate = true;
            }
            scrollX = leftBound;
        } else if (scrollX > rightBound) {
            if (rightAbsolute) {
                this.S.onPull(Math.abs(scrollX - rightBound) / ((float) width));
                needsInvalidate = true;
            }
            scrollX = rightBound;
        }
        this.G += scrollX - ((float) ((int) scrollX));
        scrollTo((int) scrollX, getScrollY());
        d((int) scrollX);
        return needsInvalidate;
    }

    private a j() {
        float scrollOffset;
        float marginOffset = 0.0f;
        int width = f();
        if (width > 0) {
            scrollOffset = ((float) getScrollX()) / ((float) width);
        } else {
            scrollOffset = 0.0f;
        }
        if (width > 0) {
            marginOffset = ((float) this.p) / ((float) width);
        }
        int lastPos = -1;
        float lastOffset = 0.0f;
        float lastWidth = 0.0f;
        boolean first = true;
        a lastItem = null;
        int i = 0;
        while (i < this.g.size()) {
            a ii = (a) this.g.get(i);
            if (!(first || ii.b == lastPos + 1)) {
                ii = this.h;
                ii.e = (lastOffset + lastWidth) + marginOffset;
                ii.b = lastPos + 1;
                ii.d = 1.0f;
                i--;
            }
            float offset = ii.e;
            float leftBound = offset;
            float rightBound = (ii.d + offset) + marginOffset;
            if (!first && scrollOffset < leftBound) {
                return lastItem;
            }
            if (scrollOffset < rightBound || i == this.g.size() - 1) {
                return ii;
            }
            first = false;
            lastPos = ii.b;
            lastOffset = offset;
            lastWidth = ii.d;
            lastItem = ii;
            i++;
        }
        return lastItem;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean needsInvalidate = false;
        int overScrollMode = getOverScrollMode();
        if (overScrollMode == 0 || (overScrollMode == 1 && this.b != null && this.b.a() > 1)) {
            int restoreCount;
            int height;
            int width;
            if (!this.R.isFinished()) {
                restoreCount = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.t * ((float) width));
                this.R.setSize(height, width);
                needsInvalidate = this.R.draw(canvas) | 0;
                canvas.restoreToCount(restoreCount);
            }
            if (!this.S.isFinished()) {
                restoreCount = canvas.save();
                width = getWidth();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.u + 1.0f)) * ((float) width));
                this.S.setSize(height, width);
                needsInvalidate |= this.S.draw(canvas);
                canvas.restoreToCount(restoreCount);
            }
        } else {
            this.R.finish();
            this.S.finish();
        }
        if (needsInvalidate) {
            ViewCompat.d(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.p > 0 && this.q != null && this.g.size() > 0 && this.b != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float marginOffset = ((float) this.p) / ((float) width);
            int itemIndex = 0;
            a ii = (a) this.g.get(0);
            float offset = ii.e;
            int itemCount = this.g.size();
            int firstPos = ii.b;
            int lastPos = ((a) this.g.get(itemCount - 1)).b;
            int pos = firstPos;
            while (pos < lastPos) {
                float drawAt;
                while (pos > ii.b && itemIndex < itemCount) {
                    itemIndex++;
                    ii = (a) this.g.get(itemIndex);
                }
                if (pos == ii.b) {
                    drawAt = (ii.e + ii.d) * ((float) width);
                    offset = (ii.e + ii.d) + marginOffset;
                } else {
                    drawAt = (1.0f + offset) * ((float) width);
                    offset += 1.0f + marginOffset;
                }
                if (((float) this.p) + drawAt > ((float) scrollX)) {
                    this.q.setBounds(Math.round(drawAt), this.r, Math.round(((float) this.p) + drawAt), this.s);
                    this.q.draw(canvas);
                }
                if (drawAt <= ((float) (scrollX + width))) {
                    pos++;
                } else {
                    return;
                }
            }
        }
    }

    private void a(MotionEvent ev) {
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) == this.K) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.G = ev.getX(newPointerIndex);
            this.K = ev.getPointerId(newPointerIndex);
            if (this.L != null) {
                this.L.clear();
            }
        }
    }

    private void b(boolean enabled) {
        if (this.y != enabled) {
            this.y = enabled;
        }
    }

    public boolean canScrollHorizontally(int direction) {
        if (this.b == null) {
            return false;
        }
        int width = f();
        int scrollX = getScrollX();
        if (direction < 0) {
            if (scrollX > ((int) (((float) width) * this.t))) {
                return true;
            }
            return false;
        } else if (direction <= 0 || scrollX >= ((int) (((float) width) * this.u))) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            for (int i = group.getChildCount() - 1; i >= 0; i--) {
                View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && y + scrollY >= child.getTop() && y + scrollY < child.getBottom()) {
                    if (a(child, true, dx, (x + scrollX) - child.getLeft(), (y + scrollY) - child.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (checkV && v.canScrollHorizontally(-dx)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (!super.dispatchKeyEvent(event)) {
            boolean f;
            if (event.getAction() == 0) {
                switch (event.getKeyCode()) {
                    case 21:
                        if (!event.hasModifiers(2)) {
                            f = f(17);
                            break;
                        }
                        f = k();
                        break;
                    case 22:
                        if (!event.hasModifiers(2)) {
                            f = f(66);
                            break;
                        }
                        f = l();
                        break;
                    case 61:
                        if (!event.hasNoModifiers()) {
                            if (event.hasModifiers(1)) {
                                f = f(1);
                                break;
                            }
                        }
                        f = f(2);
                        break;
                }
            }
            f = false;
            if (!f) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean f(int direction) {
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        } else if (currentFocused != null) {
            boolean isChild = false;
            for (ViewPager parent = currentFocused.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                if (parent == this) {
                    isChild = true;
                    break;
                }
            }
            if (!isChild) {
                StringBuilder sb = new StringBuilder();
                sb.append(currentFocused.getClass().getSimpleName());
                for (ViewParent parent2 = currentFocused.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ").append(parent2.getClass().getSimpleName());
                }
                new StringBuilder("arrowScroll tried to find focus based on non-child current focused view ").append(sb.toString());
                currentFocused = null;
            }
        }
        boolean handled = false;
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        if (nextFocused == null || nextFocused == currentFocused) {
            if (direction == 17 || direction == 1) {
                handled = k();
            } else {
                if (direction != 66) {
                }
                handled = l();
            }
        } else if (direction == 17) {
            handled = (currentFocused == null || a(this.i, nextFocused).left < a(this.i, currentFocused).left) ? nextFocused.requestFocus() : k();
        } else if (direction == 66) {
            int nextLeft = a(this.i, nextFocused).left;
            int currLeft = a(this.i, currentFocused).left;
            if (currentFocused == null || nextLeft > currLeft) {
                handled = nextFocused.requestFocus();
            }
            handled = l();
        }
        if (handled) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
        }
        return handled;
    }

    private Rect a(Rect outRect, View child) {
        if (outRect == null) {
            outRect = new Rect();
        }
        if (child == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.left = child.getLeft();
            outRect.right = child.getRight();
            outRect.top = child.getTop();
            outRect.bottom = child.getBottom();
            ViewGroup parent = child.getParent();
            while ((parent instanceof ViewGroup) && parent != this) {
                ViewGroup group = parent;
                outRect.left += group.getLeft();
                outRect.right += group.getRight();
                outRect.top += group.getTop();
                outRect.bottom += group.getBottom();
                parent = group.getParent();
            }
        }
        return outRect;
    }

    private boolean k() {
        if (this.c <= 0) {
            return false;
        }
        setCurrentItem(this.c - 1, true);
        return true;
    }

    private boolean l() {
        if (this.b == null || this.c >= this.b.a() - 1) {
            return false;
        }
        setCurrentItem(this.c + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        int focusableCount = views.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child.getVisibility() == 0) {
                    a ii = a(child);
                    if (ii != null && ii.b == this.c) {
                        child.addFocusables(views, direction, focusableMode);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && focusableCount != views.size()) || !isFocusable()) {
            return;
        }
        if (((focusableMode & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && views != null) {
            views.add(this);
        }
    }

    public void addTouchables(ArrayList<View> views) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                a ii = a(child);
                if (ii != null && ii.b == this.c) {
                    child.addTouchables(views);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        int index;
        int increment;
        int end;
        int count = getChildCount();
        if ((direction & 2) != 0) {
            index = 0;
            increment = 1;
            end = count;
        } else {
            index = count - 1;
            increment = -1;
            end = -1;
        }
        for (int i = index; i != end; i += increment) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                a ii = a(child);
                if (ii != null && ii.b == this.c && child.requestFocus(direction, previouslyFocusedRect)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(event);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                a ii = a(child);
                if (ii != null && ii.b == this.c && child.dispatchPopulateAccessibilityEvent(event)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new b();
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof b) && super.checkLayoutParams(p);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new b(getContext(), attrs);
    }
}
