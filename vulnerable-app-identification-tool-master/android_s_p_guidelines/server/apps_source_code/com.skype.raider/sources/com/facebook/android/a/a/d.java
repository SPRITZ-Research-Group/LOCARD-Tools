package com.facebook.android.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import com.facebook.crypto.a.b;
import com.facebook.crypto.b.a;
import com.facebook.crypto.f;

public final class d implements a {
    protected byte[] a;
    protected boolean b;
    private final f c;
    private final SharedPreferences d;
    private final b e;

    public d(Context context, f config) {
        String prefName;
        if (config == f.KEY_128) {
            prefName = "crypto";
        } else {
            prefName = "crypto." + String.valueOf(config);
        }
        this.d = context.getSharedPreferences(prefName, 0);
        this.e = new b();
        this.c = config;
    }

    public final synchronized byte[] a() throws b {
        byte[] bArr = null;
        synchronized (this) {
            if (!this.b) {
                String str = "cipher_key";
                int i = this.c.d;
                String string = this.d.getString(str, null);
                if (string == null) {
                    bArr = new byte[i];
                    this.e.nextBytes(bArr);
                    Editor edit = this.d.edit();
                    edit.putString(str, Base64.encodeToString(bArr, 0));
                    edit.commit();
                } else if (string != null) {
                    bArr = Base64.decode(string, 0);
                }
                this.a = bArr;
            }
            this.b = true;
            bArr = this.a;
        }
        return bArr;
    }

    public final byte[] b() throws b {
        byte[] iv = new byte[this.c.e];
        this.e.nextBytes(iv);
        return iv;
    }
}
