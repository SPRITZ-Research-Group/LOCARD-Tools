package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import defpackage.acnr;
import defpackage.actp;
import defpackage.actx;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ClassMapperLite {
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();
    private static final Map<String, String> map;

    static {
        StringBuilder stringBuilder;
        Map linkedHashMap = new LinkedHashMap();
        List b = acnr.b((Object[]) new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", "J", "Double", "D"});
        actp a = actx.a((actp) acnr.a((Collection) b), 2);
        int a2 = a.a();
        int b2 = a.b();
        int c = a.c();
        if (c <= 0 ? a2 < b2 : a2 > b2) {
            while (true) {
                stringBuilder = new StringBuilder("kotlin/");
                stringBuilder.append((String) b.get(a2));
                int i = a2 + 1;
                linkedHashMap.put(stringBuilder.toString(), b.get(i));
                stringBuilder = new StringBuilder("kotlin/");
                stringBuilder.append((String) b.get(a2));
                stringBuilder.append("Array");
                String stringBuilder2 = stringBuilder.toString();
                StringBuilder stringBuilder3 = new StringBuilder("[");
                stringBuilder3.append((String) b.get(i));
                linkedHashMap.put(stringBuilder2, stringBuilder3.toString());
                if (a2 == b2) {
                    break;
                }
                a2 += c;
            }
        }
        linkedHashMap.put("kotlin/Unit", "V");
        ClassMapperLite$map$1$1 classMapperLite$map$1$1 = new ClassMapperLite$map$1$1(linkedHashMap);
        classMapperLite$map$1$1.invoke("Any", "java/lang/Object");
        classMapperLite$map$1$1.invoke("Nothing", "java/lang/Void");
        classMapperLite$map$1$1.invoke("Annotation", "java/lang/annotation/Annotation");
        for (String str : acnr.b((Object[]) new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"})) {
            classMapperLite$map$1$1.invoke(str, "java/lang/".concat(String.valueOf(str)));
        }
        for (String str2 : acnr.b((Object[]) new String[]{"Iterator", "Collection", "List", "Set", "Map", "ListIterator"})) {
            classMapperLite$map$1$1.invoke("collections/".concat(String.valueOf(str2)), "java/util/".concat(String.valueOf(str2)));
            classMapperLite$map$1$1.invoke("collections/Mutable".concat(String.valueOf(str2)), "java/util/".concat(String.valueOf(str2)));
        }
        classMapperLite$map$1$1.invoke("collections/Iterable", "java/lang/Iterable");
        classMapperLite$map$1$1.invoke("collections/MutableIterable", "java/lang/Iterable");
        classMapperLite$map$1$1.invoke("collections/Map.Entry", "java/util/Map$Entry");
        classMapperLite$map$1$1.invoke("collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (c = 0; c <= 22; c++) {
            classMapperLite$map$1$1.invoke("Function".concat(String.valueOf(c)), "kotlin/jvm/functions/Function".concat(String.valueOf(c)));
            classMapperLite$map$1$1.invoke("reflect/KFunction".concat(String.valueOf(c)), "kotlin/reflect/KFunction");
        }
        for (String str22 : acnr.b((Object[]) new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum"})) {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str22);
            stringBuilder4.append(".Companion");
            String stringBuilder5 = stringBuilder4.toString();
            stringBuilder = new StringBuilder("kotlin/jvm/internal/");
            stringBuilder.append(str22);
            stringBuilder.append("CompanionObject");
            classMapperLite$map$1$1.invoke(stringBuilder5, stringBuilder.toString());
        }
        map = linkedHashMap;
    }

    private ClassMapperLite() {
    }

    public static final String mapClass(String str) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            return str2;
        }
        StringBuilder stringBuilder = new StringBuilder("L");
        stringBuilder.append(str.replace('.', '$'));
        stringBuilder.append(';');
        return stringBuilder.toString();
    }
}
