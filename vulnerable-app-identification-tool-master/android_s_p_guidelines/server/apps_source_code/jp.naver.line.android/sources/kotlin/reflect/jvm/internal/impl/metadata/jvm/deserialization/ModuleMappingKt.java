package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

public final class ModuleMappingKt {
    private static final String internalNameOf(String str, String str2) {
        if ((((CharSequence) str).length() == 0 ? 1 : null) != null) {
            return str2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.replace('.', '/'));
        stringBuilder.append("/");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }
}
