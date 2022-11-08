package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.v;

public enum AnnotationUseSiteTarget {
    ;
    
    private final String renderName;

    private AnnotationUseSiteTarget(String str) {
        if (str == null) {
            r1 = name();
            if (r1 != null) {
                str = r1.toLowerCase();
            } else {
                throw new v("null cannot be cast to non-null type java.lang.String");
            }
        }
        this.renderName = str;
    }

    public final String getRenderName() {
        return this.renderName;
    }
}
