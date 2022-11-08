package kotlin.reflect.jvm.internal.impl.resolve.constants;

import defpackage.acry;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public abstract class ConstantValue<T> {
    private final T value;

    public abstract KotlinType getType(ModuleDescriptor moduleDescriptor);

    public ConstantValue(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            Object value = getValue();
            Object obj2 = null;
            if (!(obj instanceof ConstantValue)) {
                obj = null;
            }
            ConstantValue constantValue = (ConstantValue) obj;
            if (constantValue != null) {
                obj2 = constantValue.getValue();
            }
            if (!acry.a(value, obj2)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        Object value = getValue();
        return value != null ? value.hashCode() : 0;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
