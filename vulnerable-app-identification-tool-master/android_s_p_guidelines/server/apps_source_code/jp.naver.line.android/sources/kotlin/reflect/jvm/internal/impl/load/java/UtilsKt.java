package kotlin.reflect.jvm.internal.impl.load.java;

public final class UtilsKt {
    public static final kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue lexicalCastFrom(kotlin.reflect.jvm.internal.impl.types.KotlinType r4, java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:kotlin.reflect.jvm.internal.impl.load.java.UtilsKt.lexicalCastFrom(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.lang.String):kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue. bs: []
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
        r0 = r4.getConstructor();
        r0 = r0.getDeclarationDescriptor();
        r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
        r2 = 0;
        if (r1 == 0) goto L_0x003e;
    L_0x000d:
        r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r0;
        r1 = r0.getKind();
        r3 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ENUM_CLASS;
        if (r1 != r3) goto L_0x003e;
    L_0x0017:
        r4 = r0.getUnsubstitutedInnerClassesScope();
        r5 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r5);
        r0 = kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation.FROM_BACKEND;
        r0 = (kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation) r0;
        r4 = r4.getContributedClassifier(r5, r0);
        r5 = r4 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
        if (r5 == 0) goto L_0x003d;
    L_0x002b:
        r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r4;
        r5 = r4.getKind();
        r0 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ENUM_ENTRY;
        if (r5 != r0) goto L_0x003d;
    L_0x0035:
        r5 = new kotlin.reflect.jvm.internal.impl.load.java.EnumEntry;
        r5.<init>(r4);
        r5 = (kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue) r5;
        return r5;
    L_0x003d:
        return r2;
    L_0x003e:
        r4 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.makeNotNullable(r4);
        r0 = kotlin.reflect.jvm.internal.impl.utils.NumbersKt.extractRadix(r5);
        r1 = r0.component1();
        r0 = r0.component2();
        r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isBoolean(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r3 == 0) goto L_0x005e;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x0054:
        r4 = java.lang.Boolean.parseBoolean(r5);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        r5 = java.lang.Boolean.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x005e:
        r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isChar(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r3 == 0) goto L_0x0077;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x0064:
        r5 = (java.lang.CharSequence) r5;	 Catch:{ IllegalArgumentException -> 0x00c0 }
        r4 = r5.length();	 Catch:{ IllegalArgumentException -> 0x00c0 }
        r0 = 1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r4 != r0) goto L_0x00c0;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x006d:
        r4 = 0;	 Catch:{ IllegalArgumentException -> 0x00c0 }
        r4 = r5.charAt(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        r5 = java.lang.Character.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x0077:
        r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isByte(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r3 == 0) goto L_0x0082;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x007d:
        r5 = defpackage.adda.a(r1, r0);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x0082:
        r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isShort(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r3 == 0) goto L_0x008d;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x0088:
        r5 = defpackage.adda.b(r1, r0);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x008d:
        r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isInt(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r3 == 0) goto L_0x0098;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x0093:
        r5 = defpackage.adda.c(r1, r0);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x0098:
        r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isLong(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r3 == 0) goto L_0x00a3;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x009e:
        r5 = defpackage.adda.d(r1, r0);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x00a3:
        r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isFloat(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r0 == 0) goto L_0x00ae;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x00a9:
        r5 = defpackage.adcz.c(r5);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x00ae:
        r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isDouble(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r0 == 0) goto L_0x00b9;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x00b4:
        r5 = defpackage.adcz.d(r5);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00c0 }
    L_0x00b9:
        r4 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isString(r4);	 Catch:{ IllegalArgumentException -> 0x00c0 }
        if (r4 == 0) goto L_0x00c0;
    L_0x00bf:
        goto L_0x00c1;
    L_0x00c0:
        r5 = r2;
    L_0x00c1:
        if (r5 == 0) goto L_0x00cb;
    L_0x00c3:
        r4 = new kotlin.reflect.jvm.internal.impl.load.java.Constant;
        r4.<init>(r5);
        r4 = (kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue) r4;
        return r4;
    L_0x00cb:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.UtilsKt.lexicalCastFrom(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.lang.String):kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue");
    }
}
