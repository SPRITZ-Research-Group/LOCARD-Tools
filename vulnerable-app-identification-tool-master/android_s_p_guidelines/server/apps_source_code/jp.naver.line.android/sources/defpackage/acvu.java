package defpackage;

import java.lang.ref.WeakReference;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0005\u001a\u00020\u0006H\u0000\u001a&\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b\u0000\u0010\t*\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0000\"*\u0010\u0000\u001a\u001e\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u00040\u0001X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"K_CLASS_CACHE", "Lkotlin/reflect/jvm/internal/pcollections/HashPMap;", "", "kotlin.jvm.PlatformType", "", "clearKClassCache", "", "getOrCreateKotlinClass", "Lkotlin/reflect/jvm/internal/KClassImpl;", "T", "jClass", "Ljava/lang/Class;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acvu */
public final class acvu {
    private static aczv<String, Object> a = aczv.a();

    public static final <T> acvv<T> a(Class<T> cls) {
        acvv<T> acvv;
        Object name = cls.getName();
        Object a = a.a(name);
        Object obj = null;
        if (a instanceof WeakReference) {
            acvv = (acvv) ((WeakReference) a).get();
            if (acvv != null) {
                obj = acvv.a();
            }
            if (acry.a(obj, (Object) cls)) {
                return acvv;
            }
        } else if (a != null) {
            for (WeakReference weakReference : (WeakReference[]) a) {
                acvv<T> acvv2 = (acvv) weakReference.get();
                if (acry.a(acvv2 != null ? acvv2.a() : null, (Object) cls)) {
                    return acvv2;
                }
            }
            int length = ((Object[]) a).length;
            obj = new WeakReference[(length + 1)];
            System.arraycopy(a, 0, obj, 0, length);
            acvv = new acvv(cls);
            obj[length] = new WeakReference(acvv);
            a = a.a(name, obj);
            return acvv;
        }
        acvv = new acvv(cls);
        a = a.a(name, new WeakReference(acvv));
        return acvv;
    }
}
