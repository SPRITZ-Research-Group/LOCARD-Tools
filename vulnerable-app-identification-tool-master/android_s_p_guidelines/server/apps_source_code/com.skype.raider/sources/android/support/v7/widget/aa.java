package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.i;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.menu.r;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.android.video.hw.extension.SliqConstants;
import java.lang.reflect.Method;

public class aa implements r {
    private static Method a;
    private static Method b;
    private static Method h;
    private Drawable A;
    private OnItemClickListener B;
    private OnItemSelectedListener C;
    private final d D;
    private final c E;
    private final a F;
    private Runnable G;
    private final Rect H;
    private Rect I;
    private boolean J;
    u c;
    int d;
    final e e;
    final Handler f;
    PopupWindow g;
    private Context i;
    private ListAdapter j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;
    private boolean v;
    private View w;
    private int x;
    private DataSetObserver y;
    private View z;

    private class a implements Runnable {
        final /* synthetic */ aa a;

        a(aa aaVar) {
            this.a = aaVar;
        }

        public final void run() {
            this.a.p();
        }
    }

    private class b extends DataSetObserver {
        final /* synthetic */ aa a;

        b(aa aaVar) {
            this.a = aaVar;
        }

        public final void onChanged() {
            if (this.a.f()) {
                this.a.c();
            }
        }

        public final void onInvalidated() {
            this.a.e();
        }
    }

    private class c implements OnScrollListener {
        final /* synthetic */ aa a;

        c(aa aaVar) {
            this.a = aaVar;
        }

        public final void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }

