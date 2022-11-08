package defpackage;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.e;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001aI\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\u0010\u000b\u001a$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u0014²\u0006\u0014\u0010\u0015\u001a\u00020\u000f\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u0002²\u0006\u0014\u0010\u0016\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u0002"}, d2 = {"createAnnotationInstance", "T", "", "annotationClass", "Ljava/lang/Class;", "values", "", "", "methods", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/Map;Ljava/util/List;)Ljava/lang/Object;", "throwIllegalArgumentType", "", "index", "", "name", "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflect-api", "hashCode", "toString"}, k = 2, mv = {1, 1, 13})
/* renamed from: acxs */
public final class acxs {
    static final /* synthetic */ acuo[] a = new acuo[]{acso.a(new acsg(acso.a(acxs.class, "kotlin-reflect-api"), "hashCode", "<v#0>")), acso.a(new acsg(acso.a(acxs.class, "kotlin-reflect-api"), "toString", "<v#1>"))};

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062,\u0010\u0007\u001a(\u0012\f\u0012\n \u0004*\u0004\u0018\u00010\u00010\u0001 \u0004*\u0014\u0012\u000e\b\u0001\u0012\n \u0004*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\b0\bH\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"<anonymous>", "", "T", "<anonymous parameter 0>", "kotlin.jvm.PlatformType", "method", "Ljava/lang/reflect/Method;", "args", "", "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acxs$c */
    final class c implements InvocationHandler {
        final /* synthetic */ Class a;
        final /* synthetic */ e b;
        final /* synthetic */ acuo c;
        final /* synthetic */ e d;
        final /* synthetic */ acuo e;
        final /* synthetic */ a f;
        final /* synthetic */ Map g;

        c(Class cls, e eVar, acuo acuo, e eVar2, acuo acuo2, a aVar, Map map) {
            this.a = cls;
            this.b = eVar;
            this.c = acuo;
            this.d = eVar2;
            this.e = acuo2;
            this.f = aVar;
            this.g = map;
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) {
            obj = method.getName();
            if (obj != null) {
                int hashCode = obj.hashCode();
                if (hashCode != -1776922004) {
                    if (hashCode != 147696667) {
                        if (hashCode == 1444986633 && obj.equals("annotationType")) {
                            return this.a;
                        }
                    } else if (obj.equals("hashCode")) {
                        return this.d.d();
                    }
                } else if (obj.equals("toString")) {
                    return this.b.d();
                }
            }
            if (acry.a(obj, (Object) "equals") && objArr != null && objArr.length == 1) {
                return Boolean.valueOf(this.f.a(acno.d(objArr)));
            }
            if (this.g.containsKey(obj)) {
                return this.g.get(obj);
            }
            StringBuilder stringBuilder = new StringBuilder("Method is not supported: ");
            stringBuilder.append(method);
            stringBuilder.append(" (args: ");
            if (objArr == null) {
                objArr = new Object[0];
            }
            stringBuilder.append(acno.f(objArr));
            stringBuilder.append(')');
            throw new acxb(stringBuilder.toString());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"equals", "", "T", "", "other", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acxs$a */
    final class a extends acrz implements acqr<Object, Boolean> {
        final /* synthetic */ Class a;
        final /* synthetic */ List b;
        final /* synthetic */ Map c;

        a(Class cls, List list, Map map) {
            this.a = cls;
            this.b = list;
            this.c = map;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return Boolean.valueOf(a(obj));
        }

        public final boolean a(Object obj) {
            Object obj2 = null;
            Annotation annotation = (Annotation) (!(obj instanceof Annotation) ? null : obj);
            if (annotation != null) {
                acua a = acqo.a(annotation);
                if (a != null) {
                    obj2 = acqo.a(a);
                }
            }
            if (acry.a(obj2, this.a)) {
                Iterable<Method> iterable = this.b;
                if (!((iterable instanceof Collection) && ((Collection) iterable).isEmpty())) {
                    for (Method method : iterable) {
                        boolean equals;
                        Object obj3 = this.c.get(method.getName());
                        Object invoke = method.invoke(obj, new Object[0]);
                        if (obj3 instanceof boolean[]) {
                            boolean[] zArr = (boolean[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(zArr, (boolean[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.BooleanArray");
                            }
                        } else if (obj3 instanceof char[]) {
                            char[] cArr = (char[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(cArr, (char[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.CharArray");
                            }
                        } else if (obj3 instanceof byte[]) {
                            byte[] bArr = (byte[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(bArr, (byte[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.ByteArray");
                            }
                        } else if (obj3 instanceof short[]) {
                            short[] sArr = (short[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(sArr, (short[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.ShortArray");
                            }
                        } else if (obj3 instanceof int[]) {
                            int[] iArr = (int[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(iArr, (int[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.IntArray");
                            }
                        } else if (obj3 instanceof float[]) {
                            float[] fArr = (float[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(fArr, (float[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.FloatArray");
                            }
                        } else if (obj3 instanceof long[]) {
                            long[] jArr = (long[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(jArr, (long[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.LongArray");
                            }
                        } else if (obj3 instanceof double[]) {
                            double[] dArr = (double[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(dArr, (double[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.DoubleArray");
                            }
                        } else if (obj3 instanceof Object[]) {
                            Object[] objArr = (Object[]) obj3;
                            if (invoke != null) {
                                equals = Arrays.equals(objArr, (Object[]) invoke);
                                continue;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.Array<*>");
                            }
                        } else {
                            equals = acry.a(obj3, invoke);
                            continue;
                        }
                        if (!equals) {
                            obj = null;
                            break;
                        }
                    }
                }
                obj = 1;
                if (obj != null) {
                    return true;
                }
            }
            return false;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "T", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acxs$b */
    final class b extends acrz implements acqq<Integer> {
        final /* synthetic */ Map a;

        b(Map map) {
            this.a = map;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            int i = 0;
            for (Entry entry : this.a.entrySet()) {
                int hashCode;
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                if (value instanceof boolean[]) {
                    hashCode = Arrays.hashCode((boolean[]) value);
                } else if (value instanceof char[]) {
                    hashCode = Arrays.hashCode((char[]) value);
                } else if (value instanceof byte[]) {
                    hashCode = Arrays.hashCode((byte[]) value);
                } else if (value instanceof short[]) {
                    hashCode = Arrays.hashCode((short[]) value);
                } else if (value instanceof int[]) {
                    hashCode = Arrays.hashCode((int[]) value);
                } else if (value instanceof float[]) {
                    hashCode = Arrays.hashCode((float[]) value);
                } else if (value instanceof long[]) {
                    hashCode = Arrays.hashCode((long[]) value);
                } else if (value instanceof double[]) {
                    hashCode = Arrays.hashCode((double[]) value);
                } else if (value instanceof Object[]) {
                    hashCode = Arrays.hashCode((Object[]) value);
                } else {
                    hashCode = value.hashCode();
                }
                i += hashCode ^ (str.hashCode() * 127);
            }
            return Integer.valueOf(i);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "T", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acxs$d */
    final class d extends acrz implements acqq<String> {
        final /* synthetic */ Class a;
        final /* synthetic */ Map b;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010&\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "T", "", "entry", "", "invoke"}, k = 3, mv = {1, 1, 13})
        /* renamed from: acxs$d$a */
        final class a extends acrz implements acqr<Entry<? extends String, ? extends Object>, String> {
            public static final a a = new a();

            a() {
                super(1);
            }

            public final /* synthetic */ Object invoke(Object obj) {
                String arrays;
                Entry entry = (Entry) obj;
                String str = (String) entry.getKey();
                obj = entry.getValue();
                if (obj instanceof boolean[]) {
                    arrays = Arrays.toString((boolean[]) obj);
                } else if (obj instanceof char[]) {
                    arrays = Arrays.toString((char[]) obj);
                } else if (obj instanceof byte[]) {
                    arrays = Arrays.toString((byte[]) obj);
                } else if (obj instanceof short[]) {
                    arrays = Arrays.toString((short[]) obj);
                } else if (obj instanceof int[]) {
                    arrays = Arrays.toString((int[]) obj);
                } else if (obj instanceof float[]) {
                    arrays = Arrays.toString((float[]) obj);
                } else if (obj instanceof long[]) {
                    arrays = Arrays.toString((long[]) obj);
                } else if (obj instanceof double[]) {
                    arrays = Arrays.toString((double[]) obj);
                } else if (obj instanceof Object[]) {
                    arrays = Arrays.toString((Object[]) obj);
                } else {
                    arrays = obj.toString();
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append('=');
                stringBuilder.append(arrays);
                return stringBuilder.toString();
            }
        }

        d(Class cls, Map map) {
            this.a = cls;
            this.b = map;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('@');
            stringBuilder.append(this.a.getCanonicalName());
            acnz.a(this.b.entrySet(), stringBuilder, ", ", "(", ")", 0, null, a.a, 48);
            return stringBuilder.toString();
        }
    }

    public static /* synthetic */ Object a(Class cls, Map map) {
        Iterable<String> keySet = map.keySet();
        Collection arrayList = new ArrayList(acns.a((Iterable) keySet, 10));
        for (String declaredMethod : keySet) {
            arrayList.add(cls.getDeclaredMethod(declaredMethod, new Class[0]));
        }
        return acxs.a(cls, map, (List) arrayList);
    }

    public static final <T> T a(Class<T> cls, Map<String, ? extends Object> map, List<Method> list) {
        a aVar = new a(cls, list, map);
        e a = h.a(new b(map));
        acuo acuo = a[0];
        e a2 = h.a(new d(cls, map));
        acuo acuo2 = a[1];
        T newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new c(cls, a2, acuo2, a, acuo, aVar, map));
        if (newProxyInstance != null) {
            return newProxyInstance;
        }
        throw new v("null cannot be cast to non-null type T");
    }

    public static final /* synthetic */ Object a(Object obj, Class cls) {
        if (!(obj instanceof Class)) {
            if (obj instanceof acua) {
                obj = acqo.a((acua) obj);
            } else if (obj instanceof Object[]) {
                Object obj2 = (Object[]) obj;
                if (!(obj2 instanceof Class[])) {
                    if (!(obj2 instanceof acua[])) {
                        obj = obj2;
                    } else if (obj != null) {
                        acua[] acuaArr = (acua[]) obj;
                        Collection arrayList = new ArrayList(acuaArr.length);
                        for (acua a : acuaArr) {
                            arrayList.add(acqo.a(a));
                        }
                        obj = ((List) arrayList).toArray(new Class[0]);
                        if (obj == null) {
                            throw new v("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    } else {
                        throw new v("null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                    }
                }
            }
            if (cls.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }

    public static final /* synthetic */ Void a(int i, String str, Class cls) {
        String stringBuilder;
        acua a = acry.a((Object) cls, (Object) Class.class) ? acso.a(acua.class) : (cls.isArray() && acry.a(cls.getComponentType(), (Object) Class.class)) ? acso.a(acua[].class) : acso.a(cls);
        if (acry.a(a.c(), acso.a(Object[].class).c())) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(a.c());
            stringBuilder2.append('<');
            stringBuilder2.append(acso.a(acqo.a(a).getComponentType()).c());
            stringBuilder2.append('>');
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = a.c();
        }
        StringBuilder stringBuilder3 = new StringBuilder("Argument #");
        stringBuilder3.append(i);
        stringBuilder3.append(' ');
        stringBuilder3.append(str);
        stringBuilder3.append(" is not of the required type ");
        stringBuilder3.append(stringBuilder);
        throw new IllegalArgumentException(stringBuilder3.toString());
    }
}
