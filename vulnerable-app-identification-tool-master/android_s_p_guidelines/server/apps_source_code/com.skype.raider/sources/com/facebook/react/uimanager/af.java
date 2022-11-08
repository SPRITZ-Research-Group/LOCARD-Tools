package com.facebook.react.uimanager;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.n;
import com.facebook.react.d.c;

public final class af {
    private static final float[] a = new float[2];
    private static final PointF b = new PointF();
    private static final float[] c = new float[2];
    private static final Matrix d = new Matrix();

    public static int a(float eventX, float eventY, ViewGroup viewGroup) {
        return a(eventX, eventY, viewGroup, a);
    }

    public static int a(float eventX, float eventY, ViewGroup viewGroup, float[] viewCoords) {
        ap.b();
        int targetTag = viewGroup.getId();
        viewCoords[0] = eventX;
        viewCoords[1] = eventY;
        View nativeTargetView = a(viewCoords, viewGroup);
        if (nativeTargetView == null) {
            return targetTag;
        }
        View reactTargetView = nativeTargetView;
        while (reactTargetView != null && reactTargetView.getId() <= 0) {
            reactTargetView = (View) reactTargetView.getParent();
        }
        if (reactTargetView == null) {
            return targetTag;
        }
        float f = viewCoords[0];
        float f2 = viewCoords[1];
        if (reactTargetView instanceof t) {
            return ((t) reactTargetView).a(f, f2);
        }
        return reactTargetView.getId();
    }

    private static View a(float[] eventCoords, ViewGroup viewGroup) {
        int childrenCount = viewGroup.getChildCount();
        z zIndexedViewGroup = viewGroup instanceof z ? (z) viewGroup : null;
        for (int i = childrenCount - 1; i >= 0; i--) {
            int childIndex;
            float f;
            Object obj;
            if (zIndexedViewGroup != null) {
                childIndex = zIndexedViewGroup.a(i);
            } else {
                childIndex = i;
            }
            View child = viewGroup.getChildAt(childIndex);
            PointF childPoint = b;
            float scrollX = (eventCoords[0] + ((float) viewGroup.getScrollX())) - ((float) child.getLeft());
            float scrollY = (((float) viewGroup.getScrollY()) + eventCoords[1]) - ((float) child.getTop());
            Matrix matrix = child.getMatrix();
            if (matrix.isIdentity()) {
                f = scrollX;
                scrollX = scrollY;
            } else {
                float[] fArr = c;
                fArr[0] = scrollX;
                fArr[1] = scrollY;
                Matrix matrix2 = d;
                matrix.invert(matrix2);
                matrix2.mapPoints(fArr);
                f = fArr[0];
                scrollX = fArr[1];
            }
            if ((child instanceof c) && ((c) child).d() != null) {
                Rect d = ((c) child).d();
                if (f >= ((float) (-d.left)) && f < ((float) ((child.getRight() - child.getLeft()) + d.right)) && scrollX >= ((float) (-d.top))) {
                    if (scrollX < ((float) (d.bottom + (child.getBottom() - child.getTop())))) {
                        childPoint.set(f, scrollX);
                        obj = 1;
                    }
                }
                obj = null;
            } else if (f < 0.0f || f >= ((float) (child.getRight() - child.getLeft())) || scrollX < 0.0f || scrollX >= ((float) (child.getBottom() - child.getTop()))) {
                obj = null;
            } else {
                childPoint.set(f, scrollX);
                obj = 1;
            }
            if (obj != null) {
                View targetView;
                float restoreX = eventCoords[0];
                float restoreY = eventCoords[1];
                eventCoords[0] = childPoint.x;
                eventCoords[1] = childPoint.y;
                q c_ = child instanceof v ? ((v) child).c_() : q.AUTO;
                if (!child.isEnabled()) {
                    if (c_ == q.AUTO) {
                        c_ = q.BOX_NONE;
                    } else if (c_ == q.BOX_ONLY) {
                        c_ = q.NONE;
                    }
                }
                if (c_ == q.NONE) {
                    targetView = null;
                } else if (c_ == q.BOX_ONLY) {
                    targetView = child;
                } else if (c_ == q.BOX_NONE) {
                    if (child instanceof ViewGroup) {
                        targetView = a(eventCoords, (ViewGroup) child);
                        if (targetView == child) {
                            if ((child instanceof t) && ((t) child).a(eventCoords[0], eventCoords[1]) != child.getId()) {
                                targetView = child;
                            }
                        }
                    }
                    targetView = null;
                } else if (c_ == q.AUTO) {
                    targetView = ((child instanceof u) && ((u) child).b(eventCoords[0], eventCoords[1])) ? child : child instanceof ViewGroup ? a(eventCoords, (ViewGroup) child) : child;
                } else {
                    throw new n("Unknown pointer event type: " + c_.toString());
                }
                if (targetView != null) {
                    return targetView;
                }
                eventCoords[0] = restoreX;
                eventCoords[1] = restoreY;
            }
        }
        return viewGroup;
    }
}
