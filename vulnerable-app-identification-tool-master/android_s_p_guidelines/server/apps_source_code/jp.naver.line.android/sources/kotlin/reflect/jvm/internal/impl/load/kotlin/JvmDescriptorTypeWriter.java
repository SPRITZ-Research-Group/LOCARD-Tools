package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.addb;
import kotlin.reflect.jvm.internal.impl.name.Name;

public class JvmDescriptorTypeWriter<T> {
    private T jvmCurrentType;
    private int jvmCurrentTypeArrayLevel;
    private final JvmTypeFactory<T> jvmTypeFactory;

    public void writeArrayEnd() {
    }

    public void writeArrayType() {
        if (this.jvmCurrentType == null) {
            this.jvmCurrentTypeArrayLevel++;
        }
    }

    public void writeClass(T t) {
        writeJvmTypeAsIs(t);
    }

    protected final void writeJvmTypeAsIs(T t) {
        if (this.jvmCurrentType == null) {
            Object t2;
            if (this.jvmCurrentTypeArrayLevel > 0) {
                JvmTypeFactory jvmTypeFactory = this.jvmTypeFactory;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(addb.a((CharSequence) "[", this.jvmCurrentTypeArrayLevel));
                stringBuilder.append(this.jvmTypeFactory.toString(t2));
                t2 = jvmTypeFactory.createFromString(stringBuilder.toString());
            }
            this.jvmCurrentType = t2;
        }
    }

    public void writeTypeVariable(Name name, T t) {
        writeJvmTypeAsIs(t);
    }
}
