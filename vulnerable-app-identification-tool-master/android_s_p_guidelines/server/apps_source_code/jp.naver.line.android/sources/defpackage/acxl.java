package defpackage;

import java.lang.reflect.Method;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"signature", "", "Ljava/lang/reflect/Method;", "getSignature", "(Ljava/lang/reflect/Method;)Ljava/lang/String;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acxl */
public final class acxl {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acxl$a */
    final class a extends acrz implements acqr<Class<?>, String> {
        public static final a a = new a();

        a() {
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return adab.f((Class) obj);
        }
    }

    public static final /* synthetic */ String a(Method method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append(acno.a((Object[]) method.getParameterTypes(), (CharSequence) "", (CharSequence) "(", (CharSequence) ")", 0, null, (acqr) a.a, 24));
        stringBuilder.append(adab.f(method.getReturnType()));
        return stringBuilder.toString();
    }
}
