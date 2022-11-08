package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzly;

class BaseAdView extends ViewGroup {
    protected final zzly zzut;

    public BaseAdView(Context context, int i) {
        super(context);
        this.zzut = new zzly(this, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.zzut = new zzly(this, attributeSet, false, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.zzut = new zzly(this, attributeSet, false, i2);
    }

    public void destroy() {
        this.zzut.destroy();
    }

    public AdListener getAdListener() {
        return this.zzut.getAdListener();
    }

    public AdSize getAdSize() {
        return this.zzut.getAdSize();
    }

    public String getAdUnitId() {
        return this.zzut.getAdUnitId();
    }

    public String getMediationAdapterClassName() {
        return this.zzut.getMediationAdapterClassName();
    }

    public boolean isLoading() {
        return this.zzut.isLoading();
    }

    public void loadAd(AdRequest adRequest) {
        this.zzut.zza(adRequest.zzay());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            i3 = ((i3 - i) - measuredWidth) / 2;
            i4 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i3, i4, measuredWidth + i3, measuredHeight + i4);
        }
    }

    protected void onMeasure(int i, int i2) {
        int heightInPixels;
        int i3 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize = null;
            try {
                adSize = getAdSize();
            } catch (Throwable e) {
                zzane.zzb("Unable to retrieve ad size.", e);
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                heightInPixels = adSize.getHeightInPixels(context);
                i3 = widthInPixels;
            } else {
                heightInPixels = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            i3 = childAt.getMeasuredWidth();
            heightInPixels = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(i3, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(heightInPixels, getSuggestedMinimumHeight()), i2));
    }

    public void pause() {
        this.zzut.pause();
    }

    public void resume() {
        this.zzut.resume();
    }

    public void setAdListener(AdListener adListener) {
        this.zzut.setAdListener(adListener);
        if (adListener == null) {
            this.zzut.zza(null);
            this.zzut.setAppEventListener(null);
            return;
        }
        if (adListener instanceof zzjd) {
            this.zzut.zza((zzjd) adListener);
        }
        if (adListener instanceof AppEventListener) {
            this.zzut.setAppEventListener((AppEventListener) adListener);
        }
    }

    public void setAdSize(AdSize adSize) {
        this.zzut.setAdSizes(adSize);
    }

    public void setAdUnitId(String str) {
        this.zzut.setAdUnitId(str);
    }
}
