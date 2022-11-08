package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
import com.skype.Defines;

public final class b {
    static final j a;
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public int b = -1;
    private final AccessibilityNodeInfo c;

    public static class a {
        public static final a A = new a(b.a.d());
        public static final a B = new a(b.a.f());
        public static final a C = new a(b.a.g());
        public static final a D = new a(b.a.h());
        public static final a a = new a(1);
        public static final a b = new a(2);
        public static final a c = new a(4);
        public static final a d = new a(8);
        public static final a e = new a(16);
        public static final a f = new a(32);
        public static final a g = new a(64);
        public static final a h = new a(128);
        public static final a i = new a((int) Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE);
        public static final a j = new a(512);
        public static final a k = new a(1024);
        public static final a l = new a(2048);
        public static final a m = new a(4096);
        public static final a n = new a(8192);
        public static final a o = new a(16384);
        public static final a p = new a(32768);
        public static final a q = new a(65536);
        public static final a r = new a(131072);
        public static final a s = new a(262144);
        public static final a t = new a(524288);
        public static final a u = new a(1048576);
        public static final a v = new a(2097152);
        public static final a w = new a(b.a.b());
        public static final a x = new a(b.a.a());
        public static final a y = new a(b.a.c());
        public static final a z = new a(b.a.e());
        final Object E;

        private a(int actionId) {
            this(b.a.a(actionId));
        }

        private a(Object action) {
            this.E = action;
        }
    }

    static class j {
        j() {
        }

        public Object a(int actionId) {
            return null;
        }

        public boolean c(AccessibilityNodeInfo info, Object action) {
            return false;
        }

        public boolean a(AccessibilityNodeInfo info) {
            return false;
        }

        public boolean b(AccessibilityNodeInfo info) {
            return false;
        }

        public void a(AccessibilityNodeInfo info, int granularities) {
        }

        public int c(AccessibilityNodeInfo info) {
            return 0;
        }

        public void a(AccessibilityNodeInfo info, boolean visibleToUser) {
        }

        public void b(AccessibilityNodeInfo info, boolean focused) {
        }

        public String d(AccessibilityNodeInfo info) {
            return null;
        }

        public void a(AccessibilityNodeInfo info, Object collectionInfo) {
        }

        public void b(AccessibilityNodeInfo info, Object collectionItemInfo) {
        }

        public Object a(int rowCount, int columnCount) {
            return null;
        }

        public Object a(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading) {
            return null;
        }

        public void e(AccessibilityNodeInfo info) {
        }

        public void a(AccessibilityNodeInfo info, CharSequence error) {
        }

        public void a(AccessibilityNodeInfo info, View labeled) {
        }

        public void f(AccessibilityNodeInfo info) {
        }

        public Object a() {
            return null;
        }

        public Object h() {
            return null;
        }

        public Object b() {
            return null;
        }

        public Object c() {
            return null;
        }

        public Object d() {
            return null;
        }

        public Object e() {
            return null;
        }

        public Object f() {
            return null;
        }

        public Object g() {
            return null;
        }
    }

    @RequiresApi(16)
    static class b extends j {
        b() {
        }

        public final boolean a(AccessibilityNodeInfo info) {
            return info.isVisibleToUser();
        }

        public final void a(AccessibilityNodeInfo info, boolean visibleToUser) {
            info.setVisibleToUser(visibleToUser);
        }

        public final boolean b(AccessibilityNodeInfo info) {
            return info.isAccessibilityFocused();
        }

        public final void b(AccessibilityNodeInfo info, boolean focused) {
            info.setAccessibilityFocused(focused);
        }

        public final void a(AccessibilityNodeInfo info, int granularities) {
            info.setMovementGranularities(granularities);
        }

        public final int c(AccessibilityNodeInfo info) {
            return info.getMovementGranularities();
        }
    }

    @RequiresApi(17)
    static class c extends b {
        c() {
        }

        public final void a(AccessibilityNodeInfo info, View labeled) {
            info.setLabelFor(labeled);
        }
    }

    @RequiresApi(18)
    static class d extends c {
        d() {
        }

        public final String d(AccessibilityNodeInfo info) {
            return info.getViewIdResourceName();
        }
    }

    @RequiresApi(19)
    static class e extends d {
        e() {
        }

        public final void a(AccessibilityNodeInfo info, Object collectionInfo) {
            info.setCollectionInfo((CollectionInfo) collectionInfo);
        }

        public Object a(int rowCount, int columnCount) {
            return CollectionInfo.obtain(rowCount, columnCount, false);
        }

        public Object a(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading) {
            return CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading);
        }

        public final void b(AccessibilityNodeInfo info, Object collectionItemInfo) {
            info.setCollectionItemInfo((CollectionItemInfo) collectionItemInfo);
        }

