package com.facebook.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.adapters.s;
import com.facebook.ads.internal.adapters.u;
import com.facebook.ads.internal.view.c.a.f;
import com.facebook.ads.internal.view.f.b.z;
import com.facebook.ads.internal.view.h;
import com.facebook.ads.internal.view.m;
import com.facebook.ads.internal.view.n;
import com.facebook.ads.internal.view.o;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AudienceNetworkActivity extends Activity {
    private final List<a> a = new ArrayList();
    private RelativeLayout b;
    private int c = -1;
    private String d;
    private com.facebook.ads.internal.t.b.a e;
    private long f;
    private long g;
    private int h;
    private com.facebook.ads.internal.view.a i;
    private com.facebook.ads.internal.view.b.c j;

    public interface a {
        boolean a();
    }

    private static class b implements com.facebook.ads.internal.view.a.a {
        final WeakReference<AudienceNetworkActivity> a;

        private b(AudienceNetworkActivity audienceNetworkActivity) {
            this.a = new WeakReference(audienceNetworkActivity);
        }

        /* synthetic */ b(AudienceNetworkActivity audienceNetworkActivity, byte b) {
            this(audienceNetworkActivity);
        }

        public final void a(View view) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b.addView(view);
            }
        }

        public void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str);
            }
        }

        public final void a(String str, com.facebook.ads.internal.j.d dVar) {
            if (this.a.get() != null) {
                AudienceNetworkActivity.a((AudienceNetworkActivity) this.a.get(), str, dVar);
            }
        }
    }

    private static class c {
        private final AudienceNetworkActivity a;
        private final Intent b;
        private final com.facebook.ads.internal.m.c c;

        private c(AudienceNetworkActivity audienceNetworkActivity, Intent intent, com.facebook.ads.internal.m.c cVar) {
            this.a = audienceNetworkActivity;
            this.b = intent;
            this.c = cVar;
        }

        /* synthetic */ c(AudienceNetworkActivity audienceNetworkActivity, Intent intent, com.facebook.ads.internal.m.c cVar, byte b) {
            this(audienceNetworkActivity, intent, cVar);
        }

        static /* synthetic */ com.facebook.ads.internal.view.a a(c cVar) {
            k kVar = (k) cVar.b.getSerializableExtra("rewardedVideoAdDataBundle");
            return kVar.e().i() != null ? new n(cVar.a, cVar.c, new e(cVar.a, (byte) 0), kVar) : new o(cVar.a, cVar.c, new com.facebook.ads.internal.view.f.a(cVar.a), new e(cVar.a, (byte) 0), kVar);
        }

        private void a(com.facebook.ads.internal.view.a aVar) {
            aVar.setListener(new b(this.a, (byte) 0));
        }

        private boolean a() {
            return this.b.getBooleanExtra("useCache", false);
        }

        private g b() {
            return (g) this.b.getSerializableExtra("ad_data_bundle");
        }

        static /* synthetic */ com.facebook.ads.internal.view.a a(c cVar, RelativeLayout relativeLayout) {
            com.facebook.ads.internal.view.a mVar = new m(cVar.a, cVar.c, new b(cVar.a, (byte) 0));
            mVar.a((View) relativeLayout);
            mVar.a(cVar.b.getIntExtra("video_time_polling_interval", 200));
            return mVar;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a d(c cVar) {
            com.facebook.ads.internal.view.a a = s.a(cVar.b.getStringExtra("uniqueId"));
            if (a == null) {
                return null;
            }
            cVar.a(a);
            return a;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a e(c cVar) {
            com.facebook.ads.internal.view.a hVar = new h(cVar.a, cVar.c, cVar.b(), cVar.a() ? new com.facebook.ads.internal.d.b(cVar.a) : null);
            cVar.a(hVar);
            return hVar;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a f(c cVar) {
            com.facebook.ads.internal.view.a gVar = new com.facebook.ads.internal.view.g(cVar.a, cVar.b(), cVar.c);
            cVar.a(gVar);
            return gVar;
        }

        static /* synthetic */ com.facebook.ads.internal.view.a g(c cVar) {
            com.facebook.ads.internal.view.a fVar = new f(cVar.a, cVar.c, cVar.a() ? new com.facebook.ads.internal.d.b(cVar.a) : null);
            cVar.a(fVar);
            return fVar;
        }
    }

    private class d implements OnLongClickListener {
        final /* synthetic */ AudienceNetworkActivity a;

        private d(AudienceNetworkActivity audienceNetworkActivity) {
            this.a = audienceNetworkActivity;
        }

        /* synthetic */ d(AudienceNetworkActivity audienceNetworkActivity, byte b) {
            this(audienceNetworkActivity);
        }

        public final boolean onLongClick(View view) {
            boolean z = false;
            if (!(this.a.j == null || this.a.b == null)) {
                this.a.j.setBounds(0, 0, this.a.b.getWidth(), this.a.b.getHeight());
                com.facebook.ads.internal.view.b.c a = this.a.j;
                if (!this.a.j.a()) {
                    z = true;
                }
                a.a(z);
            }
            return true;
        }
    }

    private static class e extends b {
        private e(AudienceNetworkActivity audienceNetworkActivity) {
            super(audienceNetworkActivity, (byte) 0);
        }

        /* synthetic */ e(AudienceNetworkActivity audienceNetworkActivity, byte b) {
            this(audienceNetworkActivity);
        }

        public final void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str);
                String a = z.REWARDED_VIDEO_END_ACTIVITY.a();
                String a2 = z.REWARDED_VIDEO_ERROR.a();
                if (str.equals(a) || str.equals(a2)) {
                    ((AudienceNetworkActivity) this.a.get()).finish();
                }
            }
        }
    }

    private void a(String str) {
        android.support.v4.content.c.a((Context) this).a(new Intent(str + ":" + this.d));
    }

    public final void a(a aVar) {
        this.a.add(aVar);
    }

    public final void b(a aVar) {
        this.a.remove(aVar);
    }

    public void finish() {
        if (!isFinishing()) {
            if (this.e == com.facebook.ads.internal.t.b.a.REWARDED_VIDEO) {
                a(z.REWARDED_VIDEO_CLOSED.a());
            } else {
                a("com.facebook.ads.interstitial.dismissed");
            }
            super.finish();
        }
    }

    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        this.g += currentTimeMillis - this.f;
        this.f = currentTimeMillis;
        if (this.g > ((long) this.h)) {
            Object obj;
            Object obj2 = null;
            Iterator it = this.a.iterator();
            while (true) {
                obj = obj2;
                if (!it.hasNext()) {
                    break;
                }
                obj2 = ((a) it.next()).a() ? 1 : obj;
            }
            if (obj == null) {
                super.onBackPressed();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.i instanceof u) {
            ((u) this.i).a(configuration);
        } else if (this.i instanceof o) {
            ((o) this.i).onConfigurationChanged(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        com.facebook.ads.internal.view.a a;
        super.onCreate(bundle);
        com.facebook.ads.internal.q.a.d.a();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.b = new RelativeLayout(this);
        com.facebook.ads.internal.q.a.u.a(this.b, -16777216);
        setContentView(this.b, new LayoutParams(-1, -1));
        Intent intent = getIntent();
        if (bundle != null) {
            this.c = bundle.getInt("predefinedOrientationKey", -1);
            this.d = bundle.getString("uniqueId");
            this.e = (com.facebook.ads.internal.t.b.a) bundle.getSerializable("viewType");
        } else {
            this.c = intent.getIntExtra("predefinedOrientationKey", -1);
            this.d = intent.getStringExtra("uniqueId");
            this.e = (com.facebook.ads.internal.t.b.a) intent.getSerializableExtra("viewType");
            this.h = intent.getIntExtra("skipAfterSeconds", 0) * Constants.ONE_SECOND;
        }
        c cVar = new c(this, getIntent(), com.facebook.ads.internal.m.d.a((Context) this), (byte) 0);
        if (this.e != null) {
            switch (this.e) {
                case FULL_SCREEN_VIDEO:
                    a = c.a(cVar, this.b);
                    break;
                case REWARDED_VIDEO:
                    a = c.a(cVar);
                    break;
                case INTERSTITIAL_WEB_VIEW:
                    a = new com.facebook.ads.internal.view.e(cVar.a, cVar.c, new b(cVar.a, (byte) 0));
                    break;
                case BROWSER:
                    a = new com.facebook.ads.internal.view.b(cVar.a, cVar.c, new b(cVar.a, (byte) 0));
                    break;
                case INTERSTITIAL_OLD_NATIVE_VIDEO:
                    a = c.d(cVar);
                    break;
                case INTERSTITIAL_NATIVE_VIDEO:
                    a = c.e(cVar);
                    break;
                case INTERSTITIAL_NATIVE_IMAGE:
                    a = c.f(cVar);
                    break;
                case INTERSTITIAL_NATIVE_CAROUSEL:
                    a = c.g(cVar);
                    break;
            }
        }
        a = null;
        this.i = a;
        if (this.i == null) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(null, "Unable to infer viewType from intent or savedInstanceState"));
            a("com.facebook.ads.interstitial.error");
            finish();
            return;
        }
        int i;
        this.i.a(intent, bundle, this);
        a("com.facebook.ads.interstitial.displayed");
        this.f = System.currentTimeMillis();
        if (this.e == com.facebook.ads.internal.t.b.a.INTERSTITIAL_WEB_VIEW) {
            i = 1;
        } else {
            byte i2 = (byte) 0;
        }
        if (com.facebook.ads.internal.l.a.b((Context) this) && this.e != com.facebook.ads.internal.t.b.a.BROWSER) {
            this.j = new com.facebook.ads.internal.view.b.c();
            this.j.a(intent.getStringExtra("placementId"));
            this.j.b(getPackageName());
            long longExtra = intent.getLongExtra("requestTime", 0);
            if (longExtra != 0) {
                this.j.a(longExtra);
            }
            View textView = new TextView(this);
            textView.setText("Debug");
            textView.setTextColor(-1);
            com.facebook.ads.internal.q.a.u.a(textView, Color.argb(160, 0, 0, 0));
            textView.setPadding(5, 5, 5, 5);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.addRule(11, -1);
            textView.setLayoutParams(layoutParams);
            OnLongClickListener dVar = new d();
            textView.setOnLongClickListener(dVar);
            if (i2 != 0) {
                this.b.addView(textView);
            } else {
                this.b.setOnLongClickListener(dVar);
            }
            this.b.getOverlay().add(this.j);
        }
    }

    protected void onDestroy() {
        if (this.e == com.facebook.ads.internal.t.b.a.REWARDED_VIDEO) {
            a(z.REWARDED_VIDEO_ACTIVITY_DESTROYED.a());
        } else {
            a("com.facebook.ads.interstitial.activity_destroyed");
        }
        if (this.b != null) {
            this.b.removeAllViews();
        }
        if (this.i != null) {
            s.a(this.i);
            this.i.e();
            this.i = null;
        }
        if (this.j != null && com.facebook.ads.internal.l.a.b((Context) this)) {
            this.j.b();
        }
        super.onDestroy();
    }

    public void onPause() {
        this.g += System.currentTimeMillis() - this.f;
        if (this.i != null) {
            this.i.h();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f = System.currentTimeMillis();
        if (this.i != null) {
            this.i.i();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.i != null) {
            this.i.a(bundle);
        }
        bundle.putInt("predefinedOrientationKey", this.c);
        bundle.putString("uniqueId", this.d);
        bundle.putSerializable("viewType", this.e);
    }

    public void onStart() {
        super.onStart();
        if (this.c != -1) {
            setRequestedOrientation(this.c);
        }
    }

    static /* synthetic */ void a(AudienceNetworkActivity audienceNetworkActivity, String str, com.facebook.ads.internal.j.d dVar) {
        Intent intent = new Intent(str + ":" + audienceNetworkActivity.d);
        intent.putExtra(EventsEntry.COLUMN_NAME_EVENT, dVar);
        android.support.v4.content.c.a((Context) audienceNetworkActivity).a(intent);
    }
}
