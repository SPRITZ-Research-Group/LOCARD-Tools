package com.google.android.gms.common.images;

public final class Size {
    private final int zand;
    private final int zane;

    public Size(int i, int i2) {
        this.zand = i;
        this.zane = i2;
    }

    public final int getWidth() {
        return this.zand;
    }

    public final int getHeight() {
        return this.zane;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.zand == size.zand && this.zane == size.zane;
    }

    public final String toString() {
        int i = this.zand;
        int i2 = this.zane;
        StringBuilder stringBuilder = new StringBuilder(23);
        stringBuilder.append(i);
        stringBuilder.append("x");
        stringBuilder.append(i2);
        return stringBuilder.toString();
    }

    private static NumberFormatException zah(String str) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 16);
        stringBuilder.append("Invalid Size: \"");
        stringBuilder.append(str);
        stringBuilder.append("\"");
        throw new NumberFormatException(stringBuilder.toString());
    }

    public static com.google.android.gms.common.images.Size parseSize(java.lang.String r3) throws java.lang.NumberFormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.images.Size.parseSize(java.lang.String):com.google.android.gms.common.images.Size. bs: []
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
        if (r3 == 0) goto L_0x0035;
    L_0x0002:
        r0 = 42;
        r0 = r3.indexOf(r0);
        if (r0 >= 0) goto L_0x0010;
    L_0x000a:
        r0 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r0 = r3.indexOf(r0);
    L_0x0010:
        if (r0 < 0) goto L_0x0030;
    L_0x0012:
        r1 = new com.google.android.gms.common.images.Size;	 Catch:{ NumberFormatException -> 0x002b }
        r2 = 0;	 Catch:{ NumberFormatException -> 0x002b }
        r2 = r3.substring(r2, r0);	 Catch:{ NumberFormatException -> 0x002b }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x002b }
        r0 = r0 + 1;	 Catch:{ NumberFormatException -> 0x002b }
        r0 = r3.substring(r0);	 Catch:{ NumberFormatException -> 0x002b }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x002b }
        r1.<init>(r2, r0);	 Catch:{ NumberFormatException -> 0x002b }
        return r1;
    L_0x002b:
        r3 = zah(r3);
        throw r3;
    L_0x0030:
        r3 = zah(r3);
        throw r3;
    L_0x0035:
        r3 = new java.lang.IllegalArgumentException;
        r0 = "string must not be null";
        r3.<init>(r0);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.Size.parseSize(java.lang.String):com.google.android.gms.common.images.Size");
    }

    public final int hashCode() {
        return this.zane ^ ((this.zand << 16) | (this.zand >>> 16));
    }
}
