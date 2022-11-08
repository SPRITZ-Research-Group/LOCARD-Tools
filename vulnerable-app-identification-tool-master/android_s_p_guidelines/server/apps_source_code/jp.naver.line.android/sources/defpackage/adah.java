package defpackage;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.name.FqName;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* renamed from: adah */
public final class adah {
    public static List<adac> a(adag adag) {
        AnnotatedElement a = adag.a();
        if (a != null) {
            Annotation[] declaredAnnotations = a.getDeclaredAnnotations();
            if (declaredAnnotations != null) {
                return adai.a(declaredAnnotations);
            }
        }
        return acob.a;
    }

    public static adac a(adag adag, FqName fqName) {
        AnnotatedElement a = adag.a();
        if (a != null) {
            Annotation[] declaredAnnotations = a.getDeclaredAnnotations();
            if (declaredAnnotations != null) {
                return adai.a(declaredAnnotations, fqName);
            }
        }
        return null;
    }
}
