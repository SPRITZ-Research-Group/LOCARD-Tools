package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;

class Result {
    private final int subtreeSize;
    private final KotlinType type;
    private final boolean wereChanges;

    public Result(KotlinType kotlinType, int i, boolean z) {
        this.type = kotlinType;
        this.subtreeSize = i;
        this.wereChanges = z;
    }

    public final int getSubtreeSize() {
        return this.subtreeSize;
    }

    public KotlinType getType() {
        return this.type;
    }

    public final boolean getWereChanges() {
        return this.wereChanges;
    }

    public final KotlinType getTypeIfChanged() {
        return this.wereChanges ? getType() : null;
    }
}
