package defpackage;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.b;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.u;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u001a\u0012\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0002*\u0006\u0012\u0002\b\u00030\u0002\u001a\u000e\u0010$\u001a\u00020%*\u0006\u0012\u0002\b\u00030\u0002\"&\u0010\u0000\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001X\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00070\u0006X\u0004¢\u0006\u0002\n\u0000\",\u0010\t\u001a \u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\u0002\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\",\u0010\n\u001a \u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0002\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0019\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u0019\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u0004*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0006*\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u001f\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0019\u0010\u001d\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001f\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001c¨\u0006&"}, d2 = {"FUNCTION_CLASSES", "", "Ljava/lang/Class;", "Lkotlin/Function;", "", "PRIMITIVE_CLASSES", "", "Lkotlin/reflect/KClass;", "", "PRIMITIVE_TO_WRAPPER", "WRAPPER_TO_PRIMITIVE", "classId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "getClassId", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/name/ClassId;", "desc", "", "getDesc", "(Ljava/lang/Class;)Ljava/lang/String;", "functionClassArity", "getFunctionClassArity", "(Ljava/lang/Class;)Ljava/lang/Integer;", "parameterizedTypeArguments", "Ljava/lang/reflect/Type;", "getParameterizedTypeArguments", "(Ljava/lang/reflect/Type;)Ljava/util/List;", "primitiveByWrapper", "getPrimitiveByWrapper", "(Ljava/lang/Class;)Ljava/lang/Class;", "safeClassLoader", "Ljava/lang/ClassLoader;", "getSafeClassLoader", "(Ljava/lang/Class;)Ljava/lang/ClassLoader;", "wrapperByPrimitive", "getWrapperByPrimitive", "createArrayType", "isEnumClassOrSpecializedEnumEntryClass", "", "descriptors.runtime"}, k = 2, mv = {1, 1, 13})
/* renamed from: adab */
public final class adab {
    private static final List<acua<? extends Object>> a;
    private static final Map<Class<? extends Object>, Class<? extends Object>> b;
    private static final Map<Class<? extends Object>, Class<? extends Object>> c;
    private static final Map<Class<? extends b<?>>, Integer> d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/lang/reflect/ParameterizedType;", "it", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: adab$a */
    final class a extends acrz implements acqr<ParameterizedType, ParameterizedType> {
        public static final a a = new a();

        a() {
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            Type ownerType = ((ParameterizedType) obj).getOwnerType();
            if (!(ownerType instanceof ParameterizedType)) {
                ownerType = null;
            }
            return (ParameterizedType) ownerType;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lkotlin/sequences/Sequence;", "Ljava/lang/reflect/Type;", "kotlin.jvm.PlatformType", "it", "Ljava/lang/reflect/ParameterizedType;", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: adab$b */
    final class b extends acrz implements acqr<ParameterizedType, adbo<? extends Type>> {
        public static final b a = new b();

        b() {
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return acno.k(((ParameterizedType) obj).getActualTypeArguments());
        }
    }

    public static final ClassLoader a(Class<?> cls) {
        ClassLoader classLoader = cls.getClassLoader();
        return classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
    }

    public static final boolean b(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }

    static {
        r1 = new acua[8];
        int i = 0;
        r1[0] = acso.a(Boolean.TYPE);
        r1[1] = acso.a(Byte.TYPE);
        r1[2] = acso.a(Character.TYPE);
        r1[3] = acso.a(Double.TYPE);
        r1[4] = acso.a(Float.TYPE);
        r1[5] = acso.a(Integer.TYPE);
        r1[6] = acso.a(Long.TYPE);
        r1[7] = acso.a(Short.TYPE);
        List b = acnr.b((Object[]) r1);
        a = b;
        Iterable<acua> iterable = b;
        Collection arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (acua acua : iterable) {
            arrayList.add(u.a(acqo.c(acua), acqo.b(acua)));
        }
        b = acom.a((Iterable) (List) arrayList);
        iterable = a;
        arrayList = new ArrayList(acns.a((Iterable) iterable, 10));
        for (acua acua2 : iterable) {
            arrayList.add(u.a(acqo.b(acua2), acqo.c(acua2)));
        }
        c = acom.a((Iterable) (List) arrayList);
        Iterable b2 = acnr.b((Object[]) new Class[]{acqq.class, acqr.class, acrc.class, acrg.class, acrh.class, acri.class, acrj.class, acrk.class, acrl.class, acrm.class, acqs.class, acqt.class, acqu.class, acqv.class, acqw.class, acqx.class, acqy.class, acqz.class, acra.class, acrb.class, acrd.class, acre.class, acrf.class});
        Collection arrayList2 = new ArrayList(acns.a(b2, 10));
        for (Object next : b2) {
            int i2 = i + 1;
            if (i < 0) {
                acnr.a();
            }
            arrayList2.add(u.a((Class) next, Integer.valueOf(i)));
            i = i2;
        }
        d = acom.a((Iterable) (List) arrayList2);
    }

    public static final Class<?> c(Class<?> cls) {
        return (Class) b.get(cls);
    }

    public static final Class<?> d(Class<?> cls) {
        return (Class) c.get(cls);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final ClassId e(Class<?> cls) {
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("Can't compute ClassId for primitive type: ".concat(String.valueOf(cls)));
        } else if (cls.isArray()) {
            throw new IllegalArgumentException("Can't compute ClassId for array type: ".concat(String.valueOf(cls)));
        } else {
            if (cls.getEnclosingMethod() == null && cls.getEnclosingConstructor() == null) {
                if ((((CharSequence) cls.getSimpleName()).length() == 0 ? 1 : null) == null) {
                    ClassId e;
                    Class declaringClass = cls.getDeclaringClass();
                    if (declaringClass != null) {
                        e = adab.e(declaringClass);
                        if (e != null) {
                            e = e.createNestedClassId(Name.identifier(cls.getSimpleName()));
                        }
                    }
                    e = ClassId.topLevel(new FqName(cls.getName()));
                    return e;
                }
            }
            FqName fqName = new FqName(cls.getName());
            return new ClassId(fqName.parent(), FqName.topLevel(fqName.shortName()), true);
        }
    }

    public static final String f(Class<?> cls) {
        if (acry.a((Object) cls, Void.TYPE)) {
            return "V";
        }
        String name = adab.g(cls).getName();
        if (name != null) {
            return name.substring(1).replace('.', '/');
        }
        throw new v("null cannot be cast to non-null type java.lang.String");
    }

    public static final Class<?> g(Class<?> cls) {
        return Array.newInstance(cls, 0).getClass();
    }

    public static final List<Type> a(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return acob.a;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (parameterizedType.getOwnerType() == null) {
            return acno.f(parameterizedType.getActualTypeArguments());
        }
        return adbw.e(adbw.c(adbu.a((Object) type, (acqr) a.a), (acqr) b.a));
    }
}
