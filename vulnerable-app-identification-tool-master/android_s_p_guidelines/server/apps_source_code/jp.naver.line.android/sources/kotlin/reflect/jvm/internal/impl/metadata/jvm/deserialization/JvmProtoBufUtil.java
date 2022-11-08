package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import defpackage.acnr;
import defpackage.acns;
import defpackage.acnz;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.m;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmFieldSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;

public final class JvmProtoBufUtil {
    private static final ExtensionRegistryLite EXTENSION_REGISTRY;
    public static final JvmProtoBufUtil INSTANCE = new JvmProtoBufUtil();

    static {
        ExtensionRegistryLite newInstance = ExtensionRegistryLite.newInstance();
        JvmProtoBuf.registerAllExtensions(newInstance);
        EXTENSION_REGISTRY = newInstance;
    }

    private JvmProtoBufUtil() {
    }

    public final ExtensionRegistryLite getEXTENSION_REGISTRY() {
        return EXTENSION_REGISTRY;
    }

    public static final m<JvmNameResolver, Class> readClassDataFrom(String[] strArr, String[] strArr2) {
        return readClassDataFrom(BitEncoding.decodeBytes(strArr), strArr2);
    }

    public static final m<JvmNameResolver, Class> readClassDataFrom(byte[] bArr, String[] strArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        return new m(INSTANCE.readNameResolver(byteArrayInputStream, strArr), Class.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    public static final m<JvmNameResolver, Package> readPackageDataFrom(String[] strArr, String[] strArr2) {
        return readPackageDataFrom(BitEncoding.decodeBytes(strArr), strArr2);
    }

    public static final m<JvmNameResolver, Package> readPackageDataFrom(byte[] bArr, String[] strArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        return new m(INSTANCE.readNameResolver(byteArrayInputStream, strArr), Package.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    public static final m<JvmNameResolver, Function> readFunctionDataFrom(String[] strArr, String[] strArr2) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(BitEncoding.decodeBytes(strArr));
        return new m(INSTANCE.readNameResolver(byteArrayInputStream, strArr2), Function.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    private final JvmNameResolver readNameResolver(InputStream inputStream, String[] strArr) {
        return new JvmNameResolver(StringTableTypes.parseDelimitedFrom(inputStream, EXTENSION_REGISTRY), strArr);
    }

    public final Method getJvmMethodSignature(Function function, NameResolver nameResolver, TypeTable typeTable) {
        String mapTypeDefault;
        JvmMethodSignature jvmMethodSignature = (JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(function, JvmProtoBuf.methodSignature);
        int name = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? function.getName() : jvmMethodSignature.getName();
        if (jvmMethodSignature == null || !jvmMethodSignature.hasDesc()) {
            Collection a = acnr.a((Object) ProtoTypeTableUtilKt.receiverType(function, typeTable));
            Iterable<ValueParameter> valueParameterList = function.getValueParameterList();
            Collection arrayList = new ArrayList(acns.a((Iterable) valueParameterList, 10));
            for (ValueParameter type : valueParameterList) {
                arrayList.add(ProtoTypeTableUtilKt.type(type, typeTable));
            }
            Iterable<Type> b = acnz.b(a, (Iterable) (List) arrayList);
            Collection arrayList2 = new ArrayList(acns.a((Iterable) b, 10));
            for (Type mapTypeDefault2 : b) {
                String mapTypeDefault3 = INSTANCE.mapTypeDefault(mapTypeDefault2, nameResolver);
                if (mapTypeDefault3 == null) {
                    return null;
                }
                arrayList2.add(mapTypeDefault3);
            }
            List list = (List) arrayList2;
            mapTypeDefault = mapTypeDefault(ProtoTypeTableUtilKt.returnType(function, typeTable), nameResolver);
            if (mapTypeDefault == null) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(acnz.a((Iterable) list, (CharSequence) "", (CharSequence) "(", (CharSequence) ")", 0, null, null, 56));
            stringBuilder.append(mapTypeDefault);
            mapTypeDefault = stringBuilder.toString();
        } else {
            mapTypeDefault = nameResolver.getString(jvmMethodSignature.getDesc());
        }
        return new Method(nameResolver.getString(name), mapTypeDefault);
    }

    public final Method getJvmConstructorSignature(Constructor constructor, NameResolver nameResolver, TypeTable typeTable) {
        String a;
        JvmMethodSignature jvmMethodSignature = (JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(constructor, JvmProtoBuf.constructorSignature);
        String string = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? "<init>" : nameResolver.getString(jvmMethodSignature.getName());
        if (jvmMethodSignature == null || !jvmMethodSignature.hasDesc()) {
            Iterable<ValueParameter> valueParameterList = constructor.getValueParameterList();
            Collection arrayList = new ArrayList(acns.a((Iterable) valueParameterList, 10));
            for (ValueParameter type : valueParameterList) {
                String mapTypeDefault = INSTANCE.mapTypeDefault(ProtoTypeTableUtilKt.type(type, typeTable), nameResolver);
                if (mapTypeDefault == null) {
                    return null;
                }
                arrayList.add(mapTypeDefault);
            }
            a = acnz.a((Iterable) (List) arrayList, (CharSequence) "", (CharSequence) "(", (CharSequence) ")V", 0, null, null, 56);
        } else {
            a = nameResolver.getString(jvmMethodSignature.getDesc());
        }
        return new Method(string, a);
    }

    public static /* synthetic */ Field getJvmFieldSignature$default(JvmProtoBufUtil jvmProtoBufUtil, Property property, NameResolver nameResolver, TypeTable typeTable, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return jvmProtoBufUtil.getJvmFieldSignature(property, nameResolver, typeTable, z);
    }

    public final Field getJvmFieldSignature(Property property, NameResolver nameResolver, TypeTable typeTable, boolean z) {
        JvmPropertySignature jvmPropertySignature = (JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(property, JvmProtoBuf.propertySignature);
        if (jvmPropertySignature == null) {
            return null;
        }
        JvmFieldSignature field = jvmPropertySignature.hasField() ? jvmPropertySignature.getField() : null;
        if (field == null && z) {
            return null;
        }
        String mapTypeDefault;
        int name = (field == null || !field.hasName()) ? property.getName() : field.getName();
        if (field == null || !field.hasDesc()) {
            mapTypeDefault = mapTypeDefault(ProtoTypeTableUtilKt.returnType(property, typeTable), nameResolver);
            if (mapTypeDefault == null) {
                return null;
            }
        }
        mapTypeDefault = nameResolver.getString(field.getDesc());
        return new Field(nameResolver.getString(name), mapTypeDefault);
    }

    private final String mapTypeDefault(Type type, NameResolver nameResolver) {
        return type.hasClassName() ? ClassMapperLite.mapClass(nameResolver.getQualifiedClassName(type.getClassName())) : null;
    }

    public static final boolean isMovedFromInterfaceCompanion(Property property) {
        return JvmFlags.INSTANCE.getIS_MOVED_FROM_INTERFACE_COMPANION().get(((Number) property.getExtension(JvmProtoBuf.flags)).intValue()).booleanValue();
    }
}
