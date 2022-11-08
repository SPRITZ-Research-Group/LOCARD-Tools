package com.facebook.internal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.net.Uri;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.common.d;
import com.facebook.common.g;
import com.facebook.common.h;
import com.facebook.n;
import com.facebook.p;
import com.facebook.s;
import com.linecorp.yuki.sensetime.SenseTimeSlam;
import java.util.Locale;

public class bo extends Dialog {
    private static final int a = h.com_facebook_activity_theme;
    private static volatile int m;
    private String b;
    private String c;
    private br d;
    private WebView e;
    private ProgressDialog f;
    private ImageView g;
    private FrameLayout h;
    private bs i;
    private boolean j;
    private boolean k;
    private boolean l;
    private LayoutParams n;

    protected static void a(android.content.Context r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bo.a(android.content.Context):void. bs: []
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
        if (r2 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r2.getPackageManager();	 Catch:{ NameNotFoundException -> 0x002d }
        r2 = r2.getPackageName();	 Catch:{ NameNotFoundException -> 0x002d }
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ NameNotFoundException -> 0x002d }
        r2 = r0.getApplicationInfo(r2, r1);	 Catch:{ NameNotFoundException -> 0x002d }
        if (r2 == 0) goto L_0x002c;
    L_0x0013:
        r0 = r2.metaData;
        if (r0 != 0) goto L_0x0018;
    L_0x0017:
        goto L_0x002c;
    L_0x0018:
        r0 = m;
        if (r0 != 0) goto L_0x002b;
    L_0x001c:
        r2 = r2.metaData;
        r0 = "com.facebook.sdk.WebDialogTheme";
        r2 = r2.getInt(r0);
        if (r2 == 0) goto L_0x0027;
    L_0x0026:
        goto L_0x0029;
    L_0x0027:
        r2 = a;
    L_0x0029:
        m = r2;
    L_0x002b:
        return;
    L_0x002c:
        return;
    L_0x002d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bo.a(android.content.Context):void");
    }

    public static bo a(Context context, String str, Bundle bundle, int i, br brVar) {
        a(context);
        return new bo(context, str, bundle, i, brVar);
    }

    public final void a(br brVar) {
        this.d = brVar;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        if (this.e != null) {
            this.e.stopLoading();
        }
        if (!(this.k || this.f == null || !this.f.isShowing())) {
            this.f.dismiss();
        }
        super.dismiss();
    }

    protected void onStart() {
        super.onStart();
        if (this.i == null || this.i.getStatus() != Status.PENDING) {
            d();
            return;
        }
        this.i.execute(new Void[0]);
        this.f.show();
    }

