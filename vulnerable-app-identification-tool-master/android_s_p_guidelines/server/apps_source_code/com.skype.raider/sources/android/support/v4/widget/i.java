package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class i {
    static final d a;

    static class d {
        private static Method a;
        private static boolean b;

        d() {
        }

        public void a(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
            if ((android.support.v4.view.d.a(gravity, ViewCompat.f(anchor)) & 7) == 5) {
                xoff -= popup.getWidth() - anchor.getWidth();
            }
            popup.showAsDropDown(anchor, xoff, yoff);
        }

        public void a(PopupWindow popupWindow, boolean overlapAnchor) {
        }

        public void a(PopupWindow popupWindow, int layoutType) {
            if (!b) {
                try {
                    Method declaredMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", new Class[]{Integer.TYPE});
                    a = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception e) {
                }
                b = true;
            }
            if (a != null) {
                try {
                    a.invoke(popupWindow, new Object[]{Integer.valueOf(layoutType)});
                } catch (Exception e2) {
                }
            }
        }
    }

    @RequiresApi(19)
    static class a extends d {
        a() {
        }

        public final void a(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
            popup.showAsDropDown(anchor, xoff, yoff, gravity);
        }
    }

    @RequiresApi(21)
    static class b extends a {
        private static Field a;

        b() {
        }

        static {
            try {
                Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
        }

        public void a(PopupWindow popupWindow, boolean overlapAnchor) {
            if (a != null) {
                try {
                    a.set(popupWindow, Boolean.valueOf(overlapAnchor));
                } catch (IllegalAccessException e) {
                }
            }
        }
    }

    @RequiresApi(23)
    static class c extends b {
        c() {
        }

        public final void a(PopupWindow popupWindow, boolean overlapAnchor) {
            popupWindow.setOverlapAnchor(overlapAnchor);
        }

        public final void a(PopupWindow popupWindow, int layoutType) {
            popupWindow.setWindowLayoutType(layoutType);
        }
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            a = new c();
        } else if (VERSION.SDK_INT >= 21) {
            a = new b();
        } else if (VERSION.SDK_INT >= 19) {
            a = new a();
        } else {
            a = new d();
        }
    }

    public static void a(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
        a.a(popup, anchor, xoff, yoff, gravity);
    }

    public static void a(PopupWindow popupWindow, boolean overlapAnchor) {
        a.a(popupWindow, overlapAnchor);
    }

    public static void a(PopupWindow popupWindow, int layoutType) {
        a.a(popupWindow, layoutType);
    }
}
