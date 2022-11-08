package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.j;
import com.google.android.exoplayer.SampleSource;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hs;
import defpackage.m;
import defpackage.r;
import defpackage.v;

final class AlertController {
    private int A;
    private boolean B = false;
    private CharSequence C;
    private Drawable D;
    private CharSequence E;
    private Drawable F;
    private CharSequence G;
    private Drawable H;
    private int I = 0;
    private Drawable J;
    private ImageView K;
    private TextView L;
    private TextView M;
    private View N;
    private int O;
    private int P;
    private boolean Q;
    private int R = 0;
    private final OnClickListener S = new OnClickListener(this) {
        final /* synthetic */ AlertController a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            Message obtain = (view != this.a.c || this.a.d == null) ? (view != this.a.e || this.a.f == null) ? (view != this.a.g || this.a.h == null) ? null : Message.obtain(this.a.h) : Message.obtain(this.a.f) : Message.obtain(this.a.d);
            if (obtain != null) {
                obtain.sendToTarget();
            }
            this.a.p.obtainMessage(1, this.a.a).sendToTarget();
        }
    };
    final r a;
    ListView b;
    Button c;
    Message d;
    Button e;
    Message f;
    Button g;
    Message h;
    NestedScrollView i;
    ListAdapter j;
    int k = -1;
    int l;
    int m;
    int n;
    int o;
    Handler p;
    private final Context q;
    private final Window r;
    private final int s;
    private CharSequence t;
    private CharSequence u;
    private View v;
    private int w;
    private int x;
    private int y;
    private int z;

    public class RecycleListView extends ListView {
        private final int a;
        private final int b;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, v.RecycleListView);
            this.b = obtainStyledAttributes.getDimensionPixelOffset(v.RecycleListView_paddingBottomNoButtons, -1);
            this.a = obtainStyledAttributes.getDimensionPixelOffset(v.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean z, boolean z2) {
            if (!z2 || !z) {
                setPadding(getPaddingLeft(), z ? getPaddingTop() : this.a, getPaddingRight(), z2 ? getPaddingBottom() : this.b);
            }
        }
    }

    public AlertController(Context context, r rVar, Window window) {
        this.q = context;
        this.a = rVar;
        this.r = window;
        this.p = new e(rVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, v.AlertDialog, m.alertDialogStyle, 0);
        this.O = obtainStyledAttributes.getResourceId(v.AlertDialog_android_layout, 0);
        this.P = obtainStyledAttributes.getResourceId(v.AlertDialog_buttonPanelSideLayout, 0);
        this.l = obtainStyledAttributes.getResourceId(v.AlertDialog_listLayout, 0);
        this.m = obtainStyledAttributes.getResourceId(v.AlertDialog_multiChoiceItemLayout, 0);
        this.n = obtainStyledAttributes.getResourceId(v.AlertDialog_singleChoiceItemLayout, 0);
        this.o = obtainStyledAttributes.getResourceId(v.AlertDialog_listItemLayout, 0);
        this.Q = obtainStyledAttributes.getBoolean(v.AlertDialog_showTitle, true);
        this.s = obtainStyledAttributes.getDimensionPixelSize(v.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        rVar.supportRequestWindowFeature(1);
    }

    private static boolean c(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (c(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public final void a(CharSequence charSequence) {
        this.t = charSequence;
        if (this.L != null) {
            this.L.setText(charSequence);
        }
    }

    public final void a(View view) {
        this.N = view;
    }

    public final void b(CharSequence charSequence) {
        this.u = charSequence;
        if (this.M != null) {
            this.M.setText(charSequence);
        }
    }

    public final void a(int i) {
        this.v = null;
        this.w = i;
        this.B = false;
    }

    public final void b(View view) {
        this.v = view;
        this.w = 0;
        this.B = false;
    }

    public final void a(View view, int i, int i2, int i3, int i4) {
        this.v = view;
        this.w = 0;
        this.B = true;
        this.x = i;
        this.y = i2;
        this.z = i3;
        this.A = i4;
    }

    public final void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (onClickListener != null) {
            message = this.p.obtainMessage(i, onClickListener);
        }
        switch (i) {
            case SampleSource.SAMPLE_READ /*-3*/:
                this.G = charSequence;
                this.h = message;
                this.H = drawable;
                return;
            case -2:
                this.E = charSequence;
                this.f = message;
                this.F = drawable;
                return;
            case -1:
                this.C = charSequence;
                this.d = message;
                this.D = drawable;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public final void b(int i) {
        this.J = null;
        this.I = i;
        if (this.K != null) {
            if (i != 0) {
                this.K.setVisibility(0);
                this.K.setImageResource(this.I);
                return;
            }
            this.K.setVisibility(8);
        }
    }

    public final void a(Drawable drawable) {
        this.J = drawable;
        this.I = 0;
        if (this.K != null) {
            if (drawable != null) {
                this.K.setVisibility(0);
                this.K.setImageDrawable(drawable);
                return;
            }
            this.K.setVisibility(8);
        }
    }

    public final int c(int i) {
        TypedValue typedValue = new TypedValue();
        this.q.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    private static ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    static void a(View view, View view2, View view3) {
        int i = 4;
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            if (view.canScrollVertically(1)) {
                i = 0;
            }
            view3.setVisibility(i);
        }
    }

    private static void a(Button button) {
        LayoutParams layoutParams = (LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    public final void a() {
        int i;
        int i2;
        int i3;
        if (this.P == 0 || this.R != 1) {
            i = this.O;
        } else {
            i = this.P;
        }
        this.a.setContentView(i);
        View findViewById = this.r.findViewById(r.parentPanel);
        View findViewById2 = findViewById.findViewById(r.topPanel);
        View findViewById3 = findViewById.findViewById(r.contentPanel);
        View findViewById4 = findViewById.findViewById(r.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById.findViewById(r.customPanel);
        int i4 = 0;
        View inflate = this.v != null ? this.v : this.w != 0 ? LayoutInflater.from(this.q).inflate(this.w, viewGroup, false) : null;
        Object obj = inflate != null ? 1 : null;
        if (obj == null || !c(inflate)) {
            this.r.setFlags(131072, 131072);
        }
        if (obj != null) {
            FrameLayout frameLayout = (FrameLayout) this.r.findViewById(r.custom);
            frameLayout.addView(inflate, new ViewGroup.LayoutParams(-1, -1));
            if (this.B) {
                frameLayout.setPadding(this.x, this.y, this.z, this.A);
            }
            if (this.b != null) {
                ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams()).g = BitmapDescriptorFactory.HUE_RED;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        inflate = viewGroup.findViewById(r.topPanel);
        View findViewById5 = viewGroup.findViewById(r.contentPanel);
        View findViewById6 = viewGroup.findViewById(r.buttonPanel);
        ViewGroup a = a(inflate, findViewById2);
        ViewGroup a2 = a(findViewById5, findViewById3);
        ViewGroup a3 = a(findViewById6, findViewById4);
        this.i = (NestedScrollView) this.r.findViewById(r.scrollView);
        this.i.setFocusable(false);
        this.i.setNestedScrollingEnabled(false);
        this.M = (TextView) a2.findViewById(16908299);
        if (this.M != null) {
            if (this.u != null) {
                this.M.setText(this.u);
            } else {
                this.M.setVisibility(8);
                this.i.removeView(this.M);
                if (this.b != null) {
                    ViewGroup viewGroup2 = (ViewGroup) this.i.getParent();
                    int indexOfChild = viewGroup2.indexOfChild(this.i);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView(this.b, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    a2.setVisibility(8);
                }
            }
        }
        this.c = (Button) a3.findViewById(16908313);
        this.c.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.C) && this.D == null) {
            this.c.setVisibility(8);
            i2 = 0;
        } else {
            this.c.setText(this.C);
            if (this.D != null) {
                this.D.setBounds(0, 0, this.s, this.s);
                this.c.setCompoundDrawables(this.D, null, null, null);
            }
            this.c.setVisibility(0);
            i2 = 1;
        }
        this.e = (Button) a3.findViewById(16908314);
        this.e.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.E) && this.F == null) {
            this.e.setVisibility(8);
        } else {
            this.e.setText(this.E);
            if (this.F != null) {
                this.F.setBounds(0, 0, this.s, this.s);
                this.e.setCompoundDrawables(this.F, null, null, null);
            }
            this.e.setVisibility(0);
            i2 |= 2;
        }
        this.g = (Button) a3.findViewById(16908315);
        this.g.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.G) && this.H == null) {
            this.g.setVisibility(8);
        } else {
            this.g.setText(this.G);
            if (this.D != null) {
                this.D.setBounds(0, 0, this.s, this.s);
                this.c.setCompoundDrawables(this.D, null, null, null);
            }
            this.g.setVisibility(0);
            i2 |= 4;
        }
        Context context = this.q;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(m.alertDialogCenterButtons, typedValue, true);
        if ((typedValue.data != 0 ? 1 : null) != null) {
            if (i2 == 1) {
                a(this.c);
            } else if (i2 == 2) {
                a(this.e);
            } else if (i2 == 4) {
                a(this.g);
            }
        }
        if ((i2 != 0 ? 1 : null) == null) {
            a3.setVisibility(8);
        }
        if (this.N != null) {
            a.addView(this.N, 0, new ViewGroup.LayoutParams(-1, -2));
            this.r.findViewById(r.title_template).setVisibility(8);
        } else {
            this.K = (ImageView) this.r.findViewById(16908294);
            if ((TextUtils.isEmpty(this.t) ^ 1) == 0 || !this.Q) {
                this.r.findViewById(r.title_template).setVisibility(8);
                this.K.setVisibility(8);
                a.setVisibility(8);
            } else {
                this.L = (TextView) this.r.findViewById(r.alertTitle);
                this.L.setText(this.t);
                if (this.I != 0) {
                    this.K.setImageResource(this.I);
                } else if (this.J != null) {
                    this.K.setImageDrawable(this.J);
                } else {
                    this.L.setPadding(this.K.getPaddingLeft(), this.K.getPaddingTop(), this.K.getPaddingRight(), this.K.getPaddingBottom());
                    this.K.setVisibility(8);
                }
            }
        }
        Object obj2 = (viewGroup == null || viewGroup.getVisibility() == 8) ? null : 1;
        boolean z = (a == null || a.getVisibility() == 8) ? false : true;
        boolean z2 = (a3 == null || a3.getVisibility() == 8) ? false : true;
        if (!(z2 || a2 == null)) {
            findViewById5 = a2.findViewById(r.textSpacerNoButtons);
            if (findViewById5 != null) {
                findViewById5.setVisibility(0);
            }
        }
        if (z) {
            if (this.i != null) {
                this.i.setClipToPadding(true);
            }
            if (this.u == null && this.b == null) {
                findViewById2 = null;
            } else {
                findViewById2 = a.findViewById(r.titleDividerNoCustom);
            }
            if (findViewById2 != null) {
                findViewById2.setVisibility(0);
            }
        } else if (a2 != null) {
            findViewById2 = a2.findViewById(r.textSpacerNoTitle);
            if (findViewById2 != null) {
                findViewById2.setVisibility(0);
            }
        }
        if (this.b instanceof RecycleListView) {
            ((RecycleListView) this.b).setHasDecor(z, z2);
        }
        if (obj2 == null) {
            findViewById = this.b != null ? this.b : this.i;
            if (findViewById != null) {
                if (z2) {
                    i4 = 2;
                }
                i3 = z | i4;
                findViewById4 = this.r.findViewById(r.scrollIndicatorUp);
                inflate = this.r.findViewById(r.scrollIndicatorDown);
                if (VERSION.SDK_INT >= 23) {
                    hs.e(findViewById, i3);
                    if (findViewById4 != null) {
                        a2.removeView(findViewById4);
                    }
                    if (inflate != null) {
                        a2.removeView(inflate);
                    }
                } else {
                    if (findViewById4 != null && (i3 & 1) == 0) {
                        a2.removeView(findViewById4);
                        findViewById4 = null;
                    }
                    if (inflate != null && (i3 & 2) == 0) {
                        a2.removeView(inflate);
                        inflate = null;
                    }
                    if (!(findViewById4 == null && inflate == null)) {
                        if (this.u != null) {
                            this.i.setOnScrollChangeListener(new j(this) {
                                final /* synthetic */ AlertController c;

                                public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                                    AlertController.a(nestedScrollView, findViewById4, inflate);
                                }
                            });
                            this.i.post(new Runnable(this) {
                                final /* synthetic */ AlertController c;

                                public final void run() {
                                    AlertController.a(this.c.i, findViewById4, inflate);
                                }
                            });
                        } else if (this.b != null) {
                            this.b.setOnScrollListener(new OnScrollListener(this) {
                                final /* synthetic */ AlertController c;

                                public final void onScrollStateChanged(AbsListView absListView, int i) {
                                }

                                public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
                                    AlertController.a(absListView, findViewById4, inflate);
                                }
                            });
                            this.b.post(new Runnable(this) {
                                final /* synthetic */ AlertController c;

                                public final void run() {
                                    AlertController.a(this.c.b, findViewById4, inflate);
                                }
                            });
                        } else {
                            if (findViewById4 != null) {
                                a2.removeView(findViewById4);
                            }
                            if (inflate != null) {
                                a2.removeView(inflate);
                            }
                        }
                    }
                }
            }
        }
        ListView listView = this.b;
        if (listView != null && this.j != null) {
            listView.setAdapter(this.j);
            i3 = this.k;
            if (i3 >= 0) {
                listView.setItemChecked(i3, true);
                listView.setSelection(i3);
            }
        }
    }
}
