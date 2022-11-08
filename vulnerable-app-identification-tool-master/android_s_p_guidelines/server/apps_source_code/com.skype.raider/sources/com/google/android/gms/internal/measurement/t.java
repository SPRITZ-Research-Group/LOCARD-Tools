package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.a;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.skype.Defines;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class t extends en {
    t(eo eoVar) {
        super(eoVar);
    }

    private static Boolean a(double d, fc fcVar) {
        try {
            return a(new BigDecimal(d), fcVar, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Boolean a(long j, fc fcVar) {
        try {
            return a(new BigDecimal(j), fcVar, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean a(Boolean bool, boolean z) {
        return bool == null ? null : Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean a(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!(z || i == 1)) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    q().y().a("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private static Boolean a(String str, fc fcVar) {
        Boolean bool = null;
        if (!ew.i(str)) {
            return bool;
        }
        try {
            return a(new BigDecimal(str), fcVar, 0.0d);
        } catch (NumberFormatException e) {
            return bool;
        }
    }

    @VisibleForTesting
    private final Boolean a(String str, fe feVar) {
        int i = 0;
        String str2 = null;
        ab.a((Object) feVar);
        if (str == null || feVar.c == null || feVar.c.intValue() == 0) {
            return null;
        }
        List list;
        if (feVar.c.intValue() == 6) {
            if (feVar.f == null || feVar.f.length == 0) {
                return null;
            }
        } else if (feVar.d == null) {
            return null;
        }
        int intValue = feVar.c.intValue();
        boolean z = feVar.e != null && feVar.e.booleanValue();
        String toUpperCase = (z || intValue == 1 || intValue == 6) ? feVar.d : feVar.d.toUpperCase(Locale.ENGLISH);
        if (feVar.f == null) {
            list = null;
        } else {
            String[] strArr = feVar.f;
            if (z) {
                list = Arrays.asList(strArr);
            } else {
                list = new ArrayList();
                int length = strArr.length;
                while (i < length) {
                    list.add(strArr[i].toUpperCase(Locale.ENGLISH));
                    i++;
                }
            }
        }
        if (intValue == 1) {
            str2 = toUpperCase;
        }
        return a(str, intValue, z, toUpperCase, list, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @VisibleForTesting
    private static Boolean a(BigDecimal bigDecimal, fc fcVar, double d) {
        boolean z = true;
        ab.a((Object) fcVar);
        if (fcVar.c == null || fcVar.c.intValue() == 0) {
            return null;
        }
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        if (fcVar.c.intValue() == 4) {
            if (fcVar.f == null || fcVar.g == null) {
                return null;
            }
        } else if (fcVar.e == null) {
            return null;
        }
        int intValue = fcVar.c.intValue();
        BigDecimal bigDecimal4;
        if (fcVar.c.intValue() == 4) {
            if (!ew.i(fcVar.f) || !ew.i(fcVar.g)) {
                return null;
            }
            try {
                bigDecimal2 = new BigDecimal(fcVar.f);
                bigDecimal4 = new BigDecimal(fcVar.g);
                bigDecimal3 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (!ew.i(fcVar.e)) {
            return null;
        } else {
            try {
                bigDecimal2 = null;
                bigDecimal3 = new BigDecimal(fcVar.e);
                bigDecimal4 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        if (intValue == 4) {
            if (bigDecimal2 == null) {
                return null;
            }
        }
        switch (intValue) {
            case 1:
                if (bigDecimal.compareTo(bigDecimal3) != -1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (bigDecimal.compareTo(bigDecimal3) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d != 0.0d) {
                    if (!(bigDecimal.compareTo(bigDecimal3.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal3.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1)) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal.compareTo(bigDecimal3) != 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 4:
                if (bigDecimal.compareTo(bigDecimal2) == -1 || bigDecimal.compareTo(bigDecimal4) == 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
        }
        return null;
    }

    @WorkerThread
    final fi[] a(String str, fj[] fjVarArr, fo[] foVarArr) {
        int intValue;
        BitSet bitSet;
        BitSet bitSet2;
        int i;
        fi fiVar;
        Object obj;
        cs r_;
        int i2;
        int length;
        Map map;
        Map map2;
        fi fiVar2;
        ab.a(str);
        HashSet hashSet = new HashSet();
        a aVar = new a();
        Map aVar2 = new a();
        a aVar3 = new a();
        Map e = r_().e(str);
        if (e != null) {
            for (Integer intValue2 : e.keySet()) {
                intValue = intValue2.intValue();
                fn fnVar = (fn) e.get(Integer.valueOf(intValue));
                bitSet = (BitSet) aVar2.get(Integer.valueOf(intValue));
                bitSet2 = (BitSet) aVar3.get(Integer.valueOf(intValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    aVar2.put(Integer.valueOf(intValue), bitSet);
                    bitSet2 = new BitSet();
                    aVar3.put(Integer.valueOf(intValue), bitSet2);
                }
                for (i = 0; i < (fnVar.c.length << 6); i++) {
                    if (ew.a(fnVar.c, i)) {
                        q().C().a("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (ew.a(fnVar.d, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                fiVar = new fi();
                aVar.put(Integer.valueOf(intValue), fiVar);
                fiVar.f = Boolean.valueOf(false);
                fiVar.e = fnVar;
                fiVar.d = new fn();
                fiVar.d.d = ew.a(bitSet);
                fiVar.d.c = ew.a(bitSet2);
            }
        }
        if (fjVarArr != null) {
            fj fjVar = null;
            long j = 0;
            Long l = null;
            a aVar4 = new a();
            int length2 = fjVarArr.length;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= length2) {
                    break;
                }
                fk[] fkVarArr;
                String str2;
                Long l2;
                long j2;
                fj fjVar2;
                ah a;
                ah ahVar;
                long j3;
                int intValue3;
                BitSet bitSet3;
                BitSet bitSet4;
                fj fjVar3 = fjVarArr[i4];
                String str3 = fjVar3.d;
                fk[] fkVarArr2 = fjVar3.c;
                if (s().c(str, al.O)) {
                    int length3;
                    fk fkVar;
                    n();
                    Long l3 = (Long) ew.b(fjVar3, "_eid");
                    Object obj2 = l3 != null ? 1 : null;
                    obj = (obj2 == null || !str3.equals("_ep")) ? null : 1;
                    if (obj != null) {
                        n();
                        str3 = (String) ew.b(fjVar3, "_en");
                        if (TextUtils.isEmpty(str3)) {
                            q().v().a("Extra parameter without an event name. eventId", l3);
                        } else {
                            Long l4;
                            if (fjVar == null || l == null || l3.longValue() != l.longValue()) {
                                Pair a2 = r_().a(str, l3);
                                if (a2 == null || a2.first == null) {
                                    q().v().a("Extra parameter without existing main event. eventName, eventId", str3, l3);
                                } else {
                                    fj fjVar4 = (fj) a2.first;
                                    j = ((Long) a2.second).longValue();
                                    n();
                                    l4 = (Long) ew.b(fjVar4, "_eid");
                                    fjVar = fjVar4;
                                }
                            } else {
                                l4 = l;
                            }
                            j--;
                            if (j <= 0) {
                                r_ = r_();
                                r_.c();
                                r_.q().C().a("Clearing complex main event info. appId", str);
                                try {
                                    r_.x().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                                } catch (SQLiteException e2) {
                                    r_.q().v().a("Error clearing complex main event", e2);
                                }
                            } else {
                                r_().a(str, l3, j, fjVar);
                            }
                            fk[] fkVarArr3 = new fk[(fjVar.c.length + fkVarArr2.length)];
                            i2 = 0;
                            fk[] fkVarArr4 = fjVar.c;
                            length3 = fkVarArr4.length;
                            i = 0;
                            while (i < length3) {
                                fkVar = fkVarArr4[i];
                                n();
                                if (ew.a(fjVar3, fkVar.c) == null) {
                                    i3 = i2 + 1;
                                    fkVarArr3[i2] = fkVar;
                                } else {
                                    i3 = i2;
                                }
                                i++;
                                i2 = i3;
                            }
                            if (i2 > 0) {
                                length = fkVarArr2.length;
                                i3 = 0;
                                while (i3 < length) {
                                    i = i2 + 1;
                                    fkVarArr3[i2] = fkVarArr2[i3];
                                    i3++;
                                    i2 = i;
                                }
                                fkVarArr = i2 == fkVarArr3.length ? fkVarArr3 : (fk[]) Arrays.copyOf(fkVarArr3, i2);
                                str2 = str3;
                                l2 = l4;
                                j2 = j;
                                fjVar2 = fjVar;
                            } else {
                                q().y().a("No unique parameters in main event. eventName", str3);
                                fkVarArr = fkVarArr2;
                                str2 = str3;
                                l2 = l4;
                                j2 = j;
                                fjVar2 = fjVar;
                            }
                        }
                        i3 = i4 + 1;
                    } else if (obj2 != null) {
                        n();
                        Long valueOf = Long.valueOf(0);
                        l = ew.b(fjVar3, "_epc");
                        if (l != null) {
                            valueOf = l;
                        }
                        j = valueOf.longValue();
                        if (j <= 0) {
                            q().y().a("Complex event with zero extra param count. eventName", str3);
                            fkVarArr = fkVarArr2;
                            str2 = str3;
                            l2 = l3;
                            j2 = j;
                            fjVar2 = fjVar3;
                        } else {
                            r_().a(str, l3, j, fjVar3);
                            fkVarArr = fkVarArr2;
                            str2 = str3;
                            l2 = l3;
                            j2 = j;
                            fjVar2 = fjVar3;
                        }
                    }
                    a = r_().a(str, fjVar3.d);
                    if (a != null) {
                        q().y().a("Event aggregate wasn't created during raw event logging. appId, event", av.a(str), m().a(str2));
                        ahVar = new ah(str, fjVar3.d, 1, 1, fjVar3.e.longValue(), 0, null, null, null);
                    } else {
                        ahVar = a.a();
                    }
                    r_().a(ahVar);
                    j3 = ahVar.c;
                    map = (Map) aVar4.get(str2);
                    if (map != null) {
                        map = r_().f(str, str2);
                        if (map == null) {
                            map = new a();
                        }
                        aVar4.put(str2, map);
                        map2 = map;
                    } else {
                        map2 = map;
                    }
                    for (Integer intValue22 : map2.keySet()) {
                        intValue3 = intValue22.intValue();
                        if (hashSet.contains(Integer.valueOf(intValue3))) {
                            bitSet = (BitSet) aVar2.get(Integer.valueOf(intValue3));
                            bitSet2 = (BitSet) aVar3.get(Integer.valueOf(intValue3));
                            if (((fi) aVar.get(Integer.valueOf(intValue3))) != null) {
                                fiVar2 = new fi();
                                aVar.put(Integer.valueOf(intValue3), fiVar2);
                                fiVar2.f = Boolean.valueOf(true);
                                bitSet = new BitSet();
                                aVar2.put(Integer.valueOf(intValue3), bitSet);
                                bitSet2 = new BitSet();
                                aVar3.put(Integer.valueOf(intValue3), bitSet2);
                                bitSet3 = bitSet2;
                                bitSet4 = bitSet;
                            } else {
                                bitSet3 = bitSet2;
                                bitSet4 = bitSet;
                            }
                            for (fa faVar : (List) map2.get(Integer.valueOf(intValue3))) {
                                if (q().a(2)) {
                                    q().C().a("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), faVar.c, m().a(faVar.d));
                                    q().C().a("Filter definition", m().a(faVar));
                                }
                                if (faVar.c != null || faVar.c.intValue() > Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) {
                                    q().y().a("Invalid event filter ID. appId, id", av.a(str), String.valueOf(faVar.c));
                                } else if (bitSet4.get(faVar.c.intValue())) {
                                    q().C().a("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), faVar.c);
                                } else {
                                    Boolean a3;
                                    ax C;
                                    String str4;
                                    Boolean bool;
                                    if (faVar.f != null) {
                                        a3 = a(j3, faVar.f);
                                        if (a3 == null) {
                                            a3 = null;
                                        } else if (!a3.booleanValue()) {
                                            a3 = Boolean.valueOf(false);
                                        }
                                        C = q().C();
                                        str4 = "Event filter result";
                                        if (a3 != null) {
                                            obj2 = "null";
                                        } else {
                                            bool = a3;
                                        }
                                        C.a(str4, obj2);
                                        if (a3 != null) {
                                            hashSet.add(Integer.valueOf(intValue3));
                                        } else {
                                            bitSet3.set(faVar.c.intValue());
                                            if (a3.booleanValue()) {
                                                bitSet4.set(faVar.c.intValue());
                                            }
                                        }
                                    }
                                    Set hashSet2 = new HashSet();
                                    for (fb fbVar : faVar.e) {
                                        if (TextUtils.isEmpty(fbVar.f)) {
                                            q().y().a("null or empty param name in filter. event", m().a(str2));
                                            a3 = null;
                                            break;
                                        }
                                        hashSet2.add(fbVar.f);
                                    }
                                    Map aVar5 = new a();
                                    for (fk fkVar2 : fkVarArr) {
                                        if (hashSet2.contains(fkVar2.c)) {
                                            if (fkVar2.e == null) {
                                                if (fkVar2.f == null) {
                                                    if (fkVar2.d == null) {
                                                        q().y().a("Unknown value for param. event, param", m().a(str2), m().b(fkVar2.c));
                                                        a3 = null;
                                                        break;
                                                    }
                                                    aVar5.put(fkVar2.c, fkVar2.d);
                                                } else {
                                                    aVar5.put(fkVar2.c, fkVar2.f);
                                                }
                                            } else {
                                                aVar5.put(fkVar2.c, fkVar2.e);
                                            }
                                        }
                                    }
                                    fb[] fbVarArr = faVar.e;
                                    int length4 = fbVarArr.length;
                                    i2 = 0;
                                    while (true) {
                                        intValue = i2;
                                        if (intValue >= length4) {
                                            a3 = Boolean.valueOf(true);
                                            break;
                                        }
                                        fb fbVar2 = fbVarArr[intValue];
                                        boolean equals = Boolean.TRUE.equals(fbVar2.e);
                                        String str5 = fbVar2.f;
                                        if (TextUtils.isEmpty(str5)) {
                                            q().y().a("Event has empty param name. event", m().a(str2));
                                            a3 = null;
                                            break;
                                        }
                                        Object obj3 = aVar5.get(str5);
                                        if (obj3 instanceof Long) {
                                            if (fbVar2.d == null) {
                                                q().y().a("No number filter for long param. event, param", m().a(str2), m().b(str5));
                                                a3 = null;
                                                break;
                                            }
                                            a3 = a(((Long) obj3).longValue(), fbVar2.d);
                                            if (a3 == null) {
                                                a3 = null;
                                                break;
                                            }
                                            if (((!a3.booleanValue() ? 1 : 0) ^ equals) != 0) {
                                                a3 = Boolean.valueOf(false);
                                                break;
                                            }
                                        } else if (obj3 instanceof Double) {
                                            if (fbVar2.d == null) {
                                                q().y().a("No number filter for double param. event, param", m().a(str2), m().b(str5));
                                                a3 = null;
                                                break;
                                            }
                                            a3 = a(((Double) obj3).doubleValue(), fbVar2.d);
                                            if (a3 == null) {
                                                a3 = null;
                                                break;
                                            }
                                            if (((!a3.booleanValue() ? 1 : 0) ^ equals) != 0) {
                                                a3 = Boolean.valueOf(false);
                                                break;
                                            }
                                        } else if (obj3 instanceof String) {
                                            if (fbVar2.c == null) {
                                                if (fbVar2.d != null) {
                                                    if (!ew.i((String) obj3)) {
                                                        q().y().a("Invalid param value for number filter. event, param", m().a(str2), m().b(str5));
                                                        a3 = null;
                                                        break;
                                                    }
                                                    a3 = a((String) obj3, fbVar2.d);
                                                } else {
                                                    q().y().a("No filter for String param. event, param", m().a(str2), m().b(str5));
                                                    a3 = null;
                                                    break;
                                                }
                                            }
                                            a3 = a((String) obj3, fbVar2.c);
                                            if (a3 == null) {
                                                a3 = null;
                                                break;
                                            }
                                            if (((!a3.booleanValue() ? 1 : 0) ^ equals) != 0) {
                                                a3 = Boolean.valueOf(false);
                                                break;
                                            }
                                        } else if (obj3 == null) {
                                            q().C().a("Missing param for filter. event, param", m().a(str2), m().b(str5));
                                            a3 = Boolean.valueOf(false);
                                        } else {
                                            q().y().a("Unknown param type. event, param", m().a(str2), m().b(str5));
                                            a3 = null;
                                        }
                                        i2 = intValue + 1;
                                    }
                                    C = q().C();
                                    str4 = "Event filter result";
                                    if (a3 != null) {
                                        bool = a3;
                                    } else {
                                        obj2 = "null";
                                    }
                                    C.a(str4, obj2);
                                    if (a3 != null) {
                                        bitSet3.set(faVar.c.intValue());
                                        if (a3.booleanValue()) {
                                            bitSet4.set(faVar.c.intValue());
                                        }
                                    } else {
                                        hashSet.add(Integer.valueOf(intValue3));
                                    }
                                }
                            }
                        } else {
                            q().C().a("Skipping failed audience ID", Integer.valueOf(intValue3));
                        }
                    }
                    l = l2;
                    j = j2;
                    fjVar = fjVar2;
                    i3 = i4 + 1;
                }
                fkVarArr = fkVarArr2;
                str2 = str3;
                l2 = l;
                j2 = j;
                fjVar2 = fjVar;
                a = r_().a(str, fjVar3.d);
                if (a != null) {
                    ahVar = a.a();
                } else {
                    q().y().a("Event aggregate wasn't created during raw event logging. appId, event", av.a(str), m().a(str2));
                    ahVar = new ah(str, fjVar3.d, 1, 1, fjVar3.e.longValue(), 0, null, null, null);
                }
                r_().a(ahVar);
                j3 = ahVar.c;
                map = (Map) aVar4.get(str2);
                if (map != null) {
                    map2 = map;
                } else {
                    map = r_().f(str, str2);
                    if (map == null) {
                        map = new a();
                    }
                    aVar4.put(str2, map);
                    map2 = map;
                }
                while (r9.hasNext()) {
                    intValue3 = intValue22.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        bitSet = (BitSet) aVar2.get(Integer.valueOf(intValue3));
                        bitSet2 = (BitSet) aVar3.get(Integer.valueOf(intValue3));
                        if (((fi) aVar.get(Integer.valueOf(intValue3))) != null) {
                            bitSet3 = bitSet2;
                            bitSet4 = bitSet;
                        } else {
                            fiVar2 = new fi();
                            aVar.put(Integer.valueOf(intValue3), fiVar2);
                            fiVar2.f = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            aVar2.put(Integer.valueOf(intValue3), bitSet);
                            bitSet2 = new BitSet();
                            aVar3.put(Integer.valueOf(intValue3), bitSet2);
                            bitSet3 = bitSet2;
                            bitSet4 = bitSet;
                        }
                        for (fa faVar2 : (List) map2.get(Integer.valueOf(intValue3))) {
                            if (q().a(2)) {
                                q().C().a("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), faVar2.c, m().a(faVar2.d));
                                q().C().a("Filter definition", m().a(faVar2));
                            }
                            if (faVar2.c != null) {
                            }
                            q().y().a("Invalid event filter ID. appId, id", av.a(str), String.valueOf(faVar2.c));
                        }
                    } else {
                        q().C().a("Skipping failed audience ID", Integer.valueOf(intValue3));
                    }
                }
                l = l2;
                j = j2;
                fjVar = fjVar2;
                i3 = i4 + 1;
            }
        }
        if (foVarArr != null) {
            Map aVar6 = new a();
            for (fo foVar : foVarArr) {
                map = (Map) aVar6.get(foVar.d);
                if (map == null) {
                    map = r_().g(str, foVar.d);
                    if (map == null) {
                        map = new a();
                    }
                    aVar6.put(foVar.d, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                for (Integer intValue222 : r5.keySet()) {
                    length = intValue222.intValue();
                    if (hashSet.contains(Integer.valueOf(length))) {
                        q().C().a("Skipping failed audience ID", Integer.valueOf(length));
                    } else {
                        bitSet = (BitSet) aVar2.get(Integer.valueOf(length));
                        bitSet2 = (BitSet) aVar3.get(Integer.valueOf(length));
                        if (((fi) aVar.get(Integer.valueOf(length))) == null) {
                            fiVar2 = new fi();
                            aVar.put(Integer.valueOf(length), fiVar2);
                            fiVar2.f = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            aVar2.put(Integer.valueOf(length), bitSet);
                            bitSet2 = new BitSet();
                            aVar3.put(Integer.valueOf(length), bitSet2);
                        }
                        for (fd fdVar : (List) r5.get(Integer.valueOf(length))) {
                            if (q().a(2)) {
                                q().C().a("Evaluating filter. audience, filter, property", Integer.valueOf(length), fdVar.c, m().c(fdVar.d));
                                q().C().a("Filter definition", m().a(fdVar));
                            }
                            if (fdVar.c == null || fdVar.c.intValue() > Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) {
                                q().y().a("Invalid property filter ID. appId, id", av.a(str), String.valueOf(fdVar.c));
                                hashSet.add(Integer.valueOf(length));
                                break;
                            } else if (bitSet.get(fdVar.c.intValue())) {
                                q().C().a("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(length), fdVar.c);
                            } else {
                                Boolean bool2;
                                Object obj4;
                                fb fbVar3 = fdVar.e;
                                if (fbVar3 == null) {
                                    q().y().a("Missing property filter. property", m().c(foVar.d));
                                    bool2 = null;
                                } else {
                                    boolean equals2 = Boolean.TRUE.equals(fbVar3.e);
                                    if (foVar.f != null) {
                                        if (fbVar3.d == null) {
                                            q().y().a("No number filter for long property. property", m().c(foVar.d));
                                            bool2 = null;
                                        } else {
                                            bool2 = a(a(foVar.f.longValue(), fbVar3.d), equals2);
                                        }
                                    } else if (foVar.g != null) {
                                        if (fbVar3.d == null) {
                                            q().y().a("No number filter for double property. property", m().c(foVar.d));
                                            bool2 = null;
                                        } else {
                                            bool2 = a(a(foVar.g.doubleValue(), fbVar3.d), equals2);
                                        }
                                    } else if (foVar.e == null) {
                                        q().y().a("User property has no value, property", m().c(foVar.d));
                                        bool2 = null;
                                    } else if (fbVar3.c == null) {
                                        if (fbVar3.d == null) {
                                            q().y().a("No string or number filter defined. property", m().c(foVar.d));
                                        } else if (ew.i(foVar.e)) {
                                            bool2 = a(a(foVar.e, fbVar3.d), equals2);
                                        } else {
                                            q().y().a("Invalid user property value for Numeric number filter. property, value", m().c(foVar.d), foVar.e);
                                        }
                                        bool2 = null;
                                    } else {
                                        bool2 = a(a(foVar.e, fbVar3.c), equals2);
                                    }
                                }
                                ax C2 = q().C();
                                String str6 = "Property filter result";
                                if (bool2 == null) {
                                    obj4 = "null";
                                } else {
                                    Boolean obj42 = bool2;
                                }
                                C2.a(str6, obj42);
                                if (bool2 == null) {
                                    hashSet.add(Integer.valueOf(length));
                                } else {
                                    bitSet2.set(fdVar.c.intValue());
                                    if (bool2.booleanValue()) {
                                        bitSet.set(fdVar.c.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        fi[] fiVarArr = new fi[aVar2.size()];
        i2 = 0;
        for (Integer intValue2222 : aVar2.keySet()) {
            intValue = intValue2222.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue))) {
                fiVar2 = (fi) aVar.get(Integer.valueOf(intValue));
                fiVar = fiVar2 == null ? new fi() : fiVar2;
                int i5 = i2 + 1;
                fiVarArr[i2] = fiVar;
                fiVar.c = Integer.valueOf(intValue);
                fiVar.d = new fn();
                fiVar.d.d = ew.a((BitSet) aVar2.get(Integer.valueOf(intValue)));
                fiVar.d.c = ew.a((BitSet) aVar3.get(Integer.valueOf(intValue)));
                r_ = r_();
                obj = fiVar.d;
                r_.N();
                r_.c();
                ab.a(str);
                ab.a(obj);
                try {
                    byte[] bArr = new byte[obj.d()];
                    b a4 = b.a(bArr, bArr.length);
                    obj.a(a4);
                    a4.a();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("audience_id", Integer.valueOf(intValue));
                    contentValues.put("current_results", bArr);
                    try {
                        if (r_.x().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                            r_.q().v().a("Failed to insert filter results (got -1). appId", av.a(str));
                        }
                        i2 = i5;
                    } catch (SQLiteException e22) {
                        r_.q().v().a("Error storing filter results. appId", av.a(str), e22);
                        i2 = i5;
                    }
                } catch (IOException e3) {
                    r_.q().v().a("Configuration loss. Failed to serialize filter results. appId", av.a(str), e3);
                    i2 = i5;
                }
            }
        }
        return (fi[]) Arrays.copyOf(fiVarArr, i2);
    }

    protected final boolean t() {
        return false;
    }
}
