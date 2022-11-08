package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.m.a;
import javax.annotation.Nullable;

public final class ah {
    private final Resources a;
    private final String b = this.a.getResourcePackageName(a.common_google_play_services_unknown_issue);

    public ah(Context context) {
        ab.a((Object) context);
        this.a = context.getResources();
    }

    @Nullable
    public final String a(String str) {
        int identifier = this.a.getIdentifier(str, "string", this.b);
        return identifier == 0 ? null : this.a.getString(identifier);
    }
}