        public final void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == 1 && !this.a.q() && this.a.g.getContentView() != null) {
                this.a.f.removeCallbacks(this.a.e);
                this.a.e.run();
            }
        }
    }

    private class d implements OnTouchListener {
        final /* synthetic */ aa a;

        d(aa aaVar) {
            this.a = aaVar;
        }

        public final boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (action == 0 && this.a.g != null && this.a.g.isShowing() && x >= 0 && x < this.a.g.getWidth() && y >= 0 && y < this.a.g.getHeight()) {
                this.a.f.postDelayed(this.a.e, 250);
            } else if (action == 1) {
                this.a.f.removeCallbacks(this.a.e);
            }
            return false;
        }
    }

    private class e implements Runnable {
        final /* synthetic */ aa a;

        e(aa aaVar) {
            this.a = aaVar;
        }

        public final void run() {
            if (this.a.c != null && ViewCompat.D(this.a.c) && this.a.c.getCount() > this.a.c.getChildCount() && this.a.c.getChildCount() <= this.a.d) {
                this.a.g.setInputMethodMode(2);
                this.a.c();
            }
        }
    }

    static {
        try {
            a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
        }
        try {
            b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException e2) {
        }
        try {
            h = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
        } catch (NoSuchMethodException e3) {
        }
    }

    public aa(@NonNull Context context) {
        this(context, null, android.support.v7.appcompat.a.a.listPopupWindowStyle);
    }

    public aa(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public aa(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        this.k = -2;
        this.l = -2;
        this.o = 1002;
        this.q = true;
        this.t = 0;
        this.u = false;
        this.v = false;
        this.d = Integer.MAX_VALUE;
        this.x = 0;
        this.e = new e(this);
        this.D = new d(this);
        this.E = new c(this);
        this.F = new a(this);
        this.H = new Rect();
        this.i = context;
        this.f = new Handler(context.getMainLooper());
        TypedArray a = context.obtainStyledAttributes(attrs, j.ListPopupWindow, defStyleAttr, defStyleRes);
        this.m = a.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.n = a.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.n != 0) {
            this.p = true;
        }
        a.recycle();
        this.g = new j(context, attrs, defStyleAttr, defStyleRes);
        this.g.setInputMethodMode(1);
    }

    public void a(@Nullable ListAdapter adapter) {
        if (this.y == null) {
            this.y = new b(this);
        } else if (this.j != null) {
            this.j.unregisterDataSetObserver(this.y);
        }
        this.j = adapter;
        if (this.j != null) {
            adapter.registerDataSetObserver(this.y);
        }
        if (this.c != null) {
            this.c.setAdapter(this.j);
        }
    }

    public final void d() {
        this.x = 0;
    }

    public final void h() {
        this.J = true;
        this.g.setFocusable(true);
    }

    public final boolean i() {
        return this.J;
    }

    public final void a(@Nullable Drawable d) {
        this.g.setBackgroundDrawable(d);
    }

    public final void j() {
        this.g.setAnimationStyle(0);
    }

    @Nullable
    public final View k() {
        return this.z;
    }

    public final void b(@Nullable View anchor) {
        this.z = anchor;
    }

    public final int l() {
        return this.m;
    }

    public final void a(int offset) {
        this.m = offset;
    }

    public final int m() {
        if (this.p) {
            return this.n;
        }
        return 0;
    }

    public final void b(int offset) {
        this.n = offset;
        this.p = true;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final void a(Rect bounds) {
        this.I = bounds;
    }

    public final void c(int gravity) {
        this.t = gravity;
    }

    public final int n() {
        return this.l;
    }

    public final void d(int width) {
        Drawable popupBackground = this.g.getBackground();
        if (popupBackground != null) {
            popupBackground.getPadding(this.H);
            this.l = (this.H.left + this.H.right) + width;
            return;
        }
        this.l = width;
    }

    public final void a(@Nullable OnItemClickListener clickListener) {
        this.B = clickListener;
    }

    public void c() {
        int i;
        int i2;
        boolean z;
        int height;
        int makeMeasureSpec;
        boolean z2 = true;
        LayoutParams layoutParams;
        View view;
        if (this.c == null) {
            boolean z3;
            Context context = this.i;
            this.G = new Runnable(this) {
                final /* synthetic */ aa a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    View view = this.a.k();
                    if (view != null && view.getWindowToken() != null) {
                        this.a.c();
                    }
                }
            };
            if (this.J) {
                z3 = false;
            } else {
                z3 = true;
            }
            this.c = a(context, z3);
            if (this.A != null) {
                this.c.setSelector(this.A);
            }
            this.c.setAdapter(this.j);
            this.c.setOnItemClickListener(this.B);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new OnItemSelectedListener(this) {
                final /* synthetic */ aa a;

                {
                    this.a = this$0;
                }

                public final void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    if (position != -1) {
                        u dropDownList = this.a.c;
                        if (dropDownList != null) {
                            dropDownList.a(false);
                        }
                    }
                }

                public final void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            this.c.setOnScrollListener(this.E);
            if (this.C != null) {
                this.c.setOnItemSelectedListener(this.C);
            }
            View view2 = this.c;
            View view3 = this.w;
            if (view3 != null) {
                View linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, 0, 1.0f);
                switch (this.x) {
                    case 0:
                        linearLayout.addView(view3);
                        linearLayout.addView(view2, layoutParams2);
                        break;
                    case 1:
                        linearLayout.addView(view2, layoutParams2);
                        linearLayout.addView(view3);
                        break;
                    default:
                        new StringBuilder("Invalid hint position ").append(this.x);
                        break;
                }
                if (this.l >= 0) {
                    i = this.l;
                    i2 = Integer.MIN_VALUE;
                } else {
                    i2 = 0;
                    i = 0;
                }
                view3.measure(MeasureSpec.makeMeasureSpec(i, i2), 0);
                layoutParams = (LayoutParams) view3.getLayoutParams();
                i2 = layoutParams.bottomMargin + (view3.getMeasuredHeight() + layoutParams.topMargin);
                view = linearLayout;
            } else {
                view = view2;
                i2 = 0;
            }
            this.g.setContentView(view);
        } else {
            this.g.getContentView();
            view = this.w;
            if (view != null) {
                layoutParams = (LayoutParams) view.getLayoutParams();
                i2 = layoutParams.bottomMargin + (view.getMeasuredHeight() + layoutParams.topMargin);
            } else {
                i2 = 0;
            }
        }
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            i = this.H.top + this.H.bottom;
            if (!this.p) {
                this.n = -this.H.top;
            }
        } else {
            this.H.setEmpty();
            i = 0;
        }
        if (this.g.getInputMethodMode() == 2) {
            z = true;
        } else {
            z = false;
        }
        int a = a(this.z, this.n, z);
        if (this.u || this.k == -1) {
            height = a + i;
        } else {
            switch (this.l) {
                case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), Integer.MIN_VALUE);
                    break;
                case -1:
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), ErrorDialogData.SUPPRESSED);
                    break;
                default:
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.l, ErrorDialogData.SUPPRESSED);
                    break;
            }
            makeMeasureSpec = this.c.a(makeMeasureSpec, a - i2);
            if (makeMeasureSpec > 0) {
                i2 += i + (this.c.getPaddingTop() + this.c.getPaddingBottom());
            }
            height = makeMeasureSpec + i2;
        }
        boolean noInputMethod = q();
        i.a(this.g, this.o);
        int widthSpec;
        int heightSpec;
        PopupWindow popupWindow;
        if (!this.g.isShowing()) {
            if (this.l == -1) {
                widthSpec = -1;
            } else if (this.l == -2) {
                widthSpec = this.z.getWidth();
            } else {
                widthSpec = this.l;
            }
            if (this.k == -1) {
                heightSpec = -1;
            } else if (this.k == -2) {
                heightSpec = height;
            } else {
                heightSpec = this.k;
            }
            this.g.setWidth(widthSpec);
            this.g.setHeight(heightSpec);
            if (a != null) {
                try {
                    a.invoke(this.g, new Object[]{Boolean.valueOf(true)});
                } catch (Exception e) {
                }
            }
            popupWindow = this.g;
            if (this.v || this.u) {
                z2 = false;
            }
            popupWindow.setOutsideTouchable(z2);
            this.g.setTouchInterceptor(this.D);
            if (this.s) {
                i.a(this.g, this.r);
            }
            if (h != null) {
                try {
                    h.invoke(this.g, new Object[]{this.I});
                } catch (Exception e2) {
                }
            }
            i.a(this.g, this.z, this.m, this.n, this.t);
            this.c.setSelection(-1);
            if (!this.J || this.c.isInTouchMode()) {
                p();
            }
            if (!this.J) {
                this.f.post(this.F);
            }
        } else if (ViewCompat.D(this.z)) {
            if (this.l == -1) {
                widthSpec = -1;
            } else if (this.l == -2) {
                widthSpec = this.z.getWidth();
            } else {
                widthSpec = this.l;
            }
            if (this.k == -1) {
                if (noInputMethod) {
                    heightSpec = height;
                } else {
                    heightSpec = -1;
                }
                PopupWindow popupWindow2;
                if (noInputMethod) {
                    popupWindow2 = this.g;
                    if (this.l == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow2.setWidth(i2);
                    this.g.setHeight(0);
                } else {
                    popupWindow2 = this.g;
                    if (this.l == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow2.setWidth(i2);
                    this.g.setHeight(-1);
                }
            } else if (this.k == -2) {
                heightSpec = height;
            } else {
                heightSpec = this.k;
            }
            popupWindow = this.g;
            if (this.v || this.u) {
                z2 = false;
            }
            popupWindow.setOutsideTouchable(z2);
            popupWindow = this.g;
            View view4 = this.z;
            int i3 = this.m;
            makeMeasureSpec = this.n;
            if (widthSpec < 0) {
                i = -1;
            } else {
                i = widthSpec;
            }
            if (heightSpec < 0) {
                a = -1;
            } else {
                a = heightSpec;
            }
            popupWindow.update(view4, i3, makeMeasureSpec, i, a);
        }
    }

    public final void e() {
        this.g.dismiss();
        if (this.w != null) {
            ViewParent parent = this.w.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.w);
            }
        }
        this.g.setContentView(null);
        this.c = null;
        this.f.removeCallbacks(this.e);
    }

    public final void a(@Nullable OnDismissListener listener) {
        this.g.setOnDismissListener(listener);
    }

    public final void o() {
        this.g.setInputMethodMode(2);
    }

    public final void p() {
        u list = this.c;
        if (list != null) {
            list.a(true);
            list.requestLayout();
        }
    }

    public final boolean f() {
        return this.g.isShowing();
    }

    public final boolean q() {
        return this.g.getInputMethodMode() == 2;
    }

    @Nullable
    public final ListView g() {
        return this.c;
    }

    @NonNull
    u a(Context context, boolean hijackFocus) {
        return new u(context, hijackFocus);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final void r() {
        this.s = true;
        this.r = true;
    }

    private int a(View anchor, int yOffset, boolean ignoreBottomDecorations) {
        if (b != null) {
            try {
                return ((Integer) b.invoke(this.g, new Object[]{anchor, Integer.valueOf(yOffset), Boolean.valueOf(ignoreBottomDecorations)})).intValue();
            } catch (Exception e) {
            }
        }
        return this.g.getMaxAvailableHeight(anchor, yOffset);
    }
}
