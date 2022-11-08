package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.k;
import com.bumptech.glide.d;
import com.bumptech.glide.w;
import defpackage.alt;
import defpackage.alu;
import defpackage.bu;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class n implements Callback {
    private static final o i = new o() {
        public final w a(d dVar, i iVar, p pVar, Context context) {
            return new w(dVar, iVar, pVar, context);
        }
    };
    final Map<FragmentManager, l> a = new HashMap();
    final Map<k, SupportRequestManagerFragment> b = new HashMap();
    private volatile w c;
    private final Handler d;
    private final o e;
    private final bu<View, Fragment> f = new bu();
    private final bu<View, android.app.Fragment> g = new bu();
    private final Bundle h = new Bundle();

    public n(o oVar) {
        if (oVar == null) {
            oVar = i;
        }
        this.e = oVar;
        this.d = new Handler(Looper.getMainLooper(), this);
    }

    private w b(Context context) {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = this.e.a(d.a(context.getApplicationContext()), new b(), new h(), context.getApplicationContext());
                }
            }
        }
        return this.c;
    }

    public final w a(Context context) {
        while (context != null) {
            if (alu.c() && !(context instanceof Application)) {
                if (context instanceof FragmentActivity) {
                    return a((FragmentActivity) context);
                }
                if (context instanceof Activity) {
                    return a((Activity) context);
                }
                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
            }
            return b(context);
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    public final w a(FragmentActivity fragmentActivity) {
        if (alu.d()) {
            return a(fragmentActivity.getApplicationContext());
        }
        c(fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager(), null, d(fragmentActivity));
    }

    public final w a(Fragment fragment) {
        alt.a(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (alu.d()) {
            return a(fragment.getActivity().getApplicationContext());
        }
        return a(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
    }

    public final w a(Activity activity) {
        if (alu.d()) {
            return a(activity.getApplicationContext());
        }
        c(activity);
        return a((Context) activity, activity.getFragmentManager(), null, d(activity));
    }

    public final w a(View view) {
        if (alu.d()) {
            return a(view.getContext().getApplicationContext());
        }
        android.app.Fragment fragment;
        Activity activity;
        alt.a((Object) view, "Argument must not be null");
        alt.a(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Context context = view.getContext();
        while (true) {
            fragment = null;
            if (!(context instanceof Activity)) {
                if (!(context instanceof ContextWrapper)) {
                    activity = null;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                activity = (Activity) context;
                break;
            }
        }
        if (activity == null) {
            return a(view.getContext().getApplicationContext());
        }
        View findViewById;
        if (activity instanceof FragmentActivity) {
            Fragment fragment2;
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            this.f.clear();
            a(fragmentActivity.getSupportFragmentManager().f(), this.f);
            findViewById = fragmentActivity.findViewById(16908290);
            while (!view.equals(findViewById)) {
                fragment2 = (Fragment) this.f.get(view);
                if (fragment2 != null || !(view.getParent() instanceof View)) {
                    break;
                }
                view = (View) view.getParent();
            }
            this.f.clear();
            if (fragment2 != null) {
                return a(fragment2);
            }
            return a(activity);
        }
        this.g.clear();
        a(activity.getFragmentManager(), this.g);
        findViewById = activity.findViewById(16908290);
        while (!view.equals(findViewById)) {
            fragment = (android.app.Fragment) this.g.get(view);
            if (fragment != null || !(view.getParent() instanceof View)) {
                break;
            }
            view = (View) view.getParent();
        }
        this.g.clear();
        if (fragment == null) {
            return a(activity);
        }
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (alu.d() || VERSION.SDK_INT < 17) {
            return a(fragment.getActivity().getApplicationContext());
        } else {
            return a(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
        }
    }

    private static void a(Collection<Fragment> collection, Map<View, Fragment> map) {
        if (collection != null) {
            for (Fragment fragment : collection) {
                if (!(fragment == null || fragment.getView() == null)) {
                    map.put(fragment.getView(), fragment);
                    a(fragment.getChildFragmentManager().f(), (Map) map);
                }
            }
        }
    }

    @TargetApi(26)
    @Deprecated
    private void a(FragmentManager fragmentManager, bu<View, android.app.Fragment> buVar) {
        if (VERSION.SDK_INT >= 26) {
            for (android.app.Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    buVar.put(fragment.getView(), fragment);
                    a(fragment.getChildFragmentManager(), (bu) buVar);
                }
            }
            return;
        }
        b(fragmentManager, buVar);
    }

    @java.lang.Deprecated
    private void b(android.app.FragmentManager r5, defpackage.bu<android.view.View, android.app.Fragment> r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.bumptech.glide.manager.n.b(android.app.FragmentManager, bu):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = this;
        r0 = 0;
    L_0x0001:
        r1 = r4.h;
        r2 = "key";
        r3 = r0 + 1;
        r1.putInt(r2, r0);
        r0 = 0;
        r1 = r4.h;	 Catch:{ Exception -> 0x0014 }
        r2 = "key";	 Catch:{ Exception -> 0x0014 }
        r1 = r5.getFragment(r1, r2);	 Catch:{ Exception -> 0x0014 }
        r0 = r1;
    L_0x0014:
        if (r0 == 0) goto L_0x0032;
    L_0x0016:
        r1 = r0.getView();
        if (r1 == 0) goto L_0x0030;
    L_0x001c:
        r1 = r0.getView();
        r6.put(r1, r0);
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 17;
        if (r1 < r2) goto L_0x0030;
    L_0x0029:
        r0 = r0.getChildFragmentManager();
        r4.a(r0, r6);
    L_0x0030:
        r0 = r3;
        goto L_0x0001;
    L_0x0032:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.n.b(android.app.FragmentManager, bu):void");
    }

    @TargetApi(17)
    private static void c(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @Deprecated
    final l b(Activity activity) {
        return a(activity.getFragmentManager(), null, d(activity));
    }

    private l a(FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        l lVar = (l) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (lVar == null) {
            lVar = (l) this.a.get(fragmentManager);
            if (lVar == null) {
                lVar = new l();
                lVar.a(fragment);
                if (z) {
                    lVar.a().a();
                }
                this.a.put(fragmentManager, lVar);
                fragmentManager.beginTransaction().add(lVar, "com.bumptech.glide.manager").commitAllowingStateLoss();
                this.d.obtainMessage(1, fragmentManager).sendToTarget();
            }
        }
        return lVar;
    }

    @Deprecated
    private w a(Context context, FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        l a = a(fragmentManager, fragment, z);
        w b = a.b();
        if (b != null) {
            return b;
        }
        b = this.e.a(d.a(context), a.a(), a.c(), context);
        a.a(b);
        return b;
    }

    final SupportRequestManagerFragment b(FragmentActivity fragmentActivity) {
        return a(fragmentActivity.getSupportFragmentManager(), null, d(fragmentActivity));
    }

    private static boolean d(Activity activity) {
        return !activity.isFinishing();
    }

    private SupportRequestManagerFragment a(k kVar, Fragment fragment, boolean z) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) kVar.a("com.bumptech.glide.manager");
        if (supportRequestManagerFragment == null) {
            supportRequestManagerFragment = (SupportRequestManagerFragment) this.b.get(kVar);
            if (supportRequestManagerFragment == null) {
                supportRequestManagerFragment = new SupportRequestManagerFragment();
                supportRequestManagerFragment.a(fragment);
                if (z) {
                    supportRequestManagerFragment.a().a();
                }
                this.b.put(kVar, supportRequestManagerFragment);
                kVar.a().a((Fragment) supportRequestManagerFragment, "com.bumptech.glide.manager").e();
                this.d.obtainMessage(2, kVar).sendToTarget();
            }
        }
        return supportRequestManagerFragment;
    }

    private w a(Context context, k kVar, Fragment fragment, boolean z) {
        SupportRequestManagerFragment a = a(kVar, fragment, z);
        w b = a.b();
        if (b != null) {
            return b;
        }
        b = this.e.a(d.a(context), a.a(), a.c(), context);
        a.a(b);
        return b;
    }

    public final boolean handleMessage(Message message) {
        Object obj = null;
        boolean z = true;
        Object remove;
        switch (message.what) {
            case 1:
                obj = (FragmentManager) message.obj;
                remove = this.a.remove(obj);
                break;
            case 2:
                obj = (k) message.obj;
                remove = this.b.remove(obj);
                break;
            default:
                z = false;
                remove = null;
                break;
        }
        if (z && remove == null && Log.isLoggable("RMRetriever", 5)) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: ".concat(String.valueOf(obj)));
        }
        return z;
    }
}
