package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.ar;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import java.util.Date;
import org.json.JSONException;

final class ah {
    private static final String a = "ah";
    private String b;
    private SharedPreferences c;

    public ah(Context context) {
        this(context, (byte) 0);
    }

    private ah(Context context, byte b) {
        bn.a((Object) context, "context");
        String str = null;
        if (bj.a(null)) {
            str = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        }
        this.b = str;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.c = context.getSharedPreferences(this.b, 0);
    }

    public final Bundle a() {
        Bundle bundle = new Bundle();
        for (String str : this.c.getAll().keySet()) {
            try {
                a(str, bundle);
            } catch (JSONException e) {
                ai aiVar = ai.CACHE;
                String str2 = a;
                StringBuilder stringBuilder = new StringBuilder("Error reading cached value for key: '");
                stringBuilder.append(str);
                stringBuilder.append("' -- ");
                stringBuilder.append(e);
                ar.a(aiVar, 5, str2, stringBuilder.toString());
                return null;
            }
        }
        return bundle;
    }

    public final void b() {
        this.c.edit().clear().apply();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("com.facebook.TokenCachingStrategy.Token");
        if (string == null || string.length() == 0 || bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate", 0) == 0) {
            return false;
        }
        return true;
    }

    public static String b(Bundle bundle) {
        bn.a((Object) bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.Token");
    }

    public static f c(Bundle bundle) {
        bn.a((Object) bundle, "bundle");
        if (bundle.containsKey("com.facebook.TokenCachingStrategy.AccessTokenSource")) {
            return (f) bundle.getSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource");
        }
        return bundle.getBoolean("com.facebook.TokenCachingStrategy.IsSSO") ? f.FACEBOOK_APPLICATION_WEB : f.WEB_VIEW;
    }

    public static String d(Bundle bundle) {
        bn.a((Object) bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.ApplicationId");
    }

    static Date a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j == Long.MIN_VALUE) {
            return null;
        }
        return new Date(j);
    }

