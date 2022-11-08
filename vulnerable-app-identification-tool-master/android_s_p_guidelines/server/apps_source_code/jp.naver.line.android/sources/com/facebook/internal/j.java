package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.c;
import androidx.browser.customtabs.d;
import com.facebook.s;

public final class j {
    private Uri a;

    public j(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        String a = bg.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s.g());
        stringBuilder.append("/dialog/");
        stringBuilder.append(str);
        this.a = bj.a(a, stringBuilder.toString(), bundle);
    }

    public final void a(Activity activity, String str) {
        c d = new d().d();
        d.a.setPackage(str);
        d.a.addFlags(1073741824);
        d.a(activity, this.a);
    }
}
