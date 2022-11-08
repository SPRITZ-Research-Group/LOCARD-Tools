package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;

public class SendReq extends MultimediaMessagePdu {
    public SendReq() {
        try {
            a(128);
            b();
            this.a.a("application/vnd.wap.multipart.related".getBytes(), 132);
            a(new EncodedStringValue("insert-address-token".getBytes()));
            this.a.a(("T" + Long.toHexString(System.currentTimeMillis())).getBytes(), 152);
        } catch (InvalidHeaderValueException e) {
            throw new RuntimeException(e);
        }
    }

    SendReq(PduHeaders headers, PduBody body) {
        super(headers, body);
    }

    public final void e() throws InvalidHeaderValueException {
        this.a.a(129, 134);
    }

    public final void f() {
        this.a.a(604800, 136);
    }

    public final void a(long value) {
        this.a.a(value, 142);
    }

    public final void a(byte[] value) {
        this.a.a(value, 138);
    }

    public final void g() throws InvalidHeaderValueException {
        this.a.a(129, 144);
    }

    public final void a(EncodedStringValue[] value) {
        this.a.a(value);
    }
}
