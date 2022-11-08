package androidx.appcompat.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class o extends DataSetObservable {
    static final String a = "o";
    private static final Object e = new Object();
    private static final Map<String, o> f = new HashMap();
    final Context b;
    final String c;
    boolean d;
    private final Object g;
    private final List<p> h;
    private final List<r> i;
    private Intent j;
    private q k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private s p;

    public final int a() {
        int size;
        synchronized (this.g) {
            e();
            size = this.h.size();
        }
        return size;
    }

    public final ResolveInfo a(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.g) {
            e();
            resolveInfo = ((p) this.h.get(i)).a;
        }
        return resolveInfo;
    }

    public final int a(ResolveInfo resolveInfo) {
        synchronized (this.g) {
            e();
            List list = this.h;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((p) list.get(i)).a == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public final Intent b(int i) {
        synchronized (this.g) {
            if (this.j == null) {
                return null;
            }
            e();
            p pVar = (p) this.h.get(i);
            ComponentName componentName = new ComponentName(pVar.a.activityInfo.packageName, pVar.a.activityInfo.name);
            Intent intent = new Intent(this.j);
            intent.setComponent(componentName);
            if (this.p != null) {
                Intent intent2 = new Intent(intent);
                if (this.p.a()) {
                    return null;
                }
            }
            a(new r(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public final ResolveInfo b() {
        synchronized (this.g) {
            e();
            if (this.h.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = ((p) this.h.get(0)).a;
            return resolveInfo;
        }
    }

    public final void c(int i) {
        synchronized (this.g) {
            e();
            p pVar = (p) this.h.get(i);
            p pVar2 = (p) this.h.get(0);
            a(new r(new ComponentName(pVar.a.activityInfo.packageName, pVar.a.activityInfo.name), System.currentTimeMillis(), pVar2 != null ? (pVar2.b - pVar.b) + 5.0f : 1.0f));
        }
    }

    private void d() {
        if (!this.m) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        } else if (this.n) {
            this.n = false;
            if (!TextUtils.isEmpty(this.c)) {
                new t(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.i), this.c});
            }
        }
    }

    public final int c() {
        int size;
        synchronized (this.g) {
            e();
            size = this.i.size();
        }
        return size;
    }

    private void e() {
        int g = g() | h();
        i();
        if (g != 0) {
            f();
            notifyChanged();
        }
    }

    private boolean f() {
        if (this.k == null || this.j == null || this.h.isEmpty() || this.i.isEmpty()) {
            return false;
        }
        Collections.unmodifiableList(this.i);
        return true;
    }

    private boolean g() {
        int i = 0;
        if (!this.o || this.j == null) {
            return false;
        }
        this.o = false;
        this.h.clear();
        List queryIntentActivities = this.b.getPackageManager().queryIntentActivities(this.j, 0);
        int size = queryIntentActivities.size();
        while (i < size) {
            this.h.add(new p((ResolveInfo) queryIntentActivities.get(i)));
            i++;
        }
        return true;
    }

    private boolean h() {
        if (!this.d || !this.n || TextUtils.isEmpty(this.c)) {
            return false;
        }
        this.d = false;
        this.m = true;
        j();
        return true;
    }

    private boolean a(r rVar) {
        boolean add = this.i.add(rVar);
        if (add) {
            this.n = true;
            i();
            d();
            f();
            notifyChanged();
        }
        return add;
    }

    private void i() {
        int size = this.i.size() - this.l;
        if (size > 0) {
            this.n = true;
            for (int i = 0; i < size; i++) {
                this.i.remove(0);
            }
        }
    }

    private void j() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.appcompat.widget.o.j():void. bs: []
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
        r9 = this;
        r0 = r9.b;	 Catch:{ FileNotFoundException -> 0x00c1 }
        r1 = r9.c;	 Catch:{ FileNotFoundException -> 0x00c1 }
        r0 = r0.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x00c1 }
        r1 = android.util.Xml.newPullParser();	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r2 = "UTF-8";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r1.setInput(r0, r2);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r2 = 0;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0012:
        r3 = 1;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        if (r2 == r3) goto L_0x001d;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0015:
        r4 = 2;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        if (r2 == r4) goto L_0x001d;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0018:
        r2 = r1.next();	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        goto L_0x0012;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x001d:
        r2 = "historical-records";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r4 = r1.getName();	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r2 = r2.equals(r4);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        if (r2 == 0) goto L_0x0078;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0029:
        r2 = r9.i;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r2.clear();	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x002e:
        r4 = r1.next();	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        if (r4 == r3) goto L_0x0072;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0034:
        r5 = 3;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        if (r4 == r5) goto L_0x002e;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0037:
        r5 = 4;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        if (r4 == r5) goto L_0x002e;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x003a:
        r4 = r1.getName();	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r5 = "historical-record";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r4 = r5.equals(r4);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        if (r4 == 0) goto L_0x006a;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0046:
        r4 = "activity";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r5 = 0;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r4 = r1.getAttributeValue(r5, r4);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r6 = "time";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r6 = r1.getAttributeValue(r5, r6);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r6 = java.lang.Long.parseLong(r6);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r8 = "weight";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r5 = r1.getAttributeValue(r5, r8);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r5 = java.lang.Float.parseFloat(r5);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r8 = new androidx.appcompat.widget.r;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r8.<init>(r4, r6, r5);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r2.add(r8);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        goto L_0x002e;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x006a:
        r1 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r2 = "Share records file not well-formed.";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r1.<init>(r2);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        throw r1;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0072:
        if (r0 == 0) goto L_0x00ba;
    L_0x0074:
        r0.close();	 Catch:{ IOException -> 0x0077 }
    L_0x0077:
        return;
    L_0x0078:
        r1 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r2 = "Share records file does not start with historical-records tag.";	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        r1.<init>(r2);	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
        throw r1;	 Catch:{ XmlPullParserException -> 0x009e, IOException -> 0x0082 }
    L_0x0080:
        r1 = move-exception;
        goto L_0x00bb;
    L_0x0082:
        r1 = move-exception;
        r2 = a;	 Catch:{ all -> 0x0080 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0080 }
        r4 = "Error reading historical recrod file: ";	 Catch:{ all -> 0x0080 }
        r3.<init>(r4);	 Catch:{ all -> 0x0080 }
        r4 = r9.c;	 Catch:{ all -> 0x0080 }
        r3.append(r4);	 Catch:{ all -> 0x0080 }
        r3 = r3.toString();	 Catch:{ all -> 0x0080 }
        android.util.Log.e(r2, r3, r1);	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x00ba;
    L_0x009a:
        r0.close();	 Catch:{ IOException -> 0x009d }
    L_0x009d:
        return;
    L_0x009e:
        r1 = move-exception;
        r2 = a;	 Catch:{ all -> 0x0080 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0080 }
        r4 = "Error reading historical recrod file: ";	 Catch:{ all -> 0x0080 }
        r3.<init>(r4);	 Catch:{ all -> 0x0080 }
        r4 = r9.c;	 Catch:{ all -> 0x0080 }
        r3.append(r4);	 Catch:{ all -> 0x0080 }
        r3 = r3.toString();	 Catch:{ all -> 0x0080 }
        android.util.Log.e(r2, r3, r1);	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x00ba;
    L_0x00b6:
        r0.close();	 Catch:{ IOException -> 0x00b9 }
    L_0x00b9:
        return;
    L_0x00ba:
        return;
    L_0x00bb:
        if (r0 == 0) goto L_0x00c0;
    L_0x00bd:
        r0.close();	 Catch:{ IOException -> 0x00c0 }
    L_0x00c0:
        throw r1;
    L_0x00c1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o.j():void");
    }
}
