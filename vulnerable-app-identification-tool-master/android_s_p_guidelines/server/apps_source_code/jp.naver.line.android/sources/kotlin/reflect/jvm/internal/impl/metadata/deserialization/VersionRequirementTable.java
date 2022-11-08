package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import defpackage.acnz;
import defpackage.acob;
import defpackage.acru;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement;

public final class VersionRequirementTable {
    public static final Companion Companion = new Companion();
    private static final VersionRequirementTable EMPTY = new VersionRequirementTable(acob.a);
    private final List<VersionRequirement> infos;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final VersionRequirementTable getEMPTY() {
            return VersionRequirementTable.EMPTY;
        }

        public final VersionRequirementTable create(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable versionRequirementTable) {
            return versionRequirementTable.getRequirementCount() == 0 ? getEMPTY() : new VersionRequirementTable(versionRequirementTable.getRequirementList(), null);
        }
    }

    private VersionRequirementTable(List<VersionRequirement> list) {
        this.infos = list;
    }

    public /* synthetic */ VersionRequirementTable(List list, acru acru) {
        this(list);
    }

    public final VersionRequirement get(int i) {
        return (VersionRequirement) acnz.b(this.infos, i);
    }
}
