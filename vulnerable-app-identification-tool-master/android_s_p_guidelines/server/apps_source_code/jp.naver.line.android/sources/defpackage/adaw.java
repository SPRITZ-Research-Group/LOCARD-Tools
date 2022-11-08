package defpackage;

import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* renamed from: adaw */
public final class adaw {
    public static boolean a(adav adav) {
        return Modifier.isAbstract(adav.c());
    }

    public static boolean b(adav adav) {
        return Modifier.isStatic(adav.c());
    }

    public static boolean c(adav adav) {
        return Modifier.isFinal(adav.c());
    }

    public static Visibility d(adav adav) {
        int c = adav.c();
        if (Modifier.isPublic(c)) {
            return Visibilities.PUBLIC;
        }
        if (Modifier.isPrivate(c)) {
            return Visibilities.PRIVATE;
        }
        if (!Modifier.isProtected(c)) {
            return JavaVisibilities.PACKAGE_VISIBILITY;
        }
        if (Modifier.isStatic(c)) {
            return JavaVisibilities.PROTECTED_STATIC_VISIBILITY;
        }
        return JavaVisibilities.PROTECTED_AND_PACKAGE;
    }
}
