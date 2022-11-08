package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.j;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.skype.android.video.hw.extension.SliqConstants;
import java.lang.ref.WeakReference;

final class AlertController {
    private boolean A = false;
    private CharSequence B;
    private CharSequence C;
    private CharSequence D;
    private int E = 0;
    private Drawable F;
    private ImageView G;
    private TextView H;
    private TextView I;
    private View J;
    private int K;
    private int L;
    private boolean M;
    private int N = 0;
    private final OnClickListener O = new OnClickListener(this) {
        final /* synthetic */ AlertController a;

        {
            this.a = this$0;
        }

        public final void onClick(View v) {
            Message m;
            if (v == this.a.c && this.a.d != null) {
                m = Message.obtain(this.a.d);
            } else if (v == this.a.e && this.a.f != null) {
                m = Message.obtain(this.a.f);
            } else if (v != this.a.g || this.a.h == null) {
                m = null;
            } else {
                m = Message.obtain(this.a.h);
            }
            if (m != null) {
                m.sendToTarget();
            }
            this.a.p.obtainMessage(1, this.a.a).sendToTarget();
        }
    };
    final AppCompatDialog a;
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
    private CharSequence s;
    private CharSequence t;
    private View u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public static class RecycleListView extends ListView {
        private final int a;
        private final int b;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public RecycleListView(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray ta = context.obtainStyledAttributes(attrs, j.RecycleListView);
            this.b = ta.getDimensionPixelOffset(j.RecycleListView_paddingBottomNoButtons, -1);
            this.a = ta.getDimensionPixelOffset(j.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean hasTitle, boolean hasButtons) {
            if (!hasButtons || !hasTitle) {
                setPadding(getPaddingLeft(), hasTitle ? getPaddingTop() : this.a, getPaddingRight(), hasButtons ? getPaddingBottom() : this.b);
            }
        }
    }

    public static class a {
        public int A;
        public boolean B = false;
        public boolean[] C;
        public boolean D;
        public boolean E;
        public int F = -1;
        public OnMultiChoiceClickListener G;
        public Cursor H;
        public String I;
        public String J;
        public OnItemSelectedListener K;
        public boolean L = true;
        public final Context a;
        public final LayoutInflater b;
        public int c = 0;
        public Drawable d;
        public int e = 0;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public DialogInterface.OnClickListener j;
        public CharSequence k;
        public DialogInterface.OnClickListener l;
        public CharSequence m;
        public DialogInterface.OnClickListener n;
        public boolean o;
        public OnCancelListener p;
        public OnDismissListener q;
        public OnKeyListener r;
        public CharSequence[] s;
        public ListAdapter t;
        public DialogInterface.OnClickListener u;
        public int v;
        public View w;
        public int x;
        public int y;
        public int z;

        /* renamed from: android.support.v7.app.AlertController$a$1 */
        class AnonymousClass1 extends ArrayAdapter<CharSequence> {
            final /* synthetic */ RecycleListView a;
            final /* synthetic */ a b;

            AnonymousClass1(a this$0, Context x0, int x1, CharSequence[] x3, RecycleListView recycleListView) {
                this.b = this$0;
                this.a = recycleListView;
                super(x0, x1, 16908308, x3);
            }

            public final View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (this.b.C != null && this.b.C[position]) {
                    this.a.setItemChecked(position, true);
                }
                return view;
            }
        }

        /* renamed from: android.support.v7.app.AlertController$a$2 */
        class AnonymousClass2 extends CursorAdapter {
            final /* synthetic */ RecycleListView a;
            final /* synthetic */ AlertController b;
            final /* synthetic */ a c;
            private final int d;
            private final int e;

            AnonymousClass2(a this$0, Context x0, Cursor x1, RecycleListView recycleListView, AlertController alertController) {
                this.c = this$0;
                this.a = recycleListView;
                this.b = alertController;
                super(x0, x1, false);
                Cursor cursor = getCursor();
                this.d = cursor.getColumnIndexOrThrow(this.c.I);
                this.e = cursor.getColumnIndexOrThrow(this.c.J);
            }

            public final void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.d));
                this.a.setItemChecked(cursor.getPosition(), cursor.getInt(this.e) == 1);
            }

            public final View newView(Context context, Cursor cursor, ViewGroup parent) {
                return this.c.b.inflate(this.b.m, parent, false);
            }
        }

        /* renamed from: android.support.v7.app.AlertController$a$3 */
        class AnonymousClass3 implements OnItemClickListener {
            final /* synthetic */ AlertController a;
            final /* synthetic */ a b;

            AnonymousClass3(a this$0, AlertController alertController) {
                this.b = this$0;
                this.a = alertController;
            }

            public final void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                this.b.u.onClick(this.a.a, position);
                if (!this.b.E) {
                    this.a.a.dismiss();
                }
            }
        }

        /* renamed from: android.support.v7.app.AlertController$a$4 */
        class AnonymousClass4 implements OnItemClickListener {
            final /* synthetic */ RecycleListView a;
            final /* synthetic */ AlertController b;
            final /* synthetic */ a c;

            AnonymousClass4(a this$0, RecycleListView recycleListView, AlertController alertController) {
                this.c = this$0;
                this.a = recycleListView;
                this.b = alertController;
            }

            public final void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                if (this.c.C != null) {
                    this.c.C[position] = this.a.isItemChecked(position);
                }
                this.c.G.onClick(this.b.a, position, this.a.isItemChecked(position));
            }
        }

        public a(Context context) {
            this.a = context;
            this.o = true;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }
    }

    private static final class b extends Handler {
        private WeakReference<DialogInterface> a;

        public b(DialogInterface dialog) {
            this.a = new WeakReference(dialog);
        }

        public final void handleMessage(Message msg) {
            switch (msg.what) {
                case SliqConstants.SLIQ_ERROR_INVALID_MEMORY /*-3*/:
                case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                case -1:
                    ((DialogInterface.OnClickListener) msg.obj).onClick((DialogInterface) this.a.get(), msg.what);
                    return;
                case 1:
                    ((DialogInterface) msg.obj).dismiss();
                    return;
                default:
                    return;
            }
        }
    }

    private static class c extends ArrayAdapter<CharSequence> {
        public c(Context context, int resource, CharSequence[] objects) {
            super(context, resource, 16908308, objects);
        }

        public final boolean hasStableIds() {
            return true;
        }

        public final long getItemId(int position) {
            return (long) position;
        }
    }

    public AlertController(Context context, AppCompatDialog di, Window window) {
        this.q = context;
        this.a = di;
        this.r = window;
        this.p = new b(di);
        TypedArray a = context.obtainStyledAttributes(null, j.AlertDialog, android.support.v7.appcompat.a.a.alertDialogStyle, 0);
        this.K = a.getResourceId(j.AlertDialog_android_layout, 0);
        this.L = a.getResourceId(j.AlertDialog_buttonPanelSideLayout, 0);
        this.l = a.getResourceId(j.AlertDialog_listLayout, 0);
        this.m = a.getResourceId(j.AlertDialog_multiChoiceItemLayout, 0);
        this.n = a.getResourceId(j.AlertDialog_singleChoiceItemLayout, 0);
        this.o = a.getResourceId(j.AlertDialog_listItemLayout, 0);
        this.M = a.getBoolean(j.AlertDialog_showTitle, true);
        a.recycle();
        di.a().c(1);
    }

    private static boolean c(View v) {
        if (v.onCheckIsTextEditor()) {
            return true;
        }
        if (!(v instanceof ViewGroup)) {
            return false;
        }
        ViewGroup vg = (ViewGroup) v;
        int i = vg.getChildCount();
        while (i > 0) {
            i--;
            if (c(vg.getChildAt(i))) {
                return true;
            }
        }
        return false;
    }

    public final void a() {
        int contentView;
        View view;
        boolean z;
        int indexOfChild;
        boolean z2;
        int i = 0;
        if (this.L == 0 || this.N != 1) {
            contentView = this.K;
        } else {
            contentView = this.L;
        }
        this.a.setContentView(contentView);
        View findViewById = this.r.findViewById(f.parentPanel);
        View findViewById2 = findViewById.findViewById(f.topPanel);
        View findViewById3 = findViewById.findViewById(f.contentPanel);
        View findViewById4 = findViewById.findViewById(f.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById.findViewById(f.customPanel);
        if (this.u != null) {
            view = this.u;
        } else if (this.v != 0) {
            view = LayoutInflater.from(this.q).inflate(this.v, viewGroup, false);
        } else {
            view = null;
        }
        if (view != null) {
            z = true;
        } else {
            z = false;
        }
        if (!(z && c(view))) {
            this.r.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.r.findViewById(f.custom);
            frameLayout.addView(view, new LayoutParams(-1, -1));
            if (this.A) {
                frameLayout.setPadding(this.w, this.x, this.y, this.z);
            }
            if (this.b != null) {
                ((android.support.v7.widget.LinearLayoutCompat.a) viewGroup.getLayoutParams()).g = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View findViewById5 = viewGroup.findViewById(f.topPanel);
        view = viewGroup.findViewById(f.contentPanel);
        View findViewById6 = viewGroup.findViewById(f.buttonPanel);
        ViewGroup a = a(findViewById5, findViewById2);
        ViewGroup a2 = a(view, findViewById3);
        ViewGroup a3 = a(findViewById6, findViewById4);
        this.i = (NestedScrollView) this.r.findViewById(f.scrollView);
        this.i.setFocusable(false);
        this.i.setNestedScrollingEnabled(false);
        this.I = (TextView) a2.findViewById(16908299);
        if (this.I != null) {
            if (this.t != null) {
                this.I.setText(this.t);
            } else {
                this.I.setVisibility(8);
                this.i.removeView(this.I);
                if (this.b != null) {
                    ViewGroup viewGroup2 = (ViewGroup) this.i.getParent();
                    indexOfChild = viewGroup2.indexOfChild(this.i);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView(this.b, indexOfChild, new LayoutParams(-1, -1));
                } else {
                    a2.setVisibility(8);
                }
            }
        }
        this.c = (Button) a3.findViewById(16908313);
        this.c.setOnClickListener(this.O);
        if (TextUtils.isEmpty(this.B)) {
            this.c.setVisibility(8);
            indexOfChild = 0;
        } else {
            this.c.setText(this.B);
            this.c.setVisibility(0);
            indexOfChild = 1;
        }
        this.e = (Button) a3.findViewById(16908314);
        this.e.setOnClickListener(this.O);
        if (TextUtils.isEmpty(this.C)) {
            this.e.setVisibility(8);
        } else {
            this.e.setText(this.C);
            this.e.setVisibility(0);
            indexOfChild |= 2;
        }
        this.g = (Button) a3.findViewById(16908315);
        this.g.setOnClickListener(this.O);
        if (TextUtils.isEmpty(this.D)) {
            this.g.setVisibility(8);
        } else {
            this.g.setText(this.D);
            this.g.setVisibility(0);
            indexOfChild |= 4;
        }
        Context context = this.q;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (indexOfChild == 1) {
                a(this.c);
            } else if (indexOfChild == 2) {
                a(this.e);
            } else if (indexOfChild == 4) {
                a(this.g);
            }
        }
        if (indexOfChild != 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            a3.setVisibility(8);
        }
        if (this.J != null) {
            a.addView(this.J, 0, new LayoutParams(-1, -2));
            this.r.findViewById(f.title_template).setVisibility(8);
        } else {
            this.G = (ImageView) this.r.findViewById(16908294);
            if ((!TextUtils.isEmpty(this.s)) && this.M) {
                this.H = (TextView) this.r.findViewById(f.alertTitle);
                this.H.setText(this.s);
                if (this.E != 0) {
                    this.G.setImageResource(this.E);
                } else if (this.F != null) {
                    this.G.setImageDrawable(this.F);
                } else {
                    this.H.setPadding(this.G.getPaddingLeft(), this.G.getPaddingTop(), this.G.getPaddingRight(), this.G.getPaddingBottom());
                    this.G.setVisibility(8);
                }
            } else {
                this.r.findViewById(f.title_template).setVisibility(8);
                this.G.setVisibility(8);
                a.setVisibility(8);
            }
        }
        boolean z3 = (viewGroup == null || viewGroup.getVisibility() == 8) ? false : true;
        if (a == null || a.getVisibility() == 8) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (a3 == null || a3.getVisibility() == 8) {
            z = false;
        } else {
            z = true;
        }
        if (!(z || a2 == null)) {
            findViewById = a2.findViewById(f.textSpacerNoButtons);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
        if (z2) {
            if (this.i != null) {
                this.i.setClipToPadding(true);
            }
            if (this.t == null && this.b == null) {
                findViewById = null;
            } else {
                findViewById = a.findViewById(f.titleDividerNoCustom);
            }
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        } else if (a2 != null) {
            findViewById = a2.findViewById(f.textSpacerNoTitle);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
        if (this.b instanceof RecycleListView) {
            ((RecycleListView) this.b).setHasDecor(z2, z);
        }
        if (!z3) {
            view = this.b != null ? this.b : this.i;
            if (view != null) {
                int i2;
                if (z2) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (z) {
                    i = 2;
                }
                i |= i2;
                findViewById5 = this.r.findViewById(f.scrollIndicatorUp);
                findViewById = this.r.findViewById(f.scrollIndicatorDown);
                if (VERSION.SDK_INT >= 23) {
                    ViewCompat.d(view, i);
                    if (findViewById5 != null) {
                        a2.removeView(findViewById5);
                    }
                    if (findViewById != null) {
                        a2.removeView(findViewById);
                    }
                } else {
                    if (findViewById5 != null && (i & 1) == 0) {
                        a2.removeView(findViewById5);
                        findViewById5 = null;
                    }
                    if (findViewById != null && (i & 2) == 0) {
                        a2.removeView(findViewById);
                        findViewById = null;
                    }
                    if (!(findViewById5 == null && findViewById == null)) {
                        if (this.t != null) {
                            this.i.setOnScrollChangeListener(new android.support.v4.widget.NestedScrollView.b(this) {
                                final /* synthetic */ AlertController c;

                                public final void a(NestedScrollView v) {
                                    AlertController.a(v, findViewById5, findViewById);
                                }
                            });
                            this.i.post(new Runnable(this) {
                                final /* synthetic */ AlertController c;

                                public final void run() {
                                    AlertController.a(this.c.i, findViewById5, findViewById);
                                }
                            });
                        } else if (this.b != null) {
                            this.b.setOnScrollListener(new OnScrollListener(this) {
                                final /* synthetic */ AlertController c;

                                public final void onScrollStateChanged(AbsListView view, int scrollState) {
                                }

                                public final void onScroll(AbsListView v, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                    AlertController.a(v, findViewById5, findViewById);
                                }
                            });
                            this.b.post(new Runnable(this) {
                                final /* synthetic */ AlertController c;

                                public final void run() {
                                    AlertController.a(this.c.b, findViewById5, findViewById);
                                }
                            });
                        } else {
                            if (findViewById5 != null) {
                                a2.removeView(findViewById5);
                            }
                            if (findViewById != null) {
                                a2.removeView(findViewById);
                            }
                        }
                    }
                }
            }
        }
        ListView listView = this.b;
        if (listView != null && this.j != null) {
            listView.setAdapter(this.j);
            int i3 = this.k;
            if (i3 >= 0) {
                listView.setItemChecked(i3, true);
                listView.setSelection(i3);
            }
        }
    }

    public final void a(CharSequence title) {
        this.s = title;
        if (this.H != null) {
            this.H.setText(title);
        }
    }

    public final void a(View customTitleView) {
        this.J = customTitleView;
    }

    public final void b(CharSequence message) {
        this.t = message;
        if (this.I != null) {
            this.I.setText(message);
        }
    }

    public final void a(int layoutResId) {
        this.u = null;
        this.v = layoutResId;
        this.A = false;
    }

    public final void b(View view) {
        this.u = view;
        this.v = 0;
        this.A = false;
    }

    public final void a(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.u = view;
        this.v = 0;
        this.A = true;
        this.w = viewSpacingLeft;
        this.x = viewSpacingTop;
        this.y = viewSpacingRight;
        this.z = viewSpacingBottom;
    }

    public final void a(int whichButton, CharSequence text, DialogInterface.OnClickListener listener, Message msg) {
        if (listener != null) {
            msg = this.p.obtainMessage(whichButton, listener);
        }
        switch (whichButton) {
            case SliqConstants.SLIQ_ERROR_INVALID_MEMORY /*-3*/:
                this.D = text;
                this.h = msg;
                return;
            case SliqConstants.SLIQ_ERROR_NOT_ENOUGH_DATA /*-2*/:
                this.C = text;
                this.f = msg;
                return;
            case -1:
                this.B = text;
                this.d = msg;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public final void b(int resId) {
        this.F = null;
        this.E = resId;
        if (this.G == null) {
            return;
        }
        if (resId != 0) {
            this.G.setVisibility(0);
            this.G.setImageResource(this.E);
            return;
        }
        this.G.setVisibility(8);
    }

    public final void a(Drawable icon) {
        this.F = icon;
        this.E = 0;
        if (this.G == null) {
            return;
        }
        if (icon != null) {
            this.G.setVisibility(0);
            this.G.setImageDrawable(icon);
            return;
        }
        this.G.setVisibility(8);
    }

    public final int c(int attrId) {
        TypedValue out = new TypedValue();
        this.q.getTheme().resolveAttribute(attrId, out, true);
        return out.resourceId;
    }

    @Nullable
    private static ViewGroup a(@Nullable View customPanel, @Nullable View defaultPanel) {
        if (customPanel == null) {
            if (defaultPanel instanceof ViewStub) {
                defaultPanel = ((ViewStub) defaultPanel).inflate();
            }
            return (ViewGroup) defaultPanel;
        }
        if (defaultPanel != null) {
            ViewParent parent = defaultPanel.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(defaultPanel);
            }
        }
        if (customPanel instanceof ViewStub) {
            customPanel = ((ViewStub) customPanel).inflate();
        }
        return (ViewGroup) customPanel;
    }

    static void a(View v, View upIndicator, View downIndicator) {
        int i = 0;
        if (upIndicator != null) {
            upIndicator.setVisibility(v.canScrollVertically(-1) ? 0 : 4);
        }
        if (downIndicator != null) {
            if (!v.canScrollVertically(1)) {
                i = 4;
            }
            downIndicator.setVisibility(i);
        }
    }

    private static void a(Button button) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.gravity = 1;
        params.weight = 0.5f;
        button.setLayoutParams(params);
    }
}
