package kotlin.reflect.jvm.internal.impl.load.java;

import defpackage.acru;
import defpackage.acry;
import java.util.Arrays;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface JavaClassFinder {

    public final class Request {
        private final ClassId classId;
        private final JavaClass outerClass;
        private final byte[] previouslyFoundClassFileContent;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Request) {
                    Request request = (Request) obj;
                    if (acry.a(this.classId, request.classId)) {
                        if (acry.a(this.previouslyFoundClassFileContent, request.previouslyFoundClassFileContent)) {
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            ClassId classId = this.classId;
            int i = 0;
            int hashCode = (classId != null ? classId.hashCode() : 0) * 31;
            byte[] bArr = this.previouslyFoundClassFileContent;
            hashCode = (hashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
            JavaClass javaClass = this.outerClass;
            if (javaClass != null) {
                i = javaClass.hashCode();
            }
            return hashCode + i;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("Request(classId=");
            stringBuilder.append(this.classId);
            stringBuilder.append(", previouslyFoundClassFileContent=");
            stringBuilder.append(Arrays.toString(this.previouslyFoundClassFileContent));
            stringBuilder.append(", outerClass=");
            stringBuilder.append(this.outerClass);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }

        public Request(ClassId classId, byte[] bArr, JavaClass javaClass) {
            this.classId = classId;
            this.previouslyFoundClassFileContent = bArr;
            this.outerClass = javaClass;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public /* synthetic */ Request(ClassId classId, byte[] bArr, JavaClass javaClass, int i, acru acru) {
            if ((i & 2) != 0) {
                bArr = null;
            }
            if ((i & 4) != 0) {
                javaClass = null;
            }
            this(classId, bArr, javaClass);
        }
    }

    JavaClass findClass(Request request);

    JavaPackage findPackage(FqName fqName);

    Set<String> knownClassNamesInPackage(FqName fqName);
}
