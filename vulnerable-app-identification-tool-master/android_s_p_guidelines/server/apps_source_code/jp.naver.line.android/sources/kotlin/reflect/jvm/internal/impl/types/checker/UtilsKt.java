package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;

public final class UtilsKt {
    public static final KotlinType findCorrespondingSupertype(KotlinType kotlinType, KotlinType kotlinType2, TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(new SubtypePathNode(kotlinType, null));
        TypeConstructor constructor = kotlinType2.getConstructor();
        while (!arrayDeque.isEmpty()) {
            SubtypePathNode subtypePathNode = (SubtypePathNode) arrayDeque.poll();
            KotlinType type = subtypePathNode.getType();
            TypeConstructor constructor2 = type.getConstructor();
            KotlinType type2;
            if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor2, constructor)) {
                boolean isMarkedNullable = type.isMarkedNullable();
                for (subtypePathNode = subtypePathNode.getPrevious(); subtypePathNode != null; subtypePathNode = subtypePathNode.getPrevious()) {
                    Object obj;
                    type2 = subtypePathNode.getType();
                    Iterable<TypeProjection> arguments = type2.getArguments();
                    if (!((arguments instanceof Collection) && ((Collection) arguments).isEmpty())) {
                        for (TypeProjection projectionKind : arguments) {
                            Object obj2;
                            if (projectionKind.getProjectionKind() != Variance.INVARIANT) {
                                obj2 = 1;
                                continue;
                            } else {
                                obj2 = null;
                                continue;
                            }
                            if (obj2 != null) {
                                obj = 1;
                                break;
                            }
                        }
                    }
                    obj = null;
                    if (obj != null) {
                        type = approximate(CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(TypeConstructorSubstitution.Companion.create(type2), false, 1, null).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT));
                    } else {
                        type = TypeConstructorSubstitution.Companion.create(type2).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                    }
                    isMarkedNullable = isMarkedNullable || type2.isMarkedNullable();
                }
                TypeConstructor constructor3 = type.getConstructor();
                if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor)) {
                    return TypeUtils.makeNullableAsSpecified(type, isMarkedNullable);
                }
                StringBuilder stringBuilder = new StringBuilder("Type constructors should be equals!\nsubstitutedSuperType: ");
                stringBuilder.append(debugInfo(constructor3));
                stringBuilder.append(", \n\nsupertype: ");
                stringBuilder.append(debugInfo(constructor));
                stringBuilder.append(" \n");
                stringBuilder.append(typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor));
                throw new AssertionError(stringBuilder.toString());
            }
            for (KotlinType type22 : constructor2.getSupertypes()) {
                arrayDeque.add(new SubtypePathNode(type22, subtypePathNode));
            }
        }
        return null;
    }

    private static final KotlinType approximate(KotlinType kotlinType) {
        return (KotlinType) CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType).getUpper();
    }

    private static final String debugInfo(TypeConstructor typeConstructor) {
        StringBuilder stringBuilder = new StringBuilder();
        UtilsKt$debugInfo$1$1 utilsKt$debugInfo$1$1 = new UtilsKt$debugInfo$1$1(stringBuilder);
        utilsKt$debugInfo$1$1.invoke("type: ".concat(String.valueOf(typeConstructor)));
        StringBuilder stringBuilder2 = new StringBuilder("hashCode: ");
        stringBuilder2.append(typeConstructor.hashCode());
        utilsKt$debugInfo$1$1.invoke(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder("javaClass: ");
        stringBuilder2.append(typeConstructor.getClass().getCanonicalName());
        utilsKt$debugInfo$1$1.invoke(stringBuilder2.toString());
        for (DeclarationDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor(); declarationDescriptor != null; declarationDescriptor = declarationDescriptor.getContainingDeclaration()) {
            stringBuilder2 = new StringBuilder("fqName: ");
            stringBuilder2.append(DescriptorRenderer.FQ_NAMES_IN_TYPES.render(declarationDescriptor));
            utilsKt$debugInfo$1$1.invoke(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder("javaClass: ");
            stringBuilder2.append(declarationDescriptor.getClass().getCanonicalName());
            utilsKt$debugInfo$1$1.invoke(stringBuilder2.toString());
        }
        return stringBuilder.toString();
    }
}
