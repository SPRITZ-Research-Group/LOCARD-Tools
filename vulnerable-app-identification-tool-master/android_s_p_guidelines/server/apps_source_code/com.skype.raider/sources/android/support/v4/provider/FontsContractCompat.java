package android.support.v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.c;
import android.support.v4.graphics.h;
import android.support.v4.util.g;
import android.support.v4.util.k;
import android.support.v4.util.l;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public final class FontsContractCompat {
    private static final g<String, Typeface> a = new g(16);
    private static final b b = new b("fonts");
    private static final Object c = new Object();
    @GuardedBy("sLock")
    private static final l<String, ArrayList<android.support.v4.provider.b.a<Typeface>>> d = new l();
    private static final Comparator<byte[]> e = new Comparator<byte[]>() {
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = (byte[]) obj2;
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return bArr[i] - bArr2[i];
                }
            }
            return 0;
        }
    };

    public static class FontFamilyResult {
        private final int a;
        private final a[] b;

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        @interface FontResultStatus {
        }

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public FontFamilyResult(int statusCode, @Nullable a[] fonts) {
            this.a = statusCode;
            this.b = fonts;
        }

        public final int a() {
            return this.a;
        }

        public final a[] b() {
            return this.b;
        }
    }

    public static class FontRequestCallback {

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        @interface FontRequestFailReason {
        }
    }

    public static class a {
        private final Uri a;
        private final int b;
        private final int c;
        private final boolean d;
        private final int e;

        @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
        public a(@NonNull Uri uri, @IntRange(from = 0) int ttcIndex, @IntRange(from = 1, to = 1000) int weight, boolean italic, int resultCode) {
            this.a = (Uri) k.a(uri);
            this.b = ttcIndex;
            this.c = weight;
            this.d = italic;
            this.e = resultCode;
        }

        @NonNull
        public final Uri a() {
            return this.a;
        }

        @IntRange(from = 0)
        public final int b() {
            return this.b;
        }

        @IntRange(from = 1, to = 1000)
        public final int c() {
            return this.c;
        }

        public final boolean d() {
            return this.d;
        }

        public final int e() {
            return this.e;
        }
    }

    private static Typeface b(Context context, a request, int style) {
        int i = 0;
        try {
            PackageManager packageManager = context.getPackageManager();
            Resources resources = context.getResources();
            String a = request.a();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(a, 0);
            if (resolveContentProvider == null) {
                throw new NameNotFoundException("No package found for authority: " + a);
            } else if (resolveContentProvider.packageName.equals(request.b())) {
                List d;
                ProviderInfo providerInfo;
                FontFamilyResult result;
                List a2 = a(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
                Collections.sort(a2, e);
                if (request.d() != null) {
                    d = request.d();
                } else {
                    d = FontResourcesParserCompat.a(resources, request.e());
                }
                while (i < d.size()) {
                    List arrayList = new ArrayList((Collection) d.get(i));
                    Collections.sort(arrayList, e);
                    if (a(a2, arrayList)) {
                        providerInfo = resolveContentProvider;
                        break;
                    }
                    i++;
                }
                providerInfo = null;
                if (providerInfo == null) {
                    result = new FontFamilyResult(1, null);
                } else {
                    result = new FontFamilyResult(0, a(context, request, providerInfo.authority));
                }
                if (result.a() == 0) {
                    return c.a(context, result.b(), style);
                }
                return null;
            } else {
                throw new NameNotFoundException("Found content provider " + a + ", but package was not " + request.b());
            }
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static Typeface a(final Context context, final a request, @Nullable final TextView targetView, int strategy, int timeout, final int style) {
        final Object id = request.f() + "-" + style;
        Typeface cached = (Typeface) a.a(id);
        if (cached != null) {
            return cached;
        }
        boolean isBlockingFetch = strategy == 0;
        if (isBlockingFetch && timeout == -1) {
            return b(context, request, style);
        }
        Callable fetcher = new Callable<Typeface>() {
            public final /* synthetic */ Object call() throws Exception {
                Typeface a = FontsContractCompat.b(context, request, style);
                if (a != null) {
                    FontsContractCompat.a.a(id, a);
                }
                return a;
            }
        };
        if (isBlockingFetch) {
            try {
                return (Typeface) b.a(fetcher, timeout);
            } catch (InterruptedException e) {
                return null;
            }
        }
        final WeakReference<TextView> textViewWeak = new WeakReference(targetView);
        android.support.v4.provider.b.a<Typeface> reply = new android.support.v4.provider.b.a<Typeface>() {
            public final /* synthetic */ void a(Object obj) {
                Typeface typeface = (Typeface) obj;
                if (((TextView) textViewWeak.get()) != null) {
                    targetView.setTypeface(typeface, style);
                }
            }
        };
        synchronized (c) {
            if (d.containsKey(id)) {
                ((ArrayList) d.get(id)).add(reply);
                return null;
            }
            ArrayList<android.support.v4.provider.b.a<Typeface>> pendingReplies = new ArrayList();
            pendingReplies.add(reply);
            d.put(id, pendingReplies);
            b.a(fetcher, new android.support.v4.provider.b.a<Typeface>() {
                public final /* synthetic */ void a(Object obj) {
                    ArrayList arrayList;
                    Typeface typeface = (Typeface) obj;
                    synchronized (FontsContractCompat.c) {
                        arrayList = (ArrayList) FontsContractCompat.d.get(id);
                        FontsContractCompat.d.remove(id);
                    }
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 < arrayList.size()) {
                            ((android.support.v4.provider.b.a) arrayList.get(i2)).a(typeface);
                            i = i2 + 1;
                        } else {
                            return;
                        }
                    }
                }
            });
            return null;
        }
    }

    @RequiresApi(19)
    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> a(Context context, a[] fonts) {
        HashMap<Uri, ByteBuffer> out = new HashMap();
        for (a font : fonts) {
            if (font.e() == 0) {
                Uri uri = font.a();
                if (!out.containsKey(uri)) {
                    out.put(uri, h.a(context, uri));
                }
            }
        }
        return Collections.unmodifiableMap(out);
    }

    private static boolean a(List<byte[]> signatures, List<byte[]> requestSignatures) {
        if (signatures.size() != requestSignatures.size()) {
            return false;
        }
        for (int i = 0; i < signatures.size(); i++) {
            if (!Arrays.equals((byte[]) signatures.get(i), (byte[]) requestSignatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> a(Signature[] signatures) {
        List<byte[]> shas = new ArrayList();
        for (Signature toByteArray : signatures) {
            shas.add(toByteArray.toByteArray());
        }
        return shas;
    }

    @VisibleForTesting
    @NonNull
    private static a[] a(Context context, a request, String authority) {
        Throwable th;
        ArrayList<a> result = new ArrayList();
        Uri uri = new Builder().scheme("content").authority(authority).build();
        Uri fileBaseUri = new Builder().scheme("content").authority(authority).appendPath("file").build();
        Cursor cursor = null;
        try {
            if (VERSION.SDK_INT > 16) {
                cursor = context.getContentResolver().query(uri, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{request.c()}, null, null);
            } else {
                cursor = context.getContentResolver().query(uri, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{request.c()}, null);
            }
            if (cursor != null && cursor.getCount() > 0) {
                int resultCodeColumnIndex = cursor.getColumnIndex("result_code");
                ArrayList<a> result2 = new ArrayList();
                try {
                    int idColumnIndex = cursor.getColumnIndex("_id");
                    int fileIdColumnIndex = cursor.getColumnIndex("file_id");
                    int ttcIndexColumnIndex = cursor.getColumnIndex("font_ttc_index");
                    int weightColumnIndex = cursor.getColumnIndex("font_weight");
                    int italicColumnIndex = cursor.getColumnIndex("font_italic");
                    while (cursor.moveToNext()) {
                        Uri fileUri;
                        int resultCode = resultCodeColumnIndex != -1 ? cursor.getInt(resultCodeColumnIndex) : 0;
                        int ttcIndex = ttcIndexColumnIndex != -1 ? cursor.getInt(ttcIndexColumnIndex) : 0;
                        if (fileIdColumnIndex == -1) {
                            fileUri = ContentUris.withAppendedId(uri, cursor.getLong(idColumnIndex));
                        } else {
                            fileUri = ContentUris.withAppendedId(fileBaseUri, cursor.getLong(fileIdColumnIndex));
                        }
                        int weight = weightColumnIndex != -1 ? cursor.getInt(weightColumnIndex) : 400;
                        boolean italic = italicColumnIndex != -1 && cursor.getInt(italicColumnIndex) == 1;
                        result2.add(new a(fileUri, ttcIndex, weight, italic, resultCode));
                    }
                    result = result2;
                } catch (Throwable th2) {
                    th = th2;
                    result = result2;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return (a[]) result.toArray(new a[0]);
        } catch (Throwable th3) {
            th = th3;
        }
        if (cursor != null) {
            cursor.close();
        }
        throw th;
    }
}
