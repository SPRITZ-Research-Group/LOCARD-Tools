package defpackage;

import com.google.obf.ly;
import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u00020\u0019*\u00060\u001aj\u0002`\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\u0018\u0010\u001e\u001a\u00020\u0019*\u00060\u001aj\u0002`\u001b2\u0006\u0010\u001f\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lkotlin/reflect/jvm/internal/ReflectionObjectRenderer;", "", "()V", "renderer", "Lkotlin/reflect/jvm/internal/impl/renderer/DescriptorRenderer;", "renderCallable", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "renderFunction", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "renderLambda", "invoke", "renderParameter", "parameter", "Lkotlin/reflect/jvm/internal/KParameterImpl;", "renderProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "renderType", "type", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "renderTypeParameter", "typeParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/TypeParameterDescriptor;", "appendReceiverType", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "receiver", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "appendReceivers", "callable", "kotlin-reflect-api"}, k = 1, mv = {1, 1, 13})
/* renamed from: acxi */
public final class acxi {
    public static final acxi a = new acxi();
    private static final DescriptorRenderer b = DescriptorRenderer.FQ_NAMES_IN_TYPES;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/reflect/jvm/internal/impl/descriptors/ValueParameterDescriptor;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acxi$a */
    final class a extends acrz implements acqr<ValueParameterDescriptor, String> {
        public static final a a = new a();

        a() {
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) obj;
            acxi acxi = acxi.a;
            return acxi.a(valueParameterDescriptor.getType());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/reflect/jvm/internal/impl/descriptors/ValueParameterDescriptor;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acxi$b */
    final class b extends acrz implements acqr<ValueParameterDescriptor, String> {
        public static final b a = new b();

        b() {
            super(1);
        }

        public final /* synthetic */ Object invoke(Object obj) {
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) obj;
            acxi acxi = acxi.a;
            return acxi.a(valueParameterDescriptor.getType());
        }
    }

    private acxi() {
    }

    private static void a(StringBuilder stringBuilder, ReceiverParameterDescriptor receiverParameterDescriptor) {
        if (receiverParameterDescriptor != null) {
            stringBuilder.append(b.renderType(receiverParameterDescriptor.getType()));
            stringBuilder.append(ly.a);
        }
    }

    private static void a(StringBuilder stringBuilder, CallableDescriptor callableDescriptor) {
        ReceiverParameterDescriptor a = acxm.a(callableDescriptor);
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        acxi.a(stringBuilder, a);
        Object obj = (a == null || extensionReceiverParameter == null) ? null : 1;
        if (obj != null) {
            stringBuilder.append("(");
        }
        acxi.a(stringBuilder, extensionReceiverParameter);
        if (obj != null) {
            stringBuilder.append(")");
        }
    }

    public static String a(PropertyDescriptor propertyDescriptor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(propertyDescriptor.isVar() ? "var " : "val ");
        acxi.a(stringBuilder, (CallableDescriptor) propertyDescriptor);
        stringBuilder.append(b.renderName(propertyDescriptor.getName(), true));
        stringBuilder.append(": ");
        stringBuilder.append(b.renderType(propertyDescriptor.getType()));
        return stringBuilder.toString();
    }

    public static String a(FunctionDescriptor functionDescriptor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("fun ");
        acxi.a(stringBuilder, (CallableDescriptor) functionDescriptor);
        stringBuilder.append(b.renderName(functionDescriptor.getName(), true));
        acnz.a(functionDescriptor.getValueParameters(), stringBuilder, ", ", "(", ")", 0, null, a.a, 48);
        stringBuilder.append(": ");
        KotlinType returnType = functionDescriptor.getReturnType();
        if (returnType == null) {
            acry.a();
        }
        stringBuilder.append(b.renderType(returnType));
        return stringBuilder.toString();
    }

    public static String b(FunctionDescriptor functionDescriptor) {
        StringBuilder stringBuilder = new StringBuilder();
        acxi.a(stringBuilder, (CallableDescriptor) functionDescriptor);
        acnz.a(functionDescriptor.getValueParameters(), stringBuilder, ", ", "(", ")", 0, null, b.a, 48);
        stringBuilder.append(" -> ");
        KotlinType returnType = functionDescriptor.getReturnType();
        if (returnType == null) {
            acry.a();
        }
        stringBuilder.append(b.renderType(returnType));
        return stringBuilder.toString();
    }

    public static String a(acwl acwl) {
        String a;
        StringBuilder stringBuilder = new StringBuilder();
        switch (acxj.a[acwl.c().ordinal()]) {
            case 1:
                stringBuilder.append("extension receiver");
                break;
            case 2:
                stringBuilder.append("instance");
                break;
            case 3:
                StringBuilder stringBuilder2 = new StringBuilder("parameter #");
                stringBuilder2.append(acwl.f());
                stringBuilder2.append(' ');
                stringBuilder2.append(acwl.a());
                stringBuilder.append(stringBuilder2.toString());
                break;
        }
        stringBuilder.append(" of ");
        CallableDescriptor c = acwl.e().c();
        if (c instanceof PropertyDescriptor) {
            a = acxi.a((PropertyDescriptor) c);
        } else if (c instanceof FunctionDescriptor) {
            a = acxi.a((FunctionDescriptor) c);
        } else {
            throw new IllegalStateException("Illegal callable: ".concat(String.valueOf(c)).toString());
        }
        stringBuilder.append(a);
        return stringBuilder.toString();
    }

    public static String a(TypeParameterDescriptor typeParameterDescriptor) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (acxj.b[typeParameterDescriptor.getVariance().ordinal()]) {
            case 2:
                stringBuilder.append("in ");
                break;
            case 3:
                stringBuilder.append("out ");
                break;
        }
        stringBuilder.append(typeParameterDescriptor.getName());
        return stringBuilder.toString();
    }

    public static String a(KotlinType kotlinType) {
        return b.renderType(kotlinType);
    }
}
