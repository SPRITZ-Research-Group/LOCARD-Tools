package jp.naver.line.android.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum a {
    AU("KDDI", "KDDI", "44007", "44008", "44050", "44051", "44052", "44053", "44054", "44055", "44056", "44070", "44071", "44072", "44073", "44074", "44075", "44076", "44077", "44078", "44079", "44080", "44081", "44082", "44083", "44084", "44085", "44086", "44088", "44089", "44170"),
    AU_SMARTPASS(null, null, "44007_SP"),
    SoftBank("SBM", "SoftBank", "44004", "44006", "44020", "44040", "44041", "44042", "44043", "44044", "44045", "44046", "44047", "44048", "44090", "44092", "44093", "44094", "44095", "44096", "44097", "44098"),
    DoCoMo("DOCOMO", "NTT DOCOMO", "44001", "44002", "44003", "44009", "44010", "44011", "44012", "44013", "44014", "44015", "44016", "44017", "44018", "44019", "44021", "44022", "44023", "44024", "44025", "44026", "44027", "44028", "44029", "44030", "44031", "44032", "44033", "44034", "44035", "44036", "44037", "44038", "44039", "44049", "44058", "44060", "44061", "44062", "44063", "44064", "44065", "44066", "44067", "44068", "44069", "44087", "44099", "44140", "44141", "44142", "44143", "44144", "44145", "44161", "44162", "44163", "44164", "44165", "44190", "44191", "44192", "44193", "44194", "44198", "44199"),
    OTHER(null, null, new String[0]);
    
    private static final String TAG = "AllianceCarrier";
    private final String brandName;
    private final String carrierName;
    private final Set<String> simOperators;

    private a(String str, String str2, String... strArr) {
        this.brandName = str;
        this.carrierName = str2;
        this.simOperators = new HashSet(Arrays.asList(strArr));
    }

    public static final jp.naver.line.android.model.a a(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.model.a.a(java.lang.String, java.lang.String, java.lang.String):jp.naver.line.android.model.a. bs: []
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
        r0 = 0;
        if (r8 == 0) goto L_0x0025;
    L_0x0003:
        r1 = values();
        r2 = r1.length;
        r3 = 0;
    L_0x0009:
        if (r3 >= r2) goto L_0x0025;
    L_0x000b:
        r4 = r1[r3];
        r5 = r4.carrierName;
        if (r5 == 0) goto L_0x0022;
    L_0x0011:
        r5 = r8.toLowerCase();
        r6 = r4.carrierName;
        r6 = r6.toLowerCase();
        r5 = r5.contains(r6);
        if (r5 == 0) goto L_0x0022;
    L_0x0021:
        return r4;
    L_0x0022:
        r3 = r3 + 1;
        goto L_0x0009;
    L_0x0025:
        if (r9 == 0) goto L_0x0047;
    L_0x0027:
        r8 = r9.length();
        r1 = 5;
        if (r8 < r1) goto L_0x0047;
    L_0x002e:
        r7 = values();	 Catch:{ Exception -> 0x006a }
        r8 = r7.length;	 Catch:{ Exception -> 0x006a }
    L_0x0033:
        if (r0 >= r8) goto L_0x006a;	 Catch:{ Exception -> 0x006a }
    L_0x0035:
        r1 = r7[r0];	 Catch:{ Exception -> 0x006a }
        r2 = r1.simOperators;	 Catch:{ Exception -> 0x006a }
        if (r2 == 0) goto L_0x0044;	 Catch:{ Exception -> 0x006a }
    L_0x003b:
        r2 = r1.simOperators;	 Catch:{ Exception -> 0x006a }
        r2 = r2.contains(r9);	 Catch:{ Exception -> 0x006a }
        if (r2 == 0) goto L_0x0044;
    L_0x0043:
        return r1;
    L_0x0044:
        r0 = r0 + 1;
        goto L_0x0033;
    L_0x0047:
        if (r7 == 0) goto L_0x006a;
    L_0x0049:
        r8 = values();
        r9 = r8.length;
    L_0x004e:
        if (r0 >= r9) goto L_0x006a;
    L_0x0050:
        r1 = r8[r0];
        r2 = r1.brandName;
        if (r2 == 0) goto L_0x0067;
    L_0x0056:
        r2 = r7.toLowerCase();
        r3 = r1.brandName;
        r3 = r3.toLowerCase();
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0067;
    L_0x0066:
        return r1;
    L_0x0067:
        r0 = r0 + 1;
        goto L_0x004e;
    L_0x006a:
        r7 = OTHER;
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.model.a.a(java.lang.String, java.lang.String, java.lang.String):jp.naver.line.android.model.a");
    }
}
