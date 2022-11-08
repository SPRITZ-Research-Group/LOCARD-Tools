package com.facebook.ads.internal.view.e;

import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.s;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.List;

public final class c extends a<e> {
    private final List<String> a;
    private final int b;

    c(List<String> list, int i) {
        this.a = list;
        this.b = i;
    }

    public final int a() {
        return this.a.size();
    }

    public final /* synthetic */ void a(s sVar, int i) {
        e eVar = (e) sVar;
        String str = (String) this.a.get(i);
        LayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.b * 4 : this.b, 0, i >= a() + -1 ? this.b * 4 : this.b, 0);
        eVar.t().setLayoutParams(marginLayoutParams);
        eVar.t().a(str);
    }

    public final /* synthetic */ s a(ViewGroup viewGroup, int i) {
        return new e(new d(viewGroup.getContext()));
    }
}
