package com.oblador.keychain.a;

import android.support.annotation.NonNull;
import com.adjust.sdk.Constants;
import com.facebook.android.a.a.a;
import com.facebook.android.a.a.d;
import com.facebook.crypto.c;
import com.facebook.crypto.f;
import com.facebook.crypto.g;
import com.facebook.react.bridge.ag;
import java.nio.charset.Charset;

public final class b implements a {
    private final c a;

    public b(ag reactContext) {
        this.a = new c(new d(reactContext, f.KEY_256), a.a().a, f.KEY_256);
    }

    public final String a() {
        return "FacebookConceal";
    }

    public final int b() {
        return 16;
    }

    public final a.c a(@NonNull String service, @NonNull String username, @NonNull String password) throws com.oblador.keychain.b.a {
        if (this.a.a()) {
            try {
                return new a.c(this.a.a(username.getBytes(Charset.forName(Constants.ENCODING)), b(service)), this.a.a(password.getBytes(Charset.forName(Constants.ENCODING)), c(service)), this);
            } catch (Exception e) {
                throw new com.oblador.keychain.b.a("Encryption failed for service " + service, e);
            }
        }
        throw new com.oblador.keychain.b.a("Crypto is missing");
    }

    public final com.oblador.keychain.a.a.b a(@NonNull String service, @NonNull byte[] username, @NonNull byte[] password) throws com.oblador.keychain.b.a {
        if (this.a.a()) {
            g usernameEntity = b(service);
            g passwordEntity = c(service);
            try {
                return new com.oblador.keychain.a.a.b(new String(this.a.b(username, usernameEntity), Charset.forName(Constants.ENCODING)), new String(this.a.b(password, passwordEntity), Charset.forName(Constants.ENCODING)));
            } catch (Exception e) {
                throw new com.oblador.keychain.b.a("Decryption failed for service " + service, e);
            }
        }
        throw new com.oblador.keychain.b.a("Crypto is missing");
    }

    public final void a(@NonNull String service) {
    }

    private static g b(String service) {
        return g.a(d(service) + "user");
    }

    private static g c(String service) {
        return g.a(d(service) + "pass");
    }

    private static String d(String service) {
        return "RN_KEYCHAIN:" + service;
    }
}
