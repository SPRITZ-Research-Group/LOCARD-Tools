package defpackage;

import java.lang.reflect.Member;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* renamed from: acxv */
public final class acxv {
    public static <M extends Member> void a(acxu<? extends M> acxu, Object[] objArr) {
        if (acyv.a(acxu) != objArr.length) {
            StringBuilder stringBuilder = new StringBuilder("Callable expects ");
            stringBuilder.append(acyv.a(acxu));
            stringBuilder.append(" arguments, but ");
            stringBuilder.append(objArr.length);
            stringBuilder.append(" were provided.");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
