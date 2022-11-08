package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.h.a;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.af;
import com.google.android.gms.location.ai;
import java.util.HashMap;
import java.util.Map;

public final class p {
    private final ad<n> a;
    private final Context b;
    private ContentProviderClient c = null;
    private boolean d = false;
    private final Map<a<Object>, u> e = new HashMap();
    private final Map<a<Object>, t> f = new HashMap();
    private final Map<a<Object>, q> g = new HashMap();

    public p(Context context, ad<n> adVar) {
        this.b = context;
        this.a = adVar;
    }

    public final void a() throws RemoteException {
        synchronized (this.e) {
            for (ai aiVar : this.e.values()) {
                if (aiVar != null) {
                    ((n) this.a.a()).a(zzbf.a(aiVar));
                }
            }
            this.e.clear();
        }
        synchronized (this.g) {
            for (af afVar : this.g.values()) {
                if (afVar != null) {
                    ((n) this.a.a()).a(zzbf.a(afVar));
                }
            }
            this.g.clear();
        }
        synchronized (this.f) {
            for (t tVar : this.f.values()) {
                if (tVar != null) {
                    ((n) this.a.a()).a(new zzo(2, null, tVar.asBinder(), null));
                }
            }
            this.f.clear();
        }
    }

    public final void a(LocationRequest locationRequest, PendingIntent pendingIntent, i iVar) throws RemoteException {
        this.a.b();
        ((n) this.a.a()).a(new zzbf(1, zzbd.a(locationRequest), null, pendingIntent, null, iVar != null ? iVar.asBinder() : null));
    }

    public final void b() throws RemoteException {
        if (this.d) {
            this.a.b();
            ((n) this.a.a()).m_();
            this.d = false;
        }
    }
}
