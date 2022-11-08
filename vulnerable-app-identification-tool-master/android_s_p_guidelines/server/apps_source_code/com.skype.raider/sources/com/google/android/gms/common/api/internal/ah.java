package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.q;
import java.util.Set;

@WorkerThread
public interface ah {
    void a(q qVar, Set<Scope> set);

    void b(ConnectionResult connectionResult);
}
