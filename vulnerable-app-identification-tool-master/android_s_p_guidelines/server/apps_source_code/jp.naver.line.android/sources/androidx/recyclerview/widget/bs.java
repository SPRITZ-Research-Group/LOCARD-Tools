package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import defpackage.hs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class bs {
    final ArrayList<cb> a = new ArrayList();
    ArrayList<cb> b = null;
    final ArrayList<cb> c = new ArrayList();
    int d = 2;
    bq e;
    final /* synthetic */ RecyclerView f;
    private final List<cb> g = Collections.unmodifiableList(this.a);
    private int h = 2;
    private bz i;

    public bs(RecyclerView recyclerView) {
        this.f = recyclerView;
    }

    public final void a() {
        this.a.clear();
        d();
    }

    public final void a(int i) {
        this.h = i;
        b();
    }

    final void b() {
        this.d = this.h + (this.f.mLayout != null ? this.f.mLayout.x : 0);
        for (int size = this.c.size() - 1; size >= 0 && this.c.size() > this.d; size--) {
            e(size);
        }
    }

    public final List<cb> c() {
        return this.g;
    }

    private boolean c(cb cbVar) {
        if (cbVar.isRemoved()) {
            return this.f.mState.g;
        }
        if (cbVar.mPosition < 0 || cbVar.mPosition >= this.f.mAdapter.getItemCount()) {
            StringBuilder stringBuilder = new StringBuilder("Inconsistency detected. Invalid view holder adapter position");
            stringBuilder.append(cbVar);
            stringBuilder.append(this.f.exceptionLabel());
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else if (!this.f.mState.g && this.f.mAdapter.getItemViewType(cbVar.mPosition) != cbVar.getItemViewType()) {
            return false;
        } else {
            if (!this.f.mAdapter.hasStableIds() || cbVar.getItemId() == this.f.mAdapter.getItemId(cbVar.mPosition)) {
                return true;
            }
            return false;
        }
    }

    private boolean a(cb cbVar, int i, int i2, long j) {
        cbVar.mOwnerRecyclerView = this.f;
        int itemViewType = cbVar.getItemViewType();
        long nanoTime = this.f.getNanoTime();
        if (j != Long.MAX_VALUE && !this.e.b(itemViewType, nanoTime, j)) {
            return false;
        }
        this.f.mAdapter.bindViewHolder(cbVar, i);
        this.e.b(cbVar.getItemViewType(), this.f.getNanoTime() - nanoTime);
        if (this.f.isAccessibilityEnabled()) {
            View view = cbVar.itemView;
            if (hs.f(view) == 0) {
                hs.a(view, 1);
            }
            if (!hs.c(view)) {
                cbVar.addFlags(16384);
                hs.a(view, this.f.mAccessibilityDelegate.b);
            }
        }
        if (this.f.mState.g) {
            cbVar.mPreLayoutPosition = i2;
        }
        return true;
    }

    public final int b(int i) {
        if (i < 0 || i >= this.f.mState.b()) {
            StringBuilder stringBuilder = new StringBuilder("invalid position ");
            stringBuilder.append(i);
            stringBuilder.append(". State item count is ");
            stringBuilder.append(this.f.mState.b());
            stringBuilder.append(this.f.exceptionLabel());
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else if (this.f.mState.g) {
            return this.f.mAdapterHelper.b(i);
        } else {
            return i;
        }
    }

    public final View c(int i) {
        return d(i);
    }

    final View d(int i) {
        return a(i, Long.MAX_VALUE).itemView;
    }

    final cb a(int i, long j) {
        bs bsVar = this;
        int i2 = i;
        StringBuilder stringBuilder;
        if (i2 < 0 || i2 >= bsVar.f.mState.b()) {
            stringBuilder = new StringBuilder("Invalid item position ");
            stringBuilder.append(i2);
            stringBuilder.append("(");
            stringBuilder.append(i2);
            stringBuilder.append("). Item count:");
            stringBuilder.append(bsVar.f.mState.b());
            stringBuilder.append(bsVar.f.exceptionLabel());
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
        cb f;
        Object obj;
        int b;
        cb cbVar;
        Object obj2;
        boolean a;
        LayoutParams layoutParams;
        RecyclerView.LayoutParams layoutParams2;
        boolean z = true;
        if (bsVar.f.mState.g) {
            f = f(i);
            if (f != null) {
                obj = 1;
                if (f == null) {
                    f = g(i);
                    if (f != null) {
                        if (c(f)) {
                            f.addFlags(4);
                            if (f.isScrap()) {
                                bsVar.f.removeDetachedView(f.itemView, false);
                                f.unScrap();
                            } else if (f.wasReturnedFromScrap()) {
                                f.clearReturnedFromScrapFlag();
                            }
                            a(f);
                            f = null;
                        } else {
                            obj = 1;
                        }
                    }
                }
                if (f == null) {
                    b = bsVar.f.mAdapterHelper.b(i2);
                    if (b >= 0 || b >= bsVar.f.mAdapter.getItemCount()) {
                        stringBuilder = new StringBuilder("Inconsistency detected. Invalid item position ");
                        stringBuilder.append(i2);
                        stringBuilder.append("(offset:");
                        stringBuilder.append(b);
                        stringBuilder.append(").state:");
                        stringBuilder.append(bsVar.f.mState.b());
                        stringBuilder.append(bsVar.f.exceptionLabel());
                        throw new IndexOutOfBoundsException(stringBuilder.toString());
                    }
                    int itemViewType = bsVar.f.mAdapter.getItemViewType(b);
                    if (bsVar.f.mAdapter.hasStableIds()) {
                        f = a(bsVar.f.mAdapter.getItemId(b), itemViewType);
                        if (f != null) {
                            f.mPosition = b;
                            obj = 1;
                        }
                    }
                    if (f == null && bsVar.i != null) {
                        View a2 = bsVar.i.a();
                        if (a2 != null) {
                            f = bsVar.f.getChildViewHolder(a2);
                            if (f == null) {
                                stringBuilder = new StringBuilder("getViewForPositionAndType returned a view which does not have a ViewHolder");
                                stringBuilder.append(bsVar.f.exceptionLabel());
                                throw new IllegalArgumentException(stringBuilder.toString());
                            } else if (f.shouldIgnore()) {
                                stringBuilder = new StringBuilder("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                                stringBuilder.append(bsVar.f.exceptionLabel());
                                throw new IllegalArgumentException(stringBuilder.toString());
                            }
                        }
                    }
                    if (f == null) {
                        f = e().a(itemViewType);
                        if (f != null) {
                            f.resetInternal();
                            if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                                d(f);
                            }
                        }
                    }
                    if (f == null) {
                        long nanoTime = bsVar.f.getNanoTime();
                        if (j != Long.MAX_VALUE && !bsVar.e.a(itemViewType, nanoTime, j)) {
                            return null;
                        }
                        f = bsVar.f.mAdapter.createViewHolder(bsVar.f, itemViewType);
                        if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                            RecyclerView findNestedRecyclerView = RecyclerView.findNestedRecyclerView(f.itemView);
                            if (findNestedRecyclerView != null) {
                                f.mNestedRecyclerView = new WeakReference(findNestedRecyclerView);
                            }
                        }
                        bsVar.e.a(itemViewType, bsVar.f.getNanoTime() - nanoTime);
                    }
                }
                cbVar = f;
                obj2 = obj;
                if (!(obj2 == null || bsVar.f.mState.g || !cbVar.hasAnyOfTheFlags(8192))) {
                    cbVar.setFlags(0, 8192);
                    if (bsVar.f.mState.j) {
                        be.e(cbVar);
                        cbVar.getUnmodifiedPayloads();
                        bsVar.f.recordAnimationInfoIfBouncedHiddenView(cbVar, be.d(cbVar));
                    }
                }
                if (!bsVar.f.mState.g && cbVar.isBound()) {
                    cbVar.mPreLayoutPosition = i2;
                } else if (!cbVar.isBound() || cbVar.needsUpdate() || cbVar.isInvalid()) {
                    a = a(cbVar, bsVar.f.mAdapterHelper.b(i2), i, j);
                    layoutParams = cbVar.itemView.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams2 = (RecyclerView.LayoutParams) bsVar.f.generateDefaultLayoutParams();
                        cbVar.itemView.setLayoutParams(layoutParams2);
                    } else if (bsVar.f.checkLayoutParams(layoutParams)) {
                        layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                    } else {
                        layoutParams2 = (RecyclerView.LayoutParams) bsVar.f.generateLayoutParams(layoutParams);
                        cbVar.itemView.setLayoutParams(layoutParams2);
                    }
                    layoutParams2.c = cbVar;
                    if (obj2 == null || !a) {
                        z = false;
                    }
                    layoutParams2.f = z;
                    return cbVar;
                }
                a = false;
                layoutParams = cbVar.itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams2 = (RecyclerView.LayoutParams) bsVar.f.generateDefaultLayoutParams();
                    cbVar.itemView.setLayoutParams(layoutParams2);
                } else if (bsVar.f.checkLayoutParams(layoutParams)) {
                    layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                } else {
                    layoutParams2 = (RecyclerView.LayoutParams) bsVar.f.generateLayoutParams(layoutParams);
                    cbVar.itemView.setLayoutParams(layoutParams2);
                }
                layoutParams2.c = cbVar;
                z = false;
                layoutParams2.f = z;
                return cbVar;
            }
        }
        f = null;
        obj = null;
        if (f == null) {
            f = g(i);
            if (f != null) {
                if (c(f)) {
                    obj = 1;
                } else {
                    f.addFlags(4);
                    if (f.isScrap()) {
                        bsVar.f.removeDetachedView(f.itemView, false);
                        f.unScrap();
                    } else if (f.wasReturnedFromScrap()) {
                        f.clearReturnedFromScrapFlag();
                    }
                    a(f);
                    f = null;
                }
            }
        }
        if (f == null) {
            b = bsVar.f.mAdapterHelper.b(i2);
            if (b >= 0) {
            }
            stringBuilder = new StringBuilder("Inconsistency detected. Invalid item position ");
            stringBuilder.append(i2);
            stringBuilder.append("(offset:");
            stringBuilder.append(b);
            stringBuilder.append(").state:");
            stringBuilder.append(bsVar.f.mState.b());
            stringBuilder.append(bsVar.f.exceptionLabel());
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
        cbVar = f;
        obj2 = obj;
        cbVar.setFlags(0, 8192);
        if (bsVar.f.mState.j) {
            be.e(cbVar);
            cbVar.getUnmodifiedPayloads();
            bsVar.f.recordAnimationInfoIfBouncedHiddenView(cbVar, be.d(cbVar));
        }
        if (!bsVar.f.mState.g) {
        }
        a = a(cbVar, bsVar.f.mAdapterHelper.b(i2), i, j);
        layoutParams = cbVar.itemView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams2 = (RecyclerView.LayoutParams) bsVar.f.generateDefaultLayoutParams();
            cbVar.itemView.setLayoutParams(layoutParams2);
        } else if (bsVar.f.checkLayoutParams(layoutParams)) {
            layoutParams2 = (RecyclerView.LayoutParams) bsVar.f.generateLayoutParams(layoutParams);
            cbVar.itemView.setLayoutParams(layoutParams2);
        } else {
            layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        }
        layoutParams2.c = cbVar;
        z = false;
        layoutParams2.f = z;
        return cbVar;
    }

    private void d(cb cbVar) {
        if (cbVar.itemView instanceof ViewGroup) {
            a((ViewGroup) cbVar.itemView, false);
        }
    }

    private void a(ViewGroup viewGroup, boolean z) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, true);
            }
        }
        if (!z) {
            return;
        }
        if (viewGroup.getVisibility() == 4) {
            viewGroup.setVisibility(0);
            viewGroup.setVisibility(4);
            return;
        }
        int visibility = viewGroup.getVisibility();
        viewGroup.setVisibility(4);
        viewGroup.setVisibility(visibility);
    }

    public final void a(View view) {
        cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isTmpDetached()) {
            this.f.removeDetachedView(view, false);
        }
        if (childViewHolderInt.isScrap()) {
            childViewHolderInt.unScrap();
        } else if (childViewHolderInt.wasReturnedFromScrap()) {
            childViewHolderInt.clearReturnedFromScrapFlag();
        }
        a(childViewHolderInt);
    }

    final void d() {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            e(size);
        }
        this.c.clear();
        if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
            this.f.mPrefetchRegistry.a();
        }
    }

    final void e(int i) {
        a((cb) this.c.get(i), true);
        this.c.remove(i);
    }

    final void a(cb cbVar) {
        boolean z = false;
        if (cbVar.isScrap() || cbVar.itemView.getParent() != null) {
            StringBuilder stringBuilder = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:");
            stringBuilder.append(cbVar.isScrap());
            stringBuilder.append(" isAttached:");
            if (cbVar.itemView.getParent() != null) {
                z = true;
            }
            stringBuilder.append(z);
            stringBuilder.append(this.f.exceptionLabel());
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (cbVar.isTmpDetached()) {
            StringBuilder stringBuilder2 = new StringBuilder("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
            stringBuilder2.append(cbVar);
            stringBuilder2.append(this.f.exceptionLabel());
            throw new IllegalArgumentException(stringBuilder2.toString());
        } else if (cbVar.shouldIgnore()) {
            StringBuilder stringBuilder3 = new StringBuilder("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            stringBuilder3.append(this.f.exceptionLabel());
            throw new IllegalArgumentException(stringBuilder3.toString());
        } else {
            boolean doesTransientStatePreventRecycling = cbVar.doesTransientStatePreventRecycling();
            Object obj = (this.f.mAdapter != null && doesTransientStatePreventRecycling && this.f.mAdapter.onFailedToRecycleView(cbVar)) ? 1 : null;
            if (obj != null || cbVar.isRecyclable()) {
                if (this.d <= 0 || cbVar.hasAnyOfTheFlags(526)) {
                    obj = null;
                } else {
                    int size = this.c.size();
                    if (size >= this.d && size > 0) {
                        e(0);
                        size--;
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK && size > 0 && !this.f.mPrefetchRegistry.a(cbVar.mPosition)) {
                        size--;
                        while (size >= 0) {
                            if (!this.f.mPrefetchRegistry.a(((cb) this.c.get(size)).mPosition)) {
                                break;
                            }
                            size--;
                        }
                        size++;
                    }
                    this.c.add(size, cbVar);
                    obj = 1;
                }
                if (obj == null) {
                    a(cbVar, true);
                    z = true;
                }
            } else {
                obj = null;
            }
            this.f.mViewInfoStore.f(cbVar);
            if (obj == null && !r1 && doesTransientStatePreventRecycling) {
                cbVar.mOwnerRecyclerView = null;
            }
        }
    }

    final void a(cb cbVar, boolean z) {
        RecyclerView.clearNestedRecyclerViewIfNotNested(cbVar);
        if (cbVar.hasAnyOfTheFlags(16384)) {
            cbVar.setFlags(0, 16384);
            hs.a(cbVar.itemView, null);
        }
        if (z) {
            e(cbVar);
        }
        cbVar.mOwnerRecyclerView = null;
        e().a(cbVar);
    }

    final void b(View view) {
        cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.mScrapContainer = null;
        childViewHolderInt.mInChangeScrap = false;
        childViewHolderInt.clearReturnedFromScrapFlag();
        a(childViewHolderInt);
    }

    final void c(View view) {
        cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !this.f.canReuseUpdatedViewHolder(childViewHolderInt)) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            childViewHolderInt.setScrapContainer(this, true);
            this.b.add(childViewHolderInt);
        } else if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.f.mAdapter.hasStableIds()) {
            childViewHolderInt.setScrapContainer(this, false);
            this.a.add(childViewHolderInt);
        } else {
            StringBuilder stringBuilder = new StringBuilder("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
            stringBuilder.append(this.f.exceptionLabel());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    final void b(cb cbVar) {
        if (cbVar.mInChangeScrap) {
            this.b.remove(cbVar);
        } else {
            this.a.remove(cbVar);
        }
        cbVar.mScrapContainer = null;
        cbVar.mInChangeScrap = false;
        cbVar.clearReturnedFromScrapFlag();
    }

    private cb f(int i) {
        if (this.b != null) {
            int size = this.b.size();
            if (size != 0) {
                int i2 = 0;
                int i3 = 0;
                while (i3 < size) {
                    cb cbVar = (cb) this.b.get(i3);
                    if (cbVar.wasReturnedFromScrap() || cbVar.getLayoutPosition() != i) {
                        i3++;
                    } else {
                        cbVar.addFlags(32);
                        return cbVar;
                    }
                }
                if (this.f.mAdapter.hasStableIds()) {
                    i = this.f.mAdapterHelper.a(i, 0);
                    if (i > 0 && i < this.f.mAdapter.getItemCount()) {
                        long itemId = this.f.mAdapter.getItemId(i);
                        while (i2 < size) {
                            cb cbVar2 = (cb) this.b.get(i2);
                            if (cbVar2.wasReturnedFromScrap() || cbVar2.getItemId() != itemId) {
                                i2++;
                            } else {
                                cbVar2.addFlags(32);
                                return cbVar2;
                            }
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }

    private cb g(int i) {
        View view;
        int size = this.a.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            cb cbVar = (cb) this.a.get(i3);
            if (cbVar.wasReturnedFromScrap() || cbVar.getLayoutPosition() != i || cbVar.isInvalid() || (!this.f.mState.g && cbVar.isRemoved())) {
                i3++;
            } else {
                cbVar.addFlags(32);
                return cbVar;
            }
        }
        j jVar = this.f.mChildHelper;
        i3 = jVar.c.size();
        for (int i4 = 0; i4 < i3; i4++) {
            view = (View) jVar.c.get(i4);
            cb b = jVar.a.b(view);
            if (b.getLayoutPosition() == i && !b.isInvalid() && !b.isRemoved()) {
                break;
            }
        }
        view = null;
        if (view != null) {
            cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            this.f.mChildHelper.f(view);
            size = this.f.mChildHelper.c(view);
            if (size != -1) {
                this.f.mChildHelper.d(size);
                c(view);
                childViewHolderInt.addFlags(8224);
                return childViewHolderInt;
            }
            StringBuilder stringBuilder = new StringBuilder("layout index should not be -1 after unhiding a view:");
            stringBuilder.append(childViewHolderInt);
            stringBuilder.append(this.f.exceptionLabel());
            throw new IllegalStateException(stringBuilder.toString());
        }
        size = this.c.size();
        while (i2 < size) {
            cb cbVar2 = (cb) this.c.get(i2);
            if (cbVar2.isInvalid() || cbVar2.getLayoutPosition() != i) {
                i2++;
            } else {
                this.c.remove(i2);
                return cbVar2;
            }
        }
        return null;
    }

    private cb a(long j, int i) {
        int size;
        for (size = this.a.size() - 1; size >= 0; size--) {
            cb cbVar = (cb) this.a.get(size);
            if (cbVar.getItemId() == j && !cbVar.wasReturnedFromScrap()) {
                if (i == cbVar.getItemViewType()) {
                    cbVar.addFlags(32);
                    if (cbVar.isRemoved() && !this.f.mState.g) {
                        cbVar.setFlags(2, 14);
                    }
                    return cbVar;
                }
                this.a.remove(size);
                this.f.removeDetachedView(cbVar.itemView, false);
                b(cbVar.itemView);
            }
        }
        size = this.c.size();
        while (true) {
            size--;
            if (size < 0) {
                return null;
            }
            cb cbVar2 = (cb) this.c.get(size);
            if (cbVar2.getItemId() == j) {
                if (i == cbVar2.getItemViewType()) {
                    this.c.remove(size);
                    return cbVar2;
                }
                e(size);
                return null;
            }
        }
    }

    private void e(cb cbVar) {
        if (this.f.mRecyclerListener != null) {
            this.f.mRecyclerListener.a(cbVar);
        }
        if (this.f.mAdapter != null) {
            this.f.mAdapter.onViewRecycled(cbVar);
        }
        if (this.f.mState != null) {
            this.f.mViewInfoStore.f(cbVar);
        }
    }

    final void a(bz bzVar) {
        this.i = bzVar;
    }

    final bq e() {
        if (this.e == null) {
            this.e = new bq();
        }
        return this.e;
    }
}
