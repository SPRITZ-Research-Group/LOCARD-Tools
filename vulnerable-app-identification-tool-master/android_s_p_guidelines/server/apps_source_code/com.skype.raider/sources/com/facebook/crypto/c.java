package com.facebook.crypto;

import com.facebook.crypto.b.a;
import com.facebook.crypto.d.b;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class c {
    private final a a;
    private final b b;
    private final d c;

    public c(a keyChain, b nativeCryptoLibrary, f config) {
        this.a = new a(keyChain, config);
        this.b = nativeCryptoLibrary;
        this.c = new e(this.b, this.a, config);
    }

    public final boolean a() {
        try {
            this.b.a();
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public final byte[] a(byte[] plainTextBytes, g entity) throws com.facebook.crypto.a.b, com.facebook.crypto.a.a, IOException {
        OutputStream outputStream = new com.facebook.crypto.c.a(plainTextBytes.length + b());
        OutputStream cipherStream = this.c.a(outputStream, entity);
        cipherStream.write(plainTextBytes);
        cipherStream.close();
        return outputStream.a();
    }

    public final byte[] b(byte[] cipherTextBytes, g entity) throws com.facebook.crypto.a.b, com.facebook.crypto.a.a, IOException {
        int cipherTextLength = cipherTextBytes.length;
        InputStream plainTextStream = this.c.a(new ByteArrayInputStream(cipherTextBytes), entity);
        com.facebook.crypto.c.a output = new com.facebook.crypto.c.a(cipherTextLength - b());
        byte[] buffer = new byte[1024];
        while (true) {
            int read = plainTextStream.read(buffer);
            if (read != -1) {
                output.write(buffer, 0, read);
            } else {
                plainTextStream.close();
                return output.a();
            }
        }
    }

    private int b() {
        return this.c.a();
    }
}
