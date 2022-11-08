package kotlin.reflect.jvm.internal.impl.types;

import defpackage.acru;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public abstract class TypeSubstitution {
    public static final Companion Companion = new Companion();
    public static final TypeSubstitution EMPTY = new TypeSubstitution$Companion$EMPTY$1();

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    public boolean approximateCapturedTypes() {
        return false;
    }

    public boolean approximateContravariantCapturedTypes() {
        return false;
    }

    public Annotations filterAnnotations(Annotations annotations) {
        return annotations;
    }

    public abstract TypeProjection get(KotlinType kotlinType);

    public boolean isEmpty() {
        return false;
    }

    public KotlinType prepareTopLevelType(KotlinType kotlinType, Variance variance) {
        return kotlinType;
    }

    public final TypeSubstitutor buildSubstitutor() {
        return TypeSubstitutor.create(this);
    }
}
