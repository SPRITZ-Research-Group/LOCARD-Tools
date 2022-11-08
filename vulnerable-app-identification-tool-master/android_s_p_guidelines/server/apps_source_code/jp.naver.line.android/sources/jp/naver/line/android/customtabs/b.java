package jp.naver.line.android.customtabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public abstract class b {
    private Context a;
    private Uri b;

    public void a(Intent intent, Bundle bundle) {
    }

    public abstract void a(Uri uri, c cVar);

    public void a(Bundle bundle) {
    }

    public abstract void a(c cVar);

    public void c() {
    }

    public void d() {
    }

    public final void a(Context context, Uri uri, Intent intent, Bundle bundle) {
        this.a = context;
        this.b = uri;
        a(intent, bundle);
    }

    public final Context a() {
        return this.a;
    }

    public final Uri b() {
        return this.b;
    }
}
