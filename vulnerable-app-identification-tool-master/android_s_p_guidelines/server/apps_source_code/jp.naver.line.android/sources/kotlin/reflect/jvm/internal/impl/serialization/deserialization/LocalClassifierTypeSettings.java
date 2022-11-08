package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public interface LocalClassifierTypeSettings {

    public final class Default implements LocalClassifierTypeSettings {
        public static final Default INSTANCE = new Default();

        public final SimpleType getReplacementTypeForLocalClassifiers() {
            return null;
        }

        private Default() {
        }
    }

    SimpleType getReplacementTypeForLocalClassifiers();
}
