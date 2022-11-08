package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.FacebookActivity;
import com.facebook.n;
import com.facebook.s;

public final class l {
    public static boolean a(k kVar) {
        return b(kVar).b() != -1;
    }

    public static void a(a aVar, n nVar) {
        bn.b(s.f());
        Intent intent = new Intent();
        intent.setClass(s.f(), FacebookActivity.class);
        intent.setAction(FacebookActivity.a);
        av.a(intent, aVar.b().toString(), null, av.a(), av.a(nVar));
        aVar.a(intent);
    }

    public static void a(a aVar, String str, Bundle bundle) {
        bn.b(s.f());
        bn.a(s.f());
        Bundle bundle2 = new Bundle();
        bundle2.putString("action", str);
        bundle2.putBundle("params", bundle);
        Intent intent = new Intent();
        av.a(intent, aVar.b().toString(), str, av.a(), bundle2);
        intent.setClass(s.f(), FacebookActivity.class);
        intent.setAction("FacebookDialogFragment");
        aVar.a(intent);
    }

    public static void a(a aVar, m mVar, k kVar) {
        Context f = s.f();
        String a = kVar.a();
        bb b = b(kVar);
        int b2 = b.b();
        if (b2 != -1) {
            Bundle a2;
            if (av.a(b2)) {
                a2 = mVar.a();
            } else {
                a2 = mVar.b();
            }
            if (a2 == null) {
                a2 = new Bundle();
            }
            Intent a3 = av.a(f, aVar.b().toString(), a, b, a2);
            if (a3 != null) {
                aVar.a(a3);
                return;
            }
            throw new n("Unable to create Intent; this likely means theFacebook app is not installed.");
        }
        throw new n("Cannot present this dialog. This likely means that the Facebook app is not installed.");
    }

    private static bb b(k kVar) {
        String j = s.j();
        String a = kVar.a();
        return av.a(a, a(j, a, kVar));
    }

    private static int[] a(String str, String str2, k kVar) {
        t a = s.a(str, str2, kVar.name());
        if (a != null) {
            return a.d();
        }
        return new int[]{kVar.b()};
    }
}
