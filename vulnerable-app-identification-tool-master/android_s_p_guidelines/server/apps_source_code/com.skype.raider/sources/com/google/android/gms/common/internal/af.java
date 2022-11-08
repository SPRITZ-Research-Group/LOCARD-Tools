package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.b.d;
import com.google.android.gms.b.f;
import com.google.android.gms.b.f.a;

public final class af extends f<x> {
    private static final af a = new af();

    private af() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View a(Context context, int i, int i2) throws a {
        return a.b(context, i, i2);
    }

    private final View b(Context context, int i, int i2) throws a {
        try {
            SignInButtonConfig signInButtonConfig = new SignInButtonConfig(i, i2);
            return (View) d.a(((x) a(context)).a(d.a((Object) context), signInButtonConfig));
        } catch (Throwable e) {
            throw new a("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    public final /* bridge */ /* synthetic */ Object a(IBinder iBinder) {
        return x.a.a(iBinder);
    }
}
