package com.skype4life.syncadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.PermissionChecker;

public final class c {
    private final Context a;
    private final e b;

    public c(Context context) {
        this.a = context;
        this.b = new d(context);
    }

    public final boolean a(Intent intent) {
        return this.b.a(intent.resolveType(this.a)) && PermissionChecker.a(this.a, "android.permission.READ_CONTACTS") == 0;
    }

    public final Intent b(Intent intent) {
        String str;
        String mimeType = intent.resolveType(this.a);
        i nativeRawData = this.b.a(intent.getData());
        String str2 = "type";
        k a = this.b.a();
        if (a.b().equals(mimeType)) {
            str = "contactAudio";
        } else if (a.e().equals(mimeType)) {
            str = "contactVideo";
        } else if (a.g().equals(mimeType)) {
            str = "contactMessage";
        } else if (a.d().equals(mimeType)) {
            str = "contactPhone";
        } else {
            throw new IllegalArgumentException("No related mimeType");
        }
        intent.putExtra(str2, str);
        intent.putExtra("number", nativeRawData.c());
        intent.putExtra("skypeId", nativeRawData.b());
        return intent;
    }
}
