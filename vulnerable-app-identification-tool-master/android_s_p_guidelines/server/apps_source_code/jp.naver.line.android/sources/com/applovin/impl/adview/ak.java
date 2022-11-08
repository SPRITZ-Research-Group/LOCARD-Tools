package com.applovin.impl.adview;

import android.content.Context;
import android.view.View;
import com.applovin.sdk.AppLovinSdk;

public abstract class ak extends View {
    protected final AppLovinSdk a;
    protected final Context b;

    ak(AppLovinSdk appLovinSdk, Context context) {
        super(context);
        this.b = context;
        this.a = appLovinSdk;
    }

    public static ak a(AppLovinSdk appLovinSdk, Context context, al alVar) {
        return alVar.equals(al.Invisible) ? new ch(appLovinSdk, context) : alVar.equals(al.WhiteXOnTransparentGrey) ? new cj(appLovinSdk, context) : new cq(appLovinSdk, context);
    }

    public abstract void a(int i);
}
