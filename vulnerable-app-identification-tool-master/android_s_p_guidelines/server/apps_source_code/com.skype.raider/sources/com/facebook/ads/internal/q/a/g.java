package com.facebook.ads.internal.q.a;

import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;

public class g {
    private static final String a = g.class.getSimpleName();

    public enum a {
        UNKNOWN(0),
        UNROOTED(1),
        ROOTED(2);
        
        public final int d;

        private a(int i) {
            this.d = i;
        }
    }

    private static boolean a(String str) {
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File name : listFiles) {
                        if (name.getName().equals(str)) {
                            return true;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static a a() {
        Object obj = null;
        try {
            if (!new File("/system/app/Superuser.apk").exists()) {
                String str = Build.TAGS;
                Object obj2 = (str == null || !str.contains("test-keys")) ? null : 1;
                if (obj2 == null) {
                }
            }
            obj = 1;
            return obj != null ? a.ROOTED : a.UNROOTED;
        } catch (Throwable th) {
            return a.UNKNOWN;
        }
    }

    @Nullable
    public static String a(Context context) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (Signature toByteArray : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
                stringBuilder.append(i.a(MessageDigest.getInstance("SHA1").digest(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(toByteArray.toByteArray())).getPublicKey().getEncoded())));
                stringBuilder.append(";");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
