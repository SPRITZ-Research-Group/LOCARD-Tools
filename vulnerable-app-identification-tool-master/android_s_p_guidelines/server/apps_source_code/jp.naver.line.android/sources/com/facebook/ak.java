package com.facebook;

import android.content.Intent;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import defpackage.lj;

public final class ak {
    private static volatile ak a;
    private final lj b;
    private final aj c;
    private Profile d;

    private ak(lj ljVar, aj ajVar) {
        bn.a((Object) ljVar, "localBroadcastManager");
        bn.a((Object) ajVar, "profileCache");
        this.b = ljVar;
        this.c = ajVar;
    }

    static ak a() {
        if (a == null) {
            synchronized (ak.class) {
                if (a == null) {
                    a = new ak(lj.a(s.f()), new aj());
                }
            }
        }
        return a;
    }

    final Profile b() {
        return this.d;
    }

    final boolean c() {
        Profile a = this.c.a();
        if (a == null) {
            return false;
        }
        a(a, false);
        return true;
    }

    final void a(Profile profile) {
        a(profile, true);
    }

    private void a(Profile profile, boolean z) {
        Profile profile2 = this.d;
        this.d = profile;
        if (z) {
            if (profile != null) {
                this.c.a(profile);
            } else {
                this.c.b();
            }
        }
        if (!bj.a((Object) profile2, (Object) profile)) {
            a(profile2, profile);
        }
    }

    private void a(Profile profile, Profile profile2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_PROFILE", profile);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_PROFILE", profile2);
        this.b.a(intent);
    }
}
