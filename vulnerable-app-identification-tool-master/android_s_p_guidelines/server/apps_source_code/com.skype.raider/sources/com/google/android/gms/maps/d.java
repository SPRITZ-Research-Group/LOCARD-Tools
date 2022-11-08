package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.h;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.maps.a.am;
import com.google.android.gms.maps.a.ap;
import com.google.android.gms.maps.model.b;
import com.google.android.gms.maps.model.g;
import javax.annotation.concurrent.GuardedBy;

public final class d {
    @GuardedBy("MapsInitializer.class")
    private static boolean a = false;

    public static synchronized int a(Context context) {
        int i = 0;
        synchronized (d.class) {
            ab.a((Object) context, (Object) "Context is null");
            if (!a) {
                try {
                    ap a = am.a(context);
                    b.a(a.a());
                    b.a(a.b());
                    a = true;
                } catch (RemoteException e) {
                    throw new g(e);
                } catch (h e2) {
                    i = e2.a;
                }
            }
        }
        return i;
    }
}
