package com.microsoft.tokenshare.a;

import android.support.annotation.NonNull;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

final class d implements a {
    private final String a;

    d(@NonNull String algorithm) {
        this.a = algorithm;
    }

    public final boolean a(Key cryptoKey, @NonNull byte[] data, @NonNull byte[] signature) {
        boolean z = false;
        if (!(cryptoKey instanceof SecretKey)) {
            return z;
        }
        try {
            Mac mac = Mac.getInstance(this.a);
            mac.init(cryptoKey);
            return MessageDigest.isEqual(mac.doFinal(data), signature);
        } catch (InvalidKeyException e) {
            return z;
        } catch (NoSuchAlgorithmException e2) {
            return z;
        }
    }
}
