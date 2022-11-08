package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.PopupWindow.OnDismissListener;
import defpackage.he;
import defpackage.hs;

public class y {
    private final Context a;
    private final l b;
    private final boolean c;
    private final int d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
    private aa i;
    private x j;
    private OnDismissListener k;
    private final OnDismissListener l;

    public y(Context context, l lVar, View view, boolean z, int i) {
        this(context, lVar, view, z, i, 0);
    }

    public y(Context context, l lVar, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new OnDismissListener(this) {
            final /* synthetic */ y a;

            {
                this.a = r1;
            }

            public final void onDismiss() {
                this.a.f();
            }
        };
        this.a = context;
        this.b = lVar;
        this.f = view;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    public final void a(OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public final void a(View view) {
        this.f = view;
    }

    public final void a(boolean z) {
        this.h = z;
        if (this.j != null) {
            this.j.a(z);
        }
    }

    public final void a() {
        this.g = 8388613;
    }

    public final void b() {
        if (!d()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public final androidx.appcompat.view.menu.x c() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r0_10 androidx.appcompat.view.menu.x) in PHI: PHI: (r0_12 androidx.appcompat.view.menu.x) = (r0_10 androidx.appcompat.view.menu.x), (r0_11 androidx.appcompat.view.menu.x) binds: {(r0_10 androidx.appcompat.view.menu.x)=B:11:0x003f, (r0_11 androidx.appcompat.view.menu.x)=B:12:0x0050}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r14 = this;
        r0 = r14.j;
        if (r0 != 0) goto L_0x0082;
    L_0x0004:
        r0 = r14.a;
        r1 = "window";
        r0 = r0.getSystemService(r1);
        r0 = (android.view.WindowManager) r0;
        r0 = r0.getDefaultDisplay();
        r1 = new android.graphics.Point;
        r1.<init>();
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 17;
        if (r2 < r3) goto L_0x0021;
    L_0x001d:
        r0.getRealSize(r1);
        goto L_0x0024;
    L_0x0021:
        r0.getSize(r1);
    L_0x0024:
        r0 = r1.x;
        r1 = r1.y;
        r0 = java.lang.Math.min(r0, r1);
        r1 = r14.a;
        r1 = r1.getResources();
        r2 = defpackage.p.abc_cascading_menus_min_smallest_width;
        r1 = r1.getDimensionPixelSize(r2);
        if (r0 < r1) goto L_0x003c;
    L_0x003a:
        r0 = 1;
        goto L_0x003d;
    L_0x003c:
        r0 = 0;
    L_0x003d:
        if (r0 == 0) goto L_0x0050;
    L_0x003f:
        r0 = new androidx.appcompat.view.menu.g;
        r2 = r14.a;
        r3 = r14.f;
        r4 = r14.d;
        r5 = r14.e;
        r6 = r14.c;
        r1 = r0;
        r1.<init>(r2, r3, r4, r5, r6);
        goto L_0x0062;
    L_0x0050:
        r0 = new androidx.appcompat.view.menu.ag;
        r8 = r14.a;
        r9 = r14.b;
        r10 = r14.f;
        r11 = r14.d;
        r12 = r14.e;
        r13 = r14.c;
        r7 = r0;
        r7.<init>(r8, r9, r10, r11, r12, r13);
    L_0x0062:
        r1 = r14.b;
        r0.a(r1);
        r1 = r14.l;
        r0.a(r1);
        r1 = r14.f;
        r0.a(r1);
        r1 = r14.i;
        r0.setCallback(r1);
        r1 = r14.h;
        r0.a(r1);
        r1 = r14.g;
        r0.a(r1);
        r14.j = r0;
    L_0x0082:
        r0 = r14.j;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.y.c():androidx.appcompat.view.menu.x");
    }

    public final boolean d() {
        if (g()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public final boolean a(int i, int i2) {
        if (g()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        a(i, i2, true, true);
        return true;
    }

    private void a(int i, int i2, boolean z, boolean z2) {
        x c = c();
        c.b(z2);
        if (z) {
            if ((he.a(this.g, hs.g(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            c.b(i);
            c.c(i2);
            int i3 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            c.a(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        c.a();
    }

    public final void e() {
        if (g()) {
            this.j.b();
        }
    }

    protected void f() {
        this.j = null;
        if (this.k != null) {
            this.k.onDismiss();
        }
    }

    public final boolean g() {
        return this.j != null && this.j.c();
    }

    public final void a(aa aaVar) {
        this.i = aaVar;
        if (this.j != null) {
            this.j.setCallback(aaVar);
        }
    }
}
