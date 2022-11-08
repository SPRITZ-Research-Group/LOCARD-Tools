package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;

public class GenericPdu {
    PduHeaders a;

    public GenericPdu() {
        this.a = null;
        this.a = new PduHeaders();
    }

    GenericPdu(PduHeaders headers) {
        this.a = null;
        this.a = headers;
    }

    public final int a() {
        return this.a.a(140);
    }

    public final void a(int value) throws InvalidHeaderValueException {
        this.a.a(value, 140);
    }

    public final void b() throws InvalidHeaderValueException {
        this.a.a(18, 141);
    }

    public void a(EncodedStringValue value) {
        this.a.a(value, 137);
    }
}
