package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.a;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.google.android.gms.internal.b.h;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

final class af {
    af() {
    }

    @WorkerThread
    static ag a(Context context, String str) throws ah {
        ag c = c(context, str);
        return c != null ? c : b(context, str);
    }

    @Nullable
    private static ag a(SharedPreferences sharedPreferences, String str) throws ah {
        String string = sharedPreferences.getString(p.a(str, "|P|"), null);
        String string2 = sharedPreferences.getString(p.a(str, "|K|"), null);
        return (string == null || string2 == null) ? null : new ag(a(string, string2), b(sharedPreferences, str));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Nullable
    private static ag a(File file) throws ah, IOException {
        Throwable th = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String property = properties.getProperty("pub");
            String property2 = properties.getProperty("pri");
            if (property == null || property2 == null) {
                a(null, fileInputStream);
                return null;
            }
            ag agVar = new ag(a(property, property2), Long.parseLong(properties.getProperty("cre")));
            a(null, fileInputStream);
            return agVar;
        } catch (Exception e) {
            throw new ah(e);
        } catch (Throwable th2) {
            Throwable th3 = th2;
            a(th, fileInputStream);
            throw th3;
        }
    }

    private static KeyPair a(String str, String str2) throws ah {
        Exception e;
        String valueOf;
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (InvalidKeySpecException e2) {
                e = e2;
                valueOf = String.valueOf(e);
                new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid key stored ").append(valueOf);
                throw new ah(e);
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                valueOf = String.valueOf(e);
                new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid key stored ").append(valueOf);
                throw new ah(e);
            }
        } catch (Exception e4) {
            throw new ah(e4);
        }
    }

    static void a(Context context) {
        for (File file : b(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(Context context, String str, ag agVar) {
        try {
            File e = e(context, str);
            e.createNewFile();
            Properties properties = new Properties();
            properties.setProperty("pub", Base64.encodeToString(agVar.a.getPublic().getEncoded(), 11));
            properties.setProperty("pri", Base64.encodeToString(agVar.a.getPrivate().getEncoded(), 11));
            properties.setProperty("cre", String.valueOf(agVar.b));
            FileOutputStream fileOutputStream = new FileOutputStream(e);
            properties.store(fileOutputStream, null);
            a(null, fileOutputStream);
        } catch (IOException e2) {
            String valueOf = String.valueOf(e2);
            new StringBuilder(String.valueOf(valueOf).length() + 21).append("Failed to write key: ").append(valueOf);
        }
    }

    private static /* synthetic */ void a(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
                return;
            } catch (Throwable th2) {
                h.a(th, th2);
                return;
            }
        }
        fileInputStream.close();
    }

    private static /* synthetic */ void a(Throwable th, FileOutputStream fileOutputStream) {
        if (th != null) {
            try {
                fileOutputStream.close();
                return;
            } catch (Throwable th2) {
                h.a(th, th2);
                return;
            }
        }
        fileOutputStream.close();
    }

    private static long b(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(p.a(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    @WorkerThread
    static ag b(Context context, String str) {
        ag agVar = new ag(a.a(), System.currentTimeMillis());
        try {
            ag c = c(context, str);
            if (c != null) {
                return c;
            }
        } catch (ah e) {
        }
        a(context, str, agVar);
        b(context, str, agVar);
        return agVar;
    }

    private static File b(Context context) {
        File c = a.c(context);
        return (c == null || !c.isDirectory()) ? context.getFilesDir() : c;
    }

    private static void b(Context context, String str, ag agVar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (agVar.equals(a(sharedPreferences, str))) {
                return;
            }
        } catch (ah e) {
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(p.a(str, "|P|"), Base64.encodeToString(agVar.a.getPublic().getEncoded(), 11));
        edit.putString(p.a(str, "|K|"), Base64.encodeToString(agVar.a.getPrivate().getEncoded(), 11));
        edit.putString(p.a(str, "cre"), String.valueOf(agVar.b));
        edit.commit();
    }

    @Nullable
    private static ag c(Context context, String str) throws ah {
        ah ahVar;
        ah e;
        try {
            ag d = d(context, str);
            if (d != null) {
                b(context, str, d);
                return d;
            }
            ahVar = null;
            try {
                d = a(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (d != null) {
                    a(context, str, d);
                    return d;
                }
                e = ahVar;
                if (e == null) {
                    return null;
                }
                throw e;
            } catch (ah e2) {
                e = e2;
            }
        } catch (ah e3) {
            ahVar = e3;
        }
    }

    @Nullable
    private static ag d(Context context, String str) throws ah {
        String valueOf;
        File e = e(context, str);
        if (!e.exists()) {
            return null;
        }
        try {
            return a(e);
        } catch (IOException e2) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                valueOf = String.valueOf(e2);
                new StringBuilder(String.valueOf(valueOf).length() + 40).append("Failed to read key from file, retrying: ").append(valueOf);
            }
            try {
                return a(e);
            } catch (Exception e3) {
                valueOf = String.valueOf(e3);
                new StringBuilder(String.valueOf(valueOf).length() + 45).append("IID file exists, but failed to read from it: ").append(valueOf);
                throw new ah(e3);
            }
        }
    }

    private static File e(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "com.google.InstanceId.properties";
        } else {
            try {
                str2 = Base64.encodeToString(str.getBytes(Constants.ENCODING), 11);
                str2 = new StringBuilder(String.valueOf(str2).length() + 33).append("com.google.InstanceId_").append(str2).append(".properties").toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(b(context), str2);
    }
}
