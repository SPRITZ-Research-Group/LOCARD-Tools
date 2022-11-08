package kotlin.reflect.jvm.internal.impl.load.kotlin;

import defpackage.acru;
import kotlin.k;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;

public final class MemberSignature {
    public static final Companion Companion = new Companion();
    private final String signature;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final MemberSignature fromMethod(NameResolver nameResolver, JvmMethodSignature jvmMethodSignature) {
            return fromMethodNameAndDesc(nameResolver.getString(jvmMethodSignature.getName()), nameResolver.getString(jvmMethodSignature.getDesc()));
        }

        public final MemberSignature fromMethodNameAndDesc(String str, String str2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            return new MemberSignature(stringBuilder.toString(), null);
        }

        public final MemberSignature fromFieldNameAndDesc(String str, String str2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("#");
            stringBuilder.append(str2);
            return new MemberSignature(stringBuilder.toString(), null);
        }

        public final MemberSignature fromJvmMemberSignature(JvmMemberSignature jvmMemberSignature) {
            if (jvmMemberSignature instanceof Method) {
                return fromMethodNameAndDesc(jvmMemberSignature.getName(), jvmMemberSignature.getDesc());
            }
            if (jvmMemberSignature instanceof Field) {
                return fromFieldNameAndDesc(jvmMemberSignature.getName(), jvmMemberSignature.getDesc());
            }
            throw new k();
        }

        public final MemberSignature fromMethodSignatureAndParameterIndex(MemberSignature memberSignature, int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(memberSignature.getSignature$descriptors_jvm());
            stringBuilder.append("@");
            stringBuilder.append(i);
            return new MemberSignature(stringBuilder.toString(), null);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof MemberSignature) {
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        String str = this.signature;
        return str != null ? str.hashCode() : 0;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("MemberSignature(signature=");
        stringBuilder.append(this.signature);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private MemberSignature(String str) {
        this.signature = str;
    }

    public /* synthetic */ MemberSignature(String str, acru acru) {
        this(str);
    }

    public final String getSignature$descriptors_jvm() {
        return this.signature;
    }
}
