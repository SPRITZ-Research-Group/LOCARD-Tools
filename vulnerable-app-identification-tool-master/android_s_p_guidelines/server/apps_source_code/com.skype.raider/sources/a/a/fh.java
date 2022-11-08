package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.f.i;

public final class fh {
    private final SharedPreferences a;

    public fh(Context context) {
        this.a = context.getSharedPreferences("com.appboy.offline.storagemap", 0);
    }

    public final String a() {
        return this.a.getString("last_user", "");
    }

    public final void a(String str) {
        i.a(str);
        Editor edit = this.a.edit();
        edit.putString("last_user", str);
        edit.apply();
    }

    public final void b(String str) {
        i.a(str);
        Editor edit = this.a.edit();
        edit.putString("default_user", str);
        edit.putString("last_user", str);
        edit.apply();
    }
}
