package defpackage;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.name.FqName;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"findAnnotation", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaAnnotation;", "", "", "fqName", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "([Ljava/lang/annotation/Annotation;Lorg/jetbrains/kotlin/name/FqName;)Lkotlin/reflect/jvm/internal/structure/ReflectJavaAnnotation;", "getAnnotations", "", "([Ljava/lang/annotation/Annotation;)Ljava/util/List;", "descriptors.runtime"}, k = 2, mv = {1, 1, 13})
/* renamed from: adai */
public final class adai {
    public static final List<adac> a(Annotation[] annotationArr) {
        Collection arrayList = new ArrayList(annotationArr.length);
        for (Annotation adac : annotationArr) {
            arrayList.add(new adac(adac));
        }
        return (List) arrayList;
    }

    public static final adac a(Annotation[] annotationArr, FqName fqName) {
        for (Annotation annotation : annotationArr) {
            if (acry.a(adab.e(acqo.a(acqo.a(annotation))).asSingleFqName(), (Object) fqName)) {
                break;
            }
        }
        Annotation annotation2 = null;
        return annotation2 != null ? new adac(annotation2) : null;
    }
}
