package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import defpackage.acnz;
import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Primitive;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class MethodSignatureMappingKt {
    public static final String computeJvmDescriptor(FunctionDescriptor functionDescriptor, boolean z, boolean z2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (z2) {
            stringBuilder.append(functionDescriptor instanceof ConstructorDescriptor ? "<init>" : functionDescriptor.getName().asString());
        }
        stringBuilder.append("(");
        for (ValueParameterDescriptor type : functionDescriptor.getValueParameters()) {
            appendErasedType(stringBuilder, type.getType());
        }
        stringBuilder.append(")");
        if (z) {
            if (TypeSignatureMappingKt.hasVoidReturnType(functionDescriptor)) {
                stringBuilder.append("V");
            } else {
                KotlinType returnType = functionDescriptor.getReturnType();
                if (returnType == null) {
                    acry.a();
                }
                appendErasedType(stringBuilder, returnType);
            }
        }
        return stringBuilder.toString();
    }

    public static /* synthetic */ String computeJvmDescriptor$default(FunctionDescriptor functionDescriptor, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return computeJvmDescriptor(functionDescriptor, z, z2);
    }

    public static final boolean forceSingleValueParameterBoxing(CallableDescriptor callableDescriptor) {
        if (!(callableDescriptor instanceof FunctionDescriptor)) {
            return false;
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) callableDescriptor;
        if (functionDescriptor.getValueParameters().size() != 1 || SpecialBuiltinMembers.isFromJavaOrBuiltins((CallableMemberDescriptor) callableDescriptor) || (acry.a(functionDescriptor.getName().asString(), ProductAction.ACTION_REMOVE) ^ 1) != 0) {
            return false;
        }
        JvmType mapToJvmType = mapToJvmType(((ValueParameterDescriptor) acnz.i(functionDescriptor.getOriginal().getValueParameters())).getType());
        JvmPrimitiveType jvmPrimitiveType = null;
        if (!(mapToJvmType instanceof Primitive)) {
            mapToJvmType = null;
        }
        Primitive primitive = (Primitive) mapToJvmType;
        if (primitive != null) {
            jvmPrimitiveType = primitive.getJvmPrimitiveType();
        }
        if (jvmPrimitiveType != JvmPrimitiveType.INT) {
            return false;
        }
        FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(functionDescriptor);
        if (overriddenBuiltinFunctionWithErasedValueParametersInJava == null) {
            return false;
        }
        JvmType mapToJvmType2 = mapToJvmType(((ValueParameterDescriptor) acnz.i(overriddenBuiltinFunctionWithErasedValueParametersInJava.getOriginal().getValueParameters())).getType());
        if (acry.a(DescriptorUtilsKt.getFqNameUnsafe(overriddenBuiltinFunctionWithErasedValueParametersInJava.getContainingDeclaration()), KotlinBuiltIns.FQ_NAMES.mutableCollection.toUnsafe()) && (mapToJvmType2 instanceof Object) && acry.a(((Object) mapToJvmType2).getInternalName(), (Object) "java/lang/Object")) {
            return true;
        }
        return false;
    }

    public static final String getInternalName(ClassDescriptor classDescriptor) {
        ClassId mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(DescriptorUtilsKt.getFqNameSafe(classDescriptor).toUnsafe());
        if (mapKotlinToJava != null) {
            return JvmClassName.byClassId(mapKotlinToJava).getInternalName();
        }
        return TypeSignatureMappingKt.computeInternalName$default(classDescriptor, null, false, 2, null);
    }

    private static final void appendErasedType(StringBuilder stringBuilder, KotlinType kotlinType) {
        stringBuilder.append(mapToJvmType(kotlinType));
    }

    public static final JvmType mapToJvmType(KotlinType kotlinType) {
        return (JvmType) TypeSignatureMappingKt.mapType$default(kotlinType, JvmTypeFactoryImpl.INSTANCE, TypeMappingMode.DEFAULT, TypeMappingConfigurationImpl.INSTANCE, null, null, false, 32, null);
    }

    public static final String computeJvmSignature(CallableDescriptor callableDescriptor) {
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        if (DescriptorUtils.isLocal(callableDescriptor)) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor == null || classDescriptor.getName().isSpecial()) {
            return null;
        }
        callableDescriptor = callableDescriptor.getOriginal();
        if (!(callableDescriptor instanceof SimpleFunctionDescriptor)) {
            callableDescriptor = null;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) callableDescriptor;
        if (simpleFunctionDescriptor == null) {
            return null;
        }
        return signatureBuildingComponents.signature(classDescriptor, computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null));
    }
}