    protected void onStop() {
        if (this.i != null) {
            this.i.cancel(true);
            this.f.dismiss();
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        this.k = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.k = false;
        if (bj.d(getContext()) && this.n != null && this.n.token == null) {
            this.n.token = getOwnerActivity().getWindow().getAttributes().token;
            new StringBuilder("Set token on onAttachedToWindow(): ").append(this.n.token);
            bj.a();
        }
        super.onAttachedToWindow();
    }

    public void onWindowAttributesChanged(LayoutParams layoutParams) {
        if (layoutParams.token == null) {
            this.n = layoutParams;
        }
        super.onWindowAttributesChanged(layoutParams);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new ProgressDialog(getContext());
        this.f.requestWindowFeature(1);
        this.f.setMessage(getContext().getString(g.com_facebook_loading));
        this.f.setCanceledOnTouchOutside(false);
        this.f.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ bo a;

            {
                this.a = r1;
            }

            public final void onCancel(DialogInterface dialogInterface) {
                this.a.cancel();
            }
        });
        requestWindowFeature(1);
        this.h = new FrameLayout(getContext());
        d();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        this.g = new ImageView(getContext());
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bo a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.cancel();
            }
        });
        this.g.setImageDrawable(getContext().getResources().getDrawable(d.com_facebook_close));
        this.g.setVisibility(4);
        if (this.b != null) {
            a((this.g.getDrawable().getIntrinsicWidth() / 2) + 1);
        }
        this.h.addView(this.g, new ViewGroup.LayoutParams(-2, -2));
        setContentView(this.h);
    }

    protected final void b(String str) {
        this.c = str;
    }

    protected Bundle a(String str) {
        Uri parse = Uri.parse(str);
        Bundle c = bj.c(parse.getQuery());
        c.putAll(bj.c(parse.getFragment()));
        return c;
    }

    protected final boolean a() {
        return this.j;
    }

    protected final boolean b() {
        return this.l;
    }

    protected final WebView c() {
        return this.e;
    }

    public final void d() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        getWindow().setLayout(Math.min(a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels, displayMetrics.density, 480, 800), displayMetrics.widthPixels), Math.min(a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.heightPixels : displayMetrics.widthPixels, displayMetrics.density, 800, SenseTimeSlam.MAX_PREVIEW_WIDTH_UPPER_S), displayMetrics.heightPixels));
    }

    private static int a(int i, float f, int i2, int i3) {
        int i4 = (int) (((float) i) / f);
        double d = 0.5d;
        if (i4 <= i2) {
            d = 1.0d;
        } else if (i4 < i3) {
            double d2 = (double) (i3 - i4);
            double d3 = (double) (i3 - i2);
            Double.isNaN(d2);
            Double.isNaN(d3);
            d = 0.5d + ((d2 / d3) * 0.5d);
        }
        double d4 = (double) i;
        Double.isNaN(d4);
        return (int) (d4 * d);
    }

    protected final void a(Bundle bundle) {
        if (this.d != null && !this.j) {
            this.j = true;
            this.d.a(bundle, null);
            dismiss();
        }
    }

    protected final void a(Throwable th) {
        if (this.d != null && !this.j) {
            n nVar;
            this.j = true;
            if (th instanceof n) {
                nVar = (n) th;
            } else {
                nVar = new n(th);
            }
            this.d.a(null, nVar);
            dismiss();
        }
    }

    public void cancel() {
        if (this.d != null && !this.j) {
            a(new p());
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void a(int i) {
        View linearLayout = new LinearLayout(getContext());
        this.e = new WebView(this, getContext()) {
            final /* synthetic */ bo a;

            public final void onWindowFocusChanged(boolean r1) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bo.3.onWindowFocusChanged(boolean):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
                /*
                r0 = this;
                super.onWindowFocusChanged(r1);	 Catch:{ NullPointerException -> 0x0004 }
                return;
            L_0x0004:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bo.3.onWindowFocusChanged(boolean):void");
            }
        };
        this.e.setVerticalScrollBarEnabled(false);
        this.e.setHorizontalScrollBarEnabled(false);
        this.e.setWebViewClient(new bq());
        this.e.getSettings().setJavaScriptEnabled(true);
        this.e.loadUrl(this.b);
        this.e.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.e.setVisibility(4);
        this.e.getSettings().setSavePassword(false);
        this.e.getSettings().setSaveFormData(false);
        this.e.setFocusable(true);
        this.e.setFocusableInTouchMode(true);
        this.e.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ bo a;

            {
                this.a = r1;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (!view.hasFocus()) {
                    view.requestFocus();
                }
                return false;
            }
        });
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.e);
        linearLayout.setBackgroundColor(-872415232);
        this.h.addView(linearLayout);
    }

    protected bo(Context context, String str) {
        bn.a();
        this(context, str, m);
    }

    private bo(Context context, String str, int i) {
        if (i == 0) {
            bn.a();
            i = m;
        }
        super(context, i);
        this.c = "fbconnect://success";
        this.j = false;
        this.k = false;
        this.l = false;
        this.b = str;
    }

    private bo(Context context, String str, Bundle bundle, int i, br brVar) {
        if (i == 0) {
            bn.a();
            i = m;
        }
        super(context, i);
        this.c = "fbconnect://success";
        this.j = false;
        this.k = false;
        this.l = false;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.c = bj.f(context) ? "fbconnect://chrome_os_success" : "fbconnect://success";
        bundle.putString("redirect_uri", this.c);
        bundle.putString("display", "touch");
        bundle.putString("client_id", s.j());
        bundle.putString("sdk", String.format(Locale.ROOT, "android-%s", new Object[]{s.h()}));
        this.d = brVar;
        if (str.equals(AppLovinEventTypes.USER_SHARED_LINK) && bundle.containsKey("media")) {
            this.i = new bs(this, str, bundle);
            return;
        }
        String a = bg.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s.g());
        stringBuilder.append("/dialog/");
        stringBuilder.append(str);
        this.b = bj.a(a, stringBuilder.toString(), bundle).toString();
    }
}
