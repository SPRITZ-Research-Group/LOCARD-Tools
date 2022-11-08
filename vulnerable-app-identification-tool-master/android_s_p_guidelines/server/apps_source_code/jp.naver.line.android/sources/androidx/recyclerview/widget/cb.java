package androidx.recyclerview.widget;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import defpackage.hs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class cb {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    static final int FLAG_BOUND = 1;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_MOVED = 2048;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    public final View itemView;
    int mFlags;
    boolean mInChangeScrap = false;
    private int mIsRecyclableCount = 0;
    long mItemId = -1;
    int mItemViewType = -1;
    WeakReference<RecyclerView> mNestedRecyclerView;
    int mOldPosition = -1;
    RecyclerView mOwnerRecyclerView;
    List<Object> mPayloads = null;
    int mPendingAccessibilityState = -1;
    int mPosition = -1;
    int mPreLayoutPosition = -1;
    bs mScrapContainer = null;
    cb mShadowedHolder = null;
    cb mShadowingHolder = null;
    List<Object> mUnmodifiedPayloads = null;
    private int mWasImportantForAccessibilityBeforeHidden = 0;

    public cb(View view) {
        if (view != null) {
            this.itemView = view;
            return;
        }
        throw new IllegalArgumentException("itemView may not be null");
    }

    void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
        addFlags(8);
        offsetPosition(i2, z);
        this.mPosition = i;
    }

    void offsetPosition(int i, boolean z) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (z) {
            this.mPreLayoutPosition += i;
        }
        this.mPosition += i;
        if (this.itemView.getLayoutParams() != null) {
            ((LayoutParams) this.itemView.getLayoutParams()).e = true;
        }
    }

    void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }

    void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }

    boolean shouldIgnore() {
        return (this.mFlags & 128) != 0;
    }

    @Deprecated
    public final int getPosition() {
        return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
    }

    public final int getLayoutPosition() {
        return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
    }

    public final int getAdapterPosition() {
        if (this.mOwnerRecyclerView == null) {
            return -1;
        }
        return this.mOwnerRecyclerView.getAdapterPositionFor(this);
    }

    public final int getOldPosition() {
        return this.mOldPosition;
    }

    public final long getItemId() {
        return this.mItemId;
    }

    public final int getItemViewType() {
        return this.mItemViewType;
    }

    boolean isScrap() {
        return this.mScrapContainer != null;
    }

    void unScrap() {
        this.mScrapContainer.b(this);
    }

    boolean wasReturnedFromScrap() {
        return (this.mFlags & 32) != 0;
    }

    void clearReturnedFromScrapFlag() {
        this.mFlags &= -33;
    }

    void clearTmpDetachFlag() {
        this.mFlags &= -257;
    }

    void stopIgnoring() {
        this.mFlags &= -129;
    }

    void setScrapContainer(bs bsVar, boolean z) {
        this.mScrapContainer = bsVar;
        this.mInChangeScrap = z;
    }

    boolean isInvalid() {
        return (this.mFlags & 4) != 0;
    }

    boolean needsUpdate() {
        return (this.mFlags & 2) != 0;
    }

    boolean isBound() {
        return (this.mFlags & 1) != 0;
    }

    boolean isRemoved() {
        return (this.mFlags & 8) != 0;
    }

    boolean hasAnyOfTheFlags(int i) {
        return (i & this.mFlags) != 0;
    }

    boolean isTmpDetached() {
        return (this.mFlags & 256) != 0;
    }

    boolean isAdapterPositionUnknown() {
        return (this.mFlags & 512) != 0 || isInvalid();
    }

    void setFlags(int i, int i2) {
        this.mFlags = (i & i2) | (this.mFlags & (i2 ^ -1));
    }

    void addFlags(int i) {
        this.mFlags = i | this.mFlags;
    }

    void addChangePayload(Object obj) {
        if (obj == null) {
            addFlags(1024);
            return;
        }
        if ((1024 & this.mFlags) == 0) {
            createPayloadsIfNeeded();
            this.mPayloads.add(obj);
        }
    }

    private void createPayloadsIfNeeded() {
        if (this.mPayloads == null) {
            this.mPayloads = new ArrayList();
            this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
        }
    }

    void clearPayload() {
        if (this.mPayloads != null) {
            this.mPayloads.clear();
        }
        this.mFlags &= -1025;
    }

    List<Object> getUnmodifiedPayloads() {
        if ((this.mFlags & 1024) != 0) {
            return FULLUPDATE_PAYLOADS;
        }
        if (this.mPayloads == null || this.mPayloads.size() == 0) {
            return FULLUPDATE_PAYLOADS;
        }
        return this.mUnmodifiedPayloads;
    }

    void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        this.mPendingAccessibilityState = -1;
        RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }

    void onEnteredHiddenState(RecyclerView recyclerView) {
        if (this.mPendingAccessibilityState != -1) {
            this.mWasImportantForAccessibilityBeforeHidden = this.mPendingAccessibilityState;
        } else {
            this.mWasImportantForAccessibilityBeforeHidden = hs.f(this.itemView);
        }
        recyclerView.setChildImportantForAccessibilityInternal(this, 4);
    }

    void onLeftHiddenState(RecyclerView recyclerView) {
        recyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ViewHolder{");
        stringBuilder.append(Integer.toHexString(hashCode()));
        stringBuilder.append(" position=");
        stringBuilder.append(this.mPosition);
        stringBuilder.append(" id=");
        stringBuilder.append(this.mItemId);
        stringBuilder.append(", oldPos=");
        stringBuilder.append(this.mOldPosition);
        stringBuilder.append(", pLpos:");
        stringBuilder.append(this.mPreLayoutPosition);
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder.toString());
        if (isScrap()) {
            stringBuilder2.append(" scrap ");
            stringBuilder2.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
        }
        if (isInvalid()) {
            stringBuilder2.append(" invalid");
        }
        if (!isBound()) {
            stringBuilder2.append(" unbound");
        }
        if (needsUpdate()) {
            stringBuilder2.append(" update");
        }
        if (isRemoved()) {
            stringBuilder2.append(" removed");
        }
        if (shouldIgnore()) {
            stringBuilder2.append(" ignored");
        }
        if (isTmpDetached()) {
            stringBuilder2.append(" tmpDetached");
        }
        if (!isRecyclable()) {
            stringBuilder = new StringBuilder(" not recyclable(");
            stringBuilder.append(this.mIsRecyclableCount);
            stringBuilder.append(")");
            stringBuilder2.append(stringBuilder.toString());
        }
        if (isAdapterPositionUnknown()) {
            stringBuilder2.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            stringBuilder2.append(" no parent");
        }
        stringBuilder2.append("}");
        return stringBuilder2.toString();
    }

    public final void setIsRecyclable(boolean z) {
        this.mIsRecyclableCount = z ? this.mIsRecyclableCount - 1 : this.mIsRecyclableCount + 1;
        if (this.mIsRecyclableCount < 0) {
            this.mIsRecyclableCount = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ".concat(String.valueOf(this)));
        } else if (z || this.mIsRecyclableCount != 1) {
            if (z && this.mIsRecyclableCount == 0) {
                this.mFlags &= -17;
            }
        } else {
            this.mFlags |= 16;
        }
    }

    public final boolean isRecyclable() {
        return (this.mFlags & 16) == 0 && !hs.d(this.itemView);
    }

    boolean shouldBeKeptAsChild() {
        return (this.mFlags & 16) != 0;
    }

    boolean doesTransientStatePreventRecycling() {
        return (this.mFlags & 16) == 0 && hs.d(this.itemView);
    }

    boolean isUpdated() {
        return (this.mFlags & 2) != 0;
    }
}
