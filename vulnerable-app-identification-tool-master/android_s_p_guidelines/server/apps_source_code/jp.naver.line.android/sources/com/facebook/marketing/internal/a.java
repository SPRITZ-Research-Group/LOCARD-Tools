package com.facebook.marketing.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.s;
import defpackage.amm;

public final class a {
    private final amm a;

    public a(Context context, String str) {
        this.a = amm.b(context, str);
    }

    public final void a() {
        if (s.m()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "sdk_initialized");
            this.a.b("fb_codeless_debug", bundle);
        }
    }

    public final void b() {
        if (s.m()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "gesture_triggered");
            this.a.b("fb_codeless_debug", bundle);
        }
    }

    public final void c() {
        if (s.m()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "session_ready");
            this.a.b("fb_codeless_debug", bundle);
        }
    }

    public final void a(String str) {
        if (s.m()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "indexing_start");
            bundle.putString("_activity_name", str);
            this.a.b("fb_codeless_debug", bundle);
        }
    }

    public final void b(String str) {
        if (s.m()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "indexing_complete");
            bundle.putString("_activity_name", str);
            this.a.b("fb_codeless_debug", bundle);
        }
    }

    public final void c(String str) {
        if (s.m()) {
            Bundle bundle = new Bundle();
            bundle.putString("_codeless_action", "indexing_cancelled");
            bundle.putString("_activity_name", str);
            this.a.b("fb_codeless_debug", bundle);
        }
    }
}
