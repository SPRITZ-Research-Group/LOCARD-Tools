package defpackage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MethodAnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.v;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\t\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\f\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\r\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0001H\u0002J$\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\u001a\u0010\u0018\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\u0019\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030\u0006H\u0002¨\u0006\u001b"}, d2 = {"Lkotlin/reflect/jvm/internal/components/ReflectClassStructure;", "", "()V", "loadClassAnnotations", "", "klass", "Ljava/lang/Class;", "visitor", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinJvmBinaryClass$AnnotationVisitor;", "loadConstructorAnnotations", "memberVisitor", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinJvmBinaryClass$MemberVisitor;", "loadFieldAnnotations", "loadMethodAnnotations", "processAnnotation", "annotation", "", "processAnnotationArgumentValue", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "value", "processAnnotationArguments", "annotationType", "visitMembers", "classLiteralValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ClassLiteralValue;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* renamed from: acze */
final class acze {
    public static final acze a = new acze();

    private acze() {
    }

    public static void a(Class<?> cls, AnnotationVisitor annotationVisitor) {
        for (Annotation a : cls.getDeclaredAnnotations()) {
            acze.a(annotationVisitor, a);
        }
        annotationVisitor.visitEnd();
    }

    private static void a(AnnotationVisitor annotationVisitor, Annotation annotation) {
        Class a = acqo.a(acqo.a(annotation));
        AnnotationArgumentVisitor visitAnnotation = annotationVisitor.visitAnnotation(adab.e(a), new aczd(annotation));
        if (visitAnnotation != null) {
            a.a(visitAnnotation, annotation, a);
        }
    }

