package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import defpackage.acru;
import defpackage.acry;

public abstract class JvmMemberSignature {

    public final class Field extends JvmMemberSignature {
        private final String desc;
        private final String name;

        public final String component1() {
            return getName();
        }

        public final String component2() {
            return getDesc();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Field) {
                    Field field = (Field) obj;
                    if (acry.a(getName(), field.getName())) {
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            String name = getName();
            int i = 0;
            int hashCode = (name != null ? name.hashCode() : 0) * 31;
            String desc = getDesc();
            if (desc != null) {
                i = desc.hashCode();
            }
            return hashCode + i;
        }

        public Field(String str, String str2) {
            super();
            this.name = str;
            this.desc = str2;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getName() {
            return this.name;
        }

        public final String asString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getName());
            stringBuilder.append(':');
            stringBuilder.append(getDesc());
            return stringBuilder.toString();
        }
    }

    public final class Method extends JvmMemberSignature {
        private final String desc;
        private final String name;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Method) {
                    Method method = (Method) obj;
                    if (acry.a(getName(), method.getName())) {
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            String name = getName();
            int i = 0;
            int hashCode = (name != null ? name.hashCode() : 0) * 31;
            String desc = getDesc();
            if (desc != null) {
                i = desc.hashCode();
            }
            return hashCode + i;
        }

        public Method(String str, String str2) {
            super();
            this.name = str;
            this.desc = str2;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getName() {
            return this.name;
        }

        public final String asString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getName());
            stringBuilder.append(getDesc());
            return stringBuilder.toString();
        }
    }

    public abstract String asString();

    public abstract String getDesc();

    public abstract String getName();

    private JvmMemberSignature() {
    }

    public /* synthetic */ JvmMemberSignature(acru acru) {
        this();
    }

    public final String toString() {
        return asString();
    }
}
