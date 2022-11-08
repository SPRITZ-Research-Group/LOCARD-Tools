package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public interface FlexibleTypeDeserializer {

    public final class ThrowException implements FlexibleTypeDeserializer {
        public static final ThrowException INSTANCE = new ThrowException();

        private ThrowException() {
        }

        public final KotlinType create(Type type, String str, SimpleType simpleType, SimpleType simpleType2) {
            throw new IllegalArgumentException("This method should not be used.");
        }
    }

    KotlinType create(Type type, String str, SimpleType simpleType, SimpleType simpleType2);
}
