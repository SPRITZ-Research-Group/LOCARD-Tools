package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import jp.naver.android.npush.common.NPushIntent;

@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzem = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap();

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @KeepForSdk
    public String getVersion(String str) {
        Throwable e;
        GmsLogger gmsLogger;
        String str2;
        String str3;
        String valueOf;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzen.containsKey(str)) {
            return (String) this.zzen.get(str);
        }
        Properties properties = new Properties();
        String str4 = null;
        try {
            InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", new Object[]{str}));
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                String property = properties.getProperty(NPushIntent.EXTRA_VERSION, null);
                try {
                    StringBuilder stringBuilder = new StringBuilder((String.valueOf(str).length() + 12) + String.valueOf(property).length());
                    stringBuilder.append(str);
                    stringBuilder.append(" version is ");
                    stringBuilder.append(property);
                    zzel.v("LibraryVersion", stringBuilder.toString());
                    str4 = property;
                } catch (Throwable e2) {
                    Throwable th = e2;
                    str4 = property;
                    e = th;
                    gmsLogger = zzel;
                    str2 = "LibraryVersion";
                    str3 = "Failed to get app version for libraryName: ";
                    valueOf = String.valueOf(str);
                    gmsLogger.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf), e);
                    if (str4 == null) {
                        str4 = "UNKNOWN";
                        zzel.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
                    }
                    this.zzen.put(str, str4);
                    return str4;
                }
            }
            GmsLogger gmsLogger2 = zzel;
            String str5 = "LibraryVersion";
            str2 = "Failed to get app version for libraryName: ";
            str3 = String.valueOf(str);
            gmsLogger2.e(str5, str3.length() != 0 ? str2.concat(str3) : new String(str2));
        } catch (IOException e3) {
            e = e3;
            gmsLogger = zzel;
            str2 = "LibraryVersion";
            str3 = "Failed to get app version for libraryName: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            gmsLogger.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf), e);
            if (str4 == null) {
                str4 = "UNKNOWN";
                zzel.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
            }
            this.zzen.put(str, str4);
            return str4;
        }
        if (str4 == null) {
            str4 = "UNKNOWN";
            zzel.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
        }
        this.zzen.put(str, str4);
        return str4;
    }
}
