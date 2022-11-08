package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import defpackage.acno;
import defpackage.acnz;
import defpackage.acom;
import defpackage.acru;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.u;

public enum KotlinTarget {
    ;
    
    private static final Set<KotlinTarget> ALL_TARGET_SET = null;
    public static final Companion Companion = null;
    private static final Set<KotlinTarget> DEFAULT_TARGET_SET = null;
    private static final Map<AnnotationUseSiteTarget, KotlinTarget> USE_SITE_MAPPING = null;
    private static final HashMap<String, KotlinTarget> map = null;
    private final String description;
    private final boolean isDefault;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    private KotlinTarget(String str, boolean z) {
        this.description = str;
        this.isDefault = z;
    }

    static {
        Companion = new Companion();
        map = new HashMap();
        for (KotlinTarget kotlinTarget : values()) {
            map.put(kotlinTarget.name(), kotlinTarget);
        }
        KotlinTarget[] values = values();
        Collection arrayList = new ArrayList();
        for (KotlinTarget kotlinTarget2 : values) {
            if (kotlinTarget2.isDefault) {
                arrayList.add(kotlinTarget2);
            }
        }
        DEFAULT_TARGET_SET = acnz.m((List) arrayList);
        ALL_TARGET_SET = acno.h(values());
        USE_SITE_MAPPING = acom.a(u.a(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER, VALUE_PARAMETER), u.a(AnnotationUseSiteTarget.FIELD, FIELD), u.a(AnnotationUseSiteTarget.PROPERTY, PROPERTY), u.a(AnnotationUseSiteTarget.FILE, FILE), u.a(AnnotationUseSiteTarget.PROPERTY_GETTER, PROPERTY_GETTER), u.a(AnnotationUseSiteTarget.PROPERTY_SETTER, PROPERTY_SETTER), u.a(AnnotationUseSiteTarget.RECEIVER, VALUE_PARAMETER), u.a(AnnotationUseSiteTarget.SETTER_PARAMETER, VALUE_PARAMETER), u.a(AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD, FIELD));
    }
}
