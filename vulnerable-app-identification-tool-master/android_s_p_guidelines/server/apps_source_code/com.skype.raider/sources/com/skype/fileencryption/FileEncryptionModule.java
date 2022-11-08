package com.skype.fileencryption;

import android.util.Pair;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptionModule extends ReactContextBaseJavaModule {
    private static final int AES_IV_SIZE = 16;
    private static final int AES_KEY_SIZE = 32;
    private static final int AUTH_KEY_SIZE = 32;
    private static final int BUFFER_SIZE = 8192;
    private static final String DEFAULT_ALGO_KEY = "defaultAlgo";
    private static final String DEFAULT_ALGO_VALUE = "AES/CBC/PKCS7Padding";
    private static final String HASH_ALGO = "SHA256";
    private static final String HMAC_ALGO = "HmacSHA256";
    private static final String TAG = FileEncryptionModule.class.getName();

    FileEncryptionModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "FileEncryptionNative";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put(DEFAULT_ALGO_KEY, DEFAULT_ALGO_VALUE);
        return constants;
    }

    @ReactMethod
    public void getTempLocation(String filename, ae promise) {
        try {
            File tempLocation = getReactApplicationContext().getCacheDir();
            if (filename != null) {
                tempLocation = new File(tempLocation, filename);
            }
            promise.a(tempLocation.toURI().toASCIIString());
        } catch (Throwable e) {
            promise.a("Failure", e);
        }
    }

    @ReactMethod
    public void moveFile(String from, String to, ae promise) {
        Throwable e;
        Throwable th;
        FileInputStream input = null;
        FileOutputStream output = null;
        File inFile = fileFromPath(from);
        if (!inFile.renameTo(fileFromPath(to))) {
            try {
                FileOutputStream output2;
                FileInputStream input2 = new FileInputStream(inFile);
                try {
                    output2 = new FileOutputStream(fileFromPath(to));
                } catch (Exception e2) {
                    e = e2;
                    input = input2;
                    try {
                        promise.a("Failure", e);
                        if (input != null) {
                            try {
                                input.close();
                            } catch (Exception e3) {
                                return;
                            }
                        }
                        if (output != null) {
                            output.close();
                        }
                        if (!inFile.exists()) {
                            inFile.delete();
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        if (input != null) {
                            try {
                                input.close();
                            } catch (Exception e4) {
                                throw th;
                            }
                        }
                        if (output != null) {
                            output.close();
                        }
                        if (inFile.exists()) {
                            inFile.delete();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    input = input2;
                    if (input != null) {
                        input.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                    if (inFile.exists()) {
                        inFile.delete();
                    }
                    throw th;
                }
                try {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while (true) {
                        int bytesRead = input2.read(buffer);
                        if (-1 != bytesRead) {
                            output2.write(buffer, 0, bytesRead);
                        } else {
                            try {
                                break;
                            } catch (Exception e5) {
                                output = output2;
                                input = input2;
                            }
                        }
                    }
                    input2.close();
                    output2.close();
                    if (inFile.exists()) {
                        inFile.delete();
                    }
                    output = output2;
                    input = input2;
                } catch (Exception e6) {
                    e = e6;
                    output = output2;
                    input = input2;
                    promise.a("Failure", e);
                    if (input != null) {
                        input.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                    if (!inFile.exists()) {
                        inFile.delete();
                        return;
                    }
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    output = output2;
                    input = input2;
                    if (input != null) {
                        input.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                    if (inFile.exists()) {
                        inFile.delete();
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e = e7;
                promise.a("Failure", e);
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
                if (!inFile.exists()) {
                    inFile.delete();
                    return;
                }
                return;
            }
        }
        promise.a(null);
    }

    @ReactMethod
    public void encryptFile(String inPath, String outPath, String algo, al iv, al key, al authkey, ae promise) {
        if (DEFAULT_ALGO_VALUE.equals(algo)) {
            try {
                promise.a(toResultObject(processWithDefaultAlgo(1, inPath, outPath, iv, key, authkey)));
                return;
            } catch (Throwable e) {
                promise.a("Failure", e);
                return;
            }
        }
        promise.a("Wrong input", "Only defaultAlgo is supported for encryption right now");
    }

    @ReactMethod
    public void decryptFile(String inPath, String outPath, String algo, al iv, al key, al authkey, ae promise) {
        if (DEFAULT_ALGO_VALUE.equals(algo)) {
            try {
                promise.a(toResultObject(processWithDefaultAlgo(2, inPath, outPath, iv, key, authkey)));
                return;
            } catch (Throwable e) {
                promise.a("Failure", e);
                return;
            }
        }
        promise.a("Wrong input", "Only defaultAlgo is supported for decryption right now");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Pair<byte[], byte[]> processWithDefaultAlgo(int mode, String inPath, String outPath, al iv, al key, al authkey) throws Exception {
        FileInputStream input;
        FileOutputStream output;
        Mac hmacGen;
        MessageDigest hashGen;
        int totalBytes;
        Throwable th;
        FileInputStream input2 = null;
        FileOutputStream output2 = null;
        if (iv.size() != 16) {
            throw new InvalidAlgorithmParameterException("IV must be 16 bytes long", null);
        } else if (key.size() != 32) {
            throw new InvalidAlgorithmParameterException("Key must be 32 bytes long", null);
        } else if (authkey.size() != 32) {
            throw new InvalidAlgorithmParameterException("Auth Key must be 32 bytes long", null);
        } else {
            try {
                byte[] typedIV = toByteArray(iv);
                byte[] typedKey = toByteArray(key);
                byte[] typedAuthKey = toByteArray(authkey);
                input = new FileInputStream(fileFromPath(inPath));
                try {
                    output = new FileOutputStream(fileFromPath(outPath));
                    SecretKeySpec keySpec = new SecretKeySpec(typedKey, DEFAULT_ALGO_VALUE);
                    SecretKeySpec authKeySpec = new SecretKeySpec(typedAuthKey, HMAC_ALGO);
                    Cipher cipher = Cipher.getInstance(DEFAULT_ALGO_VALUE);
                    cipher.init(mode, keySpec, new IvParameterSpec(typedIV));
                    hmacGen = Mac.getInstance(HMAC_ALGO);
                    hmacGen.init(authKeySpec);
                    hmacGen.update(typedIV);
                    hashGen = MessageDigest.getInstance(HASH_ALGO);
                    hashGen.update(typedIV);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    totalBytes = 0;
                    while (true) {
                        int bytesRead = input.read(buffer);
                        if (-1 != bytesRead) {
                            totalBytes += bytesRead;
                            byte[] part = cipher.update(buffer, 0, bytesRead);
                            output.write(part);
                            if (mode == 1) {
                                hmacGen.update(part);
                                hashGen.update(part);
                            } else {
                                try {
                                    hmacGen.update(buffer, 0, bytesRead);
                                    hashGen.update(buffer, 0, bytesRead);
                                } catch (Throwable th2) {
                                    th = th2;
                                    output2 = output;
                                    input2 = input;
                                }
                            }
                        } else {
                            try {
                                break;
                            } catch (BadPaddingException e) {
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    input2 = input;
                    if (output2 != null) {
                        output2.close();
                    }
                    if (input2 != null) {
                        input2.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (output2 != null) {
                    output2.close();
                }
                if (input2 != null) {
                    input2.close();
                }
                throw th;
            }
        }
        byte[] hmac = hmacGen.doFinal();
        byte[] hash = hashGen.digest(hmac);
        FLog.d(TAG, "processed : " + totalBytes);
        Pair<byte[], byte[]> pair = new Pair(hmac, hash);
        output.close();
        input.close();
        return pair;
    }

    private byte[] toByteArray(al rnArray) {
        Double[] objects = (Double[]) rnArray.toArrayList().toArray(new Double[0]);
        byte[] bytes = new byte[objects.length];
        for (int i = 0; i < objects.length; i++) {
            bytes[i] = objects[i].byteValue();
        }
        return bytes;
    }

    private File fileFromPath(String path) {
        try {
            return new File(new URI(path));
        } catch (IllegalArgumentException e) {
        } catch (URISyntaxException e2) {
        }
        return new File(path);
    }

    private int[] byteToIntArray(byte[] input) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i] & 255;
        }
        return result;
    }

    private ar toResultObject(Pair<byte[], byte[]> validation) {
        ar object = new WritableNativeMap();
        object.putArray("hmac", b.a(byteToIntArray((byte[]) validation.first)));
        object.putArray("hash", b.a(byteToIntArray((byte[]) validation.second)));
        return object;
    }
}
