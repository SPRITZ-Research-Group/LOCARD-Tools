package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

public interface TypeConstructor {
    KotlinBuiltIns getBuiltIns();

    ClassifierDescriptor getDeclarationDescriptor();

    List<TypeParameterDescriptor> getParameters();

    Collection<KotlinType> getSupertypes();

    boolean isDenotable();
}
