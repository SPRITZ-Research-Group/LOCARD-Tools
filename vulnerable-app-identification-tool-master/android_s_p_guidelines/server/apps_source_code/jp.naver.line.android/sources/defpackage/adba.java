package defpackage;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/ReflectJavaType$Factory;", "", "()V", "create", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaType;", "type", "Ljava/lang/reflect/Type;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* renamed from: adba */
public final class adba {
    private adba() {
    }

    public /* synthetic */ adba(byte b) {
        this();
    }

    public static adaz a(Type type) {
        boolean z = type instanceof Class;
        if (z) {
            Class cls = (Class) type;
            if (cls.isPrimitive()) {
                return new aday(cls);
            }
        }
        if ((type instanceof GenericArrayType) || (z && ((Class) type).isArray())) {
            return new adak(type);
        }
        if (type instanceof WildcardType) {
            return new adbd((WildcardType) type);
        }
        return new adan(type);
    }
}
