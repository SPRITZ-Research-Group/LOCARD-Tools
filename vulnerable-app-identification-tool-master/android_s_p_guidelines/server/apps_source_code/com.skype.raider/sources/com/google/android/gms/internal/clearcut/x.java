package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.ah.c;
import java.io.IOException;
import java.util.Map.Entry;

final class x extends w<d> {
    x() {
    }

    final int a(Entry<?, ?> entry) {
        return ((d) entry.getKey()).a;
    }

    final aa<d> a(Object obj) {
        return ((c) obj).zzjv;
    }

    final void a(dk dkVar, Entry<?, ?> entry) throws IOException {
        d dVar = (d) entry.getKey();
        switch (dVar.b) {
            case DOUBLE:
                dkVar.a(dVar.a, ((Double) entry.getValue()).doubleValue());
                return;
            case FLOAT:
                dkVar.a(dVar.a, ((Float) entry.getValue()).floatValue());
                return;
            case INT64:
                dkVar.a(dVar.a, ((Long) entry.getValue()).longValue());
                return;
            case UINT64:
                dkVar.c(dVar.a, ((Long) entry.getValue()).longValue());
                return;
            case INT32:
                dkVar.c(dVar.a, ((Integer) entry.getValue()).intValue());
                return;
            case FIXED64:
                dkVar.d(dVar.a, ((Long) entry.getValue()).longValue());
                return;
            case FIXED32:
                dkVar.d(dVar.a, ((Integer) entry.getValue()).intValue());
                return;
            case BOOL:
                dkVar.a(dVar.a, ((Boolean) entry.getValue()).booleanValue());
                return;
            case UINT32:
                dkVar.e(dVar.a, ((Integer) entry.getValue()).intValue());
                return;
            case SFIXED32:
                dkVar.a(dVar.a, ((Integer) entry.getValue()).intValue());
                return;
            case SFIXED64:
                dkVar.b(dVar.a, ((Long) entry.getValue()).longValue());
                return;
            case SINT32:
                dkVar.f(dVar.a, ((Integer) entry.getValue()).intValue());
                return;
            case SINT64:
                dkVar.e(dVar.a, ((Long) entry.getValue()).longValue());
                return;
            case ENUM:
                dkVar.c(dVar.a, ((Integer) entry.getValue()).intValue());
                return;
            case BYTES:
                dkVar.a(dVar.a, (h) entry.getValue());
                return;
            case STRING:
                dkVar.a(dVar.a, (String) entry.getValue());
                return;
            case GROUP:
                dkVar.b(dVar.a, entry.getValue(), bv.a().a(entry.getValue().getClass()));
                return;
            case MESSAGE:
                dkVar.a(dVar.a, entry.getValue(), bv.a().a(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }

    final void a(Object obj, aa<d> aaVar) {
        ((c) obj).zzjv = aaVar;
    }

    final boolean a(bk bkVar) {
        return bkVar instanceof c;
    }

    final aa<d> b(Object obj) {
        aa<d> a = a(obj);
        if (!a.d()) {
            return a;
        }
        aa aaVar = (aa) a.clone();
        a(obj, aaVar);
        return aaVar;
    }

    final void c(Object obj) {
        a(obj).c();
    }
}
