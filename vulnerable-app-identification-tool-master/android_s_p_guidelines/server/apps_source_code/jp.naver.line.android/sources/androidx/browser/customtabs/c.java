package androidx.browser.customtabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.a;

public final class c {
    public final Intent a;
    public final Bundle b;

    public final void a(Context context, Uri uri) {
        this.a.setData(uri);
        a.a(context, this.a, this.b);
    }

    c(Intent intent, Bundle bundle) {
        this.a = intent;
        this.b = bundle;
    }
}
