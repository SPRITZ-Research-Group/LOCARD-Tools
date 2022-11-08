package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import defpackage.acnz;
import defpackage.acqq;
import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.v;

public final class JavaTypeResolverKt {
    private static final FqName JAVA_LANG_CLASS_FQ_NAME = new FqName("java.lang.Class");

    public static final TypeProjection makeStarProjection(TypeParameterDescriptor typeParameterDescriptor, JavaTypeAttributes javaTypeAttributes) {
        if (javaTypeAttributes.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) {
            return new TypeProjectionImpl(StarProjectionImplKt.starProjectionType(typeParameterDescriptor));
        }
        return new StarProjectionImpl(typeParameterDescriptor);
    }

    public static /* synthetic */ JavaTypeAttributes toAttributes$default(TypeUsage typeUsage, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            typeParameterDescriptor = null;
        }
        return toAttributes(typeUsage, z, typeParameterDescriptor);
    }

    public static final JavaTypeAttributes toAttributes(TypeUsage typeUsage, boolean z, TypeParameterDescriptor typeParameterDescriptor) {
        return new JavaTypeAttributes(typeUsage, null, z, typeParameterDescriptor, 2, null);
    }

    public static /* synthetic */ KotlinType getErasedUpperBound$default(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, acqq acqq, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterDescriptor2 = null;
        }
        if ((i & 2) != 0) {
            acqq = new JavaTypeResolverKt$getErasedUpperBound$1(typeParameterDescriptor);
        }
        return getErasedUpperBound(typeParameterDescriptor, typeParameterDescriptor2, acqq);
    }

    public static final KotlinType getErasedUpperBound(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, acqq<? extends KotlinType> acqq) {
        if (typeParameterDescriptor == typeParameterDescriptor2) {
            return (KotlinType) acqq.invoke();
        }
        KotlinType kotlinType = (KotlinType) acnz.e(typeParameterDescriptor.getUpperBounds());
        if (kotlinType.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor) {
            return TypeUtilsKt.replaceArgumentsWithStarProjections(kotlinType);
        }
        Object typeParameterDescriptor3;
        if (typeParameterDescriptor2 != null) {
            typeParameterDescriptor3 = typeParameterDescriptor2;
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            while (true) {
                Object obj = (TypeParameterDescriptor) declarationDescriptor;
                if ((acry.a(obj, typeParameterDescriptor3) ^ 1) == 0) {
                    return (KotlinType) acqq.invoke();
                }
                KotlinType kotlinType2 = (KotlinType) acnz.e(obj.getUpperBounds());
                if (kotlinType2.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor) {
                    return TypeUtilsKt.replaceArgumentsWithStarProjections(kotlinType2);
                }
                declarationDescriptor = kotlinType2.getConstructor().getDeclarationDescriptor();
                if (declarationDescriptor == null) {
                    throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                }
            }
        } else {
            throw new v("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
        }
    }
}
