package com.facebook.ads.internal.view.c.a;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.g;

public final class c extends RecyclerView {
    public c(Context context) {
        super(context);
        g linearLayoutManager = new LinearLayoutManager(context, 0, false);
        linearLayoutManager.o();
        super.setLayoutManager(linearLayoutManager);
    }

    public final void setLayoutManager(g gVar) {
    }

    public final LinearLayoutManager t() {
        return (LinearLayoutManager) super.e();
    }

    public final /* bridge */ /* synthetic */ g e() {
        return (LinearLayoutManager) super.e();
    }
}
