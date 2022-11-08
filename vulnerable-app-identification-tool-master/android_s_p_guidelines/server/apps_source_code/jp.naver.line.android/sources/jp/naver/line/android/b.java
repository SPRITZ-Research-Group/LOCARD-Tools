package jp.naver.line.android;

import android.text.TextUtils;
import defpackage.acip;
import defpackage.acjn;
import defpackage.acjo;
import defpackage.dft;
import defpackage.qcd;
import defpackage.qce;
import defpackage.uti;
import defpackage.utk;
import defpackage.xar;
import java.io.Closeable;
import java.util.Properties;

public final class b {
    public static final acjo a = new acjo("3rdApp", acjn.VERBOSE);
    public static final acjo b = new acjo("DAO", acjn.VERBOSE);
    public static final qcd c = new qcd("Common");
    public static final qcd d = new qcd("Recommend");
    public static final qcd e = new qcd("Settings");
    public static final t f = new t(BuildConfig.class);
    public static final qce g = qce.valueOf(BuildConfig.APP_PHASE);
    public static final xar h = xar.valueOf(BuildConfig.APPLICATION_TYPE);
    private static volatile String i;

    static {
        dft.a(BuildConfig.TALK_SERVER_HOST, BuildConfig.TALK_SERVER_HOST_SECONDARY);
        dft.a();
        dft.b();
        dft.c(BuildConfig.OBS_CDN_SERVER_HOST);
        dft.a(BuildConfig.OBJECT_STORAGE_SERVER_HOST);
        dft.b(BuildConfig.URL_STICKER_SHOP_CONTENTS);
        dft.d(BuildConfig.URL_SHOP_CDN_SERVER_HOST);
        uti.a();
        utk.a(h.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String a() {
        Throwable th;
        if (i == null) {
            synchronized (b.class) {
                if (i == null) {
                    String str = null;
                    Closeable resourceAsStream;
                    try {
                        resourceAsStream = b.class.getClassLoader().getResourceAsStream("tracking-id");
                        if (resourceAsStream != null) {
                            try {
                                if (resourceAsStream.available() > 0) {
                                    Properties properties = new Properties();
                                    properties.load(resourceAsStream);
                                    str = properties.getProperty("MARKET_TRACKING_ID");
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                acip.a(resourceAsStream);
                                throw th;
                            }
                        }
                        acip.a(resourceAsStream);
                        if (TextUtils.isEmpty(str) && b()) {
                            str = "MA_BETA";
                        }
                        i = str;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        resourceAsStream = null;
                        th = th4;
                        acip.a(resourceAsStream);
                        throw th;
                    }
                }
            }
        }
        return i;
    }

    public static boolean b() {
        switch (g) {
            case LOCAL:
            case ALPHA:
            case BETA:
                return true;
            default:
                return false;
        }
    }
}
