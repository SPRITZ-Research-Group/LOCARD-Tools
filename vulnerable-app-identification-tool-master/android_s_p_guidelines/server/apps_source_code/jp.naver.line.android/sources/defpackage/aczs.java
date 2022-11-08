package defpackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/components/SignatureSerializer;", "", "()V", "constructorDesc", "", "constructor", "Ljava/lang/reflect/Constructor;", "fieldDesc", "field", "Ljava/lang/reflect/Field;", "methodDesc", "method", "Ljava/lang/reflect/Method;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* renamed from: aczs */
final class aczs {
    public static final aczs a = new aczs();

    private aczs() {
    }

    public static String a(Method method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (Class f : method.getParameterTypes()) {
            stringBuilder.append(adab.f(f));
        }
        stringBuilder.append(")");
        stringBuilder.append(adab.f(method.getReturnType()));
        return stringBuilder.toString();
    }

    public static String a(Constructor<?> constructor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (Class f : constructor.getParameterTypes()) {
            stringBuilder.append(adab.f(f));
        }
        stringBuilder.append(")V");
        return stringBuilder.toString();
    }

    public static String a(Field field) {
        return adab.f(field.getType());
    }
}
