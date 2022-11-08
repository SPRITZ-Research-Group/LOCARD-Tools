package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class NameResolverUtilKt {
    public static final ClassId getClassId(NameResolver nameResolver, int i) {
        return ClassId.fromString(nameResolver.getQualifiedClassName(i), nameResolver.isLocalClassName(i));
    }

    public static final Name getName(NameResolver nameResolver, int i) {
        return Name.guessByFirstCharacter(nameResolver.getString(i));
    }
}
