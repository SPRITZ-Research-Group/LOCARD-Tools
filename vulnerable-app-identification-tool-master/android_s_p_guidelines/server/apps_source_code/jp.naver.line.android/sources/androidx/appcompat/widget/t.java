package androidx.appcompat.widget;

import android.os.AsyncTask;

final class t extends AsyncTask<Object, Void, Void> {
    final /* synthetic */ o a;

    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a(objArr);
    }

    t(o oVar) {
        this.a = oVar;
    }

    private java.lang.Void a(java.lang.Object... r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.appcompat.widget.t.a(java.lang.Object[]):java.lang.Void. bs: []
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
        r11 = this;
        r0 = 0;
        r1 = r12[r0];
        r1 = (java.util.List) r1;
        r2 = 1;
        r12 = r12[r2];
        r12 = (java.lang.String) r12;
        r3 = 0;
        r4 = r11.a;	 Catch:{ FileNotFoundException -> 0x00dd }
        r4 = r4.b;	 Catch:{ FileNotFoundException -> 0x00dd }
        r4 = r4.openFileOutput(r12, r0);	 Catch:{ FileNotFoundException -> 0x00dd }
        r12 = android.util.Xml.newSerializer();
        r12.setOutput(r4, r3);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r5 = "UTF-8";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r6 = java.lang.Boolean.TRUE;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.startDocument(r5, r6);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r5 = "historical-records";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.startTag(r3, r5);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r5 = r1.size();	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r6 = 0;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
    L_0x002b:
        if (r6 >= r5) goto L_0x0061;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
    L_0x002d:
        r7 = r1.remove(r0);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r7 = (androidx.appcompat.widget.r) r7;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r8 = "historical-record";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.startTag(r3, r8);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r8 = "activity";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r9 = r7.a;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r9 = r9.flattenToString();	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.attribute(r3, r8, r9);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r8 = "time";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r9 = r7.b;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r9 = java.lang.String.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.attribute(r3, r8, r9);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r8 = "weight";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r7 = r7.c;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.attribute(r3, r8, r7);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r7 = "historical-record";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.endTag(r3, r7);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r6 = r6 + 1;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        goto L_0x002b;	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
    L_0x0061:
        r0 = "historical-records";	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.endTag(r3, r0);	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12.endDocument();	 Catch:{ IllegalArgumentException -> 0x00b3, IllegalStateException -> 0x0094, IOException -> 0x0075 }
        r12 = r11.a;
        r12.d = r2;
        if (r4 == 0) goto L_0x00d2;
    L_0x006f:
        r4.close();	 Catch:{ IOException -> 0x00d2 }
        goto L_0x00d2;
    L_0x0073:
        r12 = move-exception;
        goto L_0x00d3;
    L_0x0075:
        r12 = move-exception;
        r0 = androidx.appcompat.widget.o.a;	 Catch:{ all -> 0x0073 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0073 }
        r5 = "Error writing historical record file: ";	 Catch:{ all -> 0x0073 }
        r1.<init>(r5);	 Catch:{ all -> 0x0073 }
        r5 = r11.a;	 Catch:{ all -> 0x0073 }
        r5 = r5.c;	 Catch:{ all -> 0x0073 }
        r1.append(r5);	 Catch:{ all -> 0x0073 }
        r1 = r1.toString();	 Catch:{ all -> 0x0073 }
        android.util.Log.e(r0, r1, r12);	 Catch:{ all -> 0x0073 }
        r12 = r11.a;
        r12.d = r2;
        if (r4 == 0) goto L_0x00d2;
    L_0x0093:
        goto L_0x006f;
    L_0x0094:
        r12 = move-exception;
        r0 = androidx.appcompat.widget.o.a;	 Catch:{ all -> 0x0073 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0073 }
        r5 = "Error writing historical record file: ";	 Catch:{ all -> 0x0073 }
        r1.<init>(r5);	 Catch:{ all -> 0x0073 }
        r5 = r11.a;	 Catch:{ all -> 0x0073 }
        r5 = r5.c;	 Catch:{ all -> 0x0073 }
        r1.append(r5);	 Catch:{ all -> 0x0073 }
        r1 = r1.toString();	 Catch:{ all -> 0x0073 }
        android.util.Log.e(r0, r1, r12);	 Catch:{ all -> 0x0073 }
        r12 = r11.a;
        r12.d = r2;
        if (r4 == 0) goto L_0x00d2;
    L_0x00b2:
        goto L_0x006f;
    L_0x00b3:
        r12 = move-exception;
        r0 = androidx.appcompat.widget.o.a;	 Catch:{ all -> 0x0073 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0073 }
        r5 = "Error writing historical record file: ";	 Catch:{ all -> 0x0073 }
        r1.<init>(r5);	 Catch:{ all -> 0x0073 }
        r5 = r11.a;	 Catch:{ all -> 0x0073 }
        r5 = r5.c;	 Catch:{ all -> 0x0073 }
        r1.append(r5);	 Catch:{ all -> 0x0073 }
        r1 = r1.toString();	 Catch:{ all -> 0x0073 }
        android.util.Log.e(r0, r1, r12);	 Catch:{ all -> 0x0073 }
        r12 = r11.a;
        r12.d = r2;
        if (r4 == 0) goto L_0x00d2;
    L_0x00d1:
        goto L_0x006f;
    L_0x00d2:
        return r3;
    L_0x00d3:
        r0 = r11.a;
        r0.d = r2;
        if (r4 == 0) goto L_0x00dc;
    L_0x00d9:
        r4.close();	 Catch:{ IOException -> 0x00dc }
    L_0x00dc:
        throw r12;
    L_0x00dd:
        r0 = move-exception;
        r1 = androidx.appcompat.widget.o.a;
        r2 = "Error writing historical record file: ";
        r12 = java.lang.String.valueOf(r12);
        r12 = r2.concat(r12);
        android.util.Log.e(r1, r12, r0);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.t.a(java.lang.Object[]):java.lang.Void");
    }
}
