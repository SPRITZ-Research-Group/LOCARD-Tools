package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import defpackage.acob;
import defpackage.acom;
import defpackage.acru;
import java.util.Map;

public final class ModuleMapping {
    public static final ModuleMapping CORRUPTED = new ModuleMapping(acom.a(), new BinaryModuleData(acob.a), "CORRUPTED");
    public static final Companion Companion = new Companion();
    public static final ModuleMapping EMPTY = new ModuleMapping(acom.a(), new BinaryModuleData(acob.a), "EMPTY");
    private final String debugName;
    private final BinaryModuleData moduleData;
    private final Map<String, PackageParts> packageFqName2Parts;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping loadModuleMapping(byte[] r15, java.lang.String r16, boolean r17, boolean r18, defpackage.acqr<? super kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion, kotlin.y> r19) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.Companion.loadModuleMapping(byte[], java.lang.String, boolean, boolean, acqr):kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
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
            r14 = this;
            r0 = r15;
            r2 = r19;
            if (r0 != 0) goto L_0x0008;
        L_0x0005:
            r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.EMPTY;
            return r0;
        L_0x0008:
            r3 = new java.io.DataInputStream;
            r4 = new java.io.ByteArrayInputStream;
            r4.<init>(r15);
            r4 = (java.io.InputStream) r4;
            r3.<init>(r4);
            r0 = r3.readInt();	 Catch:{ IOException -> 0x01dc }
            r4 = new int[r0];	 Catch:{ IOException -> 0x01dc }
            r5 = 0;	 Catch:{ IOException -> 0x01dc }
            r6 = 0;	 Catch:{ IOException -> 0x01dc }
        L_0x001c:
            if (r6 >= r0) goto L_0x0027;	 Catch:{ IOException -> 0x01dc }
        L_0x001e:
            r7 = r3.readInt();	 Catch:{ IOException -> 0x01dc }
            r4[r6] = r7;	 Catch:{ IOException -> 0x01dc }
            r6 = r6 + 1;
            goto L_0x001c;
        L_0x0027:
            r6 = new kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
            r0 = java.util.Arrays.copyOf(r4, r0);
            r6.<init>(r0);
            if (r17 != 0) goto L_0x003e;
        L_0x0032:
            r0 = r6.isCompatible();
            if (r0 != 0) goto L_0x003e;
        L_0x0038:
            r2.invoke(r6);
            r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.EMPTY;
            return r0;
        L_0x003e:
            r6 = (kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion) r6;
            r0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionSpecificBehaviorKt.isKotlin1Dot4OrLater(r6);
            if (r0 == 0) goto L_0x004b;
        L_0x0046:
            r0 = r3.readInt();
            goto L_0x004c;
        L_0x004b:
            r0 = 0;
        L_0x004c:
            r6 = new kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
            r7 = 1;
            r0 = r0 & r7;
            if (r0 == 0) goto L_0x0054;
        L_0x0052:
            r0 = 1;
            goto L_0x0055;
        L_0x0054:
            r0 = 0;
        L_0x0055:
            r6.<init>(r4, r0);
            if (r17 != 0) goto L_0x0066;
        L_0x005a:
            r0 = r6.isCompatible();
            if (r0 != 0) goto L_0x0066;
        L_0x0060:
            r2.invoke(r6);
            r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.EMPTY;
            return r0;
        L_0x0066:
            r3 = (java.io.InputStream) r3;
            r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module.parseFrom(r3);
            if (r0 != 0) goto L_0x0071;
        L_0x006e:
            r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.EMPTY;
            return r0;
        L_0x0071:
            r1 = new java.util.LinkedHashMap;
            r1.<init>();
            r2 = r0.getPackagePartsList();
            r2 = r2.iterator();
        L_0x007e:
            r3 = r2.hasNext();
            r4 = 0;
            if (r3 == 0) goto L_0x0146;
        L_0x0085:
            r3 = r2.next();
            r3 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts) r3;
            r6 = r3.getPackageFqName();
            r8 = r1;
            r8 = (java.util.Map) r8;
            r9 = r8.get(r6);
            if (r9 != 0) goto L_0x00a0;
        L_0x0098:
            r9 = new kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.PackageParts;
            r9.<init>(r6);
            r8.put(r6, r9);
        L_0x00a0:
            r9 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.PackageParts) r9;
            r8 = r3.getShortClassNameList();
            r8 = (java.lang.Iterable) r8;
            r8 = r8.iterator();
            r10 = 0;
        L_0x00ad:
            r11 = r8.hasNext();
            if (r11 == 0) goto L_0x00f8;
        L_0x00b3:
            r11 = r8.next();
            r11 = (java.lang.String) r11;
            r12 = r3.getMultifileFacadeShortNameIdList();
            r12 = defpackage.acnz.b(r12, r10);
            r12 = (java.lang.Integer) r12;
            if (r12 == 0) goto L_0x00cf;
        L_0x00c5:
            r12 = r12.intValue();
            r12 = r12 - r7;
            r12 = java.lang.Integer.valueOf(r12);
            goto L_0x00d0;
        L_0x00cf:
            r12 = r4;
        L_0x00d0:
            if (r12 == 0) goto L_0x00e5;
        L_0x00d2:
            r13 = r3.getMultifileFacadeShortNameList();
            r12 = (java.lang.Number) r12;
            r12 = r12.intValue();
            r13 = (java.util.List) r13;
            r12 = defpackage.acnz.b(r13, r12);
            r12 = (java.lang.String) r12;
            goto L_0x00e6;
        L_0x00e5:
            r12 = r4;
        L_0x00e6:
            if (r12 == 0) goto L_0x00ed;
        L_0x00e8:
            r12 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMappingKt.access$internalNameOf(r6, r12);
            goto L_0x00ee;
        L_0x00ed:
            r12 = r4;
        L_0x00ee:
            r11 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMappingKt.access$internalNameOf(r6, r11);
            r9.addPart(r11, r12);
            r10 = r10 + 1;
            goto L_0x00ad;
        L_0x00f8:
            if (r18 == 0) goto L_0x007e;
        L_0x00fa:
            r6 = r3.getClassWithJvmPackageNameShortNameList();
            r6 = (java.lang.Iterable) r6;
            r6 = r6.iterator();
            r8 = 0;
        L_0x0105:
            r11 = r6.hasNext();
            if (r11 == 0) goto L_0x007e;
        L_0x010b:
            r11 = r6.next();
            r11 = (java.lang.String) r11;
            r12 = r3.getClassWithJvmPackageNamePackageIdList();
            r12 = defpackage.acnz.b(r12, r8);
            r12 = (java.lang.Integer) r12;
            if (r12 != 0) goto L_0x0127;
        L_0x011d:
            r12 = r3.getClassWithJvmPackageNamePackageIdList();
            r12 = defpackage.acnz.h(r12);
            r12 = (java.lang.Integer) r12;
        L_0x0127:
            if (r12 == 0) goto L_0x0143;
        L_0x0129:
            r12 = r12.intValue();
            r13 = r0.getJvmPackageNameList();
            r13 = (java.util.List) r13;
            r12 = defpackage.acnz.b(r13, r12);
            r12 = (java.lang.String) r12;
            if (r12 != 0) goto L_0x013c;
        L_0x013b:
            goto L_0x0143;
        L_0x013c:
            r11 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMappingKt.internalNameOf(r12, r11);
            r9.addPart(r11, r4);
        L_0x0143:
            r8 = r8 + 1;
            goto L_0x0105;
        L_0x0146:
            r2 = r0.getMetadataPartsList();
            r2 = r2.iterator();
        L_0x014e:
            r3 = r2.hasNext();
            if (r3 == 0) goto L_0x018f;
        L_0x0154:
            r3 = r2.next();
            r3 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts) r3;
            r5 = r1;
            r5 = (java.util.Map) r5;
            r6 = r3.getPackageFqName();
            r7 = r5.get(r6);
            if (r7 != 0) goto L_0x0173;
        L_0x0167:
            r7 = new kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.PackageParts;
            r8 = r3.getPackageFqName();
            r7.<init>(r8);
            r5.put(r6, r7);
        L_0x0173:
            r7 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.PackageParts) r7;
            r3 = r3.getShortClassNameList();
            r3 = (java.lang.Iterable) r3;
            r3 = r3.iterator();
        L_0x017f:
            r5 = r3.hasNext();
            if (r5 == 0) goto L_0x014e;
        L_0x0185:
            r5 = r3.next();
            r5 = (java.lang.String) r5;
            r7.addMetadataPart(r5);
            goto L_0x017f;
        L_0x018f:
            r2 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolverImpl;
            r3 = r0.getStringTable();
            r5 = r0.getQualifiedNameTable();
            r2.<init>(r3, r5);
            r0 = r0.getAnnotationList();
            r0 = (java.lang.Iterable) r0;
            r3 = new java.util.ArrayList;
            r5 = 10;
            r5 = defpackage.acns.a(r0, r5);
            r3.<init>(r5);
            r3 = (java.util.Collection) r3;
            r0 = r0.iterator();
        L_0x01b3:
            r5 = r0.hasNext();
            if (r5 == 0) goto L_0x01cb;
        L_0x01b9:
            r5 = r0.next();
            r5 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation) r5;
            r5 = r5.getId();
            r5 = r2.getQualifiedClassName(r5);
            r3.add(r5);
            goto L_0x01b3;
        L_0x01cb:
            r3 = (java.util.List) r3;
            r0 = new kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping;
            r1 = (java.util.Map) r1;
            r2 = new kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.BinaryModuleData;
            r2.<init>(r3);
            r3 = r16;
            r0.<init>(r1, r2, r3, r4);
            return r0;
        L_0x01dc:
            r0 = kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.CORRUPTED;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping.Companion.loadModuleMapping(byte[], java.lang.String, boolean, boolean, acqr):kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ModuleMapping");
        }
    }

    private ModuleMapping(Map<String, PackageParts> map, BinaryModuleData binaryModuleData, String str) {
        this.packageFqName2Parts = map;
        this.moduleData = binaryModuleData;
        this.debugName = str;
    }

    public /* synthetic */ ModuleMapping(Map map, BinaryModuleData binaryModuleData, String str, acru acru) {
        this(map, binaryModuleData, str);
    }

    public final Map<String, PackageParts> getPackageFqName2Parts() {
        return this.packageFqName2Parts;
    }

    public final String toString() {
        return this.debugName;
    }
}
