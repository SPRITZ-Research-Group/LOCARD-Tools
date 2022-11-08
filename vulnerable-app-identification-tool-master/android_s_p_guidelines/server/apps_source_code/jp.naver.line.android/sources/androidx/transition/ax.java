package androidx.transition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import defpackage.hs;
import java.lang.reflect.Method;
import java.util.ArrayList;

final class ax extends ViewGroup {
    static Method a;
    ViewGroup b;
    View c;
    ArrayList<Drawable> d = null;
    aw e;

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.transition.ax.<clinit>():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r0 = android.view.ViewGroup.class;	 Catch:{ NoSuchMethodException -> 0x001d }
        r1 = "invalidateChildInParentFast";	 Catch:{ NoSuchMethodException -> 0x001d }
        r2 = 3;	 Catch:{ NoSuchMethodException -> 0x001d }
        r2 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x001d }
        r3 = 0;	 Catch:{ NoSuchMethodException -> 0x001d }
        r4 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x001d }
        r2[r3] = r4;	 Catch:{ NoSuchMethodException -> 0x001d }
        r3 = 1;	 Catch:{ NoSuchMethodException -> 0x001d }
        r4 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x001d }
        r2[r3] = r4;	 Catch:{ NoSuchMethodException -> 0x001d }
        r3 = 2;	 Catch:{ NoSuchMethodException -> 0x001d }
        r4 = android.graphics.Rect.class;	 Catch:{ NoSuchMethodException -> 0x001d }
        r2[r3] = r4;	 Catch:{ NoSuchMethodException -> 0x001d }
        r0 = r0.getDeclaredMethod(r1, r2);	 Catch:{ NoSuchMethodException -> 0x001d }
        a = r0;	 Catch:{ NoSuchMethodException -> 0x001d }
        return;
    L_0x001d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ax.<clinit>():void");
    }

    ax(Context context, ViewGroup viewGroup, View view, aw awVar) {
        super(context);
        this.b = viewGroup;
        this.c = view;
        setRight(viewGroup.getWidth());
        setBottom(viewGroup.getHeight());
        viewGroup.addView(this);
        this.e = awVar;
    }

    protected final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || (this.d != null && this.d.contains(drawable));
    }

    public final void a(View view) {
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (!(viewGroup == this.b || viewGroup.getParent() == null || !hs.F(viewGroup))) {
                int[] iArr = new int[2];
                int[] iArr2 = new int[2];
                viewGroup.getLocationOnScreen(iArr);
                this.b.getLocationOnScreen(iArr2);
                hs.d(view, iArr[0] - iArr2[0]);
                hs.c(view, iArr[1] - iArr2[1]);
            }
            viewGroup.removeView(view);
            if (view.getParent() != null) {
                viewGroup.removeView(view);
            }
        }
        super.addView(view, getChildCount() - 1);
    }

    public final void b(View view) {
        super.removeView(view);
        Object obj = (getChildCount() == 0 && (this.d == null || this.d.size() == 0)) ? 1 : null;
        if (obj != null) {
            this.b.removeView(this);
        }
    }

    public final void invalidateDrawable(Drawable drawable) {
        invalidate(drawable.getBounds());
    }

    protected final void dispatchDraw(Canvas canvas) {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.b.getLocationOnScreen(iArr);
        this.c.getLocationOnScreen(iArr2);
        int i = 0;
        canvas.translate((float) (iArr2[0] - iArr[0]), (float) (iArr2[1] - iArr[1]));
        canvas.clipRect(new Rect(0, 0, this.c.getWidth(), this.c.getHeight()));
        super.dispatchDraw(canvas);
        int size = this.d == null ? 0 : this.d.size();
        while (i < size) {
            ((Drawable) this.d.get(i)).draw(canvas);
            i++;
        }
    }

    public final ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        if (this.b != null) {
            rect.offset(iArr[0], iArr[1]);
            if (this.b instanceof ViewGroup) {
                iArr[0] = 0;
                iArr[1] = 0;
                int[] iArr2 = new int[2];
                int[] iArr3 = new int[2];
                int[] iArr4 = new int[2];
                this.b.getLocationOnScreen(iArr3);
                this.c.getLocationOnScreen(iArr4);
                iArr2[0] = iArr4[0] - iArr3[0];
                iArr2[1] = iArr4[1] - iArr3[1];
                rect.offset(iArr2[0], iArr2[1]);
                return super.invalidateChildInParent(iArr, rect);
            }
            invalidate(rect);
        }
        return null;
    }
}
