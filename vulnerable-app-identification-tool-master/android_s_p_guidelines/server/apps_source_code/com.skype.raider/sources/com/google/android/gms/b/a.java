package com.google.android.gms.b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.e;
import com.google.android.gms.common.internal.h;
import java.util.LinkedList;

public abstract class a<T extends c> {
    private T a;
    private Bundle b;
    private LinkedList<a> c;
    private final e<T> d = new g(this);

    private interface a {
        int a();

        void b();
    }

    private final void a(int i) {
        while (!this.c.isEmpty() && ((a) this.c.getLast()).a() >= i) {
            this.c.removeLast();
        }
    }

    public static void a(FrameLayout frameLayout) {
        e a = GoogleApiAvailability.a();
        Context context = frameLayout.getContext();
        int a2 = a.a(context);
        CharSequence c = h.c(context, a2);
        CharSequence e = h.e(context, a2);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(c);
        linearLayout.addView(textView);
        Intent a3 = a.a(context, a2, null);
        if (a3 != null) {
            View button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(e);
            linearLayout.addView(button);
            button.setOnClickListener(new i(context, a3));
        }
    }

    private final void a(a aVar) {
        if (this.a != null) {
            aVar.b();
            return;
        }
        if (this.c == null) {
            this.c = new LinkedList();
        }
        this.c.add(aVar);
        a(this.d);
    }

    public final T a() {
        return this.a;
    }

    protected abstract void a(e<T> eVar);

    public final void b() {
        a(new h(this));
    }

    public final void c() {
        a(new j(this));
    }

    public final void d() {
        if (this.a != null) {
            this.a.b();
        } else {
            a(5);
        }
    }

    public final void e() {
        if (this.a != null) {
            this.a.c();
        } else {
            a(1);
        }
    }
}
