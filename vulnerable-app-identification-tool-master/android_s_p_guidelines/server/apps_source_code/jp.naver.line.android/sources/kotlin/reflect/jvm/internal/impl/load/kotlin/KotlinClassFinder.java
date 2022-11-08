package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acru;
import java.util.Arrays;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public interface KotlinClassFinder {

    public abstract class Result {

        public final class ClassFileContent extends Result {
            private final byte[] content;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final boolean equals(Object obj) {
                if (this != obj) {
                    if (obj instanceof ClassFileContent) {
                    }
                    return false;
                }
                return true;
            }

            public final int hashCode() {
                byte[] bArr = this.content;
                return bArr != null ? Arrays.hashCode(bArr) : 0;
            }

            public final String toString() {
                StringBuilder stringBuilder = new StringBuilder("ClassFileContent(content=");
                stringBuilder.append(Arrays.toString(this.content));
                stringBuilder.append(")");
                return stringBuilder.toString();
            }

            public final byte[] getContent() {
                return this.content;
            }
        }

        public final class KotlinClass extends Result {
            private final KotlinJvmBinaryClass kotlinJvmBinaryClass;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final boolean equals(Object obj) {
                if (this != obj) {
                    if (obj instanceof KotlinClass) {
                    }
                    return false;
                }
                return true;
            }

            public final int hashCode() {
                KotlinJvmBinaryClass kotlinJvmBinaryClass = this.kotlinJvmBinaryClass;
                return kotlinJvmBinaryClass != null ? kotlinJvmBinaryClass.hashCode() : 0;
            }

            public final String toString() {
                StringBuilder stringBuilder = new StringBuilder("KotlinClass(kotlinJvmBinaryClass=");
                stringBuilder.append(this.kotlinJvmBinaryClass);
                stringBuilder.append(")");
                return stringBuilder.toString();
            }

            public KotlinClass(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
                super();
                this.kotlinJvmBinaryClass = kotlinJvmBinaryClass;
            }

            public final KotlinJvmBinaryClass getKotlinJvmBinaryClass() {
                return this.kotlinJvmBinaryClass;
            }
        }

        private Result() {
        }

        public /* synthetic */ Result(acru acru) {
            this();
        }

        public final KotlinJvmBinaryClass toKotlinJvmBinaryClass() {
            KotlinClass kotlinClass = (KotlinClass) (!(this instanceof KotlinClass) ? null : this);
            return kotlinClass != null ? kotlinClass.getKotlinJvmBinaryClass() : null;
        }
    }

    Result findKotlinClassOrContent(JavaClass javaClass);

    Result findKotlinClassOrContent(ClassId classId);
}
