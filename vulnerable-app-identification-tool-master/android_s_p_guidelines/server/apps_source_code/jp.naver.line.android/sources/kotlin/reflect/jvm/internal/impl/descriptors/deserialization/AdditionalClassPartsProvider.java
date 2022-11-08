package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import defpackage.acob;
import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface AdditionalClassPartsProvider {

    public final class None implements AdditionalClassPartsProvider {
        public static final None INSTANCE = new None();

        private None() {
        }

        public final Collection<KotlinType> getSupertypes(ClassDescriptor classDescriptor) {
            return acob.a;
        }

        public final Collection<SimpleFunctionDescriptor> getFunctions(Name name, ClassDescriptor classDescriptor) {
            return acob.a;
        }

        public final Collection<Name> getFunctionsNames(ClassDescriptor classDescriptor) {
            return acob.a;
        }

        public final Collection<ClassConstructorDescriptor> getConstructors(ClassDescriptor classDescriptor) {
            return acob.a;
        }
    }

    Collection<ClassConstructorDescriptor> getConstructors(ClassDescriptor classDescriptor);

    Collection<SimpleFunctionDescriptor> getFunctions(Name name, ClassDescriptor classDescriptor);

    Collection<Name> getFunctionsNames(ClassDescriptor classDescriptor);

    Collection<KotlinType> getSupertypes(ClassDescriptor classDescriptor);
}
