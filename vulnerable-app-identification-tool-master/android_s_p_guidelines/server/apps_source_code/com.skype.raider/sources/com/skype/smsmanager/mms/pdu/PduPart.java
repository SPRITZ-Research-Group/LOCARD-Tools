package com.skype.smsmanager.mms.pdu;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class PduPart {
    static final byte[] a = "from-data".getBytes();
    static final byte[] b = "attachment".getBytes();
    static final byte[] c = "inline".getBytes();
    private Map<Integer, Object> d;
    private Uri e;
    private byte[] f;

    public PduPart() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.d = new HashMap();
    }

    public final void a(byte[] data) {
        if (data != null) {
            this.f = new byte[data.length];
            System.arraycopy(data, 0, this.f, 0, data.length);
        }
    }

    public final byte[] a() {
        if (this.f == null) {
            return null;
        }
        byte[] byteArray = new byte[this.f.length];
        System.arraycopy(this.f, 0, byteArray, 0, this.f.length);
        return byteArray;
    }

    public final Uri b() {
        return this.e;
    }

    public final void b(byte[] contentId) {
        if (contentId == null || contentId.length == 0) {
            throw new IllegalArgumentException("Content-Id may not be null or empty.");
        } else if (contentId.length > 1 && ((char) contentId[0]) == '<' && ((char) contentId[contentId.length - 1]) == '>') {
            this.d.put(Integer.valueOf(192), contentId);
        } else {
            byte[] buffer = new byte[(contentId.length + 2)];
            buffer[0] = (byte) 60;
            buffer[buffer.length - 1] = (byte) 62;
            System.arraycopy(contentId, 0, buffer, 1, contentId.length);
            this.d.put(Integer.valueOf(192), buffer);
        }
    }

    public final byte[] c() {
        return (byte[]) this.d.get(Integer.valueOf(192));
    }

    public final void a(int charset) {
        this.d.put(Integer.valueOf(129), Integer.valueOf(charset));
    }

    public final int d() {
        Integer charset = (Integer) this.d.get(Integer.valueOf(129));
        if (charset == null) {
            return 0;
        }
        return charset.intValue();
    }

    public final void c(byte[] contentLocation) {
        if (contentLocation == null) {
            throw new NullPointerException("null content-location");
        }
        this.d.put(Integer.valueOf(142), contentLocation);
    }

    public final byte[] e() {
        return (byte[]) this.d.get(Integer.valueOf(142));
    }

    public final void d(byte[] contentDisposition) {
        if (contentDisposition == null) {
            throw new NullPointerException("null content-disposition");
        }
        this.d.put(Integer.valueOf(197), contentDisposition);
    }

    public final void e(byte[] contentType) {
        if (contentType == null) {
            throw new NullPointerException("null content-type");
        }
        this.d.put(Integer.valueOf(145), contentType);
    }

    public final byte[] f() {
        return (byte[]) this.d.get(Integer.valueOf(145));
    }

    public final void f(byte[] contentTransferEncoding) {
        if (contentTransferEncoding == null) {
            throw new NullPointerException("null content-transfer-encoding");
        }
        this.d.put(Integer.valueOf(200), contentTransferEncoding);
    }

    public final byte[] g() {
        return (byte[]) this.d.get(Integer.valueOf(200));
    }

    public final void g(byte[] name) {
        if (name == null) {
            throw new NullPointerException("null content-id");
        }
        this.d.put(Integer.valueOf(151), name);
    }

    public final byte[] h() {
        return (byte[]) this.d.get(Integer.valueOf(151));
    }

    public final void h(byte[] fileName) {
        if (fileName == null) {
            throw new NullPointerException("null content-id");
        }
        this.d.put(Integer.valueOf(152), fileName);
    }

    public final byte[] i() {
        return (byte[]) this.d.get(Integer.valueOf(152));
    }
}
