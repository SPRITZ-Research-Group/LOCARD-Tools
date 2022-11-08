package com.facebook.ads.internal.q.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import java.io.IOException;
import java.util.concurrent.Executors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class e {
    public static volatile a a = a.NOT_INITIALIZED;
    private static int b = -1;

    enum a {
        NOT_INITIALIZED,
        INITIALIZING,
        INITIALIZED
    }

    public static int a(final Context context) {
        if (a == a.NOT_INITIALIZED) {
            Object obj;
            if (a == a.INITIALIZED) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null && a == a.NOT_INITIALIZED) {
                a = a.INITIALIZING;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    public final void run() {
                        if (e.a != a.INITIALIZED) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
                            int i = sharedPreferences.getInt("AppMinSdkVersion", -1);
                            if (i != -1) {
                                e.b = i;
                                e.a = a.INITIALIZED;
                                return;
                            }
                            i = VERSION.SDK_INT >= 24 ? e.d(context) : e.b(context);
                            e.b = i;
                            sharedPreferences.edit().putInt("AppMinSdkVersion", i).commit();
                            e.a = a.INITIALIZED;
                        }
                    }
                });
            }
        }
        return b;
    }

    public static int b(Context context) {
        try {
            XmlPullParser openXmlResourceParser = context.getAssets().openXmlResourceParser("AndroidManifest.xml");
            while (openXmlResourceParser.next() != 1) {
                if (openXmlResourceParser.getEventType() == 2 && openXmlResourceParser.getName().equals("uses-sdk")) {
                    for (int i = 0; i < openXmlResourceParser.getAttributeCount(); i++) {
                        if (openXmlResourceParser.getAttributeName(i).equals("minSdkVersion")) {
                            return Integer.parseInt(openXmlResourceParser.getAttributeValue(i));
                        }
                    }
                    continue;
                }
            }
            return 0;
        } catch (XmlPullParserException e) {
            return 0;
        } catch (IOException e2) {
            return 0;
        }
    }

    private static int d(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).minSdkVersion;
        } catch (NameNotFoundException e) {
            return i;
        }
    }
}
