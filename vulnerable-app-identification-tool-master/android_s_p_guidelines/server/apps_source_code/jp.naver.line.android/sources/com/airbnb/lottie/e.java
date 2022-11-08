package com.airbnb.lottie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;
import defpackage.to;
import defpackage.vn;
import defpackage.wf;
import defpackage.wz;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class e {
    private static final Map<String, l<d>> a = new HashMap();

    public static l<d> a(Context context, String str) {
        return vn.a(context, str);
    }

    public static l<d> b(Context context, final String str) {
        context = context.getApplicationContext();
        return a(str, new Callable<k<d>>() {
            public final /* synthetic */ Object call() throws Exception {
                return e.c(context, str);
            }
        });
    }

    public static k<d> c(Context context, String str) {
        try {
            String concat = "asset_".concat(String.valueOf(str));
            if (str.endsWith(".zip")) {
                return a(new ZipInputStream(context.getAssets().open(str)), concat);
            }
            return a(context.getAssets().open(str), concat, true);
        } catch (Throwable e) {
            return new k(e);
        }
    }

    public static l<d> a(Context context, final int i) {
        context = context.getApplicationContext();
        return a(a(i), new Callable<k<d>>() {
            public final /* synthetic */ Object call() throws Exception {
                return e.b(context, i);
            }
        });
    }

    public static k<d> b(Context context, int i) {
        try {
            return a(context.getResources().openRawResource(i), a(i), true);
        } catch (Throwable e) {
            return new k(e);
        }
    }

    private static String a(int i) {
        return "rawRes_".concat(String.valueOf(i));
    }

    public static k<d> a(InputStream inputStream, String str) {
        return a(inputStream, str, true);
    }

    private static k<d> a(InputStream inputStream, String str, boolean z) {
        try {
            k<d> b = b(new JsonReader(new InputStreamReader(inputStream)), str);
            return b;
        } finally {
            if (z) {
                wz.a((Closeable) inputStream);
            }
        }
    }

    public static l<d> a(final String str) {
        return a(null, new Callable<k<d>>() {
            final /* synthetic */ String b = null;

            public final /* synthetic */ Object call() throws Exception {
                return e.a(str, this.b);
            }
        });
    }

    public static k<d> a(String str, String str2) {
        return b(new JsonReader(new StringReader(str)), str2);
    }

    public static l<d> a(final JsonReader jsonReader, final String str) {
        return a(str, new Callable<k<d>>() {
            public final /* synthetic */ Object call() throws Exception {
                return e.b(jsonReader, str);
            }
        });
    }

    public static k<d> b(JsonReader jsonReader, String str) {
        try {
            Object a = wf.a(jsonReader);
            to.a().a(str, a);
            return new k(a);
        } catch (Throwable e) {
            return new k(e);
        }
    }

    public static k<d> a(ZipInputStream zipInputStream, String str) {
        try {
            k<d> b = b(zipInputStream, str);
            return b;
        } finally {
            wz.a((Closeable) zipInputStream);
        }
    }

    private static k<d> b(ZipInputStream zipInputStream, String str) {
        Map hashMap = new HashMap();
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            Object obj = null;
            while (nextEntry != null) {
                if (!nextEntry.getName().contains("__MACOSX")) {
                    if (nextEntry.getName().contains(".json")) {
                        obj = (d) a(zipInputStream, str, false).a();
                    } else if (nextEntry.getName().contains(".png")) {
                        String[] split = nextEntry.getName().split("/");
                        hashMap.put(split[split.length - 1], BitmapFactory.decodeStream(zipInputStream));
                    }
                    nextEntry = zipInputStream.getNextEntry();
                }
                zipInputStream.closeEntry();
                nextEntry = zipInputStream.getNextEntry();
            }
            if (obj == null) {
                return new k(new IllegalArgumentException("Unable to parse composition"));
            }
            for (Entry entry : hashMap.entrySet()) {
                h a = a((d) obj, (String) entry.getKey());
                if (a != null) {
                    a.a((Bitmap) entry.getValue());
                }
            }
            for (Entry entry2 : obj.j().entrySet()) {
                if (((h) entry2.getValue()).e() == null) {
                    StringBuilder stringBuilder = new StringBuilder("There is no image for ");
                    stringBuilder.append(((h) entry2.getValue()).d());
                    return new k(new IllegalStateException(stringBuilder.toString()));
                }
            }
            to.a().a(str, obj);
            return new k(obj);
        } catch (Throwable e) {
            return new k(e);
        }
    }

    private static h a(d dVar, String str) {
        for (h hVar : dVar.j().values()) {
            if (hVar.d().equals(str)) {
                return hVar;
            }
        }
        return null;
    }

    private static l<d> a(final String str, Callable<k<d>> callable) {
        final d a = to.a().a(str);
        if (a != null) {
            return new l(new Callable<k<d>>() {
                public final /* synthetic */ Object call() throws Exception {
                    return new k(a);
                }
            });
        }
        if (a.containsKey(str)) {
            return (l) a.get(str);
        }
        l<d> lVar = new l(callable);
        lVar.a(new i<d>() {
            public final /* synthetic */ void a(Object obj) {
                d dVar = (d) obj;
                if (str != null) {
                    to.a().a(str, dVar);
                }
                e.a.remove(str);
            }
        });
        lVar.c(new i<Throwable>() {
            public final /* synthetic */ void a(Object obj) {
                e.a.remove(str);
            }
        });
        a.put(str, lVar);
        return lVar;
    }
}
