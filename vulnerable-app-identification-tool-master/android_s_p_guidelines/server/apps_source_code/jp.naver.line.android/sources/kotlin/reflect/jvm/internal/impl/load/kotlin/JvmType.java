package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acru;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

public abstract class JvmType {

    public final class Array extends JvmType {
        private final JvmType elementType;

        public Array(JvmType jvmType) {
            super();
            this.elementType = jvmType;
        }

        public final JvmType getElementType() {
            return this.elementType;
        }
    }

    public final class Object extends JvmType {
        private final String internalName;

        public Object(String str) {
            super();
            this.internalName = str;
        }

        public final String getInternalName() {
            return this.internalName;
        }
    }

    public final class Primitive extends JvmType {
        private final JvmPrimitiveType jvmPrimitiveType;

        public Primitive(JvmPrimitiveType jvmPrimitiveType) {
            super();
            this.jvmPrimitiveType = jvmPrimitiveType;
        }

        public final JvmPrimitiveType getJvmPrimitiveType() {
            return this.jvmPrimitiveType;
        }
    }

    private JvmType() {
    }

    public /* synthetic */ JvmType(acru acru) {
        this();
    }

    public String toString() {
        return JvmTypeFactoryImpl.INSTANCE.toString(this);
    }
}
