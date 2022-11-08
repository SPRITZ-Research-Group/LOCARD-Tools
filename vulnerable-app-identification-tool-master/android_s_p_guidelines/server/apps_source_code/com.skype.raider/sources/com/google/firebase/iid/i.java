package com.google.firebase.iid;

import android.support.annotation.WorkerThread;
import android.support.v4.util.a;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.c.h;
import com.google.android.gms.c.j;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.annotation.concurrent.GuardedBy;

final class i {
    @GuardedBy("this")
    private final Map<Pair<String, String>, h<String>> a = new a();

    i() {
    }

    private static String a(l lVar, h<String> hVar) throws IOException {
        Exception e;
        try {
            Object a = lVar.a();
            hVar.a(a);
            return a;
        } catch (IOException e2) {
            e = e2;
            hVar.a(e);
            throw e;
        } catch (RuntimeException e3) {
            e = e3;
            hVar.a(e);
            throw e;
        }
    }

    private final synchronized l b(String str, String str2, l lVar) {
        l jVar;
        Pair pair = new Pair(str, str2);
        h hVar = (h) this.a.get(pair);
        if (hVar != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(pair);
                new StringBuilder(String.valueOf(valueOf).length() + 29).append("Joining ongoing request for: ").append(valueOf);
            }
            jVar = new j(hVar);
        } else {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf2 = String.valueOf(pair);
                new StringBuilder(String.valueOf(valueOf2).length() + 24).append("Making new request for: ").append(valueOf2);
            }
            h hVar2 = new h();
            this.a.put(pair, hVar2);
            jVar = new k(this, lVar, hVar2, pair);
        }
        return jVar;
    }

    private static String b(h<String> hVar) throws IOException {
        Throwable cause;
        try {
            return (String) j.a(hVar.a());
        } catch (ExecutionException e) {
            cause = e.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(cause);
            }
        } catch (Throwable cause2) {
            throw new IOException(cause2);
        }
    }

    final /* synthetic */ String a(l lVar, h hVar, Pair pair) throws IOException {
        try {
            String a = a(lVar, hVar);
            synchronized (this) {
                this.a.remove(pair);
            }
            return a;
        } catch (Throwable th) {
            synchronized (this) {
                this.a.remove(pair);
            }
        }
    }

    @WorkerThread
    final String a(String str, String str2, l lVar) throws IOException {
        return b(str, str2, lVar).a();
    }
}
