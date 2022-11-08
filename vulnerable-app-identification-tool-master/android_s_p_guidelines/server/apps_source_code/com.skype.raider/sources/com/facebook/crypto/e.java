package com.facebook.crypto;

import com.facebook.crypto.b.a;
import com.facebook.crypto.c.c;
import com.facebook.crypto.cipher.NativeGCMCipher;
import com.facebook.crypto.d.b;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class e implements d {
    private final b a;
    private final a b;
    private final f c;

    public e(b mNativeLibrary, a mKeyChain, f config) {
        this.a = mNativeLibrary;
        this.b = mKeyChain;
        this.c = config;
    }

    public final OutputStream a(OutputStream cipherStream, g entity) throws IOException, com.facebook.crypto.a.a, com.facebook.crypto.a.b {
        cipherStream.write(1);
        cipherStream.write(this.c.c);
        byte[] iv = this.b.b();
        NativeGCMCipher gcmCipher = new NativeGCMCipher(this.a);
        gcmCipher.a(this.b.a(), iv);
        cipherStream.write(iv);
        a(gcmCipher, (byte) 1, this.c.c, entity.a());
        return new c(cipherStream, gcmCipher, this.c.f);
    }

    public final InputStream a(InputStream is, g entity) throws IOException, com.facebook.crypto.a.a, com.facebook.crypto.a.b {
        boolean z;
        boolean z2 = true;
        byte cryptoVersion = (byte) is.read();
        byte cipherID = (byte) is.read();
        if (cryptoVersion == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        com.facebook.crypto.d.a.b(z, "Unexpected crypto version " + cryptoVersion);
        if (cipherID != this.c.c) {
            z2 = false;
        }
        com.facebook.crypto.d.a.b(z2, "Unexpected cipher ID " + cipherID);
        byte[] iv = new byte[this.c.e];
        new DataInputStream(is).readFully(iv);
        NativeGCMCipher gcmCipher = new NativeGCMCipher(this.a);
        gcmCipher.b(this.b.a(), iv);
        a(gcmCipher, cryptoVersion, cipherID, entity.a());
        return new com.facebook.crypto.c.b(is, gcmCipher, this.c.f);
    }

    private static void a(NativeGCMCipher gcmCipher, byte cryptoVersion, byte cipherID, byte[] entityBytes) throws com.facebook.crypto.cipher.a {
        byte[] cipherIDBytes = new byte[]{cipherID};
        gcmCipher.a(new byte[]{cryptoVersion}, 1);
        gcmCipher.a(cipherIDBytes, 1);
        gcmCipher.a(entityBytes, entityBytes.length);
    }

    public final int a() {
        return (this.c.e + 2) + this.c.f;
    }
}
