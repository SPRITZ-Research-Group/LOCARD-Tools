package com.skype.smsmanager.mms.pdu;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PduBody {
    private Vector<PduPart> a;
    private Map<String, PduPart> b;
    private Map<String, PduPart> c;
    private Map<String, PduPart> d;
    private Map<String, PduPart> e;

    public PduBody() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = new Vector();
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
    }

    private void c(PduPart part) {
        byte[] contentId = part.c();
        if (contentId != null) {
            this.b.put(new String(contentId), part);
        }
        byte[] contentLocation = part.e();
        if (contentLocation != null) {
            this.c.put(new String(contentLocation), part);
        }
        byte[] name = part.h();
        if (name != null) {
            this.d.put(new String(name), part);
        }
        byte[] fileName = part.i();
        if (fileName != null) {
            this.e.put(new String(fileName), part);
        }
    }

    public final boolean a(PduPart part) {
        if (part == null) {
            throw new NullPointerException();
        }
        c(part);
        return this.a.add(part);
    }

    public final void b(PduPart part) {
        if (part == null) {
            throw new NullPointerException();
        }
        c(part);
        this.a.add(0, part);
    }

    public final void a() {
        this.a.clear();
    }

    public final PduPart a(int index) {
        return (PduPart) this.a.get(index);
    }

    public final int b() {
        return this.a.size();
    }
}
