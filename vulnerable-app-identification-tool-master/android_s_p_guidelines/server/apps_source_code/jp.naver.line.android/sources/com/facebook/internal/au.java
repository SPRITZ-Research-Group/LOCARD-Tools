package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.FacebookContentProvider;
import com.facebook.n;
import com.facebook.s;
import java.util.UUID;

public final class au {
    private final UUID a;
    private final String b;
    private final String c;
    private Bitmap d;
    private Uri e;
    private boolean f;
    private boolean g;

    /* synthetic */ au(UUID uuid, Bitmap bitmap, Uri uri, byte b) {
        this(uuid, bitmap, uri);
    }

    private au(UUID uuid, Bitmap bitmap, Uri uri) {
        String a;
        this.a = uuid;
        this.d = bitmap;
        this.e = uri;
        boolean z = true;
        if (uri != null) {
            String scheme = uri.getScheme();
            if (AppLovinEventTypes.USER_VIEWED_CONTENT.equalsIgnoreCase(scheme)) {
                this.f = true;
                if (uri.getAuthority() == null || uri.getAuthority().startsWith("media")) {
                    z = false;
                }
                this.g = z;
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                this.g = true;
            } else if (!bj.b(uri)) {
                throw new n("Unsupported scheme for media Uri : ".concat(String.valueOf(scheme)));
            }
        } else if (bitmap != null) {
            this.g = true;
        } else {
            throw new n("Cannot share media without a bitmap or Uri set");
        }
        this.c = !this.g ? null : UUID.randomUUID().toString();
        if (this.g) {
            a = FacebookContentProvider.a(s.j(), uuid, this.c);
        } else {
            a = this.e.toString();
        }
        this.b = a;
    }

    public final String a() {
        return this.b;
    }
}
