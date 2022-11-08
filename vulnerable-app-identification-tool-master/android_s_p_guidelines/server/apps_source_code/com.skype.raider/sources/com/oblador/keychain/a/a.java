package com.oblador.keychain.a;

import android.support.annotation.NonNull;

public interface a {

    public static abstract class a<T> {
        public final T a;
        public final T b;

        public a(T username, T password) {
            this.a = username;
            this.b = password;
        }
    }

    public static class b extends a<String> {
        public b(String username, String password) {
            super(username, password);
        }
    }

    public static class c extends a<byte[]> {
        public a c;

        public c(byte[] username, byte[] password, a cipherStorage) {
            super(username, password);
            this.c = cipherStorage;
        }
    }

    b a(@NonNull String str, @NonNull byte[] bArr, @NonNull byte[] bArr2) throws com.oblador.keychain.b.a;

    c a(@NonNull String str, @NonNull String str2, @NonNull String str3) throws com.oblador.keychain.b.a;

    String a();

    void a(@NonNull String str) throws com.oblador.keychain.b.c;

    int b();
}
