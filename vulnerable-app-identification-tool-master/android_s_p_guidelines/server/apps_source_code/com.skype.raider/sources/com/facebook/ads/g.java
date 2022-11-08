package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.r.h;
import java.util.ArrayList;
import java.util.List;

public final class g extends h {
    public g(Context context, String str) {
        super(context, str);
        a(h.NATIVE_UNKNOWN);
    }

    private g(e eVar) {
        super(eVar);
    }

    final List<g> a() {
        if (c().r() == null) {
            return null;
        }
        List<g> arrayList = new ArrayList();
        for (e gVar : c().r()) {
            arrayList.add(new g(gVar));
        }
        return arrayList;
    }

    public final void a(View view, MediaView mediaView, @Nullable AdIconView adIconView, @Nullable List<View> list) {
        if (mediaView != null) {
            mediaView.a(this);
        }
        if (adIconView != null) {
            adIconView.a(this);
        }
        if (list != null) {
            c().a(view, mediaView, list);
        } else {
            c().a(view, (com.facebook.ads.internal.n.g) mediaView);
        }
    }

    public final void a(View view, MediaView mediaView, @Nullable List<View> list) {
        a(view, mediaView, null, list);
    }
}
