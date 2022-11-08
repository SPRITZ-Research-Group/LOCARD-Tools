package kotlin.reflect.jvm.internal.impl.utils;

import defpackage.acom;
import defpackage.acru;
import defpackage.acry;
import defpackage.acsi;
import defpackage.acso;
import defpackage.acuo;
import java.util.Map;
import kotlin.e;

public final class Jsr305State {
    static final /* synthetic */ acuo[] $$delegatedProperties = new acuo[]{acso.a(new acsi(acso.a(Jsr305State.class), "description", "getDescription()[Ljava/lang/String;"))};
    public static final Companion Companion = new Companion();
    public static final Jsr305State DEFAULT = new Jsr305State(ReportLevel.WARN, null, acom.a(), false, 8, null);
    public static final Jsr305State DISABLED;
    public static final Jsr305State STRICT;
    private final e description$delegate;
    private final boolean enableCompatqualCheckerFrameworkAnnotations;
    private final ReportLevel global;
    private final ReportLevel migration;
    private final Map<String, ReportLevel> user;

    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Jsr305State) {
                Jsr305State jsr305State = (Jsr305State) obj;
                if (acry.a(this.global, jsr305State.global) && acry.a(this.migration, jsr305State.migration) && acry.a(this.user, jsr305State.user)) {
                    if ((this.enableCompatqualCheckerFrameworkAnnotations == jsr305State.enableCompatqualCheckerFrameworkAnnotations ? 1 : null) != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        ReportLevel reportLevel = this.global;
        int i = 0;
        int hashCode = (reportLevel != null ? reportLevel.hashCode() : 0) * 31;
        ReportLevel reportLevel2 = this.migration;
        hashCode = (hashCode + (reportLevel2 != null ? reportLevel2.hashCode() : 0)) * 31;
        Map map = this.user;
        if (map != null) {
            i = map.hashCode();
        }
        hashCode = (hashCode + i) * 31;
        i = this.enableCompatqualCheckerFrameworkAnnotations;
        if (i != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("Jsr305State(global=");
        stringBuilder.append(this.global);
        stringBuilder.append(", migration=");
        stringBuilder.append(this.migration);
        stringBuilder.append(", user=");
        stringBuilder.append(this.user);
        stringBuilder.append(", enableCompatqualCheckerFrameworkAnnotations=");
        stringBuilder.append(this.enableCompatqualCheckerFrameworkAnnotations);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public Jsr305State(ReportLevel reportLevel, ReportLevel reportLevel2, Map<String, ? extends ReportLevel> map, boolean z) {
        this.global = reportLevel;
        this.migration = reportLevel2;
        this.user = map;
        this.enableCompatqualCheckerFrameworkAnnotations = z;
        this.description$delegate = h.a(new Jsr305State$description$2(this));
    }

    public final ReportLevel getGlobal() {
        return this.global;
    }

    public final ReportLevel getMigration() {
        return this.migration;
    }

    public final Map<String, ReportLevel> getUser() {
        return this.user;
    }

    public /* synthetic */ Jsr305State(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, boolean z, int i, acru acru) {
        if ((i & 8) != 0) {
            z = true;
        }
        this(reportLevel, reportLevel2, map, z);
    }

    public final boolean getEnableCompatqualCheckerFrameworkAnnotations() {
        return this.enableCompatqualCheckerFrameworkAnnotations;
    }

    public final boolean getDisabled() {
        return ((Jsr305State) this) == DISABLED;
    }

    static {
        ReportLevel reportLevel = ReportLevel.IGNORE;
        DISABLED = new Jsr305State(reportLevel, reportLevel, acom.a(), false, 8, null);
        ReportLevel reportLevel2 = ReportLevel.STRICT;
        STRICT = new Jsr305State(reportLevel2, reportLevel2, acom.a(), false, 8, null);
    }
}
