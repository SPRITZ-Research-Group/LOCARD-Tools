package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

class co {
    private final String a;
    private final String b;

    co(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (str2 != null) {
            this.a = str.toLowerCase(Locale.ENGLISH);
            this.b = str2;
        } else {
            throw new IllegalArgumentException("No classname specified");
        }
    }

    static co a(String str, AppLovinLogger appLovinLogger) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return null;
        }
        StringBuilder stringBuilder;
        if (str.contains(":")) {
            try {
                int indexOf = str.indexOf(58);
                if (indexOf > 0 && indexOf < str.length() - 1) {
                    return new co(str.substring(0, indexOf).toLowerCase(Locale.ENGLISH), str.substring(indexOf + 1, str.length()));
                }
                stringBuilder = new StringBuilder("Unable to parse config '");
                stringBuilder.append(str);
                stringBuilder.append("': malformed string");
                appLovinLogger.userError("MediationAdapterManager", stringBuilder.toString());
                return null;
            } catch (Throwable th) {
                StringBuilder stringBuilder2 = new StringBuilder("Unable to parse config '");
                stringBuilder2.append(str);
                stringBuilder2.append("'");
                appLovinLogger.userError("MediationAdapterManager", stringBuilder2.toString(), th);
            }
        } else {
            String toLowerCase = str.toLowerCase(Locale.ENGLISH);
            if (cn.a.containsKey(toLowerCase)) {
                return new co(toLowerCase, (String) cn.a.get(toLowerCase));
            }
            stringBuilder = new StringBuilder("Unable to create config '");
            stringBuilder.append(str);
            stringBuilder.append("': unknown name");
            appLovinLogger.userError("MediationAdapterManager", stringBuilder.toString());
            return null;
        }
    }

    static String a(Collection<co> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (co c : collection) {
            stringBuilder.append(c.c());
            stringBuilder.append(',');
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    static Collection<co> b(String str, AppLovinLogger appLovinLogger) {
        if (appLovinLogger != null) {
            Collection arrayList = new ArrayList();
            for (String a : aa.a(str)) {
                co a2 = a(a, appLovinLogger);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("No logger specified");
    }

    String a() {
        return this.b;
    }

    String b() {
        return this.a;
    }

    String c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(":");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            co coVar = (co) obj;
            if (!this.a == null ? this.a.equals(coVar.a) : coVar.a == null) {
                return false;
            }
            if (this.b != null) {
                return this.b.equals(coVar.b);
            }
            if (coVar.b == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.a != null ? this.a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[Adapter Spec: ");
        stringBuilder.append(c());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