    private final void a(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor r6, java.lang.annotation.Annotation r7, java.lang.Class<?> r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:acze.a(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor, java.lang.annotation.Annotation, java.lang.Class):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r8 = r8.getDeclaredMethods();
        r0 = r8.length;
        r1 = 0;
        r2 = 0;
    L_0x0007:
        if (r2 >= r0) goto L_0x0024;
    L_0x0009:
        r3 = r8[r2];
        r4 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x0021 }
        r4 = r3.invoke(r7, r4);	 Catch:{ IllegalAccessException -> 0x0021 }
        if (r4 != 0) goto L_0x0016;	 Catch:{ IllegalAccessException -> 0x0021 }
    L_0x0013:
        defpackage.acry.a();	 Catch:{ IllegalAccessException -> 0x0021 }
    L_0x0016:
        r3 = r3.getName();
        r3 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r3);
        r5.a(r6, r3, r4);
    L_0x0021:
        r2 = r2 + 1;
        goto L_0x0007;
    L_0x0024:
        r6.visitEnd();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: acze.a(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor, java.lang.annotation.Annotation, java.lang.Class):void");
    }

    private static ClassLiteralValue a(Class<?> cls) {
        Object cls2;
        int i = 0;
        while (cls2.isArray()) {
            i++;
            cls2 = cls2.getComponentType();
        }
        if (!cls2.isPrimitive()) {
            ClassId e = adab.e(cls2);
            ClassId mapJavaToKotlin = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(e.asSingleFqName());
            if (mapJavaToKotlin != null) {
                e = mapJavaToKotlin;
            }
            return new ClassLiteralValue(e, i);
        } else if (acry.a(cls2, Void.TYPE)) {
            return new ClassLiteralValue(ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.unit.toSafe()), i);
        } else {
            PrimitiveType primitiveType = JvmPrimitiveType.get(cls2.getName()).getPrimitiveType();
            if (i > 0) {
                return new ClassLiteralValue(ClassId.topLevel(primitiveType.getArrayTypeFqName()), i - 1);
            }
            return new ClassLiteralValue(ClassId.topLevel(primitiveType.getTypeFqName()), i);
        }
    }

    private final void a(AnnotationArgumentVisitor annotationArgumentVisitor, Name name, Object obj) {
        Class cls = obj.getClass();
        if (acry.a((Object) cls, (Object) Class.class)) {
            if (obj != null) {
                annotationArgumentVisitor.visitClassLiteral(name, acze.a((Class) obj));
                return;
            }
            throw new v("null cannot be cast to non-null type java.lang.Class<*>");
        } else if (aczk.a.contains(cls)) {
            annotationArgumentVisitor.visit(name, obj);
        } else if (adab.b(cls)) {
            if (!cls.isEnum()) {
                cls = cls.getEnclosingClass();
            }
            ClassId e = adab.e(cls);
            if (obj != null) {
                annotationArgumentVisitor.visitEnum(name, e, Name.identifier(((Enum) obj).name()));
                return;
            }
            throw new v("null cannot be cast to non-null type kotlin.Enum<*>");
        } else if (Annotation.class.isAssignableFrom(cls)) {
            cls = (Class) acno.d(cls.getInterfaces());
            annotationArgumentVisitor = annotationArgumentVisitor.visitAnnotation(name, adab.e(cls));
            if (annotationArgumentVisitor != null) {
                if (obj != null) {
                    a(annotationArgumentVisitor, (Annotation) obj, cls);
                    return;
                }
                throw new v("null cannot be cast to non-null type kotlin.Annotation");
            }
        } else if (cls.isArray()) {
            AnnotationArrayArgumentVisitor visitArray = annotationArgumentVisitor.visitArray(name);
            if (visitArray != null) {
                Object componentType = cls.getComponentType();
                int i = 0;
                Object[] objArr;
                int length;
                if (componentType.isEnum()) {
                    ClassId e2 = adab.e(componentType);
                    if (obj != null) {
                        objArr = (Object[]) obj;
                        int length2 = objArr.length;
                        while (i < length2) {
                            Object obj2 = objArr[i];
                            if (obj2 != null) {
                                visitArray.visitEnum(e2, Name.identifier(((Enum) obj2).name()));
                                i++;
                            } else {
                                throw new v("null cannot be cast to non-null type kotlin.Enum<*>");
                            }
                        }
                    }
                    throw new v("null cannot be cast to non-null type kotlin.Array<*>");
                } else if (acry.a(componentType, (Object) Class.class)) {
                    if (obj != null) {
                        objArr = (Object[]) obj;
                        length = objArr.length;
                        while (i < length) {
                            Object obj3 = objArr[i];
                            if (obj3 != null) {
                                visitArray.visitClassLiteral(acze.a((Class) obj3));
                                i++;
                            } else {
                                throw new v("null cannot be cast to non-null type java.lang.Class<*>");
                            }
                        }
                    }
                    throw new v("null cannot be cast to non-null type kotlin.Array<*>");
                } else if (obj != null) {
                    objArr = (Object[]) obj;
                    length = objArr.length;
                    while (i < length) {
                        visitArray.visit(objArr[i]);
                        i++;
                    }
                } else {
                    throw new v("null cannot be cast to non-null type kotlin.Array<*>");
                }
                visitArray.visitEnd();
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder("Unsupported annotation argument value (");
            stringBuilder.append(cls);
            stringBuilder.append("): ");
            stringBuilder.append(obj);
            throw new UnsupportedOperationException(stringBuilder.toString());
        }
    }

    public static void a(Class<?> cls, MemberVisitor memberVisitor) {
        Name identifier;
        aczs aczs;
        MethodAnnotationVisitor visitMethod;
        int i;
        int i2;
        int length;
        int length2;
        MemberVisitor memberVisitor2 = memberVisitor;
        for (Method method : cls.getDeclaredMethods()) {
            identifier = Name.identifier(method.getName());
            aczs = aczs.a;
            visitMethod = memberVisitor2.visitMethod(identifier, aczs.a(method));
            if (visitMethod != null) {
                for (Annotation a : method.getDeclaredAnnotations()) {
                    acze.a((AnnotationVisitor) visitMethod, a);
                }
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                length = parameterAnnotations.length;
                for (i2 = 0; i2 < length; i2++) {
                    for (Annotation annotation : parameterAnnotations[i2]) {
                        Class a2 = acqo.a(acqo.a(annotation));
                        AnnotationArgumentVisitor visitParameterAnnotation = visitMethod.visitParameterAnnotation(i2, adab.e(a2), new aczd(annotation));
                        if (visitParameterAnnotation != null) {
                            a.a(visitParameterAnnotation, annotation, a2);
                        }
                    }
                }
                visitMethod.visitEnd();
            }
        }
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        int length3 = declaredConstructors.length;
        int i3 = 0;
        while (i3 < length3) {
            Constructor[] constructorArr;
            Constructor constructor = declaredConstructors[i3];
            identifier = Name.special("<init>");
            aczs = aczs.a;
            visitMethod = memberVisitor2.visitMethod(identifier, aczs.a(constructor));
            if (visitMethod == null) {
                constructorArr = declaredConstructors;
            } else {
                for (Annotation a3 : constructor.getDeclaredAnnotations()) {
                    acze.a((AnnotationVisitor) visitMethod, a3);
                }
                Annotation[][] parameterAnnotations2 = constructor.getParameterAnnotations();
                Object[] objArr = (Object[]) parameterAnnotations2;
                if (((objArr.length == 0 ? 1 : 0) ^ 1) != 0) {
                    int length4 = constructor.getParameterTypes().length - objArr.length;
                    i2 = parameterAnnotations2.length;
                    for (i = 0; i < i2; i++) {
                        Annotation[] annotationArr = parameterAnnotations2[i];
                        length2 = annotationArr.length;
                        int i4 = 0;
                        while (i4 < length2) {
                            Annotation annotation2 = annotationArr[i4];
                            Class a4 = acqo.a(acqo.a(annotation2));
                            constructorArr = declaredConstructors;
                            AnnotationArgumentVisitor visitParameterAnnotation2 = visitMethod.visitParameterAnnotation(i + length4, adab.e(a4), new aczd(annotation2));
                            if (visitParameterAnnotation2 != null) {
                                a.a(visitParameterAnnotation2, annotation2, a4);
                            }
                            i4++;
                            declaredConstructors = constructorArr;
                        }
                        constructorArr = declaredConstructors;
                    }
                }
                constructorArr = declaredConstructors;
                visitMethod.visitEnd();
            }
            i3++;
            declaredConstructors = constructorArr;
        }
        for (Field field : cls.getDeclaredFields()) {
            Name identifier2 = Name.identifier(field.getName());
            aczs aczs2 = aczs.a;
            AnnotationVisitor visitField = memberVisitor2.visitField(identifier2, aczs.a(field), null);
            if (visitField != null) {
                for (Annotation a5 : field.getDeclaredAnnotations()) {
                    acze.a(visitField, a5);
                }
                visitField.visitEnd();
            }
        }
    }
}
