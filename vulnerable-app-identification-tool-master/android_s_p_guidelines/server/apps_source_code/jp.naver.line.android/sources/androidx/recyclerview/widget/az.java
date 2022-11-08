package androidx.recyclerview.widget;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import defpackage.fk;
import java.util.List;

public abstract class az<VH extends cb> {
    private boolean mHasStableIds = false;
    private final ba mObservable = new ba();

    public abstract int getItemCount();

    public long getItemId(int i) {
        return -1;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    }

    public abstract void onBindViewHolder(VH vh, int i);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    }

    public boolean onFailedToRecycleView(VH vh) {
        return false;
    }

    public void onViewAttachedToWindow(VH vh) {
    }

    public void onViewDetachedFromWindow(VH vh) {
    }

    public void onViewRecycled(VH vh) {
    }

    public void onBindViewHolder(VH vh, int i, List<Object> list) {
        onBindViewHolder(vh, i);
    }

    public final VH createViewHolder(ViewGroup viewGroup, int i) {
        try {
            fk.a("RV CreateView");
            VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
            if (onCreateViewHolder.itemView.getParent() == null) {
                onCreateViewHolder.mItemViewType = i;
                return onCreateViewHolder;
            }
            throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
        } finally {
            fk.a();
        }
    }

    public final void bindViewHolder(VH vh, int i) {
        vh.mPosition = i;
        if (hasStableIds()) {
            vh.mItemId = getItemId(i);
        }
        vh.setFlags(1, 519);
        fk.a("RV OnBindView");
        onBindViewHolder(vh, i, vh.getUnmodifiedPayloads());
        vh.clearPayload();
        LayoutParams layoutParams = vh.itemView.getLayoutParams();
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            ((RecyclerView.LayoutParams) layoutParams).e = true;
        }
        fk.a();
    }

    public void setHasStableIds(boolean z) {
        if (hasObservers()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.mHasStableIds = z;
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final boolean hasObservers() {
        return this.mObservable.a();
    }

    public void registerAdapterDataObserver(bb bbVar) {
        this.mObservable.registerObserver(bbVar);
    }

    public void unregisterAdapterDataObserver(bb bbVar) {
        this.mObservable.unregisterObserver(bbVar);
    }

    public final void notifyDataSetChanged() {
        this.mObservable.b();
    }

    public final void notifyItemChanged(int i) {
        this.mObservable.a(i, 1);
    }

    public final void notifyItemChanged(int i, Object obj) {
        this.mObservable.a(i, 1, obj);
    }

    public final void notifyItemRangeChanged(int i, int i2) {
        this.mObservable.a(i, i2);
    }

    public final void notifyItemRangeChanged(int i, int i2, Object obj) {
        this.mObservable.a(i, i2, obj);
    }

    public final void notifyItemInserted(int i) {
        this.mObservable.b(i, 1);
    }

    public final void notifyItemMoved(int i, int i2) {
        this.mObservable.d(i, i2);
    }

    public final void notifyItemRangeInserted(int i, int i2) {
        this.mObservable.b(i, i2);
    }

    public final void notifyItemRemoved(int i) {
        this.mObservable.c(i, 1);
    }

    public final void notifyItemRangeRemoved(int i, int i2) {
        this.mObservable.c(i, i2);
    }
}
