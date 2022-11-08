package defpackage;

/* renamed from: acsp */
public class acsp {
    public acud a(acrx acrx) {
        return acrx;
    }

    public acui a(acsb acsb) {
        return acsb;
    }

    public acuq a(acsf acsf) {
        return acsf;
    }

    public acus a(acsh acsh) {
        return acsh;
    }

    public acuc a(Class cls, String str) {
        return new acsd(cls, str);
    }

    public acua a(Class cls) {
        return new acrs(cls);
    }

    public String a(acrz acrz) {
        return a((acrw) acrz);
    }

    public String a(acrw acrw) {
        String obj = acrw.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring(21) : obj;
    }
}
