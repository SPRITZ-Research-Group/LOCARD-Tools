package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.content.a;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hp;
import defpackage.hs;
import defpackage.ie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator<e> COMPARATOR = new Comparator<e>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return ((e) obj).b - ((e) obj2).b;
        }
    };
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    static final int[] LAYOUT_ATTRS = new int[]{16842931};
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private static final Interpolator sInterpolator = new Interpolator() {
        public final float getInterpolation(float f) {
            f -= 1.0f;
            return ((((f * f) * f) * f) * f) + 1.0f;
        }
    };
    private static final l sPositionComparator = new l();
    private int mActivePointerId = -1;
    a mAdapter;
    private List<g> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable = new Runnable(this) {
        final /* synthetic */ ViewPager a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.setScrollState(0);
            this.a.populate();
        }
    };
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout = true;
    private float mFirstOffset = -3.4028235E38f;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private h mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<e> mItems = new ArrayList();
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset = Float.MAX_VALUE;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets = false;
    private j mObserver;
    private int mOffscreenPageLimit = 1;
    private h mOnPageChangeListener;
    private List<h> mOnPageChangeListeners;
    private int mPageMargin;
    private i mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState = null;
    private ClassLoader mRestoredClassLoader = null;
    private int mRestoredCurItem = -1;
    private EdgeEffect mRightEdge;
    private int mScrollState = 0;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final e mTempItem = new e();
    private final Rect mTempRect = new Rect();
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public class LayoutParams extends android.view.ViewGroup.LayoutParams {
        public boolean a;
        public int b;
        float c = BitmapDescriptorFactory.HUE_RED;
        boolean d;
        int e;
        int f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    public class SavedState extends AbsSavedState {
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
        int a;
        Parcelable b;
        ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("FragmentPager.SavedState{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" position=");
            stringBuilder.append(this.a);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    public ViewPager(Context context) {
        super(context);
        initViewPager();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViewPager();
    }

    void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f);
        this.mCloseEnough = (int) (2.0f * f);
        this.mDefaultGutterSize = (int) (f * 16.0f);
        hs.a((View) this, new f(this));
        if (hs.f(this) == 0) {
            hs.a((View) this, 1);
        }
        hs.a((View) this, new hp(this) {
            final /* synthetic */ ViewPager a;
            private final Rect b = new Rect();

            {
                this.a = r1;
            }

            public final ie onApplyWindowInsets(View view, ie ieVar) {
                ie a = hs.a(view, ieVar);
                if (a.f()) {
                    return a;
                }
                Rect rect = this.b;
                rect.left = a.a();
                rect.top = a.b();
                rect.right = a.c();
                rect.bottom = a.d();
                int childCount = this.a.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ie b = hs.b(this.a.getChildAt(i), a);
                    rect.left = Math.min(b.a(), rect.left);
                    rect.top = Math.min(b.b(), rect.top);
                    rect.right = Math.min(b.c(), rect.right);
                    rect.bottom = Math.min(b.d(), rect.bottom);
                }
                return a.a(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        if (!(this.mScroller == null || this.mScroller.isFinished())) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    void setScrollState(int i) {
        if (this.mScrollState != i) {
            this.mScrollState = i;
            if (this.mPageTransformer != null) {
                enableLayers(i != 0);
            }
            dispatchOnScrollStateChanged(i);
        }
    }

    public void setAdapter(a aVar) {
        int i = 0;
        if (this.mAdapter != null) {
            this.mAdapter.setViewPagerObserver(null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.mItems.size(); i2++) {
                e eVar = (e) this.mItems.get(i2);
                this.mAdapter.destroyItem((ViewGroup) this, eVar.b, eVar.a);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            removeNonDecorViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        a aVar2 = this.mAdapter;
        this.mAdapter = aVar;
        this.mExpectedAdapterCount = 0;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new j(this);
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (z) {
                requestLayout();
            } else {
                populate();
            }
        }
        if (this.mAdapterChangeListeners != null && !this.mAdapterChangeListeners.isEmpty()) {
            int size = this.mAdapterChangeListeners.size();
            while (i < size) {
                ((g) this.mAdapterChangeListeners.get(i)).onAdapterChanged(this, aVar2, aVar);
                i++;
            }
        }
    }

    private void removeNonDecorViews() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((LayoutParams) getChildAt(i).getLayoutParams()).a) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public a getAdapter() {
        return this.mAdapter;
    }

    public void addOnAdapterChangeListener(g gVar) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(gVar);
    }

    public void removeOnAdapterChangeListener(g gVar) {
        if (this.mAdapterChangeListeners != null) {
            this.mAdapterChangeListeners.remove(gVar);
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i, this.mFirstLayout ^ 1, false);
    }

    public void setCurrentItem(int i, boolean z) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i, z, false);
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    void setCurrentItemInternal(int i, boolean z, boolean z2) {
        setCurrentItemInternal(i, z, z2, 0);
    }

    void setCurrentItemInternal(int i, boolean z, boolean z2, int i2) {
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.mCurItem != i || this.mItems.size() == 0) {
            z2 = true;
            if (i < 0) {
                i = 0;
            } else if (i >= this.mAdapter.getCount()) {
                i = this.mAdapter.getCount() - 1;
            }
            int i3 = this.mOffscreenPageLimit;
            if (i > this.mCurItem + i3 || i < this.mCurItem - i3) {
                for (i3 = 0; i3 < this.mItems.size(); i3++) {
                    ((e) this.mItems.get(i3)).c = true;
                }
            }
            if (this.mCurItem == i) {
                z2 = false;
            }
            if (this.mFirstLayout) {
                this.mCurItem = i;
                if (z2) {
                    dispatchOnPageSelected(i);
                }
                requestLayout();
                return;
            }
            populate(i);
            scrollToItem(i, z, i2, z2);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void scrollToItem(int i, boolean z, int i2, boolean z2) {
        e infoForPosition = infoForPosition(i);
        int clientWidth = infoForPosition != null ? (int) (((float) getClientWidth()) * Math.max(this.mFirstOffset, Math.min(infoForPosition.e, this.mLastOffset))) : 0;
        if (z) {
            smoothScrollTo(clientWidth, 0, i2);
            if (z2) {
                dispatchOnPageSelected(i);
                return;
            }
        }
        if (z2) {
            dispatchOnPageSelected(i);
        }
        completeScroll(false);
        scrollTo(clientWidth, 0);
        pageScrolled(clientWidth);
    }

    @Deprecated
    public void setOnPageChangeListener(h hVar) {
        this.mOnPageChangeListener = hVar;
    }

    public void addOnPageChangeListener(h hVar) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(hVar);
    }

    public void removeOnPageChangeListener(h hVar) {
        if (this.mOnPageChangeListeners != null) {
            this.mOnPageChangeListeners.remove(hVar);
        }
    }

    public void clearOnPageChangeListeners() {
        if (this.mOnPageChangeListeners != null) {
            this.mOnPageChangeListeners.clear();
        }
    }

    public void setPageTransformer(boolean z, i iVar) {
        setPageTransformer(z, iVar, 2);
    }

    public void setPageTransformer(boolean z, i iVar, int i) {
        int i2 = 1;
        boolean z2 = iVar != null;
        Object obj = z2 != (this.mPageTransformer != null) ? 1 : null;
        this.mPageTransformer = iVar;
        setChildrenDrawingOrderEnabled(z2);
        if (z2) {
            if (z) {
                i2 = 2;
            }
            this.mDrawingOrder = i2;
            this.mPageTransformerLayerType = i;
        } else {
            this.mDrawingOrder = 0;
        }
        if (obj != null) {
            populate();
        }
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.mDrawingOrder == 2) {
            i2 = (i - 1) - i2;
        }
        return ((LayoutParams) ((View) this.mDrawingOrderedChildren.get(i2)).getLayoutParams()).f;
    }

    h setInternalPageChangeListener(h hVar) {
        h hVar2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = hVar;
        return hVar2;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public void setOffscreenPageLimit(int i) {
        if (i <= 0) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder("Requested offscreen page limit ");
            stringBuilder.append(i);
            stringBuilder.append(" too small; defaulting to 1");
            Log.w(str, stringBuilder.toString());
            i = 1;
        }
        if (i != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i;
            populate();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.mPageMargin;
        this.mPageMargin = i;
        int width = getWidth();
        recomputeScrollPosition(width, width, i, i2);
        requestLayout();
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(a.a(getContext(), i));
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mMarginDrawable;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    void smoothScrollTo(int i, int i2) {
        smoothScrollTo(i, i2, 0);
    }

    void smoothScrollTo(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int currX;
        Object obj = (this.mScroller == null || this.mScroller.isFinished()) ? null : 1;
        if (obj != null) {
            currX = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            currX = getScrollX();
        }
        int i4 = currX;
        int scrollY = getScrollY();
        int i5 = i - i4;
        int i6 = i2 - scrollY;
        if (i5 == 0 && i6 == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        i = getClientWidth();
        i2 = i / 2;
        float f = (float) i;
        float f2 = (float) i2;
        f2 += distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i5)) * 1.0f) / f)) * f2;
        i3 = Math.abs(i3);
        if (i3 > 0) {
            i = Math.round(Math.abs(f2 / ((float) i3)) * 1000.0f) * 4;
        } else {
            i = (int) (((((float) Math.abs(i5)) / ((f * this.mAdapter.getPageWidth(this.mCurItem)) + ((float) this.mPageMargin))) + 1.0f) * 100.0f);
        }
        int min = Math.min(i, MAX_SETTLE_DURATION);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(i4, scrollY, i5, i6, min);
        hs.e(this);
    }

    e addNewItem(int i, int i2) {
        e eVar = new e();
        eVar.b = i;
        eVar.a = this.mAdapter.instantiateItem((ViewGroup) this, i);
        eVar.d = this.mAdapter.getPageWidth(i);
        if (i2 < 0 || i2 >= this.mItems.size()) {
            this.mItems.add(eVar);
        } else {
            this.mItems.add(i2, eVar);
        }
        return eVar;
    }

    void dataSetChanged() {
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        Object obj = (this.mItems.size() >= (this.mOffscreenPageLimit * 2) + 1 || this.mItems.size() >= count) ? null : 1;
        Object obj2 = obj;
        int i = this.mCurItem;
        int i2 = 0;
        Object obj3 = null;
        while (i2 < this.mItems.size()) {
            e eVar = (e) this.mItems.get(i2);
            int itemPosition = this.mAdapter.getItemPosition(eVar.a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i2);
                    i2--;
                    if (obj3 == null) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        obj3 = 1;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, eVar.b, eVar.a);
                    if (this.mCurItem == eVar.b) {
                        i = Math.max(0, Math.min(this.mCurItem, count - 1));
                    }
                } else if (eVar.b != itemPosition) {
                    if (eVar.b == this.mCurItem) {
                        i = itemPosition;
                    }
                    eVar.b = itemPosition;
                }
                obj2 = 1;
            }
            i2++;
        }
        if (obj3 != null) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (obj2 != null) {
            count = getChildCount();
            for (i2 = 0; i2 < count; i2++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
                if (!layoutParams.a) {
                    layoutParams.c = BitmapDescriptorFactory.HUE_RED;
                }
            }
            setCurrentItemInternal(i, false, true);
            requestLayout();
        }
    }

    void populate() {
        populate(this.mCurItem);
    }

    void populate(int r18) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.viewpager.widget.ViewPager.populate(int):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r17 = this;
        r0 = r17;
        r1 = r18;
        r2 = r0.mCurItem;
        if (r2 == r1) goto L_0x0011;
    L_0x0008:
        r2 = r0.mCurItem;
        r2 = r0.infoForPosition(r2);
        r0.mCurItem = r1;
        goto L_0x0012;
    L_0x0011:
        r2 = 0;
    L_0x0012:
        r1 = r0.mAdapter;
        if (r1 != 0) goto L_0x001a;
    L_0x0016:
        r17.sortChildDrawingOrder();
        return;
    L_0x001a:
        r1 = r0.mPopulatePending;
        if (r1 == 0) goto L_0x0022;
    L_0x001e:
        r17.sortChildDrawingOrder();
        return;
    L_0x0022:
        r1 = r17.getWindowToken();
        if (r1 != 0) goto L_0x0029;
    L_0x0028:
        return;
    L_0x0029:
        r1 = r0.mAdapter;
        r1.startUpdate(r0);
        r1 = r0.mOffscreenPageLimit;
        r4 = r0.mCurItem;
        r4 = r4 - r1;
        r5 = 0;
        r4 = java.lang.Math.max(r5, r4);
        r6 = r0.mAdapter;
        r6 = r6.getCount();
        r7 = r6 + -1;
        r8 = r0.mCurItem;
        r8 = r8 + r1;
        r1 = java.lang.Math.min(r7, r8);
        r7 = r0.mExpectedAdapterCount;
        if (r6 != r7) goto L_0x0214;
    L_0x004b:
        r7 = 0;
    L_0x004c:
        r8 = r0.mItems;
        r8 = r8.size();
        if (r7 >= r8) goto L_0x006c;
    L_0x0054:
        r8 = r0.mItems;
        r8 = r8.get(r7);
        r8 = (androidx.viewpager.widget.e) r8;
        r9 = r8.b;
        r10 = r0.mCurItem;
        if (r9 < r10) goto L_0x0069;
    L_0x0062:
        r9 = r8.b;
        r10 = r0.mCurItem;
        if (r9 != r10) goto L_0x006c;
    L_0x0068:
        goto L_0x006d;
    L_0x0069:
        r7 = r7 + 1;
        goto L_0x004c;
    L_0x006c:
        r8 = 0;
    L_0x006d:
        if (r8 != 0) goto L_0x0077;
    L_0x006f:
        if (r6 <= 0) goto L_0x0077;
    L_0x0071:
        r8 = r0.mCurItem;
        r8 = r0.addNewItem(r8, r7);
    L_0x0077:
        r9 = 0;
        if (r8 == 0) goto L_0x01a2;
    L_0x007a:
        r10 = r7 + -1;
        if (r10 < 0) goto L_0x0087;
    L_0x007e:
        r11 = r0.mItems;
        r11 = r11.get(r10);
        r11 = (androidx.viewpager.widget.e) r11;
        goto L_0x0088;
    L_0x0087:
        r11 = 0;
    L_0x0088:
        r12 = r17.getClientWidth();
        r13 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 > 0) goto L_0x0092;
    L_0x0090:
        r3 = 0;
        goto L_0x009f;
    L_0x0092:
        r14 = r8.d;
        r14 = r13 - r14;
        r15 = r17.getPaddingLeft();
        r15 = (float) r15;
        r3 = (float) r12;
        r15 = r15 / r3;
        r3 = r14 + r15;
    L_0x009f:
        r14 = r0.mCurItem;
        r14 = r14 + -1;
        r15 = r10;
        r10 = r7;
        r7 = 0;
    L_0x00a6:
        if (r14 < 0) goto L_0x0105;
    L_0x00a8:
        r16 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1));
        if (r16 < 0) goto L_0x00d3;
    L_0x00ac:
        if (r14 >= r4) goto L_0x00d3;
    L_0x00ae:
        if (r11 == 0) goto L_0x0105;
    L_0x00b0:
        r5 = r11.b;
        if (r14 != r5) goto L_0x0101;
    L_0x00b4:
        r5 = r11.c;
        if (r5 != 0) goto L_0x0101;
    L_0x00b8:
        r5 = r0.mItems;
        r5.remove(r15);
        r5 = r0.mAdapter;
        r11 = r11.a;
        r5.destroyItem(r0, r14, r11);
        r15 = r15 + -1;
        r10 = r10 + -1;
        if (r15 < 0) goto L_0x00ff;
    L_0x00ca:
        r5 = r0.mItems;
        r5 = r5.get(r15);
        r5 = (androidx.viewpager.widget.e) r5;
        goto L_0x0100;
    L_0x00d3:
        if (r11 == 0) goto L_0x00e9;
    L_0x00d5:
        r5 = r11.b;
        if (r14 != r5) goto L_0x00e9;
    L_0x00d9:
        r5 = r11.d;
        r7 = r7 + r5;
        r15 = r15 + -1;
        if (r15 < 0) goto L_0x00ff;
    L_0x00e0:
        r5 = r0.mItems;
        r5 = r5.get(r15);
        r5 = (androidx.viewpager.widget.e) r5;
        goto L_0x0100;
    L_0x00e9:
        r5 = r15 + 1;
        r5 = r0.addNewItem(r14, r5);
        r5 = r5.d;
        r7 = r7 + r5;
        r10 = r10 + 1;
        if (r15 < 0) goto L_0x00ff;
    L_0x00f6:
        r5 = r0.mItems;
        r5 = r5.get(r15);
        r5 = (androidx.viewpager.widget.e) r5;
        goto L_0x0100;
    L_0x00ff:
        r5 = 0;
    L_0x0100:
        r11 = r5;
    L_0x0101:
        r14 = r14 + -1;
        r5 = 0;
        goto L_0x00a6;
    L_0x0105:
        r3 = r8.d;
        r4 = r10 + 1;
        r5 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1));
        if (r5 >= 0) goto L_0x0196;
    L_0x010d:
        r5 = r0.mItems;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x011e;
    L_0x0115:
        r5 = r0.mItems;
        r5 = r5.get(r4);
        r5 = (androidx.viewpager.widget.e) r5;
        goto L_0x011f;
    L_0x011e:
        r5 = 0;
    L_0x011f:
        if (r12 > 0) goto L_0x0123;
    L_0x0121:
        r7 = 0;
        goto L_0x012b;
    L_0x0123:
        r7 = r17.getPaddingRight();
        r7 = (float) r7;
        r11 = (float) r12;
        r7 = r7 / r11;
        r7 = r7 + r13;
    L_0x012b:
        r11 = r0.mCurItem;
    L_0x012d:
        r11 = r11 + 1;
        if (r11 >= r6) goto L_0x0196;
    L_0x0131:
        r12 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r12 < 0) goto L_0x0160;
    L_0x0135:
        if (r11 <= r1) goto L_0x0160;
    L_0x0137:
        if (r5 == 0) goto L_0x0196;
    L_0x0139:
        r12 = r5.b;
        if (r11 != r12) goto L_0x0195;
    L_0x013d:
        r12 = r5.c;
        if (r12 != 0) goto L_0x0195;
    L_0x0141:
        r12 = r0.mItems;
        r12.remove(r4);
        r12 = r0.mAdapter;
        r5 = r5.a;
        r12.destroyItem(r0, r11, r5);
        r5 = r0.mItems;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x015e;
    L_0x0155:
        r5 = r0.mItems;
        r5 = r5.get(r4);
        r5 = (androidx.viewpager.widget.e) r5;
        goto L_0x0195;
    L_0x015e:
        r5 = 0;
        goto L_0x0195;
    L_0x0160:
        if (r5 == 0) goto L_0x017c;
    L_0x0162:
        r12 = r5.b;
        if (r11 != r12) goto L_0x017c;
    L_0x0166:
        r5 = r5.d;
        r3 = r3 + r5;
        r4 = r4 + 1;
        r5 = r0.mItems;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x015e;
    L_0x0173:
        r5 = r0.mItems;
        r5 = r5.get(r4);
        r5 = (androidx.viewpager.widget.e) r5;
        goto L_0x0195;
    L_0x017c:
        r5 = r0.addNewItem(r11, r4);
        r4 = r4 + 1;
        r5 = r5.d;
        r3 = r3 + r5;
        r5 = r0.mItems;
        r5 = r5.size();
        if (r4 >= r5) goto L_0x015e;
    L_0x018d:
        r5 = r0.mItems;
        r5 = r5.get(r4);
        r5 = (androidx.viewpager.widget.e) r5;
    L_0x0195:
        goto L_0x012d;
    L_0x0196:
        r0.calculatePageOffsets(r8, r10, r2);
        r1 = r0.mAdapter;
        r2 = r0.mCurItem;
        r3 = r8.a;
        r1.setPrimaryItem(r0, r2, r3);
    L_0x01a2:
        r1 = r0.mAdapter;
        r1.finishUpdate(r0);
        r1 = r17.getChildCount();
        r2 = 0;
    L_0x01ac:
        if (r2 >= r1) goto L_0x01d5;
    L_0x01ae:
        r3 = r0.getChildAt(r2);
        r4 = r3.getLayoutParams();
        r4 = (androidx.viewpager.widget.ViewPager.LayoutParams) r4;
        r4.f = r2;
        r5 = r4.a;
        if (r5 != 0) goto L_0x01d2;
    L_0x01be:
        r5 = r4.c;
        r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1));
        if (r5 != 0) goto L_0x01d2;
    L_0x01c4:
        r3 = r0.infoForChild(r3);
        if (r3 == 0) goto L_0x01d2;
    L_0x01ca:
        r5 = r3.d;
        r4.c = r5;
        r3 = r3.b;
        r4.e = r3;
    L_0x01d2:
        r2 = r2 + 1;
        goto L_0x01ac;
    L_0x01d5:
        r17.sortChildDrawingOrder();
        r1 = r17.hasFocus();
        if (r1 == 0) goto L_0x0213;
    L_0x01de:
        r1 = r17.findFocus();
        if (r1 == 0) goto L_0x01e9;
    L_0x01e4:
        r3 = r0.infoForAnyChild(r1);
        goto L_0x01ea;
    L_0x01e9:
        r3 = 0;
    L_0x01ea:
        if (r3 == 0) goto L_0x01f2;
    L_0x01ec:
        r1 = r3.b;
        r2 = r0.mCurItem;
        if (r1 == r2) goto L_0x0213;
    L_0x01f2:
        r1 = 0;
    L_0x01f3:
        r2 = r17.getChildCount();
        if (r1 >= r2) goto L_0x0213;
    L_0x01f9:
        r2 = r0.getChildAt(r1);
        r3 = r0.infoForChild(r2);
        if (r3 == 0) goto L_0x0210;
    L_0x0203:
        r3 = r3.b;
        r4 = r0.mCurItem;
        if (r3 != r4) goto L_0x0210;
    L_0x0209:
        r3 = 2;
        r2 = r2.requestFocus(r3);
        if (r2 != 0) goto L_0x0213;
    L_0x0210:
        r1 = r1 + 1;
        goto L_0x01f3;
    L_0x0213:
        return;
    L_0x0214:
        r1 = r17.getResources();	 Catch:{ NotFoundException -> 0x0221 }
        r2 = r17.getId();	 Catch:{ NotFoundException -> 0x0221 }
        r1 = r1.getResourceName(r2);	 Catch:{ NotFoundException -> 0x0221 }
        goto L_0x0229;
    L_0x0221:
        r1 = r17.getId();
        r1 = java.lang.Integer.toHexString(r1);
    L_0x0229:
        r2 = new java.lang.IllegalStateException;
        r3 = new java.lang.StringBuilder;
        r4 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r3.<init>(r4);
        r4 = r0.mExpectedAdapterCount;
        r3.append(r4);
        r4 = ", found: ";
        r3.append(r4);
        r3.append(r6);
        r4 = " Pager id: ";
        r3.append(r4);
        r3.append(r1);
        r1 = " Pager class: ";
        r3.append(r1);
        r1 = r17.getClass();
        r3.append(r1);
        r1 = " Problematic adapter: ";
        r3.append(r1);
        r1 = r0.mAdapter;
        r1 = r1.getClass();
        r3.append(r1);
        r1 = r3.toString();
        r2.<init>(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.populate(int):void");
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            if (this.mDrawingOrderedChildren == null) {
                this.mDrawingOrderedChildren = new ArrayList();
            } else {
                this.mDrawingOrderedChildren.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.mDrawingOrderedChildren.add(getChildAt(i));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    private void calculatePageOffsets(e eVar, int i, e eVar2) {
        int i2;
        int size;
        int count = this.mAdapter.getCount();
        int clientWidth = getClientWidth();
        float f = clientWidth > 0 ? ((float) this.mPageMargin) / ((float) clientWidth) : BitmapDescriptorFactory.HUE_RED;
        if (eVar2 != null) {
            int i3 = eVar2.b;
            Object obj;
            e eVar3;
            if (i3 < eVar.b) {
                float f2 = (eVar2.e + eVar2.d) + f;
                i3++;
                i2 = 0;
                while (i3 <= eVar.b && i2 < this.mItems.size()) {
                    obj = this.mItems.get(i2);
                    while (true) {
                        eVar3 = (e) obj;
                        if (i3 <= eVar3.b || i2 >= this.mItems.size() - 1) {
                            while (i3 < eVar3.b) {
                                f2 += this.mAdapter.getPageWidth(i3) + f;
                                i3++;
                            }
                        } else {
                            i2++;
                            obj = this.mItems.get(i2);
                        }
                    }
                    while (i3 < eVar3.b) {
                        f2 += this.mAdapter.getPageWidth(i3) + f;
                        i3++;
                    }
                    eVar3.e = f2;
                    f2 += eVar3.d + f;
                    i3++;
                }
            } else if (i3 > eVar.b) {
                size = this.mItems.size() - 1;
                float f3 = eVar2.e;
                while (true) {
                    i3--;
                    if (i3 < eVar.b || size < 0) {
                        break;
                    }
                    obj = this.mItems.get(size);
                    while (true) {
                        eVar3 = (e) obj;
                        if (i3 >= eVar3.b || size <= 0) {
                            while (i3 > eVar3.b) {
                                f3 -= this.mAdapter.getPageWidth(i3) + f;
                                i3--;
                            }
                        } else {
                            size--;
                            obj = this.mItems.get(size);
                        }
                    }
                    while (i3 > eVar3.b) {
                        f3 -= this.mAdapter.getPageWidth(i3) + f;
                        i3--;
                    }
                    f3 -= eVar3.d + f;
                    eVar3.e = f3;
                }
            }
        }
        i2 = this.mItems.size();
        float f4 = eVar.e;
        size = eVar.b - 1;
        this.mFirstOffset = eVar.b == 0 ? eVar.e : -3.4028235E38f;
        count--;
        this.mLastOffset = eVar.b == count ? (eVar.e + eVar.d) - 1.0f : Float.MAX_VALUE;
        int i4 = i - 1;
        while (i4 >= 0) {
            e eVar4 = (e) this.mItems.get(i4);
            while (size > eVar4.b) {
                f4 -= this.mAdapter.getPageWidth(size) + f;
                size--;
            }
            f4 -= eVar4.d + f;
            eVar4.e = f4;
            if (eVar4.b == 0) {
                this.mFirstOffset = f4;
            }
            i4--;
            size--;
        }
        f4 = (eVar.e + eVar.d) + f;
        int i5 = eVar.b + 1;
        i++;
        while (i < i2) {
            e eVar5 = (e) this.mItems.get(i);
            while (i5 < eVar5.b) {
                f4 += this.mAdapter.getPageWidth(i5) + f;
                i5++;
            }
            if (eVar5.b == count) {
                this.mLastOffset = (eVar5.d + f4) - 1.0f;
            }
            eVar5.e = f4;
            f4 += eVar5.d + f;
            i++;
            i5++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.mCurItem;
        if (this.mAdapter != null) {
            savedState.b = this.mAdapter.saveState();
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.mAdapter != null) {
                this.mAdapter.restoreState(savedState.b, savedState.c);
                setCurrentItemInternal(savedState.a, false, true);
                return;
            }
            this.mRestoredCurItem = savedState.a;
            this.mRestoredAdapterState = savedState.b;
            this.mRestoredClassLoader = savedState.c;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        layoutParams2.a |= isDecorView(view);
        if (!this.mInLayout) {
            super.addView(view, i, layoutParams);
        } else if (layoutParams2 == null || !layoutParams2.a) {
            layoutParams2.d = true;
            addViewInLayout(view, i, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    private static boolean isDecorView(View view) {
        return view.getClass().getAnnotation(d.class) != null;
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    e infoForChild(View view) {
        for (int i = 0; i < this.mItems.size(); i++) {
            e eVar = (e) this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(view, eVar.a)) {
                return eVar;
            }
        }
        return null;
    }

    e infoForAnyChild(View view) {
        while (true) {
            ViewPager parent = view.getParent();
            if (parent == this) {
                return infoForChild(view);
            }
            if (parent != null && (parent instanceof View)) {
                view = parent;
            }
        }
        return null;
    }

    e infoForPosition(int i) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            e eVar = (e) this.mItems.get(i2);
            if (eVar.b == i) {
                return eVar;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        measuredWidth = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i4 = measuredHeight;
        measuredHeight = measuredWidth;
        while (true) {
            boolean z = true;
            int i5 = 1073741824;
            if (0 >= childCount) {
                break;
            }
            View childAt = getChildAt(0);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams != null && layoutParams.a) {
                    int i6;
                    int i7;
                    int i8 = layoutParams.b & 7;
                    int i9 = layoutParams.b & 112;
                    Object obj = (i9 == 48 || i9 == 80) ? 1 : null;
                    if (!(i8 == 3 || i8 == 5)) {
                        z = false;
                    }
                    i8 = Integer.MIN_VALUE;
                    if (obj != null) {
                        i8 = 1073741824;
                    } else if (z) {
                        i6 = 1073741824;
                        if (layoutParams.width == -2) {
                            i7 = layoutParams.width == -1 ? layoutParams.width : measuredHeight;
                            i8 = 1073741824;
                        } else {
                            i7 = measuredHeight;
                        }
                        if (layoutParams.height == -2) {
                            i3 = layoutParams.height == -1 ? layoutParams.height : i4;
                        } else {
                            i3 = i4;
                            i5 = i6;
                        }
                        childAt.measure(MeasureSpec.makeMeasureSpec(i7, i8), MeasureSpec.makeMeasureSpec(i3, i5));
                        if (obj != null) {
                            i4 -= childAt.getMeasuredHeight();
                        } else if (z) {
                            measuredHeight -= childAt.getMeasuredWidth();
                        }
                    }
                    i6 = Integer.MIN_VALUE;
                    if (layoutParams.width == -2) {
                        i7 = measuredHeight;
                    } else {
                        if (layoutParams.width == -1) {
                        }
                        i8 = 1073741824;
                    }
                    if (layoutParams.height == -2) {
                        i3 = i4;
                        i5 = i6;
                    } else if (layoutParams.height == -1) {
                    }
                    childAt.measure(MeasureSpec.makeMeasureSpec(i7, i8), MeasureSpec.makeMeasureSpec(i3, i5));
                    if (obj != null) {
                        i4 -= childAt.getMeasuredHeight();
                    } else if (z) {
                        measuredHeight -= childAt.getMeasuredWidth();
                    }
                }
            }
            measuredWidth = 0 + 1;
        }
        r0.mChildWidthMeasureSpec = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        r0.mChildHeightMeasureSpec = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        r0.mInLayout = true;
        populate();
        i3 = 0;
        r0.mInLayout = false;
        measuredWidth = getChildCount();
        while (i3 < measuredWidth) {
            View childAt2 = getChildAt(i3);
            if (childAt2.getVisibility() != 8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (layoutParams2 == null || !layoutParams2.a) {
                    childAt2.measure(MeasureSpec.makeMeasureSpec((int) (((float) measuredHeight) * layoutParams2.c), 1073741824), r0.mChildHeightMeasureSpec);
                }
            }
            i3++;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            recomputeScrollPosition(i, i3, this.mPageMargin, this.mPageMargin);
        }
    }

    private void recomputeScrollPosition(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.mItems.isEmpty()) {
            e infoForPosition = infoForPosition(this.mCurItem);
            i = (int) ((infoForPosition != null ? Math.min(infoForPosition.e, this.mLastOffset) : BitmapDescriptorFactory.HUE_RED) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (i != getScrollX()) {
                completeScroll(false);
                scrollTo(i, getScrollY());
            }
        } else if (this.mScroller.isFinished()) {
            scrollTo((int) ((((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))) * ((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3))), getScrollY());
        } else {
            this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        ViewPager viewPager = this;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i7 = paddingBottom;
        int i8 = 0;
        paddingBottom = paddingTop;
        paddingTop = paddingLeft;
        for (paddingLeft = 0; paddingLeft < childCount; paddingLeft++) {
            View childAt = getChildAt(paddingLeft);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int i9 = layoutParams.b & 7;
                    int i10 = layoutParams.b & 112;
                    if (i9 == 1) {
                        i9 = Math.max((i5 - childAt.getMeasuredWidth()) / 2, paddingTop);
                    } else if (i9 == 3) {
                        i9 = paddingTop;
                        paddingTop = childAt.getMeasuredWidth() + paddingTop;
                    } else if (i9 != 5) {
                        i9 = paddingTop;
                    } else {
                        i9 = (i5 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i10 == 16) {
                        i10 = Math.max((i6 - childAt.getMeasuredHeight()) / 2, paddingBottom);
                    } else if (i10 == 48) {
                        i10 = paddingBottom;
                        paddingBottom = childAt.getMeasuredHeight() + paddingBottom;
                    } else if (i10 != 80) {
                        i10 = paddingBottom;
                    } else {
                        i10 = (i6 - i7) - childAt.getMeasuredHeight();
                        i7 += childAt.getMeasuredHeight();
                    }
                    i9 += scrollX;
                    childAt.layout(i9, i10, childAt.getMeasuredWidth() + i9, i10 + childAt.getMeasuredHeight());
                    i8++;
                }
            }
        }
        i5 = (i5 - paddingTop) - paddingRight;
        for (paddingLeft = 0; paddingLeft < childCount; paddingLeft++) {
            View childAt2 = getChildAt(paddingLeft);
            if (childAt2.getVisibility() != 8) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams2.a) {
                    e infoForChild = infoForChild(childAt2);
                    if (infoForChild != null) {
                        float f = (float) i5;
                        int i11 = ((int) (infoForChild.e * f)) + paddingTop;
                        if (layoutParams2.d) {
                            layoutParams2.d = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((int) (f * layoutParams2.c), 1073741824), MeasureSpec.makeMeasureSpec((i6 - paddingBottom) - i7, 1073741824));
                        }
                        childAt2.layout(i11, paddingBottom, childAt2.getMeasuredWidth() + i11, childAt2.getMeasuredHeight() + paddingBottom);
                    }
                }
            }
        }
        viewPager.mTopPageBounds = paddingBottom;
        viewPager.mBottomPageBounds = i6 - i7;
        viewPager.mDecorChildCount = i8;
        if (viewPager.mFirstLayout) {
            z2 = false;
            scrollToItem(viewPager.mCurItem, false, 0, false);
        } else {
            z2 = false;
        }
        viewPager.mFirstLayout = z2;
    }

    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!pageScrolled(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        hs.e(this);
    }

    private boolean pageScrolled(int i) {
        if (this.mItems.size() != 0) {
            e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
            int clientWidth = getClientWidth();
            int i2 = this.mPageMargin + clientWidth;
            float f = (float) clientWidth;
            float f2 = ((float) this.mPageMargin) / f;
            int i3 = infoForCurrentScrollPosition.b;
            float f3 = ((((float) i) / f) - infoForCurrentScrollPosition.e) / (infoForCurrentScrollPosition.d + f2);
            int i4 = (int) (((float) i2) * f3);
            this.mCalledSuper = false;
            onPageScrolled(i3, f3, i4);
            if (this.mCalledSuper) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.mFirstLayout) {
            return false;
        } else {
            this.mCalledSuper = false;
            onPageScrolled(0, BitmapDescriptorFactory.HUE_RED, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    protected void onPageScrolled(int i, float f, int i2) {
        if (this.mDecorChildCount > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            int i3 = paddingRight;
            paddingRight = paddingLeft;
            for (paddingLeft = 0; paddingLeft < childCount; paddingLeft++) {
                View childAt = getChildAt(paddingLeft);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int i4 = layoutParams.b & 7;
                    if (i4 != 1) {
                        if (i4 == 3) {
                            i4 = childAt.getWidth() + paddingRight;
                        } else if (i4 != 5) {
                            i4 = paddingRight;
                        } else {
                            i4 = (width - i3) - childAt.getMeasuredWidth();
                            i3 += childAt.getMeasuredWidth();
                        }
                        paddingRight = (paddingRight + scrollX) - childAt.getLeft();
                        if (paddingRight != 0) {
                            childAt.offsetLeftAndRight(paddingRight);
                        }
                        paddingRight = i4;
                    } else {
                        i4 = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingRight);
                    }
                    int i5 = i4;
                    i4 = paddingRight;
                    paddingRight = i5;
                    paddingRight = (paddingRight + scrollX) - childAt.getLeft();
                    if (paddingRight != 0) {
                        childAt.offsetLeftAndRight(paddingRight);
                    }
                    paddingRight = i4;
                }
            }
        }
        dispatchOnPageScrolled(i, f, i2);
        if (this.mPageTransformer != null) {
            getScrollX();
            i = getChildCount();
            for (int i6 = 0; i6 < i; i6++) {
                View childAt2 = getChildAt(i6);
                if (!((LayoutParams) childAt2.getLayoutParams()).a) {
                    childAt2.getLeft();
                    getClientWidth();
                }
            }
        }
        this.mCalledSuper = true;
    }

    private void dispatchOnPageScrolled(int i, float f, int i2) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(i, f, i2);
        }
        if (this.mOnPageChangeListeners != null) {
            int size = this.mOnPageChangeListeners.size();
            for (int i3 = 0; i3 < size; i3++) {
                h hVar = (h) this.mOnPageChangeListeners.get(i3);
                if (hVar != null) {
                    hVar.onPageScrolled(i, f, i2);
                }
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    private void dispatchOnPageSelected(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(i);
        }
        if (this.mOnPageChangeListeners != null) {
            int size = this.mOnPageChangeListeners.size();
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = (h) this.mOnPageChangeListeners.get(i2);
                if (hVar != null) {
                    hVar.onPageSelected(i);
                }
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageSelected(i);
        }
    }

    private void dispatchOnScrollStateChanged(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(i);
        }
        if (this.mOnPageChangeListeners != null) {
            int size = this.mOnPageChangeListeners.size();
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = (h) this.mOnPageChangeListeners.get(i2);
                if (hVar != null) {
                    hVar.onPageScrollStateChanged(i);
                }
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    private void completeScroll(boolean z) {
        Object obj = this.mScrollState == 2 ? 1 : null;
        if (obj != null) {
            setScrollingCacheEnabled(false);
            if ((this.mScroller.isFinished() ^ 1) != 0) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        pageScrolled(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        Object obj2 = obj;
        for (int i = 0; i < this.mItems.size(); i++) {
            e eVar = (e) this.mItems.get(i);
            if (eVar.c) {
                eVar.c = false;
                obj2 = 1;
            }
        }
        if (obj2 != null) {
            if (z) {
                hs.a((View) this, this.mEndScrollRunnable);
                return;
            }
            this.mEndScrollRunnable.run();
        }
    }

    private boolean isGutterDrag(float f, float f2) {
        return (f < ((float) this.mGutterSize) && f2 > BitmapDescriptorFactory.HUE_RED) || (f > ((float) (getWidth() - this.mGutterSize)) && f2 < BitmapDescriptorFactory.HUE_RED);
    }

    private void enableLayers(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setLayerType(z ? this.mPageTransformerLayerType : 0, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ViewPager viewPager = this;
        MotionEvent motionEvent2 = motionEvent;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            resetTouch();
            return false;
        }
        if (action != 0) {
            if (viewPager.mIsBeingDragged) {
                return true;
            }
            if (viewPager.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            viewPager.mInitialMotionX = x;
            viewPager.mLastMotionX = x;
            x = motionEvent.getY();
            viewPager.mInitialMotionY = x;
            viewPager.mLastMotionY = x;
            viewPager.mActivePointerId = motionEvent2.getPointerId(0);
            viewPager.mIsUnableToDrag = false;
            viewPager.mIsScrollStarted = true;
            viewPager.mScroller.computeScrollOffset();
            if (viewPager.mScrollState != 2 || Math.abs(viewPager.mScroller.getFinalX() - viewPager.mScroller.getCurrX()) <= viewPager.mCloseEnough) {
                completeScroll(false);
                viewPager.mIsBeingDragged = false;
            } else {
                viewPager.mScroller.abortAnimation();
                viewPager.mPopulatePending = false;
                populate();
                viewPager.mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            action = viewPager.mActivePointerId;
            if (action != -1) {
                action = motionEvent2.findPointerIndex(action);
                float x2 = motionEvent2.getX(action);
                float f = x2 - viewPager.mLastMotionX;
                float abs = Math.abs(f);
                float y = motionEvent2.getY(action);
                float abs2 = Math.abs(y - viewPager.mInitialMotionY);
                if (f == BitmapDescriptorFactory.HUE_RED || isGutterDrag(viewPager.mLastMotionX, f) || !canScroll(this, false, (int) f, (int) x2, (int) y)) {
                    if (abs > ((float) viewPager.mTouchSlop) && abs * 0.5f > abs2) {
                        viewPager.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        viewPager.mLastMotionX = f > BitmapDescriptorFactory.HUE_RED ? viewPager.mInitialMotionX + ((float) viewPager.mTouchSlop) : viewPager.mInitialMotionX - ((float) viewPager.mTouchSlop);
                        viewPager.mLastMotionY = y;
                        setScrollingCacheEnabled(true);
                    } else if (abs2 > ((float) viewPager.mTouchSlop)) {
                        viewPager.mIsUnableToDrag = true;
                    }
                    if (viewPager.mIsBeingDragged && performDrag(x2)) {
                        hs.e(this);
                    }
                } else {
                    viewPager.mLastMotionX = x2;
                    viewPager.mLastMotionY = y;
                    viewPager.mIsUnableToDrag = true;
                    return false;
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(motionEvent);
        }
        if (viewPager.mVelocityTracker == null) {
            viewPager.mVelocityTracker = VelocityTracker.obtain();
        }
        viewPager.mVelocityTracker.addMovement(motionEvent2);
        return viewPager.mIsBeingDragged;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mFakeDragging) {
            return true;
        }
        boolean z = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || this.mAdapter == null || this.mAdapter.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        float x;
        int xVelocity;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                x = motionEvent.getX();
                this.mInitialMotionX = x;
                this.mLastMotionX = x;
                x = motionEvent.getY();
                this.mInitialMotionY = x;
                this.mLastMotionY = x;
                this.mActivePointerId = motionEvent.getPointerId(0);
                break;
            case 1:
                if (this.mIsBeingDragged) {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                    xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
                    this.mPopulatePending = true;
                    int clientWidth = getClientWidth();
                    int scrollX = getScrollX();
                    e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
                    float f = (float) clientWidth;
                    setCurrentItemInternal(determineTargetPage(infoForCurrentScrollPosition.b, ((((float) scrollX) / f) - infoForCurrentScrollPosition.e) / (infoForCurrentScrollPosition.d + (((float) this.mPageMargin) / f)), xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, xVelocity);
                    z = resetTouch();
                    break;
                }
                break;
            case 2:
                if (!this.mIsBeingDragged) {
                    xVelocity = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (xVelocity == -1) {
                        z = resetTouch();
                        break;
                    }
                    float x2 = motionEvent.getX(xVelocity);
                    float abs = Math.abs(x2 - this.mLastMotionX);
                    x = motionEvent.getY(xVelocity);
                    float abs2 = Math.abs(x - this.mLastMotionY);
                    if (abs > ((float) this.mTouchSlop) && abs > abs2) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        this.mLastMotionX = x2 - this.mInitialMotionX > BitmapDescriptorFactory.HUE_RED ? this.mInitialMotionX + ((float) this.mTouchSlop) : this.mInitialMotionX - ((float) this.mTouchSlop);
                        this.mLastMotionY = x;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.mIsBeingDragged) {
                    z = false | performDrag(motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)));
                    break;
                }
                break;
            case 3:
                if (this.mIsBeingDragged) {
                    scrollToItem(this.mCurItem, true, 0, false);
                    z = resetTouch();
                    break;
                }
                break;
            case 5:
                xVelocity = motionEvent.getActionIndex();
                this.mLastMotionX = motionEvent.getX(xVelocity);
                this.mActivePointerId = motionEvent.getPointerId(xVelocity);
                break;
            case 6:
                onSecondaryPointerUp(motionEvent);
                this.mLastMotionX = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
                break;
        }
        if (z) {
            hs.e(this);
        }
        return true;
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        return this.mLeftEdge.isFinished() || this.mRightEdge.isFinished();
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean performDrag(float f) {
        Object obj;
        Object obj2;
        float f2 = this.mLastMotionX - f;
        this.mLastMotionX = f;
        f = ((float) getScrollX()) + f2;
        f2 = (float) getClientWidth();
        float f3 = this.mFirstOffset * f2;
        float f4 = this.mLastOffset * f2;
        boolean z = false;
        e eVar = (e) this.mItems.get(0);
        e eVar2 = (e) this.mItems.get(this.mItems.size() - 1);
        if (eVar.b != 0) {
            f3 = eVar.e * f2;
            obj = null;
        } else {
            obj = 1;
        }
        if (eVar2.b != this.mAdapter.getCount() - 1) {
            f4 = eVar2.e * f2;
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (f < f3) {
            if (obj != null) {
                this.mLeftEdge.onPull(Math.abs(f3 - f) / f2);
                z = true;
            }
            f = f3;
        } else if (f > f4) {
            if (obj2 != null) {
                this.mRightEdge.onPull(Math.abs(f - f4) / f2);
                z = true;
            }
            f = f4;
        }
        int i = (int) f;
        this.mLastMotionX += f - ((float) i);
        scrollTo(i, getScrollY());
        pageScrolled(i);
        return z;
    }

    private e infoForCurrentScrollPosition() {
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? ((float) getScrollX()) / ((float) clientWidth) : BitmapDescriptorFactory.HUE_RED;
        float f = clientWidth > 0 ? ((float) this.mPageMargin) / ((float) clientWidth) : BitmapDescriptorFactory.HUE_RED;
        e eVar = null;
        int i = 0;
        int i2 = 1;
        int i3 = -1;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        while (i < this.mItems.size()) {
            e eVar2 = (e) this.mItems.get(i);
            if (i2 == 0) {
                i3++;
                if (eVar2.b != i3) {
                    eVar2 = this.mTempItem;
                    eVar2.e = (f2 + BitmapDescriptorFactory.HUE_RED) + f;
                    eVar2.b = i3;
                    eVar2.d = this.mAdapter.getPageWidth(eVar2.b);
                    i--;
                }
            }
            f2 = eVar2.e;
            float f3 = (eVar2.d + f2) + f;
            if (i2 == 0 && scrollX < f2) {
                return eVar;
            }
            if (scrollX >= f3) {
                i2 = this.mItems.size() - 1;
                if (i != i2) {
                    i3 = eVar2.b;
                    float f4 = eVar2.d;
                    i++;
                    eVar = eVar2;
                }
            }
            return eVar2;
        }
        return eVar;
    }

    private int determineTargetPage(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.mFlingDistance || Math.abs(i2) <= this.mMinimumVelocity) {
            i += (int) (f + (i >= this.mCurItem ? 0.4f : 0.6f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.mItems.size() <= 0) {
            return i;
        }
        return Math.max(((e) this.mItems.get(0)).b, Math.min(i, ((e) this.mItems.get(this.mItems.size() - 1)).b));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        int i = 0;
        if (overScrollMode == 0 || (overScrollMode == 1 && this.mAdapter != null && this.mAdapter.getCount() > 1)) {
            int height;
            int width;
            if (!this.mLeftEdge.isFinished()) {
                overScrollMode = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.mFirstOffset * ((float) width));
                this.mLeftEdge.setSize(height, width);
                i = 0 | this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(overScrollMode);
            }
            if (!this.mRightEdge.isFinished()) {
                overScrollMode = canvas.save();
                height = getWidth();
                width = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.mLastOffset + 1.0f)) * ((float) height));
                this.mRightEdge.setSize(width, height);
                i |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(overScrollMode);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (i != 0) {
            hs.e(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && r0.mMarginDrawable != null && r0.mItems.size() > 0 && r0.mAdapter != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f = (float) width;
            float f2 = ((float) r0.mPageMargin) / f;
            int i = 0;
            e eVar = (e) r0.mItems.get(0);
            float f3 = eVar.e;
            int size = r0.mItems.size();
            int i2 = eVar.b;
            int i3 = ((e) r0.mItems.get(size - 1)).b;
            while (i2 < i3) {
                float f4;
                float f5;
                while (i2 > eVar.b && i < size) {
                    i++;
                    eVar = (e) r0.mItems.get(i);
                }
                if (i2 == eVar.b) {
                    f3 = (eVar.e + eVar.d) * f;
                    f4 = (eVar.e + eVar.d) + f2;
                } else {
                    f4 = r0.mAdapter.getPageWidth(i2);
                    f4 = f3 + (f4 + f2);
                    f3 = (f3 + f4) * f;
                }
                if (((float) r0.mPageMargin) + f3 > ((float) scrollX)) {
                    f5 = f2;
                    r0.mMarginDrawable.setBounds(Math.round(f3), r0.mTopPageBounds, Math.round(((float) r0.mPageMargin) + f3), r0.mBottomPageBounds);
                    r0.mMarginDrawable.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    f5 = f2;
                }
                if (f3 <= ((float) (scrollX + width))) {
                    i2++;
                    f3 = f4;
                    f2 = f5;
                } else {
                    return;
                }
            }
        }
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = BitmapDescriptorFactory.HUE_RED;
        this.mInitialMotionX = BitmapDescriptorFactory.HUE_RED;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
        this.mVelocityTracker.addMovement(obtain);
        obtain.recycle();
        this.mFakeDragBeginTime = uptimeMillis;
        return true;
    }

    public void endFakeDrag() {
        if (this.mFakeDragging) {
            if (this.mAdapter != null) {
                VelocityTracker velocityTracker = this.mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
                this.mPopulatePending = true;
                int clientWidth = getClientWidth();
                int scrollX = getScrollX();
                e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
                setCurrentItemInternal(determineTargetPage(infoForCurrentScrollPosition.b, ((((float) scrollX) / ((float) clientWidth)) - infoForCurrentScrollPosition.e) / infoForCurrentScrollPosition.d, xVelocity, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, xVelocity);
            }
            endDrag();
            this.mFakeDragging = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float f) {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        } else if (this.mAdapter != null) {
            this.mLastMotionX += f;
            float scrollX = ((float) getScrollX()) - f;
            f = (float) getClientWidth();
            float f2 = this.mFirstOffset * f;
            float f3 = this.mLastOffset * f;
            e eVar = (e) this.mItems.get(0);
            e eVar2 = (e) this.mItems.get(this.mItems.size() - 1);
            if (eVar.b != 0) {
                f2 = eVar.e * f;
            }
            if (eVar2.b != this.mAdapter.getCount() - 1) {
                f3 = eVar2.e * f;
            }
            if (scrollX < f2) {
                scrollX = f2;
            } else if (scrollX > f3) {
                scrollX = f3;
            }
            int i = (int) scrollX;
            this.mLastMotionX += scrollX - ((float) i);
            scrollTo(i, getScrollY());
            pageScrolled(i);
            MotionEvent obtain = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, BitmapDescriptorFactory.HUE_RED, 0);
            this.mVelocityTracker.addMovement(obtain);
            obtain.recycle();
        }
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            actionIndex = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent.getX(actionIndex);
            this.mActivePointerId = motionEvent.getPointerId(actionIndex);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    public boolean canScrollHorizontally(int i) {
        if (this.mAdapter == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX > ((int) (((float) clientWidth) * this.mFirstOffset))) {
                return true;
            }
            return false;
        } else if (i <= 0 || scrollX >= ((int) (((float) clientWidth) * this.mLastOffset))) {
            return false;
        } else {
            return true;
        }
    }

    protected boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i4 = i2 + scrollX;
                if (i4 >= childAt.getLeft() && i4 < childAt.getRight()) {
                    int i5 = i3 + scrollY;
                    if (i5 >= childAt.getTop() && i5 < childAt.getBottom()) {
                        if (canScroll(childAt, true, i, i4 - childAt.getLeft(), i5 - childAt.getTop())) {
                            return true;
                        }
                    }
                }
            }
        }
        if (z && view.canScrollHorizontally(-i)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                switch (keyCode) {
                    case 21:
                        if (keyEvent.hasModifiers(2)) {
                            return pageLeft();
                        }
                        return arrowScroll(17);
                    case 22:
                        if (keyEvent.hasModifiers(2)) {
                            return pageRight();
                        }
                        return arrowScroll(66);
                }
            } else if (keyEvent.hasNoModifiers()) {
                return arrowScroll(2);
            } else {
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean arrowScroll(int i) {
        View findFocus = findFocus();
        boolean z = false;
        View view = null;
        if (findFocus != this) {
            if (findFocus != null) {
                Object obj;
                for (ViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        stringBuilder.append(" => ");
                        stringBuilder.append(parent2.getClass().getSimpleName());
                    }
                    String str = TAG;
                    StringBuilder stringBuilder2 = new StringBuilder("arrowScroll tried to find focus based on non-child current focused view ");
                    stringBuilder2.append(stringBuilder.toString());
                    Log.e(str, stringBuilder2.toString());
                }
            }
            view = findFocus;
        }
        findFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (findFocus != null && findFocus != view) {
            if (i == 17) {
                z = (view == null || getChildRectInPagerCoordinates(this.mTempRect, findFocus).left < getChildRectInPagerCoordinates(this.mTempRect, view).left) ? findFocus.requestFocus() : pageLeft();
            } else if (i == 66) {
                int i2 = getChildRectInPagerCoordinates(this.mTempRect, findFocus).left;
                int i3 = getChildRectInPagerCoordinates(this.mTempRect, view).left;
                if (view == null || i2 > i3) {
                    z = findFocus.requestFocus();
                }
            }
            if (z) {
                playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            }
            return z;
        } else if (i == 17 || i == 1) {
            z = pageLeft();
            if (z) {
                playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            }
            return z;
        } else if (i != 66) {
        }
        z = pageRight();
        if (z) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return z;
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewPager parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    boolean pageLeft() {
        if (this.mCurItem <= 0) {
            return false;
        }
        setCurrentItem(this.mCurItem - 1, true);
        return true;
    }

    boolean pageRight() {
        if (this.mAdapter == null || this.mCurItem >= this.mAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    e infoForChild = infoForChild(childAt);
                    if (infoForChild != null && infoForChild.b == this.mCurItem) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (!(((i2 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || arrayList == null)) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                e infoForChild = infoForChild(childAt);
                if (infoForChild != null && infoForChild.b == this.mCurItem) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int childCount = getChildCount();
        int i3 = -1;
        if ((i & 2) != 0) {
            i3 = childCount;
            childCount = 0;
            i2 = 1;
        } else {
            childCount--;
            i2 = -1;
        }
        while (childCount != i3) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() == 0) {
                e infoForChild = infoForChild(childAt);
                if (infoForChild != null && infoForChild.b == this.mCurItem && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            childCount += i2;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                e infoForChild = infoForChild(childAt);
                if (infoForChild != null && infoForChild.b == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
