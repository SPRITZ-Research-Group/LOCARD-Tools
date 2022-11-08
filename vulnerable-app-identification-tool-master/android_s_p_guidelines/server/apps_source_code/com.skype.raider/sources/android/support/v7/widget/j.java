package android.support.v7.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.widget.i;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

final class j extends PopupWindow {
    private static final boolean a = (VERSION.SDK_INT < 21);
    private boolean b;

    public j(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        aq a = aq.a(context, attrs, android.support.v7.appcompat.a.j.PopupWindow, defStyleAttr, defStyleRes);
        if (a.h(android.support.v7.appcompat.a.j.PopupWindow_overlapAnchor)) {
            boolean a2 = a.a(android.support.v7.appcompat.a.j.PopupWindow_overlapAnchor, false);
            if (a) {
                this.b = a2;
            } else {
                i.a((PopupWindow) this, a2);
            }
        }
        setBackgroundDrawable(a.a(android.support.v7.appcompat.a.j.PopupWindow_android_popupBackground));
        int i = VERSION.SDK_INT;
        if (defStyleRes != 0 && i < 11 && a.h(android.support.v7.appcompat.a.j.PopupWindow_android_popupAnimationStyle)) {
            setAnimationStyle(a.g(android.support.v7.appcompat.a.j.PopupWindow_android_popupAnimationStyle, -1));
        }
        a.a();
        if (VERSION.SDK_INT < 14) {
            try {
                final Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
                declaredField.setAccessible(true);
                Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
                declaredField2.setAccessible(true);
                final OnScrollChangedListener onScrollChangedListener = (OnScrollChangedListener) declaredField2.get(this);
                declaredField2.set(this, new OnScrollChangedListener() {
                    public final void onScrollChanged() {
                        try {
                            WeakReference<View> mAnchor = (WeakReference) declaredField.get(this);
                            if (mAnchor != null && mAnchor.get() != null) {
                                onScrollChangedListener.onScrollChanged();
                            }
                        } catch (IllegalAccessException e) {
                        }
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    public final void showAsDropDown(View anchor, int xoff, int yoff) {
        if (a && this.b) {
            yoff -= anchor.getHeight();
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

    public final void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (a && this.b) {
            yoff -= anchor.getHeight();
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    public final void update(View anchor, int xoff, int yoff, int width, int height) {
        if (a && this.b) {
            yoff -= anchor.getHeight();
        }
        super.update(anchor, xoff, yoff, width, height);
    }
}
