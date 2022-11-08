package kotlin.reflect.jvm.internal.impl.types;

import defpackage.acry;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public final class KotlinTypeFactory {
    public static final KotlinTypeFactory INSTANCE = new KotlinTypeFactory();

    private KotlinTypeFactory() {
    }

    private final MemberScope computeMemberScope(TypeConstructor typeConstructor, List<? extends TypeProjection> list) {
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (declarationDescriptor instanceof TypeParameterDescriptor) {
            return declarationDescriptor.getDefaultType().getMemberScope();
        }
        if (declarationDescriptor instanceof ClassDescriptor) {
            if (list.isEmpty()) {
                return ((ClassDescriptor) declarationDescriptor).getDefaultType().getMemberScope();
            }
            return ((ClassDescriptor) declarationDescriptor).getMemberScope(TypeConstructorSubstitution.Companion.create(typeConstructor, list));
        } else if (declarationDescriptor instanceof TypeAliasDescriptor) {
            StringBuilder stringBuilder = new StringBuilder("Scope for abbreviation: ");
            stringBuilder.append(((TypeAliasDescriptor) declarationDescriptor).getName());
            return ErrorUtils.createErrorScope(stringBuilder.toString(), true);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder("Unsupported classifier: ");
            stringBuilder2.append(declarationDescriptor);
            stringBuilder2.append(" for constructor: ");
            stringBuilder2.append(typeConstructor);
            throw new IllegalStateException(stringBuilder2.toString());
        }
    }

    public static final SimpleType simpleType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        if (!annotations.isEmpty() || !list.isEmpty() || z || typeConstructor.getDeclarationDescriptor() == null) {
            return simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, list, z, INSTANCE.computeMemberScope(typeConstructor, list));
        }
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            acry.a();
        }
        return declarationDescriptor.getDefaultType();
    }

    public static final SimpleType simpleTypeWithNonTrivialMemberScope(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z, MemberScope memberScope) {
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(typeConstructor, list, z, memberScope);
        if (annotations.isEmpty()) {
            return simpleTypeImpl;
        }
        return new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }

    public static final SimpleType simpleNotNullType(Annotations annotations, ClassDescriptor classDescriptor, List<? extends TypeProjection> list) {
        return simpleType(annotations, classDescriptor.getTypeConstructor(), list, false);
    }

    public static final UnwrappedType flexibleType(SimpleType simpleType, SimpleType simpleType2) {
        if (acry.a((Object) simpleType, (Object) simpleType2)) {
            return simpleType;
        }
        return new FlexibleTypeImpl(simpleType, simpleType2);
    }
}
