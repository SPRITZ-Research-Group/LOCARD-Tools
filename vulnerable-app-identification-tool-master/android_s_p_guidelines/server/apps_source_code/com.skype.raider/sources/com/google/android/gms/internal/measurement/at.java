package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.e;
import com.google.android.gms.measurement.AppMeasurement.a;
import com.google.android.gms.measurement.AppMeasurement.c;
import com.google.android.gms.measurement.AppMeasurement.d;
import java.util.concurrent.atomic.AtomicReference;

public final class at extends ct {
    private static final AtomicReference<String[]> a = new AtomicReference();
    private static final AtomicReference<String[]> b = new AtomicReference();
    private static final AtomicReference<String[]> c = new AtomicReference();

    at(bx bxVar) {
        super(bxVar);
    }

    @Nullable
    private final String a(zzer zzer) {
        return zzer == null ? null : !v() ? zzer.toString() : a(zzer.b());
    }

    @Nullable
    private static String a(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        int i = 0;
        ab.a((Object) strArr);
        ab.a((Object) strArr2);
        ab.a((Object) atomicReference);
        ab.b(strArr.length == strArr2.length);
        while (i < strArr.length) {
            if (ew.b(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = (String[]) atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(strArr2[i]);
                        stringBuilder.append("(");
                        stringBuilder.append(strArr[i]);
                        stringBuilder.append(")");
                        strArr3[i] = stringBuilder.toString();
                    }
                    str = strArr3[i];
                }
                return str;
            }
            i++;
        }
        return str;
    }

    private static void a(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
    }

    private final void a(StringBuilder stringBuilder, int i, fb fbVar) {
        if (fbVar != null) {
            a(stringBuilder, i);
            stringBuilder.append("filter {\n");
            a(stringBuilder, i, "complement", fbVar.e);
            a(stringBuilder, i, "param_name", b(fbVar.f));
            int i2 = i + 1;
            String str = "string_filter";
            fe feVar = fbVar.c;
            if (feVar != null) {
                a(stringBuilder, i2);
                stringBuilder.append(str);
                stringBuilder.append(" {\n");
                if (feVar.c != null) {
                    Object obj = "UNKNOWN_MATCH_TYPE";
                    switch (feVar.c.intValue()) {
                        case 1:
                            obj = "REGEXP";
                            break;
                        case 2:
                            obj = "BEGINS_WITH";
                            break;
                        case 3:
                            obj = "ENDS_WITH";
                            break;
                        case 4:
                            obj = "PARTIAL";
                            break;
                        case 5:
                            obj = "EXACT";
                            break;
                        case 6:
                            obj = "IN_LIST";
                            break;
                    }
                    a(stringBuilder, i2, "match_type", obj);
                }
                a(stringBuilder, i2, "expression", feVar.d);
                a(stringBuilder, i2, "case_sensitive", feVar.e);
                if (feVar.f.length > 0) {
                    a(stringBuilder, i2 + 1);
                    stringBuilder.append("expression_list {\n");
                    for (String str2 : feVar.f) {
                        a(stringBuilder, i2 + 2);
                        stringBuilder.append(str2);
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append("}\n");
                }
                a(stringBuilder, i2);
                stringBuilder.append("}\n");
            }
            a(stringBuilder, i + 1, "number_filter", fbVar.d);
            a(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void a(StringBuilder stringBuilder, int i, String str, fc fcVar) {
        if (fcVar != null) {
            a(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (fcVar.c != null) {
                Object obj = "UNKNOWN_COMPARISON_TYPE";
                switch (fcVar.c.intValue()) {
                    case 1:
                        obj = "LESS_THAN";
                        break;
                    case 2:
                        obj = "GREATER_THAN";
                        break;
                    case 3:
                        obj = "EQUAL";
                        break;
                    case 4:
                        obj = "BETWEEN";
                        break;
                }
                a(stringBuilder, i, "comparison_type", obj);
            }
            a(stringBuilder, i, "match_as_float", fcVar.d);
            a(stringBuilder, i, "comparison_value", fcVar.e);
            a(stringBuilder, i, "min_comparison_value", fcVar.f);
            a(stringBuilder, i, "max_comparison_value", fcVar.g);
            a(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void a(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj != null) {
            a(stringBuilder, i + 1);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(obj);
            stringBuilder.append(10);
        }
    }

    private static void a(StringBuilder stringBuilder, String str, fn fnVar) {
        int i = 0;
        if (fnVar != null) {
            int i2;
            int i3;
            a(stringBuilder, 3);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (fnVar.d != null) {
                a(stringBuilder, 4);
                stringBuilder.append("results: ");
                long[] jArr = fnVar.d;
                int length = jArr.length;
                i2 = 0;
                i3 = 0;
                while (i2 < length) {
                    Long valueOf = Long.valueOf(jArr[i2]);
                    int i4 = i3 + 1;
                    if (i3 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf);
                    i2++;
                    i3 = i4;
                }
                stringBuilder.append(10);
            }
            if (fnVar.c != null) {
                a(stringBuilder, 4);
                stringBuilder.append("status: ");
                long[] jArr2 = fnVar.c;
                int length2 = jArr2.length;
                i2 = 0;
                while (i < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i]);
                    i3 = i2 + 1;
                    if (i2 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf2);
                    i++;
                    i2 = i3;
                }
                stringBuilder.append(10);
            }
            a(stringBuilder, 3);
            stringBuilder.append("}\n");
        }
    }

    private final boolean v() {
        return this.q.q().a(3);
    }

    @Nullable
    protected final String a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!v()) {
            return bundle.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append("Bundle[{");
            }
            stringBuilder.append(b(str));
            stringBuilder.append("=");
            stringBuilder.append(bundle.get(str));
        }
        stringBuilder.append("}]");
        return stringBuilder.toString();
    }

    @Nullable
    protected final String a(ag agVar) {
        if (agVar == null) {
            return null;
        }
        if (!v()) {
            return agVar.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Event{appId='");
        stringBuilder.append(agVar.a);
        stringBuilder.append("', name='");
        stringBuilder.append(a(agVar.b));
        stringBuilder.append("', params=");
        stringBuilder.append(a(agVar.e));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    protected final String a(fa faVar) {
        int i = 0;
        if (faVar == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nevent_filter {\n");
        a(stringBuilder, 0, "filter_id", faVar.c);
        a(stringBuilder, 0, "event_name", a(faVar.d));
        a(stringBuilder, 1, "event_count_filter", faVar.f);
        stringBuilder.append("  filters {\n");
        fb[] fbVarArr = faVar.e;
        int length = fbVarArr.length;
        while (i < length) {
            a(stringBuilder, 2, fbVarArr[i]);
            i++;
        }
        a(stringBuilder, 1);
        stringBuilder.append("}\n}\n");
        return stringBuilder.toString();
    }

    protected final String a(fd fdVar) {
        if (fdVar == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nproperty_filter {\n");
        a(stringBuilder, 0, "filter_id", fdVar.c);
        a(stringBuilder, 0, "property_name", c(fdVar.d));
        a(stringBuilder, 1, fdVar.e);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    protected final String a(fl flVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nbatch {\n");
        if (flVar.c != null) {
            for (fm fmVar : flVar.c) {
                if (!(fmVar == null || fmVar == null)) {
                    a(stringBuilder, 1);
                    stringBuilder.append("bundle {\n");
                    a(stringBuilder, 1, "protocol_version", fmVar.c);
                    a(stringBuilder, 1, "platform", fmVar.k);
                    a(stringBuilder, 1, "gmp_version", fmVar.s);
                    a(stringBuilder, 1, "uploading_gmp_version", fmVar.t);
                    a(stringBuilder, 1, "config_version", fmVar.G);
                    a(stringBuilder, 1, "gmp_app_id", fmVar.A);
                    a(stringBuilder, 1, "app_id", fmVar.q);
                    a(stringBuilder, 1, "app_version", fmVar.r);
                    a(stringBuilder, 1, "app_version_major", fmVar.E);
                    a(stringBuilder, 1, "firebase_instance_id", fmVar.D);
                    a(stringBuilder, 1, "dev_cert_hash", fmVar.x);
                    a(stringBuilder, 1, "app_store", fmVar.p);
                    a(stringBuilder, 1, "upload_timestamp_millis", fmVar.f);
                    a(stringBuilder, 1, "start_timestamp_millis", fmVar.g);
                    a(stringBuilder, 1, "end_timestamp_millis", fmVar.h);
                    a(stringBuilder, 1, "previous_bundle_start_timestamp_millis", fmVar.i);
                    a(stringBuilder, 1, "previous_bundle_end_timestamp_millis", fmVar.j);
                    a(stringBuilder, 1, "app_instance_id", fmVar.w);
                    a(stringBuilder, 1, "resettable_device_id", fmVar.u);
                    a(stringBuilder, 1, "device_id", fmVar.F);
                    a(stringBuilder, 1, "limited_ad_tracking", fmVar.v);
                    a(stringBuilder, 1, "os_version", fmVar.l);
                    a(stringBuilder, 1, "device_model", fmVar.m);
                    a(stringBuilder, 1, "user_default_language", fmVar.n);
                    a(stringBuilder, 1, "time_zone_offset_minutes", fmVar.o);
                    a(stringBuilder, 1, "bundle_sequential_index", fmVar.y);
                    a(stringBuilder, 1, "service_upload", fmVar.B);
                    a(stringBuilder, 1, "health_monitor", fmVar.z);
                    if (!(fmVar.H == null || fmVar.H.longValue() == 0)) {
                        a(stringBuilder, 1, "android_id", fmVar.H);
                    }
                    if (fmVar.J != null) {
                        a(stringBuilder, 1, "retry_counter", fmVar.J);
                    }
                    fo[] foVarArr = fmVar.e;
                    if (foVarArr != null) {
                        for (fo foVar : foVarArr) {
                            if (foVar != null) {
                                a(stringBuilder, 2);
                                stringBuilder.append("user_property {\n");
                                a(stringBuilder, 2, "set_timestamp_millis", foVar.c);
                                a(stringBuilder, 2, "name", c(foVar.d));
                                a(stringBuilder, 2, "string_value", foVar.e);
                                a(stringBuilder, 2, "int_value", foVar.f);
                                a(stringBuilder, 2, "double_value", foVar.g);
                                a(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    fi[] fiVarArr = fmVar.C;
                    if (fiVarArr != null) {
                        for (fi fiVar : fiVarArr) {
                            if (fiVar != null) {
                                a(stringBuilder, 2);
                                stringBuilder.append("audience_membership {\n");
                                a(stringBuilder, 2, "audience_id", fiVar.c);
                                a(stringBuilder, 2, "new_audience", fiVar.f);
                                a(stringBuilder, "current_data", fiVar.d);
                                a(stringBuilder, "previous_data", fiVar.e);
                                a(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    fj[] fjVarArr = fmVar.d;
                    if (fjVarArr != null) {
                        for (fj fjVar : fjVarArr) {
                            if (fjVar != null) {
                                a(stringBuilder, 2);
                                stringBuilder.append("event {\n");
                                a(stringBuilder, 2, "name", a(fjVar.d));
                                a(stringBuilder, 2, "timestamp_millis", fjVar.e);
                                a(stringBuilder, 2, "previous_timestamp_millis", fjVar.f);
                                a(stringBuilder, 2, "count", fjVar.g);
                                fk[] fkVarArr = fjVar.c;
                                if (fkVarArr != null) {
                                    for (fk fkVar : fkVarArr) {
                                        if (fkVar != null) {
                                            a(stringBuilder, 3);
                                            stringBuilder.append("param {\n");
                                            a(stringBuilder, 3, "name", b(fkVar.c));
                                            a(stringBuilder, 3, "string_value", fkVar.d);
                                            a(stringBuilder, 3, "int_value", fkVar.e);
                                            a(stringBuilder, 3, "double_value", fkVar.f);
                                            a(stringBuilder, 3);
                                            stringBuilder.append("}\n");
                                        }
                                    }
                                }
                                a(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    a(stringBuilder, 1);
                    stringBuilder.append("}\n");
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    @Nullable
    protected final String a(zzeu zzeu) {
        if (zzeu == null) {
            return null;
        }
        if (!v()) {
            return zzeu.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("origin=");
        stringBuilder.append(zzeu.c);
        stringBuilder.append(",name=");
        stringBuilder.append(a(zzeu.a));
        stringBuilder.append(",params=");
        stringBuilder.append(a(zzeu.b));
        return stringBuilder.toString();
    }

    @Nullable
    protected final String a(String str) {
        return str == null ? null : v() ? a(str, a.b, a.a, a) : str;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @Nullable
    protected final String b(String str) {
        return str == null ? null : v() ? a(str, c.b, c.a, b) : str;
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @Nullable
    protected final String c(String str) {
        if (str == null) {
            return null;
        }
        if (!v()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return a(str, d.b, d.a, c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("experiment_id");
        stringBuilder.append("(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ Context k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ar l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ at m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ ew n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ee o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ bs p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ av q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ bf r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    protected final boolean t() {
        return false;
    }
}
