package defpackage;

import kotlin.Metadata;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass$Factory;", "", "()V", "create", "Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass;", "klass", "Ljava/lang/Class;", "descriptors.runtime"}, k = 1, mv = {1, 1, 13})
/* renamed from: aczi */
public final class aczi {
    private aczi() {
    }

    public /* synthetic */ aczi(byte b) {
        this();
    }

    public static aczh a(Class<?> cls) {
        ReadKotlinClassHeaderAnnotationVisitor readKotlinClassHeaderAnnotationVisitor = new ReadKotlinClassHeaderAnnotationVisitor();
        acze acze = acze.a;
        acze.a((Class) cls, (AnnotationVisitor) readKotlinClassHeaderAnnotationVisitor);
        KotlinClassHeader createHeader = readKotlinClassHeaderAnnotationVisitor.createHeader();
        return createHeader == null ? null : new aczh(cls, createHeader, (byte) 0);
    }
}
