package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

final class EnhancementResult<T> {
    private final Annotations enhancementAnnotations;
    private final T result;

    public final T component1() {
        return this.result;
    }

    public final Annotations component2() {
        return this.enhancementAnnotations;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof EnhancementResult) {
                EnhancementResult enhancementResult = (EnhancementResult) obj;
                if (acry.a(this.result, enhancementResult.result)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.result;
        int i = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Annotations annotations = this.enhancementAnnotations;
        if (annotations != null) {
            i = annotations.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("EnhancementResult(result=");
        stringBuilder.append(this.result);
        stringBuilder.append(", enhancementAnnotations=");
        stringBuilder.append(this.enhancementAnnotations);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public EnhancementResult(T t, Annotations annotations) {
        this.result = t;
        this.enhancementAnnotations = annotations;
    }
}
