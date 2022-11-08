package kotlin.reflect.jvm.internal.impl.types;

public interface TypeProjection {
    Variance getProjectionKind();

    KotlinType getType();

    boolean isStarProjection();
}
