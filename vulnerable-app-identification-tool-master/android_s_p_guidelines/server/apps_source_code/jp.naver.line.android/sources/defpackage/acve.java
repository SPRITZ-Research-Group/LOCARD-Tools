package defpackage;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001c\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\"\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00058FX\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0003\u0010\b¨\u0006\t"}, d2 = {"jvmErasure", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/KClassifier;", "getJvmErasure", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KClass;", "Lkotlin/reflect/KType;", "jvmErasure$annotations", "(Lkotlin/reflect/KType;)V", "(Lkotlin/reflect/KType;)Lkotlin/reflect/KClass;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acve */
public final class acve {
    private static acua<?> a(acuw acuw) {
        acub a = acuw.a();
        if (a != null) {
            acua<?> a2 = acve.a(a);
            if (a2 != null) {
                return a2;
            }
        }
        throw new acxb("Cannot calculate JVM erasure for type: ".concat(String.valueOf(acuw)));
    }

    public static final acua<?> a(acub acub) {
        if (acub instanceof acua) {
            return (acua) acub;
        }
        if (acub instanceof acux) {
            Object obj;
            List a = ((acux) acub).a();
            Iterator it = a.iterator();
            Object obj2;
            do {
                ClassifierDescriptor classifierDescriptor = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                acuw acuw = (acuw) obj;
                if (acuw != null) {
                    ClassifierDescriptor declarationDescriptor = ((acwy) acuw).c().getConstructor().getDeclarationDescriptor();
                    if (declarationDescriptor instanceof ClassDescriptor) {
                        classifierDescriptor = declarationDescriptor;
                    }
                    ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptor;
                    if (classDescriptor == null || classDescriptor.getKind() == ClassKind.INTERFACE || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) {
                        obj2 = null;
                        continue;
                    } else {
                        obj2 = 1;
                        continue;
                    }
                } else {
                    throw new v("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                }
            } while (obj2 == null);
            acuw acuw2 = (acuw) obj;
            if (acuw2 == null) {
                acuw2 = (acuw) acnz.f(a);
            }
            if (acuw2 != null) {
                acua<?> a2 = acve.a(acuw2);
                if (a2 != null) {
                    return a2;
                }
            }
            return acso.a(Object.class);
        }
        throw new acxb("Cannot calculate JVM erasure for type: ".concat(String.valueOf(acub)));
    }
}
