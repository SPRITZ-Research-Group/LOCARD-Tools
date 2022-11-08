package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import defpackage.acnr;
import defpackage.acns;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;

public final class TypeTable {
    private final List<Type> types;

    public TypeTable(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable typeTable) {
        List typeList = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            Iterable typeList2 = typeTable.getTypeList();
            Collection arrayList = new ArrayList(acns.a(typeList2, 10));
            int i = 0;
            for (Object next : typeList2) {
                int i2 = i + 1;
                if (i < 0) {
                    acnr.a();
                }
                Object next2 = (Type) next2;
                if (i >= firstNullable) {
                    next2 = next2.toBuilder().setNullable(true).build();
                }
                arrayList.add(next2);
                i = i2;
            }
            typeList = (List) arrayList;
        }
        this.types = typeList;
    }

    public final Type get(int i) {
        return (Type) this.types.get(i);
    }
}
