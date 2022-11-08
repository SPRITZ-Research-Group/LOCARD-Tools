package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

public class BitEncoding {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean FORCE_8TO7_ENCODING;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.BitEncoding.<clinit>():void. bs: []
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
        r0 = "kotlin.jvm.serialization.use8to7";	 Catch:{ SecurityException -> 0x0007 }
        r0 = java.lang.System.getProperty(r0);	 Catch:{ SecurityException -> 0x0007 }
        goto L_0x0008;
    L_0x0007:
        r0 = 0;
    L_0x0008:
        r1 = "true";
        r0 = r1.equals(r0);
        FORCE_8TO7_ENCODING = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.BitEncoding.<clinit>():void");
    }

    private BitEncoding() {
    }

    private static void addModuloByte(byte[] bArr, int i) {
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = (byte) ((bArr[i2] + i) & 127);
        }
    }

    public static byte[] decodeBytes(String[] strArr) {
        if (strArr.length > 0 && !strArr[0].isEmpty()) {
            char charAt = strArr[0].charAt(0);
            if (charAt == 0) {
                return UtfEncodingKt.stringsToBytes(dropMarker(strArr));
            }
            if (charAt == 65535) {
                strArr = dropMarker(strArr);
            }
        }
        byte[] combineStringArrayIntoBytes = combineStringArrayIntoBytes(strArr);
        addModuloByte(combineStringArrayIntoBytes, 127);
        return decode7to8(combineStringArrayIntoBytes);
    }

    private static String[] dropMarker(String[] strArr) {
        strArr = (String[]) strArr.clone();
        strArr[0] = strArr[0].substring(1);
        return strArr;
    }

    private static byte[] combineStringArrayIntoBytes(String[] strArr) {
        int i = 0;
        for (String length : strArr) {
            i += length.length();
        }
        byte[] bArr = new byte[i];
        int length2 = strArr.length;
        i = 0;
        int i2 = 0;
        while (i < length2) {
            String str = strArr[i];
            int length3 = str.length();
            int i3 = i2;
            i2 = 0;
            while (i2 < length3) {
                int i4 = i3 + 1;
                bArr[i3] = (byte) str.charAt(i2);
                i2++;
                i3 = i4;
            }
            i++;
            i2 = i3;
        }
        return bArr;
    }

    private static byte[] decode7to8(byte[] bArr) {
        int length = (bArr.length * 7) / 8;
        byte[] bArr2 = new byte[length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i++;
            int i4 = i2 + 1;
            bArr2[i3] = (byte) (((bArr[i] & 255) >>> i2) + ((bArr[i] & ((1 << i4) - 1)) << (7 - i2)));
            if (i2 == 6) {
                i++;
                i2 = 0;
            } else {
                i2 = i4;
            }
        }
        return bArr2;
    }
}
