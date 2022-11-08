package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.h;
import java.util.ArrayList;

public final class d {
    private final Intent a;
    private ArrayList<Bundle> b;
    private Bundle c;
    private ArrayList<Bundle> d;
    private boolean e;

    public d() {
        this((byte) 0);
    }

    private d(byte b) {
        this.a = new Intent("android.intent.action.VIEW");
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = true;
        Bundle bundle = new Bundle();
        h.a(bundle, "android.support.customtabs.extra.SESSION", null);
        this.a.putExtras(bundle);
    }

    public final d a(int i) {
        this.a.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", i);
        return this;
    }

    public final d a() {
        this.a.putExtra("android.support.customtabs.extra.ENABLE_URLBAR_HIDING", true);
        return this;
    }

    public final d b() {
        this.a.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
        return this;
    }

    public final d a(String str, PendingIntent pendingIntent) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        Bundle bundle = new Bundle();
        bundle.putString("android.support.customtabs.customaction.MENU_ITEM_TITLE", str);
        bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
        this.b.add(bundle);
        return this;
    }

    public final d c() {
        this.a.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", true);
        return this;
    }

    public final c d() {
        if (this.b != null) {
            this.a.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", this.b);
        }
        if (this.d != null) {
            this.a.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", this.d);
        }
        this.a.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.e);
        return new c(this.a, this.c);
    }
}
