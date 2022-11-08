package androidx.appcompat.widget;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import androidx.appcompat.view.menu.af;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import defpackage.gw;
import defpackage.ij;
import defpackage.p;
import defpackage.r;
import defpackage.s;
import defpackage.t;
import defpackage.v;

public class ActivityChooserView extends ViewGroup {
    final u a;
    final FrameLayout b;
    final FrameLayout c;
    gw d;
    final DataSetObserver e;
    OnDismissListener f;
    boolean g;
    int h;
    private final v i;
    private final View j;
    private final Drawable k;
    private final ImageView l;
    private final ImageView m;
    private final int n;
    private final OnGlobalLayoutListener o;
    private ListPopupWindow p;
    private boolean q;
    private int r;

    public class InnerLayout extends LinearLayout {
        private static final int[] a = new int[]{16842964};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            bu a = bu.a(context, attributeSet, a);
            setBackgroundDrawable(a.a(0));
            a.a();
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new DataSetObserver(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = r1;
            }

            public final void onChanged() {
                super.onChanged();
                this.a.a.notifyDataSetChanged();
            }

            public final void onInvalidated() {
                super.onInvalidated();
                this.a.a.notifyDataSetInvalidated();
            }
        };
        this.o = new OnGlobalLayoutListener(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = r1;
            }

            public final void onGlobalLayout() {
                if (this.a.c()) {
                    if (this.a.isShown()) {
                        this.a.d().a();
                        if (this.a.d != null) {
                            this.a.d.a(true);
                        }
                    } else {
                        this.a.d().b();
                    }
                }
            }
        };
        this.h = 4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, v.ActivityChooserView, i, 0);
        this.h = obtainStyledAttributes.getInt(v.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = obtainStyledAttributes.getDrawable(v.ActivityChooserView_expandActivityOverflowButtonDrawable);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(s.abc_activity_chooser_view, this, true);
        this.i = new v(this);
        this.j = findViewById(r.activity_chooser_view_content);
        this.k = this.j.getBackground();
        this.c = (FrameLayout) findViewById(r.default_activity_button);
        this.c.setOnClickListener(this.i);
        this.c.setOnLongClickListener(this.i);
        this.m = (ImageView) this.c.findViewById(r.image);
        FrameLayout frameLayout = (FrameLayout) findViewById(r.expand_activities_button);
        frameLayout.setOnClickListener(this.i);
        frameLayout.setAccessibilityDelegate(new AccessibilityDelegate(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = r1;
            }

            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                ij.a(accessibilityNodeInfo).w();
            }
        });
        frameLayout.setOnTouchListener(new av(this, frameLayout) {
            final /* synthetic */ ActivityChooserView a;

            public final af a() {
                return this.a.d();
            }

            protected final boolean b() {
                this.a.a();
                return true;
            }

            protected final boolean c() {
                this.a.b();
                return true;
            }
        });
        this.b = frameLayout;
        this.l = (ImageView) frameLayout.findViewById(r.image);
        this.l.setImageDrawable(drawable);
        this.a = new u(this);
        this.a.registerDataSetObserver(new DataSetObserver(this) {
            final /* synthetic */ ActivityChooserView a;

            {
                this.a = r1;
            }

            public final void onChanged() {
                super.onChanged();
                this.a.e();
            }
        });
        Resources resources = context.getResources();
        this.n = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(p.abc_config_prefDialogWidth));
    }

    public void setActivityChooserModel(o oVar) {
        this.a.a(oVar);
        if (d().c()) {
            b();
            a();
        }
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.l.setImageDrawable(drawable);
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.l.setContentDescription(getContext().getString(i));
    }

    public void setProvider(gw gwVar) {
        this.d = gwVar;
    }

    final void a(int i) {
        if (this.a.e() != null) {
            getViewTreeObserver().addOnGlobalLayoutListener(this.o);
            boolean z = this.c.getVisibility() == 0;
            int c = this.a.c();
            if (i == BaseClientBuilder.API_PRIORITY_OTHER || c <= i + z) {
                this.a.a(false);
                this.a.a(i);
            } else {
                this.a.a(true);
                this.a.a(i - 1);
            }
            ListPopupWindow d = d();
            if (!d.c()) {
                if (this.g || !z) {
                    this.a.a(true, z);
                } else {
                    this.a.a(false, false);
                }
                d.d(Math.min(this.a.a(), this.n));
                d.a();
                if (this.d != null) {
                    this.d.a(true);
                }
                d.c.setContentDescription(getContext().getString(t.abc_activitychooserview_choose_application));
                d.c.setSelector(new ColorDrawable(0));
                return;
            }
            return;
        }
        throw new IllegalStateException("No data model. Did you call #setDataModel?");
    }

    public final boolean c() {
        return d().c();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        o e = this.a.e();
        if (e != null) {
            e.registerObserver(this.e);
        }
        this.q = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        o e = this.a.e();
        if (e != null) {
            e.unregisterObserver(this.e);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.o);
        }
        if (d().c()) {
            b();
        }
        this.q = false;
    }

    protected void onMeasure(int i, int i2) {
        View view = this.j;
        if (this.c.getVisibility() != 0) {
            i2 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), 1073741824);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.j.layout(0, 0, i3 - i, i4 - i2);
        if (!d().c()) {
            b();
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    public void setInitialActivityCount(int i) {
        this.h = i;
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.r = i;
    }

    final ListPopupWindow d() {
        if (this.p == null) {
            this.p = new ListPopupWindow(getContext());
            this.p.a(this.a);
            this.p.b((View) this);
            this.p.h();
            this.p.a(this.i);
            this.p.a(this.i);
        }
        return this.p;
    }

    final void e() {
        if (this.a.getCount() > 0) {
            this.b.setEnabled(true);
        } else {
            this.b.setEnabled(false);
        }
        int c = this.a.c();
        int d = this.a.d();
        if (c == 1 || (c > 1 && d > 0)) {
            this.c.setVisibility(0);
            ResolveInfo b = this.a.b();
            PackageManager packageManager = getContext().getPackageManager();
            this.m.setImageDrawable(b.loadIcon(packageManager));
            if (this.r != 0) {
                CharSequence loadLabel = b.loadLabel(packageManager);
                this.c.setContentDescription(getContext().getString(this.r, new Object[]{loadLabel}));
            }
        } else {
            this.c.setVisibility(8);
        }
        if (this.c.getVisibility() == 0) {
            this.j.setBackgroundDrawable(this.k);
        } else {
            this.j.setBackgroundDrawable(null);
        }
    }

    public final boolean a() {
        if (d().c() || !this.q) {
            return false;
        }
        this.g = false;
        a(this.h);
        return true;
    }

    public final boolean b() {
        if (d().c()) {
            d().b();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.o);
            }
        }
        return true;
    }
}
