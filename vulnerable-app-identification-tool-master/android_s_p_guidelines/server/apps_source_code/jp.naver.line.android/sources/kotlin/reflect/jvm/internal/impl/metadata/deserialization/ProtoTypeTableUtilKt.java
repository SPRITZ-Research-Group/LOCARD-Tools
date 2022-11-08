package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import defpackage.acns;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;

public final class ProtoTypeTableUtilKt {
    public static final List<Type> supertypes(Class classR, TypeTable typeTable) {
        List<Type> supertypeList = classR.getSupertypeList();
        if ((supertypeList.isEmpty() ^ 1) == 0) {
            supertypeList = null;
        }
        if (supertypeList != null) {
            return supertypeList;
        }
        Iterable<Integer> supertypeIdList = classR.getSupertypeIdList();
        Collection arrayList = new ArrayList(acns.a((Iterable) supertypeIdList, 10));
        for (Integer intValue : supertypeIdList) {
            arrayList.add(typeTable.get(intValue.intValue()));
        }
        return (List) arrayList;
    }

    public static final Type type(Argument argument, TypeTable typeTable) {
        if (argument.hasType()) {
            return argument.getType();
        }
        return argument.hasTypeId() ? typeTable.get(argument.getTypeId()) : null;
    }

    public static final Type flexibleUpperBound(Type type, TypeTable typeTable) {
        if (type.hasFlexibleUpperBound()) {
            return type.getFlexibleUpperBound();
        }
        return type.hasFlexibleUpperBoundId() ? typeTable.get(type.getFlexibleUpperBoundId()) : null;
    }

    public static final List<Type> upperBounds(TypeParameter typeParameter, TypeTable typeTable) {
        List<Type> upperBoundList = typeParameter.getUpperBoundList();
        if ((upperBoundList.isEmpty() ^ 1) == 0) {
            upperBoundList = null;
        }
        if (upperBoundList != null) {
            return upperBoundList;
        }
        Iterable<Integer> upperBoundIdList = typeParameter.getUpperBoundIdList();
        Collection arrayList = new ArrayList(acns.a((Iterable) upperBoundIdList, 10));
        for (Integer intValue : upperBoundIdList) {
            arrayList.add(typeTable.get(intValue.intValue()));
        }
        return (List) arrayList;
    }

    public static final Type returnType(Function function, TypeTable typeTable) {
        if (function.hasReturnType()) {
            return function.getReturnType();
        }
        if (function.hasReturnTypeId()) {
            return typeTable.get(function.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Function".toString());
    }

    public static final boolean hasReceiver(Function function) {
        return function.hasReceiverType() || function.hasReceiverTypeId();
    }

    public static final Type receiverType(Function function, TypeTable typeTable) {
        if (function.hasReceiverType()) {
            return function.getReceiverType();
        }
        return function.hasReceiverTypeId() ? typeTable.get(function.getReceiverTypeId()) : null;
    }

    public static final Type returnType(Property property, TypeTable typeTable) {
        if (property.hasReturnType()) {
            return property.getReturnType();
        }
        if (property.hasReturnTypeId()) {
            return typeTable.get(property.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Property".toString());
    }

    public static final boolean hasReceiver(Property property) {
        return property.hasReceiverType() || property.hasReceiverTypeId();
    }

    public static final Type receiverType(Property property, TypeTable typeTable) {
        if (property.hasReceiverType()) {
            return property.getReceiverType();
        }
        return property.hasReceiverTypeId() ? typeTable.get(property.getReceiverTypeId()) : null;
    }

    public static final Type type(ValueParameter valueParameter, TypeTable typeTable) {
        if (valueParameter.hasType()) {
            return valueParameter.getType();
        }
        if (valueParameter.hasTypeId()) {
            return typeTable.get(valueParameter.getTypeId());
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter".toString());
    }

    public static final Type varargElementType(ValueParameter valueParameter, TypeTable typeTable) {
        if (valueParameter.hasVarargElementType()) {
            return valueParameter.getVarargElementType();
        }
        return valueParameter.hasVarargElementTypeId() ? typeTable.get(valueParameter.getVarargElementTypeId()) : null;
    }

    public static final Type outerType(Type type, TypeTable typeTable) {
        if (type.hasOuterType()) {
            return type.getOuterType();
        }
        return type.hasOuterTypeId() ? typeTable.get(type.getOuterTypeId()) : null;
    }

    public static final Type abbreviatedType(Type type, TypeTable typeTable) {
        if (type.hasAbbreviatedType()) {
            return type.getAbbreviatedType();
        }
        return type.hasAbbreviatedTypeId() ? typeTable.get(type.getAbbreviatedTypeId()) : null;
    }

    public static final Type underlyingType(TypeAlias typeAlias, TypeTable typeTable) {
        if (typeAlias.hasUnderlyingType()) {
            return typeAlias.getUnderlyingType();
        }
        if (typeAlias.hasUnderlyingTypeId()) {
            return typeTable.get(typeAlias.getUnderlyingTypeId());
        }
        throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias".toString());
    }

    public static final Type expandedType(TypeAlias typeAlias, TypeTable typeTable) {
        if (typeAlias.hasExpandedType()) {
            return typeAlias.getExpandedType();
        }
        if (typeAlias.hasExpandedTypeId()) {
            return typeTable.get(typeAlias.getExpandedTypeId());
        }
        throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias".toString());
    }
}