    private void a(java.lang.String r8, android.os.Bundle r9) throws org.json.JSONException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.ah.a(java.lang.String, android.os.Bundle):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r7 = this;
        r0 = r7.c;
        r1 = "{}";
        r0 = r0.getString(r8, r1);
        r1 = new org.json.JSONObject;
        r1.<init>(r0);
        r0 = "valueType";
        r0 = r1.getString(r0);
        r2 = "bool";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0025;
    L_0x001b:
        r0 = "value";
        r0 = r1.getBoolean(r0);
        r9.putBoolean(r8, r0);
        return;
    L_0x0025:
        r2 = "bool[]";
        r2 = r0.equals(r2);
        r3 = 0;
        if (r2 == 0) goto L_0x004a;
    L_0x002e:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new boolean[r1];
    L_0x003a:
        r2 = r1.length;
        if (r3 >= r2) goto L_0x0046;
    L_0x003d:
        r2 = r0.getBoolean(r3);
        r1[r3] = r2;
        r3 = r3 + 1;
        goto L_0x003a;
    L_0x0046:
        r9.putBooleanArray(r8, r1);
        return;
    L_0x004a:
        r2 = "byte";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x005d;
    L_0x0052:
        r0 = "value";
        r0 = r1.getInt(r0);
        r0 = (byte) r0;
        r9.putByte(r8, r0);
        return;
    L_0x005d:
        r2 = "byte[]";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0082;
    L_0x0065:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new byte[r1];
    L_0x0071:
        r2 = r1.length;
        if (r3 >= r2) goto L_0x007e;
    L_0x0074:
        r2 = r0.getInt(r3);
        r2 = (byte) r2;
        r1[r3] = r2;
        r3 = r3 + 1;
        goto L_0x0071;
    L_0x007e:
        r9.putByteArray(r8, r1);
        return;
    L_0x0082:
        r2 = "short";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0095;
    L_0x008a:
        r0 = "value";
        r0 = r1.getInt(r0);
        r0 = (short) r0;
        r9.putShort(r8, r0);
        return;
    L_0x0095:
        r2 = "short[]";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x00ba;
    L_0x009d:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new short[r1];
    L_0x00a9:
        r2 = r1.length;
        if (r3 >= r2) goto L_0x00b6;
    L_0x00ac:
        r2 = r0.getInt(r3);
        r2 = (short) r2;
        r1[r3] = r2;
        r3 = r3 + 1;
        goto L_0x00a9;
    L_0x00b6:
        r9.putShortArray(r8, r1);
        return;
    L_0x00ba:
        r2 = "int";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x00cc;
    L_0x00c2:
        r0 = "value";
        r0 = r1.getInt(r0);
        r9.putInt(r8, r0);
        return;
    L_0x00cc:
        r2 = "int[]";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x00f0;
    L_0x00d4:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new int[r1];
    L_0x00e0:
        r2 = r1.length;
        if (r3 >= r2) goto L_0x00ec;
    L_0x00e3:
        r2 = r0.getInt(r3);
        r1[r3] = r2;
        r3 = r3 + 1;
        goto L_0x00e0;
    L_0x00ec:
        r9.putIntArray(r8, r1);
        return;
    L_0x00f0:
        r2 = "long";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0102;
    L_0x00f8:
        r0 = "value";
        r0 = r1.getLong(r0);
        r9.putLong(r8, r0);
        return;
    L_0x0102:
        r2 = "long[]";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0126;
    L_0x010a:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new long[r1];
    L_0x0116:
        r2 = r1.length;
        if (r3 >= r2) goto L_0x0122;
    L_0x0119:
        r4 = r0.getLong(r3);
        r1[r3] = r4;
        r3 = r3 + 1;
        goto L_0x0116;
    L_0x0122:
        r9.putLongArray(r8, r1);
        return;
    L_0x0126:
        r2 = "float";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0139;
    L_0x012e:
        r0 = "value";
        r0 = r1.getDouble(r0);
        r0 = (float) r0;
        r9.putFloat(r8, r0);
        return;
    L_0x0139:
        r2 = "float[]";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x015e;
    L_0x0141:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new float[r1];
    L_0x014d:
        r2 = r1.length;
        if (r3 >= r2) goto L_0x015a;
    L_0x0150:
        r4 = r0.getDouble(r3);
        r2 = (float) r4;
        r1[r3] = r2;
        r3 = r3 + 1;
        goto L_0x014d;
    L_0x015a:
        r9.putFloatArray(r8, r1);
        return;
    L_0x015e:
        r2 = "double";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0170;
    L_0x0166:
        r0 = "value";
        r0 = r1.getDouble(r0);
        r9.putDouble(r8, r0);
        return;
    L_0x0170:
        r2 = "double[]";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0194;
    L_0x0178:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new double[r1];
    L_0x0184:
        r2 = r1.length;
        if (r3 >= r2) goto L_0x0190;
    L_0x0187:
        r4 = r0.getDouble(r3);
        r1[r3] = r4;
        r3 = r3 + 1;
        goto L_0x0184;
    L_0x0190:
        r9.putDoubleArray(r8, r1);
        return;
    L_0x0194:
        r2 = "char";
        r2 = r0.equals(r2);
        r4 = 1;
        if (r2 == 0) goto L_0x01b3;
    L_0x019d:
        r0 = "value";
        r0 = r1.getString(r0);
        if (r0 == 0) goto L_0x01b2;
    L_0x01a5:
        r1 = r0.length();
        if (r1 != r4) goto L_0x01b2;
    L_0x01ab:
        r0 = r0.charAt(r3);
        r9.putChar(r8, r0);
    L_0x01b2:
        return;
    L_0x01b3:
        r2 = "char[]";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x01e4;
    L_0x01bb:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r1 = new char[r1];
        r2 = 0;
    L_0x01c8:
        r5 = r1.length;
        if (r2 >= r5) goto L_0x01e0;
    L_0x01cb:
        r5 = r0.getString(r2);
        if (r5 == 0) goto L_0x01dd;
    L_0x01d1:
        r6 = r5.length();
        if (r6 != r4) goto L_0x01dd;
    L_0x01d7:
        r5 = r5.charAt(r3);
        r1[r2] = r5;
    L_0x01dd:
        r2 = r2 + 1;
        goto L_0x01c8;
    L_0x01e0:
        r9.putCharArray(r8, r1);
        return;
    L_0x01e4:
        r2 = "string";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x01f6;
    L_0x01ec:
        r0 = "value";
        r0 = r1.getString(r0);
        r9.putString(r8, r0);
        return;
    L_0x01f6:
        r2 = "stringList";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0225;
    L_0x01fe:
        r0 = "value";
        r0 = r1.getJSONArray(r0);
        r1 = r0.length();
        r2 = new java.util.ArrayList;
        r2.<init>(r1);
    L_0x020d:
        if (r3 >= r1) goto L_0x0221;
    L_0x020f:
        r4 = r0.get(r3);
        r5 = org.json.JSONObject.NULL;
        if (r4 != r5) goto L_0x0219;
    L_0x0217:
        r4 = 0;
        goto L_0x021b;
    L_0x0219:
        r4 = (java.lang.String) r4;
    L_0x021b:
        r2.add(r3, r4);
        r3 = r3 + 1;
        goto L_0x020d;
    L_0x0221:
        r9.putStringArrayList(r8, r2);
        return;
    L_0x0225:
        r2 = "enum";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0245;
    L_0x022d:
        r0 = "enumType";	 Catch:{ ClassNotFoundException -> 0x0244, IllegalArgumentException -> 0x0245 }
        r0 = r1.getString(r0);	 Catch:{ ClassNotFoundException -> 0x0244, IllegalArgumentException -> 0x0245 }
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0244, IllegalArgumentException -> 0x0245 }
        r2 = "value";	 Catch:{ ClassNotFoundException -> 0x0244, IllegalArgumentException -> 0x0245 }
        r1 = r1.getString(r2);	 Catch:{ ClassNotFoundException -> 0x0244, IllegalArgumentException -> 0x0245 }
        r0 = java.lang.Enum.valueOf(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0244, IllegalArgumentException -> 0x0245 }
        r9.putSerializable(r8, r0);	 Catch:{ ClassNotFoundException -> 0x0244, IllegalArgumentException -> 0x0245 }
    L_0x0244:
        return;
    L_0x0245:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ah.a(java.lang.String, android.os.Bundle):void");
    }
}