        public final void e(AccessibilityNodeInfo info) {
            info.setContentInvalid(true);
        }

        public final void f(AccessibilityNodeInfo info) {
            info.setCanOpenPopup(true);
        }
    }

    @RequiresApi(21)
    static class f extends e {
        f() {
        }

        public final Object a(int actionId) {
            return new AccessibilityAction(actionId, null);
        }

        public final Object a(int rowCount, int columnCount) {
            return CollectionInfo.obtain(rowCount, columnCount, false, 0);
        }

        public final boolean c(AccessibilityNodeInfo info, Object action) {
            return info.removeAction((AccessibilityAction) action);
        }

        public final Object a(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading) {
            return CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading, false);
        }

        public final void a(AccessibilityNodeInfo info, CharSequence error) {
            info.setError(error);
        }
    }

    @RequiresApi(22)
    static class g extends f {
        g() {
        }
    }

    @RequiresApi(23)
    static class h extends g {
        h() {
        }

        public final Object a() {
            return AccessibilityAction.ACTION_SCROLL_TO_POSITION;
        }

        public final Object b() {
            return AccessibilityAction.ACTION_SHOW_ON_SCREEN;
        }

        public final Object c() {
            return AccessibilityAction.ACTION_SCROLL_UP;
        }

        public final Object d() {
            return AccessibilityAction.ACTION_SCROLL_DOWN;
        }

        public final Object e() {
            return AccessibilityAction.ACTION_SCROLL_LEFT;
        }

        public final Object f() {
            return AccessibilityAction.ACTION_SCROLL_RIGHT;
        }

        public final Object g() {
            return AccessibilityAction.ACTION_CONTEXT_CLICK;
        }
    }

    @RequiresApi(24)
    static class i extends h {
        i() {
        }

        public final Object h() {
            return AccessibilityAction.ACTION_SET_PROGRESS;
        }
    }

    public static class k {
        final Object a;

        public static k a(int rowCount, int columnCount) {
            return new k(b.a.a(rowCount, columnCount));
        }

        private k(Object info) {
            this.a = info;
        }
    }

    public static class l {
        final Object a;

        public static l a(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading) {
            return new l(b.a.a(rowIndex, rowSpan, columnIndex, columnSpan, heading));
        }

        private l(Object info) {
            this.a = info;
        }
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            a = new i();
        } else if (VERSION.SDK_INT >= 23) {
            a = new h();
        } else if (VERSION.SDK_INT >= 22) {
            a = new g();
        } else if (VERSION.SDK_INT >= 21) {
            a = new f();
        } else if (VERSION.SDK_INT >= 19) {
            a = new e();
        } else if (VERSION.SDK_INT >= 18) {
            a = new d();
        } else if (VERSION.SDK_INT >= 17) {
            a = new c();
        } else if (VERSION.SDK_INT >= 16) {
            a = new b();
        } else {
            a = new j();
        }
    }

    private b(AccessibilityNodeInfo info) {
        this.c = info;
    }

    public static b a(@NonNull AccessibilityNodeInfo info) {
        return new b(info);
    }

    public final AccessibilityNodeInfo a() {
        return this.c;
    }

    public static b a(b info) {
        return a(AccessibilityNodeInfo.obtain(info.c));
    }

    public final void a(View source) {
        this.c.setSource(source);
    }

    public final void b(View child) {
        this.c.addChild(child);
    }

    public final int b() {
        return this.c.getActions();
    }

    public final void a(int action) {
        this.c.addAction(action);
    }

    public final boolean a(a action) {
        return a.c(this.c, action.E);
    }

    public final void b(int granularities) {
        a.a(this.c, granularities);
    }

    public final int c() {
        return a.c(this.c);
    }

    public final void c(View parent) {
        this.c.setParent(parent);
    }

    public final void a(Rect outBounds) {
        this.c.getBoundsInParent(outBounds);
    }

    public final void b(Rect bounds) {
        this.c.setBoundsInParent(bounds);
    }

    public final void c(Rect outBounds) {
        this.c.getBoundsInScreen(outBounds);
    }

    public final void d(Rect bounds) {
        this.c.setBoundsInScreen(bounds);
    }

    public final boolean d() {
        return this.c.isFocusable();
    }

    public final void a(boolean focusable) {
        this.c.setFocusable(focusable);
    }

    public final boolean e() {
        return this.c.isFocused();
    }

    public final void b(boolean focused) {
        this.c.setFocused(focused);
    }

    public final boolean f() {
        return a.a(this.c);
    }

    public final void c(boolean visibleToUser) {
        a.a(this.c, visibleToUser);
    }

    public final boolean g() {
        return a.b(this.c);
    }

    public final void d(boolean focused) {
        a.b(this.c, focused);
    }

    public final boolean h() {
        return this.c.isSelected();
    }

    public final void e(boolean selected) {
        this.c.setSelected(selected);
    }

    public final boolean i() {
        return this.c.isClickable();
    }

    public final void f(boolean clickable) {
        this.c.setClickable(clickable);
    }

    public final boolean j() {
        return this.c.isLongClickable();
    }

    public final void g(boolean longClickable) {
        this.c.setLongClickable(longClickable);
    }

    public final boolean k() {
        return this.c.isEnabled();
    }

    public final void h(boolean enabled) {
        this.c.setEnabled(enabled);
    }

    public final void i(boolean scrollable) {
        this.c.setScrollable(scrollable);
    }

    public final CharSequence l() {
        return this.c.getPackageName();
    }

    public final void a(CharSequence packageName) {
        this.c.setPackageName(packageName);
    }

    public final CharSequence m() {
        return this.c.getClassName();
    }

    public final void b(CharSequence className) {
        this.c.setClassName(className);
    }

    public final void c(CharSequence text) {
        this.c.setText(text);
    }

    public final CharSequence n() {
        return this.c.getContentDescription();
    }

    public final void d(CharSequence contentDescription) {
        this.c.setContentDescription(contentDescription);
    }

    public final void o() {
        this.c.recycle();
    }

    public final void a(Object collectionInfo) {
        a.a(this.c, ((k) collectionInfo).a);
    }

    public final void b(Object collectionItemInfo) {
        a.b(this.c, ((l) collectionItemInfo).a);
    }

    public final void p() {
        a.e(this.c);
    }

    public final void e(CharSequence error) {
        a.a(this.c, error);
    }

    public final void d(View labeled) {
        a.a(this.c, labeled);
    }

    public final void q() {
        a.f(this.c);
    }

    public final int hashCode() {
        return this.c == null ? 0 : this.c.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        b other = (b) obj;
        if (this.c == null) {
            if (other.c != null) {
                return false;
            }
            return true;
        } else if (this.c.equals(other.c)) {
            return true;
        } else {
            return false;
        }
    }

    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        Rect bounds = new Rect();
        a(bounds);
        builder.append("; boundsInParent: " + bounds);
        c(bounds);
        builder.append("; boundsInScreen: " + bounds);
        builder.append("; packageName: ").append(this.c.getPackageName());
        builder.append("; className: ").append(this.c.getClassName());
        builder.append("; text: ").append(this.c.getText());
        builder.append("; contentDescription: ").append(this.c.getContentDescription());
        builder.append("; viewId: ").append(a.d(this.c));
        builder.append("; checkable: ").append(this.c.isCheckable());
        builder.append("; checked: ").append(this.c.isChecked());
        builder.append("; focusable: ").append(this.c.isFocusable());
        builder.append("; focused: ").append(this.c.isFocused());
        builder.append("; selected: ").append(this.c.isSelected());
        builder.append("; clickable: ").append(this.c.isClickable());
        builder.append("; longClickable: ").append(this.c.isLongClickable());
        builder.append("; enabled: ").append(this.c.isEnabled());
        builder.append("; password: ").append(this.c.isPassword());
        builder.append("; scrollable: " + this.c.isScrollable());
        builder.append("; [");
        int actionBits = this.c.getActions();
        while (actionBits != 0) {
            String str;
            int action = 1 << Integer.numberOfTrailingZeros(actionBits);
            actionBits &= action ^ -1;
            switch (action) {
                case 1:
                    str = "ACTION_FOCUS";
                    break;
                case 2:
                    str = "ACTION_CLEAR_FOCUS";
                    break;
                case 4:
                    str = "ACTION_SELECT";
                    break;
                case 8:
                    str = "ACTION_CLEAR_SELECTION";
                    break;
                case 16:
                    str = "ACTION_CLICK";
                    break;
                case 32:
                    str = "ACTION_LONG_CLICK";
                    break;
                case 64:
                    str = "ACTION_ACCESSIBILITY_FOCUS";
                    break;
                case 128:
                    str = "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                    break;
                case Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE /*256*/:
                    str = "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                    break;
                case 512:
                    str = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                    break;
                case 1024:
                    str = "ACTION_NEXT_HTML_ELEMENT";
                    break;
                case 2048:
                    str = "ACTION_PREVIOUS_HTML_ELEMENT";
                    break;
                case 4096:
                    str = "ACTION_SCROLL_FORWARD";
                    break;
                case 8192:
                    str = "ACTION_SCROLL_BACKWARD";
                    break;
                case 16384:
                    str = "ACTION_COPY";
                    break;
                case 32768:
                    str = "ACTION_PASTE";
                    break;
                case 65536:
                    str = "ACTION_CUT";
                    break;
                case 131072:
                    str = "ACTION_SET_SELECTION";
                    break;
                default:
                    str = "ACTION_UNKNOWN";
                    break;
            }
            builder.append(str);
            if (actionBits != 0) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
