package defpackage;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\u001a6\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\n\b\u0000\u0010\n*\u0004\u0018\u00010\u000b*\b\u0012\u0004\u0012\u0002H\n0\t2\u0006\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0000\u001a\u0018\u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\u001a\u0018\u0010\u0011\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\u001a\f\u0010\u0012\u001a\u00020\r*\u00020\u0002H\u0002\u001a\u0014\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0010*\u0004\u0018\u00010\u0014H\u0000\u001a\u0012\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0010*\u00020\u0001H\u0000\"\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0015"}, d2 = {"expectedReceiverType", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "getExpectedReceiverType", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;)Lorg/jetbrains/kotlin/types/KotlinType;", "coerceToExpectedReceiverType", "", "descriptor", "createInlineClassAwareCallerIfNeeded", "Lkotlin/reflect/jvm/internal/calls/Caller;", "M", "Ljava/lang/reflect/Member;", "isDefault", "", "getBoxMethod", "Ljava/lang/reflect/Method;", "Ljava/lang/Class;", "getUnboxMethod", "hasInlineClassReceiver", "toInlineClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/DeclarationDescriptor;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acyy */
public final class acyy {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <M extends Member> acxu<M> a(acxu<? extends M> acxu, CallableMemberDescriptor callableMemberDescriptor, boolean z) {
        Object obj = null;
        if (!InlineClassesUtilsKt.isGetterOfUnderlyingPropertyOfInlineClass(callableMemberDescriptor)) {
            Object obj2;
            Iterable<ValueParameterDescriptor> valueParameters = callableMemberDescriptor.getValueParameters();
            if (!((valueParameters instanceof Collection) && ((Collection) valueParameters).isEmpty())) {
                for (ValueParameterDescriptor type : valueParameters) {
                    if (InlineClassesUtilsKt.isInlineClassType(type.getType())) {
                        obj2 = 1;
                        break;
                    }
                }
            }
            obj2 = null;
            if (obj2 == null) {
                KotlinType returnType = callableMemberDescriptor.getReturnType();
                if (returnType == null || !InlineClassesUtilsKt.isInlineClassType(returnType)) {
                    if (!(acxu instanceof acxt)) {
                        returnType = acyy.a(callableMemberDescriptor);
                        obj2 = (returnType == null || !InlineClassesUtilsKt.isInlineClassType(returnType)) ? null : 1;
                    }
                    return obj == null ? new acyw(callableMemberDescriptor, acxu, z) : acxu;
                }
            }
        }
        obj = 1;
        if (obj == null) {
        }
    }

    public static final java.lang.reflect.Method a(java.lang.Class<?> r3, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:acyy.a(java.lang.Class, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor):java.lang.reflect.Method. bs: []
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
        r0 = "unbox-impl";	 Catch:{ NoSuchMethodException -> 0x000a }
        r1 = 0;	 Catch:{ NoSuchMethodException -> 0x000a }
        r1 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x000a }
        r0 = r3.getDeclaredMethod(r0, r1);	 Catch:{ NoSuchMethodException -> 0x000a }
        return r0;
    L_0x000a:
        r0 = new acxb;
        r1 = new java.lang.StringBuilder;
        r2 = "No unbox method found in inline class: ";
        r1.<init>(r2);
        r1.append(r3);
        r3 = " (calling ";
        r1.append(r3);
        r1.append(r4);
        r3 = 41;
        r1.append(r3);
        r3 = r1.toString();
        r0.<init>(r3);
        r0 = (java.lang.Throwable) r0;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: acyy.a(java.lang.Class, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor):java.lang.reflect.Method");
    }

    public static final java.lang.reflect.Method b(java.lang.Class<?> r4, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:acyy.b(java.lang.Class, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor):java.lang.reflect.Method. bs: []
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
        r0 = "box-impl";	 Catch:{ NoSuchMethodException -> 0x0015 }
        r1 = 1;	 Catch:{ NoSuchMethodException -> 0x0015 }
        r1 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x0015 }
        r2 = 0;	 Catch:{ NoSuchMethodException -> 0x0015 }
        r3 = defpackage.acyy.a(r4, r5);	 Catch:{ NoSuchMethodException -> 0x0015 }
        r3 = r3.getReturnType();	 Catch:{ NoSuchMethodException -> 0x0015 }
        r1[r2] = r3;	 Catch:{ NoSuchMethodException -> 0x0015 }
        r0 = r4.getDeclaredMethod(r0, r1);	 Catch:{ NoSuchMethodException -> 0x0015 }
        return r0;
    L_0x0015:
        r0 = new acxb;
        r1 = new java.lang.StringBuilder;
        r2 = "No box method found in inline class: ";
        r1.<init>(r2);
        r1.append(r4);
        r4 = " (calling ";
        r1.append(r4);
        r1.append(r5);
        r4 = 41;
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        r0 = (java.lang.Throwable) r0;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: acyy.b(java.lang.Class, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor):java.lang.reflect.Method");
    }

    public static final Class<?> a(KotlinType kotlinType) {
        return acyy.a((DeclarationDescriptor) kotlinType.getConstructor().getDeclarationDescriptor());
    }

    public static final Class<?> a(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof ClassDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
            if (classDescriptor.isInline()) {
                Class<?> a = acxm.a(classDescriptor);
                if (a != null) {
                    return a;
                }
                StringBuilder stringBuilder = new StringBuilder("Class object for the class ");
                stringBuilder.append(classDescriptor.getName());
                stringBuilder.append(" cannot be found (classId=");
                stringBuilder.append(DescriptorUtilsKt.getClassId((ClassifierDescriptor) declarationDescriptor));
                stringBuilder.append(')');
                throw new acxb(stringBuilder.toString());
            }
        }
        return null;
    }

    private static final KotlinType a(CallableMemberDescriptor callableMemberDescriptor) {
        ReceiverParameterDescriptor extensionReceiverParameter = callableMemberDescriptor.getExtensionReceiverParameter();
        ReceiverParameterDescriptor dispatchReceiverParameter = callableMemberDescriptor.getDispatchReceiverParameter();
        if (extensionReceiverParameter != null) {
            return extensionReceiverParameter.getType();
        }
        KotlinType kotlinType = null;
        if (dispatchReceiverParameter == null) {
            return null;
        }
        if (callableMemberDescriptor instanceof ConstructorDescriptor) {
            return dispatchReceiverParameter.getType();
        }
        DeclarationDescriptor containingDeclaration = callableMemberDescriptor.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor != null) {
            kotlinType = classDescriptor.getDefaultType();
        }
        return kotlinType;
    }

    public static final Object a(Object obj, CallableMemberDescriptor callableMemberDescriptor) {
        if ((callableMemberDescriptor instanceof PropertyDescriptor) && InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass((VariableDescriptor) callableMemberDescriptor)) {
            return obj;
        }
        KotlinType a = acyy.a(callableMemberDescriptor);
        if (a != null) {
            Class a2 = acyy.a(a);
            if (a2 != null) {
                Method a3 = acyy.a(a2, callableMemberDescriptor);
                if (a3 != null) {
                    return a3.invoke(obj, new Object[0]);
                }
            }
        }
        return obj;
    }
}
