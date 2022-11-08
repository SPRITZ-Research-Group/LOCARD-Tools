package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

public abstract class ViewGroupManager<T extends ViewGroup> extends BaseViewManager<T, h> implements e {
    private static WeakHashMap<View, Integer> mZIndexHash = new WeakHashMap();

    public h createShadowNodeInstance() {
        return new h();
    }

    public Class<? extends h> getShadowNodeClass() {
        return h.class;
    }

    public void updateExtraData(T t, Object extraData) {
    }

    public void addView(T parent, View child, int index) {
        parent.addView(child, index);
    }

    public void addViews(T parent, List<View> views) {
        int size = views.size();
        for (int i = 0; i < size; i++) {
            addView(parent, (View) views.get(i), i);
        }
    }

    public static void setViewZIndex(View view, int zIndex) {
        mZIndexHash.put(view, Integer.valueOf(zIndex));
    }

    @Nullable
    public static Integer getViewZIndex(View view) {
        return (Integer) mZIndexHash.get(view);
    }

    public int getChildCount(T parent) {
        return parent.getChildCount();
    }

    public View getChildAt(T parent, int index) {
        return parent.getChildAt(index);
    }

    public void removeViewAt(T parent, int index) {
        parent.removeViewAt(index);
    }

    public void removeView(T parent, View view) {
        for (int i = 0; i < getChildCount(parent); i++) {
            if (getChildAt(parent, i) == view) {
                removeViewAt(parent, i);
                return;
            }
        }
    }

    public void removeAllViews(T parent) {
        for (int i = getChildCount(parent) - 1; i >= 0; i--) {
            removeViewAt(parent, i);
        }
    }

    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public boolean shouldPromoteGrandchildren() {
        return false;
    }
}
