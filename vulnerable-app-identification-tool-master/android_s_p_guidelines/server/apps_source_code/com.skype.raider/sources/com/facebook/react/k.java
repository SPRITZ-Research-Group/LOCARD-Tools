package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

final class k {
    k() {
    }

    private static boolean b(View view) {
        if (view != null && view.isShown()) {
            return view.getGlobalVisibleRect(new Rect());
        }
        return false;
    }

    private static void a(Rect rectToClip, Rect clippingRect) {
        rectToClip.left = Math.max(rectToClip.left, clippingRect.left);
        rectToClip.top = Math.max(rectToClip.top, clippingRect.top);
        rectToClip.right = Math.min(rectToClip.right, clippingRect.right);
        rectToClip.bottom = Math.min(rectToClip.bottom, clippingRect.bottom);
    }

    private static Rect c(View view) {
        int[] screenLocation = new int[2];
        view.getLocationInWindow(screenLocation);
        int x = screenLocation[0];
        int y = screenLocation[1];
        return new Rect(x, y, view.getWidth() + x, view.getHeight() + y);
    }

    private static boolean a(View v, float alphaThreshold) {
        View current = v;
        boolean backgroundFound = false;
        while (current != null) {
            if (current.getBackground() != null) {
                backgroundFound = true;
            }
            if (backgroundFound && current.getAlpha() <= alphaThreshold) {
                return false;
            }
            current = current.getParent() instanceof View ? (View) current.getParent() : null;
        }
        return true;
    }

    private static int d(View v) {
        int maxZOrder = 0;
        for (View current = v; current != null; current = current.getParent() instanceof View ? (View) current.getParent() : null) {
            Integer zOrder = ViewGroupManager.getViewZIndex(current);
            if (zOrder != null && maxZOrder < zOrder.intValue()) {
                maxZOrder = zOrder.intValue();
            }
        }
        return maxZOrder;
    }

    private boolean a(View toCheck, ViewGroup rootView, boolean[] didVisitToCheckView, Rect clippingRect) {
        boolean isVisible = didVisitToCheckView[0];
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View current = rootView.getChildAt(i);
            if (didVisitToCheckView[0] && current.getBackground() != null && a(current, 0.94f) && d(current) >= d(toCheck)) {
                Rect currentRect = c(current);
                Rect toCheckRect = c(toCheck);
                toCheckRect.inset(-1, -1);
                a(currentRect, clippingRect);
                a(toCheckRect, new Rect(0, 0, Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels));
                if (currentRect.contains(toCheckRect)) {
                    return false;
                }
            }
            if (current.getId() == toCheck.getId()) {
                didVisitToCheckView[0] = true;
                isVisible = true;
            }
            if (current instanceof ViewGroup) {
                Rect newParentClipRect = c(rootView);
                a(newParentClipRect, clippingRect);
                isVisible = a(toCheck, (ViewGroup) current, didVisitToCheckView, newParentClipRect);
                if (didVisitToCheckView[0] && !isVisible) {
                    return false;
                }
            }
        }
        return isVisible;
    }

    private boolean a(View toCheck, ViewGroup rootView) {
        return a(toCheck, rootView, new boolean[]{false}, c(rootView));
    }

    private void a(View currentView, ArrayList<View> results) {
        if ((currentView instanceof ReactViewGroup) && ((ReactViewGroup) currentView).h()) {
            results.clear();
        }
        if (currentView.isFocusable() && currentView.getVisibility() == 0) {
            results.add(currentView);
        }
        if (currentView instanceof ViewGroup) {
            ViewGroup currentViewGroup = (ViewGroup) currentView;
            for (int i = 0; i < currentViewGroup.getChildCount(); i++) {
                if (currentView.getVisibility() == 0 && currentViewGroup.getDescendantFocusability() != 393216) {
                    a(currentViewGroup.getChildAt(i), (ArrayList) results);
                }
            }
        }
    }

    final boolean a(Activity activity, final int direction) {
        ViewGroup reactRootView;
        View decorView = activity.getWindow().getDecorView();
        loop0:
        while (true) {
            ViewGroup viewGroup = (ViewGroup) decorView;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= viewGroup.getChildCount()) {
                    reactRootView = null;
                    break loop0;
                }
                decorView = viewGroup.getChildAt(i2);
                if (decorView instanceof ReactViewGroup) {
                    reactRootView = (ReactViewGroup) decorView;
                    break loop0;
                } else if (decorView instanceof ViewGroup) {
                    break;
                } else {
                    i = i2 + 1;
                }
            }
        }
        if (reactRootView == null) {
            return false;
        }
        int i3;
        int viewIndex;
        View focusableView;
        View focused = activity.getCurrentFocus();
        Context applicationContext = activity.getApplicationContext();
        ArrayList allFocusableViews = new ArrayList();
        a((View) reactRootView, allFocusableViews);
        Collection arrayList = new ArrayList();
        Iterator it = allFocusableViews.iterator();
        while (it.hasNext()) {
            decorView = (View) it.next();
            if (!a(decorView, 0.0f)) {
                arrayList.add(decorView);
            }
        }
        allFocusableViews.removeAll(arrayList);
        final DisplayMetrics displayMetrics = applicationContext.getResources().getDisplayMetrics();
        Collections.sort(allFocusableViews, new Comparator<View>(this) {
            final /* synthetic */ k c;

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                View view = (View) obj2;
                Rect a = k.c((View) obj);
                Rect a2 = k.c(view);
                int i = (int) (24.0f * displayMetrics.density);
                int i2 = a.left / i;
                int i3 = a.top / i;
                int i4 = a2.left / i;
                int i5 = a2.top / i;
                if (direction == 130 || direction == 33) {
                    int i6 = i4;
                    i4 = i5;
                    i5 = i6;
                    int i7 = i2;
                    i2 = i3;
                    i3 = i7;
                }
                if (i3 < i5) {
                    return -1;
                }
                if (i3 != i5) {
                    return 1;
                }
                if (i2 >= i4) {
                    return i2 == i4 ? 0 : 1;
                } else {
                    return -1;
                }
            }
        });
        boolean reverseOrder = direction == 1 || direction == 17 || direction == 33;
        boolean focusNext = false;
        for (i3 = 0; i3 < allFocusableViews.size(); i3++) {
            if (reverseOrder) {
                viewIndex = (allFocusableViews.size() - 1) - i3;
            } else {
                viewIndex = i3;
            }
            focusableView = (View) allFocusableViews.get(viewIndex);
            if ((focusNext && b(focusableView) && a(focusableView, reactRootView)) || focused == null) {
                return focusableView.requestFocus();
            }
            if (focusableView == focused) {
                focusNext = true;
            }
        }
        for (i3 = 0; i3 < allFocusableViews.size(); i3++) {
            if (reverseOrder) {
                viewIndex = (allFocusableViews.size() - 1) - i3;
            } else {
                viewIndex = i3;
            }
            focusableView = (View) allFocusableViews.get(viewIndex);
            if (b(focusableView) && a(focusableView, reactRootView)) {
                return focusableView.requestFocus();
            }
        }
        return false;
    }
}
