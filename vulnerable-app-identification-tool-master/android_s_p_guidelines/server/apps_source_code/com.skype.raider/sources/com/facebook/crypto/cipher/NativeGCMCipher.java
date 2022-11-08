package com.facebook.crypto.cipher;

import com.facebook.crypto.d.b;
import com.facebook.crypto.proguard.annotations.DoNotStrip;

@DoNotStrip
public class NativeGCMCipher {
    private a a = a.UNINITIALIZED;
    private final b b;
    @DoNotStrip
    private long mCtxPtr;

    private enum a {
        UNINITIALIZED,
        ENCRYPT_INITIALIZED,
        DECRYPT_INITIALIZED,
        ENCRYPT_FINALIZED,
        DECRYPT_FINALIZED
    }

    private native int nativeDecryptFinal(byte[] bArr, int i);

    private native int nativeDecryptInit(byte[] bArr, byte[] bArr2);

    private native int nativeDestroy();

    private native int nativeEncryptFinal(byte[] bArr, int i);

    private native int nativeEncryptInit(byte[] bArr, byte[] bArr2);

    private static native int nativeFailure();

    private native int nativeGetCipherBlockSize();

    private native int nativeUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    private native int nativeUpdateAad(byte[] bArr, int i);

    public NativeGCMCipher(b nativeCryptoLibrary) {
        this.b = nativeCryptoLibrary;
    }

    public final void a(byte[] key, byte[] iv) throws a, com.facebook.crypto.a.a {
        com.facebook.crypto.d.a.a(this.a == a.UNINITIALIZED, "Cipher has already been initialized");
        this.b.a();
        if (nativeEncryptInit(key, iv) == nativeFailure()) {
            throw new a("encryptInit");
        }
        this.a = a.ENCRYPT_INITIALIZED;
    }

    public final void b(byte[] key, byte[] iv) throws a, com.facebook.crypto.a.a {
        com.facebook.crypto.d.a.a(this.a == a.UNINITIALIZED, "Cipher has already been initialized");
        this.b.a();
        if (nativeDecryptInit(key, iv) == nativeFailure()) {
            throw new a("decryptInit");
        }
        this.a = a.DECRYPT_INITIALIZED;
    }

    public final int a(byte[] data, int offset, int dataLen, byte[] output, int outputOffset) throws a {
        c();
        int bytesRead = nativeUpdate(data, offset, dataLen, output, outputOffset);
        if (bytesRead >= 0) {
            return bytesRead;
        }
        throw new a(String.format(null, "update: Offset = %d; DataLen = %d; Result = %d", new Object[]{Integer.valueOf(offset), Integer.valueOf(dataLen), Integer.valueOf(bytesRead)}));
    }

    public final void a(byte[] data, int dataLength) throws a {
        c();
        if (nativeUpdateAad(data, dataLength) < 0) {
            throw new a(String.format(null, "updateAAd: DataLen = %d", new Object[]{Integer.valueOf(dataLength)}));
        }
    }

    public final void b(byte[] tag, int tagLen) throws a {
        boolean z;
        if (this.a == a.ENCRYPT_INITIALIZED) {
            z = true;
        } else {
            z = false;
        }
        com.facebook.crypto.d.a.a(z, "Cipher has not been initialized");
        this.a = a.ENCRYPT_FINALIZED;
        if (nativeEncryptFinal(tag, tagLen) == nativeFailure()) {
            throw new a(String.format(null, "encryptFinal: %d", new Object[]{Integer.valueOf(tagLen)}));
        }
    }

    public final void c(byte[] expectedTag, int tagLen) throws a {
        com.facebook.crypto.d.a.a(this.a == a.DECRYPT_INITIALIZED, "Cipher has not been initialized");
        this.a = a.DECRYPT_FINALIZED;
        if (nativeDecryptFinal(expectedTag, tagLen) == nativeFailure()) {
            throw new a("The message could not be decrypted successfully.It has either been tampered with or the wrong resource is being decrypted.");
        }
    }

    public final int b() {
        c();
        return nativeGetCipherBlockSize();
    }

    private void c() {
        boolean z = this.a == a.DECRYPT_INITIALIZED || this.a == a.ENCRYPT_INITIALIZED;
        com.facebook.crypto.d.a.a(z, "Cipher has not been initialized");
    }

    public final void a() throws a {
        boolean z = this.a == a.DECRYPT_FINALIZED || this.a == a.ENCRYPT_FINALIZED;
        com.facebook.crypto.d.a.a(z, "Cipher has not been finalized");
        if (nativeDestroy() == nativeFailure()) {
            throw new a("destroy");
        }
        this.a = a.UNINITIALIZED;
    }
}
