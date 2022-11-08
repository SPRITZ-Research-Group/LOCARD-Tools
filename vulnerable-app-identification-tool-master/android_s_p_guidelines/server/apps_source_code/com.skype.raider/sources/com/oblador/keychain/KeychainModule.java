package com.oblador.keychain;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.oblador.keychain.a.a;
import com.oblador.keychain.a.b;
import com.oblador.keychain.a.c;
import java.util.HashMap;
import java.util.Map;

public class KeychainModule extends ReactContextBaseJavaModule {
    public static final String EMPTY_STRING = "";
    public static final String E_CRYPTO_FAILED = "E_CRYPTO_FAILED";
    public static final String E_EMPTY_PARAMETERS = "E_EMPTY_PARAMETERS";
    public static final String E_KEYSTORE_ACCESS_ERROR = "E_KEYSTORE_ACCESS_ERROR";
    public static final String E_SUPPORTED_BIOMETRY_ERROR = "E_SUPPORTED_BIOMETRY_ERROR";
    public static final String FINGERPRINT_SUPPORTED_NAME = "Fingerprint";
    public static final String KEYCHAIN_MODULE = "RNKeychainManager";
    private final Map<String, a> cipherStorageMap = new HashMap();
    private final b prefsStorage;

    public String getName() {
        return KEYCHAIN_MODULE;
    }

    public KeychainModule(ag reactContext) {
        super(reactContext);
        this.prefsStorage = new b(reactContext);
        addCipherStorageToMap(new b(reactContext));
        addCipherStorageToMap(new c());
    }

    private void addCipherStorageToMap(a cipherStorage) {
        this.cipherStorageMap.put(cipherStorage.a(), cipherStorage);
    }

    @ReactMethod
    public void setGenericPasswordForOptions(String service, String username, String password, ae promise) {
        if (username != null) {
            try {
                if (!(username.isEmpty() || password == null || password.isEmpty())) {
                    service = getDefaultServiceIfNull(service);
                    this.prefsStorage.a(service, getCipherStorageForCurrentAPILevel().a(service, username, password));
                    promise.a(Boolean.valueOf(true));
                    return;
                }
            } catch (Throwable e) {
                e.getMessage();
                promise.a(E_EMPTY_PARAMETERS, e);
                return;
            } catch (Throwable e2) {
                e2.getMessage();
                promise.a(E_CRYPTO_FAILED, e2);
                return;
            }
        }
        throw new com.oblador.keychain.b.b("you passed empty or null username/password");
    }

    @ReactMethod
    public void getGenericPasswordForOptions(String service, ae promise) {
        try {
            service = getDefaultServiceIfNull(service);
            a currentCipherStorage = getCipherStorageForCurrentAPILevel();
            b.a resultSet = this.prefsStorage.a(service);
            if (resultSet == null) {
                promise.a(Boolean.valueOf(false));
                return;
            }
            a.b decryptionResult;
            if (resultSet.a.equals(currentCipherStorage.a())) {
                decryptionResult = currentCipherStorage.a(service, resultSet.b, resultSet.c);
            } else {
                a oldCipherStorage = getCipherStorageByName(resultSet.a);
                decryptionResult = oldCipherStorage.a(service, resultSet.b, resultSet.c);
                this.prefsStorage.a(service, currentCipherStorage.a(service, (String) decryptionResult.a, (String) decryptionResult.b));
                oldCipherStorage.a(service);
            }
            Object credentials = new WritableNativeMap();
            credentials.putString("service", service);
            credentials.putString("username", (String) decryptionResult.a);
            credentials.putString("password", (String) decryptionResult.b);
            promise.a(credentials);
        } catch (Throwable e) {
            e.getMessage();
            promise.a(E_KEYSTORE_ACCESS_ERROR, e);
        } catch (Throwable e2) {
            e2.getMessage();
            promise.a(E_CRYPTO_FAILED, e2);
        }
    }

    @ReactMethod
    public void resetGenericPasswordForOptions(String service, ae promise) {
        try {
            service = getDefaultServiceIfNull(service);
            b.a resultSet = this.prefsStorage.a(service);
            if (resultSet != null) {
                a cipherStorage = getCipherStorageByName(resultSet.a);
                if (cipherStorage != null) {
                    cipherStorage.a(service);
                }
            }
            this.prefsStorage.b(service);
            promise.a(Boolean.valueOf(true));
        } catch (Throwable e) {
            e.getMessage();
            promise.a(E_KEYSTORE_ACCESS_ERROR, e);
        }
    }

    @ReactMethod
    public void setInternetCredentialsForServer(@NonNull String server, String username, String password, am unusedOptions, ae promise) {
        setGenericPasswordForOptions(server, username, password, promise);
    }

    @ReactMethod
    public void getInternetCredentialsForServer(@NonNull String server, am unusedOptions, ae promise) {
        getGenericPasswordForOptions(server, promise);
    }

    @ReactMethod
    public void resetInternetCredentialsForServer(@NonNull String server, am unusedOptions, ae promise) {
        resetGenericPasswordForOptions(server, promise);
    }

    @ReactMethod
    public void getSupportedBiometryType(ae promise) {
        try {
            if (isFingerprintAuthAvailable()) {
                promise.a(FINGERPRINT_SUPPORTED_NAME);
            } else {
                promise.a(null);
            }
        } catch (Throwable e) {
            e.getMessage();
            promise.a(E_SUPPORTED_BIOMETRY_ERROR, e);
        }
    }

    private a getCipherStorageForCurrentAPILevel() throws com.oblador.keychain.b.a {
        int currentAPILevel = VERSION.SDK_INT;
        a currentCipherStorage = null;
        for (a cipherStorage : this.cipherStorageMap.values()) {
            int cipherStorageAPILevel = cipherStorage.b();
            if ((cipherStorageAPILevel <= currentAPILevel ? 1 : null) != null && (currentCipherStorage == null || cipherStorageAPILevel > currentCipherStorage.b())) {
                currentCipherStorage = cipherStorage;
            }
        }
        if (currentCipherStorage != null) {
            return currentCipherStorage;
        }
        throw new com.oblador.keychain.b.a("Unsupported Android SDK " + VERSION.SDK_INT);
    }

    private a getCipherStorageByName(String cipherStorageName) {
        return (a) this.cipherStorageMap.get(cipherStorageName);
    }

    private boolean isFingerprintAuthAvailable() {
        Context currentActivity = getCurrentActivity();
        if (VERSION.SDK_INT < 23) {
            return false;
        }
        FingerprintManager fingerprintManager = (FingerprintManager) currentActivity.getSystemService("fingerprint");
        return fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints();
    }

    @NonNull
    private String getDefaultServiceIfNull(String service) {
        return service == null ? "" : service;
    }
}
