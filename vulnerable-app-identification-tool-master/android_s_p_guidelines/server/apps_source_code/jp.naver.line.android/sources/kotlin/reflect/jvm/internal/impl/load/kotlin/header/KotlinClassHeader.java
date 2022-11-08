package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import defpackage.acob;
import defpackage.acom;
import defpackage.acru;
import defpackage.actx;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmBytecodeBinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;

public final class KotlinClassHeader {
    private final JvmBytecodeBinaryVersion bytecodeVersion;
    private final String[] data;
    private final int extraInt;
    private final String extraString;
    private final String[] incompatibleData;
    private final Kind kind;
    private final JvmMetadataVersion metadataVersion;
    private final String packageName;
    private final String[] strings;

    public enum Kind {
        ;
        
        public static final Companion Companion = null;
        private static final Map<Integer, Kind> entryById = null;
        private final int id;

        public final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(acru acru) {
                this();
            }

            public final Kind getById(int i) {
                Kind kind = (Kind) Kind.entryById.get(Integer.valueOf(i));
                return kind == null ? Kind.UNKNOWN : kind;
            }
        }

        public static final Kind getById(int i) {
            return Companion.getById(i);
        }

        private Kind(int i) {
            this.id = i;
        }

        static {
            Companion = new Companion();
            Kind[] values = values();
            Map linkedHashMap = new LinkedHashMap(actx.c(acom.a(values.length), 16));
            int length = values.length;
            int i;
            while (i < length) {
                Kind kind = values[i];
                linkedHashMap.put(Integer.valueOf(kind.id), kind);
                i++;
            }
            entryById = linkedHashMap;
        }
    }

    public KotlinClassHeader(Kind kind, JvmMetadataVersion jvmMetadataVersion, JvmBytecodeBinaryVersion jvmBytecodeBinaryVersion, String[] strArr, String[] strArr2, String[] strArr3, String str, int i, String str2) {
        this.kind = kind;
        this.metadataVersion = jvmMetadataVersion;
        this.bytecodeVersion = jvmBytecodeBinaryVersion;
        this.data = strArr;
        this.incompatibleData = strArr2;
        this.strings = strArr3;
        this.extraString = str;
        this.extraInt = i;
        this.packageName = str2;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final JvmMetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public final String[] getData() {
        return this.data;
    }

    public final String[] getIncompatibleData() {
        return this.incompatibleData;
    }

    public final String[] getStrings() {
        return this.strings;
    }

    public final String getMultifileClassName() {
        return (this.kind == Kind.MULTIFILE_CLASS_PART ? 1 : null) != null ? this.extraString : null;
    }

    public final List<String> getMultifilePartNames() {
        Object[] objArr = this.data;
        List<String> list = null;
        if ((this.kind == Kind.MULTIFILE_CLASS ? 1 : null) == null) {
            objArr = null;
        }
        if (objArr != null) {
            list = Arrays.asList(objArr);
        }
        return list == null ? acob.a : list;
    }

    public final boolean isPreRelease() {
        return (this.extraInt & 2) != 0;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.kind);
        stringBuilder.append(" version=");
        stringBuilder.append(this.metadataVersion);
        return stringBuilder.toString();
    }
}
