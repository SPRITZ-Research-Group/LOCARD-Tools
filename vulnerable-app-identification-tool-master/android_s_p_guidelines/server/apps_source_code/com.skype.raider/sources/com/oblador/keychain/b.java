package com.oblador.keychain;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Base64;
import com.facebook.react.bridge.ag;
import com.oblador.keychain.a.a.c;

public final class b {
    private final SharedPreferences a;

    public static class a {
        public final String a;
        public final byte[] b;
        public final byte[] c;

        public a(String cipherStorageName, byte[] usernameBytes, byte[] passwordBytes) {
            this.a = cipherStorageName;
            this.b = usernameBytes;
            this.c = passwordBytes;
        }
    }

    public b(ag reactContext) {
        this.a = reactContext.getSharedPreferences("RN_KEYCHAIN", 0);
    }

    public final a a(@NonNull String service) {
        byte[] bytesForUsername = f(c(service));
        byte[] bytesForPassword = f(d(service));
        String cipherStorageName = this.a.getString(e(service), null);
        if (bytesForUsername == null || bytesForPassword == null) {
            return null;
        }
        if (cipherStorageName == null) {
            cipherStorageName = "FacebookConceal";
        }
        return new a(cipherStorageName, bytesForUsername, bytesForPassword);
    }

    public final void b(@NonNull String service) {
        String keyForUsername = c(service);
        String keyForPassword = d(service);
        this.a.edit().remove(keyForUsername).remove(keyForPassword).remove(e(service)).apply();
    }

    public final void a(@NonNull String service, @NonNull c encryptionResult) {
        String keyForUsername = c(service);
        String keyForPassword = d(service);
        this.a.edit().putString(keyForUsername, Base64.encodeToString((byte[]) encryptionResult.a, 0)).putString(keyForPassword, Base64.encodeToString((byte[]) encryptionResult.b, 0)).putString(e(service), encryptionResult.c.a()).apply();
    }

    private static String c(String service) {
        return service + ":u";
    }

    private static String d(String service) {
        return service + ":p";
    }

    private static String e(String service) {
        return service + ":c";
    }

    private byte[] f(String key) {
        String value = this.a.getString(key, null);
        if (value != null) {
            return Base64.decode(value, 0);
        }
        return null;
    }
}
