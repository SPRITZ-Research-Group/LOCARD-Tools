package com.applovin.impl.sdk;

import android.content.Context;
import android.widget.Toast;
import com.applovin.sdk.AppLovinSdkUtils;

public class be {
    private final AppLovinSdkImpl a;
    private final String b;
    private final Context c;

    public be(AppLovinSdkImpl appLovinSdkImpl, Context context, String str) {
        this.a = appLovinSdkImpl;
        this.b = str;
        this.c = context;
    }

    void a() {
        AppLovinSdkUtils.runOnUiThread(new bf(this));
    }

    void a(String str, Throwable th) {
        this.a.getLogger().userError("IncentivizedConfirmationManager", "Unable to show incentivized ad reward dialog. Have you defined com.applovin.adview.AppLovinConfirmationActivity in your manifest?", th);
        Toast.makeText(this.c, str, 1).show();
    }

    String b() {
        AppLovinSdkImpl appLovinSdkImpl;
        ec ecVar;
        if (this.b.equals("accepted")) {
            appLovinSdkImpl = this.a;
            ecVar = ea.aa;
        } else if (this.b.equals("quota_exceeded")) {
            appLovinSdkImpl = this.a;
            ecVar = ea.ab;
        } else if (this.b.equals("rejected")) {
            appLovinSdkImpl = this.a;
            ecVar = ea.ac;
        } else {
            appLovinSdkImpl = this.a;
            ecVar = ea.ad;
        }
        return (String) appLovinSdkImpl.get(ecVar);
    }
}
