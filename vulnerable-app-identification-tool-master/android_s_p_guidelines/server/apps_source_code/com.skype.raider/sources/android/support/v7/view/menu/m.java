package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.d;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow.OnDismissListener;

@RestrictTo({a.LIBRARY_GROUP})
public class m {
    private final Context a;
    private final g b;
    private final boolean c;
    private final int d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
    private n.a i;
    private l j;
    private OnDismissListener k;
    private final OnDismissListener l;

    public m(@NonNull Context context, @NonNull g menu, @NonNull View anchorView, boolean overflowOnly, @AttrRes int popupStyleAttr) {
        this(context, menu, anchorView, overflowOnly, popupStyleAttr, 0);
    }

    public m(@NonNull Context context, @NonNull g menu, @NonNull View anchorView, boolean overflowOnly, @AttrRes int popupStyleAttr, @StyleRes int popupStyleRes) {
        this.g = 8388611;
        this.l = new OnDismissListener(this) {
            final /* synthetic */ m a;

            {
                this.a = this$0;
            }

            public final void onDismiss() {
                this.a.e();
            }
        };
        this.a = context;
        this.b = menu;
        this.f = anchorView;
        this.c = overflowOnly;
        this.d = popupStyleAttr;
        this.e = popupStyleRes;
    }

    public final void a(@Nullable OnDismissListener listener) {
        this.k = listener;
    }

    public final void a(@NonNull View anchor) {
        this.f = anchor;
    }

    public final void a(boolean forceShowIcon) {
        this.h = forceShowIcon;
        if (this.j != null) {
            this.j.b(forceShowIcon);
        }
    }

    public final void a(int gravity) {
        this.g = gravity;
    }

    public final void a() {
        if (!c()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    @NonNull
    public final l b() {
        if (this.j == null) {
            l cascadingMenuPopup;
            Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            if (VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else {
                defaultDisplay.getSize(point);
            }
            if ((Math.min(point.x, point.y) >= this.a.getResources().getDimensionPixelSize(d.abc_cascading_menus_min_smallest_width) ? 1 : null) != null) {
                cascadingMenuPopup = new CascadingMenuPopup(this.a, this.f, this.d, this.e, this.c);
            } else {
                cascadingMenuPopup = new s(this.a, this.b, this.f, this.d, this.e, this.c);
            }
            cascadingMenuPopup.a(this.b);
            cascadingMenuPopup.a(this.l);
            cascadingMenuPopup.a(this.f);
            cascadingMenuPopup.a(this.i);
            cascadingMenuPopup.b(this.h);
            cascadingMenuPopup.a(this.g);
            this.j = cascadingMenuPopup;
        }
        return this.j;
    }

    public final boolean c() {
        if (f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public final boolean a(int x, int y) {
        if (f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(x, y, true, true);
        return true;
    }

    private void a(int xOffset, int yOffset, boolean useOffsets, boolean showTitle) {
        l popup = b();
        popup.c(showTitle);
        if (useOffsets) {
            if ((android.support.v4.view.d.a(this.g, ViewCompat.f(this.f)) & 7) == 5) {
                xOffset += this.f.getWidth();
            }
            popup.b(xOffset);
            popup.c(yOffset);
            int halfSize = (int) ((48.0f * this.a.getResources().getDisplayMetrics().density) / 2.0f);
            popup.a(new Rect(xOffset - halfSize, yOffset - halfSize, xOffset + halfSize, yOffset + halfSize));
        }
        popup.c();
    }

    public final void d() {
        if (f()) {
            this.j.e();
        }
    }

    protected void e() {
        this.j = null;
        if (this.k != null) {
            this.k.onDismiss();
        }
    }

    public final boolean f() {
        return this.j != null && this.j.f();
    }

    public final void a(@Nullable n.a cb) {
        this.i = cb;
        if (this.j != null) {
            this.j.a(cb);
        }
    }
}
