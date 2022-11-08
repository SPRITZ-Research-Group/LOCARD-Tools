package defpackage;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.name.Name;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/ReflectJavaAnnotationArgument$Factory;", "", "()V", "create", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaAnnotationArgument;", "value", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* renamed from: adae */
public final class adae {
    private adae() {
    }

    public /* synthetic */ adae(byte b) {
        this();
    }

    public static adad a(Object obj, Name name) {
        if (adab.b(obj.getClass())) {
            return new adaq(name, (Enum) obj);
        }
        if (obj instanceof Annotation) {
            return new adaf(name, (Annotation) obj);
        }
        if (obj instanceof Object[]) {
            return new adaj(name, (Object[]) obj);
        }
        if (obj instanceof Class) {
            return new adam(name, (Class) obj);
        }
        return new adas(name, obj);
    }
}
