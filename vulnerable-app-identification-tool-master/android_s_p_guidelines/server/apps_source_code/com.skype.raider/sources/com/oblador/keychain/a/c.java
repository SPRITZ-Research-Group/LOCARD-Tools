package com.oblador.keychain.a;

import android.annotation.TargetApi;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.support.annotation.NonNull;
import com.adjust.sdk.Constants;
import com.oblador.keychain.a.a.b;
import com.oblador.keychain.b.a;
import com.skype.Defines;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

public final class c implements a {
    public final String a() {
        return "KeystoreAESCBC";
    }

    public final int b() {
        return 23;
    }

    @TargetApi(23)
    public final com.oblador.keychain.a.a.c a(@NonNull String service, @NonNull String username, @NonNull String password) throws a {
        GeneralSecurityException e;
        Exception e2;
        service = b(service);
        try {
            KeyStore keyStore = c();
            if (!keyStore.containsAlias(service)) {
                AlgorithmParameterSpec spec = new Builder(service, 3).setBlockModes(new String[]{"CBC"}).setEncryptionPaddings(new String[]{"PKCS7Padding"}).setRandomizedEncryptionRequired(true).setKeySize(Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE).build();
                KeyGenerator generator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                generator.init(spec);
                generator.generateKey();
            }
            Key key = keyStore.getKey(service, null);
            return new com.oblador.keychain.a.a.c(a(key, service, username), a(key, service, password), this);
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            throw new a("Could not encrypt data for service " + service, e);
        } catch (InvalidAlgorithmParameterException e4) {
            e = e4;
            throw new a("Could not encrypt data for service " + service, e);
        } catch (NoSuchProviderException e5) {
            e = e5;
            throw new a("Could not encrypt data for service " + service, e);
        } catch (UnrecoverableKeyException e6) {
            e = e6;
            throw new a("Could not encrypt data for service " + service, e);
        } catch (KeyStoreException e7) {
            e2 = e7;
            throw new a("Could not access Keystore for service " + service, e2);
        } catch (com.oblador.keychain.b.c e8) {
            e2 = e8;
            throw new a("Could not access Keystore for service " + service, e2);
        } catch (Exception e22) {
            throw new a("Unknown error: " + e22.getMessage(), e22);
        }
    }

    public final b a(@NonNull String service, @NonNull byte[] username, @NonNull byte[] password) throws a {
        GeneralSecurityException e;
        try {
            Key key = c().getKey(b(service), null);
            return new b(a(key, username), a(key, password));
        } catch (KeyStoreException e2) {
            e = e2;
            throw new a("Could not get key from Keystore", e);
        } catch (UnrecoverableKeyException e3) {
            e = e3;
            throw new a("Could not get key from Keystore", e);
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            throw new a("Could not get key from Keystore", e);
        } catch (com.oblador.keychain.b.c e5) {
            throw new a("Could not access Keystore", e5);
        } catch (Exception e6) {
            throw new a("Unknown error: " + e6.getMessage(), e6);
        }
    }

    public final void a(@NonNull String service) throws com.oblador.keychain.b.c {
        service = b(service);
        try {
            KeyStore keyStore = c();
            if (keyStore.containsAlias(service)) {
                keyStore.deleteEntry(service);
            }
        } catch (KeyStoreException e) {
            throw new com.oblador.keychain.b.c("Failed to access Keystore", e);
        } catch (Exception e2) {
            throw new com.oblador.keychain.b.c("Unknown error " + e2.getMessage(), e2);
        }
    }

    private static byte[] a(Key key, String service, String value) throws a {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, key);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] iv = cipher.getIV();
            outputStream.write(iv, 0, iv.length);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            cipherOutputStream.write(value.getBytes(Constants.ENCODING));
            cipherOutputStream.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new a("Could not encrypt value for service " + service, e);
        }
    }

    private static String a(Key key, byte[] bytes) throws a {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            byte[] bArr = new byte[16];
            inputStream.read(bArr, 0, 16);
            cipher.init(2, key, new IvParameterSpec(bArr));
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int n = cipherInputStream.read(buffer, 0, 1024);
                if (n <= 0) {
                    return new String(output.toByteArray(), Charset.forName(Constants.ENCODING));
                }
                output.write(buffer, 0, n);
            }
        } catch (Exception e) {
            throw new a("Could not decrypt bytes", e);
        }
    }

    private static KeyStore c() throws KeyStoreException, com.oblador.keychain.b.c {
        Exception e;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore;
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            throw new com.oblador.keychain.b.c("Could not access Keystore", e);
        } catch (CertificateException e3) {
            e = e3;
            throw new com.oblador.keychain.b.c("Could not access Keystore", e);
        } catch (IOException e4) {
            e = e4;
            throw new com.oblador.keychain.b.c("Could not access Keystore", e);
        }
    }

    @NonNull
    private static String b(@NonNull String service) {
        return service.isEmpty() ? "RN_KEYCHAIN_DEFAULT_ALIAS" : service;
    }
}
