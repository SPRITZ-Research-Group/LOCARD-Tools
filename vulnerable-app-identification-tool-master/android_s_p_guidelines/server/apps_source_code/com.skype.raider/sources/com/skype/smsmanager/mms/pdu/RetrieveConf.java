package com.skype.smsmanager.mms.pdu;

import com.skype.smsmanager.mms.InvalidHeaderValueException;

public class RetrieveConf extends MultimediaMessagePdu {
    public RetrieveConf() throws InvalidHeaderValueException {
        a(132);
    }

    RetrieveConf(PduHeaders headers, PduBody body) {
        super(headers, body);
    }

    public final byte[] e() {
        return this.a.b(132);
    }

    public final void a(EncodedStringValue value) {
        this.a.a(value, 137);
    }
}
