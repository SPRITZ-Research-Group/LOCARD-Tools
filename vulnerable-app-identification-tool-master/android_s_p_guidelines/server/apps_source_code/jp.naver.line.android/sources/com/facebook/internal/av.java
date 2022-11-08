package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.login.a;
import com.facebook.n;
import com.facebook.p;
import com.facebook.s;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.naver.android.npush.common.NPushIntent;

public final class av {
    private static final String a = "com.facebook.internal.av";
    private static final List<ba> b = e();
    private static final List<ba> c;
    private static final Map<String, List<ba>> d;
    private static final AtomicBoolean e = new AtomicBoolean(false);
    private static final List<Integer> f = Arrays.asList(new Integer[]{Integer.valueOf(20170417), Integer.valueOf(20160327), Integer.valueOf(20141218), Integer.valueOf(20141107), Integer.valueOf(20141028), Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101)});

    static {
        List arrayList = new ArrayList(e());
        arrayList.add(0, new aw());
        c = arrayList;
        Map hashMap = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new az());
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", arrayList2);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", arrayList2);
        hashMap.put("com.facebook.platform.action.request.CAMERA_EFFECT", c);
        d = hashMap;
    }

    private static List<ba> e() {
        List<ba> arrayList = new ArrayList();
        arrayList.add(new ay());
        arrayList.add(new bc());
        return arrayList;
    }

    private static Intent a(Context context, Intent intent) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity != null && q.a(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    public static Intent a(Context context, String str, Collection<String> collection, String str2, boolean z, a aVar, String str3, String str4) {
        return a(context, a(new ax(), str, (Collection) collection, str2, z, aVar, str3, str4));
    }

    private static Intent a(ba baVar, String str, Collection<String> collection, String str2, boolean z, a aVar, String str3, String str4) {
        String b = baVar.b();
        if (b == null) {
            return null;
        }
        Intent putExtra = new Intent().setClassName(baVar.a(), b).putExtra("client_id", str);
        putExtra.putExtra("facebook_sdk_version", s.h());
        if (!bj.a((Collection) collection)) {
            putExtra.putExtra("scope", TextUtils.join(",", collection));
        }
        if (!bj.a(str2)) {
            putExtra.putExtra("e2e", str2);
        }
        putExtra.putExtra(NPushIntent.EXTRA_STATE, str3);
        putExtra.putExtra("response_type", "token,signed_request");
        putExtra.putExtra("return_scopes", "true");
        if (z) {
            putExtra.putExtra("default_audience", aVar.a());
        }
        putExtra.putExtra("legacy_override", s.g());
        putExtra.putExtra("auth_type", str4);
        return putExtra;
    }

    public static Intent b(Context context, String str, Collection<String> collection, String str2, boolean z, a aVar, String str3, String str4) {
        for (ba a : b) {
            Context context2 = context;
            Intent a2 = a(context, a(a, str, (Collection) collection, str2, z, aVar, str3, str4));
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    public static final int a() {
        return ((Integer) f.get(0)).intValue();
    }

    public static boolean a(int i) {
        return f.contains(Integer.valueOf(i)) && i >= 20140701;
    }

    public static Intent a(Context context, String str, String str2, bb bbVar, Bundle bundle) {
        if (bbVar == null) {
            return null;
        }
        ba a = bbVar.a;
        if (a == null) {
            return null;
        }
        Intent a2 = a(context, new Intent().setAction("com.facebook.platform.PLATFORM_ACTIVITY").setPackage(a.a()).addCategory("android.intent.category.DEFAULT"));
        if (a2 == null) {
            return null;
        }
        a(a2, str, str2, bbVar.b, bundle);
        return a2;
    }

    public static void a(Intent intent, String str, String str2, int i, Bundle bundle) {
        String j = s.j();
        String k = s.k();
        intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", i).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", str2).putExtra("com.facebook.platform.extra.APPLICATION_ID", j);
        if (a(i)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", str);
            bj.a(bundle2, "app_name", k);
            intent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            intent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle);
            return;
        }
        intent.putExtra("com.facebook.platform.protocol.CALL_ID", str);
        if (!bj.a(k)) {
            intent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", k);
        }
        intent.putExtras(bundle);
    }

    public static Intent a(Intent intent, Bundle bundle, n nVar) {
        UUID a = a(intent);
        if (a == null) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0));
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", a.toString());
        if (nVar != null) {
            bundle2.putBundle("error", a(nVar));
        }
        intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
        if (bundle != null) {
            intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
        }
        return intent2;
    }

    public static Intent a(Context context) {
        Intent intent;
        Iterator it = b.iterator();
        do {
            intent = null;
            if (!it.hasNext()) {
                return null;
            }
            Intent addCategory = new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(((ba) it.next()).a()).addCategory("android.intent.category.DEFAULT");
            if (addCategory != null) {
                ResolveInfo resolveService = context.getPackageManager().resolveService(addCategory, 0);
                if (resolveService != null && q.a(context, resolveService.serviceInfo.packageName)) {
                    intent = addCategory;
                    continue;
                }
            }
        } while (intent == null);
        return intent;
    }

    public static boolean d(Intent intent) {
        Bundle f = f(intent);
        if (f != null) {
            return f.containsKey("error");
        }
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }

    public static Bundle e(Intent intent) {
        if (!d(intent)) {
            return null;
        }
        Bundle f = f(intent);
        if (f != null) {
            return f.getBundle("error");
        }
        return intent.getExtras();
    }

    public static n a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("error_type");
        if (string == null) {
            string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String string2 = bundle.getString("error_description");
        if (string2 == null) {
            string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (string == null || !string.equalsIgnoreCase("UserCanceled")) {
            return new n(string2);
        }
        return new p(string2);
    }

    public static Bundle a(n nVar) {
        if (nVar == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error_description", nVar.toString());
        if (nVar instanceof p) {
            bundle.putString("error_type", "UserCanceled");
        }
        return bundle;
    }

    public static int b(int i) {
        return a(b, new int[]{i}).b();
    }

    public static bb a(String str, int[] iArr) {
        return a((List) d.get(str), iArr);
    }

    private static bb a(List<ba> list, int[] iArr) {
        b();
        if (list == null) {
            return bb.a();
        }
        for (ba baVar : list) {
            int a = a(baVar.c(), a(), iArr);
            if (a != -1) {
                return bb.a(baVar, a);
            }
        }
        return bb.a();
    }

    public static void b() {
        if (e.compareAndSet(false, true)) {
            s.d().execute(new Runnable() {
                public final void run() {
                    try {
                        for (ba a : av.b) {
                            a.a(true);
                        }
                    } finally {
                        av.e.set(false);
                    }
                }
            });
        }
    }

    private static java.util.TreeSet<java.lang.Integer> b(com.facebook.internal.ba r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.av.b(com.facebook.internal.ba):java.util.TreeSet<java.lang.Integer>. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = new java.util.TreeSet;
        r0.<init>();
        r1 = com.facebook.s.f();
        r2 = r1.getContentResolver();
        r1 = "version";
        r4 = new java.lang.String[]{r1};
        r1 = new java.lang.StringBuilder;
        r3 = "content://";
        r1.<init>(r3);
        r3 = r8.a();
        r1.append(r3);
        r3 = ".provider.PlatformProvider/versions";
        r1.append(r3);
        r1 = r1.toString();
        r3 = android.net.Uri.parse(r1);
        r1 = 0;
        r5 = com.facebook.s.f();	 Catch:{ all -> 0x008d }
        r5 = r5.getPackageManager();	 Catch:{ all -> 0x008d }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008d }
        r6.<init>();	 Catch:{ all -> 0x008d }
        r8 = r8.a();	 Catch:{ all -> 0x008d }
        r6.append(r8);	 Catch:{ all -> 0x008d }
        r8 = ".provider.PlatformProvider";	 Catch:{ all -> 0x008d }
        r6.append(r8);	 Catch:{ all -> 0x008d }
        r8 = r6.toString();	 Catch:{ all -> 0x008d }
        r6 = 0;
        r8 = r5.resolveContentProvider(r8, r6);	 Catch:{ RuntimeException -> 0x0052 }
        goto L_0x005b;
    L_0x0052:
        r8 = move-exception;
        r5 = a;	 Catch:{ all -> 0x008d }
        r6 = "Failed to query content resolver.";	 Catch:{ all -> 0x008d }
        android.util.Log.e(r5, r6, r8);	 Catch:{ all -> 0x008d }
        r8 = r1;
    L_0x005b:
        if (r8 == 0) goto L_0x0087;
    L_0x005d:
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ NullPointerException -> 0x0066, NullPointerException -> 0x0066, NullPointerException -> 0x0066 }
        r1 = r8;
        goto L_0x006d;
    L_0x0066:
        r8 = a;	 Catch:{ all -> 0x008d }
        r2 = "Failed to query content resolver.";	 Catch:{ all -> 0x008d }
        android.util.Log.e(r8, r2);	 Catch:{ all -> 0x008d }
    L_0x006d:
        if (r1 == 0) goto L_0x0087;	 Catch:{ all -> 0x008d }
    L_0x006f:
        r8 = r1.moveToNext();	 Catch:{ all -> 0x008d }
        if (r8 == 0) goto L_0x0087;	 Catch:{ all -> 0x008d }
    L_0x0075:
        r8 = "version";	 Catch:{ all -> 0x008d }
        r8 = r1.getColumnIndex(r8);	 Catch:{ all -> 0x008d }
        r8 = r1.getInt(r8);	 Catch:{ all -> 0x008d }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x008d }
        r0.add(r8);	 Catch:{ all -> 0x008d }
        goto L_0x006f;
    L_0x0087:
        if (r1 == 0) goto L_0x008c;
    L_0x0089:
        r1.close();
    L_0x008c:
        return r0;
    L_0x008d:
        r8 = move-exception;
        if (r1 == 0) goto L_0x0093;
    L_0x0090:
        r1.close();
    L_0x0093:
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.av.b(com.facebook.internal.ba):java.util.TreeSet<java.lang.Integer>");
    }

    private static int a(TreeSet<Integer> treeSet, int i, int[] iArr) {
        int length = iArr.length - 1;
        Iterator descendingIterator = treeSet.descendingIterator();
        int i2 = length;
        length = -1;
        while (descendingIterator.hasNext()) {
            int intValue = ((Integer) descendingIterator.next()).intValue();
            length = Math.max(length, intValue);
            while (i2 >= 0 && iArr[i2] > intValue) {
                i2--;
            }
            if (i2 < 0) {
                return -1;
            }
            if (iArr[i2] == intValue) {
                if (i2 % 2 == 0) {
                    return Math.min(length, i);
                }
                return -1;
            }
        }
        return -1;
    }

    public static java.util.UUID a(android.content.Intent r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.av.a(android.content.Intent):java.util.UUID. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = 0;
        if (r3 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = "com.facebook.platform.protocol.PROTOCOL_VERSION";
        r2 = 0;
        r1 = r3.getIntExtra(r1, r2);
        r1 = a(r1);
        if (r1 == 0) goto L_0x0022;
    L_0x0011:
        r1 = "com.facebook.platform.protocol.BRIDGE_ARGS";
        r3 = r3.getBundleExtra(r1);
        if (r3 == 0) goto L_0x0020;
    L_0x0019:
        r1 = "action_id";
        r3 = r3.getString(r1);
        goto L_0x0028;
    L_0x0020:
        r3 = r0;
        goto L_0x0028;
    L_0x0022:
        r1 = "com.facebook.platform.protocol.CALL_ID";
        r3 = r3.getStringExtra(r1);
    L_0x0028:
        if (r3 == 0) goto L_0x002f;
    L_0x002a:
        r3 = java.util.UUID.fromString(r3);	 Catch:{ IllegalArgumentException -> 0x002f }
        goto L_0x0030;
    L_0x002f:
        r3 = r0;
    L_0x0030:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.av.a(android.content.Intent):java.util.UUID");
    }

    private static Bundle f(Intent intent) {
        if (a(intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0))) {
            return intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
        }
        return null;
    }

    public static Bundle b(Intent intent) {
        if (a(intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0))) {
            return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
        }
        return intent.getExtras();
    }

    public static Bundle c(Intent intent) {
        int intExtra = intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
        Bundle extras = intent.getExtras();
        return (!a(intExtra) || extras == null) ? extras : extras.getBundle("com.facebook.platform.protocol.RESULT_ARGS");
    }
}
