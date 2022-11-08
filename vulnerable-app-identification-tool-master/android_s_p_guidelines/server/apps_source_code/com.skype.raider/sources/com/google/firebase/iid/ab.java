package com.google.firebase.iid;

import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.android.gms.c.g;
import com.google.android.gms.c.h;
import com.google.firebase.a;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

final class ab implements ae {
    private final a a;
    private final FirebaseInstanceId b;
    private final f c;
    private final m d;
    private final ScheduledThreadPoolExecutor e = new ScheduledThreadPoolExecutor(1);

    ab(a aVar, FirebaseInstanceId firebaseInstanceId, f fVar) {
        this.a = aVar;
        this.b = firebaseInstanceId;
        this.c = fVar;
        this.d = new m(aVar.a(), fVar);
    }

    private final String a(Bundle bundle) throws IOException {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String string = bundle.getString("registration_id");
        if (string == null) {
            string = bundle.getString("unregistered");
            if (string == null) {
                string = bundle.getString("error");
                if ("RST".equals(string)) {
                    this.b.zzk();
                    throw new IOException("INSTANCE_ID_RESET");
                } else if (string != null) {
                    throw new IOException(string);
                } else {
                    string = String.valueOf(bundle);
                    new StringBuilder(String.valueOf(string).length() + 21).append("Unexpected response: ").append(string);
                    Throwable th = new Throwable();
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
            }
        }
        return string;
    }

    private final Bundle b(String str, String str2, Bundle bundle) {
        bundle.putString("scope", str2);
        bundle.putString("sender", str);
        bundle.putString("subtype", str);
        bundle.putString("appid", FirebaseInstanceId.zzf());
        bundle.putString("gmp_app_id", this.a.b().a());
        bundle.putString("gmsv", Integer.toString(this.c.d()));
        bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
        bundle.putString("app_ver", this.c.b());
        bundle.putString("app_ver_name", this.c.c());
        bundle.putString("cliv", "fiid-12451000");
        return bundle;
    }

    public final g<String> a(String str, String str2) {
        Bundle bundle = new Bundle();
        b(str, str2, bundle);
        h hVar = new h();
        this.e.execute(new ac(this, bundle, hVar));
        return hVar.a().a(this.e, new ad(this));
    }

    final String a(String str, String str2, Bundle bundle) throws IOException {
        b(str, str2, bundle);
        return a(this.d.a(bundle));
    }

    final /* synthetic */ void a(Bundle bundle, h hVar) {
        try {
            hVar.a(this.d.a(bundle));
        } catch (Exception e) {
            hVar.a(e);
        }
    }
}
