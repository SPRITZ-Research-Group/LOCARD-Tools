package kotlin.reflect.jvm.internal.impl.renderer;

import com.google.obf.ly;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class RenderingUtilsKt {
    public static final String render(Name name) {
        if (!shouldBeEscaped(name)) {
            return name.asString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("`".concat(String.valueOf(name.asString())));
        stringBuilder.append('`');
        return stringBuilder.toString();
    }

    private static final boolean shouldBeEscaped(Name name) {
        if (name.isSpecial()) {
            return false;
        }
        String asString = name.asString();
        if (!KeywordStringsGenerated.KEYWORDS.contains(asString)) {
            Object obj;
            CharSequence charSequence = asString;
            for (int i = 0; i < charSequence.length(); i++) {
                char charAt = charSequence.charAt(i);
                Object obj2 = (Character.isLetterOrDigit(charAt) || charAt == '_') ? null : 1;
                if (obj2 != null) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static final String render(FqNameUnsafe fqNameUnsafe) {
        return renderFqName(fqNameUnsafe.pathSegments());
    }

    public static final String renderFqName(List<Name> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Name name : list) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(ly.a);
            }
            stringBuilder.append(render(name));
        }
        return stringBuilder.toString();
    }
}
