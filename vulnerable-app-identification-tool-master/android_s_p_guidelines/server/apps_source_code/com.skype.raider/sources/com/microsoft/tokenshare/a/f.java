package com.microsoft.tokenshare.a;

import android.support.annotation.NonNull;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

final class f implements a {
    private final String a;

    f(@NonNull String algorithm) {
        this.a = algorithm;
    }

    public final boolean a(Key cryptoKey, @NonNull byte[] data, @NonNull byte[] signature) {
        boolean z = false;
        if (!(cryptoKey instanceof PublicKey)) {
            return z;
        }
        try {
            Signature signatureProvider = Signature.getInstance(this.a);
            signatureProvider.initVerify((PublicKey) cryptoKey);
            signatureProvider.update(data);
            return signatureProvider.verify(signature);
        } catch (InvalidKeyException e) {
            return z;
        } catch (NoSuchAlgorithmException e2) {
            return z;
        } catch (SignatureException e3) {
            return z;
        }
    }
}
