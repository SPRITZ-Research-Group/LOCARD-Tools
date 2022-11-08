package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class NewKotlinTypeCheckerKt {
    public static final boolean isClassType(SimpleType simpleType) {
        return simpleType.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor;
    }

    public static final boolean isSingleClassifierType(SimpleType simpleType) {
        return (KotlinTypeKt.isError((KotlinType) simpleType) || (simpleType.getConstructor().getDeclarationDescriptor() instanceof TypeAliasDescriptor) || (simpleType.getConstructor().getDeclarationDescriptor() == null && !(simpleType instanceof CapturedType) && !(simpleType instanceof NewCapturedType) && !(simpleType instanceof DefinitelyNotNullType))) ? false : true;
    }

    public static final boolean isIntersectionType(SimpleType simpleType) {
        return simpleType.getConstructor() instanceof IntersectionTypeConstructor;
    }
}
