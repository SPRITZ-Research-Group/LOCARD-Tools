package com.facebook.react.bridge;

import android.content.Context;

public abstract class o {
    public abstract String a(CatalystInstanceImpl catalystInstanceImpl);

    public static o a(final Context context, final String assetUrl) {
        return new o() {
            final /* synthetic */ boolean c = false;

            public final String a(CatalystInstanceImpl instance) {
                instance.loadScriptFromAssets(context.getAssets(), assetUrl, this.c);
                return assetUrl;
            }
        };
    }
}
