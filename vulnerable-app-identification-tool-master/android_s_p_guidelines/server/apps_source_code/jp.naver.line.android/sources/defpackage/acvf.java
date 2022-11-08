package defpackage;

import kotlin.Metadata;
import kotlin.b;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, d2 = {"reflect", "Lkotlin/reflect/KFunction;", "R", "Lkotlin/Function;", "kotlin-reflect-api"}, k = 2, mv = {1, 1, 13})
/* renamed from: acvf */
public final class acvf {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0015\u0010\u0005\u001a\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/SimpleFunctionDescriptor;", "R", "p1", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "p2", "Lkotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Function;", "Lkotlin/ParameterName;", "name", "proto", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: acvf$a */
    final class a extends acrx implements acrc<MemberDeserializer, Function, SimpleFunctionDescriptor> {
        public static final a a = new a();

        a() {
            super(2);
        }

        public final String getName() {
            return "loadFunction";
        }

        public final acuc getOwner() {
            return acso.a(MemberDeserializer.class);
        }

        public final String getSignature() {
            return "loadFunction(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;)Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;";
        }

        public final /* synthetic */ Object invoke(Object obj, Object obj2) {
            return ((MemberDeserializer) obj).loadFunction((Function) obj2);
        }
    }

    public static final <R> acud<R> a(b<? extends R> bVar) {
        Metadata metadata = (Metadata) bVar.getClass().getAnnotation(Metadata.class);
        if (metadata == null) {
            return null;
        }
        String[] d1 = metadata.d1();
        boolean z = false;
        if ((d1.length == 0 ? 1 : null) != null) {
            d1 = null;
        }
        if (d1 == null) {
            return null;
        }
        m readFunctionDataFrom = JvmProtoBufUtil.readFunctionDataFrom(d1, metadata.d2());
        JvmNameResolver jvmNameResolver = (JvmNameResolver) readFunctionDataFrom.c();
        Function function = (Function) readFunctionDataFrom.d();
        int[] mv = metadata.mv();
        if ((metadata.xi() & 8) != 0) {
            z = true;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) acxm.a(bVar.getClass(), function, jvmNameResolver, new TypeTable(function.getTypeTable()), new JvmMetadataVersion(mv, z), a.a);
        if (simpleFunctionDescriptor == null) {
            return null;
        }
        return new acwc(acvg.a, simpleFunctionDescriptor);
    }
}
