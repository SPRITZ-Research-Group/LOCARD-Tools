package ezvcard;

import defpackage.aqh;

public enum f {
    V2_1("2.1", aqh.OLD, null),
    V3_0("3.0", aqh.NEW, null),
    V4_0("4.0", aqh.NEW, "urn:ietf:params:xml:ns:vcard-4.0");
    
    private final aqh syntaxStyle;
    private final String version;
    private final String xmlNamespace;

    private f(String str, aqh aqh, String str2) {
        this.version = str;
        this.syntaxStyle = aqh;
        this.xmlNamespace = str2;
    }

    public final String a() {
        return this.version;
    }

    public final aqh b() {
        return this.syntaxStyle;
    }

    public final String c() {
        return this.xmlNamespace;
    }

    public static f a(String str) {
        for (f fVar : values()) {
            if (fVar.version.equals(str)) {
                return fVar;
            }
        }
        return null;
    }

    public final String toString() {
        return this.version;
    }
}
