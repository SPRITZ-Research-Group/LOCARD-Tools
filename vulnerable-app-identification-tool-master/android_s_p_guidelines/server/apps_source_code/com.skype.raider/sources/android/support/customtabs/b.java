package android.support.customtabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.e;
import java.util.ArrayList;

public final class b {
    @NonNull
    public final Intent a;
    @Nullable
    public final Bundle b;

    public static final class a {
        private final Intent a;
        private ArrayList<Bundle> b;
        private Bundle c;
        private ArrayList<Bundle> d;
        private boolean e;

        public a() {
            this((byte) 0);
        }

        private a(byte b) {
            this.a = new Intent("android.intent.action.VIEW");
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = true;
            Bundle bundle = new Bundle();
            e.a(bundle, "android.support.customtabs.extra.SESSION", null);
            this.a.putExtras(bundle);
        }

        public final b a() {
            if (this.b != null) {
                this.a.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", this.b);
            }
            if (this.d != null) {
                this.a.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", this.d);
            }
            this.a.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.e);
            return new b(this.a, this.c, (byte) 0);
        }
    }

    /* synthetic */ b(Intent x0, Bundle x1, byte b) {
        this(x0, x1);
    }

    private b(Intent intent, Bundle startAnimationBundle) {
        this.a = intent;
        this.b = startAnimationBundle;
    }
}
