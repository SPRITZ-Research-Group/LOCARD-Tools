package defpackage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.aa;
import kotlin.k;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\b*\u00020\nH\u0002\"\"\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000b"}, d2 = {"boundReceiver", "", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "getBoundReceiver", "(Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;)Ljava/lang/Object;", "computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/calls/Caller;", "isGetter", "", "isJvmFieldPropertyInCompanionObject", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acwx */
public final class acwx {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isJvmStaticProperty", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acwx$a */
    final class a extends acrz implements acqq<Boolean> {
        final /* synthetic */ acwt a;

        a(acwt acwt) {
            this.a = acwt;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            return this.a.a().m().getAnnotations().hasAnnotation(acxm.a());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isNotNullProperty", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acwx$b */
    final class b extends acrz implements acqq<Boolean> {
        final /* synthetic */ acwt a;

        b(acwt acwt) {
            this.a = acwt;
            super(0);
        }

        public final /* synthetic */ Object invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            return !TypeUtils.isNullableType(this.a.a().m().getType());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"computeFieldCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "Ljava/lang/reflect/Field;", "field", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acwx$c */
    final class c extends acrz implements acqr<Field, acxw<? extends Field>> {
        final /* synthetic */ acwt a;
        final /* synthetic */ boolean b;
        final /* synthetic */ b c;
        final /* synthetic */ a d;

        c(acwt acwt, boolean z, b bVar, a aVar) {
            this.a = acwt;
            this.b = z;
            this.c = bVar;
            this.d = aVar;
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            return a((Field) obj);
        }

        public final acxw<Field> a(Field field) {
            acyc acyd;
            acyi acyj;
            if (acwx.a(this.a.a().m()) || !Modifier.isStatic(field.getModifiers())) {
                if (this.b) {
                    if (this.a.g()) {
                        acyd = new acyd(field, this.a.a().j());
                    } else {
                        acyd = new acyf(field);
                    }
                    return acyd;
                }
                if (this.a.g()) {
                    acyj = new acyj(field, this.c.a(), this.a.a().j());
                } else {
                    acyj = new acyl(field, this.c.a());
                }
                return acyj;
            } else if (this.d.a()) {
                if (this.b) {
                    if (this.a.g()) {
                        acyd = new acye(field);
                    } else {
                        acyd = new acyg(field);
                    }
                    return acyd;
                }
                if (this.a.g()) {
                    acyj = new acyk(field, this.c.a());
                } else {
                    acyj = new acym(field, this.c.a());
                }
                return acyj;
            } else if (this.b) {
                return new acyh(field);
            } else {
                return new acyn(field, this.c.a());
            }
        }
    }

    public static final /* synthetic */ acxu a(acwt acwt, boolean z) {
        acvz acvz = acvy.b;
        if (acvy.c.a(acwt.a().n())) {
            return aczc.a;
        }
        acxu a;
        a aVar = new a(acwt);
        c cVar = new c(acwt, z, new b(acwt), aVar);
        acxk acxk = acxk.a;
        acvo a2 = acxk.a(acwt.a().m());
        StringBuilder stringBuilder;
        Method a3;
        StringBuilder stringBuilder2;
        if (a2 instanceof acvr) {
            JvmMethodSignature getter;
            Field k;
            acyo acyp;
            acvr acvr = (acvr) a2;
            JvmPropertySignature d = acvr.d();
            Method method = null;
            if (z) {
                if (d.hasGetter()) {
                    getter = d.getGetter();
                    if (getter != null) {
                        method = acwt.a().f().b(acvr.e().getString(getter.getName()), acvr.e().getString(getter.getDesc()));
                    }
                    if (method != null) {
                        if (InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(acwt.a().m()) || !acry.a(acwt.a().m().getVisibility(), Visibilities.INTERNAL)) {
                            k = acwt.a().k();
                            if (k != null) {
                                a = cVar.a(k);
                            } else {
                                stringBuilder = new StringBuilder("No accessors or field is found for property ");
                                stringBuilder.append(acwt.a());
                                throw new acxb(stringBuilder.toString());
                            }
                        }
                        Class a4 = acyy.a(acwt.a().m().getContainingDeclaration());
                        if (a4 != null) {
                            a3 = acyy.a(a4, (CallableMemberDescriptor) acwt.a().m());
                            if (a3 != null) {
                                acyz acza;
                                if (acwt.g()) {
                                    acza = new acza(a3, acwt.a().j());
                                } else {
                                    acza = new aczb(a3);
                                }
                                a = acza;
                            }
                        }
                        stringBuilder = new StringBuilder("Underlying property of inline class ");
                        stringBuilder.append(acwt.a());
                        stringBuilder.append(" should have a field");
                        throw new acxb(stringBuilder.toString());
                    } else if (Modifier.isStatic(method.getModifiers())) {
                        if (acwt.g()) {
                            acyp = new acyp(method, acwt.a().j());
                        } else {
                            acyp = new acys(method);
                        }
                        a = acyp;
                    } else if (aVar.a()) {
                        if (acwt.g()) {
                            acyp = new acyq(method);
                        } else {
                            acyp = new acyt(method);
                        }
                        a = acyp;
                    } else {
                        if (acwt.g()) {
                            acyp = new acyr(method, acwt.a().j());
                        } else {
                            acyp = new acyu(method);
                        }
                        a = acyp;
                    }
                }
            } else if (d.hasSetter()) {
                getter = d.getSetter();
                if (getter != null) {
                    method = acwt.a().f().b(acvr.e().getString(getter.getName()), acvr.e().getString(getter.getDesc()));
                }
                if (method != null) {
                    if (InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(acwt.a().m())) {
                    }
                    k = acwt.a().k();
                    if (k != null) {
                        stringBuilder = new StringBuilder("No accessors or field is found for property ");
                        stringBuilder.append(acwt.a());
                        throw new acxb(stringBuilder.toString());
                    }
                    a = cVar.a(k);
                } else if (Modifier.isStatic(method.getModifiers())) {
                    if (acwt.g()) {
                        acyp = new acys(method);
                    } else {
                        acyp = new acyp(method, acwt.a().j());
                    }
                    a = acyp;
                } else if (aVar.a()) {
                    if (acwt.g()) {
                        acyp = new acyu(method);
                    } else {
                        acyp = new acyr(method, acwt.a().j());
                    }
                    a = acyp;
                } else {
                    if (acwt.g()) {
                        acyp = new acyt(method);
                    } else {
                        acyp = new acyq(method);
                    }
                    a = acyp;
                }
            }
            getter = null;
            if (getter != null) {
                method = acwt.a().f().b(acvr.e().getString(getter.getName()), acvr.e().getString(getter.getDesc()));
            }
            if (method != null) {
                if (InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(acwt.a().m())) {
                }
                k = acwt.a().k();
                if (k != null) {
                    a = cVar.a(k);
                } else {
                    stringBuilder = new StringBuilder("No accessors or field is found for property ");
                    stringBuilder.append(acwt.a());
                    throw new acxb(stringBuilder.toString());
                }
            } else if (Modifier.isStatic(method.getModifiers())) {
                if (acwt.g()) {
                    acyp = new acyp(method, acwt.a().j());
                } else {
                    acyp = new acys(method);
                }
                a = acyp;
            } else if (aVar.a()) {
                if (acwt.g()) {
                    acyp = new acyq(method);
                } else {
                    acyp = new acyt(method);
                }
                a = acyp;
            } else {
                if (acwt.g()) {
                    acyp = new acyr(method, acwt.a().j());
                } else {
                    acyp = new acyu(method);
                }
                a = acyp;
            }
        } else if (a2 instanceof acvp) {
            a = cVar.a(((acvp) a2).b());
        } else if (a2 instanceof acvq) {
            acyo acyp2;
            if (z) {
                a3 = ((acvq) a2).b();
            } else {
                acvq acvq = (acvq) a2;
                a3 = acvq.c();
                if (a3 == null) {
                    stringBuilder2 = new StringBuilder("No source found for setter of Java method property: ");
                    stringBuilder2.append(acvq.b());
                    throw new acxb(stringBuilder2.toString());
                }
            }
            if (acwt.g()) {
                acyp2 = new acyp(a3, acwt.a().j());
            } else {
                acyp2 = new acys(a3);
            }
            a = acyp2;
        } else if (a2 instanceof acvs) {
            acvn b;
            if (z) {
                b = ((acvs) a2).b();
            } else {
                b = ((acvs) a2).c();
                if (b == null) {
                    stringBuilder = new StringBuilder("No setter found for property ");
                    stringBuilder.append(acwt.a());
                    throw new acxb(stringBuilder.toString());
                }
            }
            a3 = acwt.a().f().b(b.b(), b.c());
            if (a3 != null) {
                int isStatic = Modifier.isStatic(a3.getModifiers()) ^ 1;
                if (aa.a && isStatic == 0) {
                    stringBuilder2 = new StringBuilder("Mapped property cannot have a static accessor: ");
                    stringBuilder2.append(acwt.a());
                    throw new AssertionError(stringBuilder2.toString());
                } else if (acwt.g()) {
                    return new acyp(a3, acwt.a().j());
                } else {
                    return new acys(a3);
                }
            }
            stringBuilder = new StringBuilder("No accessor found for property ");
            stringBuilder.append(acwt.a());
            throw new acxb(stringBuilder.toString());
        } else {
            throw new k();
        }
        return acyy.a(a, acwt.b(), false);
    }

    public static final /* synthetic */ boolean a(PropertyDescriptor propertyDescriptor) {
        DeclarationDescriptor containingDeclaration = propertyDescriptor.getContainingDeclaration();
        if (!DescriptorUtils.isCompanionObject(containingDeclaration)) {
            return false;
        }
        containingDeclaration = containingDeclaration.getContainingDeclaration();
        if ((DescriptorUtils.isInterface(containingDeclaration) || DescriptorUtils.isAnnotationClass(containingDeclaration)) && (!(propertyDescriptor instanceof DeserializedPropertyDescriptor) || !JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor) propertyDescriptor).getProto()))) {
            return false;
        }
        return true;
    }
}
