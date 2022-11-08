package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class w extends View {
    private u a;

    public w(Context context, u uVar) {
        super(context);
        this.a = uVar;
        setLayoutParams(new LayoutParams(0, 0));
    }

    public void onWindowVisibilityChanged(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }
}
