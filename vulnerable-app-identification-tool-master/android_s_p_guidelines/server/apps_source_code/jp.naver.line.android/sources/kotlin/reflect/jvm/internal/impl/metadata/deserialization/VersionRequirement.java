package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import defpackage.acru;
import defpackage.acry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.a;
import kotlin.k;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Level;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.VersionKind;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

public final class VersionRequirement {
    public static final Companion Companion = new Companion();
    private final Integer errorCode;
    private final VersionKind kind;
    private final a level;
    private final String message;
    private final Version version;

    public final class Companion {

        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Level.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[Level.WARNING.ordinal()] = 1;
                $EnumSwitchMapping$0[Level.ERROR.ordinal()] = 2;
                $EnumSwitchMapping$0[Level.HIDDEN.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(acru acru) {
            this();
        }

        public final List<VersionRequirement> create(MessageLite messageLite, NameResolver nameResolver, VersionRequirementTable versionRequirementTable) {
            List versionRequirementList;
            if (messageLite instanceof Class) {
                versionRequirementList = ((Class) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof Constructor) {
                versionRequirementList = ((Constructor) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof Function) {
                versionRequirementList = ((Function) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof Property) {
                versionRequirementList = ((Property) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof TypeAlias) {
                versionRequirementList = ((TypeAlias) messageLite).getVersionRequirementList();
            } else {
                StringBuilder stringBuilder = new StringBuilder("Unexpected declaration: ");
                stringBuilder.append(messageLite.getClass());
                throw new IllegalStateException(stringBuilder.toString());
            }
            Collection arrayList = new ArrayList();
            for (Integer intValue : versionRequirementList) {
                VersionRequirement create = VersionRequirement.Companion.create(intValue.intValue(), nameResolver, versionRequirementTable);
                if (create != null) {
                    arrayList.add(create);
                }
            }
            return (List) arrayList;
        }

        public final VersionRequirement create(int i, NameResolver nameResolver, VersionRequirementTable versionRequirementTable) {
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement versionRequirement = versionRequirementTable.get(i);
            VersionRequirement versionRequirement2 = null;
            if (versionRequirement == null) {
                return null;
            }
            a aVar;
            Version decode = Version.Companion.decode(versionRequirement.hasVersion() ? Integer.valueOf(versionRequirement.getVersion()) : null, versionRequirement.hasVersionFull() ? Integer.valueOf(versionRequirement.getVersionFull()) : null);
            Level level = versionRequirement.getLevel();
            if (level == null) {
                acry.a();
            }
            switch (WhenMappings.$EnumSwitchMapping$0[level.ordinal()]) {
                case 1:
                    aVar = a.WARNING;
                    break;
                case 2:
                    aVar = a.ERROR;
                    break;
                case 3:
                    aVar = a.HIDDEN;
                    break;
                default:
                    throw new k();
            }
            a aVar2 = aVar;
            Integer valueOf = versionRequirement.hasErrorCode() ? Integer.valueOf(versionRequirement.getErrorCode()) : null;
            if (versionRequirement.hasMessage()) {
                versionRequirement2 = nameResolver.getString(versionRequirement.getMessage());
            }
            return new VersionRequirement(decode, versionRequirement.getVersionKind(), aVar2, valueOf, versionRequirement2);
        }
    }

    public final class Version {
        public static final Companion Companion = new Companion();
        public static final Version INFINITY = new Version(256, 256, 256);
        private final int major;
        private final int minor;
        private final int patch;

        public final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(acru acru) {
                this();
            }

            public final Version decode(Integer num, Integer num2) {
                if (num2 != null) {
                    return new Version(num2.intValue() & 255, (num2.intValue() >> 8) & 255, (num2.intValue() >> 16) & 255);
                }
                if (num != null) {
                    return new Version(num.intValue() & 7, (num.intValue() >> 3) & 15, (num.intValue() >> 7) & 127);
                }
                return Version.INFINITY;
            }
        }

        public final boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Version) {
                    Version version = (Version) obj;
                    if ((this.major == version.major ? 1 : null) != null) {
                        if ((this.minor == version.minor ? 1 : null) != null) {
                            if ((this.patch == version.patch ? 1 : null) != null) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return (((this.major * 31) + this.minor) * 31) + this.patch;
        }

        public Version(int i, int i2, int i3) {
            this.major = i;
            this.minor = i2;
            this.patch = i3;
        }

        public /* synthetic */ Version(int i, int i2, int i3, int i4, acru acru) {
            if ((i4 & 4) != 0) {
                i3 = 0;
            }
            this(i, i2, i3);
        }

        public final String asString() {
            StringBuilder stringBuilder;
            int i;
            if (this.patch == 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.major);
                stringBuilder.append('.');
                i = this.minor;
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.major);
                stringBuilder.append('.');
                stringBuilder.append(this.minor);
                stringBuilder.append('.');
                i = this.patch;
            }
            stringBuilder.append(i);
            return stringBuilder.toString();
        }

        public final String toString() {
            return asString();
        }
    }

    public VersionRequirement(Version version, VersionKind versionKind, a aVar, Integer num, String str) {
        this.version = version;
        this.kind = versionKind;
        this.level = aVar;
        this.errorCode = num;
        this.message = str;
    }

    public final Version getVersion() {
        return this.version;
    }

    public final VersionKind getKind() {
        return this.kind;
    }

    public final String toString() {
        StringBuilder stringBuilder;
        String stringBuilder2;
        StringBuilder stringBuilder3 = new StringBuilder("since ");
        stringBuilder3.append(this.version);
        stringBuilder3.append(' ');
        stringBuilder3.append(this.level);
        if (this.errorCode != null) {
            stringBuilder = new StringBuilder(" error ");
            stringBuilder.append(this.errorCode);
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        if (this.message != null) {
            stringBuilder = new StringBuilder(": ");
            stringBuilder.append(this.message);
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        return stringBuilder3.toString();
    }
}
